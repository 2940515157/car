<template>
  <div>
    <!-- 班组信息管理 -->

    <el-form ref="form" :model="hrBrancheTeamsQuery" label-width="80px" size="mini">
      <el-row>
        <el-col span="4">
          <el-form-item label="名称:">
            <el-input
                placeholder="请输入内容"
                v-model="hrBrancheTeamsQuery.name"
                clearable>
            </el-input>
          </el-form-item>
        </el-col>


        <el-col span="4">
          <el-form-item label="班组类型:">

            <el-select v-model="hrBrancheTeamsQuery.type" clearable placeholder="请选择">
              <el-option
                  v-for="item in teamTypes"
                  :key="item.dictid"
                  :label="item.dictname"
                  :value="item.dictid">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col span="4">
          <el-form-item label="车间:">

            <el-select v-model="hrBrancheTeamsQuery.bdUuid" clearable placeholder="请选择">
              <el-option
                  v-for="item in hrBrancheOrgs"
                  :key="item.bdUuid"
                  :label="item.orgName"
                  :value="item.bdUuid">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>


        <el-button @click="clickQuery" style="height:37px">查询</el-button>
      </el-row>

    </el-form>

    <el-row :gutter="20">
      <el-col span="12">
        <el-button type="primary" icon="el-icon-plus" @click="dialogFormVisible = true; form = {}" size="mini">增加
        </el-button>
        <el-button type="primary" icon="el-icon-edit" @click="clickUpdate" size="mini">编辑</el-button>
        <el-button type="primary" icon="el-icon-delete" @click="deleteByIds" size="mini">删除</el-button>
      </el-col>

      <el-col span="12">
        <el-button type="primary" icon="el-icon-plus" @click="getHrEmployees" size="mini">添加成员</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="clickUpdate" size="mini">保存数据</el-button>
        <el-button type="primary" icon="el-icon-delete" @click="deleteTeamEmp" size="mini">删除成员</el-button>
      </el-col>

    </el-row>
    <el-row :gutter="20">
      <el-col span="12">
        <el-table
            :data="hrBrancheTeams"
            highlight-current-row
            @row-click="rowClick"

            border
        >

          <el-table-column
              type="index"
          >
          </el-table-column>


          <el-table-column
              property="teamName"
              label="名称"
          >
          </el-table-column>

          <el-table-column
              property="hrBrancheOrg.orgName"
              label="车间"
          >

          </el-table-column>
          <el-table-column
              property="dictname"
              label="班组类型"
          >

          </el-table-column>

        </el-table>

        <!-- 第一套 -->
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="page"
            :page-sizes="pageSizes"
            :page-size="rows"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </el-col>

      <el-col span="12">
        <el-table
            :data="hrTeamsEmps"
            highlight-current-row

            @selection-change="handleSelectionChange2"
            border
        >

          <el-table-column
              type="index"
          >
          </el-table-column>

          <el-table-column
              type="selection"
          >
          </el-table-column>

          <el-table-column
              property="empName"
              label="员工名字"
          >
          </el-table-column>

          <el-table-column
              property="empCode"
              label="员工编号"
          >

          </el-table-column>
          <el-table-column
              property="teamName"
              label="班组信息"
          >
          </el-table-column>
          <el-table-column
              property="rate"
              label="分成比例"
          >


          </el-table-column>

        </el-table>

        <!-- 第二 套 -->
        <el-pagination
            @size-change="handleSizeChange2"
            @current-change="handleCurrentChange2"
            :current-page="this.PageTwo.page"
            :page-sizes="this.PageTwo.pageSizes"
            :page-size="this.PageTwo.rows"
            layout="total, sizes, prev, pager, next, jumper"
            :total="this.PageTwo.total">
        </el-pagination>
      </el-col>
    </el-row>


    <!-- 添加 和 修改 -->
    <el-dialog :title="form.teamUuid==null?'增加班组信息':'编辑班组信息'" :visible.sync="dialogFormVisible">
      <el-form :model="form">

        <el-row>
          <el-col span="10">
            <el-form-item label="名称:" :label-width="formLabelWidth">
              <el-input v-model="form.teamName" autocomplete="off" style="width:200px"></el-input>
            </el-form-item>
          </el-col>

          <el-col span="10">
            <el-form-item label="班组类型:" :label-width="formLabelWidth">

              <el-select v-model="form.teamType" clearable placeholder="请选择" @change="selectOnChang">
                <el-option
                    v-for="item in teamTypes"
                    :key="item.dictid"
                    :label="item.dictname"
                    :value="item.dictid">
                </el-option>
              </el-select>

            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="车间:" :label-width="formLabelWidth" v-show="this.form.teamType!='O2O'">

          <el-select v-model="form.bdUuid" clearable placeholder="请选择">
            <el-option
                v-for="item in hrBrancheOrgs"
                :key="item.bdUuid"
                :label="item.orgName"
                :value="item.bdUuid">
            </el-option>
          </el-select>

        </el-form-item>


      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加 成员 员工 -->
    <el-dialog
        title="增加班组成员"
        :visible.sync="dialogVisibleemp"
        width="30%"
        center

        :before-close="handleClose">

      <!-- 员工 -->
      <el-table
          :data="hrEmployees"
          highlight-current-row
          height="500"
          @selection-change="handleSelectionChange3"
          border
      >

        <el-table-column
            type="index"
        >
        </el-table-column>

        <el-table-column
            type="selection"
        >
        </el-table-column>

        <el-table-column
            property="empName"
            label="员工名字"
        >
        </el-table-column>

        <el-table-column
            property="empCode"
            label="员工编号"
        >

        </el-table-column>
        <el-table-column
            property="empTypeName"
            label="员工类别名字"
        >

        </el-table-column>

      </el-table>

      <!-- 第三 套 -->
      <el-pagination
          @size-change="handleSizeChange3"
          @current-change="handleCurrentChange3"
          :current-page="this.PageThree.page"
          :page-sizes="this.PageThree.pageSizes"
          :page-size="this.PageThree.rows"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.PageThree.total">
      </el-pagination>

      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addOkEmp">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<script>
