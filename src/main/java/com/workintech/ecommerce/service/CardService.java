package com.workintech.ecommerce.service;

import com.workintech.ecommerce.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findAll();
    Card findById(Long id);
    Card save(Card card);
    void delete(Card card);
    Card update(Card card);
}