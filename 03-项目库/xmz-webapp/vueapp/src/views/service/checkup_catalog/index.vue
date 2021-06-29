<!-- - - - - - - - - - - - -->
<!--        点检项目        -->
<!-- - - - - - - - - - - - -->

<template>

    <div>

        <el-container style="height: 550px; border: 1px solid #eee">
            <!-- 左边区域 -->
            <el-aside width="200px" style="background-color: rgb(238, 241, 246);">
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
            <el-container >
                <el-header style="height:40px">
                <!-- 查询输入框 -->
                <el-form :inline="true" :model="sccQuery" class="demo-form-inline" size="mini" style="margin-top: 15px;">

                    <el-form-item label="项目名称">
                        <el-input v-model="sccQuery.catalogName" placeholder="项目名称" clearable @clear="getSccList"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="search">查询</el-button>
                    </el-form-item>

                    <el-form-item>
                        <el-button  icon="el-icon-circle-plus-outline" size="mini" @click="dialogFormVisible = true">添加</el-button>
                        <el-button  icon="el-icon-edit" size="mini" @click="updateScc">编辑</el-button>
                        <el-button icon="el-icon-delete" size="mini" @click="removeScc">删除</el-button>
                    </el-form-item>
                </el-form>

                    <!-- 表格数据 -->
                    <el-table
                            height="400px"
                            :data="tableData"
                            tooltip-effect="dark"
                            style="width: 100%"
                            @selection-change="handleSelectionChange">
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
<!--                        <el-table-column-->
<!--                                prop="normalUuid"-->
<!--                                label="正常">-->
<!--                            <template slot-scope="scope">-->
<!--                                <span v-for="(type,index) in scope.row.type" :key="index">-->
<!--                                    <span>{{type.type==='normal'?type.description+',':''}}</span>-->
<!--                                </span>-->
<!--                            </template>-->
<!--                        </el-table-column>-->
<!--                        <el-table-column-->
<!--                                prop="slightUuid"-->
<!--                                label="轻微异常">-->
<!--                            <template slot-scope="scope">-->
<!--                          <span v-for="(type,index) in scope.row.type" :key="index">-->
<!--                            <span>{{type.type==='slight'?type.description+',':''}}</span>-->
<!--                          </span>-->
<!--                            </template>-->
<!--                        </el-table-column>-->
<!--                        <el-table-column-->
<!--                                prop="moreUuid"-->
<!--                                label="严重异常">-->
<!--                            <template slot-scope="scope">-->
<!--                          <span v-for="(type,index) in scope.row.type" :key="index">-->
<!--                            <span>{{type.type==='more'?type.description+',':''}}</span>-->
<!--                          </span>-->
<!--                            </template>-->
<!--                        </el-table-column>-->

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

        <!--新增的对话框-->
        <el-dialog title="增加点检项目" :visible.sync="dialogFormVisible">
            <el-card class="box-card">
                <el-form :model="form" size="mini" :inline="true" label-position="left" label-width="140px">
                    <el-form-item label="项目名称">
                        <el-input v-model="form.catalogName" autocomplete="off" style="width:150px"></el-input>
                    </el-form-item>

                    <el-form-item label="项目所属目录">
                        <el-select v-model="form.scdUuid" placeholder="请选择" size="mini" style="width:150px">
                            <el-option
                                    v-for="item in options2"
                                    :key="item.scdUuid"
                                    :label="item.directoryName"
                                    :value="item.scdUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="提醒开始的时间(天)">
                        <el-input v-model="form.remindStartTime" autocomplete="off" style="width:150px"></el-input>
                    </el-form-item>

                    <el-form-item label="再次点检的时间(天)">
                        <el-input v-model="form.aginCheckupTime" size="mini" style="width:150px"></el-input>
                    </el-form-item>
                    <br>

                    <el-form-item>
                        <label>正常</label><br/>
                        <el-input
                                v-model="zc"
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容" style="width:216px">
                        </el-input>
                    </el-form-item>

                    <el-form-item>
                        <label>轻微异常</label><br/>
                        <el-input
                                v-model="qwyc"
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容" style="width:216px">
                        </el-input>
                    </el-form-item>


                    <el-form-item>
                        <label>严重异常</label><br/>
                        <el-input
                                v-model="yzyc"
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容" style="width:216px">
                        </el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-checkbox v-model="checked" @change="checkedisSetMile">剩余里程/时间</el-checkbox>
                    </el-form-item>

                </el-form>

            </el-card>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>

        <!--修改的对话框-->
        <el-dialog title="修改点检项目" :visible.sync="dialogFormVisible2">
            <el-card class="box-card">
                <el-form :model="form2" size="mini" :inline="true" label-position="left" label-width="140px">
                    <el-form-item label="项目名称">
                        <el-input v-model="form2.catalogName" autocomplete="off" style="width:150px"></el-input>
                    </el-form-item>

                    <el-form-item label="项目所属目录">
                        <el-select v-model="form2.scdUuid" placeholder="请选择" size="mini" style="width:150px">
                            <el-option
                                    v-for="item in options2"
                                    :key="item.scdUuid"
                                    :label="item.directoryName"
                                    :value="item.scdUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="提醒开始的时间(天)">
                        <el-input v-model="form2.remindStartTime" autocomplete="off" style="width:150px"></el-input>
                    </el-form-item>

                    <el-form-item label="再次点检的时间(天)">
                        <el-input v-model="form2.aginCheckupTime" size="mini" style="width:150px"></el-input>
                    </el-form-item>
                    <br>

                    <el-form-item>
                        <label>正常</label><br/>
                        <el-input
                                v-model="Uzc"
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容" style="width:216px">
                        </el-input>
                    </el-form-item>

                    <el-form-item>
                        <label>轻微异常</label><br/>
                        <el-input
                                v-model="Uqwyc"
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容" style="width:216px">
                        </el-input>
                    </el-form-item>


                    <el-form-item>
                        <label>严重异常</label><br/>
                        <el-input
                                v-model="Uyzyc"
                                type="textarea"
                                :autosize="{ minRows: 2, maxRows: 4}"
                                placeholder="请输入内容" style="width:216px">
                        </el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-checkbox v-model="checked2" @change="checkedisSetMile2">剩余里程/时间</el-checkbox>
                    </el-form-item>

                </el-form>

            </el-card>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="updateSubmit">确 定</el-button>
            </div>
        </el-dialog>


    </div>


