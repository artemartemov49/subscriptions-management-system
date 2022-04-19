package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.Subscription;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SubscriptionRepository extends
    JpaRepository<Subscription, Integer>,
    QuerydslPredicateExecutor<Subscription> {

    Optional<Subscription> findByUserIdAndItemId(Integer userId, Integer itemId);

}
