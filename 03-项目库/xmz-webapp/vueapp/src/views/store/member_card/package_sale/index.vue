<!-- - - - - - - - - - - - - -->
<!--        套餐销售      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">套餐销售</span>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header style="height:40px;">


                <!-- 查询输入框 -->
                <el-form :inline="true" :model="apcQuery"
                         class="demo-form-inline" size="mini" style="margin-top: 15px;">
                    <el-form-item label="客户名称">
                        <el-input v-model="apcQuery.custName" style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="客户电话">
                        <el-input v-model="apcQuery.custPhone"  style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="卡号">
                        <el-input v-model="apcQuery.cardNo"  style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="套餐名称">
                        <el-input v-model="apcQuery.apcName"  style="width:130px"></el-input>
                    </el-form-item>

                    <br>
                    <el-form-item label="状态">
                        <template>
                            <el-radio-group v-model="radio" @change="radios">
                                <el-radio :label="0">未付款</el-radio>
                                <el-radio :label="1">已付款</el-radio>
                                <el-radio :label="2">收款中</el-radio>
                                <el-radio :label="3">已结案</el-radio>
                            </el-radio-group>
                        </template>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="serach">查询</el-button>
                    </el-form-item>

                    <br>
                    <el-form-item>
                        <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true">套餐销售</el-button>
<!--                        <el-button  icon="el-icon-delete" size="mini">删除</el-button>-->
<!--                        <el-button icon="el-icon-refresh-left" size="mini"  @click="chexiaojiean">撤销结案</el-button>-->
<!--                        <el-button icon="el-icon-refresh-left" size="mini"  @click="taocanchexiao">套餐撤销</el-button>-->
                    </el-form-item>
                </el-form>

                <!-- 表格数据 -->
                <el-table
                        height="350px"
                        :data="apcData"
                        border
                        stripe
                        ref="tableData"
                        @row-click="singleElection">
                    <el-table-column label="" width="65">
                        <template slot-scope="scope">
                            <el-radio class="radio" v-model="templateSelection" :label="scope.$index">&nbsp;</el-radio>
                        </template>
                    </el-table-column>
                    <el-table-column prop="cardNo" label="卡号"></el-table-column>
                    <el-table-column prop="custName" label="客户名称"></el-table-column>
                    <el-table-column prop="custPhone" label="手机"></el-table-column>
                    <el-table-column prop="apcName" label="套餐名称"></el-table-column>
                    <el-table-column prop="apcStatus" label="已购买套餐的状态"></el-table-column>
