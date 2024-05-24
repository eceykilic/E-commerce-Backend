package com.workintech.ecommerce.converter;

import com.workintech.ecommerce.dto.response.*;
import com.workintech.ecommerce.entity.*;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    // Address Conversion Methods
    public static AddressResponse findAddress(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getName(),
                address.getSurname(),
                address.getPhone(),
                address.getCity(),
                address.getDistrict(),
                address.getNeighborhood(),
                address.getAddress()
        );
    }

    public static List<AddressResponse> findAll(List<Address> addresses) {
        List<AddressResponse> addressResponses = new ArrayList<>();
        for (Address address : addresses) {
            addressResponses.add(findAddress(address));
        }
        return addressResponses;
    }

    // Category Conversion Methods
    public static List<CategoryResponse> findCategories(List<Category> categories) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            categoryResponses.add(new CategoryResponse(
                    category.getId(),
                    category.getCode(),
                    category.getTitle(),
                    category.getImg(),
                    category.getRating(),
                    category.getGender()
            ));
        }
        return categoryResponses;
    }

    // User Conversion Methods
    public static List<UserResponse> findUsers(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            ));
        }
        return userResponses;
    }

    public static UserResponse findUser(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User convertUserResponseToUser(UserResponse userResponse) {
        User user = new User();
        user.setId(userResponse.getId());
        user.setName(userResponse.getName());
        user.setEmail(userResponse.getEmail());
        return user;
    }

    // Product Conversion Methods
    public static List<ProductResponse> findProducts(List<Products> products) {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Products product : products) {
            productResponses.add(new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock(),
                    product.getCategoryId(),
                    product.getRating(),
                    product.getSellCount(),
                    product.getImage()
            ));
        }
        return productResponses;
    }

    public static ProductResponse findProduct(Products product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategoryId(),
                product.getRating(),
                product.getSellCount(),
                product.getImage()
        );
    }

    // Role Conversion Methods
    public static List<RoleResponse> findRoles(List<Role> roles) {
        List<RoleResponse> responses = new ArrayList<>();
        for (Role role : roles) {
            String roleName;
            // Assign name based on the authority of the role
            switch (role.getAuthority()) {
                case "ROLE_ADMIN":
                    roleName = "Yönetici";
                    break;
                case "ROLE_STORE":
                    roleName = "Mağaza";
                    break;
                case "ROLE_CUSTOMER":
                    roleName = "Müşteri";
                    break;
                default:
                    roleName = "Bilinmeyen";
                    break;
            }
            responses.add(new RoleResponse(role.getId(), roleName, role.getAuthority()));
        }
        return responses;
    }
}
