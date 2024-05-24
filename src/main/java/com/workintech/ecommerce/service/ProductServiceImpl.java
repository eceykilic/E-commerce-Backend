package com.workintech.ecommerce.ecommerce.service;

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
        EcommerceValidation.checkEmptyOrNull(product.getImage(),"image");
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
    public List<ProductResponse> searchByName(String filter) {
        EcommerceValidation.checkEmptyOrNull(filter,"filter");
        return Converter.findProducts(productRepository.searchByName(filter));
    }

    @Override
    public List<ProductResponse> highestToLowestSorting() {
        return Converter.findProducts(productRepository.highestToLowestSorting());
    }

    @Override
    public List<ProductResponse> lowestToHighestSorting() {
        return Converter.findProducts(productRepository.lowestToHighestSorting());
    }

    @Override
    public List<ProductResponse> worstToBestSorting() {
        return Converter.findProducts(productRepository.worstToBestSorting());
    }

    @Override
    public List<ProductResponse> bestToWorstSorting() {
        return Converter.findProducts(productRepository.bestToWorstSorting());
    }

    @Override
    public List<ProductResponse> searchAndLowestSorting(String filter) {
        EcommerceValidation.checkEmptyOrNull(filter,"filter");
        return Converter.findProducts(productRepository.searchAndLowestSorting(filter));
    }

    @Override
    public List<ProductResponse> searchAndHighestSorting(String filter) {
        EcommerceValidation.checkEmptyOrNull(filter,"filter");
        return Converter.findProducts(productRepository.searchAndHighestSorting(filter));
    }

    @Override
    public List<ProductResponse> searchAndWorstSorting(String filter) {
        EcommerceValidation.checkEmptyOrNull(filter,"filter");
        return Converter.findProducts(productRepository.searchAndWorstSorting(filter));
    }

    @Override
    public List<ProductResponse> searchAndBestSorting(String filter) {
        EcommerceValidation.checkEmptyOrNull(filter,"filter");
        return Converter.findProducts(productRepository.searchAndBestSorting(filter));
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

    @Override
    public List<Products> searchByNameAndCategory(String filter, Long categoryId) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        EcommerceValidation.checkEmptyOrNull(filter,"filter ");
        return productRepository.searchByNameAndCategory(filter,categoryId);
    }

    @Override
    public List<Products> searchAndWorstSortAndCategory(Long categoryId, String filter) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        EcommerceValidation.checkEmptyOrNull(filter,"filter ");
        return productRepository.searchAndWorstSortAndCategory(categoryId,filter);
    }

    @Override
    public List<Products> searchAndBestSortAndCategory(Long categoryId, String filter) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        EcommerceValidation.checkEmptyOrNull(filter,"filter ");
        return productRepository.searchAndBestSortAndCategory(categoryId,filter);
    }

    @Override
    public List<Products> searchAndAscSortAndCategory(Long categoryId, String filter) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        EcommerceValidation.checkEmptyOrNull(filter,"filter ");
        return productRepository.searchAndAscSortAndCategory(categoryId, filter);
    }

    @Override
    public List<Products> searchAndDescSortAndCategory(Long categoryId, String filter) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        EcommerceValidation.checkEmptyOrNull(filter,"filter ");
        return productRepository.searchAndDescSortAndCategory(categoryId, filter);
    }

    @Override
    public List<Products> highestToLowestSortingAndCategory(Long categoryId) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        return productRepository.highestToLowestSortingAndCategory(categoryId);
    }

    @Override
    public List<Products> lowestToHighestSortingAndCategory(Long categoryId) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        return productRepository.lowestToHighestSortingAndCategory(categoryId);
    }

    @Override
    public List<Products> worstToBestSortingAndCategory(Long categoryId) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        return productRepository.worstToBestSortingAndCategory(categoryId);
    }


    @Override
    public List<Products> bestToWorstSortingAndCategory(Long categoryId) {
        EcommerceValidation.isCategoryIdValid("category id",categoryId);
        return productRepository.bestToWorstSortingAndCategory(categoryId);
    }

    @Override
    public List<ProductResponse> fetchAndSaveAllFromExternalService() {
        return null;
    }


}