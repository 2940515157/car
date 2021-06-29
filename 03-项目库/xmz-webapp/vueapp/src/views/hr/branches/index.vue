<!-- - - - - - - - - - - - - -->
<!--        门店信息管理       -->
<!-- - - - - - - - - - - -  -->

<template>
  <div>
    <el-container>
      <el-header height="100px">
        <b>门店信息管理</b>
        <!--查询表单组件-->
        <el-form :inline="true" :model="Query" class="demo-form-inline" size="mini">
          <el-form-item label="大区：">
            <el-select v-model="Query.orgUuid" clearable @clear="getOps" @change="getOps2" placeholder="请选择">
              <el-option
                  v-for="item in hrOrg"
                  :key="item.orgUuid"
                  :label="item.orgName"
                  :value="item.orgUuid">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="区域：">
            <el-select v-model="Query.areaUuid" clearable placeholder="请选择">
              <el-option
                  v-for="item in hrArea"
                  :key="item.areaUuid"
                  :label="item.areaName"
                  :value="item.areaUuid">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="门店名称：">
            <el-input v-model="Query.brName" placeholder="门店名称"></el-input>
          </el-form-item>
          <el-form-item label="门店编号：">
            <el-input v-model="Query.brCode" placeholder="门店编号"></el-input>
          </el-form-item>
          <el-form-item label="门店类型：">
            <el-select v-model="Query.brType" clearable placeholder="请选择">
              <el-option value="self" label="直营店">
              </el-option>
              <el-option value="eb" label="嘛雀店">
              </el-option>
              <el-option value="affiliate" label="加盟店">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-model="Query.brPhone" label="营业电话：">
            <el-input placeholder="营业电话"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="search">查询</el-button>
          </el-form-item>
        </el-form>
      </el-header>
      <el-main>
        <!--          按钮区域-->
        <el-form size="mini">
          <!--          添加-->
          <el-form-item>
            <el-button type="success" @click="dialogFormVisible = true">添加</el-button>
            <!--          编辑-->
            <el-button type="success" @click="updata">编辑</el-button>
            <!--          删除-->
            <el-button @click="handleDelete">删除</el-button>
