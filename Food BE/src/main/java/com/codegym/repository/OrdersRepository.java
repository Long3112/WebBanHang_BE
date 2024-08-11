package com.codegym.repository;

import com.codegym.model.Orders;
import com.codegym.model.Role;
import com.codegym.model.Shop;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;

import java.sql.ResultSet;
import java.util.*;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(value = "select o.id from orders o join orders_shops os on os.orders_id = o.id join order_product op on op.order_id = o.id where os.shops_id = ?1", nativeQuery = true)
    Set<Long> getOrdersByShopsContainingAndStatus(Long id);

    @Query(value = "select orders_id from orders_shops where shops_id = ?1", nativeQuery = true)
    List<Long> getOrdersByShopId(Long id);

    @Query(value = "select o from Orders o where (o.user.name LIKE %:str%  or o.user.phoneNumber like %:str%) and (o.delivery.id in :ships) and o.date between :start and :time_end")
    List<Orders> filterOrders(@Param("str") String str, @Param("ships") Collection<Long> ships, @Param("start") Date start, @Param("time_end") Date end);

    List<Orders> getOrdersByUserId(Long id);

    @Query(value = "select sum(total) from orders where user_id = ?1 and month(orders_date) = month(current_date()) and year(orders_date) = year(current_date())", nativeQuery = true)
    Long getTotalPriceByUserId(Long id);

    @Query(value = "select count(o.id) from orders o join coupons_orders co on co.orders_id = o.id where o.user_id = ?1 and month(orders_date) = month(current_date()) and year(orders_date) = year(current_date())", nativeQuery = true)
    Long getCouponQuantityUsedByUserId(Long id);

    @Query(value = "select count(id) from orders where user_id = ? and month(orders_date) = month(current_date()) and year(orders_date) = year(current_date())", nativeQuery = true)
    Long getOrdersQuantityByUserId(Long id);


    @Query(value = "select o.id from orders_shops os inner join orders o on o.id = os.orders_id where o.status = 'DONE' and os.shops_id = :id and month(o.orders_date) = month(current_date()) and year(o.orders_date) = year(current_date())", nativeQuery = true)
    List<Long> getOrdersInMonth(@Param("id") Long id);

    @Query(value = "select o.id from orders_shops os inner join orders o on o.id = os.orders_id where o.status = 'DONE' and os.shops_id = :id and weekofyear(o.orders_date) = weekofyear(current_date()) and year(o.orders_date) = year(current_date())",nativeQuery = true)
    List<Long> getOrdersOnWeek(@Param("id") Long id);


    @Query(value = "select o.id from orders_shops os inner join orders o on o.id = os.orders_id where o.status = 'DONE' and os.shops_id = :id and quarter(o.orders_date) = quarter(current_date()) and year(o.orders_date) = year(current_date())",nativeQuery = true)
    List<Long> getOrdersInPeriod(@Param("id") Long id);

    @Query(value = "select o.id from orders_shops os inner join orders o on o.id = os.orders_id where o.status = 'DONE' and os.shops_id = :id and month(o.orders_date) = month(date_sub(current_date(),interval 1 month)) and year(o.orders_date) = year(current_date());",nativeQuery = true)
    List<Long> getOrdersLastMonth(@Param("id") Long id);

    @Query(value = "select fiq.food_id from (select op.food_id, sum(quantity) as quantity from order_product op join orders o on o.id = op.order_id join orders_shops os on os.orders_id = o.id where dayofyear(o.orders_date) = dayofyear(current_time) and year(orders_date) = year(current_time) and os.shops_id = :id group by op.food_id,o.orders_date) as fiq order by fiq.quantity desc;",nativeQuery = true)
    List<Long> getBestSellerTodayByShopId(@Param("id") Long id);

    @Query(value = "select fiq.quantity from (select op.food_id, sum(quantity) as quantity from order_product op join orders o on o.id = op.order_id join orders_shops os on os.orders_id = o.id where dayofyear(o.orders_date) = dayofyear(current_time) and year(orders_date) = year(current_time) and os.shops_id = :id group by op.food_id,o.orders_date) as fiq order by fiq.quantity desc;",nativeQuery = true)
    List<Integer> getBestSellerQuantityTodayByShopId(@Param("id") Long id);

    @Query(value = "select o.id from orders o inner join orders_shops os on os.orders_id = o.id where o.user_id = ?1 and os.shops_id = ?2",nativeQuery = true)
    List<Long> getOrdersByUserIdAndShopId(Long user,Long shop);

    @Query(value = "select o.user_id from orders o inner join orders_shops os on os.orders_id = o.id where os.shops_id = ?1 group by o.user_id",nativeQuery = true)
    List<Long> getUserByShopId(Long id);
}