import request from '@/utils/request'

export default {
  // 获取所有字典实例数据
  getEntrysList(page, size, entrys) {
    return request({
      url: `/basicsdataheadservice/eos-dict-entry/pageQuery/${page}/${size}`,
      method: 'post',
      data: entrys
    })
  },
  // 获取所有字典实例数据
  getDictTypeList(page, size, dictType) {
    return request({
      url: `/basicsdataheadservice/eos-dict-type/pageQuery/${page}/${size}`,
      method: 'post',
      data: dictType
    })
  },
  // 通过字典代码查询
  queryByDictTypeId(dicttypeid) {
    return request({
      url: `/basicsdataheadservice/eos-dict-entry/queryByDictTypeId/${dicttypeid}`,
      method: 'get'
    })
  },
  // 获取所有字典类型信息拿到类型属性
  getType() {
    return request({
      url: '/basicsdataheadservice/eos-dict-type/queryAll',
      method: 'get'
    })
  },
  // 删除字典实例信息
  deleteEntry(dicttypeid, id) {
    console.log(3, dicttypeid)
    return request({
      url: `/basicsdataheadservice/eos-dict-entry/delete/${dicttypeid}/${id}`,
      method: 'delete'
    })
  },
  // 根据字典项id查询字典信息
  queryByDictId(dictid) {
    return request({
      url: `/basicsdataheadservice/eos-dict-entry/queryById/${dictid}`,
      method: 'get'
    })
  },
  // 添加字典项信息
  addEntry(Entry) {
    return request({
      url: '/basicsdataheadservice/eos-dict-entry/addEos',
      method: 'post',
      data: Entry
    })
  },
  // 修改字典项信息
  updateEntry(Entry) {
    return request({
      url: '/basicsdataheadservice/eos-dict-entry/updateEos',
      method: 'post',
      data: Entry
    })
  }
}
