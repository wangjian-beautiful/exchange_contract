// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

public interface TransactionPageRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:transaction.TransactionPageRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 page = 1;</code>
   */
  int getPage();

  /**
   * <code>int32 size = 2;</code>
   */
  int getSize();

  /**
   * <code>.transaction.TransactionPO transactionPO = 3;</code>
   */
  boolean hasTransactionPO();
  /**
   * <code>.transaction.TransactionPO transactionPO = 3;</code>
   */
  TransactionPO getTransactionPO();
  /**
   * <code>.transaction.TransactionPO transactionPO = 3;</code>
   */
  TransactionPOOrBuilder getTransactionPOOrBuilder();
}