package com.test.mapper;

import com.test.bean.po.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 23194
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2024-06-27 02:34:06
* @Entity com.test.bean.po.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据账号username查询一个含有角色信息的登录用户对象
     * @param username
     * @return 一个含有角色信息的登录用户实体模型对象
     */
    Admin getOneByName(String username);

    /**
     * 根据账号username查询一个含有角色、菜单信息的登录用户对象
     * @param username
     * @return 一个含有角色、菜单信息的登录用户实体模型对象
     */
    Admin getOneWithRolelistMenulistByName(String username);

    int register(Admin admin);


    /**
     * 手机号码唯一性 校验
     * 数据库查询 用户注册的手机号码 是否已经存在
     * @param adminName
     * @return
     */
    List<Admin> getListByName(String adminName);


}




