package com.diploma.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by boubdyk on 27.02.2016.
 */

@Entity
@Table(name = "\"test_table\"")
public class TestEntity implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "field")
    private String field;

    public TestEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("TestEntity[")
                .append("id=")
                .append(id)
                .append(", field=")
                .append(field)
                .append("] ")
                .toString();
    }
}
