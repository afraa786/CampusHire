import React,{useEffect, createContext,useState} from 'react'
import axios from 'axios'
import HrPage from './components/Homepages/HrPage';

export const DataContext=createContext(null);


const App = () => {

  const [data, setData] = useState('')
  const fetchData=async()=>{
    try {
      const response=await axios.get('/api/placements/all')
      setData(response.data)
    } catch (error) {
      console.log(error.message)
    }
    
  }

useEffect(()=>{
  fetchData()
},[])
  return (
    <>
    <DataContext.Provider value={data}>
      <HrPage></HrPage>
    </DataContext.Provider>
    </>
  )
}

export default App