package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card,Long> {
    @Query(value = "SELECT * FROM ecommerce.card AS c WHERE c.id = :cardId",nativeQuery = true)
    Card getCardById(long cardId);
}