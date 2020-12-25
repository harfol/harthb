package org.thingsboard.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.thingsboard.server.common.data.exception.ThingsboardException;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientInfo;
import org.thingsboard.server.common.data.oauth2.OAuth2ClientsParams;
import org.thingsboard.server.common.data.oauth2.SchemeType;
import org.thingsboard.server.dao.oauth2.OAuth2Configuration;
import org.thingsboard.server.queue.util.TbCoreComponent;
import org.thingsboard.server.service.security.permission.Operation;
import org.thingsboard.server.service.security.permission.Resource;
import org.thingsboard.server.utils.MiscUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@TbCoreComponent
@RequestMapping("/api")
@Slf4j
public class OAuth2Controller extends BaseController {

    @Autowired
    private OAuth2Configuration oAuth2Configuration;

    @RequestMapping(value = "/noauth/oauth2Clients", method = RequestMethod.POST)
    @ResponseBody
    public List<OAuth2ClientInfo> getOAuth2Clients(HttpServletRequest request) throws ThingsboardException {
        try {
            return oAuth2Service.getOAuth2Clients(MiscUtils.getScheme(request), MiscUtils.getDomainNameAndPort(request));
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/oauth2/config", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public OAuth2ClientsParams getCurrentOAuth2Params() throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.OAUTH2_CONFIGURATION_INFO, Operation.READ);
            return oAuth2Service.findOAuth2Params();
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/oauth2/config", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public OAuth2ClientsParams saveOAuth2Params(@RequestBody OAuth2ClientsParams oauth2Params) throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.OAUTH2_CONFIGURATION_INFO, Operation.WRITE);
            oAuth2Service.saveOAuth2Params(oauth2Params);
            return oAuth2Service.findOAuth2Params();
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAnyAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/oauth2/loginProcessingUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getLoginProcessingUrl() throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.OAUTH2_CONFIGURATION_INFO, Operation.READ);
            return "\"" + oAuth2Configuration.getLoginProcessingUrl() + "\"";
        } catch (Exception e) {
            throw handleException(e);
        }
    }
}
