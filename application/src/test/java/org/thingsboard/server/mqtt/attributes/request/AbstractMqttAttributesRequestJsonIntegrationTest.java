package org.thingsboard.server.mqtt.attributes.request;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thingsboard.server.common.data.TransportPayloadType;

@Slf4j
public abstract class AbstractMqttAttributesRequestJsonIntegrationTest extends AbstractMqttAttributesRequestIntegrationTest {

    @Before
    public void beforeTest() throws Exception {
        processBeforeTest("Test Request attribute values from the server json", "Gateway Test Request attribute values from the server json", TransportPayloadType.JSON, null, null);
    }

    @After
    public void afterTest() throws Exception {
        processAfterTest();
    }

    @Test
    public void testRequestAttributesValuesFromTheServer() throws Exception {
        processTestRequestAttributesValuesFromTheServer();
    }

    @Test
    public void testRequestAttributesValuesFromTheServerGateway() throws Exception {
        processTestGatewayRequestAttributesValuesFromTheServer();
    }
}
