package org.thingsboard.server.actors.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.thingsboard.server.actors.ActorSystemContext;
import org.thingsboard.server.actors.TbActorCtx;
import org.thingsboard.server.common.msg.TbActorMsg;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractContextAwareMsgProcessor {

    protected final ActorSystemContext systemContext;
    protected final ObjectMapper mapper = new ObjectMapper();

    protected AbstractContextAwareMsgProcessor(ActorSystemContext systemContext) {
        super();
        this.systemContext = systemContext;
    }

    private ScheduledExecutorService getScheduler() {
        return systemContext.getScheduler();
    }

    protected void schedulePeriodicMsgWithDelay(TbActorCtx ctx, TbActorMsg msg, long delayInMs, long periodInMs) {
        systemContext.schedulePeriodicMsgWithDelay(ctx, msg, delayInMs, periodInMs);
    }

    protected void scheduleMsgWithDelay(TbActorCtx ctx, TbActorMsg msg, long delayInMs) {
        systemContext.scheduleMsgWithDelay(ctx, msg, delayInMs);
    }

}
