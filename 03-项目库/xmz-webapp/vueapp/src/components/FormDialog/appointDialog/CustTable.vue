<template>
  <div>
    <el-table ref="table" :data="records" size="mini"
              height="300"
              highlight-current-row border
              row-key="licensePlate"
              @current-change="currentTableRow"
              @select="selectionTableRow"
              @select-all="selectionAllTableRow"

    >
      <el-table-column type="index" align="center" width="50" fixed></el-table-column>
      <el-table-column type="selection" align="center" width="50" fixed></el-table-column>
      <el-table-column prop="licensePlate" label="车牌" width="100"></el-table-column>
      <el-table-column prop="custName" label="客户" width="100"></el-table-column>
      <el-table-column prop="custPhone" label="手机" width="100"></el-table-column>
      <el-table-column prop="custLevel" label="等级" width="150"></el-table-column>
      <el-table-column prop="carName" label="车辆" width="500"></el-table-column>
      <!--    <el-table-column prop="owedSa" label="专属SA" width="100"></el-table-column>-->
      <!--    <el-table-column prop="owedSa" label="专属门店" width="200"></el-table-column>-->
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
  </div>
</template>

<script>
export default {
  name: "CustTable",
  props:{
    search: Object,
  },
  data(){
    return{
      records: [],
      currentKey: '',
      page: {
        size: 20,
        total: 100,
        current: 1,
        pages: 5,
      },
    }
  },
  methods: {
    currentTableRow(currentRow) {
      this.$refs.table.clearSelection()
      this.$refs.table.toggleRowSelection(currentRow)
      this.currentKey = currentRow
      this.$emit("select",this.currentKey)
    },
    selectionTableRow(selection, row) {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow(row);
      this.currentKey = row
      this.$emit("select",this.currentKey)
    },
    selectionAllTableRow() {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow();
      this.currentKey = ""
      this.$emit("select",this.currentKey)
    },
    loadTableData(){
      let _this = this;
      let page = _this.page;
      _this.$axios.get(`/brCarService/other/custPage/${page.current}/${page.size}`,{
        params: _this.search
      }).then(({data})=>{
        _this.records = data.records;
        page.total = data.total;
        page.size = data.size;
      })
    }
  },
  watch:{
    search(search){
      if(search.licensePlate !== this.currentKey.licensePlate){
        this.loadTableData()
      }
    }
  },
  created(){
    let _this = this;
    _this.loadTableData();
  }
}
</script>

<style scoped>

</style>