package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boubdyk on 22.02.2016.
 */

@Entity
@Table(name = "\"patients\"")
public class PatientsEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "id_desease")
    private Long idDesease;

    @Column(name = "id_month")
    private Long idMonth;

    @Column(name = "number_of_patients")
    private Integer numberOfPatients;

    public PatientsEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDesease() {
        return idDesease;
    }

    public void setIdDesease(Long idDesease) {
        this.idDesease = idDesease;
    }

    public Long getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(Long idMonth) {
        this.idMonth = idMonth;
    }

    public Integer getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(Integer numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ForecastEntity[")
                .append("id=")
                .append(id)
                .append(", numberOfPatients=")
                .append(numberOfPatients)
                .append(", idMonth=")
                .append(idMonth)
                .append(", idDesease=")
                .append(idDesease)
                .append("] ")
                .toString();
    }
}
