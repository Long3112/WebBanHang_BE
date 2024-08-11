package com.codegym.controller;

import com.codegym.model.Food;
import com.codegym.model.WishList;
import com.codegym.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private IWishListService wishListService;

    @GetMapping("/dup")
    public ResponseEntity<Boolean> checkFoodInWishlist(@Param("user") Long user, @Param("food")Long food){
           boolean check = wishListService.checkStatus(user,food);
           return new ResponseEntity<>(check,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WishList> getWishList(@PathVariable Long id) {
        WishList wishList = wishListService.findByUserId(id);
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addToWishList(@PathVariable Long id, @RequestBody Food food) {
        WishList wishList = wishListService.findByUserId(id);
        wishList.getFood().add(food);
        wishListService.save(wishList);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteFromWishList(@PathVariable Long id, @RequestBody Food food) {
        WishList wishList = wishListService.findByUserId(id);
        wishList.getFood().remove(food);
        wishListService.save(wishList);
        return new ResponseEntity<>("DELETING DONE", HttpStatus.OK);
    }

    @PostMapping("/deleteAll")
    public ResponseEntity<?> deleteAllFromWishList(@RequestBody Long id) {
        WishList wishList = wishListService.findByUserId(id);
        wishList.getFood().clear();
        wishListService.save(wishList);
        return new ResponseEntity<>("DELETING ALL", HttpStatus.OK);
    }
}