<!-- - - - - - - - - - - -->
<!--        会员卡管理        -->
<!-- - - - - - - - - - - -->


<template>

    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">会员卡管理</span>

            <el-container style="height: 550px; border: 1px solid #eee">

                <!-- 上边区域 -->
                <el-container>
                    <el-header style="height:40px">
                        <!-- 查询输入框 -->
                        <el-form :inline="true" :model="accQuery" label-width="110px"
                                 class="demo-form-inline" size="mini" style="margin-top: 15px;">
                            <div style="margin-left:-50px">
                                <el-form-item label="车主名称">
                                    <el-input v-model="accQuery.custName" placeholder="车主名称"
                                              style="width:130px"></el-input>
                                </el-form-item>
                                <el-form-item label="车主电话">
                                    <el-input v-model="accQuery.custPhone" placeholder="车主电话"
                                              style="width:130px"></el-input>
                                </el-form-item>
                                <el-form-item label="车牌号">
                                    <el-input v-model="accQuery.licensePlate" placeholder="车牌号"
                                              style="width:130px"></el-input>
                                </el-form-item>
                                <br>
                                <el-form-item label="卡号">
                                    <el-input v-model="accQuery.cardNo" placeholder="卡号" style="width:130px"></el-input>
                                </el-form-item>

                                <el-form-item label="是否导入">
                                    <el-select v-model="accQuery.impFlag" placeholder="请选择" size="mini"
                                               style="width:130px">
                                        <el-option
                                                v-for="item in daoru"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="search">查询</el-button>
                                </el-form-item>
                            </div>
                            <el-form-item>
                                <el-button icon="el-icon-circle-plus-outline" size="mini"
                                           @click="dialogFormVisible = true">开卡
                                </el-button>
