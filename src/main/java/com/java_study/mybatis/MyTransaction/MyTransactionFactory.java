package com.java_study.mybatis.MyTransaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

// 自定义事务工厂
public class MyTransactionFactory implements TransactionFactory  {
    public void setProperties(Properties properties) {

    }

    public Transaction newTransaction(Connection connection) {
        return new MyTransaction(connection);
    }

    /**
     *
     * @param dataSource  数据源
     * @param transactionIsolationLevel   事务隔离级别
     * @param b
     * @return
     */
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
        return new MyTransaction(dataSource,transactionIsolationLevel,b);
    }
}
