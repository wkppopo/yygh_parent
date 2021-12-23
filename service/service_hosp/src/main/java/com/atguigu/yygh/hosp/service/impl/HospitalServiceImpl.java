package com.atguigu.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.atguigu.yygh.client.DictFeignClient;
import com.atguigu.yygh.hosp.repository.HospitalRepository;

import com.atguigu.yygh.hosp.service.HospitalService;
import com.atguigu.yygh.model.hosp.Hospital;
import com.atguigu.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 用于MongoDb 数据库
 */
@Service
public class  HospitalServiceImpl implements HospitalService {
    private HospitalRepository hospitalRepository;
    @Autowired
    private DictFeignClient dictFeignClient;

    @Autowired
    public void setHospitalRepository(HospitalRepository hospitalRepository){
        this.hospitalRepository=hospitalRepository;
    }


    @Override
    public void save(Map<String, Object> paramMap) {
        //判断是否存在数据
        String mapString = JSONObject.toJSONString(paramMap);//将map转成json字符串
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);
        String hoscode = hospital.getHoscode(); //获取医院编码信息
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);
        //如果不存在 添加

        //如果存在 修改
        if(hospitalExist!=null){
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }else{
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    /**
     *  从mongodb数据库中查询数据
     *  条件，分页合并查询
     *  返回 page 对象
     */
    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalSetQueryVo) {
        //创建Pageable对象
        Pageable pageable= PageRequest.of(page-1,limit);
        /*//创建条件匹配器
        ExampleMatcher exampleMatcher=ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);//忽略大小写

        //HospitalQueryVo 转换为Hospital对象
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalSetQueryVo,hospital);

        //创建Example对象
        Example<Hospital> example=Example.of(hospital,exampleMatcher);*/
        //Page<Hospital> all = hospitalRepository.findAll(example, pageable);//从mongodb中获取到信息
        Page<Hospital> all = hospitalRepository.findAll(pageable);
        System.out.println("******执行完从MongoDb中查询数据。。。然后执行遍历："+all.getContent());
/*

        all.getContent().stream().forEach(item->{
            this.setHospitalHosType(item);
        });
*/

        return all;
    }
    //用于上面的foreach遍历，对里面的每一个元素进行一次操作
    private Hospital setHospitalHosType(Hospital hospital) {
        //根据dictCode 和value 获取医院等级名称
        //远程接口调用接口中的方法
        String hostypeString =dictFeignClient.getName("Hostype",hospital.getHostype());
        String provinceString = dictFeignClient.getName("", hospital.getProvinceCode());
        String cityString = dictFeignClient.getName("", hospital.getCityCode());
        String districtString = dictFeignClient.getName("", hospital.getDistrictCode());

        hospital.getParam().put("fullAddress",provinceString+cityString+districtString);
        hospital.getParam().put("hostypeString",hostypeString);
        return hospital;
    }
}
