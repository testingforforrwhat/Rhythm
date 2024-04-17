package com.test.controller.users;


import com.test.bean.bo.UsersLoginBo;
import com.test.bean.bo.UsersRegistBo;
import com.test.bean.dto.BaseDTO;
import com.test.bean.dto.DTO;
import com.test.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户模块 控制器
 * */
@RestController
@RequestMapping("/customer")
public class UsersController {

    // 依赖项
    @Autowired
    private UsersService usersService;

    /**
     * 客户注册接口
     * post http://127.0.0.1:8001/customer/regist
     * @param usersRegistBo 客户注册业务模型对象
     * @return 响应报文体
     * */
    @PostMapping("/regist")
    public BaseDTO regist(UsersRegistBo usersRegistBo ){
        // 调用业务逻辑层 的 客户注册功能
        int result = usersService.regist( usersRegistBo );
        // 实例化 DTO 对象 => 封装响应报文体
        BaseDTO dto = new BaseDTO();
        if( result == 1 ){
            dto.setCode( 200 );
            dto.setMessage( "注册成功" );
        }else if( result == -1 ){
            dto.setCode( 501 );
            dto.setMessage( "短信验证码校验失败" );
        }else if( result == -2 ){
            dto.setCode( 502 );
            dto.setMessage( "该手机号码已注册" );
        }
        return dto;
    }

    /**
     * 客户登录接口
     * post http://127.0.0.1:8001/customer/login
     * @param usersLoginBo 客户登录业务模型对象
     * @return 响应报文体
     * */
    @PostMapping("/login")
    public DTO login(UsersLoginBo usersLoginBo ){
        // 调用业务逻辑层的 客户登录功能
        String token = usersService.login( usersLoginBo );
        // 实例化 DTO 对象，封装响应报文体
        DTO dto = new DTO();
        // 判断 登录是否成功
        if( token == null ){
            // 登录失败
            dto.setCode( 401 );
            dto.setMessage( "手机号或密码错误，请重新填写" );
        }else{
            // 登录成功
            dto.setCode( 200 );
            dto.setMessage( "登录成功" );
            dto.setData( token );
        }
        return dto;
    }

}
