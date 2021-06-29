<template>
  <el-dialog :visible.sync="visible" width="900px" append-to-body>
    <!-- 标题 -->
    <div slot="title" style="font-size: small; margin: -5px -5px -5px -5px">
      <span>{{ title }}</span>
    </div>
    <!-- 主体 -->
    <div style="margin: -20px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
      <el-container style="background-color: #fafaff">
        <!-- 树 -->
        <el-aside style="width: 25%">
          <el-main style="padding: 10px 6px 10px 10px;">
            <el-card shadow="hover" body-style="height: 350px">
              <div slot="header" style="font-size: small; margin: -5px -5px -5px -5px">
                <span>请选择服务目录</span>
              </div>
              <div style="height: 330px">
                <el-scrollbar style="height: 99%;" wrap-style="overflow:hidden scroll;">
                  <el-tree icon-class="el-icon-folder"
                           :data="treeData"
                           :expand-on-click-node="false"
                           :props="{label: 'label',children: 'children'}"
                           @node-click="handleTreeNodeClick"
                  ></el-tree>
                </el-scrollbar>
              </div>
            </el-card>
          </el-main>
        </el-aside>
        <!-- 表 -->
        <el-main style="padding: 10px 10px 10px 6px; width: 75%">
          <el-card shadow="hover" body-style="height: 380px; margin: -15px ">
            <div slot="header" style="font-size: small; margin: -5px -5px -5px -5px">
              <span>请选择服务</span>
              <el-divider direction="vertical"></el-divider>
              <span>目录：{{checkedTreeKey.label}}</span>
              <el-link v-show="checkedTreeKey.label" icon="el-icon-close" type="danger" @click="checkedTreeKey={};loadTableData()"></el-link>
            </div>
            <el-row :gutter="5">
              <el-col :span="10">
                <el-row>
                  <el-col :span="9"><label class="my-mini-label">服务项目名称:</label></el-col>
                  <el-col :span="15"><el-input size="mini" v-model="searchBox.form.catalogName"></el-input></el-col>
                </el-row>
              </el-col>
              <el-col :span="8">
                <el-row>
                  <el-col :span="8"><label class="my-mini-label">服务大类:</label></el-col>
                  <el-col :span="16">
                    <el-select size="mini" v-model="searchBox.form.topType" value="-1">
                      <el-option class="my_mini_option" label="请选择" value="-1"></el-option>
                    </el-select>
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="6">
                <el-button style="float: right" size="mini" icon="el-icon-search" plain></el-button>
              </el-col>
            </el-row>
            <!-- 分隔符 -->
            <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
            <el-table ref="table" height="312" :data="tableData" size="mini" @select="selectTableRow">
              <el-table-column type="index" align="center" width="50" fixed></el-table-column>
              <el-table-column type="selection" align="center" width="50" fixed></el-table-column>
              <el-table-column label="服务项目名称" prop="catalogName" width="200" header-align="center" ></el-table-column>
              <el-table-column label="服务项目类型" prop="servTypeName" width="120" header-align="center" ></el-table-column>
              <el-table-column label="服务大类" prop="topType" width="100" header-align="center" ></el-table-column>
              <el-table-column label="标准工时" prop="stdManHour" width="100" header-align="center" ></el-table-column>
              <el-table-column label="标准价格" prop="stdPrice" width="100" header-align="center" ></el-table-column>
              <el-table-column label="标准面数" prop="stdSideNum" width="100" header-align="center" ></el-table-column>
              <el-table-column label="SA增值率" prop="commRateSa" width="120" header-align="center" ></el-table-column>
              <el-table-column label="技师提成率" prop="commRateWorker" width="120" header-align="center" ></el-table-column>
              <el-table-column label="不同车型价格一致" prop="isSamePrice" width="150" header-align="center" ></el-table-column>
              <el-table-column label="服务项目编号" prop="catalogCode" width="120" header-align="center" ></el-table-column>
              <el-table-column></el-table-column>
            </el-table>
            <div>
              <el-pagination small
                  :page-sizes="[10, 20, 50, 100]"
                  :page-size.sync="page.size"
                  :pager-count="7"
                  :total="page.total"
                  :current-page.sync="page.current"
                  @size-change="loadTableData"
                  @current-change="loadTableData"
                  layout="total, sizes, prev, pager, next, jumper">
              </el-pagination>
            </div>
          </el-card>
        </el-main>
      </el-container>
    </div>
    <!-- 尾页 -->
    <div slot="footer" style="margin: -10px">
      <el-button size="mini" @click="visible=false">取 消</el-button>
      <el-button size="mini" type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: "ServCatalog",
  props:{
    selectedKey:Array,
  },
  data() {
    return {
      treeData: [],
      tableData:[],
      searchBox:{
        form: {},
      },
      page: {
        size: 20,
        total: 100,
        current: 1,
        pages: 5,
      },
      visible: false,
      title: "选择服务项目",
      checkedTreeKey: "",
      selectedTableRow: [],
      subDisabled: true,
    }
  },
  methods: {
    selectTableRow(selection){
      this.selectedTableRow = selection
    },
    lazyLoadTreeData() {
      const _this = this
      _this.$axios.get(`/serviceServiceProduct/base-serv-type`).then(result=>{
        _this.treeData=result.data.rows;
      })
    },
    handleTreeNodeClick(data) {
      this.checkedTreeKey = data
      this.selectedTableRow = [];
      this.subDisabled = true;
      // this.lazyLoadDialogTableData(true)
      this.loadTableData();

    },
    loadTableData(){
      const _this = this
      let page = _this.page;
      let search = _this.searchBox.form;
      let checkedTreeKey = _this.checkedTreeKey;
      _this.$axios.post(`/serviceServiceProduct/base-serv-catalog/queryAllPage/${page.current}/${page.size}/`,
          Object.assign(search,{stUuid: checkedTreeKey.stUuid||null})).then(({data})=>{
        _this.tableData = data.rows;
        page.total = data.total;
        page.size = data.size;
      })
    },
    show(){
      this.visible = true;
      this.$refs["table"].clearSelection();
    },
    submit(){
      this.visible = false;
      this.$emit("submit",this.selectedTableRow)
    }
  },
  created(){
    let _this = this;
    _this.lazyLoadTreeData();
    _this.loadTableData();
  }
}
</script>

<style scoped>
.my-mini-label {
  vertical-align: middle;
  float: right;
  font-size: 12px;
  color: #606266;
  line-height: 28px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
}
.my_mini_option{
  font-size: 8px;
  height: 24px;
  line-height: 24px;
}
</style>