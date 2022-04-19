package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Item;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends
    JpaRepository<Item, Integer>,
    QuerydslPredicateExecutor<Item> {

}
