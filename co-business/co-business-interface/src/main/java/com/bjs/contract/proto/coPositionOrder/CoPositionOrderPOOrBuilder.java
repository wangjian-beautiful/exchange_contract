// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderPO.proto

package com.bjs.contract.proto.coPositionOrder;

public interface CoPositionOrderPOOrBuilder extends
    // @@protoc_insertion_point(interface_extends:coPositionOrder.CoPositionOrderPO)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.google.protobuf.Int64Value id = 1;</code>
   */
  boolean hasId();
  /**
   * <code>.google.protobuf.Int64Value id = 1;</code>
   */
  com.google.protobuf.Int64Value getId();
  /**
   * <code>.google.protobuf.Int64Value id = 1;</code>
   */
  com.google.protobuf.Int64ValueOrBuilder getIdOrBuilder();

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
   * <code>.google.protobuf.Int64Value uid = 3;</code>
   */
  boolean hasUid();
  /**
   * <code>.google.protobuf.Int64Value uid = 3;</code>
   */
  com.google.protobuf.Int64Value getUid();
  /**
   * <code>.google.protobuf.Int64Value uid = 3;</code>
   */
  com.google.protobuf.Int64ValueOrBuilder getUidOrBuilder();

  /**
   * <code>.google.protobuf.Int32Value positionType = 4;</code>
   */
  boolean hasPositionType();
  /**
   * <code>.google.protobuf.Int32Value positionType = 4;</code>
   */
  com.google.protobuf.Int32Value getPositionType();
  /**
   * <code>.google.protobuf.Int32Value positionType = 4;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getPositionTypeOrBuilder();

  /**
   * <code>string positionSide = 5;</code>
   */
  String getPositionSide();
  /**
   * <code>string positionSide = 5;</code>
   */
  com.google.protobuf.ByteString
      getPositionSideBytes();

  /**
   * <code>.google.protobuf.Int32Value leverageLevel = 6;</code>
   */
  boolean hasLeverageLevel();
  /**
   * <code>.google.protobuf.Int32Value leverageLevel = 6;</code>
   */
  com.google.protobuf.Int32Value getLeverageLevel();
  /**
   * <code>.google.protobuf.Int32Value leverageLevel = 6;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getLeverageLevelOrBuilder();

  /**
   * <code>string nominalValue = 7;</code>
   */
  String getNominalValue();
  /**
   * <code>string nominalValue = 7;</code>
   */
  com.google.protobuf.ByteString
      getNominalValueBytes();

  /**
   * <code>string margin = 8;</code>
   */
  String getMargin();
  /**
   * <code>string margin = 8;</code>
   */
  com.google.protobuf.ByteString
      getMarginBytes();

  /**
   * <code>string maintenanceMargin = 9;</code>
   */
  String getMaintenanceMargin();
  /**
   * <code>string maintenanceMargin = 9;</code>
   */
  com.google.protobuf.ByteString
      getMaintenanceMarginBytes();

  /**
   * <code>string closeFee = 10;</code>
   */
  String getCloseFee();
  /**
   * <code>string closeFee = 10;</code>
   */
  com.google.protobuf.ByteString
      getCloseFeeBytes();

  /**
   * <code>string marginRatioMolecule = 11;</code>
   */
  String getMarginRatioMolecule();
  /**
   * <code>string marginRatioMolecule = 11;</code>
   */
  com.google.protobuf.ByteString
      getMarginRatioMoleculeBytes();

  /**
   * <code>string dealBase = 12;</code>
   */
  String getDealBase();
  /**
   * <code>string dealBase = 12;</code>
   */
  com.google.protobuf.ByteString
      getDealBaseBytes();

  /**
   * <code>string frozenQuote = 13;</code>
   */
  String getFrozenQuote();
  /**
   * <code>string frozenQuote = 13;</code>
   */
  com.google.protobuf.ByteString
      getFrozenQuoteBytes();

  /**
   * <code>string avgPrice = 14;</code>
   */
  String getAvgPrice();
  /**
   * <code>string avgPrice = 14;</code>
   */
  com.google.protobuf.ByteString
      getAvgPriceBytes();

  /**
   * <code>string dealQuote = 15;</code>
   */
  String getDealQuote();
  /**
   * <code>string dealQuote = 15;</code>
   */
  com.google.protobuf.ByteString
      getDealQuoteBytes();

  /**
   * <code>.google.protobuf.Int32Value status = 16;</code>
   */
  boolean hasStatus();
  /**
   * <code>.google.protobuf.Int32Value status = 16;</code>
   */
  com.google.protobuf.Int32Value getStatus();
  /**
   * <code>.google.protobuf.Int32Value status = 16;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getStatusOrBuilder();

  /**
   * <code>string ip = 17;</code>
   */
  String getIp();
  /**
   * <code>string ip = 17;</code>
   */
  com.google.protobuf.ByteString
      getIpBytes();

  /**
   * <code>.google.protobuf.Int32Value source = 18;</code>
   */
  boolean hasSource();
  /**
   * <code>.google.protobuf.Int32Value source = 18;</code>
   */
  com.google.protobuf.Int32Value getSource();
  /**
   * <code>.google.protobuf.Int32Value source = 18;</code>
   */
  com.google.protobuf.Int32ValueOrBuilder getSourceOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp ctime = 19;</code>
   */
  boolean hasCtime();
  /**
   * <code>.google.protobuf.Timestamp ctime = 19;</code>
   */
  com.google.protobuf.Timestamp getCtime();
  /**
   * <code>.google.protobuf.Timestamp ctime = 19;</code>
   */
  com.google.protobuf.TimestampOrBuilder getCtimeOrBuilder();

  /**
   * <code>.google.protobuf.Timestamp mtime = 20;</code>
   */
  boolean hasMtime();
  /**
   * <code>.google.protobuf.Timestamp mtime = 20;</code>
   */
  com.google.protobuf.Timestamp getMtime();
  /**
   * <code>.google.protobuf.Timestamp mtime = 20;</code>
   */
  com.google.protobuf.TimestampOrBuilder getMtimeOrBuilder();

  /**
   * <code>string frozenBase = 21;</code>
   */
  String getFrozenBase();
  /**
   * <code>string frozenBase = 21;</code>
   */
  com.google.protobuf.ByteString
      getFrozenBaseBytes();
}
