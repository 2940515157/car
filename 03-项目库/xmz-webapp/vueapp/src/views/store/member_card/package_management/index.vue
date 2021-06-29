<!-- - - - - - - - - - - - -->
<!--        门店套餐管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <span hidden>{{version}}</span>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">门店套餐管理</span>

            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container>
                    <el-header style="height:40px;">

                        <!-- 查询输入框 -->
                        <el-form :inline="true" :model="bpQuery"
                                 class="demo-form-inline" size="mini" style="margin-top: 15px;">
                            <el-form-item label="套餐名称">
                                <el-input v-model="bpQuery.apName" style="width:130px"></el-input>
                            </el-form-item>

                            <el-form-item label="门店套餐状态">
                                <el-select v-model="bpQuery.brStatus" placeholder="请选择" size="mini"
                                           style="width:130px">
                                    <el-option
                                            v-for="item in brStatus"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>

                            <el-form-item label="套餐类型">
                                <el-select v-model="bpQuery.apType" placeholder="请选择" size="mini"
                                           style="width:130px">
                                    <el-option
                                            v-for="item in apType"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>


                            <el-form-item>
                                <el-button type="primary" @click="search">查询</el-button>
                            </el-form-item>

                            <br>
                            <el-form-item>
                                <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true,ruleForm={}">增加</el-button>
