// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserLeveragePO.proto

package com.bjs.contract.proto.userLeverage;

public final class UserLeveragePOProto {
  private UserLeveragePOProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_userLeverage_UserLeveragePO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_userLeverage_UserLeveragePO_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\024UserLeveragePO.proto\022\014userLeverage\032\037go" +
      "ogle/protobuf/timestamp.proto\032\036google/pr" +
      "otobuf/wrappers.proto\"\370\001\n\016UserLeveragePO" +
      "\022\'\n\002id\030\001 \001(\0132\033.google.protobuf.Int64Valu" +
      "e\022(\n\003uid\030\002 \001(\0132\033.google.protobuf.Int64Va" +
      "lue\022\016\n\006symbol\030\003 \001(\t\022-\n\010leverage\030\004 \001(\0132\033." +
      "google.protobuf.Int32Value\022)\n\005ctime\030\005 \001(" +
      "\0132\032.google.protobuf.Timestamp\022)\n\005mtime\030\006" +
      " \001(\0132\032.google.protobuf.TimestampB<\n#com." +
      "bjs.contract.proto.userLeverageB\023UserLev" +
      "eragePOProtoP\001b\006proto3"
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
    internal_static_userLeverage_UserLeveragePO_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_userLeverage_UserLeveragePO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_userLeverage_UserLeveragePO_descriptor,
        new String[] { "Id", "Uid", "Symbol", "Leverage", "Ctime", "Mtime", });
    com.google.protobuf.TimestampProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
