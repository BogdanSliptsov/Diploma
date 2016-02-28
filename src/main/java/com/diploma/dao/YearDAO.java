package com.diploma.dao;

import com.diploma.entity.YearEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Repository
public class YearDAO implements IDataAccessObject<YearEntity, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public YearDAO() {}

    @Override
    public Long create(YearEntity newInstance) {

        entityManager.persist(newInstance);
        return newInstance.getId();
    }

    @Override
    public YearEntity read(Long id) {
        return entityManager.find(YearEntity.class, id);
    }

    @Override
    public YearEntity update(YearEntity transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Long persistentObjectID) {
        entityManager.remove(read(persistentObjectID));
    }

    /**
     * Used to get year number by year id.
     * @param yearId year id.
     * @return year number.
     */
    public Integer getYearNumberByYearId(Long yearId) {
        String query = "SELECT y.yearNumber FROM YearEntity y WHERE y.id=" + yearId;
        TypedQuery<Integer> result = entityManager.createQuery(query, Integer.class);
        return result.getSingleResult();
    }

    /**
     * Used to get year id by year number.
     * @param yearNumber
     * @return
     */
    public Long getYearIdByYearNumber(Integer yearNumber) {
        String query = "SELECT y.id FROM YearEntity y WHERE y.yearNumber=" + yearNumber;
        TypedQuery<Long> result = entityManager.createQuery(query, Long.class);
        return result.getSingleResult();
    }
}
