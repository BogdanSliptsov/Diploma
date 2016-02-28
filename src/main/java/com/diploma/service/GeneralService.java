package com.diploma.service;

import com.diploma.Point;
import com.diploma.approximation.ExponentialSmoothing;
import com.diploma.dao.*;
import com.diploma.entity.*;
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

    @Inject
    private YearService yearService;

    @Inject
    private ForecastService forecastService;

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
//        YearEntity yearEntity = new YearEntity(year);
//        Long yearId = yearDAO.create(yearEntity);
        Long yearId = yearService.createYear(year);
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
     * @return id of new record.
     */
    public Long fillDataByYear(String diseaseName, Integer year, Integer numberOfPatients) {
        Long diseaseId = deseaseDAO.getIdByName(diseaseName);
//        YearEntity yearEntity = new YearEntity(year);
//        Long yearId = yearDAO.create(yearEntity);
        Long yearId = yearService.createYear(year);

        PatientsEntity patientsEntity = new PatientsEntity(numberOfPatients, yearDAO.read(yearId), deseaseDAO.read(diseaseId));
        return patientsDAO.create(patientsEntity);
    }

    /**
     * Used to restore number of patients for absent years.
     * @param diseaseName desease name.
     * @return List of restored years or null of there nothing to restore.
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

//    public void restoreDataForMonths(String diseaseName) {
//
//    }

    /**
     * Used to get all patients of disease by disease name.
     * @param diseaseName disease name.
     * @return map: key=year, value=numberOfPatients.
     */
    public Map<Integer, Integer> getAllPatientsOfDisease(String diseaseName) {
        Long diseaseId = deseaseDAO.getIdByName(diseaseName);
//        List<Integer> numberOfPatients = patientsDAO.getNumberOfPatientsByDiseaseIdForYears(diseaseId);
        List<Integer> actualNumberOfPatients = new ArrayList<>();
        List<Long> actualYearIDs = patientsDAO.getAllYearsForDisease(diseaseId);
        List<Integer> actualYearsNumbers = new ArrayList<>();

        int counterForNextLoop = 0;
        for (Long yearId : actualYearIDs) {
            actualYearsNumbers.add(yearDAO.getYearNumberByYearId(yearId));
            actualNumberOfPatients.add(patientsDAO.getNumberOfPatientsForYearID(diseaseId, yearId));
            counterForNextLoop++;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < counterForNextLoop; i++) {
            map.put(actualYearsNumbers.get(i), actualNumberOfPatients.get(i));
        }

        return map;
    }

    /**
     * Used to make forecast for disease by years.
     * @param diseaseName
     * @param lastYear value max = 5
     */
    public void expSmoothForecastByYear(String diseaseName, int lastYear) {
        ExponentialSmoothing exponentialSmoothing = new ExponentialSmoothing();
        List<Double> inputListForSmoothing = new ArrayList<>();
        Long diseaseId = deseaseDAO.getIdByName(diseaseName);
//        List<Integer> numberOfPatients = patientsDAO.getNumberOfPatientsByDiseaseIdForYears(diseaseId);
        List<Long> actualYearIDs = patientsDAO.getAllYearsForDisease(diseaseId);
        List<Integer> actualYearsNumbers = new ArrayList<>();
        List<Integer> actualNumberOfPatients = new ArrayList<>();


        for (Long yearId : actualYearIDs) {
            actualYearsNumbers.add(yearDAO.getYearNumberByYearId(yearId));
            actualNumberOfPatients.add(patientsDAO.getNumberOfPatientsForYearID(diseaseId, yearId));
        }

        int lastLoop = actualNumberOfPatients.size();
        lastYear ++;
        while (lastYear > 0) {
            for (int i = 0; i < actualNumberOfPatients.size(); i++) {
                inputListForSmoothing.add(actualNumberOfPatients.get(i) + 0.0);
            }
            List<Double> smoothList = exponentialSmoothing.smooth(inputListForSmoothing);
            Double newValue = smoothList.get(smoothList.size() - 1);
            inputListForSmoothing.add(newValue);
            //Add forecasted years
            Long yearId = yearService.createYear(actualYearsNumbers.get(actualYearsNumbers.size() - 1) + 1);
            actualYearsNumbers.add(yearDAO.getYearNumberByYearId(yearId));
            actualNumberOfPatients.add(newValue.intValue());
            lastYear--;
        }

        forecastService.deleteForYear("SMOOTHING", deseaseDAO.getIdByName(diseaseName));

        //Addind years to table YEAR and gettin year IDs
        for (int i = 0; i < actualNumberOfPatients.size(); i++) {
            if (i != lastLoop) {
                forecastDAO.create(new ForecastEntity(actualNumberOfPatients.get(i), deseaseDAO.read(diseaseId),
                        yearDAO.read(yearService.createYear(actualYearsNumbers.get(i))), "SMOOTHING"));
            }
        }
    }

    /**
     * Used to get all forecasted patients of disease by disease name.
     * @param diseaseName disease name.
     * @return map: key=year, value=numberOfPatients.
     */
    public Map<Integer, Integer> getAllForecastedPatientsOfDiseaseSmoothing(String diseaseName) {
        Long diseaseId = deseaseDAO.getIdByName(diseaseName);
//        List<Integer> numberOfPatients = patientsDAO.getNumberOfPatientsByDiseaseIdForYears(diseaseId);
        List<Integer> actualNumberOfPatients = new ArrayList<>();
        List<Long> actualYearIDs = forecastDAO.getAllForecastedYearsForDisease(diseaseId);
        List<Integer> actualYearsNumbers = new ArrayList<>();

        int counterForNextLoop = 0;
        for (Long yearId : actualYearIDs) {
            actualYearsNumbers.add(yearDAO.getYearNumberByYearId(yearId));
            actualNumberOfPatients.add(forecastDAO.getNumberOfForecastedPatientsForYearID(diseaseId, yearId));
            counterForNextLoop++;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < counterForNextLoop; i++) {
            map.put(actualYearsNumbers.get(i), actualNumberOfPatients.get(i));
        }

        return map;
    }

}
