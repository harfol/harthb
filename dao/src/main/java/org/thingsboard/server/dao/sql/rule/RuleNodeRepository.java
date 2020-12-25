package org.thingsboard.server.dao.sql.rule;

import org.springframework.data.repository.CrudRepository;
import org.thingsboard.server.dao.model.sql.RuleNodeEntity;

public interface RuleNodeRepository extends CrudRepository<RuleNodeEntity, String> {

}
