package com.diploma.dao;

import com.diploma.entity.PatientsEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
