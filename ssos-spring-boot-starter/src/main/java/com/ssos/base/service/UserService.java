package com.ssos.base.service;

import com.ssos.base.dto.UserDTO;
import com.ssos.base.dto.UserLoginDTO;
import com.ssos.base.entity.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-19 13:23
 * @Vsersion: 1.0
 */
public interface UserService {
    boolean addDate(UserDTO user);

    Integer updateDate(User user);

    List<User> selectAll(User user);

    String login(UserLoginDTO userLoginDTO);

}
