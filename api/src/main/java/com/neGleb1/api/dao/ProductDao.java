package com.neGleb1.api.dao;

import java.util.List;

import com.neGleb1.api.dto.ProductItemResponse;
import com.neGleb1.api.model.Product;
import com.neGleb1.api.model.ProductAttributes;

public interface ProductDao {

    List<ProductItemResponse> selectAll();
    List<ProductItemResponse> selectAllByQuery(String query);
    List<ProductItemResponse> selectAllBySexAndCategory(String sex, String category);
    List<ProductItemResponse> selectAllByMerchantId(long id);
    ProductItemResponse select(long id);
    ProductAttributes selectAttributesById(long id);
    long save(Product product);
    void saveProductAttributes(ProductAttributes attr);
    void delete(long id);
    void update(Product product);
    void updateProductAttributes(ProductAttributes attr);

}
