package com.example.slimsecret.controller;

import com.example.slimsecret.model.Product;
import com.example.slimsecret.model.Snack;
import com.example.slimsecret.repository.ProductRepository;
import com.example.slimsecret.repository.SnackRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class SnackController {

    private final SnackRepository snackRepository;
    private final ProductRepository productRepository;

    @PostMapping("/snack")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Snack createSnack(@RequestBody Snack snack) {
        return snackRepository.save(snack);
    }

    @PostMapping("/snack/product/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Snack addProductToSnack(@RequestBody Product product, @PathVariable String alias) {
        Snack snack = snackRepository.findSnackByAlias(alias);
        List<Product> productList = snack.getProductList();
        productList.add(product);
        snack.setProductList(productList);
        return snackRepository.save(snack);
    }

    @GetMapping("/snack/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Snack createSnack(@PathVariable String alias) {
        return snackRepository.findSnackByAlias(alias);
    }

    @DeleteMapping("snack/{id}/{alias}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void deleteProductInSnack(@PathVariable Long id, @PathVariable String alias){
        Snack snack  = snackRepository.findSnackByAlias(alias);
        List<Product> productArrayList = snack.getProductList();
        productArrayList.removeIf(product -> product.getId().equals(id));
        snack.setProductList(productArrayList);
        snackRepository.save(snack);
        productRepository.deleteById(id);
    }
}
