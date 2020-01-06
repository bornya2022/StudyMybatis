package com.java_study.mybatis.model.system;

/**
 * 女雇员表
 */
public class FemaleEmployee extends Employee{
    private  FemaleHealthForm femaleHealthForm=null;

    public FemaleHealthForm getFemaleHealthForm() {
        return femaleHealthForm;
    }

    public void setFemaleHealthForm(FemaleHealthForm femaleHealthForm) {
        this.femaleHealthForm = femaleHealthForm;
    }
}
