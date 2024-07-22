package com.test.mapper;

import com.test.bean.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 23194
* @description 针对表【role】的数据库操作Mapper
* @createDate 2024-06-27 02:53:38
* @Entity com.test.bean.po.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据登录用户编号查询该登录用户拥有的角色列表
     * @param AdminId
     * @return
     */
    List<Role> selectListByAdminId(String AdminId);

    List<Role> getListByOperateUrl(String requestUrl);
}




