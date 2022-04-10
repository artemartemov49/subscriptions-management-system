package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DurationRepository extends
    JpaRepository<Duration, Integer>,
    QuerydslPredicateExecutor<Duration> {

}
