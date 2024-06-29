package com.test.mapper;

import com.test.bean.po.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 23194
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2024-06-27 02:34:06
* @Entity com.test.bean.po.Admin
*/
public interface AdminMapper extends BaseMapper<Admin> {

    Admin getOneByName(String username);
}




