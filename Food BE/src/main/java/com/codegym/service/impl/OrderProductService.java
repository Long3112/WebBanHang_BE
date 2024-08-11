package com.codegym.service.impl;

import com.codegym.model.OrderProduct;
import com.codegym.model.OrderProductPK;
import com.codegym.model.Orders;
import com.codegym.repository.OrdersRepository;
import com.codegym.service.IOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderProductService implements IOrderProductService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public List<OrderProduct> findAll() {
        return null;
    }

    @Override
    public OrderProduct findById(Long id) {
        return null;
    }

    @Override
    public void save(OrderProduct orderProduct) {
       Orders orders  = new Orders();
       orderProduct.getOrderProductPK().setOrder(orders);
//       ordersRepository.save(orderProduct);
    }
    @Override
    public void remove(Long id) {

    }
}
