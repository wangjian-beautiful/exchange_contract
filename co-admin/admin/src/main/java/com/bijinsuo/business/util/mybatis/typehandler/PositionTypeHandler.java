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
@MappedTypes(Order.PositionType.class)
public class PositionTypeHandler extends BaseTypeHandler<Order.PositionType> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order.PositionType positionType, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, positionType.tag);
  }

  @Override
  public Order.PositionType getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Order.PositionType.from(resultSet.getInt(s));
  }

  @Override
  public Order.PositionType getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Order.PositionType.from(resultSet.getInt(i));
  }

  @Override
  public Order.PositionType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Order.PositionType.from(callableStatement.getInt(i));
  }
}
