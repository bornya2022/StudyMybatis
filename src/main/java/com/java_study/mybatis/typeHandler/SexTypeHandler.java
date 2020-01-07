package com.java_study.mybatis.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexTypeHandler implements TypeHandler<String> {
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {

    }

    public String getResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public String getResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
