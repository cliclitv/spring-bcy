import { get, post } from './post'
const host = 'https://www.yootoo.cc'

export function getPosts(tag, page, pageSize) {
  return get(`${host}/posts?&tag=${tag}&page=${page}&size=${pageSize}`)
}

export function getPostDetail(id) {
  return get(`${host}/post/${id}`)
}

export function getTermDetail(id) {
  return get(`${host}/term/${id}`)
}

export function getUserInfo() {
  return JSON.parse(window.localStorage.getItem('user')) || {}
}

export function addUser(user) {
  return post(`${host}/user/register`, user)
}

export function getUser(id) {
  return get(`${host}/user/${id}`)
}

export function getReplys(pid, page?, pageSize?) {
  return get(`${host}/comments?pid=${pid}&page=${page || 1}&size=${pageSize || 1000}`)
}

export function addReply(data) {
  return post(`${host}/comment`, data)
}

export function getGonggao() {
  return get(`${host}/post/1`)
}

export function getTerms(cat, author, uid) {
  return get(`${host}/terms?cat=${cat}&author=${author}&uid=${uid}`)
}

export function addTerm(data) {
  return post(`${host}/term`, data)
}

export function addpost(data) {
  return post(`${host}/post`, data)
}