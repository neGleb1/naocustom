import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getProducts } from "../../redux/product";
import ServerError from "../utilComponents/ServerError";
import LoadingIcon from "../utilComponents/LoadingIcon";
import ProductList from "../product/ProductList";
import Banner from "./Banner";
import "../../styles/App.css";

export default function Main(){

  const dispatch = useDispatch();
  const products = useSelector((state) => state.product.content);
  const isLoading = useSelector((state) => state.product.isLoading);
  const error = useSelector((state) => state.product.error);

  useEffect(() => {
    dispatch(getProducts());
  }, [dispatch]);

  if (isLoading) {
    return <LoadingIcon/>;
  }

  if (error) {
    return <ServerError/>;
  }

  return (
    <>
      <Banner/>
      <ProductList products={products}/>
    </>
  );
}