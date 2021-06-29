<!-- - - - - - - - - - - - -->
<!--       营销组合产品      -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <el-form :inline="true" :model="productQuery" class="demo-form-inline" size="mini">
                <el-form-item label="名称">
                    <el-input  v-model="productQuery.acpName" placeholder="产品"></el-input>
                </el-form-item>

                <el-form-item label="状态">
                    <el-select v-model="productQuery.status" placeholder="请选择" clearable>
                        <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.label"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="SerachChaKan">查询</el-button>
                </el-form-item>
            </el-form>
            <el-button type="primary" size="mini" icon="el-icon-plus"  >增加</el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit"  >编辑</el-button>
            <el-button type="danger"  size="mini" icon="el-icon-plus">选择发行范围</el-button>
            <el-button type="primary" size="mini"  icon="el-icon-delete">删除</el-button>
            <el-button type="primary" size="mini" icon="el-icon-upload2" >上架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" >下架</el-button>
            <el-button type="primary" size="mini" icon="el-icon-download" >强制退回</el-button>

            <el-table
                    size="mini"
                    ref="multipleTable"
                    :data="tableData"
                    tooltip-effect="dark"
                    style="width: 100%"
                    max-height="350px"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="acpName"
                        label="名称"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="totalPrice"
                        label="售价"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="startDate"
                        label="有效开始日期"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="endDate"
                        label="有效结束日期"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="statusName"
                        label="状态"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="bizTypeName"
                        label="业务分类"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="serviceTypeName"
                        label="服务类型"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="CREATED_BY"
                        label="创建人"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注"
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
        </el-card>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            return{
                //状态
                options: [{
                    id: 'init',
                    label: '未上架'
                },{
                    id: 'up',
                    label: '上架'
                }, {
                    id: 'under',
                    label: '下架'
                },{
                    id: 'noPerfect',
                    label: '待完善'
                } ],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //查询条件
                productQuery:{},
                tableData: [],
            }
        },
        created() {
            this.getCompositeProduct();
        },
        methods:{

            //营销产品分页条件查询
            getCompositeProduct(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-composite-product/queryAllPage/${this.current}/${this.limit}`,_this.productQuery).then(result=>{
                    _this.tableData=result.data.rows;
                    _this.total=result.data.total;
                })
            },
            //点击查询
            SerachChaKan(){
                this.getCompositeProduct();
            },
            //表格选择事件
            handleSelectionChange(){

            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getCompositeProduct();

            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getCompositeProduct();
            }
        }
    }
</script>
<style scoped>


    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>

