package com.atguigu.yygh.user.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.atguigu.yygh.common.helper.JwtHelper;
import com.atguigu.yygh.model.user.UserInfo;
import com.atguigu.yygh.user.mapper.UserInfoMapper;
import com.atguigu.yygh.user.service.UserInfoService;
import com.atguigu.yygh.vo.user.LoginVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoserviceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    //用户手机号登陆接口
    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        String code = loginVo.getCode(); //获取验证码
        String phone = loginVo.getPhone(); //获取手机号

        //从loginVo中获取手机号和验证码
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(phone)){
            throw new RuntimeException("还未登陆");
        }
        //TODO 判断手机号和验证码是否为空

        //判断手机验证码和输入的验证码是否一致
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        UserInfo userInfo = baseMapper.selectOne(wrapper); //根据手机号码去数据库查询用户信息对象
        if(userInfo==null){
            userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setPhone(phone);
            userInfo.setStatus(1);//可用
            baseMapper.insert(userInfo); //将用户对象信息添加到数据库
        }
        //校验是否被禁用
        if(userInfo.getStatus()==0){
            throw new RuntimeException("登陆被禁");
        }

        //判断是否是第一次登陆，根据手机号查询是否是第一次登陆，如果不存在相同手机号就是第一次登陆

        //不是第一次直接登陆

        //返回登陆信息

        //返回登陆用户名

        //返回token信息
        Map<String,Object> map=new HashMap<>();
        String name = userInfo.getName();
        if(StringUtils.isEmpty(name)){
            name=userInfo.getNickName();
        }
        map.put("name",name);
        //TODO token的生成
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("token",token);

        return map;
    }
}
