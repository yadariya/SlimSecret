package com.example.slimsecret.controller;

import com.example.slimsecret.model.Lunch;
import com.example.slimsecret.model.Product;
import com.example.slimsecret.repository.LunchRepository;
import com.example.slimsecret.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LunchController {

    private final LunchRepository lunchRepository;
    private final ProductRepository productRepository;

    @PostMapping("/lunch")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Lunch createDinner(@RequestBody Lunch lunch) {
        return lunchRepository.save(lunch);
    }

    @PostMapping("/lunch/product/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Lunch addProductToDinner(@RequestBody Product product, @PathVariable String alias) {
        Lunch lunch = lunchRepository.findLunchByAlias(alias);
        List<Product> productList = lunch.getProductList();
        productList.add(product);
        lunch.setProductList(productList);
        return lunchRepository.save(lunch);
    }

    @GetMapping("/lunch/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Lunch createDinner(@PathVariable String alias) {
        return lunchRepository.findLunchByAlias(alias);
    }

    @DeleteMapping("lunch/{id}/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void deleteProductInLunch(@PathVariable Long id, @PathVariable String alias) {
        Lunch lunch = lunchRepository.findLunchByAlias(alias);
        List<Product> productArrayList = lunch.getProductList();
        productArrayList.removeIf(product -> product.getId().equals(id));
        lunch.setProductList(productArrayList);
        lunchRepository.save(lunch);
        productRepository.deleteById(id);
    }
}
