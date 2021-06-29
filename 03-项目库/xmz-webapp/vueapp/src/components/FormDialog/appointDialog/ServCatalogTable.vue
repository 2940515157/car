<template>
  <div>
    <el-table ref="table" :data="records" size="mini"
              height="300" border
              :show-summary="records.length>0" :summary-method="getSummaries"
              row-key="catalogUuid"
              @select="selectTableRow" @select-all="selectAllTableRow">
      <el-table-column type="index" align="center" width="70"></el-table-column>
      <el-table-column type="selection" align="center" width="50"></el-table-column>
      <el-table-column prop="catalogName" label="服务项目" header-align="center" width="152">
        <div slot="header">
          服务项目
          <el-link type="primary" style="float:right;font-size: smaller" @click="openScd">添加</el-link>
        </div>
      </el-table-column>
      <el-table-column prop="stdManHour" label="标准工时(时)" header-align="center" width="100"></el-table-column>
      <el-table-column prop="stdPrice" label="标准价格(元)" header-align="center" width="100"></el-table-column>
      <el-table-column prop="stdSideNum" label="标准面数" header-align="center" width="80"></el-table-column>
      <el-table-column prop="stdAmt" label="标准金额(元)" header-align="center" width="100"></el-table-column>
      <el-table-column prop="discount" label="折扣" align="center" width="111">
        <template v-if="!row.top"  slot-scope="{row}">
          <el-input-number v-model="row.discount" size="mini"
                           :min="0" :max="100"
                           style="width: 89px;"
                           @blur="row.discount?'':row.discount=0"
                           @change="rowDiscountChange(row)"
                           aria-required="true"
                           controls-position="right"
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="price" label="结算金额(元)" header-align="center" width="100"></el-table-column>
      <el-table-column>
        <template v-if="!row.top" slot-scope="{row}">
          <el-link type="primary" style="float:right;font-size: smaller" @click="removeRecord(row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <serv-catalog-dialog ref="scd" @submit="scdSubmit"></serv-catalog-dialog>
  </div>
</template>

<script>
import ServCatalogDialog from "./ServCatalogDialog.vue"

let topRecords = {top:true,catalogUuid: -1,catalogName:"请选择",stdManHour:"0",stdAmt:"0",stdPrice:"0",price:"0"};

export default {
  name: "ServOption",
  components: {
    ServCatalogDialog
  },
  data() {
    return {
      records: [topRecords],
      selectedTableRow: [],
      scdRows: new Set(),
      deleteTableIds:[],
      checkoutTableRow: [],
    }
  },
  methods: {
    getSummaries(param) {
      let {columns} = param
      let data = this.selectedTableRow
      let sums = []
      let fields = ["stdManHour", "stdPrice", "stdAmt", "price"]
      let total = {};
      fields.forEach(f => {
        total[f] = 0
      })
      if (data.length > 0) {
        data.forEach(item => {
          fields.forEach(f => {
            let num = Number(item[f]);
            total[f] = total[f] += num;
          })
        });
      }
      columns.forEach(({property}, index) => {
        if (index === 0) {
          sums[index] = '总计';
          return
        }
        if (property === "discount") {
          let stdAmt = total["stdAmt"]
          let price = total["price"]
          sums[index] = (((price / stdAmt) * 100)||0).toFixed(2) + "%"
          return
        }
        if (["stdAmt", "price"].indexOf(property) > -1) {
          sums[index] = total[property].toFixed(2)
          return
        }
        if (fields.indexOf(property) > -1) {
          sums[index] = total[property]
        }
      })
      // console.log(sums,data)
      return sums
    },
    rowDiscountChange(row) {
      let {stdAmt, discount} = row;
      row.price = (stdAmt * (100 - (discount ? discount : 0)) / 100).toFixed(2)
    },
    selectTableRow(selection,row) {
      let _this = this;
      let ti = selection.indexOf(topRecords)
      if(ti>-1){
        _this.$refs["table"].toggleRowSelection(topRecords)
      }
      if(row.itemUuid){
        let i = _this.checkoutTableRow.indexOf(row)
        if( row.isActive === "Y") {
          row.isActive = "N";
          _this.checkoutTableRow.push(row);
        }else {
          row.isActive = "Y";
          _this.checkoutTableRow.splice(i,1)
        }
      }
      this.selectedTableRow = selection
    },
    selectAllTableRow(selection){
      this.selectedTableRow = selection
    },
    openScd() {
      this.$refs.scd.show()
    },
    removeRecord(row){
      let _this = this;
      let ri = _this.records.indexOf(row);
      this.records.splice(ri,1);
      let rsi = _this.selectedTableRow.indexOf(row);
      if(rsi>-1){
        this.selectedTableRow.splice(rsi,1);
      }
      if(row.itemUuid){
        _this.deleteTableIds.push(row.itemUuid);
      }
      _this.scdRows.size>0?_this.scdRows.delete(row.catalogUuid):"";
    },
    scdSubmit(rows) {
      let _this = this;
      let rowCopy = JSON.parse(JSON.stringify(rows));
      rowCopy.forEach(row => {
        if (!this.scdRows.has(row.catalogUuid)) {
          let {stdPrice} = row;
          _this.scdRows.add(row.catalogUuid)
          Object.assign(row, {stdAmt: stdPrice, discount: 0, price: stdPrice,isActive: "Y"})
          _this.records.push(row)
          _this.$refs["table"].toggleRowSelection(row)
          _this.selectedTableRow.push(row)
        }
      })

    },
    getSelectionInServItem(){
      let selection = [];
      this.selectedTableRow.forEach(item=>{
        if (item.top) return
        selection.push({
          itemUuid: item.itemUuid||null,
          sbUuid: item.sbUuid||null,
          servItemUuid: item.catalogUuid,
          stdManHour: item.stdManHour,
          stdPrice: item.stdPrice,
          stdSideNum: item.stdSideNum,
          stdAmt: item.stdAmt,
          discount: item.discount,
          price: item.price,
          isActive: item.isActive,
        })
      })
      return selection;
    },
    loadingItems(items){
      let _this = this;
      _this.scdRows.size>0?_this.scdRows.clear():"";
      _this.records.splice(0,_this.records.length,topRecords)
      _this.$refs["table"].clearSelection();
      _this.selectedTableRow = []
      items.forEach(item=>{
        // let row = {
        //   catalogUuid: item.servItemUuid,
        //   stdManHour: item.stdManHour,
        //   stdPrice: item.stdPrice,
        //   stdSideNum: item.stdSideNum,
        //   stdAmt: item.stdAmt,
        //   discount: item.discount,
        //   price: item.price
        // }
        let row = item;
        _this.scdRows.add(row.catalogUuid)
        _this.records.push(row)
        if(row.isActive === "Y"){
          _this.$refs["table"].toggleRowSelection(row)
          _this.selectedTableRow.push(row)
        }
      })
    }
  },
  created() {
  }
}
</script>

<style scoped>
.tab_bottom_row {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  background: #e8f3fe;
  color: #7dbcfc;
}
</style>