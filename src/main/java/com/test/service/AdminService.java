package com.test.service;

import com.test.bean.bo.AdminRegistBo;
import com.test.bean.po.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
* @author 23194
* @description 针对表【admin】的数据库操作Service
* @createDate 2024-06-27 02:34:06
*/
public interface AdminService extends IService<Admin>, UserDetailsService {

    int regist(AdminRegistBo adminRegistBo);
}
