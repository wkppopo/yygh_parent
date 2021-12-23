package com.atguigu.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.atguigu.yygh.cmn.listener.DictListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.cmn.service.DictService;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.model.cmn.DictEeVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    private DictMapper dictMapper;
    @Autowired
    public void setDictMapper(DictMapper dictMapper){
        this.dictMapper=dictMapper;
    }
    /**
     *  根据父ID查询是否有子项
     */
    @Override //
    @Cacheable(value="dict",keyGenerator="keyGenerator") //将方法返回的数据存入缓存
    public List<Dict> findByParentId(long id) {
        System.out.println("进入findByParentId()方法");
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id",id);

        List<Dict> dictList = baseMapper.selectList(dictQueryWrapper);
        //遍历数组中的所有 Dict 对象
        for (Dict dict : dictList) {
            Long id1 = dict.getId();
            if(isChildren(id1)){
                dict.setHasChildren(true);
            }else{
                dict.setHasChildren(false);
            }
        }
        return dictList;
    }


    /**
     *  导出数据字典接口的实现
     */
    @Override
    public void exportDictData(HttpServletResponse response) throws IOException {
        //设置下载的信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //URLEncoder.encode("数据字典","utf-8") 可以防止中文乱码 与easyExcel无关
        String fileName= URLEncoder.encode("数据字典","utf-8"); //输出一个中文的文件名
        response.setHeader("Content-disposition","attachment;fileName="+fileName+".xlsx");

        //创建一个新的List
        List<DictEeVo> dictEeVos = new ArrayList<>();

        //查询所有信息
        List<Dict> dicts = baseMapper.selectList(null);

        for (Dict dict : dicts) {
            DictEeVo dictEeVo = new DictEeVo();

            dictEeVo.setId(dict.getId());
            dictEeVo.setDictCode(dict.getDictCode());
            dictEeVo.setName(dict.getName());
            dictEeVo.setParentId(dict.getParentId());
            dictEeVo.setValue(dict.getValue());

            dictEeVos.add(dictEeVo);
        }
        //调用方法写出
        EasyExcel.write(response.getOutputStream(),DictEeVo.class).sheet("数据字典").doWrite(dictEeVos);
    }
    /**
     *  上传文件的接口
     */
    @Override
    @CacheEvict(value = "dict",allEntries = true)  //每次调用这个方法就会清空dict缓存中的所有内容
    public void importDictData(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(dictMapper)).sheet().doRead();
    }

    /**
     *  根据dictCode and value 查询name
     */
    @Override
    public String getDictName(String dictCode, String value) {
        //如果dictCode为空 则根据value查询
        if(StringUtils.isEmpty(dictCode)||dictCode==null){
            //直接根据value查询
            QueryWrapper<Dict> wrapper=new QueryWrapper<>();
            wrapper.eq("value",value); //条件查询
            Dict dict = dictMapper.selectOne(wrapper);
            return dict.getName();
        }else{
            QueryWrapper<Dict> wrapper=new QueryWrapper<>();
            wrapper.eq("dict_code",dictCode);
            Dict dict = dictMapper.selectOne(wrapper);
            Long parent_id = dict.getId();
            Dict findDict=dictMapper.selectOne(new QueryWrapper<Dict>().eq("parent_id",parent_id)
            .eq("value",value));
            return findDict.getName();
        }
    }

    /**
     *   根据父级 id 判断 是否有子节点 返回 Boolean
     */
    public boolean isChildren(long id){
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id",id);

        Integer integer = baseMapper.selectCount(dictQueryWrapper);

        return integer>0;
    }
}
