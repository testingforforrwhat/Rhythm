package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.Operate;
import com.test.service.OperateService;
import com.test.mapper.OperateMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【operate】的数据库操作Service实现
* @createDate 2024-06-27 03:10:16
*/
@Service
public class OperateServiceImpl extends ServiceImpl<OperateMapper, Operate>
    implements OperateService{

}




