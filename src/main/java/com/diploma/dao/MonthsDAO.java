package com.diploma.dao;

import com.diploma.entity.MonthsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by boubdyk on 23.02.2016.
 */

@Repository
public class MonthsDAO implements IDataAccessObject<MonthsEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public MonthsDAO() {}

    @Override
    public Long create(MonthsEntity newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public MonthsEntity read(Long id) {
        return entityManager.find(MonthsEntity.class, id);
    }

    @Override
    public MonthsEntity update(MonthsEntity transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Long persistentObjectID) {
        MonthsEntity monthsEntity = read(persistentObjectID);
        entityManager.remove(monthsEntity);
    }
}
