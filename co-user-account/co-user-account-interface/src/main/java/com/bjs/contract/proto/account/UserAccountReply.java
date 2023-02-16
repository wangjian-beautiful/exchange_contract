// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccountService.proto

package com.bjs.contract.proto.account;

/**
 * Protobuf type {@code account.UserAccountReply}
 */
public  final class UserAccountReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:account.UserAccountReply)
    UserAccountReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserAccountReply.newBuilder() to construct.
  private UserAccountReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserAccountReply() {
    userAccountTransferResponse_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserAccountReply(
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
              userAccountTransferResponse_ = new java.util.ArrayList<UserAccountTransferResponse>();
              mutable_bitField0_ |= 0x00000001;
            }
            userAccountTransferResponse_.add(
                input.readMessage(UserAccountTransferResponse.parser(), extensionRegistry));
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
        userAccountTransferResponse_ = java.util.Collections.unmodifiableList(userAccountTransferResponse_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return AccountBizServiceProto.internal_static_account_UserAccountReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return AccountBizServiceProto.internal_static_account_UserAccountReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserAccountReply.class, Builder.class);
  }

  public static final int USERACCOUNTTRANSFERRESPONSE_FIELD_NUMBER = 1;
  private java.util.List<UserAccountTransferResponse> userAccountTransferResponse_;
  /**
   * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
   */
  public java.util.List<UserAccountTransferResponse> getUserAccountTransferResponseList() {
    return userAccountTransferResponse_;
  }
  /**
   * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
   */
  public java.util.List<? extends UserAccountTransferResponseOrBuilder>
      getUserAccountTransferResponseOrBuilderList() {
    return userAccountTransferResponse_;
  }
  /**
   * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
   */
  public int getUserAccountTransferResponseCount() {
    return userAccountTransferResponse_.size();
  }
  /**
   * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
   */
  public UserAccountTransferResponse getUserAccountTransferResponse(int index) {
    return userAccountTransferResponse_.get(index);
  }
  /**
   * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
   */
  public UserAccountTransferResponseOrBuilder getUserAccountTransferResponseOrBuilder(
      int index) {
    return userAccountTransferResponse_.get(index);
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
    for (int i = 0; i < userAccountTransferResponse_.size(); i++) {
      output.writeMessage(1, userAccountTransferResponse_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < userAccountTransferResponse_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, userAccountTransferResponse_.get(i));
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
    if (!(obj instanceof UserAccountReply)) {
      return super.equals(obj);
    }
    UserAccountReply other = (UserAccountReply) obj;

    if (!getUserAccountTransferResponseList()
        .equals(other.getUserAccountTransferResponseList())) return false;
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
    if (getUserAccountTransferResponseCount() > 0) {
      hash = (37 * hash) + USERACCOUNTTRANSFERRESPONSE_FIELD_NUMBER;
      hash = (53 * hash) + getUserAccountTransferResponseList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserAccountReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserAccountReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserAccountReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserAccountReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserAccountReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserAccountReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserAccountReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserAccountReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserAccountReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserAccountReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserAccountReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserAccountReply parseFrom(
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
  public static Builder newBuilder(UserAccountReply prototype) {
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
   * Protobuf type {@code account.UserAccountReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:account.UserAccountReply)
      UserAccountReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return AccountBizServiceProto.internal_static_account_UserAccountReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return AccountBizServiceProto.internal_static_account_UserAccountReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserAccountReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.account.UserAccountReply.newBuilder()
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
        getUserAccountTransferResponseFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      if (userAccountTransferResponseBuilder_ == null) {
        userAccountTransferResponse_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        userAccountTransferResponseBuilder_.clear();
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return AccountBizServiceProto.internal_static_account_UserAccountReply_descriptor;
    }

    @Override
    public UserAccountReply getDefaultInstanceForType() {
      return UserAccountReply.getDefaultInstance();
    }

    @Override
    public UserAccountReply build() {
      UserAccountReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UserAccountReply buildPartial() {
      UserAccountReply result = new UserAccountReply(this);
      int from_bitField0_ = bitField0_;
      if (userAccountTransferResponseBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          userAccountTransferResponse_ = java.util.Collections.unmodifiableList(userAccountTransferResponse_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.userAccountTransferResponse_ = userAccountTransferResponse_;
      } else {
        result.userAccountTransferResponse_ = userAccountTransferResponseBuilder_.build();
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
      if (other instanceof UserAccountReply) {
        return mergeFrom((UserAccountReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserAccountReply other) {
      if (other == UserAccountReply.getDefaultInstance()) return this;
      if (userAccountTransferResponseBuilder_ == null) {
        if (!other.userAccountTransferResponse_.isEmpty()) {
          if (userAccountTransferResponse_.isEmpty()) {
            userAccountTransferResponse_ = other.userAccountTransferResponse_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureUserAccountTransferResponseIsMutable();
            userAccountTransferResponse_.addAll(other.userAccountTransferResponse_);
          }
          onChanged();
        }
      } else {
        if (!other.userAccountTransferResponse_.isEmpty()) {
          if (userAccountTransferResponseBuilder_.isEmpty()) {
            userAccountTransferResponseBuilder_.dispose();
            userAccountTransferResponseBuilder_ = null;
            userAccountTransferResponse_ = other.userAccountTransferResponse_;
            bitField0_ = (bitField0_ & ~0x00000001);
            userAccountTransferResponseBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getUserAccountTransferResponseFieldBuilder() : null;
          } else {
            userAccountTransferResponseBuilder_.addAllMessages(other.userAccountTransferResponse_);
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
      UserAccountReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserAccountReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<UserAccountTransferResponse> userAccountTransferResponse_ =
      java.util.Collections.emptyList();
    private void ensureUserAccountTransferResponseIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        userAccountTransferResponse_ = new java.util.ArrayList<UserAccountTransferResponse>(userAccountTransferResponse_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        UserAccountTransferResponse, UserAccountTransferResponse.Builder, UserAccountTransferResponseOrBuilder> userAccountTransferResponseBuilder_;

    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public java.util.List<UserAccountTransferResponse> getUserAccountTransferResponseList() {
      if (userAccountTransferResponseBuilder_ == null) {
        return java.util.Collections.unmodifiableList(userAccountTransferResponse_);
      } else {
        return userAccountTransferResponseBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public int getUserAccountTransferResponseCount() {
      if (userAccountTransferResponseBuilder_ == null) {
        return userAccountTransferResponse_.size();
      } else {
        return userAccountTransferResponseBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public UserAccountTransferResponse getUserAccountTransferResponse(int index) {
      if (userAccountTransferResponseBuilder_ == null) {
        return userAccountTransferResponse_.get(index);
      } else {
        return userAccountTransferResponseBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder setUserAccountTransferResponse(
        int index, UserAccountTransferResponse value) {
      if (userAccountTransferResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.set(index, value);
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder setUserAccountTransferResponse(
        int index, UserAccountTransferResponse.Builder builderForValue) {
      if (userAccountTransferResponseBuilder_ == null) {
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.set(index, builderForValue.build());
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder addUserAccountTransferResponse(UserAccountTransferResponse value) {
      if (userAccountTransferResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.add(value);
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder addUserAccountTransferResponse(
        int index, UserAccountTransferResponse value) {
      if (userAccountTransferResponseBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.add(index, value);
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder addUserAccountTransferResponse(
        UserAccountTransferResponse.Builder builderForValue) {
      if (userAccountTransferResponseBuilder_ == null) {
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.add(builderForValue.build());
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder addUserAccountTransferResponse(
        int index, UserAccountTransferResponse.Builder builderForValue) {
      if (userAccountTransferResponseBuilder_ == null) {
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.add(index, builderForValue.build());
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder addAllUserAccountTransferResponse(
        Iterable<? extends UserAccountTransferResponse> values) {
      if (userAccountTransferResponseBuilder_ == null) {
        ensureUserAccountTransferResponseIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, userAccountTransferResponse_);
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder clearUserAccountTransferResponse() {
      if (userAccountTransferResponseBuilder_ == null) {
        userAccountTransferResponse_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public Builder removeUserAccountTransferResponse(int index) {
      if (userAccountTransferResponseBuilder_ == null) {
        ensureUserAccountTransferResponseIsMutable();
        userAccountTransferResponse_.remove(index);
        onChanged();
      } else {
        userAccountTransferResponseBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public UserAccountTransferResponse.Builder getUserAccountTransferResponseBuilder(
        int index) {
      return getUserAccountTransferResponseFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public UserAccountTransferResponseOrBuilder getUserAccountTransferResponseOrBuilder(
        int index) {
      if (userAccountTransferResponseBuilder_ == null) {
        return userAccountTransferResponse_.get(index);  } else {
        return userAccountTransferResponseBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public java.util.List<? extends UserAccountTransferResponseOrBuilder>
         getUserAccountTransferResponseOrBuilderList() {
      if (userAccountTransferResponseBuilder_ != null) {
        return userAccountTransferResponseBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(userAccountTransferResponse_);
      }
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public UserAccountTransferResponse.Builder addUserAccountTransferResponseBuilder() {
      return getUserAccountTransferResponseFieldBuilder().addBuilder(
          UserAccountTransferResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public UserAccountTransferResponse.Builder addUserAccountTransferResponseBuilder(
        int index) {
      return getUserAccountTransferResponseFieldBuilder().addBuilder(
          index, UserAccountTransferResponse.getDefaultInstance());
    }
    /**
     * <code>repeated .account.UserAccountTransferResponse userAccountTransferResponse = 1;</code>
     */
    public java.util.List<UserAccountTransferResponse.Builder>
         getUserAccountTransferResponseBuilderList() {
      return getUserAccountTransferResponseFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        UserAccountTransferResponse, UserAccountTransferResponse.Builder, UserAccountTransferResponseOrBuilder>
        getUserAccountTransferResponseFieldBuilder() {
      if (userAccountTransferResponseBuilder_ == null) {
        userAccountTransferResponseBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            UserAccountTransferResponse, UserAccountTransferResponse.Builder, UserAccountTransferResponseOrBuilder>(
                userAccountTransferResponse_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        userAccountTransferResponse_ = null;
      }
      return userAccountTransferResponseBuilder_;
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


    // @@protoc_insertion_point(builder_scope:account.UserAccountReply)
  }

  // @@protoc_insertion_point(class_scope:account.UserAccountReply)
  private static final UserAccountReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserAccountReply();
  }

  public static UserAccountReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserAccountReply>
      PARSER = new com.google.protobuf.AbstractParser<UserAccountReply>() {
    @Override
    public UserAccountReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserAccountReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserAccountReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UserAccountReply> getParserForType() {
    return PARSER;
  }

  @Override
  public UserAccountReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

