import { get, post } from './post'

export function getPosts(tag, page, pageSize) {
  return get(`https://www.ichushou.com/posts?&tag=${tag}&page=${page}&size=${pageSize}`)
}

export function getPostDetail(id) {
  return get(`https://www.ichushou.com/post/${id}`)
}

export function getTermDetail(id) {
  return get(`https://www.ichushou.com/term/${id}`)
}

export function getUserInfo() {
  return JSON.parse(window.localStorage.getItem('user')) || {}
}

export function addUser(user) {
  return post(`https://www.ichushou.com/register`, user)
}

export function getUser(id) {
  return get(`https://www.ichushou.com/user/${id}`)
}

export function getComments(pid, rid, page?, pageSize?) {
  return get(`https://www.ichushou.com/comments?pid=${pid}&rid=${rid}&page=${page || 1}&pageSize=${pageSize || 1000}`)
}

export function addComment({ pid, uid, pos, content, rid = 0, ruid, read = 0 }) {
  return post('https://www.ichushou.com/comment/add', {
    content,
    pos: pos.toString(),
    uid: getUserInfo().id,
    pid, rid, ruid, read
  })
}

export function getGonggao() {
  return get(`https://www.ichushou.com/post/1`)
}

export function getTerms(cat, author, uid) {
  return get(`https://www.ichushou.com/terms?cat=${cat}&author=${author}&uid=${uid}`)
}

export function addTerm(data) {
  return post('https://www.ichushou.com/term', data)
}

export function addpost(data) {
  return post('https://www.ichushou.com/post', data)
}