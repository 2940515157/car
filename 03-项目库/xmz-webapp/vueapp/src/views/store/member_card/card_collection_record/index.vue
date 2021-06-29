<!-- - - - - - - - - - - - - -->
<!--        领卡记录      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            <span>领卡记录</span>
        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header>
                <!-- 查询输入框 -->
                <el-form :inline="true" :model="abcQuery" :label-position="right"
                         class="demo-form-inline" size="mini" style="margin-top: 15px;">
                    <el-form-item label="卡号">
                        <el-input v-model="abcQuery.cardNo" placeholder="卡号" style="width:130px"></el-input>
                    </el-form-item>

                    <el-form-item label="领卡人">
                        <el-select placeholder="请选择" v-model="abcQuery.empUuid" size="mini" style="width:125px">
                            <el-option
                                    v-for="item in options"
                                    :key="item.empUuid"
                                    :label="item.empName"
                                    :value="item.empUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>
<!--                    v-model="abcQuery.impFlag"-->
                    <el-form-item label="是否已经发放给客户" >
                        <el-select v-model="abcQuery.isExtend" placeholder="请选择" size="mini" style="width:130px">
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

                </el-form>

                    <el-divider></el-divider>

                <!-- 表格数据 -->
                <el-table
                        border
                        height="400px"
                        ref="multipleTable"
                        :data="tableData"
                        tooltip-effect="dark"
                        style="width: 100%">
                    <el-table-column
                            prop="cardNo"
                            label="卡号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            label="领卡人"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="name2"
                            label="发放人"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="cardCompany"
                            label="卡商"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="isExtend"
                            label="是否已经发放给客户"
                            show-overflow-tooltip>
                        <template slot-scope="scope">
                            <span v-if="scope.row.isExtend=='Y'">是</span>
                            <span v-if="scope.row.isExtend=='N'">否</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="brCardStatus"
                            label="卡状态"
                            show-overflow-tooltip>
                    </el-table-column>
                    <el-table-column
                            prop="createdDate"
                            label="发卡时间"
                            show-overflow-tooltip>
                    </el-table-column>

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
        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                //查询条件
                abcQuery:{},
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [5, 10, 50, 100, 500],
                // 页大小
                limit: 10,
                // 总记录数
                total: 0,
                tableData:[],
                daoru: [{
                    value: '',
                    label: ''
                }, {
                    value: 'Y',
                    label: '是'
                }, {
                    value: 'N',
                    label: '否'
                }],

                options:[],


            };
        },
        created () {
            this.getAbcList()
            this.getEmpList()
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            // 获取所有领卡人
            async getEmpList () {
                const response = await this.$axios.post('/brVipcard/acct-br-card/listHe')
                this.options = response.data.list
            },
            // 获取领卡记录列表的方法
            async getAbcList () {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-br-card/queryPage/${this.current}/${this.limit}`, this.abcQuery)
                this.tableData = response.data.rows
                this.total = response.data.total

            },
            search(){
                this.getAbcList()
            },
            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getAbcList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getAbcList()
            },

        },


    };
</script>

<style scoped>

</style>
