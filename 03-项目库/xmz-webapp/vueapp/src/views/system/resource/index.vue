<!-- - - - - - - - - - - - -->
<!--        资源管理        -->
<!-- - - - - - - - - - - - -->
<template>
  <div>
    <!-- 头部 -->
    <div class="header-panel">
      <el-row>
        <el-col :span="12">
          <el-form size="mini" :model="searchParams" :inline="true">
            <el-form-item label="资源名称">
              <el-input v-model="searchParams.resourceName"></el-input>
            </el-form-item>
            <el-form-item label="归属">
              <el-select v-model="searchParams.resourceOrigin" clearable>
                <el-option v-for="(key, value) in resourceOriginsMap" :key="key" :label="key" :value="value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <template v-if="$store.getters.btnCode.indexOf('op_search')>-1">
                <el-button type="primary" icon="el-icon-search" @click="loadTableData">查询</el-button>
              </template>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="12">
          <template v-if="$store.getters.btnCode.indexOf('op_remove')>-1">
            <el-button type="danger" style="float: right; margin:0 5px"
                       size="mini" @click="handleOpenFormDialogInDelete">删除
            </el-button>
          </template>
          <template v-if="$store.getters.btnCode.indexOf('op_update')>-1">
            <el-button type="warning" style="float: right;margin:0 5px"
                       size="mini" @click="handleOpenFormDialogInEdit">修改
            </el-button>
          </template>
          <template v-if="$store.getters.btnCode.indexOf('op_add')>-1">
            <el-button type="primary" style="float: right;margin:0 5px"
                       size="mini" @click="handleOpenFormDialogInAdd">增加
            </el-button>
          </template>
        </el-col>
      </el-row>
    </div>
    <!-- 分隔符 -->
    <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
    <!-- 表主体 -->
    <el-table size="mini" height="480" :data="tableData" :fit="false" class="mainDataTable"
              border
              highlight-current-row
              row-key="resourceId"
              lazy :load="lazyLoadTableData"
              @current-change="handleCurrentChange"
              style=" margin: 0 -20px">
      <el-table-column fixed label="资源名称" prop="resourceName" width="200"></el-table-column>
      <el-table-column label="父资源" prop="parentName" width="151"></el-table-column>
      <el-table-column label="类型" prop="resourceType" width="81">
        <template slot-scope="{row:{resourceType}}">
          {{resourceTypesMap[resourceType]}}
        </template>
      </el-table-column>
      <el-table-column label="归属" prop="resourceOrigin" width="105">
        <template slot-scope="{row:{resourceOrigin}}">
          {{resourceOriginsMap[resourceOrigin]}}
        </template>
      </el-table-column>
      <el-table-column label="资源编码" prop="resourceNo" width="170"></el-table-column>
      <el-table-column label="URL" prop="url" width="256"></el-table-column>
      <el-table-column label="ICON" prop="img" width="60">
        <template slot-scope="{row:{img}}">
          <el-tooltip effect="dark" :content="img" placement="bottom-start">
            <span :class="img" style="font-size: 18px"></span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="序号" prop="treeSeq" width="61"></el-table-column>
      <el-table-column label="显示" prop="isShow" width="61">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isShow===0" type="warning" size="mini">否</el-tag>
          <el-tag v-else size="mini">是</el-tag>
          <!--                    {{scope.row.isShow===0?'否':'是'}}-->
        </template>
      </el-table-column>
    </el-table>
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
                  <el-form-item label="归属:" label-width="80px">
                    <el-select style="width:185px" v-model="formDialogNode.fromData.resourceOrigin" clearable>
                      <el-option v-for="(key, value) in resourceOriginsMap" :key="key" :label="key" :value="value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="父资源:" label-width="80px">
                    <el-input :value="formDialogNode.fromData.parentName" style="width: 185px;">
                      <el-button  slot="append" icon="el-icon-more" @click="handleShowResourceDialog"></el-button>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="资源名称:" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.resourceName"  style="width:185px"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item  label="资源编码:" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.resourceNo" style="width: 185px;"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="资源类型:" label-width="80px">
                    <el-select style="width:185px" v-model="formDialogNode.fromData.resourceType" clearable>
                      <el-option v-for="(key, value) in resourceTypesMap" :key="key" :label="key" :value="value"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="排序号:" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.treeSeq" style="width: 185px;"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="是否显示:" label-width="80px">
                    <el-radio-group v-model="formDialogNode.fromData.isShow">
                      <el-radio-button label="0">否</el-radio-button>
                      <el-radio-button label="1">是</el-radio-button>
                    </el-radio-group>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="图标:" label-width="80px">
                    <el-row>
                      <el-col :span="2">
                        <div class="demo">
                          <i :class="formDialogNode.fromData.img+' menu-form-icon-i'"></i>
                        </div>
                      </el-col>
                      <el-col :span="22">
                        <el-autocomplete
                            v-model="formDialogNode.fromData.img"
                            :fetch-suggestions="queryIconClassSearch"
                            style="width: 427px;"
                            clearable
                        >
                          <template slot-scope="{item}">
                          <span style="float:left" class="mini_icon_option">
                            <span v-for="(span,index) in splitIconClassValue(item.value,formDialogNode.fromData.img)" :key="index" >
                              <span v-if="index===1" style="color: #409EFF;font-weight:bold;">{{span}}</span>
                              <span v-else>{{span}}</span>
                            </span>
                          </span>
                            <span>
                            <i :class="item.value" class="menu-form-icon-search-icon-i"></i>
                          </span>
                          </template>
                        </el-autocomplete>
                      </el-col>
                    </el-row>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="URL:" label-width="80px">
                    <el-input v-model="formDialogNode.fromData.url" style="width: 465px;"></el-input>
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
      <!-- 资源树对话框 -->
      <el-dialog :visible.sync="resourceDialogNode.visible" width="300px" append-to-body>
        <!-- 标题 -->
        <div slot="title" style="font-size: small; margin: -5px -5px -5px -5px">
          <span>资源树</span>
        </div>
        <!-- 主体 -->
        <div style="margin: -20px;">
          <div style="padding: 0 10px 10px 10px">
            <label>
              <span style="display: inline-block; font-size: small; width: 50px">名称：</span>
              <el-input v-model="resourceDialogNode.fromData.resourceName" size="mini" style="width: 150px;"></el-input>
            </label>
            <el-button slot="append" size="mini" style="margin-left: 10px" @click="loadTreeData" >查询</el-button>
          </div>
          <el-card shadow="hover" body-style="height: 250px">
            <el-scrollbar style="height: 100%;" wrap-style="overflow:hidden scroll;">
              <el-tree
                  :data="treeData"
                  lazy :load="lazyLoadTreeData"
                  :props="{label: 'resourceName',children: 'children',isLeaf: data => !data.hasChildren}"
                  @node-click="(data)=>{
                    this.resourceDialogNode.currentChangeKey = data
                  }"
              ></el-tree>
            </el-scrollbar>
          </el-card>
        </div>
        <!-- 尾页 -->
        <div slot="footer" style="margin: -5px -5px -5px -5px">
          <el-button size="mini" @click="resourceDialogNode.visible=false">取 消</el-button>
          <el-button size="mini" type="primary" @click="handleChangeCurrentTreeResourceId">确 定</el-button>
        </div>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "index",
  data() {
    return {
      searchParams: {},
      tableData: [],
      treeData: [],
      currentChangeKey: '',
      formDialogNode: {
        visible: false,
        title: "",
        type: "",
        fromData: {},
      },
      resourceDialogNode: {
        first: true,
        visible: false,
        fromData: {},
        currentChangeKey: '',
      },
      resourceTypesMap: {
        menu: "菜单",
        open: "弹出"
      },
      resourceOriginsMap: {
        base: "总店",
        eb: "菜单",
        store: "门店"
      },
    }
  },
  computed: {},
  methods: {
    handleOpenFormDialogInAdd() {
      const _this = this;
      _this.formDialogNode.title = "增加资源"
      _this.formDialogNode.type = "add"
      _this.formDialogNode.fromData = {isShow:1}
      _this.formDialogNode.visible = true
    },
    handleOpenFormDialogInEdit() {
      const _this = this;
      if(_this.currentChangeKey){
        _this.$axios.get(`/serviceSystem/sys-resource/get/${_this.currentChangeKey.resourceId}`).then(result=>{
          _this.formDialogNode.title = "修改资源"
          _this.formDialogNode.type = "edit"
          _this.formDialogNode.fromData = result.data
          _this.formDialogNode.fromData.parentName = _this.currentChangeKey.parentName
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
    handleShowResourceDialog() {
      const _this = this;
      if(_this.formDialogNode.fromData.resourceOrigin){
        _this.resourceDialogNode.visible = true
        if (!_this.resourceDialogNode.first) _this.loadTreeData()
        _this.resourceDialogNode.first = false

      }else {
        _this.$alert('你没有选择归属，不能打开树','请选择归属',{
          confirmButtonText: '确定',
          callback: () => {
            this.$message({
              type: 'info',
              message: '请选择归属'
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
            await _this.$axios.delete(`/serviceSystem/sys-resource/delete/${_this.currentChangeKey.resourceId}`)
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
    handleCurrentChange(currentRow){
      this.currentChangeKey = currentRow;
    },
    handleChangeCurrentTreeResourceId(){
      this.formDialogNode.fromData.parentId =
          this.resourceDialogNode.currentChangeKey.resourceId
      this.formDialogNode.fromData.parentName =
          this.resourceDialogNode.currentChangeKey.resourceName
      this.resourceDialogNode.visible = false
    },
    loadTableData() {
      let _this = this
      _this.tableData = []
      _this.$axios.get("/serviceSystem/sys-resource/queryResource",{
        params: {
          resourceName: _this.searchParams.resourceName,
          resourceOrigin: _this.searchParams.resourceOrigin
        }
      }).then(result => {
        _this.tableData = result.data
        _this.currentChangeKey  = ''
      })
    },
    loadTreeData(){
      let _this = this
      _this.treeData = []
      _this.$axios.get("/serviceSystem/sys-resource/queryResource",{
        params: {
          resourceOrigin: _this.formDialogNode.fromData.resourceOrigin,
          resourceName: _this.resourceDialogNode.fromData.resourceName
        }
      }).then(result => {
        _this.treeData  = result.data
      })
    },
    handleConfirmFormDialog(){
      let _this = this;
      let _form = _this.formDialogNode.fromData;
      return async function (){
        let result;
        if(_this.formDialogNode.type === "add"){
          result = await _this.$axios.post("/serviceSystem/sys-resource/add",_form)
        }else {
          result = await _this.$axios.put("/serviceSystem/sys-resource/update",_form)
        }
        _this.$message(result.message)
        _this.loadTableData();
        _this.formDialogNode.visible = false
      }()

    },

    queryIconClassSearch(queryString, cb){
      import("@/assets/element-ui-icon.json").then(data=>{
        let restaurants = data.default;
        let results = queryString? restaurants.filter((restaurant)=>{
          return restaurant.value.indexOf(queryString) > -1;
        }) : restaurants;
        cb(results);
      })
    },
    splitIconClassValue(value,search){
      let results = search?value.split(search):[value];
      if(results.length>1)results.splice(1,0,search)
      return results;
    },

    async lazyLoadTableData(tree, treeNode, resolve) {
      let _this = this;
      let res = await _this.$axios.get("/serviceSystem/sys-resource/queryResource",{
        params: {
          parentId: tree.resourceId,
          resourceName: _this.searchParams.resourceName,
          resourceOrigin: _this.searchParams.resourceOrigin
        }
      })
      resolve(res.data)
    },
    async lazyLoadTreeData(tree, resolve) {
      let _this = this;
      let res = [];
      if(_this.tableData.length>0){
        res = await _this.$axios.get("/serviceSystem/sys-resource/queryResource",{
          params: {
            parentId: tree.data.resourceId,
            resourceOrigin: _this.formDialogNode.fromData.resourceOrigin,
            resourceName: _this.resourceDialogNode.fromData.resourceName
          }
        })
      }
      resolve(res.data)
    },
  },
  created() {
    let _this = this;
    _this.loadTableData();
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
.menu-form-icon-i{
  display: inline-block;
  min-height: 28px;
  font-size: 20px;
  color: #606266;
  line-height: 28px;
  transition: color .15s linear;
}
.menu-form-icon-i:hover{
  color: #409EFF;
}
.mini_icon_option{
  font-size: 8px;
  height: 20px;
  line-height: 28px;
}
.menu-form-icon-search-icon-i{
  float:right;
  min-height: 28px;
  font-size:16px;
  color: #606266;
  line-height:28px;
  transition: color .15s linear;
}
.menu-form-icon-search-icon-i:hover{
  color: #409EFF;
}

</style>
