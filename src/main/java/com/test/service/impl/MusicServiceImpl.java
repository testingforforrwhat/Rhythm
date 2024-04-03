package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Music;
import com.test.service.MusicService;
import com.test.mapper.MusicMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【music】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService{

}




