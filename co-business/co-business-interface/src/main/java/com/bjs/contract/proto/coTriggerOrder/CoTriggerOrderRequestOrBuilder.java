// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoTriggerOrderService.proto

package com.bjs.contract.proto.coTriggerOrder;

public interface CoTriggerOrderRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coTriggerOrder.CoTriggerOrderRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   */
  long getId();

  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
   */
  boolean hasCoTriggerOrderPO();
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
   */
  CoTriggerOrderPO getCoTriggerOrderPO();
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
   */
  CoTriggerOrderPOOrBuilder getCoTriggerOrderPOOrBuilder();
}
