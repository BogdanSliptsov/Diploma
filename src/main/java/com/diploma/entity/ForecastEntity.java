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

    @Column(name = "id_month")
    private Long idMonth;

    @Column(name = "id_desease")
    private Long idDesease;

    public ForecastEntity() {}

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

    public Long getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(Long idMonth) {
        this.idMonth = idMonth;
    }

    public Long getIdDesease() {
        return idDesease;
    }

    public void setIdDesease(Long idDesease) {
        this.idDesease = idDesease;
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
