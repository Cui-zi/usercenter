package com.cuiziyu.usercenter.service;

import com.cuiziyu.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

/**
* @author ASUS-ExpertBook
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-07-24 10:33:31
*/

/**
 * 用户服务
 * @author cuiziyu
 */
public interface UserService extends IService<User> {

    /**
     *用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String identityCode);



    /**
     * 用户登录
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    //用户信息脱敏函数
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
}
