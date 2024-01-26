import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { addProduct } from '../../redux/product';
import { getMerchant } from "../../redux/merchant";
import ServerError from "../utilComponents/ServerError";
import '../../styles/ProductForm.css';
import UserService from '../../config/UserService';

export default function ProductForm() {

    const dispatch = useDispatch();
    const merchant = useSelector((state) => state.merchant.content);
    const merchantIsLoading = useSelector((state) => state.merchant.isLoading);
    const merchantError = useSelector((state) => state.merchant.error);
    const navigate = useNavigate();

    const isLoading = useSelector((state) => state.product.isLoading);
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
            setId(merchant.merchantId);
            console.log(id);
            setNewProduct({...newProduct, merchantId: merchant.merchantId});
        }
    }, [merchant]);

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(newProduct);
        const form = new FormData();
        form.append('merchantId', id);
        form.append('productName', newProduct.productName);
        form.append('sex', newProduct.sex);
        form.append('category', newProduct.category);
        form.append('price', newProduct.price);
        form.append('size', newProduct.size);
        form.append('brand', newProduct.brand);
        form.append('material', newProduct.material);
        form.append('color', newProduct.color);
        form.append('description', newProduct.description);
        form.append('image', file);
        dispatch(addProduct([ 
            id, 
            form ]))
        .then(() => navigate(-1));
    };

    const [newProduct, setNewProduct] = useState({
        merchantId: 0,
        productName: '',
        sex: 'men',
        category: 'jacket',
        price: 0,
        size: '-',
        brand: '-',
        material: '-',
        color: '-',
        description: '-',
    });
    const [id, setId] = useState(0);
    const [file, setFile] = useState();
    const [preview, setPreview] = useState();

    function handleFile(e) {
        setFile(URL.createObjectURL(e.target.files[0]));

        if (!e.target.files || e.target.files.length === 0) {
            setFile(undefined);
            return;
        }
        setFile(e.target.files[0]);
    }

    useEffect(() => {
        if (!file) {
            setPreview(undefined);
            return;
        }
        const objectUrl = URL.createObjectURL(file);
        setPreview(objectUrl);
        return () => URL.revokeObjectURL(objectUrl);
    }, [file])

    if (merchantError) {
        return <ServerError/>;
    }

    if (error) {
        return <ServerError/>;
    }

    return (
        <div className="new-product">
            <h1>New product</h1>
            <form className="product-form" onSubmit={(e) => handleSubmit(e)}>
                <div className='new-product-section-container'>
                    <section className='required-attributes-section'>

                        <label><b>Product name</b></label>
                        <input type="text" onChange={(e)=>setNewProduct({...newProduct, productName: e.target.value})} autoComplete='false' autoCorrect='false' placeholder="Enter product name" name="productName" id="productName-input" required/>

                        <label><b>Category</b></label>
                        <select name="category" onChange={(e)=>setNewProduct({...newProduct, category: e.target.value})} id="category-input">
                            <option value="jacket">Jacket</option>
                            <option value="shoes">Shoes</option>
                            <option value="hat">Hat</option>
                            <option value="underwear">Underwear</option>
                            <option value="pants">Pants</option>
                            <option value="tshirt">T-Shirt</option>
                            <option value="accessories">Accessories</option>
                        </select>

                        <label><b>Price</b></label>
                        <input type="number" onChange={(e)=>setNewProduct({...newProduct, price: e.target.value})} placeholder="Enter price" autoComplete='false' autoCorrect='false' name="price" id="price-input" required="true"/>

                        <div className='file-input-container'>
                            <label><b>Product thumbnail</b></label>
                            <input type="file" onChange={(e)=>{handleFile(e)}} name="photos" id="photo-input" required="true"/>
                            {file &&  <img src={preview} /> }
                        </div>

                        <section className='sex-input-section'>
                            <p>Choose sex</p>
                            <ul>
                                <li>
                                    <input type="radio" onChange={(e)=>setNewProduct({...newProduct, sex: e.target.value})} checked={newProduct.sex === "male"} value="male" name="men" id="men-input" />
                                    <label for="men-input"><b>Male</b></label>
                                </li>
                                <li>
                                    <input type="radio" onChange={(e)=>setNewProduct({...newProduct, sex: e.target.value})} checked={newProduct.sex === "female"} value="female" name="women" id="women-input"/>
                                    <label for="women-input"><b>Female</b></label>
                                </li>
                            </ul>
                        </section>
                    </section>
                    <section className='optional-attributes-section'>

                        <label><b>Size</b></label>
                        <input type="text" onChange={(e)=>setNewProduct({...newProduct, size: e.target.value})} autoComplete='false' autoCorrect='false' placeholder="Enter product size" name="size" id="size-input"/>

                        <label><b>Brand</b></label>
                        <input type="text" onChange={(e)=>setNewProduct({...newProduct, brand: e.target.value})} autoComplete='false' autoCorrect='false' placeholder="Enter product brand" name="brand" id="brand-input"/>

                        <label><b>Material</b></label>
                        <input type="text" onChange={(e)=>setNewProduct({...newProduct, material: e.target.value})} autoComplete='false' autoCorrect='false' placeholder="Enter product material" name="material" id="material-input"/>

                        <label><b>Description</b></label>
                        <input type="text" onChange={(e)=>setNewProduct({...newProduct, description: e.target.value})} autoComplete='false' autoCorrect='false' placeholder="Enter additional info" name="description" id="description-input"/>

                        <label><b>Color</b></label>
                        <input type="color" onChange={(e)=>setNewProduct({...newProduct, color: e.target.value})} name="color" id="color-input"/>
                    </section>
                </div>
                <div className='new-product-form-submit'>
                    <button type="submit" className="new-product-submit">Submit</button>
                </div>
            </form>
        </div>
    );
}