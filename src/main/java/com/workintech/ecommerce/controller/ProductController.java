package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.converter.Converter;
import com.workintech.ecommerce.dto.response.ProductResponse;
import com.workintech.ecommerce.entity.Products;
import com.workintech.ecommerce.service.ProductService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final String EXTERNAL_API_URL = "https://workintech-fe-ecommerce.onrender.com/products?limit=587";
    private static final String EXTERNAL_API_BASE_URL = "https://workintech-fe-ecommerce.onrender.com/products/";

    private final RestTemplate restTemplate;
    private final ProductService productService;

    @Autowired
    public ProductController(RestTemplate restTemplate, ProductService productService) {
        this.restTemplate = restTemplate;
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        try {
            // Harici API'den verileri çek
            ResponseEntity<String> response = restTemplate.getForEntity(EXTERNAL_API_URL, String.class);
            // Harici API'den alınan veriyi doğrudan döndür
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Harici API'den veri çekilirken bir hata oluştu.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@Positive @PathVariable Long id) {
        try {
            ResponseEntity<Products> response = restTemplate.getForEntity(EXTERNAL_API_BASE_URL + id, Products.class);
            Products product = response.getBody();
            if (product != null) {
                // Harici API yanıtını kontrol edin
                System.out.println("API Response: " + product);

                // Products varlığını ProductResponse DTO'suna dönüştür
                ProductResponse productResponse = Converter.findProduct(product);
                return ResponseEntity.ok(productResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ürün bulunamadı.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ürün getirilirken bir hata oluştu.");
        }
    }
}
