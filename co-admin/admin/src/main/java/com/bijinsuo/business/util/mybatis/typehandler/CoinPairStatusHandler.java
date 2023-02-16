package com.bijinsuo.business.util.mybatis.typehandler;

import com.bijinsuo.business.entity.CoinPair;
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
@MappedTypes(CoinPair.Status.class)
public class CoinPairStatusHandler extends BaseTypeHandler<CoinPair.Status> {
  @Override
  public void setNonNullParameter(PreparedStatement preparedStatement, int i, CoinPair.Status status, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, status.tag);
  }

  @Override
  public CoinPair.Status getNullableResult(ResultSet resultSet, String s) throws SQLException {
    return CoinPair.Status.from(resultSet.getInt(s));
  }

  @Override
  public CoinPair.Status getNullableResult(ResultSet resultSet, int i) throws SQLException {
    return CoinPair.Status.from(resultSet.getInt(i));
  }

  @Override
  public CoinPair.Status getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
    return CoinPair.Status.from(callableStatement.getInt(i));
  }
}
