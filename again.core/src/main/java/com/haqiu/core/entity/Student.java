package com.haqiu.core.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_student")
public class Student implements Serializable {
    private static final long serialVersionUID = -1333100668202265970L;

    @Id
    private Long sno;

    private String sname;

    private String sdree;

    private Short sage;

    private String ssex;

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

    public String getSdree() {
        return sdree;
    }

    public void setSdree(String sdree) {
        this.sdree = sdree;
    }

    public Short getSage() {
        return sage;
    }

    public void setSage(Short sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
}
