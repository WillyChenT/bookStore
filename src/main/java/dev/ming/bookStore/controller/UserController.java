package dev.ming.bookStore.controller;

import dev.ming.bookStore.model.entity.User;
import dev.ming.bookStore.model.request.LoginRequest;
import dev.ming.bookStore.service.UserService;
import dev.ming.bookStore.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 註冊接口
     * @param userInfo
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo){
        int rows = userService.save(userInfo);

        return rows ==1 ? JsonData.buildSuccess():JsonData.buildError(400,"註冊失敗，請重新嘗試");

    }

    /**
     * 登入接口
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());

        return StringUtils.isNotBlank(token)?JsonData.buildSuccess(token):JsonData.buildError("帳號密碼錯誤，請重新輸入");
    }

    /**
     * 查詢用戶資訊
     * @param request
     * @return
     */
    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        if(null == userId){
            return JsonData.buildError("查詢失敗");
        }
        User user = userService.findByUserId(userId);

        return null!=user?JsonData.buildSuccess(user):JsonData.buildError("查無資料");
    }

}
