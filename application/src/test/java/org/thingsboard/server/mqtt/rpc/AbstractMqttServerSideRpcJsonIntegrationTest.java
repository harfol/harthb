package org.thingsboard.server.mqtt.rpc;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.thingsboard.server.common.data.TransportPayloadType;

@Slf4j
public abstract class AbstractMqttServerSideRpcJsonIntegrationTest extends AbstractMqttServerSideRpcIntegrationTest {

    @Before
    public void beforeTest() throws Exception {
        processBeforeTest("RPC test device", "RPC test gateway", TransportPayloadType.JSON, null, null);
    }

    @After
    public void afterTest() throws Exception {
        super.processAfterTest();
    }

    @Test
    public void testServerMqttOneWayRpc() throws Exception {
        processOneWayRpcTest();
    }

    @Test
    public void testServerMqttTwoWayRpc() throws Exception {
        processTwoWayRpcTest();
    }

    @Test
    public void testGatewayServerMqttOneWayRpc() throws Exception {
        processOneWayRpcTestGateway("Gateway Device OneWay RPC Json");
    }

    @Test
    public void testGatewayServerMqttTwoWayRpc() throws Exception {
        processTwoWayRpcTestGateway("Gateway Device TwoWay RPC Json");
    }

    protected void processOneWayRpcTestGateway(String deviceName) throws Exception {
        MqttAsyncClient client = getMqttAsyncClient(gatewayAccessToken);
        String payload = "{\"device\": \"" + deviceName + "\", \"type\": \"" + TransportPayloadType.JSON.name() + "\"}";
        byte[] payloadBytes = payload.getBytes();
        validateOneWayRpcGatewayResponse(deviceName, client, payloadBytes);
    }

}
