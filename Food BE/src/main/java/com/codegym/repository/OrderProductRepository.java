package com.codegym.repository;

import com.codegym.model.OrderProduct;
import com.codegym.model.OrderProductPK;
import com.codegym.model.Orders;
import com.codegym.model.Shop;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {

       @Query(value = "select food_id from order_product where order_id = ?1",nativeQuery = true)
       List<Long> findOrderProductsByOrder(Long id);

       @Query(value = "select quantity from order_product where order_id = ?1",nativeQuery = true)
       List<Integer> findQuantityByOrder(Long id);


}
