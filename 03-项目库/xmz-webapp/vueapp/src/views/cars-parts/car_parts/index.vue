<!-- - - - - - - - - - - - -->
<!--        配件信息        -->
<!-- - - - - - - - - - - - -->

<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <b>配件信息</b>
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
        <el-header height="140px">
          <b>配件产品库</b>
          <!--查询表单组件-->
          <el-form :model="baseCarPartsSkuQuery" :inline="true" class="demo-form-inline" size="mini">
            <el-form-item label="展示名称：">
              <el-input v-model="baseCarPartsSkuQuery.showName" placeholder="展示名称"></el-input>
            </el-form-item>
            <el-form-item label="小拇指编码：">
              <el-input v-model="baseCarPartsSkuQuery.partNo" placeholder="小拇指编码"></el-input>
            </el-form-item>
            <el-form-item label="车型匹配状态：">
              <el-select clearable v-model="baseCarPartsSkuQuery.matchType" placeholder="请选择">
                <el-option value="common" label="未匹配">
                </el-option>
                <el-option value="special" label="以匹配">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="是否启用：">
              <el-select clearable v-model="baseCarPartsSkuQuery.usableFlag" placeholder="请选择">
                <el-option value="Y" label="是">
                </el-option>
                <el-option value="N" label="否">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="品牌：">
              <el-select clearable v-model="baseCarPartsSkuQuery.brand" placeholder="请选择">
                <el-option
                    v-for="item in carBrandList"
                    :key="item.brandUuid"
                    :label="item.name"
                    :value="item.brandUuid">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="型号：">
              <el-input v-model="baseCarPartsSkuQuery.model" placeholder="型号"></el-input>
            </el-form-item>
            <el-form-item label="规格：">
              <el-input v-model="baseCarPartsSkuQuery.spec" placeholder="规格"></el-input>
            </el-form-item>
            <el-form-item label="归属：">
              <el-select clearable v-model="baseCarPartsSkuQuery.brUuid" placeholder="请选择">
                <el-option value="1" label="门店">
                </el-option>
                <el-option value="" label="总店">
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
              <el-button type="success" @click="add">添加</el-button>
              <!--          编辑-->
              <el-button type="success" @click="updata">编辑</el-button>
              <!--          删除-->
              <el-button @click="handleDelete">删除</el-button>
