<!-- - - - - - - - - - - - - - -->
<!--         服务项目管理        -->
<!-- - - - - - - - - - - - - - -->

<template>
    <div>
        <el-container style="height: 640px; border: 1px solid #eee">
            <!--左边-->
            <el-aside width="200px"   style="background-color: rgb(238, 241, 246)">
                <h3>服务目录</h3>
                <!-- 树形控件-->
                <el-tree
                        :data="data"
                        default-expand-all
                        node-key="id"
                        ref="tree"
                        highlight-current
                        style="height: 435px"
                        @node-click="clicknode">
                </el-tree>
            </el-aside>

            <el-container>
                <!--头-->
                <el-header height="50px">
                    <p>总部统一服务目录管理</p>
                </el-header>
                <!--搜索与添加区域-->
                <el-form :inline="true" :model="SerObjectQuery" class="demo-form-inline" label-width="110px" size="mini">

                    <el-form-item label="是否启用" style="margin-left: -24px">
                        <el-select v-model="SerObjectQuery.usableFlag" placeholder="--请选择--" clearable>
                            <el-option
                                    v-for="item in options"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="服务大类" >
                        <el-select v-model="SerObjectQuery.topType" placeholder="--请选择--"  clearable>
                            <el-option
                                    v-for="item in options1"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="是否常用">
                        <el-select v-model="SerObjectQuery.isCommon" placeholder="--请选择--" clearable>
                            <el-option
                                    v-for="item in options2"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>


                    <el-form-item label="服务项目名称">
                        <el-input v-model="SerObjectQuery.catalogName"  placeholder="服务项目名称" ></el-input>
                    </el-form-item>

                    <el-form-item label="服务类型" style="margin-left: -10px">
                        <el-input v-model="SerObjectQuery.servTypeName" placeholder="服务类型" ></el-input>
                    </el-form-item>


                    <el-form-item style="margin-left: 60px">
                        <el-button type="primary" icon="el-icon-search" @click="Serachc">查询</el-button>
                    </el-form-item>

                </el-form>
                <!--  主体内容-->
                <el-main style="margin-top: -30px">
                    <el-button type="primary" size="mini" icon="el-icon-plus"  @click="addFuWu=true">增加</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit"  @click="handleEdit()">修改</el-button>
                    <el-button type="danger"  size="mini" icon="el-icon-delete" @click="removeD()">删除</el-button>
                    <el-button type="primary" size="mini"  icon="el-icon-share" @click="openPZ()">配置服务项目对应的配件目录</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="EditSA()">批量SA增值率</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="EditJS()">批量计师提成率</el-button>
                    <el-table
                            ref="multipleTable"
                            :data="ServObjectData"
                            tooltip-effect="dark"
                            style="width: 100%"
                            max-height="350px"
                            @selection-change="handleSelectionChange">
                        <el-table-column
                                type="selection"
                                width="55">
                        </el-table-column>


                        <el-table-column
                                prop="topType"
                                label="服务大类"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="servTypeName"
                                label="服务类型"
                                width="120">
                        </el-table-column>

                        <el-table-column
                                prop="catalogName"
                                label="服务项目名称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="catalogNameShort"
                                label="简称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="stdManHour"
                                label="标准工时"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="stdPrice"
                                label="标准价格"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="stdManHour"
                                label="标准面数"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="commRateSa"
                                label="SA增殖率"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="commRateWorker"
                                label="计师提成率"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="commRateWorker"
                                label="计师提成率"
                                width="90">
                        </el-table-column>
                        <el-table-column
                                prop="saCanEditPrice"
                                label="服务顾问是否可改价格"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="isSamePrice"
                                label="是否不同车型价格一致"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="brCanEditName"
                                label="门店是否可修改名称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="isSamePrice"
                                label="是否不同车型价格一致"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="isSamePrice"
                                label="是否不同车型价格一致"
                                width="120">
                        </el-table-column>

                        <el-table-column
                                prop="usableFlag"
                                label="是否启用"
                                show-overflow-tooltip>
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
                    <el-dialog width="60%" :title="form.catalogUuid == null ? '增加总统统一服务目录管理':'编辑总统统一服务目录管理'"
                               @close="addDialogClosed" :visible.sync="addFuWu" >

                        <fieldset style="border:solid 1px #aaa;padding: 1px" >
                            <legend>基本信息</legend>
                          <!--  内容主体-->
                            <el-form :model="form" size="mini" :inline="true" :rules="addSerObjectFormRules"
                                     ref="addSerObjectFormRef">

                                <el-form-item label="服务类型:" label-width="171" style="margin-left: 75px">
                                    <el-input v-model="typeName" autocomplete="off" style="width: 100px"></el-input>
                                    <el-button  icon="el-icon-more" @click="addfuwuType"></el-button>
                                </el-form-item>

                                <el-form-item label="服务项目编号:" :label-width="formLabelWidth" prop="catalogCode">
                                    <el-input v-model="form.catalogCode" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="服务项目名称:" :label-width="formLabelWidth" prop="catalogName">
                                    <el-input v-model="form.catalogName" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <!--  第二行-->
                                <el-form-item label="简称:" :label-width="formLabelWidth" >
                                    <el-input v-model="form.catalogNameShort" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>


                                <el-form-item label="服务顾问是否可改价格:" :label-width="formLabelWidth">
                                    <el-select v-model="form.saCanEditPrice" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>


                                <el-form-item label="是否不同车型价格一致:" :label-width="formLabelWidth">
                                    <el-select v-model="form.isSamePrice" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>


                                <el-form-item label="门店是否可修改名称:" :label-width="formLabelWidth">
                                    <el-select v-model="form.brCanEditName" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>


                                <el-form-item label="是否快速结算:" :label-width="formLabelWidth">
                                    <el-select v-model="form.isQuick" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                                <el-form-item label="是否常用:" :label-width="formLabelWidth">
                                    <el-select v-model="form.isCommon" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>


                                <el-form-item label="SA增值率:" :label-width="formLabelWidth">
                                    <el-input v-model="form.commRateSa" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="技师提成率:" :label-width="formLabelWidth">
                                    <el-input v-model="form.commRateWorker" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="序号:" :label-width="formLabelWidth">
                                    <el-input v-model="form.seqNo" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="统计方法:" :label-width="formLabelWidth">
                                    <el-select v-model="form.statMethod" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options3"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>

                                <el-form-item label="统计分类:" :label-width="formLabelWidth">
                                    <el-select v-model="form.statType" placeholder="--请选择--" style="width: 100px">
                                        <el-option
                                                v-for="item in options4"
                                                :key="item.id"
                                                :label="item.label"
                                                :value="item.id">
                                        </el-option>
                                    </el-select>
                                </el-form-item>


                                <el-form-item label="别名1:" :label-width="formLabelWidth">
                                    <el-input v-model="form.alias1" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>


                                <el-form-item label="别名2:" :label-width="formLabelWidth">
                                    <el-input v-model="form.alias2" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="别名3:" :label-width="formLabelWidth">
                                    <el-input v-model="form.alias3" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="别名4:" :label-width="formLabelWidth">
                                    <el-input v-model="form.alias3" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-form-item label="别名5:" :label-width="formLabelWidth">
                                    <el-input v-model="form.alias4" autocomplete="off" style="width: 100px"></el-input>
                                </el-form-item>

                                <el-input v-model="form.servTypeUuid" type="hidden" ></el-input>

                            </el-form>
                        </fieldset>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="addFuWu = false">取 消</el-button>
                            <el-button type="primary" @click="save()">确 定</el-button>
                        </div>
                    </el-dialog>

                    <!--  修改SA提成率-->
                    <el-dialog title="SA增值率设置" width="20%"   :visible.sync="editSA" >
                        <el-form :model="forms" size="mini" :inline="true">

                            <el-form-item label="提成率:" >
                                <el-input v-model="form.commRateSa" autocomplete="off" placeholder="请输入0-100的数值%"></el-input>
                            </el-form-item>

                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="editSA = false">取 消</el-button>
                            <el-button type="primary" @click="save2()">确 定</el-button>
                        </div>
                    </el-dialog>

                    <!--  修改技师提成率-->
                    <el-dialog title="技师增值率设置" width="20%"   :visible.sync="editJS" >
                        <el-form :model="form" size="mini" :inline="true">

                            <el-form-item label="提成率:" >
                                <el-input v-model="forms.commRateWorker" autocomplete="off" placeholder="请输入0-100的数值%"></el-input>
                            </el-form-item>

                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="editJS = false">取 消</el-button>
                            <el-button type="primary" @click="save1()">确 定</el-button>
                        </div>
                    </el-dialog>

                    <!--选择服务类型-->
                    <el-dialog title="选择服务类型" width="30%"   :visible.sync="addFuWuType" >
                        <el-form :model="form" size="mini" :inline="true">
                            <!-- 树形控件-->
                            <el-tree
                                    :data="data1"
                                    default-expand-all
                                    node-key="id"
                                    ref="tree"
                                    highlight-current
                                    @node-click="clicknode1"
                                    style="height: 435px">
                            </el-tree>

                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="addFuWuType = false">取 消</el-button>
                        </div>
                    </el-dialog>

                    <el-dialog width="60%"  title="编辑服务项目与配件目录对应关系" :visible.sync="bscpart" >
                        <span style="font-size:12px">服务项目名称:</span>
                        <br>

                        <el-aside width="200px">
                        <el-tree

                                show-checkbox
                                :data="treeData"
                                node-key="partTypeUuid"
                                :props="defaultProps"
                                ref="tree">
                        </el-tree>
                        </el-aside>
                        <el-button size="mini" type="primary" @click="xuanzepei" style="width: 85px;float: right;margin-top: -300px;margin-right: 465px">选择&gt;&gt;</el-button>
                        <el-button size="mini" type="primary" @click="quxiaopei" style="width: 85px;float: right;margin-top: -250px;margin-right: 465px">取消&gt;&gt;</el-button>
                        <el-table
                                height="420px"
                                ref="multipleTable"
                                :data="tableData5"
                                border
                                tooltip-effect="dark"
                                style="width: 380px;float: right;margin-top: -520px;margin-right: 30px"
                                @selection-change="handleSelectionChange1">
                            <el-table-column
                                    type="selection"
                                    width="40">
                            </el-table-column>

                            <el-table-column
                                    prop="bptName"
                                    label="已选中的配件目录名称"
                                    show-overflow-tooltip>
                            </el-table-column>


                        </el-table>

                        <!--分页区域-->
                        <el-pagination small
                                       style="float: right;margin-top: -60px"
                                       @size-change="handleSizeChange1"
                                       @current-change="handleCurrentChange1"
                                       :current-page="current2"
                                       :page-sizes="pageSizeList"
                                       :page-size="limit2"
                                       layout="total, sizes, prev, pager, next, jumper"
                                       :total="total2">
                        </el-pagination>
                    </el-dialog>

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>



