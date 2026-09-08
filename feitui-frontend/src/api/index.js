import request from '@/utils/request'

export function submitContact(data) {
  return request({
    url: '/contact/submit',
    method: 'post',
    data
  })
}

export function getContactList(params) {
  return request({
    url: '/contact/list',
    method: 'get',
    params
  })
}

export function getFeatureList() {
  return request({
    url: '/feature/list',
    method: 'get'
  })
}

export function getStatsList() {
  return request({
    url: '/stats/list',
    method: 'get'
  })
}

export function getVideoList() {
  return request({
    url: '/video/list',
    method: 'get'
  })
}

export function getHighlightList() {
  return request({
    url: '/highlight/list',
    method: 'get'
  })
}
