package com.atguigu.yygh.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自动填充必须跟实体类在同一个项目 否则失效
 */
//@Slf4j
//@Component  implements MetaObjectHandler
public class MyMetaObjectHandler {
    /*@Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("create_time", new Date(),metaObject);
        this.setFieldValByName("update_time",new Date(),metaObject);
        this.setFieldValByName("is_deleted",0,metaObject);
    }

    *//**
     * 修改时填充
     *      加个if判断 可以避免手动填充的值被自动填充覆盖掉
     *
     * @param metaObject
     *//*
    @Override
    public void updateFill(MetaObject metaObject) {
        Object update_time = getFieldValByName("update_time", metaObject);
        if(update_time==null){
            this.setFieldValByName("update_time",new Date(),metaObject);
        }

    }*/
}
