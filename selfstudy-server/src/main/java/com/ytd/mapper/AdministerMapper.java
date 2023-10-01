package com.ytd.mapper;

import com.ytd.entity.Administer;
import com.ytd.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdministerMapper {
    @Select("select * from administer where username = #{username}")
    Administer selectByUsername(String username);
    @Select("select * from administer where id = #{id}")
    Administer selectById(Long id);
    @Insert("insert into administer(username,password,status) values(#{username},#{password},#{status})")
    void addAdminister(Administer administer);
    @Select("select * from user")
    List<User> selectAll();
}
