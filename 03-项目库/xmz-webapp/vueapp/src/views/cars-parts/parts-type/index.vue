<!-- - - - - - - - - - - - -->
<!--        配件目录        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <b>配件目录</b>
        <div style="height: 500px">
          <el-scrollbar style="height: 99%" wrap-style="overflow:hidden scroll;">
            <el-tree
              :data="treeData"
              node-key="partTypeUuid"
              :props="defaultProps"
              ref="tree"
              @node-click="carTypeFuTree">
            </el-tree>
          </el-scrollbar>
        </div>
      </el-aside>
      <el-container>
        <!--        查询区域-->
        <el-header height="100px">
          <b>配件目录</b>
          <!--查询表单组件-->
          <el-form :inline="true" :model="basePartsTypeQuery" class="demo-form-inline" size="mini">
            <el-form-item label="名称/别名：">
              <el-input v-model="basePartsTypeQuery.name" placeholder="名称/别名"></el-input>
            </el-form-item>
            <el-form-item label="数据维护方式：">
              <el-select clearable v-model="basePartsTypeQuery.dataMtType" placeholder="请选择">
                <el-option value="other" label="手动加立洋">

                </el-option>
                <el-option value="manual" label="总部手动">

                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="是否常用：">
              <el-select clearable v-model="basePartsTypeQuery.isCommon" placeholder="请选择">
                <el-option value="Y" label="是">

                </el-option>
                <el-option value="N" label="否">

                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="匹配类型：">
              <el-select clearable v-model="basePartsTypeQuery.matchType" placeholder="请选择">
                <el-option value="common" label="通用件">

                </el-option>
                <el-option value="special" label="匹配件">

                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="配件大类：">
              <el-select clearable v-model="basePartsTypeQuery.servType" placeholder="请选择">
                <el-option value="maintain" label="机修">

                </el-option>
                <el-option value="cosmetology" label="美容">

                </el-option>
                <el-option value="paint" label="油漆">

                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="是否启用：">
              <el-select clearable v-model="basePartsTypeQuery.usableFlag" placeholder="请选择">
                <el-option value="Y" label="是">

                </el-option>
                <el-option value="N" label="否">

                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="search">查询</el-button>
            </el-form-item>
          </el-form>
        </el-header>
<!--        数据展示区域-->
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
              <!--          配件扩展字段定义-->
<!--              <el-button type="success" @click="kuoZhangZiDuan=true">配件扩展字段定义</el-button>-->
              <!--          设置配件品牌-->
              <el-button @click="sheZhiPeiJian">设置配件品牌</el-button>
              <!--          启用-->
