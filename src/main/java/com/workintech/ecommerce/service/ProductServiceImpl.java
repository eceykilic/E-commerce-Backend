package com.workintech.ecommerce.service;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.response.ProductResponse;
import com.workintech.ecommerce.entity.Products;
import com.workintech.ecommerce.exception.EcommerceException;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.service.ProductService;
import com.workintech.ecommerce.util.EcommerceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Products saveProduct(Products product) {
        Optional<Products> foundProduct = productRepository.findByProductName(product.getName());
        if(foundProduct.isPresent()){
            throw new EcommerceException(EcommerceValidation.IS_PRODUCT_PRESENT, HttpStatus.BAD_REQUEST);
        }
        EcommerceValidation.checkEmptyOrNull(product.getName(),"name");
        EcommerceValidation.checkEmptyOrNull(product.getDescription(),"description");
        if (product.getImages() == null || product.getImages().isEmpty()) {
            throw new EcommerceException("Image cannot be empty or null", HttpStatus.BAD_REQUEST);
        }
        EcommerceValidation.isCategoryIdValid("category id", product.getCategoryId());
        return productRepository.save(product);
    }

    @Override
    public List<ProductResponse> saveAll(List<Products> productsList) {
        List<Products> savedProducts = productRepository.saveAll(productsList);
        return Converter.findProducts(savedProducts);
    }


    @Override
    public ProductResponse findByProductName(String filter) {
        Optional<Products> optionalProduct = productRepository.findByProductName(filter);
        if(optionalProduct.isPresent()){
            return Converter.findProduct(optionalProduct.get());
        }
        EcommerceValidation.checkEmptyOrNull(filter,"filter");
        throw new EcommerceException(EcommerceValidation.IS_NOT_PRODUCT_PRESENT, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<Products> getProductsByCategoryId(Long categoryId) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        return productRepository.getProductsByCategoryId(categoryId);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products deleteProduct(Long id) {
        EcommerceValidation.isValid(id,"id");
        Products deletedProduct=new Products();
        Optional<Products> product = productRepository.findById(id);
        if(product.isPresent()){
            deletedProduct=product.get();
            productRepository.delete(deletedProduct);
            return deletedProduct;
        }
        throw new EcommerceException(EcommerceValidation.IS_NOT_PRODUCT_PRESENT, HttpStatus.NOT_FOUND);
    }
    @Override
    public Products getProductById(Long id) {
        EcommerceValidation.isValid(id,"id");
        Products product=new Products();
        Optional<Products> optionalProduct= productRepository.findById(id);
        if(optionalProduct.isPresent()){
            product= optionalProduct.get();
        }
        return product;
    }
}
