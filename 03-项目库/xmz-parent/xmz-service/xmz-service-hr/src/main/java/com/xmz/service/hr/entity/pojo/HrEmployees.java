package com.xmz.service.hr.entity.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工信息
 * </p>
 *
 * @author hyc
 * @since 2021-05-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="HrEmployees对象", description="员工信息")
public class HrEmployees implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "uuid")
        @TableId(value = "EMP_UUID", type = IdType.ASSIGN_UUID)
      private String empUuid;

      @ApiModelProperty(value = "员工编号")
      @TableField("EMP_CODE")
    private String empCode;

      @ApiModelProperty(value = "姓名")
      @TableField("EMP_NAME")
    private String empName;

      @ApiModelProperty(value = "电话")
      @TableField("EMP_PHONE")
    private String empPhone;

      @ApiModelProperty(value = "性别")
      @TableField("EMP_SEX")
    private String empSex;

      @ApiModelProperty(value = "email")
      @TableField("EMP_EMAIL")
    private String empEmail;

      @ApiModelProperty(value = "生日")
      @TableField("EMP_BIRTHDAY")
    private Date empBirthday;

      @ApiModelProperty(value = "工资")
      @TableField("EMP_WAGE")
    private BigDecimal empWage;

      @ApiModelProperty(value = "等级")
      @TableField("EMP_GRADE")
    private BigDecimal empGrade;

      @ApiModelProperty(value = "门店UUID")
      @TableField("BR_UUID")
    private String brUuid;

      @ApiModelProperty(value = "部门UUID")
      @TableField("ORG_UUID")
    private String orgUuid;

      @ApiModelProperty(value = "对应账号ID")
      @TableField("ACCT_ID")
    private String acctId;

      @ApiModelProperty(value = "员工类别（SA、技师）")
      @TableField("EMP_TYPE")
    private String empType;

      @ApiModelProperty(value = "是否有效(Y:有效；N:无效)")
      @TableField("IS_ACTIVE")
    private String isActive;

      @ApiModelProperty(value = "创建日期")
      @TableField("CREATED_DATE")
    private Date createdDate;

      @ApiModelProperty(value = "修改日期")
      @TableField("UPDATED_DATE")
    private Date updatedDate;

      @ApiModelProperty(value = "创建人")
      @TableField("CREATED_BY")
    private String createdBy;

      @ApiModelProperty(value = "修改人")
      @TableField("UPDATED_BY")
    private String updatedBy;

      @ApiModelProperty(value = "版本")
      @TableField("VERSION")
    private BigDecimal version;

      @ApiModelProperty(value = "技师等级(大工,中工,小工)")
      @TableField("WORKER_LEVEL")
    private String workerLevel;

      @ApiModelProperty(value = "学历")
      @TableField("EDU_BACKGROUND")
    private String eduBackground;

      @ApiModelProperty(value = "证件类型")
      @TableField("CARD_TYEP")
    private String cardTyep;

      @ApiModelProperty(value = "证件号码")
      @TableField("CARD_NO")
    private String cardNo;

      @ApiModelProperty(value = "离职时间")
      @TableField("LEAVE_DATE")
    private Date leaveDate;

      @ApiModelProperty(value = "状态(正常、离职)")
      @TableField("STATUS")
    private String status;

      @ApiModelProperty(value = "离职原因")
      @TableField("LEAVE_REASON")
    private String leaveReason;

      @ApiModelProperty(value = "备注")
      @TableField("REMARK")
    private String remark;

      @ApiModelProperty(value = "拼音")
      @TableField("PINYIN")
    private String pinyin;

      @ApiModelProperty(value = "职位")
      @TableField("EMP_POST")
    private String empPost;

      @ApiModelProperty(value = "SA排名")
      @TableField("SA_RANK")
    private String saRank;


}
