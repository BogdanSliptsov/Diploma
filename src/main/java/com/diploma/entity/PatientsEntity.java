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

    @Column(name = "number_of_patients")
    private Integer numberOfPatients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_year")
    private YearEntity yearEntity;

//    @Column(name = "id_year")
//    private Long yearId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_month")
    private MonthsEntity monthsEntity;

//    @Column(name = "id_month")
//    private Long idMonth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_desease")
    private DeseaseEntity deseaseEntity;

//    @Column(name = "id_desease")
//    private Long diseaseId;

    public PatientsEntity() {}

    public PatientsEntity(Integer numberOfPatients, YearEntity yearEntity, MonthsEntity monthsEntity, DeseaseEntity deseaseEntity) {
        this.numberOfPatients = numberOfPatients;
        this.yearEntity = yearEntity;
        this.monthsEntity = monthsEntity;
        this.deseaseEntity = deseaseEntity;
    }

    public PatientsEntity(Integer numberOfPatients, YearEntity yearEntity, DeseaseEntity deseaseEntity) {
        this.numberOfPatients = numberOfPatients;
        this.yearEntity = yearEntity;
        this.deseaseEntity = deseaseEntity;
    }

    //
//    public PatientsEntity(Integer numberOfPatients, Long yearId, Long idMonth, Long diseaseId) {
//        this.numberOfPatients = numberOfPatients;
//        this.yearId = yearId;
//        this.idMonth = idMonth;
//        diseaseId = diseaseId;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonthsEntity getMonthsEntity() {
        return monthsEntity;
    }

    public void setMonthsEntity(MonthsEntity monthsEntity) {
        this.monthsEntity = monthsEntity;
    }

    public DeseaseEntity getDeseaseEntity() {
        return deseaseEntity;
    }

    public void setDeseaseEntity(DeseaseEntity deseaseEntity) {
        this.deseaseEntity = deseaseEntity;
    }

    public Integer getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(Integer numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public YearEntity getYearEntity() {
        return yearEntity;
    }

    public void setYearEntity(YearEntity yearEntity) {
        this.yearEntity = yearEntity;
    }


//    public Long getYearId() {
//        return yearId;
//    }
//
//    public void setYearId(Long yearId) {
//        this.yearId = yearId;
//    }
//
//    public Long getIdMonth() {
//        return idMonth;
//    }
//
//    public void setIdMonth(Long idMonth) {
//        this.idMonth = idMonth;
//    }
//
//    public Long getDiseaseId() {
//        return diseaseId;
//    }
//
//    public void setDiseaseId(Long diseaseId) {
//        diseaseId = diseaseId;
//    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("PatientsEntity[")
                .append("id=")
                .append(id)
                .append(", numberOfPatients=")
                .append(numberOfPatients)
                .append(", monthsEntity=")
                .append(monthsEntity)
                .append(", deseaseEntity=")
                .append(deseaseEntity)
                .append(", yearEntity=")
                .append(yearEntity)
//                .append(", yearId=")
//                .append(yearId)
//                .append(", idMonth=")
//                .append(idMonth)
//                .append(", diseaseId=")
//                .append(diseaseId)
                .append("] ")
                .toString();
    }
}
