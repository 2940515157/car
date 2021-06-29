<!-- - - - - - - - - - - - -->
<!--        车系管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
    <h3>车系管理</h3>
        <el-form ref="form" :inline="true" :model="BaseCarCategoryQuery" size="mini">

            品牌：<el-select v-model="BaseCarCategoryQuery.brandUuid" style="width: 150px" placeholder="请选择" size="mini">
            <el-option :value="null"> ---请选择--- </el-option>
            <el-option
                    v-for="item in pOptions"
                    :key="item.brandUuid"
                    :label="item.name"
                    :value="item.brandUuid">
            </el-option>
        </el-select>
            厂家：<el-select v-model="BaseCarCategoryQuery.factoryUuid" style="width: 150px" placeholder="请选择" size="mini">
            <el-option :value="null"> ---请选择--- </el-option>
            <el-option
                    v-for="item in fOptions"
                    :key="item.factoryUuid"
                    :label="item.name"
                    :value="item.factoryUuid">
            </el-option>
        </el-select>


            <el-form-item label="车系名称:">
                <el-input v-model="BaseCarCategoryQuery.name" style="width: 100px"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="getCarCategory">查询</el-button>
            </el-form-item>
        </el-form>
        <el-button type="primary" size="mini" @click="dialogFormVisible=true,form = {}">添加</el-button>
        <el-button type="success" size="mini" @click="update">编辑</el-button>
        <el-button type="danger" size="mini" @click="handleDelete">删除</el-button>

        <el-table
                height="400px"
                ref="multipleTable"
                :data="carCategpryList"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange"
                 >

            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>

            <el-table-column
                    label="车系名称"
                    width="80" prop="name">
            </el-table-column>
            <el-table-column
                    label="品牌"
                    width="80" prop="bname">
            </el-table-column>

            <el-table-column
                    label="厂家"
                    width="80" prop="fname">
            </el-table-column>
            <el-table-column
                    label="排序"
                    width="80" prop="seq">
            </el-table-column>
        </el-table>
        <!--分页-->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="current"
                :page-sizes="pageSizeList"
                :page-size="limit"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
        <div>
            <!--添加-->
            <el-dialog :title="form.categoryUuid == null ? '新增信息':'编辑信息'"  :visible.sync="dialogFormVisible">
                <fieldset>
                    <legend>基本信息</legend><br>
                    车系名称：<el-input v-model="form.name" style="width: 150px" size="mini"></el-input>
                    排序：<el-input v-model="form.eqs" style="width: 150px" size="mini"></el-input><br><br>
                    品牌：<el-select v-model="form.brandUuid" style="width: 150px" placeholder="请选择" size="mini">
                    <el-option
                            v-for="item in pOptions"
                            :key="item.brandUuid"
                            :label="item.name"
                            :value="item.brandUuid">
                    </el-option>
                </el-select>
                    厂家：<el-select v-model="form.factoryUuid" style="width: 150px" placeholder="请选择" size="mini">
                    <el-option
                            v-for="item in fOptions"
                            :key="item.factoryUuid"
                            :label="item.name"
                            :value="item.factoryUuid">
                    </el-option>
                </el-select>
                </fieldset>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save">确 定</el-button>
                </div>
            </el-dialog>

        </div>
    </div>
</template>

<script>
    export default{
        //加载表格数据
        created() {
            this.getCarCategory()
            this.getpOptions()
            this.getfOptions()
            },
    data(){
        return {
            dialogFormVisible:false,
            //
            multipleSelection:[],
            carCategpryList:[],
            //选中的id
            selectId:[],
            //品牌下拉框
            pOptions:[],
            //厂家下拉框
            fOptions:[],
            //条件查询条件
            BaseCarCategoryQuery:{},
            form:{
                name:"",
                bname:"",
                fname:"",
                eqs:"",
            },
            // 当前页号
            current: 1,
            // 分页列表
            pageSizeList: [2, 5, 10, 20, 50, 100],
            // 页大小
            limit: 5,
            // 总记录数
            total: 0,
    }},
    methods:{
        //条件查询
        setbCC(){
            this.getCarCategory()
        },

        // 获取表格数据
        async getCarCategory(){
            console.log(this.BaseCarCategoryQuery)
            // 获取所有点检项目的方法
            const _this = this
            const response = await _this.$axios.post(`/serviceBasedata/base-car-category/pageQuery/${this.current}/${this.limit}`,
                this.BaseCarCategoryQuery)
            console.log(response.data)
            this.carCategpryList = response.data.rows
            this.total = response.data.total
        },
        //获取品牌 数据
        async getpOptions(){
            const response = await this.$axios.get(`/serviceBasedata/carBand/queryAll`)
             this.pOptions= response.data.rows;
        },
        //获取厂家 数据
        async getfOptions(){

            const response = await this.$axios.get(`/serviceBasedata/carFactory/queryAllFName`)

            this.fOptions= response.data.list;
            console.log(this.fOptions)
        },
        //删除
        handleDelete() {
            if (this.multipleSelection.length == 0) {
                this.$message({
                    type: 'info',
                    message: '至少选择一条数据'
                })
                return
            }
            this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                for (var i = 0; i < this.selectId.length; i++) {
                    const _this = this
                    _this.$axios.delete(`/serviceBasedata/base-car-category/delete/${this.selectId[i]}`).then(result => {
                        console.log(result.data);
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        //刷新
                        this.getCarCategory()
                    })

                }
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        // 页大小改变事件
        handleSizeChange (val) {
            console.log(`每页 ${val} 条`)
            this.limit = val
            this.getCarCategory()
        },
        // 页号改变事件
        handleCurrentChange (val) {
            console.log(`当前页: ${val}`)
            this.current = val
            // 查询
            this.getCarCategory()
        },
        // 编辑数据回显
        async update(){
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
                    message:'只能选中一条数据'
                })
                return
            }
            this.dialogFormVisible=true
            // 获取要修改的门票编号
            console.log(this.selectId)
            const response = await this.$axios.get(`/serviceBasedata/base-car-category/queryById/${this.selectId}`)
            this.form = response.data.baseCarCategory
        },
        // 确认按钮点击事件
        async save(){
            // 添加或者修改操作
            const response = await this.$axios.post(`/serviceBasedata/base-car-category/${this.form.categoryUuid == null ? 'addBaseCarCategory' : 'updateBaseCarCategory'}`, this.form)
            if (response.code == 20000) {
                // 提示消息
                this.$message({
                    type: 'success',
                    message: '成功!'
                })
                this.dialogFormVisible = false
                // 刷新数据表格
                this.getCarCategory()
            }
        },

        //获取选中id
        handleSelectionChange(val) {
            this.multipleSelection=val;
            //获取选中id
            this.selectId=[]
            for(var i=0;i<val.length;i++){
                this.selectId.push(val[i].categoryUuid);
                console.log(this.selectId)
            }
        },
    }

    }
</script>

<style scoped>

</style>
