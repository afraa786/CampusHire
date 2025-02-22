import React,{useState} from 'react'
import { AppliedData } from '../NavComponents/FakeD';
import UserIcon from '@/assets/user.png'
import {motion,AnimatePresence} from 'motion/react'

const ViewEpost = () => {

    const [activeIndex, setActiveIndex] = useState(null);
  
  const Data=AppliedData
  return (
    
      <div className=' flex bg-stone-200 w-full h-full rounded-2xl p-2 flex-col'>
        <div className=' text-black font-mono flex '><h1 className='
        text-[2vw] font-black'>Full stack developer</h1></div>
        <div className='w-full bg-black h-[2px] rounded-3xl'></div>
        <div className='overflow-scroll pb-60'>


        {Data.map((item, idx) => (


          <motion.div
                    className='bg-black text-white p-2 m-2 rounded flex item-center flex-row'
                    key={idx} 
                    style={{ position: "relative" }}
                    onHoverStart={() => setActiveIndex(idx)}
                    onHoverEnd={() => setActiveIndex(null)}
                  >
                    <img className=' rounded-full h-8 mt-2' src={UserIcon} alt="" />
                    <motion.h1
                      style={{ 
                        cursor: "pointer", 
                        margin: 0,
                        padding: "1vw",
                        position: "relative",
                        color:'white'
                      }}
                      whileHover={{ color: "#2196f3" }}
                    >
                      
                      {item.name}
                    </motion.h1>
          
                    <AnimatePresence>
                      {activeIndex === idx && (
                        <motion.div
                          initial={{ opacity: 0, y: 10 }}
                          animate={{ opacity: 1, y: 0 }}
                          exit={{ opacity: 0, y: 10 }}
                          transition={{ duration: 0.2 }}
                          style={{
                            position: "absolute",
                            top: "100%",
                            left: 0,
                            background: "white",
                            padding: "1rem",
                            borderRadius: "8px",
                            boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
                            maxWidth: "35vw",
                            minHeight:"43vh",
                            zIndex: 10
                          }}
                          // Prevent hover end when moving to dropdown
                          onHoverStart={(e) => e.stopPropagation()}
                        >
                          <div className=' text-black font-sans bg-yellow-400 rounded-xl p-2'>
                          <h2 className=' font-extrabold m-2 font-sans'>Description : </h2>
                          <p className='pl-6 font-sans'>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad et eos excepturi minima aspernatur autem, assumenda ducimus obcaecati nulla, commodi impedit quia sunt id molestias voluptas officia pariatur tenetur alias.</p>
                          <h1 className=' font-extrabold m-2 font-sans'> Branch : {item.branch}</h1>
                          <h1 className=' font-extrabold m-2 font-sans'>Active Backlog : {item.activeBacklog}</h1>
                          <h1 className=' font-extrabold m-2 font-sans'>Backlog : {item.backlog}</h1>
                          </div>

                        </motion.div>
                      )}
                    </AnimatePresence>
                  </motion.div>


        ))}



        </div>
      </div>
  )
}

export default ViewEpost