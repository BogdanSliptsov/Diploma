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

    /**
     * Used to get all deseases from DB.
     * @return list of deseases.
     */
    public List<DeseaseEntity> getAll() {
        String query = "SELECT d FROM DeseaseEntity d";
        TypedQuery<DeseaseEntity> result = entityManager.createQuery(query, DeseaseEntity.class);
        return result.getResultList();
    }

    /**
     * Used to get desease ID by desease name
     * @param name
     * @return desease id.
     */
    public Long getIdByName(String name) {
        String query = "SELECT d FROM DeseaseEntity d WHERE d.name=\'" + name + "\'";
        TypedQuery<DeseaseEntity> result = entityManager.createQuery(query, DeseaseEntity.class);
        return result.getSingleResult().getId();
    }
}
