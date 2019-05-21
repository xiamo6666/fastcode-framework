package com.ssos.base.mapper;


import com.ssos.base.entity.User;
import com.ssos.mybatilsUtils.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-19 14:58
 * @Vsersion: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select p.identify from user u\n" +
            "LEFT JOIN user_role ur on u.id = ur.user_id\n" +
            "LEFT JOIN role r on ur.role_id = r.id\n" +
            "left JOIN role_permission rp on r.id = rp.role_id\n" +
            "LEFT JOIN permission p on p.id = rp.permission_id\n" +
            "where u.username = #{username}")
    List<String> queryPermission(@Param("username") String username);


    @Select("select r.name from `user` u\n" +
            "LEFT JOIN user_role ur on u.id = ur.user_id\n" +
            "LEFT JOIN role r on ur.role_id = r.id\n" +
            "where u.id=#{userId}")
    List<String> qeuryRole(@Param("userId") Long userId);
}
