package com.diploma.service;

import com.diploma.dao.DeseaseDAO;
import com.diploma.entity.DeseaseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Named
@Transactional
public class DeseaseService {

    private DeseaseDAO deseaseDAO;

    public DeseaseService() {}

    /**
     * Used to get all deseases from DB.
     * @return list of deseases.
     */
    public List<DeseaseEntity> getAllDeseases() {
        return deseaseDAO.getAll();
    }

    /**
     * Adding desease by name.
     * @param name desease name
     * @return id of desease in DB.
     */
    public Long addDeseaseByName(String name) {
        if (StringUtils.isEmpty(name)) return null;
        DeseaseEntity deseaseEntity = new DeseaseEntity(name);
        return deseaseDAO.create(deseaseEntity);
    }

    /**
     * Used to fix desease name.
     * @param actualName name that is present in DB.
     * @param newName new name(fixed name)
     * @return true if operation was successful, false in other case.
     */
    public boolean updateDeseaseByName(String actualName, String newName) {
        if (StringUtils.isEmpty(actualName) || StringUtils.isEmpty(newName))
            return false;
        Long id = deseaseDAO.getIdByName(actualName);
        DeseaseEntity deseaseEntity = deseaseDAO.read(id);
        deseaseEntity.setName(newName);
        deseaseDAO.update(deseaseEntity);
        return deseaseEntity.getName().equals(newName) ? true : false;
    }

    /**
     * Used to delete desease from DB.
     * @param name desease name.
     * @return string value that notifies user about success of operation or the reason of the problem.
     */
    public String deleteDesease(String name) {
        if (StringUtils.isEmpty(name)) return "ERROR! You've entered empty field.";
        Long id = deseaseDAO.getIdByName(name);
        if (id == null) return "Entered desease is absent in Data Base.";
        deseaseDAO.delete(id);
        return deseaseDAO.read(id) == null ? "Deleted." : "Not Deleted.";
    }
}
