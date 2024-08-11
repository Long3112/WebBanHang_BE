package com.codegym.controller;

import com.codegym.model.Food;
import com.codegym.model.Shop;
import com.codegym.service.IFoodService;
import com.codegym.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/user/shops")
public class ShopUserController {
    @Autowired
    private IShopService shopService;

    @Autowired
    private IFoodService foodService;

    //Show các Shop lên màn hình của User
    @GetMapping("")
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shops = shopService.findAll();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    //Show các Food của shop nào đó theo Shop_ID
    @GetMapping("/{id}")
    public ResponseEntity<List<Food>> getFoodByShopId(@PathVariable Long id) {
        List<Food> foods = foodService.findByShopId(id);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    //Show các Food của shop nào đó theo Shop_ID
    @GetMapping("/shopDetail/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Shop shop = shopService.findById(id);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }




//    //Show chi tiết food trong shop theo food_ID
//    @PostMapping("/{id}")
//    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
//        Food food = foodService.findById(id);
//        return new ResponseEntity<>(food, HttpStatus.OK);
//    }


}
