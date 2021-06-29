<!-- - - - - - - - - - - - -->
<!--        车辆厂家        -->
<!-- - - - - - - - - - - - -->
<!-- - - - - - - - - - - - -->
<!--        车辆厂家        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <el-form :inline="true" :model="baseFactoryQuery" class="demo-form-inline" size="mini" :label-position="left" label-width="100px">

        <el-form-item>
                厂家简称：<el-input v-model="baseFactoryQuery.name" style="width: 180px" clearable="请输入厂家简称"></el-input>
                厂家全称：<el-input v-model="baseFactoryQuery.fullName" style="width: 180px" clearable="请输入厂家全称"></el-input>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="sea">查询</el-button><br>
                <el-button type="primary" size="mini" @click="dialogFormVisible=true,form = {}">添加</el-button>
                <el-button type="success" size="mini" @click="update">编辑</el-button>
                <el-button type="danger" size="mini" @click="handleDelete">删除</el-button>

            </el-form-item>
        </el-form>

        <el-table
                border
                height="288px"
                ref="multipleTable"
                :data="carFactoryList"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="厂家简称"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="fullName"
                    label="厂家全称"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="seq"
                    label="排序"
                    show-overflow-tooltip>
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
        <div>
            <!--添加-->
            <el-dialog :title="form.factoryUuid == null ? '新增厂家信息':'编辑厂家信息'"  :visible.sync="dialogFormVisible">
                <fieldset>
                    <legend>基本信息</legend><br>
                    厂家全称：<el-input v-model="form.fullName" style="width: 346px" size="mini"></el-input><br><br>
                    厂家简称：<el-input v-model="form.name" style="width: 150px" size="mini"></el-input>
                    排序：<el-input v-model="form.seq" style="width: 150px" size="mini"></el-input><br><br>

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
    export default {
        created() {
            this.getFactory()
        },
        data() {
            return {
                dialogFormVisible:false,
                //表格
                carFactoryList:[],
                //保存查询条件的对象
                baseFactoryQuery: {},
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 表单数据
                form : {},
                left : null,
                multipleSelection: [],
                //选中的id
                selectId:[],
            }
        },

        methods: {
            // 获取表格数据
            async getFactory() {
                // 获取所有点检项目的方法
                const _this = this
                const response = await _this.$axios.post(`/serviceBasedata/carFactory/page/${this.current}/${this.limit}`,
                    this.baseFactoryQuery)
                console.log(response.data)
                this.carFactoryList = response.data.rows
                this.total = response.data.total


            },

            //查询
            sea(){
                this.getFactory()
            },

            toggleSelection(rows) {

                if (rows) {
                    rows.forEach(row => {
                        this.$refs.multipleTable.toggleRowSelection(row);
                    });
                } else {
                    this.$refs.multipleTable.clearSelection();
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection=val;
                this.selectId=[]
                for(var i=0;i<val.length;i++){
                    this.selectId.push(val[i].factoryUuid);
                    console.log(this.selectId)
                }
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
                        _this.$axios.delete(`/serviceBasedata/carFactory/${this.selectId[i]}`).then(result => {
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getFactory();
                            this.getRightList(this.baseFactoryQuery);
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
                this.getFactory()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getFactory()
            },
            // 编辑按钮点击事件
            async update(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                this.dialogFormVisible=true
                // 通过Id查询
                console.log(this.selectId)
                const response = await this.$axios.get(`/serviceBasedata/carFactory/queryById/${this.selectId}`)
                this.form = response.data.carFactory

            },
            // 确认按钮点击事件
            async save(){
                // 添加或者修改操作
                const response = await this.$axios.post(`/serviceBasedata/carFactory/${this.form.factoryUuid == null ? 'add' : 'update'}`, this.form)
                if (response.code == 20000) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    this.dialogFormVisible = false
                    // 刷新数据表格
                    this.getFactory()
                }
            }
        },



    }


</script>

<style scoped>

</style>

