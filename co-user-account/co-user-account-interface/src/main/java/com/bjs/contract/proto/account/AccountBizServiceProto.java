// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountService.proto

package com.bjs.contract.proto.account;

public final class AccountBizServiceProto {
  private AccountBizServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountRateRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountRateRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountUserIdRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountUserIdRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountUserTagRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountUserTagRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserTagResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserTagResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountOperate_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountOperate_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountPageRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountPageRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountIdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountIdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountUidRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountUidRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountListReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountListReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserAccountTransferPO_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserAccountTransferPO_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserAccountTransferResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserAccountTransferResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserAccountList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserAccountList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_AccountTransferReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_AccountTransferReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserAccountTransferListRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserAccountTransferListRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserAccountReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserAccountReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_account_UserAccountListReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_account_UserAccountListReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\024AccountService.proto\022\007account\032\017Account" +
      "PO.proto\"C\n\016AccountRequest\022\n\n\002id\030\001 \001(\003\022%" +
      "\n\taccountPO\030\002 \001(\0132\022.account.AccountPO\"0\n" +
      "\022AccountRateRequest\022\n\n\002id\030\001 \001(\003\022\016\n\006amoun" +
      "t\030\002 \001(\t\"1\n\024AccountUserIdRequest\022\013\n\003uid\030\001" +
      " \001(\003\022\014\n\004type\030\002 \001(\003\"1\n\025AccountUserTagRequ" +
      "est\022\013\n\003uid\030\001 \001(\003\022\013\n\003tag\030\002 \001(\t\"!\n\017UserTag" +
      "Response\022\016\n\006amount\030\001 \001(\t\"\262\001\n\016AccountOper" +
      "ate\022\013\n\003uid\030\001 \001(\003\022\016\n\006amount\030\002 \001(\t\022\016\n\006symb" +
      "ol\030\003 \001(\t\022\r\n\005scene\030\004 \001(\t\022\017\n\007refType\030\005 \001(\t" +
      "\022\r\n\005refId\030\006 \001(\003\022\025\n\rprofitAndLoss\030\007 \001(\t\022\026" +
      "\n\016ventureCapital\030\010 \001(\t\022\025\n\rserviceCharge\030" +
      "\t \001(\t\"W\n\022AccountPageRequest\022\014\n\004page\030\001 \001(" +
      "\005\022\014\n\004size\030\002 \001(\005\022%\n\taccountPO\030\003 \001(\0132\022.acc" +
      "ount.AccountPO\";\n\022AccountListRequest\022%\n\t" +
      "accountPO\030\001 \003(\0132\022.account.AccountPO\"\037\n\021A" +
      "ccountIdsRequest\022\n\n\002id\030\001 \003(\003\" \n\021AccountU" +
      "idRequest\022\013\n\003uid\030\001 \001(\003\"V\n\014AccountReply\022\016" +
      "\n\006status\030\001 \001(\010\022\017\n\007message\030\002 \001(\t\022%\n\taccou" +
      "ntPO\030\003 \001(\0132\022.account.AccountPO\"H\n\020Accoun" +
      "tListReply\022%\n\taccountPO\030\001 \003(\0132\022.account." +
      "AccountPO\022\r\n\005total\030\002 \001(\003\"D\n\025UserAccountT" +
      "ransferPO\022\013\n\003uid\030\001 \001(\003\022\016\n\006amount\030\002 \001(\t\022\016" +
      "\n\006symbol\030\003 \001(\t\";\n\033UserAccountTransferRes" +
      "ponse\022\013\n\003uid\030\001 \001(\003\022\017\n\007arrears\030\002 \001(\t\"\214\001\n\017" +
      "UserAccountList\022\024\n\014canUseAmount\030\001 \001(\t\022\023\n" +
      "\013totalAmount\030\002 \001(\t\022\025\n\risolateMargin\030\003 \001(" +
      "\t\022\022\n\nlockAmount\030\004 \001(\t\022\023\n\013totalMargin\030\005 \001" +
      "(\t\022\016\n\006symbol\030\006 \001(\t\"`\n\022AccountTransferReq" +
      "\022\013\n\003uid\030\001 \001(\003\022\016\n\006amount\030\002 \001(\t\022\016\n\006symbol\030" +
      "\003 \001(\t\022\014\n\004type\030\004 \001(\t\022\017\n\007balance\030\005 \001(\t\"_\n\036" +
      "UserAccountTransferListRequest\022=\n\025userAc" +
      "countTransferPO\030\001 \003(\0132\036.account.UserAcco" +
      "untTransferPO\"]\n\020UserAccountReply\022I\n\033use" +
      "rAccountTransferResponse\030\001 \003(\0132$.account" +
      ".UserAccountTransferResponse\"w\n\024UserAcco" +
      "untListReply\022-\n\013accountList\030\001 \003(\0132\030.acco" +
      "unt.UserAccountList\022\024\n\014totalBalance\030\002 \001(" +
      "\t\022\032\n\022totalBalanceSymbol\030\003 \001(\t2\245\013\n\021Accoun" +
      "tBizService\0228\n\007getById\022\027.account.Account" +
      "Request\032\022.account.AccountPO\"\000\022J\n\017selectL" +
      "istByIds\022\032.account.AccountIdsRequest\032\031.a" +
      "ccount.AccountListReply\"\000\022E\n\tselectAll\022\033" +
      ".account.AccountPageRequest\032\031.account.Ac" +
      "countListReply\"\000\022F\n\nselectList\022\033.account" +
      ".AccountPageRequest\032\031.account.AccountLis" +
      "tReply\"\000\022=\n\tinsertOne\022\027.account.AccountR" +
      "equest\032\025.account.AccountReply\"\000\022G\n\013inser" +
      "tBatch\022\033.account.AccountListRequest\032\031.ac" +
      "count.AccountListReply\"\000\022>\n\nupdateById\022\027" +
      ".account.AccountRequest\032\025.account.Accoun" +
      "tReply\"\000\022C\n\013updateBatch\022\033.account.Accoun" +
      "tListRequest\032\025.account.AccountReply\"\000\022>\n" +
      "\nremoveById\022\027.account.AccountRequest\032\025.a" +
      "ccount.AccountReply\"\000\022@\n\tremoveAll\022\032.acc" +
      "ount.AccountIdsRequest\032\025.account.Account" +
      "Reply\"\000\022B\n\016accountOperate\022\027.account.Acco" +
      "untOperate\032\025.account.AccountReply\"\000\022B\n\016g" +
      "etUserBalance\022\027.account.AccountRequest\032\025" +
      ".account.AccountReply\"\000\022G\n\rgetUidBalance" +
      "\022\035.account.AccountUserIdRequest\032\025.accoun" +
      "t.AccountReply\"\000\022@\n\017getByUidAndType\022\027.ac" +
      "count.AccountRequest\032\022.account.AccountPO" +
      "\"\000\022:\n\006freeze\022\027.account.AccountRequest\032\025." +
      "account.AccountReply\"\000\022\\\n\024AccountRateDed" +
      "uction\022\'.account.UserAccountTransferList" +
      "Request\032\031.account.UserAccountReply\"\000\022R\n\016" +
      "AccountRateAdd\022\'.account.UserAccountTran" +
      "sferListRequest\032\025.account.AccountReply\"\000" +
      "\022Q\n\022getUserAccountList\022\032.account.Account" +
      "UidRequest\032\035.account.UserAccountListRepl" +
      "y\"\000\022O\n\021getUserTagAccount\022\036.account.Accou" +
      "ntUserTagRequest\032\030.account.UserTagRespon" +
      "se\"\000\022G\n\017accountTransfer\022\033.account.Accoun" +
      "tTransferReq\032\025.account.AccountReply\"\000B:\n" +
      "\036com.bjs.contract.proto.accountB\026Account" +
      "BizServiceProtoP\001b\006proto3"
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
          AccountPOProto.getDescriptor(),
        }, assigner);
    internal_static_account_AccountRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_account_AccountRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountRequest_descriptor,
        new String[] { "Id", "AccountPO", });
    internal_static_account_AccountRateRequest_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_account_AccountRateRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountRateRequest_descriptor,
        new String[] { "Id", "Amount", });
    internal_static_account_AccountUserIdRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_account_AccountUserIdRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountUserIdRequest_descriptor,
        new String[] { "Uid", "Type", });
    internal_static_account_AccountUserTagRequest_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_account_AccountUserTagRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountUserTagRequest_descriptor,
        new String[] { "Uid", "Tag", });
    internal_static_account_UserTagResponse_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_account_UserTagResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserTagResponse_descriptor,
        new String[] { "Amount", });
    internal_static_account_AccountOperate_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_account_AccountOperate_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountOperate_descriptor,
        new String[] { "Uid", "Amount", "Symbol", "Scene", "RefType", "RefId", "ProfitAndLoss", "VentureCapital", "ServiceCharge", });
    internal_static_account_AccountPageRequest_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_account_AccountPageRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountPageRequest_descriptor,
        new String[] { "Page", "Size", "AccountPO", });
    internal_static_account_AccountListRequest_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_account_AccountListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountListRequest_descriptor,
        new String[] { "AccountPO", });
    internal_static_account_AccountIdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_account_AccountIdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountIdsRequest_descriptor,
        new String[] { "Id", });
    internal_static_account_AccountUidRequest_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_account_AccountUidRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountUidRequest_descriptor,
        new String[] { "Uid", });
    internal_static_account_AccountReply_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_account_AccountReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountReply_descriptor,
        new String[] { "Status", "Message", "AccountPO", });
    internal_static_account_AccountListReply_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_account_AccountListReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountListReply_descriptor,
        new String[] { "AccountPO", "Total", });
    internal_static_account_UserAccountTransferPO_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_account_UserAccountTransferPO_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserAccountTransferPO_descriptor,
        new String[] { "Uid", "Amount", "Symbol", });
    internal_static_account_UserAccountTransferResponse_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_account_UserAccountTransferResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserAccountTransferResponse_descriptor,
        new String[] { "Uid", "Arrears", });
    internal_static_account_UserAccountList_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_account_UserAccountList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserAccountList_descriptor,
        new String[] { "CanUseAmount", "TotalAmount", "IsolateMargin", "LockAmount", "TotalMargin", "Symbol", });
    internal_static_account_AccountTransferReq_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_account_AccountTransferReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_AccountTransferReq_descriptor,
        new String[] { "Uid", "Amount", "Symbol", "Type", "Balance", });
    internal_static_account_UserAccountTransferListRequest_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_account_UserAccountTransferListRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserAccountTransferListRequest_descriptor,
        new String[] { "UserAccountTransferPO", });
    internal_static_account_UserAccountReply_descriptor =
      getDescriptor().getMessageTypes().get(17);
    internal_static_account_UserAccountReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserAccountReply_descriptor,
        new String[] { "UserAccountTransferResponse", });
    internal_static_account_UserAccountListReply_descriptor =
      getDescriptor().getMessageTypes().get(18);
    internal_static_account_UserAccountListReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_account_UserAccountListReply_descriptor,
        new String[] { "AccountList", "TotalBalance", "TotalBalanceSymbol", });
    AccountPOProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
