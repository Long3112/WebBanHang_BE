package com.codegym.repository;

import com.codegym.model.Coupon;
import com.codegym.model.Role;
import com.codegym.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

     List<Coupon> getCouponsByShopIdIsAndStatus(Long id,boolean status);
     List<Coupon> getCouponsByShopIdIs(Long id);

     @Query(value = "select c from Coupon c where c.shop.id = :shop and c.status = true and c.quantity > 0 and c NOT IN :ids")
     List<Coupon> getEnableStatusCoupon(@Param("shop") Long shop,@Param("ids") Collection<Coupon> coupons);

     @Query(value = "select o.coupons from Orders o join o.user u where u.id = :id")
     Collection<Coupon> getUsedCoupon(@Param("id") Long id);

     List<Coupon> getCouponsByShopId(Long id);
}
