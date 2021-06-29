<template>
  <div>
    <el-table ref="table" :data="records" size="mini"
              height="300" border
              :show-summary="records.length>0" :summary-method="getSummaries"
              row-key="partUuid"
              @select="selectTableRow" @select-all="selectAllTableRow">
      <el-table-column type="index" align="center" width="70" fixed></el-table-column>
      <el-table-column type="selection" align="center" width="50" fixed></el-table-column>
      <el-table-column prop="showName" label="配件名称" header-align="center" width="200">
        <div slot="header">
          服务项目
          <el-link type="primary" style="float:right;font-size: smaller" @click="opencpd">添加</el-link>
        </div>
      </el-table-column>
      <el-table-column prop="partName" label="配件目录" header-align="center" width="100"></el-table-column>
      <el-table-column prop="stockNum" label="库存数量" header-align="center" width="100"></el-table-column>
      <el-table-column prop="salPrice" label="单价(元)" header-align="center" width="80"></el-table-column>
      <el-table-column prop="unit" label="单位" header-align="center" width="80"></el-table-column>
      <el-table-column prop="partNum" label="数量" align="center" width="111">
        <template v-if="!row.top"  slot-scope="{row}">
          <el-input-number v-model="row.partNum" size="mini"
                           :min="1" :max="row.stockNum"
                           style="width: 89px;"
                           @blur="row.partNum?'':row.partNum=0"
                           @change="rowComputeTotal(row)"
                           aria-required="true"
                           controls-position="right"
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="stdAmt" label="标准金额(元)" header-align="center" width="100"></el-table-column>
      <el-table-column prop="discount" label="折扣" align="center" width="111">
        <template v-if="!row.top"  slot-scope="{row}">
          <el-input-number v-model="row.discount" size="mini"
                           :min="0" :max="100"
                           style="width: 89px;"
                           @blur="row.discount?'':row.discount=0"
                           @change="rowComputeTotal(row)"
                           aria-required="true"
                           controls-position="right"
          ></el-input-number>
        </template>
      </el-table-column>
      <el-table-column prop="price" label="结算金额(元)" header-align="center" width="100"></el-table-column>
      <el-table-column >
        <template v-if="!row.top" slot-scope="{row}">
          <el-link type="primary" style="float:right;font-size: smaller" @click="removeRecord(row)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <carsparts-dialog ref="cpd" @submit="cpdSubmit"></carsparts-dialog>
  </div>
</template>

<script>
import CarspartsDialog from "./CarspartsDialog.vue"

let topRecords = {top:true,partUuid: -1,showName:"请选择",stdAmt:"0",salPrice:"0",price:"0"};

export default {
  name: "ServOption",
  components: {
    CarspartsDialog
  },
  data() {
    return {
      records: [topRecords],
      selectedTableRow: [],
      cpdRows: new Set(),
      deleteTableIds: [],
      checkoutTableRow: [],
    }
  },
  methods: {
    getSummaries(param) {
      let {columns} = param
      let data = this.selectedTableRow
      let sums = []
      let fields = ["salPrice", "stdAmt", "price"]
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
    rowComputeTotal(row) {
      let {salPrice,partNum,discount} = row;
      row.stdAmt = Number(salPrice*partNum)
      row.price = (row.stdAmt * (100 - (discount ? discount : 0)) / 100).toFixed(2)
    },
    selectTableRow(selection,row) {
      let _this = this;
      let ti = selection.indexOf(topRecords)
      if(ti>-1){
        _this.$refs["table"].toggleRowSelection(topRecords)
      }
      if(row.sbpUuid){
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
    opencpd() {
      this.$refs.cpd.show()
    },
    removeRecord(row){
      let _this = this;
      let ri = _this.records.indexOf(row);
      this.records.splice(ri,1);
      let rsi = _this.selectedTableRow.indexOf(row);
      console.log(_this.selectedTableRow,rsi)
      if(rsi>-1){
        this.selectedTableRow.splice(rsi,1);
      }
      if(row.sbpUuid){
        _this.deleteTableIds.push(row.sbpUuid);
      }
      _this.cpdRows.size>0?_this.cpdRows.delete(row.partUuid):"";
    },
    cpdSubmit(rows) {
      let _this = this;
      let rowCopy = JSON.parse(JSON.stringify(rows));
      rowCopy.forEach(row => {
        if (!this.cpdRows.has(row.partUuid)) {
          let salPrice = Number((35+100*Math.random()).toFixed(0));
          let stockNum = Number((35+100*Math.random()).toFixed(0))
          this.cpdRows.add(row.partUuid)
          Object.assign(row, {
            stockNum, partNum: 1,salPrice,stdAmt: salPrice, discount: 0, price: salPrice,isActive: "Y"
          })
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
          sbpUuid: item.sbpUuid||null,
          sbUuid: item.sbUuid||null,
          partUuid: item.partUuid,
          stockNum: item.stockNum,
          salPrice: item.salPrice,
          partNum: item.partNum,
          stdAmt: item.stdAmt,
          discount: item.discount,
          price: item.price,
          unit: item.unit,
          isActive: item.isActive,
        })
      })
      return selection;
    },
    loadingItems(items){
      let _this = this;
      _this.cpdRows.size>0?_this.cpdRows.clear():"";
      _this.records.splice(0,_this.records.length,topRecords)
      _this.$refs["table"].clearSelection();
      _this.selectedTableRow = []
      items.forEach(item=>{
        // let row = {
        //   artUuid: item.partUuid,
        //   stockNum: item.stockNum,
        //   salPrice: item.salPrice,
        //   partNum: item.partNum,
        //   stdAmt: item.stdAmt,
        //   discount: item.discount,
        //   price: item.price,
        //   unit: item.unit,
        // }
        let row = item
        _this.cpdRows.add(row.partUuid)
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