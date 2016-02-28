package com.diploma.service;

import com.diploma.Point;
import com.diploma.dao.*;
import com.diploma.entity.DeseaseEntity;
import com.diploma.entity.MonthsEntity;
import com.diploma.entity.PatientsEntity;
import com.diploma.entity.YearEntity;
import com.diploma.interpolation.LagrangeInterpolation;
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

//    /**
//     * Used to get table of month and number of patients for each month.
//     * @param deseaseID desease id.
//     * @return map of key = monthsID, value = numberOfPatients.
//     */
//    public Map<Long, Integer> getAllPatientsOfDesease(Long deseaseID) {
//        if (deseaseID == null) return null;
//        Map<Long, Integer> map = new HashMap<>();
//        for (PatientsEntity p: patientsDAO.getByDeseaseID(deseaseID)) {
//            map.put(p.getDiseaseId(), p.getNumberOfPatients());
//        }
//        return map;
//    }

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
            patientsEntity = new PatientsEntity(numberOfPatients, ye, monthsDAO.read(monthsIDs.get(counter)),
                                                deseaseDAO.read(deseaseId));
            patientsDAO.create(patientsEntity);
            counter++;
        }
    }

    /**
     * Used to fill data in patients table by year.
     * @param diseaseName disease name.
     * @param year year number.
     * @param numberOfPatients number of patients for current disease.
     */
    public void fillDataByYear(String diseaseName, Integer year, Integer numberOfPatients) {
        Long diseaseId = deseaseDAO.getIdByName(diseaseName);
        YearEntity yearEntity = new YearEntity(year);
        Long yearId = yearDAO.create(yearEntity);

        PatientsEntity patientsEntity = new PatientsEntity(numberOfPatients, yearEntity, deseaseDAO.read(diseaseId));
        patientsDAO.create(patientsEntity);
    }

    /**
     * Used to restore number of patients for absent years.
     * @param diseaseName desease name.
     * @return List of restored values or null of there nothin to restore.
     */
    public List<Integer> restoreDataForYears(String diseaseName) {
        List<Integer> yearsToRestore = new ArrayList<>();
        List<Integer> actualYearsNumbers = new ArrayList<>();
        Long diseaseId = deseaseDAO.getIdByName(diseaseName);
        List<Long> actualYearIDs = patientsDAO.getAllYearsForDisease(diseaseId);
        List<Integer> actualNumberOfPatients = new ArrayList<>();
        for (Long yearId : actualYearIDs) {
            actualYearsNumbers.add(yearDAO.getYearNumberByYearId(yearId));
            actualNumberOfPatients.add(patientsDAO.getNumberOfPatientsForYearID(diseaseId, yearId));
        }
        for (int i = 0; i < actualYearsNumbers.size() - 1; i++) {
            if (!actualYearsNumbers.get(i + 1).equals(actualYearsNumbers.get(i) + 1)) {
                yearsToRestore.add(actualYearsNumbers.get(i) + 1);
            }
        }
        if (yearsToRestore.isEmpty()) return null;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < actualYearsNumbers.size(); i++) {
            points.add(new Point(actualYearsNumbers.get(i) + 0.0, actualNumberOfPatients.get(i) + 0.0));
        }

        LagrangeInterpolation interpolation = new LagrangeInterpolation(points);
        List<Long> patientsToRestore = new ArrayList<>();
        for (int i = 0; i < yearsToRestore.size(); i++) {
            patientsToRestore.add(Math.round(interpolation.calculate(yearsToRestore.get(i) + 0.0)));
        }

        //Filling restored data
        for (int i = 0; i < patientsToRestore.size(); i++) {
            fillDataByYear(diseaseName, yearsToRestore.get(i), (int)(long)patientsToRestore.get(i));
        }

//        return patientsToRestore;
        return yearsToRestore;
    }


}
