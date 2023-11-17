package com.ytd.mapper;

import com.ytd.annotation.AutoFill;
import com.ytd.entity.Administer;
import com.ytd.entity.Seat;
import com.ytd.entity.User;
import com.ytd.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 添加用户
     * @param user
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into user(username,password,status,create_time) values(#{username},#{password},#{status},#{createTime})")
    void addUser(User user);
    /**
     * 根据id动态修改用户属性
     * @param user
     */
    @AutoFill(OperationType.UPDATE)
    void updateUser(User user);
    /**
     * 根据id预约座位
     * @param id
     */
    @Update("update seat set status=1 where id=#{id}")
    void reserveSeat(Integer id);

    /**
     * 根据username查找user信息
     * @param username
     * @return user
     */
    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);
    /**
     * 通过id查询user信息
     * @param id
     * @return user
     */
    @Select("select * from user where id = #{id}")
    User selectUserById(Long id);
}
