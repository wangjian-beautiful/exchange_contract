// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ContractConfigService.proto

package com.bjs.contract.proto.contractConfig;

/**
 * Protobuf type {@code contractConfig.ContractConfigReply}
 */
public  final class ContractConfigReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:contractConfig.ContractConfigReply)
    ContractConfigReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ContractConfigReply.newBuilder() to construct.
  private ContractConfigReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ContractConfigReply() {
    message_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ContractConfigReply(
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
            ContractConfigPO.Builder subBuilder = null;
            if (contractConfigPO_ != null) {
              subBuilder = contractConfigPO_.toBuilder();
            }
            contractConfigPO_ = input.readMessage(ContractConfigPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(contractConfigPO_);
              contractConfigPO_ = subBuilder.buildPartial();
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
    return ContractConfigBizServiceProto.internal_static_contractConfig_ContractConfigReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ContractConfigBizServiceProto.internal_static_contractConfig_ContractConfigReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ContractConfigReply.class, Builder.class);
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

  public static final int CONTRACTCONFIGPO_FIELD_NUMBER = 3;
  private ContractConfigPO contractConfigPO_;
  /**
   * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
   */
  public boolean hasContractConfigPO() {
    return contractConfigPO_ != null;
  }
  /**
   * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
   */
  public ContractConfigPO getContractConfigPO() {
    return contractConfigPO_ == null ? ContractConfigPO.getDefaultInstance() : contractConfigPO_;
  }
  /**
   * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
   */
  public ContractConfigPOOrBuilder getContractConfigPOOrBuilder() {
    return getContractConfigPO();
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
    if (contractConfigPO_ != null) {
      output.writeMessage(3, getContractConfigPO());
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
    if (contractConfigPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getContractConfigPO());
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
    if (!(obj instanceof ContractConfigReply)) {
      return super.equals(obj);
    }
    ContractConfigReply other = (ContractConfigReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (hasContractConfigPO() != other.hasContractConfigPO()) return false;
    if (hasContractConfigPO()) {
      if (!getContractConfigPO()
          .equals(other.getContractConfigPO())) return false;
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
    if (hasContractConfigPO()) {
      hash = (37 * hash) + CONTRACTCONFIGPO_FIELD_NUMBER;
      hash = (53 * hash) + getContractConfigPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ContractConfigReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ContractConfigReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ContractConfigReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ContractConfigReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ContractConfigReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ContractConfigReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ContractConfigReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ContractConfigReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ContractConfigReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ContractConfigReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ContractConfigReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ContractConfigReply parseFrom(
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
  public static Builder newBuilder(ContractConfigReply prototype) {
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
   * Protobuf type {@code contractConfig.ContractConfigReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:contractConfig.ContractConfigReply)
      ContractConfigReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ContractConfigBizServiceProto.internal_static_contractConfig_ContractConfigReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ContractConfigBizServiceProto.internal_static_contractConfig_ContractConfigReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ContractConfigReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.contractConfig.ContractConfigReply.newBuilder()
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

      if (contractConfigPOBuilder_ == null) {
        contractConfigPO_ = null;
      } else {
        contractConfigPO_ = null;
        contractConfigPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ContractConfigBizServiceProto.internal_static_contractConfig_ContractConfigReply_descriptor;
    }

    @Override
    public ContractConfigReply getDefaultInstanceForType() {
      return ContractConfigReply.getDefaultInstance();
    }

    @Override
    public ContractConfigReply build() {
      ContractConfigReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public ContractConfigReply buildPartial() {
      ContractConfigReply result = new ContractConfigReply(this);
      result.status_ = status_;
      result.message_ = message_;
      if (contractConfigPOBuilder_ == null) {
        result.contractConfigPO_ = contractConfigPO_;
      } else {
        result.contractConfigPO_ = contractConfigPOBuilder_.build();
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
      if (other instanceof ContractConfigReply) {
        return mergeFrom((ContractConfigReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ContractConfigReply other) {
      if (other == ContractConfigReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.hasContractConfigPO()) {
        mergeContractConfigPO(other.getContractConfigPO());
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
      ContractConfigReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ContractConfigReply) e.getUnfinishedMessage();
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

    private ContractConfigPO contractConfigPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        ContractConfigPO, ContractConfigPO.Builder, ContractConfigPOOrBuilder> contractConfigPOBuilder_;
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public boolean hasContractConfigPO() {
      return contractConfigPOBuilder_ != null || contractConfigPO_ != null;
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public ContractConfigPO getContractConfigPO() {
      if (contractConfigPOBuilder_ == null) {
        return contractConfigPO_ == null ? ContractConfigPO.getDefaultInstance() : contractConfigPO_;
      } else {
        return contractConfigPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public Builder setContractConfigPO(ContractConfigPO value) {
      if (contractConfigPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        contractConfigPO_ = value;
        onChanged();
      } else {
        contractConfigPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public Builder setContractConfigPO(
        ContractConfigPO.Builder builderForValue) {
      if (contractConfigPOBuilder_ == null) {
        contractConfigPO_ = builderForValue.build();
        onChanged();
      } else {
        contractConfigPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public Builder mergeContractConfigPO(ContractConfigPO value) {
      if (contractConfigPOBuilder_ == null) {
        if (contractConfigPO_ != null) {
          contractConfigPO_ =
            ContractConfigPO.newBuilder(contractConfigPO_).mergeFrom(value).buildPartial();
        } else {
          contractConfigPO_ = value;
        }
        onChanged();
      } else {
        contractConfigPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public Builder clearContractConfigPO() {
      if (contractConfigPOBuilder_ == null) {
        contractConfigPO_ = null;
        onChanged();
      } else {
        contractConfigPO_ = null;
        contractConfigPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public ContractConfigPO.Builder getContractConfigPOBuilder() {
      
      onChanged();
      return getContractConfigPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    public ContractConfigPOOrBuilder getContractConfigPOOrBuilder() {
      if (contractConfigPOBuilder_ != null) {
        return contractConfigPOBuilder_.getMessageOrBuilder();
      } else {
        return contractConfigPO_ == null ?
            ContractConfigPO.getDefaultInstance() : contractConfigPO_;
      }
    }
    /**
     * <code>.contractConfig.ContractConfigPO contractConfigPO = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        ContractConfigPO, ContractConfigPO.Builder, ContractConfigPOOrBuilder>
        getContractConfigPOFieldBuilder() {
      if (contractConfigPOBuilder_ == null) {
        contractConfigPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            ContractConfigPO, ContractConfigPO.Builder, ContractConfigPOOrBuilder>(
                getContractConfigPO(),
                getParentForChildren(),
                isClean());
        contractConfigPO_ = null;
      }
      return contractConfigPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:contractConfig.ContractConfigReply)
  }

  // @@protoc_insertion_point(class_scope:contractConfig.ContractConfigReply)
  private static final ContractConfigReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ContractConfigReply();
  }

  public static ContractConfigReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ContractConfigReply>
      PARSER = new com.google.protobuf.AbstractParser<ContractConfigReply>() {
    @Override
    public ContractConfigReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ContractConfigReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ContractConfigReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ContractConfigReply> getParserForType() {
    return PARSER;
  }

  @Override
  public ContractConfigReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

