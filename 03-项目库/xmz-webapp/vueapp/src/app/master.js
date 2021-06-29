import Vue from "./_index.js"
import Master from "./Master.vue"
import store from "../store/index.js"
import router,{getRoutes} from "../router/_index.js"

getRoutes("base")
new Vue({
    render: h => h(Master),
    router,
    store
}).$mount("#app")
