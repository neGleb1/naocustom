package com.neGleb1.api.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.neGleb1.api.dao.ImageDao;
import com.neGleb1.api.mapper.ImageRowMapper;
import com.neGleb1.api.model.Image;

@Repository
public class ImageDaoI implements ImageDao {

    private final JdbcTemplate jdbcTemplate;

    public ImageDaoI(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Image> selectAllByProductId(long id) {
        String sql = "SELECT image_id, product_id, is_thumbnail, image_path, image_timestamp FROM product_image WHERE is_thumbnail = FALSE AND product_id = ?";
        return jdbcTemplate.query(sql, new ImageRowMapper(), id);
    }

    @Override
    public void save(Image image) {
        String sql = "INSERT INTO product_image (product_id, is_thumbnail, image_path, image_timestamp)"
            + " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
            image.getProductId(), 
            image.getIsThumbnail(), 
            image.getPath(), 
            image.getTimestamp()
        );
    }

    @Override
    public void delete(String path) {
        String sql = "DELETE FROM product_image WHERE image_path = ?";
        jdbcTemplate.update(sql, path);
    }   
}
