package com.example.meeTeam.item.repository;

import com.example.meeTeam.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long id);
    void deleteById(Long id);
}