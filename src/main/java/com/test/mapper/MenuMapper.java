package com.test.mapper;

import com.test.bean.po.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.bean.po.Role;

import java.util.List;

/**
* @author 23194
* @description 针对表【menu】的数据库操作Mapper
* @createDate 2024-07-22 11:30:12
* @Entity com.test.bean.po.Menu
*/
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据登录用户编号查询该登录用户拥有的菜单列表
     * @param AdminId
     * @return
     */
    List<Menu> selectListByAdminId(String AdminId);

}




