// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SettlementPO.proto

package com.bjs.contract.proto.settlement;

public final class SettlementPOProto {
  private SettlementPOProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_settlement_SettlementPO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_settlement_SettlementPO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022SettlementPO.proto\022\nsettlement\032\037google" +
      "/protobuf/timestamp.proto\032\036google/protob" +
      "uf/wrappers.proto\"\271\003\n\014SettlementPO\022\'\n\002id" +
      "\030\001 \001(\0132\033.google.protobuf.Int64Value\022\016\n\006s" +
      "ymbol\030\002 \001(\t\022)\n\004type\030\003 \001(\0132\033.google.proto" +
      "buf.Int32Value\022(\n\003uid\030\004 \001(\0132\033.google.pro" +
      "tobuf.Int64Value\0221\n\014coverOrderId\030\005 \001(\0132\033" +
      ".google.protobuf.Int64Value\0224\n\017positionO" +
      "rderId\030\006 \001(\0132\033.google.protobuf.Int64Valu" +
      "e\022\022\n\nsettleBase\030\007 \001(\t\022\023\n\013settleQuote\030\010 \001" +
      "(\t\022\026\n\016settleAvgPrice\030\t \001(\t\022)\n\005ctime\030\n \001(" +
      "\0132\032.google.protobuf.Timestamp\022)\n\005mtime\030\013" +
      " \001(\0132\032.google.protobuf.Timestamp\022\016\n\006prof" +
      "it\030\014 \001(\t\022\013\n\003fee\030\r \001(\tB8\n!com.bjs.contrac" +
      "t.proto.settlementB\021SettlementPOProtoP\001b" +
      "\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
          com.google.protobuf.WrappersProto.getDescriptor(),
        }, assigner);
    internal_static_settlement_SettlementPO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_settlement_SettlementPO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_settlement_SettlementPO_descriptor,
        new String[] { "Id", "Symbol", "Type", "Uid", "CoverOrderId", "PositionOrderId", "SettleBase", "SettleQuote", "SettleAvgPrice", "Ctime", "Mtime", "Profit", "Fee", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}