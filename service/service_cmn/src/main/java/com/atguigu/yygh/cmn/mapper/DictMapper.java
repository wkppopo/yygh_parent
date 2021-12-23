package com.atguigu.yygh.cmn.mapper;

import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository     //声明为IOC容器的数据库组件
@MapperScan("com.atguigu.yygh.cmn.mapper")
public interface DictMapper extends BaseMapper<Dict> {

}
