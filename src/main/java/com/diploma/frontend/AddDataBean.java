package com.diploma.frontend;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "addData")
public class AddDataBean {

    private String addBy;


    public AddDataBean() {
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy;
    }
}
