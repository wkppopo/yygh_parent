package com.atguigu.yygh.hosp.mapper;

import com.atguigu.yygh.model.hosp.HospitalSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository     //声明为IOC容器的数据库组件
public interface HospitalSetMapper extends BaseMapper<HospitalSet> {

}
