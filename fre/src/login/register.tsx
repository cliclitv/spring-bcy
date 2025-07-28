import { useState, useEffect } from 'fre'
import { A, push } from '../router'
import { post } from '../util/post'
import './login.css'
import { getUserInfo, getUser, updateUser, addUser } from '../util/api'

export function logout() {
    localStorage.clear()
    window.location.href = '/login'
}

export default function Register({ uid }) {

    const [user, setUser] = useState({} as any)

    useEffect(() => {
        if (uid) {
            getUser(uid).then((user: any) => {
                setUser(user.result)
            })
        }

    }, [])

    function change(key, val) {
        setUser({
            ...user,
            [key as any]: val,
        } as any)
    }


    async function register() {
        if (!user.name || !user.email || !user.pwd) {
            alert('全都得填::>_<::')
            return
        }
        const res = await addUser(user) as any
        if (res.code === 200) {
            alert("注册成功啦~")
        } else {
            alert(res.msg)
        }
    }

    return <div class="section">
        <div class="login">
            <div className="header">
                <li><h1>{uid ? `${user.name}の资料卡` : '注册'}</h1></li>
            </div>
            <li><input type="text" placeholder="邮箱" onInput={(e) => change('email', e.target.value)} value={user.email} /></li>
            <li><input type="text" placeholder="笔名" onInput={(e) => change('name', e.target.value)} value={user.name} /></li>
            <li><input type="text" placeholder={uid ? "留空则不改" : "密码"} onInput={(e) => change('pwd', e.target.value)} /></li>
            {user.id && <li><input type="text" placeholder="签名(可不填)" onInput={(e) => change('sign', e.target.value)} value={user.sign} /></li>}

            {uid && (getUserInfo().level & 0b1000) != 0 && <select value={user.level} onInput={e => change('level', e.target.value)}>
                <option value="1">游客</option>
                <option value="2">作者</option>
                <option value="4">审核</option>
                <option value="8">管理</option>
            </select>}
            <li><button onClick={register}>{uid ? '修改' : '注册'}</button></li>
            {uid && <li><button onClick={logout} type='error'>退出登陆</button></li>}
            {!uid && <li><A href="/login">登录</A></li>}
        </div>
    </div>
}
