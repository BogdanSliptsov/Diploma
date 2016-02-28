package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by boubdyk on 22.02.2016.
 */

@Entity
@Table(name = "\"months\"")
public class MonthsEntity implements Serializable {

    @Id
    @Column(name = "idmonths")
    @GeneratedValue
    private Long id;

    @Column(name = "months_number")
    private Integer monthsNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_year")
    private YearEntity yearEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthsEntity")
    private List<ForecastEntity> forecastEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthsEntity")
    private List<PatientsEntity> patientsEntities;

    public MonthsEntity() {}

    public MonthsEntity(Integer monthsNumber, YearEntity yearEntity) {
        this.monthsNumber = monthsNumber;
        this.yearEntity = yearEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthsNumber() {
        return monthsNumber;
    }

    public void setMonthsNumber(Integer monthsNumber) {
        this.monthsNumber = monthsNumber;
    }

    public YearEntity getYearEntity() {
        return yearEntity;
    }

    public void setYearEntity(YearEntity yearEntity) {
        this.yearEntity = yearEntity;
    }

    public List<ForecastEntity> getForecastEntities() {
        return forecastEntities;
    }

    public void setForecastEntities(List<ForecastEntity> forecastEntities) {
        this.forecastEntities = forecastEntities;
    }

    public List<PatientsEntity> getPatientsEntities() {
        return patientsEntities;
    }

    public void setPatientsEntities(List<PatientsEntity> patientsEntities) {
        this.patientsEntities = patientsEntities;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MonthEntity[")
                .append("id=")
                .append(id)
                .append(", monthsNumber=")
                .append(monthsNumber)
                .append(", yearEntity=")
                .append(yearEntity.getYearNumber())
                .append("] ")
                .toString();
    }
}
