package com.bijinsuo.business.util.mybatis.typehandler;

import com.bijinsuo.business.entity.Order;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Winter
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(Order.Status.class)
public class OrderStatusHandler extends BaseTypeHandler<Order.Status> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order.Status status, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i,status.tag);
  }

  @Override
  public Order.Status getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Order.Status.from(resultSet.getInt(s));
  }

  @Override
  public Order.Status getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Order.Status.from(resultSet.getInt(i));
  }

  @Override
  public Order.Status getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Order.Status.from(callableStatement.getInt(i));
  }
}
