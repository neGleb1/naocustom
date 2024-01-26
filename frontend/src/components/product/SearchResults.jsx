import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useLocation } from "react-router-dom";
import { getProductsByQuery } from "../../redux/product";
import ServerError from "../utilComponents/ServerError";
import LoadingIcon from "../utilComponents/LoadingIcon";
import ProductList from "../product/ProductList";
import "../../styles/App.css";

export default function SearchResults(){

    const location = useLocation();

    const dispatch = useDispatch();
    const products = useSelector((state) => state.product.content);
    const isLoading = useSelector((state) => state.product.isLoading);
    const error = useSelector((state) => state.product.error);

    useEffect(() => {
        dispatch(getProductsByQuery(new URLSearchParams(location.search).get('q')));
    }, [location, dispatch]);

    if (isLoading) {
        return <LoadingIcon/>;
    }

    if (error) {
        return <ServerError/>;
    }

    return (
        <>
            <ProductList products={products}/>
        </>
    );
}