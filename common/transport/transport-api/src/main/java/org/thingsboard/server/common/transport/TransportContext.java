package org.thingsboard.server.common.transport;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;
import org.thingsboard.server.queue.discovery.TbServiceInfoProvider;
import org.thingsboard.server.queue.scheduler.SchedulerComponent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ashvayka on 15.10.18.
 */
@Slf4j
@Data
@Service
@ConditionalOnExpression("'${service.type:null}'=='tb-transport' || ('${service.type:null}'=='monolith' && '${transport.api_enabled:true}'=='true')")
public abstract class TransportContext {

    protected final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TransportService transportService;
    @Autowired
    private TbServiceInfoProvider serviceInfoProvider;
    @Autowired
    private SchedulerComponent scheduler;

    @Getter
    private ExecutorService executor;

    @PostConstruct
    public void init() {
        executor = Executors.newWorkStealingPool(50);
    }

    @PreDestroy
    public void stop() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }

    public String getNodeId() {
        return serviceInfoProvider.getServiceId();
    }

}