<!--                                <el-button icon="el-icon-circle-plus-outline" size="mini"-->
<!--                                           @click="dialogFormVisible5 = true">会员卡激活-->
<!--                                </el-button>-->
<!--                                <el-button icon="el-icon-circle-plus-outline" size="mini"-->
<!--                                           @click="dialogFormVisible6 = true">充值-->
<!--                                </el-button>-->
<!--                                <el-button icon="el-icon-edit" size="mini" @click="dongjie">冻结</el-button>-->
<!--                                <el-button icon="el-icon-edit" size="mini" @click="jiedong">解冻</el-button>-->
<!--                                <el-button icon="el-icon-edit" size="mini" @click="buka">补卡</el-button>-->
<!--                                <el-button icon="el-icon-error" size="mini" @click="guashi">挂失</el-button>-->
<!--                                <el-button icon="el-icon-error" size="mini" @click="quxiaoguashi">取消挂失</el-button>-->
<!--                                <el-button icon="el-icon-refresh-left" size="mini" @click="chexiao">撤销</el-button>-->
                            </el-form-item>
                        </el-form>

                        <!-- 表格数据 -->

                        <el-table
                                height="350px"
                                :data="acctableData"
                                border
                                stripe
                                ref="tableData"
                                @row-click="singleElection">
                            <el-table-column label="" width="65">
                                <template slot-scope="scope">
                                    <el-radio class="radio" v-model="templateSelection" :label="scope.$index">&nbsp;
                                    </el-radio>
                                </template>
                            </el-table-column>
                            <el-table-column prop="cardNo" label="卡号" width="150px"></el-table-column>
                            <el-table-column prop="status" label="卡状态"></el-table-column>
                            <el-table-column prop="balance" label="卡余额"></el-table-column>
                            <el-table-column prop="discountRate" label="折扣率"></el-table-column>
                            <el-table-column prop="custName" label="车主名称"></el-table-column>
                            <el-table-column prop="custPhone" label="车主电话" width="150px"></el-table-column>
                            <el-table-column prop="name" label="开卡人"></el-table-column>
                            <el-table-column prop="createdDate" label="开卡日期" width="160px"></el-table-column>
                            <el-table-column prop="saname" label="专属SA"></el-table-column>
                            <el-table-column prop="cardType" label="类型"></el-table-column>
                            <el-table-column prop="impFlag" label="是否导入">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.impFlag=='Y'">是</span>
                                    <span v-if="scope.row.impFlag=='N'">否</span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="brName" label="发卡门店" width="200px"></el-table-column>
                            <el-table-column prop="remark" label="备注"></el-table-column>
                        </el-table>

                        <!-- 分页 -->
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

            <!--开卡的对话框-->
            <el-dialog title="增加会员开卡" :visible.sync="dialogFormVisible" width="900px">

                <el-form :model="addCarform" size="mini" :inline="true">
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
                            <el-button icon="el-icon-circle-plus-outline"
                                       @click="dialogFormVisible2 = true"></el-button>
                        </el-form-item>


                        <el-form-item label="客户名称">
                            <el-input v-model="custName" autocomplete="off" style="width:120px"
                                      readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="客户等级">
                            <el-input v-model="custLevel" autocomplete="off" style="width:120px"
                                      readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="客户专属SA">
                            <el-input v-model="saname" autocomplete="off" style="width:120px"
                                      readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="客户专属门店">
                            <el-input v-model="brName" autocomplete="off" style="width:120px"
                                      readonly="readonly"></el-input>
                        </el-form-item>

                    </fieldset>

                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>卡信息</legend>
                        <el-form-item label="卡号">
                            <el-popover
                                    placement="bottom"
                                    width="700"
                                    trigger="hover">
                                <el-table :data="abcData" border height="250px" @cell-click="abcclick">
                                    <el-table-column width="150" property="cardNo" label="卡号"></el-table-column>
                                    <el-table-column width="300" property="brName" label="发卡门店"></el-table-column>
                                    <el-table-column width="100" property="cardType" label="类型"></el-table-column>
                                </el-table>
                                <!-- 分页 -->
                                <el-pagination
                                        @size-change="handleSizeChange2"
                                        @current-change="handleCurrentChange2"
                                        :current-page="current2"
                                        :page-sizes="pageSizeList2"
                                        :page-size="limit2"
                                        layout="total, sizes, prev, pager, next, jumper"
                                        :total="total2">
                                </el-pagination>
                                <el-input v-model="abcQuery.cardNo" slot="reference" placeholder="卡号"
                                          style="width:150px" @input="querykahao"></el-input>
                            </el-popover>
                        </el-form-item>

                        <el-form-item label="发卡门店">
                            <el-input v-model="brName2" autocomplete="off" style="width:120px"
                                      readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="类型">
                            <el-input v-model="cardType" autocomplete="off" style="width:120px"
                                      readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="备注">
                            <el-input
                                    type="textarea"
                                    v-model="mark"
                                    :autosize="{ minRows: 2, maxRows: 4}"
                                    placeholder="请输入内容">
                            </el-input>
                        </el-form-item>
                    </fieldset>


                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>车辆信息</legend>
                        <!-- 表格数据 -->
                        <el-button icon="el-icon-circle-plus-outline" size="mini"
                                   @click="dialogFormVisible4 = true">添加
                        </el-button>
                        <el-button icon="el-icon-delete" size="mini">删除</el-button>
                        <el-table
                                ref="multipleTableCar"
                                :data="carDate"
                                tooltip-effect="dark"
                                style="width: 100%">
                            <el-table-column
                                    type="selection"
                                    width="55">
                            </el-table-column>
                            <el-table-column
                                    prop="licensePlate"
                                    label="车牌号"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    prop="custName"
                                    label="车主"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    label="车辆信息">
                                <template slot-scope="{row}">
                                    <span>{{row.brand}} {{row.carModel}} {{row.saleName}} {{row.carYear==null?'':row.carYear+'款'}}</span>
                                </template>

                            </el-table-column>
                        </el-table>
                    </fieldset>


                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary"  @click="addVipCar">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 开卡中新增客户车辆信息的对话框 -->
            <el-dialog title="新增客户车辆信息" :visible.sync="dialogFormVisible2" width="900px">
                <el-form :inline="true" :model="form" size="mini">
                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>基本信息</legend>

                        <el-form-item label="客户名称">
                            <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="性别">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="手机">
                            <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="证件类型">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="证件号码">
                            <el-input autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>

                        <el-form-item label="座机">
                            <el-input autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>

                        <el-form-item label="生日">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="客户来源">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="介绍人">
                            <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                        </el-form-item>

                        <el-form-item label="Email">
                            <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                        </el-form-item>
                    </fieldset>


                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>车辆基本信息</legend>
                        <el-form-item label="车牌号">
                            <el-input autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>
                        <el-form-item label="VIN码">
                            <el-input autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>
                        <el-form-item label="发动机号">
                            <el-input autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>

                        <el-form-item label="颜色">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="车型">
                            <el-input autocomplete="off" style="width:300px" readonly="readonly"></el-input>
                            <el-button icon="el-icon-more" @click="dialogFormVisible3 = true"></el-button>
                        </el-form-item>

                        <el-form-item label="购车年份">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="购车月份">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="车辆性质">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="行程里程(KM)">
                            <el-input autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>

                        <el-form-item label="年检时间">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="年检到期时间">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="环保到期时间">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="商业险保险公司">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="交强险保险公司">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="交强线到期日期">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="商业保险到期日期">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                    </fieldset>
                </el-form>


                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 新增客户车辆中点击车型的对话框 -->
            <el-dialog title="选择车型" :visible.sync="dialogFormVisible3" width="900px">
                <el-container style="height: 550px; border: 1px solid #eee">
                    <!-- 上边区域 -->
                    <el-container style="margin-top: -16px">
                        <el-header style="height:30px;margin-top:-20px">

                        </el-header>

                        <!-- 查询输入框 -->
                        <el-form :inline="true" :model="accQuery"
                                 class="demo-form-inline" size="mini" style="margin-top: 15px;">
                            <div style="margin-left:-50px">
                                <el-form-item label="名称">
                                    <el-input placeholder="车牌号" style="width:130px"></el-input>
                                </el-form-item>

                                <el-form-item label="品牌">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:130px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                                <el-form-item label="车系">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:130px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="年款">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:130px">
                                        <el-option
                                                v-for="item in value"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="排量(升)">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:130px">
                                        <el-option
                                                v-for="item in value"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary">查询</el-button>
                                </el-form-item>
                            </div>

                        </el-form>

                        <!-- 表格数据 -->

                        <el-table
                                :data="tableData"
                                border
                                stripe
                                ref="tableData">
                            <el-table-column prop="id" label="ID"></el-table-column>
                            <el-table-column prop="title" label="标题"></el-table-column>
                            <el-table-column prop="priority" label="优先级"></el-table-column>
                            <el-table-column prop="state" label="状态"></el-table-column>
                            <el-table-column prop="dealingPeople" label="处理人"></el-table-column>
                        </el-table>

                        <!-- 分页 -->
                        <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="current"
                                :page-sizes="[100, 200, 300, 400]"
                                :page-size="100"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="400">
                        </el-pagination>
                    </el-container>
                </el-container>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible3 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>

            </el-dialog>

            <!-- 开卡中增加车辆对话框 -->
            <el-dialog title="选择车辆" :visible.sync="dialogFormVisible4">
                <el-container style="height: 550px; border: 1px solid #eee">
                    <!-- 上边区域 -->
                    <el-container style="margin-top: -16px">
                        <el-header style="height:30px;margin-top:-20px">
                            <div style=";margin-top:-12px">

                            </div>
                        </el-header>

                        <!-- 查询输入框 -->
                        <el-form :inline="true" :model="accQuery"
                                 class="demo-form-inline" size="mini" style="margin-top: 15px;">
                            <div style="margin-left:-50px">
                                <el-form-item label="车主">
                                    <el-input placeholder="车牌号" style="width:130px"></el-input>
                                </el-form-item>

                                <el-form-item label="名称">
                                    <el-input placeholder="车牌号" style="width:130px"></el-input>
                                </el-form-item>


                                <el-form-item>
                                    <el-button type="primary">查询</el-button>
                                </el-form-item>
                            </div>

                        </el-form>

                        <!-- 表格数据 -->

                        <el-table
                                :data="tableData"
                                border
                                stripe
                                ref="tableData">
                            <el-table-column type="selection">

                            </el-table-column>
                            <el-table-column prop="id" label="车主"></el-table-column>
                            <el-table-column prop="title" label="车牌号"></el-table-column>
                            <el-table-column prop="priority" label="VIN码"></el-table-column>
                            <el-table-column prop="state" label="车辆信息"></el-table-column>
                        </el-table>

                        <!-- 分页 -->
                        <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="current"
                                :page-sizes="[100, 200, 300, 400]"
                                :page-size="100"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="400">
                        </el-pagination>
                    </el-container>
                </el-container>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible4 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 会员卡激活的对话框 -->
            <el-dialog title="会员卡激活" :visible.sync="dialogFormVisible5">
                <el-form :model="form" size="mini" :inline="true">
                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>客户基本信息</legend>

                        <el-form-item label="手机">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="客户名称">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="性别">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:120px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="证件类型">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:120px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="证件号码">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="生日">
                            <div class="block">
                                <el-date-picker
                                        v-model="value1"
                                        type="date"
                                        placeholder="选择日期"
                                        style="width:125px">
                                </el-date-picker>
                            </div>
                        </el-form-item>

                        <el-form-item label="所在行业">
                            <el-select v-model="value" placeholder="请选择" size="mini" style="width:120px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="地址">
                            <el-input autocomplete="off" style="width:335px"></el-input>
                        </el-form-item>
                    </fieldset>


                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>新卡信息</legend>
                        <el-form-item label="卡号">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="卡余额">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                    </fieldset>


                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible5 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 会员卡充值的对话框 -->
            <el-dialog title="会员卡充值" :visible.sync="dialogFormVisible6" width="800px">
                <el-container style="height: 550px; border: 1px solid #eee">
                    <!-- 上边区域 -->
                    <el-container style="margin-top: -16px">
                        <el-header style="height:30px;margin-top:-20px">

                        </el-header>

                        <!-- 查询输入框 -->
                        <el-form :inline="true" :model="accQuery" label-position="reight" label-width="100px"
                                 class="demo-form-inline" size="mini" style="margin-top: 15px;">

                            <el-form-item label="卡号|手机号">
                                <el-popover
                                        placement="bottom"
                                        width="400"
                                        trigger="hover">
                                    <el-table :data="gridDatas">
                                        <el-table-column width="150" property="date" label="日期"></el-table-column>
                                        <el-table-column width="100" property="name" label="姓名"></el-table-column>
                                        <el-table-column width="300" property="address" label="地址"></el-table-column>
                                    </el-table>
                                    <el-input slot="reference" placeholder="卡号|手机号" style="width:200px"></el-input>
                                </el-popover>
                            </el-form-item>

                            <el-form-item label="储值产品">
                                <el-popover
                                        placement="bottom"
                                        width="400"
                                        trigger="hover">
                                    <el-table :data="gridDatas">
                                        <el-table-column width="150" property="date" label="日期"></el-table-column>
                                        <el-table-column width="100" property="name" label="姓名"></el-table-column>
                                        <el-table-column width="300" property="address" label="地址"></el-table-column>
                                    </el-table>
                                    <el-input slot="reference" placeholder="储值产品" style="width:200px"></el-input>
                                </el-popover>
                            </el-form-item>

                            <fieldset style="border:solid 1px  #C0C5CB">
                                <legend>客户卡信息 & 储值产品信息</legend>
                                <el-form-item label="卡号">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="卡余额">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="卡状态">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                                <el-form-item label="开卡日期">
                                    <div class="block">
                                        <el-date-picker
                                                v-model="value1"
                                                type="date"
                                                placeholder="选择日期"
                                                style="width:125px">
                                        </el-date-picker>
                                    </div>
                                </el-form-item>

                                <el-form-item label="客户名称">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="手机">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="客户等级">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                                <el-form-item label="专属SA">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="专属门店">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="产品名称">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="售价">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="赠送价值">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="总价值">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="充值后总额">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="已收金额">
                                    <el-input autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                                </el-form-item>

                                <el-form-item label="收款状态">
                                    <el-select v-model="value" placeholder="请选择" size="mini" style="width:125px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value">
                                        </el-option>
                                    </el-select>
                                </el-form-item>
                            </fieldset>

                            <fieldset style="border:solid 1px  #C0C5CB;width:250px">
                                <legend>录入收款金额</legend>
                                <div style="margin-left:-20px">
                                    <el-form-item label="微信支付">
                                        <el-input autocomplete="off" placeholder="微信支付金额"
                                                  style="width:120px"></el-input>
                                    </el-form-item>
                                    <br/>
                                    <el-form-item label="代收金额">
                                        <el-input autocomplete="off" style="width:120px"></el-input>
                                    </el-form-item>
                                </div>
                            </fieldset>


                            <el-container style="margin-left:310px;margin-top:-140px">
                                <el-main>
                                    <div style="height:150px; overflow:auto;border:solid 1px  #C0C5CB">

                                        <el-table :data="gridDatas">
                                            <el-table-column width="100" property="date" label="操作"></el-table-column>
                                            <el-table-column width="100" property="name" label="应收金额"></el-table-column>
                                            <el-table-column width="100" property="address"
                                                             label="实收金额"></el-table-column>
                                            <el-table-column width="100" property="name" label="付款方式"></el-table-column>
                                            <el-table-column width="100" property="address"
                                                             label="收款内容"></el-table-column>
                                        </el-table>

                                    </div>
                                </el-main>
                            </el-container>

                        </el-form>

                    </el-container>
                </el-container>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible6 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 冻结的对话框 -->
            <el-dialog title="冻结" :visible.sync="dialogFormVisible7" width="500px">
                <el-form :model="form" size="mini" :inline="true">
                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>资金账号信息</legend>

                        <el-form-item label="姓名">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="电话">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>
                    </fieldset>

                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>冻结原因</legend>

                        <el-form-item label="原因:">
                            <el-radio-group v-model="radio">
                                <el-radio :label="3">遗失</el-radio>
                                <el-radio :label="6">损坏</el-radio>
                                <el-radio :label="9">其他</el-radio>
                            </el-radio-group>
                        </el-form-item>


                        <el-form-item label="备注:">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="请输入内容"
                                    v-model="textarea">
                            </el-input>
                        </el-form-item>


                    </fieldset>


                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible7 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 挂失的对话框 -->
            <el-dialog title="挂失" :visible.sync="dialogFormVisible8" width="500px">
                <el-form :model="form" size="mini" :inline="true">
                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>资金账号信息</legend>

                        <el-form-item label="姓名">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="电话">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>
                    </fieldset>

                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>挂失原因</legend>

                        <el-form-item label="原因:">
                            <el-radio-group v-model="radio">
                                <el-radio :label="3">遗失</el-radio>
                                <el-radio :label="6">损坏</el-radio>
                                <el-radio :label="9">其他</el-radio>
                            </el-radio-group>
                        </el-form-item>


                        <el-form-item label="备注:">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="请输入内容"
                                    v-model="textarea">
                            </el-input>
                        </el-form-item>


                    </fieldset>


                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible8 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 补卡的对话框 -->
            <el-dialog title="补卡" :visible.sync="dialogFormVisible9" width="550px">
                <el-form :model="form" size="mini" :inline="true">
                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>资金账号信息</legend>

                        <el-form-item label="姓名">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>

                        <el-form-item label="电话">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>
                    </fieldset>

                    <fieldset style="border:solid 1px  #C0C5CB">
                        <legend>卡信息</legend>

                        <el-form-item label="卡号">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                            <el-button icon="el-icon-more" @click="dialogFormVisible10 = true"></el-button>
                        </el-form-item>

                        <el-form-item label="发卡门店">
                            <el-input autocomplete="off" style="width:120px"></el-input>
                        </el-form-item>


                        <el-form-item label="备注:">
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="请输入内容"
                                    v-model="textarea">
                            </el-input>
                        </el-form-item>


                    </fieldset>


                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible9 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>

            <!-- 补卡中点击卡号的对话框 -->
            <el-dialog title="选择卡信息" :visible.sync="dialogFormVisible10" width="550px">
                <el-container style="height: 400px; border: 1px solid #eee">
                    <!-- 上边区域 -->
                    <el-container style="margin-top: -20px">
                        <el-header style="height:30px">
                            <div style="margin-top:-15px">
                                <el-dropdown>
                                    <i class="el-icon-tickets" style="margin-right: 10px;"></i>
                                </el-dropdown>
                                <span style="font-size:15px">发卡管理</span>
                            </div>
                        </el-header>

                        <!-- 查询输入框 -->
                        <el-form :inline="true" :model="employeeQuery" class="demo-form-inline" size="mini"
                                 style="margin-top: 15px;">

                            <el-form-item label="卡号">
                                <el-input placeholder="点检包名称"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary">查询</el-button>
                            </el-form-item>
                        </el-form>

                        <!-- 表格数据 -->
                        <el-table
                                ref="multipleTable"
                                :data="acctableData"
                                tooltip-effect="dark"
                                style="width: 100%">
                            <el-table-column
                                    type="selection"
                                    width="55">
                            </el-table-column>
                            <el-table-column
                                    label="卡号"
                                    width="120">
                                <template slot-scope="scope">{{ scope.row.date }}</template>
                            </el-table-column>
                            <el-table-column
                                    prop="name"
                                    label="领卡门店"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="领卡人"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="发卡人"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="卡商"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="是否已经发放给客户"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="有效期"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="备注"
                                    show-overflow-tooltip>
                            </el-table-column>
                        </el-table>

                        <!-- 分页 -->
                        <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="current"
                                :page-sizes="[100, 200, 300, 400]"
                                :page-size="100"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="400">
                        </el-pagination>

                    </el-container>
                </el-container>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible10 = false">取 消</el-button>
                    <el-button type="primary">确 定</el-button>
                </div>
            </el-dialog>


        </el-card>
    </div>


