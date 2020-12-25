package org.thingsboard.server.common.data.oauth2;

import lombok.*;

@EqualsAndHashCode
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainInfo {
    private SchemeType scheme;
    private String name;
}
