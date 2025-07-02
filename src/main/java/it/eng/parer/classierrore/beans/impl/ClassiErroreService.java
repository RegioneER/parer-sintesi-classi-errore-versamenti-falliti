/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package it.eng.parer.classierrore.beans.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.parer.classierrore.beans.IClassiErroreDao;
import it.eng.parer.classierrore.beans.IClassiErroreService;
import it.eng.parer.classierrore.beans.dto.ClasseErroreBean;
import it.eng.parer.classierrore.beans.dto.ClasseErroreDto;
import it.eng.parer.classierrore.beans.dto.CodiceErroreBean;
import it.eng.parer.classierrore.beans.exceptions.AppGenericRuntimeException;
import it.eng.parer.classierrore.beans.exceptions.ErrorCategory;
import it.eng.parer.classierrore.beans.model.ClassiErroreResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
public class ClassiErroreService implements IClassiErroreService {

    private static final Logger log = LoggerFactory.getLogger(ClassiErroreService.class);

    @Inject
    IClassiErroreDao classiErroreDao;

    @Override
    @Transactional(value = TxType.REQUIRED, rollbackOn = {
	    AppGenericRuntimeException.class })
    public ClassiErroreResponse listClassiErrorePerVarsFalliti(String nmUserid, String nmAmbiente,
	    String nmEnte, String nmStrut, String uri) {
	String struttura = nmAmbiente + " / " + nmEnte + " / " + nmStrut;
	try {
	    // Verifico se l'utente è abilitato alla struttura, in caso contrario restituisco 0
	    // classi di errore
	    boolean utenteAbilitato = classiErroreDao.isUtenteAbilitatoStrut(nmUserid, nmAmbiente,
		    nmEnte, nmStrut);

	    // Inizializzo le informazioni da restituire
	    List<ClasseErroreDto> listaClassiErrore = new ArrayList<>();
	    int totaleClassiErrore = 0;

	    if (utenteAbilitato) {
		// Ricavo le informazioni da restituire
		listaClassiErrore = elabClassiErrore(nmAmbiente, nmEnte, nmStrut);
		totaleClassiErrore = listaClassiErrore.stream().mapToInt(ClasseErroreDto::getTotale)
			.sum();
	    }

	    log.atInfo().log("ClassiErrore - Recuperati {} classi errore per la struttura {}",
		    totaleClassiErrore, struttura);
	    // Ritorna la response
	    return new ClassiErroreResponse(struttura, listaClassiErrore, totaleClassiErrore, uri);

	} catch (Exception e) {
	    throw AppGenericRuntimeException.builder().category(ErrorCategory.INTERNAL_ERROR)
		    .cause(e)
		    .message(
			    "Errore estrazione lista classi errore per nmUserid {0} e struttura {1}",
			    nmUserid, struttura)
		    .build();
	}
    }

    private List<ClasseErroreDto> elabClassiErrore(String nmAmbiente, String nmEnte,
	    String nmStrut) {
	log.atInfo().log(
		"Classi Errore per Versamenti Falliti - Recupero le classi di errore per i versamenti falliti della struttura {}",
		nmAmbiente + " / " + nmEnte + " / " + nmStrut);
	Stream<CodiceErroreBean> result = classiErroreDao.findCodiciErrorePerVersFalliti(nmAmbiente,
		nmEnte, nmStrut);
	// Li "organizzo" in oggetti Map e ricavo il numero di tipi dato
	return processStreamCodiciErrore(result);
    }

    public static List<ClasseErroreDto> processStreamCodiciErrore(
	    Stream<CodiceErroreBean> objectsStream) {
	// Pre-operazione: "ESTRAGGO" le classi errori da ogni codice errore
	// Prendo lo stream in input e ricavo un nuovo stream questa volta contenente non il codice
	// errore ma la classe
	Stream<ClasseErroreBean> modifiedStream = objectsStream.map(obj -> {
	    String clErrLast = extractClErrLast(obj.getCdErrPrinc());
	    return new ClasseErroreBean(obj.getCdRegistroKeyUnitaDoc(), obj.getAaKeyUnitaDoc(),
		    obj.getCdKeyUnitaDoc(), clErrLast, obj.getDtLastSesErr());
	});

	// 1. Crea una mappa per tenere traccia degli errori più recenti per ogni terna di chiavi
	// usando lo stream
	// modificato
	// La mappa è composta da chiave (terna registro-anno-numero) e valore (il record completo
	// di ClasseErroreBean)
	Map<String, ClasseErroreBean> mostRecentByTriple =
		// Raggruppa gli oggetti dello stream di ClasseErroreBean in base alla chiave
		// registro-anno-numero:
		// così creo una mappa "intermedia" composta da chiave la terna e
		modifiedStream
			.collect(Collectors.groupingBy(
				obj -> obj.getCdRegistroKeyUnitaDoc() + obj.getAaKeyUnitaDoc()
					+ obj.getCdKeyUnitaDoc(),
				// "Riduco" gli oggetti con stessa chiave (terna di valori)
				// restituendo l'oggetto con
				// dtLastSesErr più recente,
				// quindi per ogni terna tengo solo il più recente
				Collectors.reducing((obj1,
					obj2) -> obj1.getDtLastSesErr()
						.isAfter(obj2.getDtLastSesErr()) ? obj1 : obj2)))
			.values().stream().map(Optional::get)
			// Creo così la mappa finale: sempre la famosa tena come chiave e l'oggetto
			// ClasseErroreBean
			// come con la data più recente
			.collect(
				Collectors.toMap(
					obj -> obj.getCdRegistroKeyUnitaDoc()
						+ obj.getAaKeyUnitaDoc() + obj.getCdKeyUnitaDoc(),
					obj -> obj));

	// 2. Raggruppa gli errori più recenti per cdErrPrinc e conta
	Map<String, List<ClasseErroreBean>> errorsByCode = new TreeMap<>(mostRecentByTriple.values()
		.stream().collect(Collectors.groupingBy(ClasseErroreBean::getClErrLast)));

	// 3. Crea la mappa finale con conteggi e data più recente
	List<ClasseErroreDto> result = new ArrayList<>();
	for (Map.Entry<String, List<ClasseErroreBean>> entry : errorsByCode.entrySet()) {
	    String errCode = entry.getKey();
	    List<ClasseErroreBean> objectsWithCode = entry.getValue();
	    LocalDateTime maxLocalDateTime = objectsWithCode.stream()
		    .map(ClasseErroreBean::getDtLastSesErr).max(LocalDateTime::compareTo)
		    .orElse(null); // Gestisce il caso in cui la lista è vuota

	    result.add(new ClasseErroreDto(errCode, objectsWithCode.size(), maxLocalDateTime));
	}

	return result;
    }

    private static String extractClErrLast(String cdErrPrinc) {
	if (cdErrPrinc != null) {
	    return cdErrPrinc.contains("-0") ? cdErrPrinc.substring(0, cdErrPrinc.indexOf("-"))
		    : cdErrPrinc;
	}
	return "_non_disponibile";
    }

}
