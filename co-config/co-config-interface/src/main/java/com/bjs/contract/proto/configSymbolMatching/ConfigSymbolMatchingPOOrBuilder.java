// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConfigSymbolMatchingPO.proto

package com.bjs.contract.proto.configSymbolMatching;

public interface ConfigSymbolMatchingPOOrBuilder extends
    // @@protoc_insertion_point(interface_extends:configSymbolMatching.ConfigSymbolMatchingPO)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.google.protobuf.Int32Value id = 1;</code>
   */
  boolean hasId();
  /**
   * <code>.google.protobuf.Int32Value id = 1;</code>
   */
  com.google.protobuf.Int32Value getId();
  /**
   * <code>.google.protobuf.Int32Value id = 1;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getIdOrBuilder();

  /**
   * <code>string base = 2;</code>
   */
  String getBase();
  /**
   * <code>string base = 2;</code>
   */
  com.google.protobuf.ByteString
      getBaseBytes();

  /**
   * <code>string quote = 3;</code>
   */
  String getQuote();
  /**
   * <code>string quote = 3;</code>
   */
  com.google.protobuf.ByteString
      getQuoteBytes();

  /**
   * <code>.google.protobuf.Int32Value isOpen = 4;</code>
   */
  boolean hasIsOpen();
  /**
   * <code>.google.protobuf.Int32Value isOpen = 4;</code>
   */
  com.google.protobuf.Int32Value getIsOpen();
  /**
   * <code>.google.protobuf.Int32Value isOpen = 4;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getIsOpenOrBuilder();

  /**
   * <code>string server = 5;</code>
   */
  String getServer();
  /**
   * <code>string server = 5;</code>
   */
  com.google.protobuf.ByteString
      getServerBytes();

  /**
   * <code>.google.protobuf.Timestamp ctime = 6;</code>
   */
  boolean hasCtime();
  /**
   * <code>.google.protobuf.Timestamp ctime = 6;</code>
   */
  com.google.protobuf.Timestamp getCtime();
  /**
   * <code>.google.protobuf.Timestamp ctime = 6;</code>
   */
  com.google.protobuf.TimestampOrBuilder getCtimeOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp mtime = 7;</code>
   */
  boolean hasMtime();
  /**
   * <code>.google.protobuf.Timestamp mtime = 7;</code>
   */
  com.google.protobuf.Timestamp getMtime();
  /**
   * <code>.google.protobuf.Timestamp mtime = 7;</code>
   */
  com.google.protobuf.TimestampOrBuilder getMtimeOrBuilder();
}
