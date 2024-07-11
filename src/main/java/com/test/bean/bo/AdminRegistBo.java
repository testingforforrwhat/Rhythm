package com.test.bean.bo;

import lombok.Data;

/**
 * 客户模块注册业务模型类
 * */
@Data
public class AdminRegistBo {

    private String adminName;   // 登录账户名称（手机号）
    private String adminPass;   // 登录账户密码明文

}
