package com.neGleb1.api.dao;

import com.neGleb1.api.model.Merchant;

public interface MerchantDao {
    void save(Merchant merchant);
    void delete(String email);
    Merchant selectByEmail(String email);
}