<!--            &lt;!&ndash;          账户B币充值&ndash;&gt;-->
<!--            <el-button @click="zhangHuChongZhiChaXun=true">账户B币充值</el-button>-->
<!--            &lt;!&ndash;          设置一日贷信用额度&ndash;&gt;-->
<!--            <el-button @click="piLiangSheZhiYiRiDaiEDu=true">设置一日贷信用额度</el-button>-->
<!--            &lt;!&ndash;          设置门店是否参加游戏化&ndash;&gt;-->
<!--            <el-button @click="sheZhiShiFouYouXiHua=true">设置门店是否参加游戏化</el-button>-->
<!--            &lt;!&ndash;          设置会员卡信用额度&ndash;&gt;-->
<!--            <el-button @click="sheZhiVIPXinYonEDu=true">设置会员卡信用额度</el-button>-->
<!--            &lt;!&ndash;          设置门店是否嘛雀店&ndash;&gt;-->
<!--            <el-button @click="sheZhiShiFouMaQueDian=true">设置门店是否嘛雀店</el-button>-->
<!--            &lt;!&ndash;          设置留底总部金额比例&ndash;&gt;-->
<!--            <el-button @click="sheZhiLiuDiZonBuJinEBiLi=true">设置留底总部金额比例</el-button>-->
<!--            &lt;!&ndash;          设置门店是否资金归结总部&ndash;&gt;-->
<!--            <el-button>设置门店是否资金归结总部</el-button>-->
          </el-form-item>
        </el-form>
        <el-table
            :data="tableData"
            height="280px"
            style="width: 100%"
            @selection-change="handleSelectionChange"
            ref="multipleTable">
          <el-table-column
              type="selection"
              width="55">
          </el-table-column>
          <el-table-column
              prop="brCode"
              label="门店编号">
          </el-table-column>
          <el-table-column
              prop="brName"
              label="门店名称">
          </el-table-column>
          <el-table-column
              label="门店类型">
            <template slot-scope="scope">
              <span v-if="scope.row.brType=='self'">
                  直营店
                </span>
              <span v-else-if="scope.row.brType=='eb'">
                  嘛雀店
                </span>
              <span v-else-if="scope.row.brType=='affiliate'">
                  加盟店
              </span>
            </template>
          </el-table-column>
          <el-table-column
              prop="creditQuota"
              label="一日贷信用额度">
          </el-table-column>
          <el-table-column
              prop="remainCreditQuota"
              label="一日贷剩余额度">
          </el-table-column>
          <el-table-column
              prop="creditLines"
              label="信用额度">
          </el-table-column>
          <el-table-column
              prop="pkgCreditQuota"
              label="会员卡信用额度">
          </el-table-column>
          <el-table-column
              prop="pkgCreditRate"
              label="留底总部金额比例">
          </el-table-column>
          <el-table-column
              prop="lines"
              label="商城预付款余额">
          </el-table-column>
          <el-table-column
              prop="marketCostRatio"
              label="市场费用比率">
          </el-table-column>
          <el-table-column
              prop="brPhone"
              label="营业电话">
          </el-table-column>
          <el-table-column
              prop="daQu"
              label="大区">
          </el-table-column>
          <el-table-column
              prop="quYu"
              label="区域">
          </el-table-column>
          <el-table-column
              prop="address"
              label="地址">
          </el-table-column>
          <el-table-column
              prop="platePrefix"
              label="门店车牌前缀">
          </el-table-column>
          <el-table-column
              prop="brMaturity"
              label="门店成熟系数">
          </el-table-column>
          <el-table-column
              label="是否参加游戏化">
            <template slot-scope="scope">
                <span v-if="scope.row.gameFlag=='Y'">
                  是
                </span>
              <span v-else-if="scope.row.gameFlag=='N'">
                  否
                </span>
            </template>
          </el-table-column>
          <el-table-column
              label="是否嘛雀店">
            <template slot-scope="scope">
                <span v-if="scope.row.sparrowFlag=='Y'">
                  是
                </span>
              <span v-else-if="scope.row.sparrowFlag=='N'">
                  否
                </span>
            </template>
          </el-table-column>
          <el-table-column
              label="是否可发送短信">
            <template slot-scope="scope">
                <span v-if="scope.row.sendMsg=='Y'">
                  是
                </span>
              <span v-else-if="scope.row.sendMsg=='N'">
                  否
                </span>
            </template>
          </el-table-column>
          <el-table-column
              label="资金是否归结总部">
            <template slot-scope="scope">
                <span v-if="scope.row.isFundHeadquarter=='Y'">
                  是
                </span>
              <span v-else-if="scope.row.isFundHeadquarter=='N'">
                  否
                </span>
            </template>
          </el-table-column>
          <el-table-column
              prop="fax"
              label="传真">
          </el-table-column>
          <el-table-column
              prop="tariff"
              label="税号">
          </el-table-column>
          <el-table-column
              label="门店设定保单审核">
            <template slot-scope="scope">
                <span v-if="scope.row.insurCheckFlag=='Y'">
                  是
                </span>
              <span v-else-if="scope.row.insurCheckFlag=='N'">
                  否
                </span>
            </template>
          </el-table-column>
          <el-table-column
              prop="remark"
              label="备注">
          </el-table-column>
        </el-table>

        <!-- 分页-->
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="page"
            :page-sizes="[10, 30, 50,100]"
            :page-size="row"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>

        <!--新增或者修改的对话框-->
        <el-dialog :title="from.brUuid==null?'新增门店信息':'修改门店信息'"
                   :visible.sync="dialogFormVisible" width="800px"
        @close="close">
          <el-form :model="from" :inline="true" size="mini" >
            <fieldset>
              <legend>基本信息</legend>

              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="门店编号：">
                    <el-input v-model="from.brCode" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="	门店名称：" >
                    <el-input v-model="from.brName" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="简称：" >
                    <el-input v-model="from.shortName" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="	营业电话：" >
                    <el-input v-model="from.brPhone" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="省份：">
                    <el-select v-model="from.provUuid" @change="getOpsAdd1" placeholder="请选择">
                      <el-option
                          v-for="item in baseProvinces"
                          :key="item.provUuid"
                          :label="item.provName"
                          :value="item.provUuid">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="城市：">
                    <el-select v-model="from.cityUuid" placeholder="请选择">
                      <el-option
                          v-for="item in baseCitys"
                          :key="item.cityUuid"
                          :label="item.cityName"
                          :value="item.cityUuid">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="门店类型：">
                    <el-select v-model="from.brType" placeholder="请选择">
                      <el-option value="self" label="直营店">
                      </el-option>
                      <el-option value="eb" label="嘛雀店">
                      </el-option>
                      <el-option value="affiliate" label="加盟店">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="大区：">
                    <el-select v-model="from.orgUuid" @change="getOpsAdd2" placeholder="请选择">
                      <el-option
                          v-for="item in hrOrgFrom"
                          :key="item.orgUuid"
                          :label="item.orgName"
                          :value="item.orgUuid">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="区域：" >
                    <el-select v-model="from.areaUuid" placeholder="请选择">
                      <el-option
                          v-for="item in hrAreaFrom"
                          :key="item.areaUuid"
                          :label="item.areaName"
                          :value="item.areaUuid">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="门店车牌前缀：" >
                    <el-input v-model="from.platePrefix" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="是否参与游戏化：" >
                    <el-select v-model="from.gameFlag" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="登录后跳转到页面：" >
                    <el-select v-model="from.afterLoginRedirectType" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-form-item label="地址1：" >
                  <el-input v-model="from.address" autocomplete="off"></el-input>
                </el-form-item>
              </el-row>
              <el-row :gutter="24">
                <el-form-item label="地址2：" >
                  <el-input v-model="from.address2" autocomplete="off"></el-input>
                </el-form-item>
              </el-row>
            </fieldset>

            <fieldset>
              <legend>财务信息</legend>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="市场费用比率：" >
                    <el-input v-model="from.marketCostRatio" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="门店成熟系数：" >
                    <el-input v-model="from.brMaturity" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="门店审核保单：" >
                    <el-select v-model="from.insurCheckFlag" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="会员卡信用额度：" >
                    <el-input v-model="from.pkgCreditQuota" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="留底总部金额比例：" >
                    <el-input v-model="from.pkgCreditRate" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </fieldset>

            <fieldset>
              <legend>门店详情信息</legend>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="店长：" >
                    <el-input v-model="from.brManager" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="店长电话：" >
                    <el-input v-model="from.managerPhone" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="Email：" >
                    <el-input v-model="from.email" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="传真：" >
                    <el-input v-model="from.fax" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="经度：" >
                    <el-input v-model="from.longitude" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="纬度：" >
                    <el-input v-model="from.latitude" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="税号：" >
                    <el-input v-model="from.tariff" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="工位：" >
                    <el-input v-model="from.station" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="法人代表：" >
                    <el-input v-model="from.legalPerson" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="法人电话：" >
                    <el-input v-model="from.legalPhone" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="门店合同号：" >
                    <el-input v-model="from.contractNo" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="统一服务热线：" >
                    <el-input v-model="from.printBaseTel" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  <el-form-item label="广告：" >
                    <el-input v-model="from.printBaseAdv" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="总部标识：" >
                    <el-input v-model="from.printBaseTitle" autocomplete="off"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-form-item label="备注：" >
                  <el-input v-model="from.remark" autocomplete="off"></el-input>
                </el-form-item>
              </el-row>
            </fieldset>

            <fieldset>
              <el-row :gutter="24">
                <el-col :span="12">
                  <el-form-item label="系统是否开通：" >
                    <el-select v-model="from.isOpen" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="开通日期：" >
                    <el-date-picker v-model="from.openDate"
                                    type="datetime"
                                    placeholder="选择开通日期"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    default-time="00:00:00"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="12">
                  <el-form-item label="系统是否上线：" >
                    <el-select v-model="from.isOnline" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="上线日期：" >
                    <el-date-picker
                        v-model="from.onlineDate"
                        type="datetime"
                        placeholder="选择上线日期"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        default-time="00:00:00"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="12">
                  <el-form-item label="总部套餐是否上线：" label-width="120px">
                    <el-select v-model="from.pkgOnline" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="套餐首次成交日期：" label-width="120px">
                    <el-date-picker
                        v-model="from.pkgDealDate"
                        type="datetime"
                        placeholder="选择套餐首次成交日期"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        default-time="00:00:00"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="12">
                  <el-form-item label="配件商城是否成交：" label-width="120px">
                    <el-select v-model="from.ebDeal" placeholder="请选择">
                      <el-option value="Y" label="是">
                      </el-option>
                      <el-option value="N" label="否">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="配件首次成交日期：" label-width="120px">
                    <el-date-picker
                        v-model="from.ebDealDate"
                        type="datetime"
                        placeholder="选择配件首次成交日期"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        default-time="00:00:00"/>
                  </el-form-item>
                </el-col>
              </el-row>
            </fieldset>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>

        <!--           账户充值查询的对话框-->
