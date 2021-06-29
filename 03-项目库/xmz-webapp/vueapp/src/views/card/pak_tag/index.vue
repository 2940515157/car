<!-- - - - - - - - - - - - -->
<!--        套餐标签        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <el-container style="height: 640px; border: 1px solid #eee">
            <!--左边-->
            <el-aside width="200px"   style="background-color: rgb(238, 241, 246)">
                <h3>标签目录</h3>
                <!-- 树形控件-->
                <el-tree
                        :data="data"
                        default-expand-all
                        :expand-on-click-node="false"
                        node-key="id"
                        ref="tree"
                        highlight-current
                        @node-click="clicknode"
                        style="height: 435px">
                </el-tree>
            </el-aside>

            <el-container>
                <!--头-->
                <el-header height="50px">
                    <p>套餐标签</p>
                </el-header>
                <!--搜索与添加区域-->
                <el-form :inline="true" :model="TaoQuery"  class="demo-form-inline" label-width="110px" size="mini">


                    <el-form-item label="套餐标签">
                        <el-input  v-model="TaoQuery.label"  placeholder="套餐标签" ></el-input>
                    </el-form-item>


                    <el-form-item style="margin-left: 60px">
                        <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
                    </el-form-item>

                </el-form>
                <!--  主体内容-->
                <el-main style="margin-top: -30px">
                    <el-button type="primary" size="mini" icon="el-icon-plus" @click="addTaoCan=true" >增加标签</el-button>
                    <el-button v-if="isShow" type="primary" size="mini" icon="el-icon-plus"  @click="addFenl()">增加分类</el-button>
                    <el-button type="primary" size="mini" icon="el-icon-edit" @click="editTaoCan()">编辑标签</el-button>
                    <el-button type="danger"  size="mini" icon="el-icon-delete" @click="removeTaoCan()">删除</el-button>
                    <el-table
                            ref="multipleTable"
                            :data="tableData"
                            tooltip-effect="dark"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
                        <el-table-column
                                type="selection"
                                width="55">
                        </el-table-column>

                        <el-table-column
                                prop="label"
                                label="标签"
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


                    <!--增加标签-->
                    <el-dialog :title="form.bptdUuid == null ? '增加套餐标签':'编辑套餐标签'" width="30%"   :visible.sync="addTaoCan" >
                        <el-form :model="form" size="mini" :inline="true">

                            <el-card class="box-card">
                                <el-form-item label="套餐标签:" >
                                    <el-input v-model="form.label" autocomplete="off" placeholder="套餐标签"></el-input>
                                </el-form-item>

                            </el-card>

                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="addTaoCan = false">取 消</el-button>
                            <el-button type="primary" @click="save()">确 定</el-button>
                        </div>
                    </el-dialog>

                    <!--增加分类-->
                    <el-dialog title="增加标签分类" width="30%"   :visible.sync="addFenLei" >
                        <el-form :model="form1" size="mini" :inline="true">

                            <el-card class="box-card">
                                <el-form-item label="标签分类:" >
                                    <el-input v-model="form1.label" autocomplete="off" placeholder="标签分类"></el-input>
                                </el-form-item>

                            </el-card>

                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="addFenLei = false">取 消</el-button>
                            <el-button type="primary" @click="saves()">确 定</el-button>
                        </div>
                    </el-dialog>

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){

            return {

                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //增加 编辑套餐标签
                addTaoCan:false,
                //添加分类
                addFenLei:false,
                // 封装表单数据的对象
                form: {},
                // 表单元素的label的宽度
                formLabelWidth: '191px',
                //套餐标签查询条件
                TaoQuery:{},
                //套餐标签右边的数据
                tableData: [],
                multipleSelection: [],
                //左边菜单的数据
                data: [],
                //左边菜单的级别
                jibie:{},
                //左边菜单的录入
                userid:{},
                //选中的id
                selectId:[],
                //添加分类的按钮
                isShow:false,
                //添加分类
                form1:{}
            };

        },
        created() {
            this.getlist();
            this.getRightList();

        },
        methods:{
            //获取左边菜单
            getlist(){
                const _this = this
                _this.$axios.get("/serviceCard/base-pkg-tag-directory").then(result=>{

                    //console.log(result.data)
                    _this.data=result.data.rows;
                })
            },
            //单击左边菜单
            clicknode(data){
                const _this = this
                //获取点击菜单的id
                _this.TaoQuery.bptdUuid= data.bptdUuid;
                //获取点击菜单的级别
                this.jibie=data.directoryLevel;
                //获取点击菜单的录入
                this.userid= data.opUserUuid;
                if(data.directoryLevel=='1'){
                    this.isShow=true;
                }else {
                    this.isShow=false;
                }
                this.getRightList(this.getRightList)

            },
            //获取右边的菜单
            getRightList(){
                const _this = this
                _this.$axios.post(`/serviceCard/base-pkg-tag-directory/menuRight/${this.current}/${this.limit}`,_this.TaoQuery).then(result=>{
                    _this.tableData=result.data.rows;
                    _this.total=result.data.total;
                })
            },
            //查询
            search(){
                this. getRightList();
            },
            //选中改变事件
            handleSelectionChange(val){
                this.multipleSelection=val;
                this.selectId=[]
                for(var i=0;i<val.length;i++){
                    this.selectId.push(val[i].bptdUuid);
                }
            },
            //删除
            removeTaoCan(){
                if(this.multipleSelection.length==0){
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
                    for(var i=0;i<this.selectId.length;i++){
                        const _this = this
                        _this.$axios.delete(`/serviceCard/base-pkg-tag-directory/delete/${this.selectId[i]}`).then(result=>{
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getlist();
                            this.getRightList(this.TaoQuery);
                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            //添加 或 修改
            save(){
                //添加完关闭对话框
                this.addTaoCan=false;
                if(this.form.bptdUuid==null){
                    //录入
                    this.form.opUserUuid=this.userid;
                    //父类id
                    this.form.partentUuid=this.TaoQuery.bptdUuid;
                    //级别
                    this.form.directoryLevel=this.jibie+Number(1);
                    //版本
                    this.form.version=0;
                    const _this = this
                    _this.$axios.post('/serviceCard/base-pkg-tag-directory/add',_this.form).then(result=>{
                        if(result.code===20000){
                          this.$message({
                              type:'success',
                              message:'添加成功!',
                          });
                        }
                        //刷新
                        this.getlist();
                        this.getRightList(this.TaoQuery);
                    })
                }else if(this.form.bptdUuid!=null){
                    //版本
                    this.form.version=this.form.version+Number(1);
                    const _this = this
                    _this.$axios.post('/serviceCard/base-pkg-tag-directory/update',_this.form).then(result=>{
                        if(result.code===20000){
                            this.$message({
                                type:'success',
                                message:'修改成功!',
                            });
                        }
                        //刷新
                        this.getlist();
                        this.getRightList(this.TaoQuery);
                    })

                }
            },

            //打开修改框
            editTaoCan(){
                if(this.multipleSelection.length==0){
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
                //打开修改对话框
                this.addTaoCan=true;
                const _this = this
                _this.$axios.post(`/serviceCard/base-pkg-tag-directory/queryById/${this.multipleSelection[0].bptdUuid}`).then(result=>{
                    this.form=result.data.basePkgTagDirectory;

                })
            },

            //打开添加分类框
            addFenl(){
                if(this.multipleSelection.length==0){
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
                this.addFenLei=true;
            },

            saves(){

                //选中的父类id
                this.form1.partentUuid= this.multipleSelection[0].bptdUuid
                //录入
                this.form1.opUserUuid=this.userid;
                //级别
                this.form1.directoryLevel=3
                //版本
                this.form1.version=0;
                const _this = this
                _this.$axios.post('/serviceCard/base-pkg-tag-directory/addf',_this.form1).then(result=>{
                    if(result.code===20000){
                        this.$message({
                            type:'success',
                            message:'添加成功!',
                        });
                    }
                    //刷新
                    this.getlist();
                    this.getRightList(this.TaoQuery);
                })


                //添加完关闭对话框
                this.addFenLei=false;

            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getRightList()

            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getRightList()
            }
        }
    }


</script>

<style scoped>


    .el-aside {
        height: 500px;
    }


</style>
