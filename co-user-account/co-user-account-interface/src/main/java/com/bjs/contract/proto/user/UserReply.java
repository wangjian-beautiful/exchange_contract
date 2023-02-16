// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserService.proto

package com.bjs.contract.proto.user;

/**
 * Protobuf type {@code user.UserReply}
 */
public  final class UserReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:user.UserReply)
    UserReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserReply.newBuilder() to construct.
  private UserReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserReply() {
    message_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserReply(
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
          case 8: {

            status_ = input.readBool();
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 26: {
            UserPO.Builder subBuilder = null;
            if (userPO_ != null) {
              subBuilder = userPO_.toBuilder();
            }
            userPO_ = input.readMessage(UserPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(userPO_);
              userPO_ = subBuilder.buildPartial();
            }

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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return UserBizServiceProto.internal_static_user_UserReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return UserBizServiceProto.internal_static_user_UserReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserReply.class, Builder.class);
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private boolean status_;
  /**
   * <code>bool status = 1;</code>
   */
  public boolean getStatus() {
    return status_;
  }

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private volatile Object message_;
  /**
   * <code>string message = 2;</code>
   */
  public String getMessage() {
    Object ref = message_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>string message = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    Object ref = message_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int USERPO_FIELD_NUMBER = 3;
  private UserPO userPO_;
  /**
   * <code>.user.UserPO userPO = 3;</code>
   */
  public boolean hasUserPO() {
    return userPO_ != null;
  }
  /**
   * <code>.user.UserPO userPO = 3;</code>
   */
  public UserPO getUserPO() {
    return userPO_ == null ? UserPO.getDefaultInstance() : userPO_;
  }
  /**
   * <code>.user.UserPO userPO = 3;</code>
   */
  public UserPOOrBuilder getUserPOOrBuilder() {
    return getUserPO();
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
    if (status_ != false) {
      output.writeBool(1, status_);
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, message_);
    }
    if (userPO_ != null) {
      output.writeMessage(3, getUserPO());
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, status_);
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, message_);
    }
    if (userPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getUserPO());
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
    if (!(obj instanceof UserReply)) {
      return super.equals(obj);
    }
    UserReply other = (UserReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (hasUserPO() != other.hasUserPO()) return false;
    if (hasUserPO()) {
      if (!getUserPO()
          .equals(other.getUserPO())) return false;
    }
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
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getStatus());
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    if (hasUserPO()) {
      hash = (37 * hash) + USERPO_FIELD_NUMBER;
      hash = (53 * hash) + getUserPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserReply parseFrom(
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
  public static Builder newBuilder(UserReply prototype) {
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
   * Protobuf type {@code user.UserReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:user.UserReply)
      UserReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return UserBizServiceProto.internal_static_user_UserReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return UserBizServiceProto.internal_static_user_UserReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.user.UserReply.newBuilder()
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
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      status_ = false;

      message_ = "";

      if (userPOBuilder_ == null) {
        userPO_ = null;
      } else {
        userPO_ = null;
        userPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return UserBizServiceProto.internal_static_user_UserReply_descriptor;
    }

    @Override
    public UserReply getDefaultInstanceForType() {
      return UserReply.getDefaultInstance();
    }

    @Override
    public UserReply build() {
      UserReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UserReply buildPartial() {
      UserReply result = new UserReply(this);
      result.status_ = status_;
      result.message_ = message_;
      if (userPOBuilder_ == null) {
        result.userPO_ = userPO_;
      } else {
        result.userPO_ = userPOBuilder_.build();
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
      if (other instanceof UserReply) {
        return mergeFrom((UserReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserReply other) {
      if (other == UserReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.hasUserPO()) {
        mergeUserPO(other.getUserPO());
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
      UserReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean status_ ;
    /**
     * <code>bool status = 1;</code>
     */
    public boolean getStatus() {
      return status_;
    }
    /**
     * <code>bool status = 1;</code>
     */
    public Builder setStatus(boolean value) {
      
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool status = 1;</code>
     */
    public Builder clearStatus() {
      
      status_ = false;
      onChanged();
      return this;
    }

    private Object message_ = "";
    /**
     * <code>string message = 2;</code>
     */
    public String getMessage() {
      Object ref = message_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessage(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private UserPO userPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        UserPO, UserPO.Builder, UserPOOrBuilder> userPOBuilder_;
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public boolean hasUserPO() {
      return userPOBuilder_ != null || userPO_ != null;
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public UserPO getUserPO() {
      if (userPOBuilder_ == null) {
        return userPO_ == null ? UserPO.getDefaultInstance() : userPO_;
      } else {
        return userPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public Builder setUserPO(UserPO value) {
      if (userPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        userPO_ = value;
        onChanged();
      } else {
        userPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public Builder setUserPO(
        UserPO.Builder builderForValue) {
      if (userPOBuilder_ == null) {
        userPO_ = builderForValue.build();
        onChanged();
      } else {
        userPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public Builder mergeUserPO(UserPO value) {
      if (userPOBuilder_ == null) {
        if (userPO_ != null) {
          userPO_ =
            UserPO.newBuilder(userPO_).mergeFrom(value).buildPartial();
        } else {
          userPO_ = value;
        }
        onChanged();
      } else {
        userPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public Builder clearUserPO() {
      if (userPOBuilder_ == null) {
        userPO_ = null;
        onChanged();
      } else {
        userPO_ = null;
        userPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public UserPO.Builder getUserPOBuilder() {
      
      onChanged();
      return getUserPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    public UserPOOrBuilder getUserPOOrBuilder() {
      if (userPOBuilder_ != null) {
        return userPOBuilder_.getMessageOrBuilder();
      } else {
        return userPO_ == null ?
            UserPO.getDefaultInstance() : userPO_;
      }
    }
    /**
     * <code>.user.UserPO userPO = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        UserPO, UserPO.Builder, UserPOOrBuilder>
        getUserPOFieldBuilder() {
      if (userPOBuilder_ == null) {
        userPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            UserPO, UserPO.Builder, UserPOOrBuilder>(
                getUserPO(),
                getParentForChildren(),
                isClean());
        userPO_ = null;
      }
      return userPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:user.UserReply)
  }

  // @@protoc_insertion_point(class_scope:user.UserReply)
  private static final UserReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserReply();
  }

  public static UserReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserReply>
      PARSER = new com.google.protobuf.AbstractParser<UserReply>() {
    @Override
    public UserReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UserReply> getParserForType() {
    return PARSER;
  }

  @Override
  public UserReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