<script>


    export default {
        name: "index",
        data() {
            return {
                //打开配件
                bscpart:false,
                //配件树状目录
                treeData: [],
                //服务项目和配件的
                tableData5:[],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                //选择发行范围
                current2: 1,
                limit2: 5,
                total2: 0,
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //服务类型
                addFuWuType:false,
                //服务项目添加对话框
                addFuWu:false,
                //打开修改SA提成率对话框
                editSA:false,
                //打开修改JS提成率对话框
                editJS:false,
                // 封装表单数据的对象
                form: {},
                forms: {},
                form1:{},
                // 表单元素的label的宽度
                formLabelWidth: '191px',

                ServObjectData: [],
                //服务项目选中的id
                multipleSelection: [],
                //服务项目和配件对应关系 服务项目选中的id
                multipleSelectionData:[],
                //是否启用
                options: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}],
                //服务大类
                options1:[
                    {id: 'cosmetology', label: '美容'},
                    {id: 'maintain', label: '机修'},
                    {id: 'paint', label: '油漆'},
                    {id: 'metalPlate', label: '钣金'},
                ],
                //是否常用
                options2: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}],
                //统计方法
                options3: [
                    {id: 'fix', label: '按固定分类'},
                    {id: 'workcard', label: '按派工单'}],
                //统计分类
                options4: [
                    {id: 'cosmetology', label: '美容'},
                    {id: 'maintain', label: '机修'},
                    {id: 'paint', label: '油漆'},
                    {id: 'accessories', label: '精品'},
                    {id: 'metalPlate', label: '钣金'},
                    {id: 'glass', label: '玻璃'},
                    {id: 'wash', label: '洗车'},
                    {id: 'tyre', label: '轮胎'}],
                //左边的菜单数据
                data: [],
                //获取添加服务类型
                data1:[],
                muData:[],
                //右边服务项目的分页查询数据
                SerObjectQuery:{},
                //添加服务的类型数据
                typeName:'',
                addSerObjectFormRules: {
                    catalogCode: [
                        {required: true, message: '请输入', trigger: 'blur'},
                    ]
                },
            };

        },
        created() {
            //查询左边的菜单
            this.getLeftList();
            //查询右边的菜单
            this.getRightList();
        },
        methods:{
            //单击查询
            Serachc(){
                this.getRightList();
            },
            //单击左边的菜单查询
            clicknode(data){
                const _this = this
                //获取点击菜单的id
                _this.SerObjectQuery.stUuid= data.stUuid;
                //查询右边的菜单
                this.getRightList(this.getRightList);
            },
            //获取左边的菜单
            getLeftList(){
                const _this = this
                _this.$axios.get("/serviceServiceProduct/base-serv-type").then(result=>{
                    _this.data=result.data.rows;
                    _this.data1=result.data.rows;
                })
            },
            //获取右边的菜单
            getRightList(){
                const _this = this
                _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/queryAllPage/${this.current}/${this.limit}`,_this.SerObjectQuery).then(result=>{
                    _this.ServObjectData=result.data.rows;
                    _this.total=result.data.total;
                })
            },
            //修改技师提成率
            save1(){
                for(var i=0;i<this.multipleSelection.length;i++) {
                    this.forms.catalogUuid=this.multipleSelection[i];
                    const _this = this
                    _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/update2/`,_this.forms).then(result => {
                        if (result.code === 20000) {
                            this.$message({
                                type: 'success',
                                message: '修改成功!'
                            });
                            //刷新
                            //查询左边的菜单
                            this.getLeftList();
                            //查询右边的菜单
                            this.getRightList();
                            this.editJS=false;
                            this.forms={};
                        }

                    })
                }
            },
            //查询配件树状目录
            getTreeData(){
                const _this = this
                _this.$axios.post(`/serviceCarsparts/cartype/carTypeZiTree`).then(result=>{
                    _this.treeData=result.data.rows;
                })
            },
            //获取配件名称
            getPeiJian(){
                const _this = this
                _this.$axios.post(`/serviceServiceProduct/base-serv-catalog-part/queryByPart/${this.current2}/${this.limit2}/${this.multipleSelection[0]}`).then(result=>{
                    _this.tableData5=result.data.rows
                    _this.total2=result.data.total;

                })
            },
            //打开配置服务项目对应的配件目录
            openPZ(){
                if(this.multipleSelection.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                this.bscpart=true;
                this.getTreeData();
                //获取配件名称
                this.getPeiJian();


            },
            //选择
            xuanzepei(){
                if(this.$refs.tree.getCheckedNodes()[0].children[0]===0){
                    return;
                }
                const _this = this
                //配件id
                _this.form1.partTypeUuid=this.$refs.tree.getCheckedNodes()[0].partTypeUuid
                //服务项目id
                _this.form1.catalogUuid=this.multipleSelection[0];
                //是否有效
                _this.form1.isActive='Y';
                _this.form1.version=0;
                _this.$axios.post('/serviceServiceProduct/base-serv-catalog-part/add',_this.form1).then(result=>{
                    if(result.code===20000){
                        this.$message({
                            type:'success',
                            message:'添加成功!',
                        });
                    }
                    //刷新
                    this.getTreeData();
                    //获取配件名称
                    this.getPeiJian();
                })
            },
            //取消
            quxiaopei(){
                if(this.multipleSelectionData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    for(var i=0;i<this.multipleSelectionData.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceServiceProduct/base-serv-catalog-part/delete/${this.multipleSelectionData[i]}`).then(result=>{
                            if(result.code===20000){
                                this.$message({
                                    type: 'success',
                                    message: '删除成功!'
                                });
                                //刷新
                                this.getTreeData();
                                //获取配件名称
                                this.getPeiJian();
                            }

                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            //打开修改技师提成率对话框
            async  EditJS(){
                if(this.multipleSelection.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                this.editJS=true;

            },
            //打开修改SA提成率对话框
            async EditSA(){
                if(this.multipleSelection.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                this.editSA=true;

            },
            //修改SA提成率
            save2(){
                for(var i=0;i<this.multipleSelection.length;i++) {
                    this.forms.catalogUuid=this.multipleSelection[i];
                    const _this = this
                    _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/update1/`,_this.forms).then(result => {
                        if (result.code === 20000) {
                            this.$message({
                                type: 'success',
                                message: '修改成功!'
                            });
                            //刷新
                            //查询左边的菜单
                            this.getLeftList();
                            //查询右边的菜单
                            this.getRightList();
                            this.editJS=false;
                            this.forms={};
                        }

                    })
                }
            },
            //添加的时候单击
            clicknode1(data){
                this.form.servTypeUuid=data.stUuid;
                this.typeName=data.label;
                //关闭添加类型对话框
                this.addFuWuType=false;
            },
            //添加服务项目里服务类型
            addfuwuType(){
                this.addFuWuType=true;

            },
            //监听对话框关闭事件
            addDialogClosed(){
                this.form={};
                this.typeName='';
                this.$refs.addSerObjectFormRef.resetFields();
            },
            //添加修改
            async save(){
                this.$refs.addSerObjectFormRef.validate(valid => {
                if (!valid) return;
                    this.addFuWu=false
                    if(this.form.catalogUuid==null){
                        const _this = this
                        _this.$axios.post('/serviceServiceProduct/base-serv-catalog/add',_this.form).then(result=>{
                            if(result.code===20000){
                                this.$message({
                                    type:'success',
                                    message:'添加成功!',
                                });
                            }
                            //查询左边的菜单
                            this.getLeftList();
                            //查询右边的菜单
                            this.getRightList();
                        })
                    }else if(this.form.catalogUuid!=null){
                        const _this = this
                        _this.$axios.post('/serviceServiceProduct/base-serv-catalog/update',_this.form).then(result=>{
                            if(result.code===20000){
                                this.$message({
                                    type:'success',
                                    message:'修改成功!',
                                });
                            }

                            //查询左边的菜单
                            this.getLeftList();
                            //查询右边的菜单
                            this.getRightList();
                        })
                    }
                    this.form={};
                    this.typeName='';
                    //关闭对话框
                    this.addFuWu = false

                })
            },

            //打开修改
            handleEdit(){
                if(this.multipleSelection.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.multipleSelection.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                const _this = this
                _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/queryById/${this.multipleSelection[0]}`).then(result=>{

                    _this.form=result.data.baseServCatalog;
                    _this.typeName=this.muData[0];

                    //打开修改对话框
                    this.addFuWu = true
                })



            },
            //删除
            removeD(){
                if(this.multipleSelection.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }

                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    for(var i=0;i<this.multipleSelection.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceServiceProduct/base-serv-catalog/delete/${this.multipleSelection[i]}`).then(result=>{
                          if(result.code===20000){
                              this.$message({
                                  type: 'success',
                                  message: '删除成功!'
                              });
                              //刷新
                              //查询左边的菜单
                              this.getLeftList();
                              //查询右边的菜单
                              this.getRightList();
                          }

                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //获取选中的id
            handleSelectionChange(val){
                //先清空id数组
                this.multipleSelection = [];
                this.muData=[];
                //去重获取被选中的id值存入到数组中
                for (let selectedItem of val) {
                    this.multipleSelection.push(selectedItem.catalogUuid)
                    this.muData.push(selectedItem.servTypeName)
                }
            },

            //获取服务项目和配件的选中
            handleSelectionChange1(val){
                //先清空id数组
                this.multipleSelectionData = [];

                //去重获取被选中的id值存入到数组中
                for (let selectedItem of val) {
                    this.multipleSelectionData.push(selectedItem.scpUuid)
                }
            },
            // 页大小改变事件
            handleSizeChange1 (val) {
                this.limit2 = val
            },
            // 页号改变事件
            handleCurrentChange1 (val) {
                this.current2 = val
            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getRightList();
            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getRightList();
            }
        }
    }


</script>

<style scoped>


    .el-aside {
        height: 500px;
    }


</style>
