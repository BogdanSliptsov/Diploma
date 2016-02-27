package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Entity
@Table(name = "\"year\"")
public class YearEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "year_number")
    private Integer yearNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "yearEntity")
    private List<MonthsEntity> monthsEntities;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "yearEntity")
//    private List<PatientsEntity> patientsEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "yearEntity")
    private List<ForecastEntity> forecastEntities;

    public YearEntity() {}

    public YearEntity(Integer yearNumber) {
        this.yearNumber = yearNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(Integer yearNumber) {
        this.yearNumber = yearNumber;
    }

    public List<MonthsEntity> getMonthsEntities() {
        return monthsEntities;
    }

    public void setMonthsEntities(List<MonthsEntity> monthsEntities) {
        this.monthsEntities = monthsEntities;
    }

//    public List<PatientsEntity> getPatientsEntities() {
//        return patientsEntities;
//    }
//
//    public void setPatientsEntities(List<PatientsEntity> patientsEntities) {
//        this.patientsEntities = patientsEntities;
//    }

    public List<ForecastEntity> getForecastEntities() {
        return forecastEntities;
    }

    public void setForecastEntities(List<ForecastEntity> forecastEntities) {
        this.forecastEntities = forecastEntities;
    }



    @Override
    public String toString() {
        return new StringBuilder()
                .append("YearEntity[")
                .append("id=")
                .append(id)
                .append(", yearNumber=")
                .append(yearNumber)
                .append(", monthsEntities=")
                .append(monthsEntities)
                .append("] ")
                .toString();
    }
}
