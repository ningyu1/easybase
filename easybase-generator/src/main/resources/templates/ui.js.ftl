import request from '@/utils/request'

export function fetchList(query) {
    return request({
        url: '/${(entity?replace("Entity", ""))?uncap_first}/list',
        method: 'get',
        params: query
    })
}

export function create(data) {
    return request({
        url: '/${(entity?replace("Entity", ""))?uncap_first}/create',
        method: 'post',
        data
    })
}

export function update(data) {
    return request({
        url: '/${(entity?replace("Entity", ""))?uncap_first}/update',
        method: 'post',
        data
    })
}

export function del(id) {
    return request({
        url: '/${(entity?replace("Entity", ""))?uncap_first}/delete/' + id,
        method: 'post'
    })
}

export function batchDelete(data) {
    return request({
        url: '/${(entity?replace("Entity", ""))?uncap_first}/delete',
        method: 'post',
        data
    })
}

export function getObj(id) {
    return request({
        url: '/${(entity?replace("Entity", ""))?uncap_first}/get/' + id,
        method: 'get'
    })
}
