import { useEffect, useState } from 'fre'
import './home.css'
import * as dayjs from 'dayjs'

import { push } from '../use-route'
import { getTerms, getUserInfo } from '../util/api'
import Publish from '../publish/publish-he'

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


export default function App(props) {
    const [terms, setTerms] = useState([])

    console.log(props.id)

    useEffect(() => {
        getTerms('', '', 0).then(res => {
            setTerms(res.data)
        })
    }, [])
    return (
        <div class="wrap">
            <div class="main">
                <div class="write">
                    <div></div>
                    <button className="write-btn" onclick={() => push('/publish/0')}>
                        <i class="icon-font icon-writerin-f"> </i> 添加合集</button>
                </div>
                <div className="terms">
                    <ul>
                        <li>文名</li>
                        <li>简介</li>
                        <li>作者</li>
                        <li>发布时间</li>
                    </ul>
                    {terms.map(term => {
                        return <ul onClick={() => push(`/read/${term.id}`,{title:term.title})}>
                            <li>{term.title}</li>
                            <li>{term.content}</li>
                            <li>{term.author}</li>
                            <li>{dayjs(term.createTime).format('YYYY-MM-DD HH:mm')}</li>
                        </ul>
                    })}
                </div>
                {props.id && <CenterSection comp={Publish} props={props}></CenterSection>}
            </div>
        </div>

    )
}