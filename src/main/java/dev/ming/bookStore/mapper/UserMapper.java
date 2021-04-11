package dev.ming.bookStore.mapper;

import dev.ming.bookStore.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int save(User user);

    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    User findByUserId(@Param("user_id")Integer userId);
}
