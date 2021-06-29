<!-- - - - - - - - - - - - -->
<!--        批量开卡        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <h1>还在开发中......</h1>



        </el-card>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            return{
                options: [{
                    id: '1',
                    label: '是'
                }, {
                    id: '2',
                    label: '否'
                }]
                ,
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 2,
                // 总记录数
                total: 0,
                // 封装表单数据的对象
                form: {},
                //批量开卡
                addPiLiang:false,
                // 表单元素的label的宽度
                formLabelWidth: '50px',
                formLabelWidth1: '100px',
                tableData: [{
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-08',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-06',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-07',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }],
            }
        },
        methods:{
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val

            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
            }
        }
    }
</script>
<style scoped>
    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>
