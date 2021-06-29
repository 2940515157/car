import router from "./index.js";
import store from "../store/index.js"
import {getToken} from "../../utils/token.js"
const login = "/login"
const whiteList = [login,'/'] // 重定向白名单

store.dispatch("refresh")

router.beforeEach((to,form, next) => {
    // URL定向拦截
    // 是否为白名单
    if(whiteList.indexOf(to.path) === -1) {
        // 是否通过权限认证
        if(getToken()){
            next()
        }else {
            // 否，跳转到登录页面
            window.location.assign(`/master/${to.path}`)
        }
    }else {
        next()
    }
})