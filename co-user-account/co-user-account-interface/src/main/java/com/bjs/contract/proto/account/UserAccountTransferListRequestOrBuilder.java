// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountService.proto

package com.bjs.contract.proto.account;

public interface UserAccountTransferListRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.UserAccountTransferListRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  java.util.List<UserAccountTransferPO>
      getUserAccountTransferPOList();
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  UserAccountTransferPO getUserAccountTransferPO(int index);
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  int getUserAccountTransferPOCount();
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  java.util.List<? extends UserAccountTransferPOOrBuilder>
      getUserAccountTransferPOOrBuilderList();
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  UserAccountTransferPOOrBuilder getUserAccountTransferPOOrBuilder(
      int index);
}