<!--              &lt;!&ndash;          匹配车型&ndash;&gt;-->
<!--              <el-button @click="piPeiCheXing=true">匹配车型</el-button>-->
<!--              &lt;!&ndash;          批量标准价&ndash;&gt;-->
<!--              <el-button @click="piLiangBiaoZhunJia=true">批量标准价</el-button>-->
<!--              &lt;!&ndash;          批量加价率&ndash;&gt;-->
<!--              <el-button @click="piLiangJiaJiaLv=true">批量加价率</el-button>-->
<!--              &lt;!&ndash;          启用&ndash;&gt;-->
<!--              <el-button>启用</el-button>-->
<!--              &lt;!&ndash;          禁用&ndash;&gt;-->
<!--              <el-button>禁用</el-button>-->
            </el-form-item>
          </el-form>
          <el-table
              :data="tableData"
              style="width: 100%"
              @selection-change="handleSelectionChange"
              ref="multipleTable"
          height="270px">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
            <el-table-column
                prop="bptNAME"
                label="配件目录"
                width="180">
            </el-table-column>
            <el-table-column
                prop="showName"
                label="展示名称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="partNo"
                label="小拇指编码">
            </el-table-column>
            <el-table-column
                prop="partName"
                label="商品名称"
                width="180">
            </el-table-column>
            <el-table-column
                label="是否启用"
                width="180">
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
                prop="brand"
                label="品牌">
            </el-table-column>
            <el-table-column
                prop="model"
                label="厂商型号"
                width="180">
            </el-table-column>
            <el-table-column
                prop="lyCode"
                label="厂商编号"
                width="180">
            </el-table-column>
            <el-table-column
                label="最小购买单位">
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
                prop="spec"
                label="规格"
                width="180">
            </el-table-column>
            <el-table-column
                prop="stdPrice"
                label="总部标准价"
                width="180">
            </el-table-column>
            <el-table-column
                prop="stdPriceAddRate"
                label="总部批量加价率">
            </el-table-column>
            <el-table-column
                label="通用型号sku"
                width="180">
              <template slot-scope="scope">
                <span v-if="scope.row.matchType=='special'">
                  是
                </span>
                <span v-else-if="scope.row.matchType=='common'">
                  否
                </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="hbBRCODE"
                label="门店编号"
                width="180">
            </el-table-column>
            <el-table-column
                prop="hbBRNAME"
                label="门店名称">
            </el-table-column>
            <el-table-column
                prop="remark"
                label="备注"
                width="180">
            </el-table-column>
            <el-table-column
                label="车型匹配状态"
                width="180">
              <template slot-scope="scope">
                <span v-if="scope.row.matchType=='special'">
                  是
                </span>
                <span v-else-if="scope.row.matchType=='common'">
                  否
                </span>
              </template>
            </el-table-column>
            <el-table-column
                prop="skuAlias1"
                label="别名1">
            </el-table-column>
            <el-table-column
                prop="skuAlias2"
                label="别名2">
            </el-table-column>
            <el-table-column
                prop="skuAlias3"
                label="别名3">
            </el-table-column>
            <el-table-column
                prop="skuAlias4"
                label="别名4">
            </el-table-column>
            <el-table-column
                prop="skuAlias5"
                label="别名5">
            </el-table-column>
          </el-table>

          <!-- 分页-->
          <el-pagination
              @size-change="handleSizeChange1"
              @current-change="handleCurrentChange1"
              :current-page="page1"
              :page-sizes="[10,20,30,100]"
              :page-size="row1"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total1">
          </el-pagination>


          <!--新增或者修改的对话框-->
          <el-dialog @close="close" :title="baseCarPartsSkuAdd.partUuid==null?'新增配件产品库':'修改配件产品库'" :visible.sync="dialogFormVisible">
            <el-form :inline="true" :model="baseCarPartsSkuAdd" size="mini" >
              <fieldset>
                <legend>基本信息</legend>

                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="配件目录：" >
                        <el-input v-model="baseCarPartsSkuAdd.partType" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="小拇指编码：" >
                        <el-input v-model="baseCarPartsSkuAdd.partNo" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="商品名称：" >
                        <el-input v-model="baseCarPartsSkuAdd.partName" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="品牌：">
                        <el-select v-model="baseCarPartsSkuAdd.brand" placeholder="请选择">
                          <el-option v-for="item in carBrandListAdd"
                                  :key="item.name"
                                  :label="item.name"
                                  :value="item.name">
                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="	厂商型号：" >
                        <el-input v-model="baseCarPartsSkuAdd.model" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="规格：" >
                        <el-input v-model="baseCarPartsSkuAdd.spec" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="总部标准价：" >
                        <el-input v-model="baseCarPartsSkuAdd.stdPrice" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="总部标准加价率：" >
                        <el-input v-model="baseCarPartsSkuAdd.stdPriceAddRate" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="最小购买单位：" >
                        <el-select v-model="baseCarPartsSkuAdd.unit" placeholder="请选择">
                          <el-option label="瓶" value="bottle">
                            
                          </el-option>
                          <el-option label="对" value="oneOfPair">
                            
                          </el-option>
                          <el-option label="片"  value="piece">

                          </el-option>
                          <el-option label="副" value="pair">

                          </el-option>
                          <el-option label="只" value="branch">

                          </el-option>
                          <el-option label="组" value="set">

                          </el-option>
                          <el-option label="部" value="parts">

                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="厂商编号：" >
                        <el-input v-model="baseCarPartsSkuAdd.lyCode" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="展示名称：" >
                        <el-input v-model="baseCarPartsSkuAdd.showName" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="精品类型：" >
                        <el-select v-model="baseCarPartsSkuAdd.patUuid" placeholder="请选择">
                        </el-select>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="通用型号SKU：" >
                        <el-select v-model="baseCarPartsSkuAdd.matchType" placeholder="请选择">
                          <el-option label="是" value="special">

                          </el-option>
                          <el-option label="否" value="common">

                          </el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="包装规格：" >
                        <el-input v-model="baseCarPartsSkuAdd.pkgSpec" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="OE号：" >
                        <el-input v-model="baseCarPartsSkuAdd.oeNo" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="别名1：" >
                        <el-input v-model="baseCarPartsSkuAdd.skuAlias1" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="别名2：" >
                        <el-input v-model="baseCarPartsSkuAdd.skuAlias2" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="别名3：" >
                        <el-input v-model="baseCarPartsSkuAdd.skuAlias3" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-col :span="8">
                      <el-form-item label="别名4：" >
                        <el-input v-model="baseCarPartsSkuAdd.skuAlias4" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="8">
                      <el-form-item label="别名5：" >
                        <el-input v-model="baseCarPartsSkuAdd.skuAlias5" autocomplete="off"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-row :gutter="24">
                    <el-form-item label="生产厂家：" >
                      <el-input v-model="baseCarPartsSkuAdd.manufacturer" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-row>
                  <el-row :gutter="24">
                    <el-form-item label="销售促销信息：" >
                      <el-input v-model="baseCarPartsSkuAdd.salRemark" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-row>
                  <el-row :gutter="24">
                    <el-form-item label="备注：" >
                      <el-input v-model="baseCarPartsSkuAdd.remark" autocomplete="off"></el-input>
                    </el-form-item>
                  </el-row>
                  <el-row :gutter="24">
                    <el-form-item label="配件图片：" >
                      <el-input autocomplete="off"></el-input>
                    </el-form-item>
                  </el-row>

              </fieldset>

              <fieldset>
                <legend>配件扩展信息</legend>
                <el-form-item label="容量：" >
                  <el-input v-model="baseCarPartsSkuAdd.ext1" autocomplete="off"></el-input>
                </el-form-item>
              </fieldset>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>

          <!--匹配车型的对话框-->
          <el-dialog title="匹配车型" :visible.sync="piPeiCheXing" width="1000px">
