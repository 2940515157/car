<!-- - - - - - - - - - - - -->
<!--        员工管理        -->
<!-- - - - - - - - - - - - -->

<template>
    <div>
        <h3>员工管理1</h3>
        <el-form ref="form" :inline="true" :model="EmployeesQuery" size="mini" >
 <!--           <el-form-item label="大区：" >
                <el-select v-model="form.null" placeholder="&#45;&#45;请选择&#45;&#45;" style="width: 100px">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="区域：" >
                <el-select v-model="form.STATUS" placeholder="&#45;&#45;请选择&#45;&#45;" style="width: 100px">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="门店：" >
                <el-select v-model="form.STATUS" placeholder="&#45;&#45;请选择&#45;&#45;" style="width: 100px">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>-->
            <el-form-item label="员工类别：" >
            <el-select v-model="EmployeesQuery.empType" placeholder="--请选择--" style="width: 100px">
                <el-option label="--请选择--" value=""></el-option>
                <el-option label="总部员工" value="headquarters"></el-option>
                <el-option label="SA" value="sa"></el-option>
                <el-option label="店长" value="manager"></el-option>
                <el-option label="技师" value="technician"></el-option>
                <el-option label="门店后勤" value="logistics"></el-option>
                <el-option label="业主" value="owner"></el-option>
                <el-option label="门店出纳" value="cashier"></el-option>

            </el-select>
        </el-form-item>
            <el-form-item label="员工状态：" >
                <el-select v-model="EmployeesQuery.status" placeholder="--请选择--" style="width: 100px">
                    <el-option label="--请选择--" value=""></el-option>
                    <el-option label="在职" value="jobing"></el-option>
                    <el-option label="离职" value="leaveJob"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="姓名:">
                <el-input v-model="EmployeesQuery.empName" style="width: 100px"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="seEmp">查询</el-button>
            </el-form-item>


        </el-form>

        <el-button type="primary"  @click="dialogFormVisible=true,form = {}">添加</el-button>
        <el-button type="primary"   @click="update">编辑</el-button>
        <el-button type="primary"   @click="update1">设置员工离职</el-button>
        <el-button type="primary"   @click="update2">撤销员工离职</el-button>
       <!--表格 -->
        <el-table
                height="400px"
                ref="multipleTable"
                :data="employeesList"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">

            <el-table-column
                    type="index"
                    width="50">
            </el-table-column>
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
           <!-- <el-table-column
                    label=" "
                    width="80" prop="empUuid" v-if="values">
            </el-table-column>-->
            <el-table-column
                    label="姓名"
                    width="80" prop="empName">
            </el-table-column>
            <el-table-column
                    label="电话"
                    width="120" prop="empPhone">
            </el-table-column>
            <el-table-column
                    label="性别"
                    width="120">
                <template slot-scope="scope">
                    <span v-if="scope.row.empSex=='M'">男</span>
                    <span v-if="scope.row.empSex=='F'">女</span>
                </template>
            </el-table-column>

            <el-table-column
                    width=""
                    label="员工状态">
                <template slot-scope="scope">
                    <span v-if="scope.row.status=='jobing'">在职</span>
                    <span v-if="scope.row.status=='leaveJob'">离职</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="门店名称"
                    width="120" >
                <template slot-scope="scope">
                    ({{scope.row.brCode}}){{scope.row.brName}}
                </template>
            </el-table-column>
            <el-table-column
                    label="部门名称"
                    width="120" prop="orgName">
            </el-table-column>
            <el-table-column
                    label="员工类别"
                    width="120">
                <template slot-scope="scope">
                    <span v-if="scope.row.empType=='headquarters'">总部员工</span>
                    <span v-if="scope.row.empType=='sa'">SA</span>
                    <span v-if="scope.row.empType=='manager'">店长</span>
                    <span v-if="scope.row.empType=='technician'">技师</span>
                    <span v-if="scope.row.empType=='logistics'">门店后勤</span>
                    <span v-if="scope.row.empType=='owner'">业主</span>
                    <span v-if="scope.row.empType=='cashier'">门店出纳</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="签约时间"
                    width="120" prop="createdDate">
            </el-table-column>
            <el-table-column
                    label="生日"
                    width="120" prop="empBirthday">
            </el-table-column>
            <el-table-column
                    label="email"
                    width="120" prop="empEmail">
            </el-table-column>
            <el-table-column
                    label="员工编号"
                    width="120" prop="empCode">
            </el-table-column>
            <el-table-column
                    label="备注"
                    width="120" prop="remark">
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="current"
                :page-sizes="pageSizeList"
                :page-size="limit"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
        </el-pagination>
        <!--添加or修改-->
        <div>
            <el-dialog :title="form.empUuid == null ? '新增品牌信息':'编辑品牌信息'"  :visible.sync="dialogFormVisible">
                <fieldset>
                    <legend>基本信息</legend><br>
                    姓名：<el-input v-model="form.empName" style="width: 150px" size="mini"></el-input>
                    性别：<el-select v-model="form.empSex" placeholder="--请选择--" style="width: 150px">
                    <el-option label="男" value="F"></el-option>
                    <el-option label="女" value="M"></el-option>
                </el-select>
                    电话：<el-input v-model="form.empPhone" style="width: 150px" size="mini"></el-input><br>
                   员工类别： <el-select v-model="form.empType" placeholder="--请选择--" style="width: 150px">
                    <el-option label="总部员工" value="headquarters"></el-option>
                    <el-option label="SA" value="sa"></el-option>
                    <el-option label="店长" value="manager"></el-option>
                    <el-option label="技师" value="technician"></el-option>
                    <el-option label="门店后勤" value="logistics"></el-option>
                    <el-option label="业主" value="owner"></el-option>
                    <el-option label="门店出纳" value="cashier"></el-option>
                    </el-select>
                    职位：<el-input v-model="form.empPost" style="width: 150px" size="mini"></el-input>
                    学历： <el-select v-model="form.eduBackground" placeholder="--请选择--" style="width: 150px">
                    <el-option label="初中" value="juniorHighSchool"></el-option>
                    <el-option label="小学" value="primarySchool"></el-option>
                    <el-option label="高中" value="trainingSchool"></el-option>
                    <el-option label="大学" value="university"></el-option>
                    <el-option label="研究生" value="graduateStudents"></el-option>
                </el-select><br>
                    证件类型：<el-select v-model="form.cardTyep" placeholder="--请选择--" style="width: 150px">
                    <el-option label="身份证" value="cardId"></el-option>
                    <el-option label="士兵证" value="cardSoldiers"></el-option>
                    <el-option label="驾驶证" value="driverLicense"></el-option>
                    <el-option label="军官证" value="militaryOfficer"></el-option>
                    <el-option label="护照" value="passport"></el-option>
                    <el-option label="其他" value="other"></el-option>

                </el-select>
                    证件号：<el-input v-model="form.cardNo" style="width: 150px" size="mini"></el-input>
                    门店名称：<el-select v-model="form.brUuid" placeholder="--请选择--" style="width: 150px">
                    <el-option
                            v-for="(item, $index) in boption"
                            :key="$index"
                            :label="item.brName"
                            :value="item.brCode">
                    </el-option>
                </el-select><br>
                    部门名称：<el-input v-model="form.name" style="width: 150px" size="mini"></el-input>

                        生日：<el-date-picker
                            v-model="form.time"
                            type="date"
                            placeholder="选择日期" style="width: 150px">
                    </el-date-picker>
                    email：<el-input v-model="form.name" style="width: 150px" size="mini"></el-input><br>

                    备注：<el-input v-model="form.sty" type="textarea"
                                 :rows="3"></el-input>

                </fieldset>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>


