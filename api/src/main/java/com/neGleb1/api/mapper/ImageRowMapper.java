package com.neGleb1.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.neGleb1.api.model.Image;

public class ImageRowMapper implements RowMapper<Image> {

    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
        Image image = new Image();
        image.setImageId(rs.getLong("image_id"));
        image.setProductId(rs.getLong("product_id"));
        image.setIsThumbnail(rs.getBoolean("is_thumbnail"));
        image.setPath(rs.getString("image_path"));
        image.setTimestamp(rs.getString("image_timestamp"));
        return image;
    }
    
}
