package com.example.meeTeam.item.repository;

import com.example.meeTeam.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long id);
    void deleteById(Long id);

    @Query("SELECT i FROM Item i WHERE i.itemCategory = :itemCategory")
    Page<Item> findByItemCategoryWithPaging(@Param("itemCategory") String itemCategory, Pageable pageable);

}