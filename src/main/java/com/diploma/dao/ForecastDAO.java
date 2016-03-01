package com.diploma.dao;

import com.diploma.entity.ForecastEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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


    /**
     * Used to delete record for disease by math method name.
     * @param methodName math method name.
     * @param diseaseId disease id.
     */
    public void deleteByMethodNameForYears(String methodName, Long diseaseId) {
        Query query = entityManager.createNativeQuery("DELETE FROM forecast WHERE methodName=\""
                + methodName + "\" AND id_desease=" + diseaseId
                + " AND id_month IS NULL ");
        query.executeUpdate();
    }

    /**
     * Used to delete record for disease by math method name.
     * @param diseaseId disease id.
     */
    public void deleteByDiseaseId(Long diseaseId) {
        Query query = entityManager.createNativeQuery("DELETE FROM forecast WHERE id_desease=" + diseaseId
                + " AND id_month IS NULL ");
        query.executeUpdate();
    }

    /**
     * Used to get all forecasted years ID's for current disease.
     * @param diseaseId disease id.
     * @return list of ID's.
     */
    public List<Long> getAllForecastedYearsForDisease(Long diseaseId, String mathMethod) {
        String query = "SELECT f.yearEntity.id FROM ForecastEntity f WHERE f.deseaseEntity.id=" + diseaseId +
                " AND f.methodName=\'" + mathMethod +
                "\' AND f.monthsEntity.id is NULL";
        TypedQuery<Long> result = entityManager.createQuery(query, Long.class);
        return result.getResultList();
    }

    /**
     * Used to get number of patients for year.
     * @return number of patients.
     */
    public Integer getNumberOfForecastedPatientsForYearID(Long diseaseId, Long yearId, String mathMethod) {
        String query = "SELECT p.numberOfPatients FROM ForecastEntity p WHERE p.yearEntity.id=" +
                yearId + " AND p.deseaseEntity.id=" + diseaseId +
                " AND p.methodName=\'" + mathMethod +
                "\' AND p.monthsEntity.id IS NULL";
        TypedQuery<Integer> result = entityManager.createQuery(query, Integer.class);
        return result.getSingleResult();
    }

    /**
     * Used to get all forecast records for disease and exp smoothing method.
     * @param diseaseId disease id.
     * @return list of forecast records.
     */
    public List<ForecastEntity> getAllForecastRecordsByDiseaseForSmoothing(Long diseaseId) {
        String query = "SELECT f FROM ForecastEntity f WHERE f.deseaseEntity.id=" + diseaseId +
                " AND f.methodName=\'SMOOTHING\'";
        TypedQuery<ForecastEntity> result = entityManager.createQuery(query, ForecastEntity.class);
        return result.getResultList();
    }
}
