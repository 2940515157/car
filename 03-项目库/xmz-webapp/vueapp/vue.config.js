const INDEX_HTML = "public/_index.html";

module.exports = {
    // 打包配置
    pages: {
        "master":{
            // 打包入口
            entry: "src\\app\\master.js",
            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "车管家总店管理系统"
        },
        "store":{
            // 打包入口
            entry: "src\\app\\store.js",
            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "车管家门店管理系统"
        },
        "login":{
            // 打包入口
            entry: "src\\app\\login.js",
            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "车管家登录页面"
        },
        "Test":{
            // 打包入口
            entry: "src\\app\\test.js",
            // 打包模板
            template: INDEX_HTML,
            // 标题
            title: "车管家测试页面"
        }
    },
    // 服务器配置
    devServer: {
        // 端口号
        port: "2333",
        // 热部署
        hot: true,
        // 启动完毕时打开浏览器
        open: true,
        // 打开浏览器时访问的页面
        openPage: "login/",
        // 代理
        proxy: {
            //需要代理的URL标识头
            "/api":{
                // 跳转的连接
                target: "http://localhost:9520/",
                // 允许跨域
                changeOrigin: true,
                // 路径重写
                /* */
                /*pathRewrite: {
                    //将标识头清空
                    '/api': ''
                }*/
            }
        }
    }
};
