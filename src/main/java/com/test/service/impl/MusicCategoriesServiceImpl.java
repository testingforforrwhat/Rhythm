package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.MusicCategories;
import com.test.service.MusicCategoriesService;
import com.test.mapper.MusicCategoriesMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【music_categories】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicCategoriesServiceImpl extends ServiceImpl<MusicCategoriesMapper, MusicCategories>
    implements MusicCategoriesService{

}




