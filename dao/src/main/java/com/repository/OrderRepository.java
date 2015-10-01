package com.repository;

import com.models.Order;
import com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kzub on 9/20/2015.
 */
@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
}
