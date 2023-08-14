package com.donatoordep.dscommerce.repositories;

import com.donatoordep.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
