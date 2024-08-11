package com.codegym.controller;

import com.codegym.model.Food;
import com.codegym.model.Shop;
import com.codegym.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/user/foods")
public class FoodUserController {
    @Autowired
    private IFoodService foodService;

    @GetMapping("")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foodList = foodService.findAll();
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> search(@RequestParam("foodName") String name) {
        List<Food> list = foodService.findByNameContaining(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Food food = foodService.findById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/same/{id}")
    public ResponseEntity<HashMap<String,List<Food>>> getSimilarFoodsByFoodId(@PathVariable Long id){
        HashMap<String,List<Food>> hashMap = foodService.findSimilarFoodsByFoodId(id);
        return new ResponseEntity<>(hashMap,HttpStatus.OK);
    }

    @GetMapping("/bestseller")
    public ResponseEntity<List<Food>> getBestSellerFood(){
           List<Food> foods = foodService.getBestSellerFoods();
           return new ResponseEntity<>(foods,HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<HashMap<String,List>> getFoodsFilter(@RequestParam(name = "address",required = false)String address, @RequestParam(name = "start",required = false) Double start,@RequestParam(name = "end",required = false)Double end,@RequestParam(name="coupon",required = false) String coupons){
           HashMap<String,List> back = new HashMap<>();
           List<Food> foods = foodService.FilterFood(address,coupons,start,end);
           back.put("foods",foods);
           return new ResponseEntity<>(back,HttpStatus.OK);
    }

}
