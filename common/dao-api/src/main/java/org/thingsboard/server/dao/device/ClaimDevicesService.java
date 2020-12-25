package org.thingsboard.server.dao.device;

import com.google.common.util.concurrent.ListenableFuture;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.dao.device.claim.ClaimResult;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ClaimDevicesService {

    ListenableFuture<Void> registerClaimingInfo(TenantId tenantId, DeviceId deviceId, String secretKey, long durationMs);

    ListenableFuture<ClaimResult> claimDevice(Device device, CustomerId customerId, String secretKey) throws ExecutionException, InterruptedException;

    ListenableFuture<List<Void>> reClaimDevice(TenantId tenantId, Device device);

}
