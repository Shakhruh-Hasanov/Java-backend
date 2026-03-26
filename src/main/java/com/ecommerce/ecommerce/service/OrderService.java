package com.ecommerce.ecommerce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.dto.OrderItemRequest;
import com.ecommerce.ecommerce.dto.OrderRequest;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.OrderItem;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.OrderRepository;
import com.ecommerce.ecommerce.repository.ProductRepository;
import com.ecommerce.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;


import jakarta.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    public Order createOrder(OrderRequest request){
        
        //Lets find a user 

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found!"));

        //lets create an order
        Order order = new Order();

        order.setUser(user);
        order.setCreateAt(LocalDateTime.now());

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemRequest itemRequest : request.getItems()){

            Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found!"));

            // checking if there is enough stock
            if(product.getStock() < itemRequest.getQuantity()){

                throw new RuntimeException("Not enough stock for product:" + product.getName());
            
            }


            // creating the order items
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);

            // calculating the total 
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            //reducing the stock 

            product.setStock(product.getStock() - itemRequest.getQuantity());

            orderItems.add(orderItem);

        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);

    }
}
