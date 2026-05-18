package com.agh.order.controller;

import com.agh.order.entity.OrderInfo;
import com.agh.order.model.OrderResponse;
import com.agh.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Operation(summary = "Create order ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created Successfully"),
            @ApiResponse(responseCode = "400",description = "Few details are missing")
    })
    @PostMapping("/place")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderInfo orderRequest){

        OrderInfo placedOrder = orderService.placeOrder(orderRequest);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.mapToResponse(placedOrder);
        return ResponseEntity.ok(orderResponse);

    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get Order details using order Id")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable long orderId){
        OrderInfo order = orderService.getOrder(orderId);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.mapToResponse(order);
        return ResponseEntity.ok(orderResponse);
    }

    @Operation(summary = "Cancel Order by providing Order Id")
    @PutMapping("cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable long orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order cancelled successfully");
    }
}
