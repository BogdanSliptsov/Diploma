package com.diploma.service;

import com.diploma.dao.ForecastDAO;
import com.diploma.entity.ForecastEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by boubdyk on 28.02.2016.
 */

@Named
@Transactional
public class ForecastService {

    @Inject
    private ForecastDAO forecastDAO;

    public ForecastService() {}

    public List<ForecastEntity> getForecastForDisease(Long diseaseId) {
        return forecastDAO.getAllByDiseaseId(diseaseId);
    }

    public boolean deleteForYear(String methodName, Long diseaseId) {
        forecastDAO.deleteByMethodNameForYears(methodName, diseaseId);
        return true;
    }

    public List<ForecastEntity> getForecastByDiseaseIdForSmoothing(Long diseaseId) {
        return forecastDAO.getAllForecastRecordsByDiseaseForSmoothing(diseaseId);
    }
}
