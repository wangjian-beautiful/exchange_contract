// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

public interface UserTransactionRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:transaction.UserTransactionRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string beginTime = 1;</code>
   */
  String getBeginTime();
  /**
   * <code>string beginTime = 1;</code>
   */
  com.google.protobuf.ByteString
      getBeginTimeBytes();

  /**
   * <code>string endTime = 2;</code>
   */
  String getEndTime();
  /**
   * <code>string endTime = 2;</code>
   */
  com.google.protobuf.ByteString
      getEndTimeBytes();

  /**
   * <code>int32 page = 3;</code>
   */
  int getPage();

  /**
   * <code>int32 limit = 4;</code>
   */
  int getLimit();

  /**
   * <code>string symbol = 5;</code>
   */
  String getSymbol();
  /**
   * <code>string symbol = 5;</code>
   */
  com.google.protobuf.ByteString
      getSymbolBytes();

  /**
   * <code>string type = 6;</code>
   */
  String getType();
  /**
   * <code>string type = 6;</code>
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>int64 uid = 7;</code>
   */
  long getUid();
}
