package org.thingsboard.server.dao.sql.component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.thingsboard.server.common.data.plugin.ComponentScope;
import org.thingsboard.server.common.data.plugin.ComponentType;
import org.thingsboard.server.dao.model.sql.ComponentDescriptorEntity;

import java.util.UUID;

/**
 * Created by Valerii Sosliuk on 5/6/2017.
 */
public interface ComponentDescriptorRepository extends PagingAndSortingRepository<ComponentDescriptorEntity, UUID> {

    ComponentDescriptorEntity findByClazz(String clazz);

    @Query("SELECT cd FROM ComponentDescriptorEntity cd WHERE cd.type = :type " +
            "AND LOWER(cd.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<ComponentDescriptorEntity> findByType(@Param("type") ComponentType type,
                                               @Param("textSearch") String textSearch,
                                               Pageable pageable);

    @Query("SELECT cd FROM ComponentDescriptorEntity cd WHERE cd.type = :type " +
            "AND cd.scope = :scope AND LOWER(cd.searchText) LIKE LOWER(CONCAT(:textSearch, '%'))")
    Page<ComponentDescriptorEntity> findByScopeAndType(@Param("type") ComponentType type,
                                                       @Param("scope") ComponentScope scope,
                                                       @Param("textSearch") String textSearch,
                                                       Pageable pageable);

    void deleteByClazz(String clazz);
}
