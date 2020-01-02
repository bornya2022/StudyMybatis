package com.java_study.mybatis.DbcpDataSource;

//自定义数据源工厂

import org.apache.ibatis.datasource.DataSourceFactory;


import javax.sql.DataSource;
import java.util.Properties;

/**
 * 当使用第三方数据源时，需要提供自定义的DataSourceFactory
 */
public class DbcpDataSourceFactory implements DataSourceFactory {
    private Properties properties=null;

    public void setProperties(Properties properties) {
        this.properties=properties;
    }

    public DataSource getDataSource() {
        DataSource dataSource= null;
        /**
         * BasicDataSourceFactory方法的实现需要导入org.apache.common.dbcp2.BasicDataSourceFactory包
         */
       // dataSource=BasicDataSourceFactory.createDataSource(properties);
        return  dataSource;
    }
}
