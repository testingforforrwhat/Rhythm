package com.test.bean.bo;

import lombok.Data;

/**
 * 客户登录业务模型类
 * */
@Data
public class AdminLoginBo {

    private String adminName;       // 账户名称（手机号）
    private String adminPass;       // 账户密码明文

}
