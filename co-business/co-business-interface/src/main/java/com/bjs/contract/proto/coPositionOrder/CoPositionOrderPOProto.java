// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderPO.proto

package com.bjs.contract.proto.coPositionOrder;

public final class CoPositionOrderPOProto {
  private CoPositionOrderPOProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_coPositionOrder_CoPositionOrderPO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_coPositionOrder_CoPositionOrderPO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\027CoPositionOrderPO.proto\022\017coPositionOrd" +
      "er\032\037google/protobuf/timestamp.proto\032\036goo" +
      "gle/protobuf/wrappers.proto\"\377\004\n\021CoPositi" +
      "onOrderPO\022\'\n\002id\030\001 \001(\0132\033.google.protobuf." +
      "Int64Value\022\016\n\006symbol\030\002 \001(\t\022(\n\003uid\030\003 \001(\0132" +
      "\033.google.protobuf.Int64Value\0221\n\014position" +
      "Type\030\004 \001(\0132\033.google.protobuf.Int32Value\022" +
      "\024\n\014positionSide\030\005 \001(\t\0222\n\rleverageLevel\030\006" +
      " \001(\0132\033.google.protobuf.Int32Value\022\024\n\014nom" +
      "inalValue\030\007 \001(\t\022\016\n\006margin\030\010 \001(\t\022\031\n\021maint" +
      "enanceMargin\030\t \001(\t\022\020\n\010closeFee\030\n \001(\t\022\033\n\023" +
      "marginRatioMolecule\030\013 \001(\t\022\020\n\010dealBase\030\014 " +
      "\001(\t\022\023\n\013frozenQuote\030\r \001(\t\022\020\n\010avgPrice\030\016 \001" +
      "(\t\022\021\n\tdealQuote\030\017 \001(\t\022+\n\006status\030\020 \001(\0132\033." +
      "google.protobuf.Int32Value\022\n\n\002ip\030\021 \001(\t\022+" +
      "\n\006source\030\022 \001(\0132\033.google.protobuf.Int32Va" +
      "lue\022)\n\005ctime\030\023 \001(\0132\032.google.protobuf.Tim" +
      "estamp\022)\n\005mtime\030\024 \001(\0132\032.google.protobuf." +
      "Timestamp\022\022\n\nfrozenBase\030\025 \001(\tBB\n&com.bjs" +
      ".contract.proto.coPositionOrderB\026CoPosit" +
      "ionOrderPOProtoP\001b\006proto3"
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
    internal_static_coPositionOrder_CoPositionOrderPO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_coPositionOrder_CoPositionOrderPO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_coPositionOrder_CoPositionOrderPO_descriptor,
        new String[] { "Id", "Symbol", "Uid", "PositionType", "PositionSide", "LeverageLevel", "NominalValue", "Margin", "MaintenanceMargin", "CloseFee", "MarginRatioMolecule", "DealBase", "FrozenQuote", "AvgPrice", "DealQuote", "Status", "Ip", "Source", "Ctime", "Mtime", "FrozenBase", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
