import { render, useState, useEffect } from "fre"
import { getUserInfo } from "../util/api"
import Avatar from "../component/avatar/avatar"
import './header.css'
import { push } from "../use-route"

function debounce(func, wait) {
    let timeout
    return function () {
        const context = this
        const args = arguments
        clearTimeout(timeout)
        timeout = setTimeout(function () {
            func.apply(context, args)
        }, wait)
    }
}

export default function Header() {
    let user = getUserInfo()


    return (
        <header>
            <div className="top wrap" >
                <div class="bio">
                    {/* <div className="logo" onClick={() => push('/')}></div> */}
                    <h1 onClick={() => push('/')}>菠萝饭</h1>
                    <nav>
                        <ul>
                            <li class="active">发现</li>
                            <li>同人</li>
                            {user.id == null && <li onClick={() => push('/login')}>登录</li>}
                        </ul>
                    </nav>
                </div>
                <Avatar email={getUserInfo().email}></Avatar>
            </div>

        </header>
    )
}