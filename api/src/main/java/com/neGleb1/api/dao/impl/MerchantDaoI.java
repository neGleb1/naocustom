package com.neGleb1.api.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.neGleb1.api.dao.MerchantDao;
import com.neGleb1.api.mapper.MerchantRowMapper;
import com.neGleb1.api.model.Merchant;

@Repository
public class MerchantDaoI implements MerchantDao {

    private final JdbcTemplate jdbcTemplate;

    public MerchantDaoI(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Merchant merchant) {
        String sql = "INSERT INTO merchant (merchant_name, email, info, merchant_timestamp)"
            + " VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
            merchant.getMerchantName(),
            merchant.getEmail(),
            merchant.getInfo(),
            merchant.getTimestamp()
        );
    }

    @Override
    public void delete(String email) {
        String sql = "DELETE FROM merchant WHERE email = ?";
        jdbcTemplate.update(sql, email);
    }

    @Override
    public Merchant selectByEmail(String email) {
        String sql = "SELECT merchant_id, merchant_name, email, info, merchant_timestamp FROM merchant WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new MerchantRowMapper(), email);
    }
    
}
