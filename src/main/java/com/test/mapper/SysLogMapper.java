package com.test.mapper;

import com.test.bean.po.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author 23194
* @description 针对表【sys_log(系统日志)】的数据库操作Mapper
* @createDate 2024-06-02 13:25:12
* @Entity com.test.bean.po.SysLog
*/

@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}




