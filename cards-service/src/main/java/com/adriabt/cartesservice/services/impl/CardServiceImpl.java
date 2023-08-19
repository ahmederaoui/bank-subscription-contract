package com.adriabt.cartesservice.services.impl;

import com.adriabt.cartesservice.entities.Card;
import com.adriabt.cartesservice.repositories.CardRepository;
import com.adriabt.cartesservice.services.ICardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {
    private final CardRepository cardRepository;
    @Override
    public Card createCard(Card card) {
        card.setCardNumber(new Random().nextLong(100,999));
        return cardRepository.save(card);
    }
}
