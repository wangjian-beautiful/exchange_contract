// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FundingRatePO.proto

package com.bjs.contract.proto.fundingRate;

public final class FundingRatePOProto {
  private FundingRatePOProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_fundingRate_FundingRatePO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_fundingRate_FundingRatePO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\023FundingRatePO.proto\022\013fundingRate\032\037goog" +
      "le/protobuf/timestamp.proto\032\036google/prot" +
      "obuf/wrappers.proto\"\334\001\n\rFundingRatePO\022\'\n" +
      "\002id\030\001 \001(\0132\033.google.protobuf.Int64Value\022\016" +
      "\n\006symbol\030\002 \001(\t\022\014\n\004rate\030\003 \001(\t\022.\n\nsettleTi" +
      "me\030\004 \001(\0132\032.google.protobuf.Timestamp\022)\n\005" +
      "mtime\030\005 \001(\0132\032.google.protobuf.Timestamp\022" +
      ")\n\005ctime\030\006 \001(\0132\032.google.protobuf.Timesta" +
      "mpB:\n\"com.bjs.contract.proto.fundingRate" +
      "B\022FundingRatePOProtoP\001b\006proto3"
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
    internal_static_fundingRate_FundingRatePO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_fundingRate_FundingRatePO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_fundingRate_FundingRatePO_descriptor,
        new String[] { "Id", "Symbol", "Rate", "SettleTime", "Mtime", "Ctime", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
