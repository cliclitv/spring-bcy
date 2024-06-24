import { render, Fragment, } from "fre"
import { push, useRoutes } from './use-route'
import './app.css'
import Header from "./header/header"
import Footer from "./header/footer"
import { getToken } from "./util/post"
import { getUserInfo } from "./util/api"

const routes = {
    '/': import('./home/home'),
    '/login': import('./login/login'),
    '/register': import('./login/register'),
    '/publish/:id': import('./home/home'),
}

const App = (props) => {
    let route = useRoutes(routes)
    let token = getToken()
    let user = getUserInfo()
    if(!user){
        push('/login')
    }
    if (token) {
        // 不需要登录，直接写 localstorage
        window.localStorage.setItem('token', token)
    }
    return <main>
        {!token && <Header />}
        <div class="container">{route}</div>
        {!token && <Footer />}
    </main>
}

render(<App />, document.getElementById("app"))


