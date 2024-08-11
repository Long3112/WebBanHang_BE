package com.codegym.service.impl;

import com.codegym.model.City;
import com.codegym.model.District;
import com.codegym.model.Food;
import com.codegym.repository.FoodRepository;
import com.codegym.service.IFoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.Key;
import java.util.*;

@Service
public class FoodService implements IFoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food findById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void remove(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> findByShopId(Long shopId) {
        return foodRepository.findByShopId(shopId);
    }

    @Override
    public List<Food> findByNameContaining(String searchName) {
        return foodRepository.findByNameContaining(searchName);
    }

    @Override
    public HashMap<String,List<Food>> findSimilarFoodsByFoodId(Long id) {
        List<Food> shop_foods = foodRepository.getSameFoodsByShopId(id);
        List<Long> bestseller_ids = foodRepository.getBestsellerFoodToday();
        List<Food> bestseller = new LinkedList<>();
        int index = 10;
        if(bestseller_ids.size() < 10) index = bestseller_ids.size();
        for (int i = 0 ; i < index;i++){
            bestseller.add(foodRepository.findById(bestseller_ids.get(i)).get());
        }
        HashMap<String,List<Food>> back = new HashMap<>();
        back.put("shop",shop_foods);
        back.put("bestseller",bestseller);
        return back;
    }

    @Override
    public List<Food> getBestSellerFoods() {
        List<Long> bestseller_ids = foodRepository.getBestsellerFoodToday();
        List<Food> bestseller = new LinkedList<>();
        for (int i = 0 ; i < bestseller_ids.size();i++){
            bestseller.add(foodRepository.findById(bestseller_ids.get(i)).get());
        }
        return bestseller;
    }

    @Override
    public List<Food> findByShopIdAndNameContaining(Long shopId, String name) {
        return foodRepository.findByShopIdAndNameContaining(shopId, name);
    }

    @Override
    public List<Food> FilterFood(String address, String coupon, Double start, Double end) {


        ObjectMapper mapper = new ObjectMapper();
        String address_name = new String();
        String[] arr = new String[2];
        if(address != null)
//            arr= address.split("-");
//        try {
//            URL url = new URL("https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json");
//            List<Object> data = mapper.readValue(url, List.class);
//            for (Object o: data){
//                   String str = mapper.writeValueAsString(o);
//                   City city = mapper.readValue(str.toLowerCase(),City.class);
//                   if(city.getId().equals(arr[0])){
//                           for (int i = 0 ; i < city.getDistricts().size();i++){
//                               if(city.getDistricts().get(i).getId().equals(arr[1]))
//                                   address_name = city.getDistricts().get(i).getName();
//                           }
//                   }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        address_name = address_name.replaceAll("(huyện|quận|thị xã)\\s+", "");
        int number = 0 ;

        List<Long> addresses = new LinkedList<>();
        if (address != null){
             number++;
             addresses = foodRepository.getFoodsByShopAddress(address_name);
        }

        List<Long> priceRange = new LinkedList<>();
        if (start != null && end != null){
            number++;
            priceRange = foodRepository.getFoodsByPriceRange(start,end);
        }

        List<Long> coupons = new LinkedList<>();
        if (coupon != null) {
            number++;
            coupons = foodRepository.getFoodsByShopCoupon(coupon);
        }

        HashMap<Long,Integer> check = new HashMap<>();
        for (int i = 0 ; i < priceRange.size();i++){
                check.put(priceRange.get(i),1);
        }
        for (int i = 0 ; i < addresses.size();i++){
            if(check.get(addresses.get(i)) == null)
                check.put(addresses.get(i),1);
            else{
                int total = check.get(addresses.get(i)).intValue();
                check.remove(addresses.get(i));
                check.put(addresses.get(i),total+1);
            }
        }
        for (int i = 0 ; i < coupons.size();i++){
            if(check.get(coupons.get(i)) == null)
                check.put(addresses.get(i),1);
            else{
                int total = check.get(coupons.get(i)).intValue();
                check.remove(coupons.get(i));
                check.put(coupons.get(i),total+1);
            }
        }
        if(number == 0){
             List<Food> foods = foodRepository.findAll();
             return foods;
        }
        List<Food> foods = new LinkedList<>();
        Set<Long> keySet = check.keySet();
        for (Long key: keySet){
             if(check.get(key).intValue() == number){
                 Food food = foodRepository.findById(key).get();
                 foods.add(food);
             }
        }
        return foods;
    }
}