</template>

<script>
    export default {
        data() {
            return {
                //左边树状菜单
                data: [],
                // 保存表格数据的变量
                tableData: [],
                // 保存查询条件的对象
                sccQuery: {},
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 封装表单数据的对象
                form: {//异常类型
                    type:[],
                    //异常描述
                    descriptions:[]
                },
                // 是否显示对话框
                dialogFormVisible: false,
                dialogFormVisible2: false,
                // 下拉框的数据
                options2: [],
                // 剩余里程数/时间 的复选框
                checked:true,
                checked2:true,
                //正常
                zc:null,
                //轻微异常
                qwyc:null,
                //严重异常
                yzyc:null,
                multipleSelection: [],
                //选中的id
                selectId:[],
                form2: {},
                type:[],
                descriptions:[],
                //正常
                Uzc:null,
                //轻微异常
                Uqwyc:null,
                //严重异常
                Uyzyc:null,
            };
        },
        created () {
            this.getSccList()
            this.getDirList()
            this.getTreeList()
            this.checkedisSetMile()
        },
        methods: {
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            // 获取所有点检项目的方法
            async getSccList () {
                const _this = this
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-catalog/queryPage/${this.current}/${this.limit}`, this.sccQuery)
                console.log(response.data)
                this.tableData = response.data.rows
                this.total = response.data.total

            },
            //获取左边树状菜单
            async getTreeList () {
                const _this = this
                const response = await _this.$axios.get('/serviceServiceProduct/serv-checkup-directory/findAllTree')
                console.log("树状菜单："+response.data.rows)
                _this.data = response.data.rows
            },
            // 查询按钮的方法
            search () {
                this.getSccList()
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
            // 获取项目所属目录的下拉框
            async getDirList () {
                const _this = this
                const response = await _this.$axios.get('/serviceServiceProduct/serv-checkup-directory/listall')
                console.log(response.data)
                this.options2 = response.data.list
            },
            //当复选框改变时触发的事件
            checkedisSetMile(){
                let _this = this;
                if(_this.checked==true){
                    _this.form.isSetMile='Y'
                }else{
                    _this.form.isSetMile='N'
                }
            },
            checkedisSetMile2(){
                let _this = this;
                if(_this.checked2==true){
                    _this.form2.isSetMile='Y'
                }else{
                    _this.form2.isSetMile='N'
                }
            },
            //添加点检项目
            async save(){
                let _this = this;
                if(_this.zc!=null&&_this.qwyc!=null&&_this.yzyc!=null){
                    _this.form.type.push("normal")
                    _this.form.type.push("slight")
                    _this.form.type.push("more")
                    _this.form.descriptions.push(_this.zc)
                    _this.form.descriptions.push(_this.qwyc)
                    _this.form.descriptions.push(_this.yzyc)
                    // console.log("类型："+_this.form.type)
                    // console.log("描述："+_this.form.descriptions)

                }else{
                    this.$message({
                        type: 'warning',
                        message: '请填写异常描述!'
                    })
                    return;
                }
                //添加
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-catalog/add/`, _this.form)
                if (response.code == 20000) {
                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    _this.dialogFormVisible=false
                    // 刷新数据表格
                    _this.getSccList()
                }

            },
            //删除点检项目
            removeScc(){
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
                        _this.$axios.delete(`/serviceServiceProduct/serv-checkup-catalog/delete/${this.selectId[i]}`).then(result=>{
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getSccList();

                        })
                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //编辑点检项目
            async updateScc(){
                if(this.multipleSelection.length==0||this.multipleSelection.length>1){
                    this.$message({
                        type:'info',
                        message:'请选择一条数据'
                    })
                    return
                }
                this.form2={}
                this.dialogFormVisible2 = true
                const response = await this.$axios.get(`/serviceServiceProduct/serv-checkup-catalog/${this.selectId}`)

                this.form2=response.data.myServCheckupCatalogList[0]
                this.Uzc=response.data.myServCheckupCatalogList[0].description
                for(var i=0;i<response.data.myServCheckupCatalogList.length;i++){
                    // eslint-disable-next-line no-empty
                    if(response.data.myServCheckupCatalogList[i].type=="normal"){
                        this.Uzc=response.data.myServCheckupCatalogList[i].description
                    }
                    if(response.data.myServCheckupCatalogList[i].type=="slight"){
                        this.Uqwyc=response.data.myServCheckupCatalogList[i].description
                    }
                    if(response.data.myServCheckupCatalogList[i].type=="more"){
                        this.Uyzyc=response.data.myServCheckupCatalogList[i].description
                    }
                    if(response.data.myServCheckupCatalogList[i].isSetMile=="Y"){
                        this.checked2=true
                    }else{
                        this.checked2=false
                    }
                }
            },

            //编辑确定
            async updateSubmit(){
                let _this = this;
                _this.type.length=0;
                _this.descriptions.length=0;
                _this.type.push("normal","slight","more")
                _this.descriptions.push(_this.Uzc,_this.Uqwyc,_this.Uyzyc)
                _this.form2.type = _this.type
                _this.form2.descriptions=_this.descriptions

                console.log(_this.descriptions)
                //添加
                const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-catalog/update/`, _this.form2)
                if (response.code == 20000) {
                    // 提示消息
                    _this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    _this.dialogFormVisible2=false
                    // 刷新数据表格
                    _this.getSccList()
                }
            },
            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getSccList()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getSccList()
            },
            //表格选中改变事件
            handleSelectionChange(val){
                this.multipleSelection=val;
                this.selectId=[]
                for(var i=0;i<val.length;i++){
                    this.selectId.push(val[i].sccUuid);
                    console.log(this.selectId);
                }
            },

        },


    };
</script>

<style scoped>

</style>
