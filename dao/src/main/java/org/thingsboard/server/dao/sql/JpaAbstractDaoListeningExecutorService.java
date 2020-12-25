package org.thingsboard.server.dao.sql;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class JpaAbstractDaoListeningExecutorService {

    @Autowired
    protected JpaExecutorService service;

}
