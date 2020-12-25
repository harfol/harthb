package org.thingsboard.server.common.data.rule;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class DefaultRuleChainCreateRequest implements Serializable {

    private static final long serialVersionUID = 5600333716030561537L;

    private String name;

}
