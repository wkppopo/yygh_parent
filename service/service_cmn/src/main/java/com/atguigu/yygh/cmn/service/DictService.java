package com.atguigu.yygh.cmn.service;

import com.atguigu.yygh.model.cmn.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface DictService extends IService<Dict> {
    //根据父id查询子数据列表
    List<Dict> findByParentId(long id);
    //到处数据字典接口
    void exportDictData(HttpServletResponse response) throws IOException;

    //上传文件的接口
    void importDictData(MultipartFile file) throws IOException;

    //根据dictCode and value 查询name
    String getDictName(String dictCode, String value);
}
