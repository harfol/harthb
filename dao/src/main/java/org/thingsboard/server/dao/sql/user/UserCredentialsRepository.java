package org.thingsboard.server.dao.sql.user;

import org.springframework.data.repository.CrudRepository;
import org.thingsboard.server.dao.model.sql.UserCredentialsEntity;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 4/22/2017.
 */
public interface UserCredentialsRepository extends CrudRepository<UserCredentialsEntity, UUID> {

    UserCredentialsEntity findByUserId(UUID userId);

    UserCredentialsEntity findByActivateToken(String activateToken);

    UserCredentialsEntity findByResetToken(String resetToken);
}
