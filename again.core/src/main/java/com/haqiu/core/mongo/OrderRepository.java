package com.haqiu.core.mongo;

import com.haqiu.core.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by H-QIU on 2016/11/24.
 */
public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByCustomer(String customer);

    List<Order> findByCustomerLike(String customer);

    List<Order> findByCustomerAndType(String customer, String type);

    List<Order> getByType(String type);

    @Query("{customer:'Chuck Wagon'}")
    List<Order> findChucksOrders();

}
