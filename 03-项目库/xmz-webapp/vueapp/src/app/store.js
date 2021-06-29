import Vue from "./_index.js"
import Store from "./Store.vue"
import store from "../store/index.js"
// import router from "../router/store/index.js"
import router,{getRoutes} from "../router/_index.js"
getRoutes("store")
new Vue({
    render: h => h(Store),
    router,
    store
}).$mount("#app")
