// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FundingRateService.proto

package com.bjs.contract.proto.fundingRate;

public interface FundingRateReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:fundingRate.FundingRateReply)
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
   * <code>.fundingRate.FundingRatePO fundingRatePO = 3;</code>
   */
  boolean hasFundingRatePO();
  /**
   * <code>.fundingRate.FundingRatePO fundingRatePO = 3;</code>
   */
  FundingRatePO getFundingRatePO();
  /**
   * <code>.fundingRate.FundingRatePO fundingRatePO = 3;</code>
   */
  FundingRatePOOrBuilder getFundingRatePOOrBuilder();
}
