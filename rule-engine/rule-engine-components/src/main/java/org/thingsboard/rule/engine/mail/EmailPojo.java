package org.thingsboard.rule.engine.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class EmailPojo {

    private final String from;
    private final String to;
    private final String cc;
    private final String bcc;
    private final String subject;
    private final String body;

}
