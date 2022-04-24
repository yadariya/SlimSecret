package com.example.slimsecret.controller;

import com.example.slimsecret.model.Lunch;
import com.example.slimsecret.model.Product;
import com.example.slimsecret.repository.LunchRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LunchController {

    LunchRepository lunchRepository;

    @PostMapping("/lunch")
    public Lunch createDinner(@RequestBody Lunch lunch) {
        return lunchRepository.save(lunch);
    }

    @PostMapping("/lunch/product/{alias}")
    public Lunch addProductToDinner(@RequestBody Product product, @PathVariable String alias) {
        Lunch lunch = lunchRepository.findLunchByAlias(alias);
        List<Product> productList = lunch.getProductList();
        productList.add(product);
        lunch.setProductList(productList);
        return lunchRepository.save(lunch);
    }

    @GetMapping("/lunch/{alias}")
    public Lunch createDinner(@PathVariable String alias) {
        return lunchRepository.findLunchByAlias(alias);
    }
}
