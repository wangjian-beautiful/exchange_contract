// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConfigSymbolMatchingService.proto

package com.bjs.contract.proto.configSymbolMatching;

/**
 * Protobuf type {@code configSymbolMatching.ConfigSymbolMatchingReply}
 */
public  final class ConfigSymbolMatchingReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:configSymbolMatching.ConfigSymbolMatchingReply)
    ConfigSymbolMatchingReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ConfigSymbolMatchingReply.newBuilder() to construct.
  private ConfigSymbolMatchingReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ConfigSymbolMatchingReply() {
    message_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ConfigSymbolMatchingReply(
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
            ConfigSymbolMatchingPO.Builder subBuilder = null;
            if (configSymbolMatchingPO_ != null) {
              subBuilder = configSymbolMatchingPO_.toBuilder();
            }
            configSymbolMatchingPO_ = input.readMessage(ConfigSymbolMatchingPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(configSymbolMatchingPO_);
              configSymbolMatchingPO_ = subBuilder.buildPartial();
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
    return ConfigSymbolMatchingBizServiceProto.internal_static_configSymbolMatching_ConfigSymbolMatchingReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return ConfigSymbolMatchingBizServiceProto.internal_static_configSymbolMatching_ConfigSymbolMatchingReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            ConfigSymbolMatchingReply.class, Builder.class);
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

  public static final int CONFIGSYMBOLMATCHINGPO_FIELD_NUMBER = 3;
  private ConfigSymbolMatchingPO configSymbolMatchingPO_;
  /**
   * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
   */
  public boolean hasConfigSymbolMatchingPO() {
    return configSymbolMatchingPO_ != null;
  }
  /**
   * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
   */
  public ConfigSymbolMatchingPO getConfigSymbolMatchingPO() {
    return configSymbolMatchingPO_ == null ? ConfigSymbolMatchingPO.getDefaultInstance() : configSymbolMatchingPO_;
  }
  /**
   * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
   */
  public ConfigSymbolMatchingPOOrBuilder getConfigSymbolMatchingPOOrBuilder() {
    return getConfigSymbolMatchingPO();
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
    if (configSymbolMatchingPO_ != null) {
      output.writeMessage(3, getConfigSymbolMatchingPO());
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
    if (configSymbolMatchingPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getConfigSymbolMatchingPO());
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
    if (!(obj instanceof ConfigSymbolMatchingReply)) {
      return super.equals(obj);
    }
    ConfigSymbolMatchingReply other = (ConfigSymbolMatchingReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (hasConfigSymbolMatchingPO() != other.hasConfigSymbolMatchingPO()) return false;
    if (hasConfigSymbolMatchingPO()) {
      if (!getConfigSymbolMatchingPO()
          .equals(other.getConfigSymbolMatchingPO())) return false;
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
    if (hasConfigSymbolMatchingPO()) {
      hash = (37 * hash) + CONFIGSYMBOLMATCHINGPO_FIELD_NUMBER;
      hash = (53 * hash) + getConfigSymbolMatchingPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static ConfigSymbolMatchingReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ConfigSymbolMatchingReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ConfigSymbolMatchingReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ConfigSymbolMatchingReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ConfigSymbolMatchingReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static ConfigSymbolMatchingReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static ConfigSymbolMatchingReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ConfigSymbolMatchingReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static ConfigSymbolMatchingReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static ConfigSymbolMatchingReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static ConfigSymbolMatchingReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static ConfigSymbolMatchingReply parseFrom(
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
  public static Builder newBuilder(ConfigSymbolMatchingReply prototype) {
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
   * Protobuf type {@code configSymbolMatching.ConfigSymbolMatchingReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:configSymbolMatching.ConfigSymbolMatchingReply)
      ConfigSymbolMatchingReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return ConfigSymbolMatchingBizServiceProto.internal_static_configSymbolMatching_ConfigSymbolMatchingReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return ConfigSymbolMatchingBizServiceProto.internal_static_configSymbolMatching_ConfigSymbolMatchingReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              ConfigSymbolMatchingReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingReply.newBuilder()
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

      if (configSymbolMatchingPOBuilder_ == null) {
        configSymbolMatchingPO_ = null;
      } else {
        configSymbolMatchingPO_ = null;
        configSymbolMatchingPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return ConfigSymbolMatchingBizServiceProto.internal_static_configSymbolMatching_ConfigSymbolMatchingReply_descriptor;
    }

    @Override
    public ConfigSymbolMatchingReply getDefaultInstanceForType() {
      return ConfigSymbolMatchingReply.getDefaultInstance();
    }

    @Override
    public ConfigSymbolMatchingReply build() {
      ConfigSymbolMatchingReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public ConfigSymbolMatchingReply buildPartial() {
      ConfigSymbolMatchingReply result = new ConfigSymbolMatchingReply(this);
      result.status_ = status_;
      result.message_ = message_;
      if (configSymbolMatchingPOBuilder_ == null) {
        result.configSymbolMatchingPO_ = configSymbolMatchingPO_;
      } else {
        result.configSymbolMatchingPO_ = configSymbolMatchingPOBuilder_.build();
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
      if (other instanceof ConfigSymbolMatchingReply) {
        return mergeFrom((ConfigSymbolMatchingReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(ConfigSymbolMatchingReply other) {
      if (other == ConfigSymbolMatchingReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.hasConfigSymbolMatchingPO()) {
        mergeConfigSymbolMatchingPO(other.getConfigSymbolMatchingPO());
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
      ConfigSymbolMatchingReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (ConfigSymbolMatchingReply) e.getUnfinishedMessage();
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

    private ConfigSymbolMatchingPO configSymbolMatchingPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        ConfigSymbolMatchingPO, ConfigSymbolMatchingPO.Builder, ConfigSymbolMatchingPOOrBuilder> configSymbolMatchingPOBuilder_;
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public boolean hasConfigSymbolMatchingPO() {
      return configSymbolMatchingPOBuilder_ != null || configSymbolMatchingPO_ != null;
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public ConfigSymbolMatchingPO getConfigSymbolMatchingPO() {
      if (configSymbolMatchingPOBuilder_ == null) {
        return configSymbolMatchingPO_ == null ? ConfigSymbolMatchingPO.getDefaultInstance() : configSymbolMatchingPO_;
      } else {
        return configSymbolMatchingPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public Builder setConfigSymbolMatchingPO(ConfigSymbolMatchingPO value) {
      if (configSymbolMatchingPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        configSymbolMatchingPO_ = value;
        onChanged();
      } else {
        configSymbolMatchingPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public Builder setConfigSymbolMatchingPO(
        ConfigSymbolMatchingPO.Builder builderForValue) {
      if (configSymbolMatchingPOBuilder_ == null) {
        configSymbolMatchingPO_ = builderForValue.build();
        onChanged();
      } else {
        configSymbolMatchingPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public Builder mergeConfigSymbolMatchingPO(ConfigSymbolMatchingPO value) {
      if (configSymbolMatchingPOBuilder_ == null) {
        if (configSymbolMatchingPO_ != null) {
          configSymbolMatchingPO_ =
            ConfigSymbolMatchingPO.newBuilder(configSymbolMatchingPO_).mergeFrom(value).buildPartial();
        } else {
          configSymbolMatchingPO_ = value;
        }
        onChanged();
      } else {
        configSymbolMatchingPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public Builder clearConfigSymbolMatchingPO() {
      if (configSymbolMatchingPOBuilder_ == null) {
        configSymbolMatchingPO_ = null;
        onChanged();
      } else {
        configSymbolMatchingPO_ = null;
        configSymbolMatchingPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public ConfigSymbolMatchingPO.Builder getConfigSymbolMatchingPOBuilder() {
      
      onChanged();
      return getConfigSymbolMatchingPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    public ConfigSymbolMatchingPOOrBuilder getConfigSymbolMatchingPOOrBuilder() {
      if (configSymbolMatchingPOBuilder_ != null) {
        return configSymbolMatchingPOBuilder_.getMessageOrBuilder();
      } else {
        return configSymbolMatchingPO_ == null ?
            ConfigSymbolMatchingPO.getDefaultInstance() : configSymbolMatchingPO_;
      }
    }
    /**
     * <code>.configSymbolMatching.ConfigSymbolMatchingPO configSymbolMatchingPO = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        ConfigSymbolMatchingPO, ConfigSymbolMatchingPO.Builder, ConfigSymbolMatchingPOOrBuilder>
        getConfigSymbolMatchingPOFieldBuilder() {
      if (configSymbolMatchingPOBuilder_ == null) {
        configSymbolMatchingPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            ConfigSymbolMatchingPO, ConfigSymbolMatchingPO.Builder, ConfigSymbolMatchingPOOrBuilder>(
                getConfigSymbolMatchingPO(),
                getParentForChildren(),
                isClean());
        configSymbolMatchingPO_ = null;
      }
      return configSymbolMatchingPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:configSymbolMatching.ConfigSymbolMatchingReply)
  }

  // @@protoc_insertion_point(class_scope:configSymbolMatching.ConfigSymbolMatchingReply)
  private static final ConfigSymbolMatchingReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new ConfigSymbolMatchingReply();
  }

  public static ConfigSymbolMatchingReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ConfigSymbolMatchingReply>
      PARSER = new com.google.protobuf.AbstractParser<ConfigSymbolMatchingReply>() {
    @Override
    public ConfigSymbolMatchingReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ConfigSymbolMatchingReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ConfigSymbolMatchingReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ConfigSymbolMatchingReply> getParserForType() {
    return PARSER;
  }

  @Override
  public ConfigSymbolMatchingReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
