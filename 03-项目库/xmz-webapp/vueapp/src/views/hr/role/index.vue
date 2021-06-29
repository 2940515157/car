<!-- - - - - - - - - -  - - - -->
<!--        用户角色管理        -->
<!-- - - - - - - - - - -  - - -->

<template>
  <div>
    <!-- 头部 -->
    <div class="header-panel">
      <el-row>
        <el-col :span="16">
          <el-form size="mini" :model="searchParams" :inline="true">
            <el-form-item label="角色名称">
              <el-input v-model="searchParams.name" style="width: 120px"></el-input>
            </el-form-item>
            <el-form-item label="角色代码">
              <el-input v-model="searchParams.roleNo" style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" @click="loadTableData">查询</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="8">
          <el-button type="" style="float: right;margin:0 5px"
                     plain size="mini" @click="handleOpenPermissionsFormDialogNode">权限管理
          </el-button>
          <el-button type="danger" style="float: right; margin:0 5px"
                     plain size="mini" @click="handleOpenFormDialogInDelete">删除
          </el-button>
          <el-button type="primary" style="float: right;margin:0 5px"
                     plain size="mini" @click="handleOpenFormDialogInEdit">编辑
          </el-button>
          <el-button type="primary" style="float: right;margin:0 5px"
                     plain size="mini" @click="handleOpenFormDialogInAdd">增加
          </el-button>
        </el-col>
      </el-row>
    </div>
    <!-- 分隔符 -->
    <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
    <!-- 表主体 -->
    <el-table ref="table" size="mini" highlight-current-row :height="tableHeight" :data="tableData" :fit="false"
              class="mainDataTable"
              style=" margin: 0 -20px"
              @current-change="currentTableRow"
              @select="selectionTableRow"
              @select-all="selectionAllTableRow"
    >
      <el-table-column type="index" align="center" width="50"></el-table-column>
      <el-table-column type="selection" align="center" width="50"></el-table-column>
      <el-table-column label="角色名称" width="300" prop="name"></el-table-column>
      <el-table-column label="角色代码" width="300" prop="roleNo"></el-table-column>
      <el-table-column label="角色归属" width="100" prop="roleOrigin">
        <template slot-scope="{row:{roleOrigin}}">
          {{ resourceOriginsMap[roleOrigin] }}
        </template>
      </el-table-column>
      <el-table-column label="角色描述" width="400" prop="memo"></el-table-column>
      <el-table-column label="启用" width="50">
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
    <!-- 表单对话框 -->
    <el-dialog :visible.sync="formDialogNode.visible" width="600px">
      <!-- 标题 -->
      <div slot="title">
        <span>{{ formDialogNode.title }}</span>
      </div>
      <!-- 主体 -->
      <div style="margin: -20px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
        <el-container>
          <el-main>
            <el-form :model="formDialogNode.formData" :inline="true" size="mini" label-position="left">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="角色名称:" label-width="80px">
                    <el-input v-model="formDialogNode.formData.name" style="width:185px"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="角色代码:" label-width="80px">
                    <el-input v-model="formDialogNode.formData.roleNo" style="width:185px"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="角色归属:" label-width="80px">
                    <el-select style="width:185px" v-model="formDialogNode.formData.roleOrigin" clearable>
                      <el-option v-for="(key, value) in resourceOriginsMap" :key="key" :label="key"
                                 :value="value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="是否启用:" label-width="80px">
                    <el-checkbox v-model="formDialogNode.formData.isActive"
                                 true-label="Y" false-label="N"></el-checkbox>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="角色说明:" label-width="80px">
                    <el-input v-model="formDialogNode.formData.memo" type="textarea" :rows="2"
                              style="width: 465px;"></el-input>
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
        <el-button size="mini" @click="handleSubmitClick" :disabled="formDialogNode.subDisabled"
                   type="primary">确 定
        </el-button>
      </div>
    </el-dialog>

    <!-- 权限操作配置对话框 -->
    <el-dialog :visible.sync="permissionsFormDialogNode.visible" width="80%">
      <!-- 标题 -->
      <div slot="title">
        <span>{{ permissionsFormDialogNode.title }}</span>
      </div>
      <!-- 主体 -->
      <div style="margin: -20px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
        <el-container style="background-color: #fafaff">
          <!-- 树 -->
          <el-aside>
            <el-main style="padding-right: 0">
              <el-card shadow="hover" body-style="height: 370px">
                <el-radio-group v-model="permissionsFormDialogNode.resourceOrigin" @change="handleChangeResourceOrigin"
                                size="mini">
                  <el-radio-button label="base">总部</el-radio-button>
                  <el-radio-button label="store">门店</el-radio-button>
                </el-radio-group>
                <!-- 分隔符 -->
                <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
                <div style="height: 330px">
                  <el-scrollbar style="height: 99%;" wrap-style="overflow:hidden scroll;">
                    <el-tree
                        icon-class="el-icon-folder"
                        :data="permissionsFormDialogNode.treeData"
                        lazy :load="lazyLoadTreeData"
                        :props="{label: 'resourceName',children: 'children',isLeaf: data => !data.hasChildren}"
                        @node-click="handleTreeNodeClick"
                    ></el-tree>
                  </el-scrollbar>
                </div>
              </el-card>
            </el-main>
          </el-aside>
          <!-- 表 -->
          <el-main>
            <el-card shadow="hover" body-style="height: 370px">
              <el-table :data="permissionsFormDialogNode.tableData"
                        ref="dialogTable" size="mini" :height="permissionsFormDialogNode.tableHeight"
                        border
                        stripe
                        :fit="false" class="mainDataTable"
                        style=" margin: 0 -20px">
                <el-table-column type="index" align="center" width="50" fixed></el-table-column>
                <el-table-column label="资源名称" prop="resName" width="150" fixed></el-table-column>
                <el-table-column v-for="(item) in permissionsFormDialogNode.tableColumns" :key="item.operId"
                                 :label="item.operName"
                                 align="center" header-align="left">
                  <template slot-scope="{row}">
                    <el-checkbox
                        v-if="row[item.operId]&&row[item.operId]['isShow']==='Y'"
                        v-model="row[item.operId]['isActive']"
                        true-label="Y" false-label="N"
                        @change="handleChangePerCheckbox(row[item.operId])"
                    >
                    </el-checkbox>
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </el-main>
        </el-container>
      </div>
      <!-- 尾页 -->
      <div slot="footer" style="margin: -5px -5px -5px -5px">
        <el-button size="mini" @click="permissionsFormDialogNode.visible=false">取 消</el-button>
        <el-button size="mini" @click="handlePermissionsFormDialogSubmitClick" :disabled="permissionsFormDialogNode.subDisabled" type="primary">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      searchParams: {},
      tableHeight: window.innerHeight - 274,
      tableData: [],
      currentChangeKey: "",
      page: {
        size: 20,
        total: 0,
        current: 1,
        pages: 5,
      },
      resourceOriginsMap: {
        base: "总部",
        eb: "菜单",
        store: "门店"
      },
      formDialogNode: {
        visible: false,
        title: "标题",
        formData: {}
      },
      permissionsFormDialogNode: {
        visible: false,
        tableHeight: 370,
        title: "操作配置",
        treeData: [],
        currentChangeKey: {},
        changeData: new Set(),
        subDisabled: true,
        resourceOrigin: "base",
        first: true,
        tableData: [],
        tableColumns: []
      },
    }
  },
  methods: {
    handleOpenFormDialogInAdd() {
      const _this = this;
      _this.formDialogNode.title = "增加角色"
      _this.formDialogNode.type = "add"
      _this.formDialogNode.formData = {isShow: 1}
      _this.formDialogNode.visible = true
    },
    handleOpenFormDialogInEdit() {
      const _this = this;
      if (_this.currentChangeKey) {
        this.$refs.table.clearSelection()
        this.$refs.table.toggleRowSelection(_this.currentChangeKey)
        _this.$axios.get(`/serviceHr/sys-role/get/${_this.currentChangeKey.roleId}`).then((result) => {
          _this.formDialogNode.title = "修改角色"
          _this.formDialogNode.type = "edit"
          _this.formDialogNode.formData = result.data
          _this.formDialogNode.visible = true
        })
      } else {
        _this.$alert('你没有选择记录，不能修改', '请选择记录', {
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
      if (_this.currentChangeKey) {
        return async function () {
          _this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            await _this.$axios.delete(`/serviceHr/sys-role/delete/${_this.currentChangeKey.roleId}`)
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
      } else {
        _this.$alert('你没有选择记录，不能删除', '请选择记录', {
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
    handleOpenPermissionsFormDialogNode() {
      const _this = this
      if (_this.currentChangeKey) {
        let _formDialogNode = this.permissionsFormDialogNode;
        _formDialogNode.visible = true
        _formDialogNode.treeData = []
        _formDialogNode.tableData = []
        _formDialogNode.changeData.clear();
        _formDialogNode.subDisabled = true;
        if (!_formDialogNode.first) _this.lazyLoadTreeData()
        _formDialogNode.first = false
      } else {
        _this.$alert('你没有选择记录，不能修改', '请选择记录', {
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
    handleSubmitClick() {
      let _this = this;
      let _form = _this.formDialogNode.formData;
      return async function () {
        let result;
        if (_this.formDialogNode.type === "add") {
          result = await _this.$axios.post("/serviceHr/sys-role/save", _form)
        } else {
          result = await _this.$axios.put("/serviceHr/sys-role/update", _form)
        }
        _this.$message(result.message)
        _this.loadTableData();
        _this.formDialogNode.visible = false
      }()
    },
    handleChangeResourceOrigin() {
      let _this = this;
      _this.permissionsFormDialogNode.treeData = [];
      _this.lazyLoadTreeData()
    },
    handleTreeNodeClick(data) {

      this.permissionsFormDialogNode.currentChangeKey = data
      this.loadPrsTableData()
      this.permissionsFormDialogNode.changeData.clear();
      this.permissionsFormDialogNode.subDisabled = true;
      // this.lazyLoadDialogTableData(true)

    },
    handleChangePerCheckbox(val){
      this.permissionsFormDialogNode.changeData.add(Object.assign(val,{roleId: this.currentChangeKey.roleId}))
      this.permissionsFormDialogNode.subDisabled = false
    },
    handlePermissionsFormDialogSubmitClick(){
      let _this = this;
      let changeData = this.permissionsFormDialogNode.changeData;
      let formDialogNode = this.permissionsFormDialogNode;
      console.log(changeData)
      _this.$axios.post("/serviceHr/sys-role/updatePrs",
          {
            prs: JSON.stringify(Array.from(changeData.values()))
          }
      ).then(()=>{
        _this.$message.success("执行成功")
        changeData.clear();
        formDialogNode.subDisabled = true;
        _this.loadPrsTableData();
      })


    },
    currentTableRow(currentRow) {
      this.$refs.table.clearSelection()
      this.$refs.table.toggleRowSelection(currentRow)
      this.currentChangeKey = currentRow
    },
    selectionTableRow(selection, row) {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow(row);
      this.currentChangeKey = row
    },
    selectionAllTableRow() {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow();
      this.currentChangeKey = ""
    },
    loadTableData() {
      let _this = this;
      _this.tableData = [];
      _this.$axios.get(`/serviceHr/sys-role/list/${_this.page.current}/${_this.page.size}`, {
        params: Object.assign(_this.searchParams)
      }).then(result => {
        _this.tableData = result.data.records
        _this.page.total = result.data.total
      })
    },
    async lazyLoadTreeData(tree, resolve) {
      let _this = this;
      let res = await _this.$axios.get("/serviceSystem/sys-resource/queryResource", {
        params: {
          parentId: tree ? tree.data.resourceId : null,
          resourceOrigin: _this.permissionsFormDialogNode.resourceOrigin
        }
      })
      if (tree) {
        resolve(res.data)
      } else {
        _this.permissionsFormDialogNode.treeData = res.data
      }
    },
    async loadPrsTableData() {
      let _this = this;
      let _roleId = _this.currentChangeKey.roleId;
      let _resId = _this.permissionsFormDialogNode.currentChangeKey.resourceId;
      _this.permissionsFormDialogNode.tableData = [];

      let result = await _this.$axios.get(`/serviceHr/sys-role/getPrs/${_roleId}/${_resId}`)

      _this.permissionsFormDialogNode.tableData = result.data.prs
      _this.permissionsFormDialogNode.tableColumns = result.data.col
      _this.permissionsFormDialogNode.tableHeight = 300
      setTimeout(function (){
        _this.permissionsFormDialogNode.tableHeight = 370
      },150)

    }


  },
  created() {
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
  margin: -10px
}
</style>
