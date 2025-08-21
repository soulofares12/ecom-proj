package com.example.ecom_proj.service;

import com.example.ecom_proj.model.Product;
import com.example.ecom_proj.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

//    public void AddProduct(Product p) {
//        repo.save(p);
//    }

    public Product getProduct(int id) {
       return repo.findById(id).orElse(new Product());
    }

    public Product addProduct(Product p, MultipartFile imageFile) throws IOException {
        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType(imageFile.getContentType());
        p.setImageData(imageFile.getBytes());
       return repo.save(p);
    }
}
