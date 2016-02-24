package com.diploma.dao;

import com.diploma.entity.DeseaseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by boubdyk on 23.02.2016.
 */

@Repository
public class DeseaseDAO implements IDataAccessObject<DeseaseEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public DeseaseDAO() {}

    @Override
    public Long create(DeseaseEntity newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public DeseaseEntity read(Long id) {
        return entityManager.find(DeseaseEntity.class, id);
    }

    @Override
    public DeseaseEntity update(DeseaseEntity transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Long persistentObjectID) {
        DeseaseEntity deseaseEntity = read(persistentObjectID);
        entityManager.remove(deseaseEntity);
    }
}
