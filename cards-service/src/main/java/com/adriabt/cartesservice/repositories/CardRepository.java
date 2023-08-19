package com.adriabt.cartesservice.repositories;

import com.adriabt.cartesservice.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,String> {
}
