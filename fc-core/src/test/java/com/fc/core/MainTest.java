package com.fc.core;

import cn.hutool.json.JSONObject;
import cn.hutool.json.ObjectMapper;
import com.fc.common.model.LoginInfo;

import java.util.Map;
public class MainTest {
    public static void main(String[] args) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId("123321");
        JSONObject entries = new JSONObject(loginInfo);
        ObjectMapper.of(loginInfo).map(entries, null);
    }
}
