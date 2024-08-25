package com.example.meeTeam.orders.repository;

import com.example.meeTeam.item.Item;
import com.example.meeTeam.orders.OrderItem;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<OrderItem> findById(Long orderItemid) ;

    void deleteById(Long orderItemid);
}
