<!-- - - - - - - - - - - - -->
<!--        呼叫中心回访        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <el-form :inline="true" :model="hrvistiQuery" class="demo-form-inline"  :label-position="left" label-width="100px">
      <el-form-item>
      大区：<el-select @change="orgChange" v-model="hrvistiQuery.orgUuid" placeholder="--请选择--" >
        <el-option label="--请选择--" value=""></el-option>
        <el-option
        v-for="item in orglist"
        :key="item.orgName"
        :label="item.orgName"
        :value="item.orgUuid">
      </el-option>
    </el-select>&emsp;
      区域：<el-select @change="orgChange2" v-model="hrvistiQuery.areaUuid" placeholder="--请选择--" >
        <el-option label="--请选择--" value=""></el-option>
        <el-option
        v-for="item in arealist"
        :key="item.areaName"
        :label="item.areaName"
        :value="item.areaUuid">
      </el-option>
    </el-select>&emsp;
      门店：<el-select v-model="hrvistiQuery.brUuid" placeholder="--请选择--" >
        <el-option label="--请选择--" value=""></el-option>
        <el-option
        v-for="item in brancheslist"
        :key="item.brName"
        :label="item.brName"
        :value="item.brUuid">
      </el-option>
    </el-select>&emsp;
      <el-button type="primary" @click="sea" >查询</el-button><br>
      </el-form-item>
    </el-form>
    <el-table
      :data="hrvisitlist"
      border
      style="width: 100%">
      <el-table-column
          label="ID"
          align="center"
          type="index"
          width="50">
      </el-table-column>
      <el-table-column
        prop="brCode"
        label="门店编码"
        width="80">
      </el-table-column>
      <el-table-column
        prop="brName"
        label="门店名称"
        width="250">
      </el-table-column>
      <el-table-column
        prop="custName"
        label="车主"
        width="70">
      </el-table-column>
      <el-table-column
        prop="custPhone"
        label="车主电话"
        width="120">
      </el-table-column>
      <el-table-column
        prop="licensePlate"
        label="车牌"
        width="90">
      </el-table-column>
      <el-table-column
        prop="maintPosition"
        label="维修明细"
        width="140">
      </el-table-column>
      <el-table-column
        prop="ttlAmt"
        label="维修金额"
        width="80">
      </el-table-column>
      <el-table-column
        prop="lastArriveDate"
        label="上次维修时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="revNo"
        label="接车单据号">
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      //门店下拉框数据
      brancheslist:[],
      //区域下拉框数据
      arealist:[],
      //大区下拉框数据
      orglist:[],
      // 保存查询条件的对象
      hrvistiQuery: {
        areaUuid: "",
        orgUuid:"",
        brUuid:""
      },
      // 当前页号
      current: 1,
      // 分页列表
      pageSizeList: [2, 5, 10, 20, 50, 100],
      // 页大小
      limit: 5,
      // 总记录数
      total: 0,
      formButtonWidth:'550px',
      formLabelWidth:'100px',
      dialogFormVisible: false,
      form: {
        checkname: '',
      },
      hrvisitlist:{},
    }
  },
  methods:{
    // 获取数据绑定到表格
    async getList() {
      console.log(this.hrvistiQuery)
      const _this = this
      const response = await _this.$axios.post(`/serviceHr/serv-visit-record/page/${this.current}/${this.limit}`,
          this.hrvistiQuery)
      console.log("响应结果",response)
      this.hrvisitlist = response.data.rows
      this.total = response.data.total
    },
    //  大区下拉框改变事件
    orgChange(){
      this.hrvistiQuery.areaUuid = ""
      this.getallarea();
    },
    orgChange2(){
      this.hrvistiQuery.brUuid = ""
      this.getallbranches();
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
    //查询
    async sea(){
      //console.log(this.servCheckQuery)
        console.log(this.hrvistiQuery)
        const _this = this
        const response = await _this.$axios.post(`/serviceHr/serv-visit-record/page/${this.current}/${this.limit}`,
            this.hrvistiQuery)
        console.log("响应结果",response)
        this.hrvisitlist = response.data.rows
        this.total = response.data.total
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
    //拿值赋值
    getallorg(){
      this.$axios.get("serviceHr/serv-visit-record/queryall").then(resp =>{
        this.orglist=resp.data.list
        //console.log()
      })
    },
    getallarea(){
      this.$axios.post("serviceHr/serv-visit-record/queryallarea",
          this.hrvistiQuery).then(resp =>{
        this.arealist=resp.data.listarea
        console.log(1,this.arealist)
        const orgId=resp.data.listarea.parentUuid
        console.log(2,orgId)
      })
    },

    getallbranches(){
      this.$axios.post("serviceHr/serv-visit-record/queryallbranches",
          this.hrvistiQuery).then(resp =>{
        this.brancheslist=resp.data.listbranches
        console.log(1,this.brancheslist)
      })
    },
  },
  created() {
    this.getList()

    this.getallorg()

    this.getallarea()

    this.getallbranches()
  }
}


</script>

<style scoped>

</style>
