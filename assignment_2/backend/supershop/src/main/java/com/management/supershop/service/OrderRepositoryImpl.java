package com.management.supershop.service;

import com.management.supershop.model.Cart;
import com.management.supershop.model.Order;
import com.management.supershop.model.OrderItem;
import com.management.supershop.repository.CartRepository;
import com.management.supershop.repository.OrderRepository;
import com.management.supershop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepositoryImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public OrderRepositoryImpl(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order placeOrder(Long userId) {
        // Fetch user cart
        List<Cart> cartItems = cartRepository.getCartByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Cannot place order.");
        }

        // Calculate total price and prepare order items
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        for (Cart cart : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cart.getProductId());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setPrice(productRepository.findById(cart.getProductId()).getPrice());
            totalPrice = totalPrice.add(orderItem.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
            orderItems.add(orderItem);
        }

        // Save the order
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        // Clear the cart
        cartItems.forEach(cart -> cartRepository.removeProductFromCart(userId, cart.getProductId()));

        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }
}