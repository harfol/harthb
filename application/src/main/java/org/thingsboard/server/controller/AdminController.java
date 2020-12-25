package org.thingsboard.server.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thingsboard.rule.engine.api.MailService;
import org.thingsboard.rule.engine.api.SmsService;
import org.thingsboard.server.common.data.sms.config.TestSmsRequest;
import org.thingsboard.server.common.data.AdminSettings;
import org.thingsboard.server.common.data.UpdateMessage;
import org.thingsboard.server.common.data.exception.ThingsboardException;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.security.model.SecuritySettings;
import org.thingsboard.server.dao.settings.AdminSettingsService;
import org.thingsboard.server.queue.util.TbCoreComponent;
import org.thingsboard.server.service.security.permission.Operation;
import org.thingsboard.server.service.security.permission.Resource;
import org.thingsboard.server.service.security.system.SystemSecurityService;
import org.thingsboard.server.service.update.UpdateService;

@RestController
@TbCoreComponent
@RequestMapping("/api/admin")
public class AdminController extends BaseController {

    @Autowired
    private MailService mailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private AdminSettingsService adminSettingsService;

    @Autowired
    private SystemSecurityService systemSecurityService;

    @Autowired
    private UpdateService updateService;

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/settings/{key}", method = RequestMethod.GET)
    @ResponseBody
    public AdminSettings getAdminSettings(@PathVariable("key") String key) throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.ADMIN_SETTINGS, Operation.READ);
            AdminSettings adminSettings = checkNotNull(adminSettingsService.findAdminSettingsByKey(TenantId.SYS_TENANT_ID, key));
            if (adminSettings.getKey().equals("mail")) {
                ((ObjectNode) adminSettings.getJsonValue()).put("password", "");
            }
            return adminSettings;
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    @ResponseBody
    public AdminSettings saveAdminSettings(@RequestBody AdminSettings adminSettings) throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.ADMIN_SETTINGS, Operation.WRITE);
            adminSettings = checkNotNull(adminSettingsService.saveAdminSettings(TenantId.SYS_TENANT_ID, adminSettings));
            if (adminSettings.getKey().equals("mail")) {
                mailService.updateMailConfiguration();
                ((ObjectNode) adminSettings.getJsonValue()).put("password", "");
            } else if (adminSettings.getKey().equals("sms")) {
                smsService.updateSmsConfiguration();
            }
            return adminSettings;
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/securitySettings", method = RequestMethod.GET)
    @ResponseBody
    public SecuritySettings getSecuritySettings() throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.ADMIN_SETTINGS, Operation.READ);
            return checkNotNull(systemSecurityService.getSecuritySettings(TenantId.SYS_TENANT_ID));
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/securitySettings", method = RequestMethod.POST)
    @ResponseBody
    public SecuritySettings saveSecuritySettings(@RequestBody SecuritySettings securitySettings) throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.ADMIN_SETTINGS, Operation.WRITE);
            securitySettings = checkNotNull(systemSecurityService.saveSecuritySettings(TenantId.SYS_TENANT_ID, securitySettings));
            return securitySettings;
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/settings/testMail", method = RequestMethod.POST)
    public void sendTestMail(@RequestBody AdminSettings adminSettings) throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.ADMIN_SETTINGS, Operation.READ);
            adminSettings = checkNotNull(adminSettings);
            if (adminSettings.getKey().equals("mail")) {
                String email = getCurrentUser().getEmail();
                mailService.sendTestMail(adminSettings.getJsonValue(), email);
            }
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/settings/testSms", method = RequestMethod.POST)
    public void sendTestSms(@RequestBody TestSmsRequest testSmsRequest) throws ThingsboardException {
        try {
            accessControlService.checkPermission(getCurrentUser(), Resource.ADMIN_SETTINGS, Operation.READ);
            smsService.sendTestSms(testSmsRequest);
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    @PreAuthorize("hasAuthority('SYS_ADMIN')")
    @RequestMapping(value = "/updates", method = RequestMethod.GET)
    @ResponseBody
    public UpdateMessage checkUpdates() throws ThingsboardException {
        try {
            return updateService.checkUpdates();
        } catch (Exception e) {
            throw handleException(e);
        }
    }

}
