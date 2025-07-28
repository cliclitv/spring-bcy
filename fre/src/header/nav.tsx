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

export default function Nav() {
    let user = getUserInfo()
    return (
        <nav>
            <nav className="top wrap" >
                <ul>
                    <li class="active">综合讨论区</li>
                    <li>同人创作区</li>
                    <li>画师区</li>
                    <li>BUG反馈区</li>
                </ul>
                <section class="intro"></section>
            </nav>
        </nav>
    )
}