import Vue from "vue";
import VueRouter from "vue-router"
import store from "../store/index.js"
import request from "../utils/request";
import Layout from "../components/layout/index.vue"
import {getToken} from "../utils/token";

Vue.use(VueRouter);
let routes = [];
let router = new VueRouter({routes})
export default router;

/**
 * 动态路由，从服务区获取当前用户所拥有的路由，解析、对URL经行Vue绑定
 */
export function getRoutes(origin){
    request.post("serviceSystem/sys-security/getRoutes",
        {origin}
    ).then(result=>{
        let {routes,indexMap} = result.data;
        let r = [];
        Object.keys(indexMap).forEach(key=>{
            let route = routes[indexMap[key]];
            route.component = Layout;
            let children = route.children
            if(children){
                children.forEach(item=>{
                    item.path += ":id"
                    Object.assign(item,{
                        component: ()=>import(/* webpackChunkName: "routes_view" */ "@/" + item.url)
                    })
                })
            }
            r.push(route);
        })
        store.commit('set_menus',r)
        router.addRoutes(r)
    })
}


/**
 * 整合前端路由权限
 */
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
            let params = to.params;
            if(params.id){
                store.commit("set_btnCode",[])
                request.post("/serviceSystem/sys-security/getBtnCode",{
                    resId: to.params['id'].substr(3)
                }).then(({data}) => {
                    store.commit("set_btnCode",data)
                })
            }
        }else {
            // 否，跳转到登录页面
            store.dispatch("logout",`redirect=${to.path}`)
        }
    }else {
        next()
    }
})