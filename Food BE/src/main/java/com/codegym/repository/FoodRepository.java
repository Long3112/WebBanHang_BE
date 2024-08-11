package com.codegym.repository;

import com.codegym.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findByShopId(Long shopId);

    List<Food> findByNameContaining(String name);

    List<Food> findByShopIdAndNameContaining(Long shopId, String name);

    @Query(value = "select f from Food f where f.shop.id = :id")
    List<Food> getSameFoodsByShopId(@Param("id") Long id);

    @Query(value = "select fiq.food_id from (select op.food_id, sum(quantity) as quantity from order_product op join orders o on o.id = op.order_id where DAY(o.orders_date) = DAY(current_date()) AND MONTH(o.orders_date) = MONTH(current_date()) AND YEAR(o.orders_date) = YEAR(current_date()) group by op.food_id,o.orders_date) as fiq order by fiq.quantity desc", nativeQuery = true)
    List<Long> getBestsellerFoodToday();

    @Query(value = "select f.id from food  f inner join shop s on s.id = f.shop_id where s.address like %:address%", nativeQuery = true)
    List<Long> getFoodsByShopAddress(@Param("address") String address);

    @Query(value = "select f.id from food  f inner join shop s on s.id = f.shop_id inner join coupon c on c.id = s.id where f.shop_id in (select shop_id from coupon where type like %:coupon% group by shop_id) ", nativeQuery = true)
    List<Long> getFoodsByShopCoupon(@Param("coupon") String type);

    @Query(value = "select id from food where price >= :start and price <= :end", nativeQuery = true)
    List<Long> getFoodsByPriceRange( @Param("start") Double start, @Param("end") Double end);
}
