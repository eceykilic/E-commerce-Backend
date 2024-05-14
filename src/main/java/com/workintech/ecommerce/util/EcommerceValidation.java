package com.workintech.ecommerce.util;


import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exception.EcommerceException;
import org.springframework.http.HttpStatus;

public class EcommerceValidation {

    public static String MUST_NOT_BE_NULL_EMPTY = "must not be null or empty";
    public static String MUST_BE_BIGGER_THAN_0="must be bigger than 0!";
    public static final String ENTER_VALID_CATEGORYID=" must be a valid!";
    public static final String IS_EMAIL_PRESENT="Email already taken.";
    public static final String IS_USER_PRESENT="User not found!";
    public static final String IS_USER_VALID="User not valid!";
    public static  final String IS_PRODUCT_PRESENT="There is a product with the given name!";
    public static final String IS_NOT_PRODUCT_PRESENT="The product is not found!";
    public static final String IS_NOT_PRESENT_ADDRESS="The address is not found!";

    public static void checkEmptyOrNull(String value, String field) {
        if (value == null || value.isEmpty())
            throw new EcommerceException(field + MUST_NOT_BE_NULL_EMPTY, HttpStatus.BAD_REQUEST);
    }
    public static void isValid(double value, String field) {
        if (value < 0) throw new EcommerceException(field + MUST_BE_BIGGER_THAN_0, HttpStatus.BAD_REQUEST);
    }
    public static void isCategoryIdValid(String value, long id){
        if (id < 0 || id > 14 ) throw new EcommerceException(value + ENTER_VALID_CATEGORYID + id, HttpStatus.BAD_REQUEST);
    }

    public static void IsUserPresent(User user){
        if(user == null){
            throw new EcommerceException(EcommerceValidation.IS_USER_PRESENT, HttpStatus.NOT_FOUND);
        }
    }



}