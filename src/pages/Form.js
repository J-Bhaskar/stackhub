import {useRef} from 'react'
import { useNavigate } from 'react-router-dom';
export default function Form(){

    const buyerPrice=useRef();
    const buyerQty=useRef();
    const navigate=useNavigate();

    const baseUrl="http://localhost:8081/addPendingTable"

    async function handleSubmit(event){

        event.preventDefault();

        console.log(buyerPrice.current.value);
        console.log(buyerQty.current.value);
        const data={
            "buyerPrice":buyerPrice.current.value,
            "buyerQty":buyerQty.current.value
        }
        //const navigate=useNavigate();
       const response=await  fetch(baseUrl,{
        body:data,
        method:"post",
        headers: {
            'Content-Type': 'application/json',
          },
        body: JSON.stringify(data),
       })
       console.log(response);
       //navigate("/tableData")
       alert("Data added sucessfully...")
       navigate("/tableData")

  
    }
    return(
        <div>
            <style>
                {`
                    form {
                        max-width: 400px;
                        margin: 0 auto;
                        padding: 20px;
                        border: 1px solid #ccc;
                        border-radius: 8px;
                        background-color: #f9f9f9;
                    }

                    form div {
                        margin-bottom: 15px;
                    }

                    label {
                        display: block;
                        font-weight: bold;
                        margin-bottom: 5px;
                    }

                    input[type="text"] {
                        width: 100%;
                        padding: 8px;
                        border: 1px solid #ccc;
                        border-radius: 4px;
                        box-sizing: border-box;
                    }

                    button {
                        width: 100%;
                        padding: 10px;
                        background-color: #4CAF50;
                        color: white;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                        font-size: 16px;
                    }

                    button:hover {
                        background-color: #45a049;
                    }
                    h1{
                    text-align:center;
                    }
                `}
            </style>
            <h1>Add Data</h1>
            <form onSubmit={handleSubmit} method="post" className='mt-5'>
                <div>
                    <label>Buyer Price : </label>
                    <input type="text" name="buyerPrice" ref={buyerPrice}/>
                </div>
                <div>
                    <label>Buyer Qantity : </label>
                    <input type="text" name="buyerQty" ref={buyerQty}/>
                </div>
              <button >Submit</button>
            </form>
        </div>
    )
}