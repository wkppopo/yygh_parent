package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.common.result.ResultCodeEnum;
import com.atguigu.yygh.common.utils.MD5;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

//因为默认不再使用代理，所以需要后端配置跨域，springboot 在controller上添加注解解决跨域
//import org.springframework.web.bind.annotation.*;
//@CrossOrigin

@CrossOrigin(origins="*",maxAge=3600) //用于跨域但不起效
@RestController
@RequestMapping("/swagger/admin/hosp/hospitalSet")
@Api(tags = "医院设置控制器类") //tags =给控制器类起个别名
public class HospitalSetController {
    //在控制器中使用set注入service
    private HospitalSetService hospitalSetService;

    @Autowired
    public void setHospitalSetService(HospitalSetService hospitalSetService) {
        this.hospitalSetService = hospitalSetService;
    }

    /**
     * 查询所有
     */
    @GetMapping("/findAll")
    @ApiOperation(value="查询所有",notes = "实现注意事项：没有参数")
    public Result<List<HospitalSet>> findAllHospitalSet(){
        /*try{
            int i=1/0;
        }catch(Exception e){
            throw new RuntimeException("类:controller  行：40 --findAll请求方法发生异常" );
        }*/

        List<HospitalSet> list = hospitalSetService.list();
        if(list!=null){
            return Result.ok(list);
        }else{
            return Result.ok(null);
        }
    }
    /**
     * 根据 id 查询单个对象
     */
    //swagger/admin/hosp/hospitalSet/selectById/{id}
    @GetMapping("/selectById/{id}")
    @ApiOperation(value="根据id查询 返回单个对象",notes="实现注意事项：需要数据表的id参数")
    public Result<HospitalSet> hospitalSet(@PathVariable("id") int id){
        HospitalSet byId = hospitalSetService.getById(id);
        if(byId !=null){
            return Result.ok(byId);
        }else{
            return Result.ok(null);
        }
    }

    /**
     * 插入数据
     */
    @PostMapping("/insert")
    @ApiOperation(value="增加数据",notes="实现注意事项：hoscode 唯一约束")
    public boolean insertInto(){
        HospitalSet hospitalSet = new HospitalSet();

        hospitalSet.setHosname("协和医院");
        hospitalSet.setHoscode("4789565399785"); //唯一约束
        hospitalSet.setApiUrl("http://www.bing.com");
        hospitalSet.setSignKey("2684981651");
        hospitalSet.setContactsName("老六");
        hospitalSet.setContactsPhone("17377501961");
        hospitalSet.setStatus(1l);

        /**
         * 下面的字段做了 自动填充 本模块中的启动类
         *
         * 必须 加在主启动类上
         *      @ComponentScan("com.atguigu")  //这条很重要--跨模块代码功能依赖这个注解进行完全扫描
         */
        /*hospitalSet.setCreateTime(new Timestamp(new Date().getTime()));
        hospitalSet.setUpdateTime(new Timestamp(new Date().getTime()));
        hospitalSet.setIsDeleted(0);*/

        return hospitalSetService.save(hospitalSet);
    }

    /***
     * 逻辑删除
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id实现逻辑删除数据",notes="实现注意事项：需要一个id参数")
    public Result  deleteById(@PathVariable long id){
        boolean b = hospitalSetService.removeById(id);
        if(b){
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else{
            return Result.ok(ResultCodeEnum.FAIL);
        }
    }
    /**
     * 条件分页查询
     */
    @PostMapping("/findpage/{current}/{limit}")
    @ApiOperation(value = "分页查询带条件")
    public Result pageHospitalSet(@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        Page<HospitalSet> hospitalSetPage = new Page<>(current, limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();

        String name = hospitalSetQueryVo.getHosname();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        String code = hospitalSetQueryVo.getHoscode();
        if(!StringUtils.isEmpty(code))
        wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());

        Page<HospitalSet> page = hospitalSetService.page(hospitalSetPage, wrapper);

        return Result.ok(page);
    }
    /**
     * 增加医院设置
     */
    @PostMapping("/saveHospitalSet")
    @ApiOperation(value = "添加数据到库")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){

        //自定义生成签名key
        hospitalSet.setStatus(1l); //1：能使用  0:不能使用
        Random random = new Random();
        int i = random.nextInt(55);
        hospitalSet.setSignKey(MD5.encrypt(String.valueOf(i)));

        boolean save = hospitalSetService.save(hospitalSet);
        if(save){
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else {
            return Result.fail(ResultCodeEnum.FAIL);
        }

    }

    /**
     * 根据 id 获取数据  getById(id)
     */
    @GetMapping("/getHospSet/{id}")
    @ApiOperation(value="根据id获取数据")
    public Result getHospSet(@PathVariable int id){
        HospitalSet byId = hospitalSetService.getById(id);
        return Result.ok(byId);
    }

    /**
     * 修改数据 updateById (hospitalSet)
     */

    @ApiOperation(value="修改数据")
    @PostMapping("/updateHospitalSet")
    public Result updateHosp(@RequestBody HospitalSet hospitalSet){
        boolean b = hospitalSetService.updateById(hospitalSet);
        if(b){
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else{
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }


    /**
     * 批量删除
     */
    @ApiOperation(value="批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList){
        boolean b = hospitalSetService.removeByIds(idList);
        if(b){
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else{
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

   /* *//**
     *锁定
     *//*
    @PutMapping("/lockHospitalSet/{id}/{status}")
    @ApiOperation(value="锁定")
    public Result lockHospitalSet(@PathVariable int id,@PathVariable long status){
        HospitalSet byId = hospitalSetService.getById(id);
        byId.setStatus(status);
        boolean b = hospitalSetService.updateById(byId);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
*/
    /**
     * 发送签名密钥
     */
    @PutMapping("/sendKey/{id}")
    @ApiOperation(value="发送签名密钥")
    public Result sendKey(@PathVariable long id){
        HospitalSet byId = hospitalSetService.getById(id);
        String hoscode = byId.getHoscode();
        String signKey = byId.getSignKey();
        //TODO 发送短信
        return Result.ok();
    }
    /**
     * 锁定
     */
    @ApiOperation(value="锁定行")
    @PutMapping("/lock/{id}/{status}")
    public Result lockHospSet(@PathVariable long id,@PathVariable long status){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        Long status1 = hospitalSet.getStatus();
        if(status1==status){
            return Result.ok("传入的状态码相同");
        }else{
            hospitalSet.setStatus(status);
        }
        boolean b = hospitalSetService.updateById(hospitalSet);
        if(b){
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else{
            return Result.ok(ResultCodeEnum.FAIL);
        }
    }
}
