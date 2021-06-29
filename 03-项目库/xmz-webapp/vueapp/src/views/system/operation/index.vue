<!-- - - - - - - - - - - - -->
<!--        操作管理        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <!-- 头部 -->
    <div class="header-panel">
      <el-row>
        <el-col :span="12">
          <el-form size="mini" :model="searchParams" :inline="true">
            <el-form-item label="名称">
              <el-input v-model="searchParams.name"></el-input>
            </el-form-item>
            <el-form-item label="说明">
              <el-input v-model="searchParams.memo"></el-input>
            </el-form-item>
            <el-form-item>
              <template v-if="$store.getters.btnCode.indexOf('op_search')>-1">
                <el-button type="primary" icon="el-icon-search" @click="page.current =1;loadTableData()">查询</el-button>
              </template>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12">
          <template v-if="$store.getters.btnCode.indexOf('op_remove')>-1">
            <el-button type="danger" style="float: right; margin:0 5px"
                       size="mini" icon="el-icon-delete"
                       @click="handleOpenFormDialogInDelete">删除
            </el-button>
          </template>
          <template v-if="$store.getters.btnCode.indexOf('op_update')>-1">
            <el-button type="warning" style="float: right;margin:0 5px"
                       size="mini" icon="el-icon-edit"
                       @click="handleOpenFormDialogInEdit">修改
            </el-button>
          </template>
          <template v-if="$store.getters.btnCode.indexOf('op_add')>-1">
            <el-button type="primary" style="float: right;margin:0 5px"
                       size="mini" icon="el-icon-plus"
                       @click="handleOpenFormDialogInAdd">增加
            </el-button>
          </template>
        </el-col>
      </el-row>
    </div>
    <!-- 分隔符 -->
    <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
    <!-- 表主体 -->
    <el-table ref="table" size="mini" highlight-current-row :height="tableHeight" :data="tableData" :fit="false" class="mainDataTable"
              style=" margin: 0 -20px"
              @current-change="currentTableRow"
              @select="selectionTableRow"
              @select-all="selectionAllTableRow"
    >
      <el-table-column type="index" align="center" width="50"></el-table-column>
      <el-table-column type="selection" align="center" width="50"></el-table-column>
      <el-table-column label="名称" prop="name" width="200"></el-table-column>
      <el-table-column label="按钮代码" prop="operationNo" width="250"></el-table-column>
      <el-table-column label="序号" prop="seqNo" width="200"></el-table-column>
      <el-table-column label="说明" prop="memo" width="441"></el-table-column>
      <el-table-column label="启用" prop="isActive" width="61">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isActive==='N'" type="warning" size="mini">否</el-tag>
          <el-tag v-else size="mini">是</el-tag>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div>
      <el-pagination
          :page-sizes="[10, 20, 50, 100]"
          :page-size.sync="page.size"
          :total="page.total"
          :current-page.sync="page.current"
          @size-change="loadTableData"
          @current-change="loadTableData"
          layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>
    <!--表单对话框-->
    <el-dialog :visible.sync="formDialogNode.visible" width="600px">
      <!-- 标题 -->
      <div slot="title">
        <span>{{ formDialogNode.title }}</span>
      </div>
      <!-- 主体 -->
      <div style="margin: -20px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
        <el-container>
          <el-main>
            <el-form :model="formDialogNode.fromData" :inline="true" size="mini" label-position="left">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="名称：" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.name" style="width: 185px;"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="按钮代码:" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.operationNo" style="width: 185px;"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="序号：" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.seqNo" style="width:185px"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="启用：" label-width="80px">
                    <el-radio-group v-model="formDialogNode.fromData.isActive">
                      <el-radio-button label="N">否</el-radio-button>
                      <el-radio-button label="Y">是</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="说明：" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.memo" type="textarea" :rows="2" style="width: 465px;"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </el-main>
        </el-container>
      </div>
      <!-- 尾页 -->
      <div slot="footer" style="margin: -5px -5px -5px -5px">
        <el-button size="mini" @click="formDialogNode.visible=false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handleConfirmFormDialog">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      tableHeight: window.innerHeight - 274,
      currentChangeKey: "",
      tableData: [],
      searchParams: {},
      page: {
        size: 10,
        total: 100,
        current: 1
      },
      formDialogNode: {
        visible: false,
        title: "",
        type: "",
        fromData: {},
      },
      resourceDialogNode: {
        visible: false,
      }
    }
  },
  methods: {
    handleOpenFormDialogInAdd() {
      const _this = this;
      _this.formDialogNode.title = "增加功能配置"
      _this.formDialogNode.type = "add"
      _this.formDialogNode.fromData = {}
      _this.formDialogNode.visible = true
    },
    handleOpenFormDialogInEdit() {
      const _this = this;
      if(_this.currentChangeKey){
        this.$refs.table.clearSelection()
        this.$refs.table.toggleRowSelection(_this.currentChangeKey)
        _this.$axios.get(`/serviceSystem/sys-operation/get/${_this.currentChangeKey.operationId}`).then((result)=>{
          _this.formDialogNode.title = "修改功能配置"
          _this.formDialogNode.type = "edit"
          _this.formDialogNode.fromData = result.data
          _this.formDialogNode.visible = true
        })
      }else {
        _this.$alert('你没有选择记录，不能修改','请选择记录',{
          confirmButtonText: '确定',
          callback: () => {
            this.$message({
              type: 'info',
              message: '请选择记录'
            });
          }
        })
      }

    },
    handleOpenFormDialogInDelete() {
      const _this = this;
      if(_this.currentChangeKey){
        return async function (){
          _this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            await _this.$axios.delete(`/serviceSystem/sys-operation/delete/${_this.currentChangeKey.operationId}`)
            _this.$message({
              type: 'success',
              message: '删除成功!'
            });
            _this.loadTableData();
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }()
      }else {
        _this.$alert('你没有选择记录，不能删除','请选择记录',{
          confirmButtonText: '确定',
          callback: () => {
            this.$message({
              type: 'info',
              message: '请选择记录'
            });
          }
        })
      }
    },
    currentTableRow(currentRow){
      this.$refs.table.clearSelection()
      this.$refs.table.toggleRowSelection(currentRow)
      this.currentChangeKey = currentRow
    },
    selectionTableRow(selection, row) {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow(row);
      this.currentChangeKey = row
    },
    selectionAllTableRow(){
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow();
      this.currentChangeKey = ""
    },
    loadTableData(){
      let _this = this;
      _this.tableData = [];
      _this.$axios.get(`/serviceSystem/sys-operation/queryPage/${_this.page.current}/${_this.page.size}`, {
        params: Object.assign(_this.searchParams)
      }).then(result => {
        _this.tableData = result.data.records
        _this.page.total = result.data.total
      })
    },
    handleConfirmFormDialog(){
      let _this = this;
      let _form = _this.formDialogNode.fromData;
      return async function (){
        let result;
        if(_this.formDialogNode.type === "add"){
          result = await _this.$axios.post("/serviceSystem/sys-operation/save",_form)
        }else {
          result = await _this.$axios.put("/serviceSystem/sys-operation/update",_form)
        }
        _this.$message(result.message)
        _this.loadTableData();
        _this.formDialogNode.visible = false
      }()

    },
  },
  created(){
    let _this = this;
    _this.loadTableData();
    document.querySelector("body").onresize = function () {
      _this.tableHeight = innerHeight - 274
    }
  }
}
</script>

<style scoped>
.mainDataTable {
  width: calc(100% + 40px);
  max-width: calc(100% + 40px);
}

.header-panel {
  margin: -10px;
}
</style>
