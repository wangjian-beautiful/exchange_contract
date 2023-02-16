// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoTriggerOrderService.proto

package com.bjs.contract.proto.coTriggerOrder;

/**
 * Protobuf type {@code coTriggerOrder.CoTriggerOrderRequest}
 */
public  final class CoTriggerOrderRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coTriggerOrder.CoTriggerOrderRequest)
    CoTriggerOrderRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoTriggerOrderRequest.newBuilder() to construct.
  private CoTriggerOrderRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoTriggerOrderRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoTriggerOrderRequest(
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

            id_ = input.readInt64();
            break;
          }
          case 18: {
            CoTriggerOrderPO.Builder subBuilder = null;
            if (coTriggerOrderPO_ != null) {
              subBuilder = coTriggerOrderPO_.toBuilder();
            }
            coTriggerOrderPO_ = input.readMessage(CoTriggerOrderPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(coTriggerOrderPO_);
              coTriggerOrderPO_ = subBuilder.buildPartial();
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
    return CoTriggerOrderBizServiceProto.internal_static_coTriggerOrder_CoTriggerOrderRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoTriggerOrderBizServiceProto.internal_static_coTriggerOrder_CoTriggerOrderRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoTriggerOrderRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int COTRIGGERORDERPO_FIELD_NUMBER = 2;
  private CoTriggerOrderPO coTriggerOrderPO_;
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
   */
  public boolean hasCoTriggerOrderPO() {
    return coTriggerOrderPO_ != null;
  }
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
   */
  public CoTriggerOrderPO getCoTriggerOrderPO() {
    return coTriggerOrderPO_ == null ? CoTriggerOrderPO.getDefaultInstance() : coTriggerOrderPO_;
  }
  /**
   * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
   */
  public CoTriggerOrderPOOrBuilder getCoTriggerOrderPOOrBuilder() {
    return getCoTriggerOrderPO();
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
    if (id_ != 0L) {
      output.writeInt64(1, id_);
    }
    if (coTriggerOrderPO_ != null) {
      output.writeMessage(2, getCoTriggerOrderPO());
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    if (coTriggerOrderPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCoTriggerOrderPO());
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
    if (!(obj instanceof CoTriggerOrderRequest)) {
      return super.equals(obj);
    }
    CoTriggerOrderRequest other = (CoTriggerOrderRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (hasCoTriggerOrderPO() != other.hasCoTriggerOrderPO()) return false;
    if (hasCoTriggerOrderPO()) {
      if (!getCoTriggerOrderPO()
          .equals(other.getCoTriggerOrderPO())) return false;
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    if (hasCoTriggerOrderPO()) {
      hash = (37 * hash) + COTRIGGERORDERPO_FIELD_NUMBER;
      hash = (53 * hash) + getCoTriggerOrderPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoTriggerOrderRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoTriggerOrderRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoTriggerOrderRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoTriggerOrderRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoTriggerOrderRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoTriggerOrderRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoTriggerOrderRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoTriggerOrderRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoTriggerOrderRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoTriggerOrderRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoTriggerOrderRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoTriggerOrderRequest parseFrom(
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
  public static Builder newBuilder(CoTriggerOrderRequest prototype) {
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
   * Protobuf type {@code coTriggerOrder.CoTriggerOrderRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coTriggerOrder.CoTriggerOrderRequest)
      CoTriggerOrderRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoTriggerOrderBizServiceProto.internal_static_coTriggerOrder_CoTriggerOrderRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoTriggerOrderBizServiceProto.internal_static_coTriggerOrder_CoTriggerOrderRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoTriggerOrderRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coTriggerOrder.CoTriggerOrderRequest.newBuilder()
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
      id_ = 0L;

      if (coTriggerOrderPOBuilder_ == null) {
        coTriggerOrderPO_ = null;
      } else {
        coTriggerOrderPO_ = null;
        coTriggerOrderPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoTriggerOrderBizServiceProto.internal_static_coTriggerOrder_CoTriggerOrderRequest_descriptor;
    }

    @Override
    public CoTriggerOrderRequest getDefaultInstanceForType() {
      return CoTriggerOrderRequest.getDefaultInstance();
    }

    @Override
    public CoTriggerOrderRequest build() {
      CoTriggerOrderRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoTriggerOrderRequest buildPartial() {
      CoTriggerOrderRequest result = new CoTriggerOrderRequest(this);
      result.id_ = id_;
      if (coTriggerOrderPOBuilder_ == null) {
        result.coTriggerOrderPO_ = coTriggerOrderPO_;
      } else {
        result.coTriggerOrderPO_ = coTriggerOrderPOBuilder_.build();
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
      if (other instanceof CoTriggerOrderRequest) {
        return mergeFrom((CoTriggerOrderRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoTriggerOrderRequest other) {
      if (other == CoTriggerOrderRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.hasCoTriggerOrderPO()) {
        mergeCoTriggerOrderPO(other.getCoTriggerOrderPO());
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
      CoTriggerOrderRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoTriggerOrderRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long id_ ;
    /**
     * <code>int64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <code>int64 id = 1;</code>
     */
    public Builder setId(long value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0L;
      onChanged();
      return this;
    }

    private CoTriggerOrderPO coTriggerOrderPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        CoTriggerOrderPO, CoTriggerOrderPO.Builder, CoTriggerOrderPOOrBuilder> coTriggerOrderPOBuilder_;
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public boolean hasCoTriggerOrderPO() {
      return coTriggerOrderPOBuilder_ != null || coTriggerOrderPO_ != null;
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public CoTriggerOrderPO getCoTriggerOrderPO() {
      if (coTriggerOrderPOBuilder_ == null) {
        return coTriggerOrderPO_ == null ? CoTriggerOrderPO.getDefaultInstance() : coTriggerOrderPO_;
      } else {
        return coTriggerOrderPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public Builder setCoTriggerOrderPO(CoTriggerOrderPO value) {
      if (coTriggerOrderPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        coTriggerOrderPO_ = value;
        onChanged();
      } else {
        coTriggerOrderPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public Builder setCoTriggerOrderPO(
        CoTriggerOrderPO.Builder builderForValue) {
      if (coTriggerOrderPOBuilder_ == null) {
        coTriggerOrderPO_ = builderForValue.build();
        onChanged();
      } else {
        coTriggerOrderPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public Builder mergeCoTriggerOrderPO(CoTriggerOrderPO value) {
      if (coTriggerOrderPOBuilder_ == null) {
        if (coTriggerOrderPO_ != null) {
          coTriggerOrderPO_ =
            CoTriggerOrderPO.newBuilder(coTriggerOrderPO_).mergeFrom(value).buildPartial();
        } else {
          coTriggerOrderPO_ = value;
        }
        onChanged();
      } else {
        coTriggerOrderPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public Builder clearCoTriggerOrderPO() {
      if (coTriggerOrderPOBuilder_ == null) {
        coTriggerOrderPO_ = null;
        onChanged();
      } else {
        coTriggerOrderPO_ = null;
        coTriggerOrderPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public CoTriggerOrderPO.Builder getCoTriggerOrderPOBuilder() {
      
      onChanged();
      return getCoTriggerOrderPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    public CoTriggerOrderPOOrBuilder getCoTriggerOrderPOOrBuilder() {
      if (coTriggerOrderPOBuilder_ != null) {
        return coTriggerOrderPOBuilder_.getMessageOrBuilder();
      } else {
        return coTriggerOrderPO_ == null ?
            CoTriggerOrderPO.getDefaultInstance() : coTriggerOrderPO_;
      }
    }
    /**
     * <code>.coTriggerOrder.CoTriggerOrderPO coTriggerOrderPO = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        CoTriggerOrderPO, CoTriggerOrderPO.Builder, CoTriggerOrderPOOrBuilder>
        getCoTriggerOrderPOFieldBuilder() {
      if (coTriggerOrderPOBuilder_ == null) {
        coTriggerOrderPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            CoTriggerOrderPO, CoTriggerOrderPO.Builder, CoTriggerOrderPOOrBuilder>(
                getCoTriggerOrderPO(),
                getParentForChildren(),
                isClean());
        coTriggerOrderPO_ = null;
      }
      return coTriggerOrderPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:coTriggerOrder.CoTriggerOrderRequest)
  }

  // @@protoc_insertion_point(class_scope:coTriggerOrder.CoTriggerOrderRequest)
  private static final CoTriggerOrderRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoTriggerOrderRequest();
  }

  public static CoTriggerOrderRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoTriggerOrderRequest>
      PARSER = new com.google.protobuf.AbstractParser<CoTriggerOrderRequest>() {
    @Override
    public CoTriggerOrderRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoTriggerOrderRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoTriggerOrderRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoTriggerOrderRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CoTriggerOrderRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
