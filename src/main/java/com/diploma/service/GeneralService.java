package com.diploma.service;

import com.diploma.dao.DeseaseDAO;
import com.diploma.dao.ForecastDAO;
import com.diploma.dao.MonthsDAO;
import com.diploma.dao.PatientsDAO;
import com.diploma.entity.DeseaseEntity;
import com.diploma.entity.PatientsEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by boubdyk on 23.02.2016.
 */

@Named
@Transactional
public class GeneralService {

    @Inject
    private DeseaseDAO deseaseDAO;

    @Inject
    private ForecastDAO forecastDAO;

    @Inject
    private MonthsDAO monthsDAO;

    @Inject
    private PatientsDAO patientsDAO;

    public GeneralService() {}

    /**
     * Used to get table of month and number of patients for each month.
     * @param deseaseID desease id.
     * @return map of key = monthsID, value = numberOfPatients.
     */
    public Map<Long, Integer> getAllPatientsOfDesease(Long deseaseID) {
        if (deseaseID == null) return null;
        Map<Long, Integer> map = new HashMap<>();
        for (PatientsEntity p: patientsDAO.getByDeseaseID(deseaseID)) {
            map.put(p.getIdMonth(), p.getNumberOfPatients());
        }
        return map;
    }


}