<!--        <el-dialog title="账户充值查询" :visible.sync="zhangHuChongZhiChaXun">-->
<!--          <el-container>-->
<!--            <el-header height="160px">-->
<!--&lt;!&ndash;              数据展示&ndash;&gt;-->
<!--              <el-row :gutter="24">-->
<!--                <el-col :span="6">-->
<!--                  门店名称：-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  门店编号：-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  门店类型：-->
<!--                </el-col>-->
<!--                <el-col :span="6">-->
<!--                  营业电话：-->
<!--                </el-col>-->
<!--              </el-row>-->
<!--&lt;!&ndash;              查询区域&ndash;&gt;-->
<!--              <el-form :inline="true" class="demo-form-inline" size="mini">-->
<!--                <el-form-item label="车型匹配状态：">-->
<!--                  <el-select placeholder="请选择">-->
<!--                    <el-option value="1">-->
<!--                    </el-option>-->
<!--                    <el-option value="2">-->
<!--                    </el-option>-->
<!--                  </el-select>-->
<!--                </el-form-item>-->
<!--                <el-form-item label="日期：">-->
<!--                  <el-select placeholder="请选择">-->
<!--                    <el-option value="1">-->
<!--                    </el-option>-->
<!--                    <el-option value="2">-->
<!--                    </el-option>-->
<!--                  </el-select>-->
<!--                  <el-date-picker-->
<!--                      type="datetime"-->
<!--                      placeholder="开始时间"-->
<!--                      value-format="yyyy-MM-dd HH:mm:ss"-->
<!--                      default-time="00:00:00"/>-->
<!--                  <el-date-picker-->
<!--                      type="datetime"-->
<!--                      placeholder="结束时间"-->
<!--                      value-format="yyyy-MM-dd HH:mm:ss"-->
<!--                      default-time="00:00:00"/>-->
<!--                </el-form-item>-->
<!--                <el-form-item>-->
<!--                  <el-button type="primary" @click="search">查询</el-button>-->
<!--                </el-form-item>-->
<!--              </el-form>-->
<!--            </el-header>-->
<!--            <el-main>-->
<!--              <el-button @click="zhangHuChongZhi=true">充值</el-button>-->
<!--              <el-button @click="BBiJieDo=true">解冻</el-button>-->
<!--&lt;!&ndash;              充值数据展示&ndash;&gt;-->
<!--              <el-table-->
<!--                  :data="tableData"-->
<!--                  style="width: 100%">-->
<!--                <el-table-column-->
<!--                    prop="date"-->
<!--                    label="日期"-->
<!--                    width="180">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                    prop="name"-->
<!--                    label="姓名"-->
<!--                    width="180">-->
<!--                </el-table-column>-->
<!--                <el-table-column-->
<!--                    prop="address"-->
<!--                    label="地址">-->
<!--                </el-table-column>-->
<!--              </el-table>-->

