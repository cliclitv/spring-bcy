import { render, useState, useEffect } from "fre"
import { push } from '../use-route'
import { getUser, getBal } from "../util/api"
import Search from "./search"
import './header.css'
import Avatar from "../component/avatar/avatar"
import { logout } from "../login/register"

function debounce(func, wait) {
    let timeout;
    return function () {
        const context = this;
        const args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(function () {
            func.apply(context, args);
        }, wait);
    };
}

export default function Header() {
    const [key, setKey] = useState("")
    const [show, setShow] = useState(false)

    let user = getUser() || {}
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
            <div className="top wrap">
                <h1>触手♂阅读</h1>
            </div>
            <nav>
                <ul class="wrap">
                    <li class="active">首页</li>
                    <button>作者中心</button>
                </ul>
            </nav>
        </header>
    )
}