</template>

<script>
    export default {
        data() {
            return {
                //会员卡查询条件的对象
                accQuery: {},
                //会员卡查询列表
                acctableData: [],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 封装表单数据的对象
                form: {},
                daoru: [{
                    value: '',
                    label: '---请选择---'
                }, {
                    value: 'Y',
                    label: '是'
                }, {
                    value: 'N',
                    label: '否'
                }],
                mark:"",
                //客户与车辆查询条件的对象
                cciQuery: {},
                //客户与车辆的查询列表
                gridData: [],
                carDate: [],
                brName: '',
                custName: '',
                custLevel: '',
                saname: '',
                kahao: '',
                abcData: [],
                abcQuery: {
                    cardNo : ''
                },
                // 当前页号
                current2: 1,
                // 分页列表
                pageSizeList2: [50, 100, 500, 1000, 5000],
                // 页大小
                limit2: 500,
                // 总记录数
                total2: 0,
                brName2: '',
                cardType: '',
                addCarform:[],
                addCarform2:[],

                gridDatas: [],
                options: [],
                value1: '',
                tableData: [],
                data: [{
                    id: 1,
                    label: '小拇指点检目录',
                    children: [{
                        id: 2,
                        label: '火花目录',
                    }, {
                        id: 3,
                        label: '大灯目录'
                    }, {
                        id: 4,
                        label: '动力目录'
                    }
                        , {
                            id: 5,
                            label: '动力目录'
                        }
                        , {
                            id: 6,
                            label: '动力目录'
                        }
                        , {
                            id: 7,
                            label: '动力目录'
                        }]
                }],
                mode: "transfer", // transfer addressList
                toData: [],
                employeeQuery: {},
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dialogFormVisible3: false,
                dialogFormVisible4: false,
                dialogFormVisible5: false,
                dialogFormVisible6: false,
                dialogFormVisible7: false,
                dialogFormVisible8: false,
                dialogFormVisible9: false,
                dialogFormVisible10: false,
                value: '',
                input: '',

                bool: '',
                templateSelection: '',
                templateRadio: 0,
                radio: 3,
            };
        },
        created() {
            this.getAccList()
            this.getCciList()
            this.getAbcList()
        },

        methods: {
            textarea() {

            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            singleElection(row) {
                this.templateSelection = this.tableData.indexOf(row);
                this.templateRadio = row.id;
            },
            // 获取会员卡列表的方法
            async getAccList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-cust-card/queryPage/${this.current}/${this.limit}`, this.accQuery)
                this.acctableData = response.data.rows
                this.total = response.data.total
            },
            // 查询按钮的方法
            search() {
                this.getAccList()
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
            //点击客户与车辆表格时触发
            async clickGrid(row) {
                this.addCarform=[]
                this.addCarform=row;
                console.log(this.addCarform)
                this.accQuery.custUuid=row.custUuid;
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-cust-card/queryPage/${this.current}/${this.limit}`, this.accQuery)
                if(response.data.rows.length==0){
                    this.carDate = []
                    this.carDate.push(row);
                    this.cciQuery.custPhone = row.custPhone
                    this.saname = row.saname
                    this.brName = row.brName
                    this.custName = row.custName
                    this.custLevel = row.custLevel
                }else{
                    this.$message({
                        message: '该客户已经买过卡，请进行其他操作，如挂失...',
                        type: 'warning'
                    });
                }


            },
            querykahao() {
                this.getAbcList()
            },
            // 获取领卡记录列表的方法
            async getAbcList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-br-card/queryCardNo/${this.current2}/${this.limit2}`, this.abcQuery)
                this.abcData = response.data.rows
                this.total2 = response.data.total
            },
            abcclick(row) {
                this.addCarform2=[]
                this.addCarform2=row;
                console.log(this.addCarform2)
                this.abcQuery.cardNo = ''
                this.abcQuery.cardNo = row.cardNo
                this.brName2 = row.brName
                this.cardType = row.cardType


            },
            async addVipCar(){
                this.dialogFormVisible=false;
                this.accQuery={}
                this.addCarform.saUuid=this.$store.getters.userInfo.id
                const _this = this
                await _this.$axios.post(`/brVipcard/acct-cust-card/add/`, {
                    addCarform: JSON.stringify(_this.addCarform),
                    addCarform2: JSON.stringify(_this.addCarform2),
                })

                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    // 刷新数据表格
                    this.getAccList()

            },


            // 页大小改变事件
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getAccList()
            },
            // 页号改变事件
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getAccList()
            },
            // 页大小改变事件
            handleSizeChange2(val) {
                console.log(`每页 ${val} 条`)
                this.limit2 = val
                this.getAbcList()
            },
            // 页号改变事件
            handleCurrentChange2(val) {
                console.log(`当前页: ${val}`)
                this.current2 = val
                // 查询
                this.getAbcList()
            },
            //冻结
            dongjie() {
                if (this.templateRadio == 0) {
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                } else {
                    this.dialogFormVisible7 = true;
                }
            },
            //解冻
            jiedong() {
                if (this.templateRadio == 0) {
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                }
            },
            //补卡
            buka() {
                if (this.templateRadio == 0) {
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                } else {
                    this.dialogFormVisible9 = true;
                }
            },
            //挂失
            guashi() {
                if (this.templateRadio == 0) {
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                } else {
                    this.dialogFormVisible8 = true;
                }
            },
            //取消挂失
            quxiaoguashi() {
                if (this.templateRadio == 0) {
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                }
            },
            //撤销
            chexiao() {
                if (this.templateRadio == 0) {
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                }
            },

        },


    };
</script>

<style scoped>

</style>
