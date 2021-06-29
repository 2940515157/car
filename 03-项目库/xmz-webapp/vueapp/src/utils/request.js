/**
 * 这是一个再封装的Axios，封装了请求拦截，响应拦截，
 * 实现了再请求头中加入了‘X-token’头；
 * 实现了对错误请求的封装，以及对特殊错误的处理；
 */
import {getToken} from "./token.js"
const qs = require("querystring")

// 导入Axios
import Axios from "axios"
// 导入element提示信息
import {Message,MessageBox} from "element-ui"

// 创建Axios实例
const _index = Axios.create({
    baseURL: "/api/",
    timeout: 25000 // 请求超时时间
})

// 设置请求拦截器
_index.interceptors.request.use(
    config => {
        config.headers["X-token"] = getToken()
        if(config.method === 'get'){
            config.paramsSerializer = params =>{
                return qs.stringify(params)
            }
        }
        return config
    }
)

// 设置响应拦截器
_index.interceptors.response.use(
    response => {
        // 获取返回包
        const result = response.data
        // 判断是否是ERROR包
        if(!result.flag){
            // 生成ERROR提示信息
            Message({
                message: result.message,
                type: 'error',
                duration: 5000,
                showClose: true,
            })
            // 特殊状态码执行
            // 50008:非法的token; 50012:该用户被其他客户端登录;  50014:Token过期;
            // 统一登出
            if(result.code === 50008 || result.code === 50012 || result.code === 50014){
                MessageBox.confirm(
                    '是否重新登录？',
                    '你已被登出',
                    {
                        confirmButtonText: '重新登录',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    console.log("重新登录")
                })
            }
            return Promise.reject("Code:"+result.code+"["+result.message)
        } else {
            return result
        }

    },
    error => {
        Message({
            message: error.message,
            type: 'error',
            duration: 2000,
            showClose: true,
        })
        return Promise.reject(error)
    }
)


export default _index