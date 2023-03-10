// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SettlementService.proto

package com.bjs.contract.proto.settlement;

/**
 * Protobuf type {@code settlement.SettlementReply}
 */
public  final class SettlementReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:settlement.SettlementReply)
    SettlementReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SettlementReply.newBuilder() to construct.
  private SettlementReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SettlementReply() {
    message_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SettlementReply(
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
            SettlementPO.Builder subBuilder = null;
            if (settlementPO_ != null) {
              subBuilder = settlementPO_.toBuilder();
            }
            settlementPO_ = input.readMessage(SettlementPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(settlementPO_);
              settlementPO_ = subBuilder.buildPartial();
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
    return SettlementBizServiceProto.internal_static_settlement_SettlementReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return SettlementBizServiceProto.internal_static_settlement_SettlementReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            SettlementReply.class, Builder.class);
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

  public static final int SETTLEMENTPO_FIELD_NUMBER = 3;
  private SettlementPO settlementPO_;
  /**
   * <code>.settlement.SettlementPO settlementPO = 3;</code>
   */
  public boolean hasSettlementPO() {
    return settlementPO_ != null;
  }
  /**
   * <code>.settlement.SettlementPO settlementPO = 3;</code>
   */
  public SettlementPO getSettlementPO() {
    return settlementPO_ == null ? SettlementPO.getDefaultInstance() : settlementPO_;
  }
  /**
   * <code>.settlement.SettlementPO settlementPO = 3;</code>
   */
  public SettlementPOOrBuilder getSettlementPOOrBuilder() {
    return getSettlementPO();
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
    if (settlementPO_ != null) {
      output.writeMessage(3, getSettlementPO());
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
    if (settlementPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getSettlementPO());
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
    if (!(obj instanceof SettlementReply)) {
      return super.equals(obj);
    }
    SettlementReply other = (SettlementReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (hasSettlementPO() != other.hasSettlementPO()) return false;
    if (hasSettlementPO()) {
      if (!getSettlementPO()
          .equals(other.getSettlementPO())) return false;
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
    if (hasSettlementPO()) {
      hash = (37 * hash) + SETTLEMENTPO_FIELD_NUMBER;
      hash = (53 * hash) + getSettlementPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static SettlementReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SettlementReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SettlementReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SettlementReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SettlementReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SettlementReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SettlementReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SettlementReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static SettlementReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static SettlementReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static SettlementReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SettlementReply parseFrom(
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
  public static Builder newBuilder(SettlementReply prototype) {
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
   * Protobuf type {@code settlement.SettlementReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:settlement.SettlementReply)
      SettlementReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return SettlementBizServiceProto.internal_static_settlement_SettlementReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return SettlementBizServiceProto.internal_static_settlement_SettlementReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SettlementReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.settlement.SettlementReply.newBuilder()
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

      if (settlementPOBuilder_ == null) {
        settlementPO_ = null;
      } else {
        settlementPO_ = null;
        settlementPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return SettlementBizServiceProto.internal_static_settlement_SettlementReply_descriptor;
    }

    @Override
    public SettlementReply getDefaultInstanceForType() {
      return SettlementReply.getDefaultInstance();
    }

    @Override
    public SettlementReply build() {
      SettlementReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public SettlementReply buildPartial() {
      SettlementReply result = new SettlementReply(this);
      result.status_ = status_;
      result.message_ = message_;
      if (settlementPOBuilder_ == null) {
        result.settlementPO_ = settlementPO_;
      } else {
        result.settlementPO_ = settlementPOBuilder_.build();
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
      if (other instanceof SettlementReply) {
        return mergeFrom((SettlementReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(SettlementReply other) {
      if (other == SettlementReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.hasSettlementPO()) {
        mergeSettlementPO(other.getSettlementPO());
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
      SettlementReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (SettlementReply) e.getUnfinishedMessage();
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

    private SettlementPO settlementPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        SettlementPO, SettlementPO.Builder, SettlementPOOrBuilder> settlementPOBuilder_;
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public boolean hasSettlementPO() {
      return settlementPOBuilder_ != null || settlementPO_ != null;
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public SettlementPO getSettlementPO() {
      if (settlementPOBuilder_ == null) {
        return settlementPO_ == null ? SettlementPO.getDefaultInstance() : settlementPO_;
      } else {
        return settlementPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public Builder setSettlementPO(SettlementPO value) {
      if (settlementPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        settlementPO_ = value;
        onChanged();
      } else {
        settlementPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public Builder setSettlementPO(
        SettlementPO.Builder builderForValue) {
      if (settlementPOBuilder_ == null) {
        settlementPO_ = builderForValue.build();
        onChanged();
      } else {
        settlementPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public Builder mergeSettlementPO(SettlementPO value) {
      if (settlementPOBuilder_ == null) {
        if (settlementPO_ != null) {
          settlementPO_ =
            SettlementPO.newBuilder(settlementPO_).mergeFrom(value).buildPartial();
        } else {
          settlementPO_ = value;
        }
        onChanged();
      } else {
        settlementPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public Builder clearSettlementPO() {
      if (settlementPOBuilder_ == null) {
        settlementPO_ = null;
        onChanged();
      } else {
        settlementPO_ = null;
        settlementPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public SettlementPO.Builder getSettlementPOBuilder() {
      
      onChanged();
      return getSettlementPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    public SettlementPOOrBuilder getSettlementPOOrBuilder() {
      if (settlementPOBuilder_ != null) {
        return settlementPOBuilder_.getMessageOrBuilder();
      } else {
        return settlementPO_ == null ?
            SettlementPO.getDefaultInstance() : settlementPO_;
      }
    }
    /**
     * <code>.settlement.SettlementPO settlementPO = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        SettlementPO, SettlementPO.Builder, SettlementPOOrBuilder>
        getSettlementPOFieldBuilder() {
      if (settlementPOBuilder_ == null) {
        settlementPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            SettlementPO, SettlementPO.Builder, SettlementPOOrBuilder>(
                getSettlementPO(),
                getParentForChildren(),
                isClean());
        settlementPO_ = null;
      }
      return settlementPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:settlement.SettlementReply)
  }

  // @@protoc_insertion_point(class_scope:settlement.SettlementReply)
  private static final SettlementReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new SettlementReply();
  }

  public static SettlementReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SettlementReply>
      PARSER = new com.google.protobuf.AbstractParser<SettlementReply>() {
    @Override
    public SettlementReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SettlementReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SettlementReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<SettlementReply> getParserForType() {
    return PARSER;
  }

  @Override
  public SettlementReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

