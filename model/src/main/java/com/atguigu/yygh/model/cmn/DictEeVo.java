package com.atguigu.yygh.model.cmn;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "dict")
public class DictEeVo {
    @ExcelProperty(value = "id",index = 0)
    private Long id;
    @ExcelProperty(value = "上级id",index = 1)
    private long parentId;
    @ExcelProperty(value = "名称",index = 2)
    private String name;
    @ExcelProperty(value = "值",index = 3)
    private String value;
    @ExcelProperty(value = "编码",index = 4)
    private String dictCode;

}

