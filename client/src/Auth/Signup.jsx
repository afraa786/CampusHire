import React from 'react';
import { motion } from 'framer-motion'; // Import motion from framer-motion
import HireImg from '@/assets/Login-hire.svg';
import { useNavigate } from 'react-router-dom';

const Signup = () => {
  const navigate = useNavigate();
  return (
    <div className='bg-gradient-to-t from-slate-800 from-10% via-purple-300 via-100% text-black flex justify-between items-center h-screen px-4'>
      <div className='w-[43vw] h-[80vh] bg-white rounded-3xl border-4 border-gray-300 flex flex-col items-center justify-between'>
        <h1 className=' text-[2.5vw] font-bold text-purple-400'>Campus-hire</h1>
        <div className='flex flex-col justify-center items-center border-4 w-[40vw] h-[30vh] rounded-3xl hover:border-purple-300 '>
        <input type="text" placeholder='Email' className=' border-2 w-[35vw] h-[9vh] outline-none rounded-3xl px-5 text-xl font-mono' />
        <input type="text" placeholder='Password' className=' border-2 w-[35vw] h-[9vh] outline-none rounded-3xl px-5 text-xl font-mono mt-[1vw] ' />
        </div>

        <div className=' flex flex-col items-center h-[30vh]'>
        <button className=' text-3xl bg-purple-400 text-white px-6 py-2 rounded-3xl m-6'> Signup </button>
        <p>Already have an account?</p>
        <p onClick={
          ()=>{
            navigate('/Login')
          }
        } className=' text-purple-400 hover:text-purple-500 hover:cursor-pointer'>Login</p>
        </div>

      </div>

      {/* Wrap the img with motion and add floating animation */}
      <motion.img
        className='h-[80vh] rounded-[190px]'
        src={HireImg}
        alt="Hire Image"
        animate={{
          y: [0, -20, 0], // Move the image up and down
        }}
        transition={{
          duration: 2, // Animation duration
          repeat: Infinity, // Loop the animation infinitely
          ease: 'easeInOut', // Smooth easing
        }}
      />
    </div>
  );
};

export default Signup;