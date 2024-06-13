import { get, post } from './post'

export function getPost(sort, tag, page, pageSize, status?, uid?) {
  return get(`https://www.ichushou.com/posts?status=${status || 'public'}&sort=${sort}&tag=${tag}&uid=${uid || ''}&page=${page}&pageSize=${pageSize}`)
}

export function getPostB(type, tag, page, pageSize, status?, uid?) {
  return get(`https://www.ichushou.com/posts?status=${status}&sort=${type}&tag=${tag}&uid=${uid || ''}&page=${page}&pageSize=${pageSize}`)
}

export function getRank(day) {
  return get(`https://www.ichushou.com/rank?day=${day}`)
}

export function getPostDetail(pid) {
  return get(`https://www.ichushou.com/post/${pid}`)
}

export function getPlayUrl(url) {
  return get(`https://www.ichushou.com/play?url=${url}`)
}

export function getPv(pid) {
  return get(`https://www.ichushou.com/pv/${pid}`)
}

export function getSearch(key) {
  return get(`https://www.ichushou.com/search/posts?key=${key}`)
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

export function getDogeToken({ fname, rname }) {
  return get(`https://www.ichushou.com/doge?fname=${fname}&rname=${rname}`)
}

export function getTransfer({ from, to }) {
  return get(`https://www.ichushou.com/eth/transfer?from=${from}&to=${to}`)
}

export function getBal(from) {
  return get(`https://www.ichushou.com/eth/balanceof?from=${from}`)
}

export function pay({ price, order, uid }) {
  return get(`https://www.ichushou.com/vip/pay?price=${price}&order=${order}&uid=${uid}`)
}

export function paycheck(tradeno) {
  return get(`https://www.ichushou.com/vip/paycheck?tradeno=${tradeno}`)
}

export function getArticles(pid) {
  return get(`https://www.ichushou.com/articles?pid=${pid}&page=1&pageSize=200`)
}

export function getArticle(pid) {
  return get(`https://www.ichushou.com/article/${pid}`)
}

export function addArticle({ pid, oid, title, content, bio }) {
  return post(`https://www.ichushou.com/article/add`, {
    pid: parseInt(pid),
    oid: parseInt(oid),
    content,
    title, bio
  })
}

export function updateArticle({ pid, oid, title, content, bio, id }) {
  return post(`https://www.ichushou.com/article/update/${id}`, {
    pid: parseInt(pid),
    oid: parseInt(oid),
    content,
    title, bio
  })
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

export function getDanmakus(pid, p, page?, pageSize?) {
  console.log(pid)
  return get(`https://www.ichushou.com/danmakus?pid=${pid}&p=${p}&page=${page || 1}&pageSize=${pageSize || 1000}`)
}

export function addDanmaku({ pid, p, uid, color, pos, content }) {
  return post('https://www.ichushou.com/danmaku/add', {
    content,
    color,
    p,
    pos,
    uid: getUserInfo().id,
    pid
  })
}


export function getUsers(names) {
  return get(`https://www.ichushou.com/users?names=${names.join(',')}`)
}

export function getGonggao(){
  return get(`https://www.ichushou.com/post/1`)
}