import {useState, useRef, useEffect} from 'react';
import { Link } from "react-router-dom";
import '../../styles/Menu.css';

export default function Menu({closeMenu, menuOpened}){

    const ref = useRef(null);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (ref.current && !ref.current.contains(event.target)) {
                closeMenu();
            }
        };
        document.addEventListener('click', handleClickOutside, true);
        return () => {
            document.removeEventListener('click', handleClickOutside, true);
        };
    }, [ closeMenu ]);

    const [menStyle, setMenStyle] = useState("menu-men");
    const [womenStyle, setWomenStyle] = useState("menu-women disabled");

    const toggleMenCatalog = (e) => {;
        setMenStyle("menu-men");
        setWomenStyle("menu-women disabled");
    }

    const toggleWomenCatalog = (e) => {
        setMenStyle("menu-men disabled");
        setWomenStyle("menu-women");
    }

    return (
        <div ref={ref} className={(menuOpened) ? "menu menu-active" : "menu"}>
            <div className="menu-container" role='tablist'>
                <div className='menu-tabs'>
                    <button className='men-tab' onClick={toggleMenCatalog} role='tab' aria-label='men products' aria-selected="true">MEN</button>
                    <button className='women-tab' onClick={toggleWomenCatalog} role='tab' aria-label='women products' aria-selected="fasle">WOMEN</button>
                </div>
                <div className={menStyle}>
                    <ul>
                        <li>
                            <p>
                                <Link to="/products/male/shoes">Shoes for men</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/male/tshirt">Tshirts for men</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/male/hat">Hats for men</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/male/jacket">Jackets for men</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/male/pants">Pants for men</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/male/underwear">Underwear for men</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/male/accessories">Accessories for men</Link>
                            </p>
                        </li>
                    </ul>
                </div>
                <div className={womenStyle}>
                    <ul>
                        <li>
                            <p>
                                <Link to="/products/female/shoes">Shoes for women</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/female/tshirt">Tshirts for women</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/female/hat">Hats for women</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/female/jacket">Jackets for women</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/female/pants">Pants for women</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/female/underwear">Underwear for women</Link>
                            </p>
                        </li>
                        <li>
                            <p>
                                <Link to="/products/female/accessories">Accessories for women</Link>
                            </p>
                        </li>
                    </ul>
                </div>
            </div>
            <button className='menu-close-button' onClick={closeMenu}></button>
        </div>
    );
}