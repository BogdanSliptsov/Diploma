package com.diploma.dao;

import com.diploma.entity.PatientsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by boubdyk on 23.02.2016.
 */

@Repository
public class PatientsDAO implements IDataAccessObject<PatientsEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public PatientsDAO() {}

    @Override
    public Long create(PatientsEntity newInstance) {
        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public PatientsEntity read(Long id) {
        return entityManager.find(PatientsEntity.class, id);
    }

    @Override
    public PatientsEntity update(PatientsEntity transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Long persistentObjectID) {
        PatientsEntity patientsEntity = read(persistentObjectID);
        entityManager.remove(patientsEntity);
    }

    /**
     * Used to get all patients of desease.
     * @param deseaseID
     * @return
     */
    public List<PatientsEntity> getByDeseaseID(Long deseaseID) {
        String query = "SELECT p FROM PatientsEntity p WHERE p.deseaseEntity.id=\'" + deseaseID + "\'";
        TypedQuery<PatientsEntity> result = entityManager.createQuery(query, PatientsEntity.class);
        return result.getResultList();
    }

    /**
     * Used to get all patients from DB.
     * @return
     */
    public List<PatientsEntity> getAll() {
        String query = "SELECT p FROM PatientsEntity p";
        TypedQuery<PatientsEntity> result = entityManager.createQuery(query, PatientsEntity.class);
        return result.getResultList();
    }

    /**
     * Used to get all years ID's for current disease.
     * @param diseaseId disease id.
     * @return list of ID's.
     */
    public List<Long> getAllYearsForDisease(Long diseaseId) {
        String query = "SELECT p.yearEntity.id FROM PatientsEntity p WHERE p.deseaseEntity.id=" + diseaseId +
                " AND p.monthsEntity.id is NULL";
        TypedQuery<Long> result = entityManager.createQuery(query, Long.class);
        return result.getResultList();
    }

    /**
     * Used to get ID's of all months for current disease.
     * @param diseaseId disease id.
     * @return lis of months ID's.
     */
    public List<Long> getAllMonthsForDisease(Long diseaseId) {
        String query = "SELECT p.id_month FROM PatientsEntity p WHERE p.id_desease=" + diseaseId;
        TypedQuery<Long> result = entityManager.createQuery(query, Long.class);
        return result.getResultList();
    }

    /**
     * Used to get number of patients for year.
     * @return number of patients.
     */
    public Integer getNumberOfPatientsForYearID(Long diseaseId, Long yearId) {
        String query = "SELECT p.numberOfPatients FROM PatientsEntity p WHERE p.yearEntity.id=" +
                yearId + " AND p.deseaseEntity.id=" + diseaseId +
                " AND p.monthsEntity.id IS NULL";
        TypedQuery<Integer> result = entityManager.createQuery(query, Integer.class);
        return result.getSingleResult();
    }

    /**
     * Used to get number of patients by disease id.
     * @param diseaseId disease id.
     * @return list of numberOfPatients.
     */
    public List<Integer> getNumberOfPatientsByDiseaseIdForYears(Long diseaseId) {
        String query = "SELECT p.numberOfPatients FROM PatientsEntity p WHERE p.deseaseEntity.id=" + diseaseId +
                " AND p.monthsEntity.id IS NULL";
        TypedQuery<Integer> result = entityManager.createQuery(query, Integer.class);
        return result.getResultList();
    }

    /**
     * Used to
     * @param diseaseId
     * @param yearId
     * @return
     */
    public Long getPatientsByDiseaseNameAndYearId(Long diseaseId, Long yearId) {
        String query = "SELECT p.id FROM PatientsEntity p WHERE p.yearEntity.id=" +
                yearId + " AND p.deseaseEntity.id=" + diseaseId +
                " AND p.monthsEntity.id IS NULL";
        TypedQuery<Long> result = entityManager.createQuery(query, Long.class);
        return result.getSingleResult();
    }

    /**
     * Used to delete all patients of disease.
     * @param diseaseId disease id.
     */
    public void deletePatientsByDiseaseId(Long diseaseId) {
        Query query = entityManager.createNativeQuery("DELETE FROM patients WHERE id_desease=" + diseaseId
                + " AND id_month IS NULL ");
        query.executeUpdate();
    }
}
