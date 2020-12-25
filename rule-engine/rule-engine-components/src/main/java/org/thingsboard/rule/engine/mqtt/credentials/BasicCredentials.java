package org.thingsboard.rule.engine.mqtt.credentials;

import io.netty.handler.ssl.SslContext;
import lombok.Data;
import org.thingsboard.mqtt.MqttClientConfig;

import java.util.Optional;

@Data
public class BasicCredentials implements MqttClientCredentials {

    private String username;
    private String password;

    @Override
    public Optional<SslContext> initSslContext() {
        return Optional.empty();
    }

    @Override
    public void configure(MqttClientConfig config) {
        config.setUsername(username);
        config.setPassword(password);
    }

}

