package com.neGleb1.api.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neGleb1.api.dto.ProductCardResponse;
import com.neGleb1.api.dto.ProductItemResponse;
import com.neGleb1.api.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<ProductItemResponse> getProducts() {
        return productService.getAllProducts(); 
    }

    @GetMapping("/{productId}")
    @Transactional(readOnly = true)
    public ProductCardResponse getProduct(@PathVariable("productId") long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/product")
    @Transactional(readOnly = true)
    public List<ProductItemResponse> getProductsByCategory(
        @RequestParam(value="sex", required = true) String sex, 
        @RequestParam(value="category", required = true) String category
    ) {
        return productService.getAllProductsBySexAndCategory(sex, category);
    }

    @GetMapping("/search")
    @Transactional(readOnly = true)
    public List<ProductItemResponse> getProductsByQuery(
        @RequestParam(value="q", required = true) String q
    ) {
        return productService.getAllProductsByQuery(q);
    }
}
