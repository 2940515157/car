<!-- - - - - - - - - - - - - -->
<!--        优惠卷消费记录      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">优惠卷消费记录</span>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header style="height:40px;">

                <!-- 查询输入框 -->
                <el-form :inline="true" :model="acbrQuery" :label-position="right"
                         class="demo-form-inline" size="mini" style="margin-top: 15px;">
                    <el-form-item label="车牌号">
                        <el-input v-model="acbrQuery.licensePlate"  style="width:130px"></el-input>
                    </el-form-item>

                    <el-form-item label="车主名称">
                        <el-input v-model="acbrQuery.custName" style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="车主电话">
                        <el-input v-model="acbrQuery.custPhone"  style="width:130px"></el-input>
                    </el-form-item>

                    <el-form-item label="优惠卷名称">
                        <el-input v-model="acbrQuery.acName" style="width:130px"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="SerachCha">查询</el-button>
                    </el-form-item>

                </el-form>

                <!-- 表格数据 -->
                <el-table
                        height="400px"
                        ref="multipleTable"
                        :data="acbrData"
                        tooltip-effect="dark"
                        style="width: 100%">
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column
                            prop="acName"
                            label="优惠卷名称"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="acNumber"
                            label="优惠卷号"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="totalPrice"
                            label="优惠卷单价"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="custName"
                            label="客户姓名"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="custPhone"
                            label="电话"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="useDate"
                            label="消费时间"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="licensePlate"
                            label="车牌"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="refCode"
                            label="业务单据号"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="bizRefType"
                            label="业务类型"
                            show-overflow-tooltip>
                    </el-table-column>
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


        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                acbrQuery:{},
                acbrData:[],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,


            };
        },
        created() {
            this.getAbcrList()

        },
        methods: {
            // 获取所有门店套餐的方法
            async getAbcrList () {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-cou-pon/queryPage/${this.current}/${this.limit}`, this.acbrQuery)
                this.acbrData = response.data.rows
                this.total = response.data.total

            },
            SerachCha(){
                this.getAbcrList();
            },

            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getAbcrList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getAbcrList()
            },
        },


    };
</script>

<style scoped>

</style>
