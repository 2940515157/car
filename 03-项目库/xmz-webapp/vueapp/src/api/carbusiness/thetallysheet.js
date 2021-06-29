import request from '@/utils/request'

export default {

     // 1. 条件分页查询
     getList (current, size, query) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-car-check/page/${current}/${size}`,
            // url: `/carbusinessservice/serv-car-check/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: query
          })
    },
     // 2. 根据编号删除
    deleteById (id) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-car-check/deleteById/${id}`,
            // url: `/carbusinessservice/serv-car-check/deleteById/{sqrUuid}`,
            method: 'delete'
        })
    },
    // 3. 添加或修改
    save (form) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-car-check/${form.checkUuid==null?'add':'update'}`,
            method: 'post',
            data: form
        })
    },
    // 根据id查询
    queryById (id) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-car-check/queryById/${id}`,
            method: 'post'
        })
    },

}