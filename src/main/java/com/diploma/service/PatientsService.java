package com.diploma.service;

import com.diploma.dao.PatientsDAO;
import com.diploma.entity.PatientsEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Named
@Transactional
public class PatientsService {

    @Inject
    private PatientsDAO patientsDAO;

    public PatientsService() {}

    /**
     * Used to get all patients from DB.
     * @return
     */
    public List<PatientsEntity> getAllPatients() {
        return patientsDAO.getAll();
    }

}
