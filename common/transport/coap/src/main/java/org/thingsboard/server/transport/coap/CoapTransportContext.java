package org.thingsboard.server.transport.coap;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.thingsboard.server.common.transport.TransportContext;
import org.thingsboard.server.transport.coap.adaptors.CoapTransportAdaptor;

/**
 * Created by ashvayka on 18.10.18.
 */
@Slf4j
@ConditionalOnExpression("'${service.type:null}'=='tb-transport' || ('${service.type:null}'=='monolith' && '${transport.api_enabled:true}'=='true' && '${transport.coap.enabled}'=='true')")
@Component
public class CoapTransportContext extends TransportContext {

    @Getter
    @Value("${transport.coap.bind_address}")
    private String host;

    @Getter
    @Value("${transport.coap.bind_port}")
    private Integer port;

    @Getter
    @Value("${transport.coap.timeout}")
    private Long timeout;

    @Getter
    @Autowired
    private CoapTransportAdaptor adaptor;

}
