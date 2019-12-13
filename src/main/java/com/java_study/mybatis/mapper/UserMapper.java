package com.java_study.mybatis.mapper;

import com.java_study.mybatis.model.User;

import java.util.List;

public interface UserMapper {
    /*
     * 新增用戶
     * @param user
     * @return
     * @throws Exception
     */
    public int insertUser(User user) throws  Exception;
    /*
     * 修改用戶
     * @param user
     * @param id
     * @return
     * @throws Exception
     */
    public int updateUser(User user) throws Exception;
    /*
     * 刪除用戶
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteUser(User user) throws Exception;
    /*
     * 根据 id 查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public User selectUser(User user) throws Exception;
    /*
     * 查询所有的用户信息
     * @return
     * @throws Exception
     */
    public List<User> selectAllUSer() throws Exception;
}
