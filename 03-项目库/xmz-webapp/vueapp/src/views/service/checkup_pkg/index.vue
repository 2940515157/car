<!-- - - - - - - - - - - -->
<!--        点检包        -->
<!-- - - - - - - - - - - -->


<template>

    <div>

        <el-container style="height: 550px; border: 1px solid #eee">


            <!-- 上边区域 -->
            <el-container style="margin-top: -16px">
                <el-header style="height:40px">

                <!-- 查询输入框 -->
                <el-form :inline="true" :model="scpQuery" class="demo-form-inline" size="mini" style="margin-top: 25px;">

                    <el-form-item label="点检包名称" >
                        <el-input v-model="scpQuery.pkgName" placeholder="点检包名称" clearable @clear="getScpList"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="search">查询</el-button>
                    </el-form-item>

                    <el-form-item>
                        <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true,form = {}">添加</el-button>
                        <el-button  icon="el-icon-edit" size="mini" @click="update">编辑</el-button>
                        <el-button icon="el-icon-top" size="mini" @click="shangjia">上架</el-button>
                        <el-button icon="el-icon-bottom" size="mini" @click="xiajia">下架</el-button>
                        <el-button icon="el-icon-thumb" size="mini" @click="peizhi">配置点检包相关项目</el-button>
                    </el-form-item>
                </el-form>

                <!-- 表格数据 -->
                <el-table
                        height="400px"
                        :data="tableData"
                        tooltip-effect="dark"
                        style="width: 100%"
                        @row-click="singleElection"
                        @current-change="handleSelectionChange">
                    <el-table-column label="" width="65">
                        <template slot-scope="scope">
                            <el-radio class="radio" v-model="templateSelection" :label="scope.$index">&nbsp;</el-radio>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="pkgName"
                            label="点检包名称"
                            width="200">
                        <template slot-scope="scope">
                            <a @click="handleEdit(scope.$index, scope.row)" style="color:blue;cursor:pointer;border-bottom: blue solid 1px">{{scope.row.pkgName}}</a>
                        </template>

                    </el-table-column>
                    <el-table-column
                            prop="status"
                            label="点检包状态"
                            width="200">
                        <template slot-scope="scope">
                            <span v-if="scope.row.status=='init'">未上架</span>
                            <span v-if="scope.row.status=='confirm'">上架</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="description"
                            label="点检包简介"
                            width="200">
                    </el-table-column>
                    <el-table-column
                            prop="matchMileageMin"
                            label="匹配里程小值"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="matchMileageMax"
                            label="匹配里程大值"
                            width="120">
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

        <el-dialog :title="this.pkgName" :visible.sync="dialogFormVisible4" width="300px">
            <!--根据点检包名称查询数据表格-->
            <el-table
                    height="300px"
                    :data="pNameData"
                    tooltip-effect="dark"
                    style="width: 100%">
                <el-table-column
                        prop="catalogName"
                        label="点检项目"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="directoryName"
                        label="所属目录"
                        width="120">
                </el-table-column>
            </el-table>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible4 = false">关闭</el-button>
            </div>
        </el-dialog>

        <!--新增或者修改的对话框-->
        <el-dialog :title="form.scpUuid == null ? '增加点检项目':'编辑点检项目'" :visible.sync="dialogFormVisible">
            <el-card class="box-card">
                <el-form :model="form" size="mini" :inline="true">
                    <el-form-item label="点检包名称">
                        <el-input v-model="form.pkgName" autocomplete="off" style="width:200px"></el-input>
                    </el-form-item>

                    <el-form-item label="点检包简介">
                        <el-input v-model="form.description" autocomplete="off" style="width:200px"></el-input>
                    </el-form-item>

                    <el-form-item label="匹配里程(公里)">
                        <el-input size="mini" style="width:200px" v-model="form.matchMileageMin"></el-input>至
                        <el-input size="mini" style="width:200px" v-model="form.matchMileageMax"></el-input>
                    </el-form-item>


                    <el-card>
                        <el-button type="primary" size="mini"  icon="el-icon-delete" @click="addInputer()">删除</el-button>
                        <el-table
                                ref="multipleTables"
                                :data="tableData1"
                                tooltip-effect="dark"
                                style="width: 100%"
                                max-height="250"
                                @selection-change="selectChange"
                                border
                                @cell-dblclick="celledit">
                            <el-table-column label="序号" width="50">
                                <template slot-scope="scope">
                                    {{scope.row.index=scope.$index+1}}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    type="selection"
                                    width="55">
                            </el-table-column>
                            <el-table-column
                                    label="点检项目"
                                    width="120">
                                <template slot-scope="scope">
                                    <el-input  size="mini" v-model="scope.row.catalogName" v-if="scope.row.flag" @blur="inputClick(scope.row)" v-focus readonly="readonly"></el-input>
                                    <span v-if="!scope.row.flag">{{scope.row.catalogName}}</span>
                                </template>
                            </el-table-column>

                            <el-table-column
                                    label="所属系统"
                                    width="120">
