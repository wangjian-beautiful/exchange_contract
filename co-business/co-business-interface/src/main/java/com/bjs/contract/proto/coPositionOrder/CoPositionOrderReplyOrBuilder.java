// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderService.proto

package com.bjs.contract.proto.coPositionOrder;

public interface CoPositionOrderReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coPositionOrder.CoPositionOrderReply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool status = 1;</code>
   */
  boolean getStatus();

  /**
   * <code>string message = 2;</code>
   */
  String getMessage();
  /**
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

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
