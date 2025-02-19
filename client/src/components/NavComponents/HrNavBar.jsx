import React,{useState} from 'react'
import {motion,AnimatePresence} from 'motion/react'
import Contributers from './Contributers';
import UserIcon from '@/assets/user.png'
import MenuIcon from '@/assets/burger.png';
const HrNavBar = () => {
  const [activeIndex, setActiveIndex] = useState(null);
  
  const navItems = [
    { id: 1, title: "Home", content: "Home Content" },
    { id: 2, title: "View existing", content: "About Content" },
    { id: 3, title: "Create post", content: "hello" },
    { id: 4, title: "Contributer's", content: <Contributers/> },
  ];

  return ( 
    <div>
    <nav className='navc' style={{
      display: "flex",
      gap: "2rem",
      padding: "1rem",
      position: "relative",
      width:"100%",
      flexWrap:"wrap",
      backgroundColor:"black", 
      justifyContent:"space-between",
    }}>
      <div className=' flex flex-wrap '>
        <img src={MenuIcon} className='hamburger hidden h-11 px-3 mr-4 hover:cursor-pointer' alt="" />
      <div className=' font-mono font-extrabold text-xl px-4 py-1 bg-white text-black rounded-xl flex justify-center items-center hover:cursor-pointer'>
        <p>LOGO</p>
      </div>

      <div className='nextTologo flex flex-wrap'>
      {navItems.map((item, index) => (
        <motion.div 
          key={item.id} 
          style={{ position: "relative" }}
          onHoverStart={() => setActiveIndex(index)}
          onHoverEnd={() => setActiveIndex(null)}
        >
          <motion.h1
            style={{ 
              cursor: "pointer", 
              margin: 0,
              padding: "0.5rem 1rem",
              position: "relative",
              color:'white'
            }}
            whileHover={{ color: "#2196f3" }}
          >
            {item.title}
          </motion.h1>

          <AnimatePresence>
            {activeIndex === index && (
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
                  minWidth: "200px",
                  zIndex: 10
                }}
                // Prevent hover end when moving to dropdown
                onHoverStart={(e) => e.stopPropagation()}
              >
                {item.content}
              </motion.div>
            )}
          </AnimatePresence>
        </motion.div>
      ))}
      </div>
      </div>

      <div className='text-white flex'>
      <p className=' hover:text-blue-400 cursor-pointer px-3 py-1.5'>Sign up</p>
      <p className=' signLog hover:text-blue-400 cursor-pointer px-3 py-1.5'>Log in</p>
      <img src={UserIcon} className='h-10 rounded-full' alt="" />
      </div>
    </nav>
    </div>
  );
}

export default HrNavBar