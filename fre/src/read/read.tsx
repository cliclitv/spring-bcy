import { useEffect, useState } from 'fre'
import './read.css'

import { push } from '../use-route'
import { getPosts, getTerms, getUserInfo } from '../util/api'
import Avatar from '../component/avatar/avatar'
import * as dayjs from 'dayjs'

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

export default function Read(props) {
    const [post, setPost] = useState({} as any)

    const user = getUserInfo()
    const isEditor = user.level > 1

    useEffect(() => {
        getPosts(props.id, 1, 1).then(res => {
            setPost(res.data[0])
        })
    }, [])
    return (
        <div class="main">
            <div class="wrap">
                {isEditor && <div class="write">
                    <div></div>
                    <button className="write-btn" onclick={() => push('/publish/0')}>
                        <i class="icon-font icon-writerin-f"> </i> 添加章节</button>
                </div>}
                <div className="post">
                    <div className="info">
                        <Avatar email={post.email} name={post.name}></Avatar> 发布于 <time>{dayjs(post.createTime).format('YYYY-MM-DD HH:mm')}</time>
                    </div>
                    <h2>{post.title}</h2>
                    <p>{post.content}</p>
                </div>

            </div>
        </div>

    )
}