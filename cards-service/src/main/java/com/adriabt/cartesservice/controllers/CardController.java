package com.adriabt.cartesservice.controllers;

import com.adriabt.cartesservice.CartesServiceApplication;
import com.adriabt.cartesservice.entities.Card;
import com.adriabt.cartesservice.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final ICardService cardService;
    @PostMapping("/create")
    public ResponseEntity<?> addCard(@RequestBody Card card){
        try {
            return ResponseEntity.ok(cardService.createCard(card));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
