import { render, useState, useEffect } from "fre"
import { getUserInfo } from "../util/api";
import './header.css'

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
            <div className="top wrap">
                <h1>北极圈</h1>
            </div>
            <nav>
                <ul class="wrap">
                    <li class="active">首页</li>
                    {/* <button>作者中心</button> */}
                </ul>
            </nav>
        </header>
    )
}