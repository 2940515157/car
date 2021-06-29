<!-- - - - - - - - - - - - - -->
<!--        优惠劵上架      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">门店优惠劵</span>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header style="height:40px;">

                    <el-form
                            :inline="true"
                            :model="queryForm"
                            size="mini"
                            class="demo-form-inline"
                            style="margin-top:5px;padding-top:5px;margin-left:10px;">
                        <el-form-item label="门店优惠券状态：">
                            <el-select v-model="queryForm.statusm" filterable placeholder="请选择">
                                <el-option
                                        key="key"
                                        label="---请选择---"
                                        value=""/>
                                <el-option
                                        key="init"
                                        label="未上架"
                                        value="init"/>
                                <el-option
                                        key="up"
                                        label="上架"
                                        value="up"/>
                                <el-option
                                        key="under"
                                        label="下架"
                                        value="under"/>
                                <el-option
                                        key="improved"
                                        label="待完善"
                                        value="improved"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="门店上架日期：">
                            <el-date-picker
                                    v-model="queryForm.putStart"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                            <el-date-picker
                                    v-model="queryForm.putStop"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
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
                        <el-form-item label="门店下架日期：">
                            <el-date-picker
                                    v-model="queryForm.pullStart"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                            <el-date-picker
                                    v-model="queryForm.pullStop"
                                    type="date"
                                    placeholder="选择日期">
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="getList">查询</el-button>
                            <el-button type="default" @click="reset">清空</el-button>
                        </el-form-item>
                        <br>
                    </el-form>

                    <!--  操作按钮  -->
                    <el-row style="height:35px;background-color: #F2F6FC">
                        <div style="margin-top: 3px;margin-left: 15px">
                            <el-button type="primary" icon="el-icon-caret-top" size="mini" @click="putaway">上架</el-button>
                            <el-button type="primary" icon="el-icon-caret-bottom" size="mini" @click="soldOut">下架</el-button>
                        </div>
                    </el-row>

                    <!--  优惠券列表  -->
                    <el-table
                            height="350px"
                            ref="couDet"
                            :data="couDetList"
                            border
                            stripe
                            style="margin-top:5px;width: 100%;"
                            header-align="center"
                            size="mini"
                            @selection-change="selCouDet"
                            @row-click="toggleSelection">
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
                                prop="statusm"
                                align="center"
                                label="门店优惠券状态"
                                show-overflow-tooltip
                                min-width="130"/>
                        <el-table-column
                                prop="statusz"
                                align="center"
                                label="总部优惠券状态"
                                show-overflow-tooltip
                                min-width="130">
                        </el-table-column>
                        <el-table-column
                                :formatter="dateTime"
                                prop="putOnDate"
                                align="center"
                                label="门店上架日期"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                :formatter="dateTime"
                                prop="pullOffDate"
                                align="center"
                                label="门店下架日期"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="totalPrice"
                                align="center"
                                label="卡券售价(B币购买价格)"
                                show-overflow-tooltip
                                min-width="150">
                        </el-table-column>
                        <el-table-column
                                prop="replacePrice"
                                align="center"
                                label="抵用总金额(服务+配件)"
                                show-overflow-tooltip
                                min-width="150">
                            <template slot-scope="scope">
                                <span v-if="scope.row.totalPrice === '0.00'">0</span>
                                <span v-if="scope.row.totalPrice !== '0.00'">{{ scope.row.totalPrice }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="compositeFlag"
                                align="center"
                                label="组合产品"
                                show-overflow-tooltip
                                min-width="100">
                            <template slot-scope="scope">
                                <span>{{ scope.row.compositeFlag === 'Y' ? '是' : '否' }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="salPrice"
                                align="center"
                                label="销售价格"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="totalCount"
                                align="center"
                                label="发行总数"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="remainder"
                                align="center"
                                label="剩余数量"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                :formatter="dateTime"
                                prop="startDatez"
                                align="center"
                                label="有效期(开始)"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                :formatter="dateTime"
                                prop="endDatez"
                                align="center"
                                label="有效期(结束)"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                :formatter="dateTime"
                                prop="putNoDatez"
                                align="center"
                                label="上架时间"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                :formatter="dateTime"
                                prop="pullOffDatez"
                                align="center"
                                label="下架时间"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="remark"
                                align="center"
                                label="备注"
                                show-overflow-tooltip
                                min-width="100"/>
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


        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                // 查询条件
                queryForm: {},
                // 优惠券列表
                couDetList: [],
                // 选中的优惠券
                couDet: {},
                // 优惠券当前页
                couDetPage: 1,
                // 优惠券分页列表
                couDetPageList: [10, 20, 50, 100],
                // 优惠券每页记录数
                couDetLimit: 10,
                // 优惠券总记录数
                couDetTotal: 0,
                // 优惠券对话框
                dialogCouDet: false,
                // 服务项目列表
                serverList: [],
                // 配件列表
                partsList: []
            }
        },
        created() { // 页面渲染之前执行
            this.getList()
        },
        methods: {
            // 查询优惠券列表
            getList() {
                this.queryForm.brUuid = 'ff80808151f8afb801521583513201b0'
                 this.$axios.post(`/brVipcard/br_coupon/pageList/${this.couDetPage}/${this.couDetLimit}`,
                    this.queryForm)
                    .then(response => {
                        this.couDetList = response.data.list
                        this.couDetTotal = response.data.total
                    })
            },
            // 时间格式化
            dateTime(row, column) {
                var date = row[column.property]
                return /\d{4}-\d{1,2}-\d{1,2}/g.exec(date)
            },
            // 优惠券明细排序
            index(index) {
                var page = this.couDetPage
                var limit = this.couDetLimit
                return index + (page - 1) * limit + 1
            },
            // 清空查询表单
            reset() {
                this.queryForm = {}
            },
            // 上架优惠券
            putaway() {
                if (this.$refs.couDet.selection.length === 0) {
                    this.$message({
                        message: '请选择一条数据',
                        type: 'warning'
                    })
                    return
                }

                if (this.$refs.couDet.selection[0].status === 'up') {
                    this.$message({
                        message: '不能重复上架',
                        type: 'warning'
                    })
                    return
                }
                var _this=this;
                var data = {bcUuid: this.couDet.bcUuid, status: 'up'}
               _this.$axios.post(`/brVipcard/acct-cou-pon/updateStatus/`,data)
                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })

                //刷新数据表格
                this.getList()
            },
            // 下架优惠券
            soldOut() {
                if (this.$refs.couDet.selection.length === 0) {
                    this.$message({
                        message: '请选择一条数据',
                        type: 'warning'
                    })
                    return
                }

                if (this.$refs.couDet.selection[0].status === 'under') {
                    this.$message({
                        message: '商品已下架',
                        type: 'warning'
                    })
                    return
                }

                var _this=this;
                var data = {bcUuid: this.couDet.bcUuid, status: 'under'}
                _this.$axios.post(`/brVipcard/acct-cou-pon/updateStatus/`,data)

                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })

                //刷新数据表格
                this.getList()
            },
            // 优惠券单选
            toggleSelection(row) {
                console.log(row)
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
