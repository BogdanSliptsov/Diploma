package com.diploma.service;

import com.diploma.dao.YearDAO;
import com.diploma.entity.YearEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by boubdyk on 28.02.2016.
 */

@Named
@Transactional
public class YearService {

    @Inject
    private YearDAO yearDAO;

    public YearService() {}

    /**
     * Used to create year.
     * @param yearNumber year number.
     * @return id of new year or existing id if year exist.
     */
    public Long createYear(Integer yearNumber) {
        Long id;
        boolean flagEx = false;
        try {
            id = yearDAO.getYearIdByYearNumber(yearNumber);
        } catch (javax.persistence.NoResultException nre) {
            flagEx = true;
            id = null;
        }
        if (flagEx) {
            id = yearDAO.create(new YearEntity(yearNumber));
        }
        return id;
    }
}