<!--              <el-button>启用</el-button>-->
<!--              &lt;!&ndash;          禁用&ndash;&gt;-->
<!--              <el-button>禁用</el-button>-->
            </el-form-item>
          </el-form>
          <el-table
              :data="tableData"
              style="width: 100%"
          height="300px"
              @selection-change="handleSelectionChange"
              ref="multipleTable">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column
                prop="name"
                label="名称">
            </el-table-column>
            <el-table-column
                prop="code"
                label="配件类型编号">
            </el-table-column>
            <el-table-column
                label="匹配类型">
              <template slot-scope="scope">
                <span v-if="scope.row.matchType=='common'">
                  通用件
                </span>
                <span v-else-if="scope.row.matchType=='special'">
                  匹配件
                </span>
              </template>
            </el-table-column>
            <el-table-column
                label="配件类型">
              <template slot-scope="scope">
                <span v-if="scope.row.partMktType=='common'">
                  易损件
                </span>
                <span v-else-if="scope.row.partMktType=='special'">
                  车型件
                </span>
              </template>
            </el-table-column>
            <el-table-column
                label="是否启用">
              <template slot-scope="scope">
                <span v-if="scope.row.usableFlag=='Y'">
                  是
                </span>
                <span v-else-if="scope.row.usableFlag=='N'">
                  否
                </span>
              </template>
            </el-table-column>
            <el-table-column
                label="是否常用">
              <template slot-scope="scope">
                <span v-if="scope.row.isCommon=='Y'">
                  是
                </span>
                <span v-else-if="scope.row.isCommon=='N'">
                  否
                </span>
              </template>
            </el-table-column>
            <el-table-column
                label="配件大类">
              <template slot-scope="scope">
                <span v-if="scope.row.servType=='maintain'">
                  机修
                </span>
                <span v-else-if="scope.row.servType=='cosmetology'">
                  美容
                </span>
                <span v-else-if="scope.row.servType=='paint'">
                  油漆
                </span>
              </template>
            </el-table-column>
            <el-table-column
                label="单位">
              <template slot-scope="scope">
              <span v-if="scope.row.unit=='bottle'">
                瓶
              </span>
                <span v-else-if="scope.row.unit=='oneOfPair'">
                对
              </span>
                <span v-else-if="scope.row.unit=='piece'">
                片
              </span>
                <span v-else-if="scope.row.unit=='pair'">
                副
              </span>
                <span v-else-if="scope.row.unit=='branch'">
                只
              </span>
                <span v-else-if="scope.row.unit=='set'">
                组
              </span>
                <span v-else-if="scope.row.unit=='parts'">
                部
              </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="remark"
                label="备注">
            </el-table-column>
            <el-table-column
                prop="seq"
                label="序号">
            </el-table-column>
            <el-table-column
                prop="alias1"
                label="别名1">
            </el-table-column>
            <el-table-column
                prop="alias2"
                label="别名2">
            </el-table-column>
            <el-table-column
                prop="alias3"
                label="别名3">
            </el-table-column>
            <el-table-column
                prop="alias4"
                label="别名4">
            </el-table-column>
            <el-table-column
                prop="alias5"
                label="别名5">
            </el-table-column>
            <el-table-column
                label="数据维护方式">
              <template slot-scope="scope">
                <span v-if="scope.row.dataMtType=='other'">
                  手动加立洋
                </span>
                <span v-else-if="scope.row.dataMtType=='manual'">
                  总部手动
                </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="jyTypeCode"
                label="精友目录编号">
            </el-table-column>
            <el-table-column
                label="配件类型">
              <template slot-scope="scope">
                <span v-if="scope.row.partMktType=='common'">
                  易损件
                </span>
                <span v-else-if="scope.row.partMktType=='special'">
                  车型件
                </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="priceAddRate"
                label="加价率">
            </el-table-column>
            <el-table-column
                label="统计方法">
              <template slot-scope="scope">
                <span v-if="scope.row.statMethod=='workcard'">
                  按派工单
                </span>
                <span v-else-if="scope.row.statMethod=='fix'">
                  按固定分类
                </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="statType"
                label="统计分类">
              <template slot-scope="scope">
                <span v-if="scope.row.statType=='maintain'">
                  机修
                </span>
                <span v-else-if="scope.row.statType=='cosmetology'">
                  美容
                </span>
                <span v-else-if="scope.row.statType=='paint'">
                  油漆
                </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="rptStatType"
                label="报表统计分类">
              <template slot-scope="scope">
                <span v-if="scope.row.rptStatType=='unComEpd'">
                  精品
                </span>
                <span v-else-if="scope.row.rptStatType=='oil'">
                  油品
                </span>
                <span v-else-if="scope.row.rptStatType=='comEpd'">
                  常用易损件
                </span>
                <span v-else-if="scope.row.rptStatType=='special'">
                  车型件
                </span>
            </template>

            </el-table-column>
          </el-table>

          <!-- 分页-->
          <el-pagination
              @size-change="handleSizeChange1"
              @current-change="handleCurrentChange1"
              :current-page="page1"
              :page-sizes="[10, 20, 50,100]"
              :page-size="row1"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total1">
          </el-pagination>


          <!--新增或者修改的对话框-->
          <el-dialog :title="basePartsTypeAdd.partTypeUuid==null?'新增配件目录':'修改配件目录'"
                     :visible.sync="dialogFormVisible"
          @close="close">
            <fieldset>
              <legend>基本信息</legend>
              <el-form v-model="basePartsTypeAdd" :inline="true" size="mini" >

                <el-form-item label="上级配件目录：" >
                  <el-cascader
                      :props="props"
                      :show-all-levels="false"
                      v-model="basePartsTypeAdd.parentTypeUuid"
                      :options="treeData" autocomplete="off"></el-cascader>
                </el-form-item>
                <el-row ::gutter="24">
                  <el-col ::span="8">
                    <el-form-item label="配件类型编号：" >
                      <el-input v-model="basePartsTypeAdd.code" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col ::span="8">
                    <el-form-item label="名称：" >
                      <el-input v-model="basePartsTypeAdd.name" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col ::span="8">
                    <el-form-item label="别名1：" >


                      <el-input v-model="basePartsTypeAdd.alias1" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span="8">
                    <el-form-item label="别名2：" >
                      <el-input v-model="basePartsTypeAdd.alias2" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="别名3：" >
                      <el-input v-model="basePartsTypeAdd.alias3" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="别名4：" >
                      <el-input v-model="basePartsTypeAdd.alias4" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span="8">
                    <el-form-item label="别名5：" >
                      <el-input v-model="basePartsTypeAdd.alias5" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="配件大类：">
                      <el-select v-model="basePartsTypeAdd.servType" placeholder="请选择">
                        <el-option value="maintain" label="机修">

                        </el-option>
                        <el-option value="cosmetology" label="美容">

                        </el-option>
                        <el-option value="paint" label="油漆">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="数据维护方式：" >
                      <el-select v-model="basePartsTypeAdd.dataMtType" placeholder="请选择">
                        <el-option value="other" label="手动加立洋">

                        </el-option>
                        <el-option value="manual" label="总部手动">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span="8">
                    <el-form-item label="是否常用：" >
                      <el-select v-model="basePartsTypeAdd.isCommon" placeholder="请选择">
                        <el-option value="Y" label="是">

                        </el-option>
                        <el-option value="N" label="否">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="允许门店新增SKU：" >
                      <el-select v-model="basePartsTypeAdd.brCanAdd" placeholder="请选择">
                        <el-option value="Y" label="是">

                        </el-option>
                        <el-option value="N" label="否">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="匹配类型：" >
                      <el-select v-model="basePartsTypeAdd.matchType" placeholder="请选择">
                        <el-option value="common" label="通用件">

                        </el-option>
                        <el-option value="special" label="匹配件">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span="8">
                    <el-form-item label="序号：" >
                      <el-input v-model="basePartsTypeAdd.seq" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="最小购买单位：" >
                      <el-select v-model="basePartsTypeAdd.unit" placeholder="请选择">
                        <el-option value="bottle" label="瓶">

                        </el-option>
                        <el-option value="oneOfPair" label="对">

                        </el-option>
                        <el-option value="piece" label="片">

                        </el-option>
                        <el-option value="pair" label="副">

                        </el-option>
                        <el-option value="branch" label="只">

                        </el-option>
                        <el-option value="set" label="组">

                        </el-option>
                        <el-option value="parts" label="部">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="精友目录编号：" >
                      <el-input v-model="basePartsTypeAdd.jyTypeCode" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row ::gutter="24">
                  <el-col :span="8">
                    <el-form-item label="配件类型：" >
                      <el-select v-model="basePartsTypeAdd.partMktType" placeholder="请选择">
                        <el-option value="common" label="易损件">

                        </el-option>
                        <el-option value="special" label="车型件">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="统计方法：" >
                      <el-select v-model="basePartsTypeAdd.statMethod" placeholder="请选择">
                        <el-option value="workcard" label="按派工单">

                        </el-option>
                        <el-option value="fix" label="按固定分类">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="统计分类：" >
                      <el-select v-model="basePartsTypeAdd.statType" placeholder="请选择">
                        <el-option value="maintain" label="机修">

                        </el-option>
                        <el-option value="cosmetology" label="美容">

                        </el-option>
                        <el-option value="paint" label="油漆">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-row :gutter="24">
                  <el-col :span="8">
                    <el-form-item label="加价率：" >
                      <el-input v-model="basePartsTypeAdd.priceAddRate" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="报表统计分类：" >
                      <el-select v-model="basePartsTypeAdd.rptStatType" placeholder="请选择">
                        <el-option value="unComEpd" label="精品">

                        </el-option>
                        <el-option value="oil" label="油品">

                        </el-option>
                        <el-option value="comEpd" label="常用易损件">

                        </el-option>
                        <el-option value="special" label="车型件">

                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>
                  <el-col :span="8">
                    <el-form-item label="备注：" >
                      <el-input v-model="basePartsTypeAdd.remark" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </fieldset>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>

          </el-dialog>

          <!--配件扩展字段定义的对话框-->
          <el-dialog title="配件扩展字段定义" :visible.sync="kuoZhangZiDuan">
            <fieldset>
              <legend>配件信息</legend>
              <el-row :gutter="24">
                <el-col :span="8">
                  名称：
                </el-col>
                <el-col :span="8">
                  数据维护方式：
                </el-col>
                <el-col :span="8">
                  配件大类：
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  是否常用：
                </el-col>
                <el-col :span="8">
                  匹配类型：
                </el-col>
              </el-row>
            </fieldset>
