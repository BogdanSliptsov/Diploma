package com.diploma.service;

import com.diploma.dao.TestDAO;
import com.diploma.entity.TestEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Named
@Transactional
public class TestService {

    @Inject
    private TestDAO testDAO;

    public TestService() {}

    public Long testCreate(TestEntity testEntity) {
        return testDAO.create(testEntity);
    }

    public TestEntity testRead(Long id) {
        return testDAO.read(id);
    }

    public TestEntity testUpdate(TestEntity testEntity) {
        return testDAO.update(testEntity);
    }

    public void testDelete(Long id) {
        testDAO.delete(id);
    }
}
