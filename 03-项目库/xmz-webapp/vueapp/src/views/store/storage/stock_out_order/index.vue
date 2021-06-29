<!-- - - - - - - - - - - - -->
<!--        出库单        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <el-form :inline="true" :model="wwhouseOutQuery" class="demo-form-inline"  :label-position="left" label-width="100px">
      <el-form-item>
        车牌：<el-input v-model="wwhouseOutQuery.licensePlate" style="width: 150px"></el-input>
        车主：<el-input v-model="wwhouseOutQuery.custName" style="width: 150px"></el-input>
        接车单据号：<el-input v-model="wwhouseOutQuery.revNo" style="width: 180px"></el-input>
        出库类型：<el-select v-model="wwhouseOutQuery.dictid1" placeholder="请选择" style="width: 150px">
        <el-option label="--请选择--" value=""></el-option>
        <el-option
            v-for="item in wwotypelist"
            :key="item.dictid1"
            :label="item.dictname"
            :value="item.dictid1">
        </el-option>
      </el-select>
        来源类型：<el-select v-model="wwhouseOutQuery.dictid2" placeholder="请选择" style="width: 150px">
        <el-option label="--请选择--" value=""></el-option>
        <el-option
            v-for="item in wwofromtypelist"
            :key="item.dictid2"
            :label="item.dictname2"
            :value="item.dictid2">
        </el-option>
      </el-select>
        <el-button type="primary" @click="sea" >查询</el-button><br>
      </el-form-item>
    </el-form>
  <el-table
      :data="wwolist"
      border
      style="width: 100%">
    <el-table-column
        prop="wmWarehouseName"
        label="出库仓库"
        width="180">
    </el-table-column>
    <el-table-column
        prop="wwoType"
        label="出库类型"
        width="140"
        :formatter=fmtot>
    </el-table-column>
    <el-table-column
        prop="createdBy"
        label="操作人"
        width="80">
    </el-table-column>
    <el-table-column
        prop="createdDate"
        label="操作时间"
        width="160">
    </el-table-column>
    <el-table-column
        prop="fromType"
        label="来源类型"
        width="140"
        :formatter=fmtof>
    </el-table-column>
    <el-table-column
        prop="totalAmount"
        label="总金额"
        width="70">
    </el-table-column>
    <el-table-column
        prop="revNo"
        label="接车单"
        width="155">
    </el-table-column>
    <el-table-column
        prop="wwoNo"
        label="出库单号"
        width="160">
    </el-table-column>
    <el-table-column
        prop="status"
        label="出库状态"
        width="80"
        :formatter=fmtos>
    </el-table-column>
    <el-table-column
        prop="licensePlate"
        label="车牌"
        width="120">
    </el-table-column>
    <el-table-column
        prop="custName"
        label="车主"
        width="80">
    </el-table-column>
    <el-table-column
        prop="custPhone"
        label="车主电话"
        width="120">
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
          wwotypelist:[],
          wwofromtypelist:[],
      // 当前页号
      current: 1,
      // 分页列表
      pageSizeList: [2, 5, 10, 20, 50, 100],
      // 页大小
      limit: 10,
      // 总记录数
      total: 0,

      wwhouseOutQuery:{
        dictid1:"",
        dictid2:"",
        custName:"",
        licensePlate:"",
        revNo:"",
      },
      wwolist: {},
    }
      },
      methods:{
        fmtot(row) {
          if (row.wwoType == 'partsOut') {
            return '配件领料出库'
          }
          if (row.wwoType == 'boutiqueOut') {
            return '精品出库'
          }
          if (row.wwoType == 'purchaseReturnOut') {
            return '采购退件出库'
          }
          if (row.wwoType == 'inventory') {
            return '盘点出库'
          }
          if (row.wwoType == 'partsSaleOut') {
            return '精品出库'
          }
          if (row.wwoType == 'boutiqueOut') {
            return '配件销售出库'
          }
          if (row.wwoType == 'allotOut') {
            return '调拨出库'
          }
          if (row.wwoType == 'reverseOut') {
            return '入库撤销'
          }
          if (row.wwoType == 'vndrRecall') {
            return '供应商召回'
          }
          if (row.wwoType == 'dbpOut') {
            return '铺货出库'
          }
          if (row.wwoType == 'inventoryLoss') {
            return '盘亏出库'
          }
          if (row.wwoType == 'dbpAdjust') {
            return '铺货调整出库'
          }
        },
        fmtof(row) {
          if (row.fromType == 'purchaseOrder') {
            return '采购订单'
          }
          if (row.fromType == 'storeDevolve') {
            return '门店调拨'
          }
          if (row.fromType == 'servMaterialReq') {
            return '领料单'
          }
          if (row.fromType == 'reverse') {
            return '撤销入库'
          }
          if (row.fromType == 'returnGoods') {
            return '退货出库'
          }
          if (row.fromType == 'vndrRecall') {
            return '供应商召回'
          }
          if (row.fromType == 'dbp') {
            return '铺货'
          }
        },
        fmtos(row) {
          if (row.status == 'confirm') {
            return '已确认'
          }
          if (row.status == 'cancel') {
            return '未确认'
          }
          },

        // 查询
        async sea(){
          this.getList()
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
        getallwwotype(){
          this.$axios.get("brStorages/wm-warehouse-Out/listAllwwotype").then(resp =>{
            this.wwotypelist=resp.data.listAllwwotype
            //console.log()
          })
        },
        getallwwofromtype(){
          this.$axios.get("brStorages/wm-warehouse-Out/listAllfromtype").then(resp =>{
            this.wwofromtypelist=resp.data.listAllfromtype
            //console.log()
          })
        },
        // 获取数据绑定到表格
        async getList() {
          console.log(this.wwhouseOutQuery)
          const _this = this
          const response = await _this.$axios.post(`/brStorages/wm-warehouse-Out/page/${this.current}/${this.limit}`,
              this.wwhouseOutQuery)
          console.log("响应结果",response)
          this.wwolist = response.data.rows
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
      created(){

        this.getList()

        this.getallwwotype()

        this.getallwwofromtype()

      }
    }
</script>

<style scoped>

</style>
