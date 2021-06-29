<template>
    <div>
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <!-- 国家工具栏 -->
                <td>
                    <div id="entryTool">
                        <el-button type="primary" size="mini" @click="countryadd" class="el-icon-plus">增加</el-button>
                        <el-button type="warning" size="mini" @click="countryedit" class="el-icon-edit">修改</el-button>
                        <el-button type="danger" size="mini" @click="countrydel" class="el-icon-delete">删除</el-button>
                    </div>
                </td>
                <!-- 省工具栏 -->
                <td>
                    <div id="entryTool2">
                        <el-button type="primary" size="mini" @click="provinceadd" class="el-icon-plus">增加</el-button>
                        <el-button type="warning" size="mini" @click="provinceedit" class="el-icon-edit">修改</el-button>
                        <el-button type="danger" size="mini" @click="provincedel" class="el-icon-delete">删除</el-button>
                    </div>
                </td>
                <!-- 市具栏 -->
                <td>
                    <div id="entryTool3">
                        <el-button type="primary" size="mini" @click="cityadd" class="el-icon-plus">增加</el-button>
                        <el-button type="warning" size="mini" @click="cityedit" class="el-icon-edit">修改</el-button>
                        <el-button type="danger" size="mini" @click="citydel" class="el-icon-delete">删除</el-button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <!--国家信息-->
                    <el-table
                            :data="countryData"
                            style="width: 100%;"
                            max-height="350"
                            highlight-current-row
                            @current-change="countrytableCurrentChange"
                            ref="countrymultipleTable"
                            @row-click="countrytableClick">
                        <el-table-column
                                type="selection"
                                width="70">
                        </el-table-column>
                        <el-table-column
                                type="index"
                                width="70">
                        </el-table-column>
                        <el-table-column
                                prop="countryName"
                                label="国家名称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="countryCode"
                                label="国家代码"
                                width="120">
                        </el-table-column>
                    </el-table>
                    <!-- 国家新增或编辑模态框-->
                    <el-dialog :title="countryUuid == '' ? '新增国家信息':'编辑国家信息'" :visible.sync="countrydialogFormVisible">
                        <el-form :model="countryform" label-width="100px">
                            <fieldset>
                                <legend>基本信息</legend>
                                <div >
                                    <table>
                                        <tr>
                                            <td>
                                                <label>国家名称</label>
                                                    <el-input style="width: 180px" v-model="countryform.countryName"></el-input>
                                                <label>国家代码</label>
                                                    <el-input style="width: 180px" v-model="countryform.countryCode"></el-input>

                                            </td>
                                        </tr>
                                    </table>
                                </div>

                            </fieldset>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="countrydialogFormVisible = false,countryform={}">取 消</el-button>
                            <el-button type="primary" @click="countrysave">确 定</el-button>
                        </div>
                    </el-dialog>
                </td>
                <td>
                    <!--省信息-->
                    <el-table
                            :data="provinceData"
                            style="width: 100%"
                            max-height="350"
                            highlight-current-row
                            @current-change="provincetableCurrentChange"
                            ref="provincemultipleTable"
                            @row-click="provincetableClick">
                        <el-table-column
                                type="selection"
                                width="70">
                        </el-table-column>
                        <el-table-column
                                type="index"
                                width="70">
                        </el-table-column>
                        <el-table-column
                                prop="provName"
                                label="省名称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="provCode"
                                label="省代码"
                                width="120">
                        </el-table-column>
                    </el-table>
                    <!-- 省新增或编辑模态框-->
                    <el-dialog :title="provinceUuid == '' ? '新增省信息':'编辑省信息'" :visible.sync="provincedialogFormVisible">

                        <el-form :model="provinceform" label-width="100px">
                            <fieldset>
                                <legend>基本信息</legend>
                                <div >
                                    <table>
                                        <tr>
                                            <td>
                                                <label>省名称</label>
                                                    <el-input v-model="provinceform.provName"></el-input>

                                                <label>省代码</label>
                                                    <el-input v-model="provinceform.provCode"></el-input>

                                            </td>
                                        </tr>
                                    </table>
                                </div>

                            </fieldset>
                        </el-form>

                        <div slot="footer" class="dialog-footer">
                            <el-button @click="provincedialogFormVisible = false,provinceform={}">取 消</el-button>
                            <el-button type="primary" @click="provincesave">确 定</el-button>
                        </div>
                    </el-dialog>
                </td>
                <td>
                    <!--市信息-->
                    <el-table
                            :data="cityData"
                            style="width: 100%"
                            max-height="350"

                            highlight-current-row
                            @current-change="citytableCurrentChange"
                            ref="citymultipleTable"
                            @row-click="citytableClick">
                        <el-table-column
                                type="selection"
                                width="70">
                        </el-table-column>
                        <el-table-column
                                type="index"
                                width="70">
                        </el-table-column>
                        <el-table-column
                                prop="cityName"
                                label="市名称"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="cityCode"
                                label="市代码"
                                width="120">
                        </el-table-column>
                    </el-table>
                    <!-- 市新增或编辑模态框-->
                    <el-dialog :title="cityUuid == '' ? '新增市信息':'编辑市信息'" :visible.sync="citydialogFormVisible">

                        <el-form :model="cityform" label-width="100px">
                            <fieldset>
                                <legend>基本信息</legend>
                                <div >
                                    <table>
                                        <tr>
                                            <td>
                                                <label>市名称</label>
                                                <el-input v-model="cityform.cityName"></el-input>

                                                <label>市代码</label>
                                                <el-input v-model="cityform.cityCode"></el-input>

                                            </td>
                                        </tr>
                                    </table>
                                </div>

                            </fieldset>
                        </el-form>

                        <div slot="footer" class="dialog-footer">
                            <el-button @click="citydialogFormVisible = false,cityform={}">取 消</el-button>
                            <el-button type="primary" @click="citysave">确 定</el-button>
                        </div>
                    </el-dialog>
                </td>
            </tr>
            <tr>
                <!--国家分页-->
                <td><el-pagination
                        @size-change="countrysizeChange"
                        @current-change="countrycurrentChange"
                        :current-page="countrycurrent"
                        :page-sizes="countrypageSizeList"
                        :page-size="countrysize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="countrytotal">
                </el-pagination></td>
                <!--省分页-->
                <td><el-pagination
                        @size-change="provincesizeChange"
                        @current-change="provincecurrentChange"
                        :current-page="provincecurrent"
                        :page-sizes="provincepageSizeList"
                        :page-size="provincesize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="provincetotal">
                </el-pagination></td>
                <!--市分页-->
                <td><el-pagination
                        @size-change="citysizeChange"
                        @current-change="citycurrentChange"
                        :current-page="citycurrent"
                        :page-sizes="citypageSizeList"
                        :page-size="citysize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="citytotal">
                </el-pagination></td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        data(){
            return{
                //保存数据表格的变量
                countryData:[],
                provinceData:[],
                cityData:[],
                //当前页号
                countrycurrent: 1,
                provincecurrent: 1,
                citycurrent: 1,
                //分页列表
                countrypageSizeList: [10, 50, 100, 200, 300],
                provincepageSizeList: [10, 50, 100, 200, 300],
                citypageSizeList: [10, 50, 100, 200, 300],
                //页大小
                countrysize: 10,
                provincesize: 10,
                citysize: 10,
                //总记录数
                countrytotal: 0,
                provincetotal: 0,
                citytotal: 0,
                //封装表单数据的对象
                countryform: {},
                provinceform: {},
                cityform: {},

                countryUuid: '',
                provinceUuid: '',
                cityUuid: '',
                //是否显示对话框
                countrydialogFormVisible: false,
                provincedialogFormVisible: false,
                citydialogFormVisible: false,
                // 表单元素的label的宽度
                formLabelWidth: '50'
            }
        },
        methods: {
            //国家表格数据
            async getcountryData() {
               const result = await this.$axios.post(`/serviceBasedata/baseCountrys/page/${this.countrycurrent}/${this.countrysize}`)
               this.countryData = result.data.rows
               this.countrytotal = result.data.total
           },
            //国家添加
            countryadd() {
               this.countryUuid = ''
               this.countryform = {}

               this.countrydialogFormVisible = true
               this.$refs.countrymultipleTable.clearSelection();
           },
            //国家修改
            async countryedit() {
               if (this.countryUuid == '') {
                   alert("请选中一行记录")
                   return
               }
               const result = await this.$axios.get(`/serviceBasedata/baseCountrys/queryById/${this.countryUuid}`)
               this.countryform = result.data.baseCountrys
               this.countrydialogFormVisible = true


           },
            //国家修改或保存
            async countrysave() {
               // eslint-disable-next-line no-unused-vars
               let formEntry = {};

               this.countrydialogFormVisible = false
               if (this.countryUuid == '') {
                   formEntry = this.countryform
               } else {
                   formEntry = {
                       countryName: this.countryform.countryName, countryCode: this.countryform.countryCode, countryUuid: this.countryUuid
                   }
               }
               //添加或者修改操作
               const result = await this.$axios.post(`/serviceBasedata/baseCountrys/${this.countryUuid == '' ? 'add' : 'update'}`, formEntry)
               if (result.flag) {
                   // 提示消息
                   this.$message({
                       type: 'success',
                       message: '成功!'
                   })
                   // 刷新数据表格
                   await this.getcountryData()

               }
           },
            //国家删除
            countrydel() {
               this.$confirm('将删除此数据, 是否继续?', '提示', {
                   confirmButtonText: '确定',
                   cancelButtonText: '取消',
                   type: 'warning'
               }).then(async () => {
                   // 删除操作（物理，逻辑）
                   const result = await this.$axios.delete(`/serviceBasedata/baseCountrys/delete/${this.countryUuid}`)
                   if (result.flag) {
                       // 提示消息
                       this.$message({
                           type: 'success',
                           message: '成功!'
                       })
                       // 刷新数据表格
                       await this.getcountryData()
                       this.$refs.countrymultipleTable.clearSelection();

                   }
               }).catch(() => {
                   this.$message({
                       type: 'info',
                       message: '已取消删除'
                   })
               })
           },

            //国家选中行事件
            countrytableCurrentChange(val){
               this.countryUuid = val.countryUuid;
               this.cityData=[];
               this.getprovinceData();
           },
            //国家行点击事件
            countrytableClick(row){
                this.$refs.countrymultipleTable.clearSelection();
                this.$refs.countrymultipleTable.toggleRowSelection(row);

           },
            //页号改变事件
            countrycurrentChange(val){
                this.countrycurrent= val;
                this.getcountryData();
            },
            //页大小改变事件
            countrysizeChange(val){
               this.countrysize = val;
               this.getcountryData();
           },

            //省表格数据
            async getprovinceData() {
                const result = await this.$axios.post(`/serviceBasedata/baseProvinces/page/${this.countryUuid}/${this.provincecurrent}/${this.provincesize}`)
                this.provinceData = result.data.rows
                this.provincetotal = result.data.total
            },
            //省添加
            provinceadd() {
                this.provinceUuid = ''
                this.provinceform = {}
                this.provinceform.countryUuid=this.countryUuid
                this.provincedialogFormVisible = true
                this.$refs.provincemultipleTable.clearSelection();
            },
            //省修改
            async provinceedit() {
                if (this.provinceUuid == '') {
                    alert("请选中一行记录")
                    return
                }
                const result = await this.$axios.get(`/serviceBasedata/baseProvinces/queryById/${this.countryUuid}/${this.provinceUuid}`)
                this.provinceform = result.data.baseProvinces
                this.provincedialogFormVisible = true


            },
            //省修改或保存
            async provincesave() {
                // eslint-disable-next-line no-unused-vars
                let formEntry = {};

                this.provincedialogFormVisible = false
                if (this.provinceUuid == '') {
                    formEntry = this.provinceform
                } else {
                    formEntry = {
                        provName: this.provinceform.provName, provCode: this.provinceform.provCode, provUuid: this.provinceUuid, countryUuid: this.countryUuid
                    }
                }
                //添加或者修改操作
                const result = await this.$axios.post(`/serviceBasedata/baseProvinces/${this.provinceUuid == '' ? 'add' : 'update'}`, formEntry)
                console.log(formEntry)
                if (result.flag) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })

                    // 刷新数据表格
                    await this.getprovinceData()

                }
            },
            //省删除
            provincedel() {
                this.$confirm('将删除此数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    // 删除操作（物理，逻辑）
                    const result = await this.$axios.delete(`/serviceBasedata/baseProvinces/delete/${this.provinceUuid}`)
                    if (result.flag) {
                        // 提示消息
                        this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        // 刷新数据表格
                        await this.getprovinceData()
                        this.$refs.provincemultipleTable.clearSelection();

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            },
            //省选中行事件
            provincetableCurrentChange(val){
                if(val){
                    this.provinceUuid = val.provUuid;
                    this.getcityData();
                }

            },
            //省行点击事件
            provincetableClick(row){
                this.$refs.provincemultipleTable.clearSelection();
                this.$refs.provincemultipleTable.toggleRowSelection(row);
            },
            //页号改变事件
            provincecurrentChange(val){
                this.provincecurrent = val;
                this.getprovinceData();
            },
            //页大小改变事件
            provincesizeChange(val){
                this.provincesize = val;
                this.getprovinceData();
            },

            //市表格数据
            async getcityData() {
                const result = await this.$axios.post(`/serviceBasedata/baseCitys/page/${this.countryUuid}/${this.provinceUuid}/${this.citycurrent}/${this.citysize}`)
                this.cityData = result.data.rows
                this.citytotal = result.data.total
            },
            //市添加
            cityadd() {
                this.cityUuid = ''
                this.cityform = {}
                this.cityform.this.provinceUuid=this.provinceUuid
                this.citydialogFormVisible = true
                this.$refs.citymultipleTable.clearSelection();
            },
            //市修改
            async cityedit() {
                if (this.cityUuid == '') {
                    alert("请选中一行记录")
                    return
                }
                const result = await this.$axios.get(`/serviceBasedata/baseCitys/queryById/${this.countryUuid}/${this.provinceUuid}/${this.cityUuid}`)
                this.cityform = result.data.baseCitys
                this.citydialogFormVisible = true


            },
            //市修改或保存
            async citysave() {
                // eslint-disable-next-line no-unused-vars
                let formEntry = {};

                this.citydialogFormVisible = false
                if (this.cityUuid == '') {
                    formEntry = this.cityform
                } else {
                    formEntry = {
                        cityName: this.cityform.cityName, cityCode: this.cityform.cityCode,cityUuid: this.cityUuid
                    }
                }
                //添加或者修改操作
                const result = await this.$axios.post(`/serviceBasedata/baseCitys/${this.cityUuid == '' ? 'add' : 'update'}`, formEntry)
                if (result.flag) {
                    // 提示消息
                    this.$message({
                        type: 'success',
                        message: '成功!'
                    })
                    // 刷新数据表格
                    await this.getcityData()

                }
            },
            //市删除
            citydel() {
                this.$confirm('将删除此数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(async () => {
                    // 删除操作（物理，逻辑）
                    const result = await this.$axios.delete(`/serviceBasedata/baseCitys/delete/${this.cityUuid}`)
                    if (result.flag) {
                        // 提示消息
                        this.$message({
                            type: 'success',
                            message: '成功!'
                        })
                        // 刷新数据表格
                        await this.getcityData()
                        this.$refs.citymultipleTable.clearSelection();

                    }
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    })
                })
            },
            //市选中行事件
            citytableCurrentChange(val){
                if(val){
                    this.cityUuid = val.cityUuid;
                }
            },
            //市行点击事件
            citytableClick(row){
                this.$refs.citymultipleTable.clearSelection();
                this.$refs.citymultipleTable.toggleRowSelection(row);
            },
            //页号改变事件
            citycurrentChange(val){
                this.citycurrent = val;
                this.getcityData();
            },
            //页大小改变事件
            citysizeChange(val){
                this.citysize = val;
                this.getcityData();
            }
       },
        created() {
            this.getcountryData();
        }
    }
</script>

<style scoped>

</style>
