package com.simplilearn.mihneapopa.service;

import com.simplilearn.mihneapopa.exceptions.ProductNotFoundException;
import com.simplilearn.mihneapopa.model.Product;
import com.simplilearn.mihneapopa.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) throws ProductNotFoundException {
        try {
            Optional<Product> product = productRepository.findById(id);
            return product.get();
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        Product productToBeSaved = Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
        return productRepository.save(productToBeSaved);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) throws ProductNotFoundException {
        try {
            Optional<Product> productToBeUpdated = productRepository.findById(product.getId());
            if (!productToBeUpdated.isPresent()) {
                throw new ProductNotFoundException();
            }

            Product existingProduct = productToBeUpdated.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            return productRepository.save(existingProduct);
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) throws ProductNotFoundException {
        try {
         Optional<Product> productToBeDeleted = productRepository.findById(id);
         productToBeDeleted.ifPresent(value -> productRepository.delete(value));
        } catch (Exception e) {
            throw new ProductNotFoundException();
        }
    }

}
