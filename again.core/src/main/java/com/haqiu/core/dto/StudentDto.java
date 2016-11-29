package com.haqiu.core.dto;

import java.io.Serializable;

/**
 * Created by H-QIU on 2016/11/20.
 */
public class StudentDto implements Serializable {

    private Long sno;

    private String sname;

    private float avg;

    public Long getSno() {
        return sno;
    }

    public void setSno(Long sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }
}
