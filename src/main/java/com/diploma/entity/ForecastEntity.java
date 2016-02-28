package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boubdyk on 22.02.2016.
 */

@Entity
@Table(name = "\"forecast\"")
public class ForecastEntity implements Serializable {

    @Id
    @Column(name = "idforecast")
    @GeneratedValue
    private Long id;

    @Column(name = "number_of_patients")
    private Integer numberOfPatients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_desease")
    private DeseaseEntity deseaseEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_month")
    private MonthsEntity monthsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_year")
    private YearEntity yearEntity;

    @Column(name = "methodName")
    private String methodName;

    public ForecastEntity() {}

    public ForecastEntity(Integer numberOfPatients, DeseaseEntity deseaseEntity, YearEntity yearEntity, String methodName) {
        this.numberOfPatients = numberOfPatients;
        this.deseaseEntity = deseaseEntity;
        this.yearEntity = yearEntity;
        this.methodName = methodName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(Integer numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public DeseaseEntity getDeseaseEntity() {
        return deseaseEntity;
    }

    public void setDeseaseEntity(DeseaseEntity deseaseEntity) {
        this.deseaseEntity = deseaseEntity;
    }

    public MonthsEntity getMonthsEntity() {
        return monthsEntity;
    }

    public void setMonthsEntity(MonthsEntity monthsEntity) {
        this.monthsEntity = monthsEntity;
    }

    public YearEntity getYearEntity() {
        return yearEntity;
    }

    public void setYearEntity(YearEntity yearEntity) {
        this.yearEntity = yearEntity;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ForecastEntity[")
                .append("id=")
                .append(id)
                .append(", numberOfPatients=")
                .append(numberOfPatients)
                .append(", methodName=")
                .append(methodName)
                .append(", monthsEntity=")
                .append(monthsEntity)
                .append(", deseaseEntity=")
                .append(deseaseEntity)
                .append(", yearEntity=")
                .append(yearEntity)
                .append("] ")
                .toString();
    }
}
