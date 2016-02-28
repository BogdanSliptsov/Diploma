package com.diploma;

import com.diploma.entity.DeseaseEntity;
import com.diploma.entity.PatientsEntity;
import com.diploma.service.DeseaseService;
import com.diploma.service.GeneralService;
import com.diploma.service.PatientsService;
import com.diploma.service.YearService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by boubdyk on 27.02.2016.
 */
public class TestDB {

    private static ApplicationContext context;
    private static DeseaseService deseaseService;
    private static GeneralService generalService;
    private static PatientsService patientsService;
    private static YearService yearService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        deseaseService = context.getBean(DeseaseService.class);
        generalService = context.getBean(GeneralService.class);
        patientsService = context.getBean(PatientsService.class);
        yearService = context.getBean(YearService.class);
    }

    public static void main(String[] args) {

//        generalService.fillDataByYear("OPA", 2010, 2100);
//        generalService.fillDataByYear("OPA", 2011, 2765);
//        generalService.fillDataByYear("OPA", 2013, 2983);
//        generalService.fillDataByYear("OPA", 2014, 2481);
//        generalService.fillDataByYear("OPA", 2016, 2198);
//
//
//        for (Integer l : generalService.restoreDataForYears("OPA")) {
//            System.out.println(l);
//        }
//
//
//        for (PatientsEntity p: patientsService.getAllPatients()) {
//            System.out.println(p);
//        }

        System.out.println(yearService.createYear(2010));

    }
}
