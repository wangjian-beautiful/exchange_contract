// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MaintenanceMarginRateService.proto

package com.bjs.contract.proto.maintenanceMarginRate;

/**
 * Protobuf type {@code maintenanceMarginRate.MaintenanceMarginRateReply}
 */
public  final class MaintenanceMarginRateReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:maintenanceMarginRate.MaintenanceMarginRateReply)
    MaintenanceMarginRateReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MaintenanceMarginRateReply.newBuilder() to construct.
  private MaintenanceMarginRateReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MaintenanceMarginRateReply() {
    message_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MaintenanceMarginRateReply(
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
            MaintenanceMarginRatePO.Builder subBuilder = null;
            if (maintenanceMarginRatePO_ != null) {
              subBuilder = maintenanceMarginRatePO_.toBuilder();
            }
            maintenanceMarginRatePO_ = input.readMessage(MaintenanceMarginRatePO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(maintenanceMarginRatePO_);
              maintenanceMarginRatePO_ = subBuilder.buildPartial();
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
    return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            MaintenanceMarginRateReply.class, Builder.class);
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

  public static final int MAINTENANCEMARGINRATEPO_FIELD_NUMBER = 3;
  private MaintenanceMarginRatePO maintenanceMarginRatePO_;
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
   */
  public boolean hasMaintenanceMarginRatePO() {
    return maintenanceMarginRatePO_ != null;
  }
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
   */
  public MaintenanceMarginRatePO getMaintenanceMarginRatePO() {
    return maintenanceMarginRatePO_ == null ? MaintenanceMarginRatePO.getDefaultInstance() : maintenanceMarginRatePO_;
  }
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
   */
  public MaintenanceMarginRatePOOrBuilder getMaintenanceMarginRatePOOrBuilder() {
    return getMaintenanceMarginRatePO();
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
    if (maintenanceMarginRatePO_ != null) {
      output.writeMessage(3, getMaintenanceMarginRatePO());
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
    if (maintenanceMarginRatePO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getMaintenanceMarginRatePO());
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
    if (!(obj instanceof MaintenanceMarginRateReply)) {
      return super.equals(obj);
    }
    MaintenanceMarginRateReply other = (MaintenanceMarginRateReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (hasMaintenanceMarginRatePO() != other.hasMaintenanceMarginRatePO()) return false;
    if (hasMaintenanceMarginRatePO()) {
      if (!getMaintenanceMarginRatePO()
          .equals(other.getMaintenanceMarginRatePO())) return false;
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
    if (hasMaintenanceMarginRatePO()) {
      hash = (37 * hash) + MAINTENANCEMARGINRATEPO_FIELD_NUMBER;
      hash = (53 * hash) + getMaintenanceMarginRatePO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static MaintenanceMarginRateReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MaintenanceMarginRateReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MaintenanceMarginRateReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MaintenanceMarginRateReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MaintenanceMarginRateReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MaintenanceMarginRateReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MaintenanceMarginRateReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MaintenanceMarginRateReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static MaintenanceMarginRateReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static MaintenanceMarginRateReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static MaintenanceMarginRateReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MaintenanceMarginRateReply parseFrom(
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
  public static Builder newBuilder(MaintenanceMarginRateReply prototype) {
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
   * Protobuf type {@code maintenanceMarginRate.MaintenanceMarginRateReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:maintenanceMarginRate.MaintenanceMarginRateReply)
      MaintenanceMarginRateReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              MaintenanceMarginRateReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRateReply.newBuilder()
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

      if (maintenanceMarginRatePOBuilder_ == null) {
        maintenanceMarginRatePO_ = null;
      } else {
        maintenanceMarginRatePO_ = null;
        maintenanceMarginRatePOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRateReply_descriptor;
    }

    @Override
    public MaintenanceMarginRateReply getDefaultInstanceForType() {
      return MaintenanceMarginRateReply.getDefaultInstance();
    }

    @Override
    public MaintenanceMarginRateReply build() {
      MaintenanceMarginRateReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public MaintenanceMarginRateReply buildPartial() {
      MaintenanceMarginRateReply result = new MaintenanceMarginRateReply(this);
      result.status_ = status_;
      result.message_ = message_;
      if (maintenanceMarginRatePOBuilder_ == null) {
        result.maintenanceMarginRatePO_ = maintenanceMarginRatePO_;
      } else {
        result.maintenanceMarginRatePO_ = maintenanceMarginRatePOBuilder_.build();
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
      if (other instanceof MaintenanceMarginRateReply) {
        return mergeFrom((MaintenanceMarginRateReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(MaintenanceMarginRateReply other) {
      if (other == MaintenanceMarginRateReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (other.hasMaintenanceMarginRatePO()) {
        mergeMaintenanceMarginRatePO(other.getMaintenanceMarginRatePO());
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
      MaintenanceMarginRateReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (MaintenanceMarginRateReply) e.getUnfinishedMessage();
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

    private MaintenanceMarginRatePO maintenanceMarginRatePO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder> maintenanceMarginRatePOBuilder_;
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public boolean hasMaintenanceMarginRatePO() {
      return maintenanceMarginRatePOBuilder_ != null || maintenanceMarginRatePO_ != null;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public MaintenanceMarginRatePO getMaintenanceMarginRatePO() {
      if (maintenanceMarginRatePOBuilder_ == null) {
        return maintenanceMarginRatePO_ == null ? MaintenanceMarginRatePO.getDefaultInstance() : maintenanceMarginRatePO_;
      } else {
        return maintenanceMarginRatePOBuilder_.getMessage();
      }
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public Builder setMaintenanceMarginRatePO(MaintenanceMarginRatePO value) {
      if (maintenanceMarginRatePOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        maintenanceMarginRatePO_ = value;
        onChanged();
      } else {
        maintenanceMarginRatePOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public Builder setMaintenanceMarginRatePO(
        MaintenanceMarginRatePO.Builder builderForValue) {
      if (maintenanceMarginRatePOBuilder_ == null) {
        maintenanceMarginRatePO_ = builderForValue.build();
        onChanged();
      } else {
        maintenanceMarginRatePOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public Builder mergeMaintenanceMarginRatePO(MaintenanceMarginRatePO value) {
      if (maintenanceMarginRatePOBuilder_ == null) {
        if (maintenanceMarginRatePO_ != null) {
          maintenanceMarginRatePO_ =
            MaintenanceMarginRatePO.newBuilder(maintenanceMarginRatePO_).mergeFrom(value).buildPartial();
        } else {
          maintenanceMarginRatePO_ = value;
        }
        onChanged();
      } else {
        maintenanceMarginRatePOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public Builder clearMaintenanceMarginRatePO() {
      if (maintenanceMarginRatePOBuilder_ == null) {
        maintenanceMarginRatePO_ = null;
        onChanged();
      } else {
        maintenanceMarginRatePO_ = null;
        maintenanceMarginRatePOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public MaintenanceMarginRatePO.Builder getMaintenanceMarginRatePOBuilder() {
      
      onChanged();
      return getMaintenanceMarginRatePOFieldBuilder().getBuilder();
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    public MaintenanceMarginRatePOOrBuilder getMaintenanceMarginRatePOOrBuilder() {
      if (maintenanceMarginRatePOBuilder_ != null) {
        return maintenanceMarginRatePOBuilder_.getMessageOrBuilder();
      } else {
        return maintenanceMarginRatePO_ == null ?
            MaintenanceMarginRatePO.getDefaultInstance() : maintenanceMarginRatePO_;
      }
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO maintenanceMarginRatePO = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder>
        getMaintenanceMarginRatePOFieldBuilder() {
      if (maintenanceMarginRatePOBuilder_ == null) {
        maintenanceMarginRatePOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder>(
                getMaintenanceMarginRatePO(),
                getParentForChildren(),
                isClean());
        maintenanceMarginRatePO_ = null;
      }
      return maintenanceMarginRatePOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:maintenanceMarginRate.MaintenanceMarginRateReply)
  }

  // @@protoc_insertion_point(class_scope:maintenanceMarginRate.MaintenanceMarginRateReply)
  private static final MaintenanceMarginRateReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new MaintenanceMarginRateReply();
  }

  public static MaintenanceMarginRateReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MaintenanceMarginRateReply>
      PARSER = new com.google.protobuf.AbstractParser<MaintenanceMarginRateReply>() {
    @Override
    public MaintenanceMarginRateReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MaintenanceMarginRateReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MaintenanceMarginRateReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<MaintenanceMarginRateReply> getParserForType() {
    return PARSER;
  }

  @Override
  public MaintenanceMarginRateReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

