package com.atguigu.yygh.model.cmn;

import com.alibaba.excel.annotation.ExcelProperty;
import com.atguigu.yygh.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@ApiModel(description="数据字典")
@TableName(value="dict",autoResultMap = true) //将实体类名与表名 实现映射 ,自动实现表字段与属性的映射
public class Dict extends BaseEntity {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="id")
    @TableId(type= IdType.NONE)
    private Long id;

    @ApiModelProperty(value = "上级id")
    private long parentId;


    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "编码")
    private String dictCode;


    @ApiModelProperty(value = "值")
    private String value;


    @ApiModelProperty(value = "是否包含子节点")
    @TableField(exist = false)
    private boolean hasChildren;
}
