package com.diploma.service;

import com.diploma.dao.ForecastDAO;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by boubdyk on 28.02.2016.
 */

@Named
@Transactional
public class ForecastService {

    @Inject
    private ForecastDAO forecastDAO;

    public ForecastService() {}


}
