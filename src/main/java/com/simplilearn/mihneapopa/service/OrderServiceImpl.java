package com.simplilearn.mihneapopa.service;

import com.simplilearn.mihneapopa.exceptions.OrderNotFoundException;
import com.simplilearn.mihneapopa.model.Order;
import com.simplilearn.mihneapopa.model.Product;
import com.simplilearn.mihneapopa.model.User;
import com.simplilearn.mihneapopa.repository.OrderRepository;
import com.simplilearn.mihneapopa.repository.ProductRepository;
import com.simplilearn.mihneapopa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    ;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) throws OrderNotFoundException {
        try {
            Optional<Order> order = orderRepository.findById(id);
            return order.get();
        } catch (Exception e) {
            throw new OrderNotFoundException();
        }
    }

    @Override
    public Order saveOrder(Integer productId, Integer userId) {
        Product product = productRepository.findById(productId).get();
        User user = userRepository.findById(userId).get();
        Order order = Order.builder()
                .product(product)
                .user(user)
                .orderDate(LocalDateTime.now())
                .build();
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) throws OrderNotFoundException {
        try {
            Optional<Order> orderOptional = orderRepository.findById(order.getId());
            if (!orderOptional.isPresent()) {
                throw new OrderNotFoundException();
            }

            Order existingOrder = orderOptional.get();
            existingOrder.setProduct(order.getProduct());

            return orderRepository.save(existingOrder);
        } catch (Exception e) {
            throw new OrderNotFoundException();
        }
    }

    @Override
    public void deleteOrder(Integer id) throws OrderNotFoundException {
        try {
            Optional<Order> order = orderRepository.findById(id);
            order.ifPresent(value -> orderRepository.delete(value));
            if (!order.isPresent()) {
                throw new OrderNotFoundException();
            }
        } catch (Exception e) {
            throw new OrderNotFoundException();
        }
    }
}
