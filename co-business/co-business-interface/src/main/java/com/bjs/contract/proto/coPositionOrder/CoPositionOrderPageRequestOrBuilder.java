// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderService.proto

package com.bjs.contract.proto.coPositionOrder;

public interface CoPositionOrderPageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coPositionOrder.CoPositionOrderPageRequest)
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
   * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 3;</code>
   */
  boolean hasCoPositionOrderPO();
  /**
   * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 3;</code>
   */
  CoPositionOrderPO getCoPositionOrderPO();
  /**
   * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 3;</code>
   */
  CoPositionOrderPOOrBuilder getCoPositionOrderPOOrBuilder();
}
