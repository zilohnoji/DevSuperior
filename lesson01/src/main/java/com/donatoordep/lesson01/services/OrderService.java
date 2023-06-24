package com.donatoordep.lesson01.services;

import com.donatoordep.lesson01.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    public ShippingService service;

    public Double total(Order order) {
        return (order.getBasic() - ((order.getDiscount() / 100.0) * order.getBasic())) + service.shipment(order);
    }
}
