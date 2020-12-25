package org.thingsboard.rule.engine.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.thingsboard.rule.engine.api.NodeConfiguration;

import java.util.Collections;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TbRestApiCallNodeConfiguration implements NodeConfiguration<TbRestApiCallNodeConfiguration> {

    private String restEndpointUrlPattern;
    private String requestMethod;
    private Map<String, String> headers;
    private boolean useSimpleClientHttpFactory;
    private int readTimeoutMs;
    private int maxParallelRequestsCount;
    private boolean useRedisQueueForMsgPersistence;
    private boolean trimQueue;
    private int maxQueueSize;
    private boolean enableProxy;
    private boolean useSystemProxyProperties;
    private String proxyHost;
    private int proxyPort;
    private String proxyUser;
    private String proxyPassword;
    private String proxyScheme;

    @Override
    public TbRestApiCallNodeConfiguration defaultConfiguration() {
        TbRestApiCallNodeConfiguration configuration = new TbRestApiCallNodeConfiguration();
        configuration.setRestEndpointUrlPattern("http://localhost/api");
        configuration.setRequestMethod("POST");
        configuration.setHeaders(Collections.emptyMap());
        configuration.setUseSimpleClientHttpFactory(false);
        configuration.setReadTimeoutMs(0);
        configuration.setMaxParallelRequestsCount(0);
        configuration.setUseRedisQueueForMsgPersistence(false);
        configuration.setTrimQueue(false);
        configuration.setEnableProxy(false);
        return configuration;
    }
}
