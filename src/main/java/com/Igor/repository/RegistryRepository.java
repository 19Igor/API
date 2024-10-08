package com.Igor.repository;

import com.Igor.model.Registry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends
        JpaRepository<Registry, Long>,
        QuerydslPredicateExecutor<Registry>{

}
