package com.diploma.service;

import com.diploma.dao.*;
import com.diploma.entity.DeseaseEntity;
import com.diploma.entity.MonthsEntity;
import com.diploma.entity.PatientsEntity;
import com.diploma.entity.YearEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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

    @Inject
    private YearDAO yearDAO;

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
            map.put(p.getDiseaseId(), p.getNumberOfPatients());
        }
        return map;
    }

    /**
     * Used to fill data by months
     * @param year year.
     * @param dataMap key = month number, value = number of patients.
     * @param deseaseName desease name.
     */
    public void fillDataByMonths(String deseaseName, Integer year, Map<Integer, Integer> dataMap) {
        Long deseaseId = deseaseDAO.getIdByName(deseaseName);
        YearEntity yearEntity = new YearEntity(year);
        Long yearId = yearDAO.create(yearEntity);
        List<Long> monthsIDs = new ArrayList<>();

        //Fills months table.
        MonthsEntity monthsEntity;
        YearEntity ye = yearDAO.read(yearId);
        for (Integer monthsNumber: dataMap.keySet()) {
            monthsEntity = new MonthsEntity(monthsNumber, ye);
            monthsIDs.add(monthsDAO.create(monthsEntity));
        }

        //Fills patients table.
        PatientsEntity patientsEntity;
        //Counter is used to get IDs of months.
        int counter = 0;
        for (Integer numberOfPatients: dataMap.values()) {
            patientsEntity = new PatientsEntity(numberOfPatients, ye.getId(), monthsDAO.read(monthsIDs.get(counter)).getId(),
                                                deseaseDAO.read(deseaseId).getId());
            patientsDAO.create(patientsEntity);
            counter++;
        }
    }

    private boolean checkDataPresenceForMonths(Integer year, Integer monthNumber, Long deseaseId) {
        List<PatientsEntity> patientsEntities = patientsDAO.getByDeseaseID(deseaseId);
        //TODO check of presence here
        return true;
    }


}
