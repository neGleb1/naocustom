package com.neGleb1.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.neGleb1.api.model.ProductAttributes;

public class ProductAttributesRowMapper  implements RowMapper<ProductAttributes> {

    @Override
    public ProductAttributes mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductAttributes attributes = new ProductAttributes();
        attributes.setAttributesId(rs.getLong("attributes_id"));
        attributes.setProductId(rs.getLong("product_id"));
        attributes.setDescription(rs.getString("product_description"));
        attributes.setColor(rs.getString("color"));
        attributes.setSize(rs.getString("product_size"));
        attributes.setBrand(rs.getString("brand"));
        attributes.setMaterial(rs.getString("material"));
        return attributes;
    }
    
}
