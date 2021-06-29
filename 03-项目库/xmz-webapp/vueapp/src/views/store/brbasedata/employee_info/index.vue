<template>
  <div>

    <!--查询表单开始-->
                    
    <el-form :inline="true" :model="hrEmployessQuery" class="demo-form-inline" style="padding:20px;padding-bottom:0">


      <el-form-item label="姓名">
        <el-input v-model="hrEmployessQuery.name" placeholder="姓名" clearable :size='s'></el-input>
      </el-form-item>

<!--      <el-form-item label="员工状态">
        <el-select v-model="hrEmployessQuery.empStatus" placeholder="请选择" :size='s'>
          <el-option value="" label="-&#45;&#45;请选择-&#45;&#45;"></el-option>
          <el-option v-for="esl in empStatusList" :key="esl.value" :value="esl.value" :label="esl.name"></el-option>
        </el-select>
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" @click="search" :size='s' icon="el-icon-search">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 查询表单结束 -->
        
    <!-- 按钮块开始 -->
    <div style="padding-left:20px">
      <el-button type="primary" :size='s' icon="el-icon-plus" @click="dialogFormVisible=true;form={};">增加</el-button>
      <el-button type="primary" :size='s' icon="el-icon-edit" @click="edit">编辑</el-button>
      <el-button type="primary" :size='s' icon="el-icon-edit" @click="handleDelete">删除</el-button>
                      
<!--      <el-button type="primary" :size='s' icon="el-icon-edit" @click="openLeaveJob">设置员工离职</el-button>
      <el-button type="primary" :size='s' icon="el-icon-edit" @click="unLeaveJob">撤销员工离职</el-button>-->
    </div>
    <!-- 按钮块结束 -->

    <!--员工数据表格展示开始-->
    <div style="width:100%;height:450px;overflow:auto;position:relative">
      <el-table
          :data="hrEmployeesList"
          stripe
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            type="index"
            width="70"
            :index="indexMethod">
        </el-table-column>
        <el-table-column
            type="selection"
            width="70">
        </el-table-column>
        <el-table-column
            label="姓名"
            width="100"
            prop="empName">
        </el-table-column>
        <el-table-column
            label="电话"
            width="100"
            prop="empPhone">
        </el-table-column>
        <el-table-column
            label="性别"
            width="100"
            prop="sex">
        </el-table-column>
        <el-table-column
            label="员工状态"
            width="100"
            prop="empStatusName">
        </el-table-column>
        <el-table-column
            label="门店名称"
            width="100"
            prop="branchesName">
        </el-table-column>
        <el-table-column
            label="部门名称"
            width="100"
            prop="orgName">
        </el-table-column>
        <el-table-column
            label="员工类别"
            width="100"
            prop="empTypeName">
        </el-table-column>
        <el-table-column
            label="签约时间"
            width="100"
            prop="createdDate">
        </el-table-column>
        <el-table-column
            label="生日"
            width="100"
            prop="empBirthday">
        </el-table-column>
        <el-table-column
            label="email"
            width="100"
            prop="empEmail">
        </el-table-column>
        <el-table-column
            label="员工编号"
            width="100"
            prop="empCode">
        </el-table-column>
        <el-table-column
            label="备注"
            width="100"
            prop="remark">
        </el-table-column>
      </el-table>
    </div>
    <!-- 员工数据表格展示结束 -->

    <!--分页组件开始-->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="current"
        :page-sizes="pageSizes"
        :page-size="size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
    >
    </el-pagination>
    <!-- 分页组件结束 -->

    <!-- 添加/编辑数据模态框开始 -->
    <el-dialog :title="form.empUuid==null?'增加员工管理':'编辑员工管理'" :visible.sync="dialogFormVisible" width="60%">
      <fieldset>
        <legend>基本信息</legend>
        <el-form :model="form" style="padding:0">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="姓名" :size='s'>
                <el-input v-model="form.empName" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="性别">
                <el-select v-model="form.empSex" placeholder="请选择" :size='s'>
                  <el-option value="" label="---请选择---"></el-option>
                  <el-option v-for="s in sexs" :key="s.value" :value="s.value" :label="s.name"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="电话" :size='s'>
                <el-input v-model="form.empPhone" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="员工类别">
                <el-select v-model="form.empType" placeholder="请选择" :size='s'>
                  <el-option value="" label="---请选择---"></el-option>
                  <el-option v-for="etl in empTypeList" :key="etl.value" :value="etl.value"
                             :label="etl.name"></el-option>
                </el-select>
              </el-form-item>
            </el-col>

            <el-col :span="8">
              <el-form-item label="技师等级">
                <el-select v-model="form.workerLevel" placeholder="请选择" :disabled="select.technicianGradeSelect"
                           :size='s'>
                  <el-option value="" label="---请选择---"></el-option>
                  <el-option v-for="tg in technicianGrade" :key="tg.value" :value="tg.value"
                             :label="tg.name"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="证件类型">
                <el-select v-model="form.cardTyep" placeholder="请选择" :size='s'>
                  <el-option value="" label="---请选择---"></el-option>
                  <el-option v-for="ct in certificatesType" :key="ct.value" :value="ct.value"
                             :label="ct.name"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="证件号" :size='s'>
                <el-input v-model="form.cardNo" autocomplete="off"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="学历">
                <el-select v-model="form.eduBackground" placeholder="请选择" :size='s'>
                  <el-option value="" label="---请选择---"></el-option>
                  <el-option v-for="e in education" :key="e.value" :value="e.value" :label="e.name"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">

            <el-col :span="8">
              <el-form-item label="生日" :size='s'>
                <el-date-picker
                    v-model="form.empBirthday"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="8">
              <el-form-item label="email" :size='s'>
                <el-input v-model="form.empEmail"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-form-item label="备注" :size='s'>
              <el-input
                  type="textarea"
                  :rows="2"
                  v-model="form.remark">
              </el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </fieldset>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加/编辑数据模态框结束 -->

    <!-- 设置员工离职模态框开始 -->
    <el-dialog title="设置员工离职" :visible.sync="leaveJobDialogFormVisible" width="60%">
      <fieldset>
        <legend>基本信息</legend>
        <el-form>

          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="离职时间" :size='s'>
                <el-date-picker
                    v-model="form.leaveDate"
                    type="date"
                    placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-form-item label="离职原因" :size='s'>
              <el-input
                  type="textarea"
                  :rows="2"
                  v-model="form.leaveReason">
              </el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </fieldset>

      <div slot="footer" class="dialog-footer">
        <el-button @click="leaveJobDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="leaveJob">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 设置员工离职模态框结束 -->

  </div>
