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
    const [key, setKey] = useState("")
    const [show, setShow] = useState(false)

    let user = getUserInfo() || {}
    const keydown = (e) => {
        console.log(key)
    }

    const changeKey = debounce((key) => {
        setKey(key)
    }, 500)

    const openWallet = () => {
        setShow(!show)
    }

    return (
        <header>
            <div className="top wrap" >
                <div class="bio">
                    <div className="logo" onClick={()=>push('/')}></div>
                    <nav>
                        <ul>
                            <li class="active">藏书馆</li>
                            <li>原耽</li>
                            <li>同人</li>
                        </ul>
                    </nav>
                </div>
                <Avatar email={getUserInfo().email}></Avatar>
            </div>

        </header>
    )
}