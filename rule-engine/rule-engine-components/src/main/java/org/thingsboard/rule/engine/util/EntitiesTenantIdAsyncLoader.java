package org.thingsboard.rule.engine.util;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import org.thingsboard.rule.engine.api.TbContext;
import org.thingsboard.rule.engine.api.TbNodeException;
import org.thingsboard.server.common.data.HasTenantId;
import org.thingsboard.server.common.data.id.AlarmId;
import org.thingsboard.server.common.data.id.AssetId;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.id.RuleChainId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.UserId;

public class EntitiesTenantIdAsyncLoader {

    public static ListenableFuture<TenantId> findEntityIdAsync(TbContext ctx, EntityId original) {

        switch (original.getEntityType()) {
            case TENANT:
                return Futures.immediateFuture((TenantId) original);
            case CUSTOMER:
                return getTenantAsync(ctx.getCustomerService().findCustomerByIdAsync(ctx.getTenantId(), (CustomerId) original));
            case USER:
                return getTenantAsync(ctx.getUserService().findUserByIdAsync(ctx.getTenantId(), (UserId) original));
            case ASSET:
                return getTenantAsync(ctx.getAssetService().findAssetByIdAsync(ctx.getTenantId(), (AssetId) original));
            case DEVICE:
                return getTenantAsync(ctx.getDeviceService().findDeviceByIdAsync(ctx.getTenantId(), (DeviceId) original));
            case ALARM:
                return getTenantAsync(ctx.getAlarmService().findAlarmByIdAsync(ctx.getTenantId(), (AlarmId) original));
            case RULE_CHAIN:
                return getTenantAsync(ctx.getRuleChainService().findRuleChainByIdAsync(ctx.getTenantId(), (RuleChainId) original));
            default:
                return Futures.immediateFailedFuture(new TbNodeException("Unexpected original EntityType " + original));
        }
    }

    private static <T extends HasTenantId> ListenableFuture<TenantId> getTenantAsync(ListenableFuture<T> future) {
        return Futures.transformAsync(future, in -> {
            return in != null ? Futures.immediateFuture(in.getTenantId())
                    : Futures.immediateFuture(null);
        }, MoreExecutors.directExecutor());
    }
}
