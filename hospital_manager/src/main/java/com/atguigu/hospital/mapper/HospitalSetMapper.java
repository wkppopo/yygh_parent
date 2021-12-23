package com.atguigu.hospital.mapper;

import com.atguigu.hospital.model.HospitalSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper    //这个注解就是为了避免写.xml文件
@Repository
public interface HospitalSetMapper extends BaseMapper<HospitalSet> {

}
