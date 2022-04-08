package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import com.artem.subscriptionsmanagementsystem.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends
    JpaRepository<Item, Integer>,
    QuerydslPredicateExecutor<User> {

}
