package com.test.service.impl;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.bo.UsersLoginBo;
import com.test.bean.bo.UsersRegistBo;
import com.test.bean.po.Users;
import com.test.service.UsersService;
import com.test.mapper.UsersMapper;
import com.test.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @author 23194
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-04-09 23:38:47
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    // 依赖项
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UsersMapper usersMapper;

    /**
     * 客户注册业务功能
     * @param usersRegistBo 客户注册业务模型对象
     * @return 注册结果。1：注册成功。-1：短信验证码校验失败。-2：手机号已注册账户
     * */
    @Override
    public int regist(UsersRegistBo usersRegistBo) {
        // 步骤一：短信验证码 校验
        // 1.1 从 Redis中存放的 正确短信验证码
        Object validate = redisUtil.get( "SMS-Validate-" + usersRegistBo.getUserPhone() );
        // 1.2 判断 正确短信验证码 是否和 用户填写的验证码 一致
        if( validate == null || !usersRegistBo.getUserValidate().equals( validate.toString() ) ){
            // 短信验证码 校验失败
            return -1;
        }

        // 步骤二：手机号码唯一性 校验
        // 去数据库查询 用户注册的手机号码 是否已经存在
        LambdaQueryWrapper<Users> usersWrapper = Wrappers.lambdaQuery();
        usersWrapper.eq( Users::getUsername , usersRegistBo.getUserLoginName() );
        List<Users> userInfoList = usersMapper.selectList( usersWrapper );
        if( userInfoList.size() > 0 ){
            // 手机号码唯一性 校验失败
            return -2;
        }

        // 可以执行注册业务
        // 步骤三：将当前用户填写的注册信息 添加到 数据库中
        // 3.1 实例化 用户实体模型对象
        Users userInfo = new Users();
        // 3.2 设置 用户实体模型对象的 成员属性
        userInfo.setUsername( usersRegistBo.getUserLoginName() );
        // 密码存入数据库：用户填写的密码明文 => 加盐 => 加密 => 密码密文
        // 生成密码盐值
        String salt = UUID.randomUUID().toString();
        userInfo.setPassword(
                //          MD5加密        (              用户填写的密码明文          + 密码盐值            )
                DigestUtils.md5DigestAsHex( ( usersRegistBo.getUserLoginPass() + salt ).getBytes() )
        );
        userInfo.setPhone( usersRegistBo.getUserPhone() );
        userInfo.setSalt( salt );
        // 3.3 将 用户实体模型对象 添加到数据库
        usersMapper.insert( userInfo );
        return 1;
    }

    /**
     * 客户登录业务功能
     * @param usersLoginBo 客户登录业务模型对象
     * @return 签发令牌Token。null表示登录失败。
     * */
    @Override
    public String login(UsersLoginBo usersLoginBo) {

        // 步骤一：账户名称校验
        // 判断 用户填写的账户名称（手机号）是否在数据库存在
        LambdaQueryWrapper<Users> usersWrapper = Wrappers.lambdaQuery();
        usersWrapper.eq( Users::getUsername , usersLoginBo.getUserLoginName() );
        List<Users> userInfoList = usersMapper.selectList( usersWrapper );
        if( userInfoList.size() == 0 ){
            // 账户名称校验 失败
            return null;
        }

        // 步骤二：账户密码校验
        // 2.1 获取 用户填写的账户名称（手机号） 在数据库的数据
        Users user = userInfoList.get(0);
        // 2.2 对 用户填写的密码明文，进行 加盐加密，成密文
        String password =
                //                  MD5加密(               用户登录时填写的密码明文   +  用户注册时生成的密码盐值    )
                DigestUtils.md5DigestAsHex( ( usersLoginBo.getUserLoginPass() + user.getSalt() ).getBytes() );
        // 2.3 将 用户登录时填写的密码明文，加盐加密后的密文，和数据库中该账户注册时的密码密文，判断是否相等
        if( ! password.equals( user.getPassword() ) ){
            // 账户密码校验 失败
            return null;
        }

        // 步骤三：登录校验成功。签发令牌。使用JWT（JSon Web Token）插件。
        String token = JWT.create()                             // 创建令牌
                .withAudience( JSON.toJSONString( user ) )  // 载荷客户信息
                .withIssuedAt( new Date() )                     // 载荷签发时间（系统当前时间）
                .withExpiresAt( new Date( System.currentTimeMillis() + 1000 * 60 * 24 ) )   // 载荷到期时间（24分钟之后的时间戳）
                .sign( Algorithm.HMAC256( user.getUserId().toString() ) );  // 签发令牌

        // 步骤四：将签发的令牌，存入Redis中。拼接上Authorization的策略（Bearer Token）前缀。
        redisUtil.set( "Bearer " + token , user , 60 * 24 );

        // 步骤五：返回签发的令牌
        return token;
    }

}




