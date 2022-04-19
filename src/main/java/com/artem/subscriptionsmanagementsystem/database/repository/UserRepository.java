package com.artem.subscriptionsmanagementsystem.database.repository;

import com.artem.subscriptionsmanagementsystem.database.entity.User;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
    JpaRepository<User, Integer>,
    QuerydslPredicateExecutor<User> {

    @EntityGraph(attributePaths = {"subscriptions.item"})
    Optional<User> findById(Integer id);
}
