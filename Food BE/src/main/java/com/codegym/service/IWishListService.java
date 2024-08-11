package com.codegym.service;

import com.codegym.model.WishList;

public interface IWishListService extends IGenerateService<WishList>{

    WishList findByUserId(Long id);

    void save(WishList wishList);

    boolean checkStatus(Long user,Long food);

}