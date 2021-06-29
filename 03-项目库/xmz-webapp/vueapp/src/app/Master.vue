<template>
  <el-container>
    <!-- 头部 -->
    <el-header class="index-header" height="80px">
      <nav>
        <!-- LOGO -->
        <router-link class="logo" to="/Home">
          <img src="@/assets/logo.jpg" alt="青城电商"/>
        </router-link>
        <!-- 右侧菜单 -->
        <div class="right-menu">
          <el-popover
              placement="bottom"
              title="账户信息"
              width="300"
              v-if="true"
              trigger="hover">
            <span slot="reference" style="line-height:28px; color: #FFF">{{ $store.getters.userInfo.loginName }}</span>
            <div slot="default">
              <div class="height-center" style="padding:10px 0;background:#fafaff;">
                <el-avatar
                    shape="square"
                    size="large"
                    src="/"
                    style="width: 100px;height: 100px;"
                ></el-avatar>
              </div>
              <div style="line-height: 40px;">
                <el-row>
                  <el-col :span="6">当前用户：</el-col>
                  <el-col :span="18">{{ $store.getters.userInfo.name }}</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">密码：</el-col>
                  <el-col :span="18">
                    <el-link style="line-height:15px" type="warning" @click="handleOpenFormUpdatePwdDialog(false)">更改密码</el-link>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">登录时间：</el-col>
                  <el-col :span="18">{{ $store.getters.userInfo.loginTime }}</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">登录门店：</el-col>
                  <el-col :span="18">
                    <el-row :gutter="13">
                      <el-col :span="17">
                        <el-input size="mini" v-model="linkCode"></el-input>
                      </el-col>
                      <el-col :span="5">
                        <el-button size="mini" @click="handleLink">进入</el-button>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
              </div>
              <div>
                <el-button style="float: right" size="mini" type="primary" @click="$store.dispatch('logout')">注销
                </el-button>
              </div>
            </div>
          </el-popover>
        </div>
        <!-- 顶部导航栏 -->
        <div class="menu-bar">

        </div>

      </nav>
    </el-header>
    <!-- 主体 -->
    <el-main class="index-main">
      <el-container class="index-main-box">
        <!-- 侧边栏 -->
        <el-aside width="250px" style="background-color: #545c64">
          <el-scrollbar style="height: 100%;" wrap-style="overflow:hidden scroll;" view-style="">
            <el-menu background-color="#545c64"
                     text-color="#FFF"
                     active-text-color="#FFD405"
                     :router="true"
                     :default-active="defaultActive"
                     :unique-opened="true"
            >
              <menu-item :menus="$store.getters.menus"></menu-item>
            </el-menu>
          </el-scrollbar>
        </el-aside>
        <!-- 主体 -->
        <el-main style="background: #FAFAFA; padding: 5px">
          <el-card class="box-card" style="height: 99.5%" body-style="height: calc(100% - 61px);">
            <div slot="header" class="clearfix">
              <el-page-header :content="$route.name"></el-page-header>
            </div>
            <router-view></router-view>
          </el-card>
        </el-main>
      </el-container>
    </el-main>
    <!-- 底部 -->
    <el-footer class="index-footer" height="28px"></el-footer>
    <!--更改密码对话框-->
    <el-dialog :visible.sync="formDialogNode.visible" :before-close="closePwdDialog" width="500px">
      <!-- 标题 -->
      <div slot="title" >
        <span>{{ formDialogNode.title }}</span>
      </div>
      <!-- 主体 -->
      <el-form :model="formDialogNode.formData" size="mini"
               :rules="formDialogNode.rules"
               style="margin: -20px -5px; border-bottom: 1px solid #EBEEF5;border-top: 1px solid #EBEEF5;">
        <el-form-item label="旧密码" prop="oldPassword">
          <el-input type="password" show-password v-model="formDialogNode.formData.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" show-password v-model="formDialogNode.formData.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="再次输入新密码" prop="rePassword">
          <el-input type="password" show-password v-model="formDialogNode.formData.rePassword"></el-input>
        </el-form-item>
      </el-form>
      <!-- 尾页 -->
      <div slot="footer" style="margin: -5px -5px -5px -5px">
        <el-button size="mini" @click="formDialogNode.visible=false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handleSubmitClick">确 定</el-button>
      </div>
    </el-dialog>

  </el-container>
