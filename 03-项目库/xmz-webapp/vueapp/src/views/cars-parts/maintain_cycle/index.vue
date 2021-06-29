<template>
    <div id="parent">
        <!--搜索框-->
        <div id="search">
            <el-form :inline="true" :model="BMTSQuery">
                <el-form-item label="保养类型">
                    <el-select @change="mcSelectChange" :disabled="maintainTypeFlag" filterable style="width: 130px"
                               v-model="BMTSQuery.maintainType" placeholder="请选择">
                        <el-option
                                v-for="(item, $index)  in BMTStypedata"
                                :key="$index"
                                :label="item.dictname"
                                :value="item.dictid">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="服务目录编号">
                    <el-input v-model="BMTSQuery.catalogCode"></el-input>
                </el-form-item>
                <el-form-item label="服务目录名称">
                    <el-input v-model="BMTSQuery.catalogName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="default" icon="el-icon-search" @click="search"></el-button>
                    <el-button type="default" icon="el-icon-close" @click="clear"></el-button>
                </el-form-item>
            </el-form>
        </div>
        <!-- 工具栏 -->
        <div id="entryTool">
            <el-button type="primary" size="mini" @click="add" class="el-icon-plus">增加</el-button>
            <el-button type="warning" size="mini" @click="edit" class="el-icon-edit">修改</el-button>
            <el-button type="danger" size="mini" @click="del" class="el-icon-delete">删除</el-button>
        </div>
        <!--数据表格-->
        <el-table
                :data="BMTSData"
                style="width: 100%"
                max-height="350"
                highlight-current-row
                @current-change="tableCurrentChange"
                ref="multipleTable"
                @row-click="tableClick">
            <el-table-column
                    type="selection"
                    width="70">
            </el-table-column>
            <el-table-column
                    type="index"
                    width="70">
            </el-table-column>
            <el-table-column
                    prop="dictname"
                    label="保养类型"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="catalogCode"
                    label="服务目录编号"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="catalogName"
                    label="服务目录名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="remark"
                    label="备注"
                    width="120">
            </el-table-column>

        </el-table>
        <!--分页-->
        <el-pagination
                @size-change="sizeChange"
                @current-change="currentChange"
                :current-page="current"
                :page-sizes="pageSizeList"
                :page-size="size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
        <!-- 新增或编辑模态框-->
        <el-dialog :title="mtsUuid == '' ? '新增服务信息':'编辑服务信息'" :visible.sync="dialogFormVisible">

            <el-form :model="BMTSform" label-width="100px">
                <fieldset>
                    <legend>基本信息</legend>
                    <el-form-item label="服务目录名称" :label-width="formLabelWidth">
                        <el-input v-model="BMTSform.catalogName"></el-input>
                    </el-form-item>
                    <el-form-item label="保养类型" :label-width="formLabelWidth">
                        <el-select filterable style="width: 150px" v-model="BMTSform.maintainType"
                                   placeholder="请选择">
                            <el-option
                                    v-for="item in maintainTypeData"
                                    :key="item.dictid"
                                    :label="item.dictname"
                                    :value="item.dictid">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="备注" :label-width="formLabelWidth">
                        <el-input type="textarea" v-model="BMTSform.remark"></el-input>
                    </el-form-item>
                </fieldset>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false,BMTSform={}">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                //保存服务关系的变量
                BMTSData: [],
                BMTStypedata:[],
                //当前页号
                current: 1,
                //分页列表
                pageSizeList: [10, 50, 100, 200, 300],
                //页大小
                size: 10,
                //总记录数
                total: 0,
                //保存查询条件的对象
                BMTSQuery: {},
                //下拉框数据
                brandSelectData: [],
                //所有保养类型信息
                maintainTypeData: [],
                //封装表单数据的对象
                BMTSform: {},
                mtsUuid: '',
                //是否显示对话框
                dialogFormVisible: false,
                // 表单元素的label的宽度
                formLabelWidth: '120px',
                maintainTypeFlag:false
            }
        },
        methods: {
            //获取表格所有数据
            async getBMTSData() {
                const result = await this.$axios.post(`/serviceCarsparts/base-maintain-type-serv/queryList/${this.current}/${this.size}`, this.BMTSQuery)
                this.BMTSData = result.data.rows
                this.total = result.data.total

            },
            //查询保养类型数据
            async getMaintainTypeData() {
                const result = await this.$axios.get(`/serviceCarsparts/base-maintain-type-serv/queryDict/DICT_MAINTAIN_TYPE`)
                this.maintainTypeData = result.data

            },
            //查询下拉框数据
            async getMaintainservData() {
                const result = await this.$axios.post(`/serviceCarsparts/base-maintain-type-serv/select`, this.BMTSQuery)
                this.BMTStypedata = result.data.rows
            },
            //下拉框改变事件
            brandSelectChange() {
                this.getMaintainservData();
                this.getBMTSData();
            },
            ccSelectChange() {
                this.maintainTypeFlag = false;
                this.getMaintainservData();
                this.getBMTSData();
            },
            mcSelectChange() {
                this.getBMTSData();
            },
            //点击添加按钮
            add() {
                this.mtsUuid = ''
                this.BMTSform = {}
                this.dialogFormVisible = true
                this.$refs.multipleTable.clearSelection();

            },
            //修改
            async edit() {
                if (this.mtsUuid == '') {
                    alert("请选中一行记录")
                    return
                }

                const result = await this.$axios.get(`/serviceCarsparts/base-maintain-type-serv/queryById/${this.mtsUuid}`)
                this.BMTSform = result.data
                console.log(result.data)
                this.dialogFormVisible = true


            },
            //修改或保存
            async save() {
                // eslint-disable-next-line no-unused-vars
                let formEntry = {};

                this.dialogFormVisible = false
                if (this.mtsUuid == '') {
                    formEntry = this.BMTSform
                } else {
                    formEntry = {
                        dictname: this.BMTSform.dictname, catalogName: this.BMTSform.catalogName, remark: this.BMTSform.remark,mtsUuid: this.mtsUuid
                    }
                }

                //添加或者修改操作
                const result = await this.$axios.post(`/serviceCarsparts/base-maintain-type-serv/${this.mtsUuid == '' ? 'add' : 'update'}`, formEntry)
                if (result.flag) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '操作成功!'
                    })

                    // 刷新数据表格
                    await this.getBMTSData()

                }
            },
            //删除
            del() {
                this.$confirm('将删除此数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    // 删除操作（物理，逻辑）
                    const result = await this.$axios.delete(`/serviceCarsparts/base-maintain-type-serv/delete/${this.mtsUuid}`)
                    if (result.flag) {
                        // 提示消息
                        this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        // 刷新数据表格
                        await this.getBMTSData()
                        this.$refs.multipleTable.clearSelection();

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            },
            // 查询
            search() {
                this.getBMTSData();
                this.mtsUuid = ''
            },
            // 清空
            clear() {
                this.BMTSQuery = {};
                this.maintainTypeFlag = false;
                this.getMaintainservData();
                this.getBMTSData();

            },
            //选中行事件
            tableCurrentChange(val) {
                this.mtsUuid = val.mtsUuid;
            },
            //行点击事件
            tableClick(row) {
                this.$refs.multipleTable.clearSelection();
                this.$refs.multipleTable.toggleRowSelection(row);
            },
            // 页号改变事件
            currentChange(val) {
                this.current = val;
                this.getBMTSData();
            },
            // 页大小改变事件
            sizeChange(val) {
                this.size = val;
                this.getBMTSData();
            }
        },
        created() {
            this.getBMTSData();
            this.getMaintainservData();
            this.getMaintainTypeData();

        }
    }
</script>

<style scoped>

</style>
