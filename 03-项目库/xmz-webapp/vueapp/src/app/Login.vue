<template>

  <div class="loginMain">
    <div class=loginIcon></div>
    <el-form class="loginCon" :model="form" :inline="true" label-width="45px">
      <el-form-item>
        <span class="userIcon inputIcon" slot="label"></span>
        <el-input v-model="form.username" placeholder="管理员账号"></el-input>
      </el-form-item>
      <el-form-item>
        <span class="pwdIcon inputIcon" slot="label"></span>
        <el-input type="password" :show-password="true" v-model="form.password" placeholder="密码"></el-input>
      </el-form-item>
    </el-form>
    <div class="loginBtn">
      <el-button @click="doLogin">登录</el-button>
    </div>
  </div>


</template>

<script>
export default {
  name: "Login",
  data: function () {
    return {
      form: {
        username: "admin",
        password: "",
      }
    }
  },
  methods: {
    doLogin() {

    }
  }
}
</script>

<style scoped>
.loginMain{
  width: 760px;
  height: 310px;
  /*border: 1px solid #000;*/

  margin: auto auto;
  position: relative;
  transform: translateY(50%)
}

.loginIcon {
  /*background: url(../public/img/loginlogo.png) no-repeat 50% 0;*/
  padding-bottom: 60px;
  height: 128px;
}

.loginCon {
  background: url(../../public/images/loginbox.png) no-repeat 0 0;
  height: 70px;
  padding: 50px 40px 0;
  margin-bottom: 40px;
}

.inputIcon {
  float: left;
  display: inline-block;
  width: 45px;
  height: 45px;
  vertical-align: middle;
  border-right: 1px solid #8ae1d2;
}

.userIcon {
  background: url(../../public/images/user.png) no-repeat 50% 50% #fff;
}

.pwdIcon {
  background: url(../../public/images/pwd.png) no-repeat 50% 50% #fff;
}

.loginBtn {
  text-align: center;
}


</style>

<style>
body {
  /*背景*/
  margin: 0;
  background-image: linear-gradient(to right, #294664 , #6EFCFD);
}
/*    侵入式设置样式*/
.el-input__inner {
  height: 45px;
  width: 285px;
  line-height: 45px;
  border-radius: 0;
}

.el-button {
  background: none;
  height: 40px;
  line-height: 16px;
  font-size: 16px;
  padding: 0;
  color: #ffffff;
  vertical-align: middle;
  width: 100px;
}

.el-button:focus, .el-button:hover {
  color: #ffffff;
  background: rgba(255, 255, 255, 0.50);
  border-color: #ffffff;
}
</style>