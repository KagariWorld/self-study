package com.ytd.mapper;

import com.ytd.entity.Administer;
import com.ytd.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdministerMapper {
    /**
     * 通过用户名查询管理员信息
     * @param username
     * @return
     */
    @Select("select * from administer where username = #{username}")
    Administer selectByUsername(String username);

    /**
     * 通过id查询管理员信息
     * @param id
     * @return
     */
    @Select("select * from administer where id = #{id}")
    Administer selectById(Long id);

    /**
     * 添加管理员
     * @param administer
     */
    @Insert("insert into administer(username,password,status,create_time) values(#{username},#{password},#{status},#{createTime})")
    void addAdminister(Administer administer);

    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select * from user")
    List<User> selectAll();

    /**
     * 根据id动态修改用户属性
     * @param user
     */
    void updateUser(User user);

    void updateAdminister(Administer administer);
}