<!--                                <el-button  icon="el-icon-delete" size="mini">编辑</el-button>-->
<!--                                <el-button  icon="el-icon-delete" size="mini">删除</el-button>-->
                                <el-button icon="el-icon-refresh-left" size="mini" @click="shangjia">上架</el-button>
                                <el-button icon="el-icon-refresh-left" size="mini" @click="xiajia">下架</el-button>
                            </el-form-item>
                        </el-form>

                        <!-- 表格数据 -->
                        <el-table
                                height="400px"
                                :data="tableData"
                                border
                                stripe
                                ref="tableData"
                                @selection-change="tableDataSelectionChange">
                            <el-table-column
                                    type="selection"
                                    width="55">
                            </el-table-column>
                            <el-table-column prop="apName" label="套餐名称"></el-table-column>
                            <el-table-column prop="apType" label="套餐类型"></el-table-column>
                            <el-table-column prop="origionType" label="发起类型"></el-table-column>
                            <el-table-column prop="brStatus" label="门店套餐状态"></el-table-column>
                            <el-table-column prop="status" label="总部套餐状态"></el-table-column>
                            <el-table-column prop="putOnDate" label="上架时间"></el-table-column>
                            <el-table-column prop="pullOffDate" label="下架时间"></el-table-column>
                            <el-table-column prop="totalPrice" label="套餐售价"></el-table-column>
                            <el-table-column prop="saleRate" label="销售增值比率"></el-table-column>
                            <el-table-column prop="canOwner" label="专属化"></el-table-column>
                            <el-table-column prop="validMonth" label="使用期限"></el-table-column>
                            <el-table-column prop="startDate" label="有效期（开始）"></el-table-column>
                            <el-table-column prop="endDate" label="有效期（结束）"></el-table-column>
                            <el-table-column prop="bizType" label="业务分类"></el-table-column>
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
            <el-dialog title="增加套餐" :visible.sync="dialogFormVisible" width="1000px" @close="resetForm('ruleForm')">
                <el-container style="height: 550px; border: 1px solid #eee">
                    <!-- 上边区域 -->
                    <el-container style="margin-top: -16px">
                        <el-header style="height:30px;margin-top:-20px">

                        </el-header>

                        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" :inline="true" label-width="135px"
                                 class="demo-form-inline" size="mini" style="margin-top: 15px;">
                            <fieldset style="border:solid 1px  #C0C5CB">
                                <legend>基本信息</legend>
                            <el-form-item label="套餐名称" prop="apName">
                                <el-input v-model="ruleForm.apName" autocomplete="off" style="width:150px"></el-input>
                            </el-form-item>
                            <el-form-item label="服务类型" prop="serviceType">
                                <el-select v-model="ruleForm.serviceType" placeholder="请选择" size="mini" style="width:150px">
                                    <el-option label="机修" value="maintain"></el-option>
                                    <el-option label="油漆" value="paint"></el-option>
                                    <el-option label="美容" value="cosmetology"></el-option>
                                    <el-option label="综合" value="all"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="套餐类型" prop="apType">
                                <el-select v-model="ruleForm.apType" placeholder="请选择" size="mini" style="width:150px">
                                    <el-option label="服务套餐" value="service"></el-option>
                                    <el-option label="服务配件套餐" value="servAndParts"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="套餐售价" prop="totalPrice">
                                <el-input v-model="ruleForm.totalPrice" autocomplete="off" style="width:150px"></el-input>
                            </el-form-item>
                            <el-form-item label="折扣率(%)" prop="discountRate">
                                <el-input :value="ruleForm.discountRate" autocomplete="off" style="width:150px" readonly></el-input>
                            </el-form-item>
                            <el-form-item label="销售增值比率(%)" prop="saleRate">
                                <el-input v-model="ruleForm.saleRate" autocomplete="off" style="width:150px"></el-input>
                            </el-form-item>
                            <el-form-item label="是否自动专属" prop="canOwner">
                                <el-select v-model="ruleForm.canOwner" placeholder="请选择" size="mini" style="width:150px">
                                    <el-option label="是" value="Y"></el-option>
                                    <el-option label="否" value="N"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="业务分类" prop="bizType">
                                <el-select v-model="ruleForm.bizType" placeholder="请选择" size="mini" style="width:150px">
                                    <el-option label="机油卡" value="P001"></el-option>
                                    <el-option label="计次卡" value="P002"></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="开始时间" prop="startDate">
                                <el-date-picker
                                        style="width:150px"
                                        v-model="ruleForm.startDate"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="结束时间" prop="endDate">
                                <el-date-picker
                                        style="width:150px"
                                        v-model="ruleForm.endDate"
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="使用期限(月)" prop="validMonth">
                                <el-input v-model="ruleForm.validMonth" autocomplete="off" style="width:150px"></el-input>
                            </el-form-item>
                            <el-form-item label="备注">
                            <el-input
                                    type="textarea"
                                    placeholder="请输入内容"
                                    v-model="ruleForm.remark"
                                    maxlength="30"
                                    show-word-limit>
                            </el-input>
                            </el-form-item>
                            </fieldset>
                        </el-form>
                            <el-tabs type="border-card">
                                <el-tab-pane label="服务项目">
                                    <el-table
                                            style="width: 100%"
                                            height="200"
                                            :data="xzFWData"
                                            border
                                            stripe
                                            ref="tableData"
                                            @cell-dblclick="celledit">
                                        <el-table-column prop="catalogName" label="服务项目"></el-table-column>
                                        <el-table-column prop="servTypeName" label="服务类型"></el-table-column>
                                        <el-table-column label="次数">
                                            <template slot-scope="scope">
                                                <el-input type="text" size="mini"  v-model="scope.row.apdNumber" @input="nums2()"></el-input>
                                            </template>
                                        </el-table-column>
                                        <el-table-column prop="salePrice" label="单次套餐服务销售价格"></el-table-column>
                                        <el-table-column prop="stdPrice" label="标准服务单价"></el-table-column>
                                        <el-table-column prop="stlAgreementPrice" label="协议结算单价"></el-table-column>
                                        <el-table-column label="客户消费-SA增值折扣(%)">
                                            <template slot-scope="scope">
                                                <el-input type="text" size="mini"  v-model="scope.row.commissionRateSa"></el-input>
                                            </template>
                                        </el-table-column>

                                    </el-table>
                                </el-tab-pane>

                                <el-tab-pane label="配件">
                                    <el-table
                                            style="width: 100%"
                                            height="200"
                                            :data="fwData"
                                            border
                                            stripe
                                            @cell-dblclick="peijian">
                                        <el-table-column prop="bptNAME" label="配件目录"></el-table-column>
                                        <el-table-column prop="showName" label="商品名称">
                                        </el-table-column>
                                        <el-table-column label="总数量">
                                            <template slot-scope="scope">

                                                <el-input  size="mini" v-model="scope.row.appNumber" @input="nums"></el-input>
                                            </template>
                                        </el-table-column>
                                        <el-table-column prop="unit" label="单位">
                                            <template slot-scope="scope">
              <span v-if="scope.row.unit=='bottle'">
                瓶
              </span>
                                                <span v-else-if="scope.row.unit=='oneOfPair'">
                对
              </span>
                                                <span v-else-if="scope.row.unit=='piece'">
                片
              </span>
                                                <span v-else-if="scope.row.unit=='pair'">
                副
              </span>
                                                <span v-else-if="scope.row.unit=='branch'">
                只
              </span>
                                                <span v-else-if="scope.row.unit=='set'">
                组
              </span>
                                                <span v-else-if="scope.row.unit=='parts'">
                部
              </span>
                                            </template>
                                        </el-table-column>

                                        <el-table-column prop="stdPrice" label="单次销售价格"></el-table-column>
                                        <el-table-column label="单次标准报价">
                                            <template slot-scope="scope">

                                                <el-input  size="mini" v-model="scope.row.salePrice" @input="dcbzPrice"></el-input>
                                            </template>
                                        </el-table-column>
