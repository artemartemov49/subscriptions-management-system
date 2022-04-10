package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PriceRepository extends
    JpaRepository<Price, Integer>,
    QuerydslPredicateExecutor<Price> {

}
