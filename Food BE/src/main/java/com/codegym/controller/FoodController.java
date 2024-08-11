package com.codegym.controller;

import com.codegym.model.Food;
import com.codegym.service.IFoodService;
import com.codegym.service.impl.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin("*")
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private IFoodService foodService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        List<Food> list = foodService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> add(@RequestBody Food food) {
        foodService.save(food);
        String message = "Add success";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody Food food) {
        food.setId(id);
        foodService.save(food);
        String message = "Edit success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        foodService.remove(id);
        String message = "Delete success";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> search(@RequestParam("foodName") String name) {
        List<Food> list = foodService.findByNameContaining(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Food food = foodService.findById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);

    }

    @GetMapping("/shop/{id}")
    public ResponseEntity<List<Food>> getFoodByShopId(@PathVariable Long id) {
        List<Food> list = foodService.findByShopId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{shop_id}/search")
    public ResponseEntity<List<Food>> searchFoodInShop(@RequestParam("foodName") String name, @PathVariable Long shop_id) {
        List<Food> list = foodService.findByShopIdAndNameContaining(shop_id, name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}