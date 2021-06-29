<!-- - - - - - - - - - - -->
<!--       优惠券管理      -->
<!-- - - - - - - - - - - -->

<template>
    <div>

        <!--卡片视图区域-->
        <el-card class="k-card">
            <!--查询条件-->
            <el-form :inline="true" :model="CouponQuery" class="demo-form-inline" size="mini">

                <el-form-item label="卡卷名称">
                    <el-input v-model="CouponQuery.acName" placeholder="卡卷名称"></el-input>
                </el-form-item>

                <el-form-item label="业务分类">
                    <el-select v-model="CouponQuery.bizType" clearable placeholder="请选择">
                        <el-option
                                v-for="item in options1"
                                :key="item.dictid"
                                :label="item.dictname"
                                :value="item.dictid">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="卡卷使用类型">
                    <el-select v-model="CouponQuery.acUseType" clearable placeholder="请选择">
                        <el-option
                                v-for="item in options3"
                                :key="item.dictid"
                                :label="item.dictname"
                                :value="item.dictid">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="CouponQuery.status" clearable placeholder="请选择">
                        <el-option
                                v-for="item in options4"
                                :key="item.dictid"
                                :label="item.dictname"
                                :value="item.dictid">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="SerachCha">查询</el-button>
                </el-form-item>
            </el-form>
            <!--按钮区域-->
            <el-button type="primary" size="mini" icon="el-icon-plus" @click="addYHui=true">增加</el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="editOp">编辑</el-button>
            <el-button type="danger" size="mini" icon="el-icon-plus" @click="xuanzefa">选择发行范围</el-button>
            <el-button type="primary" size="mini" icon="el-icon-delete" @click="removes">删除</el-button>
            <el-button type="primary" size="mini" icon="el-icon-upload2" @click="ShangJia">上架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="xiajia">下架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="tuihui">强制退回</el-button>
            <!--内容主体-->
            <el-table size="mini"
                      ref="multipleTable"
                      :data="CouponData"
                      tooltip-effect="dark"
                      max-height="500px"
                      border
                      style="width: 100%"
                      @selection-change="handleSelectionChange1">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>
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
                        prop="acTypeName"
                        label="卡卷类型"
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
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="current"
                    :page-sizes="pageSizeList"
                    :page-size="limit"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>

            <!--增加 或 编辑 -->
            <el-dialog :title="form.acUuid == null ? '增加优惠劵管理':'编辑优惠劵管理'" :visible.sync="addYHui" width="70%" @close="DialogClosed">
                <fieldset style="border:solid 1px #aaa;padding: 3px">
                    <legend>基本信息</legend>
                    <el-form :model="form" size="mini" :inline="true"
                             :rules="FormRules" ref="FormRef">
                        <el-form-item label="卡卷名称:" prop="acName" :label-width="formLabelWidth">
                            <el-input v-model="form.acName" autocomplete="off" style="width: 300px"></el-input>
                        </el-form-item><br>

                        <el-form-item label="业务分类:" prop="bizType" :label-width="formLabelWidth">
                            <el-select v-model="form.bizType" placeholder="--请选择--" style="width: 189.48px">
                                <el-option
                                        v-for="item in options1"
                                        :key="item.dictid"
                                        :label="item.dictname"
                                        :value="item.dictid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="卡卷类型:" prop="acType" :label-width="formLabelWidth">
                            <el-select v-model="form.acType" placeholder="--请选择--" style="width: 189.48px">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.dictid"
                                        :label="item.dictname"
                                        :value="item.dictid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="卡卷使用类型:" prop="acUseType" :label-width="formLabelWidth">
                            <el-select v-model="form.acUseType" placeholder="--请选择--" style="width: 189.48px">
                                <el-option
                                        v-for="item in options3"
                                        :key="item.dictid"
                                        :label="item.dictname"
                                        :value="item.dictid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="抵用总金额:" prop="replacePrice" :label-width="formLabelWidth">
                            <el-input readonly v-model="form.replacePrice" autocomplete="off" style="width: 189.48px"></el-input>
                        </el-form-item>

                        <el-form-item label="发行总数:" prop="totalCount" :label-width="formLabelWidth" >
                            <el-input v-model="form.totalCount" autocomplete="off" style="width: 189.48px" @input="sunCount"></el-input>
                        </el-form-item>
                        <el-form-item label="剩余数量:" prop="remainder" :label-width="formLabelWidth">
                            <el-input readonly v-model="form.remainder" autocomplete="off" style="width: 189.48px"></el-input>
                        </el-form-item>

                        <el-form-item label="组合产品:" prop="compositeFlag" :label-width="formLabelWidth">
                            <el-select v-model="form.compositeFlag" placeholder="--请选择--" style="width: 189.48px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="卡卷售价(B币):" prop="totalPrice" :label-width="formLabelWidth">
                            <el-input v-model="form.totalPrice" autocomplete="off" style="width: 189.48px"></el-input>
                        </el-form-item>

                        <el-form-item label="销售价格:" prop="salPrice" :label-width="formLabelWidth">
                            <el-input v-model="form.salPrice" autocomplete="off" style="width: 189.48px"></el-input>
                        </el-form-item>

                        <el-form-item label="有效期(开始):" prop="startDate" :label-width="formLabelWidth">
                            <el-date-picker style="width: 189.48px"
                                            type="datetime"
                                            v-model="form.startDate"
                                            placeholder="选择开始时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="有效期(结束):" prop="endDate" :label-width="formLabelWidth">
                            <el-date-picker style="width: 189.48px"
                                            type="datetime"
                                            v-model="form.endDate"
                                            placeholder="选择截止时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item><br>

                        <el-form-item label="备注:" prop="remark" :label-width="formLabelWidth">
                            <el-input type="textarea" v-model="form.remark" style="width: 400px"></el-input>
                        </el-form-item>


                        <el-tabs v-model="activeName" type="card">
                            <el-tab-pane label="服务" name="first">
                                <el-button type="primary" size="mini" icon="el-icon-plus" @click="addInputer()">增加</el-button>
                                <el-button type="primary" size="mini" icon="el-icon-delete" @click="removeInputer()">删除
                                </el-button>
                                <!-- 服务表-->
                                <el-table
                                        ref="multipleTables"
                                        :data="xzFWData"
                                        tooltip-effect="dark"
                                        style="width: 100%"
                                        max-height="250"
                                        @selection-change="selectChange"
                                        border
                                        @cell-dblclick="celledit">
                                    <el-table-column label="序号" width="50">
                                        <template slot-scope="scope">
                                            {{scope.row.index=scope.$index+1}}
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            type="selection"
                                            width="55">
                                    </el-table-column>
                                    <el-table-column
                                            prop="servTypeName"
                                            label="服务目录"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="catalogName"
                                            label="服务项目"
                                            width="120">
                                    </el-table-column>
                                    <el-table-column
                                            prop="replacePrice"
                                            label="服务可抵用金额"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.replacePrice" type="text">{{ scope.row.replacePrice }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            prop="allocationBase"
                                            label="总部分摊比率"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.allocationBase" type="text">{{ scope.row.allocationBase}}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            prop="allocationBranches"
                                            label="门店分摊比率"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.allocationBranches" type="text">{{ scope.row.allocationBranches }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            prop="allocationSa"
                                            label="SA分摊比率"
                                            show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.allocationSa" type="text" style="width: 200px">{{ scope.row.allocationSa }}</el-input>
                                        </template>
                                    </el-table-column>

                                </el-table>

                            </el-tab-pane>
                            <!--配件表-->
                            <el-tab-pane label="配件" name="second">
                                <el-button type="primary" size="mini" icon="el-icon-plus" @click="adInputer">增加</el-button>
                                <el-button type="primary" size="mini" icon="el-icon-delete" @click="removInputer">删除</el-button>
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
                                    <el-table-column
                                            prop="replacePrice"
                                            label="服务可抵用金额"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.replacePrice" type="text">{{ scope.row.replacePrice }}</el-input>
                                        </template>
                                    </el-table-column>
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
                                            label="总部分摊比率"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input size="mini"  v-model="scope.row.allocationBase" type="text">{{ scope.row.allocationBase }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="门店分摊比率"
                                            width="120">
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.allocationBranches" type="text">{{ scope.row.allocationBranches }}</el-input>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="SA分摊比率"
                                            show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <el-input size="mini" v-model="scope.row.allocationSa" type="text">{{ scope.row.allocationSa }}</el-input>
                                        </template>
                                    </el-table-column>

                                </el-table>
                            </el-tab-pane>
                        </el-tabs>

                    </el-form>
                </fieldset>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="addYHui = false">取 消</el-button>
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
                                    @size-change="handleSizeChange1"
                                    @current-change="handleCurrentChange1"
                                    :current-page="page1"
                                    :page-sizes="[10, 20, 50,100]"
                                    :page-size="row1"
                                    layout="total, sizes, prev, pager, next, jumper"
                                    :total="total1">
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
            <el-dialog width="60%"  title="选择发行范围" :visible.sync="faCare_setup" >
                <span style="font-size:12px">优惠卷产品名称:</span>{{this.yhjName}}
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
                        @selection-change="handleSelectionChange5">
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

                <el-button size="mini" type="primary" @click="faxing" style="width: 85px;float: right;margin-top: -200px;margin-right: 435px">选择&gt;&gt;</el-button>
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
                               @size-change="handleSizeChange5"
                               @current-change="handleCurrentChange5"
                               :current-page="current5"
                               :page-sizes="pageSizeList"
                               :page-size="limit5"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total5">
                </el-pagination>

                <!--分页区域-->
                <el-pagination small
                               style="float: right;margin-top: -20px"
                               @size-change="handleSizeChange6"
                               @current-change="handleCurrentChange6"
                               :current-page="current6"
                               :page-sizes="pageSizeList"
                               :page-size="limit6"
                               layout="total, sizes, prev, pager, next, jumper"
                               :total="total6">
                </el-pagination>
            </el-dialog>



        </el-card>
    </div>
</template>

<script>
    export default {
        name: "index",
        data() {
            return {
                //验证规则
                FormRules:{
                    acName:[
                        {required: true, message: '请输入卡卷名称', trigger: 'blur'},
                    ],
                    bizType:[
                        {required: true, message: '请选择业务分类'},
                    ],
                    acType:[
                        {required: true, message: '请选择卡券类型'},
                    ],
                    acUseType:[
                        {required: true, message: '请选择卡券使用类型'},
                    ],
                    totalCount:[
                        {required: true, message: '请输入发行总数', trigger: 'blur'},
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
                    compositeFlag:[
                        {required: true, message: '请选择是否组合产品'},
                    ],
                    totalPrice:[
                        {required: true, message: '请输入卡券售币', trigger: 'blur'},
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
                    salPrice:[
                        {required: true, message: '请输入卡券售币', trigger: 'blur'},
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
                    startDate: [
                        {required: true, message: '请选择开始时间'},
                    ],
                    endDate: [
                        {required: true, message: '请选择结束时间'},
                    ],
                },
                //优惠券产品名称
                yhjName:'',
                replacePrice:'',
                allocationBase:'',
                allocationBranches:'',
                allocationSa:'',
                fwData: [{nums: ''}],
                //服务目录的树形菜单
                fwmlData: [],
                SerObjectQuery: {},
                //服务项目选中的id
                multipleSelection: [],
                muData: [],
                ServObjectData: [],
                // 当前页号
                current3: 1,
                // 分页列表
                pageSizeList3: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit3: 5,
                // 总记录数
                total3: 0,
                xzFWData: [{nums2: ''}],
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
                //当前时间
                currentTime: '',
                //优惠券数据
                CouponData: [],
                //优惠券查询条件
                CouponQuery: {},
                //优惠券选中的数据
                selecctCouData:[],
                forms: {},
                // 表单元素的label的宽度
                formLabelWidth: '141px',
                names: '',
                //组合产品
                options: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}],
                //业务分类
                options1: [],
                //卡券类型
                options2: [],
                //卡券使用类型
                options3: [],
                //状态
                options4: [],

                activeName: 'first',
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //增加 编辑优惠
                addYHui: false,
                //选择服务
                dialogFormVisible2: false,
                //选择配件
                dialogFormVisible3: false,
                //配件信息查询条件
                baseCarPartsSkuQuery: {},
                multipleTabless: [],
                //表单数据
                form: {},
                //配件目录
                treeData: [],
                //卡券id
                AcUuid:'',
                //服务类型id
                stUuid:[],
                acdUuid:[],
                //配件
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                basePartsTypeQuery: {},
                peijianData:[],
                row1: 10,
                page1: 1,
                total1: 0,
                peijianData2:[],
                //选中的优惠券状态
                selectStuts:[],
                //选中优惠的数据
                SelectionYHData:[],
                //选择发行范围对话框
                faCare_setup:false,
                //区域
                quyu:{
                    type: "1"
                },
                //选择发行范围
                options5: [
                    {id: '1', label: '大区'},
                    {id: '2', label: '门店'}],
                //发行范围的数据
                FaXingData:[],
                tableData5:[],
                SelectionData5:[],
                total5:0,
                total6:0,
                current5:1,
                current6:1,
                limit5:5,
                limit6:5,
                SelectionData6:[],
                selectId2:[],
                form1:{},
                //门店名称
                brName:'',
                toms:{},
                scUuid1:'',
                partTypeUuids:[],
                servCatalogUuid:'',
                //配件被选中的数据
                PeiJianTabless:[],
            }
        },
        created() {
            //获取优惠券
            this.getCouponList();
            //获取当前时间
            this.mounted();
            //查询左边的菜单
            this.getLeftList();
            //查询右边的菜单
            this.getRightList();
            //配件信息
            this.getTreeData();
            this.getTableData();
            //业务分类
            this.getYeWuList();
            //卡券使用类型
           this.getKaQuaznList();
           //卡券状态
           this.getStutaList();
           //卡券类型
          this.getKaQuanTypeList();
        },
        methods: {
            //获取当前时间
            mounted() {
                this.currentTime = this.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            },

            //打开优惠券
            editOp(){

                if(this.selecctCouData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selecctCouData.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }

                if(this.SelectionYHData[0].statusName==='上架'||this.SelectionYHData[0].statusName==='下架'){
                    this.$message({
                        type:'info',
                        message:'上架下架产品不能修改'
                    })
                    return
                }

                //根据卡券id查询出优惠券
                const _this = this
                _this.$axios.post(`/serviceCard/acct-coupon/queryById/${this.SelectionYHData[0].acUuid}`).then(({data})=>{
                    // console.log(data)
                    this.form=data.acctCoupon;
                    // console.log(this.form)

                })
                //根据卡券id查询出优惠券服务明细
                this.formss={}
                this.scUuid1=''
                this.formss.acUuid=this.SelectionYHData[0].acUuid
                _this.$axios.post(`/serviceCard/acct-coupon-detail/queryById/`,this.formss).then(result=>{
                    this.xzFWData=result.data.list;
                    for(var i=0;i<this.xzFWData.length;i++){
                        this.scUuid1+=this.xzFWData[i].servCatalogUuid+','
                    }
                })

                this.getKaQuanPeiJian(this.formss)
                //弹出添加 或 修改对话框
                this.addYHui=true;

            },
            //根据卡券id 查出卡券配件明细
            getKaQuanPeiJian(formss){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-coupon-parts-detail/queryById/`,formss).then(result=>{
                    this.fwData=result.data.list;
                })
            },
            //对话框关闭
            DialogClosed(){
                this.$refs.FormRef.resetFields();
                this.xzFWData=[]
            },
            //优惠券添加 或 修改
            save() {
                this.$refs.FormRef.validate(valid => {
                    if (!valid) return;
                    if (this.form.acUseType == "service" && this.fwData.length > 1) {
                        this.$message({
                            message: '警告哦，"您选的是服务套餐，请不要添加配件数据！"',
                            type: 'warning'
                        });
                        return
                    }
                    if (this.form.acUseType == "parts" && this.xzFWData.length > 1) {
                        this.$message({
                            message: '警告哦，"您选的是配件套餐，请不要添加服务数据！"',
                            type: 'warning'
                        });
                        return
                    }

                    if(this.form.endDate<this.form.startDate){
                      this.$message({
                        message: '警告哦，"您的开始时间小于结束时间！"',
                        type: 'warning'
                      });
                      return
                    }


                    if (this.form.acUuid == null) {
                        //创建人
                        this.form.creatorUuid =this.$store.getters.userInfo.id;
                        //优惠券规则
                        this.form.acNumber = '20200725000001';
                        //默认不上架
                        this.form.status='init'
                        //是否有效
                        this.form.isActive = 'Y';
                        //创建日期
                        this.form.createdDate = this.currentTime;
                        //创建人
                        this.form.createdBy = this.$store.getters.userInfo.name;
                        //版本
                        this.form.version = 0;

                        //抵用总金额
                        this.form.replacePrice = 0;
                        for(var j=0;j<this.xzFWData.length;j++){
                            this.form.replacePrice+= Number(this.xzFWData[j].replacePrice);
                        }

                        const _this = this
                        if(this.form.acUseType == "service"){
                            // alert("TRUE")
                            // 添加优惠券  和优惠券服务
                            _this.$axios.post('/serviceCard/acct-coupon/add',{
                                form: JSON.stringify(this.form),
                                forms: JSON.stringify(this.xzFWData),

                            }).then(result => {
                                if (result.code === 20000) {
                                    this.$message({
                                        type: 'success',
                                        message: '添加成功!',
                                    });
                                }
                                //添加完 关闭对话框
                                this.addYHui = false;
                                //刷新
                                this.getCouponList();
                                this.xzFWData=[];
                                this.form={};
                            })
                        }else {
                            // alert("FALSE")
                            //抵用总金额
                            this.form.replacePrice = 0;
                            for(let k=0;k<this.fwData.length;k++){
                                this.form.replacePrice+= Number(this.fwData[k].replacePrice);
                                this.partTypeUuids[k]={partTypeUuids: this.fwData[k].partType};
                            }
                            // 添加优惠券 和 优惠券配件
                            _this.$axios.post('/serviceCard/acct-coupon/adds',{
                                form: JSON.stringify(this.form),
                                forms: JSON.stringify(this.fwData),
                                partTypeUuid: JSON.stringify(this.partTypeUuids),
                            }).then(result => {
                                if (result.code === 20000) {
                                    this.$message({
                                        type: 'success',
                                        message: '添加成功!',
                                    });
                                }
                                //添加完 关闭对话框
                                this.addYHui = false;
                                //刷新
                                this.getCouponList();
                                this.fwData={};
                            })
                        }


                    } else if (this.form.acUuid != null) {
                        //修改日期
                        this.form.updatedDate = this.currentTime;
                        //修改人
                        this.form.updatedBy = this.$store.getters.userInfo.name;
                        //版本
                        this.form.version = this.form.version + Number(1);
                        //抵用总金额
                        this.form.replacePrice = 0;
                        for(var q=0;q<this.xzFWData.length;q++){
                            this.form.replacePrice+= Number(this.xzFWData[q].replacePrice);
                        }
                        const _this = this
                        _this.$axios.post('/serviceCard/acct-coupon/update', _this.form).then(result => {
                            if (result.code === 20000) {
                                this.$message({
                                    type: 'success',
                                    message: '修改成功!',
                                });
                            }
                        })

                        for(var i=0;i<this.xzFWData.length;i++){
                            this.stUuid[i]={stUuid: this.xzFWData[i].servTypeUuid};
                            this.acdUuid[i]={acdUuid: this.xzFWData[i].acdUuid};
                            this.servCatalogUuid+=this.xzFWData[i].servCatalogUuid+','
                        }
                        //修改优惠券与服务项目明细
                        _this.$axios.post('/serviceCard/acct-coupon-detail/update',{
                            forms2: JSON.stringify(this.xzFWData),
                            stUuid: JSON.stringify(this.stUuid),
                            acdUuid: JSON.stringify(this.acdUuid),
                            AcUuid: this.form.acUuid
                        }).then(result => {
                            if (result.code === 20000) {
                                //刷新
                                this.getCouponList();
                                this.xzFWData=[]
                                this.addYHui=false;
                            }

                        })

                    }
                })
            },
            //服务选中事件
            selectChange(val) {
                this.multipleTabless = val;
            },
            //配件选中事件
            selectChange1(val){
                this.PeiJianTabless= val;

            },
            /* 删除输入框*/
            removeInputer() {
                if (this.multipleTabless.length === 0) {
                    this.$message.warning('请至少选择一行数据')
                    return;
                }
                for (let i = this.multipleTabless.length; i > 0; i--) {

                    // 获取选中行的索引的方法
                    // 遍历表格中tableData数据和选中的val数据，比较它们的rowNum,相等则输出选中行的索引
                    // rowNum的作用主要是为了让每一行有一个唯一的数据，方便比较，可以根据个人的开发需求从后台传入特定的数据
                    if(this.multipleTabless[0].servTypeUuid==null){
                        this.xzFWData.splice(0,1)
                    }
                  this.xzFWData.splice(this.xzFWData[i].index-1,1)


                }

            },
            /* 添加服务输入框*/
            addInputer(){
                var datas =[]
                this.xzFWData.push(datas)
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

            //点击服务项目 弹出服务项目对话框
            celledit(row, column) {
                row.flag = true
                if (column.label === '服务项目') {
                    row.edit_enddate = !row.edit_enddate;
                    this.dialogFormVisible2 = true;
                }
            },
            celledit1(row) {
                var _this = this;
                for (var i = 0; i < _this.tableData1.length; i++) {
                    if (_this.tableData1[i].name === row.name) {
                        alert(_this.tableData1[i].name);
                        return;
                    }
                }
                _this.tableData1.push(row);
                this.xuanzeFuWu = false;
            },
            //分页条件查询优惠券
            getCouponList() {
                const _this = this
                _this.$axios.post(`/serviceCard/acct-coupon/queryAllACoup/${this.current}/${this.limit}`, _this.CouponQuery).then(result => {
                    _this.CouponData = result.data.rows;
                    _this.total = result.data.total;
                })
            },
            //点击查询
            SerachCha() {
                this.getCouponList();
            },
            //点击服务目录查询服务项目
            clicknode(data) {
                const _this = this
                //获取点击菜单的id
                _this.SerObjectQuery.stUuid = data.stUuid;
                //查询右边的菜单
                this.getRightList(this.getRightList);
            },
            //查询服务项目左边的服务目录菜单
            getLeftList() {
                const _this = this
                _this.$axios.get("/serviceServiceProduct/base-serv-type").then(result => {
                    _this.fwmlData = result.data.rows;
                })
            },
            //查询右边服务项目数据
            getRightList() {
                const _this = this
                _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/queryAllPage/${this.current3}/${this.limit3}`, _this.SerObjectQuery).then(result => {
                    _this.ServObjectData = result.data.rows;
                    _this.total3 = result.data.total;
                })
            },
            //点击查询服务项目
            Serachc() {
                this.getRightList();
            },
            //双击获取服务项目数据
            dbfwml(row) {
                const _this = this
                for (var i = 0; i < _this.xzFWData.length; i++) {
                    if (_this.xzFWData[i].catalogUuid == row.catalogUuid) {
                        this.$message({
                            type:'info',
                            message:'不能添加相同的项目'
                        })
                        return
                    }

                }
                this.xzFWData.push(row)
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
                    this.total1 = result.data.total
                })
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
                console.log(row)
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
            //双击  获取配件信息对话框
            peijian(row, column){
                if (column.label === '商品名称') {
                    row.edit_enddate = !row.edit_enddate;
                    this.dialogFormVisible3 = true;
                }
            },


            //删除优惠券
            removes(){
                if(this.selecctCouData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }

                for(var i=0;i<this.selectStuts.length;i++){
                    if(this.selectStuts[i]=='上架'){
                        this.$message({
                            type:'info',
                            message:'已上架的不能删除'
                        })
                        return
                    }
                    if(this.selectStuts[i]=='下架'){
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
                    for(var i=0;i<this.selecctCouData.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceCard/acct-coupon/delete/${this.selecctCouData[i]}`).then(result=>{
                            if(result.code === 20000){
                                this.$message({
                                    type: 'success',
                                    message: '删除成功!'
                                });
                            }
                            //获取优惠券
                            this.getCouponList();
                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //上架
            ShangJia(){
                if(this.selecctCouData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selecctCouData.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                if(this.SelectionYHData[0].statusName==='上架'||this.SelectionYHData[0].endDate<this.currentTime){
                    this.$message({
                        type:'info',
                        message:'只有非上架产品且在有效时间内的产品才能上架'
                    })
                    return
                }
                this.getquyu()
                if(this.total6===0){
                    this.$message({
                        type:'info',
                        message:'请选择发行范围'
                    })
                    return
                }

                this.form=this.SelectionYHData[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-coupon/update1',_this.form).then(result=>{
                    if(result.code === 20000){
                        this.$message({
                            type: 'success',
                            message: '上架成功!'
                        });
                    }
                    //获取优惠券
                    this.getCouponList();
                })

            },

            //下架
            xiajia(){
                if(this.selecctCouData.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selecctCouData.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }

                if(this.SelectionYHData[0].statusName==='下架'){
                    this.$message({
                        type:'info',
                        message:'只有上架产品才能下架'
                    })
                    return
                }

                this.form=this.SelectionYHData[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-coupon/update2',_this.form).then(result=>{
                    if(result.code === 20000){
                        this.$message({
                            type: 'success',
                            message: '下架成功!'
                        });
                    }
                    //获取优惠券
                    this.getCouponList();
                })
            },

            //强制退回
            tuihui(){
                if(this.selecctCouData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selecctCouData.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }

                if(this.SelectionYHData[0].statusName==='下架'||this.SelectionYHData[0].statusName==='上架'){
                    return;
                }else {
                    this.$message({
                        type:'info',
                        message:'未上架的储值卡无须强制退回'
                    })
                }

                this.form=this.SelectionYHData[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-coupon/update3',_this.form).then(result=>{
                    if(result.code === 20000){
                        this.$message({
                            type: 'success',
                            message: '下架成功!'
                        });
                    }
                    //获取优惠券
                    this.getCouponList();
                })
            },

            // 发行总数等于剩余数量
            sunCount(){
                this.form.remainder=this.form.totalCount;
            },

            //选择
            faxing(){

                if(this. SelectionData5.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.quyu.type==='1'){
                    const _this = this
                    for(var j=0;j<this.SelectionData5.length;j++) {
                        _this.forms.acUuid = this.SelectionYHData[0].acUuid;
                        _this.forms.pkUuid = this.SelectionData5[j].orgUuid;
                        _this.forms.cpaType = 'org';
                        _this.forms.isActive = 'Y';
                        _this.forms.version = 0;
                        _this.$axios.post('/serviceCard/acct-coupon-area/add', _this.forms).then(result => {
                            if (result.code === 20000) {
                                //刷新
                                this.getquyu();
                            }

                        })
                    }
                }else {
                    const _this = this
                    for (var i = 0; i < this.SelectionData5.length; i++) {
                        _this.forms.acUuid = this.SelectionYHData[0].acUuid;
                        _this.forms.pkUuid = this.SelectionData5[i].brUuid
                        _this.forms.cpaType = 'br'
                        _this.forms.isActive = 'Y';
                        _this.forms.version = 0;
                        _this.$axios.post('/serviceCard/acct-coupon-area/add', _this.forms).then(result => {
                            if(result.code === 20000) {
                                //刷新
                                this.getquyu();
                            }
                        })

                    }
                }

            },
            //取消
            quxiaofa(){
                if(this.SelectionData6.length===0){
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
                        _this.$axios.delete(`/serviceCard/acct-coupon-area/delete/${this.selectId2[i]}`).then(result=>{
                            if(result.code === 20000) {
                                //刷新
                                this.getquyu()
                            }
                        })
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //选择发行范围
            xuanzefa(){
                if(this.selecctCouData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selecctCouData.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                this.yhjName=this.SelectionYHData[0].acName;
                this.faCare_setup=true;
                //查询卡的区域
                this.getquyu();

                if(this.quyu.type==='1'){
                    this.getHrOrgList();
                    return;
                }
                this.getHrBList();
            },

          //业务分类
         async getYeWuList(){
            const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_COUPON_BIZ_TYPE`)
            this.options1= result.data
          },
          //卡券使用类型
          async getKaQuaznList(){
            const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_COUPON_USE_TYPE`)
            this.options3= result.data
          },
          //优惠券状态
          async getStutaList(){
            const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_PRODUCT_STATUS`)
            this.options4= result.data
          },
          //卡券类型
          async getKaQuanTypeList(){
            const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_COUPON_TYPE`)
            this.options2= result.data
          },

            //查询优惠券的区域
            getquyu(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-coupon-area/queryAllPage/${this.current6}/${this.limit6}/${this.SelectionYHData[0].acUuid}`).then(result=>{
                    _this.tableData5=result.data.rows
                    _this.total6=result.data.total;

                })
            },
            //门店查询
            branchesSearch(){
                this.getHrBList()
            },
            //分页查询大区 部门
            getHrOrgList(){
                const _this = this
                _this.$axios.post(`/serviceHr/HrOrg/queryAllPage/${this.current5}/${this.limit5}`).then(result=>{
                    _this.FaXingData=result.data.rows
                    _this.total5=result.data.total;
                })
            },
            //分页查询门店
            getHrBList(){
                const _this = this
                _this.toms.brName=this.brName
                _this.$axios.post(`/serviceHr/hr-branches/queryAllHrBPage/${this.current5}/${this.limit5}`,this.toms).then(result=>{
                    _this.FaXingData=result.data.rows
                    _this.total5=result.data.total;
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
            //选择发行范围 选中改变事件
            handleSelectionChange5(val){
                this.SelectionData5=val;
            },
            handleSelectionChange6(val){

                this.SelectionData6=val;
                this.selectId2=[]
                for(var i=0;i<val.length;i++){
                    this.selectId2.push(val[i].acaUuid);
                }
            },

            //获取选中的id
            handleSelectionChange(val) {
                //先清空id数组
                this.multipleSelection = [];
                this.muData = [];
                //去重获取被选中的id值存入到数组中
                for (let selectedItem of val) {
                    this.multipleSelection.push(selectedItem.catalogUuid)
                    this.muData.push(selectedItem.servTypeName)
                }
            },
            //优惠券选择
            handleSelectionChange1(val) {
                this.SelectionYHData=val;
                //先清空
                this.selecctCouData=[];
                this.selectStuts=[]
                //去重获取被选中的id值存入到数组中
                for (let selectedItem of val) {
                    this.selecctCouData.push(selectedItem.acUuid)
                    this.selectStuts.push(selectedItem.statusName)
                }

            },
            // 页大小改变事件
            handleSizeChange(val) {
                this.getCouponList();
                this.limit = val

            },
            // 页号改变事件
            handleCurrentChange(val) {
                this.getCouponList();
                this.current = val
            },
            //配件信息每页条数变化
            handleSizeChange1(val) {
                this.row1 = val;
                this.getTableData();
            },
            //配件信息页数变化
            handleCurrentChange1(val) {
                this.page1 = val;
                this.getTableData();
            },
            // 大区部门页大小改变事件
            handleSizeChange5 (val) {
                this.limit5 = val
                if(this.quyu.type==='1'){
                    this.getHrOrgList()
                    return
                }
                this.getHrBList()

            },
            // 大区部门页号改变事件
            handleCurrentChange5 (val) {
                this.current5 = val
                if(this.quyu.type==='1'){
                    this.getHrOrgList()
                    return
                }
                this.getHrBList()
            },
            // 页大小改变事件
            handleSizeChange6 (val) {
                this.limit6 = val

            },
            // 页号改变事件
            handleCurrentChange6 (val) {
                this.current6 = val
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
        }
    }
</script>
<style scoped>
    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>
