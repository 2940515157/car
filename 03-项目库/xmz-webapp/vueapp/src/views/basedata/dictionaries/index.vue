<template>
  <div>
    <!-- 字典类型-->
    <div id="type">

      <!-- 工具栏 -->
      <div id="typeTool">
        <el-form size="mini" :inline="true" :model="queryParam" Department="demo-form-inline">
          <el-form-item label="字典代码">
            <el-input style="width: 130px" v-model="queryParam.dicttypeid"></el-input>
          </el-form-item>
          <el-form-item label="字典名称">
            <el-input style="width: 130px" v-model="queryParam.dicttypename"></el-input>
          </el-form-item>
          <el-form-item label="字典类型">
            <el-select style="width: 100px" v-model="queryParam.dictType" placeholder="请选择">
              <el-option value="sys" label="系统字典"></el-option>
              <el-option value="busi" label="业务字典"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="search"></el-button>
            <el-button type="info" icon="el-icon-close" @click="reset"></el-button>
          </el-form-item>
        </el-form>
      </div>

      <!-- 数据表格 -->
      <el-table
          size="mini"
          :data="dictTypeTableData"
          highlight-current-row
          style="width: 100%"
          @current-change="tableCurrentChange"
          ref="multipleTable"
          @row-click="tableClick">
        <el-table-column
            align="center"
            type="selection"
            width="50">
        </el-table-column>
        <el-table-column
            align="center"
            type="index"
            width="50">
        </el-table-column>
        <el-table-column
            align="center"
            label="字典代码"
            prop="dicttypeid"
            width="250">
        </el-table-column>
        <el-table-column
            align="center"
            prop="dicttypename"
            label="字典名称"
            width="250">
        </el-table-column>
        <el-table-column
            align="center"
            property="dictType"
            label="字典类型">
        </el-table-column>
      </el-table>

      <!-- 分页栏 -->
      <el-pagination
          @size-change="sizeChange"
          @current-change="currentChange"
          :current-page="current"
          :page-sizes="pageSizeList"
          :page-size="size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </div>

    <!-- 类型信息-->
    <div id="entry">

      <!-- 工具栏 -->
      <div id="entryTool">
        <el-button :disabled="dictType=='sys' || dictTypeId==''"  type="primary" size="mini" @click="add" class="el-icon-plus"></el-button>
        <el-button :disabled="dictType=='sys' || dictId==''" type="warning" size="mini" @click="edit" class="el-icon-edit"></el-button>
        <el-button :disabled="dictType=='sys' || dictId==''" type="danger" size="mini" @click="del" class="el-icon-delete"></el-button>
      </div>

      <!-- 数据表格 -->
      <div id="entryTable">
      <el-table
          :data="dictEntryTableData"
          size="mini"
          highlight-current-row
          style="width: 100%"
          :default-sort="{prop: 'sortno', order: 'ascending'}"
          @current-change="tableCurrentChange2"
          ref="multipleTable2"
          @row-click="tableClick2">
        <el-table-column
            align="center"
            type="selection"
            width="50">
        </el-table-column>
        <el-table-column
            align="center"
            type="index"
            width="50">
        </el-table-column>
        <el-table-column
            align="center"
            label="字典项代码"
            prop="dictid"
            width="180">
        </el-table-column>
        <el-table-column
            align="center"
            prop="dictname"
            label="字典项名称"
            width="180">
        </el-table-column>
        <el-table-column
            align="center"
            property="sortno"
            sortable
            label="排序">
        </el-table-column>
      </el-table>
      </div>

      <!-- 分页栏 -->
      <el-pagination
          @size-change="sizeChange2"
          @current-change="currentChange2"
          :current-page="current2"
          :page-sizes="pageSizeList"
          :page-size="size2"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total2">
      </el-pagination>

      <!-- 添加编辑对话框 -->
      <el-dialog :title="dictId == '' ? '添加':'编辑'" :visible.sync="dialogFormVisible" width="400px">
        <el-form :model="form">
          <el-form-item label="字典项代码" :label-width="formLabelWidth">
            <el-input :readonly="dictId == ''?false:true" style="width: 200px"
                      v-model="form.dictid"></el-input>
          </el-form-item>
          <el-form-item label="字典项名称" :label-width="formLabelWidth">
            <el-input style="width: 200px" v-model="form.dictname"></el-input>
          </el-form-item>
          <el-form-item label="排序" :label-width="formLabelWidth">
            <el-input style="width: 200px" v-model="form.sortno"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" Department="dialog-footer">
          <el-button @click="dialogFormVisible = false,form={}">取 消</el-button>
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>

    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      // 所有字典类型
      dictTypeTableData: [],
      // 所有字典信息
      dictEntryTableData: [],
      // 查询参数
      queryParam: {},
      // 字典类型id
      dictTypeId: '',
      // 字典类型
      dictType:'',
      // 字典信息id
      dictId: '',
      //增加修改表单
      form:{},
      // 当前页号
      current: 1,
      current2: 1,
      // 分页列表
      pageSizeList: [5, 10, 15, 20],
      // 页大小
      size: 10,
      size2: 10,
      // 总记录数
      total: 0,
      total2: 0,
      // 是否显示对话框
      dialogFormVisible: false,
      // 表单元素的label的宽度
      formLabelWidth: '100px'
    }
  },
  methods: {
    //查询所有字典类型
    async getDictTypeList() {
      const result = await this.$axios.post(`/serviceBasedata/dicttype/queryPage/${this.current}/${this.size}`, this.queryParam)
      this.dictTypeTableData = result.data.rows
      this.total = result.data.total
    },
    //查询字典信息
    async getDictEntryList() {
      const result = await this.$axios.get(`/serviceBasedata/dictentry/queryByTypeId/${this.dictTypeId}/${this.current2}/${this.size2}`)
      this.dictEntryTableData = result.data.rows
      this.total2 = result.data.total
    },
    // 清空
    reset() {
      this.queryParam = {}
    },
    // 查询
    search() {
      this.getDictTypeList()
      this.dictTypeId=null
      this.getDictEntryList()
    },
    //添加
    add(){
      this.dictId='';
      this.form = {};
      this.$refs.multipleTable2.clearSelection();
      this.dialogFormVisible = true;
    },
    //修改
    async edit(){
      const result = await this.$axios.get(`/serviceBasedata/dictentry/queryByDictId/${this.dictTypeId}/${this.dictId}`)
      this.form = result.data
      this.dialogFormVisible = true
    },
    //修改保存
    async save() {
      let formEntry={};
      this.dialogFormVisible = false
      if (this.dictId == null || this.dictId == ''){
        this.form.dicttypeid=this.dictTypeId
        formEntry=this.form
      }else {
        formEntry={dictid:this.form.dictid,dictname:this.form.dictname,sortno:this.form.sortno}
      }
      // 添加或者修改操作
      const result = await this.$axios.post(`/serviceBasedata/dictentry/${this.dictId == null || this.dictId == '' ? 'add' : 'update'}`, formEntry)
      if (result.flag) {
        // 提示消息
        this.$message({
          type: 'success',
          message: '成功!'
        })
        // 刷新数据表格
        await this.getDictEntryList()
      }
    },
    //删除
    del(){
      this.$confirm('将删除此数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        // 删除操作（物理，逻辑）
        const result = await this.$axios.delete(`/serviceBasedata/dictentry/delete/${this.dictId}`)
        if (result.flag) {
          // 提示消息
          this.$message({
            type: 'success',
            message: '成功!'
          })
          // 刷新数据表格
          await this.getDictEntryList()
          this.$refs.multipleTable2.clearSelection();
          this.dictId='';
        }

      })
    },
    //选中行事件
    tableCurrentChange(val) {
      this.dictTypeId = val.dicttypeid;
      this.dictType = val.dictType;
      this.dictId='';
      this.getDictEntryList();
    },
    tableCurrentChange2(val) {
      this.dictId = val.dictid;
    },

    //行点击事件
    tableClick(row){
      this.$refs.multipleTable.clearSelection();
      this.$refs.multipleTable.toggleRowSelection(row);
    },
    tableClick2(row){
      this.$refs.multipleTable2.clearSelection();
      this.$refs.multipleTable2.toggleRowSelection(row);
    },

    // 页号改变事件
    currentChange(val) {
      this.current = val;
      this.getDictTypeList();
    },
    currentChange2(val) {
      this.current2 = val;
      this.getDictEntryList();
    },

    // 页大小改变事件
    sizeChange(val) {
      this.size = val;
      this.getDictTypeList();
    },
    sizeChange2(val) {
      this.size2 = val;
      this.getDictEntryList();
    }
  },
  created() {
    this.getDictTypeList();
  }
}
</script>

<style scoped>
#type {
  width: 750px;
  height: 540px;
  margin-left: -20px;
  margin-top: -20px;
}

#entry {
  width: 530px;
  height: 540px;
  margin-left: 730px;
  margin-top: -540px;
}

#typeTool {
  margin-top: 10px;
}

#entryTool {
  margin-left: 330px;
}
#entryTable{
  margin-top: 20px;
}
</style>