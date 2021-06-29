import Vue from "vue"

import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"
import Locale from "element-ui/lib/locale/lang/zh-CN.js"
Vue.use(ElementUI,{Locale})

import VueRouter from "vue-router";
Vue.use(VueRouter)


import Request from '../utils/request.js'
Vue.prototype.$axios = Request

export default Vue