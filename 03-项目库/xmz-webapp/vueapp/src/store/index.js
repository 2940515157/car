import Vuex from "vuex"
import Vue from "vue"

import request from "../utils/request";
import {getToken, setToken, removeToken} from "../utils/token.js"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: getToken(),
        userInfo: {},
        roles: [],
        menus: [],
        version: 1,
        origin: "",
        brCode: "",
        btnCode: [],
    },
    getters: {
        "token": state => state.token,
        "userInfo": state => state.userInfo,
        "roles": state => state.roles,
        "menus": state => state.menus,
        "origin": state => state.origin,
        "version": state => state.version,
        "brCode": state => state.brCode,
        "btnCode": state => state.btnCode
    },
    mutations: {
        "set_token": (state, token) => {
            state.token = token
            setToken(token)
            state.version++
        },
        "set_userInfo": (state, userInfo) => {
            state.userInfo = userInfo
            state.version++
        },
        "set_roles": (state, roles) => {
            state.roles = roles
            state.version++
        },
        "set_menus": (state, menus)=>{
            state.menus = menus
            state.version++
        },
        "set_origin": (state, origin) => {
            state.origin = origin
            state.version++
        },
        "set_brCode": (state, brCode) => {
            state.brCode = brCode
            state.version++
        },
        "set_btnCode": (state, btnCode) =>{
            state.btnCode = btnCode
            state.version++
        },
    },
    actions: {
        "login"({commit}, loginToken) {
            return new Promise((resolve, reject) => {
                request.post("/serviceSystem/sys-security/login", loginToken)
                    .then((result) => {
                        commit("set_token", result.data)
                        resolve()
                    }).catch(error => {
                    reject(error)
                })
            })
        },
        "refresh"(store) {
            request.post("/serviceSystem/sys-security/getCurrentSubject")
                .then((request) => {
                    if(request.data === null) store.dispatch("logout")
                    store.commit("set_userInfo", request.data)
                }).catch(() => {
                store.dispatch("logout")
            })
        },
        "logout"({getters,commit},query) {
            let loginName = ""+getters.userInfo.loginName;
            removeToken();
            commit("set_userInfo", {})
            commit("set_roles", [])
            commit("set_token", "NO_TOKEN")
            window.location.assign(`/login?loginName=${loginName}&${query}`)
        }
    }
})
