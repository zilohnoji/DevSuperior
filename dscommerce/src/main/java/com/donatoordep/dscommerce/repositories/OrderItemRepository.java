package com.donatoordep.dscommerce.repositories;

import com.donatoordep.dscommerce.entities.OrderItem;
import com.donatoordep.dscommerce.entities.OrderItemPK;
import com.donatoordep.dscommerce.entities.User;
import com.donatoordep.dscommerce.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
