package com.diploma.dao;

import com.diploma.entity.ForecastEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by boubdyk on 23.02.2016.
 */

@Repository
public class ForecastDAO implements IDataAccessObject<ForecastEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public ForecastDAO() {}

    @Override
    public Long create(ForecastEntity newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public ForecastEntity read(Long id) {
        return entityManager.find(ForecastEntity.class, id);
    }

    @Override
    public ForecastEntity update(ForecastEntity transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Long persistentObjectID) {
        ForecastEntity forecastEntity = read(persistentObjectID);
        entityManager.remove(forecastEntity);
    }
}
