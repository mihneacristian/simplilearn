package com.simplilearn.mihneapopa.service;

import com.simplilearn.mihneapopa.exceptions.ProductNotFoundException;
import com.simplilearn.mihneapopa.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getProductById(Integer id) throws ProductNotFoundException;

    Product saveProduct(Product product);

    Product updateProduct(Product product) throws ProductNotFoundException;

    void deleteProduct(Integer id) throws ProductNotFoundException;
}
