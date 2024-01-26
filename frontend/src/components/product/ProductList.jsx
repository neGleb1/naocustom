import { Link } from "react-router-dom";
import ProductItem from "./ProductItem";
import "../../styles/ProductList.css"

export default function ProductList({products, deleteMode, updateMode, onClickEvent}) {

    if(products.length <= 0) {
        return (<p>No products</p>);
    }

    return (
        <div className="product-list">
            {deleteMode && !updateMode && Array.isArray(products) && products?.map((product) => (
                <ProductItem key={product.productId} product={product} onClickEvent={onClickEvent} deleteMode={deleteMode}/>
            ))}
            {updateMode && !deleteMode && Array.isArray(products) && products?.map((product) => (
                <Link to={`/cabinet/merchant/products/update/${product.productId}`}>
                    <ProductItem key={product.productId} product={product} updateMode={updateMode}/>
                </Link>
            ))}
            {!deleteMode && !updateMode && Array.isArray(products) && products?.map((product) => (
                <Link to={`/products/${product.productId}`}>
                    <ProductItem key={product.productId} product={product}/>
                </Link>
            ))}
        </div>
    );
}