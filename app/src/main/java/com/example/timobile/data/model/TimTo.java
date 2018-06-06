package com.example.timobile.data.model;

import java.io.Serializable;

/**
 * Created by Грунин Роман on 10.05.2018.
 */

public class TimTo implements Serializable {
    private String def_req;

    public String getMaintance_real() {
        return maintance_real;
    }

    public void setMaintance_real(String maintance_real) {
        this.maintance_real = maintance_real;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public String getDef_real() {
        return def_real;
    }

    public void setDef_real(String def_real) {
        this.def_real = def_real;
    }

    public String getRem_end_date() {
        return rem_end_date;
    }

    public void setRem_end_date(String req_end_date) {
        this.rem_end_date = req_end_date;
    }

    public String getIsp_name() {
        return isp_name;
    }

    public void setIsp_name(String isp_name) {
        this.isp_name = isp_name;
    }

    private String to_id;
    private String maintance_req;
    private String maintance_real;

    private String req_date;
    private String def_real;
    private String rem_end_date;
    private String req_owner_name;
    private String isp_name;


    public String getReq_owner_name() {
        return req_owner_name;
    }

    public void setReq_owner_name(String req_owner_name) {
        this.req_owner_name = req_owner_name;
    }


    public String getDef_req() {
        return def_req;
    }

    public void setDef_req(String def_req) {
        this.def_req = def_req;
    }

    public String getMaintance_req() {
        return maintance_req;
    }

    public void setMaintance_req(String maintance_req) {
        this.maintance_req = maintance_req;
    }


    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }
}
