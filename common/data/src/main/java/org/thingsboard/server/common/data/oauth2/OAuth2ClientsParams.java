package org.thingsboard.server.common.data.oauth2;

import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Data
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OAuth2ClientsParams {
    private boolean enabled;
    private List<OAuth2ClientsDomainParams> domainsParams;
}
