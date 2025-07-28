import { useEffect, useState, Fragment } from 'fre'
import Avatar from '../component/avatar/avatar'
import { push } from '../router'
import { addReply, getReplys, getUserInfo } from '../util/api'
import './reply.css'
import Markdown from '../component/md/md'
import { removeSuo } from '../util/avatar'

export default function Reply({ post }) {
    const [reply, setReply] = useState('')
    const [replys, setReplys] = useState([])
    useEffect(() => {
        getReplys(post.id, 0).then(res => {
            setReplys((res as any).data || [])
        })

    }, [post.id])

    function submit() {
        if (reply.length < 1) {
            return
        }

        addReply({
            pid: post.id,
            content: reply,
            cid: 0,
            uid: user.id
        } as any).then((res: any) => {
            alert(res.msg)
        })

    }
    const user = getUserInfo() || {}
    return <div>
        <div class="reply wrap">

            <h1>共有{replys ? replys.length : 0}条回帖</h1>
            {replys && replys.map(item => {
                //@ts-ignore
                const time = dayjs(item.time).format('MM-DD-YYYY')
                return <div className="reply-item">
                    <Avatar email={item.email}></Avatar>
                    <div className="reply-block">
                        <p>{item.name}</p>
                        <p>{item.content}</p>
                        <p>{time}</p>
                    </div>

                </div>
            })}
            <div className="reply-input">
                <Avatar email={user.email}></Avatar>
                <input type="text" placeholder="发个回帖见证一下" onInput={(e) => setReply(e.target.value)} />
                {user.id ? <button onClick={submit}>发送</button> : <button onclick={() => push('/login')}>登录</button>}
            </div>
        </div>
    </div>
}