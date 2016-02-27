package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "year")
    private Integer year;

    @Column(name = "months_number")
    private Integer monthsNumber;

    @Column(name = "id_year")
    private Long idYear;

    public MonthsEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonthsNumber() {
        return monthsNumber;
    }

    public void setMonthsNumber(Integer monthsNumber) {
        this.monthsNumber = monthsNumber;
    }

    public Long getIdYear() {
        return idYear;
    }

    public void setIdYear(Long idYear) {
        this.idYear = idYear;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MonthEntity[")
                .append("id=")
                .append(id)
                .append(", year=")
                .append(year)
                .append(", monthsNumber=")
                .append(monthsNumber)
                .append(", idYear=")
                .append(idYear)
                .append("] ")
                .toString();
    }
}