<!--            查询区域-->
            <el-form :inline="true" class="demo-form-inline" size="mini">
              <el-form-item label="品牌：">
                <el-select placeholder="请选择">
                  <el-option value="1">
                  </el-option>
                  <el-option value="2">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="车系：">
                <el-select placeholder="请选择">
                  <el-option value="1">
                  </el-option>
                  <el-option value="2">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="年款：">
                <el-select placeholder="请选择">
                  <el-option value="1">
                  </el-option>
                  <el-option value="2">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="排量(升)：">
                <el-select placeholder="请选择">
                  <el-option value="1">
                  </el-option>
                  <el-option value="2">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="厂家：">
                <el-select placeholder="请选择">
                  <el-option value="1">
                  </el-option>
                  <el-option value="2">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="车型名称：">
                <el-input placeholder="规格"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="search">查询</el-button>
              </el-form-item>
            </el-form>
            <el-row :gutter="24">
              <el-col :span="10">
<!--                左表格-->
<!--                <el-table-->
<!--                    :data="tableData"-->
<!--                    style="width: 100%">-->
<!--                  <el-table-column-->
<!--                      prop="date"-->
<!--                      label="日期"-->
<!--                      width="180">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      prop="name"-->
<!--                      label="姓名"-->
<!--                      width="180">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      prop="address"-->
<!--                      label="地址">-->
<!--                  </el-table-column>-->
<!--                </el-table>-->

                <!-- 分页-->
                <el-pagination
                    class="transfer-footer"
                    @size-change="handleSizeChange2"
                    @current-change="handleCurrentChange2"
                    :current-page="page2"
                    :page-sizes="[10, 20, 50,100]"
                    :page-size="row2"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total2">
                </el-pagination>
              </el-col>
<!--          中间按钮-->
              <el-col :span="4">
                <el-button > 选定》》 </el-button>
                <el-button > 《《取消 </el-button>
              </el-col>
<!--             右表格 -->
              <el-col :span="10">
<!--                <el-table-->
<!--                    :data="tableData"-->
<!--                    style="width: 100%">-->
<!--                  <el-table-column-->
<!--                      prop="date"-->
<!--                      label="日期"-->
<!--                      width="180">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      prop="name"-->
<!--                      label="姓名"-->
<!--                      width="180">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      prop="address"-->
<!--                      label="地址">-->
<!--                  </el-table-column>-->
<!--                </el-table>-->
                <!-- 分页-->
                <el-pagination
                    class="transfer-footer"
                    @size-change="handleSizeChange3"
                    @current-change="handleCurrentChange3"
                    :current-page="page3"
                    :page-sizes="[10, 20, 50,100]"
                    :page-size="row3"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total3">
                </el-pagination>
              </el-col>
            </el-row>

            <div slot="footer" class="dialog-footer">
              <el-button @click="piPeiCheXing = false">取 消</el-button>
              <el-button type="primary" >确 定</el-button>
            </div>

          </el-dialog>

