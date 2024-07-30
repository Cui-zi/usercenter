package com.cuiziyu.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuiziyu.usercenter.common.BaseResponse;
import com.cuiziyu.usercenter.common.ErrorCode;
import com.cuiziyu.usercenter.common.ResultUtils;
import com.cuiziyu.usercenter.exception.BusinessException;
import com.cuiziyu.usercenter.model.domain.User;
import com.cuiziyu.usercenter.model.domain.request.UserLoginRequest;
import com.cuiziyu.usercenter.model.domain.request.UserRegisterRequest;
import com.cuiziyu.usercenter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.cuiziyu.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.cuiziyu.usercenter.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //用户注册请求
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        //校验
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount =userRegisterRequest.getUserAccount();
        String userPassword =userRegisterRequest.getUserPassword();
        String checkPassword =userRegisterRequest.getCheckPassword();
        String identityCode =userRegisterRequest.getIdentityCode();
        if(StringUtils.isAllBlank(userAccount,userPassword,checkPassword,identityCode)){
            return null;
        }

        //基础校验通过，调用业务逻辑层
        Long result = userService.userRegister(userAccount, userPassword, checkPassword, identityCode);
        return ResultUtils.success(result);
    }

    //用户登录请求
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }

        String userAccount =userLoginRequest.getUserAccount();
        String userPassword =userLoginRequest.getUserPassword();
        if(StringUtils.isAllBlank(userAccount,userPassword)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }

        //基础校验通过，调用业务逻辑层
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    //用户注销请求
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if(request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //基础校验通过，调用业务逻辑层
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    //获取当前用户信息
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            return null;
        }
        long userId = currentUser.getId();
        //TODO 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    //查询用户请求
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUser(String username, HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH, "缺少管理员权限");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    //删除用户请求
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if(id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    //判断是否为管理员函数
    private boolean isAdmin(HttpServletRequest request){
        //仅管理员可以查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }

}