import hrOrg from '@/api/hrmanagementservice/hr-branche-org'
import hrTeam from '@/api/hrmanagementservice/hr-branche-team'
import hte from '@/api/hrmanagementservice/hr-team-emp'
import {mapGetters} from 'vuex'
import emp from '@/api/hrmanagement/staffmanagement'

export default {

  data() {
    return {

      // 班组信息
      hrBrancheTeams: [],
      //班组成员信息
      hrTeamsEmps: [],
      // 员工信息
      hrEmployees: [],
      //页面
      page: 1,
      //显示行数
      rows: 10,
      //总记录数
      total: 0,
      //分页列表
      pageSizes: [10, 100, 150, 200],
      //查询表单
      hrBrancheTeamsQuery: {},

      //班组类型
      teamTypes: [],

      //车间
      hrBrancheOrgs: [],

      //表单数据
      form: {},

      // 第二  分页
      PageTwo:
          {
            total: 0,
            page: 1,
            rows: 20,
            pageSizes: [10, 20, 50, 100],
            selectid: '',
          },

      //第三 分页
      PageThree:
          {
            total: 0,
            page: 1,
            rows: 20,
            pageSizes: [10, 20, 50, 100],
            selectid: '',
          },


      //控制 班组信息 对话框 的 显示
      dialogFormVisible: false,

      //控制 添加成员的 对话框
      dialogVisibleemp: false,

      //表单的 label 宽度
      formLabelWidth: '100px',
      //选中的数据id
      selectid: '',

      // 统一 门店 id
      brUuid: this.$store.getters.brCode,

      //获取 选中的 员工 的对象
      selectEmpTeam: [],

      //获取 选中的 班组成员信息
      selectTeamEmpIds: []

    }
  },
  methods: {
    //获取班组 数据
    async gethrBrancheTeams() {

      //门店的br_uiid             (假数据,写死)
      this.hrBrancheTeamsQuery.brUuid = this.brUuid;

      const respons = await hrTeam.gethrBrancheTeams(this.page, this.rows, this.hrBrancheTeamsQuery)


      this.hrBrancheTeams = eval('(' + respons.data.hrBrancheTeams + ')')

      this.total = respons.data.total;
    },
    //获取班组成员
    async getHrTeamEmp() {

      const response = await hte.getHrTeamEmp(this.PageTwo.page, this.PageTwo.rows, this.selectid);

      this.hrTeamsEmps = response.data.hrTeamsEmps;

      this.PageTwo.total = response.data.total;
    },
    //获取门店员工
    async getHrEmployees() {

      if (this.selectid == '') {
        this.$message({
          type: 'info',
          message: '请选择一条数据!'
        })
        return
      }

      this.dialogVisibleemp = true

      const response = await emp.queryBrandEmp(this.PageThree.page, this.PageThree.rows, this.selectid, this.brUuid)

      this.hrEmployees = response.data.rows

      this.PageThree.total = response.data.total;
    },
    //点击添加成员
    async addOkEmp() {

      this.dialogVisibleemp = false
       await hte.addEmp(this.selectEmpTeam);

      this.getHrTeamEmp()

    },
    //点击查询
    clickQuery() {

      this.page = 1;
      this.gethrBrancheTeams();
    },
    //  增加 或 修改
    async save() {

      this.dialogFormVisible = false


      this.form.updatedBy = this.name;
      this.form.brUuid = this.brUuid

      if (this.form.teamUuid == undefined || this.form.teamUuid == '') {

        this.form.createdBy = this.name

         await hrTeam.add(this.form)
      } else {

         await hrTeam.update(this.form)
      }


      this.$message({
        type: 'success',
        message: '操作成功!'
      })

      this.gethrBrancheTeams();

    },

    //班组类型 为o2o类型时隐藏 去掉 下拉框的值
    selectOnChang() {

      if (this.form.teamType == "O2O") {

        this.form.bdUuid = ''
      }

    },
    //修改点击时
    async clickUpdate() {

      if (this.selectid == '') {

        this.$message({
          type: 'info',
          message: '请选中一条数据!'
        })

        return;
      }
      //打开dialog框
      this.dialogFormVisible = true;

      const response = await hrTeam.queryById(this.selectid)

      this.form = response.data

    }
    ,
    //删除 班组 数据
    async deleteByIds() {

      if (this.selectid == '') {

        this.$message({
          type: 'info',
          message: '请选中一条数据!'
        })
        return

      }

      const response = await hrTeam.deleteById(this.selectid)

      this.gethrBrancheTeams();
      this.$message({
        type: 'success',
        message: response.message
      })

      this.selectid = ''
    }
    ,
    //删除 班组 成员 信息
    async deleteTeamEmp() {


      if (this.selectTeamEmpIds.length < 1) {

        this.$message({
          type: 'info',
          message: '请选中一条数据!'
        })
        return

      }

      const response = await hte.deleteTeamEmp(this.selectTeamEmpIds)

      this.getHrTeamEmp();
      this.$message({
        type: 'success',
        message: response.message
      })

    },
    //保存 数据  班组成员 信息
    async saveData() {

      var i = 0;
      for ( i ; i < this.hrTeamsEmps.length; i++) {

        //  this.hrBrancheTeams[i].updatedBy=this.name

        i += parseFloat(this.hrTeamsEmps[i].rate)
      }

      if (i != 1) {
        this.$message({
          type: 'info',
          message: '单个分成比例必须为0-1之间,且本组分成比例相加等于1!'
        })
        return
      }


      const response = await hte.updateTeamEmp(this.hrBrancheTeams)

      this.getHrTeamEmp()
      this.$message({
        type: 'success',
        message: response.message
      })

    },
    //获取车间
    async getBrancheOrg() {

      //获取门店br_uuid

      const response = await hrOrg.queryAll(this.brUuid);

      this.hrBrancheOrgs = response.data.hrBrancheOrgs;
    }
    ,
    //获取班组类型
    async getTeamTypes() {

      //
      const response = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_TEAMS_TYPE`)

      this.teamTypes = response.data;
    },
    //当这一行被点击时
    rowClick(row) {

      this.selectid = row.teamUuid;

      this.getHrTeamEmp()
    },
    //当页面最大行数改变时
    handleSizeChange(val) {
      this.rows = val;
      this.gethrBrancheTeams();
    },
    //页码改变时
    handleCurrentChange(val) {
      this.page = val;
      this.gethrBrancheTeams();
    },
    //当页面最大行数改变时  2
    handleSizeChange2(val) {
      this.PageTwo.rows = val;
      this.getHrTeamEmp();
    },
    //页码改变时    2
    handleCurrentChange2(val) {
      this.PageTwo.page = val;
      this.getHrTeamEmp();
    },
    //当页面最大行数改变时  3
    handleSizeChange3(val) {
      this.PageThree.rows = val;
      this.getHrEmployees();
    },
    //页码改变时    3
    handleCurrentChange3(val) {
      this.PageThree.page = val;
      this.getHrEmployees();
    },
    handleSelectionChange2(val) {

      this.selectTeamEmpIds = []

      for (var i = 0; i < val.length; i++) {

        this.selectTeamEmpIds.push(val[i].teamEmpUuid)
      }

    }
    ,
    handleSelectionChange3(val) {

      this.selectEmpTeam = []

      for (var i = 0; i < val.length; i++) {

        var e = {"teamUuid": this.selectid, "empUuid": val[i].empUuid, "createdBy": this.name, "updatedBy": this.name}
        this.selectEmpTeam.push(e);
      }

    }
  },
  computed: {
    ...mapGetters([
      'name',
    ])
  },
  created() {

    //获取班组类型
    this.getTeamTypes();

    //获取车间信息
    this.getBrancheOrg();

    //获取班组信息
    this.gethrBrancheTeams();
  }
}
</script>
<style scoped>

</style>
