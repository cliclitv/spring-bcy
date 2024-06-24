import { useState, useEffect } from "fre"
import { push } from "../use-route"
import { addTerm, getTermDetail, getUserInfo, updatePost } from "../util/api"
import './publish.css'



export default function Publish(props) {

    const user = getUserInfo()
    const [post, setPost] = useState({ title: "", cat: "原耽", author: user.name, createTime: "", content: "", uid: user.id })

    useEffect(() => {
        getTermDetail(props.id).then(res => {
            setPost(res.data)
        })
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
            console.log(res)
        })
    }

    return (

        <div className="upload wrap">
            <div className="title">
                <input type="text" placeholder="请输入文名" value={post.title} onInput={e => change('title', e.target.value)} />
            </div>
            {
                post.cat === '藏书馆' && <div className="title">
                    <input type="text" placeholder="请输入原作者名" value={post.author} onInput={e => change('author', e.target.value)} />
                </div>
            }
            <textarea spellcheck="false" placeholder="请输入文案，支持 markdown 语法" value={post.content} onInput={e => change('content', e.target.value)}></textarea>
            <div className="options">
                <select onInput={e => change('cat', e.target.value)}>
                    <option value="藏书馆" selected={post.cat === '藏书馆'}>藏书馆</option>
                    <option value="原耽" selected={post.cat === '原耽'}>原耽</option>
                    <option value="同人" selected={post.cat === '同人'}>同人</option>
                </select>
                {props.id > 0 && <input type="text" value={post.createTime} onInput={e => change('time', e.target.value)} />}
            </div>
            <div className="tags">


            </div>
            <div className="submit" onClick={submit}>
                <button>添加</button>
            </div>
        </div>
    )
}
