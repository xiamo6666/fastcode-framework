package com.ssos.web.service;

import com.ssos.web.dto.UserDTO;
import com.ssos.web.entity.User;

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

    boolean updateDate(User user);

    List<User> selectAll(User user);

    String login(String loginToken);

}
