import { useState,useEffect } from "react";
import{CategoryScale, Chart, LinearScale, Tooltip} from 'chart.js/auto'
import PieChart from "./PieChart";
import './Table.css'
Chart.register(CategoryScale,LinearScale,Tooltip)

export default function Table(){

    const pendingUrl="http://localhost:8081/getPendingData"
     const completedUrl="http://localhost:8081/getCompletedData"
    const [pendingTableData,setPendingTableData]=useState()
    const[completedTableData,setCompletedTableData]=useState();

   useEffect(()=>async function fetchData(){
       const pendingResponse=await fetch(pendingUrl);
       const pendingData=await pendingResponse.json();
       setPendingTableData(prev=>{
        if(prev)
       return [...prev,pendingData];
      else
        return pendingData;
       }
       )
       const completedResponse=await fetch(completedUrl);
       const completedData=await completedResponse.json();
      setCompletedTableData(prev=>{
        if(prev)
       return [...prev,completedData];
      else
        return completedData;
       }
       )
  
    }
,[])
    return(
        <div>
            <div>
                <h1>Pending table data</h1>
            <table className="pending-table">
                <tr className="table-head">
                    <th>
                        id
                    </th>
                    <th>
                        Buyer price
                    </th>
                    <th>
                        Buyer Quantity
                    </th>
                    <th>
                        Seller Price
                    </th>
                    <th>
                        Seller Quantiy
                    </th>
                </tr>
                 {pendingTableData!==undefined&&pendingTableData.map(data=>{
                      return(   <tr key={data.id}> <td>{data.id}</td>
                        <td>{data.buyerPrice}</td>
                        <td>{data.buyerQty}</td>
                        <td>{data.sellerPrice}</td>
                        <td>{data.sellerQty}</td>
                        </tr>)
                 })
                }
             
            </table>
            </div>

            <div>
                <h1>Completed Table data</h1>
                <table>
                <tr>
                    <th>
                        id
                    </th>
                    <th>price</th>
                    <th>quantity</th>
                </tr>
            
                 {completedTableData!==undefined&&completedTableData.map(data=>{
                      return(   <tr key={data.cId}> <td>{data.cId}</td>
                        <td>{data.price}</td>
                        <td>{data.quantity}</td>
                       
                        </tr>)
                 })
                }
             
            </table>
            </div>
           <PieChart />
            
        </div>
    )
}