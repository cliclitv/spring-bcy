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
                <button className="write-btn">挖坑</button>
            </div>
            <section class="table">
                <table>
                    <thead>
                        <tr>
                            <th>列标题1</th>
                            <th>列标题2</th>
                            <th>列标题3</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>行1，列1</td>
                            <td>行1，列2</td>
                            <td>行1，列3</td>
                        </tr>
                        <tr>
                            <td>行2，列1</td>
                            <td>行2，列2</td>
                            <td>行2，列3</td>
                        </tr>
                    </tbody>
                </table>
            </section>

        </div>

    )
}