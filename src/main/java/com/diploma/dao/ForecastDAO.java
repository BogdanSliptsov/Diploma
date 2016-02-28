package com.diploma.dao;

import com.diploma.entity.ForecastEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedHashMap;
import java.util.List;

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

    /**
     * Used to get all forecasts for disease
     * @return
     */
    public List<ForecastEntity> getAllByDiseaseId(Long diseaseId) {
        String query = "SELECT f FROM ForecastEntity f WHERE f.deseaseEntity.id=" + diseaseId;
        TypedQuery<ForecastEntity> result = entityManager.createQuery(query, ForecastEntity.class);
        return result.getResultList();
    }
}
