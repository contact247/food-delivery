package com.example.swiggato.repositories;

import com.example.swiggato.models.OrderEntity;
import com.example.swiggato.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {

    String servedMoreThanXOrdersHQL =  "select p.res from (select o.restaurant res, count(o.restaurant) c from OrderEntity o group by o.restaurant) p where p.c>:x";
    String servedMoreThanXOrders="select p.restaurant_id from (select count(o.restaurant_id) c, o.restaurant_id from order_entity o group by o.restaurant_id) p where p.c>:x";
    @Query(value = servedMoreThanXOrdersHQL)
    List<Restaurant> getRestservedMoreThanXOrders(int x);
}
