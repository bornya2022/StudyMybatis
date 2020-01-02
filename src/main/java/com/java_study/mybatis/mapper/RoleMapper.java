package com.java_study.mybatis.mapper;

import com.java_study.mybatis.model.Role;

import java.util.List;

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
}
