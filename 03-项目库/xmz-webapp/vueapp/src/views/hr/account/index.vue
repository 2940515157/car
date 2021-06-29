<!-- - - - - - - - - -  - - - -->
<!--        用户账号管理        -->
<!-- - - - - - - - - - -  - - -->

<template>
  <div>
    <span hidden>{{ version }}</span>
    <!-- 头部 -->
    <div class="header-panel">
      <el-row>
        <el-col :span="16">
          <el-popover
              placement="right-start"
              trigger="click"
              width="600"
          >
            <el-form :model="searchParams" size="mini" :inline="true" label-position="left">
              <el-form-item label="大区:" label-width="50px">
                <el-select size="mini" v-model="searchParams.orgId"
                           filterable remote :remote-method="loadOrgOptions"
                           @change="loadAreaOptions"
                           style="width:140px" clearable>
                  <el-option v-for="(item) in orgOptions" :key="item.id"
                             class="size_option" :label="item.label" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="区域:" label-width="50px">
                <el-select size="mini" v-model="searchParams.areaId"
                           filterable remote :remote-method="loadAreaOptions"
                           @change="loadBrOptions"
                           style="width:140px" clearable>
                  <el-option v-for="(item) in areaOptions" :key="item.id"
                             class="size_option" :label="item.label" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="门店:" label-width="50px">
                <el-select size="mini" v-model="searchParams.brId"
                           filterable remote :remote-method="loadBrOptions"
                           @change="upVersion"
                           style="width:140px" clearable>
                  <el-option v-for="(item) in brOptions" :key="item.id"
                             class="size_option" :label="item.label" :value="item.id"></el-option>
                </el-select>
              </el-form-item>
              <br/>
              <el-form-item label="用户名:" label-width="70px">
                <el-input v-model="searchParams.name" style="width:220px"></el-input>
              </el-form-item>
              <el-form-item label="登录名:" label-width="70px">
                <el-input v-model="searchParams.loginName" style="width:220px"></el-input>
              </el-form-item>
            </el-form>
            <!-- 分隔符 -->
            <div style="margin:5px -10px;border-bottom: 1px solid #EBEEF5;"></div>
            <el-button style="float: right; margin:0 5px" type="primary" size="mini" @click="loadTableData"
                       icon="el-icon-search">
              查询
            </el-button>
            <el-button slot="reference" type="primary" size="mini" icon="el-icon-search"></el-button>
          </el-popover>
        </el-col>
        <el-col :span="8">
          <el-popover
              placement="left"
              trigger="click">
            <el-button type="primary" style="margin:0 5px" plain size="mini" @click="handleResetPassword">重置密码
            </el-button>
            <el-button type="primary" style="margin:0 5px" plain size="mini" @click="handleOpenRoleGrantDialogNode">
              角色授予
            </el-button>
            <el-button type="warning" style="margin:0 5px" plain size="mini" @click="handleLock('locking')">锁定账号
            </el-button>
            <el-button type="warning" style="margin:0 5px" plain size="mini" @click="handleLock('unlocking')">解锁账号
            </el-button>
            <el-button slot="reference" style="float: right;margin:0 5px"
                       plain size="mini" icon="el-icon-menu"></el-button>
          </el-popover>
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
              border
              @current-change="currentTableRow"
              @select="selectionTableRow"
              @select-all="selectionAllTableRow"
    >
      <el-table-column type="index" align="center" width="50"></el-table-column>
      <el-table-column type="selection" align="center" width="50"></el-table-column>
      <el-table-column label="用户名" prop="name" width="100"></el-table-column>
      <el-table-column label="登录名" prop="loginName" width="100"></el-table-column>
      <!--      <el-table-column label="姓名" prop="empName" width="100"></el-table-column>-->
      <el-table-column label="帐号类型" prop="type" width="100">
        <template slot-scope="{row:{type}}">
          {{ accountTypeDict[type] }}
        </template>
      </el-table-column>
      <el-table-column label="帐号状态" prop="status" width="100">
        <template slot-scope="{row:{status}}">
          {{ statusDict[status] }}
        </template>
      </el-table-column>
      <!--      <el-table-column label="门店名称" prop="brName" width="250"></el-table-column>-->
      <el-table-column label="最近登录时间" prop="lastLoginTime" width="250"></el-table-column>
      <el-table-column label="备注" prop="remark" width="250"></el-table-column>
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
                <el-col :span="24">
                  <el-form-item label="员工：" label-width="80px">
                    <el-input size="mini" :readonly="true" :value="formDialogNode.fromData.name"
                              style="width:465px" clearable>
                      <el-button slot="append" icon="el-icon-more"
                                 @click="HandleOpenEmpDataTableDialog"></el-button>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="登录名：" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.loginName" style="width:185px"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="账号类型:" label-width="80px">
                    <el-select size="mini" v-model="formDialogNode.fromData.type" style="width:185px" clearable>
                      <el-option v-for="(key,value) in accountTypeDict" :key="value.id"
                                 class="size_option" :label="key" :value="value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="备注：" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.remark" type="textarea" :rows="2"
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
        <el-button size="mini" type="primary" @click="handleConfirmFormDialog">确 定</el-button>
      </div>
      <!-- 内联员工表对话框 -->
      <el-dialog :visible.sync="formDialogNode.empTable.visible" width="550px" append-to-body>
        <!-- 标题 -->
        <div slot="title" style="font-size: small; margin: -5px -5px -5px -5px">
          <span>员工表</span>
        </div>
        <!-- 主体 -->
        <div style="margin: -20px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
          <!-- 搜索 -->
          <div style="margin-top: 5px; padding: 0 5px">
            <el-row :gutter="5">
              <el-col :span="2">
                <label class="my-label">名称:</label>
              </el-col>
              <el-col :span="19">
                <el-input size="mini" v-model="formDialogNode.empTable.searchDoParams.name"></el-input>
              </el-col>
              <!--              <el-col :span="2">-->
              <!--                <label class="my-label">门店:</label>-->
              <!--              </el-col>-->
              <!--              <el-col :span="9">-->
              <!--                <el-input size="mini" v-model="formDialogNode.empTable.searchDoParams.name"></el-input>-->
              <!--              </el-col>-->
              <el-col :span="3">
                <el-button size="mini" style="float: right" @click="loadEmpTableData">查询</el-button>
              </el-col>
            </el-row>
          </div>
          <div style="margin:5px 0;border-bottom: 1px solid #EBEEF5;"></div>
          <!-- 表 -->
          <el-table ref="dialogTable" size="mini" highlight-current-row :data="formDialogNode.empTable.tableData"
                    height="250"
                    :fit="false"
                    @select="selectionDialogTableRow"
                    @current-change="currentDialogTableRow"
                    @select-all="selectionAllDialogTableRow"
                    style=" margin: 0">
            <el-table-column type="index" align="center" width="50"></el-table-column>
            <el-table-column type="selection" align="center" width="45"></el-table-column>
            <el-table-column label="名称" prop="empName" width="150"></el-table-column>
            <el-table-column label="门店" prop="brName" width="295"></el-table-column>
          </el-table>
          <!-- 分页 -->
          <div>
            <el-pagination
                style="text-align: center;"
                layout="prev, pager, next"
                :page-size.sync="formDialogNode.empTable.page.size"
                :current-page.sync="formDialogNode.empTable.page.current"
                :total="formDialogNode.empTable.page.total"
                @current-change="loadEmpTableData">
            </el-pagination>
          </div>

        </div>
        <!-- 尾页 -->
        <div slot="footer" style="margin: -5px -5px -5px -5px">
          <el-button size="mini" @click="formDialogNode.empTable.visible=false">取 消</el-button>
          <el-button size="mini" type="primary" @click="HandleConfirmEmpDialogTable">确 定</el-button>
        </div>
      </el-dialog>
    </el-dialog>
    <!--角色授予对话框-->
    <el-dialog :visible.sync="roleGrantDialogNode.visible" width="640px">
      <!-- 标题 -->
      <div slot="title">
        <span>{{ roleGrantDialogNode.title }}</span>
      </div>
      <!-- 主体 -->
      <div style="margin: -20px;border-top: 1px solid #EBEEF5;">
        <el-row :gutter="1" style="margin: 10px">
          <el-col :span="3">
            <label class="my-label">名称:</label>
          </el-col>
          <el-col :span="18">
            <el-input size="mini" v-model="roleGrantDialogNode.searchDoParams.name"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button size="mini" style="float: right" @click="HandleSearchRoleTable">查询</el-button>
          </el-col>
        </el-row>
        <!-- 分隔符 -->
        <div style="margin:5px 0;border-bottom: 1px solid #EBEEF5;"></div>
        <el-table ref="roleTable" size="mini" :data="roleGrantDialogNode.tableData" height="300"
                  @row-click="clickRoleGrantTableRow"
                  @select="selectionRoleGrantTableRow"
                  :fit="true">
          <el-table-column type="index" align="center" width="50"></el-table-column>
          <el-table-column type="selection" align="center" width="45"></el-table-column>
          <el-table-column label="名称" prop="roleName" width="230"></el-table-column>
          <el-table-column label="描述" prop="roleMemo" width="295"></el-table-column>
          <div v-if="roleGrantDialogNode.infinite.visible"
               slot="append"
               v-infinite-scroll="lazyLoadRoleTableData"
               :infinite-scroll-disabled="disabledRoleTableInfinite()">
            <p v-if="roleGrantDialogNode.infinite.loading" class="infinite-list-item">
              加载中...({{ roleGrantDialogNode.tableData.length }}/{{ roleGrantDialogNode.page.total }})
            </p>
            <p v-if="roleGrantDialogNode.infinite.noMore" class="infinite-list-item">没有更多了</p>
          </div>
        </el-table>
      </div>
      <!-- 尾页 -->
      <div slot="footer" style="margin: -5px -5px -5px -5px">
        <el-button size="mini" @click="roleGrantDialogNode.visible=false">取 消</el-button>
        <el-button size="mini" @click="handleRoleGrantSubmitClick" :disabled="roleGrantDialogNode.subDisabled"
                   type="primary">确 定
        </el-button>

      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      version: "",
      tableHeight: window.innerHeight - 261,
      roleGrantDialogNode: {
        searchParams: {},
        infinite: {
          visible: false,
          loading: false,
          noMore: false
        },
        searchDoParams: {},
        subDisabled: true,
        page: {
          size: 20,
          total: 0,
          current: 1,
          pages: 5,
        },
        changeData: new Set(),
        visible: false,
        title: "角色授予",
        tableData: [],
      },
      tableData: [],
      searchParams: {},
      currentChangeKey: "",
      page: {
        size: 10,
        total: 100,
        current: 1
      },
      formDialogNode: {
        visible: false,
        title: "",
        fromData: {},
        empTable: {
          page: {
            size: 20,
            total: 80,
            current: 1
          },
          currentChangeKey: {},
          tableData: [],
          searchDoParams: {},
          visible: false,
        }
      },
      orgOptions: [],
      areaOptions: [],
      brOptions: [],
      empOptions: [
        {id: "efoo", label: "EFOO"},
        {id: "ebar", label: "EBAR"}
      ],
      statusDict: {
        normal: "正常",
        locking: "锁定"
      },
      accountTypeDict: {
        admin: "管理员",
        general: "普通"
      }
    }
  },
  methods: {
    upVersion() {
      this.version = Math.random();
    },
    handleConfirmFormDialog() {
      let _this = this;
      let from = _this.formDialogNode.fromData;
      return async function () {
        let result;
        if (_this.formDialogNode.type === "add") {
          result = await _this.$axios.post("/serviceHr/sys-account/save", from)
        } else {
          result = await _this.$axios.put("/serviceHr/sys-account/update", from)
        }
        _this.$message(result.message)
        _this.loadTableData();
        _this.formDialogNode.visible = false
      }()

    },
    handleOpenFormDialogInAdd() {
      const _this = this;
      _this.formDialogNode.title = "增加员工账号"
      _this.formDialogNode.type = "add"
      _this.formDialogNode.fromData = {}
      _this.formDialogNode.visible = true
    },
    handleOpenFormDialogInEdit() {
      const _this = this;
      if (_this.currentChangeKey) {
        if(_this.checkOperation()) return
        _this.$axios.get(`/serviceHr/sys-account/get/${_this.currentChangeKey.accountId}`).then(result => {
          _this.formDialogNode.title = "修改员工账号"
          _this.formDialogNode.type = "edit"
          _this.formDialogNode.fromData = result.data
          _this.formDialogNode.visible = true
          // console.log(result
          // )
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
            await _this.$axios.delete(`/serviceHr/sys-account/remove/${_this.currentChangeKey.accountId}`)
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
    handleLock(methode) {
      const _this = this;
      let span = methode === "locking" ? "锁定" : "解锁"
      if (_this.currentChangeKey) {
        return async function () {
          _this.$confirm(`'此操作将${span}该账号, 是否继续?', '提示'`, {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            await _this.$axios.put(`/serviceHr/sys-account/${methode}/${_this.currentChangeKey.accountId}`)
            _this.$message({
              type: 'success',
              message: `${span}成功!`
            });
            _this.loadTableData();
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: `已取消${span}`
            });
          });
        }()
      } else {
        _this.$alert(`你没有选择记录，不能${span}`, '请选择记录', {
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
    handleResetPassword() {
      const _this = this;
      if (_this.currentChangeKey) {
        return async function () {
          _this.$confirm('此操作将会重置该账号的密码, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(async () => {
            await _this.$axios.put(`/serviceHr/sys-account/respwd/${_this.currentChangeKey.accountId}`)
            _this.$message({
              type: 'success',
              message: '重置成功!'
            });
            _this.loadTableData();
          }).catch(() => {
            _this.$message({
              type: 'info',
              message: '已取消重置'
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
    HandleConfirmEmpDialogTable() {
      let currentKey = this.formDialogNode.empTable.currentChangeKey;
      this.formDialogNode.empTable.visible = false
      this.formDialogNode.fromData.refId = currentKey.empUuid;
      this.formDialogNode.fromData.name = currentKey.empName;
    },
    HandleOpenEmpDataTableDialog() {
      this.formDialogNode.empTable.visible = true
      this.loadEmpTableData();
    },
    handleRoleGrantSubmitClick() {
      let _this = this;
      _this.$axios.post("/serviceHr/sys-account-role/barthSaveOrUpdate",
          Array.from(this.roleGrantDialogNode.changeData.values())
      ).then(() => {
        _this.$message.success("执行成功")
        this.roleGrantDialogNode.changeData.clear();
        this.roleGrantDialogNode.subDisabled = true;
        _this.lazyLoadRoleTableData(true)
      })
    },
    handleOpenRoleGrantDialogNode() {
      const _this = this;
      if (_this.currentChangeKey) {
        _this.roleGrantDialogNode.changeData.clear();
        _this.roleGrantDialogNode.subDisabled = true;
        _this.roleGrantDialogNode.visible = true;
        _this.lazyLoadRoleTableData(true)
      } else {
        _this.$alert('你没有选择记录，不能进行授予操作', '请选择记录', {
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
    HandleSearchRoleTable() {
      this.roleGrantDialogNode.searchParams = this.roleGrantDialogNode.searchDoParams
      this.lazyLoadRoleTableData(true)
    },
    clickRoleGrantTableRow(currentRow) {
      // let isActive = currentRow.isActive === "Y";
      currentRow.isActive = currentRow.isActive === "Y" ? "N" : "Y";
      currentRow.accountId = this.currentChangeKey.accountId
      this.roleGrantDialogNode.changeData.add(currentRow)
      this.roleGrantDialogNode.subDisabled = false
      this.$refs.roleTable.toggleRowSelection(currentRow)
    },
    selectionRoleGrantTableRow(selection, row) {
      // let isActive = row.isActive === "Y";
      row.isActive = row.isActive === "Y" ? "N" : "Y";
      row.accountId = this.currentChangeKey.accountId
      this.roleGrantDialogNode.changeData.add(row)
      this.roleGrantDialogNode.subDisabled = false
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
    currentDialogTableRow(currentRow) {
      this.$refs.dialogTable.clearSelection()
      this.$refs.dialogTable.toggleRowSelection(currentRow)
      this.formDialogNode.empTable.currentChangeKey = currentRow

    },
    selectionDialogTableRow(selection, row) {
      this.$refs.dialogTable.clearSelection()
      this.$refs.dialogTable.setCurrentRow(row);
      this.formDialogNode.empTable.currentChangeKey = row
    },
    selectionAllDialogTableRow() {
      this.$refs.dialogTable.clearSelection()
      this.$refs.dialogTable.setCurrentRow();
      this.formDialogNode.empTable.currentChangeKey = ""
    },
    disabledRoleTableInfinite() {
      let infinite = this.roleGrantDialogNode.infinite;
      return infinite.loading || infinite.noMore
    },
    checkOperation(){
      const _this = this;
      const id = _this.currentChangeKey.accountId;
      if(id === _this.$store.getters.userInfo.id){
        _this.$alert('你在试图变更你当前登录账户的记录！！！', '不可变更', {
          confirmButtonText: '确定',
          callback: () => {
            this.$message({
              type: 'info',
              message: '请选择其他记录'
            });
          }
        })
        return true;
      }
      // if(id === "0d0dbf129e998d796b50ccbca1aa5358"){
      //   _this.$alert('你在试图变更最高管理员的记录！！！', '不可变更', {
      //     confirmButtonText: '确定',
      //     callback: () => {
      //       this.$message({
      //         type: 'info',
      //         message: '请选择其他记录'
      //       });
      //     }
      //   })
      //   return true;
      // }
    },
    loadTableData() {
      let _this = this;
      _this.tableData = [];
      _this.$axios.get(`/serviceHr/sys-account/queryPage/${_this.page.current}/${_this.page.size}`, {
        params: Object.assign(_this.searchParams)
      }).then(result => {
        _this.tableData = result.data.records
        _this.page.total = result.data.total
      })

    },
    async loadOrgOptions() {
      let _this = this;
      _this.orgOptions = [];
      let result = await _this.$axios.get('/serviceHr/HrOrg/getOrgOptions/')
      _this.orgOptions = result.data;
      _this.loadAreaOptions();
    },
    async loadAreaOptions() {
      let _this = this;
      _this.areaOptions = [];
      _this.searchParams.areaId = "";
      let orgId = this.searchParams.orgId || "-1";
      if (orgId === "-1") return;
      let result = await _this.$axios.get(`/serviceHr/hrArea/getAreaOptions/${orgId}`)
      _this.areaOptions = result.data;
      _this.loadBrOptions();

    },
    async loadBrOptions() {
      let _this = this;
      _this.brOptions = [];
      _this.searchParams.brId = "";
      let areaId = this.searchParams.areaId || "-1";
      if (areaId === "-1") return;
      let result = await _this.$axios.get(`/serviceHr/hr-branches/getBrOptions/${areaId}`)
      _this.brOptions = result.data;

    },
    async lazyLoadRoleTableData(reLoad) {
      let _this = this;
      let infinite = _this.roleGrantDialogNode.infinite;
      let page = _this.roleGrantDialogNode.page;
      infinite.loading = true;

      if (reLoad) {
        page.size = 20
        page.total = 0
        page.current = 1
        page.pages = 0
      }
      if (reLoad) {
        infinite.visible = false
        infinite.loading = false
        infinite.noMore = false
      }

      let result = await _this.$axios.get(
          `/serviceHr/sys-account/RoleGrant/infiniteList/${_this.currentChangeKey.accountId}/${reLoad ? page.current : page.current + 1}/${page.size}`
          , {params: _this.roleGrantDialogNode.searchParams}
      ).catch(() => {
        infinite.loading = false;
      })
      if (reLoad) {
        _this.roleGrantDialogNode.tableData = result.data.records
      } else {
        result.data.records.map(item => {
          _this.roleGrantDialogNode.tableData.push(item)
        })
      }
      page.total = result.data.total
      page.current = result.data.current
      page.size = result.data.size
      page.pages = result.data.pages
      infinite.loading = false;
      setTimeout(() => {
        result.data.records.every((item) => {
          if (item.isActive === "Y") {
            _this.$refs.roleTable.toggleRowSelection(item)
            return true
          }
          return false
        })
        infinite.visible = true
      }, 150)
      if (page.pages === page.current) {
        if (_this.roleGrantDialogNode.tableData.length === page.total) {
          infinite.noMore = true;
        }
      }

    },
    loadEmpTableData() {
      let _this = this;
      let page = _this.formDialogNode.empTable.page;
      let searchParams = _this.formDialogNode.empTable.searchDoParams;
      _this.$axios.post(`/serviceHr/employees/pageQuery/${page.current}/${page.size}`, {
        params: searchParams
      }).then(result => {
        _this.formDialogNode.empTable.tableData = result.data.rows;
        _this.formDialogNode.empTable.page.total = result.data.total;
      })
    },
  },
  created() {
    let _this = this;
    _this.loadTableData();
    _this.loadOrgOptions();

    document.querySelector("body").onresize = function () {
      _this.tableHeight = innerHeight - 261
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
  margin: -15px -10px 2px;
}

.size_option {
  font-size: 8px;
  height: 20px;
  line-height: 20px;
}

.my-label {
  text-align: right;
  vertical-align: middle;
  float: left;
  font-size: 12px;
  color: #606266;
  line-height: 28px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
}

.infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  background: #e8f3fe;
  color: #7dbcfc;
}
</style>
