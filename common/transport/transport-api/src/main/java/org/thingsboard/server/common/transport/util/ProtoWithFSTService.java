package org.thingsboard.server.common.transport.util;

import lombok.extern.slf4j.Slf4j;
import org.nustaq.serialization.FSTConfiguration;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.msg.TbActorMsg;
import org.thingsboard.server.common.transport.util.DataDecodingEncodingService;

import java.util.Optional;

@Slf4j
@Service
public class ProtoWithFSTService implements DataDecodingEncodingService {

    private final FSTConfiguration config = FSTConfiguration.createDefaultConfiguration();

    @Override
    public <T> Optional<T> decode(byte[] byteArray) {
        try {
            T msg = (T) config.asObject(byteArray);
            return Optional.of(msg);
        } catch (IllegalArgumentException e) {
            log.error("Error during deserialization message, [{}]", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <T> byte[] encode(T msq) {
        return config.asByteArray(msq);
    }

}
