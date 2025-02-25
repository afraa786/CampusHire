import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HrPage from './components/Homepages/HrPage'
import CreatePost from './components/OtherComponents/CreatePost'
import Login from './Auth/Login'
import Signup from './Auth/Signup'

const router = createBrowserRouter([
    {
        path: '/',
        element: <HrPage />,
    },
    {
        path: '/post',
        element: <CreatePost />,
    },
    {
        path:"/Login",
        element:<Login/>,
    },
    {
        path:"/Signup",
        element:<Signup/>
    }
])

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <RouterProvider router={router} />
    </StrictMode>
)
