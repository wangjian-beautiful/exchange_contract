package com.bijinsuo.common.identify;

import lombok.Data;

/**
 * @author Winter
 */
public class UserContextHolder {
  public static final ThreadLocal<User> user = new ThreadLocal<>();

  private UserContextHolder() {

  }

  public static User get(){
    return user.get();
  }

  public static void clear(){
    user.remove();
  }

  public static void set(User u){
    user.set(u);
  }

  @Data
  public static class User {
    private Long id;
    private String token;
  }
}
