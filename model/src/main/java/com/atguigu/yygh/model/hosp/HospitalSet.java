package com.atguigu.yygh.model.hosp;

import com.atguigu.yygh.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ApiModel(description="医院设置") //当HospitalSet类成为控制器类的返回对象时 此对象被生成swagger 的PAI文档
@TableName(value="hospital_set",autoResultMap = true) //将实体类名与表名 实现映射 ,自动实现表字段与属性的映射
public class HospitalSet extends BaseEntity {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="医院名称")
    private String hosname;
    @ApiModelProperty(value="医院编号")
    private String hoscode;
    @ApiModelProperty(value = "api路径地址")
    private String apiUrl;

    @ApiModelProperty(value = "签名钥匙")
    private String signKey;
    @ApiModelProperty(value = "联系人名字")
    private String contactsName;
    @ApiModelProperty(value = "联系人手机号")
    private String contactsPhone;

    @ApiModelProperty(value = "状态")
    private Long status;

}
