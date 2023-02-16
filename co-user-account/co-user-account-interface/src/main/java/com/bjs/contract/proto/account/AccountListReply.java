// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountService.proto

package com.bjs.contract.proto.account;

/**
 * Protobuf type {@code account.AccountListReply}
 */
public  final class AccountListReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.AccountListReply)
    AccountListReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AccountListReply.newBuilder() to construct.
  private AccountListReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AccountListReply() {
    accountPO_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AccountListReply(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              accountPO_ = new java.util.ArrayList<AccountPO>();
              mutable_bitField0_ |= 0x00000001;
            }
            accountPO_.add(
                input.readMessage(AccountPO.parser(), extensionRegistry));
            break;
          }
          case 16: {

            total_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        accountPO_ = java.util.Collections.unmodifiableList(accountPO_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return AccountBizServiceProto.internal_static_account_AccountListReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return AccountBizServiceProto.internal_static_account_AccountListReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            AccountListReply.class, Builder.class);
  }

  private int bitField0_;
  public static final int ACCOUNTPO_FIELD_NUMBER = 1;
  private java.util.List<AccountPO> accountPO_;
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  public java.util.List<AccountPO> getAccountPOList() {
    return accountPO_;
  }
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  public java.util.List<? extends AccountPOOrBuilder>
      getAccountPOOrBuilderList() {
    return accountPO_;
  }
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  public int getAccountPOCount() {
    return accountPO_.size();
  }
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  public AccountPO getAccountPO(int index) {
    return accountPO_.get(index);
  }
  /**
   * <code>repeated .account.AccountPO accountPO = 1;</code>
   */
  public AccountPOOrBuilder getAccountPOOrBuilder(
      int index) {
    return accountPO_.get(index);
  }

  public static final int TOTAL_FIELD_NUMBER = 2;
  private long total_;
  /**
   * <code>int64 total = 2;</code>
   */
  public long getTotal() {
    return total_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < accountPO_.size(); i++) {
      output.writeMessage(1, accountPO_.get(i));
    }
    if (total_ != 0L) {
      output.writeInt64(2, total_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < accountPO_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, accountPO_.get(i));
    }
    if (total_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, total_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof AccountListReply)) {
      return super.equals(obj);
    }
    AccountListReply other = (AccountListReply) obj;

    if (!getAccountPOList()
        .equals(other.getAccountPOList())) return false;
    if (getTotal()
        != other.getTotal()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getAccountPOCount() > 0) {
      hash = (37 * hash) + ACCOUNTPO_FIELD_NUMBER;
      hash = (53 * hash) + getAccountPOList().hashCode();
    }
    hash = (37 * hash) + TOTAL_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTotal());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static AccountListReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static AccountListReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static AccountListReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static AccountListReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static AccountListReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static AccountListReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static AccountListReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static AccountListReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static AccountListReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static AccountListReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static AccountListReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static AccountListReply parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(AccountListReply prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code account.AccountListReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.AccountListReply)
      AccountListReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return AccountBizServiceProto.internal_static_account_AccountListReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return AccountBizServiceProto.internal_static_account_AccountListReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              AccountListReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.account.AccountListReply.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getAccountPOFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      if (accountPOBuilder_ == null) {
        accountPO_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        accountPOBuilder_.clear();
      }
      total_ = 0L;

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return AccountBizServiceProto.internal_static_account_AccountListReply_descriptor;
    }

    @Override
    public AccountListReply getDefaultInstanceForType() {
      return AccountListReply.getDefaultInstance();
    }

    @Override
    public AccountListReply build() {
      AccountListReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public AccountListReply buildPartial() {
      AccountListReply result = new AccountListReply(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (accountPOBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          accountPO_ = java.util.Collections.unmodifiableList(accountPO_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.accountPO_ = accountPO_;
      } else {
        result.accountPO_ = accountPOBuilder_.build();
      }
      result.total_ = total_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof AccountListReply) {
        return mergeFrom((AccountListReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(AccountListReply other) {
      if (other == AccountListReply.getDefaultInstance()) return this;
      if (accountPOBuilder_ == null) {
        if (!other.accountPO_.isEmpty()) {
          if (accountPO_.isEmpty()) {
            accountPO_ = other.accountPO_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureAccountPOIsMutable();
            accountPO_.addAll(other.accountPO_);
          }
          onChanged();
        }
      } else {
        if (!other.accountPO_.isEmpty()) {
          if (accountPOBuilder_.isEmpty()) {
            accountPOBuilder_.dispose();
            accountPOBuilder_ = null;
            accountPO_ = other.accountPO_;
            bitField0_ = (bitField0_ & ~0x00000001);
            accountPOBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAccountPOFieldBuilder() : null;
          } else {
            accountPOBuilder_.addAllMessages(other.accountPO_);
          }
        }
      }
      if (other.getTotal() != 0L) {
        setTotal(other.getTotal());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      AccountListReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (AccountListReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<AccountPO> accountPO_ =
      java.util.Collections.emptyList();
    private void ensureAccountPOIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        accountPO_ = new java.util.ArrayList<AccountPO>(accountPO_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        AccountPO, AccountPO.Builder, AccountPOOrBuilder> accountPOBuilder_;

    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public java.util.List<AccountPO> getAccountPOList() {
      if (accountPOBuilder_ == null) {
        return java.util.Collections.unmodifiableList(accountPO_);
      } else {
        return accountPOBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public int getAccountPOCount() {
      if (accountPOBuilder_ == null) {
        return accountPO_.size();
      } else {
        return accountPOBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public AccountPO getAccountPO(int index) {
      if (accountPOBuilder_ == null) {
        return accountPO_.get(index);
      } else {
        return accountPOBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder setAccountPO(
        int index, AccountPO value) {
      if (accountPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAccountPOIsMutable();
        accountPO_.set(index, value);
        onChanged();
      } else {
        accountPOBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder setAccountPO(
        int index, AccountPO.Builder builderForValue) {
      if (accountPOBuilder_ == null) {
        ensureAccountPOIsMutable();
        accountPO_.set(index, builderForValue.build());
        onChanged();
      } else {
        accountPOBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder addAccountPO(AccountPO value) {
      if (accountPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAccountPOIsMutable();
        accountPO_.add(value);
        onChanged();
      } else {
        accountPOBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder addAccountPO(
        int index, AccountPO value) {
      if (accountPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAccountPOIsMutable();
        accountPO_.add(index, value);
        onChanged();
      } else {
        accountPOBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder addAccountPO(
        AccountPO.Builder builderForValue) {
      if (accountPOBuilder_ == null) {
        ensureAccountPOIsMutable();
        accountPO_.add(builderForValue.build());
        onChanged();
      } else {
        accountPOBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder addAccountPO(
        int index, AccountPO.Builder builderForValue) {
      if (accountPOBuilder_ == null) {
        ensureAccountPOIsMutable();
        accountPO_.add(index, builderForValue.build());
        onChanged();
      } else {
        accountPOBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder addAllAccountPO(
        Iterable<? extends AccountPO> values) {
      if (accountPOBuilder_ == null) {
        ensureAccountPOIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, accountPO_);
        onChanged();
      } else {
        accountPOBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder clearAccountPO() {
      if (accountPOBuilder_ == null) {
        accountPO_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        accountPOBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public Builder removeAccountPO(int index) {
      if (accountPOBuilder_ == null) {
        ensureAccountPOIsMutable();
        accountPO_.remove(index);
        onChanged();
      } else {
        accountPOBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public AccountPO.Builder getAccountPOBuilder(
        int index) {
      return getAccountPOFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public AccountPOOrBuilder getAccountPOOrBuilder(
        int index) {
      if (accountPOBuilder_ == null) {
        return accountPO_.get(index);  } else {
        return accountPOBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public java.util.List<? extends AccountPOOrBuilder>
         getAccountPOOrBuilderList() {
      if (accountPOBuilder_ != null) {
        return accountPOBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(accountPO_);
      }
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public AccountPO.Builder addAccountPOBuilder() {
      return getAccountPOFieldBuilder().addBuilder(
          AccountPO.getDefaultInstance());
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public AccountPO.Builder addAccountPOBuilder(
        int index) {
      return getAccountPOFieldBuilder().addBuilder(
          index, AccountPO.getDefaultInstance());
    }
    /**
     * <code>repeated .account.AccountPO accountPO = 1;</code>
     */
    public java.util.List<AccountPO.Builder>
         getAccountPOBuilderList() {
      return getAccountPOFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        AccountPO, AccountPO.Builder, AccountPOOrBuilder>
        getAccountPOFieldBuilder() {
      if (accountPOBuilder_ == null) {
        accountPOBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            AccountPO, AccountPO.Builder, AccountPOOrBuilder>(
                accountPO_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        accountPO_ = null;
      }
      return accountPOBuilder_;
    }

    private long total_ ;
    /**
     * <code>int64 total = 2;</code>
     */
    public long getTotal() {
      return total_;
    }
    /**
     * <code>int64 total = 2;</code>
     */
    public Builder setTotal(long value) {
      
      total_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 total = 2;</code>
     */
    public Builder clearTotal() {
      
      total_ = 0L;
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:account.AccountListReply)
  }

  // @@protoc_insertion_point(class_scope:account.AccountListReply)
  private static final AccountListReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new AccountListReply();
  }

  public static AccountListReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AccountListReply>
      PARSER = new com.google.protobuf.AbstractParser<AccountListReply>() {
    @Override
    public AccountListReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AccountListReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AccountListReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<AccountListReply> getParserForType() {
    return PARSER;
  }

  @Override
  public AccountListReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

