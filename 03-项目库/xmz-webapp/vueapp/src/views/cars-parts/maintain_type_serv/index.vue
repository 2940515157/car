<template>
  <div id="parent">
    <!-- 搜索框 -->
    <div id="search">
      <el-form size="mini" :inline="true" :model="queryParam" Department="demo-form-inline">

        <el-form-item label="品牌">
          <el-select @change="brandSelectChange" :disabled="brandFlag" filterable style="width: 130px"
                     v-model="queryParam.brand"
                     placeholder="请选择">
            <el-option
                    v-for="(item, $index) in brandSelectData"
                    :key="$index"
                    :label="item.brand"
                    :value="item.brand">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="车系">
          <el-select @change="modelSelectChange" :disabled="modelFlag" filterable style="width: 130px"
                     v-model="queryParam.model" placeholder="请选择">
            <el-option
                    v-for="(item, $index)  in carMaintainData2"
                    :key="$index"
                    :label="item.carModel"
                    :value="item.carModel">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="年款">
          <el-select @change="yearSelectChange" :disabled="yearFlag" filterable style="width: 130px"
                     v-model="queryParam.year" placeholder="请选择">
            <el-option
                    v-for="(item, $index)  in carMaintainData2"
                    :key="$index"
                    :label="item.carYear"
                    :value="item.carYear">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="排量">
          <el-select @change="ccSelectChange" :disabled="ccFlag" filterable style="width: 130px" v-model="queryParam.cc"
                     placeholder="请选择">
            <el-option
                    v-for="(item, $index)  in carMaintainData2"
                    :key="$index"
                    :label="item.displacement"
                    :value="item.displacement">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="保养类型">
          <el-select @change="mcSelectChange" :disabled="maintainTypeFlag" filterable style="width: 130px"
                     v-model="queryParam.maintainType" placeholder="请选择">
            <el-option
                    v-for="(item, $index)  in carMaintainData2"
                    :key="$index"
                    :label="item.dictname"
                    :value="item.maintainType">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="default" icon="el-icon-close" @click="clear"></el-button>
        </el-form-item>

        <!-- 增删改 -->
        <el-form-item style="margin-left: 100px;">
          <el-button type="primary" @click="add" class="el-icon-plus"></el-button>
          <el-button type="warning" :disabled="mcUuid==null || mcUuid==''" @click="edit"
                     class="el-icon-edit"></el-button>
          <el-button type="danger" :disabled="mcUuid==null || mcUuid==''" @click="del"
                     class="el-icon-delete"></el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 数据表格 -->
    <div id="table">
      <el-table
              v-loading="loading"
              height="410"
              size="mini"
              :data="carMaintainData"
              highlight-current-row
              style="width: 100%"
              @current-change="tableCurrentChange"
              ref="multipleTable"
              @row-click="tableClick">
        <el-table-column
                fixed
                align="center"
                type="selection"
                width="50">
        </el-table-column>
        <el-table-column
                fixed
                align="center"
                type="index"
                width="50">
        </el-table-column>
        <el-table-column
                fixed
                align="center"
                label="品牌"
                width="100px"
                prop="brand">
        </el-table-column>
        <el-table-column
                fixed
                align="center"
                label="车系"
                width="100px"
                prop="carModel">
        </el-table-column>
        <el-table-column
                align="center"
                label="车辆信息"
                width="270px"
                prop="saleName">
        </el-table-column>
        <el-table-column
                align="center"
                prop="carYear"
                label="年款">
        </el-table-column>
        <el-table-column
                align="center"
                width="120px"
                prop="dictname"
                label="保养类型">
        </el-table-column>
        <el-table-column
                align="center"
                prop="mfMileage"
                label="首保里程">
        </el-table-column>
        <el-table-column
                align="center"
                prop="mfDate"
                width="100px"
                label="首保时间(月)">
        </el-table-column>
        <el-table-column
                align="center"
                prop="mfOp"
                width="250px"
                label="首保操作">
        </el-table-column>
        <el-table-column
                align="center"
                property="mfMileage"
                label="二保里程">
        </el-table-column>
        <el-table-column
                align="center"
                property="mfDate"
                width="100px"
                label="二保时间(月)">
        </el-table-column>
        <el-table-column
                align="center"
                property="mfOp"
                width="250px"
                label="二保操作">
        </el-table-column>
        <el-table-column
                align="center"
                property="intervalMileage"
                label="间隔里程">
        </el-table-column>
        <el-table-column
                align="center"
                property="intervalDate"
                width="100px"
                label="间隔时间(月)">
        </el-table-column>
        <el-table-column
                align="center"
                property="intervalOp"
                width="250px"
                label="间隔操作">
        </el-table-column>
        <el-table-column
                align="center"
                prop="remark"
                label="备注">
        </el-table-column>

      </el-table>
    </div>

    <!-- 分页栏 -->
    <el-pagination
            @size-change="sizeChange"
            @current-change="currentChange"
            :current-page="current"
            :page-sizes="pageSizeList"
            :page-size="size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>

    <!-- 添加编辑对话框 -->
    <el-dialog :title="mcUuid == '' ? '添加':'编辑'" :visible.sync="dialogFormVisible" width="850px">

      <!-- 搜索框 -->
      <div v-show="mcUuid==null || mcUuid==''" style="margin-top: -30px">
        <el-form size="mini" :inline="true" :model="queryParam" Department="demo-form-inline">
          <el-form-item label="品牌">
            <el-select @change="brandSelectChange2" :disabled="brandFlag2" filterable style="width: 130px"
                       v-model="queryParam.brand"
                       placeholder="请选择">
              <el-option
                      v-for="(item, $index) in brandSelectData"
                      :key="$index"
                      :label="item.brand"
                      :value="item.brand">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="车系">
            <el-select @change="modelSelectChange2" :disabled="modelFlag2" filterable style="width: 130px"
                       v-model="queryParam.model" placeholder="请选择">
              <el-option
                      v-for="(item, $index)  in carMaintainData2"
                      :key="$index"
                      :label="item.carModel"
                      :value="item.carModel">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="年款">
            <el-select @change="yearSelectChange2" :disabled="yearFlag2" filterable style="width: 130px"
                       v-model="queryParam.year" placeholder="请选择">
              <el-option
                      v-for="(item, $index)  in carMaintainData2"
                      :key="$index"
                      :label="item.carYear"
                      :value="item.carYear">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="排量">
            <el-select @change="ccSelectChange2" :disabled="ccFlag2" filterable style="width: 130px"
                       v-model="queryParam.cc"
                       placeholder="请选择">
              <el-option
                      v-for="(item, $index)  in carMaintainData2"
                      :key="$index"
                      :label="item.displacement"
                      :value="item.displacement">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="default" icon="el-icon-close" @click="clear2"></el-button>
          </el-form-item>

        </el-form>
      </div>

      <!-- 数据表格 -->
      <div v-show="mcUuid==null || mcUuid==''" id="table2">
        <el-table
                height="210"
                size="mini"
                :data="carData"
                highlight-current-row
                @current-change="tableCurrentChange2"
                ref="multipleTable2"
                @row-click="tableClick2">
          <el-table-column
                  fixed
                  align="center"
                  type="selection"
                  width="50">
          </el-table-column>
          <el-table-column
                  fixed
                  align="center"
                  type="index"
                  width="50">
          </el-table-column>
          <el-table-column
                  align="center"
                  label="车辆信息"
                  width="450px"
                  prop="showName">
          </el-table-column>
        </el-table>
        <!-- 分页栏 -->
        <el-pagination
                @size-change="sizeChange2"
                @current-change="currentChange2"
                :current-page="current2"
                :page-sizes="pageSizeList"
                :page-size="size2"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total2">
        </el-pagination>
      </div>

      <!--  表单    -->
      <el-form style="margin-top: 10px" size="mini" inline :model="form">
        <el-form-item v-show="mcUuid!=null || mcUuid!=''" label="车辆信息" :label-width="formLabelWidth">
          <el-input style="width: 450px" readonly :value="carShowName"></el-input>
        </el-form-item>
        <el-form-item label="备注" :label-width="formLabelWidth">
          <el-input style="width: 310px" v-model="form.remark"></el-input>
        </el-form-item>
        <el-form-item label="保养类型" :label-width="formLabelWidth">
          <el-select filterable style="width: 150px" v-model="form.maintainType"
                     placeholder="请选择">
            <el-option
                    v-for="item in maintainTypeData"
                    :key="item.dictid"
                    :label="item.dictname"
                    :value="item.dictid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="首保里程" :label-width="formLabelWidth">
          <el-input style="width: 100px" v-model="form.mfMileage"></el-input>
        </el-form-item>
        <el-form-item label="首保时间(月)" :label-width="formLabelWidth">
          <el-input-number controls-position="right" style="width: 100px" :min="1" :max="12" size="mini"
                           v-model="form.mfDate"></el-input-number>
        </el-form-item>
        <el-form-item label="首保操作" :label-width="formLabelWidth">
          <el-input style="width: 150px" v-model="form.mfOp"></el-input>
        </el-form-item>
        <el-form-item label="二保里程" :label-width="formLabelWidth">
          <el-input style="width: 100px" v-model="form.msMileage"></el-input>
        </el-form-item>
        <el-form-item label="二保时间(月)" :label-width="formLabelWidth">
          <el-input-number controls-position="right" style="width: 100px" :min="1" :max="12" size="mini"
                           v-model="form.msDate"></el-input-number>
        </el-form-item>
        <el-form-item label="二保操作" :label-width="formLabelWidth">
          <el-input style="width: 150px" v-model="form.msOp"></el-input>
        </el-form-item>
        <el-form-item label="间隔里程" :label-width="formLabelWidth">
          <el-input style="width: 100px" v-model="form.intervalMileage"></el-input>
        </el-form-item>
        <el-form-item label="间隔时间(月)" :label-width="formLabelWidth">
          <el-input-number controls-position="right" style="width: 100px" :min="1" :max="12" size="mini"
                           v-model="form.intervalDate"></el-input-number>
        </el-form-item>
        <el-form-item label="间隔操作" :label-width="formLabelWidth">
          <el-input style="width: 150px" v-model="form.intervalOp"></el-input>
        </el-form-item>
      </el-form>

      <!-- 操作按钮 -->
      <div style="margin-top: -50px;" slot="footer" Department="dialog-footer">
        <el-button size="mini" @click="cancel">取 消</el-button>
        <el-button size="mini" type="primary" @click="save">确 定</el-button>
      </div>

    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        loading: false,
        // 查询参数
        queryParam: {brand: '', model: '', year: '', cc: '', maintainType: ''},
        // 查询参数
        queryParamAdd: {brand: '', carModel: '', carYear: '', displacement: ''},
        //所以保养信息
        carMaintainData: [],
        carMaintainData2: [],
        //下拉框数据
        brandSelectData: [],
        //下拉框是否禁用
        brandFlag: false,
        modelFlag: true,
        yearFlag: true,
        ccFlag: true,
        brandFlag2: false,
        modelFlag2: true,
        yearFlag2: true,
        ccFlag2: true,
        maintainTypeFlag: true,
        //id
        mcUuid: '',
        //表单
        form: {
          mcUuid: '', carTypeUuid: '', maintainType: '', remark: '',
          mfMileage: '', mfDate: '', mfOp: '',
          msMileage: '', msDate: '', msOp: '',
          intervalMileage: '', intervalDate: '', intervalOp: ''
        },
        // 当前页号
        current: 1,
        current2: 1,
        // 分页列表
        pageSizeList: [5, 10, 15, 20],
        // 页大小
        size: 10,
        size2: 5,
        // 总记录数
        total: 0,
        total2: 0,
        // 是否显示对话框
        dialogFormVisible: false,
        // 表单元素的label的宽度
        formLabelWidth: '100px',
        //所有汽车数据
        carData: [],
        //所有保养类型信息
        maintainTypeData: [],
        carShowName: ''
      }
    },

    methods: {
      //查询所有保养信息
      async getCarMaintainData() {
        this.loading = true;
        const result = await this.$axios.post(`/serviceCarsparts/base-maintain-cycle/queryList/${this.current}/${this.size}`, this.queryParam)
        this.carMaintainData = result.data.rows
        this.total = result.data.total
        this.loading = false;
      },
      //查询所有汽车型号
      async getCarData() {
        this.loading = true;
        const result = await this.$axios.post(`/serviceCarsparts/base-maintain-cycle/carList/${this.current2}/${this.size2}`, this.queryParam)
        this.carData = result.data.rows
        this.total2 = result.data.total
      },
      //查询保养类型数据
      async getMaintainTypeData() {
        const result = await this.$axios.get(`/serviceCarsparts/base-maintain-cycle/queryDict/DICT_MAINTAIN_TYPE`)
        this.maintainTypeData = result.data
      },
      //查询下拉框数据
      async getCarMaintainData2() {
        const result = await this.$axios.post(`/serviceCarsparts/base-maintain-cycle/select`, this.queryParam)
        this.carMaintainData2 = result.data.rows
      },
      //下拉框改变事件
      brandSelectChange() {
        this.brandFlag = true;
        this.modelFlag = false;
        this.getCarMaintainData2();
        this.getCarMaintainData();
      },
      modelSelectChange() {
        this.yearFlag = false;
        this.modelFlag = true;
        this.getCarMaintainData2();
        this.getCarMaintainData();
      },
      yearSelectChange() {
        this.ccFlag = false;
        this.yearFlag = true;
        this.getCarMaintainData2();
        this.getCarMaintainData();
      },
      ccSelectChange() {
        this.maintainTypeFlag = false;
        this.ccFlag = true;
        this.getCarMaintainData2();
        this.getCarMaintainData();
      },
      mcSelectChange() {
        this.getCarMaintainData();
      },
      //add框的下拉框改变事件
      brandSelectChange2() {
        this.brandFlag2 = true;
        this.modelFlag2 = false;
        this.getCarMaintainData2();
        this.getCarData();
      },
      modelSelectChange2() {
        this.yearFlag2 = false;
        this.modelFlag2 = true;
        this.getCarMaintainData2();
        this.getCarData();
      },
      yearSelectChange2() {
        this.ccFlag2 = false;
        this.yearFlag2 = true;
        this.getCarMaintainData2();
        this.getCarData();
      },
      ccSelectChange2() {
        this.ccFlag2 = true;
        this.getCarMaintainData2();
        this.getCarData();
      },
      //点击clear按钮
      clear() {
        this.queryParam = {};
        this.brandFlag = false;
        this.modelFlag = true;
        this.yearFlag = true;
        this.ccFlag = true;
        this.maintainTypeFlag = true;
        this.getCarMaintainData2();
        this.getCarMaintainData();
      },
      clear2() {
        this.queryParam = {};
        this.brandFlag2 = false;
        this.modelFlag2 = true;
        this.yearFlag2 = true;
        this.ccFlag2 = true;
        this.getCarMaintainData2();
        this.getCarData();
      },
      //点击保存按钮
      async save() {
        this.dialogFormVisible = false
        this.queryParam = {}
        // 添加或者修改操作
        let result = await this.$axios.post(`/serviceCarsparts/base-maintain-cycle/${this.mcUuid == null || this.mcUuid == '' ? 'add' : 'update'}`, this.form)
        if (result.flag) {
          // 提示消息
          this.$message({
            type: 'success',
            message: '成功!'
          })
          // 刷新数据表格
          await this.getCarMaintainData()
          this.loading = false
        }
      },
      //点击取消按钮
      async cancel() {
        this.dialogFormVisible = false;
        this.form = {}
        this.queryParam = {}
        // 刷新数据表格
        await this.getCarMaintainData()
      },
      //点击添加按钮
      add() {
        this.mcUuid = '';
        this.form = {};
        this.brandFlag = false
        this.modelFlag = true
        this.yearFlag = true
        this.ccFlag = true
        this.queryParam = {};
        this.$refs.multipleTable.clearSelection();
        this.dialogFormVisible = true;
      },
      //点击编辑按钮
      async edit() {
        let result = await this.$axios.get(`/serviceCarsparts/base-maintain-cycle/queryById/${this.mcUuid}`)
        this.form = result.data
        this.dialogFormVisible = true
      },
      //点击删除按钮
      del() {
        this.$confirm('将删除此数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          // 删除操作（物理，逻辑）
          const result = await this.$axios.delete(`/serviceCarsparts/base-maintain-cycle/delete/${this.mcUuid}`)
          if (result.flag) {
            // 提示消息
            this.$message({
              type: 'success',
              message: '成功!'
            })
            // 刷新数据表格
            await this.getCarMaintainData();
            this.$refs.multipleTable.clearSelection();
            this.mcUuid = '';
          }

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      //选中行事件
      tableCurrentChange(val) {
        this.mcUuid = val.mcUuid;
        this.carShowName = val.showName;
      },
      tableCurrentChange2(val) {
        this.form.carTypeUuid = val.carTypeUuid;
      },
      //行点击事件
      tableClick(row) {
        this.$refs.multipleTable.clearSelection();
        this.$refs.multipleTable.toggleRowSelection(row);
      },
      tableClick2(row) {
        this.$refs.multipleTable2.clearSelection();
        this.$refs.multipleTable2.toggleRowSelection(row);
      },
      // 页号改变事件
      currentChange(val) {
        this.current = val;
        this.getCarMaintainData();
      },
      currentChange2(val) {
        this.current2 = val;
        this.getCarData()
      },
      // 页大小改变事件
      sizeChange(val) {
        this.size = val;
        this.getCarMaintainData();
      },
      sizeChange2(val) {
        this.size2 = val;
        this.getCarData()
      }
    },

    created() {
      this.getCarMaintainData();
      this.getCarMaintainData2();
      this.getCarData();
      this.getMaintainTypeData();
      setTimeout(() => {
        this.brandSelectData = this.carMaintainData2;
      }, 4000)

    }
  }
</script>

<style scoped>
  #parent {
    width: 1290px;
    height: 540px;
    margin-left: -20px;
    margin-top: -20px;
  }

  #search {
    margin-left: 20px;
  }

  #table {
    width: 1290px;
  }
</style>
