import { render, Fragment, } from "fre"
import Router, { push } from './router'
import './app.css'
import Header from "./header/header"
import Footer from "./header/footer"
import { getToken } from "./util/post"
import { getUserInfo } from "./util/api"
import { homeLoader } from "./home/home"

const routes = [
    {
        path: '/',
        element: import('./home/home'),
        loader: homeLoader
    },
    {
        path: '/:action',
        element: import('./home/home'),
        loader: homeLoader
    }
]

const App = (props) => {
    let user = getUserInfo()
    if(!user){
        push('/login')
    }

    return <main>
        <Header />
        <Router routes={routes} fallback={<div>Loading page...</div>} />
        <Footer />
    </main>
}

render(<App />, document.getElementById("app"))


