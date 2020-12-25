package org.thingsboard.server.dao.device.claim;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClaimData {

    private final String secretKey;
    private final long expirationTime;

}
