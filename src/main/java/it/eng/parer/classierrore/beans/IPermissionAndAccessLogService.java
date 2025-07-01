/**
 *
 */
package it.eng.parer.classierrore.beans;

import jakarta.validation.constraints.NotBlank;

public interface IPermissionAndAccessLogService {

    public boolean isUserEnabledOnService(
	    @NotBlank(message = "userId non valorizzato") String nmUserid);

    void createAccessLog(@NotBlank(message = "userId non valorizzato") String nmUserid,
	    String ipAddr);

}
