package com.neGleb1.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.neGleb1.api.model.Category;
import com.neGleb1.api.model.Product;
import com.neGleb1.api.model.Sex;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getLong("product_id"));
        product.setMerchantId(rs.getLong("merchant_id"));
        product.setProductName(rs.getString("product_name"));
        product.setSex(Sex.valueOf(rs.getString("sex")));
        product.setPrice(rs.getInt("price"));
        product.setIsAvailable(rs.getBoolean("is_available"));
        product.setCategory(Category.valueOf(rs.getString("category")));
        product.setTimestamp(rs.getString("product_timestamp"));
        return product;
    }
    
}
