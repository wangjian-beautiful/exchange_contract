// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoTradeService.proto

package com.bjs.contract.proto.coTrade;

public final class CoTradeBizServiceProto {
  private CoTradeBizServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTrade_CoTradeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTrade_CoTradeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTrade_CoTradePageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTrade_CoTradePageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTrade_CoTradeListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTrade_CoTradeListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTrade_CoTradeIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTrade_CoTradeIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTrade_CoTradeReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTrade_CoTradeReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coTrade_CoTradeListReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coTrade_CoTradeListReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\024CoTradeService.proto\022\007coTrade\032\017CoTrade" +
      "PO.proto\"C\n\016CoTradeRequest\022\n\n\002id\030\001 \001(\003\022%" +
      "\n\tcoTradePO\030\002 \001(\0132\022.coTrade.CoTradePO\"W\n" +
      "\022CoTradePageRequest\022\014\n\004page\030\001 \001(\005\022\014\n\004siz" +
      "e\030\002 \001(\005\022%\n\tcoTradePO\030\003 \001(\0132\022.coTrade.CoT" +
      "radePO\";\n\022CoTradeListRequest\022%\n\tcoTradeP" +
      "O\030\001 \003(\0132\022.coTrade.CoTradePO\"\037\n\021CoTradeId" +
      "sRequest\022\n\n\002id\030\001 \003(\003\"V\n\014CoTradeReply\022\016\n\006" +
      "status\030\001 \001(\010\022\017\n\007message\030\002 \001(\t\022%\n\tcoTrade" +
      "PO\030\003 \001(\0132\022.coTrade.CoTradePO\"H\n\020CoTradeL" +
      "istReply\022%\n\tcoTradePO\030\001 \003(\0132\022.coTrade.Co" +
      "TradePO\022\r\n\005total\030\002 \001(\0032\267\005\n\021CoTradeBizSer" +
      "vice\0228\n\007getById\022\027.coTrade.CoTradeRequest" +
      "\032\022.coTrade.CoTradePO\"\000\022J\n\017selectListById" +
      "s\022\032.coTrade.CoTradeIdsRequest\032\031.coTrade." +
      "CoTradeListReply\"\000\022E\n\tselectAll\022\033.coTrad" +
      "e.CoTradePageRequest\032\031.coTrade.CoTradeLi" +
      "stReply\"\000\022F\n\nselectList\022\033.coTrade.CoTrad" +
      "ePageRequest\032\031.coTrade.CoTradeListReply\"" +
      "\000\022=\n\tinsertOne\022\027.coTrade.CoTradeRequest\032" +
      "\025.coTrade.CoTradeReply\"\000\022G\n\013insertBatch\022" +
      "\033.coTrade.CoTradeListRequest\032\031.coTrade.C" +
      "oTradeListReply\"\000\022>\n\nupdateById\022\027.coTrad" +
      "e.CoTradeRequest\032\025.coTrade.CoTradeReply\"" +
      "\000\022C\n\013updateBatch\022\033.coTrade.CoTradeListRe" +
      "quest\032\025.coTrade.CoTradeReply\"\000\022>\n\nremove" +
      "ById\022\027.coTrade.CoTradeRequest\032\025.coTrade." +
      "CoTradeReply\"\000\022@\n\tremoveAll\022\032.coTrade.Co" +
      "TradeIdsRequest\032\025.coTrade.CoTradeReply\"\000" +
      "B:\n\036com.bjs.contract.proto.coTradeB\026CoTr" +
      "adeBizServiceProtoP\001b\006proto3"
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
          CoTradePOProto.getDescriptor(),
        }, assigner);
    internal_static_coTrade_CoTradeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_coTrade_CoTradeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTrade_CoTradeRequest_descriptor,
        new String[] { "Id", "CoTradePO", });
    internal_static_coTrade_CoTradePageRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_coTrade_CoTradePageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTrade_CoTradePageRequest_descriptor,
        new String[] { "Page", "Size", "CoTradePO", });
    internal_static_coTrade_CoTradeListRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_coTrade_CoTradeListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTrade_CoTradeListRequest_descriptor,
        new String[] { "CoTradePO", });
    internal_static_coTrade_CoTradeIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_coTrade_CoTradeIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTrade_CoTradeIdsRequest_descriptor,
        new String[] { "Id", });
    internal_static_coTrade_CoTradeReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_coTrade_CoTradeReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTrade_CoTradeReply_descriptor,
        new String[] { "Status", "Message", "CoTradePO", });
    internal_static_coTrade_CoTradeListReply_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_coTrade_CoTradeListReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coTrade_CoTradeListReply_descriptor,
        new String[] { "CoTradePO", "Total", });
    CoTradePOProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
