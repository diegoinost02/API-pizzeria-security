package com.pizza.web.controller;

import com.pizza.persistence.entity.OrderEntiny;
import com.pizza.persistence.projection.OrderSummary;
import com.pizza.service.OrderService;
import com.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntiny>> getAll(){
        try {
            return ResponseEntity.ok(this.orderService.getAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntiny>> getTodayOrders(){
        try {
            return ResponseEntity.ok(this.orderService.getTodayOrders());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntiny>> getOutsideOrders(){
        try {
            return ResponseEntity.ok(this.orderService.getOutsideOrders());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/onsite")
    public ResponseEntity<List<OrderEntiny>> getOnSiteOrders(){
        try {
            return ResponseEntity.ok(this.orderService.getOnSiteOrders());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntiny>> getCustomerOrders(@PathVariable String id){
        try {
            return ResponseEntity.ok(this.orderService.getCustomerOrders(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/summary/{id}")
    public ResponseEntity<OrderSummary> getSummary(@PathVariable int id){
        try {
            return ResponseEntity.ok(this.orderService.getSummary(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto){
        try {
            return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}