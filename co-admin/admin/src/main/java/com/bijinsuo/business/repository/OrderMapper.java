package com.bijinsuo.business.repository;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Winter
 */
@Repository
public interface OrderMapper {
  List<Order> selectOrderList(Order Order);
}