<!--                                <template slot-scope="scope">-->
<!--                                    <el-input type="text" >{{ scope.row.directoryName }}</el-input>-->
<!--                                </template>-->
                                <template slot-scope="scope">
                                    <el-input  size="mini" v-model="scope.row.directoryName" v-if="scope.row.flag"  v-focus readonly="readonly"></el-input>
                                    <span v-if="!scope.row.flag">{{scope.row.directoryName}}</span>
                                </template>
                            </el-table-column>

                        </el-table>
                    </el-card>


                </el-form>

            </el-card>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addPkg">确 定</el-button>
            </div>
        </el-dialog>


        <!-- 配置点检包相关项目的对话框 -->
        <el-dialog title="编辑点检包与服务项目对应关系" :visible.sync="dialogFormVisible2">

            <el-card class="box-card">
                <el-form :inline="true" :model="employeeQuery" class="demo-form-inline" size="mini" style="margin-top: 15px;">
                    <el-form-item label="点检包名称：aaaa">
                        <!-- <el-input v-model="employeeQuery.lastName" placeholder="套餐标签"></el-input> -->
                    </el-form-item>

                    <!-- 查询输入框 -->
                    <el-form-item label="服务项目名称" label-width="150px">
                        <el-input v-model="employeeQuery.lastName" placeholder="套餐标签"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary">查询</el-button>
                    </el-form-item>

                    <el-form-item>
                        <el-transfer v-model="value" :from_data='fromData'></el-transfer>
                    </el-form-item>

                </el-form>

            </el-card>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
            </div>
        </el-dialog>


        <!-- 配增加点检项目的对话框 -->
        <el-dialog  title="添加点检项目" :visible.sync="dialogFormVisible3" width="1000px" style="margin-left: 500px">

            <el-container style="height: 400px; border: 1px solid #eee">
                <!-- 左边区域 -->
                <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                    <h4>点检目录</h4>
                    <el-tree
                            :data="data"
                            highlight-current
                            default-expand-all
                            :expand-on-click-node="false"
                            @node-click="treeSearch"
                            ref="tree">
                    </el-tree>
                </el-aside>

                <!-- 上边区域 -->
                <el-container>
                    <el-header style="height:56px">
                    <!-- 查询输入框 -->
                    <el-form :inline="true" :model="sccQuery" class="demo-form-inline" size="mini" style="margin-top: 15px;">

                        <el-form-item label="项目名称">
                            <el-input v-model="sccQuery.catalogName" placeholder="项目名称" clearable @clear="getSccList"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="search2">查询</el-button>
                        </el-form-item>
                    </el-form>


                        <!-- 表格数据 -->
                        <el-table
                                height="300px"
                                :data="tableData2"
                                tooltip-effect="dark"
                                style="width: 100%"
                                @cell-dblclick="celledit1">
                            <el-table-column
                                    type="selection"
                                    width="55">
                            </el-table-column>

                            <el-table-column
                                    prop="catalogName"
                                    label="项目名称"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    prop="directoryName"
                                    label="项目所属目录"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="type"
                                    label="类型"
                                    show-overflow-tooltip>
                                <template slot-scope="scope">
                                    <span v-if="scope.row.type=='normal'">正常</span>
                                    <span v-if="scope.row.type=='slight'">轻微异常</span>
                                    <span v-if="scope.row.type=='more'">严重异常</span>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    prop="description"
                                    label="异常描述"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="remindStartTime"
                                    label="提醒产生时间(天)"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="aginCheckupTime"
                                    label="再次点检时间(天)"
                                    show-overflow-tooltip>
                            </el-table-column>
                            <el-table-column
                                    prop="isSetMile"
                                    label="剩余里程/时间"
                                    show-overflow-tooltip>
                                <template slot-scope="scope">
                                    <span v-if="scope.row.isSetMile=='Y'">是</span>
                                    <span v-if="scope.row.isSetMile=='N'">否</span>
                                </template>
                            </el-table-column>

                        </el-table>
                        <!-- 分页 -->
                        <el-pagination
                                @size-change="handleSizeChange2"
                                @current-change="handleCurrentChange2"
                                :current-page="current2"
                                :page-sizes="pageSizeList2"
                                :page-size="limit2"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total2">
                        </el-pagination>

                    </el-header>
                </el-container>
            </el-container>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                <el-button type="primary">确 定</el-button>
            </div>
        </el-dialog>

    </div>


