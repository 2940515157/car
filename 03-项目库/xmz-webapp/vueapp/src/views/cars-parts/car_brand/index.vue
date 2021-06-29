<!-- - - - - - - - - - - - -->
<!--        车辆品牌        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <el-form :inline="true" :model="baseCarQuery" class="demo-form-inline" size="mini" :label-position="left" label-width="100px">
            <el-form-item>
                品牌名称：<el-input v-model="baseCarQuery.name" style="width: 150px" clearable="请输入品牌" ></el-input>
                类型：<el-select v-model="baseCarQuery.type" style="width: 150px" clearable="请选择">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>
                <el-button type="primary" icon="el-icon-search" @click="sea">查询</el-button><br>
                <el-button type="primary" size="mini" @click="dialogFormVisible=true,form = {}">添加</el-button>
                <el-button type="success" size="mini" @click="update">编辑</el-button>
                <el-button type="danger" size="mini" @click="handleDelete">删除</el-button>
            </el-form-item>
        </el-form>
        <el-table
                border
                height="286px"
                ref="multipleTable"
                :data="carBanList"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">

            <el-table-column
                    type="selection"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="品牌名称"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="namePy"
                    label="英文名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="类型"
                    width="60">
                <template slot-scope="scope">
                    <span v-if="scope.row.type=='car'">
                        车辆
                    </span>
                    <span v-if="scope.row.type=='part'">
                        配件
                    </span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="createdBy"
                    label="创建人"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="createdDate"
                    label="创建时间"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="updatedBy"
                    label="修改人"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="updatedDate"
                    label="修改时间"
                    width="180">
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
            <el-dialog :title="form.brandUuid == null ? '新增品牌信息':'编辑品牌信息'"  :visible.sync="dialogFormVisible">
                <fieldset>
                    <legend>基本信息</legend><br>
                    品牌名称：<el-input v-model="form.name" style="width: 150px" size="mini"></el-input>
                    英文名称：<el-input v-model="form.namePy" style="width: 150px" size="mini"></el-input><br><br>
                    类型：<el-select v-model="form.type" style="width: 150px" placeholder="请选择" size="mini">
                    <el-option
                            v-for="item in options2"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
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
            this.getCar()
        },
        data() {
            return {
                dialogFormVisible:false,
                //表格
                carBanList : [],
                // 保存查询条件的对象
                baseCarQuery: {},
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
                car:'car',
                part:'part',
                options: [{
                    value: 'car',
                    label: '车辆'
                }, {
                    value: 'part',
                    label: '配件'
                }, ],
                options2: [{
                    value: 'car',
                    label: '车辆'
                }, {
                    value: 'part',
                    label: '配件'
                }, ],


            }
        },
        methods: {
            // 获取表格数据
            async getCar(){
                // 获取所有点检项目的方法
                    const _this = this
                    const response = await _this.$axios.post(`/serviceBasedata/carBand/page/${this.current}/${this.limit}`,
                        this.baseCarQuery)
                    console.log(response.data)
                    this.carBanList = response.data.rows
                    this.total = response.data.total
            },

            //查询
            sea(){
                this.getCar()
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
                    this.selectId.push(val[i].brandUuid);
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
                        _this.$axios.delete(`/serviceBasedata/carBand/${this.selectId[i]}`).then(result=>{
                            console.log(result.data);
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            //刷新
                            this.getCar();
                            this.getRightList(this.baseCarQuery);
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
                this.getCar()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getCar()
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
                    const response = await this.$axios.get(`/serviceBasedata/carBand/queryById/${this.selectId}`)
                    this.form = response.data.carBrand

            },
            // 确认按钮点击事件
            async save(){
                // 添加或者修改操作
                const response = await this.$axios.post(`/serviceBasedata/carBand/${this.form.brandUuid == null ? 'add' : 'update'}`, this.form)
                if (response.code == 20000) {
                    // 提示消息

                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    this.dialogFormVisible = false
                    // 刷新数据表格
                    this.getCar()
                }
            },


        },

    }
</script>

<style scoped>

</style>

