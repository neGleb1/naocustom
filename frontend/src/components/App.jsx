import {useState} from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import RenderOnAuthenticated from "./utilComponents/RenderOnAuthenticated";
import RenderOnRole from "./utilComponents/RenderOnRole";
import Main from "./main/Main";
import MerchantPage from "./merchant/MerchantPage";
import About from "./pages/About";
import NotFound from "./pages/NotFound";
import Basket from "./pages/Basket";
import Careers from "./pages/Careers";
import Contacts from "./pages/Contacts";
import Delivery from "./pages/Delivery";
import Help from "./pages/Help";
import OrderTrack from "./pages/OrderTrack";
import Privacy from "./pages/Privacy";
import Terms from "./pages/Terms";
import Header from "./header/Header";
import Menu from "./menu/Menu";
import Footer from "./footer/Footer";
import ProductCard from "./product/ProductCard";
import CategoryResults from "./product/CategoryResults";
import SearchResults from './product/SearchResults';
import ProductForm from './merchant/ProductForm';
import UpdateProductForm from './merchant/UpdateProductForm';
import "../styles/App.css";

export default function App() {

  const [menuOpened, setMenuOpened] = useState(false);

  const openMenu = (e) =>{
    e.preventDefault();
    if (!menuOpened){
      setMenuOpened(true);
    }
  }

  const closeMenu = () =>{
    if (menuOpened){
      setMenuOpened(false);
    }
  }

  return (
    <BrowserRouter>
    <div className='wrapper'>
      {menuOpened ? (<div className='overlay'></div>) : null}
      <Header toggleMenu={openMenu}/>
      <Menu closeMenu={closeMenu} menuOpened={menuOpened}/>
      <main className="main">
        <div className="main-container">
          <Routes>
            <Route path="/" element={<Main/>}/>
            <Route path="/products/:productId" element={<ProductCard/>}/>
            <Route path="/products/search" element={<SearchResults/>}/>
            <Route path="/products/:sex/:category" element={<CategoryResults/>}/>
            <Route path="/about" element={<About/>}/>
            <Route path="/careers" element={<Careers/>}/>
            <Route path="/contacts" element={<Contacts/>}/>
            <Route path="/delivery" element={<Delivery/>}/>
            <Route path="/help" element={<Help/>}/>
            <Route path="/privacy" element={<Privacy/>}/>
            <Route path="/terms" element={<Terms/>}/>
            <Route path="/order" element={<OrderTrack/>}/>
            <Route path="*" element={<NotFound/>}/>
            <Route path="/cabinet/merchant/products" element={
              <RenderOnRole roles={'ROLE_MERCHANT'}>
                <MerchantPage/>
              </RenderOnRole>
            }/>
            <Route path="/cabinet/merchant/products/new" element={
              <RenderOnRole roles={'ROLE_MERCHANT'}>
                <ProductForm/>
              </RenderOnRole>
            }/>
            <Route path="/cabinet/merchant/products/update/:productId" element={
              <RenderOnRole roles={'ROLE_MERCHANT'}>
                <UpdateProductForm/>
              </RenderOnRole>
            }/>
            <Route path="/basket" element={
              <RenderOnAuthenticated>
                <Basket/>
              </RenderOnAuthenticated>
            }/>
          </Routes>
        </div>
      </main>
      <Footer/>
    </div>
    </BrowserRouter>
  );
}