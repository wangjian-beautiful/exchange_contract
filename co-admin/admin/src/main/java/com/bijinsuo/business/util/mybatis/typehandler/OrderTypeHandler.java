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
@MappedTypes(Order.Type.class)
public class OrderTypeHandler extends BaseTypeHandler<Order.Type> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order.Type type, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i,type.tag);
  }

  @Override
  public Order.Type getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Order.Type.from(resultSet.getInt(s));
  }

  @Override
  public Order.Type getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Order.Type.from(resultSet.getInt(i));
  }

  @Override
  public Order.Type getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Order.Type.from(callableStatement.getInt(i));
  }
}
