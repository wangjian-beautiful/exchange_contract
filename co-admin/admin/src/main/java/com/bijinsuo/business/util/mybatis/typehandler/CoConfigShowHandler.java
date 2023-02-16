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
@MappedTypes(CoConfig.Show.class)
public class CoConfigShowHandler extends BaseTypeHandler<CoConfig.Show> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, CoConfig.Show show, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, show.tag);
  }

  @Override
  public CoConfig.Show getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return CoConfig.Show.from(resultSet.getInt(s));
  }

  @Override
  public CoConfig.Show getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return CoConfig.Show.from(resultSet.getInt(i));
  }

  @Override
  public CoConfig.Show getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return CoConfig.Show.from(callableStatement.getInt(i));
  }
}
