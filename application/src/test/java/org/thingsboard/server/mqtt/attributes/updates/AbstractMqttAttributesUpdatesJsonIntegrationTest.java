package org.thingsboard.server.mqtt.attributes.updates;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thingsboard.server.common.data.TransportPayloadType;

@Slf4j
public abstract class AbstractMqttAttributesUpdatesJsonIntegrationTest extends AbstractMqttAttributesUpdatesIntegrationTest {

    @Before
    public void beforeTest() throws Exception {
        processBeforeTest("Test Subscribe to attribute updates", "Gateway Test Subscribe to attribute updates", TransportPayloadType.JSON, null, null);
    }

    @After
    public void afterTest() throws Exception {
        processAfterTest();
    }

    @Test
    public void testSubscribeToAttributesUpdatesFromTheServer() throws Exception {
        processTestSubscribeToAttributesUpdates();
    }

    @Test
    public void testSubscribeToAttributesUpdatesFromTheServerGateway() throws Exception {
        processGatewayTestSubscribeToAttributesUpdates();
    }
}
