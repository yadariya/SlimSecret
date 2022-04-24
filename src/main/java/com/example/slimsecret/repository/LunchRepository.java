package com.example.slimsecret.repository;

import com.example.slimsecret.model.Lunch;
import org.springframework.data.repository.CrudRepository;


public interface LunchRepository extends CrudRepository<Lunch, Long> {
    Lunch findLunchByAlias(String alias);
}
