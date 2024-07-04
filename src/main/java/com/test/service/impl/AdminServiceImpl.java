package com.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Admin;
import com.test.service.AdminService;
import com.test.mapper.AdminMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        Admin admin = adminMapper.getOneByName( username );
        System.out.println("根据username查询到的用户账号信息: " + admin);
        if( admin == null ){
            System.out.println("账户名称或密码错误！请重新填写！");
            throw new UsernameNotFoundException("账户名称或密码错误！请重新填写！");
        }
        return admin;
    }

}




