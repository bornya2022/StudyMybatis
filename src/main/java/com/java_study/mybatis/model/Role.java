package com.java_study.mybatis.model;


import java.io.Serializable;

/**
 * 实现Serializable接口，使Role可序列化
 * Mybatis二级缓存开启默认要求对应的POJO对象是可序列化的
 */
public class Role implements Serializable {
    public static final long serialVersionUID=598736524547906734L;
    private Long id;
    private String roleName;
    private String note;

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
