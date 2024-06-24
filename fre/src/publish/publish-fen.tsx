import { useState, useEffect } from "fre"
import { push } from "../use-route"
import { addTerm, addpost, getPostDetail, getTermDetail, getUserInfo, updatePost } from "../util/api"
import './publish.css'



export default function Publish(props) {

    const user = getUserInfo()
    const [post, setPost] = useState({ title: "", tag: props.tag,  createTime: "", content: "", uid: user.id })

    useEffect(() => {
        if (props.id > 0) {
            getPostDetail(props.id).then(res => {
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

        addpost(post).then(res => {
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
                <div>合集: {props.ptitle}</div>
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
