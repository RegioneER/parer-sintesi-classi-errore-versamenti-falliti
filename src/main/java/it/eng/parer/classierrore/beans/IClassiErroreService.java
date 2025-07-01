package it.eng.parer.classierrore.beans;

import it.eng.parer.classierrore.beans.model.ClassiErroreResponse;
import jakarta.validation.constraints.NotBlank;

public interface IClassiErroreService {

    /**
     * Restituisce la lista delle classi di errore per versamenti falliti
     *
     * @param userId     userdId utente associato alla struttura per la quale ricercare i versamenti
     *                   falliti
     * @param nmAmbiente nome ambiente SACER
     * @param nmEnte     nome ente SACER
     * @param nmStrut    nome struttura SACER
     * @param uri        absolute uri
     *
     * @return la response decorata (vedi {@link ClassiErroreResponse})
     */
    ClassiErroreResponse listClassiErrorePerVarsFalliti(
	    @NotBlank(message = "userId non valorizzato") String userId, String nmAmbiente,
	    String nmEnte, String nmStrut, String uri);

}
