// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

public interface CoHistoryOrderRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coOrder.CoHistoryOrderRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   */
  long getId();

  /**
   * <code>string contractId = 2;</code>
   */
  String getContractId();
  /**
   * <code>string contractId = 2;</code>
   */
  com.google.protobuf.ByteString
      getContractIdBytes();

  /**
   * <code>int32 limit = 3;</code>
   */
  int getLimit();

  /**
   * <code>int32 page = 4;</code>
   */
  int getPage();

  /**
   * <code>string type = 5;</code>
   */
  String getType();
  /**
   * <code>string type = 5;</code>
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>string beginTime = 6;</code>
   */
  String getBeginTime();
  /**
   * <code>string beginTime = 6;</code>
   */
  com.google.protobuf.ByteString
      getBeginTimeBytes();

  /**
   * <code>string endTime = 7;</code>
   */
  String getEndTime();
  /**
   * <code>string endTime = 7;</code>
   */
  com.google.protobuf.ByteString
      getEndTimeBytes();
}
