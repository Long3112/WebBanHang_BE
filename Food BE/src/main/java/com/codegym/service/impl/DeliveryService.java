package com.codegym.service.impl;
import com.codegym.model.Delivery;
import com.codegym.repository.DeliveryRepository;
import com.codegym.repository.FoodRepository;
import com.codegym.service.IDeliveryService;
import com.codegym.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeliveryService implements IDeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery findById(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Delivery delivery) {
             deliveryRepository.save(delivery);
    }

    @Override
    public void remove(Long id) {
            deliveryRepository.deleteById(id);
    }
}
