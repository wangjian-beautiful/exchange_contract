// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserLeverageService.proto

package com.bjs.contract.proto.userLeverage;

public interface UserLeveragePageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:userLeverage.UserLeveragePageRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 page = 1;</code>
   */
  int getPage();

  /**
   * <code>int32 size = 2;</code>
   */
  int getSize();

  /**
   * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
   */
  boolean hasUserLeveragePO();
  /**
   * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
   */
  UserLeveragePO getUserLeveragePO();
  /**
   * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
   */
  UserLeveragePOOrBuilder getUserLeveragePOOrBuilder();
}
