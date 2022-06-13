package com.fc.core;

import cn.hutool.json.JSONObject;
import cn.hutool.json.ObjectMapper;
import com.fc.common.model.LoginInfo;

public class MainTest {
    public static void main(String[] args) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId(123321L);
        JSONObject entries = new JSONObject(loginInfo);
        ObjectMapper.of(loginInfo).map(entries, null);
    }
}
