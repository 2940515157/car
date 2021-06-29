<!-- - - - - - - - - - - - - -->
<!--        优惠劵申请      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">优惠劵申请</span>
        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header >

                    <el-form
                            :inline="true"
                            :model="queryForm"
                            size="mini"
                            class="demo-form-inline"
                            style="margin-top:5px;padding-top:5px;margin-left:10px;">
                        <el-form-item label="优惠券名称：">
                            <el-input v-model="queryForm.acName"/>
                        </el-form-item>
                        <el-form-item label="优惠券类型：">
                            <el-select style="margin-left: 28px" v-model="queryForm.acUseTypes" filterable placeholder="请选择">
                                <el-option
                                        key="key"
                                        label="---请选择---"
                                        value="">
                                </el-option>
                                <el-option
                                        key="service"
                                        label="服务现金抵用券"
                                        value="service"/>
                                <el-option
                                        key="parts"
                                        label="配件现金抵用券"
                                        value="parts"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="getList">查询</el-button>
                            <el-button type="default" @click="reset">清空</el-button>
                        </el-form-item>
                    </el-form>
                    <!--  操作按钮  -->
                    <el-row style="height:35px;background-color: #F2F6FC">
                        <div style="margin-top: 3px;margin-left: 15px">
                            <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="selectCouDet">优惠券申请</el-button>
