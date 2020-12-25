package org.thingsboard.server.common.transport.util;

import java.util.Optional;

public interface DataDecodingEncodingService {

    <T> Optional<T> decode(byte[] byteArray);

    <T> byte[] encode(T msq);

}

