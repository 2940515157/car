import Vue from "vue"

import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"
import Locale from "element-ui/lib/locale/lang/zh-CN.js"
Vue.use(ElementUI,{Locale})

import store from "../store/index.js"

import app from "./Login2.vue";
new Vue({
    render: h => h(app),
    store,
}).$mount("#app");