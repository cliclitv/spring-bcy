import { getUserInfo } from "../util/api"
import './header.css'

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
                <ul>
                    <li class="active">首页</li>
                    <li>APP</li>

                </ul>
                <div className="search">
                    <input type="text" placeholder="搜一下菊花又不会坏..." />
                    <button>
                        <i class="icon-font icon-search1"></i>
                    </button>
                </div>
                <ul class="right">
                    <li>赞助</li>
                </ul>
            </div>
        </header>
    )
}