// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoTriggerOrderPO.proto

package com.bjs.contract.proto.coTriggerOrder;

public final class CoTriggerOrderPOProto {
  private CoTriggerOrderPOProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTriggerOrder_CoTriggerOrderPO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTriggerOrder_CoTriggerOrderPO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\026CoTriggerOrderPO.proto\022\016coTriggerOrder" +
      "\032\037google/protobuf/timestamp.proto\032\036googl" +
      "e/protobuf/wrappers.proto\"\256\007\n\020CoTriggerO" +
      "rderPO\022\'\n\002id\030\001 \001(\0132\033.google.protobuf.Int" +
      "64Value\022\016\n\006symbol\030\002 \001(\t\022(\n\003uid\030\003 \001(\0132\033.g" +
      "oogle.protobuf.Int64Value\0220\n\013triggerType" +
      "\030\004 \001(\0132\033.google.protobuf.Int32Value\022\024\n\014t" +
      "riggerPrice\030\005 \001(\t\022\032\n\022currentMarketPrice\030" +
      "\006 \001(\t\0221\n\014positionType\030\007 \001(\0132\033.google.pro" +
      "tobuf.Int32Value\022\023\n\013operateType\030\010 \001(\t\022\023\n" +
      "\013operateSide\030\t \001(\t\0222\n\rleverageLevel\030\n \001(" +
      "\0132\033.google.protobuf.Int32Value\022\r\n\005price\030" +
      "\013 \001(\t\022\022\n\nvolumeBase\030\014 \001(\t\022\023\n\013volumeQuote" +
      "\030\r \001(\t\022+\n\006status\030\016 \001(\0132\033.google.protobuf" +
      ".Int32Value\022)\n\004memo\030\017 \001(\0132\033.google.proto" +
      "buf.Int32Value\022.\n\nexpireTime\030\020 \001(\0132\032.goo" +
      "gle.protobuf.Timestamp\022)\n\005ctime\030\021 \001(\0132\032." +
      "google.protobuf.Timestamp\022)\n\005mtime\030\022 \001(\013" +
      "2\032.google.protobuf.Timestamp\022-\n\010masterId" +
      "\030\023 \001(\0132\033.google.protobuf.Int64Value\022)\n\004t" +
      "ype\030\024 \001(\0132\033.google.protobuf.Int32Value\022/" +
      "\n\npositionId\030\025 \001(\0132\033.google.protobuf.Int" +
      "64Value\022,\n\007orderId\030\026 \001(\0132\033.google.protob" +
      "uf.Int64Value\022.\n\ttradeType\030\027 \001(\0132\033.googl" +
      "e.protobuf.Int32Value\022\022\n\ntradePrice\030\030 \001(" +
      "\t\022/\n\013triggerTime\030\031 \001(\0132\032.google.protobuf" +
      ".TimestampB@\n%com.bjs.contract.proto.coT" +
      "riggerOrderB\025CoTriggerOrderPOProtoP\001b\006pr" +
      "oto3"
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
    internal_static_coTriggerOrder_CoTriggerOrderPO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_coTriggerOrder_CoTriggerOrderPO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTriggerOrder_CoTriggerOrderPO_descriptor,
        new String[] { "Id", "Symbol", "Uid", "TriggerType", "TriggerPrice", "CurrentMarketPrice", "PositionType", "OperateType", "OperateSide", "LeverageLevel", "Price", "VolumeBase", "VolumeQuote", "Status", "Memo", "ExpireTime", "Ctime", "Mtime", "MasterId", "Type", "PositionId", "OrderId", "TradeType", "TradePrice", "TriggerTime", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}