<!-- - - - - - - - - - - - - -->
<!--        优惠劵明细      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>

                <el-dropdown>
                    <i class="el-icon-tickets" style="margin-right: 10px;"></i>
                </el-dropdown>
                <span style="font-size:15px">优惠劵明细</span>


        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header style="height:40px;">
                <!-- 查询输入框 -->
                    <el-form
                            :inline="true"
                            :model="queryForm"
                            size="mini"
                            class="demo-form-inline"
                            style="margin-top:5px;padding-top:5px;margin-left:10px;">
                        <el-form-item label="优惠券名称：">
                            <el-input v-model="queryForm.acName"/>
                        </el-form-item>
                        <el-form-item label="优惠券号：">
                            <el-input v-model="queryForm.acNumber"/>
                        </el-form-item>
                        <el-form-item label="状态：">
                            <el-select v-model="queryForm.status" filterable placeholder="请选择">
                                <el-option
                                        key="key"
                                        label="---请选择---"
                                        value=""/>
                                <el-option
                                        key="normal"
                                        label="未转赠"
                                        value="normal"/>
                                <el-option
                                        key="attorn"
                                        label="转赠未使用"
                                        value="attorn"/>
                                <el-option
                                        key="used"
                                        label="转赠已使用"
                                        value="used"/>
                                <el-option
                                        key="expired"
                                        label="已过期"
                                        value="expired"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="转赠客户：">
                            <el-input v-model="queryForm.custName"/>
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
                            <el-button type="primary" icon="el-icon-circle-plus-outline" size="mini" @click="getClienteList">转赠客户
                            </el-button>
                        </div>
                    </el-row>

                    <!--  优惠券列表  -->
                    <el-table
                            height="400px"
                            ref="registro"
                            :data="registroList"
                            border
                            stripe
                            style="margin-top:5px;width: 100%;"
                            header-align="center"
                            size="mini"
                            @selection-change="selregistro"
                            @row-click="toggleSelection"
                            @row-dblclick="selectregistro">
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
                                prop="acNumber"
                                align="center"
                                label="优惠券号"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="acName"
                                align="center"
                                label="优惠券名称"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="price"
                                align="center"
                                label="优惠券单价"
                                show-overflow-tooltip
                                min-width="150">
                            <template slot-scope="scope">
                                <span v-if="scope.row.price === '0.00'">0</span>
                                <span v-if="scope.row.price !== '0.00'">{{ scope.row.price }}</span>
                            </template>
                        </el-table-column>
                        <el-table-column
                                prop="statusm"
                                align="center"
                                label="状态"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="custName"
                                align="center"
                                label="转赠客户"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="custPhone"
                                align="center"
                                label="客户电话"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="name"
                                align="center"
                                label="转赠SA"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="attornDate"
                                align="center"
                                label="转赠时间"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                        <el-table-column
                                prop="useDate"
                                align="center"
                                label="消费时间"
                                show-overflow-tooltip
                                min-width="100">
                        </el-table-column>
                    </el-table>

                    <!--  分页  -->
                    <el-pagination
                            align="center"
                            :current-page="registroPage"
                            :page-sizes="registroPageList"
                            :page-size="registroLimit"
                            :total="registroTotal"
                            layout="total, sizes, prev, pager, next, jumper"
                            @size-change="handleSize"
                            @current-change="handleCurrent"/>
                </el-header>
            </el-container>
        </el-container>

            <!-- 转赠客户对话框   -->
            <el-dialog
                    :visible.sync="dialogCliente"
                    title="选择客户信息"
                    width="70%">
                <div>
                    <el-form
                            :inline="true"
                            :model="clienteForm"
                            size="mini"
                            class="demo-form-inline"
                            style="margin-top:5px;padding-top:5px;margin-left:10px;">
                        <el-form-item label="客户名称：">
                            <el-input v-model="clienteForm.custName"/>
                        </el-form-item>
                        <el-form-item label="手机：">
                            <el-input v-model="clienteForm.custPhone"/>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="getClienteList">查询</el-button>
                            <el-button type="default" @click="resetCliente">清空</el-button>
                        </el-form-item>
                        <br>
                    </el-form>

                    <!--  客户列表  -->
                    <el-table
                            ref="cliente"
                            :data="clienteList"
                            border
                            stripe
                            style="margin-top:5px;width: 100%;"
                            header-align="center"
                            size="mini"
                            @selection-change="selCliente"
                            @row-click="toggleSelectionCliente">
                        <el-table-column
                                :index="indexCliente"
                                type="index"
                                align="center"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                type="selection"
                                align="center"
                                min-width="80"/>
                        <el-table-column
                                prop="custNo"
                                align="center"
                                label="车主编号"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="custName"
                                align="center"
                                label="客户名称"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="custPhone"
                                align="center"
                                label="手机"
                                show-overflow-tooltip
                                min-width="150"/>
                        <el-table-column
                                prop="sex"
                                align="center"
                                label="性别"
                                show-overflow-tooltip
                                min-width="100"/>
                        <el-table-column
                                prop="custLevels"
                                align="center"
                                label="客户等级"
                                show-overflow-tooltip
                                min-width="100"/>
                    </el-table>
                    <!--  分页  -->
                    <el-pagination
                            align="center"
                            :current-page="clientePage"
                            :page-sizes="clientePageList"
                            :page-size="clienteLimit"
                            :total="clienteTotal"
                            layout="total, prev, pager, next, jumper"
                            @current-change="handleCurrentCliente"/>
                </div>
                <el-button type="primary" size="mini" style="margin-left: 420px;margin-top: 10px" @click="transfer">转赠</el-button>
                <el-button type="primary" size="mini" @click="dialogCliente = false">关闭</el-button>
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
                registro: {},
                // 优惠券当前页
                registroPage: 1,
                // 优惠券列表
                registroList: [],
                // 优惠券每页记录数
                registroLimit: 10,
                // 优惠券分页列表
                registroPageList: [10, 20, 50, 100],
                // 优惠券总记录数
                registroTotal: 0,
                // 优惠券对话框
                dialogregistro: false,
                // 客户查询条件
                clienteForm: {},
                // 客户当前页
                clientePage: 1,
                // 客户列表
                clienteList: [],
                // 客户每页记录数
                clienteLimit: 10,
                // 客户分页列表
                clientePageList: [10, 20, 50, 100],
                // 客户总记录数
                clienteTotal: 0,
                // 选中的客户
                cliente: {},
                // 客户对话框
                dialogCliente: false,
            };
        },
        created() {
            this.getList()
        },
        methods: {
            // 优惠券明细排序
            index(index) {
                var page = this.registroPage
                var limit = this.registroLimit
                return index + (page - 1) * limit + 1
            },
            // 客户明细排序
            indexCliente(index) {
                var page = this.clientePage
                var limit = this.clienteLimit
                return index + (page - 1) * limit + 1
            },
            // 查询优惠券列表
            getList() {
                this.queryForm.brUuid = 'ff80808151f8afb801521583513201b0'
                this.$axios.post(`/brVipcard/acct-cou-pon/pageList2/${this.registroPage}/${this.registroLimit}`, this.queryForm)
                    .then(response => {
                        this.registroList = response.data.list
                        this.registroTotal = response.data.total
                    })
            },
            // 查询客户列表
            getClienteList() {
                if (this.$refs.registro.selection[0].status !== 'normal') {
                    this.$message({
                        showClose: true,
                        message: '只有未使用的优惠劵才能转赠',
                        type: 'warning'
                    });
                    return
                }
                this.clienteForm.brUuid = 'ff80808151f8afb801521583513201b0'
                this.$axios.post(`/brVipcard/cust-cust-info/pageList/${this.clientePage}/${this.clienteLimit}`, this.clienteForm)
                    .then(response => {
                        this.clienteList = response.data.list
                        this.clienteTotal = response.data.total
                        this.dialogCliente = true
                    })
            },
            // 客户单选
            toggleSelectionCliente(row) {
                this.$refs.cliente.clearSelection()
                this.$refs.cliente.toggleRowSelection(row)
                this.cliente = row
            },
            // 点击客户复选框
            selCliente(selection) {
                if (this.$refs.cliente.selection.length >= 2) {
                    this.$refs.cliente.toggleRowSelection(selection[0])
                }
                this.cliente = this.$refs.cliente.selection[0]
            },
            // 清空客户查询表单
            resetCliente() {
                this.clienteForm = {}
            },
            // 清空优惠券查询表单
            reset() {
                this.queryForm = {}
            },
            // 优惠券页大小改变事件
            handleSize(val) {
                this.registroLimit = val
                this.getList()
            },
            // 优惠券页号改变事件
            handleCurrent(val) {
                this.registroPage = val
                // 查询
                this.getList()
            },
            // 客户页号改变事件
            handleCurrentCliente(val) {
                this.clientePage = val
                // 查询
                this.getClienteList()
            },
            // 优惠券单选
            toggleSelection(row) {
                this.$refs.registro.clearSelection()
                this.$refs.registro.toggleRowSelection(row)
                this.registro = row
            },
            // 点击优惠券复选框
            selregistro(selection) {
                if (this.$refs.registro.selection.length >= 2) {
                    this.$refs.registro.toggleRowSelection(selection[0])
                }
                this.registro = this.$refs.registro.selection[0]
            },
            // 转赠客户
            transfer() {
                if (this.cliente.custUuid == null) {
                    this.$message({
                        showClose: true,
                        message: '请选择一个客户',
                        type: 'warning'
                    });
                    return
                }
                var data = {
                    acbrUuid: this.registro.acbrUuid,
                    status: 'attorn',
                    custUuid: this.cliente.custUuid,
                    saUuid: this.$store.getters.userInfo.id
                }
                this.$axios.post(`/brVipcard/acct-coupon-buy-record/update`, data)
                    .then(response => {
                        if (response.code === 20000) {
                            // 提示消息
                            this.$message({
                                type: 'success',
                                message: '成功!'
                            })
                            // 刷新数据表格
                            this.getList()
                            this.dialogCliente = false
                        }
                    })
            }
        },


    };
</script>

<style scoped>

</style>
