package com.agh.order.service;


import com.agh.order.entity.OrderInfo;
import com.agh.order.model.OrderCreatedEvent;
import com.agh.order.model.OrderStatus;
import com.agh.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderEventProducer orderEventProducer;

    private final OrderRepository orderRepository;

    public OrderService(OrderEventProducer orderEventProducer, OrderRepository orderRepository) {
        this.orderEventProducer = orderEventProducer;
        this.orderRepository = orderRepository;
    }

    public OrderInfo placeOrder(OrderInfo order){

        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(order.getOrderId());
        event.setProductId(order.getProductId());
        event.setUserId(order.getUserId());
        event.setQuantity(order.getQuantity());
        event.setPrice(order.getPrice());

        orderEventProducer.publishOrderCreated(event);

        return orderRepository.save(order);
    }

    public OrderInfo getOrder(long orderId){
        return orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not Found"));
    }

    public void cancelOrder(long orderId){
       OrderInfo order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Not found "));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }


}
