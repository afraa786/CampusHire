import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import HrPage from './components/Homepages/HrPage'
import CreatePost from './components/OtherComponents/CreatePost'

const router = createBrowserRouter([
    {
        path: '/',
        element: <HrPage />,
    },
    {
        path: '/post',
        element: <CreatePost />,
    },
])

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <RouterProvider router={router} />
    </StrictMode>
)
