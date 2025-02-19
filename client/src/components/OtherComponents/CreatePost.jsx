import React from 'react'
import { AppliedData } from '../NavComponents/FakeD'
import Testing from './Filter'

const CreatePost = () => {
  
  return (
    <div >
      <textarea className='border-[0.2vw] hover:border-yellow-200 rounded-2xl  w-full min-h-[22vh] max-h-[22vh] outline-none bg-black p-4 resize-none' name="" id="">
      </textarea>
      <button className='bg-black border-2 rounded-lg px-3 py-1 w-full text-xl font-bold border-green-700
       hover:border-green-400 duration-300 ease-in-out text-slate-500 hover:text-white
       active:bg-slate-800 
        '>+ Create</button>
      <div className=' text-slate-500 text-xl w-3 mt-6 flex flex-wrap hover:text-white duration-300 ease-in-out hover:-cursor-pointer'>
        <Testing></Testing>
      </div>
    </div>
  )
}

export default CreatePost