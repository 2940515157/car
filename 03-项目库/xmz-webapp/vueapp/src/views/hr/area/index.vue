<!-- - - - - - - - - - - - -->
<!--        区域管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <el-form :inline="true" :model="hrAreaQuery" class="demo-form-inline" size="mini" :label-position="left" label-width="100px">

        <el-form-item>
                大区：<el-select v-model="hrAreaQuery.orgUuid" style="width: 150px" placeholder="请选择" size="mini">
                <el-option label="--请选择--" value=""></el-option>
                <el-option
                        v-for="item in bigAreaList"
                        :key="item.orgName"
                        :label="item.orgName"
                        :value="item.orgUuid">
                </el-option>
            </el-select>
                名称：<el-input v-model="hrAreaQuery.areaName" style="width: 150px" size="mini" clearable></el-input>
                区域代码：<el-input v-model="hrAreaQuery.areaCode" style="width: 150px" size="mini" clearable></el-input>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="sea">查询</el-button><br>
                <el-button type="primary" size="mini" @click="dialogFormVisible=true,form={}">添加</el-button>
                <el-button type="success" size="mini" @click="update">编辑</el-button>
                <el-button type="danger" size="mini" @click="handleDelete">删除</el-button>
            </el-form-item>
        </el-form>
        <el-table
                border
                height="285px"
                ref="multipleTable"
                :data="hrAreaList"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="areaName"
                    label="名称"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="areaCode"
                    label="区域代码"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="orgName"
                    label="大区名称"
                    width="300">
            </el-table-column>
            <el-table-column
                    prop="remark"
                    label="备注"
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
            <el-dialog :title="form.areaUuid == null ? '新增区域信息':'编辑区域信息'"  :visible.sync="dialogFormVisible">
                <fieldset>
                    <legend>基本信息</legend><br>
                    大区：<el-select v-model="form.orgUuid" style="width: 150px" placeholder="请选择" size="mini">
                    <el-option
                            v-for="item in bigAreaList"
                            :key="item.orgName"
                            :label="item.orgName"
                            :value="item.orgUuid">
                    </el-option>
                </el-select>
                    区域名称：<el-input v-model="form.areaName" style="width: 150px" size="mini" clearable></el-input><br><br>
                    区域代码：<el-input v-model="form.areaCode" style="width: 150px" size="mini" clearable></el-input><br><br>
                    备注：<el-input
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 4}"
                        placeholder="请输入内容"
                        v-model="form.remark"
                        size="mini">
                </el-input>
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
            this.getHrArea()

            // 查询出全部的大区数据
            this.getAllBigAreaList()
        },
        data() {
            return {
                dialogFormVisible: false,
                //表格
                hrAreaList: [],
                //保存查询条件对象
                hrAreaQuery: {},
                //大区下拉框数据
                bigAreaList:[],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 表单数据
                form: {},
                left: null,
                multipleSelection: [],
                //选中的id
                selectId: [],


            }


        },


        methods: {

            // 获取表格数据
            async getHrArea() {
                // 获取所有点检项目的方法
                const _this = this
                const response = await _this.$axios.post(`/serviceHr/hrArea/page/${this.current}/${this.limit}`,
                    this.hrAreaQuery)
                console.log(response.data)
                this.hrAreaList = response.data.rows
                this.total = response.data.total

            },
            //查询
            sea() {
                this.getHrArea()
            },


            // 查询全部的大区数据
            getAllBigAreaList(){
                this.$axios.get('/serviceHr/hrArea/queryAll').then(resp =>{
                    this.bigAreaList=resp.data.list
                    console.log(1,this.bigAreaList)
                    //console.log("sadsdsd"+resp.data.hrAreaList);
                })
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
                this.multipleSelection = val;
                this.selectId = []
                for (var i = 0; i < val.length; i++) {
                    this.selectId.push(val[i].areaUuid);
                    console.log(this.selectId)
                }
            },
            //删除
            handleDelete(){
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
                        _this.$axios.delete(`/serviceHr/hrArea/${this.selectId[i]}`).then(result=>{
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getHrArea();
                            this.getRightList(this.hrAreaQuery);
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
                handleSizeChange(val)
                {
                    console.log(`每页 ${val} 条`)
                    this.limit = val
                    this.getHrArea()
                },

                // 页号改变事件
                handleCurrentChange(val)
                {
                    console.log(`当前页: ${val}`)
                    this.current = val
                    // 查询
                    this.getHrArea()
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
                console.log("要查询的id="+this.selectId[0])
                const response = await this.$axios.get(`/serviceHr/hrArea/queryById/${this.selectId[0]}`)
                this.form = response.data.hrArea;
                console.log(this.form)

            },
                // 确认按钮点击事件
                async save(){
                    // 添加或者修改操作
                    const response = await this.$axios.post(`/serviceHr/hrArea/${this.form.areaUuid == null ? 'add' : 'update'}`, this.form)
                    if (response.code == 20000) {
                        // 提示消息
                        this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        this.dialogFormVisible = false
                        // 刷新数据表格
                        this.getHrArea()
                    }
                },



            },



    }
</script>

<style scoped>

</style>
