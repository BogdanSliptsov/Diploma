package com.diploma.service;

import com.diploma.dao.DeseaseDAO;
import com.diploma.dao.ForecastDAO;
import com.diploma.dao.MonthsDAO;
import com.diploma.dao.PatientsDAO;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

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

    //TODO
}
