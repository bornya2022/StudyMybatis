package com.java_study.mybatis.mapper.system;

import com.java_study.mybatis.model.system.EmployeeTask;

import java.util.List;

public interface EmployeeTaskMapper {
    public List<EmployeeTask> getEmployeeTaskByEmpId();
}
