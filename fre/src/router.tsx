/*
  const routes = [
    {
        path: '/',
        element: import('./home/home'),
        loader: homeLoader
    },
    {
        path: '/sponsor',
        element: import('./sponsor/sponsor'),
        loader: sponsorLoader
    }
  ]

  <Router routes={routes} fallback={'loading...'}></Router>
 */
  import { useState, useEffect, Suspense, lazy, useMemo } from 'fre'

  function pathSlice(path) {
    const slice = [
      new RegExp(
        `${path.substr(0, 1) === '*' ? '' : '^'}${path
          .replace(/:[a-zA-Z]+/g, '([^/]+)')
          .replace(/\*/g, '')}${path.substr(-1) === '*' ? '' : '$'}`
      )
    ]
    const params = path.match(/:[a-zA-Z#0-9]+/g)
    slice.push(params ? params.map(name => name.substr(1)) : [])
    return slice
  }
  
  export function useRouter(routes) {
    const [url, setUrl] = useState(window.location.pathname)
  
    const parsedRoutes = useMemo(() => {
      return routes.map(item => ({
        route: pathSlice(item.path),
        handler: lazy(() => item.element),
        loader: item.loader
      }))
    }, [routes])
  
    useEffect(() => {
      const handlePopState = () => {
        setUrl(window.location.pathname)
      }
      window.addEventListener('popstate', handlePopState)
      return () => window.removeEventListener('popstate', handlePopState)
    }, [])
  
    return { url, routes: parsedRoutes }
  }
  
  export default function Router({ routes, fallback = <div>Loading...</div> }) {
    const { url, routes: parsedRoutes } = useRouter(routes)
    const [match, setMatch] = useState(null)
  
    useEffect(() => {
      const currentUrl = url === '' ? '/' : url
      let isMounted = true
  
      const matchedRoute = parsedRoutes.find(({ route }) => {
        const [reg] = route
        return currentUrl.match(reg)
      })
  
      if (!matchedRoute || !isMounted) {
        setMatch(null)
        return
      }
  
      const [reg, params] = matchedRoute.route
      const res = currentUrl.match(reg)
      const newProps = {}
      if (params.length > 0 && res) {
        params.forEach((prop, index) => (newProps[prop] = res[index + 1]))
      }
  
      const loadData = async () => {
        if (typeof matchedRoute.loader === 'function') {
          return await matchedRoute.loader()
        }
        return null
      }
  
      loadData().then(data => {
        if (isMounted) {
          setMatch({
            Component: matchedRoute.handler,
            props: { data, ...newProps }
          })
        }
      })
  
      return () => { isMounted = false }
    }, [url, parsedRoutes])
  
    return (
      <Suspense fallback={fallback}>
        {match ? <match.Component {...match.props} /> : fallback}
      </Suspense>
    )
  }
  
  export const push = (path) => {
    if (path.startsWith('.')) {
      path = window.location.pathname + path.slice(1)
    }
    history.pushState({}, '', path)
    window.dispatchEvent(new PopStateEvent('popstate'))
  }
  
  export const getPath = () => window.location.pathname