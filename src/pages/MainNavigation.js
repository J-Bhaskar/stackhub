import { Link } from "react-router-dom";


export default function MainNavgation(){

    return(
        <ul style={{
            display:'flex',flexDirection:'row',gap:"200px",width:'100%'
            ,height:"40px",listStyle:'none',backgroundColor:'black',padding:'5px'
        }}>
            <li  ><Link style={{color:'white',fontWeight:'bold',textDecoration:'none' }} to="/dataAdd">Add Form</Link></li>
            <li ><Link style={{color:'white',fontWeight:'bold',textDecoration:'none'}}to="/tableData">Table Data</Link></li>
        </ul>
    )
}