<!--                            <el-button type="primary" icon="el-icon-caret-bottom" size="mini" @click="getList">优惠券明细</el-button>-->
                        </div>
                    </el-row>

                    <!--  优惠券列表  -->
                    <el-table
                            ref="couDet"
                            :data="couDetList"
                            border
                            stripe
                            style="margin-top:5px;width: 100%;"
                            header-align="center"
                            size="mini"
                        @selection-change="selCouDet"
                        @row-click="toggleSelection"
                        @row-dblclick="selectCouDet">
                        <el-table-column
                                :index="index"
                                type="index"
                                align="center"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                type="selection"
                                align="center"
                                min-width="80"/>
                        <el-table-column
                                prop="acName"
                                align="center"
                                label="优惠券名称"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="acUseTypes"
                                align="center"
                                label="优惠券类型"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="acTypes"
                                align="center"
                                label="优惠券类型"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="totalPrice"
                                align="center"
                                label="卡券售价(B币购买价格)"
                                show-overflow-tooltip
                                min-width="150">
                            <template slot-scope="scope">
                                <span v-if="scope.row.totalPrice === '0.00'">0</span>
                                <span v-if="scope.row.totalPrice !== '0.00'">{{ scope.row.totalPrice }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="replacePrice"
                                align="center"
                                label="抵用总金额(服务+配件)"
                                show-overflow-tooltip
                                min-width="150">
                        </el-table-column>
                    </el-table>

                    <!--  分页  -->
                    <el-pagination
                            align="center"
                            :current-page="couDetPage"
                            :page-sizes="couDetPageList"
                            :page-size="couDetLimit"
                            :total="couDetTotal"
                            layout="total, sizes, prev, pager, next, jumper"
                            @size-change="handleSize"
                            @current-change="handleCurrent"/>
                </el-header>
            </el-container>
        </el-container>

            <el-dialog
                    :visible.sync="dialogCouDet"
                    title="优惠券申请"
                    width="70%">
                <div>
                    <fieldset>
                        <legend style="margin-bottom: 15px;margin-left: 15px;">基本信息</legend>
                        <!--查询表单组件-->
                        <el-form
                                :inline="true"
                                :model="queryForm"
                                size="mini"
                                style="margin-left: 20px"
                                class="demo-form-inline">
                            <el-form-item>
                                <span>优惠券名称:</span>
                                <el-input style="left: 100px;position: absolute;width: 150px" v-model="couDet.acName"
                                          disabled="disabled"/>
                            </el-form-item>
                            <el-form-item>
                                <span style="left: 180px;position: absolute;width: 150px">优惠券单价(B币):</span>
                                <el-input style="left: 295px;position: absolute;width: 200px" v-model="couDet.totalPrice"
                                          disabled="disabled"/>
                            </el-form-item>
                            <el-form-item>
                                <span style="left: 519px;position: absolute;width: 200px;">发行总数:</span>
                                <el-input style="left: 610px;position: absolute;width: 200px" v-model="couDet.totalCount"
                                          disabled="disabled"/>
                            </el-form-item>
                            <br><br>
                            <el-form-item>
                                <span>剩余数量:</span>
                                <el-input style="left: 100px;position: absolute;width: 150px" v-model="couDet.remainder"
                                          disabled="disabled"/>
                            </el-form-item>
                            <el-form-item>
                                <span style="left: 200px;position: absolute;width: 150px">申请数量(张):</span>
                                <el-input style="left: 310px;position: absolute;width: 200px" v-model="couDet.num"/>
                            </el-form-item>
<!--                            <el-form-item>-->
<!--                                <span style="left: 530px;position: absolute;width: 200px;">总额(B币):</span>-->
<!--                                <el-input style="left: 625px;position: absolute;width: 200px" v-model="couDet.totalPrice*couDet.num"-->
<!--                                          disabled="disabled" />-->
<!--                            </el-form-item>-->
                        </el-form>
                    </fieldset>
                    <!--    服务项目列表    -->
                    <div v-if="couDet.acTypes === '服务现金抵用券'">
                        <h2 style="background-color: #97a8be;width: 120px;height: 30px;padding-top: 4px;" align="center">服务项目</h2>
                        <el-table
                                ref="server"
                                :data="serverList"
                                key="serverList"
                                border
                                stripe
                                size="mini"
                                style="margin-top:5px;width: 100%;"
                                header-align="center">
                            <el-table-column
                                    type="index"
                                    align="center"
                                    min-width="80"/>
                            <el-table-column
                                    prop="servTypeName"
                                    show-overflow-tooltip
                                    align="center"
                                    label="服务目录"
                                    min-width="150"/>
                            <el-table-column
                                    prop="catalogName"
                                    show-overflow-tooltip
                                    align="center"
                                    label="服务项目"
                                    min-width="150"/>
                            <el-table-column
                                    prop="salePrice"
                                    show-overflow-tooltip
                                    align="center"
                                    label="销售价格"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.salePrice == 0">0</span>
                                    <span v-if="scope.row.salePrice > 0">{{ scope.row.salePrice }}.00</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="replacePrice"
                                    show-overflow-tooltip
                                    align="center"
                                    label="服务可抵用金额"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.replacePrice == 0">0</span>
                                    <span v-if="scope.row.replacePrice > 0">{{ scope.row.replacePrice }}.00</span>
                                </template>
                            </el-table-column>
                        </el-table>
                    </div>
                    <!--    配件列表    -->
                    <div v-if="couDet.acTypes === '配件现金抵用券'">
                        <h2 style="background-color: #97a8be;width: 120px;height: 30px;padding-top: 4px;" align="center">配件</h2>
                        <el-table
                                ref="parts"
                                :data="partsList"
                                key="partsList"
                                border
                                stripe
                                size="mini"
                                style="margin-top:5px;width: 100%;"
                                header-align="center">
                            <el-table-column
                                    type="index"
                                    align="center"
                                    min-width="80"/>
                            <el-table-column
                                    prop="name"
                                    show-overflow-tooltip
                                    align="center"
                                    label="零配目录"
                                    min-width="150"/>
                            <el-table-column
                                    prop="partName"
                                    show-overflow-tooltip
                                    align="center"
                                    label="商品名称"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span>{{ scope.row.brand }}&nbsp;{{ scope.row.partName }}&nbsp;{{ scope.row.model }}</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="replacePrice"
                                    show-overflow-tooltip
                                    align="center"
                                    label="配件可抵用金额"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.replacePrice == 0">0</span>
                                    <span v-if="scope.row.replacePrice > 0">{{ scope.row.replacePrice }}.00</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="acpdNumber"
                                    show-overflow-tooltip
                                    align="center"
                                    label="数量"
                                    min-width="150"/>
                            <el-table-column
                                    prop="unit"
                                    show-overflow-tooltip
                                    align="center"
                                    label="单位"
                                    min-width="150"/>
                            <el-table-column
                                    prop="allocationBase"
                                    show-overflow-tooltip
                                    align="center"
                                    label="总部分摊比率"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.allocationBase == 0">0</span>
                                    <span v-if="scope.row.allocationBase > 0">{{ scope.row.allocationBase*100 }}%</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="allocationBranches"
                                    show-overflow-tooltip
                                    align="center"
                                    label="门店分摊比率"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.allocationBranches == 0">0</span>
                                    <span v-if="scope.row.allocationBranches > 0">{{ scope.row.allocationBranches*100 }}%</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="allocationSa"
                                    show-overflow-tooltip
                                    align="center"
                                    label="SA分摊比率"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.allocationSa == 0">0</span>
                                    <span v-if="scope.row.allocationSa > 0">{{ scope.row.allocationSa*100 }}%</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="allocationSupplier"
                                    show-overflow-tooltip
                                    align="center"
                                    label="供应商分摊比率"
                                    min-width="150">
                                <template slot-scope="scope">
                                    <span v-if="scope.row.allocationSupplier == 0">0</span>
                                    <span v-if="scope.row.allocationSupplier > 0">{{ scope.row.allocationSupplier*100 }}%</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="supplierUuid"
                                    show-overflow-tooltip
                                    align="center"
                                    label="供应商UUID"
                                    min-width="150"/>
                        </el-table>
                    </div>
                </div>
                <el-button style="margin-top: 10px;margin-left: 400px" type="primary" @click="save">申请</el-button>
                <el-button type="primary" @click="dialogCouDet = false">关闭</el-button>
            </el-dialog>





        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                // 查询条件
                queryForm: {},
                // 选中的优惠券
                couDet: {},
                // 优惠券当前页
                couDetPage: 1,
                // 优惠券列表
                couDetList: [],
                // 优惠券每页记录数
                couDetLimit: 10,
                // 优惠券分页列表
                couDetPageList: [10, 20, 50, 100],
                // 优惠券总记录数
                couDetTotal: 0,
                // 优惠券对话框
                dialogCouDet: false,
                serverList:[]
            };
        },
        created() {
            this.getList()
        },
        methods: {
            // 优惠券明细排序
            index(index) {
                var page = this.couDetPage
                var limit = this.couDetLimit
                return index + (page - 1) * limit + 1
            },
            // 查询优惠券列表
            async getList() {
                const _this=this
                _this.queryForm.brUuid = 'ff80808151f8afb801521583513201b0'
                _this.queryForm.status = 'up'
                await _this.$axios.post(`/brVipcard/br_coupon/pageList/${_this.couDetPage}/${ _this.couDetLimit}`,
                    _this.queryForm)
                    .then(response => {
                        _this.couDetList = response.data.list
                        _this.couDetTotal = response.data.total
                    })
            },
            // 清空查询表单
            reset() {
                this.queryForm = {}
            },
            // 优惠券单选
            toggleSelection(row) {
                this.$refs.couDet.clearSelection()
                this.$refs.couDet.toggleRowSelection(row)
                this.couDet = row
            },
            // 点击优惠券复选框
            selCouDet(selection) {
                if (this.$refs.couDet.selection.length >= 2) {
                    this.$refs.couDet.toggleRowSelection(selection[0])
                }
                this.couDet = this.$refs.couDet.selection[0]
            },
           //双击数据显示优惠券详情
            selectCouDet() {
               if(this.$refs.couDet.selection.length==0){
                   this.$message({
                       type: 'warning',
                       message: '请选择一条数据!'
                   })

                   return
               }

                this.couDet = this.$refs.couDet.selection[0]
                console.log(this.couDet)
                var data = {acUuid: this.couDet.acUuid}
                // 查询服务项目配件列表
                if (this.couDet.acTypes === '服务现金抵用券') {
                    this.$axios.post(`/brVipcard/acct-cou-det/serverSelectAll/`,
                        data)
                        .then(response => {
                            this.serverList = response.data.list
                            this.dialogCouDet = true
                        })
                } else if (this.couDet.acTypes === '配件现金抵用券') {
                    this.$axios.post(`/brVipcard/acct-cou-det/serverSelectAll/`,
                        data)
                        .then(response => {
                            this.serverList = response.data.list
                            this.dialogCouDet = true
                        })
                }
            },
            //申请优惠券
            save(){
                if(Number(this.couDet.remainder)<Number(this.couDet.num)){
                    this.$message({
                        type: 'warning',
                        message: '要申请的优惠券数量不足!'
                    })

                    return
                }

                this.$axios.post(`/brVipcard/acct-cou-pon/addList/${this.couDet.num}`,
                    this.couDet)
                    .then(response => {
                        if (response.code === 20000) {
                            // 提示消息
                            this.$message({
                                type: 'success',
                                message: '成功!'
                            })
                            // 刷新数据表格
                            this.getList()

                            // 关闭对话框
                            this.dialogCouDet = false;
                        }
                    })
            },

            // 优惠券页大小改变事件
            handleSize(val) {
                this.couDetLimit = val
                this.getList()
            },
            // 优惠券页号改变事件
            handleCurrent(val) {
                this.couDetPage = val
                // 查询
                this.getList()
            },


        },


    };
</script>

<style scoped>

</style>
