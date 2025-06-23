package com.example.dama_shop.repository;

import com.example.dama_shop.model.CartItem;
import com.example.dama_shop.model.Product;
import com.example.dama_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    Optional<CartItem> findByUserAndProduct(User user, Product product);

    List<CartItem> findCartItemsByUser(User user);

    void deleteByUser(User user);
}
