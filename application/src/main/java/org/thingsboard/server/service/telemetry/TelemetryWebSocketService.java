package org.thingsboard.server.service.telemetry;

import org.thingsboard.server.service.telemetry.cmd.v2.DataUpdate;
import org.thingsboard.server.service.telemetry.sub.TelemetrySubscriptionUpdate;

/**
 * Created by ashvayka on 27.03.18.
 */
public interface TelemetryWebSocketService {

    void handleWebSocketSessionEvent(TelemetryWebSocketSessionRef sessionRef, SessionEvent sessionEvent);

    void handleWebSocketMsg(TelemetryWebSocketSessionRef sessionRef, String msg);

    void sendWsMsg(String sessionId, TelemetrySubscriptionUpdate update);

    void sendWsMsg(String sessionId, DataUpdate update);

}
