<!-- - - - - - - - - - - - -->
<!--        制卡管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <!--卡片视图区域-->
        <el-card class="k-card">
            <el-form :inline="true" :model="ZhiKaQuery" class="demo-form-inline" size="mini">
                <el-form-item label="卡号前缀" prop="cardPrefix">
                    <el-input v-model="ZhiKaQuery.cardPrefix" placeholder="卡号前缀" style="width: 100px"></el-input>
                </el-form-item>
                <el-form-item label="开始卡号" prop="cardNoBeg">
                    <el-input v-model="ZhiKaQuery.cardNoBeg" placeholder="开始卡号" style="width: 100px"></el-input>
                </el-form-item>
                <el-form-item label="结束卡号" prop="cardNoEnd">
                    <el-input v-model="ZhiKaQuery.cardNoEnd" placeholder="结束卡号" style="width: 100px"></el-input>
                </el-form-item>

                <el-form-item label="类型">
                    <el-select v-model="ZhiKaQuery.cardType" placeholder="请选择" style="width: 100px" clearable>
                        <el-option
                                v-for="item in options1"
                                :key="item.id"
                                :label="item.label"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="是否导入">
                    <el-select  placeholder="请选择" style="width: 100px" clearable>
                        <el-option
                                v-for="item in options"
                                :key="item.id"
                                :label="item.label"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>


                <el-form-item>
                    <el-button type="primary" @click="SerachX">查询</el-button>
                </el-form-item>
            </el-form>

            <el-button type="primary" size="mini" icon="el-icon-plus" @click="addZhiKa=true" >增加</el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit" @click="editZhiKa" >编辑</el-button>
            <el-button type="danger" size="mini"  icon="el-icon-delete" @click="removeId">删除</el-button>


           <!-- 制卡管理数据-->
            <el-table
                    size="mini"
                    ref="multipleTable"
                    :data="tableData1"
                    tooltip-effect="dark"
                    style="width: 100%"
                    max-height="421"
                    @selection-change="handleSelectionChange">
                <el-table-column
                        type="selection"
                        width="60">
                </el-table-column>
                <el-table-column
                        prop="cardPrefix"
                        label="卡号前缀"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="cardNoBeg"
                        label="开始卡号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="cardNoEnd"
                        label="结束卡号"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="cardNum"
                        label="共计张"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="remainCardNum"
                        label="剩余张"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="sysName"
                        label="录卡人"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="cardTypeName"
                        label="类型"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="cardCompanyName"
                        label="卡商"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="makeDate"
                        label="制卡时间"
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


           <!-- 添加制卡 或 编辑制卡-->
            <el-dialog :title="form.cardStoreUuid == null ? '增加制卡管理':'编辑制卡管理'" :visible.sync="addZhiKa" @close="DialogClosed">
                <fieldset style="border:solid 1px #aaa;padding: 1px" >
                    <legend>基本信息</legend>
                    <el-form :model="form" size="mini" :inline="true"
                             :rules="FormRules" ref="FormRef">

                        <el-form-item label="卡号前缀:"   :label-width="formLabelWidth" prop="cardPrefix">
                            <el-input v-model="form.cardPrefix" autocomplete="off" style="width: 150px"></el-input>
                        </el-form-item>

                        <el-form-item label="开始卡号:" :label-width="formLabelWidth" prop="cardNoBeg">
                            <el-input v-model="form.cardNoBeg" autocomplete="off" style="width: 150px"></el-input>
                        </el-form-item>

                        <el-form-item label="结束卡号:" :label-width="formLabelWidth" prop="cardNoEnd">
                            <el-input v-model="form.cardNoEnd" autocomplete="off" style="width: 150px"></el-input>
                        </el-form-item>

                        <el-form-item label="共计张:" :label-width="formLabelWidth" prop="cardNum">
                            <el-input readonly v-model="form.cardNum" autocomplete="off" style="width: 150px"></el-input>
                        </el-form-item>


                        <el-form-item label="类型:" :label-width="formLabelWidth" prop="cardType">
                            <el-select v-model="form.cardType" placeholder="--请选择--" style="width: 150px">
                                <el-option
                                        v-for="item in options1"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="卡商:" :label-width="formLabelWidth" prop="cardCompany">
                            <el-select v-model="form.cardCompany" placeholder="--请选择--" style="width: 150px">
                                <el-option
                                        v-for="item in options2"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="制卡时间:" :label-width="formLabelWidth" prop="makeDate">
                            <el-date-picker style="width: 140px"
                                            v-model="form.makeDate"
                                            type="datetime"
                                            placeholder="选择开始时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="有效期:" prop="validDate">
                            <el-date-picker style="width: 158px"
                                            v-model="form.validDate"
                                            type="datetime"
                                            placeholder="选择截止时间"
                                            value-format="yyyy-MM-dd HH:mm:ss"
                                            default-time="00:00:00"/>
                        </el-form-item>

                        <el-form-item label="备注:" :label-width="formLabelWidth" prop="remark">
                            <el-input v-model="form.remark" type="textarea" style="width: 400px"></el-input>
                        </el-form-item>
                    </el-form>
                </fieldset>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="addZhiKa = false">取 消</el-button>
                    <el-button type="primary" @click="saveKa">确 定</el-button>
                </div>
            </el-dialog>

            <!--发卡-->
            <el-dialog title="增加发卡管理" :visible.sync="addFaKa" width="60%">
                <fieldset style="border:solid 1px #aaa;padding: 3px">
                    <legend>基本信息</legend>
                    <el-form :model="form" size="mini" :inline="true">
                        <el-form-item label="批次:" :label-width="formLabelWidth1">
                            <el-input v-model="form.lastname" autocomplete="off" style="width: 300px"></el-input>
                        </el-form-item>
                        <br>
                        <el-form-item label="张数:" :label-width="formLabelWidth1">
                            <el-input v-model="form.lastname" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>
                        <el-form-item label="开始卡号:" :label-width="formLabelWidth1">
                            <el-input v-model="form.lastname" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>
                        <el-form-item label="结束卡号:" :label-width="formLabelWidth1">
                            <el-input v-model="form.lastname" autocomplete="off" style="width: 100px"></el-input>
                        </el-form-item>


                        <el-form-item label="类型:" :label-width="formLabelWidth1">
                            <el-select v-model="options.id" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="大区:" :label-width="formLabelWidth1">
                            <el-select v-model="options.id" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="区域:" :label-width="formLabelWidth1">
                            <el-select v-model="options.id" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="领卡门店:" :label-width="formLabelWidth1">
                            <el-select v-model="options.id" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="领卡人:" :label-width="formLabelWidth1">
                            <el-select v-model="options.id" placeholder="--请选择--" style="width: 100px">
                                <el-option
                                        v-for="item in options"
                                        :key="item.id"
                                        :label="item.label"
                                        :value="item.id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <br>

                        <el-form-item label="备注:" :label-width="formLabelWidth1">
                            <el-input type="textarea" style="width: 400px"></el-input>
                        </el-form-item>
                    </el-form>
                </fieldset>

                <div slot="footer" class="dialog-footer">
                    <el-button @click="addFaKa = false">取 消</el-button>
                    <el-button type="primary" >确 定</el-button>
                </div>
            </el-dialog>

            <!--卡池-->
            <el-dialog title="发卡记录" :visible.sync="KaChi">
                <el-tabs v-model="activeName" type="card" >
                    <el-tab-pane label="发卡信息" name="first">
                        <el-table
                                ref="multipleTable"
                                :data="tableData"
                                tooltip-effect="dark"
                                style="width: 100%">
                            <el-table-column
                                    label="日期"
                                    width="120">
                                <template slot-scope="scope">{{ scope.row.date }}</template>
                            </el-table-column>
                            <el-table-column
                                    prop="name"
                                    label="姓名"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="地址"
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
                    </el-tab-pane>

                    <el-tab-pane label="门店领卡信息" name="second">
                        <el-table
                                ref="multipleTable"
                                :data="tableData"
                                tooltip-effect="dark"
                                style="width: 100%">
                            <el-table-column
                                    label="日期"
                                    width="120">
                                <template slot-scope="scope">{{ scope.row.date }}</template>
                            </el-table-column>
                            <el-table-column
                                    prop="name"
                                    label="姓名"
                                    width="120">
                            </el-table-column>
                            <el-table-column
                                    prop="address"
                                    label="地址"
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
                    </el-tab-pane>

                    <el-tab-pane label="发卡明细" name="third">
                        <!--卡片视图区域-->
                        <el-card class="k-card">
                            <el-form :inline="true"  class="demo-form-inline" size="mini">
                                <div style="margin-left: -100px">
                                    <el-form-item label="大区" :label-width="formLabelWidth">
                                        <el-select  placeholder="请选择" style="width: 100px">
                                            <el-option
                                                    v-for="item in options"
                                                    :key="item.id"
                                                    :label="item.label"
                                                    :value="item.id">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>

                                    <el-form-item label="区域" :label-width="formLabelWidth">
                                        <el-select  placeholder="请选择" style="width: 100px">
                                            <el-option
                                                    v-for="item in options"
                                                    :key="item.id"
                                                    :label="item.label"
                                                    :value="item.id">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>

                                    <el-form-item label="门店" :label-width="formLabelWidth">
                                        <el-select  placeholder="请选择" style="width: 100px">
                                            <el-option
                                                    v-for="item in options"
                                                    :key="item.id"
                                                    :label="item.label"
                                                    :value="item.id">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>


                                    <el-form-item label="卡号" :label-width="formLabelWidth">
                                        <el-input  placeholder="卡号" style="width: 100px"></el-input>
                                    </el-form-item>


                                    <el-form-item label="是否已经发放给客户" :label-width="formLabelWidth">
                                        <el-select  placeholder="请选择" style="width: 100px">
                                            <el-option
                                                    v-for="item in options"
                                                    :key="item.id"
                                                    :label="item.label"
                                                    :value="item.id">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>


                                    <el-form-item >
                                        <el-button type="primary" >查询</el-button>
                                    </el-form-item>
                                </div>
                            </el-form>
                            <el-table
                                    ref="multipleTable"
                                    :data="tableData1"
                                    tooltip-effect="dark"
                                    style="width: 100%"
                                    max-height="350">

                                <el-table-column
                                        prop="cardPrefix"
                                        label="卡号前缀"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="cardNoBeg"
                                        label="开始卡号"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="cardNoEnd"
                                        label="结束卡号"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="cardNum"
                                        label="共计张"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="remainCardNum"
                                        label="剩余张"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="sysName"
                                        label="录卡人"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="cardTypeName"
                                        label="类型"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="cardCompanyName"
                                        label="卡商"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="makeDate"
                                        label="制卡时间"
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
                        </el-card>
                    </el-tab-pane>
                </el-tabs>

            </el-dialog>

        </el-card>
    </div>
