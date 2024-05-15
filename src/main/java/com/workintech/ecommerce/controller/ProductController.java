package com.workintech.ecommerce.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.response.ProductResponse;
import com.workintech.ecommerce.entity.Products;
import com.workintech.ecommerce.service.CategoryService;
import com.workintech.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;
    private final RestTemplateBuilder restTemplateBuilder;
    private CategoryService categoryService;
    private static final String GET_PRODUCTS = "https://workintech-fe-ecommerce.onrender.com/products";

    @Autowired
    public ProductController(ProductService productService, RestTemplateBuilder restTemplateBuilder) {
        this.productService = productService;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @PostMapping("/all")
    public List<ProductResponse> saveAll() {
        List<String> imagesList = new ArrayList<>();
        List<Products> productList = new ArrayList<>();

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<JsonNode> responses = restTemplate.getForEntity(GET_PRODUCTS, JsonNode.class);

        for (JsonNode node : responses.getBody().get("products")) {
            Products product = new Products();
            product.setName(node.get("name").asText());
            product.setDescription(node.get("description").asText());
            product.setPrice(node.get("price").asText());
            product.setStock(node.get("stock").asText());
            product.setCategoryId(node.get("category_id").asLong());
            product.setRating(node.get("rating").asText());
            product.setImage(node.get("images").get(0).get("url").asText());
            productList.add(product);
        }
        return productService.saveAll(productList);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductsByCategoryId(@PathVariable Long id) {
        return Converter.findProduct(productService.getProductById(id));
    }

    @GetMapping("/")
    @ResponseBody
    public List<ProductResponse> findByParams(@RequestParam(name = "filter", required = false) String filter,
                                              @RequestParam(name = "sort", required = false, defaultValue = "default") String sort,
                                              @RequestParam(name = "category", required = false) Long id) {

        List<ProductResponse> responseList=Converter.findProducts(productService.getAllProducts());
        //SORT
        if (filter == null && id == null&& sort!=null) {
            switch (sort) {
                case "rating:desc":
                    responseList= productService.bestToWorstSorting();
                case "rating:asc":
                    responseList= productService.worstToBestSorting();
                case "price:desc":
                    responseList= productService.highestToLowestSorting();
                case "price:asc":
                    responseList= productService.lowestToHighestSorting();
            }
        }

        //SORT AND FİLTER
        else if (filter != null && id == null && sort!=null) {
            return switch (sort) {
                case "rating:desc" -> responseList=productService.searchAndBestSorting(filter);
                case "rating:asc" -> responseList=productService.searchAndWorstSorting(filter);
                case "price:desc" -> responseList=productService.searchAndHighestSorting(filter);
                case "price:asc" -> responseList=productService.searchAndLowestSorting(filter);
                default -> responseList=productService.searchByName(filter);
            };
        }

        //SORT AND CATEGORY
        else if (filter == null && id != null && sort!=null) {
            return switch (sort) {
                case "rating:desc" -> responseList=Converter.findProducts(productService.bestToWorstSortingAndCategory(id));
                case "rating:asc" -> responseList=Converter.findProducts(productService.worstToBestSortingAndCategory(id));
                case "price:desc" -> responseList= Converter.findProducts(productService.highestToLowestSortingAndCategory(id));
                case "price:asc" ->  responseList=Converter.findProducts(productService.lowestToHighestSortingAndCategory(id));
                default -> responseList=Converter.findProducts(productService.getProductsByCategoryId(id));
            };
        }

        //FILTER AND CATEGORY
        else if (filter != null && String.valueOf(id) != null && sort==null) {
            responseList=Converter.findProducts(productService.searchByNameAndCategory(filter, id));
        }

        //FILTER SORT CATEGORY
        else if(filter !=null && id != null && sort !=null){
            return switch (sort) {
                case "rating:desc" ->responseList=  Converter.findProducts(productService.searchAndBestSortAndCategory(id,filter));
                case "rating:asc" -> responseList= Converter.findProducts(productService.searchAndWorstSortAndCategory(id,filter));
                case "price:desc" -> responseList=  Converter.findProducts(productService.searchAndAscSortAndCategory(id,filter));
                case "price:asc" ->  responseList= Converter.findProducts(productService.searchAndDescSortAndCategory(id,filter));
                default -> responseList=Converter.findProducts(productService.getProductsByCategoryId(id));
            };
        }
        return responseList;
    }

}