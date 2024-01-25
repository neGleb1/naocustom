package com.neGleb1.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neGleb1.api.dto.NewProductRequest;
import com.neGleb1.api.dto.ProductItemResponse;
import com.neGleb1.api.dto.UpdateProductRequest;
import com.neGleb1.api.model.Category;
import com.neGleb1.api.model.Merchant;
import com.neGleb1.api.model.Sex;
import com.neGleb1.api.service.MerchantService;
import com.neGleb1.api.service.ProductService;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    private final ProductService productService;
    private final MerchantService merchantService;

    public MerchantController(ProductService productService, MerchantService merchantService) {
        this.productService = productService;
        this.merchantService = merchantService;
    }

    @Transactional(readOnly = true)
    @GetMapping(value="{email}")
    public Merchant getMerchant(@PathVariable("email") String email) {
        return merchantService.getMerchantByEmail(email);
    }

    @Transactional(readOnly = true)
    @GetMapping(value="{merchantId}/products")
    public List<ProductItemResponse> getMerchantProducts(@PathVariable("merchantId") Integer merchantId) {
        return productService.getAllProductsByMerchantId(merchantId);
    }

    @PostMapping(value="{id}/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public ResponseEntity<HttpStatus> addProduct(
        @PathVariable("id") Long id,
        @RequestParam("merchantId") Long merchantId,
        @RequestParam("productName") String productName,
        @RequestParam("sex") String sex,
        @RequestParam("price") Integer price,
        @RequestParam("category") String category,
        @RequestParam("color") String color,
        @RequestParam("size") String size,
        @RequestParam("brand") String brand,
        @RequestParam("material") String material,
        @RequestParam("description") String description,
        @RequestParam("image") MultipartFile image) {
            NewProductRequest product = new NewProductRequest();
            product.setMerchantId(merchantId);
            product.setProductName(productName);
            product.setSex(Sex.valueOf(sex));
            product.setPrice(price);
            product.setCategory(Category.valueOf(category));
            product.setColor(color);
            product.setBrand(brand);
            product.setMaterial(material);
            product.setDescription(description);
            productService.addProduct(product, image);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value="{merchantId}/products/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HttpStatus> updateProduct(
        @PathVariable("merchantId") Long merchantId, 
        @PathVariable("productId") Long productId,
        @RequestParam("productName") String productName,
        @RequestParam("sex") String sex,
        @RequestParam("price") Integer price,
        @RequestParam("category") String category,
        @RequestParam("color") String color,
        @RequestParam("size") String size,
        @RequestParam("brand") String brand,
        @RequestParam("material") String material,
        @RequestParam("description") String description,
        @RequestParam("oldImages") String[] oldImages,
        @RequestParam(name="thumbnail", required = false) MultipartFile thumbnail,
        @RequestParam(name="image1", required = false) MultipartFile image1,
        @RequestParam(name="image2", required = false) MultipartFile image2,
        @RequestParam(name="image3", required = false) MultipartFile image3
        ) {
            UpdateProductRequest product = new UpdateProductRequest();
            product.setProductId(productId);
            product.setMerchantId(merchantId);
            product.setProductName(productName);
            product.setSex(Sex.valueOf(sex));
            product.setPrice(price);
            product.setCategory(Category.valueOf(category));
            product.setColor(color);
            product.setSize(size);
            product.setBrand(brand);
            product.setMaterial(material);
            product.setDescription(description);
            product.setOldImages(oldImages);
            productService.updateProduct(product, thumbnail, image1, image2, image3);
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value="{merchantId}/products/{productId}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteProduct(
        @PathVariable("merchantId") Long merchantId, 
        @PathVariable("productId") Long productId
        ) {
            productService.deleteProduct(productId);
            return ResponseEntity.ok(HttpStatus.OK);
    }

}