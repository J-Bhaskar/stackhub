import { Outlet } from "react-router-dom";
import MainNavgation from "./MainNavigation";


export default function RouteLayout(){

    return(
        <>
        <MainNavgation/>
          <Outlet/>
        </>
    )
}