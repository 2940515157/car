<!-- - - - - - - - - - - - -->
<!--        点检目录        -->
<!-- - - - - - - - - - - - -->

<template>
   <div>
     <el-form :inline="true" :model="servCheckQuery" class="demo-form-inline"  :label-position="left" label-width="100px">
       <el-form-item>
       目录名称：<el-input v-model="servCheckQuery.directoryName" size="mini" style="width:160px" ></el-input>&emsp;
       <el-button type="primary" @click="sea" size="mini">查询</el-button><br>
     <el-button type="success" @click="add" size="mini">增加</el-button>
     <el-button type="primary" @click="update" size="mini">编辑</el-button>
     <el-button type="danger" @click="remove" size="mini">删除</el-button>
     </el-form-item>
     </el-form>
     <el-table
       :data="checkList"
       @selection-change="handleSelectionChange"
       border
       style="width: 100%"><el-table-column
         label="序号"
         align="center"
         type="index"
         width="180">
     </el-table-column>
       <el-table-column
         prop="cbox"
         type="selection"
         label=""
         width="180">
       </el-table-column>
       <el-table-column
         prop="directoryName"
         label="目录名称">
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
       <el-dialog :title="form.scdUuid == null ? '新增点检信息':'编辑点检信息'"  :visible.sync="dialogFormVisible">
           <el-form :model="form">
             <el-form-item label="目录名称" :label-width="formLabelWidth" >
               <el-input v-model="form.directoryName" autocomplete="off"></el-input>
             </el-form-item>
             <el-form-item :label-width="formButtonWidth">
               <el-button type="primary" @click="save">保存</el-button>
               <el-button @click="dialogFormVisible=false">取消</el-button>
             </el-form-item>
           </el-form>
       </el-dialog>

     </div>
   </div>
</template>

<script>
// import check from "@/api/check";
    export default {
        data() {
              return {
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //选中的id
                selectId:[],
                // 保存查询条件的对象
                servCheckQuery: {},

                checkQuery: {},
                formButtonWidth:'550px',
                formLabelWidth:'100px',
                dialogFormVisible: false,

                // 表单数据
                left : null,
                multipleSelection: [],
                form: {
                  checkname: '',
                },
                checkList: {},
              }
            },
            methods:{
                // 获取数据绑定到表格
                 async getList() {
                   console.log(this.servCheckQuery)
                  const _this = this
                  const response = await _this.$axios.post(`/serviceServiceProduct/serv-checkup-directory/page/${this.current}/${this.limit}`,
                      this.servCheckQuery)
                  console.log(response.data)
                  this.checkList = response.data.rows
                  this.total = response.data.total
                },
              //添加方法
              add(){
                  this.form={}

                  this.dialogFormVisible=true
              },
              //编辑方法
              async update(){
                if(this.multipleSelection.length==0){
                  this.$message({
                    type:'info',
                    message:'至少选择一条数据'
                  })
                  return
                }
                this.dialogFormVisible=true
                // 获取要修改的门票编号
                console.log(this.selectId)
                const response = await this.$axios.get(`/serviceServiceProduct/serv-checkup-directory/queryById/${this.selectId}`)
                this.form = response.data.servCheckupDirectory

              },
              // 确认按钮点击事件
              async save(){
                // 添加或者修改操作
                const response = await this.$axios.post(`/serviceServiceProduct/serv-checkup-directory/${this.form.scdUuid == null ? 'add' : 'update'}`, this.form)
                if (response.code == 20000) {
                  // 提示消息
                  this.$message({
                    type: 'success',
                    message: '成功!'
                  })
                  this.dialogFormVisible = false
                  // 刷新数据表格
                  this.getList()
                }
              },
              //查询
              sea(){
                   //console.log(this.servCheckQuery)
                   this.getList()
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
              // 获取行数据
              handleSelectionChange(val) {
                this.multipleSelection=val;
                this.selectId=[]
                for(var i=0;i<val.length;i++){
                  this.selectId.push(val[i].scdUuid);
                  console.log(this.selectId)
                }
              },
              //删除方法
              remove(){
                console.log(this.multipleSelection.length)
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
                    _this.$axios.delete(`/serviceServiceProduct/serv-checkup-directory/${this.selectId[i]}`).then(result=>{
                      console.log(result.data);
                      this.$message({
                        type: 'success',
                        message: '删除成功!'
                      });
                      //刷新
                      this.getList();
                      this.getRightList(this.servCheckQuery);
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
                this.getList()
              },
              // 页号改变事件
              handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getList()
              },
            },
      created() {
          this.getList()
      }
    }


</script>

<style scoped>

</style>
