import { useEffect, useState } from 'fre'
import './home.css'

import { push } from '../use-route'

import Register from '../login/register'

function CenterSection({ comp, props }) {
    const Comp = comp
    return <div style={{ 'position': 'relative' }}>
        <div className={props.gv ? 'postplayer' : "usercenter"}>
            <Comp {...props}></Comp>
        </div>
        <div className="mask">
            <i class='icon-font icon-back' onclick={() => {
                push('/')
            }}></i>
        </div>
    </div>

}

export default function App(props) {
    return (
        <div class="wrap">
            <ul>
                <li>挖坑</li>
                <li>填坑</li>
            </ul>
        </div>

    )
}