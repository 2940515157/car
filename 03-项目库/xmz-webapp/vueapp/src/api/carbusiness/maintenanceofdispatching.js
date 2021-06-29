import request from '@/utils/request'

export default {

     // 1. 条件分页查询
     getList (current, size, query) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-work-card/page/${current}/${size}`,
            // url: `/carbusinessservice/serv-work-card/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: query
          })
    },
    // 3. 添加或修改
    save (form) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-work-card/${form.cardUuid==null?'add':'update'}`,
            method: 'post',
            data: form
        })
    },
    // 根据id查询
    queryById (id) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-work-card/queryById/${id}`,
            method: 'post'
        })
    },

}