<!--              &lt;!&ndash; 分页&ndash;&gt;-->
<!--              <el-pagination-->
<!--                  @size-change="handleSizeChange"-->
<!--                  @current-change="handleCurrentChange"-->
<!--                  :current-page="page"-->
<!--                  :page-sizes="[10, 15, 20,30]"-->
<!--                  :page-size="row"-->
<!--                  layout="total, sizes, prev, pager, next, jumper"-->
<!--                  :total="total">-->
<!--              </el-pagination>-->
<!--              <div slot="footer" class="dialog-footer">-->
<!--                <el-button @click="zhangHuChongZhiChaXun = false">关 闭</el-button>-->
<!--              </div>-->
<!--            </el-main>-->
<!--          </el-container>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           账户充值的对话框&ndash;&gt;-->
<!--        <el-dialog title="账户充值" :visible.sync="zhangHuChongZhi">-->
<!--          <fieldset>-->
<!--            <legend>基本信息</legend>-->
<!--            <el-row :gutter="24">-->
<!--              <el-col :span="12">-->
<!--                B币账户冻结金额：-->
<!--              </el-col>-->
<!--              <el-col :span="12">-->
<!--                B币账户可用金额：-->
<!--              </el-col>-->
<!--            </el-row>-->
<!--            金额：<el-input placeholder="请输入数字" size="mini"></el-input>-->
<!--            备注：<el-input ></el-input>-->
<!--          </fieldset>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="zhangHuChongZhi = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           B币金额解冻的对话框&ndash;&gt;-->
<!--        <el-dialog title="B币金额解冻" :visible.sync="BBiJieDo">-->
<!--          <fieldset>-->
<!--            <legend>基本信息</legend>-->
<!--            <el-row :gutter="24">-->
<!--              <el-col :span="12">-->
<!--                B币账户冻结金额：-->
<!--              </el-col>-->
<!--              <el-col :span="12">-->
<!--                B币账户可用金额：-->
<!--              </el-col>-->
<!--            </el-row>-->
<!--            金额：<el-input placeholder="请输入数字" size="mini"></el-input>-->
<!--            备注：<el-input ></el-input>-->
<!--          </fieldset>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="BBiJieDo = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           批量设置一日贷信用额度的对话框&ndash;&gt;-->
<!--        <el-dialog title="批量设置一日贷信用额度" :visible.sync="piLiangSheZhiYiRiDaiEDu">-->
<!--          一日贷信用额度：<el-input placeholder="请输入数字" size="mini"></el-input>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="piLiangSheZhiYiRiDaiEDu = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           批量设置门店是否参加游戏化的对话框&ndash;&gt;-->
<!--        <el-dialog title="批量设置门店是否参加游戏化" :visible.sync="sheZhiShiFouYouXiHua">-->
<!--          是否参加游戏化：-->
<!--          <el-select placeholder="请选择">-->
<!--            <el-option value="1">-->
<!--            </el-option>-->
<!--            <el-option value="2">-->
<!--            </el-option>-->
<!--          </el-select>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="sheZhiShiFouYouXiHua = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           批量设置会员卡信用额度的对话框&ndash;&gt;-->
<!--        <el-dialog title="批量设置会员卡信用额度" :visible.sync="sheZhiVIPXinYonEDu">-->
<!--          设置会员卡信用额度：<el-input placeholder="请输入数字" size="mini"></el-input>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="sheZhiVIPXinYonEDu = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           批量设置门店是否嘛雀店的对话框&ndash;&gt;-->
<!--        <el-dialog title="批量设置门店是否嘛雀店" :visible.sync="sheZhiShiFouMaQueDian">-->
<!--          是否嘛雀店：-->
<!--          <el-select placeholder="请选择">-->
<!--            <el-option value="1">-->
<!--            </el-option>-->
<!--            <el-option value="2">-->
<!--            </el-option>-->
<!--          </el-select>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="sheZhiShiFouMaQueDian = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

