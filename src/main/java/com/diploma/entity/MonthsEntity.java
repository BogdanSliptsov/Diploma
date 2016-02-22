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

    @Override
    public String toString() {
        return new StringBuilder()
                .append("MonthEntity[")
                .append("id=")
                .append(id)
                .append(", year=")
                .append(year)
                .append("] ")
                .toString();
    }
}
