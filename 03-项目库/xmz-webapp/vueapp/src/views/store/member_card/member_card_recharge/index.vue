<!-- - - - - - - - - - - - - -->
<!--        会员卡充值       -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <el-card>
            <el-dropdown>
                <i class="el-icon-tickets" style="margin-right: 10px;"></i>
            </el-dropdown>
            <span style="font-size:15px">会员卡充值记录</span>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 上边区域 -->
            <el-container>
                <el-header style="height:40px;">

                <!-- 查询输入框 -->
                <el-form :inline="true" :model="arrQuery" :label-position="right"
                         class="demo-form-inline" size="mini" style="margin-top: 15px;">
                    <el-form-item label="卡号">
                        <el-input v-model="arrQuery.cardNo" placeholder="卡号" style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="车主名称">
                        <el-input v-model="arrQuery.custName" placeholder="车主名称" style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="车主电话">
                        <el-input v-model="arrQuery.custPhone" placeholder="车主电话" style="width:130px"></el-input>
                    </el-form-item>
                    <el-form-item label="产品名称">
                        <el-input v-model="arrQuery.acsName" placeholder="产品名称" style="width:130px"></el-input>
                    </el-form-item>

                    <br>
                    <el-form-item label="充值状态">
                    <template>
                        <el-radio-group v-model="radio" @change="radios">
                            <el-radio :label="0">待确认</el-radio>
                            <el-radio :label="1">收款中</el-radio>
                            <el-radio :label="2">已收款</el-radio>
                            <el-radio :label="3">已结案</el-radio>
                        </el-radio-group>
                    </template>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="search">查询</el-button>
                    </el-form-item>

                    <br>
                    <el-form-item>
                        <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true,form= {acsName:'',salePrice:'',totalPrice:'',cardNo:''}">充值</el-button>
