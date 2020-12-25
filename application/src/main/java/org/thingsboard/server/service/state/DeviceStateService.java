package org.thingsboard.server.service.state;

import org.springframework.context.ApplicationListener;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.queue.discovery.PartitionChangeEvent;
import org.thingsboard.server.gen.transport.TransportProtos;
import org.thingsboard.server.common.msg.queue.TbCallback;

/**
 * Created by ashvayka on 01.05.18.
 */
public interface DeviceStateService extends ApplicationListener<PartitionChangeEvent> {

    void onDeviceAdded(Device device);

    void onDeviceUpdated(Device device);

    void onDeviceDeleted(Device device);

    void onDeviceConnect(DeviceId deviceId);

    void onDeviceActivity(DeviceId deviceId, long lastReportedActivityTime);

    void onDeviceDisconnect(DeviceId deviceId);

    void onDeviceInactivityTimeoutUpdate(DeviceId deviceId, long inactivityTimeout);

    void onQueueMsg(TransportProtos.DeviceStateServiceMsgProto proto, TbCallback bytes);

}
