package com.bijinsuo.common.identify;

/**
 * @author Winter
 */
public class UserIdentityConstant {
  private UserIdentityConstant() {
  }

  public static final String REQUEST_TOKEN_HEADER = "exchange-token";
  public static final String REDIS_TOKEN_PREFIX = "user_";
  public static final String REDIS_UID_KEY = "id";
  public static final String RPC_TOKEN_KEY = "user-token";
  public static final String RPC_UID_KEY = "user-id";
}
