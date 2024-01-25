package com.neGleb1.api.service;

import org.springframework.stereotype.Service;

import com.neGleb1.api.dao.MerchantDao;
import com.neGleb1.api.model.Merchant;

@Service
public class MerchantService {

    private final MerchantDao merchantDao;
    
    public MerchantService(MerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }

    public Merchant getMerchantByEmail(String email){
        return merchantDao.selectByEmail(email);
    }

    public void addMerchant(Merchant merchant){
        merchantDao.save(merchant);
    }

    public void deleteMerchant(String email){
        merchantDao.delete(email);
    }
}
