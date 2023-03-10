// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

public interface CoOrderCancelRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coOrder.CoOrderCancelRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 uid = 1;</code>
   */
  long getUid();

  /**
   * <code>string symbol = 2;</code>
   */
  String getSymbol();
  /**
   * <code>string symbol = 2;</code>
   */
  com.google.protobuf.ByteString
      getSymbolBytes();

  /**
   * <code>string side = 3;</code>
   */
  String getSide();
  /**
   * <code>string side = 3;</code>
   */
  com.google.protobuf.ByteString
      getSideBytes();

  /**
   * <pre>
   *超时时间，单位为秒，如果不传入该参数，默认为180秒
   * </pre>
   *
   * <code>int32 timeout = 4;</code>
   */
  int getTimeout();
}
