import '../../styles/ProductItem.css'

export default function ProductItem({product, deleteMode, updateMode, onClickEvent}){

    if ( deleteMode && !updateMode )
        return (
            <div className="list-item delete" onClick={() => onClickEvent(product?.merchantId, product?.productId)}>
                <img className="list-item-thumbnail" src={product.thumbnailPath}/>
                <h4 className="list-item-price">{product.price}</h4>
                <span className="list-item-name">{product.productName}</span>
            </div>    
        );

    if ( updateMode && !deleteMode )
        return (
            <div className="list-item update">
                <img className="list-item-thumbnail" src={product.thumbnailPath}/>
                <h4 className="list-item-price">{product.price}</h4>
                <span className="list-item-name">{product.productName}</span>
            </div>
        );
    if ( !updateMode && !deleteMode )
        return (
            <div className="list-item">
                <img className="list-item-thumbnail" src={product.thumbnailPath}/>
                <h4 className="list-item-price">{product.price}</h4>
                <span className="list-item-name">{product.productName}</span>
            </div>
        );
}