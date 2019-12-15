package com.java_study.mybatis.typeHandler;


import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * 自定义类型转换器typeHandler
 * 继承TypeHandler接口
 */
public class MyTypeHandler implements TypeHandler<String>{
    //记录日志
    Logger logger =Logger.getLogger(MyTypeHandler.class);

    /**
     * 使用typeHandler通过PreparedStatement对象设置SQL参数
     * @param preparedStatement
     * @param i   参数在SQL的下标
     * @param s   参数
     * @param jdbcType  数据库类型
     * @throws SQLException
     */
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
           logger.info("设置string参数【"+s+"】");
           preparedStatement.setString(i,s);
    }

    /**
     * 从JDBC结果集中获取数据进行转换
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        String result=resultSet.getString(s);
        logger.info("读取string参数1【"+result+"】");
        return result;
    }

    public String getResult(ResultSet resultSet, int i) throws SQLException {
        String result=resultSet.getString(i);
        logger.info("读取string参数2【"+result+"】");
        return result;
    }

    /**
     * 存储过程专用
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        String result=callableStatement.getString(i);
        logger.info("读取string参数3【"+result+"】");
        return result;

    }
}
