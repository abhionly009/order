package com.agh.order.service;

import com.agh.order.entity.OrderInfo;
import com.agh.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEventProducer orderEventProducer;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrder()
    {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setProductName("Laptop");

        when(orderRepository.save(any(OrderInfo.class)))
                .thenReturn(orderInfo);
       OrderInfo result = orderService.placeOrder(orderInfo);


        assertNotNull(result);

        assertEquals("Laptop", result.getProductName());

        verify(orderRepository, times(1)).save(orderInfo);

        verify(orderEventProducer).publishOrderCreated(any());

    }

    @Test
    void testGetOrder() throws Exception{



    }

}
