// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoTriggerOrderService.proto

package com.bjs.contract.proto.coTriggerOrder;

public interface CoTriggerOrderReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coTriggerOrder.CoTriggerOrderReply)
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
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 3;</code>
   */
  boolean hasCoTriggerOrderPO();
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 3;</code>
   */
  CoTriggerOrderPO getCoTriggerOrderPO();
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 3;</code>
   */
  CoTriggerOrderPOOrBuilder getCoTriggerOrderPOOrBuilder();
}
