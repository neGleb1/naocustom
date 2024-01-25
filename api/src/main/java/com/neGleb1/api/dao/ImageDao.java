package com.neGleb1.api.dao;

import java.util.List;

import com.neGleb1.api.model.Image;

public interface ImageDao {

    List<Image> selectAllByProductId(long id);
    void save(Image image);
    void delete(String path);
}
