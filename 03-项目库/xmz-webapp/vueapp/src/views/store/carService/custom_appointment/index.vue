<!-- - - - - - - - - - - - -->
<!--        客户预约        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <!-- 头部 -->
    <div class="header-panel">
      <el-row>
        <el-col :span="2">
          <el-popover
              placement="right-start"
              trigger="click"
              width="600"
          >
            <div>预约单查询</div>
            <!-- 分隔符 -->
            <div style="margin:5px -10px;border-bottom: 1px solid #EBEEF5;"></div>
            <el-form :model="topSearchBox.form" size="mini" :inline="true" label-position="left" >
              <el-form-item label="车牌" label-width="50px">
                <el-input v-model="topSearchBox.form.licensePlate" style="width:240px"></el-input>
              </el-form-item>
              <el-form-item label="手机" label-width="50px">
                <el-input v-model="topSearchBox.form.custPhone" style="width:240px"></el-input>
              </el-form-item>
              <el-form-item label="客户姓名" label-width="80px">
                <el-input v-model="topSearchBox.form.custName" style="width:210px"></el-input>
              </el-form-item>
              <el-form-item label="预约单号" label-width="80px">
                <el-input v-model="topSearchBox.form.sblNo" style="width:210px"></el-input>
              </el-form-item>
              <el-form-item label="单号状态" label-width="80px">
                <el-input v-model="topSearchBox.form.status" style="width:210px"></el-input>
              </el-form-item>
              <el-form-item label="预约时间" label-width="80px">
                <el-date-picker
                    v-model="topSearchBox.form.blDate"
                    type="daterange"
                    align="left"
                    unlink-panels
                    value-format="yyyy-MM-dd"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    :picker-options="topSearchBox.pickerOptions">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="录入时间" label-width="80px">
                <el-date-picker
                    v-model="topSearchBox.form.createTime"
                    type="daterange"
                    align="left"
                    unlink-panels
                    value-format="yyyy-MM-dd"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    :picker-options="topSearchBox.pickerOptions">
                </el-date-picker>
              </el-form-item>
            </el-form>
            <!-- 分隔符 -->
            <div style="margin:5px -10px;border-bottom: 1px solid #EBEEF5;"></div>
            <el-button style="float: right; margin:0 5px"
                       type="primary" size="mini" plain
                       icon="el-icon-search"
            @click="loadTableData">查询</el-button>
            <el-button slot="reference" size="mini" icon="el-icon-search" plain></el-button>
          </el-popover>
        </el-col>
        <el-col :span="22">
          <el-popover
              placement="left"
              trigger="click">
            <el-button type="primary" style="margin:0 5px" plain size="mini">重置密码
            </el-button>
            <el-button slot="reference" style="float: right;margin:0 5px"
                       plain size="mini" icon="el-icon-menu"></el-button>
          </el-popover>
          <el-button type="warning" style="float: right;margin:0 5px"
                     size="mini" icon="el-icon-edit" plain
                     @click="handleEdit">修改
          </el-button>
          <el-button type="primary" style="float: right;margin:0 5px"
                     size="mini" icon="el-icon-plus" plain
                     @click="handleAdd">增加
          </el-button>
        </el-col>
      </el-row>
    </div>
    <!-- 分隔符 -->
    <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
    <!-- 表主体 -->
    <el-table ref="table" size="mini" highlight-current-row :height="tableHeight" :data="tableData" :fit="false"
              class="mainDataTable"
              style=" margin: 0 -20px"
              border
              @current-change="currentTableRow"
              @select="selectionTableRow"
              @select-all="selectionAllTableRow"
    >
      <el-table-column type="index" align="center" width="50" fixed></el-table-column>
      <el-table-column type="selection" align="center" width="50" fixed></el-table-column>
      <el-table-column prop="licensePlate" label="车牌" width="100"></el-table-column>
      <el-table-column prop="custName" label="客户信息" width="80"></el-table-column>
      <el-table-column prop="custPhone" label="手机" width="100"></el-table-column>
      <el-table-column prop="saName" label="SA" width="80"></el-table-column>
      <el-table-column prop="createdDate" label="录入时间" width="150"></el-table-column>
      <el-table-column prop="blDate" label="预约时间" width="150"></el-table-column>
      <el-table-column prop="carName" label="车辆信息" width="350"></el-table-column>
      <el-table-column prop="status" label="单据状态" width="80"></el-table-column>
      <el-table-column prop="sblNo" label="预约单号" width="150"></el-table-column>
      <el-table-column prop="sblType" label="预约类型" width="100"></el-table-column>
      <el-table-column prop="remark" label="备注" width="250"></el-table-column>

    </el-table>
    <!-- 分页 -->
    <div>
      <el-pagination
          :page-sizes="[10, 20, 50, 100]"
          :page-size.sync="page.size"
          :total="page.total"
          :current-page.sync="page.current"
          @size-change="loadTableData"
          @current-change="loadTableData"
          layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>

    <appoint-dialog ref="myDialog" @submit="myDialogSubmit"></appoint-dialog>

  </div>
</template>

<script>
import AppointDialog from "@/components/FormDialog/appointDialog"

export default {
  name: "index",
  components:{
    AppointDialog
  },
  data(){
    return {
      topSearchBox:{
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        form:{},
      },
      tableData: [],
      tableHeight: window.innerHeight - 178,
      currentChangeKey: "",
      page: {
        size: 10,
        total: 100,
        current: 1
      },
    }
  },
  methods: {
    handleRemove(){
      alert("TRUE")
    },
    handleEdit(){
      const _this = this;
      if (_this.currentChangeKey) {
        this.$refs["myDialog"].showIn("edit",_this.currentChangeKey.sbUuid);
      } else {
        _this.$alert('你没有选择记录，不能修改', '请选择记录', {
          confirmButtonText: '确定',
          callback: () => {
            this.$message({
              type: 'info',
              message: '请选择记录'
            });
          }
        })
      }
    },
    handleAdd(){
      this.$refs["myDialog"].showIn("add");
    },
    currentTableRow(currentRow) {
      this.$refs.table.clearSelection()
      this.$refs.table.toggleRowSelection(currentRow)
      this.currentChangeKey = currentRow

    },
    selectionTableRow(selection, row) {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow(row);
      this.currentChangeKey = row
    },
    selectionAllTableRow() {
      this.$refs.table.clearSelection()
      this.$refs.table.setCurrentRow();
      this.currentChangeKey = ""
    },
    loadTableData(){
      let _this = this;
      let page = _this.page;
      let search = _this.topSearchBox.form;
      _this.$axios.get(`/brCarService/serv-booking-head/listPage/${page.current}/${page.size}`,{
        params: search
      }).then(({data})=>{
        _this.tableData = data.records
        page.total = data.total;
      })
    },
    myDialogSubmit(){
      this.loadTableData();
    }
  },
  created() {
    let _this = this;
    _this.loadTableData();
    document.querySelector("body").onresize = function () {
      _this.tableHeight = innerHeight - 178
    }
  }
}
</script>

<style scoped>
.mainDataTable {
  width: calc(100% + 40px);
  max-width: calc(100% + 40px);
}
</style>
