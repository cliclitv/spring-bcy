import { useEffect, useState } from 'fre'
import './home.css'

import { push } from '../use-route'

import Register from '../login/register'
import { getTerms, getUserInfo } from '../util/api'


const user = getUserInfo()

export default function App(props) {
    const [terms, setTerms] = useState([])

    useEffect(() => {
        getTerms(user.id).then(res => {
            console.log(res)
        })
    }, [])
    return (
        <div class="wrap">
            <div class="write">
                <div></div>
                <button className="write-btn" onclick={()=>push('/draft/0')}>挖坑</button>
            </div>
        </div>

    )
}