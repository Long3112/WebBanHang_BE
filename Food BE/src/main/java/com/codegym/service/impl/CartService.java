package com.codegym.service.impl;

import com.codegym.model.Cart;
import com.codegym.model.Food;
import com.codegym.model.Shop;
import com.codegym.model.User;
import com.codegym.repository.CartRepository;
import com.codegym.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public HashMap<String, List> getCartByUserId(Long id) {
        Optional<Cart> optional = cartRepository.findCartByUserId(id);
        if(optional.isPresent()){
            Cart cart = optional.get();
            HashMap<String,List> hashMap = new HashMap<>();
            Set<Food> foods = cart.getFood();
            foods.stream().sorted(new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return (int) (o1.getShop().getId() - o2.getShop().getId());
                }
            });
            List<Food> temp = new LinkedList<>();
            int index = 0;
            for (Food food: foods){
                    if(temp.size() == 0) {
                        temp.add(food);
                    }
                    else if(temp.get(temp.size()-1).getShop().getId() != food.getShop().getId() && temp.size() > 0){
                        List<Food> push = new ArrayList<>();
                        for (int  i =0 ; i < temp.size();i++){
                            push.add(temp.get(i));
                        }
                        hashMap.put(temp.get(temp.size()-1).getShop().getId().toString(),push);
                        temp.clear();
                        temp.add(food);
                    } else {
                        temp.add(food);
                    }
            }
            if (temp.size() > 0)
                   hashMap.put(temp.get(temp.size()-1).getShop().getId().toString(),temp);
            hashMap.put("list_shop",cartRepository.getShopByUserId(id));
            return hashMap;
        }
        return null;
    }

    @Override
    public List<Long> getShopList(Long id) {
        return cartRepository.getShopByUserId(id);
    }

    public Cart findByUserId(Long id){
        Optional<Cart> cart = cartRepository.findCartByUserId(id);
        if (cart.isEmpty()){
            Cart newCart = new Cart();
            User user = new User();
            user.setId(id);
            Set<Food> foods = new HashSet<>();
            newCart.setUser(user);
            newCart.setFood(foods);
            return newCart;
        }else{
            Cart rs = cart.get();
            Set<Food> foods = rs.getFood();
            foods.stream().sorted(new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return (int) (o1.getShop().getId() - o2.getShop().getId());
                }
            });
            rs.setFood(foods);
            return rs;
        }
    }

    @Override
    public List<Food> getFoodsByCartAndShop(Long user_id,Long shop_id) {
        Optional<Cart> cart = cartRepository.findCartByUserId(user_id);
        Set<Food> foods = new HashSet<>();
        if(cart.isPresent())
        foods = cart.get().getFood();
        List<Food> food_back = new LinkedList<>();
        for(Food food: foods){
            if(food.getShop().getId().equals(shop_id))
                food_back.add(food);
        }
        System.out.println(food_back);
        return  food_back;
    }

    @Override
    public void save(Cart cart) {
           cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void removeAllById(Long id) {
         Cart cart = cartRepository.findCartByUserId(id).get();
         cart.getFood().clear();
         cartRepository.save(cart);
    }
}
