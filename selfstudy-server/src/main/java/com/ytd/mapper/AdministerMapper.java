package com.ytd.mapper;

import com.ytd.entity.Administer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministerMapper {
    @Select("select * from administer where username = #{username}")
    Administer selectByUsername(String username);
}
