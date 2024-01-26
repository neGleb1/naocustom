import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from "react-redux";
import { useParams, useNavigate } from "react-router-dom";
import { getProduct, updateProduct } from '../../redux/product';
import ServerError from "../utilComponents/ServerError";
import '../../styles/UpdateProductForm.css';

export default function UpdateProductForm() {

    const { productId } = useParams();
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const product = useSelector((state) => state.product.content);
    const error = useSelector((state) => state.product.error);

    const [updatedProductId, setUpdatedProductId] = useState(-1);
    const [updatedMerchantId, setUpdatedMerchantId] = useState(-1);
    const [name, setName] = useState('');
    const [sex, setSex] = useState('men');
    const [category, setCategory] = useState('');
    const [price, setPrice] = useState(0);
    const [size, setSize] = useState('');
    const [brand, setBrand] = useState('');
    const [material, setMaterial] = useState('');
    const [color, setColor] = useState('');
    const [description, setDescription] = useState('');
    const [oldImages, setOldImages] = useState([]);

    const [file1, setFile1] = useState();
    const [file2, setFile2] = useState();
    const [file3, setFile3] = useState();
    const [file4, setFile4] = useState();
    const [preview1, setPreview1] = useState();
    const [preview2, setPreview2] = useState();
    const [preview3, setPreview3] = useState();
    const [preview4, setPreview4] = useState();

    useEffect(() => {
        dispatch(getProduct(parseInt(productId, 10)));
    }, [productId, dispatch]);

    useEffect(() => {
        if (product.productId > 0){
            setUpdatedProductId(product.productId);
            setUpdatedMerchantId(product.merchantId);
            setName(product.productName);
            setSex(product.sex);
            setPrice(parseInt(product.price, 10));
            setCategory(product.category);
            setBrand(product.brand);
            setSize(product.size);
            setColor(product.color);
            setMaterial(product.material);
            setDescription(product.description);
            setOldImages(product.images?.map((image) => image.path));
        }
    }, [productId, product]);

    const handleSubmit = (event) => {
        event.preventDefault();
        const form = new FormData();
        form.append('productName', name);
        form.append('sex', sex);
        form.append('category', category);
        form.append('price', price);
        form.append('size', size);
        form.append('brand', brand);
        form.append('material', material);
        form.append('color', color);
        form.append('description', description);
        form.append('oldImages', oldImages);
        if (file1)
            form.append('thumbnail', file1);
        if (file2)
            form.append('image1', file2);
        if (file3)
            form.append('image2', file3);
        if (file4)
            form.append('image3', file4);
        dispatch(updateProduct([ 
            updatedMerchantId,
            updatedProductId,
            form ]))
        .then(() => navigate(-1));
    };

    function handleFile(e, callback) {
        callback(URL.createObjectURL(e.target.files[0]));

        if (!e.target.files || e.target.files.length === 0) {
            callback(undefined);
            return;
        }
        callback(e.target.files[0]);
    }

    useEffect(() => {
        if (!file1) {
            setPreview1(undefined);
            return;
        }
        const objectUrl = URL.createObjectURL(file1);
        setPreview1(objectUrl);
        return () => URL.revokeObjectURL(objectUrl);
    }, [file1])

    useEffect(() => {
        if (!file2) {
            setPreview2(undefined);
            return;
        }
        const objectUrl = URL.createObjectURL(file2);
        setPreview2(objectUrl);
        return () => URL.revokeObjectURL(objectUrl);
    }, [file2])

    useEffect(() => {
        if (!file3) {
            setPreview3(undefined);
            return;
        }
        const objectUrl = URL.createObjectURL(file3);
        setPreview3(objectUrl);
        return () => URL.revokeObjectURL(objectUrl);
    }, [file3])

    useEffect(() => {
        if (!file4) {
            setPreview4(undefined);
            return;
        }
        const objectUrl = URL.createObjectURL(file4);
        setPreview4(objectUrl);
        return () => URL.revokeObjectURL(objectUrl);
    }, [file4])

    if (error) {
        return <ServerError/>;
    }

    return (
        <div className="new-product">
            <h1>Update product</h1>
            <form className="product-form" onSubmit={(e) => handleSubmit(e)}>
                <div className='new-product-section-container'>
                    <section className='required-attributes-section'>

                        <label><b>Product name</b></label>
                        <input type="text" value={name} onChange={(e)=>setName(e.target.value)} autoComplete='false' autoCorrect='false' placeholder="Enter product name" name="productName" id="productName-input" required/>

                        <label><b>Category</b></label>
                        <select name="category" value={category} onChange={(e)=>setCategory(e.target.value)} id="category-input">
                            <option value="jacket">Jacket</option>
                            <option value="shoes">Shoes</option>
                            <option value="hat">Hat</option>
                            <option value="underwear">Underwear</option>
                            <option value="pants">Pants</option>
                            <option value="tshirt">T-Shirt</option>
                            <option value="accessories">Accessories</option>
                        </select>

                        <label><b>Price</b></label>
                        <input type="number" value={price} onChange={(e)=>setPrice(e.target.value)} placeholder="Enter price" autoComplete='false' autoCorrect='false' name="price" id="price-input" required="true"/>

                        <section className='sex-input-section'>
                            <p>Choose sex</p>
                            <ul>
                                <li>
                                    <input type="radio" onChange={(e)=>setSex(e.target.value)} checked={sex === "male"} value="male" name="men" id="men-input" />
                                    <label for="men-input"><b>Male</b></label>
                                </li>
                                <li>
                                    <input type="radio" onChange={(e)=>setSex(e.target.value)} checked={sex === "female"} value="female" name="women" id="women-input"/>
                                    <label for="women-input"><b>Female</b></label>
                                </li>
                            </ul>
                        </section>

                        <div className='new-images-input-container'>
                            <div className='image-container'>
                                <label><b>Change thumbnail</b></label>
                                <input type="file" onChange={(e)=>{handleFile(e, setFile1)}} name="photos" id="photo-input"/>
                                {file1 &&  <img src={preview1} /> }
                            </div>
                            <div className='image-container'>
                                <label><b>Image 1</b></label>
                                {file1 &&  <img src={preview1} /> }
                            </div>
                            <div className='image-container'>
                                <label><b>Image 2</b></label>
                                <input type="file" onChange={(e)=>{handleFile(e, setFile2)}} name="photos" id="photo-input"/>
                                {file2 &&  <img src={preview2} /> }
                            </div>
                            <div className='image-container'>
                                <label><b>Image 3</b></label>
                                <input type="file" onChange={(e)=>{handleFile(e, setFile3)}} name="photos" id="photo-input"/>
                                {file3 &&  <img src={preview3} /> }
                            </div>
                            <div className='image-container'>
                                <label><b>Image 4</b></label>
                                <input type="file" onChange={(e)=>{handleFile(e, setFile4)}} name="photos" id="photo-input"/>
                                {file4 &&  <img src={preview4} /> }
                            </div>
                        </div>
                    </section>
                    <section className='optional-attributes-section'>

                        <label><b>Size</b></label>
                        <input type="text" defaultValue={size} onChange={(e)=>setPrice(e.target.value)} autoComplete='false' autoCorrect='false' placeholder="Enter product size" name="size" id="size-input"/>

                        <label><b>Brand</b></label>
                        <input type="text" defaultValue={brand} onChange={(e)=>setBrand(e.target.value)} autoComplete='false' autoCorrect='false' placeholder="Enter product brand" name="brand" id="brand-input"/>

                        <label><b>Material</b></label>
                        <input type="text" defaultValue={material} onChange={(e)=>setMaterial(e.target.value)} autoComplete='false' autoCorrect='false' placeholder="Enter product material" name="material" id="material-input"/>

                        <label><b>Description</b></label>
                        <input type="text" defaultValue={description} onChange={(e)=>setDescription(e.target.value)} autoComplete='false' autoCorrect='false' placeholder="Enter additional info" name="description" id="description-input"/>

                        <label><b>Color</b></label>
                        <input type="color" defaultValue={color} onChange={(e)=>setColor(e.target.value)} name="color" id="color-input"/>

                        <div className='images-container'>
                            {oldImages.map((path) => (
                                <div className='image-container'>
                                    <label><b>Current image</b></label>
                                    <img className='image-container' src={path}/>
                                </div>
                            ))}
                        </div>
                    </section>
                </div>
                <div className='new-product-form-submit'>
                    <button type="submit" className="new-product-submit">Submit</button>
                </div>
            </form>
        </div>
    );
}