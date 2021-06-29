<!-- - - - - - - - - - - - - -->
<!--       储值卡产品定义      -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <el-form :inline="true" :model="AcctCardSetQuery"  class="demo-form-inline" size="mini">
                <el-form-item label="产品名称">
                    <el-input v-model="AcctCardSetQuery.acsName" placeholder="产品名称"></el-input>
                </el-form-item>

                <el-form-item label="类型">
                    <el-select  v-model="AcctCardSetQuery.acsType" placeholder="请选择" clearable>
                        <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.label"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="状态">
                    <el-select v-model="AcctCardSetQuery.status" placeholder="请选择" clearable>
                        <el-option
                                v-for="item in options1"
                                :key="item.dictid"
                                :label="item.dictname"
                                :value="item.dictid">
                        </el-option>
                    </el-select>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="serchChaXun()">查询</el-button>
                </el-form-item>
            </el-form>
            <el-button type="primary" size="mini" icon="el-icon-plus"  @click="addCare_setup=true">增加</el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit"  @click="editCare_setup()">编辑</el-button>
            <el-button type="danger"  size="mini" icon="el-icon-plus" @click="faCare()">选择发行范围</el-button>
            <el-button type="primary" size="mini"  icon="el-icon-delete" @click="rovmeACS()">删除</el-button>
            <el-button type="primary" size="mini" icon="el-icon-upload2" @click="ShangJia()">上架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="XiaJia()">下架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" @click="TuiHui()">强制退回</el-button>

            <el-table
                    ref="multipleTable"
                    :data="tableData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    max-height="350px"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="acsName"
                        label="产品名称"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="dictname1"
                        label="类型"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="salePrice"
                        label="售价"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="giftPrice"
                        label="赠送价值"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="totalPrice"
                        label="总价值"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="settlePrice"
                        label="结算价格"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="dictname2"
                        label="状态"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="startDate"
                        label="有效期(开始)"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="endDate"
                        label="有效期(结束)"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="saleRate"
                        label="销售增值比率"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="canOwner"
                        label="专属化"
                        width="120"
                        :formatter="formatUserState">
                </el-table-column>


                <el-table-column
                        prop="name"
                        label="核算人"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="name"
                        label="设定佣金分成比率"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="cardDesc"
                        label="描述"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注"
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


            <!-- 增加或修改-->
            <el-dialog width="60%" :title="form.ascUuid == null ? '增加储值卡产品定义':'查看详情'" :visible.sync="addCare_setup" @close="DialogClosed">

                <fieldset style="border:solid 1px #aaa;padding: 1px" >
                    <legend>基本信息</legend>

                    <el-form :model="form" size="mini" :inline="true"
                             :rules="FormRules" ref="FormRef">

                        <el-form-item label="产品名称:" prop="acsName" :label-width="formLabelWidth" >
                            <el-input v-model="form.acsName" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>


                        <el-form-item label="类型:" prop="acsType" :label-width="formLabelWidth">
                        <el-select  v-model="form.acsType" placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in options"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                        </el-form-item>


                        <el-form-item label="售价:" prop="salePrice" :label-width="formLabelWidth">
                            <el-input   v-model="form.salePrice" autocomplete="off" style="width: 100px" @input="likai1()"></el-input>
                        </el-form-item>

                        <!--  第二行-->
                        <el-form-item label="赠送价值:" prop="giftPrice" :label-width="formLabelWidth">
                            <el-input  v-model="form.giftPrice" autocomplete="off" style="width: 100px" @input="likai()"></el-input>
                        </el-form-item>

                        <el-form-item label="总价值:" :label-width="formLabelWidth">
                            <el-input readonly v-model="form.totalPrice" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>

                        <el-form-item label="成本折扣率:" :label-width="formLabelWidth">
                            <el-input readonly  v-model="form.costRate" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>

                        <el-form-item label="销售增值比率:" prop="saleRate" :label-width="formLabelWidth">
                            <el-input v-model="form.saleRate" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>


                        <el-form-item label="专属化:" prop="canOwner" :label-width="formLabelWidth">
                            <el-select v-model="form.canOwner" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="有效期(开始):" prop="startDate" :label-width="formLabelWidth">
                            <el-date-picker v-model="form.startDate" style="width: 140px"
                                            type="datetime"
                                            placeholder="选择开始时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="有效期(结束):" prop="endDate">
                            <el-date-picker v-model="form.endDate" style="width: 158px"
                                            type="datetime"
                                            placeholder="选择截止时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="描述:" :label-width="formLabelWidth">
                            <el-input v-model="form.cardDesc" type="textarea" style="width: 400px"></el-input>
                        </el-form-item>

                        <el-form-item label="备注:" :label-width="formLabelWidth">
                            <el-input v-model="form.remark" type="textarea" style="width: 400px"></el-input>
                        </el-form-item>
                    </el-form>
                </fieldset>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="addCare_setup = false">取 消</el-button>
                    <el-button type="primary" @click="save()">确 定</el-button>
                </div>
            </el-dialog>

            <!--选择发行范围-->
            <el-dialog width="60%"  title="选择发行范围" :visible.sync="faCare_setup" >
                <span style="font-size:12px">储值卡产品名称:</span>{{acsName}}
                <br>
                <label>区域类型:</label>
                <el-select  size="mini" v-model="quyu.type" @change="xuanze">
                    <el-option
                            v-for="item in options4"
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
                        @selection-change="handleSelectionChange1">
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
                acsName:'',
                //验证规则
                FormRules:{
                    acsName: [
                        {required: true, message: '请输入产品名称', trigger: 'blur'},
                    ],
                    acsType: [
                        {required: true, message: '请选择类型'},
                    ],
                    salePrice: [
                        {required: true, message: '请输入售价', trigger: 'blur'},
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
                    giftPrice: [
                        {required: true, message: '请输入赠送价值', trigger: 'blur'},
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
                    saleRate: [
                        {required: true, message: '请输入销售增值比率', trigger: 'blur'},
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
                    canOwner: [
                        {required: true, message: '请选择专属化'},
                    ],
                    startDate: [
                        {required: true, message: '请选择开始时间'},
                    ],
                    endDate: [
                        {required: true, message: '请选择结束时间'},
                    ],
                },
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
                value: [],
                filterMethod(query, item) {
                    return item.pinyin.indexOf(query) > -1;
                },
                //区域
                quyu:{
                    type: "1"
                },
                //类型
                options: [{
                    id: 'suremoov', label: '小拇指卡'
                }],
                //专属化
                options2: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}],
                //选择发行范围
                options4: [
                    {id: '1', label: '大区'},
                    {id: '2', label: '门店'}],
                //状态
                options1: [],

                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //选择发行范围  大区部门分页
                pageSizeList1: [2, 5, 100, 200, 300, 400],
                current1: 1,
                limit1: 5,
                total1: 0,
                //选择发行范围  门店分页
                current2: 1,
                limit2: 5,
                total2: 0,
                //门店名称
                brName:'',
                toms:{},
                //储值卡产品定义
                addCare_setup:false,
                //选择发行范围
                faCare_setup:false,
                // 封装表单数据的对象
                form: {},
                forms: {},
                // 表单元素的label的宽度
                formLabelWidth: '191px',
                //储值卡产品定义的数据
                tableData: [],
                //储值卡产品的查询条件
                AcctCardSetQuery:{},
                //选中的数据
                SelectionDatas:[],
                //选中的发行范围区域类型
                SelectionData1:[],
                //
                SelectionData2:[],

                //选中的id
                selectIds:[],
                selectId2:[],
                //选中的状态
                selectStuts:[],
                //当前时间
                currentTime:'',
                //发行范围的数据
                FaXingData:[],
                tableData5:[],
            }
        },
        created() {
            this.getAcctCardSetupList();
            this.mounted();
            //卡状态
            this.getStutaList();
        },
        methods:{
          //储值卡状态
          async getStutaList(){
            const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_PRODUCT_STATUS`)
            this.options1= result.data
          },

            mounted(){
                this.currentTime=this.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            },
            //判断是否专属化
            formatUserState: function (row, column, cellValue) {
                if (cellValue === 'N') {
                    return '否';
                } else if (cellValue === 'Y') {
                    return '是';
                }
            },
            //分页条件查询储值卡产品定义
            getAcctCardSetupList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-card-setup/queryAllAcct/${this.current}/${this.limit}`,_this.AcctCardSetQuery).then(result=>{
                    _this.tableData=result.data.rows;
                    _this.total=result.data.total;
                })
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
            //选择发行范围 选择下拉框发生变化
            xuanze(){
                if(this.quyu.type==='1'){
                    this.getHrOrgList();
                    return;
                }
                this.getHrBList();
            },
            //查询卡的区域
            getquyu(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-card-area/queryAllPage/${this.current2}/${this.limit2}/${this.SelectionDatas[0].acsUuid}`).then(result=>{
                    _this.tableData5=result.data.rows
                    _this.total2=result.data.total;
                })
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
                 this.faCare_setup=true;
                //查询卡的区域
                 this.getquyu();

                 if(this.quyu.type==='1'){
                  this.getHrOrgList();
                  return;
                 }
                this.getHrBList();



            },
            //选择
            xuanzefa(){
                if(this.quyu.type==='1'){
                    console.log("TRUE")
                   const _this = this
                    for(var j=0;j<this.SelectionData1.length;j++) {
                        _this.forms.acsUuid = this.SelectionDatas[0].acsUuid;
                        _this.forms.areaUuid = this.SelectionData1[j].orgUuid;
                        _this.forms.type = 'org';
                        _this.forms.isActive = 'Y';
                        _this.forms.version = 0;
                        _this.$axios.post('/serviceCard/acct-card-area/add', _this.forms).then(result => {
                            if(result.code === 20000) {
                                //刷新
                                this.getquyu();
                            }
                        })
                    }
                }else {
                    const _this = this
                    for (var i = 0; i < this.SelectionData1.length; i++) {
                        console.log(i)
                        _this.forms.acsUuid = this.SelectionDatas[0].acsUuid;
                        _this.forms.areaUuid = this.SelectionData1[i].brUuid
                        _this.forms.type = 'br'
                        _this.forms.isActive = 'Y';
                        _this.forms.version = 0;
                        console.log(_this.forms)
                        _this.$axios.post('/serviceCard/acct-card-area/add', _this.forms).then(result => {
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
                        _this.$axios.delete(`/serviceCard/acct-card-area/delete/${this.selectId2[i]}`).then(result=>{
                            if(result.code === 20000) {
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
            //选中改变事件
            handleSelectionChange(val){

                this.SelectionDatas=val;
                this.acsName=this.SelectionDatas[0].acsName
                this.selectIds=[]
                for(var i=0;i<val.length;i++){
                    this.selectIds.push(val[i].acsUuid);
                    this.selectStuts.push(val[i].dictname2)
                }
            },
            //选择发行范围 选中改变事件
            handleSelectionChange1(val){
                this.SelectionData2=val;
                this.selectId2=[]
                for(var i=0;i<val.length;i++){
                    this.selectId2.push(val[i].cardAreaUuid);
                }
            },
            //选择发行范围 选中改变事件
            handleSelectionChange2(val){
                console.log(val)
                this.SelectionData1=val;
            },
            //删除
            rovmeACS(){
                if(this.SelectionDatas.length==0){
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
                    for(var i=0;i<this.selectIds.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceCard/acct-card-setup/delete/${this.selectIds[i]}`).then(result=>{
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getAcctCardSetupList();
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
                if(this.SelectionDatas[0].dictname2==='上架'||this.SelectionDatas[0].endDate<this.currentTime){
                    this.$message({
                        type:'info',
                        message:'只有非上架产品且在有效时间内的产品才能上架'
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

                this.form=this.SelectionDatas[0];
                 const _this = this
                _this.$axios.post('/serviceCard/acct-card-setup/update1',_this.form).then(result=>{
                    if(result.code===20000){
                        this.$message({
                            type: 'success',
                            message: '上架成功!'
                        });
                        //刷新
                        this.getAcctCardSetupList();
                    }

                })

            },

            //下架
            XiaJia(){
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

                if(this.SelectionDatas[0].dictname2==='下架'||this.SelectionDatas[0].dictname2==='未上架'){
                    this.$message({
                        type:'info',
                        message:'只有上架产品才能下架'
                    })
                    return
                }

                this.form=this.SelectionDatas[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-card-setup/update2',_this.form).then(result=>{
                    console.log(result.data);
                    this.$message({
                        type: 'success',
                        message: '下架成功!'
                    });
                    //刷新
                    this.getAcctCardSetupList();
                })
            },

            //强制退回
            TuiHui(){
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

                if(this.SelectionDatas[0].dictname2==='下架'||this.SelectionDatas[0].dictname2==='上架'){
                        return;
                }else {
                    this.$message({
                        type:'info',
                        message:'未上架的储值卡无须强制退回'
                    })
                }

                this.form=this.SelectionDatas[0];
                const _this = this
                _this.$axios.post('/serviceCard/acct-card-setup/update3',_this.form).then(result=>{
                    console.log(result.data);
                    this.$message({
                        type: 'success',
                        message: '强制退回成功!'
                    });
                    //刷新
                    this.getAcctCardSetupList();
                })
            },

            //打开编辑
            editCare_setup(){
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
                _this.$axios.post(`/serviceCard/acct-card-setup/queryById/${this.SelectionDatas[0].acsUuid}`).then(result=>{
                    console.log("修改"+result.data.acctCardSetup)
                    this.form=result.data.acctCardSetup;
                    //打开修改对话框
                    this.addCare_setup=true;
                })


            },

            //计算总价值
            likai1(){
             if(this.form.giftPrice!=null){
               this.likai();
             }

            },
            //计算总价值
            likai(){
                //总价值=售价+赠送价值

               this.form.totalPrice=(parseFloat(this.form.salePrice)+parseFloat(this.form.giftPrice));

                //成本折扣率=售价除以总价
                this.form.costRate=(parseFloat(this.form.salePrice)/parseFloat(this.form.totalPrice)).toFixed(2);
             },
            //添加或修改
            save(){
                this.$refs.FormRef.validate(valid => {
                    if (!valid) return;

                  if(this.form.endDate<this.form.startDate){
                    this.$message({
                      message: '警告哦，"您的开始时间小于结束时间！"',
                      type: 'warning'
                    });
                    return
                  }
                    //添加完关闭对话框
                    this.addCare_setup = false;
                    if (this.form.ascUuid == null) {

                        //创建日期 = 当前日期
                        this.form.createdDate = this.currentTime;
                        //核算人UUID
                        this.form.checkUuid = '0d0dbf129e998d796b50ccbca1aa5358';
                        //设定佣金分成比率人UUID
                        this.form.setCrUuid = '0d0dbf129e998d796b50ccbca1aa5358';
                        //版本
                        this.form.version = 0;
                        //结算价格  =  售价减去赠送价格
                        this.form.settlePrice = (this.form.salePrice - this.form.giftPrice);
                        //发行折扣率
                        this.form.issueRate = 1;
                        //协议折扣率(有协议折扣率就用它，没有就是成本折扣率乘以发行折扣率)
                        this.form.pactRate = this.form.issueRate * this.form.costRate
                        //创建人
                        this.form.createdBy = '创建人';

                        const _this = this
                        _this.$axios.post('/serviceCard/acct-card-setup/add', _this.form).then(result => {
                            if (result.code === 20000) {
                                this.$message({
                                    type: 'success',
                                    message: '添加成功!',
                                });
                            }
                            //刷新
                            this.getAcctCardSetupList();
                            this.form={};
                        })
                    } else if (this.form.acsUuid != null) {
                        //修改日期 = 当前日期
                        this.form.updatedDate = this.currentTime;
                        //核算人UUID
                        this.form.checkUuid = '0d0dbf129e998d796b50ccbca1aa5358';
                        //设定佣金分成比率人UUID
                        this.form.setCrUuid = '0d0dbf129e998d796b50ccbca1aa5358';
                        //版本
                        this.form.version = this.form.version + Number(1);
                        //结算价格  =  售价减去赠送价格
                        this.form.settlePrice = (this.form.salePrice - this.form.giftPrice);
                        //修改人
                        this.form.createdBy = '修改人';

                        const _this = this
                        _this.$axios.post('/serviceCard/acct-card-setup/update', _this.form).then(result => {
                            if (result.code === 20000) {
                                this.$message({
                                    type: 'success',
                                    message: '修改成功!',
                                });
                            }
                            //刷新
                            this.getAcctCardSetupList();
                            this.form={};
                        })

                    }
                })
            },

            DialogClosed(){
                this.$refs.FormRef.resetFields();
            },
            //点击查询按钮查询
            serchChaXun(){
                this.getAcctCardSetupList();
            },
            // 页大小改变事件
            handleSizeChange1 (val) {
                this.limit2 = val

            },
            // 页号改变事件
            handleCurrentChange1 (val) {
                this.current2 = val
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
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getAcctCardSetupList();
            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getAcctCardSetupList();
            }
        }
    }
</script>

<style scoped>

    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>