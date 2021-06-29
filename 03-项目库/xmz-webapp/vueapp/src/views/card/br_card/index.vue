<!-- - - - - - - - - - - - -->
<!--        发卡管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <el-form :inline="true" :model="brCardQuery" class="demo-form-inline" size="mini">
                <div style="margin-left: -100px">
                    <el-form-item label="大区" :label-width="formLabelWidth" >
                        <el-select v-model="brCardQuery.orgUuid"  @change="change1" clearable placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in YingYunData"
                                    :key="item.orgUuid"
                                    :label="item.orgName"
                                    :value="item.orgUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="区域" :label-width="formLabelWidth">
                        <el-select v-model="brCardQuery.areaUuid" @change="change2" @clear='ch' clearable placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in QuYuData"
                                    :key="item.areaUuid"
                                    :label="item.areaName"
                                    :value="item.areaUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="门店" :label-width="formLabelWidth">
                        <el-select v-model="brCardQuery.brUuid" @change="change3" @clear='ch3' clearable placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in MenDianData"
                                    :key="item.brUuid"
                                    :label="item.brName"
                                    :value="item.brUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>


                    <el-form-item label="领卡人" :label-width="formLabelWidth">
                        <el-select v-model="brCardQuery.empUuid" clearable placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in LingKaRen"
                                    :key="item.empUuid"
                                    :label="item.empName"
                                    :value="item.empUuid">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="是否导入" v-model="brCardQuery.impFlag" :label-width="formLabelWidth">
                        <el-select v-model="brCardQuery.impFlag" placeholder="请选择" style="width: 100px" clearable>
                            <el-option
                                    v-for="item in options"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="卡号" :label-width="formLabelWidth">
                        <el-input v-model="brCardQuery.cardNo" placeholder="卡号" style="width: 100px"></el-input>
                    </el-form-item>

                    <el-form-item v-model="brCardQuery.isExtend" label="是否已经发放给客户" :label-width="formLabelWidth">
                        <el-select v-model="brCardQuery.isExtend" clearable placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in options1"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item v-model="brCardQuery.brCardStatus" label="卡状态" :label-width="formLabelWidth">
                        <el-select  v-model="brCardQuery.brCardStatus" clearable placeholder="请选择" style="width: 100px">
                            <el-option
                                    v-for="item in options2"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item >
                        <el-button type="primary" @click="SerachChaX">查询</el-button>
                    </el-form-item>
                </div>
            </el-form>


            <el-button type="primary"  size="mini" icon="el-icon-plus" @click="FaKa=true">发卡</el-button>
            <el-button type="danger" size="mini"  icon="el-icon-delete" @click="zuofei">作废</el-button>
            <!--  发卡数据-->
            <el-table
                    ref="multipleTable"
                    :data="brCardData"
                    size="mini"
                    tooltip-effect="dark"
                    style="width: 100%"
                    max-height="300px"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="55">
                </el-table-column>

                <el-table-column
                        prop="cardNo"
                        label="卡号"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="brName"
                        label="领卡门店"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="empName"
                        label="领卡人"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="EXTEND_UUID"
                        label="发放人"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="kaShang"
                        label="卡商"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="isExtend"
                        label="是否发放给客户"
                        width="120"
                        :formatter="formatUserState">
                </el-table-column>

                <el-table-column
                        prop="stutas"
                        label="卡状态"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="createdDate"
                        label="发卡时间"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="validDate"
                        label="有效期"
                        width="120">
                </el-table-column>

                <el-table-column
                        prop="remark"
                        label="备注"
                        show-overflow-tooltip>
                </el-table-column>
            </el-table>
            <!--分页区域-->
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="current"
                    :page-sizes="pageSizeList"
                    :page-size="limit"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>

            <el-dialog title="增加发卡管理" :visible.sync="FaKa" width="60%" @close="DialogClosed">
                <fieldset style="border:solid 1px #aaa;padding: 3px">
                    <legend>基本信息</legend>
                    <el-form :model="form" size="mini" :inline="true"
                             :rules="FormRules" ref="FormRef">

                        <el-form-item label="批次:"   :label-width="formLabelWidth1">
                            <el-select size="small" v-model="cardStoreUuid" @change="change7"   clearable placeholder="请选择" style="width: 300px">
                                <el-option v-for="item in ZhiKaData"
                                           :key="item.cardStoreUuid"
                                           :label="'前缀:'+item.cardPrefix+' 总张数:'+item.cardNum+' 剩余张数:'+item.remainCardNum"
                                           :value="item.cardStoreUuid">
                                    <span style="float: left;margin-right:30px;width:100px;">{{"前缀:"+item.cardPrefix+" 总张数:"+item.cardNum+" 剩余张数:"+item.remainCardNum }}</span>
                                    <span style="float: left;margin-right:30px;width:180px;"></span>
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <br>
                        <el-form-item label="张数:"  :label-width="formLabelWidth1">
                            <el-input v-model="zhangshu" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>
                        <el-form-item label="开始卡号:" :label-width="formLabelWidth1">
                            <el-input readonly v-model="startKaHao" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>
                        <el-form-item label="结束卡号:" :label-width="formLabelWidth1">
                            <el-input readonly v-model="endKaHao" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>


                        <el-form-item label="类型:" prop="cardCompany"  :label-width="formLabelWidth1">
                            <el-select v-model="form.cardCompany" placeholder="--请选择--" clearable style="width: 100px">
                                <el-option
                                        v-for="item in options3"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="大区:"  :label-width="formLabelWidth1">
                            <el-select v-model="this.orgUuid" @change="change4" clearable placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in YingYunData"
                                        :key="item.orgUuid"
                                        :label="item.orgName"
                                        :value="item.orgUuid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="区域:" :label-width="formLabelWidth1">
                            <el-select v-model="this.areaUuid" @change="change5" clearable placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in QuYuData"
                                        :key="item.areaUuid"
                                        :label="item.areaName"
                                        :value="item.areaUuid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="领卡门店:" prop="brUuid" :label-width="formLabelWidth1">
                            <el-select v-model="form.brUuid" clearable @change="change6" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in MenDianData"
                                        :key="item.brUuid"
                                        :label="item.brName"
                                        :value="item.brUuid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="领卡人:" prop="empUuid" :label-width="formLabelWidth1">
                            <el-select v-model="form.saUuid" clearable placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in LingKaRen"
                                        :key="item.empUuid"
                                        :label="item.empName"
                                        :value="item.empUuid">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <br>

                        <el-form-item label="备注:" :label-width="formLabelWidth1">
                            <el-input type="textarea" v-model="form.remark" style="width: 400px"></el-input>
                        </el-form-item>
                    </el-form>
                </fieldset>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="FaKa = false">取 消</el-button>
                    <el-button type="primary" @click="fakasave()">确 定</el-button>
                </div>
            </el-dialog>

        </el-card>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            return{
                //验证规则
                FormRules:{
                  brUuid:[
                      {required: true, message: '请选择领卡门店'},
                  ],
                    saUuid:[
                        {required: true, message: '请选择领卡人'},
                  ],
                  cardCompany:[
                      {required: true, message: '请选择卡商'},
                  ],
                },
                //获取当前时间
                format(date, fmt) {
                    let o = {
                        "M+": date.getMonth() + 1, //月份
                        "d+": date.getDate(), //日
                        "H+": date.getHours(), //小时
                        "m+": date.getMinutes(), //分
                        "s+": date.getSeconds(), //秒
                        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                        "S": date.getMilliseconds() //毫秒
                    };
                    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
                    for (let k in o)
                        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    return fmt;
                },
                //发卡的查询条件
                brCardQuery:{},
                //发卡的分页条件查询数据
                brCardData:[],
                //当前时间
                currentTime:'',
                //判断是否组合产品
                formatUserState: function (row, column, cellValue) {
                    if (cellValue === 'N') {
                        return '否';
                    } else if (cellValue === 'Y') {
                        return '是';
                    }
                },
                //是否导入
                options: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}],
                //是否已发放给客户
                options1: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}],
                //卡状态
                options2: [
                    {id: 'normal', label: '正常'},
                    {id: 'invalid', label: '已作废'}],
                options3: [
                    {id: 'suremoov', label: '小拇指卡'},
                  ],

                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                // 封装表单数据的对象
                form: {},
                //发卡对话框
                FaKa:false,
                // 表单元素的label的宽度
                formLabelWidth: '140px',
                // 表单元素的label的宽度
                formLabelWidth2: '50px',
                formLabelWidth1: '100px',
                //所有领卡人
                LingKaRen:[],
                //所有营运中心的部门
                YingYunData:[],
                //根据大区查出所有的区域
                QuYuData:[],
                //根据大区和区域查询出门店
                MenDianData:[],
                //查询门店的条件
                hrBranches:{},
                //部门id
                orgUuid:'',
                //大区id
                areaUuid:'',
                //查询出所有制卡管理
                ZhiKaData: [],
                cardStoreUuid:'',
                KaKuiData:{},
                zhangshu:'',
                startKaHao:'',
                endKaHao:'',
                qianzhui:'',
                shengNum:'',
                zongshu:'',
                employeeData:{},
                quyuData:{},
                //选中的发卡数据
                selectFKData:[],
            }
        },
        created() {
            this.getBrCardList();
            //查询所有营运中心的部门
            this.getYingYun();
            //根据大区查出所有的区域
            this.getQuYuList();
            //根据大区和区域查询出门店
            this.getMenDianList();
            this.getZhiKaList();
            this.mounted();
        },
        methods:{
            //获取当前时间
            mounted(){
                this.currentTime=this.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            },

            //发卡
            fakasave(){
                this.$refs.FormRef.validate(valid => {
                        if (!valid) return;
                //张数
                if(this.KaKuiData.remainCardNum<this.zhangshu){
                    this.$message({
                        type:'info',
                        message:'不能大于剩余的张数'
                    })
                    return
                }
                //如果剩余张数小于总的张数 说明已经发行了一部分

                    //卡库id
                    this.form.cardStoreUuid=this.cardStoreUuid;
                    //前缀
                    this.qianzhui=this.KaKuiData.cardPrefix;
                    //总数
                    this.zongshu=this.KaKuiData.cardNum;
                    //剩余数
                    this.shengNum=this.KaKuiData.remainCardNum;
                    //this.form.saUuid=this.$store.getters.userInfo.id;
                    //发放人
                    this.form.extendUuid=this.$store.getters.userInfo.name;
                    //是否发放给客户
                    this.form.isExtend='N';
                    //是否有效
                    this.form.isActive='Y';
                    //创建日期
                    this.form.createdDate=this.currentTime;
                    //创建人(当前登录的用户的name)
                    this.form.createdBy=this.$store.getters.userInfo.name
                    //版本
                    this.form.version=0;
                    //是否导入
                    this.form.impFlag='Y';
                    //卡片的状态
                    this.form.brCardStatus='normal';
                    const _this = this
                /*
                *
                * Object.assign(_this.form,this.zhangshu)
                * */
                    _this.$axios.post('/serviceCard/acct-br-card/add',
                        {
                            form: JSON.stringify(_this.form),
                            zhangshu: _this.zhangshu,
                            startKaHao:_this.startKaHao,
                            qianzhui:_this.qianzhui,
                            zongshu:this.zongshu,
                            shengNum:this.shengNum,
                        }
                    ).then(result=> {
                        if (result.code === 20000) {
                            this.$message({
                                type: 'success',
                                message: '添加成功!',
                            });
                        }
                    })
                //清空数据
                this.cardStoreUuid='';
                this.orgUuid='';
                this.areaUuid='';
                this.zhangshu='';
                this.startKaHao='';
                this.endKaHao='';
                this.form={};

                this.getZhiKaList();
                //添加完关闭对话框
                this.FaKa=false;

                })
            },
            //作废
            zuofei(){
                if(this.selectFKData.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
               if(this.selectFKData[0].stutas==='已作废'){
                   this.$message({
                       type:'info',
                       message:'已经作废的卡不需要再次作废!!!'
                   })
                   return
               }

                const _this = this
                for(var i=0;i<this.selectFKData.length;i++){
                    _this.$axios.post('/serviceCard/acct-br-card/update',this.selectFKData[i]).then(result=>{
                        if(result.code===20000){
                            //刷新
                            this.SerachChaX();

                        }

                    })
                }
                 this.$message({
                     type: 'success',
                     message: '作废成功!'
                 });
            },
            //查询出所有制卡管理
            getZhiKaList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-card-store/queryAll/`).then(result=>{
                    _this.ZhiKaData=result.data.list;

                })
            },
            //发卡管理分页条件查询数据
            getBrCardList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-br-card/queryAllABrCard/${this.current}/${this.limit}`,this.brCardQuery).then(result=>{
                    _this.brCardData=result.data.rows;
                    _this.total=result.data.total;
                })
            },

            //大区改变时间
            change1(val){
                this.orgUuid=val
                this.getQuYuList();
            },
            change4(val){
                this.orgUuid=val
                this.getQuYuList1();
            },
            change2(val){
                    this.areaUuid=val;
                    this.getOrgUuidList();
                    this.getMenDianList();
            },
            //门店改变时间
            change3(val){
                this.employeeData.brUuid=val;
                this.getLingKaRen();
            },
            change5(val){
                this.areaUuid=val;
                this.getOrgUuidList();
                this.getMenDianList();
            },
            change6(val){
                this.employeeData.brUuid=val;
                this.getLingKaRen();
            },
            //大区清除
            ch(){
                this.brCardQuery.orgUuid='';
                this.areaUuid='';
                //查询所有营运中心的部门
                this.getBrCardList();
                this.getOrgUuidList()
                this.getYingYun();

            },
            //门店清除
            ch3(){
                this.orgUuid='';
                this.areaUuid='';
                //查询所有营运中心的部门
                this.getMenDianList();
            },

            //查询所有领卡人
            getLingKaRen(){
                const _this = this
                this.employeeData.orgUuid= this.orgUuid;
                _this.$axios.post(`/serviceHr/employees/queryAllSa/`,_this.employeeData).then(result=>{
                    if(result.code===20000){
                        _this.LingKaRen=result.data.list;
                    }
                })
            },

            //查询所有营运中心的部门
            getYingYun(){
                const _this = this
                _this.$axios.post(`/serviceHr/HrOrg/queryAllYingYun/`,{'orgUuid':this.orgUuid}).then(result=>{
                        _this.YingYunData=result.data.list;
                })
            },

            //根据大区查出部门id
            getOrgUuidList(){
               const _this = this
                _this.$axios.post(`/serviceHr/hrArea/queryAllOrgUuid/`,{'areaUuid':_this.areaUuid}).then(result=>{
                    console.log(result.data.list.orgUuid)
                    this.brCardQuery.orgUuid=result.data.list.orgUuid
                    this.getBrCardList();
                    //查询所有营运中心的部门
                    this.getYingYun();
                    //根据大区查出所有的区域
                    this.getQuYuList();
                })
            },

            //根据大区查出所有的区域
            getQuYuList(){
                  const _this = this
                   this.quyuData.orgUuid=this.orgUuid;
                  this.$axios.post(`/serviceHr/hrArea/queryAllHrAreas/`,this.quyuData).then(result=>{
                      if(result.code===20000){
                          _this.QuYuData=result.data.list;
                          console.log(result.data.list)
                      }
                  })
              },

            getQuYuList1(){
                const _this = this
                this.quyuData.orgUuid=this.orgUuid;
                this.$axios.post(`/serviceHr/hrArea/queryAllHrAreas/`,this.quyuData).then(result=>{
                    if(result.code===20000){
                        _this.QuYuData=result.data.list;
                    }
                })
            },

            //根据大区和区域查询出门店
            getMenDianList(){
                 const _this = this
                 this.hrBranches.orgUuid=this.orgUuid;
                 this.hrBranches.areaUuid=this.areaUuid;

                 _this.$axios.post(`/serviceHr/hr-branches/queryAllMenDian/`,this.hrBranches).then(result=>{
                     if(result.code===20000){
                         _this.MenDianData=result.data.list
                     }
                 })
             },

            //点击查询
            SerachChaX(){
                this.getBrCardList();
            },
            //批次改变事件
            change7(val){
                console.log(val)
                const _this = this
                if(val!=""){
                    _this.$axios.post(`/serviceCard/acct-card-store/queryById/${val}`).then(result=>{
                        _this.KaKuiData=result.data.acctCardStore;
                        this.zhangshu=_this.KaKuiData.remainCardNum;
                        this.startKaHao=_this.KaKuiData.cardNoBeg;
                        this.endKaHao=_this.KaKuiData.cardNoEnd;
                    })
                }

            },

            //发卡管理选择事件
            handleSelectionChange(val){
                this.selectFKData=val;
            },
            //监听表单验证关闭事件
            DialogClosed(){
              this.orgUuid='';
              this.areaUuid='';
              this.brCardQuery={}
              this.SerachChaX();
              this.$refs.FormRef.resetFields();
            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                this.getBrCardList();
            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                this.getBrCardList();
            }
        }
    }
</script>
<style scoped>
    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>
