// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

public interface CoOrderRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coOrder.CoOrderRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   */
  long getId();

  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
   */
  boolean hasCoOrderPO();
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
   */
  CoOrderPO getCoOrderPO();
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
   */
  CoOrderPOOrBuilder getCoOrderPOOrBuilder();
}