<!--            弹框按钮区域-->
            <!--          增加-->
            <el-button>增加</el-button>
            <!--          删除-->
            <el-button>删除</el-button>
<!--            <el-table-->
<!--                :data="tableData"-->
<!--                style="width: 100%">-->
<!--              <el-table-column-->
<!--                  prop="date"-->
<!--                  label="日期"-->
<!--                  width="180">-->
<!--              </el-table-column>-->
<!--              <el-table-column-->
<!--                  prop="name"-->
<!--                  label="姓名"-->
<!--                  width="180">-->
<!--              </el-table-column>-->
<!--              <el-table-column-->
<!--                  prop="address"-->
<!--                  label="地址">-->
<!--              </el-table-column>-->
<!--            </el-table>-->
            <div slot="footer" class="dialog-footer">
              <el-button @click="kuoZhangZiDuan = false">取 消</el-button>
              <el-button type="primary" >确 定</el-button>
            </div>

          </el-dialog>

          <!--设置配件品牌的对话框-->
          <el-dialog @close="close" width="820px" title="设置配件品牌" :visible.sync="sheZhiPeiJianPinPai">
            <fieldset>
              <legend>配件信息</legend>
              <el-row :gutter="24">
                <el-col :span="8">
                  名称：{{this.basePartsTypeAdd.name}}
                </el-col>
                <el-col :span="8">
                  数据维护方式：<span v-if="this.basePartsTypeAdd.dataMtType=='other'">
                  手动加立洋
                </span>
                  <span v-else-if="this.basePartsTypeAdd.dataMtType=='manual'">
                  总部手动
                </span>
                </el-col>
                <el-col :span="8">
                  配件大类：<span v-if="this.basePartsTypeAdd.servType=='maintain'">
                  机修
                </span>
                  <span v-else-if="this.basePartsTypeAdd.servType=='cosmetology'">
                  美容
                </span>
                  <span v-else-if="this.basePartsTypeAdd.servType=='paint'">
                  油漆
                </span>
                </el-col>
              </el-row>
              <el-row :gutter="24">
                <el-col :span="8">
                  是否常用：<span v-if="this.basePartsTypeAdd.isCommon=='Y'">
                  是
                </span>
                  <span v-else-if="this.basePartsTypeAdd.isCommon=='N'">
                  否
                </span>
                </el-col>
                <el-col :span="8">
                  匹配类型：<span v-if="this.basePartsTypeAdd.matchType=='common'">
                  通用件
                </span>
                  <span v-else-if="this.basePartsTypeAdd.matchType=='special'">
                  匹配件
                </span>
                </el-col>
                <el-col :span="8">
                  单位：<span v-if="this.basePartsTypeAdd.unit=='bottle'">
                瓶
              </span>
                  <span v-else-if="this.basePartsTypeAdd.unit=='oneOfPair'">
                对
              </span>
                  <span v-else-if="this.basePartsTypeAdd.unit=='piece'">
                片
              </span>
                  <span v-else-if="this.basePartsTypeAdd.unit=='pair'">
                副
              </span>
                  <span v-else-if="this.basePartsTypeAdd.unit=='branch'">
                只
              </span>
                  <span v-else-if="this.basePartsTypeAdd.unit=='set'">
                组
              </span>
                  <span v-else-if="this.basePartsTypeAdd.unit=='parts'">
                部
              </span>
                </el-col>
              </el-row>
            </fieldset>
