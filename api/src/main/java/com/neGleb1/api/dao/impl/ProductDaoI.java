package com.neGleb1.api.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.neGleb1.api.dao.ProductDao;
import com.neGleb1.api.dto.ProductItemResponse;
import com.neGleb1.api.mapper.ProductAttributesRowMapper;
import com.neGleb1.api.mapper.ProductItemResponseRowMapper;
import com.neGleb1.api.model.Product;
import com.neGleb1.api.model.ProductAttributes;

@Repository
public class ProductDaoI implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDaoI(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductItemResponse> selectAll() {
        String sql = "SELECT"
                        + " product.product_id,"
                        + " merchant.merchant_id,"
                        + " product.product_name,"
                        + " merchant.merchant_name,"
                        + " product.sex,"
                        + " product.price,"
                        + " product.category,"
                        + " product_attributes.color,"
                        + " product_attributes.product_size,"
                        + " product_attributes.brand,"
                        + " product_attributes.material,"
                        + " product_image.image_path,"
                        + " product.product_timestamp"
                        + " FROM product INNER JOIN merchant ON product.merchant_id = merchant.merchant_id"
                        + " INNER JOIN product_attributes ON product.product_id = product_attributes.product_id"
                        + " INNER JOIN product_image ON product.product_id = product_image.product_id"
                        + " WHERE product.is_available = TRUE AND product_image.is_thumbnail = TRUE";

        return jdbcTemplate.query(sql, new ProductItemResponseRowMapper());
    }

    @Override
    public List<ProductItemResponse> selectAllByQuery(String query) {

        String sql = "SELECT * from search_by_query(?)";

        return jdbcTemplate.query(sql, new ProductItemResponseRowMapper(), query);
    }

    @Override
    public List<ProductItemResponse> selectAllBySexAndCategory(String sex, String category) {
        String sql = "SELECT"
                        + " product.product_id,"
                        + " merchant.merchant_id,"
                        + " product.product_name,"
                        + " merchant.merchant_name,"
                        + " product.sex,"
                        + " product.price,"
                        + " product.category,"
                        + " product_attributes.color,"
                        + " product_attributes.product_size,"
                        + " product_attributes.brand,"
                        + " product_attributes.material,"
                        + " product_image.image_path,"
                        + " product.product_timestamp"
                        + " FROM product INNER JOIN merchant ON product.merchant_id = merchant.merchant_id"
                        + " INNER JOIN product_attributes ON product.product_id = product_attributes.product_id"
                        + " INNER JOIN product_image ON product.product_id = product_image.product_id"
                        + " WHERE product.is_available = TRUE AND product_image.is_thumbnail = TRUE"
                        + " AND product.sex = ?"
                        + " AND product.category = ?";
                        
        return jdbcTemplate.query(sql, new ProductItemResponseRowMapper(), sex, category);
    }

    @Override
    public List<ProductItemResponse> selectAllByMerchantId(long id) {
        String sql = "SELECT"
                        + " product.product_id,"
                        + " merchant.merchant_id,"
                        + " product.product_name,"
                        + " merchant.merchant_name,"
                        + " product.sex,"
                        + " product.price,"
                        + " product.category,"
                        + " product_attributes.color,"
                        + " product_attributes.product_size,"
                        + " product_attributes.brand,"
                        + " product_attributes.material,"
                        + " product_image.image_path,"
                        + " product.product_timestamp"
                        + " FROM product INNER JOIN merchant ON product.merchant_id = merchant.merchant_id"
                        + " INNER JOIN product_attributes ON product.product_id = product_attributes.product_id"
                        + " INNER JOIN product_image ON product.product_id = product_image.product_id"
                        + " WHERE product.is_available = TRUE AND product_image.is_thumbnail = TRUE"
                        + " AND merchant.merchant_id = ?";
                        
        return jdbcTemplate.query(sql, new ProductItemResponseRowMapper(), id);
    }

    @Override
    public ProductItemResponse select(long id) {
        String sql = "SELECT"
                        + " product.product_id,"
                        + " merchant.merchant_id,"
                        + " product.product_name,"
                        + " merchant.merchant_name,"
                        + " product.sex,"
                        + " product.price,"
                        + " product.category,"
                        + " product_attributes.color,"
                        + " product_attributes.product_size,"
                        + " product_attributes.brand,"
                        + " product_attributes.material,"
                        + " product_image.image_path,"
                        + " product.product_timestamp"
                        + " FROM product INNER JOIN merchant ON product.merchant_id = merchant.merchant_id"
                        + " INNER JOIN product_attributes ON product.product_id = product_attributes.product_id"
                        + " INNER JOIN product_image ON product.product_id = product_image.product_id"
                        + " WHERE product.is_available = TRUE AND product_image.is_thumbnail = TRUE"
                        + " AND product.product_id = ?";
                        
        return jdbcTemplate.queryForObject(sql, new ProductItemResponseRowMapper(), id);
    }

    @Override
    public long save(Product product) {
        String sql = "INSERT INTO product (merchant_id, product_name, sex, price, is_available, category, product_timestamp)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, product.getMerchantId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getSex().toString());
            ps.setInt(4, product.getPrice());
            ps.setBoolean(5, product.getIsAvailable());       
            ps.setString(6, product.getCategory().toString());
            ps.setString(7, product.getTimestamp());
            return ps;

        }, keyHolder);
        List<Map<String, Object>> keyList = keyHolder.getKeyList();
        long id = -1;
        for (Map<String, Object> map : keyList) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (Objects.equals(entry.getKey(), "product_id")) id = (long) entry.getValue();
            }
        }
        return id;
    }

    @Override
    public void saveProductAttributes(ProductAttributes attr) {
        String sql = "INSERT INTO product_attributes (product_id, product_description, color, product_size, brand, material)"
            + " VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            attr.getProductId(), 
            attr.getDescription(), 
            attr.getColor(), 
            attr.getSize(), 
            attr.getBrand(),
            attr.getMaterial()
        );
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET product_name = ?, sex = ?, price = ?, category = ? WHERE product_id = ?";
        jdbcTemplate.update(sql,
            product.getProductName(),
            product.getSex().toString(),
            product.getPrice(),
            product.getCategory().toString(),
            product.getProductId()
        );
    }

    @Override
    public ProductAttributes selectAttributesById(long id) {
        String sql = "SELECT attributes_id, product_id, product_description, color, product_size, brand, material"
            + " FROM product_attributes WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductAttributesRowMapper(), id);
    }

    @Override
    public void updateProductAttributes(ProductAttributes attr) {
        String sql = "UPDATE product_attributes SET product_description = ?, color = ?, product_size = ?, brand = ?, material = ?"
            + " WHERE product_id = ?";
        jdbcTemplate.update(sql, 
            attr.getDescription(), 
            attr.getColor(), 
            attr.getSize(), 
            attr.getBrand(),
            attr.getMaterial(),
            attr.getProductId()
        );
    }
    
}
