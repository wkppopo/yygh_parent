package com.atguigu.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestWriter {
    public static void main(String[] args) {
        //构建一个list集合
        List<UserData> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            UserData data = new UserData();
            data.setUid(i);
            data.setUsername("Lucy"+i);
            list.add(data);
        }
        //设置文件路径 和文件名称
        String fileName="E:\\excel\\01.xlsx";
        //调用方法实现写操作
        EasyExcel.write(fileName,UserData.class).sheet("用户信息").doWrite(list);


    }
}