<!--     设置匹配品牌穿梭框       -->
            <el-transfer
                filterable
                v-model="yiXuanPinPai"
                :data="weiXuanPinPai"
                style="width: 800px"
                :titles="['未选品牌', '已选品牌']"
                :props="{
                    key: 'brandUuid',
                    label: 'name'
                   }"
                @change="gaiBian"
                >
              <!-- 分页-->
<!--              <el-pagination-->
<!--                  class="transfer-footer"-->
<!--                  slot="left-footer"-->
<!--                  @size-change="handleSizeChange2"-->
<!--                  @current-change="handleCurrentChange2"-->
<!--                  :current-page="page2"-->
<!--                  :page-size="row2"-->
<!--                  :total="total2">-->
<!--              </el-pagination>-->
              <!-- 分页2-->
<!--              <el-pagination-->
<!--                  class="transfer-footer"-->
<!--                  slot="right-footer"-->
<!--                  @size-change="handleSizeChange3"-->
<!--                  @current-change="handleCurrentChange3"-->
<!--                  :current-page="page3"-->
<!--                  :page-size="row3"-->
<!--                  :total="total3">-->
<!--              </el-pagination>-->
            </el-transfer>

            <div slot="footer" class="dialog-footer">
              <el-button @click="sheZhiPeiJianPinPai = false">取 消</el-button>
              <el-button @click="sheZhiPeiJianPinPai = false" type="primary" >确 定</el-button>
            </div>

          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "index",
      data() {
        return {
          // 是否显示添加编辑对话框
          dialogFormVisible: false,
          // 是否显示配件扩展字段定义对话框
          kuoZhangZiDuan:false,
          // 是否显示设置配件品牌对话框
          sheZhiPeiJianPinPai:false,
          treeData: [],
          //表格选中数据
          multipleSelection:[],
          //选中的id
          selectId:[],
          tableData: [],
          basePartsTypeQuery: {},
          basePartsTypeAdd:{
            version:0
          },
          //选中树菜单的子节点
          childrens:[1,1],
          //已选品牌
          yiXuanPinPai:[],
          //未选品牌
          weiXuanPinPai:[],
          defaultProps: {
            children: 'children',
            label: 'name'
          },
          props:{
            children: 'children',
            label: 'name',
            value:'partTypeUuid',
            checkStrictly: true
          },
          total1: 0,
          pages1: 0,
          row1: 10,
          page1: 1,
          total2: 0,
          pages2: 0,
          row2: 30,
          page2: 1,
          total3: 0,
          pages3: 0,
          row3: 30,
          page3: 1,
        };
      },
      created() {
        const _this = this
        _this.getTreeData();
        _this.getTableData();
      },
      methods:{
          //加载tree菜单
          getTreeData(){
            const _this = this
            _this.$axios.post(`/serviceCarsparts/cartype/carTypeZiTree`).then(result=>{
              _this.treeData=result.data.rows;
            })
          },
        //加载表格
        getTableData(){
          const _this = this
          _this.$axios.post(`/serviceCarsparts/cartype/query/${_this.page1}/${_this.row1}`,_this.basePartsTypeQuery).then(result=>{
            _this.tableData=result.data.rows;
            this.total1 = result.data.total
          })
        },
        //加载穿梭框
        getTransferData1(){
          const _this = this
          _this.$axios.get(`/serviceCarsparts/base-car-brand/queryCarBrandAll`).then(result=>{
            _this.weiXuanPinPai=result.data.carBrandList;
          })

        },
        //加载穿梭框
        getTransferData2(){
          const _this = this
            _this.$axios.post(`/serviceCarsparts/base-car-brand/queryCarBrandYi/${_this.selectId}`).then(result=>{
              if(result.data.carBrandList.length==0){
                return
              }
              _this.yiXuanPinPai=[]
              for(var i=0;i<result.data.carBrandList.length;i++){
               _this.yiXuanPinPai.push(result.data.carBrandList[i].brandUuid);
               }
            })
        },
        //选择
        handleSelectionChange(val) {
          this.multipleSelection=val;
          this.selectId=[]
          for(var i=0;i<val.length;i++){
            this.selectId.push(val[i].partTypeUuid);
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
          // 获取要修改的配件信息编号
          const response = await this.$axios.get(`/serviceCarsparts/cartype/queryById/${this.selectId}`)
          this.basePartsTypeAdd = response.data.basePartsType
          this.dialogFormVisible=true
        },

        // 确认按钮点击事件
        async save(){
          // 添加或者修改操作
          const fu=this.basePartsTypeAdd.parentTypeUuid;
          if(typeof(fu)==String){
            this.basePartsTypeAdd.parentTypeUuid=fu;
          }else {
            this.basePartsTypeAdd.parentTypeUuid=fu[fu.length-1];
          }

          const response = await this.$axios.post(`/serviceCarsparts/cartype/${this.basePartsTypeAdd.partTypeUuid == null ? 'add' : 'update'}`, this.basePartsTypeAdd)
          if (response.code == 20000) {
            // 提示消息
            this.$message({
              type: 'success',
              message: '成功!'
            })
            this.dialogFormVisible = false
            // 刷新数据表格
            this.getTableData()
            //刷新tree，
            this.getTreeData()
          }
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
              _this.$axios.delete(`/serviceCarsparts/cartype/delete/${this.selectId[i]}`).then(result=>{
                console.log(result.data);
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                //刷新
                this.getTableData();
                this.getTreeData();
              })

            }



          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });

        },

        //设置配件品牌
        async sheZhiPeiJian(){
          if(this.childrens.length>0){
              alert("非3级目录不能设置品牌信息")
            return
          }
          if(this.multipleSelection.length==0){
            this.$message({
              type:'info',
              message:'至少选择一条数据'
            })
            return
          }
          // 获取要修改的配件信息编号
          const response = await this.$axios.get(`/serviceCarsparts/cartype/queryById/${this.selectId}`)
          this.basePartsTypeAdd = response.data.basePartsType
          this.getTransferData1();
          this.getTransferData2();
          this.sheZhiPeiJianPinPai=true;
        },


        //穿梭框移动
        gaiBian(keys,fx,key){

          console.log(keys)
          console.log(fx)
          console.log(key)
        },

        //tree点击事件
        carTypeFuTree(data,node){
            console.log(data)
            this.childrens=node.childNodes;
          const _this = this
          _this.basePartsTypeQuery.parentTypeUuid=data.partTypeUuid;
          _this.getTableData();
        },

        //关闭添加修改框
        close(){
          this.basePartsTypeAdd=[];
          this.basePartsTypeAdd.version=0
        },

        //查询
        search(){
          this.getTableData();
        },

        //每页条数变化
        handleSizeChange1(val) {
          this.row1 = val;
          this.getTableData();
        },

        //页数变化
        handleCurrentChange1(val) {
          this.page1 = val;
          this.getTableData();
        },

        //每页条数变化
        handleSizeChange2(val) {
          this.row2 = val;
          this.getTransferData1();
        },

        //页数变化
        handleCurrentChange2(val) {
          this.page2 = val;
          this.getTransferData1();
        },

        //每页条数变化
        handleSizeChange3(val) {
          this.row3 = val;
          this.getTransferData2();
        },

        //页数变化
        handleCurrentChange3(val) {
          this.page3 = val;
          this.getTransferData2();
        }
      }
    }
</script>

<style scoped>


</style>
<style>
.el-transfer-panel{
  width: 300px;
  height: 500px;
}
.el-transfer-panel__list.is-filterable{
  height: 400px;
}

</style>
