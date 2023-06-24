package com.donatoordep.lesson01.services;

import com.donatoordep.lesson01.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double shipment(Order order){
        if(order.getBasic() < 100) return 20D;
        if(order.getBasic() >= 100 && order.getBasic() <= 200) return 12D;
        else return 0D;
    }
}