</template>

<script>
import MenuItem from "@/components/menu-item/index.vue"

export default {
  name: "master",
  data() {
    let rev = (rule, value, callback)=>{
      if(value !== this.formDialogNode.formData.newPassword){
        callback('两次输入密码不一致!');
      }
      console.log(value)
    }

    return {
      linkCode: 1111,
      formDialogNode:{
        visible: false,
        title: "修改密码",
        force: false,
        formData: {},
        rules: {
          oldPassword: [
            {required: true, message: '请输入旧密码', trigger: 'blur'},
          ],
          newPassword: [
            {required: true, message: '请输新入密码', trigger: 'blur'},
          ],
          rePassword: [
            {required: true, message: '请再次输入新密码', trigger: 'blur'},
            { validator:rev, trigger: 'blur' }
          ]
        },
      }
    }
  },
  methods: {
    handleLink() {
      let _this = this;
      window.location.assign(`/store/${_this.linkCode}/#/Home`)
    },
    handleOpenFormUpdatePwdDialog(force){
      let _this = this;
      let formDialogNode = _this.formDialogNode;
      formDialogNode.visible = true;
      formDialogNode.force = force;
      formDialogNode.title = `修改密码${force?'(强制的)':''}`
    },
    handleSubmitClick(){
      let _this = this;
      let formData = _this.formDialogNode.formData;
      _this.$axios.put("/serviceSystem/sys-security/updatePassword",
        Object.assign(formData,{id: _this.$store.getters.userInfo.id})
      ).then(()=>{
        _this.$message({
          type: 'success',
          message: '修改成功! 正在注销！'
        });
        _this.$store.dispatch('logout')
      })
    },
    closePwdDialog(){
      let _this = this;
      let formDialogNode = _this.formDialogNode;
      if(formDialogNode.force){
        _this.$alert('该账号被标记了"初始密码"，为保证您的账号安全，强制您修改密码','该修改操作是强制的',{
          confirmButtonText: '确定',
          callback: () => {
            formDialogNode.visible = true;
            this.$message({
              type: 'info',
              message: '该修改操作是强制的'
            });
          }
        })
      }else {
        formDialogNode.visible = false;
      }

    }
  },
  components: {
    MenuItem
  },
  computed: {
    defaultActive() {
      let _this = this;
      return _this.$route.path.split("/").splice(0, 2).join("/")
    }
  },
  created() {
    let _this = this;
    setTimeout(function (){
      if(_this.$store.getters.userInfo.passwordInitFlag === 'Y'){
        _this.handleOpenFormUpdatePwdDialog(true)
      }
    },250)
  },
}
</script>

<style scoped>
.index-header {
  padding: 0;
  background: #3f4b61;
}

.index-main {
  height: calc(100vh - 108px);
  background-color: #FFF;
  padding: 0;
}

.index-main-box {
  height: calc(100vh - 108px);
}

.index-footer {
  background: #3F4B61;
}

.logo {
  width: 160px;
  height: 36px;
  text-align: center;
  float: left;
  padding: 0 0 0 15px;

}

.right-menu {
  float: right;
  padding: 25px 100px 0 0;
  color: #fff;
}

.menu-bar {
  float: left;
  text-align: center;
  padding-left: 10px;
  margin-left: 100px;
  margin-right: 0;
}

.height-center {
  display: flex;
  justify-content: center;
  align-items: center
}

.iconNav {
  display: block;
  font-size: 24px;
  line-height: 40px;
}


</style>

<style>
body {
  margin: 0 0;
}

.el-scrollbar__thumb {
  background-color: #FAFAFA;
}

</style>
