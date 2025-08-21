package com.example.ecom_proj.controller;

import com.example.ecom_proj.model.Product;
import com.example.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;


//    @RequestMapping("/")
//    public String greet(){
//        return "Hello world";
//    }
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
//    @PostMapping("/product")
//    public void addProduct(@RequestBody Product p){
//        service.AddProduct(p);
//    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        return new ResponseEntity<>(service.getProduct(id),HttpStatus.OK);
    }
    @PostMapping(value = "/product", consumes = "multipart/form-data")
    public ResponseEntity<?> addProduct(
            @RequestPart("product") Product p,
            @RequestPart("imageFile") MultipartFile imageFile
    ) {
        try {
            Product savedProduct = service.addProduct(p, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error saving product: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
