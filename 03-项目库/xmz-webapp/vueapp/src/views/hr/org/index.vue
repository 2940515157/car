<template>
    <div>
        <!--查询表单组件-->
        <el-form :inline="true" :model="orgQuery" class="demo-form-inline">

            <el-form-item label="部门编号">
                <el-input v-model="orgQuery.orgNo"></el-input>
            </el-form-item>
            <el-form-item label="部门名称">
                <el-input v-model="orgQuery.orgName"></el-input>
            </el-form-item>
            <el-form-item label="是否停用">
                <el-select v-model="orgQuery.isDisabled" placeholder="---请选择---">
                    <el-option value="Y" label="是"></el-option>
                    <el-option value="N" label="否"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
                <el-button type="info" icon="el-icon-close" @click="reset">清空</el-button>
            </el-form-item>

        </el-form>

        <!-- 工具栏 -->
        <div id="entryTool">
            <el-button type="primary" size="mini" @click="add" class="el-icon-plus">增加</el-button>
            <el-button type="warning" size="mini" @click="edit" class="el-icon-edit">修改</el-button>
            <el-button type="danger" size="mini" @click="del" class="el-icon-delete">删除</el-button>
        </div>

        <el-table
                :data="orgData"
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
                    prop="orgNo"
                    label="部门编号"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="orgName"
                    label="部门名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="orgPhone"
                    label="部门电话"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="orgManager"
                    label="部门负责人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="isDisabled"
                    label="是否停用">
            </el-table-column>
            <el-table-column
                    prop="isBigArea"
                    label="是否大区">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址">
            </el-table-column>
            <el-table-column
                    prop="remark"
                    label="备注">
            </el-table-column>
            <el-table-column
                    prop="seqNo"
                    label="排序">
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
        <el-dialog :title="orgUuid == '' ? '新增部门信息':'编辑部门信息'" :visible.sync="dialogFormVisible">

            <el-form :model="ORGform" label-width="100px">
                <fieldset>
                    <legend>基本信息</legend>
                <el-form-item label="部门名称" :label-width="formLabelWidth">
                    <el-input v-model="ORGform.orgName"></el-input>
                </el-form-item>
                <el-form-item label="部门编号" :label-width="formLabelWidth">
                    <el-input v-model="ORGform.orgNo"></el-input>
                </el-form-item>
                <el-form-item label="是否大区" :label-width="formLabelWidth">
                    <el-radio v-model="ORGform.isBigArea" label="Y">是</el-radio>
                    <el-radio v-model="ORGform.isBigArea" label="N">否</el-radio>
                </el-form-item>
                <el-form-item label="部门电话" :label-width="formLabelWidth">
                    <el-input v-model="ORGform.orgPhone"></el-input>
                </el-form-item>
                <el-form-item label="是否停用" :label-width="formLabelWidth">
                    <el-radio v-model="ORGform.isDisabled" label="Y">是</el-radio>
                    <el-radio v-model="ORGform.isDisabled" label="N">否</el-radio>
                </el-form-item>
                <el-form-item label="排序" :label-width="formLabelWidth">
                    <el-input v-model="ORGform.seqNo"></el-input>
                </el-form-item>
                <el-form-item label="地址" :label-width="formLabelWidth">
                    <el-input v-model="ORGform.address"></el-input>
                </el-form-item>
                <el-form-item label="备注" :label-width="formLabelWidth">
                    <el-input type="textarea" v-model="ORGform.remark"></el-input>
                </el-form-item>
                </fieldset>
            </el-form>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false,ORGform={}">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                //保存数据表格的变量
                orgData: [],
                //当前页号
                current: 1,
                //分页列表
                pageSizeList: [10, 50, 100, 200, 300],
                //页大小
                size: 10,
                //总记录数
                total: 0,
                //保存查询条件的对象
                orgQuery: {},
                //封装表单数据的对象
                ORGform: {},
                orgUuid: '',
                //是否显示对话框
                dialogFormVisible: false,
                // 表单元素的label的宽度
                formLabelWidth: '120px'
            }
        },
        methods: {
            //获取表格所有数据
            async getOrgData() {
                const result = await this.$axios.post(`/serviceHr/HrOrg/page/${this.current}/${this.size}`, this.orgQuery)
                this.orgData = result.data.rows
                this.total = result.data.total


            },
            //点击添加按钮
            add() {
                this.orgUuid = ''
                this.ORGform = {}
                this.dialogFormVisible = true
                this.$refs.multipleTable.clearSelection();

            },
            //修改
            async edit() {
                if (this.orgUuid == '') {
                    alert("请选中一行记录")
                    return
                }
                const result = await this.$axios.get(`/serviceHr/HrOrg/queryById/${this.orgUuid}`)
                this.ORGform = result.data.hrOrg
                this.dialogFormVisible = true


            },
            //修改或保存
            async save() {
                // eslint-disable-next-line no-unused-vars
                let formEntry = {};

                this.dialogFormVisible = false
                if (this.orgUuid == '') {
                    formEntry = this.ORGform
                } else {
                    formEntry = {
                        orgName: this.ORGform.orgName, orgNo: this.ORGform.orgNo, isBigArea: this.ORGform.isBigArea,
                        orgPhone: this.ORGform.orgPhone, isDisabled: this.ORGform.isDisabled, seqNo: this.ORGform.seqNo,
                        address: this.ORGform.address, remark: this.ORGform.remark,orgUuid: this.orgUuid
                    }
                }
                //添加或者修改操作
                const result = await this.$axios.post(`/serviceHr/HrOrg/${this.orgUuid == '' ? 'add' : 'update'}`, formEntry)
                if (result.flag) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    // 刷新数据表格
                    await this.getOrgData()

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
                    const result = await this.$axios.delete(`/serviceHr/HrOrg/delete/${this.orgUuid}`)
                    if (result.flag) {
                        // 提示消息
                        this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        // 刷新数据表格
                        await this.getOrgData()
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
                this.getOrgData();
                this.orgUuid = ''
            },
            // 清空
            reset() {
                this.orgQuery = {}
            },
            //选中行事件
            tableCurrentChange(val) {
                this.orgUuid = val.orgUuid;
            },
            //行点击事件
            tableClick(row) {
                this.$refs.multipleTable.clearSelection();
                this.$refs.multipleTable.toggleRowSelection(row);
            },
            // 页号改变事件
            currentChange(val) {
                this.current = val;
                this.getOrgData();
            },
            // 页大小改变事件
            sizeChange(val) {
                this.size = val;
                this.getOrgData();
            }
        },
        created() {
            this.getOrgData();
        }
    }
</script>

<style scoped>

</style>
