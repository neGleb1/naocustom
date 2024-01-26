import { Link } from "react-router-dom";
import '../../styles/Footer.css';

export default function Footer() {

    const today = new Date();

    return(
        <footer className="footer">
            <div className="footer-wrapper">
                <div className="footer-upper">
                    <section className="info-section">
                        <div className='section-wrapper'>
                            <h3 className="section-title">Help &amp; Information</h3>
                            <ul>
                                <li>
                                    <p>
                                        <Link to="/help">Help</Link>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <Link to="/contacts">Contacts</Link>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <Link to="/order">Orders</Link>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <Link to="/delivery">Delivery</Link>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </section>
                    <section className="info-section">
                        <div className='section-wrapper'>
                            <h3 className="section-title">About</h3>
                            <ul>
                                <li>
                                    <p>
                                        <Link to="/about">About us</Link>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <Link to="/careers">Career</Link>
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </section>
                </div>
                <div className="footer-lower">
                    <div className="footer-lower-wrapper">
                        <p className="copyright">Made by NeGleb1 {today.getFullYear()}</p>
                        <ul>
                            <li>
                                <p>
                                    <Link to="/privacy">Privacy</Link>
                                </p>
                            </li>
                            <li>
                                <p>
                                    <Link to="/terms">Terms</Link>
                                </p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    );
}