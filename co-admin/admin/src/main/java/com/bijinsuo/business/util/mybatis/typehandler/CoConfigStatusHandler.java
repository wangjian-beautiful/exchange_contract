package com.bijinsuo.business.util.mybatis.typehandler;

import com.bijinsuo.business.entity.CoConfig;
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
@MappedTypes(CoConfig.Status.class)
public class CoConfigStatusHandler extends BaseTypeHandler<CoConfig.Status> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, CoConfig.Status status, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, status.tag);
  }

  @Override
  public CoConfig.Status getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return CoConfig.Status.from(resultSet.getInt(s));
  }

  @Override
  public CoConfig.Status getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return CoConfig.Status.from(resultSet.getInt(i));
  }

  @Override
  public CoConfig.Status getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return CoConfig.Status.from(callableStatement.getInt(i));
  }
}
