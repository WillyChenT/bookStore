package dev.ming.bookStore.service.impl;

import dev.ming.bookStore.exception.BasicException;
import dev.ming.bookStore.mapper.UserMapper;
import dev.ming.bookStore.model.entity.User;
import dev.ming.bookStore.service.UserService;
import dev.ming.bookStore.utils.CommonUtils;
import dev.ming.bookStore.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(Map<String, String> userInfo) {

        User user = parseToUser(userInfo);

        if(null != user) {
            return userMapper.save(user);
        }else{
            return -1;
        }

    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {

        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));

        if(null==user){
            return null;
        }else {
            return JWTUtils.genJsonWebToken(user);
        }
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = userMapper.findByUserId(userId);
        //在User Entity中使用JsonIgnore註解 避免回傳重要資訊
        return user;
    }


    private User parseToUser(Map<String,String> userInfo){

        if(userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            user.setPhone(userInfo.get("phone"));
            String pwd = userInfo.get("pwd");
            //MD5加密
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        }else{
            throw new BasicException(400,"缺少必要資料拉!");
        }
    }

    /**
     * 隨機頭像清單
     */
    private static final String [] headImg = {
            "https://upload.cc/i1/2021/04/05/vCxrGD.png",
            "https://upload.cc/i1/2021/04/05/Nt2oMn.png",
            "https://upload.cc/i1/2021/04/05/okRUz8.png",
            "https://upload.cc/i1/2021/04/05/Tig3C0.png",
            "https://upload.cc/i1/2021/04/05/bdVZuD.png"
    };

    private String getRandomImg(){
        int size = headImg.length;

        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }

}
