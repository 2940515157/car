<template>
  <!-- 门店信息  -->
  <div>
    <!-- 表单 -->
    <el-form ref="form" :model="hrBranches" label-width="80px" size="mini ">

      <!-- 基本信息 开始 -->
      <fieldset>
        <legend>基本信息</legend>

        <!--基本信息 第一行  -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="门店编号:">
              <el-input v-model="hrBranches.brCode" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="门店名称:">
              <el-input v-model="hrBranches.brName" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="大区:">
              <el-input v-model="hrBranches.orgName" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="门店类型:" label-width="120px">
              <el-select v-model="brType" disabled placeholder="">
                <el-option
                    v-for="item in brTypes"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>


            </el-form-item>
          </el-col>

        </el-row>

        <!--基本信息 第二行  -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="区域:">
              <el-input v-model="hrBranches.areaName" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="省份:">
              <el-select v-model="prov" disabled placeholder="">
                <el-option
                    v-for="item in provs"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>

              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="城市:">
              <el-select v-model="city" disabled placeholder="">
                <el-option
                    v-for="item in citys"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>

              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="是否参与游戏化:" label-width="120px">
              <el-select v-model="hrBranches.gameFlag" disabled placeholder="">
                <el-option
                    v-for="item in publicOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>

              </el-select>
            </el-form-item>
          </el-col>


        </el-row>


        <!-- 基本信息  第三行  -->
        <el-row :gutter="20">

          <el-col :span="12">
            <el-form-item label="地址1:">
              <el-input disabled v-model="hrBranches.address"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="地址2:">
              <el-input disabled v-model="hrBranches.address2"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <!-- 基本信息 结束... -->
      </fieldset>


      <!-- 财务信息 开始  -->
      <fieldset>
        <legend>财务信息</legend>

        <!-- 财务信息 第一行 -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="市场费用比率:" label-width="120px">
              <el-input v-model="hrBranches.marketCostRatio" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="门店成熟系数:" label-width="120px">
              <el-input v-model="hrBranches.brMaturity" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="B币额度:" label-width="120px">
              <el-input v-model="hrBranches.lines" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="资金是否归结总部:" label-width="150px">
              <el-select v-model="hrBranches.isFundHeadquarter" disabled placeholder="">
                <el-option
                    v-for="item in publicOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>


            </el-form-item>
          </el-col>
        </el-row>

        <!-- 财务信息 第二行  -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="一日贷信用额度:" label-width="120px">
              <el-input  v-model="hrBranches.creditQuota" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="一日贷剩余额度:" label-width="120px">
              <el-input  v-model="hrBranches.remainCreditQuota" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="套餐卡信用额度:" label-width="120px">
              <el-input v-model="hrBranches.pkgCreditQuota" :disabled="true"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="留底总部金额比例:" label-width="150px">
              <el-input v-model="hrBranches.pkgCreditRate" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </fieldset>
      <!-- 财务信息  结束... -->


      <!-- 门店详细信息  开始 -->
      <fieldset>
        <legend>财务信息</legend>

        <!-- 门店详细信息 第一行 -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="营业电话:" label-width="120px">
              <el-input disabled v-model="hrBranches.brPhone"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="店长:" label-width="130px">
              <el-input disabled v-model="hrBranches.brManager"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="店长电话:" label-width="120px">
              <el-input disabled v-model="hrBranches.managerPhone"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="简称:" label-width="150px">
              <el-input disabled v-model="hrBranches.shortName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 门店详细信息 第二行  -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="门店车牌前缀:" label-width="120px">
              <el-input disabled v-model="hrBranches.platePrefix"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="开户银行:" label-width="130px">
              <el-input disabled v-model="hrBranches.bank"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="银行账号:" label-width="120px">
              <el-input disabled v-model="hrBranches.bankAccount"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="户名:" label-width="150px">
              <el-input disabled v-model="hrBranches.accountName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <!-- 门店详细信息 第三行 -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="Email:" label-width="120px">
              <el-input disabled v-model="hrBranches.email"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="传真:" label-width="130px">
              <el-input disabled v-model="hrBranches.fax"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="税号:" label-width="120px">
              <el-input disabled v-model="hrBranches.tariff"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="工位:" label-width="150px">
              <el-input disabled v-model="hrBranches.station"></el-input>
            </el-form-item>
          </el-col>
        </el-row>


        <!-- 门店详细信息 第四行 -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="法人代表:" label-width="120px">
              <el-input disabled v-model="hrBranches.legalPerson"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="法人电话:" label-width="130px">
              <el-input disabled v-model="hrBranches.legalPhone"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="纬度:" label-width="120px">
              <el-input disabled v-model="hrBranches.latitude"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="经度:" label-width="150px">
              <el-input disabled v-model="hrBranches.longitude"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <!-- 门店详细信息 第五行 -->
        <el-row :gutter="20">

          <el-col :span="6">
            <el-form-item label="总部标识:" label-width="120px">
              <el-input disabled v-model="hrBranches.printBaseTitle"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="总部统一服务热线:" label-width="130px">
              <el-input disabled v-model="hrBranches.printBaseTel"></el-input>
            </el-form-item>
          </el-col>


          <el-col :span="6">
            <el-form-item label="门店合同号:" label-width="120px">
              <el-input disabled v-model="hrBranches.contractNo"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

      </fieldset>


    </el-form>


  </div>
</template>

<script>
import hr from '@/api/hrmanagementservice/hr-branches.js'

export default {
  data() {
    return {

      //表单数据
      hrBranches: {},

      //门店类型 集合
      brTypes: [],

      //省份
      provs: [],

      //城市
      citys: [],

      //是否参加游戏化 与  //资金是否归  公用
      publicOptions: [
        {label: '否', value: 'N'},
        {label: '是', value: 'Y'},

      ]

    }

  },

  methods: {
    async getgetMyHrBranches() {
      const response = await hr.getMyHrBranches(this.brCode);

      this.hrBranches = response.data;
      console.log(this.hrBranches)
    },

  },
  computed:{
    brCode () {
      return this.$store.getters.brCode;
    }
  },
  created() {

    this.getgetMyHrBranches();
  }
}
</script>

<style scoped>

</style>

