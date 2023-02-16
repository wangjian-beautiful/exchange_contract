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
@MappedTypes(CoTriggerOrder.Status.class)
public class TriggerStatusHandler extends BaseTypeHandler<CoTriggerOrder.Status> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, CoTriggerOrder.Status operateSide, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, operateSide.name());
  }

  @Override
  public CoTriggerOrder.Status getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return CoTriggerOrder.Status.from(resultSet.getInt(s));
  }

  @Override
  public CoTriggerOrder.Status getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return CoTriggerOrder.Status.from(resultSet.getString(i));
  }

  @Override
  public CoTriggerOrder.Status getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return CoTriggerOrder.Status.from(callableStatement.getString(i));
  }
}
