import React,{useEffect} from 'react'
import axios from 'axios'

const App = () => {

  const fetchData=async()=>{
    try {
      const responce=await axios.get('/api/placements/all')
      console.log(responce.data)
    } catch (error) {
      console.log(error.message)
    }
    
  }

useEffect(()=>{
  fetchData()
},[])
  return (
    <div>App</div>
  )
}

export default App