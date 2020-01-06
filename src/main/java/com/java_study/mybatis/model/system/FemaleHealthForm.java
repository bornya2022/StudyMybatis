package com.java_study.mybatis.model.system;

/**
 * 女性体检表
 */
public class FemaleHealthForm extends HealthForm {
    private  String uterus;

    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }
}
