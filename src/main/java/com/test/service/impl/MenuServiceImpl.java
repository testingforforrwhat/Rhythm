package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Menu;
import com.test.service.MenuService;
import com.test.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2024-07-22 11:30:12
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




