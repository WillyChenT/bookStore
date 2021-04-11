package dev.ming.bookStore.service;

import dev.ming.bookStore.model.entity.User;

import java.util.Map;

public interface UserService {

    int save(Map<String,String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
