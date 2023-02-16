// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserLeverageService.proto

package com.bjs.contract.proto.userLeverage;

/**
 * Protobuf type {@code userLeverage.UserLeverageReply}
 */
public  final class UserLeverageReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:userLeverage.UserLeverageReply)
    UserLeverageReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserLeverageReply.newBuilder() to construct.
  private UserLeverageReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserLeverageReply() {
    message_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserLeverageReply(
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
            UserLeveragePO.Builder subBuilder = null;
            if (userLeveragePO_ != null) {
              subBuilder = userLeveragePO_.toBuilder();
            }
            userLeveragePO_ = input.readMessage(UserLeveragePO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(userLeveragePO_);
              userLeveragePO_ = subBuilder.buildPartial();
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
    return UserLeverageBizServiceProto.internal_static_userLeverage_UserLeverageReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return UserLeverageBizServiceProto.internal_static_userLeverage_UserLeverageReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserLeverageReply.class, Builder.class);
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

  public static final int USERLEVERAGEPO_FIELD_NUMBER = 3;
  private UserLeveragePO userLeveragePO_;
  /**
   * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
   */
  public boolean hasUserLeveragePO() {
    return userLeveragePO_ != null;
  }
  /**
   * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
   */
  public UserLeveragePO getUserLeveragePO() {
    return userLeveragePO_ == null ? UserLeveragePO.getDefaultInstance() : userLeveragePO_;
  }
  /**
   * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
   */
  public UserLeveragePOOrBuilder getUserLeveragePOOrBuilder() {
    return getUserLeveragePO();
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
    if (userLeveragePO_ != null) {
      output.writeMessage(3, getUserLeveragePO());
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
    if (userLeveragePO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getUserLeveragePO());
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
    if (!(obj instanceof UserLeverageReply)) {
      return super.equals(obj);
    }
    UserLeverageReply other = (UserLeverageReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (hasUserLeveragePO() != other.hasUserLeveragePO()) return false;
    if (hasUserLeveragePO()) {
      if (!getUserLeveragePO()
          .equals(other.getUserLeveragePO())) return false;
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
    if (hasUserLeveragePO()) {
      hash = (37 * hash) + USERLEVERAGEPO_FIELD_NUMBER;
      hash = (53 * hash) + getUserLeveragePO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserLeverageReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserLeverageReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserLeverageReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserLeverageReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserLeverageReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserLeverageReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserLeverageReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserLeverageReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserLeverageReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserLeverageReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserLeverageReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserLeverageReply parseFrom(
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
  public static Builder newBuilder(UserLeverageReply prototype) {
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
   * Protobuf type {@code userLeverage.UserLeverageReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:userLeverage.UserLeverageReply)
      UserLeverageReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return UserLeverageBizServiceProto.internal_static_userLeverage_UserLeverageReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return UserLeverageBizServiceProto.internal_static_userLeverage_UserLeverageReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserLeverageReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.userLeverage.UserLeverageReply.newBuilder()
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

      if (userLeveragePOBuilder_ == null) {
        userLeveragePO_ = null;
      } else {
        userLeveragePO_ = null;
        userLeveragePOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return UserLeverageBizServiceProto.internal_static_userLeverage_UserLeverageReply_descriptor;
    }

    @Override
    public UserLeverageReply getDefaultInstanceForType() {
      return UserLeverageReply.getDefaultInstance();
    }

    @Override
    public UserLeverageReply build() {
      UserLeverageReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UserLeverageReply buildPartial() {
      UserLeverageReply result = new UserLeverageReply(this);
      result.status_ = status_;
      result.message_ = message_;
      if (userLeveragePOBuilder_ == null) {
        result.userLeveragePO_ = userLeveragePO_;
      } else {
        result.userLeveragePO_ = userLeveragePOBuilder_.build();
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
      if (other instanceof UserLeverageReply) {
        return mergeFrom((UserLeverageReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserLeverageReply other) {
      if (other == UserLeverageReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.hasUserLeveragePO()) {
        mergeUserLeveragePO(other.getUserLeveragePO());
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
      UserLeverageReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserLeverageReply) e.getUnfinishedMessage();
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

    private UserLeveragePO userLeveragePO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        UserLeveragePO, UserLeveragePO.Builder, UserLeveragePOOrBuilder> userLeveragePOBuilder_;
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public boolean hasUserLeveragePO() {
      return userLeveragePOBuilder_ != null || userLeveragePO_ != null;
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public UserLeveragePO getUserLeveragePO() {
      if (userLeveragePOBuilder_ == null) {
        return userLeveragePO_ == null ? UserLeveragePO.getDefaultInstance() : userLeveragePO_;
      } else {
        return userLeveragePOBuilder_.getMessage();
      }
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public Builder setUserLeveragePO(UserLeveragePO value) {
      if (userLeveragePOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        userLeveragePO_ = value;
        onChanged();
      } else {
        userLeveragePOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public Builder setUserLeveragePO(
        UserLeveragePO.Builder builderForValue) {
      if (userLeveragePOBuilder_ == null) {
        userLeveragePO_ = builderForValue.build();
        onChanged();
      } else {
        userLeveragePOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public Builder mergeUserLeveragePO(UserLeveragePO value) {
      if (userLeveragePOBuilder_ == null) {
        if (userLeveragePO_ != null) {
          userLeveragePO_ =
            UserLeveragePO.newBuilder(userLeveragePO_).mergeFrom(value).buildPartial();
        } else {
          userLeveragePO_ = value;
        }
        onChanged();
      } else {
        userLeveragePOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public Builder clearUserLeveragePO() {
      if (userLeveragePOBuilder_ == null) {
        userLeveragePO_ = null;
        onChanged();
      } else {
        userLeveragePO_ = null;
        userLeveragePOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public UserLeveragePO.Builder getUserLeveragePOBuilder() {
      
      onChanged();
      return getUserLeveragePOFieldBuilder().getBuilder();
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    public UserLeveragePOOrBuilder getUserLeveragePOOrBuilder() {
      if (userLeveragePOBuilder_ != null) {
        return userLeveragePOBuilder_.getMessageOrBuilder();
      } else {
        return userLeveragePO_ == null ?
            UserLeveragePO.getDefaultInstance() : userLeveragePO_;
      }
    }
    /**
     * <code>.userLeverage.UserLeveragePO userLeveragePO = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        UserLeveragePO, UserLeveragePO.Builder, UserLeveragePOOrBuilder>
        getUserLeveragePOFieldBuilder() {
      if (userLeveragePOBuilder_ == null) {
        userLeveragePOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            UserLeveragePO, UserLeveragePO.Builder, UserLeveragePOOrBuilder>(
                getUserLeveragePO(),
                getParentForChildren(),
                isClean());
        userLeveragePO_ = null;
      }
      return userLeveragePOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:userLeverage.UserLeverageReply)
  }

  // @@protoc_insertion_point(class_scope:userLeverage.UserLeverageReply)
  private static final UserLeverageReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserLeverageReply();
  }

  public static UserLeverageReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserLeverageReply>
      PARSER = new com.google.protobuf.AbstractParser<UserLeverageReply>() {
    @Override
    public UserLeverageReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserLeverageReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserLeverageReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UserLeverageReply> getParserForType() {
    return PARSER;
  }

  @Override
  public UserLeverageReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

