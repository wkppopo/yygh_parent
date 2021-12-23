package com.atguigu.yygh.model.base;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class BaseEntity implements Serializable {
    @ApiModelProperty(value="id")
    @TableId(type= IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="create_time")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(value="update_time")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除(0:未删除,1:已删除)")
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他参数")
    @TableField(exist = false) //不属于数据库的字段
    private Map<String,Object> param=new HashMap<>();
}
