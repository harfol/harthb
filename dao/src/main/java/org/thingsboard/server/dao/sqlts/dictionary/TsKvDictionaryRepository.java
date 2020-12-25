package org.thingsboard.server.dao.sqlts.dictionary;

import org.springframework.data.repository.CrudRepository;
import org.thingsboard.server.dao.model.sqlts.dictionary.TsKvDictionary;
import org.thingsboard.server.dao.model.sqlts.dictionary.TsKvDictionaryCompositeKey;
import org.thingsboard.server.dao.util.SqlTsOrTsLatestAnyDao;

import java.util.Optional;

@SqlTsOrTsLatestAnyDao
public interface TsKvDictionaryRepository extends CrudRepository<TsKvDictionary, TsKvDictionaryCompositeKey> {

    Optional<TsKvDictionary> findByKeyId(int keyId);

}