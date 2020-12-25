package org.thingsboard.server.mqtt.claim;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thingsboard.server.common.data.TransportPayloadType;

@Slf4j
public abstract class AbstractMqttClaimJsonDeviceTest extends AbstractMqttClaimDeviceTest {
    
    @Before
    public void beforeTest() throws Exception {
        super.processBeforeTest("Test Claim device", "Test Claim gateway", TransportPayloadType.JSON, null, null);
        createCustomerAndUser();
    }

    @After
    public void afterTest() throws Exception {
        super.afterTest();
    }

    @Test
    public void testClaimingDevice() throws Exception {
        processTestClaimingDevice(false);
    }

    @Test
    public void testClaimingDeviceWithoutSecretAndDuration() throws Exception {
        processTestClaimingDevice(true);
    }

    @Test
    public void testGatewayClaimingDevice() throws Exception {
        processTestGatewayClaimingDevice("Test claiming gateway device Json", false);
    }

    @Test
    public void testGatewayClaimingDeviceWithoutSecretAndDuration() throws Exception {
        processTestGatewayClaimingDevice("Test claiming gateway device empty payload Json", true);
    }
}
