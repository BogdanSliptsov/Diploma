package com.diploma.dao;

import com.diploma.entity.PatientsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<PatientsEntity> getByDeseaseID(Long deseaseID) {
        String query = "SELECT p FROM PatientsEntity p WHERE p.id_desease=\'" + deseaseID + "\'";
        TypedQuery<PatientsEntity> result = entityManager.createQuery(query, PatientsEntity.class);
        return result.getResultList();
    }
}
