import md5 from 'blueimp-md5'
import { getUser } from './api'

export function getAvatar(avatar) {
  if (/^[0-9]+@qq.com$/.test(avatar)) {
    return `https://q1.qlogo.cn/g?b=qq&nk=${avatar.substring(0, avatar.length-6)}&s=640`
  } else {
    let hash = md5(avatar)
    return `https://weavatar.com/avatar/${hash}?s=100`
  }
}

export function getSuo(content) {
  if (!content) return ""
  let m = content.match(/suo(.+?)\)/i)
  return m ? m[1].slice(2) : 'https://cdn-us.imgs.moe/2023/02/27/63fcb180cbb30.jpg'
}

export function getAv(id) {
  const [gv, fp] = id.split('#')
  return [gv.substring(2, id.length), fp ? fp.substring(1, fp.length) : 1]
}

export function isMobile() {
  try {
    document.createEvent("TouchEvent"); return true;
  } catch (e) {
    return false;
  }
}

export default function shouldVIP(time) {
  let tt = new Date(time)
  let ttt = tt.getTime()

  return ttt >= Date.now()
}
