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
@MappedTypes(Order.OperateType.class)
public class OperateTypeHandler extends BaseTypeHandler<Order.OperateType> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order.OperateType operateType, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, operateType.name());
  }

  @Override
  public Order.OperateType getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Order.OperateType.valueOf(resultSet.getString(s));
  }

  @Override
  public Order.OperateType getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Order.OperateType.valueOf(resultSet.getString(i));
  }

  @Override
  public Order.OperateType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Order.OperateType.valueOf(callableStatement.getString(i));
  }
}
