package com.ytd.mapper;

import com.ytd.annotation.AutoFill;
import com.ytd.entity.Administer;
import com.ytd.entity.Notice;
import com.ytd.entity.Seat;
import com.ytd.entity.User;
import com.ytd.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdministerMapper {


    /**
     * 添加管理员
     * @param administer
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into administer(username,password,status,create_time) values(#{username},#{password},#{status},#{createTime})")
    void addAdminister(Administer administer);
    /**
     * 添加座位
     * @param seat
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into seat(area,seat_id,img,description,status,create_time) values(#{area},#{seatId},#{img},#{description},#{status},#{createTime})")
    void addSeat(Seat seat);

    /**
     * 新增通知
     * @param notice
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into notice(title,description,create_time) value(#{title},#{description},#{createTime})")
    void addNotice(Notice notice);

    /**
     * 通过用户名查询管理员信息
     * @param username
     * @return 管理员信息
     */
    @Select("select * from administer where username = #{username}")
    Administer selectAdministerByUsername(String username);
    /**
     * 通过id查询管理员信息
     * @param id
     * @return 管理员信息
     */
    @Select("select * from administer where id = #{id}")
    Administer selectAdministerById(Long id);
    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    @Select("select * from user")
    List<User> selectAllUser();




    /**
     * 根据id动态修改用户属性
     * @param user
     */
    @AutoFill(OperationType.UPDATE)
    void updateUser(User user);
    /**
     * 根据id动态修改管理员属性
     * @param administer
     */
    @AutoFill(OperationType.UPDATE)
    void updateAdminister(Administer administer);
    /**
     * 根据id动态修改座位属性
     * @param seat
     */
    @AutoFill(OperationType.UPDATE)
    void updateSeat(Seat seat);



    /**
     * 根据id删除座位属性
     * @param id
     */
    void deleteSeats(@Param("ids") int[] id);
    @Delete("delete from notice where id = #{id}")
    void deleteNotice(Integer id);
}
