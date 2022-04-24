package com.example.slimsecret.controller;

import com.example.slimsecret.model.Dinner;
import com.example.slimsecret.model.Product;
import com.example.slimsecret.repository.DinnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class DinnerController {
    private final DinnerRepository dinnerRepository;

    @PostMapping("/dinner")
    public Dinner createDinner(@RequestBody Dinner dinner) {
        return dinnerRepository.save(dinner);
    }

    @PostMapping("/dinner/product/{alias}")
    public Dinner addProductToDinner(@RequestBody Product product, @PathVariable String alias) {
        Dinner dinner = dinnerRepository.findDinnerByAlias(alias);
        List<Product> productList = dinner.getProductList();
        productList.add(product);
        dinner.setProductList(productList);
        return dinnerRepository.save(dinner);
    }

    @GetMapping("/dinner/{alias}")
    public Dinner createDinner(@PathVariable String alias) {
        return dinnerRepository.findDinnerByAlias(alias);
    }
}
