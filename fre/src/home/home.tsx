import { useEffect, useState } from 'fre'
import './home.css'
import Nav from '../header/nav'

export function homeLoader(){
    return {
        user:'yisar'
    }
}

export default function App(props) {
    return (
        <div>
            
            <Nav></Nav>
        </div>

    )
}