package com.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.bean.po.SysLog;
import com.test.service.SysLogService;
import com.test.mapper.SysLogMapper;
import org.springframework.stereotype.Service;

/**
* @author 23194
* @description 针对表【sys_log(系统日志)】的数据库操作Service实现
* @createDate 2024-06-02 13:25:12
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
    implements SysLogService{

}




