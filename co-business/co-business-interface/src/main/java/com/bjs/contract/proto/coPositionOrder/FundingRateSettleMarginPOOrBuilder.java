// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderService.proto

package com.bjs.contract.proto.coPositionOrder;

public interface FundingRateSettleMarginPOOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coPositionOrder.FundingRateSettleMarginPO)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string symbol = 1;</code>
   */
  String getSymbol();
  /**
   * <code>string symbol = 1;</code>
   */
  com.google.protobuf.ByteString
      getSymbolBytes();

  /**
   * <code>string side = 2;</code>
   */
  String getSide();
  /**
   * <code>string side = 2;</code>
   */
  com.google.protobuf.ByteString
      getSideBytes();

  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  java.util.List<FundingRateSettleMarginItemPO>
      getFundingRateSettleMarginItemPOList();
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  FundingRateSettleMarginItemPO getFundingRateSettleMarginItemPO(int index);
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  int getFundingRateSettleMarginItemPOCount();
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  java.util.List<? extends FundingRateSettleMarginItemPOOrBuilder>
      getFundingRateSettleMarginItemPOOrBuilderList();
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  FundingRateSettleMarginItemPOOrBuilder getFundingRateSettleMarginItemPOOrBuilder(
      int index);
}