</template>

<script>
    export default {
        name: "index",
        data(){
            var validateEnd = (rule, value, callback) => {
                if(this.form.cardNoBeg){
                    if(Number(this.form.cardNoBeg) < Number(value)){
                        callback();
                    }else{
                        callback(new Error('结束开号大于开始开号'));
                    }
                }else{
                    callback();
                }
            };
            var validateEnd1 = (rule, value, callback) => {
                if(this.form.cardNoBeg){
                    if(Number(this.form.cardNoBeg) < Number(value)){
                        this.form.cardNum=Number(this.form.cardNoEnd);
                        callback();
                    }else{
                        callback(new Error('结束开号大于开始开号'));
                    }
                }else{
                    callback();
                }
            };
            return{
                //验证规则
                FormRules:{
                    cardPrefix: [
                        {required: true, message: '请输入卡号前缀', trigger: 'blur'},
                    ],
                    cardNoBeg: [
                        {required: true, message: '请输入开始卡号', trigger: 'blur'},
                        {
                            validator(rule, value, callback) {
                                var reg = /^-?\d{1,5}(?:\.\d{1,3})?$/
                                if (reg.test(value)) {
                                    callback()
                                } else {
                                    callback(new Error('请输入数字'))
                                }
                            },
                            trigger: 'blur'
                        }
                    ],
                    cardNoEnd: [
                        {required: true, message: '请输入结束卡号', trigger: 'blur'},
                        {
                            validator(rule, value, callback) {
                                var reg = /^-?\d{1,5}(?:\.\d{1,3})?$/
                                if (reg.test(value)) {
                                    callback()
                                } else {
                                    callback(new Error('请输入数字'))
                                }
                            },
                            trigger: 'blur'
                        },
                        { validator: validateEnd, trigger: 'blur' },
                        { validator: validateEnd1, trigger: 'blur' },
                    ],
                    cardType: [
                        {required: true, message: '请选择类型'},
                    ],
                    cardCompany: [
                        {required: true, message: '请选择卡商'},
                    ],
                    makeDate: [
                        {required: true, message: '请选择制卡时间'},
                    ],
                    validDate: [
                        {required: true, message: '请选择有效时间'},
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
                activeName: 'first',
                //是否导入
                options: [
                    {id: 'Y', label: '是'},
                    {id: 'N', label: '否'}
                    ],
                //类型
                options1: [
                    {id: 'platinum', label: '白金卡'},
                    {id: 'gold', label: '金卡'},
                    {id: 'silver', label: '银卡'},
                    {id: 'general', label: '普通卡'}
                ],
                //卡商
                options2: [
                    {id: 'suremoov', label: '小拇指卡'}
                ],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 100, 200, 300, 400],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
                //表单数据
                form:{},
                //卡池
                KaChi:false,
                //添加制卡
                addZhiKa:false,
                //发卡
                addFaKa:false,
                // 表单元素的label的宽度
                formLabelWidth: '141px',
                // 表单元素的label的宽度
                formLabelWidth2: '50px',
                formLabelWidth1: '100px',
                //制卡管理分页数据
                tableData1: [],
                //制卡管理分页查询条件
                ZhiKaQuery:{},
                //当前时间
                currentTime:'',
                //所有制卡管理数据
                allDate:[],
                //制卡管理选中的id
                selectId:[],
            }
        },
        created() {
            //分页条件查询制卡管理
            this.getZhiKaList();
            this.mounted();
            this.getAllList();
        },
        methods:{
            mounted(){
                this.currentTime=this.format(new Date(),"yyyy-MM-dd HH:mm:ss");
            },
            //点击查询
            SerachX(){
                //分页条件查询制卡管理
                this.getZhiKaList();
            },
            //分页条件查询制卡管理
            getZhiKaList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-card-store/queryAllZhiKa/${this.current}/${this.limit}`,_this.ZhiKaQuery).then(result=>{
                    _this.tableData1=result.data.rows;
                    _this.total=result.data.total;
                })
            },
            //查询所有制卡管理
            getAllList(){
                const _this = this
                _this.$axios.post(`/serviceCard/acct-card-store/queryAll`).then(result=>{
                    _this.allDate=result.data.list;

                })
            },
            //根据id删除
            removeId(){
                if(this.selectId.length===0){
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
                        _this.$axios.delete(`/serviceCard/acct-card-store/delete/${this.selectId[i]}`).then(result=>{
                            if(result.code===20000){
                                this.$message({
                                    type: 'success',
                                    message: '删除成功!'
                                });
                                //刷新 分页条件查询制卡管理
                                this.getZhiKaList();
                            }

                        })

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

            },
            //打开编辑对话框
            editZhiKa(){
                if(this.selectId.length===0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.selectId.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                const _this = this
                _this.$axios.post(`/serviceCard/acct-card-store/queryById/${this.selectId[0]}`).then(result=>{
                    _this.form=result.data.acctCardStore;

                    //打开修改对话框
                    this.addZhiKa = true
                })

            },
            //添加或修改制卡管理
            saveKa(){
                this.$refs.FormRef.validate(valid => {
                    if (!valid) return;

                 if(this.form.cardStoreUuid==null){
                     for(var i=0;i<this.allDate.length;i++){
                         if(this.form.cardPrefix===this.allDate[i].cardPrefix&&this.form.cardNoBeg===this.allDate[i].cardNoBeg&&this.form.cardNoEnd===this.allDate[i].cardNoEnd){
                             this.$message({
                                 type:'success',
                                 message:'部分卡号在卡号区间已存在!',
                             });
                             return;
                         }
                     }
                    //剩余张=共计张
                    this.form.remainCardNum=this.form.cardNum;
                    //录卡人
                    this.form.saUuid=this.$store.getters.userInfo.id;
                    //版本
                    this.form.version=0;
                    //是否有效
                    this.form.isActive='Y';
                    //是否导入
                    this.form.impFlag='Y';
                    //创建人
                    this.form.createdBy=this.$store.getters.userInfo.name;
                    //创建时间
                    this.form.createdDate=this.currentTime;

                    const _this = this
                    _this.$axios.post('/serviceCard/acct-card-store/add',_this.form).then(result=>{
                        if(result.code===20000){
                            this.$message({
                                type:'success',
                                message:'添加成功!',
                            });
                        }
                        //刷新 分页条件查询制卡管理
                        this.getZhiKaList();
                        //清空数据
                        this.form={};
                        //添加完成关闭对话框
                        this.addZhiKa=false;
                    })
                }else if(this.form.cardStoreUuid!=null){
                     //剩余张=共计张
                     this.form.remainCardNum=this.form.cardNum;
                     //修改人
                     this.form.updatedBy=this.$store.getters.userInfo.name;
                     //修改时间
                     this.form.updatedDate=this.currentTime;
                     //版本
                     this.form.version=1;
                    const _this = this
                    _this.$axios.post('/serviceCard/acct-card-store/update',_this.form).then(result=>{
                        if(result.code===20000){
                            this.$message({
                                type:'success',
                                message:'修改成功!',
                            });
                        }
                        //刷新 分页条件查询制卡管理
                        this.getZhiKaList();
                        //清空数据
                        this.form={};
                        //添加完成关闭对话框
                        this.addZhiKa=false;
                    })
                }
                })

            },
            //监听关闭对话框
            DialogClosed(){
                this.$refs.FormRef.resetFields();
            },
            //制卡选择事件
            handleSelectionChange(val){
                //清空
                this.selectId=[]
                //去重获取被选中的id值存入到数组中
                for (let selectedItem of val) {
                    this.selectId.push(selectedItem.cardStoreUuid)
                }
            },
            // 页大小改变事件
            handleSizeChange (val) {
                this.limit = val
                //分页条件查询制卡管理
                this.getZhiKaList();

            },
            // 页号改变事件
            handleCurrentChange (val) {
                this.current = val
                //分页条件查询制卡管理
                this.getZhiKaList();
            }
        }
    }
</script>
<style scoped>
    .k-card {
        box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
    }
</style>