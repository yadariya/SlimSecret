package com.example.slimsecret.repository;

import com.example.slimsecret.model.Breakfast;
import org.springframework.data.repository.CrudRepository;


public interface BreakfastRepository extends CrudRepository<Breakfast, Long> {
    Breakfast findBreakfastByAlias(String alias);
}
