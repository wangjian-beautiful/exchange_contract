// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

public interface CoOrderListRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coOrder.CoOrderListRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .coOrder.CoOrderPO coOrderPO = 1;</code>
   */
  java.util.List<CoOrderPO>
      getCoOrderPOList();
  /**
   * <code>repeated .coOrder.CoOrderPO coOrderPO = 1;</code>
   */
  CoOrderPO getCoOrderPO(int index);
  /**
   * <code>repeated .coOrder.CoOrderPO coOrderPO = 1;</code>
   */
  int getCoOrderPOCount();
  /**
   * <code>repeated .coOrder.CoOrderPO coOrderPO = 1;</code>
   */
  java.util.List<? extends CoOrderPOOrBuilder>
      getCoOrderPOOrBuilderList();
  /**
   * <code>repeated .coOrder.CoOrderPO coOrderPO = 1;</code>
   */
  CoOrderPOOrBuilder getCoOrderPOOrBuilder(
      int index);
}