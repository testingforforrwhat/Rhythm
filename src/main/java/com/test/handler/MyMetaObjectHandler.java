package com.test.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    Date now = new Date();

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 或者
        this.strictInsertFill(metaObject, "createdAt", () -> now, Date.class); // 起始版本 3.3.3(推荐)

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");

        this.strictUpdateFill(metaObject, "updatedAt", () -> now, Date.class); // 起始版本 3.3.3(推荐)
    }
}