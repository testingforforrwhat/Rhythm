package com.test.service;

import com.test.bean.bo.UsersLoginBo;
import com.test.bean.bo.UsersRegistBo;
import com.test.bean.po.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23194
* @description 针对表【users】的数据库操作Service
* @createDate 2024-04-09 23:38:47
*/
public interface UsersService extends IService<Users> {

    /**
     * 客户注册业务功能
     * @param usersRegistBo 客户注册业务模型对象
     * @return 注册结果。1：注册成功。-1：短信验证码校验失败。-2：手机号已注册账户
     * */
    int regist(UsersRegistBo usersRegistBo);

    /**
     * 客户登录业务功能
     * @param usersLoginBo 客户登录业务模型对象
     * @return 签发令牌Token。null表示登录失败。
     * */
    String login(UsersLoginBo usersLoginBo);

}
