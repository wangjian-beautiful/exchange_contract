// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

public final class TransactionBizServiceProto {
  private TransactionBizServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_TransactionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_TransactionRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_TransactionPageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_TransactionPageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_TransactionListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_TransactionListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_TransactionIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_TransactionIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_TransactionReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_TransactionReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_TransactionListReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_TransactionListReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_UserTransactionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_UserTransactionRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_UserTransactionReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_UserTransactionReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_transaction_UserTransactionList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_transaction_UserTransactionList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\030TransactionService.proto\022\013transaction\032" +
      "\023TransactionPO.proto\"S\n\022TransactionReque" +
      "st\022\n\n\002id\030\001 \001(\003\0221\n\rtransactionPO\030\002 \001(\0132\032." +
      "transaction.TransactionPO\"g\n\026Transaction" +
      "PageRequest\022\014\n\004page\030\001 \001(\005\022\014\n\004size\030\002 \001(\005\022" +
      "1\n\rtransactionPO\030\003 \001(\0132\032.transaction.Tra" +
      "nsactionPO\"K\n\026TransactionListRequest\0221\n\r" +
      "transactionPO\030\001 \003(\0132\032.transaction.Transa" +
      "ctionPO\"#\n\025TransactionIdsRequest\022\n\n\002id\030\001" +
      " \003(\003\"f\n\020TransactionReply\022\016\n\006status\030\001 \001(\010" +
      "\022\017\n\007message\030\002 \001(\t\0221\n\rtransactionPO\030\003 \001(\013" +
      "2\032.transaction.TransactionPO\"X\n\024Transact" +
      "ionListReply\0221\n\rtransactionPO\030\001 \003(\0132\032.tr" +
      "ansaction.TransactionPO\022\r\n\005total\030\002 \001(\003\"\204" +
      "\001\n\026UserTransactionRequest\022\021\n\tbeginTime\030\001" +
      " \001(\t\022\017\n\007endTime\030\002 \001(\t\022\014\n\004page\030\003 \001(\005\022\r\n\005l" +
      "imit\030\004 \001(\005\022\016\n\006symbol\030\005 \001(\t\022\014\n\004type\030\006 \001(\t" +
      "\022\013\n\003uid\030\007 \001(\003\"`\n\024UserTransactionReply\0229\n" +
      "\017transactionList\030\001 \003(\0132 .transaction.Use" +
      "rTransactionList\022\r\n\005count\030\002 \001(\005\"h\n\023UserT" +
      "ransactionList\022\r\n\005ctime\030\001 \001(\t\022\016\n\006amount\030" +
      "\002 \001(\t\022\016\n\006symbol\030\003 \001(\t\022\014\n\004type\030\004 \001(\t\022\024\n\014c" +
      "ontractName\030\005 \001(\t2\277\007\n\025TransactionBizServ" +
      "ice\022H\n\007getById\022\037.transaction.Transaction" +
      "Request\032\032.transaction.TransactionPO\"\000\022Z\n" +
      "\017selectListByIds\022\".transaction.Transacti" +
      "onIdsRequest\032!.transaction.TransactionLi" +
      "stReply\"\000\022U\n\tselectAll\022#.transaction.Tra" +
      "nsactionPageRequest\032!.transaction.Transa" +
      "ctionListReply\"\000\022V\n\nselectList\022#.transac" +
      "tion.TransactionPageRequest\032!.transactio" +
      "n.TransactionListReply\"\000\022M\n\tinsertOne\022\037." +
      "transaction.TransactionRequest\032\035.transac" +
      "tion.TransactionReply\"\000\022W\n\013insertBatch\022#" +
      ".transaction.TransactionListRequest\032!.tr" +
      "ansaction.TransactionListReply\"\000\022N\n\nupda" +
      "teById\022\037.transaction.TransactionRequest\032" +
      "\035.transaction.TransactionReply\"\000\022S\n\013upda" +
      "teBatch\022#.transaction.TransactionListReq" +
      "uest\032\035.transaction.TransactionReply\"\000\022N\n" +
      "\nremoveById\022\037.transaction.TransactionReq" +
      "uest\032\035.transaction.TransactionReply\"\000\022P\n" +
      "\tremoveAll\022\".transaction.TransactionIdsR" +
      "equest\032\035.transaction.TransactionReply\"\000\022" +
      "b\n\026getUserTransactionList\022#.transaction." +
      "UserTransactionRequest\032!.transaction.Use" +
      "rTransactionReply\"\000BB\n\"com.bjs.contract." +
      "proto.transactionB\032TransactionBizService" +
      "ProtoP\001b\006proto3"
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
          TransactionPOProto.getDescriptor(),
        }, assigner);
    internal_static_transaction_TransactionRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_transaction_TransactionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_TransactionRequest_descriptor,
        new String[] { "Id", "TransactionPO", });
    internal_static_transaction_TransactionPageRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_transaction_TransactionPageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_TransactionPageRequest_descriptor,
        new String[] { "Page", "Size", "TransactionPO", });
    internal_static_transaction_TransactionListRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_transaction_TransactionListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_TransactionListRequest_descriptor,
        new String[] { "TransactionPO", });
    internal_static_transaction_TransactionIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_transaction_TransactionIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_TransactionIdsRequest_descriptor,
        new String[] { "Id", });
    internal_static_transaction_TransactionReply_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_transaction_TransactionReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_TransactionReply_descriptor,
        new String[] { "Status", "Message", "TransactionPO", });
    internal_static_transaction_TransactionListReply_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_transaction_TransactionListReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_TransactionListReply_descriptor,
        new String[] { "TransactionPO", "Total", });
    internal_static_transaction_UserTransactionRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_transaction_UserTransactionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_UserTransactionRequest_descriptor,
        new String[] { "BeginTime", "EndTime", "Page", "Limit", "Symbol", "Type", "Uid", });
    internal_static_transaction_UserTransactionReply_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_transaction_UserTransactionReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_UserTransactionReply_descriptor,
        new String[] { "TransactionList", "Count", });
    internal_static_transaction_UserTransactionList_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_transaction_UserTransactionList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_transaction_UserTransactionList_descriptor,
        new String[] { "Ctime", "Amount", "Symbol", "Type", "ContractName", });
    TransactionPOProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}