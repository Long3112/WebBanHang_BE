package com.codegym.service.impl;

import com.codegym.model.Orders;
import com.codegym.model.Shop;
import com.codegym.repository.ShopRepository;
import com.codegym.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class ShopService implements IShopService {
    @Autowired
    ShopRepository shopRepository;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id).get();
    }

    @Override
    public Shop findByUserId(Long id) {
        return shopRepository.findShopByUserId(id);
    }

    @Override
    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void remove(Long id) {

    }
}
