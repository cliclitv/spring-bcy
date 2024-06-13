import { useEffect, useState } from 'fre'
import './home.css'

import { push } from '../use-route'

import Register from '../login/register'
import { getTerms, getUserInfo } from '../util/api'


const user = getUserInfo()

export default function App(props) {
    const [terms ,setTerms] = useState([])

    useEffect(()=>{
        getTerms(user.id).then(res=>{
            console.log(res)
        })
    },[])
    return (
        <div class="wrap">
            <ul>
                <li>挖坑</li>
                <li>填坑</li>
            </ul>
        </div>

    )
}