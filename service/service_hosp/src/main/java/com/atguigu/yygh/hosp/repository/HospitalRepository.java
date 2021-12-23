package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface HospitalRepository extends MongoRepository<Hospital,String> {
    //自定义的mongodb 查询方法需要根据springdata 规则来写
    Hospital getHospitalByHoscode(String hosCode);

}
