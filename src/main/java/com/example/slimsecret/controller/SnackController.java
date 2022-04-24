package com.example.slimsecret.controller;

import com.example.slimsecret.model.Product;
import com.example.slimsecret.model.Snack;
import com.example.slimsecret.repository.SnackRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class SnackController {

    SnackRepository snackRepository;

    @PostMapping("/snack")
    public Snack createDinner(@RequestBody Snack snack) {
        return snackRepository.save(snack);
    }

    @PostMapping("/snack/product/{alias}")
    public Snack addProductToDinner(@RequestBody Product product, @PathVariable String alias) {
        Snack snack = snackRepository.findSnackByAlias(alias);
        List<Product> productList = snack.getProductList();
        productList.add(product);
        snack.setProductList(productList);
        return snackRepository.save(snack);
    }

    @GetMapping("/snack/{alias}")
    public Snack createDinner(@PathVariable String alias) {
        return snackRepository.findSnackByAlias(alias);
    }
}
