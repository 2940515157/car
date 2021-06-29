import Vue from "./_index.js"
import App from "./Test.vue"
import router from "../router/index.js"

new Vue({
    render: h => h(App),
    router
}).$mount("#app")
