package com.cuiziyu.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 * @author cuiziyu
 */
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 5636763034894235065L;

    private String userAccount;

    private String userPassword;
}
