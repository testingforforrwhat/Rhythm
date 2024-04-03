package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Users;
import com.test.service.UsersService;
import com.test.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




