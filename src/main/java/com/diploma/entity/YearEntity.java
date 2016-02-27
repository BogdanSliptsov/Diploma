package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;

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

    public YearEntity() {}

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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("YearEntity[")
                .append("id=")
                .append(id)
                .append(", yearNumber=")
                .append(yearNumber)
                .append("] ")
                .toString();
    }
}
