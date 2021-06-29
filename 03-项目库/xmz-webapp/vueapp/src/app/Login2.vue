<template>

  <div class="loginMain">
    <img style="margin-left: 15px" src="@/assets/logo.jpg" alt="青城电商"/>
    <el-card class="loginCard">
      <div slot="header" class="clearfix">
        <span>请登录</span>
      </div>
      <div>
        <el-form ref="loginForm" class="loginCon" :rules="formRule" :model="form" label-width="70px">
          <el-form-item prop="username">
            <span class="userIcon inputIcon" slot="label"></span>
            <el-input v-model="form.username" placeholder="管理员账号"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <span class="pwdIcon inputIcon" slot="label"></span>
            <el-input type="password" :show-password="true" v-model="form.password" placeholder="密码"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <!-- 分隔符 -->
      <div style="margin:5px -20px;border-bottom: 1px solid #EBEEF5;"></div>
      <div class="loginBtn">
        <el-button size="small" type="success" @click="doLogin">登录</el-button>
      </div>
    </el-card>
  </div>

</template>

<script>
import {Message} from "element-ui";

export default {
  name: "Login",
  data: function () {
    return {
      form: {
        username: "",
        password: "",
      },
      formRule: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ]
      },
      query: {},
    }
  },
  methods: {
    doLogin() {
      let _this = this;
      _this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          _this.$store.dispatch("login", _this.form).then(() => {
            window.location.assign(`/master/#${_this.query.redirect||"/Home"}`)
          })
        } else {
          _this.$alert("请将表单填充完整", '表单未填充完整', {
            confirmButtonText: '确定',
            callback: () => {
              this.$message({
                type: 'info',
                message: '表单未填充完整'
              });
            }
          });
          return false;
        }
      });
    }
  },
  created(){
    let query = this.query;
    location.search.substr(1).split("&").forEach(item => {
      let s = item.split("=");
      query[s[0]] = s[1]
    })
    if(query.redirect)Message.info("你的登录令牌不存在，请重新登录")
    this.form.username = query.loginName;
  }
}
</script>

<style scoped>
.loginCard {
  width: 450px;
  height: 285px;
  /*margin: 0 auto; !*水平居中*!*/
  position: relative;
  margin-left: 60%;
  transform: translateY(50%);
}

.userIcon {
  background: url(../../public/images/user.png) no-repeat 50% 50% #fff;
}

.pwdIcon {
  background: url(../../public/images/pwd.png) no-repeat 50% 50% #fff;
}

.inputIcon {
  float: left;
  display: inline-block;
  width: 45px;
  height: 45px;
  vertical-align: middle;
}

.loginBtn {
  padding: 18px 0;
  text-align: right;
}
</style>

<style>
body {
  margin: 0;
  /*background-color: #3F4B61;*/
  background-image: url("../../public/images/loginBgImg1.jpg");
  background-size: 100vw 100vh;
  overflow: hidden;
  display: block;
  background-position: center;
  background-attachment: fixed;
  background-repeat:no-repeat;
}
</style>
