package com.bijinsuo.business.util.mybatis.typehandler;

import com.bijinsuo.business.entity.Position;
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
@MappedTypes(Position.Status.class)
public class PositionStatusHandler extends BaseTypeHandler<Position.Status> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, Position.Status operateSide, JdbcType jdbcType) throws SQLException {
    preparedStatement.setString(i, operateSide.name());
  }

  @Override
  public Position.Status getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return Position.Status.from(resultSet.getString(s));
  }

  @Override
  public Position.Status getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return Position.Status.from(resultSet.getString(i));
  }

  @Override
  public Position.Status getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return Position.Status.from(callableStatement.getString(i));
  }
}
