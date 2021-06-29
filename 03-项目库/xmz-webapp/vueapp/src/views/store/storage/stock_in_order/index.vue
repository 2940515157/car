<!-- - - - - - - - - - - - -->
<!--        入库单        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <el-form :inline="true" :model="wwhouseEnterQuery" class="demo-form-inline"  :label-position="left" label-width="100px">
      <el-form-item>
  入库单号：<el-input v-model="wwhouseEnterQuery.wweNo" style="width: 180px"></el-input>
    仓库：<el-select v-model="wwhouseEnterQuery.wmWarehouseName" placeholder="请选择">
        <el-option label="--请选择--" value=""></el-option>
      <el-option
          v-for="item in wwhouselist"
          :key="item.wmWarehouseName"
          :label="item.wmWarehouseName"
          :value="item.wmWarehouseName">
      </el-option>
    </el-select>
    入库类型：<el-select v-model="wwhouseEnterQuery.dictid1" placeholder="请选择">
        <el-option label="--请选择--" value=""></el-option>
    <el-option
        v-for="item in wwetypelist"
        :key="item.dictid1"
        :label="item.dictname"
        :value="item.dictid1">
    </el-option>
  </el-select>
    来源类型：<el-select v-model="wwhouseEnterQuery.dictid2" placeholder="请选择">
        <el-option label="--请选择--" value=""></el-option>
    <el-option
        v-for="item in fromtypelist"
        :key="item.dictid2"
        :label="item.dictname2"
        :value="item.dictid2">
    </el-option>
  </el-select>
    <el-button type="primary" @click="sea" >查询</el-button><br>
    </el-form-item>
    </el-form>
  <el-table
      :data="wwelist"
      border
      style="width: 100%">
    <el-table-column
        label="ID"
        align="center"
        type="index"
        width="50">
    </el-table-column>
    <el-table-column
        prop="wweNo"
        label="入库单号"
        width="180">
    </el-table-column>
    <el-table-column
        prop="wmWarehouseName"
        label="仓库名称"
        width="180">
    </el-table-column>
    <el-table-column
        prop="wweType"
        label="入库类型"
        width="180"
        :formatter=fmtt>
    </el-table-column>
    <el-table-column
        prop="createdDate"
        label="入库时间"
        width="180">
    </el-table-column>
    <el-table-column
        prop="createdBy"
        label="操作人"
        width="180">
    </el-table-column>
    <el-table-column
      prop="fromType"
      label="来源类型"
      width="180"
      :formatter=fmt>
  </el-table-column>
    <el-table-column
        prop="status"
        label="状态"
        :formatter=fmts>
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
      wwhouselist:[],
      wwetypelist:[],
      fromtypelist:[],
      // 当前页号
      current: 1,
      // 分页列表
      pageSizeList: [2, 5, 10, 20, 50, 100],
      // 页大小
      limit: 10,
      // 总记录数
      total: 0,
      //保存条件查询的数据
      wwhouseEnterQuery:{
        dictid1:"",
        dictid2:"",
        dictname2:"",
        dictname:"",
        wweNo:"",
        wmWarehouseName:"",
        wweType:"",
        fromType:"",
        wmWarehouseUuid:""
      },
      wwelist: {},
    }
  },
  methods:{
    fmts(row) {
      if (row.status == 'confirm') {
        return '已确认'
      }
      if (row.status == 'cancel') {
        return '未确认'
      }
    },
    fmtt(row) {
      if (row.wweType == 'allotIn') {
        return '调拨入库'
      }
      if (row.wweType == 'dbpAdjustIn') {
        return '铺货调整入库'
      }
      if (row.wweType == 'dbpRecall') {
        return '铺货召回入库'
      }
      if (row.wweType == 'dbpReject') {
        return '铺货拒绝入库'
      }
      if (row.wweType == 'initIn') {
        return '库存初始化'
      }
      if (row.wweType == 'inventory') {
        return '盘点入库'
      }
      if (row.wweType == 'partsIn') {
        return '采购入库'
      }
      if (row.wweType == 'returnMaterial') {
        return '退货入库'
      }
    },
    fmt(row){
      if (row.fromType=='dbp'){
        return '铺货'
      }
      if (row.fromType=='dbpIn'){
        return '铺货入库'
      }
      if (row.fromType=='initIn'){
        return '初始化入库'
      }
      if (row.fromType=='inventoryIn'){
        return '盘点入库'
      }
      if (row.fromType=='purchaseOrder'){
        return '采购订单'
      }
      if (row.fromType=='returnGoods'){
        return '退货出库'
      }
      if (row.fromType=='returnMaterial'){
        return '退料入库'
      }
      if (row.fromType=='reverse'){
        return '撤销入库'
      }
      if (row.fromType=='reverseIn'){
        return '撤销出库'
      }
      if (row.fromType=='servMaterialReq'){
        return '领料单'
      }
      if (row.fromType=='storeDevolve'){
        return '门店调拨'
      }
      if (row.fromType=='vndrRecall'){
        return '供应商召回'
      }

    },

    //查询
    async sea(){
      //console.log(this.servCheckQuery)
      console.log(this.wwhouseEnterQuery)
      const _this = this
      const response = await _this.$axios.post(`/brStorages/wm-warehouse-enter/page/${this.current}/${this.limit}`,
          this.wwhouseEnterQuery)
      console.log("响应结果",response)
      this.wwelist = response.data.rows
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
    getallwwehouse(){
      this.$axios.get("brStorages/wm-warehouse-enter/queryall").then(resp =>{
        this.wwhouselist=resp.data.list
        //console.log()
      })
    },
    getallwwetype(){
      this.$axios.get("brStorages/wm-warehouse-enter/listAllwwetype").then(resp =>{
        this.wwetypelist=resp.data.listAllwwetype
        //console.log()
      })
    },
    getallfromtype(){
      this.$axios.get("brStorages/wm-warehouse-enter/listAllfromtype").then(resp =>{
        this.fromtypelist=resp.data.listAllfromtype
        //console.log()
      })
    },
    // 获取数据绑定到表格
    async getList() {
      console.log(this.wwhouseEnterQuery)
      const _this = this
      const response = await _this.$axios.post(`/brStorages/wm-warehouse-enter/page/${this.current}/${this.limit}`,
          this.wwhouseEnterQuery)
      console.log("响应结果",response)
      this.wwelist = response.data.rows
      this.total = response.data.total
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
    this.getList(),

    this.getallwwehouse(),

    this.getallwwetype(),

    this.getallfromtype()
  }
}
</script>

<style scoped>

</style>
