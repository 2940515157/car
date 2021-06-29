package com.xmz.service.carsparts.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 车型
 * </p>
 *
 * @author weikang
 * @since 2021-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BaseCarType对象", description = "车型")
public class BaseCarType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "CAR_TYPE_UUID", type = IdType.ASSIGN_UUID)
    private String carTypeUuid;

    @ApiModelProperty(value = "立洋编码")
    @TableField("LY_CODE")
    private String lyCode;

    @ApiModelProperty(value = "车系ID")
    @TableField("CAR_CATEGORY_UUID")
    private String carCategoryUuid;

    @ApiModelProperty(value = "厂家")
    @TableField("FACTORY")
    private String factory;

    @ApiModelProperty(value = "品牌")
    @TableField("BRAND")
    private String brand;

    @ApiModelProperty(value = "车系")
    @TableField("CAR_CATEGORY")
    private String carCategory;

    @ApiModelProperty(value = "车型")
    @TableField("CAR_MODEL")
    private String carModel;

    @ApiModelProperty(value = "销售名称")
    @TableField("SALE_NAME")
    private String saleName;

    @ApiModelProperty(value = "销售版本")
    @TableField("SALE_VERSION")
    private String saleVersion;

    @ApiModelProperty(value = "年款")
    @TableField("CAR_YEAR")
    private BigDecimal carYear;

    @ApiModelProperty(value = "排放标准")
    @TableField("STA_EMISSION")
    private String staEmission;

    @ApiModelProperty(value = "车辆类型")
    @TableField("VEHICLE_TYPE")
    private String vehicleType;

    @ApiModelProperty(value = "车辆级别")
    @TableField("VEHICLE_LEVEL")
    private String vehicleLevel;

    @ApiModelProperty(value = "指导价格")
    @TableField("GUIDE_PRICE")
    private BigDecimal guidePrice;

    @ApiModelProperty(value = "上市时间")
    @TableField("TIME_TO_MARKET")
    private String timeToMarket;

    @ApiModelProperty(value = "上市年份")
    @TableField("TTM_YEAR")
    private BigDecimal ttmYear;

    @ApiModelProperty(value = "上市月份")
    @TableField("TTM_MONTH")
    private BigDecimal ttmMonth;

    @ApiModelProperty(value = "生产年份")
    @TableField("PRODUCTIVE_YEAR")
    private BigDecimal productiveYear;

    @ApiModelProperty(value = "停产年份")
    @TableField("STOP_YEAR")
    private BigDecimal stopYear;

    @ApiModelProperty(value = "生产状态")
    @TableField("PRODUCTION_STATUS")
    private String productionStatus;

    @ApiModelProperty(value = "销售状态")
    @TableField("SALES_STATUS")
    private String salesStatus;

    @ApiModelProperty(value = "国别")
    @TableField("COUNTRY")
    private String country;

    @ApiModelProperty(value = "国产合资进口")
    @TableField("DOMESTIC_TYPE")
    private String domesticType;

    @ApiModelProperty(value = "气缸容积")
    @TableField("CYLINDER_VOLUME")
    private String cylinderVolume;

    @ApiModelProperty(value = "排量(升）")
    @TableField("DISPLACEMENT")
    private String displacement;

    @ApiModelProperty(value = "进气形式")
    @TableField("INTAKE_FORM")
    private String intakeForm;

    @ApiModelProperty(value = "燃料类型")
    @TableField("FUEL_TYPE")
    private String fuelType;

    @ApiModelProperty(value = "燃油标号")
    @TableField("FUEL_MODEL")
    private String fuelModel;

    @ApiModelProperty(value = "最大马力(ps)")
    @TableField("MAX_HORSEPOWER")
    private String maxHorsepower;

    @ApiModelProperty(value = "最大功率(kW)")
    @TableField("MAX_POWER")
    private String maxPower;

    @ApiModelProperty(value = "最大功率转速(rpm)")
    @TableField("MAX_POWER_SPEED")
    private String maxPowerSpeed;

    @ApiModelProperty(value = "最大扭矩(N·m)")
    @TableField("MAX_TORQUE")
    private String maxTorque;

    @ApiModelProperty(value = "最大扭矩转速(rpm)")
    @TableField("MAX_TORQUE_SPEED")
    private String maxTorqueSpeed;

    @ApiModelProperty(value = "气缸排列形式")
    @TableField("CYLINDER_ARRANGE")
    private String cylinderArrange;

    @ApiModelProperty(value = "气缸数(个)")
    @TableField("CYLINDER_NUMBER")
    private BigDecimal cylinderNumber;

    @ApiModelProperty(value = "每缸气门数(个)")
    @TableField("THROTTLE_NUMBER")
    private BigDecimal throttleNumber;

    @ApiModelProperty(value = "压缩比")
    @TableField("COMPRESSION_RATIO")
    private String compressionRatio;

    @ApiModelProperty(value = "供油方式")
    @TableField("FUEL_WAY")
    private String fuelWay;

    @ApiModelProperty(value = "工信部综合油耗(L)")
    @TableField("MIIT_FUEL_CONSUMPTION")
    private String miitFuelConsumption;

    @ApiModelProperty(value = "市区工况油耗")
    @TableField("SRBAN_FUEL_CONSUMPTION")
    private String srbanFuelConsumption;

    @ApiModelProperty(value = "市郊工况油耗")
    @TableField("SUBURB_FUEL_CONSUMPTION")
    private String suburbFuelConsumption;

    @ApiModelProperty(value = "加速时间(0-100km/h)")
    @TableField("ACCELERATION_TIME")
    private BigDecimal accelerationTime;

    @ApiModelProperty(value = "最高车速")
    @TableField("MAX_SPEED")
    private String maxSpeed;

    @ApiModelProperty(value = "发动机特有技术")
    @TableField("ENGINE_ST")
    private String engineSt;

    @ApiModelProperty(value = "三元催化器")
    @TableField("TW_CATALYTIC_CONVERTER")
    private String twCatalyticConverter;

    @ApiModelProperty(value = "冷却方式")
    @TableField("COOLING_METHOD")
    private String coolingMethod;

    @ApiModelProperty(value = "缸径")
    @TableField("BORE")
    private String bore;

    @ApiModelProperty(value = "行程")
    @TableField("TRIP")
    private String trip;

    @ApiModelProperty(value = "发动机描述")
    @TableField("ENGINE_DESCRIPTION")
    private String engineDescription;

    @ApiModelProperty(value = "变速器类型")
    @TableField("TRANSMISSION_TYPE")
    private String transmissionType;

    @ApiModelProperty(value = "变速器描述")
    @TableField("TRANSMISSION_DESCRIPTION")
    private String transmissionDescription;

    @ApiModelProperty(value = "档位数")
    @TableField("GEAR_NUMBER")
    private String gearNumber;

    @ApiModelProperty(value = "前制动器类型")
    @TableField("FRONT_BRAKE_TYPE")
    private String frontBrakeType;

    @ApiModelProperty(value = "后制动器类型")
    @TableField("REAR_BRAKE_TYPE")
    private String rearBrakeType;

    @ApiModelProperty(value = "前悬挂类型")
    @TableField("FRONT_SUSPENSION_TYPE")
    private String frontSuspensionType;

    @ApiModelProperty(value = "后悬挂类型")
    @TableField("REAR_SUSPENSION_TYPE")
    private String rearSuspensionType;

    @ApiModelProperty(value = "转向机形式")
    @TableField("TURNAROUND_MODE")
    private String turnaroundMode;

    @ApiModelProperty(value = "助力类型")
    @TableField("POWER_TYPE")
    private String powerType;

    @ApiModelProperty(value = "最小离地间隙")
    @TableField("MIN_GROUND_CLEARANCE")
    private String minGroundClearance;

    @ApiModelProperty(value = "最小转弯半径")
    @TableField("MIN_TURNING_RADIUS")
    private String minTurningRadius;

    @ApiModelProperty(value = "离去角")
    @TableField("DEPARTURE_ANGLE")
    private String departureAngle;

    @ApiModelProperty(value = "接近角")
    @TableField("APPROACH_ANGLE")
    private String approachAngle;

    @ApiModelProperty(value = "发动机位置")
    @TableField("ENGINE_POSITION")
    private String enginePosition;

    @ApiModelProperty(value = "驱动方式")
    @TableField("DRIVING_METHOD")
    private String drivingMethod;

    @ApiModelProperty(value = "驱动形式")
    @TableField("DRIVE_FORM")
    private String driveForm;

    @ApiModelProperty(value = "车身型式")
    @TableField("BODY_TYPE")
    private String bodyType;

    @ApiModelProperty(value = "长度(mm)")
    @TableField("LENGTH")
    private String length;

    @ApiModelProperty(value = "宽度(mm)")
    @TableField("WIDTH")
    private String width;

    @ApiModelProperty(value = "高度(mm)")
    @TableField("HEIGHT")
    private String height;

    @ApiModelProperty(value = "轴距(mm)")
    @TableField("WHEELBASE")
    private String wheelbase;

    @ApiModelProperty(value = "前轮距(mm)")
    @TableField("FRONT_TRACK")
    private String frontTrack;

    @ApiModelProperty(value = "后轮距(mm)")
    @TableField("REAR_WHEEL")
    private String rearWheel;

    @ApiModelProperty(value = "整备质量(Kg)")
    @TableField("KERB_MASS")
    private String kerbMass;

    @ApiModelProperty(value = "最大载重质量(kg)")
    @TableField("MAX_LOAD_QUALITY")
    private String maxLoadQuality;

    @ApiModelProperty(value = "油箱容积(L)")
    @TableField("FUEL_TANK_CAPACITY")
    private BigDecimal fuelTankCapacity;

    @ApiModelProperty(value = "行李厢容积(L)")
    @TableField("BOOT")
    private String boot;

    @ApiModelProperty(value = "车顶型式")
    @TableField("ROOF_TYPE")
    private String roofType;

    @ApiModelProperty(value = "车篷型式")
    @TableField("HOOD_TYPE")
    private String hoodType;

    @ApiModelProperty(value = "车门数")
    @TableField("DOOR_NUMBER")
    private String doorNumber;

    @ApiModelProperty(value = "座位数")
    @TableField("SEAT_NUMBER")
    private String seatNumber;

    @ApiModelProperty(value = "前轮胎规格")
    @TableField("FRONT_TYRE_SPECIFICATIONS")
    private String frontTyreSpecifications;

    @ApiModelProperty(value = "后轮胎规格")
    @TableField("REAR_TIRE_SPECIFICATIONS")
    private String rearTireSpecifications;

    @ApiModelProperty(value = "前轮毂规格")
    @TableField("FWH_SPECIFICATIONS")
    private String fwhSpecifications;

    @ApiModelProperty(value = "后轮毂规格")
    @TableField("RWH_SPECIFICATION")
    private String rwhSpecification;

    @ApiModelProperty(value = "轮毂材料")
    @TableField("HUB_MATERIAL")
    private String hubMaterial;

    @ApiModelProperty(value = "备胎规格")
    @TableField("SPARE_TIRE_SPECIFICATIONS")
    private String spareTireSpecifications;

    @ApiModelProperty(value = "电动天窗")
    @TableField("ELECTRIC_SKYLIGHT")
    private String electricSkylight;

    @ApiModelProperty(value = "全景天窗")
    @TableField("PANORAMIC_SUNROOF")
    private String panoramicSunroof;

    @ApiModelProperty(value = "氙气大灯")
    @TableField("XENON_HEADLAMPS")
    private String xenonHeadlamps;

    @ApiModelProperty(value = "前雾灯")
    @TableField("FRONT_FOG_LAMP")
    private String frontFogLamp;

    @ApiModelProperty(value = "后雨刷")
    @TableField("REAR_WIPER")
    private String rearWiper;

    @ApiModelProperty(value = "空调")
    @TableField("AIR_CONDITIONER")
    private String airConditioner;

    @ApiModelProperty(value = "自动空调")
    @TableField("AUTO_AIR_CONDITIONING")
    private String autoAirConditioning;

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

    @ApiModelProperty(value = "车辆级别(立洋)")
    @TableField("VEHICLE_LEVEL_LY")
    private String vehicleLevelLy;

    @ApiModelProperty(value = "车型显示名称拼装规则：品牌 车型 销售名称 年款")
    @TableField("SHOW_NAME")
    private String showName;

    @ApiModelProperty(value = "发动机型号")
    @TableField("ENGINE_TYPE_NO")
    private String engineTypeNo;

    @ApiModelProperty(value = "精友车型代码")
    @TableField("JY_VEHICLE_CODE")
    private String jyVehicleCode;

    @ApiModelProperty(value = "精友车型ID")
    @TableField("JY_CAR_TYPE_UUID")
    private String jyCarTypeUuid;

    @ApiModelProperty(value = "是否启用Y/N")
    @TableField("USABLE_FLAG")
    private String usableFlag;


}
