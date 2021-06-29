<template>
  <div id="parent">

    <div id="tool">
      <!-- 搜索框 -->
      <el-form size="mini" :inline="true" :model="queryParam" Department="demo-form-inline">
        <el-form-item label="专属SA">
          <el-select filterable style="width: 130px" v-model="queryParam.owedSaUuid" placeholder="请选择">
            <el-option v-for="item in saData"
                       :key="item.accountId"
                       :label="item.name"
                       :value="item.accountId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input style="width: 130px" v-model="queryParam.custName"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input style="width: 130px" v-model="queryParam.custPhone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="search"></el-button>
          <el-button type="info" icon="el-icon-close" @click="reset"></el-button>
        </el-form-item>
        <!-- 增删改按钮 -->
        <el-form-item style="margin-left: 100px;">
          <el-button type="primary" @click="add" class="el-icon-plus"></el-button>
          <el-button type="warning" :disabled="custUuid==''||custUuid==null" @click="edit"
                     class="el-icon-edit"></el-button>
          <el-button type="danger" :disabled="custUuid==''||custUuid==null" @click="del"
                     class="el-icon-delete"></el-button>
        </el-form-item>
      </el-form>
    </div>

    <div id="table">
      <!-- 数据表格 -->
      <el-table
          v-loading="loading"
          height="520"
          size="mini"
          :data="custData"
          highlight-current-row
          @current-change="tableCurrentChange"
          ref="multipleTable"
          @row-click="tableClick">
        <el-table-column
            fixed
            align="center"
            type="selection"
            width="50">
        </el-table-column>
        <el-table-column
            fixed
            align="center"
            type="index"
            width="50">
        </el-table-column>
        <el-table-column
            fixed
            align="center"
            label="客户姓名"
            width="100px"
            prop="custName">
        </el-table-column>
        <el-table-column
            fixed
            align="center"
            label="电话"
            width="100px"
            prop="custPhone">
        </el-table-column>
        <el-table-column
            align="center"
            label="性别"
            width="100px"
            prop="dictname">
        </el-table-column>
        <el-table-column
            align="center"
            width="100px"
            prop="saname"
            label="专属SA">
        </el-table-column>
        <el-table-column
            align="center"
            prop="status"
            label="客户状态">
        </el-table-column>
        <el-table-column
            align="center"
            prop="remark"
            label="备注">
        </el-table-column>
        <el-table-column
            align="center"
            prop="lastTime"
            width="200px"
            label="最近来店时间">
        </el-table-column>
        <el-table-column
            align="center"
            prop="saname2"
            label="最近服务SA">
        </el-table-column>
        <el-table-column
            align="center"
            width="200px"
            prop="brName"
            label="最近服务门店">
        </el-table-column>
        <el-table-column
            align="center"
            prop="dictname2"
            label="客户来源">
        </el-table-column>
        <el-table-column
            align="center"
            prop="weixin"
            width="100px"
            label="微信号">
        </el-table-column>
        <el-table-column
            align="center"
            prop="email"
            width="200px"
            label="Email">
        </el-table-column>
        <el-table-column
            align="center"
            prop="birthday"
            width="100px"
            label="生日">
        </el-table-column>
        <el-table-column
            align="center"
            width="200px"
            prop="address"
            label="地址">
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

    <div id="form">
      <!-- 添加编辑对话框 -->
      <el-dialog :title="custUuid == '' ? '添加':'编辑'" :visible.sync="dialogFormVisible" width="800px">
          <!--  表单    -->
          <el-form style="margin-top: 10px" size="mini" inline :model="form">
          <el-form-item label="姓名" label-width="70px">
            <el-input style="width: 130px" v-model="form.custName"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="70px">
            <el-input style="width: 130px" v-model="form.custPhone"></el-input>
          </el-form-item>
          <el-form-item label="性别" label-width="70px">
            <el-select style="width: 130px" v-model="form.gender">
              <el-option label="男" value="M"></el-option>
              <el-option label="女" value="F"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户来源" label-width="70px">
            <el-select filterable style="width: 130px"
                       v-model="form.custSrc" placeholder="请选择">
              <el-option
                  v-for="item in custFromData"
                  :key="item.dictid"
                  :label="item.dictname"
                  :value="item.dictid">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="客户状态" label-width="70px">
            <el-select style="width: 130px" v-model="form.status">
              <el-option label="活跃" value="活跃"></el-option>
              <el-option label="正常" value="正常"></el-option>
              <el-option label="流失" value="流失"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="专属SA" label-width="70px">
            <el-select filterable style="width: 130px"
                       v-model="form.owedSaUuid" placeholder="请选择">
              <el-option
                  v-for="item in saData"
                  :key="item.accountId"
                  :label="item.name"
                  :value="item.accountId">
              </el-option>
            </el-select>
          </el-form-item>
            <el-form-item label="生日" label-width="70px">
              <el-date-picker  style="width: 130px" v-model="form.birthday" type="date" value-format="yyyy-MM-dd"></el-date-picker>
            </el-form-item>
            <el-form-item label="微信" label-width="70px">
              <el-input style="width: 130px" v-model="form.weixin"></el-input>
            </el-form-item>
            <el-form-item label="Email" label-width="70px">
              <el-input style="width: 130px" v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="地址" label-width="70px">
              <el-input style="width:200px" v-model="form.address"></el-input>
            </el-form-item>

        </el-form>

        <!-- 操作按钮 -->
        <div style="margin-top: -50px;" slot="footer" Department="dialog-footer">
          <el-button size="mini" icon="el-icon-close" type="info" @click="cancel"></el-button>
          <el-button size="mini" icon="el-icon-check" type="primary" @click="save"></el-button>
        </div>

      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      custData: [],
      //sa下拉框数据
      saData: [],
      custFromData: [],
      queryParam: {},
      form: {},
      custUuid: '',
      pageSizeList: [5, 10, 15, 20],
      current: 1,
      size: 10,
      total: 0,
      dialogFormVisible: false,
    }
  },
  methods: {
    //该店所有客户
    async getCustData() {
      this.loading = true;
      const result = await this.$axios.post(`/brCrm/cust-cust-info/queryPage/${this.current}/${this.size}/${this.brCode}`, this.queryParam)
      this.custData = result.data.rows
      this.total = result.data.total
      this.loading = false;
    },
    //该店所有SA(用户)
    async getSaData() {
      const result = await this.$axios.get("/brCrm/cust-cust-info/querySaInfoList/"+this.brCode)
      this.saData = result.data
    },
    //客户来源
    async getCustFromData() {
      const result = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_CUST_FROM`)
      this.custFromData = result.data
    },
    search() {
      this.getCustData();
    },
    reset() {
      this.queryParam = {}
    },
    tableCurrentChange(val) {
      this.custUuid = val.custUuid
    },
    tableClick(row) {
      this.custUuid = row.custUuid
      this.$refs.multipleTable.clearSelection();
      this.$refs.multipleTable.toggleRowSelection(row);
    },
    add() {
      this.custUuid=''
      this.form={}
      this.$refs.multipleTable.clearSelection();
      this.dialogFormVisible = true
    },
    del() {
      this.$confirm('将删除此数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        // 删除操作（物理，逻辑）
        const result = await this.$axios.delete(`/brCrm/cust-cust-info/delete/${this.custUuid}`)
        if (result.flag) {
          // 提示消息
          this.$message({
            type: 'success',
            message: '成功!'
          })
          // 刷新数据表格
          await this.getCustData();
          this.custUuid = '';
        }
      })
    },
    async edit() {
      let result = await this.$axios.get(`/brCrm/cust-cust-info/queryById/${this.custUuid}`)
      this.form = result.data
      this.dialogFormVisible = true
    },
    async save() {
      this.dialogFormVisible = false
      this.queryParam = {}
      //当前门店
      this.form.brcode=this.brCode
      // 添加或者修改操作
      let result = await this.$axios.post(`/brCrm/cust-cust-info/${this.custUuid == null || this.custUuid == '' ? 'add' : 'update'}`, this.form)
      if (result.flag) {
        // 提示消息
        this.$message({
          type: 'success',
          message: '成功!'
        })
        // 刷新数据表格
        await this.getCustData()
        this.loading = false
      }
    },
    cancel() {
      this.dialogFormVisible = false;
    },
    // 页号改变事件
    currentChange(val) {
      this.current = val;
      this.getCustData()
    },
    // 页大小改变事件
    sizeChange(val) {
      this.size = val;
      this.getCustData()
    },
  },
  computed:{
    brCode () {
      return this.$store.getters.brCode;
    }
  },
  created() {
    this.getCustData();
    this.getSaData();
    this.getCustFromData()
  }
}
</script>

<style scoped>
#parent {
  width: 1260px;
  height: 620px;
}

#tool {

}

#table {

}
</style>
