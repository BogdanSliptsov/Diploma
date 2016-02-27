package com.diploma.dao;

import com.diploma.entity.TestEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Repository
public class TestDAO implements IDataAccessObject<TestEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public TestDAO() {}

    @Override
    public Long create(TestEntity newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public TestEntity read(Long id) {
        return entityManager.find(TestEntity.class, id);
    }

    @Override
    public TestEntity update(TestEntity transientObject) {
        entityManager.merge(transientObject);
        return transientObject;
    }

    @Override
    public void delete(Long persistentObjectID) {
        entityManager.remove(read(persistentObjectID));
    }
}
