<!-- - - - - - - - - - - - - -->
<!--        储值卡产品      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">储值卡查询管理</span>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header >

                </el-header>

                <!-- 查询输入框 -->
                <el-form :inline="true" :model="acsQuery" :label-position="right"
                         class="demo-form-inline" size="mini" style="margin-top: -40px;margin-left: 20px">
                    <el-form-item label="产品名称">
                        <el-input v-model="acsQuery.acsName" style="width:130px"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="queryAcs">查询</el-button>
                    </el-form-item>
                </el-form>

                <el-divider></el-divider>

                <!-- 表格数据 -->
                <el-table
                        height="400px"
                        :data="acsData"
                        border
                        stripe>
                    <el-table-column prop="acsName" label="产品名称"></el-table-column>
                    <el-table-column prop="acsType" label="类型"></el-table-column>
                    <el-table-column prop="salePrice" label="售价"></el-table-column>
                    <el-table-column prop="giftPrice" label="赠送价值"></el-table-column>
                    <el-table-column prop="totalPrice" label="总价值"></el-table-column>
                    <el-table-column prop="status" label="状态"></el-table-column>
                    <el-table-column prop="startDate" label="有效期(开始)"></el-table-column>
                    <el-table-column prop="endDate" label="有效期(结束)"></el-table-column>
                    <el-table-column prop="saleRate" label="销售增值比率"></el-table-column>
                    <el-table-column prop="canOwner" label="专属化"></el-table-column>
                    <el-table-column prop="remark" label="描述"></el-table-column>
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
            </el-container>
        </el-container>


        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                acsData:[],
                acsQuery: {},
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
            this.getAcsList()
        },
        methods: {
            async getAcsList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-card-setup/queryPage/${this.current}/${this.limit}`, this.acsQuery)
                this.acsData = response.data.rows
                this.total = response.data.total
            },
            queryAcs(){
                this.getAcsList()
            },
            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getAcsList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getAcsList()
            },
        },


    };
</script>

<style scoped>

</style>
