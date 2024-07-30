package com.cuiziyu.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户注册请求体
 * @author cuiziyu
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 5636763034894235065L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

    private String identityCode;
}
