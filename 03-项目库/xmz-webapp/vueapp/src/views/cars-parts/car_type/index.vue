<!-- - - - - - - - - - - - -->
<!--        车型管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <!--条件查询-->
        <el-form
                :inline="true"
                :model="baseCarTypeQuery"
                class="demo-form-inline"
                size="mini"
        >
            <el-form-item label="品牌">
                <el-select v-model="baseCarTypeQuery.brand"

                        placeholder="---请选择---"
                >
                    <el-option
                            v-for="item in options"
                            :key="item.brand"
                            :label="item.brand"
                            :value="item.brand"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="年款">
                <el-select v-model="baseCarTypeQuery.year"  placeholder="---请选择---">
                    <el-option :value="null"> ---请选择--- </el-option>
                    <el-option
                            v-for="item in yoption"
                            :key="item.carYear"
                            :label="item.carYear"
                            :value="item.carYear"
                    >
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="排量">
                <el-select
                        v-model="baseCarTypeQuery.cc"
                        placeholder="---请选择---">
                    <el-option :value="null"> ---请选择--- </el-option>
                    <el-option
                            v-for="item in doption"
                            :key="item.displacement"
                            :label="item.displacement"
                            :value="item.displacement">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="getbaseCarType">查询</el-button>
            </el-form-item>
        </el-form>
        <el-button size="mini" type="primary" @click="dialogFormVisible=true,form = {}">添加</el-button>
        <el-button size="mini" @click="update()">编辑</el-button>
        <el-button size="mini" type="danger" @click="handleDelete">删除</el-button>

        <!--表格数据-->
        <el-table
                ref="multipleTable"
                :data="tableDatacct"
                tooltip-effect="dark"
                style="width: 100%"
                max-height="500"
                @selection-change="handleSelectionChange"
        >

            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column prop="usableFlag" label="是否启用" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="brand" label="品牌" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="factory" label="厂家" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="carCategory" label="车系" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="carModel" label="车型" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="saleName" label="销售名称" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="carYear" label="年款" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="staEmission" label="排放标准" show-overflow-toolt>
            </el-table-column>
            <el-table-column
                    prop="vehicleType"
                    label="车辆类型"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="vehicleLevel" label="车辆等级" show-overflow-tooltip>
            </el-table-column
            >
            <el-table-column
                prop="vehicleLevelLy"
                label="车辆等级(力洋)"
                show-overflow-tooltip
                :width="formLabelWidth"
        >
        </el-table-column>
            <el-table-column
                    prop="guidePrice"
                    label="指导价格(万元)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column prop="ttmYear" label="上市年份" show-overflow-tooltip>
            </el-table-column
            ><el-table-column prop="ttmMonth" label="上市月份" show-overflow-tooltip>
        </el-table-column>
            <el-table-column
                    prop="productiveYear"
                    label="生产年份"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="stopYear" label="停产年份" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="productionStatus"
                    label="生产状态"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="country" label="国别" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="domesticType"
                    label="国产合资进口"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="cylinderVolume"
                    label="气缸容积"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="displacement"
                    label="排量(升)"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="intakeForm" label="进气形式" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="fuelType" label="燃料类型" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="fuelModel" label="燃油标号" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="maxHorsepower"
                    label="最大马力(ps)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column prop="maxPower" label="功率(kw)" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="maxPowerSpeed"
                    label="功率转速(rpm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="maxTorque"
                    label="扭矩(N/m)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="maxTorqueSpeed"
                    label="扭矩转速(rpm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="cylinderArrange"
                    label="气缸排列形式"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="cylinderNumber"
                    label="气缸数(个)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="throttleNumber"
                    label="每缸气门数(个)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="compressionRatio"
                    label="压缩比"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="fuelWay" label="供油方式" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="miitFuelConsumption"
                    label="工信部综合油耗(L)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="srbanFuelConsumption"
                    label="市区工况油耗"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="suburbFuelConsumption"
                    label="市郊工况油耗"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="accelerationTime"
                    label="加速时间(0-100km/h)"
                    show-overflow-tooltip
                    width="200px"
            >
            </el-table-column>
            <el-table-column
                    prop="maxSpeed"
                    label="最高车速(km/h)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="transmissionType"
                    label="变速箱类型"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="transmissionDescription"
                    label="变速箱描述"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column prop="gearNumber" label="挡位数" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="frontBrakeType"
                    label="前制动器类型"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="rearBrakeType"
                    label="后制动器类型"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column prop="powerType" label="助力类型" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="minGroundClearance"
                    label="最小离地间隙"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="minTurningRadius"
                    label="最小转弯半径"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="enginePosition"
                    label="发动机位置"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="drivingMethod"
                    label="驱动方式"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="length"
                    label="长度(mm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="width"
                    label="宽度(mm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="height"
                    label="高度(mm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="wheelbase"
                    label="轴距(mm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="frontTrack"
                    label="前轮距(mm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="rearWheel"
                    label="后轮距(mm)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="kerbMass"
                    label="整备质量(kg)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="maxLoadQuality"
                    label="最大载重质量(kg)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="fuelTankCapacity"
                    label="油箱容积(L)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="boot"
                    label="行李箱容积(L)"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column prop="doorNumber" label="车门数" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="seatNumber" label="座位数" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="frontTyreSpecifications"
                    label="前轮胎规格"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="rearTireSpecifications"
                    label="后轮胎规格"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="fwhSpecifications"
                    label="前轮毂规格"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="rwhSpecification"
                    label="后轮毂规格"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column
                    prop="hubMaterial"
                    label="轮毂材料"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="spareTireSpecifications"
                    label="备胎规格"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="electricSkylight"
                    label="电动天窗"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="panoramicSunroof"
                    label="全景天窗"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="xenonHeadlamps"
                    label="氙气大灯"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column prop="frontFogLamp" label="前雾灯" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="rearWiper" label="后雨刷" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="airConditioner" label="空调" show-overflow-tooltip>
            </el-table-column>
            <el-table-column
                    prop="autoAirConditioning"
                    label="自动空调"
                    show-overflow-tooltip
            >
            </el-table-column>
            <el-table-column
                    prop="engineTypeNo"
                    label="发动机型号"
                    show-overflow-tooltip
                    :width="formLabelWidth"
            >
            </el-table-column>
            <el-table-column prop="lyCode" label="力洋编码" show-overflow-tooltip>
            </el-table-column>
        </el-table>
        <!--分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="current"
                :page-sizes="pageSizeList"
                :page-size="limit"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
        <!--新增或修改的对话框-->
        <el-dialog
                :title="form.carTypeUuid == null ? '新增车型信息' : '编辑车型信息'" :visible.sync="dialogFormVisible">
            <el-form :model="form"  ref="form">
                <el-col :span="8">
                    <el-form-item label="立洋编码" :label-width="formLabelWidth" >
                        <el-input v-model="form.lyCode" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="8">
                    <el-form-item
                            label="品牌"
                            :label-width="formLabelWidth"
                            style="text-align: left"
                    >
                        <el-select
                                v-model="form.brand"
                                filterable
                                placeholder="---请选择---"
                        >
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in options"
                                    :key="item.brand"
                                    :label="item.brand"
                                    :value="item.brand"
                            >
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item
                            label="车系"
                            :label-width="formLabelWidth"
                            style="text-align: left">
                        <el-select v-model="form.carCategory" filterable placeholder="---请选择---" @change="addcchange">
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in coption"
                                    :key="item.carCategory"
                                    :label="item.carCategory"
                                    :value="item.carCategory">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item
                            label="厂家"
                            :label-width="formLabelWidth"
                            style="text-align: left">
                        <el-select v-model="form.factory" filterable placeholder="---请选择---">
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in foption"
                                    :key="item.factory"
                                    :label="item.factory"
                                    :value="item.factory">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="车型" :label-width="formLabelWidth">
                        <el-input v-model="form.carModel" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="销售名称" :label-width="formLabelWidth" >
                        <el-input v-model="form.saleName" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="年款" :label-width="formLabelWidth" style="text-align: left">
                        <el-select v-model="form.carYear" filterable placeholder="---请选择---">
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in yearoption"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="排放标准" :label-width="formLabelWidth">
                        <el-input v-model="form.staEmission" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="车辆类型" :label-width="formLabelWidth">
                        <el-input v-model="form.vehicleType" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="8">
                    <el-form-item label="指导价格(万元)" :label-width="formLabelWidth">
                        <el-input v-model="form.guidePrice" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item
                            label="上市年份"
                            :label-width="formLabelWidth"
                            style="text-align: left">
                        <el-select v-model="form.ttmYear" filterable placeholder="---请选择---">
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in yearoption"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="上市月份" :label-width="formLabelWidth" style="text-align: left">
                        <el-select
                                v-model="form.ttmMonth"
                                filterable
                                placeholder="---请选择---">
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in monthoption"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value"
                            >
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item
                            label="生产年份"
                            :label-width="formLabelWidth"
                            style="text-align: left"
                    >
                        <el-select
                                v-model="form.productiveYear"
                                filterable
                                placeholder="---请选择---"
                        >
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in yearoption"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item
                            label="停产年份"
                            :label-width="formLabelWidth"
                            style="text-align: left">
                        <el-select
                                v-model="form.stopYear"
                                filterable
                                placeholder="---请选择---">
                            <el-option :value="null"> ---请选择--- </el-option>
                            <el-option
                                    v-for="item in yearoption"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="生产状态" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.productionStatus"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="国别" :label-width="formLabelWidth">
                        <el-input v-model="form.country" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="国产合资进口" :label-width="formLabelWidth">
                        <el-input v-model="form.domesticType" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="气缸容积" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.cylinderVolume"
                                autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="排量(升)" :label-width="formLabelWidth">
                        <el-input v-model="form.displacement" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="进气形式" :label-width="formLabelWidth">
                        <el-input v-model="form.intakeForm" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="燃料类型" :label-width="formLabelWidth">
                        <el-input v-model="form.fuelType" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="燃油标号" :label-width="formLabelWidth">
                        <el-input v-model="form.fuelModel" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="最大马力(ps)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.maxHorsepower"
                                autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="最大功率(kW)" :label-width="formLabelWidth">
                        <el-input v-model="form.maxPower" autocomplete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="最大功率转速(rpm)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.maxPowerSpeed"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item> </el-col
                ><el-col :span="8">
                <el-form-item label="最大扭矩(N·m)" :label-width="formLabelWidth">
                    <el-input v-model="form.maxTorque" autocomplete="off"></el-input>
                </el-form-item> </el-col
            ><el-col :span="8">
                <el-form-item label="最大扭矩转速(rpm)" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.maxTorqueSpeed"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col>
                <el-col :span="8">
                    <el-form-item label="气缸排列形式" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.cylinderArrange"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="气缸数(个)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.cylinderNumber"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="每缸气门数(个)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.throttleNumber"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="压缩比" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.compressionRatio"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="供油方式" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.fuelWay"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="工信部综合油耗(L)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.miitFuelConsumption"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="市区工况油耗" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.srbanFuelConsumption"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="市郊工况油耗" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.suburbFuelConsumption"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="加速时间(0-100)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.accelerationTime"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="最高车速" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.maxSpeed"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="变速器类型" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.transmissionType"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="变速器描述" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.transmissionDescription"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="档位数" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.gearNumber"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="前制动器类型" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.frontBrakeType"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="后制动器类型" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.rearBrakeType"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col><el-col :span="8">
                <el-form-item label="助力类型" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.powerType"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col><el-col :span="8">
                <el-form-item label="最小离地间隙" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.minGroundClearance"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col><el-col :span="8">
                <el-form-item label="最小转弯半径" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.minTurningRadius"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col><el-col :span="8">
                <el-form-item label="发动机位置" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.enginePosition"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col><el-col :span="8">
                <el-form-item label="驱动方式" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.drivingMethod"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col><el-col :span="8">
                <el-form-item label="长度(mm)" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.length"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col><el-col :span="8">
                <el-form-item label="宽度(mm)" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.width"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col>
                <el-col :span="8">
                    <el-form-item label="高度(mm)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.height"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="轴距(mm)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.wheelbase"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="前轮距(mm)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.frontTrack"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="后轮距(mm)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.rearWheel"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="整备质量(Kg)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.kerbMass"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col><el-col :span="8">
                <el-form-item label="最大载重质量(kg)" :label-width="formLabelWidth">
                    <el-input
                            v-model="form.maxLoadQuality"
                            autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-col>
                <el-col :span="8">
                    <el-form-item label="油箱容积(L)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.fuelTankCapacity"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="行李厢容积(L)" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.boot"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="车门数" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.doorNumber"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="座位数" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.seatNumber"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="前轮胎规格" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.fronttyrespecifications"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="后轮胎规格" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.frontTyreSpecifications"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="前轮毂规格" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.fwhSpecifications"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="后轮毂规格" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.rwhSpecification"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="轮毂材料" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.hubMaterial"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="备胎规格" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.spareTireSpecifications"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="电动天窗" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.electricSkylight"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="全景天窗" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.panoramicSunroof"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="氙气大灯" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.xenonHeadlamps"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="前雾灯" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.frontFogLamp"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="后雨刷" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.rearWiper"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="空调" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.airConditioner"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="自动空调" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.autoAirConditioning"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item label="发动机型号" :label-width="formLabelWidth">
                        <el-input
                                v-model="form.engineTypeNoengineTypeNo"
                                autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="8">
                    <el-form-item
                            label="是否启用"
                            :label-width="formLabelWidth"
                            style="text-align: left">
                        <el-select
                                v-model="form.usableFlag"
                                filterable
                        >              <el-option
                                v-for="item in usaoption"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                        >
                        </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        created () {
            this.getbaseCarType()
            this.getsbrand()
            this.getcaryear()
            this.getdisplacement()
            this.getCARCATEGORY()
            this.getFactory()
        },
        data () {
            return {
                // 表格数据
                tableDatacct: [],
                // 控制对话框是否可见
                dialogFormVisible: false,
                // 绑定新增或者修改对话框的数据的对象
                form: {},
                infiniteBox:{
                    visible: false,
                    loading: false,
                    noMore: false,
                    page: {
                        size: 20,
                        total: 0,
                        current: 1,
                        pages: 5,
                    }
                },
                // 是否是编辑，让input无法修改
                isedit: false,
                //选中的id
                selectId:[],
                // 下拉框表格数据
                ftableData: [],
                // 隐藏查询表格数据
                visible: false,
                // 查询品牌下拉框数据
                options: [],
                // 查询年款下拉框数据
                yoption: [],
                //厂家下拉框
                foption:[],
                // /查询排量下拉框数据
                doption: [],
                // 添加修改车系下拉框
                coption: [],
                // 年份下拉框的数据
                yearoption: [
                    {
                        value: '2021',
                        label: '2021'
                    },
                    {
                        value: '2020',
                        label: '2020'
                    },
                    {
                        value: '2019',
                        label: '2019'
                    },
                    {
                        value: '2018',
                        label: '2018'
                    },
                    {
                        value: '2017',
                        label: '2017'
                    },
                    {
                        value: '2016',
                        label: '2016'
                    },
                    {
                        value: '2015',
                        label: '2015'
                    },
                    {
                        value: '2014',
                        label: '2014'
                    },
                    {
                        value: '2013',
                        label: '2013'
                    },
                    {
                        value: '2012',
                        label: '2012'
                    },
                    {
                        value: '2011',
                        label: '2011'
                    },
                    {
                        value: '2010',
                        label: '2010'
                    },
                    {
                        value: '2009',
                        label: '2009'
                    },
                    {
                        value: '2008',
                        label: '2008'
                    },
                    {
                        value: '2007',
                        label: '2007'
                    },
                    {
                        value: '2006',
                        label: '2006'
                    },
                    {
                        value: '2005',
                        label: '2005'
                    },
                    {
                        value: '2004',
                        label: '2004'
                    },
                    {
                        value: '2003',
                        label: '2003'
                    },
                    {
                        value: '2002',
                        label: '2002'
                    },
                    {
                        value: '2001',
                        label: '2001'
                    },
                    {
                        value: '2000',
                        label: '2000'
                    },
                    {
                        value: '1999',
                        label: '1999'
                    },
                    {
                        value: '1998',
                        label: '1998'
                    },
                    {
                        value: '1997',
                        label: '1997'
                    },
                    {
                        value: '1996',
                        label: '1996'
                    },
                    {
                        value: '1995',
                        label: '1995'
                    },
                    {
                        value: '1994',
                        label: '1994'
                    },
                    {
                        value: '1993',
                        label: '1993'
                    },
                    {
                        value: '1992',
                        label: '1992'
                    }
                ],
                // 月份下拉框数据
                monthoption: [
                    {
                        value: '1',
                        label: '1'
                    },
                    {
                        value: '2',
                        label: '2'
                    },
                    {
                        value: '3',
                        label: '3'
                    },
                    {
                        value: '4',
                        label: '4'
                    },
                    {
                        value: '5',
                        label: '5'
                    },
                    {
                        value: '6',
                        label: '6'
                    },
                    {
                        value: '7',
                        label: '7'
                    },
                    {
                        value: '8',
                        label: '8'
                    },
                    {
                        value: '9',
                        label: '9'
                    },
                    {
                        value: '10',
                        label: '10'
                    },
                    {
                        value: '11',
                        label: '11'
                    },
                    {
                        value: '12',
                        label: '12'
                    }
                ],
                // 添加是否启用下拉框
                usaoption: [
                    {
                        value: '是',
                        label: '是'
                    },
                    {
                        value: '否',
                        label: '否'
                    }
                ],
                // 表格列宽
                formLabelWidth: '150px',
                // 当前页号
                current: 1,
                // 页大小
                limit: 10,
                // 总记录数
                total: 0,
                // 页大小列表
                pageSizeList: [10, 50, 100, 200, 300, 400],
                // 表格多选框
                multipleSelection: [],
                // 查询条件对象
                baseCarTypeQuery: {}
            }
        },

        methods: {
            addcchange(){},
            blurInput(){},
            // 添加窗口打开
            add () {
                this.form = {}
                this.dialogFormVisible = true
            },
            // 清空
            refss(){
                this.baseCarTypeQuery = {}
            },
            // 获取所有车车型信息
            async getbaseCarType () {
                const response = await this.$axios.post(`/serviceBasedata/cartype/queryPage/${this.current}/${this.limit}`,
                    this.baseCarTypeQuery)
                console.log(response.data)
                // 初始化表格数据
                this.tableDatacct = response.data.rows
                // 初始化总记录数
                this.total = response.data.total
            },
            // 确认按钮点击事件
            async save(){
                alert(this.form.carTypeUuid)
                // 添加或者修改操作
                const response = await this.$axios.post(`/serviceBasedata/cartype/${this.form.carTypeUuid == null ? 'addBaseCarType' : 'updateBaseCarType'}`, this.form)
                if (response.code == 20000) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    this.dialogFormVisible = false
                    // 刷新数据表格
                    this.getbaseCarType()
                }
            },
            // 编辑数据回显
            async update(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.multipleSelection.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选中一条数据'
                    })
                    return
                }
                this.dialogFormVisible=true
                // 获取要修改的门票编号
                console.log(this.selectId)
                const response = await this.$axios.get(`/serviceBasedata/cartype/queryById1/${this.selectId}`)
                this.form = response.data.baseCarType
            },

            //删除
            handleDelete() {
                if (this.multipleSelection.length == 0) {
                    this.$message({
                        type: 'info',
                        message: '至少选择一条数据'
                    })
                    return
                }
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    for (var i = 0; i < this.selectId.length; i++) {
                        const _this = this
                        _this.$axios.delete(`/serviceBasedata/cartype/${this.selectId[i]}`).then(result => {
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getbaseCarType()
                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //初始化厂家下拉框数据
            async getFactory () {
                const response = await this.$axios.get('/serviceBasedata/cartype/queryFactory')
                // 初始化品牌下拉框数据
                this.foption = response.data.list
            },
            //初始化品牌下拉框数据
            async getsbrand () {
                const response = await this.$axios.get('/serviceBasedata/cartype/queryBname')
                // 初始化品牌下拉框数据
                this.options = response.data.list
            },
            //初始化车系下拉框数据
            async getCARCATEGORY () {
                const response = await this.$axios.get('/serviceBasedata/cartype/queryCategory')
                // 初始化品牌下拉框数据
                this.coption = response.data.list
            },
          // 初始化年款下拉框
            async getcaryear () {
                const response = await this.$axios.get('/serviceBasedata/cartype/queryCarYear')
                // 初始化年款下拉框数据
                this.yoption = response.data.list
                console.log(this.yoption)
            },
            // 初始化排量下拉框
            async getdisplacement () {
                const response = await this.$axios.get('/serviceBasedata/cartype/queryDisplacement')
                // 初始化排量下拉框数据
                this.doption = response.data.list
                console.log(this.doption)
            },

            // 页大小改变事件
            async handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getbaseCarType()
            },
            // 页号改变事件
            async handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                this.getbaseCarType()
            },
            //获取选中id
            handleSelectionChange(val) {
                this.multipleSelection=val;
                //获取选中id
                this.selectId=[]
                for(var i=0;i<val.length;i++){
                    this.selectId.push(val[i].carTypeUuid);
                    console.log(this.selectId)
                }
            },
        }

    }
</script>
