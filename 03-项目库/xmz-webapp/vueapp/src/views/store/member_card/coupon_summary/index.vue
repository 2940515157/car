<!-- - - - - - - - - - - - - -->
<!--        优惠劵汇总      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">优惠劵汇总</span>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header style="height:40px;">

                <!-- 查询输入框 -->
                <el-form :inline="true" :model="xfQuery"
                         class="demo-form-inline" size="mini" style="margin-top: 15px;">
                    <el-form-item label="优惠劵名称">
                        <el-input v-model="xfQuery.acName" style="width:130px"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="searchs">查询</el-button>
                    </el-form-item>

                    <br>
                    <el-form-item>
                        <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true">批量转赠</el-button>
                    </el-form-item>
                </el-form>

                <!-- 表格数据 -->
                <el-table
                        height="400px"
                        :data="xfData"
                        border
                        stripe
                        ref="tableData">
                    <el-table-column prop="acName" label="优惠卷名称"></el-table-column>
                    <el-table-column prop="acType" label="优惠卷类型"></el-table-column>
                    <el-table-column prop="acUsrType" label="优惠卷类型"></el-table-column>
                    <el-table-column prop="totalCount" label="总数"></el-table-column>
                    <el-table-column prop="remainder" label="剩余数量"></el-table-column>
                    <el-table-column prop="normal" label="未转赠"></el-table-column>
                    <el-table-column prop="attorn" label="转赠未使用"></el-table-column>
                    <el-table-column prop="used" label="转赠已使用"></el-table-column>
                    <el-table-column prop="expired" label="已过期"></el-table-column>
                    <el-table-column prop="startDate" label="有效期(开始)"></el-table-column>
                    <el-table-column prop="endDate" label="有效期(结算)"></el-table-column>
                    <el-table-column prop="putOnDate" label="上架时间"></el-table-column>
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


        <!-- 批量转赠的对话框 -->
        <el-dialog title="批量转赠" :visible.sync="dialogFormVisible" width="1000px">
            <el-container style="height: 550px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-container style="margin-top: -16px">
                    <el-header style="height:30px;margin-top:-20px">

                    </el-header>

                    <el-form :inline="true" :model="form"  label-width="125px"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">


                        <el-form-item label="优惠劵名称">
                            <el-select v-model="form.acUuid" placeholder="请选择" size="mini" style="width:125px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.acUuid"
                                        :label="item.acName"
                                        :value="item.acUuid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="转赠数量">
                            <el-input v-model="num" @blur="queyAcUuid" autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="danger" @click="qrff">确认发放</el-button>
                        </el-form-item>
                        <br>
                        <el-form-item label="客户名称">
                            <el-input v-model="phQuery.custName" autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>

                        <el-form-item label="手机 ">
                            <el-input v-model="phQuery.custPhone" autocomplete="off" style="width:125px"></el-input>
                        </el-form-item>


                        <el-form-item>
                            <el-button type="primary" @click="querypph">查询</el-button>
                        </el-form-item>

                        <el-tabs type="border-card">
                            <el-tab-pane label="转赠信息">
                                <el-table :data="phDate" height="300px" border size="small"
                                          @current-change="handleSelectionChange">
                                    <el-table-column label="" width="65">
                                        <template slot-scope="scope">
                                            <el-radio class="radio" v-model="templateSelection" :label="scope.$index">&nbsp;</el-radio>
                                        </template>
                                    </el-table-column>

                                    <el-table-column prop="custName" label="客户名称"></el-table-column>
                                    <el-table-column prop="custPhone" label="手机"></el-table-column>
                                    <el-table-column prop="custLevel" label=客户等级></el-table-column>
                                </el-table>

                                <el-pagination
                                        @size-change="handleSizeChange2"
                                        @current-change="handleCurrentChange2"
                                        :current-page="current2"
                                        :page-sizes="pageSizeList2"
                                        :page-size="limit2"
                                        layout="total, sizes, prev, pager, next, jumper"
                                        :total="total">
                                </el-pagination>

                            </el-tab-pane>
                        </el-tabs>
                    </el-form>

                </el-container>
            </el-container>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false" type="primary">取 消</el-button>
            </div>
        </el-dialog>

        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                xfQuery:{},
                xfData:[],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                phDate:[],
                phQuery:{},
                // 当前页号
                current2: 1,
                // 分页列表
                pageSizeList2: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit2: 5,
                // 总记录数
                total2: 0,
                // 封装表单数据的对象
                form: {},
                // 下拉框的数据
                options: [],
                num:'',
                templateSelection: '',
                templateRadio:0,


                toData:[],
                dialogFormVisible: false,
                value: '',
                input:'',
                employeeQuery: {},
                checkList: [],
            };
        },
        created() {
            this.getList()
            this.getPhList()
            this.getYhqList()
        },
        methods: {
            //批量转赠确定
            async qrff(){
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选择转赠的客户',
                    });
                    return
                }
                const _this=this
                const response=await _this.$axios.post(`/brVipcard/acct-coupon-buy-record/addList2/${_this.num}`,
                    _this.form)
                console.log(response)
                if(response.code==20000){
                    _this.$message({
                        type: 'success',
                        message: '转赠成功!'
                    })
                    _this.dialogFormVisible=false
                    _this.getList()
                }


            },
            async queyAcUuid(){
                const _this=this
                const response=await _this.$axios.post(`/brVipcard/acct-cou-pon/queryNormal/${this.form.acUuid}`)
                var num=response.data.myAcbrStatus.num;

                if(Number(_this.num)>Number(num)){
                    _this.$message({
                        message: '该优惠券数量不足，请先申请优惠券!'
                    })
                }
            },
            selectChange(val){
                alert(val)
                this.multipleTabless=val;
            },
            //点击表格某一行时触发
            async handleSelectionChange(row) {
                this.templateRadio=row.acUuid;
                this.form.custUuid=row.custUuid
                this.form.brUuid=row.brUuid
                this.form.saUuid=this.$store.getters.userInfo.id
            },
            // 获取优惠券下拉框
            async getYhqList () {
                const response = await this.$axios.post('/brVipcard/acct-cou-pon/queryAll')
                console.log(response)
                this.options = response.data.list
            },
            // 查询优惠券列表
            async getList() {
                const _this=this
                await _this.$axios.post(`/brVipcard/acct-cou-pon/queryPage2/${this.current}/${this.limit}`,
                    _this.xfQuery)
                    .then(response => {
                        this.xfData = response.data.rows
                        this.total = response.data.total
                    })
            },
            async getPhList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-cust-card/queryPage2/${this.current2}/${this.limit2}`, this.phQuery)
                this.phDate = response.data.rows
                this.total = response.data.total
            },
            searchs(){
                this.getList()
            },
            querypph(){
                this.getPhList()
            },
            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getList()
            },
            // 页大小改变事件
            handleSizeChange2 (val) {
                console.log(`每页 ${val} 条`)
                this.limit2 = val
                this.getPhList()
            },
            // 页号改变事件
            handleCurrentChange2 (val) {
                console.log(`当前页: ${val}`)
                this.current2 = val
                this.getPhList()
            },
        },


    };
</script>

<style scoped>

</style>
