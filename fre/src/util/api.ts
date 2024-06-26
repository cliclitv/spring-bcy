import { get, post } from './post'
const host = 'https://www.tm0.net'

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

export function getComments(pid, rid, page?, pageSize?) {
  return get(`${host}/comments?pid=${pid}&rid=${rid}&page=${page || 1}&pageSize=${pageSize || 1000}`)
}

export function addComment({ pid, uid, pos, content, rid = 0, ruid, read = 0 }) {
  return post(`${host}/comment/add`, {
    content,
    pos: pos.toString(),
    uid: getUserInfo().id,
    pid, rid, ruid, read
  })
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