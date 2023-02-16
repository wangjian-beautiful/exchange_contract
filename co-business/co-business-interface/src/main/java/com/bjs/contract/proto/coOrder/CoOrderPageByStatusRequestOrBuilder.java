// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

public interface CoOrderPageByStatusRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coOrder.CoOrderPageByStatusRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 page = 1;</code>
   */
  int getPage();

  /**
   * <code>int32 limit = 2;</code>
   */
  int getLimit();

  /**
   * <code>string beginTime = 3;</code>
   */
  String getBeginTime();
  /**
   * <code>string beginTime = 3;</code>
   */
  com.google.protobuf.ByteString
      getBeginTimeBytes();

  /**
   * <code>string endTime = 4;</code>
   */
  String getEndTime();
  /**
   * <code>string endTime = 4;</code>
   */
  com.google.protobuf.ByteString
      getEndTimeBytes();

  /**
   * <code>repeated int32 status = 5;</code>
   */
  java.util.List<Integer> getStatusList();
  /**
   * <code>repeated int32 status = 5;</code>
   */
  int getStatusCount();
  /**
   * <code>repeated int32 status = 5;</code>
   */
  int getStatus(int index);

  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 6;</code>
   */
  boolean hasCoOrderPO();
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 6;</code>
   */
  CoOrderPO getCoOrderPO();
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 6;</code>
   */
  CoOrderPOOrBuilder getCoOrderPOOrBuilder();
}
