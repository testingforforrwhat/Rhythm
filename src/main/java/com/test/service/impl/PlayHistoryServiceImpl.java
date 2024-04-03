package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.PlayHistory;
import com.test.service.PlayHistoryService;
import com.test.mapper.PlayHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【play_history】的数据库操作Service实现
* @createDate 2024-04-03 12:32:12
*/
@Service
public class PlayHistoryServiceImpl extends ServiceImpl<PlayHistoryMapper, PlayHistory>
    implements PlayHistoryService{

}




