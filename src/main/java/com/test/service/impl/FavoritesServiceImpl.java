package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Favorites;
import com.test.service.FavoritesService;
import com.test.mapper.FavoritesMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【favorites】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites>
    implements FavoritesService{

}




