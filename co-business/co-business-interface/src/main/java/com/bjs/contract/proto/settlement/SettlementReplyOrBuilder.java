// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SettlementService.proto

package com.bjs.contract.proto.settlement;

public interface SettlementReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:settlement.SettlementReply)
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
   * <code>.settlement.SettlementPO settlementPO = 3;</code>
   */
  boolean hasSettlementPO();
  /**
   * <code>.settlement.SettlementPO settlementPO = 3;</code>
   */
  SettlementPO getSettlementPO();
  /**
   * <code>.settlement.SettlementPO settlementPO = 3;</code>
   */
  SettlementPOOrBuilder getSettlementPOOrBuilder();
}
