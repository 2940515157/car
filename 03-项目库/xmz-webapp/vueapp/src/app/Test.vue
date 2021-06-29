<template>
  <div >
    <el-table
        :data="tableData"
        height="250"
        border
        lazy :load="load" row-key="date"
        style="width: 100%">
      <el-table-column
          prop="date"
          label="日期"
          width="180">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="180">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "Test",
  data () {
    return {
      tableData: function (){
        let d = []
        for (let i = 0; i < 20; i++) {
          d.push({
            date: `201${i}-05-03`,
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄',
            hasChildren:true,
          })
        }
        return d
      }(),
      count: 0
    }
  },
  methods: {
    async load (data,node){
      console.log(data,node)
    }
  }
}
</script>

<style scoped>

</style>

<style>
.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: #e8f3fe;
  margin: 10px;
  color: #7dbcfc;
}
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
}
</style>