<!--                        <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="jiean">结案</el-button>-->
<!--                        <el-button icon="el-icon-refresh-left" size="mini"  @click="chexiaojiean">撤销结案</el-button>-->
<!--                        <el-button icon="el-icon-refresh-left" size="mini"  @click="chexiao">撤销</el-button>-->
                    </el-form-item>
                </el-form>

                <!-- 表格数据 -->
                <el-table
                        size="small"
                        height="350px"
                        ref="multipleTable"
                        :data="tableData"
                        tooltip-effect="dark"
                        style="width: 100%"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="55">
                    </el-table-column>
                    <el-table-column prop="cardNo" label="卡号"></el-table-column>
                    <el-table-column prop="custName" label="客户名称"></el-table-column>
                    <el-table-column prop="custPhone" label="手机"></el-table-column>
                    <el-table-column prop="acsName" label="产品名称"></el-table-column>
                    <el-table-column prop="status" label="充值状态"></el-table-column>
                    <el-table-column prop="rate" label="折扣"></el-table-column>
                    <el-table-column prop="trueAmt" label="实收金额"></el-table-column>
                    <el-table-column prop="amount" label="充值金额"></el-table-column>
                    <el-table-column prop="createdDate" label="充值日期"></el-table-column>
                    <el-table-column prop="closeDate" label="结案时间"></el-table-column>
                    <el-table-column prop="name" label="结案人"></el-table-column>
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


        <!-- 会员卡充值的对话框 -->
        <el-dialog title="会员卡充值" :visible.sync="dialogFormVisible" width="800px">
            <el-container style="height: 530px; border: 1px solid #eee">
                <!-- 上边区域 -->
                <el-continer style="margin-top: -16px">
                    <el-header style="height:30px;margin-top:-20px">

                    </el-header>

                    <!-- 查询输入框 -->
                    <el-form :inline="true" :model="phQuery" label-position="reight" label-width="100px"
                             class="demo-form-inline" size="mini" style="margin-top: 15px;">
                        <el-form-item label="卡号">
                            <el-popover
                                    placement="bottom"
                                    width="400"
                                    trigger="hover">
                                <el-table :data="phDate" height="400px" border @cell-click="phclick" size="small">
                                    <el-table-column prop="cardNo" label="卡号"></el-table-column>
                                    <el-table-column prop="custName" label="客户名称"></el-table-column>
                                    <el-table-column prop="custPhone" label="手机"></el-table-column>
                                    <el-table-column prop="licensePlate" label=车牌号></el-table-column>
                                </el-table>
                                <el-input slot="reference" v-model="phQuery.cardNo" placeholder="卡号" style="width:200px" @input="querykahao"></el-input>
                            </el-popover>
                        </el-form-item>

                        <el-form-item label="储值产品">
                            <el-popover
                                    placement="bottom"
                                    width="500"
                                    trigger="hover">
                                <el-table :data="acsDate" height="400px" border @cell-click="acslick" size="small">
                                    <el-table-column width="100" property="acsName" label="产品名称"></el-table-column>
                                    <el-table-column width="100" property="acsType" label="类型"></el-table-column>
                                    <el-table-column width="100" property="salePrice" label="售价"></el-table-column>
                                    <el-table-column width="100" property="totalPrice" label="总价值"></el-table-column>
                                    <el-table-column width="80" property="status" label="状态"></el-table-column>
                                </el-table>
                                <el-input slot="reference" v-model="acsQuery.acsName" placeholder="储值产品" style="width:200px"></el-input>
                            </el-popover>
                        </el-form-item>

                        <fieldset style="border:solid 1px  #C0C5CB;width: 720px">
                            <legend>客户卡信息 & 储值产品信息</legend>
                            <el-form-item label="卡号">
                                <el-input v-model="form.cardNo" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="卡余额">
                                <el-input v-model="form.balance" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="卡状态">
                                <el-input v-model="form.status" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="开卡日期">
                                <el-input v-model="form.createdDate" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户名称">
                                <el-input v-model="form.custName" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="手机">
                                <el-input v-model="form.custPhone" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="客户等级">
                                <el-input v-model="form.custLevel" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="专属SA">
                                <el-input v-model="form.saname" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="专属门店">
                                <el-input v-model="form.brName" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="产品名称">
                                <el-input v-model="form.acsName" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="售价">
                                <el-input v-model="form.salePrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="赠送价值">
                                <el-input v-model="form.zsPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="总价值">
                                <el-input v-model="form.totalPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="充值后总额">
                                <el-input v-model="form.zongPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="已收金额">
                                <el-input v-model="form.ysPrice" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>

                            <el-form-item label="收款状态">
                                <el-input v-model="form.skStatus" autocomplete="off" style="width:125px" readonly="readonly"></el-input>
                            </el-form-item>
                        </fieldset>

                        <fieldset style="border:solid 1px  #C0C5CB;width:250px">
                            <legend>录入收款金额</legend>
                            <div style="margin-left:-20px">
                                <el-form-item label="支付金额">
                                    <el-input v-model="form.zfPrice" autocomplete="off" placeholder="支付金额" style="width:120px"></el-input>
                                </el-form-item>
                                <br/>
                                <el-form-item label="待收金额">
                                    <el-input v-model="form.dsPrice" autocomplete="off" style="width:120px" readonly></el-input>
                                </el-form-item>
                            </div>
                        </fieldset>

                        <el-container style="margin-left:310px;margin-top:-140px">
                            <el-main>
                                <div style="height:150px; overflow:auto;border:solid 1px  #C0C5CB;width: 414px">

                                    <el-table :data="gridDatas">
                                        <el-table-column width="100" property="date" label="操作"></el-table-column>
                                        <el-table-column width="100" property="name" label="应收金额"></el-table-column>
                                        <el-table-column width="100" property="address" label="实收金额"></el-table-column>
                                        <el-table-column width="100" property="name" label="付款方式"></el-table-column>
                                        <el-table-column width="100" property="address" label="收款内容"></el-table-column>
                                    </el-table>

                                </div>
                            </el-main>
                        </el-container>

                    </el-form>

                </el-continer>
            </el-container>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitCZ">确 定</el-button>
            </div>
        </el-dialog>

        </el-card>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                tableData: [],
                //判断表格复选框是否选中
                multipleSelection: [],
                arrQuery:{},
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                phDate:[],
                acsDate:[],
                phQuery:{},
                acsQuery:{},
                // 封装表单数据的对象
                form: {acsName:'',salePrice:'',totalPrice:'',cardNo:''},
                radio:'',
                toData:[],
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dialogFormVisible3:false,

                value: '',
                input:'',
                employeeQuery: {},


            };
        },
        created() {
            this.getArrList()
            this.getPhList()
            this.getAcsList()
        },
        methods: {
            async getArrList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-recharge-record/queryPage/${this.current}/${this.limit}`, this.arrQuery)
                this.tableData = response.data.rows
                this.total = response.data.total
            },
            search(){
                this.getArrList()
            },
            async getPhList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-cust-card/queryPhone/`, this.phQuery)
                this.phDate = response.data.list
            },
            querykahao() {
                this.getPhList()
            },
            phclick(row){
                this.form.custUuid=row.custUuid
                this.form.cardNo=row.cardNo
                this.form.balance=row.balance
                this.form.status=row.status
                this.form.createdDate=row.createdDate
                this.form.custName=row.custName
                this.form.custPhone=row.custPhone
                this.form.custLevel=row.custLevel
                this.form.saname=row.saname
                this.form.brName=row.brName
                this.form.custCardUuid=row.custCardUuid
                this.form.brUuid=row.brUuid


            },
            async getAcsList() {
                const _this = this
                const response = await _this.$axios.post(`/brVipcard/acct-card-setup/listAcs/`, this.acsQuery)
                this.acsDate = response.data.list
            },
            acslick(row){
                this.form.acsName=row.acsName
                this.form.salePrice=row.salePrice
                this.form.totalPrice=row.totalPrice
                this.form.zsPrice=this.form.totalPrice-this.form.salePrice
                this.form.zongPrice=this.form.balance+this.form.totalPrice
                this.form.ysPrice=0.00
                this.form.skStatus='收款中'
                this.form.dsPrice=this.form.salePrice
                this.form.productUuid=row.acsUuid;
            },
            async submitCZ(){
                if(this.form.dsPrice!=this.form.zfPrice){
                    alert("支付金额输入有误！")
                }else{
                    const _this = this
                    _this.dialogFormVisible=false;
                     await _this.$axios.post(`/brVipcard/acct-recharge-record/add/`, this.form)
                        // 提示消息
                        this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        // 刷新数据表格
                        _this.getArrList()
                        _this.getPhList()

                }


            },
            radios(value){
               if(value==0){
                   this.arrQuery.status="待确认"
               }
                if(value==1){
                    this.arrQuery.status="收款中"
                }
                if(value==2){
                    this.arrQuery.status="已收款"
                }
                if(value==3){
                    this.arrQuery.status="已结案"
                }
                this.getArrList();
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            handleSelectionChange(val){
                this.multipleSelection = val;
            },
            //结案
            jiean(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        message: '提示哦，请至少选中一行',
                        type: 'warning'
                    });
                }
            },
            //撤销结案
            chexiaojiean(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        message: '提示哦，请至少选中一行',
                        type: 'warning'
                    });
                }
            },
            //撤销
            chexiao(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        message: '提示哦，请至少选中一行',
                        type: 'warning'
                    });
                }
            },
            // 页大小改变事件
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getArrList()
            },
            // 页号改变事件
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getArrList()
            },

        },


    };
</script>

<style scoped>

</style>
