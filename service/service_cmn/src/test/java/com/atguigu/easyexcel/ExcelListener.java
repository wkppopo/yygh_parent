package com.atguigu.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<UserData> {
    //一行一行的读取 从第二行开始(第一行表头不读)
    @Override       //将每行读取到的内容存到userData中
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("读取到的表头的信息："+headMap);
    }



}
