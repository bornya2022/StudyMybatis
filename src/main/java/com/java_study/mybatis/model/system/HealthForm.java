package com.java_study.mybatis.model.system;

/**
 * 体检表父表
 */
public class HealthForm {
    private Long id;
    private Long empId;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String note;


    public Long getId() {
        return id;
    }

    public Long getEmpId() {
        return empId;
    }

    public String getHeart() {
        return heart;
    }

    public String getLiver() {
        return liver;
    }

    public String getSpleen() {
        return spleen;
    }

    public String getLung() {
        return lung;
    }

    public String getKidney() {
        return kidney;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public void setLiver(String liver) {
        this.liver = liver;
    }

    public void setSpleen(String spleen) {
        this.spleen = spleen;
    }

    public void setLung(String lung) {
        this.lung = lung;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
