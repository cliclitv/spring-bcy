import { get, post } from './post'

export function getPost(sort, tag, page, pageSize, status?, uid?) {
  return get(`https://www.ichushou.com/posts?status=${status || 'public'}&sort=${sort}&tag=${tag}&uid=${uid || ''}&page=${page}&pageSize=${pageSize}`)
}

export function getPostDetail(pid) {
  return get(`https://www.ichushou.com/post/${pid}`)
}

export function addPost({ title, content, status, sort, tag, videos }) {
  return post('https://www.ichushou.com/post/add', {
    title,
    content,
    status,
    sort,
    tag,
    videos
  })
}

export function getUserInfo() {
  return JSON.parse(window.localStorage.getItem('user'))
}

export function updatePost({ id, title, content, status, sort, tag, time, videos }) {
  return post(`https://www.ichushou.com/post/update/${id}`, {
    id,
    title,
    content,
    status,
    sort,
    tag,
    time,
    videos
  })
}

export function updateUser({ id, name, pwd, qq, level, hash, sign }) {
  return post(`https://www.ichushou.com/user/update/${id}`, {
    name, pwd, qq, level: parseInt(level), hash, sign
  })
}

export function getUser(id) {
  return get(`https://www.ichushou.com/user/${id}`)
}

export function pay({ price, order, uid }) {
  return get(`https://www.ichushou.com/vip/pay?price=${price}&order=${order}&uid=${uid}`)
}

export function getComments(pid, rid, page?, pageSize?) {
  console.log(pid)
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

export function getTerms(uid) {
  return get(`https://www.ichushou.com/terms?pid=${uid}`)
}

export function addTerm(data){
  return post('https://www.ichushou.com/term', data)
}