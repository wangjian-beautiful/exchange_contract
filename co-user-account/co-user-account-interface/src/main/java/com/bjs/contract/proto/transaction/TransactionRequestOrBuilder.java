// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

public interface TransactionRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:transaction.TransactionRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   */
  long getId();

  /**
   * <code>.transaction.TransactionPO transactionPO = 2;</code>
   */
  boolean hasTransactionPO();
  /**
   * <code>.transaction.TransactionPO transactionPO = 2;</code>
   */
  TransactionPO getTransactionPO();
  /**
   * <code>.transaction.TransactionPO transactionPO = 2;</code>
   */
  TransactionPOOrBuilder getTransactionPOOrBuilder();
}