<!--        &lt;!&ndash;           批量设置留底总部金额比例的对话框&ndash;&gt;-->
<!--        <el-dialog title="批量设置留底总部金额比例" :visible.sync="sheZhiLiuDiZonBuJinEBiLi">-->
<!--          留底总部金额比例：<el-input placeholder="%" size="mini"></el-input>-->

<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button @click="sheZhiLiuDiZonBuJinEBiLi = false">取 消</el-button>-->
<!--            <el-button type="primary" >确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->
      </el-main>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "index",
      data() {
        return {
          // 是否显示增加修改对话框
          dialogFormVisible: false,
          // 是否显示账户充值查询对话框
          zhangHuChongZhiChaXun:false,
          // 是否显示账户充值对话框
          zhangHuChongZhi:false,
          // 是否显示B币金额解冻对话框
          BBiJieDo:false,
          // 是否显示批量设置一日贷信用额度对话框
          piLiangSheZhiYiRiDaiEDu:false,
          // 是否显示批量设置门店是否参加游戏化对话框
          sheZhiShiFouYouXiHua:false,
          // 是否显示批量设置会员卡信用额度对话框
          sheZhiVIPXinYonEDu:false,
          // 是否显示批量设置门店是否嘛雀店对话框
          sheZhiShiFouMaQueDian:false,
          // 是否显示批量设置留底总部金额比例对话框
          sheZhiLiuDiZonBuJinEBiLi:false,
          //表格选中数据
          multipleSelection:[],
          //选中的id
          selectId:[],
          //表格数据
          tableData: [],
          //查询数据
          Query: {},
          //添加或修改 数据
          from:{},
          //分页数据
          total: 0,
          pages: 0,
          row: 10,
          page: 1,
          //大区下拉框数据
          hrOrg:[],
          //区域下拉框数据
          hrArea:[],
          //添加大区下拉框数据
          hrOrgFrom:[],
          //添加区域下拉框数据
          hrAreaFrom:[],
          //省份下拉框
          baseProvinces:[],
          //城市下拉框
          baseCitys:[],
        };
      },
      created() {
        const _this = this
        _this.getTableData();
        _this.getOps();
        _this.getOpsAdd();
      },
      methods:{
        getTableData(){
          const _this = this
          _this.$axios.post(`/serviceHr/hr-branches/query/${_this.page}/${_this.row}`,_this.Query).then(result=>{
            _this.tableData=result.data.rows;
            this.total = result.data.total
          })
        },

        //获取下拉框
        getOps(){
          const _this = this
          //清空下拉框的数据
          _this.hrOrg=[];
          _this.hrArea=[];
          //获取大区下拉框
          _this.$axios.get(`/serviceHr/HrOrg/queryAll`).then(result=>{
            _this.hrOrg=result.data.hrOrg;
          })
          _this.$axios.get(`/serviceHr/hrArea/queryallQuYu`).then(result=>{
            _this.hrArea=result.data.list;
          })
        },
        //大区下拉框改变就获取区域下拉框
        getOps2(val){
          if(val==''){
            return
          }
          const _this = this
          _this.$axios.post(`/serviceHr/hrArea/queryallQuYuByOrgUuid/${val}`).then(result=>{
            _this.hrArea=result.data.hrArea;
          })
        },

        //获取添加下拉框
        getOpsAdd(){
          const _this = this
          //获取大区下拉框
          _this.$axios.get(`/serviceHr/HrOrg/queryAll`).then(result=>{
            _this.hrOrgFrom=result.data.hrOrg;
          })
          //获取省份下拉框
          _this.$axios.get(`/serviceHr/baseProvinces/queryall`).then(result=>{
            _this.baseProvinces=result.data.baseProvinces;
          })
          if(_this.from.brUuid==null){
            return
          }
          //获取区域下拉框
          _this.$axios.post(`/serviceHr/hrArea/queryallQuYuByOrgUuid/${_this.from.orgUuid}`).then(result=>{
            _this.hrAreaFrom=result.data.hrArea;
          })
          //获取城市下拉框
          _this.$axios.post(`/serviceHr/baseCitys/queryallByProvUuid/${_this.from.provUuid}`).then(result=>{
            _this.baseCitys=result.data.baseCitys;
          })
        },

        //省份下拉框改变就获取城市下拉框
        getOpsAdd1(val){
          if(val==''){
            return
          }
          const _this = this
          _this.$axios.post(`/serviceHr/baseCitys/queryallByProvUuid/${val}`).then(result=>{
            _this.baseCitys=result.data.baseCitys;
          })
        },

        //大区下拉框改变就获取区域下拉框
        getOpsAdd2(val){
          if(val==''){
            return
          }
          const _this = this
          _this.$axios.post(`/serviceHr/hrArea/queryallQuYuByOrgUuid/${val}`).then(result=>{
            _this.hrAreaFrom=result.data.hrArea;
          })
        },


        //选择
        handleSelectionChange(val) {
          this.multipleSelection=val;
          this.selectId=[]
          for(var i=0;i<val.length;i++){
            this.selectId.push(val[i].brUuid);
          }
        },
        //点击编辑按钮
        async updata(){
          if(this.multipleSelection.length==0){
            this.$message({
              type:'info',
              message:'至少选择一条数据'
            })
            return
          }
          console.log(this.selectId)
          // 获取要修改的门店编号
          const response = await this.$axios.get(`/serviceHr/hr-branches/queryById/${this.selectId}`)
          this.from = response.data.hrBranches
          this.getOpsAdd();
          this.dialogFormVisible=true
        },

        // 确认按钮点击事件
        async save(){
          // 添加或者修改操作

          const response = await this.$axios.post(`/serviceHr/hr-branches/${this.from.brUuid == null ? 'add' : 'update'}`, this.from)
          if (response.code == 20000) {
            // 提示消息
            this.$message({
              type: 'success',
              message: '成功!'
            })
            this.dialogFormVisible = false
            // 刷新数据表格
            this.getTableData()
          }
        },

        //关闭添加修改框
        close(){
          this.from=[];
        },

        //删除
        handleDelete(){
          if(this.multipleSelection.length==0){
            this.$message({
              type:'info',
              message:'至少选择一条数据'
            })
            return
          }
          this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            for(var i=0;i<this.selectId.length;i++){
              const _this = this
              _this.$axios.delete(`/serviceHr/hr-branches/delete/${this.selectId[i]}`).then(result=>{
                console.log(result.data);
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                //刷新
                this.getTableData();
              })

            }



          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });

        },

        //查询
        search(){
          this.getTableData();
        },

        //每页条数变化
        handleSizeChange(val) {
          this.row = val;
          this.getTableData();
        },

        //页数变化
        handleCurrentChange(val) {
          this.page = val;
          this.getTableData();
        },
      }
    }
</script>

<style scoped>

</style>