<!--           批量标准价的对话框-->
          <el-dialog title="批量标准价" :visible.sync="piLiangBiaoZhunJia">
            <fieldset>
              <legend>基本信息</legend>
              <b>附:假设配件成本价为100,加价率设为1.2,则表示配件销售价为120,如加价率设为1.3,则销售价为130</b>
              <br>
              <el-row :gutter="24">
                <el-col :span="12">
                  总部标准价：
                  <el-input placeholder="请输入数字" size="mini"></el-input>
                </el-col>
              </el-row>
            </fieldset>

            <div slot="footer" class="dialog-footer">
              <el-button @click="piLiangBiaoZhunJia = false">取 消</el-button>
              <el-button type="primary" >确 定</el-button>
            </div>
          </el-dialog>

          <!--           批量加价率的对话框-->
          <el-dialog title="批量加价率" :visible.sync="piLiangJiaJiaLv">
            <fieldset>
              <legend>基本信息</legend>
              <b>附:假设配件成本价为100,加价率设为1.2,则表示配件销售价为120,如加价率设为1.3,则销售价为130</b>
              <br>
              总部标准加价率：
              <el-input v-model="input" placeholder="%" size="mini"></el-input>
            </fieldset>

            <div slot="footer" class="dialog-footer">
              <el-button @click="piLiangJiaJiaLv = false">取 消</el-button>
              <el-button type="primary" >确 定</el-button>
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
          input:"",
          // 是否显示增加修改对话框
          dialogFormVisible: false,
          //是否显示匹配车型对话框
          piPeiCheXing:false,
          //是否显示批量标准价对话框
          piLiangBiaoZhunJia:false,
          //是否显示批量加价率对话框
          piLiangJiaJiaLv:false,
          treeData: [],
          //表格选中数据
          multipleSelection:[],
          //选中的id
          selectId:[],
          tableData: [],
          baseCarPartsSkuQuery: {},
          baseCarPartsSkuAdd:{
            version:0
          },
          //品牌下拉框
          carBrandList:[],
          //添加品牌下拉框
          carBrandListAdd:[],
          defaultProps: {
            children: 'children',
            label: 'name'
          },
          //选中树菜单的子节点
          childrens:[1,1],
          total1: 0,
          pages1: 0,
          row1: 10,
          page1: 1,
          total2: 0,
          pages2: 0,
          row2: 10,
          page2: 1,
          total3: 0,
          pages3: 0,
          row3: 10,
          page3: 1,
        };
      },
      created() {
        const _this = this
        _this.getTreeData();
        _this.getTableData();
        _this.getOps();
      },
      methods:{
        getTreeData(){
          const _this = this
          _this.$axios.post(`/serviceCarsparts/cartype/carTypeZiTree`).then(result=>{
            _this.treeData=result.data.rows;
          })
        },
        getOps(){
          const _this = this
          _this.$axios.get(`/serviceCarsparts/base-car-brand/queryCarBrandAll`).then(result=>{
            _this.carBrandList=result.data.carBrandList;
          })
        },

        //tree点击事件
        carTypeFuTree(data,node){
          const _this = this
          _this.baseCarPartsSkuAdd.partType=data.partTypeUuid;
          this.childrens=node.childNodes;
          if(node.childNodes.length>0){
            this.baseCarPartsSkuQuery.partType='';
            _this.getTableData();
            return
          }
          _this.baseCarPartsSkuQuery.partType=data.partTypeUuid;
          _this.getTableData();
        },

        getTableData(){
          const _this = this
          _this.$axios.post(`/serviceCarsparts/base-car-parts-sku/queryCarPartsSku/${_this.page1}/${_this.row1}`,_this.baseCarPartsSkuQuery).then(result=>{
            _this.tableData=result.data.rows;
            this.total1 = result.data.total
          })
        },
        //选择
        handleSelectionChange(val) {
          this.multipleSelection=val;
          this.selectId=[]
          for(var i=0;i<val.length;i++){
            this.selectId.push(val[i].partUuid);
          }
        },

        //点击添加按钮
        add(){
          if(this.childrens.length>0){
            alert("非3级目录不能添加配件信息")
            return
          }
          const _this = this
          _this.$axios.post(`/serviceCarsparts/base-car-brand/queryCarBrandYi/${_this.baseCarPartsSkuQuery.partType}`).then(result=>{
            _this.carBrandListAdd=result.data.carBrandList;
          })
          this.dialogFormVisible = true;
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
          const _this = this
          // 获取要修改的配件信息编号
          const response = await this.$axios.get(`/serviceCarsparts/base-car-parts-sku/queryById/${this.selectId}`)
          this.baseCarPartsSkuAdd = response.data.baseCarPartsSku
          _this.$axios.post(`/serviceCarsparts/base-car-brand/queryCarBrandYi/${_this.baseCarPartsSkuAdd.partType}`).then(result=>{
            _this.carBrandListAdd=result.data.carBrandList;
          })
          this.dialogFormVisible=true
        },

        // 确认按钮点击事件
        async save(){
          // 添加或者修改操作
          const response = await this.$axios.post(`/serviceCarsparts/base-car-parts-sku/${this.baseCarPartsSkuAdd.partUuid == null ? 'add' : 'update'}`, this.baseCarPartsSkuAdd)
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
              _this.$axios.delete(`/serviceCarsparts/base-car-parts-sku/delete/${this.selectId[i]}`).then(result=>{
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

        //关闭添加修改框
        close(){
          this.baseCarPartsSkuAdd= {};
          this.baseCarPartsSkuAdd.partType=this.baseCarPartsSkuQuery.partType
          this.baseCarPartsSkuAdd.version=0
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
        },

        //页数变化
        handleCurrentChange2(val) {
          this.page2 = val;
        },

        //每页条数变化
        handleSizeChange3(val) {
          this.row3 = val;
        },

        //页数变化
        handleCurrentChange3(val) {
          this.page3 = val;
        }
      }
    }
</script>

<style scoped>

</style>
