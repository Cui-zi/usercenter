package com.cuiziyu.usercenter.service;
import java.util.Date;

import com.cuiziyu.usercenter.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


//用户服务测试
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAdduser(){

        User user = new User();
        user.setId(0L);
        user.setUserAccount("123");
        user.setUserName("Ziyu");
        user.setAvatarUrl("https://himg.bdimg.com/sys/portrait/item/public.1.82d0a505.hd1F2_fxqGv1nPtIozr6cQ.jpg");
        user.setUserPassword("xxx");
        user.setGender(0);
        user.setPhone("123");
        user.setEmail("456");
        user.setUserStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);

        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }

    @Test
    void userRegister(){
        String userAccount = "ziyu";
        String userPassword = "";
        String checkPassword = "123456";
        String identityCode = "450404200210150013";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        Assertions.assertEquals(-1, result);

        userAccount = "zi";
        result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        Assertions.assertEquals(-1, result);

        userAccount = "ziyu";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        Assertions.assertEquals(-1, result);

        userAccount = "zi yu";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        Assertions.assertEquals(-1, result);

        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        Assertions.assertEquals(-1, result);

        userAccount = "cuiziyu";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        Assertions.assertEquals(-1, result);

        userAccount = "ziyu";
        result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        //Assertions.assertTrue(result > 0);
        Assertions.assertEquals(-1, result);
    }

}