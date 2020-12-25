package org.thingsboard.server.actors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SlowCreateActor extends TestRootActor {

    public SlowCreateActor(TbActorId actorId, ActorTestCtx testCtx) {
        super(actorId, testCtx);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testCtx.getInvocationCount().incrementAndGet();
    }

    public static class SlowCreateActorCreator implements TbActorCreator {

        private final TbActorId actorId;
        private final ActorTestCtx testCtx;

        public SlowCreateActorCreator(TbActorId actorId, ActorTestCtx testCtx) {
            this.actorId = actorId;
            this.testCtx = testCtx;
        }

        @Override
        public TbActorId createActorId() {
            return actorId;
        }

        @Override
        public TbActor createActor() {
            return new SlowCreateActor(actorId, testCtx);
        }
    }
}
