package org.thingsboard.server.service.security.system;

import org.springframework.security.core.AuthenticationException;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.security.UserCredentials;
import org.thingsboard.server.dao.exception.DataValidationException;
import org.thingsboard.server.common.data.security.model.SecuritySettings;

import javax.servlet.http.HttpServletRequest;

public interface SystemSecurityService {

    SecuritySettings getSecuritySettings(TenantId tenantId);

    SecuritySettings saveSecuritySettings(TenantId tenantId, SecuritySettings securitySettings);

    void validateUserCredentials(TenantId tenantId, UserCredentials userCredentials, String username, String password) throws AuthenticationException;

    void validatePassword(TenantId tenantId, String password, UserCredentials userCredentials) throws DataValidationException;

    String getBaseUrl(TenantId tenantId, CustomerId customerId, HttpServletRequest httpServletRequest);

}
