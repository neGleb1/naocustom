package com.neGleb1.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.neGleb1.api.model.Merchant;

public class MerchantRowMapper implements RowMapper<Merchant> {

    @Override
    public Merchant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Merchant merchant = new Merchant();
        merchant.setMerchantId(rs.getLong("merchant_id"));
        merchant.setMerchantName(rs.getString("merchant_name"));
        merchant.setEmail(rs.getString("email"));
        merchant.setInfo(rs.getString("info"));
        merchant.setTimestamp(rs.getString("merchant_timestamp"));
        return merchant;
    }
    
}
