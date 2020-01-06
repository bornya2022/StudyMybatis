package com.java_study.mybatis.model.system;

/**
 *男雇员表
 */
public class MaleEmployee extends  Employee{
    private MaleHealthForm maleHealthForm=null;

    public MaleHealthForm getMaleHealthForm() {
        return maleHealthForm;
    }

    public void setMaleHealthForm(MaleHealthForm maleHealthForm) {
        this.maleHealthForm = maleHealthForm;
    }
}
