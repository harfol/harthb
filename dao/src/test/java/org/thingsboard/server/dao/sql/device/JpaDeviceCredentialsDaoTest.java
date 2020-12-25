package org.thingsboard.server.dao.sql.device;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thingsboard.server.common.data.security.DeviceCredentials;
import org.thingsboard.server.dao.AbstractJpaDaoTest;
import org.thingsboard.server.dao.device.DeviceCredentialsDao;
import org.thingsboard.server.dao.service.AbstractServiceTest;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.thingsboard.server.dao.service.AbstractServiceTest.SYSTEM_TENANT_ID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
public class JpaDeviceCredentialsDaoTest extends AbstractJpaDaoTest {

    @Autowired
    DeviceCredentialsDao deviceCredentialsDao;

    @Test
    @DatabaseSetup("classpath:dbunit/device_credentials.xml")
    public void testFindByDeviceId() {
        UUID deviceId = UUID.fromString("958e3a30-3215-11e7-93ae-92361f002671");
        DeviceCredentials deviceCredentials = deviceCredentialsDao.findByDeviceId(SYSTEM_TENANT_ID, deviceId);
        assertNotNull(deviceCredentials);
        assertEquals("958e3314-3215-11e7-93ae-92361f002671", deviceCredentials.getId().getId().toString());
        assertEquals("ID_1", deviceCredentials.getCredentialsId());
    }

    @Test
    @DatabaseSetup("classpath:dbunit/device_credentials.xml")
    public void findByCredentialsId() {
        String credentialsId = "ID_2";
        DeviceCredentials deviceCredentials = deviceCredentialsDao.findByCredentialsId(SYSTEM_TENANT_ID, credentialsId);
        assertNotNull(deviceCredentials);
        assertEquals("958e3c74-3215-11e7-93ae-92361f002671", deviceCredentials.getId().getId().toString());
    }
}
