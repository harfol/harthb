package org.thingsboard.server.transport.coap.adaptors;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.Response;
import org.thingsboard.server.common.transport.adaptor.AdaptorException;
import org.thingsboard.server.gen.transport.TransportProtos;
import org.thingsboard.server.gen.transport.TransportProtos.ProvisionDeviceRequestMsg;
import org.thingsboard.server.transport.coap.CoapTransportResource;

import java.util.UUID;

public interface CoapTransportAdaptor {

    TransportProtos.PostTelemetryMsg convertToPostTelemetry(UUID sessionId, Request inbound) throws AdaptorException;

    TransportProtos.PostAttributeMsg convertToPostAttributes(UUID sessionId, Request inbound) throws AdaptorException;

    TransportProtos.GetAttributeRequestMsg convertToGetAttributes(UUID sessionId, Request inbound) throws AdaptorException;

    TransportProtos.ToDeviceRpcResponseMsg convertToDeviceRpcResponse(UUID sessionId, Request inbound) throws AdaptorException;

    TransportProtos.ToServerRpcRequestMsg convertToServerRpcRequest(UUID sessionId, Request inbound) throws AdaptorException;

    TransportProtos.ClaimDeviceMsg convertToClaimDevice(UUID sessionId, Request inbound, TransportProtos.SessionInfoProto sessionInfo) throws AdaptorException;

    Response convertToPublish(CoapTransportResource.CoapSessionListener session, TransportProtos.GetAttributeResponseMsg responseMsg) throws AdaptorException;

    Response convertToPublish(CoapTransportResource.CoapSessionListener session, TransportProtos.AttributeUpdateNotificationMsg notificationMsg) throws AdaptorException;

    Response convertToPublish(CoapTransportResource.CoapSessionListener session, TransportProtos.ToDeviceRpcRequestMsg rpcRequest) throws AdaptorException;

    Response convertToPublish(CoapTransportResource.CoapSessionListener coapSessionListener, TransportProtos.ToServerRpcResponseMsg msg) throws AdaptorException;

    ProvisionDeviceRequestMsg convertToProvisionRequestMsg(UUID sessionId, Request inbound) throws AdaptorException;

}
