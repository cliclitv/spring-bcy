import { useState, useEffect } from "fre"
import { push } from "../use-route"
import { addTerm, getTermDetail, getUserInfo, updatePost } from "../util/api"
import './publish.css'



export default function Publish(props) {

    const user = getUserInfo()
    const [post, setPost] = useState({ title: "", cat: "原耽", author: user.name, createTime: "", content: "", uid: user.id })

    useEffect(() => {
        if (props.id > 0) {
            getTermDetail(props.id).then(res => {
                setPost(res.data)
            })
        }
    }, [])

    function change(key, val) {
        setPost({
            ...post,
            [key as any]: val,
        } as any)
    }

    function submit() {
        if (!post.title || !post.content || !post.cat || !post.author) {
            alert("都要填")
            return
        }

        addTerm(post).then(res => {
            alert(res.msg)
            window.history.back()
        })
    }

    return (

        <div className="upload wrap">
            <div className="title">
                <input type="text" placeholder="请输合集" value={post.title} onInput={e => change('title', e.target.value)} />
            </div>
            {
                post.cat === '转载' && <div className="title">
                    <input type="text" placeholder="请输入原作者名" value={post.author} onInput={e => change('author', e.target.value)} />
                </div>
            }
            <textarea spellcheck="false" placeholder="请输入文案，支持 markdown 语法" value={post.content} onInput={e => change('content', e.target.value)}></textarea>
            <div className="options">
                <select onInput={e => change('cat', e.target.value)}>
                    <option value="绘画" selected={post.cat === '绘画'}>绘画</option>
                    <option value="写作" selected={post.cat === '写作'}>写作</option>
                    <option value="COSPLAY" selected={post.cat === 'COSPLAY'}>COSPLAY</option>
                    <option value="游戏" selected={post.cat === '游戏'}>游戏</option>
                    <option value="影音" selected={post.cat === '影音'}>影音</option>
                    <option value="技术" selected={post.cat === '技术'}>技术</option>
                    <option value="其它" selected={post.cat === '其它'}>其它</option>
                </select>
                {props.id > 0 && <input type="text" value={post.createTime} onInput={e => change('time', e.target.value)} />}
            </div>
            <div className="tags">


            </div>
            <div className="submit" onClick={submit}>
                <button>发布</button>
            </div>
        </div>
    )
}
