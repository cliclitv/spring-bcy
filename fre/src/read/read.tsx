import { useEffect, useState, Fragment } from 'fre'
import './read.css'

import { push } from '../use-route'
import { getPosts, getTermDetail, getTerms, getUserInfo } from '../util/api'
import Avatar from '../component/avatar/avatar'
import Publish from '../publish/publish-fen'
import { marked } from 'marked'

function CenterSection({ comp, props }) {
    const Comp = comp
    return <div style={{ 'position': 'relative' }}>
        <div className="center-wrap">
            <Comp {...props}></Comp>
        </div>
        <div className="mask">
            <i class='icon-font icon-back' onclick={() => {
                props.setShow(null)
            }}></i>
        </div>
    </div>

}

export default function Read(props) {
    const [post, setPost] = useState({} as any)
    const [term, setTerm] = useState({} as any)
    const [show, setShow] = useState(null)
    const [page, setPage] = useState(parseInt(window.location.hash.slice(1)) || 1)

    const user = getUserInfo()
    const isEditor = user.level > 1

    useEffect(() => {
        getPosts(props.id, page, 1).then(res => {
            setPost(res.data[0])
            getTermDetail(props.id).then(res => {
                setTerm(res.data)
            })
        })
    }, [page])

    function nextPage() {
        window.location.hash = '#' + (page + 1)
        setPage(page + 1)
    }

    function page$() {
        return <div className="page">
            {new Array(term.count).fill(null).map((c, index) => {

                return <li class={page === index + 1 ? 'active' : ''} onClick={() => {
                    window.location.hash = '#' + (index + 1)
                    setPage(index + 1)
                }}>{index + 1}</li>
            })}
            <div className="next" onClick={nextPage}>下一篇</div>
        </div>
    }

    return (
        <div class="main">
            <div class="wrap">
                {<div class="write">
                    <div className="info">
                        {'<'}{term.title}{'>'} 由  <Avatar email={term.email} name={term.name}></Avatar> 添加于 <time>{dayjs(term.createTime).format('YYYY-MM-DD HH:mm')}</time> <a onClick={() => push(`/publish/${props.id}`)}>编辑合集</a>
                    </div>
                    {isEditor ? <div>
                        <button className="write-btn" onclick={() => setShow(0)}>
                            <i class="icon-font icon-writerin-f"> </i> 添加单篇</button>
                        <button className="write-btn" onclick={() => setShow(post.id)} style={{ background: 'var(--secondary)' }}>
                            <i class="icon-font icon-writerin-f"> </i> 编辑当前单篇</button>
                    </div> : null}
                </div>}

                <div className="post">
                    {post?.title ? <><h2>{post?.title}</h2>
                        {page$()}
                        <p ref={dom => {
                            if (dom) dom.innerHTML = marked(post?.content)
                        }}></p></> : <div>还没有添加分集</div>}
                </div>
                {page$()}
            </div>
            {
                show != null && <CenterSection comp={Publish} props={{
                    id: show > 0 ? show : null,
                    tag: term.id,
                    ptitle: term.title,
                    setShow
                }}></CenterSection>
            }
        </div >
    )
}