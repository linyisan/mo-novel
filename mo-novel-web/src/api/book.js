import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/book/list',
    method: 'get',
    params: query
  })
}

export function fetchBook(id) {
  return request({
    url: '/article/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/vue-element-admin/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function createBook(data) {
  return request({
    url: '/vue-element-admin/book/create',
    method: 'post',
    data
  })
}

export function updateBook(data) {
  return request({
    url: '/vue-element-admin/book/update',
    method: 'post',
    data
  })
}
