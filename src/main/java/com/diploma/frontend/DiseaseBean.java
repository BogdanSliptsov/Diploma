package com.diploma.frontend;

import com.diploma.entity.DeseaseEntity;
import com.diploma.service.DeseaseService;
import com.diploma.service.GeneralService;
import com.diploma.service.PatientsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Yevhenii on 2/28/2016.
 */
public class DiseaseBean {

    private static ApplicationContext context;
    private static DeseaseService deseaseService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        deseaseService = context.getBean(DeseaseService.class);
    }

    public List<DeseaseEntity> getAll() {
        return deseaseService.getAllDeseases();
    }

}
