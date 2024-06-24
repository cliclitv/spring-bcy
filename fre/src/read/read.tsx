import { useEffect, useState, Fragment } from 'fre'
import './read.css'

import { push } from '../use-route'
import { getPosts, getTermDetail, getTerms, getUserInfo } from '../util/api'
import Avatar from '../component/avatar/avatar'
import * as dayjs from 'dayjs'
import Publish from '../publish/publish-fen'

function CenterSection({ comp, props }) {
    const Comp = comp
    return <div style={{ 'position': 'relative' }}>
        <div className="center-wrap">
            <Comp {...props}></Comp>
        </div>
        <div className="mask">
            <i class='icon-font icon-back' onclick={() => {
                props.setShow(false)
            }}></i>
        </div>
    </div>

}

export default function Read(props) {
    const [post, setPost] = useState({} as any)
    const [term, setTerm] = useState({} as any)
    const [show, setShow] = useState(false)

    const user = getUserInfo()
    const isEditor = user.level > 1

    useEffect(() => {
        getPosts(props.id, 1, 1).then(res => {
            setPost(res.data[0])
            getTermDetail(props.id).then(res => {
                setTerm(res.data)
            })
        })
    }, [])

    console.log(props)

    return (
        <div class="main">
            <div class="wrap">
                {isEditor && <div class="write">
                    <div className="info">
                        合集由  <Avatar email={term.email} name={term.name}></Avatar> 发布于 <time>{dayjs(term.createTime).format('YYYY-MM-DD HH:mm')}</time>
                    </div>
                    <div>
                        <button className="write-btn" onclick={() => setShow(true)}>
                            <i class="icon-font icon-writerin-f"> </i> 添加分集</button>
                        <button className="write-btn" onclick={() => push(`/publish/${props.id}`)} style={{ background: 'var(--secondary)' }}>
                            <i class="icon-font icon-writerin-f"> </i> 编辑合集</button>
                    </div>
                </div>}
                <div className="post">
                    {post.title ? <><h2>{post?.title}</h2>
                        <p>{post?.content}</p></> : <div>还没有添加分集</div>}
                </div>

            </div>
            {show && <CenterSection comp={Publish} props={{
                tag: term.id,
                ptitle: term.title,
                setShow
            }}></CenterSection>}
        </div>
    )
}