// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountService.proto

package com.bjs.contract.proto.account;

public interface AccountListRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.AccountListRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  java.util.List<AccountPO>
      getAccountPOList();
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  AccountPO getAccountPO(int index);
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  int getAccountPOCount();
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  java.util.List<? extends AccountPOOrBuilder>
      getAccountPOOrBuilderList();
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  AccountPOOrBuilder getAccountPOOrBuilder(
      int index);
}