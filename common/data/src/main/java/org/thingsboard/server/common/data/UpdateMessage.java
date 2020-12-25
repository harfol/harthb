package org.thingsboard.server.common.data;

import lombok.Data;

@Data
public class UpdateMessage {

    private final String message;
    private final boolean isUpdateAvailable;

}
