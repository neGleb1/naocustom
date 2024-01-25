package com.neGleb1.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.neGleb1.api.dto.ProductItemResponse;
import com.neGleb1.api.model.Category;
import com.neGleb1.api.model.Sex;

public class ProductItemResponseRowMapper implements RowMapper<ProductItemResponse> {

    @Override
    public ProductItemResponse mapRow(ResultSet rs, int i) throws SQLException {
        ProductItemResponse response = new ProductItemResponse();
        response.setProductId(rs.getLong("product_id"));
        response.setMerchantId(rs.getLong("merchant_id"));
        response.setProductName(rs.getString("product_name"));
        response.setMerchantName(rs.getString("merchant_name"));
        response.setSex(Sex.valueOf(rs.getString("sex")));
        response.setPrice(rs.getInt("price"));
        response.setCategory(Category.valueOf(rs.getString("category")));
        response.setColor(rs.getString("color"));
        response.setSize(rs.getString("product_size"));
        response.setBrand(rs.getString("brand"));
        response.setMaterial(rs.getString("material"));
        response.setThumbnailPath(rs.getString("image_path"));
        response.setTimestamp(rs.getString("product_timestamp"));
        return response;
    }
    
}