</template>

<script>
// 导入封装 区域表 一系列方法的js
import districtmanagement from '@/api/hrmanagement/districtmanagement'
// 导入封装 部门/大区表 一系列方法的js
import divisionalmanagement from '@/api/hrmanagement/divisionalmanagement'
// 导入封装 门店表 一系列方法的js
import storeinformationmanagement from '@/api/hrmanagement/storeinformationmanagement'
// 导入封装 员工 一系列方法的js
import staffmanagement from '@/api/hrmanagement/staffmanagement'
// 导入vuex
import {mapGetters} from 'vuex'

export default {
  data() {
    return {
      hrEmployeesList: [],//员工表格数据
      // 写死  
      hrEmployessQuery: {},//查询参数
      bigAreaList: [],//大区下拉框数据
      areaList: [],//区域下拉框数据
      hrBranchesList: [],//门店下拉框数据
      hrOrgList: [],//部门下拉框
      empTypeList: [
        {value: "headquarters", name: "总部员工"},
        {value: "sa", name: "SA"},
        {value: "technician", name: "技师"},
        {value: "manager", name: "店长"},
        {value: "owner", name: "业主"},
        {value: "logistics", name: "门店后勤"},
        {value: "cashier", name: "门店出纳"},
        {value: "tallyClerk", name: "理货员"},
      ],//员工类别
      empStatusList: [
        {value: "jobing", name: "在职"},
        {value: "leaveJob", name: "离职"},
      ],//员工状态
      sexs: [
        {value: "M", name: "男"},
        {value: "F", name: "女"},
        {value: "N", name: "未知"},
      ],//性别
      technicianGrade: [
        {value: "bigWorker", name: "大工"},
        {value: "middleWorker", name: "中工"},
        {value: "littleWorker", name: "小工"},
      ],//技师等级
      certificatesType: [
        {value: "cardId", name: "身份证"},
        {value: "driverLicense", name: "驾驶证"},
        {value: "passport", name: "护照"},
        {value: "militaryOfficer", name: "军官证"},
        {value: "cardSoldiers", name: "士兵证"},
        {value: "other", name: "其他"},
      ],//证件类型
      education: [
        {value: "primarySchool", name: "小学"},
        {value: "juniorHighSchool", name: "初中"},
        {value: "middleSchool", name: "中专/高中"},
        {value: "trainingSchool", name: "专科"},
        {value: "university", name: "本科"},
        {value: "postgraduateStudent", name: "硕士研究生"},
        {value: "graduateStudents", name: "博士研究生"},
      ],//学历
      current: 1,//页码
      pageSizes: [5,10, 50, 100],//分页列表
      size: 5,//页大小
      total: 0,//总记录数
      s: 'mini',//查询框大小
      selectData: [],//选择的数据
      selectid: [],//选中的数据id
      dialogFormVisible: false,//是否开启对话框
      leaveJobDialogFormVisible: false,//是否开启设置员工离职的对话框
      form: {},//表单数据
      select: {
        hrBranchesSelect: false,//门店下拉框，默认不禁用
        hrOrgSelect: false,//部门下拉框，默认不禁用
        technicianGradeSelect: true,//技师下拉框，默认禁用
      },//管理所有下拉框的是否禁用
    }
  },
  computed: {
    ...mapGetters([
      'userid',
      'name',
    ]),
    brCode () {
        return this.$store.getters.brCode;
      }

  },
  methods: {
    //  删除员工
    handleDelete() {
      if (this.selectData.length === 0) {
        this.$message({
          type: 'info',
          message: '至少选中一条数据'
        })
        return
      }

      this.$confirm('此操作将永久删除该记录,  是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        for (var i = 0; i < this.selectid.length; i++) {
          staffmanagement.delete(this.selectid[i])
              .then(() => {
                //  提示信息
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                })
                //  刷新表格数据
                this.getList()
              })
        }

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })

    },
    //点击修改按钮判断是否选中数据
    edit() {
      if (this.selectData.length === 0) {
        this.$message({
          type: 'info',
          message: '请选中一条数据'
        })
        return
      }
      if (this.selectData.length > 1) {
        this.$message({
          type: 'info',
          message: '只能选中一条数据'
        })
        return
      }

      staffmanagement.queryById(this.selectData[0].empUuid)
          //  请求成功
          .then(response => {

            this.dialogFormVisible = true

            this.form = response.data

          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
    //添加或者编辑表单
    save() {
      this.dialogFormVisible = false

      //判断一下当前的操作是 添加/修改
      if (this.form.empUuid == null) {
        //添加
        //手动添加 创建人 列的数据
        this.form.createdBy = this.name
        this.form.brUuid=this.brCode
        staffmanagement.isnert(this.form)
            //  请求成功
            .then(response => {
              if (response.code === 20000) {
                this.$message({
                  type: 'success',
                  message: '添加成功!'
                });

                //刷新表格
                this.getList()
              }
            })
            //  请求失败
            .catch(error => {
              console.log(error)
            })
      } else {
        //修改
        //手动添加 修改人 列的数据
        this.form.updatedBy = this.name
        this.form.brUuid=this.brCode
        staffmanagement.update(this.form)
            //  请求成功
            .then(response => {
              if (response.code === 20000) {
                this.$message({
                  type: 'success',
                  message: '修改成功!'
                });

                //刷新表格
                this.getList()
              }
            })
            //  请求失败
            .catch(error => {
              console.log(error)
            })
      }

    },
    //获取选中的数据
    handleSelectionChange(val) {
      this.selectData = val
      this.selectid = []
      for (var i = 0; i < val.length; i++) {
        this.selectid.push(val[i].empUuid);
      }
    },
    //查询按钮
    search() {
      this.getList()
    },
    //  根据条件分页查询员工数据
    getList() {
      this.hrEmployessQuery.branchesId=this.brCode
      staffmanagement.query(this.current, this.size, this.hrEmployessQuery)
          //  请求成功
          .then(response => {
            this.hrEmployeesList = response.data.rows
            this.total = response.data.total
          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
    // 查询全部的大区数据
    getAllBigAreaList() {
      divisionalmanagement.queryAllBigArea()
          //  请求成功
          .then(response => {
            this.bigAreaList = response.data.bigAreaList;
          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
    // 查询出全部的部门数据
    getAllOrg() {
      divisionalmanagement.queryAllOrg()
          //  请求成功
          .then(response => {
            this.hrOrgList = response.data.hrOrgList;
          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
    // 查询全部的区域数据
    getAllAreaList() {
      districtmanagement.queryAll()
          //  请求成功
          .then(response => {
            this.areaList = response.data.hrAreaList;
          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
    // 查询全部的门店数据
    getAllBranches() {
      storeinformationmanagement.queryAll()
          //  请求成功
          .then(response => {
            this.hrBranchesList = response.data.hrBranchesList;
          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
   /* // 打开设置员工离职的模态框
    openLeaveJob() {
      if (this.selectData.length === 0) {
        this.$message({
          type: 'info',
          message: '请选中一条数据'
        })
        return
      }
      if (this.selectData.length > 1) {
        this.$message({
          type: 'info',
          message: '只能选中一条数据'
        })
        return
      }

      // 打开设置员工离职模态框
      this.leaveJobDialogFormVisible = true
      // 设置员工id
      console.log(this.selectid[0])
      this.form.empUuid = this.selectid[0];

    },
    // 设置员工离职
    leaveJob() {
      staffmanagement.leaveJob(this.form)
          //  请求成功
          .then(() => {

            //刷新数据
            this.getList()
            //关闭模态框
            this.leaveJobDialogFormVisible = false

          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })
    },
    // 撤销员工离职
    unLeaveJob() {
      if (this.selectData.length === 0) {
        this.$message({
          type: 'info',
          message: '请选中一条数据'
        })
        return
      }
      if (this.selectData.length > 1) {
        this.$message({
          type: 'info',
          message: '只能选中一条数据'
        })
        return
      }

      staffmanagement.unLeaveJob(this.selectData[0].empUuid)
          //  请求成功
          .then(() => {

            //刷新数据
            this.getList()

          })
          //  请求失败
          .catch(error => {
            console.log(error)
          })

    },*/
    //页码改变事件
    handleSizeChange(val) {
      this.size = val
      this.getList()
    },
    //每页显示条数改变事件
    handleCurrentChange(val) {
      this.current = val
      this.getList()
    },
    //表格自动数字排序
    indexMethod(index) {
      return index + 1;
    }
  },
  created() {
    // 查询出全部员工数据
    this.getList()
    // 查询出全部的大区数据
    this.getAllBigAreaList()
    // 查询出全部的区域数据
    this.getAllAreaList()
    // 查询出全部的门店数据
    this.getAllBranches()
    // 查询出全部的部门数据
    this.getAllOrg()
  },
  //监听器
  watch: {
    // 如果员工类别发生改变，就触发这个侦听器
    'form.empType': function (newEmpType) {
      console.log(newEmpType)
      if (newEmpType == 'headquarters') {
        //如果员工类别为总部员工，则门店下拉框禁用，部门下拉框开启,技师下拉框禁用
        this.select.hrBranchesSelect = true
        this.form.brUuid = ""
        this.select.hrOrgSelect = false
        this.select.technicianGradeSelect = true
        this.form.workerLevel = ""
      } else {
        //如果员工类别不为总部员工，则门店下拉框开启，部门下拉框禁用
        this.select.hrBranchesSelect = false
        this.select.hrOrgSelect = true
        this.form.orgUuid = ""
        if (newEmpType == 'technician') {
          // 如果员工类别为技师，则技师等级下拉框可以使用
          this.select.technicianGradeSelect = false
        } else {
          // 如果员工类别不为技师，则技师等级下拉框禁用
          this.select.technicianGradeSelect = true
          this.form.workerLevel = ""
        }

      }

    }
  }
}
</script>

<style scoped>
.el-table {
  position: absolute;
  width: auto;
  max-width: none;
}

.el-input {
  width: 150px;
}

.el-form-item {
  text-align: right;
}
</style>
