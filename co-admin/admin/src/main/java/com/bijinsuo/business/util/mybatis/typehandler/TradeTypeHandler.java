package com.bijinsuo.business.util.mybatis.typehandler;

import com.bijinsuo.business.entity.CoTriggerOrder;
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
@MappedTypes(CoTriggerOrder.TradeType.class)
public class TradeTypeHandler extends BaseTypeHandler<CoTriggerOrder.TradeType> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, CoTriggerOrder.TradeType operateSide, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, operateSide.name());
  }

  @Override
  public CoTriggerOrder.TradeType getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return CoTriggerOrder.TradeType.from(resultSet.getString(s));
  }

  @Override
  public CoTriggerOrder.TradeType getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return CoTriggerOrder.TradeType.from(resultSet.getString(i));
  }

  @Override
  public CoTriggerOrder.TradeType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return CoTriggerOrder.TradeType.from(callableStatement.getString(i));
  }
}
