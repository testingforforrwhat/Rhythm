package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Role;
import com.test.service.RoleService;
import com.test.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-06-27 02:53:38
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