<!--                    <el-table-column prop="compositeFlag" label="组合产品"></el-table-column>-->
                    <el-table-column prop="totalPrice" label="套餐售价"></el-table-column>
                    <el-table-column prop="validMonth" label="使用有效期"></el-table-column>
                    <el-table-column prop="useEndDate" label="使用截至时间"></el-table-column>
                    <el-table-column prop="brName" label="门店"></el-table-column>
                </el-table>

                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page="current"
                            :page-sizes="pageSizeList"
                            :page-size="limit"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total">
                    </el-pagination>
                </el-header>
            </el-container>
        </el-container>


        <!-- 套餐销售的对话框 -->
        <el-dialog title="增加套餐销售" :visible.sync="dialogFormVisible" width="1000px">
            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container style="margin-top: -16px">
                    <el-header style="height:30px;margin-top:-20px">

                    </el-header>

                    <el-form :inline="true" label-width="125px"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <fieldset style="border:solid 1px  #C0C5CB">
                            <legend>客户基本信息</legend>
                            <el-form-item label="手机号">
                                <el-popover
                                        placement="bottom"
                                        width="500"
                                        trigger="hover">
                                    <el-table :data="gridData" border height="250px" @cell-click="clickGrid">
                                        <el-table-column width="100" property="licensePlate" label="车牌"></el-table-column>
                                        <el-table-column width="100" property="custName" label="客户名称"></el-table-column>
                                        <el-table-column width="130" property="custPhone" label="客户电话"></el-table-column>
                                        <el-table-column width="50" property="custLevel" label="客户等级"></el-table-column>
                                        <el-table-column width="100" property="saname" label="专属SA"></el-table-column>
                                        <!--                                <el-table-column width="100" property="brName" label="专属门店"></el-table-column>-->
                                    </el-table>
                                    <el-input v-model="cciQuery.custPhone" slot="reference" placeholder="手机号"
                                              style="width:150px" @input="queryPhoone"></el-input>
                                </el-popover>
                            </el-form-item>
                            <el-form-item label="卡号">
                                <el-input v-model="form.cardNo" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户名称">
                                <el-input v-model="form.custName" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户等级">
                                <el-input v-model="form.custLevel" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="专属SA">
                                <el-input v-model="form.saname" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="专属门店">
                                <el-input v-model="form.brName" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>

                        <fieldset style="border:solid 1px  #C0C5CB;">
                            <legend>套餐销售基本信息</legend>
                            <el-form-item label="套餐名称">
                                <el-input v-model="form.apcName" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                                <el-button  icon="el-icon-folder-opened" @click="dialogFormVisible2 = true">选择套餐</el-button>
                            </el-form-item>
                            <el-form-item label="套餐售价">
                                <el-input v-model="form.totalPrice" autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                            <el-form-item label="套餐开始使用时间">
                            <el-date-picker
                                    style="width:150px"
                                    v-model="form.useStartDate"
                                    type="date"
                                    placeholder="套餐开始使用时间">
                            </el-date-picker>
                            </el-form-item>
                            <el-form-item label="套餐使用截至时间">
                            <el-date-picker
                                    style="width:150px"
                                    v-model="form.useEndDate"
                                    type="date"
                                    placeholder="套餐使用截至时间">
                            </el-date-picker>
                                <el-form-item label="使用有效期">
                                    <el-input v-model="form.validMonth" autocomplete="off" style="width:150px"></el-input>
                                </el-form-item>
                            </el-form-item>
                        </fieldset>
                        <el-tabs type="border-card">
                            <el-tab-pane label="客户已购买套餐服务明细">
                                <el-table
                                        style="width: 100%"
                                        height="200"
                                        :data="gmfwData"
                                        border
                                        stripe
                                        ref="tableData">
                                    <el-table-column prop="catalogName" label="服务项目"></el-table-column>
                                    <el-table-column prop="servTypeName" label="服务类型"></el-table-column>
                                    <el-table-column prop="apdNumber" label="次数"></el-table-column>
                                    <el-table-column prop="salePrice" label="单次套餐服务销售价格"></el-table-column>
                                    <el-table-column prop="stdPrice" label="单次服务标准价"></el-table-column>
                                    <el-table-column prop="stlStdPrice" label="标准服务门店结束价"></el-table-column>
                                    <el-table-column prop="stlAgreementPrice" label="单次协议服务门店结算价"></el-table-column>
                                </el-table>
                            </el-tab-pane>
                            <el-tab-pane label="客户已购买套餐配件明细">
                                <el-table
                                        style="width: 100%"
                                        height="200"
                                        :data="peijianData"
                                        border
                                        stripe>
                                    <el-table-column prop="showName" label="商品名称"></el-table-column>
                                    <el-table-column prop="name" label="配件目录"></el-table-column>
                                    <el-table-column prop="brand" label="品牌"></el-table-column>
                                    <el-table-column prop="model" label="厂商型号"></el-table-column>
                                    <el-table-column prop="appNumber" label="数量"></el-table-column>
                                    <el-table-column prop="stdPrice" label="单次成本价"></el-table-column>
                                    <el-table-column prop="commissionRateSa" label="SA增值比率"></el-table-column>
<!--                                    <el-table-column prop="stlStdPrice" label="标准门店结算价"></el-table-column>-->
                                </el-table>
                            </el-tab-pane>
