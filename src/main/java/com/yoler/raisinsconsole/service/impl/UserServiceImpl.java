package com.yoler.raisinsconsole.service.impl;

import com.yoler.raisinsconsole.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("browserUserServiceImpl")
public class UserServiceImpl implements UserService {

    @Override
    public Map<String, Object> signIn(String userName, String password) {
        Map<String, Object> result = new HashMap<>();
        if (userName.equals("admin") && password.equals("admin")) {
            result.put("status", "200");
        } else {
            result.put("status", "500");
            result.put("errorMsg", "用户名或密码错误");
        }
        return result;
    }
}
