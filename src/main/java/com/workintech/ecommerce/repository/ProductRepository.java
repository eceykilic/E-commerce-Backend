package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, \n" +
            "p.category_id FROM ecommerce.products AS p WHERE p.name ILIKE %:productName%", nativeQuery = true)
    Optional<Products> findByProductName(String productName);

    @Query(value = "SELECT * FROM ecommerce.products AS p WHERE p.category_id = :categoryID",nativeQuery = true)
    List<Products> getProductsByCategoryId(long categoryID);

    @Query(value = "SELECT * FROM ecommerce.products AS p WHERE p.id = :id",nativeQuery = true)
    Products getProductById(Long id);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id=:categoryID, p.name ILIKE %:name% ORDER BY p.price DESC", nativeQuery = true)
    List<Products> searchAndDescSortAndCategory(Long categoryID,String name);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id=:categoryID, p.name ILIKE %:name% ORDER BY p.price ASC", nativeQuery = true)
    List<Products> searchAndAscSortAndCategory(Long categoryID,String name);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id=:categoryID, p.name ILIKE %:name% ORDER BY p.rating DESC", nativeQuery = true)
    List<Products> searchAndBestSortAndCategory(Long categoryID,String name);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id=:categoryID, p.name ILIKE %:name% ORDER BY p.rating DESC", nativeQuery = true)
    List<Products> searchAndWorstSortAndCategory(Long categoryID,String name);
    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.name ILIKE %:name%", nativeQuery = true)
    List<Products> searchByName(String name);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id:categoryID , p.name ILIKE %:name%", nativeQuery = true)
    List<Products> searchByNameAndCategory(String name,Long categoryID);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p ORDER BY p.price DESC", nativeQuery = true)
    List<Products> highestToLowestSorting();

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id = :categoryID ORDER BY p.price DESC", nativeQuery = true)
    List<Products> highestToLowestSortingAndCategory(Long categoryID);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p ORDER BY p.price ASC", nativeQuery = true)
    List<Products> lowestToHighestSorting();

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p  WHERE p.category_id = :categoryID ORDER BY p.price ASC", nativeQuery = true)
    List<Products> lowestToHighestSortingAndCategory(Long categoryID);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p ORDER BY p.rating ASC", nativeQuery = true)
    List<Products> worstToBestSorting();

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id = :categoryID ORDER BY p.rating ASC", nativeQuery = true)
    List<Products> worstToBestSortingAndCategory(Long categoryID);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p ORDER BY p.rating DESC", nativeQuery = true)
    List<Products> bestToWorstSorting();

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.category_id = :categoryID ORDER BY p.rating DESC", nativeQuery = true)
    List<Products> bestToWorstSortingAndCategory(Long categoryID);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.name ILIKE %:name% ORDER BY p.price ASC", nativeQuery = true)
    List<Products> searchAndLowestSorting(String name);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.name ILIKE %:name% ORDER BY p.price DESC", nativeQuery = true)
    List<Products> searchAndHighestSorting(String name);

    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.name ILIKE %:name% ORDER BY p.rating ASC", nativeQuery = true)
    List<Products> searchAndWorstSorting(String name);
    @Query(value = "SELECT p.id, p.name, p.description, p.price, p.rating, p.sell_count, p.stock, p.image, p.category_id" +
            " FROM ecommerce.products AS p WHERE p.name ILIKE %:name% ORDER BY p.rating DESC", nativeQuery = true)
    List<Products> searchAndBestSorting(String name);



}