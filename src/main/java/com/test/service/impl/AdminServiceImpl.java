package com.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.bo.AdminRegistBo;
import com.test.bean.po.Admin;
import com.test.bean.po.Users;
import com.test.service.AdminService;
import com.test.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 23194
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2024-06-27 02:34:06
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println("开始查询数据库... ");

        // 调用数据访问层 根据账号名称 获取 含有角色列表、菜单列表的管理员对象  resultMap/collection  类的聚合关系
        Admin admin = adminMapper.getOneByName( username );

        System.out.println("根据username查询到的用户账号信息: " + admin);
        if( admin == null ){
            System.out.println("账户名称或密码错误！请重新填写！");
            throw new UsernameNotFoundException("账户名称或密码错误！请重新填写！");
        }
        return admin;
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int regist(AdminRegistBo adminRegistBo) {

        // 步骤二：手机号码唯一性 校验
        // 去数据库查询 用户注册的手机号码 是否已经存在
        List<Admin> adminInfoList = adminMapper.getListByName( adminRegistBo.getAdminName() );
        System.out.println("根据username查询到的用户账号唯一性信息: " + adminInfoList);
        if( adminInfoList.size() > 0 ){
            // 手机号码唯一性 校验失败
            return -2;
        }

        Admin admin = new Admin();
        // 调用加密器将前端传递过来的密码进行加密
        admin.setAdminName(adminRegistBo.getAdminName());
        admin.setAdminPass(passwordEncoder.encode(adminRegistBo.getAdminPass()));
        // 将用户实体对象添加到数据库
        System.out.println("正在将用户实体对象添加到数据库");
        adminMapper.register( admin );
        return 1;
    }
}




