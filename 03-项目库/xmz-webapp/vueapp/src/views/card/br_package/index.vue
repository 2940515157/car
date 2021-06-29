<!-- - - - - - - - - - - - - -->
<!--        门店套餐情况       -->
<!-- - - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <el-form :inline="true" :model="brPackageQuery" class="demo-form-inline" size="mini">
                <div style="margin-left: -90px">
                    <el-form-item label="大区" :label-width="formLabelWidth">
                        <el-select v-model="brPackageQuery.orgUuid"  placeholder="请选择" style="width: 110px">
                            <el-option value="" label="---请选择---"></el-option>
                        <el-option
                                v-for="item in YingYunData"
                                :key="item.orgUuid"
                                :label="item.orgName"
                                :value="item.orgUuid">
                        </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="区域" :label-width="formLabelWidth">
                        <el-select v-model="brPackageQuery.areaUuid"  placeholder="请选择" style="width: 110px">
                        <el-option value="" label="---请选择---"></el-option>
                        <el-option
                                v-for="item in QuYuData"
                                :key="item.areaUuid"
                                :label="item.areaName"
                                :value="item.areaUuid">
                        </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="门店" :label-width="formLabelWidth">
                        <el-select v-model="brPackageQuery.brUuid"  placeholder="请选择" style="width: 110px">
                            <el-option value="" label="---请选择---"></el-option>
                            <el-option
                                    v-for="item in MenDianData"
                                    :key="item.brUuid"
                                    :label="item.brName"
                                    :value="item.brUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>


                    <el-form-item label="套餐模板" :label-width="formLabelWidth">
                        <el-select  placeholder="请选择" style="width: 110px">
                        </el-select>
                    </el-form-item>

                    <el-form-item label="服务类型" :label-width="formLabelWidth">
                        <el-select v-model="brPackageQuery.serviceType" placeholder="请选择" clearable style="width: 110px">
                            <el-option value="" label="---请选择---"></el-option>
                            <el-option v-for="dn3 in dictname3" :key="dn3.dn3Name" :value="dn3.dn3Name" :label="dn3.dn3Name" ></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="套餐名称" :label-width="formLabelWidth">
                        <el-input v-model="brPackageQuery.apName"  placeholder="套餐名称" style="width: 110px"></el-input>
                    </el-form-item>

                    <el-form-item label="套餐类型" :label-width="formLabelWidth">
                        <el-select v-model="brPackageQuery.apType"  placeholder="请选择" clearable style="width: 110px">
                            <el-option value="" label="---请选择---"></el-option>
                            <el-option v-for="dn2 in dictname2" :key="dn2.dn2Name" :value="dn2.dn2Name" :label="dn2.dn2Name" ></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="状态" :label-width="formLabelWidth">
                        <el-select v-model="brPackageQuery.status" placeholder="请选择" clearable style="width: 110px">
                            <el-option value="" label="---请选择---"></el-option>
                            <el-option v-for="dn4 in dictname4" :key="dn4.dn4Name" :value="dn4.dn4Name" :label="dn4.dn4Name" ></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item >
                        <el-button type="primary" @click="search">查询</el-button>
                    </el-form-item>
                </div>
            </el-form>


            <el-button type="primary"  size="mini" icon="el-icon-plus" @click="look()">查看</el-button>
            <el-button type="danger" size="mini"  icon="el-icon-delete" @click="updateUp">上架</el-button>
            <el-button type="danger" size="mini"  icon="el-icon-delete" @click="updateUnder">下架</el-button>


            <el-table
                    size="mini"
                    ref="multipleTable"
                    :data="tableData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    max-height="300px"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="brName"
                        label="门店"
                        width="300">
                </el-table-column>

                <el-table-column
                        prop="apName"
                        label="套餐名称"
                        width="171">
                </el-table-column>

                <el-table-column
                        prop="dictname2"
                        label="套餐类型"
                        width="100">
                </el-table-column>

                <el-table-column
                        prop="totalPrice"
                        label="套餐售价"
                        width="100">
                </el-table-column>

                <el-table-column
                        prop="dictname4"
                        label="状态"
                        width="100">
                </el-table-column>

                <el-table-column
                        prop="putOnDate"
                        label="上架时间"
                        width="100">
                </el-table-column>

                <el-table-column
                        prop="pullOffDate"
                        label="下架时间"
                        width="100">
                </el-table-column>

              <!--  <el-table-column
                        prop="apmUuid"
                        label="套餐模板名称"
                        width="120">
                </el-table-column>-->

                <el-table-column
                        prop="dictname3"
                        label="服务类型"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="saleRate"
                        label="销售增值比率"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="discountRate"
                        label="折扣率">
                </el-table-column>

                <el-table-column
                        prop="canOwner"
                        label="专属化"
                        :formatter="formatUserState">
                </el-table-column>

                <el-table-column
                        prop="validMonth"
                        label="使用期限(月)">
                </el-table-column>

                <el-table-column
                        prop="startDate"
                        label="开始时间">
                </el-table-column>

                <el-table-column
                        prop="endDate"
                        label="结束时间">
                </el-table-column>

                <el-table-column
                        prop="createdBy"
                        label="创建人">
                </el-table-column>

                <el-table-column
                        prop="dictname1"
                        label="业务分类">
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注">
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
        </el-card>


        <el-dialog title="查看套餐" :visible.sync="dialogFormVisible" width="60%">
            <div style="width:100%;height:400px;overflow:auto">
                <fieldset>
                    <legend>门店套餐</legend>
                    <el-form :model="form" style="padding:0">
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <el-form-item label="套餐名称：" :size='s'>
                                    {{form.apName}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="服务类型：" :size='s'>
                                    {{form.dictname3}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="套餐类型：" :size='s'>
                                    {{form.dictname2}}
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <el-form-item label="套餐售价：" :size='s'>
                                    {{form.totalPrice}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="折扣率：" :size='s'>
                                    {{form.discountRate}}
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <el-form-item label="开始时间：" :size='s'>
                                    {{form.startDate}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="结束时间：" :size='s'>
                                    {{form.endDate}}
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row :gutter="20">
                            <el-col :span="8">
                                <el-form-item label="业务分类：" :size='s'>
                                    {{form.dictname1}}
                                </el-form-item>
                            </el-col>
                            <el-col :span="8">
                                <el-form-item label="使用期限（月）：" :size='s'>
                                    {{form.validMonth}}
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-form-item label="备注:" :size='s'>
                            {{form.remark}}
                        </el-form-item>
                    </el-form>
                </fieldset>
                <br>
                <template>
                    <el-tabs v-model="activeName"  type="card">
                        <el-tab-pane label="服务项目" name="first">
                            <el-table
                                    stripe
                                    :data="acctpackagedetail"
                            >
                                <el-table-column
                                        type="index"
                                        :index="indexMethod"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="服务项目"
                                        prop="catalogName"
                                        align="center"
                                        width="200">
                                </el-table-column>
                                <el-table-column
                                        label="服务类型"
                                        prop="servTypeName"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="次数"
                                        prop="apdNumber"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="单次套餐服务销售价格"
                                        prop="salePrice"
                                        align="center"
                                        width="170">
                                </el-table-column>
                                <el-table-column
                                        label="标准服务价格"
                                        prop="stdPrice"
                                        align="center">
                                </el-table-column>
                            </el-table>

                        </el-tab-pane>
                        <el-tab-pane label="配件" name="second">
                            <el-table
                                    stripe
                                    :data="acctpackagepartsdetail"
                            >
                                <el-table-column
                                        type="index"
                                        :index="indexMethod"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="配件目录"
                                        prop="name"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="商品名称"
                                        prop="showName"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="总数量"
                                        prop="appNumber"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="单位"
                                        prop="dictname1"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="单次套餐服务销售价格"
                                        prop="salePrice"
                                        width="170"
                                        align="center">
                                </el-table-column>
                                <el-table-column
                                        label="服务标准报价"
                                        prop="stdPrice"
                                        align="center">
                                </el-table-column>
                            </el-table>
                        </el-tab-pane>
                    </el-tabs>
                </template>

                <span style="float:right;margin-right:20px">服务总计：{{serviceTotal}}&emsp;配件总计：{{partsTotal}}&emsp;合计：{{serviceTotal+partsTotal}}</span>
            </div>


            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">关 闭</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            return{
                tableData:[],
                YingYunData:[],
                QuYuData:[],
                quyuData:{},
                MenDianData:[],
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 封装表单数据的对象

                // 表单元素的label的宽度
                formLabelWidth: '140px',
                //门店套餐信息
                brpackage:[],
                //查询参数
                brPackageQuery:{},
                //服务类型
                dictname3:[
                    {dn3Name:'机修'},
                    {dn3Name:'油漆'},
                    {dn3Name:'美容'},
                    {dn3Name:'综合'}
                ],
                //套餐类型
                dictname2:[
                    {dn2Name:'服务套餐'},
                    {dn2Name:'服务配件套餐'}
                ],
                //状态
                dictname4:[
                    {dn4Name:'未上架'},
                    {dn4Name:'上架'},
                    {dn4Name:'下架'}
                ],
                //查询门店
                hrbranches:[],
                //页码
                current:1,
                //分页列表
                pageSizes:[50,100,150,200],
                //页大小
                size:50,
                //总记录数
                total:0,
                //查询框大小
                s:'mini',
                //选择的数据
                selectData:[],
                //选中的数据id
                selectid:[],
                //是否开启对话框
                dialogFormVisible:false,
                form:{},
                //标签页默认打开哪个
                activeName: 'first',
                //套餐服务
                acctpackagedetail:[],
                //套餐配件
                acctpackagepartsdetail:[],
                //服务总计
                serviceTotal:0,
                //配件总计
                partsTotal:0,
                orgUuid:'',
                areaUuid:'',

            }
        },
        created() {
            this.getMenDianList();
            //查询所有营运中心的部门
            this.getYingYun();
            //根据大区查出所有的区域
            this.getQuYuList();
            //根据大区和区域查询出门店
            this.getMenDiansList();
        },
        methods:{
            //判断是否组合产品
            formatUserState: function (row, column, cellValue) {
                if (cellValue === 'N') {
                    return '否';
                } else if (cellValue === 'Y') {
                    return '是';
                }
            },
            //修改下架
            updateUnder(){
                if(this.selectData.length===0){
                    this.$message({
                        type:'info',
                        message:'请选中一条数据!'
                    })
                    return
                }

                for(var i=0;i<this.selectData.length;i++){
                    if(this.selectData[i].status==='init'){
                        this.$message({
                            type:'info',
                            message:'产品还未上架!'
                        })
                        return
                    }
                    if(this.selectData[i].status==='under'){
                        this.$message({
                            type:'info',
                            message:'产品已下架!'
                        })
                        return
                    }
                }

                this.$confirm('此操作将下架门店套餐, 是否继续?','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).then(()=>{

                    for(var i=0;i<this.selectid.length;i++){
                        const _this = this
                        _this.$axios.post(`/serviceCard/brpackage/updates/under`,this.selectData[i]).then(result=>{
                            if (result.code === 20000) {
                                //提示信息
                                this.$message({
                                    type: 'success',
                                    message: '下架成功!'
                                })
                                //回到列表页面
                                this.getMenDianList()
                            }
                        })

                    }

                }).catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消下架'
                    })
                })
            },
            //修改上架
            updateUp(){
                if(this.selectData.length===0){
                    this.$message({
                        type:'info',
                        message:'请选中一条数据!'
                    })
                    return
                }
                for(var i=0;i<this.selectData.length;i++){
                    if(this.selectData[i].status==='up'){
                        this.$message({
                            type:'info',
                            message:'产品已上架!'
                        })
                        return
                    }

                    var t=new Date();
                    var time=t.getFullYear()+"-0"+(t.getMonth()+1)+"-"+t.getDate()+" "
                        +t.getHours()+":"+t.getMinutes()+":"+t.getSeconds();

                    if(this.selectData[i].endDate<=time){
                        this.$message({
                            type:'info',
                            message:'有效期内的产品才能上架!'
                        })
                        return
                    }
                }

                this.$confirm('此操作将上架门店套餐, 是否继续?','提示',{
                    confirmButtonText:'确定',
                    cancelButtonText:'取消',
                    type:'warning'
                }).then(()=>{
                    for(var i=0;i<this.selectid.length;i++){
                        const _this = this
                        _this.$axios.post(`/serviceCard/brpackage/update/up`,this.selectData[i]).then(result=>{
                            if (result.code === 20000) {
                               this.$message({
                                   type:'success',
                                   message:'上架成功!'
                               })
                               //回到列表页面
                               this.getMenDianList()
                           }
                        })
                    }

                }).catch(()=>{
                    this.$message({
                        type:'info',
                        message:'已取消上架'
                    })
                })
            },

            //套餐服务
            getAcctPackageDetail(apUuid){
                const _this = this
                _this.$axios.get(`/serviceCard/acct-package-detail/getApUuid/${apUuid}`).then(result=>{
                    this.acctpackagedetail=result.data.acctPackageDetail

                    for(var i=0;i<this.acctpackagedetail.length;i++){
                        this.serviceTotal+=this.acctpackagedetail[i].salePrice*this.acctpackagedetail[i].apdNumber
                    }

                })
            },
            //套餐配件
            getAcctPackagePartsDetail(apUuid){
                const _this = this
                _this.$axios.get(`/serviceCard/acct-package-parts-detail/getApUuid/${apUuid}`).then(result=>{
                    this.acctpackagepartsdetail=result.data.acctPackagePartsDetails

                    for(var i=0;i<this.acctpackagepartsdetail.length;i++){
                        this.partsTotal+=this.acctpackagepartsdetail[i].salePrice*this.acctpackagepartsdetail[i].appNumber
                    }

                })

            },
            //门店套餐列表查询的方法
            getMenDianList(){
                const _this = this
                _this.$axios.post(`/serviceCard/brpackage/queryAll/${this.current}/${this.limit}`,_this.brPackageQuery).then(result=>{
                    _this.tableData=result.data.rows;
                    _this.total=result.data.total;
                })
            },
            //查询按钮
            search(){
                this.getMenDianList()
            },
            //点击看错
            look(){
                if(this.selectData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selectData.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                const _this = this
                _this.$axios.get(`/serviceCard/brpackage/getByBrPackageUuid/${this.selectData[0].brPackageUuid}`).then(result=>{
                    this.dialogFormVisible=true

                    this.form=result.data.brPackage

                    this.serviceTotal=0
                    this.partsTotal=0

                })
                //打开查看的对话框
                this.SerachMT=true;

            },
            //大区改变时间
            change1(val){
                this.orgUuid=val
                this.getQuYuList();

            },
            change2(val){
                this.areaUuid=val;
                this.getOrgUuidList();
                this.getMenDiansList();
            },
            //查询所有营运中心的部门
            getYingYun(){
                const _this = this
                _this.$axios.post(`/serviceHr/HrOrg/queryAllYingYun/`,{'orgUuid':this.orgUuid}).then(result=>{
                    _this.YingYunData=result.data.list;
                })
            },

            //根据大区查出部门id
            getOrgUuidList(){
                const _this = this
                _this.$axios.post(`/serviceHr/hrArea/queryAllOrgUuid/`,{'areaUuid':_this.areaUuid}).then(result=>{
                    this.brPackageQuery.orgUuid=result.data.list.orgUuid
                    //查询所有营运中心的部门
                    this.getYingYun();
                    //根据大区查出所有的区域
                    this.getQuYuList();
                })
            },
            //根据大区查出所有的区域
            getQuYuList(){
                const _this = this
                this.quyuData.orgUuid=this.orgUuid;
                this.$axios.post(`/serviceHr/hrArea/queryAllHrAreas/`,this.quyuData).then(result=>{
                    if(result.code===20000){
                        _this.QuYuData=result.data.list;
                    }
                })
            },

            //根据大区和区域查询出门店
            getMenDiansList(){
                const _this = this
                this.brPackageQuery.orgUuid=this.orgUuid;
                this.brPackageQuery.areaUuid=this.areaUuid;

                _this.$axios.post(`/serviceHr/hr-branches/queryAllMenDian/`,this.brPackageQuery).then(result=>{
                    if(result.code===20000){
                        _this.MenDianData=result.data.list
                    }
                })
            },
            //选中改变事件
            handleSelectionChange(val){
                this.selectData=val
                this.selectid=[]
                for(var i=0;i<val.length;i++){
                    this.selectid.push(val[i].brPackageUuid);
                }
            },
            handleClick(tab, event) {
                console.log(tab, event);
            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getMenDianList();
            },
            indexMethod(index) {
                return index+1;
            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getMenDianList();
            }
        }
    }
</script>

<style scoped>



</style>