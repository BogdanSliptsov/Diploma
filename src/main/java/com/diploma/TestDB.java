package com.diploma;

import com.diploma.entity.DeseaseEntity;
import com.diploma.entity.PatientsEntity;
import com.diploma.service.DeseaseService;
import com.diploma.service.GeneralService;
import com.diploma.service.PatientsService;
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

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        deseaseService = context.getBean(DeseaseService.class);
        generalService = context.getBean(GeneralService.class);
        patientsService = context.getBean(PatientsService.class);
    }

    public static void main(String[] args) {

//        Map<Integer, Integer> map  = new HashMap<>();
//        map.put(1, 2000);
//        map.put(2, 2023);
//        map.put(3, 2045);
//        map.put(4, 2540);
//        map.put(5, 2120);
//        map.put(6, 1900);
//        generalService.fillDataByMonths("OPA", 2010, map);

        generalService.fillDataByYear("OPA", 2014, 9999);

        for (PatientsEntity p: patientsService.getAllPatients()) {
            System.out.println(p);
        }
    }
}
