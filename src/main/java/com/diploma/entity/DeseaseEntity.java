package com.diploma.entity;

import sun.security.krb5.internal.crypto.Des;

import javax.persistence.*;
import java.io.Serializable;

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

    public DeseaseEntity() {}

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
