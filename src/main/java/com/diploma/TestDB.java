package com.diploma;

import com.diploma.entity.TestEntity;
import com.diploma.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by boubdyk on 27.02.2016.
 */
public class TestDB {

    private static ApplicationContext context;
    private static TestService testService;

    static {
        context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
        testService = context.getBean(TestService.class);
    }

    public static void main(String[] args) {
        TestEntity testEntity = new TestEntity();

//        testEntity.setField("fildets123");
//        System.out.println(testService.testCreate(testEntity));

//        System.out.println(testService.testRead(2L));

//        TestEntity te = new TestEntity();
//        te.setId(2L);
//        te.setField("FILDDDD");
//        System.out.println(testService.testUpdate(te));

        testService.testDelete(2L);
    }
}
