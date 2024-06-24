import { useState, useEffect } from "fre"
import { push } from "../use-route"
import { addPost, getGonggao, getPostDetail, getUserInfo, updatePost } from "../util/api"
import './draft.css'



export default function Upload(props) {
    const [post, setPost] = useState({ title: "", cat: "原创", createTime: "", content: "" })
    const user = getUserInfo()
    const [tag, setTag] = useState([])


    useEffect(() => {
        if (props.id > 0) {
            getPostDetail(props.id).then((res: any) => {
                setPost(res.result)
                setTag(res.result.tag.split(' '))
            })

        } else {
            // 新增
        }
        if (user) {
            getPostB("", "", 1, 200, "", user?.id).then(res => {
                setDraft(res.posts)
            })
            getGonggao().then(res => {
                let t = tags.concat(res.result.videos.split('\n'))
                console.log(t)
                setTags([...new Set(t)])
            })
        }
    }, [props.id])

    function change(key, val) {
        setPost({
            ...post,
            [key as any]: val,
        } as any)
    }

    function selectTag(item) {
        let a = tag
        if (tag.includes(item)) {
            a = tag.filter(i => i != item)
        } else {
            a = tag.concat([item])
        }
        setTag(a)
        change('tag', a.join(' '))
    }

    function submit() {
        if (!post.title || !post.content || !post.cat || !post.tag) {
            alert("都要填")
            return
        }
        if (props.id > 0) {
            updatePost(post as any).then(res => {
                alert((res.msg || '搞定^_^') + ' gv' + res.result.id)
            })
        } else {
            console.log(post)
            addPost(post as any).then(res => {
                alert((res.msg || '搞定^_^'))
            })
        }
    }

    return (

        <div className="upload wrap">
            <div className="title">
                <input type="text" placeholder="请输入文名" value={post.title} onInput={e => change('title', e.target.value)} />
            </div>
            {
                post.cat === '藏书馆' && <div className="title">
                    <input type="text" placeholder="请输入作者笔名" value={post.title} onInput={e => change('author', e.target.value)} />
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
