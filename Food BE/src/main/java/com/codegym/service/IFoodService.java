package com.codegym.service;

import com.codegym.model.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface IFoodService extends IGenerateService<Food>{

    List<Food> findByShopId(Long shopId);
    List<Food> findByNameContaining(String name);
    List<Food> findByShopIdAndNameContaining(Long shopId, String name);

    HashMap<String,List<Food>> findSimilarFoodsByFoodId(Long id);

    List<Food> getBestSellerFoods();

    List<Food> FilterFood(String address,String coupon,Double start,Double end);
}
