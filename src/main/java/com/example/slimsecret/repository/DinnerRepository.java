package com.example.slimsecret.repository;

import com.example.slimsecret.model.Dinner;
import org.springframework.data.repository.CrudRepository;


public interface DinnerRepository extends CrudRepository<Dinner, Long> {
    Dinner findDinnerByAlias(String alias);
}
