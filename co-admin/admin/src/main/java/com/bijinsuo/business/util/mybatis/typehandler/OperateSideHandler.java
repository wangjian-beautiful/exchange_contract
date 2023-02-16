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
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Order.OperateSide.class)
public class OperateSideHandler extends BaseTypeHandler<Order.OperateSide> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order.OperateSide operateSide, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, operateSide.name());
  }

  @Override
  public Order.OperateSide getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Order.OperateSide.valueOf(resultSet.getString(s));
  }

  @Override
  public Order.OperateSide getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Order.OperateSide.valueOf(resultSet.getString(i));
  }

  @Override
  public Order.OperateSide getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Order.OperateSide.valueOf(callableStatement.getString(i));
  }
}
