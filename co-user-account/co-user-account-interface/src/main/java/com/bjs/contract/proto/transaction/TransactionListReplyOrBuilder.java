// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

public interface TransactionListReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:transaction.TransactionListReply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .transaction.TransactionPO transactionPO = 1;</code>
   */
  java.util.List<TransactionPO>
      getTransactionPOList();
  /**
   * <code>repeated .transaction.TransactionPO transactionPO = 1;</code>
   */
  TransactionPO getTransactionPO(int index);
  /**
   * <code>repeated .transaction.TransactionPO transactionPO = 1;</code>
   */
  int getTransactionPOCount();
  /**
   * <code>repeated .transaction.TransactionPO transactionPO = 1;</code>
   */
  java.util.List<? extends TransactionPOOrBuilder>
      getTransactionPOOrBuilderList();
  /**
   * <code>repeated .transaction.TransactionPO transactionPO = 1;</code>
   */
  TransactionPOOrBuilder getTransactionPOOrBuilder(
      int index);

  /**
   * <code>int64 total = 2;</code>
   */
  long getTotal();
}
