package com.codegym.controller;

import com.codegym.model.Coupon;
import com.codegym.model.Delivery;
import com.codegym.service.IDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/deliveries")
public class DeliveryController {
    @Autowired
    private IDeliveryService deliveryService;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Delivery> list = deliveryService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody Delivery delivery) {
        deliveryService.save(delivery);
        String message = "Add success";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody Delivery delivery) {
        delivery.setId(id);
        deliveryService.save(delivery);
        String message = "Edit success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        deliveryService.remove(id);
        String message = "Delete success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getFoodById(@PathVariable Long id) {
        Delivery delivery = deliveryService.findById(id);
        return new ResponseEntity<>(delivery, HttpStatus.OK);

    }
}
