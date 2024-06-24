import { useEffect, useState } from 'fre'
import './home.css'

import { push } from '../use-route'
import { getTerms, getUserInfo } from '../util/api'
import Publish from '../draft/draft'

function CenterSection({ comp, props }) {
    const Comp = comp
    return <div style={{ 'position': 'relative' }}>
        <div className="center-wrap">
            <Comp {...props}></Comp>
        </div>
        <div className="mask">
            <i class='icon-font icon-back' onclick={() => {
                push('/')
            }}></i>
        </div>
    </div>

}

const user = getUserInfo()

export default function App(props) {
    const [terms, setTerms] = useState([])

    console.log(props.id)

    useEffect(() => {
        // getTerms(user.id).then(res => {

        // })
    }, [])
    return (
        <div class="wrap">
            <div class="main">
                <div class="write">
                    <div></div>
                    <button className="write-btn" onclick={() => push('/publish/0')}>挖坑</button>
                </div>
                {props.id && <CenterSection comp={Publish} props={props}></CenterSection>}
            </div>
        </div>

    )
}