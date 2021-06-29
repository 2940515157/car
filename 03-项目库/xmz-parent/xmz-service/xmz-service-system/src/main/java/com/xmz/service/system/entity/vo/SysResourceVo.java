package com.xmz.service.system.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xmz.service.system.entity.pojo.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author KONG_No001
 * @since 2021-05-28
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SysResourceVo对象", description="资源")
public class SysResourceVo extends SysResource implements Serializable {
    @ApiModelProperty(value = "父资源")
    private String parentName;
    @ApiModelProperty(value = "有子类")
    private Boolean hasChildren;
}
