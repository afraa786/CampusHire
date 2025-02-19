import React, { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';

function HoverDiv() {
  const [isParentHovered, setIsParentHovered] = useState(false);
  const [isChildHovered, setIsChildHovered] = useState(false);
  const [Colora1, setColora1] = useState('white')
  const [Colora2, setColora2] = useState('white')
  const [Colora3, setColora3] = useState('white')
  const [Colora4, setColora4] = useState('white')
  const [Colora5, setColora5] = useState('white')

  const [Branch1, setBranch1] = useState('white')
  const [Branch2, setBranch2] = useState('white')
  const [Branch3, setBranch3] = useState('white')
  const [Branch4, setBranch4] = useState('white')
  const [Branch5, setBranch5] = useState('white')  

  const [Cgpa1, setCgpa1] = useState('white')
  const [Cgpa2, setCgpa2] = useState('white')
  const [Cgpa3, setCgpa3] = useState('white')
  const [Cgpa4, setCgpa4] = useState('white')
  const [Cgpa5, setCgpa5] = useState('white') 

  const [Exp1, setExp1] = useState('white')
  const [Exp2, setExp2] = useState('white')
  const [Exp3, setExp3] = useState('white')
  const [Exp4, setExp4] = useState('white')
  const [Exp5, setExp5] = useState('white') 
  

  // Determine if the child should be visible
  const isChildVisible = isParentHovered || isChildHovered;

  return (
    <div style={{ position: 'relative', display: 'inline-block' }}>
      {/* Parent Div */}
      <motion.div
        style={{
          width: '10px',
          height: '200px',
          borderRadius: '8px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          cursor: 'pointer',
        }}
        onHoverStart={() => setIsParentHovered(true)}
        onHoverEnd={() => setIsParentHovered(false)}
      >
        <div className=' flex flex-wrap'>
        <p className=' hover:cursor-pointer'>F</p>
        <p className=' hover:cursor-pointer'>I</p>
        <p className=' hover:cursor-pointer'>L</p>
        <p className=' hover:cursor-pointer'>T</p>
        <p className=' hover:cursor-pointer'>E</p>
        <p className=' hover:cursor-pointer'>R</p>
        </div>
      </motion.div>

      {/* Sub-Div */}
      <AnimatePresence>
        {isChildVisible && (
          <motion.div
            style={{
              position: 'absolute',
              top: 0,
              left: '100%', // Position to the right of the parent
              marginLeft: '1px', // Add some spacing
              width: '45vw',
              height: '200px',
              borderWidth:'2px',
              borderColor:'skyblue',
              borderRadius: '8px',
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'center',
              cursor: 'pointer',
            }}
            initial={{ opacity: 0, x: -10 }} // Initial animation state
            animate={{ opacity: 1, x: 0 }} // Animate in
            exit={{ opacity: 0, x: -10 }} // Animate out
            transition={{ duration: 0.2 }} // Animation duration
            onHoverStart={() => setIsChildHovered(true)}
            onHoverEnd={() => setIsChildHovered(false)}
          >  
            <div className=' flex flex-col w-full justify-center items-center '>
              <h1>Filter</h1>
              <div className='age w-full bg-white text-black  flex h-8 justify-center items-center mt-1 rounded-2xl'>
                <p className=' ages bg-zinc-500 ml-4 mr-4 font-mono text-base hover:bg-blue-400 px-2 rounded-3xl'>
                  Age:
                </p>



                
                <p
                onClick={()=>{
                  Colora1==='white'?setColora1('#9BE2EA'):setColora1('white');
                  setColora2('white');
                  setColora3('white');
                  setColora4('white');
                  setColora5('white');
                }}
                style={{
                  background:Colora1
                }}
                 className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>18-30</p>




                <p   onClick={()=>{
                  Colora2==='white'?setColora2('#9BE2EA'):setColora2('white');
                  setColora1('white');
                  setColora3('white');
                  setColora4('white');
                  setColora5('white');
                }}
                style={{
                  background:Colora2
                }}
                 className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>18-40</p>



                <p   onClick={()=>{
                  Colora3==='white'?setColora3('#9BE2EA'):setColora3('white');
                  setColora2('white');
                  setColora1('white');
                  setColora4('white');
                  setColora5('white');

                }}
                style={{
                  background:Colora3
                }}
                 className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>18-50</p>



                <p  onClick={()=>{
                  Colora4==='white'?setColora4('#9BE2EA'):setColora4('white');
                  setColora2('white');
                  setColora3('white');
                  setColora1('white');
                  setColora5('white');
                }}
                style={{
                  background:Colora4
                }} 
                 className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>18-60</p>



                <p   onClick={()=>{
                  Colora5==='white'?setColora5('#9BE2EA'):setColora5('white');
                  setColora2('white');
                  setColora3('white');
                  setColora4('white');
                  setColora1('white');
                }}
                style={{
                  background:Colora5
                }}
                 className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>60+</p>



              </div>

              <div className='w-full bg-white text-black  flex h-8 justify-center items-center mt-1 rounded-2xl'>
              <p className=' ages bg-zinc-500 ml-4 mr-4 font-mono text-base hover:bg-blue-400 px-2 rounded-3xl'>
                  Branch:
                </p>


                <p onClick={()=>{
                  Branch1==='white'?setBranch1('#9BE2EA'):setBranch1('white')
                  setBranch2('white');
                  setBranch3('white');
                  setBranch4('white');
                  setBranch5('white');

                }} style={{
                  backgroundColor:Branch1
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl '>CS</p>



                <p onClick={()=>{
                  Branch2==='white'? setBranch2('#9BE2EA'):setBranch2('white')
                  setBranch1('white');
                  setBranch3('white');
                  setBranch4('white');
                  setBranch5('white');
                }} style={{
                  backgroundColor:Branch2
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl '>Mech</p>



                <p onClick={()=>{
                  Branch3==='white'? setBranch3('#9BE2EA'):setBranch3('white')
                  setBranch2('white');
                  setBranch1('white');
                  setBranch4('white');
                  setBranch5('white');
                }} style={{
                  backgroundColor:Branch3
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl '>Elec</p>



                <p onClick={()=>{
                  Branch4==='white'? setBranch4('#9BE2EA'):setBranch4('white')
                  setBranch2('white');
                  setBranch3('white');
                  setBranch1('white');
                  setBranch5('white');
                }} style={{
                  backgroundColor:Branch4
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl '>Civil</p>



                <p onClick={()=>{
                  Branch5==='white'? setBranch5('#9BE2EA'):setBranch5('white')
                  setBranch2('white');
                  setBranch3('white');
                  setBranch4('white');
                  setBranch1('white');
                }} style={{
                  backgroundColor:Branch5
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl '>IOT</p>



              </div>

              <div className='w-full bg-white text-black  flex h-8 justify-center items-center mt-1 rounded-2xl'>
              <p className=' ages bg-zinc-500 ml-4 mr-4 font-mono text-base hover:bg-blue-400 px-2 rounded-3xl'>
                  Cgpa:
                </p>


                <p onClick={()=>{
                  Cgpa1==='white'?setCgpa1('#9BE2EA'):setCgpa1('white')
                  setCgpa2('white');
                  setCgpa3('white');
                  setCgpa4('white');
                  setCgpa5('white');

                }} style={{
                  backgroundColor:Cgpa1
                }}
                 className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>5-10</p>


                <p onClick={()=>{
                  Cgpa2==='white'?setCgpa2('#9BE2EA'):setCgpa2('white')
                  setCgpa1('white');
                  setCgpa3('white');
                  setCgpa4('white');
                  setCgpa5('white');

                }} style={{
                  backgroundColor:Cgpa2
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>6-10</p>



                <p onClick={()=>{
                  Cgpa3==='white'?setCgpa3('#9BE2EA'):setCgpa3('white')
                  setCgpa2('white');
                  setCgpa1('white');
                  setCgpa4('white');
                  setCgpa5('white');

                }} style={{
                  backgroundColor:Cgpa3
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>7-10</p>



                <p onClick={()=>{
                  Cgpa4==='white'?setCgpa4('#9BE2EA'):setCgpa4('white')
                  setCgpa2('white');
                  setCgpa3('white');
                  setCgpa1('white');
                  setCgpa5('white');

                }} style={{
                  backgroundColor:Cgpa4
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>8-10</p>



                <p onClick={()=>{
                  Cgpa5==='white'?setCgpa5('#9BE2EA'):setCgpa5('white')
                  setCgpa2('white');
                  setCgpa3('white');
                  setCgpa4('white');
                  setCgpa1('white');

                }} style={{
                  backgroundColor:Cgpa5
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>9-10</p>
              </div>

              <div className='w-full bg-white text-black  flex h-8 justify-center items-center mt-1 rounded-2xl'>
              <p className=' ages bg-zinc-500 ml-4 mr-4 font-mono text-base hover:bg-blue-400 px-2 rounded-3xl'>
                  Exp:
                </p>
                <p onClick={()=>{
                  Exp1==='white'?setExp1('#9BE2EA'):setExp1('white')
                  setExp2('white');
                  setExp3('white');
                  setExp4('white');
                  setExp5('white');

                }} style={{
                  backgroundColor:Exp1
                }}
                className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>18-30</p>


                <p onClick={()=>{
                  Exp2==='white'?setExp2('#9BE2EA'):setExp2('white')
                  setExp1('white');
                  setExp3('white');
                  setExp4('white');
                  setExp5('white');

                }} style={{
                  backgroundColor:Exp2
                }}  className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>0-10</p>


                <p onClick={()=>{
                  Exp3==='white'?setExp3('#9BE2EA'):setExp3('white')
                  setExp2('white');
                  setExp1('white');
                  setExp4('white');
                  setExp5('white');

                }} style={{
                  backgroundColor:Exp3
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>1-10</p>
                <p onClick={()=>{
                  Exp4==='white'?setExp4('#9BE2EA'):setExp4('white')
                  setExp2('white');
                  setExp3('white');
                  setExp1('white');
                  setExp5('white');

                }} style={{
                  backgroundColor:Exp4
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>2-10</p>
                <p onClick={()=>{
                  Exp5==='white'?setExp5('#9BE2EA'):setExp5('white')
                  setExp2('white');
                  setExp3('white');
                  setExp4('white');
                  setExp1('white');

                }} style={{
                  backgroundColor:Exp5
                }} className=' ages border-2 border-sky-500 ml-4 mr-4 font-mono text-base hover:border-green-400 px-2 rounded-3xl active:bg-sky-500'>4-10</p>
              </div>

            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
}

export default HoverDiv;