</template>

<script>
    export default {
        created() {
            //加载表格数据
            this.getEmployees()
            this.getbOptions()
        },
        data() {
            return {
                //关闭添加修改页面
                dialogFormVisible:false,
                form :{},
                //保存表格数据
                employeesList : [],
                // 保存查询条件的对象
                EmployeesQuery: {},
                //下拉框
                boption:[],
                //选中的id
                selectId:[],
                //
                multipleSelection:[],
                // 当前页号
                current: 1,
                // 分页列表
                pageSizeList: [2, 5, 10, 20, 50, 100],
                // 页大小
                limit: 5,
                // 总记录数
                total: 0,
            }
        },
        methods:{
            //条件查询
            seEmp(){
                this.getEmployees()
            },
            // 获取表格数据
            async getEmployees(){
                // 获取所有点检项目的方法
                const _this = this
                const response = await _this.$axios.post(`/serviceHr/employees/pageQuery/${this.current}/${this.limit}`,
                    this.EmployeesQuery)
                console.log(response.data)
                this.employeesList = response.data.rows
                this.total = response.data.total
            },
            //获取下拉框
            async getbOptions(){
                const response = await this.$axios.get(`/serviceHr/hr-branches/queryAll`)
                this.boption= response.data.list;
                console.log(this.boption)
            },
            //更新
            async update(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                if(this.multipleSelection.length>1){
                    this.$message({
                        type:'info',
                        message:'只能选择一条数据'
                    })
                    return
                }
                this.dialogFormVisible=true
                // 获取要修改的门票编号
                console.log(this.selectId)
                const response = await this.$axios.get(`/serviceHr/employees/queryById/${this.selectId}`)
                this.form = response.data.hrEmployees
            },

            // 确认按钮点击事件
            async save(){
                // 添加或者修改操作
                const response = await this.$axios.post(`/serviceHr/employees/${this.form.empUuid == null ? 'addHrEmp' : 'updateHrEmp'}`, this.form)
                if (response.code == 20000) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    this.dialogFormVisible = false
                    // 刷新数据表格
                    this.getEmployees()
                }
            },

            //获取选中id
            handleSelectionChange(val) {
                this.multipleSelection=val;
                this.selectId=[]
                for(var i=0;i<val.length;i++){
                    this.selectId.push(val[i].empUuid);
                    console.log(this.selectId)
                }
            },
            //设置员工离职
            async update1(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                // 获取编号
                console.log(this.selectId)
                // eslint-disable-next-line no-unused-vars
                const _this = this
                for (let i=0;i<this.selectId.length;i++){
                    // eslint-disable-next-line no-unused-vars
                    const response = await _this.$axios.post(`/serviceHr/employees/setStatus`,
                        {'empUuid':this.selectId[i],'status':'leaveJob'})
                }
                this.seEmp()
            },
            //撤销员工离职
            async update2(){
                if(this.multipleSelection.length==0){
                    this.$message({
                        type:'info',
                        message:'至少选择一条数据'
                    })
                    return
                }
                // 获取编号
                console.log(this.selectId)

                // eslint-disable-next-line no-unused-vars
                const _this = this
                for (let i=0;i<this.selectId.length;i++){
                    // eslint-disable-next-line no-unused-vars
                    const response = await _this.$axios.post(`/serviceHr/employees/setStatus`,
                        {'empUuid':this.selectId[i],'status': 'jobing'})
                }
                this.seEmp()
            },


            // 页大小改变事件
            handleSizeChange (val) {
                console.log(`每页 ${val} 条`)
                this.limit = val
                this.getEmployees()
            },
            // 页号改变事件
            handleCurrentChange (val) {
                console.log(`当前页: ${val}`)
                this.current = val
                // 查询
                this.getEmployees()
            },
        }
    }

</script>

<style scoped>

</style>
