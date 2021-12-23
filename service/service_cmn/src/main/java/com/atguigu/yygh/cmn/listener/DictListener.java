package com.atguigu.yygh.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.yygh.cmn.mapper.DictMapper;
import com.atguigu.yygh.model.cmn.Dict;
import com.atguigu.yygh.model.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class DictListener extends AnalysisEventListener<DictEeVo> {
    //因为进行数据库的操作需要用到dao层的接口
    private DictMapper dictMapper;
    @Autowired
    public DictListener(DictMapper dictMapper){
        this.dictMapper=dictMapper;
    }

    //将每行的内容读取存入dictEeVo对象中
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        System.out.println("后台接收到上传数据的值:"+dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
