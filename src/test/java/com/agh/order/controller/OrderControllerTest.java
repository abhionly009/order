package com.agh.order.controller;

import com.agh.order.entity.OrderInfo;
import com.agh.order.model.OrderStatus;
import com.agh.order.service.OrderService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;


    @Test
    void testCreateOrder() throws Exception{

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1L);
        orderInfo.setProductName("Laptop");
        orderInfo.setProductId(101L);
        orderInfo.setUserId(10L);
        orderInfo.setQuantity(2);
        orderInfo.setPrice(50000);
        orderInfo.setDiscount(10);
        orderInfo.setStatus(OrderStatus.CREATED);


        when(orderService.placeOrder(any(OrderInfo.class)))
                .thenReturn(orderInfo);

        String requestJson = """
        {
          "productName": "Laptop",
          "productId": 101,
          "userId": 10,
          "quantity": 2,
          "price": 50000,
          "discount": 10,
          "status": "CREATED"
        }
    """;

        mockMvc.perform(post("/orders/place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                // 🔥 IMPORTANT (response validation)
                .andExpect(jsonPath("$.productName").value("Laptop"))
                .andExpect(jsonPath("$.productId").value(101))
                .andExpect(jsonPath("$.quantity").value(2));

    }

    @Test
    void testGetOrder() throws Exception {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1L);
        orderInfo.setProductName("Laptop");

        when(orderService.getOrder(1L)).thenReturn(orderInfo);

        mockMvc.perform(get("/orders/order/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Laptop"));
    }

    // ✅ 3. Cancel Order Test
    @Test
    void testCancelOrder() throws Exception {

        doNothing().when(orderService).cancelOrder(1L);

        mockMvc.perform(put("/orders/cancel/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order cancelled successfully"));
    }
}
