import request from '@/utils/request'

export default {
     // 1. 讲师列表（条件分页查询）
     getTeacherList (page, size, teacherQuery) {
        return request({
            // url: '/table/list',
            url: `/eduservice/edu-teacher/condition/${page}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: teacherQuery
          })
    },
    // 2. 根据编号删除讲师
    deleteTeacherId (id) {
        return request({
            url: `/eduservice/edu-teacher/${id}`,
            method: 'delete'
        })
    },
    // 3. 添加讲师
    addTeacher (teacher) {
        return request({
            url: `/eduservice/edu-teacher/add`,
            method: 'post',
            data: teacher
        })
    },
    // 根据id查询
    getTeacherInfo (id) {
        return request({
            url: `/eduservice/edu-teacher/${id}`,
            method: 'get'
        })
    },
    // 修改讲师
    updateTeacherInfo (teacher) {
        return request({
            url: `/eduservice/edu-teacher/update`,
            method: 'post',
            data: teacher
        })
    }


}