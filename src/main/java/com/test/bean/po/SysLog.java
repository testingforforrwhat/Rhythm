package com.test.bean.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统日志
 * @TableName sys_log
 */
@TableName(value ="sys_log")
@Data
public class SysLog implements Serializable {

    public SysLog(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long logId;

    /**
     * 描述
     */
    private String description;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 地址
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 异常详细
     */
    private String exceptionDetail;

    /**
     * 创建日期
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}