import { Link } from "react-router-dom";
import BasketIcon from "../../assets/BasketIcon";
import BurgerIcon from "../../assets/BurgerIcon";
import ProfileIcon from "../../assets/ProfileIcon";
import MerchantIcon from "../../assets/MerchantIcon";
import SearchIcon from "../../assets/SearchIcon";
import AdminIcon from "../../assets/AdminIcon";
import Search from "./Search";
import RenderOnRole from "../utilComponents/RenderOnRole";
import '../../styles/Header.css';

export default function Header({toggleMenu}) {

    return(        
        <header className="header">
            <div className="header-container">
                <div className="nav-container">
                    <button className="burger-button" onClick={toggleMenu}>
                        <BurgerIcon/>
                    </button>
                    <div className="logo-container">
                        <Link to="/">
                            <img className="header-logo" src="/logo.svg" alt="Naocustom logo"/>
                        </Link>
                    </div>
                </div>
                <Search/>
                <ul className="nav-icons-list">
                    <li className="nav-icon search-icon">
                        <SearchIcon/>
                    </li>
                    <li className="nav-icon">
                        <ProfileIcon/>
                    </li>
                    <li className="nav-icon">
                        <Link to="/basket">
                            <BasketIcon/>
                        </Link>
                    </li>
                    <RenderOnRole roles={'ROLE_MERCHANT'}>
                        <li className="nav-icon">
                        <Link to={`/cabinet/merchant/products`}>
                                <MerchantIcon/>
                            </Link>
                        </li>
                    </RenderOnRole>
                    <RenderOnRole roles={['ROLE_ADMIN']}>
                        <li className="nav-icon">
                            <AdminIcon/>
                        </li>
                    </RenderOnRole>
                </ul>
            </div>
        </header>
    );
}