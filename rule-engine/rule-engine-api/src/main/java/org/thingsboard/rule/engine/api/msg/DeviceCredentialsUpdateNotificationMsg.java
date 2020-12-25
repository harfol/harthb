package org.thingsboard.rule.engine.api.msg;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.kv.AttributeKey;
import org.thingsboard.server.common.msg.MsgType;

import java.util.Set;

/**
 * @author Andrew Shvayka
 */
@Data
public class DeviceCredentialsUpdateNotificationMsg implements ToDeviceActorNotificationMsg {

    private final TenantId tenantId;
    private final DeviceId deviceId;

    @Override
    public MsgType getMsgType() {
        return MsgType.DEVICE_CREDENTIALS_UPDATE_TO_DEVICE_ACTOR_MSG;
    }
}
