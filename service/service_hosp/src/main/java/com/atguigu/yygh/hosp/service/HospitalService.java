package com.atguigu.yygh.hosp.service;

import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface HospitalService {
    void save(Map<String, Object> stringObjectMap);

    //医院列表条件查询带分页
    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalSetQueryVo);
}