<!--                            <el-tab-pane label="组合产品明细">-->
<!--                                <el-table-->
<!--                                        style="width: 100%"-->
<!--                                        height="250"-->
<!--                                        :data="tableData"-->
<!--                                        border-->
<!--                                        stripe-->
<!--                                        ref="tableData">-->
<!--                                    <el-table-column prop="id" label="类型"></el-table-column>-->
<!--                                    <el-table-column prop="title" label="名称"></el-table-column>-->
<!--                                    <el-table-column prop="priority" label="优惠劵购买数量"></el-table-column>-->
<!--                                </el-table>-->
<!--                            </el-tab-pane>-->
                        </el-tabs>
                    </el-form>
                </el-container>
            </el-container>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="goumai">购买</el-button>
            </div>
        </el-dialog>

        <!-- 选择套餐的对话框 -->
        <el-dialog title="选择套餐" :visible.sync="dialogFormVisible2" width="800px">
            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container>
                    <el-header style="height:40px;margin-top: -20px">
                        <el-dropdown>
                            <i class="el-icon-tickets" style="margin-right: 10px;"></i>
                        </el-dropdown>
                        <span style="font-size:15px">套餐产品</span>
                    </el-header>

                    <!-- 查询输入框 -->
                    <el-form :inline="true" :model="apQuery"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <el-form-item label="套餐名称">
                            <el-input v-model="apQuery.apName" placeholder="套餐名称" style="width:130px"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="queryAp">查询</el-button>
                        </el-form-item>
                    </el-form>

                    <!-- 表格数据 -->
                    <el-table
                            :data="apData"
                            border
                            stripe
                            @cell-dblclick="celledit1">
                        <el-table-column prop="apName" label="套餐名称"></el-table-column>
                        <el-table-column prop="totalPrice" label="套餐售价"></el-table-column>
                        <el-table-column prop="compositeFlag" label="组合产品">
                            <template slot-scope="scope">
                                <span v-if="scope.row.compositeFlag=='Y'">是</span>
                                <span v-if="scope.row.compositeFlag=='N'">否</span>
                            </template>
                        </el-table-column>
                    </el-table>

                </el-container>
            </el-container>
        </el-dialog>

        <!-- 套餐标签的对话框 -->
        <el-dialog title="套餐标签" :visible.sync="dialogFormVisible3">
            <!-- 左边区域 -->
            <el-container style="height: 400px; border: 1px solid #eee">
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                    <h4>套餐标签</h4>
<!--                    <el-tree-->
<!--                            class="filter-tree"-->
<!--                            :data="data2"-->
<!--                            :props="defaultProps"-->
<!--                            default-expand-all-->
<!--                            ref="tree">-->
<!--                    </el-tree>-->
                </el-aside>

                <!-- 上边区域 -->
                <el-container >
                    <el-header style="height:56px">
                        <el-dropdown>
                            <i class="el-icon-tickets" style="margin-right: 10px;"></i>
                        </el-dropdown>
                        <span style="font-size:15px">套餐标签</span>
                    </el-header>

                    <!-- 查询输入框 -->
                    <el-form :inline="true" class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <el-form-item label="套餐标签" >
                            <el-input placeholder="套餐标签"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary">查询</el-button>
                        </el-form-item>
                    </el-form>

                    <div style="height:400px; overflow:auto">
                        <!-- 表格数据 -->
                        <el-table
                                ref="multipleTable"
                                :data="tableData"
                                tooltip-effect="dark"
                                style="width: 100%">
                            <el-table-column
                                    type="selection"
                                    width="55">
                            </el-table-column>
                            <el-table-column
                                    prop="name"
                                    label="标签"
                                    width="120">
                            </el-table-column>

                        </el-table>
                    </div>

                    <!-- 分页 -->
