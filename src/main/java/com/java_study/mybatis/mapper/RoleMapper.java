package com.java_study.mybatis.mapper;

import com.java_study.mybatis.Params.RoleParams;
import com.java_study.mybatis.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    public int insertRole(Role role);
    public int deleteRole(Long id);
    public int updateRole(Role role);
    public Role getRole(Long id);

    /**
     * 通过角色名获得角色对象列表
     * @param roleName
     * @return
     */
    public List<Role> findRoles(String roleName);
    /**
     * 传递多个参数：
     * 法一：采用map接口，
     * 缺点：map属于键值对集合，可读性差，不能限制参数的数据类型，业务性质不强
     */
    public List<Role> findRoleByMap(Map<String,Object> parameterMap);

    /**
     * 法二：采用Mybatis提供的注解@Param，通过它定义参数名称
     * @param rolename
     * @param note
     * @return
     */
    public List<Role> findRoleByAnntation(@Param("roleName") String rolename,@Param("note") String note);

    /**
     * 通过JavaBean传递多个参数
     * @param roleParams
     * @return
     */
    public List<Role> findRoleByBean(RoleParams roleParams);

    /**
     * 分页查询
     * @param roleName
     * @param note
     * @param rowBounds  分页参数，Mybatis自动识别(提供2个重要参数：offset(偏移量)和limit(限制条数))s
     * @return
     */
    public  List<Role> findByRowBounds(@Param("roleName") String roleName, @Param("note") String note ,RowBounds rowBounds );
}
