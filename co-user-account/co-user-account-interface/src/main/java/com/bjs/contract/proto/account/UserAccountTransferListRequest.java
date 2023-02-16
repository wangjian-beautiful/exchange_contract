// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountService.proto

package com.bjs.contract.proto.account;

/**
 * Protobuf type {@code account.UserAccountTransferListRequest}
 */
public  final class UserAccountTransferListRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.UserAccountTransferListRequest)
    UserAccountTransferListRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserAccountTransferListRequest.newBuilder() to construct.
  private UserAccountTransferListRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserAccountTransferListRequest() {
    userAccountTransferPO_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserAccountTransferListRequest(
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
              userAccountTransferPO_ = new java.util.ArrayList<UserAccountTransferPO>();
              mutable_bitField0_ |= 0x00000001;
            }
            userAccountTransferPO_.add(
                input.readMessage(UserAccountTransferPO.parser(), extensionRegistry));
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
        userAccountTransferPO_ = java.util.Collections.unmodifiableList(userAccountTransferPO_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return AccountBizServiceProto.internal_static_account_UserAccountTransferListRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return AccountBizServiceProto.internal_static_account_UserAccountTransferListRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserAccountTransferListRequest.class, Builder.class);
  }

  public static final int USERACCOUNTTRANSFERPO_FIELD_NUMBER = 1;
  private java.util.List<UserAccountTransferPO> userAccountTransferPO_;
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  public java.util.List<UserAccountTransferPO> getUserAccountTransferPOList() {
    return userAccountTransferPO_;
  }
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  public java.util.List<? extends UserAccountTransferPOOrBuilder>
      getUserAccountTransferPOOrBuilderList() {
    return userAccountTransferPO_;
  }
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  public int getUserAccountTransferPOCount() {
    return userAccountTransferPO_.size();
  }
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  public UserAccountTransferPO getUserAccountTransferPO(int index) {
    return userAccountTransferPO_.get(index);
  }
  /**
   * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
   */
  public UserAccountTransferPOOrBuilder getUserAccountTransferPOOrBuilder(
      int index) {
    return userAccountTransferPO_.get(index);
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
    for (int i = 0; i < userAccountTransferPO_.size(); i++) {
      output.writeMessage(1, userAccountTransferPO_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < userAccountTransferPO_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, userAccountTransferPO_.get(i));
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
    if (!(obj instanceof UserAccountTransferListRequest)) {
      return super.equals(obj);
    }
    UserAccountTransferListRequest other = (UserAccountTransferListRequest) obj;

    if (!getUserAccountTransferPOList()
        .equals(other.getUserAccountTransferPOList())) return false;
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
    if (getUserAccountTransferPOCount() > 0) {
      hash = (37 * hash) + USERACCOUNTTRANSFERPO_FIELD_NUMBER;
      hash = (53 * hash) + getUserAccountTransferPOList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserAccountTransferListRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserAccountTransferListRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserAccountTransferListRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserAccountTransferListRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserAccountTransferListRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserAccountTransferListRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserAccountTransferListRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserAccountTransferListRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserAccountTransferListRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserAccountTransferListRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserAccountTransferListRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserAccountTransferListRequest parseFrom(
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
  public static Builder newBuilder(UserAccountTransferListRequest prototype) {
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
   * Protobuf type {@code account.UserAccountTransferListRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.UserAccountTransferListRequest)
      UserAccountTransferListRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return AccountBizServiceProto.internal_static_account_UserAccountTransferListRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return AccountBizServiceProto.internal_static_account_UserAccountTransferListRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserAccountTransferListRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.account.UserAccountTransferListRequest.newBuilder()
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
        getUserAccountTransferPOFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      if (userAccountTransferPOBuilder_ == null) {
        userAccountTransferPO_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        userAccountTransferPOBuilder_.clear();
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return AccountBizServiceProto.internal_static_account_UserAccountTransferListRequest_descriptor;
    }

    @Override
    public UserAccountTransferListRequest getDefaultInstanceForType() {
      return UserAccountTransferListRequest.getDefaultInstance();
    }

    @Override
    public UserAccountTransferListRequest build() {
      UserAccountTransferListRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UserAccountTransferListRequest buildPartial() {
      UserAccountTransferListRequest result = new UserAccountTransferListRequest(this);
      int from_bitField0_ = bitField0_;
      if (userAccountTransferPOBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          userAccountTransferPO_ = java.util.Collections.unmodifiableList(userAccountTransferPO_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.userAccountTransferPO_ = userAccountTransferPO_;
      } else {
        result.userAccountTransferPO_ = userAccountTransferPOBuilder_.build();
      }
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
      if (other instanceof UserAccountTransferListRequest) {
        return mergeFrom((UserAccountTransferListRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserAccountTransferListRequest other) {
      if (other == UserAccountTransferListRequest.getDefaultInstance()) return this;
      if (userAccountTransferPOBuilder_ == null) {
        if (!other.userAccountTransferPO_.isEmpty()) {
          if (userAccountTransferPO_.isEmpty()) {
            userAccountTransferPO_ = other.userAccountTransferPO_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureUserAccountTransferPOIsMutable();
            userAccountTransferPO_.addAll(other.userAccountTransferPO_);
          }
          onChanged();
        }
      } else {
        if (!other.userAccountTransferPO_.isEmpty()) {
          if (userAccountTransferPOBuilder_.isEmpty()) {
            userAccountTransferPOBuilder_.dispose();
            userAccountTransferPOBuilder_ = null;
            userAccountTransferPO_ = other.userAccountTransferPO_;
            bitField0_ = (bitField0_ & ~0x00000001);
            userAccountTransferPOBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getUserAccountTransferPOFieldBuilder() : null;
          } else {
            userAccountTransferPOBuilder_.addAllMessages(other.userAccountTransferPO_);
          }
        }
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
      UserAccountTransferListRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserAccountTransferListRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<UserAccountTransferPO> userAccountTransferPO_ =
      java.util.Collections.emptyList();
    private void ensureUserAccountTransferPOIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        userAccountTransferPO_ = new java.util.ArrayList<UserAccountTransferPO>(userAccountTransferPO_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        UserAccountTransferPO, UserAccountTransferPO.Builder, UserAccountTransferPOOrBuilder> userAccountTransferPOBuilder_;

    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public java.util.List<UserAccountTransferPO> getUserAccountTransferPOList() {
      if (userAccountTransferPOBuilder_ == null) {
        return java.util.Collections.unmodifiableList(userAccountTransferPO_);
      } else {
        return userAccountTransferPOBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public int getUserAccountTransferPOCount() {
      if (userAccountTransferPOBuilder_ == null) {
        return userAccountTransferPO_.size();
      } else {
        return userAccountTransferPOBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public UserAccountTransferPO getUserAccountTransferPO(int index) {
      if (userAccountTransferPOBuilder_ == null) {
        return userAccountTransferPO_.get(index);
      } else {
        return userAccountTransferPOBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder setUserAccountTransferPO(
        int index, UserAccountTransferPO value) {
      if (userAccountTransferPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.set(index, value);
        onChanged();
      } else {
        userAccountTransferPOBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder setUserAccountTransferPO(
        int index, UserAccountTransferPO.Builder builderForValue) {
      if (userAccountTransferPOBuilder_ == null) {
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.set(index, builderForValue.build());
        onChanged();
      } else {
        userAccountTransferPOBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder addUserAccountTransferPO(UserAccountTransferPO value) {
      if (userAccountTransferPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.add(value);
        onChanged();
      } else {
        userAccountTransferPOBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder addUserAccountTransferPO(
        int index, UserAccountTransferPO value) {
      if (userAccountTransferPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.add(index, value);
        onChanged();
      } else {
        userAccountTransferPOBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder addUserAccountTransferPO(
        UserAccountTransferPO.Builder builderForValue) {
      if (userAccountTransferPOBuilder_ == null) {
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.add(builderForValue.build());
        onChanged();
      } else {
        userAccountTransferPOBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder addUserAccountTransferPO(
        int index, UserAccountTransferPO.Builder builderForValue) {
      if (userAccountTransferPOBuilder_ == null) {
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.add(index, builderForValue.build());
        onChanged();
      } else {
        userAccountTransferPOBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder addAllUserAccountTransferPO(
        Iterable<? extends UserAccountTransferPO> values) {
      if (userAccountTransferPOBuilder_ == null) {
        ensureUserAccountTransferPOIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, userAccountTransferPO_);
        onChanged();
      } else {
        userAccountTransferPOBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder clearUserAccountTransferPO() {
      if (userAccountTransferPOBuilder_ == null) {
        userAccountTransferPO_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        userAccountTransferPOBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public Builder removeUserAccountTransferPO(int index) {
      if (userAccountTransferPOBuilder_ == null) {
        ensureUserAccountTransferPOIsMutable();
        userAccountTransferPO_.remove(index);
        onChanged();
      } else {
        userAccountTransferPOBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public UserAccountTransferPO.Builder getUserAccountTransferPOBuilder(
        int index) {
      return getUserAccountTransferPOFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public UserAccountTransferPOOrBuilder getUserAccountTransferPOOrBuilder(
        int index) {
      if (userAccountTransferPOBuilder_ == null) {
        return userAccountTransferPO_.get(index);  } else {
        return userAccountTransferPOBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public java.util.List<? extends UserAccountTransferPOOrBuilder>
         getUserAccountTransferPOOrBuilderList() {
      if (userAccountTransferPOBuilder_ != null) {
        return userAccountTransferPOBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(userAccountTransferPO_);
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public UserAccountTransferPO.Builder addUserAccountTransferPOBuilder() {
      return getUserAccountTransferPOFieldBuilder().addBuilder(
          UserAccountTransferPO.getDefaultInstance());
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public UserAccountTransferPO.Builder addUserAccountTransferPOBuilder(
        int index) {
      return getUserAccountTransferPOFieldBuilder().addBuilder(
          index, UserAccountTransferPO.getDefaultInstance());
    }
    /**
     * <code>repeated .account.UserAccountTransferPO userAccountTransferPO = 1;</code>
     */
    public java.util.List<UserAccountTransferPO.Builder>
         getUserAccountTransferPOBuilderList() {
      return getUserAccountTransferPOFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        UserAccountTransferPO, UserAccountTransferPO.Builder, UserAccountTransferPOOrBuilder>
        getUserAccountTransferPOFieldBuilder() {
      if (userAccountTransferPOBuilder_ == null) {
        userAccountTransferPOBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            UserAccountTransferPO, UserAccountTransferPO.Builder, UserAccountTransferPOOrBuilder>(
                userAccountTransferPO_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        userAccountTransferPO_ = null;
      }
      return userAccountTransferPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:account.UserAccountTransferListRequest)
  }

  // @@protoc_insertion_point(class_scope:account.UserAccountTransferListRequest)
  private static final UserAccountTransferListRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserAccountTransferListRequest();
  }

  public static UserAccountTransferListRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserAccountTransferListRequest>
      PARSER = new com.google.protobuf.AbstractParser<UserAccountTransferListRequest>() {
    @Override
    public UserAccountTransferListRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserAccountTransferListRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserAccountTransferListRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UserAccountTransferListRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public UserAccountTransferListRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

