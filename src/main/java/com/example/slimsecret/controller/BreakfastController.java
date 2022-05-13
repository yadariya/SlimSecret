package com.example.slimsecret.controller;

import com.example.slimsecret.model.Breakfast;
import com.example.slimsecret.model.Product;
import com.example.slimsecret.repository.BreakfastRepository;
import com.example.slimsecret.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class BreakfastController {
    private final BreakfastRepository breakfastRepository;
    private final ProductRepository productRepository;

    @PostMapping("/breakfast")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Breakfast createDinner(@RequestBody Breakfast breakfast) {
        return breakfastRepository.save(breakfast);
    }

    @PostMapping("/breakfast/product/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Breakfast addProductToDinner(@RequestBody Product product, @PathVariable String alias) {
        Breakfast breakfast = breakfastRepository.findBreakfastByAlias(alias);
        List<Product> productList = breakfast.getProductList();
        productList.add(product);
        breakfast.setProductList(productList);
        return breakfastRepository.save(breakfast);
    }

    @GetMapping("/breakfast/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Breakfast createDinner(@PathVariable String alias) {
        return breakfastRepository.findBreakfastByAlias(alias);
    }

    @DeleteMapping("breakfast/{id}/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void deleteProductInBreakfast(@PathVariable Long id, @PathVariable String alias){
        Breakfast breakfast  = breakfastRepository.findBreakfastByAlias(alias);
        List<Product> productArrayList = breakfast.getProductList();
        productArrayList.removeIf(product -> product.getId().equals(id));
        breakfast.setProductList(productArrayList);
        breakfastRepository.save(breakfast);
        productRepository.deleteById(id);
    }
}
