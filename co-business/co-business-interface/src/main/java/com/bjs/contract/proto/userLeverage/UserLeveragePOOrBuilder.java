// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserLeveragePO.proto

package com.bjs.contract.proto.userLeverage;

public interface UserLeveragePOOrBuilder extends
    // @@protoc_insertion_point(interface_extends:userLeverage.UserLeveragePO)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.google.protobuf.Int64Value id = 1;</code>
   */
  boolean hasId();
  /**
   * <code>.google.protobuf.Int64Value id = 1;</code>
   */
  com.google.protobuf.Int64Value getId();
  /**
   * <code>.google.protobuf.Int64Value id = 1;</code>
   */
  com.google.protobuf.Int64ValueOrBuilder getIdOrBuilder();

  /**
   * <code>.google.protobuf.Int64Value uid = 2;</code>
   */
  boolean hasUid();
  /**
   * <code>.google.protobuf.Int64Value uid = 2;</code>
   */
  com.google.protobuf.Int64Value getUid();
  /**
   * <code>.google.protobuf.Int64Value uid = 2;</code>
   */
  com.google.protobuf.Int64ValueOrBuilder getUidOrBuilder();

  /**
   * <code>string symbol = 3;</code>
   */
  String getSymbol();
  /**
   * <code>string symbol = 3;</code>
   */
  com.google.protobuf.ByteString
      getSymbolBytes();

  /**
   * <code>.google.protobuf.Int32Value leverage = 4;</code>
   */
  boolean hasLeverage();
  /**
   * <code>.google.protobuf.Int32Value leverage = 4;</code>
   */
  com.google.protobuf.Int32Value getLeverage();
  /**
   * <code>.google.protobuf.Int32Value leverage = 4;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getLeverageOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp ctime = 5;</code>
   */
  boolean hasCtime();
  /**
   * <code>.google.protobuf.Timestamp ctime = 5;</code>
   */
  com.google.protobuf.Timestamp getCtime();
  /**
   * <code>.google.protobuf.Timestamp ctime = 5;</code>
   */
  com.google.protobuf.TimestampOrBuilder getCtimeOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp mtime = 6;</code>
   */
  boolean hasMtime();
  /**
   * <code>.google.protobuf.Timestamp mtime = 6;</code>
   */
  com.google.protobuf.Timestamp getMtime();
  /**
   * <code>.google.protobuf.Timestamp mtime = 6;</code>
   */
  com.google.protobuf.TimestampOrBuilder getMtimeOrBuilder();
}
