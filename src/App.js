import logo from './logo.svg';
import './App.css';
import Form from './pages/Form';
import Table from './pages/Table';
import { QueryClientProvider,QueryClient } from '@tanstack/react-query';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import RouteLayout from './pages/RouteLayout';
const routes=createBrowserRouter([
  {
    path:"/",element:<RouteLayout/>
    ,children:[
      {path:"/dataAdd",element:<Form/>,
        
      },
      {
        path:'/tableData',element:<Table/>
      }
    ]
  }
])
function App() {
  return (

    <QueryClientProvider client={new QueryClient()}>
      <RouterProvider router={routes}/>
    </QueryClientProvider>
  );
}



export default App;