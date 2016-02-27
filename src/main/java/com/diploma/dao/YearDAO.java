package com.diploma.dao;

import com.diploma.entity.YearEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Repository
public class YearDAO implements IDataAccessObject<YearEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public YearDAO() {}

    @Override
    public Long create(YearEntity newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public YearEntity read(Long id) {
        return entityManager.find(YearEntity.class, id);
    }

    @Override
    public YearEntity update(YearEntity transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Long persistentObjectID) {
        entityManager.remove(read(persistentObjectID));
    }
}
