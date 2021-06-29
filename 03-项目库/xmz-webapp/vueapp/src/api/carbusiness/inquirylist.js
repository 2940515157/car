import request from '@/utils/request'

export default {

     // 1. 询价记录列表（条件分页查询）
     getList (current, size, query) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-query-record/page/${current}/${size}`,
            // url: `/carbusinessservice/serv-query-record/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: query
          })
    },
     // 2. 根据编号删除
    deleteById (sqrUuid) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-query-record/deleteById/${sqrUuid}`,
            // url: `/carbusinessservice/serv-query-record/deleteById/{sqrUuid}`,
            method: 'delete'
        })
    },
    // 3. 添加或修改储蓄卡产品定义
    save (form) {
        return request({
            url: `http://localhost:8003/carbusinessservice/serv-query-record/${form.sqrUuid==null?'add':'update'}`,
            method: 'post',
            data: form
        })
    },
    // 根据id查询
    queryById (id) {
        return request({
            url: `/carbusinessservice/serv-query-record/queryById/${id}`,
            method: 'post'
        })
    },
    // 根据客户id查询
    queryByCustId (custUuid) {
        return request({
            url: `/carbusinessservice/serv-query-record/queryByCustId/${custUuid}`,
            method: 'post'
        })
    },

}