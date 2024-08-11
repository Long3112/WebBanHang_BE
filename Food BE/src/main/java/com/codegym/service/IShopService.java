package com.codegym.service;

import com.codegym.model.Orders;
import com.codegym.model.Shop;

import java.util.List;

public interface IShopService extends IGenerateService<Shop>{
    Shop findByUserId(Long id);

}