</template>

<script>
    export default {
        created () {
            this.getScpList()
            this.getSccList()
            this.getTreeList()
        },
        data() {
            return {
                //点击包名称
                pkgName:'',
                //点检包名称查询表格
                pNameData:[],
                //点检包数据表格
                tableData: [],
                //点检项目表格数据
                tableData2:[],
                upNameDate:[],
                //查询条件
                scpQuery: {},
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 封装表单数据的对象
                form: {},

                //左边树状菜单
                data: [],
                //点检项目查询条件
                sccQuery:{},
                // 当前页号
                current2: 1,
                // 分页列表
                pageSizeList2: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit2: 5,
                // 总记录数
                total2: 0,

                tableData1:[{catalogName:'',directoryName:'',flag:false,}],
                multipleTabless: [],
                multipleSelection:[],
                id:'',
                updform:{},
                init:'init',
                confirm:'confirm',
                status:'',

                mode: "transfer", // transfer addressList
                toData:[],
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dialogFormVisible3:false,
                dialogFormVisible4:false,
                value: '',
                input:'',
                fromData:'',
                employeeQuery:{},
                templateRadio:0,
                templateSelection: '',
            };
        },
        methods: {
            inputClick(row){
                row.flag=false;
            },
            selectChange(val){
                this.multipleTabless=val;
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            singleElection (row) {
                this.templateSelection = this.tableData.indexOf(row);
                console.log(this.templateSelection)
                this.templateRadio = row.id;
            },
            //点击表格某一行时触发
            async handleSelectionChange(row) {
                this.id = row.scpUuid;
                this.updform.scpUuid=row.scpUuid;
                this.status=row.status
                const _this = this
                const response =await _this.$axios.post(`/serviceServiceProduct/serv-checkup-pkg/query/${row.pkgName}`)
                this.upNameDate= response.data.myServCheckupPkgList
            },
            //删除数据
            addInputer(){
                if (this.multipleTabless.length == 0) {
                    this.$message.warning('请至少选择一行数据')
                    return ;
                }
                for(var i=this.$refs.multipleTables.selection.length-1;i>=0;i--){
                    this.tableData1.splice(this.$refs.multipleTables.selection[i].index-1);
                }
            },
            celledit(row, column){
                row.flag=true
                if(column.label == '点检项目'){
                    row.edit_enddate = !row.edit_enddate;
                    this.dialogFormVisible3=true;
                }
                if(column.label == '所属系统'){
                    row.edit_enddate = !row.edit_enddate;
                    this.dialogFormVisible3=true;
                }
            },
            //双击点检项目的表格时触发
            celledit1(row){
                var _this=this;
                for(var i=0;i< _this.tableData1.length;i++){
                    if(_this.tableData1[i].catalogName==row.catalogName){
                        return;
                    }
                }
                _this.tableData1.push(row);

                console.log(_this.tableData1);
                this.dialogFormVisible3=false;
            },
            //添加/编辑 确定
            async addPkg(){
                var _this=this;
                console.log(_this.tableData1)
                //添加
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-pkg/${this.form.scpUuid == null ? 'add' : 'update'}/`,{
                    form: JSON.stringify(_this.form),
                    tableData: JSON.stringify(_this.tableData1),
                })
                if (response.code == 20000) {
                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    _this.dialogFormVisible=false
                    // 刷新数据表格
                    _this.getScpList()
                }

            },
            //点击包名称查询
            async handleEdit(index, row){
                console.log(row.pkgName)
                this.pkgName=row.pkgName
                this.dialogFormVisible4=true;

                const _this = this
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-pkg/query/${this.pkgName}`)
                console.log(response.data.myServCheckupPkgList)
                this.pNameData = response.data.myServCheckupPkgList

            },
            // 获取所有点检包的方法
            async getScpList () {
                const _this = this
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-pkg/queryPage/${this.current}/${this.limit}`, this.scpQuery)
                console.log(response.data)
                this.tableData = response.data.rows
                this.total = response.data.total

            },
            // 查询按钮的方法
            search () {
                this.getScpList()
            },
            // 查询按钮的方法
            search2 () {
                this.getSccList()
            },
            //获取左边树状菜单
            async getTreeList () {
                const _this = this
                const response = await _this.$axios.get('/serviceServiceProduct/serv-checkup-directory/findAllTree')
                console.log("树状菜单："+response.data.rows)
                _this.data = response.data.rows
            },
            // 获取所有点检项目的方法
            async getSccList () {
                const _this = this
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-catalog/queryPage/${this.current2}/${this.limit2}`, this.sccQuery)
                this.tableData2 = response.data.rows
                this.total2 = response.data.total

            },
            //点击左边树状菜单查询
            treeSearch(data){
                this.sccQuery={}
                if(data.label=="小拇指点检目录"){
                    this.sccQuery.directoryName==""
                    this.getSccList()
                }else{
                    this.sccQuery.directoryName=data.label
                    this.getSccList()
                }

            },
            //点击编辑时触发
            async update(){
                this.form={}
                this.tableData1=[]
                let _this=this;
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                    return
                }
                _this.dialogFormVisible=true;
                const id = _this.id
                const response = await _this.$axios.get(`/serviceServiceProduct/serv-checkup-pkg/queryById/${id}`)
                this.form = response.data.servCheckupPkg
                console.log(this.form)
                this.tableData1=this.upNameDate
            },
            //点击上架时触发
            async shangjia(){
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                    return
                }
                if(this.status=="confirm"){
                    this.$message({
                        message: '提示哦，已上架的点检包不能再次上架',
                    });
                    return
                 }

                var _this=this;
                _this.updform.status=_this.confirm
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-pkg/updateStatus/`,this.updform)
                if (response.code == 20000) {
                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    //刷新数据表格
                    _this.getScpList()
                }
            },
            async xiajia(){
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                    return
                }

                if(this.status=="init"){
                    this.$message({
                        message: '提示哦，未上架点检包不能下架',
                    });
                    return
                }
                var _this=this;
                _this.updform.status=_this.init
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-pkg/updateStatus/`,this.updform)
                if (response.code == 20000) {
                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    //刷新数据表格
                    _this.getScpList()
                }
            },
            peizhi(){
                if(this.templateRadio==0){
                    this.$message({
                        message: '提示哦，请选中一行',
                    });
                }else{
                    this.dialogFormVisible2=true;
                }
            },
            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getScpList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getScpList()
            },
            // 页大小改变事件
            handleSizeChange2(val) {
                console.log(`每页 ${val} 条`)
                this.limit2 = val
                this.getSccList()
            },
            // 页号改变事件
            handleCurrentChange2(val) {
                console.log(`当前页: ${val}`)
                this.current2 = val
                // 查询
                this.getSccList()
            },

        },
    };

</script>

<style scoped>

</style>