<!--                                         stlStdPrice-->
                                        <el-table-column prop="stlAgreementPrice" label="协议结算单价"></el-table-column>
                                        <el-table-column label="客户消费-SA增值折扣(%)">
                                            <template slot-scope="scope">
                                                <el-input type="text" size="mini"  v-model="scope.row.commissionRateSa"></el-input>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-tab-pane>
<!--                                <el-tab-pane label="优惠劵">-->
<!--                                    <el-table-->
<!--                                            style="width: 100%"-->
<!--                                            height="200"-->
<!--                                            :data="yhjData"-->
<!--                                            border-->
<!--                                            stripe-->
<!--                                            ref="tableData"-->
<!--                                            @cell-dblclick="youhuiquan">-->
<!--                                        <el-table-column prop="acName" label="卡券名称"></el-table-column>-->
<!--                                        <el-table-column prop="bizTypeName" label="业务分类"></el-table-column>-->
<!--                                        <el-table-column prop="acUseTypeName" label="卡券使用类型"></el-table-column>-->
<!--                                        <el-table-column prop="replacePrice" label="抵用总金额（元）"></el-table-column>-->
<!--                                        <el-table-column prop="salPrice" label="销售价格"></el-table-column>-->
<!--                                        <el-table-column prop="remainder" label="剩余数量"></el-table-column>-->
<!--                                        <el-table-column prop="priority" label="数量">-->
<!--                                            <template slot-scope="scope">-->
<!--                                                <el-input  size="mini" v-model="scope.row.nums3"></el-input>-->
<!--                                            </template>-->
<!--                                        </el-table-column>-->
<!--                                    </el-table>-->
<!--                                </el-tab-pane>-->
                            </el-tabs>

                    </el-container>
                </el-container>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="submitForm('ruleForm')" >确定</el-button>
                </div>
            </el-dialog>

            <!--点击服务项目表格时触发-->
            <el-dialog title="选择服务目录" :visible.sync="dialogFormVisible2" width="1000px">
                <el-container style="height: 500px; border: 1px solid #eee">
                <!--左边-->
                <el-aside width="200px"   style="background-color: rgb(238, 241, 246)">
                    <h3>服务目录</h3>
                    <!-- 树形控件-->
                    <el-tree
                            :data="fwmlData"
                            default-expand-all
                            node-key="id"
                            ref="tree"
                            highlight-current
                            style="height: 435px"
                            @node-click="clicknode">
                    </el-tree>
                </el-aside>
                    <el-container>
                        <!--头-->
                        <el-header height="50px">

                        </el-header>
                        <!--搜索与添加区域-->
                        <el-form :inline="true" :model="SerObjectQuery" class="demo-form-inline" label-width="110px" size="mini">


                            <el-form-item label="服务项目名称">
                                <el-input v-model="SerObjectQuery.catalogName"  placeholder="服务项目名称" ></el-input>
                            </el-form-item>

                            <el-form-item style="margin-left: 60px">
                                <el-button type="primary" icon="el-icon-search" @click="Serachc">查询</el-button>
                            </el-form-item>

                        </el-form>
                        <!--  主体内容-->
                        <el-main style="margin-top: -30px">
                            <el-table
                                    ref="multipleTable"
                                    :data="ServObjectData"
                                    tooltip-effect="dark"
                                    style="width: 100%"
                                    max-height="350px"
                                    @cell-dblclick="dbfwml"
                                    @selection-change="handleSelectionChange">
                                <el-table-column
                                        type="selection"
                                        width="55">
                                </el-table-column>


                                <el-table-column
                                        prop="topType"
                                        label="服务大类"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="servTypeName"
                                        label="服务类型"
                                        width="120">
                                </el-table-column>

                                <el-table-column
                                        prop="catalogName"
                                        label="服务项目名称"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="catalogNameShort"
                                        label="简称"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="stdManHour"
                                        label="标准工时"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="stdPrice"
                                        label="标准价格"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="stdManHour"
                                        label="标准面数"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="commRateSa"
                                        label="SA增殖率"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="commRateWorker"
                                        label="计师提成率"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="commRateWorker"
                                        label="计师提成率"
                                        width="90">
                                </el-table-column>
                                <el-table-column
                                        prop="saCanEditPrice"
                                        label="服务顾问是否可改价格"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="isSamePrice"
                                        label="是否不同车型价格一致"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="brCanEditName"
                                        label="门店是否可修改名称"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="isSamePrice"
                                        label="是否不同车型价格一致"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="isSamePrice"
                                        label="是否不同车型价格一致"
                                        width="120">
                                </el-table-column>

                                <el-table-column
                                        prop="usableFlag"
                                        label="是否启用"
                                        show-overflow-tooltip>
                                </el-table-column>
                            </el-table>
                            <!--分页区域-->
                            <el-pagination
                                    @size-change="handleSizeChange3"
                                    @current-change="handleCurrentChange3"
                                    :current-page="current3"
                                    :page-sizes="pageSizeList3"
                                    :page-size="limit3"
                                    layout="total, sizes, prev, pager, next, jumper"
                                    :total="total3">
                            </el-pagination>

                        </el-main>
                    </el-container>
                </el-container>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                    <el-button type="primary">确定</el-button>
                </div>
            </el-dialog>

            <!--点击配件表格时触发-->
            <el-dialog title="选择配件目录" :visible.sync="dialogFormVisible3" width="1100px">
                <el-container>
                <el-aside style="width: 300px;">
                    <b>配件目录</b>
                    <el-tree
                            style="background-color: #a8c5dd"
                            :data="treeData"
                            node-key="partTypeUuid"
                            :props="defaultProps"
                            ref="tree"
                            @node-click="carTypeFuTree">
                    </el-tree>
                </el-aside>
                    <el-container>
                        <el-main>
                            <el-table
                                    :data="peijianData"
                                    style="width: 100%"
                                    height="270px"
                                    @cell-dblclick="dbPeijjian">
                                <el-table-column
                                        prop="bptNAME"
                                        label="配件目录"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="showName"
                                        label="展示名称"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="partNo"
                                        label="小拇指编码">
                                </el-table-column>
                                <el-table-column
                                        prop="partName"
                                        label="商品名称"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        label="是否启用"
                                        width="180">
                                    <template slot-scope="scope">
                <span v-if="scope.row.usableFlag=='Y'">
                  是
                </span>
                                        <span v-else-if="scope.row.usableFlag=='N'">
                  否
                </span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        prop="brand"
                                        label="品牌">
                                </el-table-column>
                                <el-table-column
                                        prop="model"
                                        label="厂商型号"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="lyCode"
                                        label="厂商编号"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="unit"
                                        label="最小购买单位">
                                </el-table-column>
                                <el-table-column
                                        prop="spec"
                                        label="规格"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="stdPrice"
                                        label="总部标准价"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="stdPriceAddRate"
                                        label="总部批量加价率">
                                </el-table-column>
                                <el-table-column
                                        label="通用型号sku"
                                        width="180">
                                    <template slot-scope="scope">
                <span v-if="scope.row.matchType=='special'">
                  是
                </span>
                                        <span v-else-if="scope.row.matchType=='common'">
                  否
                </span>
                                    </template>
                                </el-table-column>
                                <el-table-column
                                        prop="hbBRCODE"
                                        label="门店编号"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        prop="hbBRNAME"
                                        label="门店名称">
                                </el-table-column>
                                <el-table-column
                                        prop="remark"
                                        label="备注"
                                        width="180">
                                </el-table-column>
                                <el-table-column
                                        label="车型匹配状态"
                                        width="180">
                                    <template slot-scope="scope">
                <span v-if="scope.row.matchType=='special'">
                  是
                </span>
                                        <span v-else-if="scope.row.matchType=='common'">
                  否
                </span>
                                    </template>
                                </el-table-column>
                            </el-table>
                            <!-- 分页-->
                            <el-pagination
                                    @size-change="handleSizeChange1"
                                    @current-change="handleCurrentChange1"
                                    :current-page="page1"
                                    :page-sizes="[10, 20, 50,100]"
                                    :page-size="row1"
                                    layout="total, sizes, prev, pager, next, jumper"
                                    :total="total1">
                            </el-pagination>
                        </el-main>
                    </el-container>
                    </el-container>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible3 = false">取 消</el-button>
                    <el-button type="primary">购买</el-button>
                </div>
            </el-dialog>

            <!--点击优惠券表格时触发-->
            <el-dialog title="选择优惠券" :visible.sync="dialogFormVisible4" width="1000px">

                <el-card class="k-card" style="height: 500px">
                    <el-table
                            :data="CouponData"
                            tooltip-effect="dark"
                            style="width: 100%"
                            @cell-dblclick="dbYouHuiQuan">
                        <el-table-column
                                prop="acName"
                                label="卡卷名称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="bizTypeName"
                                label="业务分类"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="acUseTypeName"
                                label="卡卷使用类型"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="statusName"
                                label="状态"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="totalPrice"
                                label="卡券售价(B币)"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="replacePrice"
                                label="抵用总金额(元)"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="totalCount"
                                label="发行总数"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="remainder"
                                label="剩余数量"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="salPrice"
                                label="销售价格"
                                width="80">
                        </el-table-column>
                        <el-table-column
                                prop="startDate"
                                label="有效期开始"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="endDate"
                                label="有效期结束"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="putOnDate"
                                label="上架时间"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="pullOffDate"
                                label="下架时间"
                                width="120">
                        </el-table-column>

                        <el-table-column
                                prop="createdBy"
                                label="创建人"
                                show-overflow-tooltip>
                        </el-table-column>
                    </el-table>
                    <!--分页区域-->
                    <el-pagination
                            @size-change="handleSizeChange4"
                            @current-change="handleCurrentChange44"
                            :current-page="current4"
                            :page-sizes="pageSizeList4"
                            :page-size="limit4"
                            layout="total, sizes, prev, pager, next, jumper"
                            :total="total4">
                    </el-pagination>

                </el-card>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible4 = false">取 消</el-button>
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
                version: 0,
                tableData: [],
                // 封装表单数据的对象
                ruleForm: {},
                bpQuery: {},
                dialogFormVisible: false,
                dialogFormVisible2:false,
                dialogFormVisible3:false,
                dialogFormVisible4:false,
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                apType: [{
                    value: '',
                    label: ''
                }, {
                    value: '服务套餐',
                    label: '服务套餐'
                }, {
                    value: '服务配件套餐',
                    label: '服务配件套餐'
                }],
                brStatus: [{
                    value: '',
                    label: ''
                }, {
                    value: '未上架',
                    label: '未上架'
                }, {
                    value: '上架',
                    label: '上架'
                }, {
                    value: '下架',
                    label: '下架'
                }, {
                    value: '待完善',
                    label: '待完善'
                }],
                //配件目录
                treeData: [],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                baseCarPartsSkuQuery: {},
                peijianData:[],
                row1: 10,
                page1: 1,
                total1: 0,
                peijianData2:[],
                zongtiao:[],
                fwData:[{nums:''}],
                //服务目录的树形菜单
                fwmlData:[],
                SerObjectQuery:{},
                //服务项目选中的id
                multipleSelection: [],
                muData:[],
                ServObjectData:[],
                // 当前页号
                current3: 1,
                // 分页列表
                pageSizeList3: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit3: 10,
                // 总记录数
                total3: 0,
                xzFWData:[{servCatalogUuid:''}],
                CouponData:[],
                // 当前页号
                current4: 1,
                // 分页列表
                pageSizeList4: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit4: 5,
                // 总记录数
                total4: 0,
                CouponQuery:{},
                yhjData:[{acUuid:'',nums3:''}],
                zprice:0,
                tcprice:0,
                rules: {
                    apName: [
                        { required: true, message: '请输入套餐名称', trigger: 'blur' },
                    ],
                    serviceType: [
                        { required: true, message: '请选择服务类型', trigger: 'change' }
                    ],
                    apType: [
                        { required: true, message: '请选套餐类型', trigger: 'change' }
                    ],
                    totalPrice: [
                        { required: true, message: '请输入套餐售价', trigger: 'blur' },
                    ],
                    saleRate: [
                        { required: true, message: '请输入销售增值比率', trigger: 'blur' },
                    ],
                    canOwner: [
                        { required: true, message: '请选择是否自动专属', trigger: 'change' },
                    ],
                    bizType: [
                        { required: true, message: '请选择业务分类', trigger: 'change' },
                    ],
                    startDate: [
                        { required: true, message: '请选择开始时间', trigger: 'blur' },
                    ],
                    endDate: [
                        { required: true, message: '请选择结束时间', trigger: 'blur' },
                    ],
                    validMonth: [
                        { required: true, message: '请输入使用期限', trigger: 'blur' },
                    ],

                },
                apdForm:{},
                tableDataSelection: [],
                //选中的套餐id
                apUuid:[],
                //选中的门店套餐id
                brPackageUuid:[],
                origionType:[]

            };
        },

        created() {
            this.getBpList()
            this.getTreeData()
            this.getPeiJianData()
            this.getLeftList()
            this.getCouponList()
        },
        methods: {
            async shangjia(){
                if(this.tableDataSelection.length==0||this.tableDataSelection.length>1){
                    this.$message({
                        type:'info',
                        message:'请选择一条数据'
                    })
                    return
                }
                console.log(this.origionType)
                if(this.origionType=="门店"){
                    await this.$axios.post(`/brVipcard/acct-package/apShangjia/${this.apUuid[0]}/${this.brPackageUuid[0]}`)
                    this.$message({
                        type:'success',
                        message:'成功'
                    })
                    this.getBpList()
                }
                if(this.origionType=="总部运营中心"){
                    await this.$axios.post(`/brVipcard/acct-package/zbapshangjia/${this.brPackageUuid[0]}`)
                    this.$message({
                        type:'success',
                        message:'成功'
                    })
                    this.getBpList()
                }
            },
            async xiajia(){
                if(this.tableDataSelection.length==0||this.tableDataSelection.length>1){
                    this.$message({
                        type:'info',
                        message:'请选择一条数据'
                    })
                    return
                }
                if(this.origionType=="门店"){
                        await this.$axios.post(`/brVipcard/acct-package/apxiajia/${this.apUuid[0]}/${this.brPackageUuid[0]}`)
                    this.$message({
                        type:'success',
                        message:'成功'
                    })
                    this.getBpList()
                }
                if(this.origionType=="总部运营中心"){
                    await this.$axios.post(`/brVipcard/acct-package/zbapxiajia/${this.brPackageUuid[0]}`)
                    this.$message({
                        type:'success',
                        message:'成功'
                    })
                    this.getBpList()
                }
            },
            tableDataSelectionChange(val){
                this.tableDataSelection=val;
                this.apUuid=[]
                this.brPackageUuid=[]
                this.origionType=[]
                for(var i=0;i<val.length;i++){
                    this.apUuid.push(val[i].apUuid)
                    this.brPackageUuid.push(val[i].brPackageUuid)
                    this.origionType.push(val[i].origionType)
                }
                console.log(this.origionType)
            },
            upVersion(){
                this.version = Math.random()
            },
            nums(){
                this.upVersion();
                this.tcprice=0;
                this.tcprice=this.zprice;

                for(var i=1;i<this.fwData.length;i++){
                    if(this.fwData[i].appNumber!=undefined&&this.fwData[i].salePrice!=undefined){
                        this.tcprice+=this.fwData[i].appNumber*this.fwData[i].salePrice
                    }
                }
                var num=this.ruleForm.totalPrice/this.tcprice
                this.ruleForm.discountRate=num.toFixed(3)

                for(var j=1;j<this.fwData.length;j++){
                    if(this.fwData[j].appNumber!=undefined&&this.fwData[j].salePrice!=undefined){
                        this.fwData[j].stdPrice=(this.fwData[j].salePrice*this.ruleForm.discountRate).toFixed(2)
                        this.fwData[j].stlAgreementPrice=(this.fwData[j].salePrice*this.ruleForm.discountRate).toFixed(2)
                    }

                }
            },
            dcbzPrice(){
                this.upVersion();
                this.tcprice=0;
                this.tcprice=this.zprice;
                for(var i=1;i<this.fwData.length;i++){
                    if(this.fwData[i].appNumber!=undefined&&this.fwData[i].salePrice!=undefined){
                        this.tcprice+=this.fwData[i].appNumber*this.fwData[i].salePrice
                    }
                }
                var num=this.ruleForm.totalPrice/this.tcprice
                this.ruleForm.discountRate=num.toFixed(3)

                for(var j=1;j<this.fwData.length;j++){
                    if(this.fwData[j].appNumber!=undefined&&this.fwData[j].dcbzPrice!=undefined){
                        this.fwData[j].stdPrice=(this.fwData[j].salePrice*this.ruleForm.discountRate).toFixed(2)
                        this.fwData[j].stlAgreementPrice=(this.fwData[j].salePrice*this.ruleForm.discountRate).toFixed(2)
                    }
                }
            },
            nums2(){
                this.zprice=0;
                this.upVersion();
                for(var i=1;i<this.xzFWData.length;i++){
                  if(this.xzFWData[i].apdNumber!=undefined){
                      this.zprice+=this.xzFWData[i].apdNumber*this.xzFWData[i].stdPrice
                  }
                }
                var num=this.ruleForm.totalPrice/this.zprice
                this.ruleForm.discountRate=num.toFixed(3)

                for(var j=1;j<this.xzFWData.length;j++){
                    this.xzFWData[j].salePrice=(this.xzFWData[j].stdPrice*this.ruleForm.discountRate).toFixed(2)
                    this.xzFWData[j].stlAgreementPrice=(this.xzFWData[j].stdPrice*this.ruleForm.discountRate).toFixed(2)

                }

            },
            submitForm(formName) {
                const _this=this;
                _this.ruleForm.creatorUuid=_this.$store.getters.userInfo.id
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if(this.ruleForm.apType=="service"&&this.fwData.length>1){
                            this.$message({
                                message: '警告哦，"您选的是服务套餐，请不要添加配件数据！"',
                                type: 'warning'
                            });
                            return
                        }
                        if(this.ruleForm.apType=="service"&&this.xzFWData.length==1){
                            this.$message({
                                message: '警告哦，"您选的是服务套餐，请添加服务后再提交！"',
                                type: 'warning'
                            });
                            return
                        }
                        if(this.ruleForm.apType=="servAndParts"&&this.xzFWData.length==1){
                            this.$message({
                                message: '警告哦，"您选的是服务配件套餐，请添加服务和配件后再提交！"',
                                type: 'warning'
                            });
                            return
                        }
                        _this.$axios.post(`/brVipcard/br-package/add/`,{
                            ruleForm: JSON.stringify(_this.ruleForm),
                            xzFWData: JSON.stringify(_this.xzFWData),
                            fwData:JSON.stringify(_this.fwData),
                        })
                        // 提示消息
                        _this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        _this.dialogFormVisible=false
                        _this.getBpList()
                    }else{
                        return false
                    }

                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            // 获取所有门店套餐的方法
            async getBpList () {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/br-package/queryPage/${this.current}/${this.limit}`, this.bpQuery)
                this.tableData = response.data.rows
                this.total = response.data.total

            },
            // 查询按钮的方法
            search () {
                this.getBpList()

            },
            //点击服务项目表格
            celledit(row, column, cell, event){
                console.log(row,'row');//当前行的所有键值内容
                console.log(column,'column');//当前列的信息
                console.log(cell,'cell');//单元格信息
                console.log(event,'event');//事件信息

               this.dialogFormVisible2=true
            },
            //点击配件表格
            peijian(){
                this.dialogFormVisible3=true
            },
            getTreeData(){
                const _this = this
                _this.$axios.post(`/serviceCarsparts/cartype/carTypeZiTree`).then(result=>{
                    _this.treeData=result.data.rows;
                })
            },
            getPeiJianData(){
                const _this = this
                _this.$axios.post(`/serviceCarsparts/base-car-parts-sku/queryCarPartsSku/
                ${_this.page1}/${_this.row1}`,_this.baseCarPartsSkuQuery).then(result=>{
                    _this.peijianData=result.data.rows;
                    console.log(result.data.rows)
                    this.total1 = result.data.total
                })
            },
            //tree点击事件
            carTypeFuTree(data,node){
                const _this = this
                if(node.childNodes.length>0){
                    _this.baseCarPartsSkuQuery.partType=''
                    _this.getPeiJianData()
                    return
                }
                _this.baseCarPartsSkuQuery.partType=data.partTypeUuid
                _this.getPeiJianData();
            },
            dbPeijjian(row){
                console.log(row)
                for(var i=1;i<this.fwData.length;i++){
                    if(this.fwData[i].showName==row.showName){
                        return
                    }else{
                        this.fwData[i].partTypeUuid=row.partType
                    }
                }
                this.fwData.push(row)
                this.dialogFormVisible3=false
            },
            youhuiquan(){
                this.dialogFormVisible4=true
            },
            //服务目录的树形菜单查询
            clicknode(data){
                const _this = this
                //获取点击菜单的id
                _this.SerObjectQuery.stUuid= data.stUuid;
                //查询右边的菜单
                this.getRightList(this.getRightList);
            },
            getLeftList(){
                const _this = this
                _this.$axios.get("/serviceServiceProduct/base-serv-type").then(result=>{
                    _this.fwmlData=result.data.rows;
                })
            },
            getRightList(){
                const _this = this
                _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/queryAllPage/${this.current3}/${this.limit3}`,_this.SerObjectQuery).then(result=>{
                    _this.ServObjectData=result.data.rows;
                    _this.total3=result.data.total;
                })
            },
            //获取选中的id
            handleSelectionChange(val){
                //先清空id数组
                this.multipleSelection = [];
                this.muData=[];
                //去重获取被选中的id值存入到数组中
                for (let selectedItem of val) {
                    this.multipleSelection.push(selectedItem.catalogUuid)
                    this.muData.push(selectedItem.servTypeName)
                }
            },
            Serachc(){
                this.getRightList();
            },
            dbfwml(row){
                console.log(row)
                const _this = this
                for(var i=1;i<_this.xzFWData.length;i++){
                    if(_this.xzFWData[i].catalogName==row.catalogName) {
                      return
                    }
                    // else{
                    //     _this.xzFWData[i].servCatalogUuid=row.catalogUuid
                    // }
                }

                _this.xzFWData.push(row)

                _this.dialogFormVisible2=false

            },
            //分页条件查询优惠券
            getCouponList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-coupon/queryBrACoup/${this.current4}/${this.limit4}`,_this.CouponQuery).then(result=>{
                    _this.CouponData=result.data.rows;
                    _this.total4=result.data.total;
                })
            },
            SerachCha(){
                this.getCouponList();
            },
            dbYouHuiQuan(row){
                const _this = this
                for(var i=0;i<_this.yhjData.length;i++){
                    if(_this.yhjData[i].acUuid==row.acUuid) {
                        return
                    }
                }
                _this.yhjData.push(row)
                _this.dialogFormVisible4=false
            },


            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getBpList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getBpList()
            },
            //每页条数变化
            handleSizeChange1(val) {
                this.row1 = val;
                this.getPeiJianData();
            },
            //页数变化
            handleCurrentChange1(val) {
                this.page1 = val;
                this.getPeiJianData();
            },
            // 页大小改变事件
            handleSizeChange3 (val) {
                this.limit3 = val
                this.getRightList();
            },
            // 页号改变事件
            handleCurrentChange3 (val) {
                this.current3 = val
                this.getRightList();
            },
            // 页大小改变事件
            handleSizeChange4 (val) {
                this.limit4 = val
                this.getCouponList();
            },
            // 页号改变事件
            handleCurrentChange44 (val) {
                this.current4 = val
                this.getCouponList();
            }


        },
    };
</script>

<style scoped>

</style>
