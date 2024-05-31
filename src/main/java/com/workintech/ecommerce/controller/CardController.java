package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.entity.Card;
import com.workintech.ecommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/card")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/")
    public Card saveCard(@RequestBody Card card){
        return cardService.save(card);
    }

    @GetMapping("/")
    public List<Card> getAllCards(){
        return cardService.findAll();
    }

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable long id){
        return cardService.findById(id);
    }

    @PutMapping("/{id}") // Kart güncelleme
    public Card updateCard(@PathVariable long id, @RequestBody Card card){
        card.setId(id); // Güncelleme işleminde id'yi set etmemiz gerek
        return cardService.update(card);
    }

    @DeleteMapping("/{id}") // Kart silme
    public void deleteCard(@PathVariable long id){
        Card card = cardService.findById(id); // Önce kartı bul
        cardService.delete(card); // Sonra sil
    }
}