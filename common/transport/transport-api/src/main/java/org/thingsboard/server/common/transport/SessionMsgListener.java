package org.thingsboard.server.common.transport;

import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceProfile;
import org.thingsboard.server.gen.transport.TransportProtos;
import org.thingsboard.server.gen.transport.TransportProtos.ToServerRpcResponseMsg;
import org.thingsboard.server.gen.transport.TransportProtos.AttributeUpdateNotificationMsg;
import org.thingsboard.server.gen.transport.TransportProtos.GetAttributeResponseMsg;
import org.thingsboard.server.gen.transport.TransportProtos.SessionCloseNotificationProto;
import org.thingsboard.server.gen.transport.TransportProtos.ToDeviceRpcRequestMsg;

import java.util.Optional;

/**
 * Created by ashvayka on 04.10.18.
 */
public interface SessionMsgListener {

    void onGetAttributesResponse(GetAttributeResponseMsg getAttributesResponse);

    void onAttributeUpdate(AttributeUpdateNotificationMsg attributeUpdateNotification);

    void onRemoteSessionCloseCommand(SessionCloseNotificationProto sessionCloseNotification);

    void onToDeviceRpcRequest(ToDeviceRpcRequestMsg toDeviceRequest);

    void onToServerRpcResponse(ToServerRpcResponseMsg toServerResponse);

    default void onDeviceProfileUpdate(TransportProtos.SessionInfoProto newSessionInfo, DeviceProfile deviceProfile) {
    }

    default void onDeviceUpdate(TransportProtos.SessionInfoProto sessionInfo, Device device, Optional<DeviceProfile> deviceProfileOpt) {
    }
}
