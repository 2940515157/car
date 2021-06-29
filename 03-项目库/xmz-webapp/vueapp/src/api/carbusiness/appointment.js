import request from '@/utils/request'

export default {

     // 1. 预约列表（条件分页查询）
     getList (current, size, query) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-booking-head/page/${current}/${size}`,
            // url: `/carbusinessservice/serv-booking-head/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: query
          })
    },
     // 2. 根据编号删除
    deleteById (sqrUuid) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-booking-head/deleteById/${sqrUuid}`,
            // url: `/carbusinessservice/serv-booking-head/deleteById/{sqrUuid}`,
            method: 'delete'
        })
    },
    // 3. 添加或修改
    save (form) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-booking-head/${form.sqrUuid==null?'add':'update'}`,
            method: 'post',
            data: form
        })
    },
    // 根据id查询
    queryById (id) {
        return request({
            url: `/carbusinessservice/serv-booking-head/queryById/${id}`,
            method: 'post'
        })
    },

}