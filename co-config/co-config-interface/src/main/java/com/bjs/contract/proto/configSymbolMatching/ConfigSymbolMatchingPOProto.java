// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConfigSymbolMatchingPO.proto

package com.bjs.contract.proto.configSymbolMatching;

public final class ConfigSymbolMatchingPOProto {
  private ConfigSymbolMatchingPOProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_configSymbolMatching_ConfigSymbolMatchingPO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_configSymbolMatching_ConfigSymbolMatchingPO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\034ConfigSymbolMatchingPO.proto\022\024configSy" +
      "mbolMatching\032\037google/protobuf/timestamp." +
      "proto\032\036google/protobuf/wrappers.proto\"\361\001" +
      "\n\026ConfigSymbolMatchingPO\022\'\n\002id\030\001 \001(\0132\033.g" +
      "oogle.protobuf.Int32Value\022\014\n\004base\030\002 \001(\t\022" +
      "\r\n\005quote\030\003 \001(\t\022+\n\006isOpen\030\004 \001(\0132\033.google." +
      "protobuf.Int32Value\022\016\n\006server\030\005 \001(\t\022)\n\005c" +
      "time\030\006 \001(\0132\032.google.protobuf.Timestamp\022)" +
      "\n\005mtime\030\007 \001(\0132\032.google.protobuf.Timestam" +
      "pBL\n+com.bjs.contract.proto.configSymbol" +
      "MatchingB\033ConfigSymbolMatchingPOProtoP\001b" +
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
    internal_static_configSymbolMatching_ConfigSymbolMatchingPO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_configSymbolMatching_ConfigSymbolMatchingPO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_configSymbolMatching_ConfigSymbolMatchingPO_descriptor,
        new String[] { "Id", "Base", "Quote", "IsOpen", "Server", "Ctime", "Mtime", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}