<!--                    <el-pagination-->
<!--                            @size-change="handleSizeChange"-->
<!--                            @current-change="handleCurrentChange"-->
<!--                            :current-page="currentPage4"-->
<!--                            :page-sizes="[100, 200, 300, 400]"-->
<!--                            :page-size="100"-->
<!--                            layout="total, sizes, prev, pager, next, jumper"-->
<!--                            :total="400">-->
<!--                    </el-pagination>-->
                </el-container>
            </el-container>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible3 = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 购买套餐销售的对话框 -->
        <el-dialog :visible.sync="dialogFormVisible4" width="1000px">
            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container style="margin-top: -16px">
                    <el-header style="height:30px;margin-top:-20px">
                        <div style=";margin-top:-12px">
                            <i class="el-icon-tickets" style="margin-right: 10px"></i>
                            <span style="font-size:15px">套餐销售</span>
                        </div>
                    </el-header>

                    <el-form :inline="true" label-width="125px"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <fieldset style="border:solid 1px  #C0C5CB">
                            <legend>套餐销售的基本信息</legend>
                            <el-form-item label="套餐名称">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                            <el-form-item label="套餐售价">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="使用有效期">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="套餐使用开始时间">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="使用截至时间">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="销售SA">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="销售时间">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>

                        <fieldset style="border:solid 1px  #C0C5CB;">
                            <legend>客户基本信息</legend>
                            <el-form-item label="手机">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户名称">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="会员卡号">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="卡余额">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户等级">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="专属SA">
                                <el-input autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="专属门店">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>
                        <el-tabs type="border-card">
                            <el-tab-pane label="客户已购买套餐服务明细">
                                <el-table
                                        style="width: 100%"
                                        height="150"
                                        :data="tableData"
                                        border
                                        stripe
                                        ref="tableData">
                                    <el-table-column prop="id" label="编号"></el-table-column>
                                    <el-table-column prop="title" label="服务类型"></el-table-column>
                                    <el-table-column prop="priority" label="次数"></el-table-column>
                                    <el-table-column prop="state" label="单次套餐服务销售价格"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次套餐成本价"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次服务标准报价"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="标准服务门店结算"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次协议服务门店结算价"></el-table-column>
                                </el-table>
                            </el-tab-pane>
                            <el-tab-pane label="客户已购买套餐配件明细">
                                <el-table
                                        style="width: 100%"
                                        height="150"
                                        :data="tableData"
                                        border
                                        stripe
                                        ref="tableData">
                                    <el-table-column prop="id" label="编号"></el-table-column>
                                    <el-table-column prop="title" label="商品名称"></el-table-column>
                                    <el-table-column prop="priority" label="配件目录"></el-table-column>
                                    <el-table-column prop="state" label="厂商编号"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="品牌"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="厂商型号"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="数量"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次数量"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次成本价"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="SA增值比率"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="技师提出比率"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="标准门店结算价"></el-table-column>
                                </el-table>
                            </el-tab-pane>
                            <el-tab-pane label="收款信息">
                                <el-table
                                        style="width: 100%"
                                        height="150"
                                        :data="tableData"
                                        border
                                        stripe
                                        ref="tableData">
                                    <el-table-column prop="id" label="编号"></el-table-column>
                                    <el-table-column prop="title" label="收款业务类型"></el-table-column>
                                    <el-table-column prop="priority" label="客户电话"></el-table-column>
                                    <el-table-column prop="state" label="客户姓名"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="车牌号"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="去零金额"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="应收金额"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="实收金额"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="状态"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="收款人"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="收款时间"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="车型"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="可开票金额"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="是否已开票"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="业务单据号"></el-table-column>
                                </el-table>
                            </el-tab-pane>
                        </el-tabs>
                    </el-form>

                </el-container>
            </el-container>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible5 = true">购买</el-button>
            </div>
        </el-dialog>

        <!-- 套餐收款的对话框 -->
        <el-dialog :visible.sync="dialogFormVisible5" width="800px">
            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container style="margin-top: -16px">
                    <el-header style="height:30px;margin-top:-20px">
                        <div style=";margin-top:-12px">
                            <i class="el-icon-tickets" style="margin-right: 10px"></i>
                            <span style="font-size:15px">编辑套餐销售</span>
                        </div>
                    </el-header>

                    <el-form :inline="true" label-width="100px"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <fieldset style="border:solid 1px  #C0C5CB">
                            <legend>基本信息</legend>
                            <el-form-item label="客户姓名">
                                <el-input v-model="form.custName" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户电话">
                                <el-input v-model="form.custPhone" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>


                            <el-form-item label="总金额">
                                <el-input v-model="form.totalPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="应收金额">
                                <el-input v-model="form.totalPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="待收金额">
                                <el-input v-model="form.totalPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>

                        <fieldset style="border:solid 1px  #C0C5CB;width:250px">
                            <legend>录入收款金额</legend>
                            <div style="margin-left:-20px">
                                <el-form-item label="支付金额">
                                    <el-input v-model="form.wxzf" autocomplete="off" placeholder="微信支付金额" style="width:120px"></el-input>
                                </el-form-item>
                                <br/>
                            </div>
                        </fieldset>

                        <el-container style="margin-left:310px;margin-top:-140px">
                            <el-main>
                                <div style="height:350px; overflow:auto;border:solid 1px  #C0C5CB">

                                    <el-table :data="tableData">
                                        <el-table-column width="100" property="date" label="操作"></el-table-column>
                                        <el-table-column width="100" property="name" label="应收金额"></el-table-column>
                                        <el-table-column width="100" property="address" label="实收金额"></el-table-column>
                                        <el-table-column width="100" property="name" label="付款方式"></el-table-column>
                                        <el-table-column width="100" property="address" label="付款名称"></el-table-column>
                                        <el-table-column width="100" property="address" label="抵扣金额"></el-table-column>
                                        <el-table-column width="100" property="name" label="状态"></el-table-column>
                                        <el-table-column width="100" property="address" label="交易单号"></el-table-column>

                                    </el-table>

                                </div>
                            </el-main>
                        </el-container>
                    </el-form>
                </el-container>
            </el-container>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible5 = false">取 消</el-button>
                <el-button type="primary" @click="skSubmit">确 定</el-button>
            </div>
        </el-dialog>

        <!-- 套餐销售撤销的对话框 -->
        <el-dialog :visible.sync="dialogFormVisible6" width="1000px">
            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container style="margin-top: -16px">
                    <el-header style="height:30px;margin-top:-20px">
                        <div style=";margin-top:-12px">
                            <i class="el-icon-tickets" style="margin-right: 10px"></i>
                            <span style="font-size:15px">套餐销售收款撤销</span>
                        </div>
                    </el-header>

                    <el-form :inline="true" label-width="125px"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <fieldset style="border:solid 1px  #C0C5CB">
                            <legend>套餐销售基本信息</legend>

                            <el-form-item label="套餐名称">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="套餐售价">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="使用有效期(月)">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>

                        <fieldset style="border:solid 1px  #C0C5CB;">
                            <legend>客户基本信息</legend>
                            <el-form-item label="手机">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户名称">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户编号">
                                <el-input autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="性别">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="证件号">
                                <el-input  autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="微信号">
                                <el-input autocomplete="off" style="width:150px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>
                        <el-tabs type="border-card">
                            <el-tab-pane label="客户已购买套餐服务明细">
                                <el-table
                                        style="width: 100%"
                                        height="250"
                                        :data="tableData"
                                        border
                                        stripe
                                        ref="tableData">
                                    <el-table-column prop="id" label="编号"></el-table-column>
                                    <el-table-column prop="title" label="服务类型"></el-table-column>
                                    <el-table-column prop="priority" label="次数"></el-table-column>
                                    <el-table-column prop="state" label="单次套餐服务销售价格"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次套餐成本价"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次服务标准报价"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="标准服务门店结算"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次协议服务门店结算价"></el-table-column>
                                </el-table>
                            </el-tab-pane>
                            <el-tab-pane label="客户已购买套餐配件明细">
                                <el-table
                                        style="width: 100%"
                                        height="250"
                                        :data="tableData"
                                        border
                                        stripe
                                        ref="tableData">
                                    <el-table-column prop="id" label="编号"></el-table-column>
                                    <el-table-column prop="title" label="商品名称"></el-table-column>
                                    <el-table-column prop="priority" label="配件目录"></el-table-column>
                                    <el-table-column prop="state" label="厂商编号"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="品牌"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="厂商型号"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="数量"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次数量"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="单次成本价"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="SA增值比率"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="技师提出比率"></el-table-column>
                                    <el-table-column prop="dealingPeople" label="标准门店结算价"></el-table-column>
                                </el-table>
                            </el-tab-pane>
                        </el-tabs>
                    </el-form>

                </el-container>
            </el-container>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible6 = false">取 消</el-button>
                <el-button type="primary">购买</el-button>
            </div>
        </el-dialog>




        </el-card>
    </div>
