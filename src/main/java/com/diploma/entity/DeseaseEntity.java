package com.diploma.entity;

import sun.security.krb5.internal.crypto.Des;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by boubdyk on 22.02.2016.
 */

@Entity
@Table(name = "\"desease\"")
public class DeseaseEntity implements Serializable {

    @Id
    @Column(name = "iddesease")
    @GeneratedValue
    private Long id;

    @Column(name = "desease_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deseaseEntity")
    private List<ForecastEntity> forecastEntities;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deseaseEntity")
//    private List<PatientsEntity> patientsEntities;

    public DeseaseEntity() {}

    public DeseaseEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ForecastEntity> getForecastEntities() {
        return forecastEntities;
    }

    public void setForecastEntities(List<ForecastEntity> forecastEntities) {
        this.forecastEntities = forecastEntities;
    }

//    public List<PatientsEntity> getPatientsEntities() {
//        return patientsEntities;
//    }
//
//    public void setPatientsEntities(List<PatientsEntity> patientsEntities) {
//        this.patientsEntities = patientsEntities;
//    }
//


    @Override
    public String toString() {
        return new StringBuilder()
                    .append("DeseaseEntity[")
                    .append("id=")
                    .append(id)
                    .append(", name=")
                    .append(name)
                    .append("] ")
                    .toString();
    }
}
