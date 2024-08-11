package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.Food;
import com.codegym.model.Shop;

import java.util.HashMap;
import java.util.List;

public interface ICartService extends IGenerateService<Cart>{

      Cart findByUserId(Long id);

      void removeAllById(Long id);

      List<Food> getFoodsByCartAndShop(Long cart_id,Long shop_id);

      HashMap<String,List> getCartByUserId(Long id);


      List<Long> getShopList(Long id);
}
