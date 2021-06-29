<template>
  <el-dialog :visible="visible" width="1000px" :before-close="close">
    <!-- 标题 -->
    <div slot="title" style="font-size: small; margin: -5px -5px -5px -5px">
      <span>{{ title }}</span>
    </div>
    <!-- 主体 -->
    <div style="margin: -20px; padding: 10px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
      <el-form ref="form" :model="form" :rules="rule" :inline="true" size="mini" label-position="left">
        <el-form-item label="车牌" prop="licensePlate">
          <el-popover
              v-if="type === 'add'"
              placement="bottom-start"
              width="700"
              trigger="hover"
          >
            <custom-vue ref="cust" @select="custSelected" :search="{licensePlate:form.licensePlate}"></custom-vue>
            <el-input slot="reference" v-model="form.licensePlate" ></el-input>
          </el-popover>
          <el-input v-if="type !== 'add'" disabled v-model="form.licensePlate" ></el-input>
        </el-form-item>
        <el-form-item label="车主" prop="custName">
          <el-input :disabled="type !== 'add'" v-model="form.custName"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="custPhone">
          <el-input :disabled="type !== 'add'" v-model="form.custPhone"></el-input>
        </el-form-item>
        <br/>
        <el-form-item label="车型" prop="carName">
          <el-input :disabled="type !== 'add'" v-model="form.carName" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="预约类型" prop="sblType">
          <el-select :disabled="type !== 'add'" v-model="form.sblType">
            <el-option class="mini_option" v-for="(label,key) in DICT_SERV_BOOKING_TYPE" :key="key" :label="label"
                       :value="key"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预约时间" prop="blDate">
          <el-date-picker
              :disabled="type !== 'add'"
              v-model="form.blDate"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <el-tabs value="first" type="card">
        <el-tab-pane label="服务项目" name="first">
          <serv-catalog-table ref="sct"></serv-catalog-table>
        </el-tab-pane>
        <el-tab-pane label="服务配件" name="second">
          <cars-parts-table ref="cpt"></cars-parts-table>
        </el-tab-pane>
      </el-tabs>
    </div>
    <!-- 尾页 -->
    <div slot="footer" style="margin: -10px">
      <el-button size="mini" @click="visible=false">取 消</el-button>
      <el-button size="mini" type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import CustomVue from "./CustTable.vue"
import ServCatalogTable from "./ServCatalogTable.vue"
import CarsPartsTable from "./CarsPartsTable.vue"

export default {
  name: "index",
  components: {
    CustomVue,
    ServCatalogTable,
    CarsPartsTable
  },
  data() {
    return {
      form: {},
      visible: false,
      title: "新增预约单",
      type: "add",
      rule: {
        "licensePlate": {required: true, message: '请输入', trigger: 'blur'},
        "custName": {required: true, message: '请输入', trigger: 'blur'},
        "custPhone": {required: true, message: '请输入', trigger: 'blur'},
        "carName": {required: true, message: '请输入', trigger: 'blur'},
        "blDate": {required: true, message: '请输入', trigger: 'blur'},
      },
      DICT_SERV_BOOKING_TYPE: {
        custBooking: "客户预约",
        saBooking: "SA预约",
        storeBooking: "门店现场预约"
      }
    }
  },
  methods: {
    submit() {
      let _this = this;
      _this.$refs["form"].validate(valid => {
        if (valid) {
          let form = JSON.stringify(Object.assign({},_this.form,{carUuid:_this.form.carTypeUuid}));
          alert(form)
          let sctRow = JSON.stringify(_this.$refs["sct"].getSelectionInServItem().concat(_this.$refs["sct"].checkoutTableRow));
          let cptRow = JSON.stringify(_this.$refs["cpt"].getSelectionInServItem().concat(_this.$refs["cpt"].checkoutTableRow));
          let sctDIds = JSON.stringify(_this.$refs["sct"].deleteTableIds);
          let cptDIds = JSON.stringify(_this.$refs["cpt"].deleteTableIds);
          if (sctRow.length > 2) {
            _this.$axios.post("/brCarService/serv-booking-head/saveOrUpdate", {form, sctRow, cptRow,sctDIds,cptDIds})
                .then(() => {
                  _this.$message({
                    type: 'success',
                    message: '录入成功!'
                  });
                  _this.visible = false;
                  _this.$emit("submit")
                })
          } else {
            _this.$alert('你没有选择至少一条项目，无法进行操作', '请选择服务项目', {
              confirmButtonText: '确定',
              callback: () => {
                this.$message({
                  type: 'info',
                  message: '请选择服务项目'
                });
              }
            })
          }
        } else {
          _this.$alert('你没有将表单填充完整，无法进行操作', '请填充完整', {
            confirmButtonText: '确定',
            callback: () => {
              this.$message({
                type: 'info',
                message: '请填充完整'
              });
            }
          })
        }
      })
    },
    close(){
      let _this = this;
      this.$confirm('你所输入的数据不会被提交和保存','确认关闭',{
        type: 'warning'
      }).then(() => {
        _this.visible = false;
      }).catch(()=>{})
    },
    custSelected(key) {
      this.form = Object.assign({}, this.form, key)
    },
    showIn(type, key) {
      let _this = this;
      if (type === "edit") {
        _this.title = "编辑预约单"
        _this.type = "edit"
        _this.$axios.get(`/brCarService/serv-booking-head/getAppointInfo/${key}`).then(({data}) => {
          _this.form = Object.assign({},data.cust,data.head)
          _this.visible = true;
          setTimeout(function () {
            _this.$refs["sct"].loadingItems(data.items);
            _this.$refs["cpt"].loadingItems(data.parts);
          }, 10)
        })
      } else {
        _this.title = "新增预约单"
        _this.type = "add"
        _this.form = {sblType: "custBooking"};
        _this.visible = true;
        setTimeout(function () {
          _this.$refs["sct"].loadingItems([]);
          _this.$refs["cpt"].loadingItems([]);
        }, 10)
      }
    }
  }
}
</script>

<style scoped>
.my-label {
  text-align: right;
  vertical-align: middle;
  float: left;
  font-size: 12px;
  color: #606266;
  line-height: 28px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
}

.mini_option {
  font-size: 8px;
  height: 28px;
  line-height: 28px;
}
</style>