import React,{useContext,useState} from 'react'
import { DataContext } from '@/App'
import HrNavBar from '../NavComponents/HrNavBar'
import CreatePost from '../OtherComponents/CreatePost'
const HrPage = () => {

  const newData=useContext(DataContext)
  console.log(newData)

  return (
    <div className='bigone h-screen w-screen bg-black'>
      <HrNavBar></HrNavBar>
      <div className='h-[1px] w-screen bg-slate-400 mb-4'></div>

      <div className=' h-[83.5vh] flex justify-between px-3 flex-wrap'>

        <div className='posts w-[48vw] h-[80.5vh] rounded-3xl border-2  text-white hover:border-sky-300 
        hover:text-sky-300 '>
        <p className=' font-extrabold text-4xl font-mono m-1 ml-3 mt-3 '>Existing post</p>
        <div className=' h-[1px] bg-white my-1 mb-3'></div>

        <div className=' w-[48vw] h-[66.5vh] flex justify-center items-center'>
        <div className=' w-[45vw]  h-[66.5vh] '>
          {/* Here comes View existing post */}
        </div>
        </div>
        </div>

        <div className='posts w-[48vw] h-[80.5vh] rounded-3xl border-2 text-white hover:border-sky-300
        hover:text-sky-300'>
        <p className=' font-extrabold text-4xl font-mono m-1 ml-3 mt-3 '>New post</p>
        <div className=' h-[1px] bg-white my-1 mb-3'></div>

        <div className=' w-[48vw] h-[66.5vh] flex justify-center items-center'>
        <div className=' w-[45vw]  h-[66.5vh] '>
          {/* Here comes New post */}
          <CreatePost />
        </div>
        </div>
        </div>

      </div>
    </div>
  )
}

export default HrPage