package org.thingsboard.rule.engine.mqtt.credentials;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.netty.handler.ssl.SslContext;
import org.thingsboard.mqtt.MqttClientConfig;
import org.thingsboard.rule.engine.mqtt.azure.AzureIotHubSasCredentials;

import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnonymousCredentials.class, name = "anonymous"),
        @JsonSubTypes.Type(value = BasicCredentials.class, name = "basic"),
        @JsonSubTypes.Type(value = AzureIotHubSasCredentials.class, name = "sas"),
        @JsonSubTypes.Type(value = CertPemClientCredentials.class, name = "cert.PEM")})
public interface MqttClientCredentials {

    Optional<SslContext> initSslContext();

    void configure(MqttClientConfig config);
}

