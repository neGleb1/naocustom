import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams  } from "react-router-dom";
import { getProductsByCategory } from "../../redux/product";
import ServerError from "../utilComponents/ServerError";
import LoadingIcon from "../utilComponents/LoadingIcon";
import ProductList from "../product/ProductList";
import "../../styles/App.css";

export default function CategoryResults(){

    const { sex, category } = useParams();

    const dispatch = useDispatch();
    const products = useSelector((state) => state.product.content);
    const isLoading = useSelector((state) => state.product.isLoading);
    const error = useSelector((state) => state.product.error);

    useEffect(() => {
        dispatch(getProductsByCategory([sex, category]));
    }, [dispatch, sex, category]);

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