package com.ytd.mapper;

import com.ytd.entity.Administer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministerMapper {
    @Select("select * from administer where username = #{username}")
    Administer selectByUsername(String username);
    @Insert("insert into administer(username,password,status) value(#{username},#{password},#{status})")
    boolean addAdminister(Administer administer);
}
