package com.example.slimsecret.repository;

import com.example.slimsecret.model.Snack;
import org.springframework.data.repository.CrudRepository;


public interface SnackRepository extends CrudRepository<Snack, Long> {
    Snack findSnackByAlias(String alias);
}
