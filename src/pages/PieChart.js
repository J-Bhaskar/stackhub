import { useEffect, useState } from 'react';
import {Pie} from 'react-chartjs-2'
import {useQuery} from '@tanstack/react-query'

export default function PieChart(){

  const quantity=  useQuery({
        queryFn:getQuantity,
        
    })
    console.log(quantity.data)
 
 
    return(
        <div style={{height:"300px",width:"500px"}}>
            {quantity.isPending&&<h1>Loading the pie chart....</h1>}
            {!quantity.isPending&&<Pie
            data={{
                labels:["Completed Table","Pending Table"],
                datasets:[{
                    label:"Table data",
                    data:[quantity.data["pendingQuantity"],quantity.data["completedQuantity"]],
                    backgroundColor:["green",
                        "red"
                    ],
                    height:"100px"
                }]
        
                
            }} options={{plugins:{
                title:{
                    display:true,
                    text:"Pending and order table"
                }
            }}}></Pie>}
        </div>
    )
}
async function getQuantity(){
    const response1 =await  fetch("http://localhost:8081/getPendingAndCompleteQuantity");
    const quantity=await response1.json();
 
    return quantity
   
}