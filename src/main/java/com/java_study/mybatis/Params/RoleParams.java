package com.java_study.mybatis.Params;

/**
 * 通过JavaBean传递多个参数
 */
public class RoleParams {
    private String roleName;
    private String note;

    public String getRoleName() {
        return roleName;
    }

    public String getNote() {
        return note;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
