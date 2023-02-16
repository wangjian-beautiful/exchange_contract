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
@MappedTypes(Order.Source.class)
public class OrderSourceHandler extends BaseTypeHandler<Order.Source> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order.Source type, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, type.tag);
  }

  @Override
  public Order.Source getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Order.Source.from(resultSet.getInt(s));
  }

  @Override
  public Order.Source getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Order.Source.from(resultSet.getInt(i));
  }

  @Override
  public Order.Source getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Order.Source.from(callableStatement.getInt(i));
  }
}
