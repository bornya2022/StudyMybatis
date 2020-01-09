package com.java_study.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;

/**
 *构建SqlSessionFactory和获取SqlSession对象工具类
 * 采用单例模式构建，确保只有一个SqlSession对象存在。
 */
public class SqlSessionFactoryUtils {
    private final static Class<SqlSessionFactoryUtils> LOCK=SqlSessionFactoryUtils.class;
    private static SqlSessionFactory sqlSessionFactory=null;

    /**
     *构造方法添加private关键字，确保唯一性，使其它类或者方法不能通用new来创建该类对象。
     */
    private SqlSessionFactoryUtils(){}
    public static SqlSessionFactory getSqlSessionFactory(){
        synchronized (LOCK){
            if(sqlSessionFactory!=null){
                return sqlSessionFactory;
            }
            String resource="mybatis_cfg.xml";
            InputStream inputStream=null;
            try {
                inputStream= Resources.getResourceAsStream(resource);
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession openSqlSession(){
        if(sqlSessionFactory==null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }

}
