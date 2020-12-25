package org.thingsboard.server.common.transport.auth;

import org.thingsboard.server.common.transport.TransportContext;
import org.thingsboard.server.gen.transport.TransportProtos;

import java.util.UUID;

public class SessionInfoCreator {

    public static TransportProtos.SessionInfoProto create(ValidateDeviceCredentialsResponse msg, TransportContext context, UUID sessionId) {
        return TransportProtos.SessionInfoProto.newBuilder()
                .setNodeId(context.getNodeId())
                .setSessionIdMSB(sessionId.getMostSignificantBits())
                .setSessionIdLSB(sessionId.getLeastSignificantBits())
                .setDeviceIdMSB(msg.getDeviceInfo().getDeviceId().getId().getMostSignificantBits())
                .setDeviceIdLSB(msg.getDeviceInfo().getDeviceId().getId().getLeastSignificantBits())
                .setTenantIdMSB(msg.getDeviceInfo().getTenantId().getId().getMostSignificantBits())
                .setTenantIdLSB(msg.getDeviceInfo().getTenantId().getId().getLeastSignificantBits())
                .setDeviceName(msg.getDeviceInfo().getDeviceName())
                .setDeviceType(msg.getDeviceInfo().getDeviceType())
                .setDeviceProfileIdMSB(msg.getDeviceInfo().getDeviceProfileId().getId().getMostSignificantBits())
                .setDeviceProfileIdLSB(msg.getDeviceInfo().getDeviceProfileId().getId().getLeastSignificantBits())
                .build();
    }

}