</template>

<script>
    export default {

        data() {
            return {
                //客户与车辆查询条件的对象
                cciQuery: {},
                //客户与车辆的查询列表
                gridData: [],
                // 封装表单数据的对象
                form: {},
                apQuery:{},
                apData:[],
                gmfwData:[],
                apcQuery:{},
                apcData:[],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                skform:{},
                radio:'',
                peijianData:[],

                data2: [],
                tableData: [],
                mode: "transfer", // transfer addressList
                toData:[],
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dialogFormVisible3:false,
                dialogFormVisible4:false,
                dialogFormVisible5:false,
                dialogFormVisible6:false,
                templateRadio:0,
                templateSelection: '',
                value: '',
                input:'',
                checkList: [],
            };
        },
        created() {
            this.getCciList()
            this.getApList()
            this.getApcList()
        },
        methods: {
            async skSubmit(){
                const _this = this
                if(this.form.wxzf!=this.form.totalPrice){
                    this.$message({
                        message: '警告哦，请输入正确的金额',
                        type: 'warning'
                    });
                }else{
                    const response = await _this.$axios.post('/brVipcard/acct-package-customer/queryDate',_this.apcQuery)
                    console.log(response.data)
                    this.$message({
                        message: '收款成功！',
                        type: 'warning'
                    });
                    _this.getApcList()
                    _this.dialogFormVisible5=false;
                }
            },
            async goumai(){
                const _this = this
                if(_this.form.cardNo==null){
                    this.$message({
                        message: '该客户还未开卡，请先开卡',
                        type: 'warning'
                    });
                    return
                }
                _this.dialogFormVisible=false
                _this.dialogFormVisible5=true
                await _this.$axios.post(`/brVipcard/acct-package-customer/add/`,{
                    form: JSON.stringify(_this.form)
                })
                _this.getApcList()


            },
            getApcList(){
                const _this = this
                _this.$axios.post(`/brVipcard/acct-package-customer/queryPage/${_this.current}/${_this.limit}`,_this.apcQuery).then(result=>{
                    _this.apcData=result.data.rows;
                    this.total = result.data.total
                })
            },
            serach(){
                this.getApcList()
            },
            radios(value){
                if(value==0){
                    this.apcQuery.apcStatus="未付款"
                }
                if(value==1){
                    this.apcQuery.apcStatus="已付款"
                }
                if(value==2){
                    this.apcQuery.apcStatus="收款中"
                }
                if(value==3){
                    this.apcQuery.apcStatus="已结案"
                }
                this.getApcList();
            },
            // 获取客户与车辆列表的方法
            async getCciList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/cust-cust-info/queryPhone/`, this.cciQuery)
                this.gridData = response.data.list
            },
            //输入框改变事件
            queryPhoone() {
                this.getCciList()
            },
            async clickGrid(row) {
                console.log(row)
                this.cciQuery.custPhone=row.custPhone
                this.form=row
                this.form.customerUuid=row.custUuid
            },
            // 门店可用的套餐
            async getApList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/br-package/listAp/`, this.apQuery)
                this.apData = response.data.list
            },
            queryAp(){
                this.getApList()
            },
            async celledit1(row){
                this.dialogFormVisible2=false
                this.form.totalPrice=row.totalPrice
                this.form.apcName=row.apName
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-package-detail/queryApUuid/${row.apUuid}`)
                this.gmfwData = response.data.list


                const response2 = await _this.$axios.post(`/brVipcard/acct-package-parts-detail/list/${row.apUuid}`)
                this.peijianData = response2.data.list
                console.log(response2.data.list)

                this.form.useStartDate='2021-06-16'
                this.form.useEndDate='2022-06-16'
                this.form.validMonth='12'
                this.form.acctPackageUuid=row.apUuid

            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            singleElection (row) {
                this.templateSelection = this.tableData.indexOf(row);
                this.templateRadio = row.id;
            },
            taocanchexiao(){
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                }else{
                    this.dialogFormVisible6=true
                }
            },
            chexiaojiean(){
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                }
            },
            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getApcList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getApcList()
            },
            //每页条数变化

        },


    };
</script>

<style scoped>

</style>
