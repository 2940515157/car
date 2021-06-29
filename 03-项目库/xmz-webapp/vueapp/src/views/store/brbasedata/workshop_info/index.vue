<template>
  <div>
    <!-- 车间信息管理 -->

    <el-form ref="form" :model="hrBrancheOrgQuery" label-width="80px" size="medium">
      <el-row>
        <el-col span="4">
          <el-form-item label="车间名称:">
            <el-input
                placeholder="请输入内容"
                v-model="hrBrancheOrgQuery.orgName"
                clearable>
            </el-input>
          </el-form-item>
        </el-col>


        <el-col span="4">
          <el-form-item label="车间类型:">

            <el-select v-model="hrBrancheOrgQuery.orgType" clearable placeholder="">
              <el-option
                  v-for="item in orgTypes"
                  :key="item.dictid"
                  :label="item.dictname"
                  :value="item.dictid">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>


        <el-button @click="clickQuery" style="height:37px">查询</el-button>
      </el-row>

    </el-form>

    <el-row>
      <el-button type="primary" icon="el-icon-plus" @click="dialogFormVisible = true; form = {}">增加</el-button>
      <el-button type="primary" icon="el-icon-edit" @click="clickUpdate">编辑</el-button>
      <el-button type="primary" icon="el-icon-delete" @click="deleteByIds">删除</el-button>

    </el-row>
    <el-table
        :data="hrBrancheOrgs"
        highlight-current-row

        @selection-change="handleSelectionChange"
        border
        style="width: 100%">
      <el-table-column
          type="index"
      >
      </el-table-column>
      <el-table-column
          type="selection"
      >
      </el-table-column>
      <el-table-column
          property="orgName"
          label="车间名称"
      >
      </el-table-column>
      <el-table-column
          property="dictname"
          label="车间类型"
      >
      </el-table-column>

    </el-table>


    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="pageSizes"
        :page-size="rows"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>


    <el-dialog :title="form.brUuid==null?'增加车间信息管理':'编辑车间信息管理'" :visible.sync="dialogFormVisible">
      <el-form :model="form">


        <el-form-item label="车间名称:" :label-width="formLabelWidth">
          <el-input v-model="form.orgName" autocomplete="off" style="width:200px"></el-input>
        </el-form-item>

        <el-form-item label="车间类型:" :label-width="formLabelWidth">

          <el-select v-model="form.orgType" placeholder="请选择活动区域">
            <el-option
                v-for="item in orgTypes"
                :key="item.dictid"
                :label="item.dictname"
                :value="item.dictid">
            </el-option>
          </el-select>

        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import hrOrg from '@/api/hrmanagementservice/hr-branche-org'
import {mapGetters} from 'vuex'

export default {

  data() {
    return {

      // 车间信息
      hrBrancheOrgs: [],
      //页面
      page: 1,
      //显示行数
      rows: 100,
      //总记录数
      total: 0,
      //分页列表
      pageSizes: [50, 100, 150, 200],
      //查询表单
      hrBrancheOrgQuery: {},

      //车间类型
      orgTypes: [],

      //表单数据
      form: {},

      //控制对话框 的 显示
      dialogFormVisible: false,

      //表单的 label 宽度
      formLabelWidth: '100px',
      //选中的数据id
      selectid: [],

      // 统一  门店 id
      brUuid: '40288582725dda1d017272b1a65909c5'

    }
  },
  methods: {
    //获取页面数据
    async getHrBrancheOrgs() {

      //门店的br_uiid             (假数据,写死)
      this.hrBrancheOrgQuery.brUuid = this.brUuid;

      const respons = await hrOrg.getHrBrancheOrgs(this.page, this.rows, this.hrBrancheOrgQuery)

      this.hrBrancheOrgs = respons.data.hrBrancheOrg;
      this.total = respons.data.total;
    },
    //点击查询
    clickQuery() {

      this.page = 1;
      this.getHrBrancheOrgs();
    },
    //  增加 或 修改
    async save() {

      this.dialogFormVisible = false

      this.form.updatedBy = this.name

      if (this.form.bdUuid == null || this.form.bdUuid == '') {

        this.form.brUuid = this.brUuid
        this.form.createdBy = this.name

      }

      await hrOrg.addOrUpdate(this.form)

      this.$message({
        type: 'success',
        message: '操作成功!'
      })

      this.getHrBrancheOrgs();

    },
    //修改点击时
    async clickUpdate() {

      if (this.selectid.length != 1) {

        this.$message({
          type: 'info',
          message: '请选中一条数据!'
        })

        return;
      }
      //打开dialog框
      this.dialogFormVisible = true;

      //根据车间bd_uuid 查询 数据
      const response = await hrOrg.queryById(this.selectid[0])

      this.form = response.data

    }
    ,
    //删除 数据
    async deleteByIds() {

      if (this.selectid.length < 1) {

        this.$message({
          type: 'info',
          message: '请选中一条数据!'
        })
        return

      }

      const response = await hrOrg.deleteById(this.selectid)

      this.getHrBrancheOrgs();
      this.$message({
        type: 'success',
        message: response.message
      })

    }
    ,
    //获取车间类型
    async getOrgTypes() {

      const response = await this.$axios.get(`/brCrm/cust-cust-info/queryDict/DICT_WORKSHOP_TYPE`)

      this.orgTypes = response.data;
    }
    ,
    //获取选中的数据
    handleSelectionChange(val) {
      //console.log(val)

      this.selectid = []

      for (var i = 0; i < val.length; i++) {
        this.selectid.push(val[i].bdUuid);
      }
    },
    //当页面最大行数改变时
    handleSizeChange(val) {
      this.row = val;
      this.getHrBrancheOrgs();
    },
    //页码改变时
    handleCurrentChange(val) {
      this.page = val;
      this.getHrBrancheOrgs();
    }
  },
  computed: {
    ...mapGetters([
      'name',
    ])
  },
  created() {

    //获取车间类型
    this.getOrgTypes();

    //获取车间信息
    this.getHrBrancheOrgs();
  }
}
</script>>
<style scoped>

</style>
