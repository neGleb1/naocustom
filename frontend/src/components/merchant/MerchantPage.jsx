
import { useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getMerchantProducts, deleteProduct } from "../../redux/product";
import ProductList from "../product/ProductList";
import { getMerchant } from "../../redux/merchant";
import UserService from "../../config/UserService";
import ServerError from "../utilComponents/ServerError";
import "../../styles/MerchantPage.css";

export default function MerchantPage() {
  const navigate = useNavigate();

  const ref = useRef(null);
  const dispatch = useDispatch();
  const merchant = useSelector((state) => state.merchant.content);
  // const merchantIsLoading = useSelector((state) => state.merchant.isLoading);
  const merchantError = useSelector((state) => state.merchant.error);

  const products = useSelector((state) => state.product.content);
  // const isLoading = useSelector((state) => state.product.isLoading);
  const error = useSelector((state) => state.product.error);

  useEffect(() => {
    console.log(UserService.getTokenParsed());
    console.log(UserService.getTokenParsed()?.['resource_access.test-realm-sso.roles']);
    if (UserService.hasRole('ROLE_MERCHANT')){
      dispatch(getMerchant(UserService.getTokenParsed()?.email))
    }
  }, [dispatch]);

  useEffect(() => {
    if (merchant.merchantId > 0){
      dispatch(getMerchantProducts(merchant.merchantId));
    }
  }, [merchant]);

  const [deleteMode, setDeleteMode] = useState(false);
  const [updateMode, setUpdateMode] = useState(false);

  const cancelEvent = () => {
    setDeleteMode(false);
    setUpdateMode(false);
  }

  const removeProduct = (merchantId, productId) => {
    dispatch(deleteProduct([merchantId, productId]))
      .then(() => navigate(0));
  }

  useEffect(() => {
    const handleClickOutside = (event) => {
        if (ref.current && !ref.current.contains(event.target)) {
          cancelEvent();
        }
    };
    document.addEventListener('click', handleClickOutside, true);
    return () => {
        document.removeEventListener('click', handleClickOutside, true);
    };
  }, [ cancelEvent ]);

  if (merchantError) {
    return <ServerError/>;
  }

  if (error) {
    return <ServerError/>;
  }

  return (
    <div ref={ref} className="merchant-page">
      <div className="merchant-page-header">
        {!deleteMode && !updateMode && <h1>{`WELCOME BACK, ${merchant.merchantName}`}</h1>}
        {deleteMode && !updateMode && <h1>Choose product to delete</h1>}
        {updateMode && !deleteMode && <h1>Choose product to update</h1>}
        <div className="merchant-tabs">
          <button 
            className={(!deleteMode && !updateMode) ? "merchant-tab add-product" : "merchant-tab add-product disabled"}
            onClick={() => navigate("/cabinet/merchant/products/new")} 
            role="tab"
          >
            New product
          </button>
          <button 
            className={(!deleteMode && !updateMode) ? "merchant-tab update-product" : "merchant-tab update-product disabled"} 
            onClick={() => setUpdateMode(true)} 
            role="tab"
          >
            Update product
          </button>
          <button 
            className={(!deleteMode && !updateMode) ? "merchant-tab delete-product" : "merchant-tab delete-product disabled"}
            onClick={() => setDeleteMode(true)} 
            role="tab"
          >
            Delete product
          </button>
          <button 
            className={(deleteMode || updateMode) ? "merchant-tab cancel" : "merchant-tab cancel disabled"}
            onClick={() => cancelEvent()} 
            role="tab"
          >
            Cancel
          </button>
        </div>
      </div>
      <ProductList products={products} deleteMode={deleteMode} updateMode={updateMode} onClickEvent={removeProduct}/>
    </div>
  );
}