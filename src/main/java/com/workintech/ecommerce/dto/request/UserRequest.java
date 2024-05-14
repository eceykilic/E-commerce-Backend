package com.workintech.ecommerce.dto.request;

public record UserRequest(String name, String email, String password, String role) {
}