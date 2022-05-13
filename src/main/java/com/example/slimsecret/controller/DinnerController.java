package com.example.slimsecret.controller;

import com.example.slimsecret.model.Dinner;
import com.example.slimsecret.model.Product;
import com.example.slimsecret.repository.DinnerRepository;
import com.example.slimsecret.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class DinnerController {
    private final DinnerRepository dinnerRepository;
    private  final  ProductRepository productRepository;

    @PostMapping("/dinner")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Dinner createDinner(@RequestBody Dinner dinner) {
        return dinnerRepository.save(dinner);
    }

    @PostMapping("/dinner/product/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Dinner addProductToDinner(@RequestBody Product product, @PathVariable String alias) {
        Dinner dinner = dinnerRepository.findDinnerByAlias(alias);
        List<Product> productList = dinner.getProductList();
        productList.add(product);
        dinner.setProductList(productList);
        return dinnerRepository.save(dinner);
    }

    @GetMapping("/dinner/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Dinner createDinner(@PathVariable String alias) {
        return dinnerRepository.findDinnerByAlias(alias);
    }

    @DeleteMapping("dinner/{id}/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void deleteProductInBreakfast(@PathVariable Long id, @PathVariable String alias){
        Dinner dinner  = dinnerRepository.findDinnerByAlias(alias);
        List<Product> productArrayList = dinner.getProductList();
        productArrayList.removeIf(product -> product.getId().equals(id));
        dinner.setProductList(productArrayList);
        dinnerRepository.save(dinner);
        productRepository.deleteById(id);
    }
}
