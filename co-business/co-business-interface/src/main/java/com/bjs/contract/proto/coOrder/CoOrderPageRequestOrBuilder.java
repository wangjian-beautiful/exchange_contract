// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

public interface CoOrderPageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coOrder.CoOrderPageRequest)
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
   * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
   */
  boolean hasCoOrderPO();
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
   */
  CoOrderPO getCoOrderPO();
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
   */
  CoOrderPOOrBuilder getCoOrderPOOrBuilder();
}