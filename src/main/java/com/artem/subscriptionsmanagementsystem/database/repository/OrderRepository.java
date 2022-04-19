package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends
    JpaRepository<Order, Integer>,
    QuerydslPredicateExecutor<Order> {

    List<Order> findAllBySubscriptionUserId(Integer userId);
}
