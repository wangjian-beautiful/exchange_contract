// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MaintenanceMarginRateService.proto

package com.bjs.contract.proto.maintenanceMarginRate;

public final class MaintenanceMarginRateBizServiceProto {
  private MaintenanceMarginRateBizServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_NominalValueRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_NominalValueRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_SymbolMaxLeverageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_SymbolMaxLeverageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_SymbolIntervalRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_SymbolIntervalRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_SymbolIntervalReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_SymbolIntervalReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateListReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateListReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateMinRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateMinRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_maintenanceMarginRate_MaintenanceMarginRateMinReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_maintenanceMarginRate_MaintenanceMarginRateMinReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\"MaintenanceMarginRateService.proto\022\025ma" +
      "intenanceMarginRate\032\035MaintenanceMarginRa" +
      "tePO.proto\"{\n\034MaintenanceMarginRateReque" +
      "st\022\n\n\002id\030\001 \001(\003\022O\n\027maintenanceMarginRateP" +
      "O\030\002 \001(\0132..maintenanceMarginRate.Maintena" +
      "nceMarginRatePO\";\n\023NominalValueRequest\022\024" +
      "\n\014nominalValue\030\001 \001(\t\022\016\n\006symbol\030\002 \001(\t\"*\n\030" +
      "SymbolMaxLeverageRequest\022\016\n\006symbol\030\001 \001(\t" +
      "\"\'\n\025SymbolIntervalRequest\022\016\n\006symbol\030\001 \001(" +
      "\t\"\217\001\n\023SymbolIntervalReply\022;\n\003min\030\001 \001(\0132." +
      ".maintenanceMarginRate.MaintenanceMargin" +
      "RatePO\022;\n\003max\030\002 \001(\0132..maintenanceMarginR" +
      "ate.MaintenanceMarginRatePO\"\217\001\n Maintena" +
      "nceMarginRatePageRequest\022\014\n\004page\030\001 \001(\005\022\014" +
      "\n\004size\030\002 \001(\005\022O\n\027maintenanceMarginRatePO\030" +
      "\003 \001(\0132..maintenanceMarginRate.Maintenanc" +
      "eMarginRatePO\"s\n MaintenanceMarginRateLi" +
      "stRequest\022O\n\027maintenanceMarginRatePO\030\001 \003" +
      "(\0132..maintenanceMarginRate.MaintenanceMa" +
      "rginRatePO\"-\n\037MaintenanceMarginRateIdsRe" +
      "quest\022\n\n\002id\030\001 \003(\003\"\216\001\n\032MaintenanceMarginR" +
      "ateReply\022\016\n\006status\030\001 \001(\010\022\017\n\007message\030\002 \001(" +
      "\t\022O\n\027maintenanceMarginRatePO\030\003 \001(\0132..mai" +
      "ntenanceMarginRate.MaintenanceMarginRate" +
      "PO\"\200\001\n\036MaintenanceMarginRateListReply\022O\n" +
      "\027maintenanceMarginRatePO\030\001 \003(\0132..mainten" +
      "anceMarginRate.MaintenanceMarginRatePO\022\r" +
      "\n\005total\030\002 \001(\003\"1\n\037MaintenanceMarginRateMi" +
      "nRequest\022\016\n\006symbol\030\001 \001(\t\"0\n\035MaintenanceM" +
      "arginRateMinReply\022\017\n\007minRate\030\001 \001(\t2\337\r\n\037M" +
      "aintenanceMarginRateBizService\022p\n\007getByI" +
      "d\0223.maintenanceMarginRate.MaintenanceMar" +
      "ginRateRequest\032..maintenanceMarginRate.M" +
      "aintenanceMarginRatePO\"\000\022q\n\021getByNominal" +
      "Value\022*.maintenanceMarginRate.NominalVal" +
      "ueRequest\032..maintenanceMarginRate.Mainte" +
      "nanceMarginRatePO\"\000\022v\n\021SymbolMaxLeverage" +
      "\022/.maintenanceMarginRate.SymbolMaxLevera" +
      "geRequest\032..maintenanceMarginRate.Mainte" +
      "nanceMarginRatePO\"\000\022l\n\016SymbolInterval\022,." +
      "maintenanceMarginRate.SymbolIntervalRequ" +
      "est\032*.maintenanceMarginRate.SymbolInterv" +
      "alReply\"\000\022\202\001\n\017selectListByIds\0226.maintena" +
      "nceMarginRate.MaintenanceMarginRateIdsRe" +
      "quest\0325.maintenanceMarginRate.Maintenanc" +
      "eMarginRateListReply\"\000\022}\n\tselectAll\0227.ma" +
      "intenanceMarginRate.MaintenanceMarginRat" +
      "ePageRequest\0325.maintenanceMarginRate.Mai" +
      "ntenanceMarginRateListReply\"\000\022~\n\nselectL" +
      "ist\0227.maintenanceMarginRate.MaintenanceM" +
      "arginRatePageRequest\0325.maintenanceMargin" +
      "Rate.MaintenanceMarginRateListReply\"\000\022u\n" +
      "\tinsertOne\0223.maintenanceMarginRate.Maint" +
      "enanceMarginRateRequest\0321.maintenanceMar" +
      "ginRate.MaintenanceMarginRateReply\"\000\022\177\n\013" +
      "insertBatch\0227.maintenanceMarginRate.Main" +
      "tenanceMarginRateListRequest\0325.maintenan" +
      "ceMarginRate.MaintenanceMarginRateListRe" +
      "ply\"\000\022v\n\nupdateById\0223.maintenanceMarginR" +
      "ate.MaintenanceMarginRateRequest\0321.maint" +
      "enanceMarginRate.MaintenanceMarginRateRe" +
      "ply\"\000\022{\n\013updateBatch\0227.maintenanceMargin" +
      "Rate.MaintenanceMarginRateListRequest\0321." +
      "maintenanceMarginRate.MaintenanceMarginR" +
      "ateReply\"\000\022v\n\nremoveById\0223.maintenanceMa" +
      "rginRate.MaintenanceMarginRateRequest\0321." +
      "maintenanceMarginRate.MaintenanceMarginR" +
      "ateReply\"\000\022x\n\tremoveAll\0226.maintenanceMar" +
      "ginRate.MaintenanceMarginRateIdsRequest\032" +
      "1.maintenanceMarginRate.MaintenanceMargi" +
      "nRateReply\"\000\022\215\001\n\033getMaintenanceMarginRat" +
      "eMin\0226.maintenanceMarginRate.Maintenance" +
      "MarginRateMinRequest\0324.maintenanceMargin" +
      "Rate.MaintenanceMarginRateMinReply\"\000BV\n," +
      "com.bjs.contract.proto.maintenanceMargin" +
      "RateB$MaintenanceMarginRateBizServicePro" +
      "toP\001b\006proto3"
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
          MaintenanceMarginRatePOProto.getDescriptor(),
        }, assigner);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateRequest_descriptor,
        new String[] { "Id", "MaintenanceMarginRatePO", });
    internal_static_maintenanceMarginRate_NominalValueRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_maintenanceMarginRate_NominalValueRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_NominalValueRequest_descriptor,
        new String[] { "NominalValue", "Symbol", });
    internal_static_maintenanceMarginRate_SymbolMaxLeverageRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_maintenanceMarginRate_SymbolMaxLeverageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_SymbolMaxLeverageRequest_descriptor,
        new String[] { "Symbol", });
    internal_static_maintenanceMarginRate_SymbolIntervalRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_maintenanceMarginRate_SymbolIntervalRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_SymbolIntervalRequest_descriptor,
        new String[] { "Symbol", });
    internal_static_maintenanceMarginRate_SymbolIntervalReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_maintenanceMarginRate_SymbolIntervalReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_SymbolIntervalReply_descriptor,
        new String[] { "Min", "Max", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_descriptor,
        new String[] { "Page", "Size", "MaintenanceMarginRatePO", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRateListRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateListRequest_descriptor,
        new String[] { "MaintenanceMarginRatePO", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRateIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateIdsRequest_descriptor,
        new String[] { "Id", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_descriptor,
        new String[] { "Status", "Message", "MaintenanceMarginRatePO", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRateListReply_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateListReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateListReply_descriptor,
        new String[] { "MaintenanceMarginRatePO", "Total", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRateMinRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateMinRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateMinRequest_descriptor,
        new String[] { "Symbol", });
    internal_static_maintenanceMarginRate_MaintenanceMarginRateMinReply_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_maintenanceMarginRate_MaintenanceMarginRateMinReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_maintenanceMarginRate_MaintenanceMarginRateMinReply_descriptor,
        new String[] { "MinRate", });
    MaintenanceMarginRatePOProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}