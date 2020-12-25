package org.thingsboard.server.transport.coap;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.core.network.config.NetworkConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

@Service("CoapTransportService")
@ConditionalOnExpression("'${service.type:null}'=='tb-transport' || ('${service.type:null}'=='monolith' && '${transport.api_enabled:true}'=='true' && '${transport.coap.enabled}'=='true')")
@Slf4j
public class CoapTransportService {

    private static final String V1 = "v1";
    private static final String API = "api";

    @Autowired
    private CoapTransportContext coapTransportContext;

    private CoapServer server;

    @PostConstruct
    public void init() throws UnknownHostException {
        log.info("Starting CoAP transport...");
        log.info("Starting CoAP transport server");
        this.server = new CoapServer(NetworkConfig.createStandardWithoutFile());
        createResources();
        InetAddress addr = InetAddress.getByName(coapTransportContext.getHost());
        InetSocketAddress sockAddr = new InetSocketAddress(addr, coapTransportContext.getPort());
        server.addEndpoint(new CoapEndpoint(sockAddr));
        server.start();
        log.info("CoAP transport started!");
    }

    private void createResources() {
        CoapResource api = new CoapResource(API);
        api.add(new CoapTransportResource(coapTransportContext, V1));
        server.add(api);
    }

    @PreDestroy
    public void shutdown() {
        log.info("Stopping CoAP transport!");
        this.server.destroy();
        log.info("CoAP transport stopped!");
    }
}
