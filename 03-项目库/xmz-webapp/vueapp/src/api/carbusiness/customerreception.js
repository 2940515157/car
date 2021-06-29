import request from '@/utils/request'

export default {

    // 1. 接车单列表（条件分页查询）
    getList (current, size, query) {
       return request({
           url: `http://localhost:8003/carbusinessservice/serv-receive-car/page/${current}/${size}`,
           // url: `/carbusinessservice/serv-query-record/page/${current}/${size}`,
           method: 'post',
           // data表示把对象转换json进行传递到接口中去
           data: query
         })
   },
   // 根据id查询
   queryById (id) {
       return request({
           url: `http://localhost:8003/carbusinessservice/serv-receive-car/queryById/${id}`,
           method: 'post'
       })
   },

}