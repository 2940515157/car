<!-- - - - - - - - - - - - -->
<!--        功能管理        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <!-- 头部 -->
    <div class="header-panel">
      <el-row>
        <el-col :span="19">
          <el-form size="mini" :model="searchParams" :inline="true">
            <el-form-item label="资源">
              <el-input v-model="searchParams.resourceName" style="width: 120px"></el-input>
            </el-form-item>
            <el-form-item label="操作名称">
              <el-input v-model="searchParams.operationName" style="width: 150px"></el-input>
            </el-form-item>
            <el-form-item label="逻辑流名称">
              <el-input v-model="searchParams.logicFlowName" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="归属">
              <el-select style="width: 150px" v-model="searchParams.resourceOrigin" clearable>
                <el-option v-for="(key, value) in resourceOriginsMap" :key="key" :label="key"
                           :value="value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <template v-if="$store.getters.btnCode.indexOf('op_search')>-1">
                <el-button type="primary" icon="el-icon-search" @click="loadTableData">查询</el-button>
              </template>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="5">
          <template v-if="$store.getters.btnCode.indexOf('op_remove')>-1">
            <el-button type="danger" style="float: right; margin:0 5px"
                       size="mini">删除
            </el-button>
          </template>
          <template v-if="$store.getters.btnCode.indexOf('op_edit')>-1">
            <el-button type="primary" style="float: right;margin:0 5px"
                       size="mini" @click="handleOpenFormDialogInEdit">编辑
            </el-button>
          </template>
        </el-col>
      </el-row>
    </div>
    <!-- 分隔符 -->
    <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
    <!-- 表主体 -->
    <el-table :data="tableData" size="mini" :height="tableHeight" :fit="false" class="mainDataTable"
              style=" margin: 0 -20px">
      <el-table-column type="index" align="center" width="50"></el-table-column>
      <el-table-column type="selection" align="center" width="50"></el-table-column>
      <el-table-column label="归属" width="250" prop="resourceOrigin">
        <template slot-scope="{row:{resourceOrigin}}">
          {{ resourceOriginsMap[resourceOrigin] }}
        </template>
      </el-table-column>
      <el-table-column label="资源" width="300" prop="resourceName"></el-table-column>
      <el-table-column label="操作名称" width="100" prop="operationName"></el-table-column>
      <el-table-column label="逻辑流名称" width="400" prop="logicFlowName"></el-table-column>
      <el-table-column min-width="0"></el-table-column>
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
    <el-dialog :visible.sync="formDialogNode.visible" width="900px">
      <!-- 标题 -->
      <div slot="title">
        <span>{{ formDialogNode.title }}</span>
      </div>
      <!-- 主体 -->
      <div style="margin: -20px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
        <el-container style="background-color: #fafaff">
          <!-- 树 -->
          <el-aside>
            <el-main style="padding-right: 0">
              <el-card shadow="hover" body-style="height: 370px">
                <el-radio-group v-model="formDialogNode.resourceOrigin" @change="handleChangeResourceOrigin"
                                size="mini">
                  <el-radio-button
                      v-for="(key, value) in resourceOriginsMap"
                      :key="value"
                      :label="value">{{ key }}
                  </el-radio-button>
                </el-radio-group>
                <!-- 分隔符 -->
                <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
                <div style="height: 330px">
                  <el-scrollbar style="height: 100%;" wrap-style="overflow:hidden scroll;">
                    <el-tree
                        :data="formDialogNode.treeData"
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
              <div>
                <el-row>
                  <el-col :span="3"><span class="my-label">
                    菜单:
                  </span></el-col>
                  <el-col :span="9"><span class="my-label">
                    {{ formDialogNode.currentChangeKey.resourceName }}
                  </span></el-col>
                  <el-col :span="3"><span class="my-label">
                    归属:</span></el-col>
                  <el-col :span="9"><span class="my-label">
                    {{ resourceOriginsMap[formDialogNode.currentChangeKey.resourceOrigin] }}
                  </span></el-col>
                </el-row>
                <el-row :gutter="1">
                  <el-col :span="3">
                    <label class="my-label">名称:</label>
                  </el-col>
                  <el-col :span="18">
                    <el-input size="mini" v-model="formDialogNode.searchDoParams.name"></el-input>
                  </el-col>
                  <el-col :span="3">
                    <el-button size="mini" style="float: right" @click="HandleDoSearchDialogTable">查询</el-button>
                  </el-col>
                </el-row>
              </div>
              <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
              <el-table ref="dialogTable" size="mini" :data="formDialogNode.tableData" height="300"
                        :fit="false" class="mainDataTable"
                        @row-click="clickDialogTableRow"
                        @select="selectionDialogTableRow"
                        style=" margin: 0 -20px">
                <el-table-column type="index" align="center" width="50"></el-table-column>
                <el-table-column type="selection" align="center" width="45"></el-table-column>
                <el-table-column label="名称" width="230">
                  <template slot-scope="{row}">
                    {{ row.name }}({{ row.operationNo }})
                  </template>
                </el-table-column>
                <el-table-column label="逻辑流" prop="logicFlowName" width="225"></el-table-column>
                <div v-if="formDialogNode.infinite.visible"
                     slot="append"
                     v-infinite-scroll="lazyLoadDialogTableData"
                     :infinite-scroll-disabled="disabledDialogInfinite()">
                  <p v-if="formDialogNode.infinite.loading" class="infinite-list-item">
                    加载中...({{ formDialogNode.tableData.length }}/{{ formDialogNode.page.total }})
                  </p>
                  <p v-if="formDialogNode.infinite.noMore" class="infinite-list-item">没有更多了</p>
                </div>
              </el-table>
            </el-card>
          </el-main>
        </el-container>
      </div>
      <!-- 尾页 -->
      <div slot="footer" style="margin: -5px -5px -5px -5px">
        <el-button size="mini" @click="formDialogNode.visible=false">取 消</el-button>
        <el-button size="mini" @click="handleSubmitClick" :disabled="formDialogNode.subDisabled" type="primary">确 定</el-button>
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
      page: {
        size: 10,
        total: 100,
        current: 1
      },
      tableData: [],
      tableHeight: window.innerHeight - 274,
      formDialogNode: {
        subDisabled: true,
        first: true,
        visible: false,
        searchParams: {},
        searchDoParams: {},
        page: {
          size: 20,
          total: 0,
          current: 1,
          pages: 5,
        },
        title: "标题",
        treeData: [],
        tableData: [],
        current: 1,
        infinite: {
          visible: false,
          loading: false,
          noMore: false
        },
        changeData: new Set(),
        size: 20,
        resourceOrigin: "eb",
        currentChangeKey: {
          resourceName: '请选择',
        },
      },
      resourceOriginsMap: {
        base: "总部",
        eb: "菜单",
        store: "商店"
      },
    }
  },
  methods: {
    handleOpenFormDialogInEdit() {
      const _this = this
      let _formDialogNode = this.formDialogNode;
      _formDialogNode.title = "编辑浏览功能配置"
      _formDialogNode.visible = true
      _formDialogNode.treeData = []
      _formDialogNode.tableData = []
      _formDialogNode.infinite.visible = false
      _formDialogNode.changeData.clear();
      if (!_formDialogNode.first) _this.lazyLoadTreeData()
      _formDialogNode.first = false
    },
    handleChangeResourceOrigin() {
      let _this = this;
      _this.formDialogNode.treeData = [];
      _this.lazyLoadTreeData()
    },
    handleTreeNodeClick(data) {
      if (!data.hasChildren) {
        this.formDialogNode.currentChangeKey = data
        // this.loadDialogTableData()
        this.formDialogNode.changeData.clear();
        this.formDialogNode.subDisabled = true;
        this.lazyLoadDialogTableData(true)
      }
    },
    HandleDoSearchDialogTable() {
      this.formDialogNode.searchParams = this.formDialogNode.searchDoParams
      this.lazyLoadDialogTableData(true)
    },
    clickDialogTableRow(currentRow) {
      // let isActive = currentRow.isActive === "Y";
      currentRow.isActive = currentRow.isActive === "Y" ? "N" : "Y";
      currentRow.operationName = currentRow.name
      currentRow.operationCode = currentRow.operationNo
      this.formDialogNode.changeData.add(currentRow)
      this.formDialogNode.subDisabled = false
      this.$refs.dialogTable.toggleRowSelection(currentRow)
    },
    selectionDialogTableRow(selection, row) {
      // let isActive = row.isActive === "Y";
      row.isActive = row.isActive === "Y" ? "N" : "Y";
      row.operationName = row.name
      row.operationCode = row.operationNo
      this.formDialogNode.changeData.add(row)
      this.formDialogNode.subDisabled = false
    },
    loadTableData() {
      let _this = this;
      _this.tableData = [];
      _this.$axios.get(`/serviceSystem/sys-permission/list/${_this.page.current}/${_this.page.size}`, {
        params: Object.assign(_this.searchParams)
      }).then(result => {
        _this.tableData = result.data.records
        _this.page.total = result.data.total
      })
    },
    disabledDialogInfinite() {
      let infinite = this.formDialogNode.infinite;
      return infinite.loading || infinite.noMore
    },
    handleSubmitClick(){
      let _this = this;
      let duplicate = {
        resourceId: _this.formDialogNode.currentChangeKey.resourceId,
        resourceName: _this.formDialogNode.currentChangeKey.resourceName,
        resourceUri: _this.formDialogNode.currentChangeKey.resourceUri
      };
      _this.$axios.put("/serviceSystem/sys-permission/batchEdit",{
        duplicate: JSON.stringify(duplicate),
        list: JSON.stringify(Array.from(this.formDialogNode.changeData.values()))
      }).then(()=>{
        _this.$message.success("执行成功")
        this.formDialogNode.changeData.clear();
        this.formDialogNode.subDisabled = true;
        _this.lazyLoadDialogTableData(true)
      })
    },
    async lazyLoadTreeData(tree, resolve) {
      let _this = this;
      let res = await _this.$axios.get("/serviceSystem/sys-resource/queryResource", {
        params: {
          parentId: tree ? tree.data.resourceId : null,
          resourceOrigin: _this.formDialogNode.resourceOrigin
        }
      })
      if (tree) {
        resolve(res.data)
      } else {
        _this.formDialogNode.treeData = res.data
      }
    },
    async lazyLoadDialogTableData(reLoad) {
      let _this = this;
      let infinite = _this.formDialogNode.infinite;
      let page = _this.formDialogNode.page;
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
          `/serviceSystem/sys-operation/infiniteList/${_this.formDialogNode.currentChangeKey.resourceId}/${reLoad ? page.current : page.current + 1}/${page.size}`
          , {params: _this.formDialogNode.searchParams}
      ).catch(() => {
        infinite.loading = false;
      })
      if (reLoad) {
        _this.formDialogNode.tableData = result.data.records
      } else {
        result.data.records.map(item => {
          _this.formDialogNode.tableData.push(item)
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
            _this.$refs.dialogTable.toggleRowSelection(item)
            return true
          }
          return false
        })
        infinite.visible = true
      }, 150)
      if (page.pages === page.current) {
        if (_this.formDialogNode.tableData.length === page.total) {
          infinite.noMore = true;
        }
      }

    },
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
<style>
.isActive {
  background: #a8c5dd !important;
}
</style>