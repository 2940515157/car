<!-- - - - - - - - - - - - - -->
<!--        套餐产品管理       -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <el-form :inline="true" :model="taocancha" class="demo-form-inline" size="mini">
                <el-form-item label="套餐名称">
                    <el-input v-model="taocancha.apName" placeholder="套餐名称"></el-input>
                </el-form-item>

                <el-form-item label="套餐类型">
                    <el-select v-model="taocancha.apType" placeholder="请选择" clearable>
                        <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.label"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="状态">
                    <el-select v-model="taocancha.status" placeholder="请选择" clearable>
                        <el-option
                                v-for="item in options1"
                                :key="item.dictid"
                                :label="item.dictname"
                                :value="item.dictid">
                        </el-option>
                    </el-select>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="serchCX()">查询</el-button>
                </el-form-item>
            </el-form>
            <el-button type="primary" size="mini" icon="el-icon-plus"  @click="addTaoCanChanPin=true">增加</el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit"  @click="edit">编辑</el-button>
            <el-button type="danger"  size="mini" icon="el-icon-plus" @click="faCare()">选择发行范围</el-button>
            <el-button type="primary" size="mini"  icon="el-icon-delete" @click="removes">删除</el-button>
            <el-button type="primary" size="mini" icon="el-icon-upload2" @click="shangjia">上架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="xiajia">下架</el-button>

            <el-table
                    max-height="350px"
                    size="mini"
                    ref="multipleTable"
                    :data="taocanData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="apName"
                        label="套餐名称"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="apTypeName"
                        label="套餐类型"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="totalPrice"
                        label="套餐售价"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="statusName"
                        label="状态"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="compositeFlag"
                        label="组合产品"
                        width="120"
                        :formatter="formatUserState">
                </el-table-column>


                <el-table-column
                        prop="validMonth"
                        label="使用期限(月)"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="startDate"
                        label="开始时间"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="endDate"
                        label="结束时间"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="origionTypeName"
                        label="发起类型"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="bizTypeName"
                        label="业务分类"
                        width="120">
                </el-table-column>
            </el-table>
            <!--分页区域-->
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="current"
                    :page-sizes="pageSizeList"
                    :page-size="limit"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>


            <!-- 添加或修改套餐产品-->
            <el-dialog :title="form.apUuid == null ? '增加套餐产品':'编辑套餐产品'" :visible.sync="addTaoCanChanPin" width="70%" @close="DialogClosed">
                <fieldset style="border:solid 1px #aaa;padding: 3px">
                    <legend>基本信息</legend>
                    <el-form :model="form" size="mini" :inline="true"
                             :rules="FormRules" ref="FormRef">
                        <el-form-item label="套餐名称:" prop="apName" :label-width="formLabelWidth">
                            <el-input v-model="form.apName" autocomplete="off" style="width: 300px"></el-input>
                        </el-form-item>
                        <br>
                        <el-form-item label="使用期限(月):" prop="validMonth" :label-width="formLabelWidth">
                            <el-input v-model="form.validMonth" autocomplete="off" style="width: 189px"></el-input>
                        </el-form-item>


                        <el-form-item label="套餐类型:" prop="apType" :label-width="formLabelWidth">
                            <el-select v-model="form.apType" placeholder="--请选择--" style="width: 189px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="套餐售价:"  :label-width="formLabelWidth">
                            <el-input readonly  v-model="form.totalPrice" autocomplete="off" style="width: 189px"></el-input>
                        </el-form-item>

                        <el-form-item label="发起类型:" prop="origionType"  :label-width="formLabelWidth">
                            <el-select v-model="form.origionType" placeholder="--请选择--" style="width: 189px">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="销售增值比率:" prop="saleRate" :label-width="formLabelWidth">
                            <el-input v-model="form.saleRate" autocomplete="off" style="width: 189px"></el-input>
                        </el-form-item>

                        <el-form-item label="专属化:" prop="canOwner" :label-width="formLabelWidth">
                            <el-select v-model="form.canOwner" placeholder="--请选择--" style="width: 189px">
                                <el-option
                                        v-for="item in options4"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="业务分类:" prop="bizType" :label-width="formLabelWidth">
                            <el-select v-model="form.bizType" placeholder="--请选择--" style="width: 189px">
                                <el-option
                                        v-for="item in options3"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="有效期(开始):" prop="startDate" :label-width="formLabelWidth">
                            <el-date-picker v-model="form.startDate" style="width: 189px"
                                            type="datetime"
                                            placeholder="选择开始时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="有效期(结束):" prop="endDate" :label-width="formLabelWidth">
                            <el-date-picker v-model="form.endDate" style="width: 189px"
                                            type="datetime"
                                            placeholder="选择截止时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="组合产品:" prop="compositeFlag" :label-width="formLabelWidth">
                            <el-select v-model="form.compositeFlag" placeholder="--请选择--" style="width: 189px">
                                <el-option
                                        v-for="item in options4"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>


                        <el-form-item label="可返B币:" prop="bReturn" :label-width="formLabelWidth">
                            <el-input v-model="form.bReturn" autocomplete="off" style="width: 189px"></el-input>
                        </el-form-item>

                        <br>
                        <el-form-item label="备注:" prop="remark" :label-width="formLabelWidth">
                            <el-input type="textarea"  v-model="form.remark" style="width: 400px"></el-input>
                        </el-form-item>

                        <el-tabs v-model="activeName" type="card">
                            <el-tab-pane label="服务" name="first">
                                <el-button type="primary" size="mini" icon="el-icon-plus"  @click="addT">增加</el-button>
                                <el-button type="primary" size="mini"  icon="el-icon-delete" @click="removeT">删除</el-button>
                                <!-- 服务表-->
                                <el-table
                                        ref="multipleTable"
                                        :data="fuwuData"
                                        tooltip-effect="dark"
                                        style="width: 100%"
                                        max-height="250"
                                        border
                                        @selection-change='selectRow'
                                        @cell-dblclick="celledit">
                                    <el-table-column
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column label="序号"
                                                     width="60" align="center">
                                        <template slot-scope="scope">
                                            {{scope.row.index=scope.$index+1}}
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            prop="catalogName"
                                            label="服务项目"
                                            width="120">
                                    </el-table-column>

                                    <el-table-column
                                            prop="servTypeName"
                                            label="服务类型"
                                            width="80">
                                    </el-table-column>

                                    <el-table-column
                                            label="次数"
                                            width="80">
                                        <template slot-scope="scope">
                                            <el-input size="mini" @change="num" v-model="scope.row.apdNumber" type="text">{{ scope.row.apdNumber }}</el-input>
                                        </template>
                                    </el-table-column>

                                    <el-table-column
                                            label="单次套餐服务销售价格"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input @change="num" @blur="price(scope.row.salePrice,scope.row.apdNumber)" size="mini" v-model="scope.row.salePrice" type="text">{{ scope.row.salePrice }}</el-input>
                                        </template>
                                    </el-table-column>

                                    <el-table-column
                                            label="标准服务价格"
                                            width="100">
                                        <template slot-scope="scope">
                                            <el-input size="mini" @change="num" v-model="scope.row.stdPrice" type="text">{{ scope.row.stdPrice }}</el-input>
                                        </template>
                                    </el-table-column>

                                    <el-table-column
                                            label="协议结算价格"
                                            width="100">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.stlAgreementPrice" type="text">{{ scope.row.stlAgreementPrice }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="客户消费-SA增值折扣"
                                            show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.commissionRateSa" type="text">{{ scope.row.commissionRateSa }}</el-input>
                                        </template>
                                    </el-table-column>

                                </el-table>
                                <span style="float:right;margin-right:20px">服务总计：{{serviceTotal}}&emsp;配件总计：{{partsTotal}}&emsp;合计：{{serviceTotal+partsTotal}}</span>
                            </el-tab-pane>
                            <!--配件表-->
                            <el-tab-pane label="配件" name="second">
                                <el-button type="primary" size="mini" icon="el-icon-plus"  >增加</el-button>
                                <el-button type="primary" size="mini"  icon="el-icon-delete">删除</el-button>
                                <el-table
                                        ref="tableData"
                                        :data="fwData"
                                        tooltip-effect="dark"
                                        style="width: 100%"
                                        max-height="250"
                                        @selection-change="selectChange1"
                                        @cell-dblclick="peijian">
                                    <el-table-column label="序号" width="50">
                                        <template slot-scope="scope">
                                            {{scope.row.index=scope.$index+1}}
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column prop="bptNAME" label="配件目录"></el-table-column>
                                    <el-table-column prop="partName" label="商品名称"></el-table-column>
                                    <el-table-column label="总数量">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.acpdNumber"></el-input>

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
                                    <el-table-column
                                            label="单次销售价格"
                                            width="80">
                                        <template slot-scope="scope">
                                            <el-input size="mini"  v-model="scope.row.salePrice" type="text">{{ scope.row.salePrice }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="单次标准报价"
                                            width="80">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.stdPrice" type="text">{{ scope.row.allocationBranches }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="协议结算报价"
                                            width="80">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.stlAgreementPrice" type="text">{{ scope.row.stlAgreementPrice }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="客服消费-SA增值折扣"
                                            show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.commissionRateSA" type="text">{{ scope.row.commissionRateSA }}</el-input>
                                        </template>
                                    </el-table-column>

                                </el-table>
                            </el-tab-pane>
                        </el-tabs>

                    </el-form>
                </fieldset>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="addTaoCanChanPin = false">取 消</el-button>
                    <el-button type="primary" @click="save()">确 定</el-button>
                </div>

            </el-dialog>

            <!--点击服务项目表格时触发-->
            <el-dialog title="选择服务目录" :visible.sync="dialogFormVisible2" width="1000px">
                <el-container style="height: 500px; border: 1px solid #eee">
                    <!--左边-->
                    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                        <h3>服务目录</h3>
                        <!-- 树形控件-->
                        <el-tree
                                :data="fuwumuData"
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
                        <el-form :inline="true" :model="SerObjectQuery" class="demo-form-inline" label-width="110px"
                                 size="mini">


                            <el-form-item label="服务项目名称">
                                <el-input v-model="SerObjectQuery.catalogName" placeholder="服务项目名称"></el-input>
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
                                    @selection-change="handleSelectionChange1">
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
                    <el-button @click="dialogFormVisible2 = false">关 闭</el-button>
                </div>
            </el-dialog>

            <!--点击配件表格时触发-->
            <el-dialog title="选择配件目录" :visible.sync="dialogFormVisible3" width="1100px">
                <el-container>
                    <el-aside width="200px">
                        <b>配件信息</b>
                        <div style="height: 500px">
                            <el-scrollbar style="height: 99%" wrap-style="overflow:hidden scroll;">
                                <el-tree
                                        :data="treeData"
                                        node-key="partTypeUuid"
                                        :props="defaultProps"
                                        ref="tree"
                                        @node-click="carTypeFuTree">
                                </el-tree>
                            </el-scrollbar>
                        </div>
                    </el-aside>
                    <el-container>
                        <el-main>
                            <el-table
                                    :data="peijianData"
                                    style="width: 100%"
                                    max-height="400px"
                                    ref="multipleTable"
                                    @cell-dblclick="dbPeijjian">

                                <el-table-column type="selection" width="55"></el-table-column>
                                <el-table-column prop="bptNAME" label="配件目录" width="180"></el-table-column>
                                <el-table-column prop="showName" label="展示名称" width="180"></el-table-column>
                                <el-table-column prop="partNo" label="小拇指编码"></el-table-column>
                                <el-table-column prop="partName" label="商品名称" width="180"></el-table-column>
                                <el-table-column label="是否启用" width="180">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.usableFlag=='Y'">是</span>
                                        <span v-else-if="scope.row.usableFlag=='N'">否</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="brand" label="品牌"></el-table-column>
                                <el-table-column prop="model" label="厂商型号" width="180"></el-table-column>
                                <el-table-column prop="lyCode" label="厂商编号" width="180"></el-table-column>
                                <el-table-column label="最小购买单位">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.unit=='bottle'">瓶</span>
                                        <span v-else-if="scope.row.unit=='oneOfPair'">对</span>
                                        <span v-else-if="scope.row.unit=='piece'">片</span>
                                        <span v-else-if="scope.row.unit=='pair'">副</span>
                                        <span v-else-if="scope.row.unit=='branch'">只</span>
                                        <span v-else-if="scope.row.unit=='set'">组</span>
                                        <span v-else-if="scope.row.unit=='parts'">部</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="spec" label="规格" width="180"></el-table-column>
                                <el-table-column prop="stdPrice" label="总部标准价" width="180"></el-table-column>
                                <el-table-column prop="stdPriceAddRate" label="总部批量加价率"></el-table-column>
                                <el-table-column label="通用型号sku" width="180">
                                    <template slot-scope="scope">
                                        <span v-if="scope.row.matchType=='special'">是</span>
                                        <span v-else-if="scope.row.matchType=='common'">否</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="hbBRCODE" label="门店编号" width="180"></el-table-column>
                                <el-table-column prop="hbBRNAME" label="门店名称"></el-table-column>
                            </el-table>
                            <!-- 分页-->
                            <el-pagination
                                    @size-change="handleSizeChange7"
                                    @current-change="handleCurrentChange7"
                                    :current-page="page1"
                                    :page-sizes="[10, 20, 50,100]"
                                    :page-size="row1"
                                    layout="total, sizes, prev, pager, next, jumper"
                                    :total="total7">
                            </el-pagination>
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="dialogFormVisible3 = false">取 消</el-button>
                                <el-button type="primary">购买</el-button>
                            </div>
                        </el-main>
                    </el-container>
                </el-container>
            </el-dialog>


            <!--选择发行范围-->
            <el-dialog width="60%"  title="选择发行范围" :visible.sync="faTao_setup" >
                <span style="font-size:12px">套餐产品名称:</span>
                <br>
                <label>区域类型:</label>
                <el-select  size="mini" v-model="quyu.type" @change="xuanze">
                    <el-option
                            v-for="item in options5"
                            :key="item.id"
                            :label="item.label"
                            :value="item.id">
                    </el-option>
                </el-select>

                <div style="float:right;margin-right:200px;margin-top:-25px"  v-if="quyu.type=='2'">
                    <el-form size="mini" :inline="true" class="demo-form-inline" style="padding:20px;padding-bottom:0">
                        <el-form-item label="门店名称：">
                            <el-input v-model="brName" placeholder="门店名称" clearable :size='s'></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" :size='s' icon="el-icon-search" @click="branchesSearch">查询</el-button>
                        </el-form-item>
                    </el-form>

                </div>

                <el-table
                        ref="multipleTable"
                        :data="FaXingData"
                        border
                        height="320px"
                        tooltip-effect="dark"
                        style="width: 380px"
                        @selection-change="handleSelectionChange2">
                    <el-table-column
                            type="selection"
                            width="40">
                    </el-table-column>

                    <el-table-column
                            :label="quyu.type==='1'?'部门编号':'门店编号'"
                            width="80">
                        <template slot-scope="{row}">
                            <span>{{quyu.type=='1'?row.orgNo:row.brCode}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column
                            :label="quyu.type=='1'?'部门名称':'门店名称'"
                            width="160">
                        <template slot-scope="{row}">
                            <span>{{quyu.type=='1'?row.orgName:row.brName}}</span>
                        </template>
                    </el-table-column>

                    <el-table-column
                            :label="quyu.type=='1'?'部门负责人':'门店类型'"
                            show-overflow-tooltip>
                        <template slot-scope="{row}">
                            <span>{{quyu.type=='1'?row.orgManager:row.brTypeName}}</span>
                        </template>
                    </el-table-column>
                </el-table>

                <el-button size="mini" type="primary" @click="xuanzefa" style="width: 85px;float: right;margin-top: -200px;margin-right: 435px">选择&gt;&gt;</el-button>
                <el-button size="mini" type="primary" @click="quxiaofa" style="width: 85px;float: right;margin-top: -150px;margin-right: 435px">取消&gt;&gt;</el-button>
                <el-table
                        height="320px"
                        ref="multipleTable"
                        :data="tableData5"
                        border
                        tooltip-effect="dark"
                        style="width: 380px;float: right;margin-top: -320px;margin-right: 30px"
                        @selection-change="handleSelectionChange6">
                    <el-table-column
                            type="selection"
                            width="40">
                    </el-table-column>

                    <el-table-column
                            prop="brName"
                            label="使用范围类型区域"
                            width="210">
                    </el-table-column>

                    <el-table-column
                            prop="orgName"
                            label="区域"
                            show-overflow-tooltip>

                    </el-table-column>
                </el-table>

                <!--分页区域-->
                <el-pagination small
                               pager-count="3"
                               @size-change="handleSizeChange2"
                               @current-change="handleCurrentChange2"
                               :current-page="current1"
                               :page-sizes="pageSizeList1"
                               :page-size="limit1"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total1">
                </el-pagination>

                <!--分页区域-->
                <el-pagination small
                               style="float: right;margin-top: -20px"
                               @size-change="handleSizeChange1"
                               @current-change="handleCurrentChange1"
                               :current-page="current2"
                               :page-sizes="pageSizeList"
                               :page-size="limit2"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total2">
                </el-pagination>
            </el-dialog>

        </el-card>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            return{
                //配件
                row1: 10,
                page1: 1,
                total7: 0,
                //配件信息
                peijianData:[],
                //配件
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                //配件信息查询条件
                baseCarPartsSkuQuery: {},
                //配件目录
                treeData: [],
                //配件
                fwData: [],
                //配件选中事件
                PeiJianTabless:[],
                //打开配件对话框
                dialogFormVisible3:false,
                forms:{},
                //选择发行范围  门店分页
                current2: 1,
                limit2: 5,
                total2: 0,
                //套餐产品范围
                tableData5:[],
                //选择发行范围
                faTao_setup:false,
                //区域
                quyu:{
                    type: "1"
                },
                //门店名称
                brName:'',
                toms:{},
                //选中的发行范围区域类型
                SelectionData1:[],
                //发行范围数据
                FaXingData:[],
                //选择发行范围  大区部门分页
                pageSizeList1: [2, 5, 100, 200, 300, 400],
                current1: 1,
                limit1: 5,
                total1: 0,
                //套餐产品范围选中的数据
                SelectionData2:[],
                //套餐产品范围选中的id
                selectId2:[],
                //验证规则
                FormRules: {
                    apName:[{required: true, message: '请输入名称', trigger: 'blur'},],
                    validMonth:[{required: true, message: '请输入期限', trigger: 'blur'},
                        {
                            validator(rule, value, callback) {
                                var reg = /^-?\d{1,5}(?:\.\d{1,3})?$/
                                if (reg.test(value)) {
                                    callback()
                                } else {
                                    callback(new Error('请输入数字'))
                                }
                            },
                            trigger: 'blur'
                        }
                    ],
                    apType:[{required: true, message: '请选择套餐类型'},],
                    origionType:[{required: true, message: '请选择发起类型'}],
                    saleRate:[{required: true, message: '请输入销售增值比率', trigger: 'blur'},],
                    canOwner:[{required: true, message: '请选择专属化'},],
                    bizType:[{required: true, message: '请选择业务分类'},],
                    startDate:[{required: true, message: '请选择开始时间'},],
                    endDate:[{required: true, message: '请选择结束时间'},],
                    compositeFlag:[{required: true, message: '请选择是否组合'},],
                },
                //服务对话框
                dialogFormVisible2:false,
                //服务表数据
                fuwuData:[],

                //服务目录数据
                fuwumuData:[],
                //服务项目查询条件
                SerObjectQuery:{},
                //服务项目数据
                ServObjectData:[],

                // 表单元素的label的宽度
                formLabelWidth: '141px',
                activeName: 'first',
                //套餐类型
                options: [
                    {id: 'service', label: '服务套餐'},
                    {id: 'servAndParts', label: '服务配件套餐'}],
                //套餐产品状态
                options1: [],
                //发起类型
                options2: [
                    {id: 'operCenter', label: '总部运营中心'},],
                //业务分类
                options3: [
                    {id: 'P001', label: '机油卡'},
                    {id: 'P002', label: '计次卡'},],
                options4: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'},],
                //选择发行范围
                options5: [
                    {id: '1', label: '大区'},
                    {id: '2', label: '门店'}],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 当前页号
                current3: 1,
                // 分页列表
                pageSizeList3: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit3: 5,
                // 总记录数
                total3: 0,
                //表单数据
                form:{},
                //套餐产品查询条件
                taocancha:{},
                //套餐产品的数据
                taocanData:[],
                //服务选中的数据
                selectlistRow:[],
                version:0,

                //选择服务
                xuanzeFuWu:false,
                //添加或修改套餐产品
                addTaoCanChanPin:false,
                //套餐产品id
                apUuid:'',
                //选中套餐的数据
                SelectionDatas:[],
                //选中的状态
                selectStuts:[],
                //选中的id
                selectIds:[],
                //当前时间
                currentTime:'',
                //获取当前时间
                format(date, fmt) {
                    let o = {
                        "M+": date.getMonth() + 1, //月份
                        "d+": date.getDate(), //日
                        "H+": date.getHours(), //小时
                        "m+": date.getMinutes(), //分
                        "s+": date.getSeconds(), //秒
                        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                        "S": date.getMilliseconds() //毫秒
                    };
                    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                    for (let k in o)
                        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    return fmt;
                },
                //服务总计
                serviceTotal:0,
                //配件总计
                partsTotal:0,
                //单次套餐服务销售价格
                salePrice:'',
                //服务标准报价
                stdPrice:'',
                //次数
                apdNumber:'',
            }
        },
        created() {
            this.getPackageList();
            //查询左边的菜单
            this.getLeftList();
            //查询右边的菜单
            this.getRightList();
            this.mounted();

            //配件信息
            this.getTreeData();
            this.getTableData();
            //套餐状态
            this.getStutaList();
        },
        methods:{
          //套餐状态
          async getStutaList(){
            const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_PRODUCT_STATUS`)
            this.options1= result.data
          },
            //配件目录树信息
            getTreeData(){
                const _this = this
                _this.$axios.post(`/serviceCarsparts/cartype/carTypeZiTree`).then(result=>{
                    _this.treeData=result.data.rows;
                })
            },
            //配件信息
            getTableData(){
                const _this = this
                _this.$axios.post(`/serviceCarsparts/base-car-parts-sku/queryCarPartsSku/${_this.page1}/${_this.row1}`,_this.baseCarPartsSkuQuery).then(result=>{
                    _this.peijianData=result.data.rows;
                    this.total7 = result.data.total
                })
            },
            mounted(){
                this.currentTime=this.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            },
            //分页查询所有套餐产品
            getPackageList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-package/queryAllAcctPackage/${this.current}/${this.limit}`,_this.taocancha).then(result=>{
                    _this.taocanData=result.data.rows;
                    _this.total=result.data.total;
                })
            },
            //点击查询按钮
            serchCX(){
                this.getPackageList();
            },
            //服务选中的
            selectRow(val){
                this.selectlistRow = val
            },
            //次数
            num(){
                this.serviceTotal=0
                this.salePrice=''
                this.apdNumber=''
                this.form.stdTotalPrice=0;

                for(var i=0;i<this.fuwuData.length;i++){
                    this.serviceTotal+=parseFloat(this.fuwuData[i].salePrice) *parseFloat(this.fuwuData[i].apdNumber)
                    this.form.stdTotalPrice+=parseFloat(this.fuwuData[i].stdPrice)
                    this.salePrice+=this.fuwuData[i].salePrice+','
                    this.apdNumber+=this.fuwuData[i].apdNumber+','
                }

                this.form.totalPrice=Number(this.serviceTotal)

            },


            //打开编辑对话框
            edit(){
                if(this.SelectionDatas.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.SelectionDatas.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }

                const _this = this
                _this.$axios.post(`/serviceCard/acct-package/queryById/${this.selectIds[0]}`).then(result=>{
                    this.form=result.data.acctPackage;
                    //打开修改对话框
                    this.addTaoCanChanPin=true;
                    this.getAcctPackageDetail(this.form.apUuid);
                })



            },

            //套餐服务
            getAcctPackageDetail(apUuid) {
                const _this = this
                _this.$axios.get(`/serviceCard/acct-package-detail/getApUuid/${apUuid}`).then(result=>{
                    this.fuwuData=result.data.acctPackageDetail

                })
            },

            //添加
            save(){
                this.$refs.FormRef.validate(valid => {
                        if (!valid) return;
                   const _this = this

                  if(this.form.endDate<this.form.startDate){
                    this.$message({
                      message: '警告哦，"您的开始时间小于结束时间！"',
                      type: 'warning'
                    });
                    return
                  }


                    if (this.form.apUuid == null) {
                        //默认未上架
                        this.form.status='init';
                        //是否有效
                        this.form.isActive='Y';

                        // 添加优惠券
                        _this.$axios.post('/serviceCard/acct-package/add',
                            {
                                form: JSON.stringify(_this.form),
                                forms: JSON.stringify(_this.selectlistRow),
                            }).then(result => {
                            if (result.code === 20000) {
                                this.$message({
                                    type: 'success',
                                    message: '添加成功!',
                                });
                                //添加完 关闭对话框
                                this.addTaoCanChanPin = false;
                                //刷新
                                this.getPackageList();
                                this.form = {};
                            }
                        })


                    }else if(this.form.apUuid!=null){
                        //修改日期
                        this.form.updatedDate = this.currentTime;
                        //修改人
                        this.form.updatedBy = this.$store.getters.userInfo.name;
                        _this.$axios.post('/serviceCard/acct-package/update', _this.form).then(result => {
                            if (result.code === 20000) {
                                this.$message({
                                    type: 'success',
                                    message: '修改成功!',
                                });
                                //添加完 关闭对话框
                                this.addTaoCanChanPin = false;
                                //刷新
                                this.getPackageList();
                                this.form = {};
                            }
                        })

                    }


                })
            },


            //删除removes
            removes(){

                if(this.SelectionDatas.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }

                for(var i=0;i<this.selectStuts.length;i++){
                    if(this.selectStuts[i]==='上架'){
                        this.$message({
                            type:'info',
                            message:'已上架的不能删除'
                        })
                        return
                    }
                    if(this.selectStuts[i]==='下架'){
                        this.$message({
                            type:'info',
                            message:'已下架的不能删除'
                        })
                        return
                    }
                }
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    for(var i=0;i<this.selectIds.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceCard/acct-package/delete/${this.selectIds[i]}`).then(result=>{
                            if(result.code===20000){
                                //刷新
                                this.getPackageList();
                            }
                        })
                    }
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            //上架
            shangjia(){
                if(this.SelectionDatas.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.SelectionDatas.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                this.getquyu()
                if(this.total2===0){
                    this.$message({
                        type:'info',
                        message:'请选择发行范围'
                    })
                    return
                }

                if(this.SelectionDatas[0].statusName==='上架'||this.SelectionDatas[0].endDate<this.currentTime){
                    this.$message({
                        type:'info',
                        message:'只有非上架产品且在有效时间内的产品才能上架'
                    })
                    return
                }

                this.form=this.SelectionDatas[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-package/update1',_this.form).then(result=>{
                    if(result.code===20000){
                        this.$message({
                            type: 'success',
                            message: '上架成功!'
                        });

                        //刷新
                        this.getPackageList();
                    }
                })


            },
            //下架
            xiajia(){
                if(this.SelectionDatas.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.SelectionDatas.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }

                if(this.SelectionDatas[0].statusName==='下架'||this.SelectionDatas[0].statusName==='未上架'||this.SelectionDatas[0].statusName==='待完善'){
                    this.$message({
                        type:'info',
                        message:'只有上架产品才能下架'
                    })
                    return
                }

                this.form=this.SelectionDatas[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-package/update2',_this.form).then(result=>{
                    if(result.code===20000){
                        this.$message({
                        type: 'success',
                        message: '下架成功!'
                        });
                        //刷新
                        this.getPackageList();
                    }
                })

            },

            tPrice(){
                this.price();
            },
            price1(){
                this.version=Math.random()
            },
            //价格变化
            price(salePrice,apdNumber){
                this.price1();
                this.form.totalPrice=(Number(apdNumber)*Number(salePrice))
            },
            //删除一行
            removeT(){

                if (this.selectlistRow.length === 0) {
                    this.$message.warning('请至少选择一行数据')
                    return;
                }
                for (let i =this.selectlistRow.length; i >0; i--) {

                    // 获取选中行的索引的方法
                    // 遍历表格中tableData数据和选中的val数据，比较它们的rowNum,相等则输出选中行的索引
                    // rowNum的作用主要是为了让每一行有一个唯一的数据，方便比较，可以根据个人的开发需求从后台传入特定的数据
                    if(this.selectlistRow[0].servTypeUuid==null){
                        this.fuwuData.splice(0,1)
                    }
                    this.fuwuData.splice(this.fuwuData[i].index-1,1)
                }
                // 删除完数据之后清除勾选框
                this.$refs.fuwuData.clearSelection()
            },
            //添加一行
            addT(){
                this.fuwuData.push({num:''})
            },
            //点击
            celledit(row, column, cell, event){
                console.log(row,'row');//当前行的所有键值内容
                console.log(column,'column');//当前列的信息
                console.log(cell,'cell');//单元格信息
                console.log(event,'event');//事件信息

                if(column.label === '服务项目'){
                    row.edit_enddate = !row.edit_enddate;
                    this.dialogFormVisible2 = true;
                }
            },


            //点击服务目录查询服务项目
            clicknode(data) {
                const _this = this
                //获取点击菜单的id
                _this.SerObjectQuery.stUuid = data.stUuid;
                //查询右边的菜单
                this.getRightList(this.getRightList);
            },
            //查询右边服务项目数据
            getRightList() {
                const _this = this
                _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/queryAllPage/${this.current3}/${this.limit3}`, _this.SerObjectQuery).then(result => {
                    _this.ServObjectData = result.data.rows;
                    _this.total3 = result.data.total;
                })
            },
            //查询服务项目左边的服务目录菜单
            getLeftList() {
                const _this = this
                _this.$axios.get("/serviceServiceProduct/base-serv-type").then(result => {
                    _this.fuwumuData = result.data.rows;
                })
            },
            //双击获取服务项目数据
            dbfwml(row) {
                const _this = this
                for (var i = 0; i < _this.fuwuData.length; i++) {

                    if (_this.fuwuData[i].catalogUuid === row.catalogUuid) {
                        return
                    }

                }
                console.log(row)
                this.fuwuData.push(row)
            },
            //点击查询服务项目
            Serachc() {
                this.getRightList();
            },


            //配件选中事件
            selectChange1(val){
                this.PeiJianTabless= val;
            },
            //双击  获取配件信息对话框
            peijian(row, column){
                if (column.label === '商品名称') {
                    row.edit_enddate = !row.edit_enddate;
                    this.dialogFormVisible3 = true;
                }
            },
            //配件目录树点击事件
            carTypeFuTree(data,node){
                const _this = this
                this.childrens=node.childNodes;
                if(node.childNodes.length>0){
                    this.baseCarPartsSkuQuery.partType='';
                    _this.getTableData();
                    return
                }
                _this.baseCarPartsSkuQuery.partType=data.partTypeUuid;
                _this.getTableData();
            },
            //双击配件信息
            dbPeijjian(row){
                const _this = this
                for (var i = 0; i < _this.fwData.length; i++) {
                    if (_this.fwData[i].partName == row.partName) {
                        this.$message({
                            type:'info',
                            message:'不能添加相同的配件商品'
                        })
                        return
                    }
                }
                this.fwData.push(row)
            },

            //添加配件输入框
            adInputer(){
                var datas =[]
                this.fwData.push(datas)
            },
            //删除配件输入框
            removInputer(){

                if (this.PeiJianTabless.length === 0) {
                    this.$message.warning('请至少选择一行数据')
                    return;
                }
                for (let i = this.PeiJianTabless.length; i > 0; i--) {

                    // 获取选中行的索引的方法
                    // 遍历表格中tableData数据和选中的val数据，比较它们的rowNum,相等则输出选中行的索引
                    // rowNum的作用主要是为了让每一行有一个唯一的数据，方便比较，可以根据个人的开发需求从后台传入特定的数据
                    if(this.PeiJianTabless[0].partUuid==null){
                        this.fwData.splice(0,1)
                    }
                    this.fwData.splice(this.fwData[i].index-1,1)

                }
            },

            //对话框关闭
            DialogClosed(){
                this.$refs.FormRef.resetFields();
                this.fuwuData=[]
            },
            //判断是否组合产品
            formatUserState: function (row, column, cellValue) {
                if (cellValue === 'N') {
                    return '否';
                } else if (cellValue === 'Y') {
                    return '是';
                }
            },

            //分页查询大区 部门
            getHrOrgList(){
                const _this = this
                _this.$axios.post(`/serviceHr/HrOrg/queryAllPage/${this.current1}/${this.limit1}`).then(result=>{
                    _this.FaXingData=result.data.rows
                    _this.total1=result.data.total;
                })
            },
            //门店查询
            branchesSearch(){
                this.getHrBList()
            },
            //分页查询门店
            getHrBList(){
                const _this = this
                _this.toms.brName=this.brName
                _this.$axios.post(`/serviceHr/hr-branches/queryAllHrBPage/${this.current1}/${this.limit1}`,this.toms).then(result=>{
                    _this.FaXingData=result.data.rows
                    _this.total1=result.data.total;
                })
            },
            //查询套餐的区域
            getquyu(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-package-area/queryAllPage/${this.current2}/${this.limit2}/${this.SelectionDatas[0].apUuid}`).then(result=>{
                    _this.tableData5=result.data.rows
                    _this.total2=result.data.total;

                })
            },
            //选择发行范围 选择下拉框发生变化
            xuanze(){
                if(this.quyu.type==='1'){
                    this.getHrOrgList();
                    return;
                }
                this.getHrBList();
            },
            //选择发行范围
            faCare(){
                if(this.SelectionDatas.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.SelectionDatas.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                this.faTao_setup=true;
                //查询套餐产品的区域
                this.getquyu();

                if(this.quyu.type==='1'){
                    this.getHrOrgList();
                    return;
                }
                this.getHrBList();



            },
            //选择
            xuanzefa(){
                console.log(this.SelectionData1[0])
                if(this.quyu.type==='1'){
                    const _this = this
                    for(var j=0;j<this.SelectionData1.length;j++) {
                        _this.forms.apUuid = this.SelectionDatas[0].apUuid;
                        _this.forms.pkUuid = this.SelectionData1[j].orgUuid;
                        _this.forms.apaType = 'org';
                        _this.forms.isActive = 'Y';
                        _this.forms.version = 0;
                        _this.$axios.post('/serviceCard/acct-package-area/add', _this.forms).then(result => {
                            if (result.code === 20000) {
                                //刷新
                                this.getquyu();
                            }

                        })
                    }
                }else {
                    const _this = this
                    for(var i=0;i<this.SelectionData1.length;i++){
                        _this.forms.apUuid=this.SelectionDatas[0].apUuid;
                        _this.forms.pkUuid=this.SelectionData1[i].areaUuid
                        _this.forms.apaType='br'
                        _this.forms.isActive='Y';
                        _this.forms.version=0;
                        _this.$axios.post('/serviceCard/acct-package-area/add',_this.forms).then(result=> {
                            if(result.code === 20000){
                                //刷新
                                this.getquyu();
                            }
                        })
                    }


                }

            },
            //取消
            quxiaofa(){
                if(this.SelectionData2.length===0){
                    this.$message({
                        type:'info',
                        message:'请选择一条数据'
                    })
                    return
                }

                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    for(var i=0;i<this.selectId2.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceCard/acct-package-area/delete/${this.selectId2[i]}`).then(result=>{
                            if(result.code === 20000){

                            //刷新
                            this.getquyu()
                            }
                        })

                    }
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },


            //套餐产品范围选中
            handleSelectionChange6(val){
                this.SelectionData2=val;
                this.selectId2=[]
                for(var i=0;i<val.length;i++){
                    this.selectId2.push(val[i].apaUuid);
                }
            },
            //套餐产品选中改变事件
            handleSelectionChange(val){
                this.SelectionDatas=val;
                this.selectIds=[]
                for(var i=0;i<val.length;i++){
                    this.selectIds.push(val[i].apUuid)
                    this.selectStuts.push(val[i].statusName)
                }
            },
            //选择发行范围 选中改变事件
            handleSelectionChange2(val){
                this.SelectionData1=val;
            },

            handleSelectionChange1(){

            },
            // 大区部门页大小改变事件
            handleSizeChange2 (val) {
                this.limit1 = val
                if(this.quyu.type==='1'){
                    this.getHrOrgList()
                    return
                }
                this.getHrBList()

            },
            // 大区部门页号改变事件
            handleCurrentChange2 (val) {
                this.current1 = val
                if(this.quyu.type==='1'){
                    this.getHrOrgList()
                    return
                }
                this.getHrBList()
            },
            //配件信息每页条数变化
            handleSizeChange7(val) {
                this.row1 = val;
                this.getTableData();
            },
            //配件信息页数变化
            handleCurrentChange7(val) {
                this.page1 = val;
                this.getTableData();
            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getPackageList();
            },

            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getPackageList();
            },
            // 套餐产品范围页大小改变事件
            handleSizeChange1 (val) {
                this.limit2 = val

            },
            // 套餐产品范页号改变事件
            handleCurrentChange1 (val) {
                this.current2 = val
            },
            //服务项目页大小改变事件
            handleSizeChange3(val){
                this.limit3 = val
                this.getRightList();
            },
            // 服务项目页号改变事件
            handleCurrentChange3(val){
                this.current3 = val
                this.getRightList();
            },
        },

    }
</script>
<style scoped>
    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>