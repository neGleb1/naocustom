import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getProduct } from "../../redux/product";
import ServerError from "../utilComponents/ServerError";
import LoadingIcon from "../utilComponents/LoadingIcon";
import "../../styles/ProductPage.css";

export default function ProductCard(){

    const { productId } = useParams();

    const dispatch = useDispatch();
    const product = useSelector((state) => state.product.content);
    const isLoading = useSelector((state) => state.product.isLoading);
    const error = useSelector((state) => state.product.error);

    useEffect(() => {
        dispatch(getProduct(parseInt(productId, 10)));
    }, [dispatch]);

    const [slide, setSlide] = useState(0);

    if (isLoading) {
        return (<LoadingIcon/>);
    }
    
    if (error) {
        return (<ServerError/>);
    }

    return (
        <div className="product-page-container">
            <div className='product-page-header'>
                <h1>{product.productName}</h1>
            </div>
            <div className='product-page-slideshow'>
            <button className="prev" type='button' onClick={()=>{slide === 0 ? setSlide(product.images.length-1) : setSlide(slide-1)}}>&#10094;</button>
                {
                    product.images?.length > 0 && product.images.map((image, index) => (
                        <div key={index} className={index === slide ? "slide active" : "slide"}>
                            <img src={image.path}/>
                        </div>
                    ))
                }
                <button className="next" type='button' onClick={()=>{slide === product.images.length-1 ? setSlide(0) : setSlide(slide+1)}}>&#10095;</button>
            </div>
            <div className='product-page-price'>
                <p>{`${product.price} €`}</p>
                <button className='cart-button' type='button'>Add to cart</button>
            </div>
            <section className='product-page-details'>
                <div className='section-header'>
                    <h2>About</h2>
                </div>
                <ul>
                    <li>{`Size: ${product.size}`}</li>
                    <li>{`Material: ${product.material}`}</li>
                    <li>{`Brand: ${product.brand}`}</li>
                    <li>{`Sex: ${product.sex}`}</li>
                    <li>{`Category: ${product.category}`}</li>
                    <li>{`Color: ${product.color}`}</li>
                    <li>{`Merchant: ${product.merchantName}`}</li>
                    <li>{`Description: ${product.description}`}</li>
                </ul>
            </section>
        </div>
    );
}