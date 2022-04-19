package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Price;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PriceRepository extends
    JpaRepository<Price, Integer>,
    QuerydslPredicateExecutor<Price> {

    @EntityGraph(attributePaths = {"item", "duration"})
    List<Price> findAll();

    @EntityGraph(attributePaths = {"item", "duration"})
    List<Price> findAllByItemId(Integer id);

    @EntityGraph(attributePaths = {"item", "duration"})
    Optional<Price> findById(Integer id);
}
