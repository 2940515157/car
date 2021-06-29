<template>
  <el-container style="height: calc(100vh)">
    <span hidden>{{storeVersion}}</span>
    <!-- 侧边栏 -->
    <el-aside width="250px" style="background-color: #fafaff">
      <el-container class="index-aside">
        <el-header class="index-aside-header" style="height: 85px;">
          <!-- LOGO -->
          <router-link class="logo" to="/Home">
            <img style="margin: 0 40px;" src="@/assets/menu_logo.png" alt="青城电商"/>
          </router-link>
        </el-header>
        <el-main class="index-aside-main">
          <el-scrollbar style="height: 100%;" wrap-style="overflow:hidden scroll;" view-style="">
            <!-- Menu -->
            <el-menu background-color="#545c64"
                     text-color="#FFF"
                     active-text-color="#FFD405"
                     :router="true"
                     :default-active="defaultActive"
                     :unique-opened="true"
                     @select="handleMenuEnter"
            >
              <menu-item :menus="$store.getters.menus"></menu-item>
            </el-menu>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-aside>
    <!-- 主体 -->
    <el-main style="background: #FAFAFA; padding: 5px">
      <div class="index_main_header">
        <div class="right-menu">
          <el-popover
              placement="bottom"
              title="账户信息"
              width="300"
              v-if="true"
              trigger="hover">
                <span slot="reference" style="line-height:28px; color: #FFF;font-size: 12px;">
                  admin
                </span>
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
                  <el-col :span="18">管理员</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">密码：</el-col>
                  <el-col :span="18">
                    <el-link style="line-height:15px" type="warning">更改密码</el-link>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">登录时间：</el-col>
                  <el-col :span="18">2021-05-21 13:20</el-col>
                </el-row>
                <el-row>
                  <el-col :span="6">返回总店</el-col>
                  <el-col :span="18">
                    <el-row :gutter="13">
                      <el-col :span="5">
                        <el-button size="mini" @click="backLink">进入</el-button>
                      </el-col>
                    </el-row>
                  </el-col>
                </el-row>
              </div>
              <div>
                <el-button style="float: right" size="mini" type="primary">注销</el-button>
              </div>
            </div>
          </el-popover>
        </div>
      </div>
      <!--<el-card style="height: 99.5%" body-style="height: calc(100% - 85px); margin: -10px">-->
      <!--  <div slot="header" class="clearfix">-->
      <!--    <el-page-header :content="$route.name"></el-page-header>-->
      <!--  </div>-->
      <!--  <el-scrollbar style="height: 100%;" wrap-style="overflow:hidden scroll;">-->
      <!--    &lt;!&ndash; <router-view></router-view>&ndash;&gt;-->
      <!--    <span v-for="i in 50" :key="i">-&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;{{ i }}-&#45;&#45;&#45;&#45;&#45;&#45;&#45;&#45;<br></span>-->
      <!--  </el-scrollbar>-->
      <!--</el-card>-->
      <el-tabs v-model="tabDefaultActive"
               @tab-remove="handleTabRemove"
               @tab-click="handleTabClick"
               type="border-card" style="height: calc(100vh - 45px);padding: 0;">
<!--        <el-tab-pane v-if="$route.path==='/Home'" label="首页" name="/Home">-->
<!--          <el-scrollbar style="height: calc(100vh - 115px);" wrap-style="overflow:hidden scroll;">-->
<!--            <h2>首页</h2>-->
<!--          </el-scrollbar>-->
<!--        </el-tab-pane>-->
        <el-tab-pane v-for="(item) in tabs" :key="item.name"
                     :label="item.label"
                     :name="item.name"
                     :closable="item.name !== '/Home'">
          <router-view v-if="item.name === defaultActive"></router-view>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>

<script>
import MenuItem from "@/components/menu-item/index.vue"

export default {
  name: "backout",
  data() {
    return {
      tabs: [],
      tabDefaultActive: "/Home",
    }
  },
  methods: {
    backLink() {
      window.location.assign("/master/")
    },
    handleTabAdd(label, name) {
      let _this = this
      const tab = {
        label,
        name
      }
      if (_this.tabs.every((tab) => tab.name !== name)) {
        _this.tabs.push(tab)
      }
      _this.tabDefaultActive = name
    },
    handleTabRemove(targetName) {
      let tabs = this.tabs;
      let activeName = this.tabDefaultActive;
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            let nextTab = tabs[index + 1] || tabs[index - 1];
            if (nextTab) {
              activeName = nextTab.name;
            }
          }
        });
        if (activeName === targetName) {
          activeName = "/Home"
        }
      }
      this.tabDefaultActive = activeName
      this.tabs = tabs.filter(tab => tab.name !== targetName)
      this.$router.push(activeName)
    },
    async handleMenuEnter() {
      if(this.$route.name){
        this.handleTabAdd(this.$route.name, this.$route.fullPath)
      }
    },
    handleTabClick() {
      this.$router.push(this.tabDefaultActive)
    }
  },
  components: {
    MenuItem
  },
  computed: {
    defaultActive() {
      let _this = this;
      return _this.$route.path.split("/").splice(0, 2).join("/")
    },
    storeVersion(){
      this.handleMenuEnter();
      return this.$store.getters.menus.length
    }
  },
  created() {
    let path = location.pathname.match(/\/store\/[0-9]+\//);
    if(path){
      let brCode = path[0].split("/")[2]
      this.$store.commit("set_brCode",brCode);
    }else {
      this.$store.dispatch("logout",this.$route.path)
    }
  }
}
</script>

<style scoped>
.right-menu {
  float: right;
  padding: 0 28px 0 0;
  color: #fff;
}

.height-center {
  display: flex;
  justify-content: center;
  align-items: center
}

.index-aside {
  /*height: calc(100vh);*/
  height: 100vh;
  background-color: #FFF;
  padding: 0;
}

.index-aside-header {
  padding: 0;
  background-color: #7B8E9F;
  border-bottom: #a8c5dd outset 5px;

}

.index-aside-main {
  height: calc(100vh - 85px);
  padding: 0;
  background-color: #545c64;
}

.index_main_header {
  height: 30px;
  padding: 0;
  margin: -5px -5px 5px -5px;
  background: #545c64;
}


</style>

<style>
body {
  margin: 0 0;
}

.el-scrollbar__thumb {
  background-color: #bbb;
}
</style>
