// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderService.proto

package com.bjs.contract.proto.coPositionOrder;

/**
 * Protobuf type {@code coPositionOrder.CoPositionOrderRequest}
 */
public  final class CoPositionOrderRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coPositionOrder.CoPositionOrderRequest)
    CoPositionOrderRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoPositionOrderRequest.newBuilder() to construct.
  private CoPositionOrderRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoPositionOrderRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoPositionOrderRequest(
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
            CoPositionOrderPO.Builder subBuilder = null;
            if (coPositionOrderPO_ != null) {
              subBuilder = coPositionOrderPO_.toBuilder();
            }
            coPositionOrderPO_ = input.readMessage(CoPositionOrderPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(coPositionOrderPO_);
              coPositionOrderPO_ = subBuilder.buildPartial();
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
    return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_CoPositionOrderRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_CoPositionOrderRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoPositionOrderRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int COPOSITIONORDERPO_FIELD_NUMBER = 2;
  private CoPositionOrderPO coPositionOrderPO_;
  /**
   * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
   */
  public boolean hasCoPositionOrderPO() {
    return coPositionOrderPO_ != null;
  }
  /**
   * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
   */
  public CoPositionOrderPO getCoPositionOrderPO() {
    return coPositionOrderPO_ == null ? CoPositionOrderPO.getDefaultInstance() : coPositionOrderPO_;
  }
  /**
   * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
   */
  public CoPositionOrderPOOrBuilder getCoPositionOrderPOOrBuilder() {
    return getCoPositionOrderPO();
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
    if (coPositionOrderPO_ != null) {
      output.writeMessage(2, getCoPositionOrderPO());
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
    if (coPositionOrderPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCoPositionOrderPO());
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
    if (!(obj instanceof CoPositionOrderRequest)) {
      return super.equals(obj);
    }
    CoPositionOrderRequest other = (CoPositionOrderRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (hasCoPositionOrderPO() != other.hasCoPositionOrderPO()) return false;
    if (hasCoPositionOrderPO()) {
      if (!getCoPositionOrderPO()
          .equals(other.getCoPositionOrderPO())) return false;
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
    if (hasCoPositionOrderPO()) {
      hash = (37 * hash) + COPOSITIONORDERPO_FIELD_NUMBER;
      hash = (53 * hash) + getCoPositionOrderPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoPositionOrderRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoPositionOrderRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoPositionOrderRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoPositionOrderRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoPositionOrderRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoPositionOrderRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoPositionOrderRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoPositionOrderRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoPositionOrderRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoPositionOrderRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoPositionOrderRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoPositionOrderRequest parseFrom(
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
  public static Builder newBuilder(CoPositionOrderRequest prototype) {
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
   * Protobuf type {@code coPositionOrder.CoPositionOrderRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coPositionOrder.CoPositionOrderRequest)
      CoPositionOrderRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_CoPositionOrderRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_CoPositionOrderRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoPositionOrderRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coPositionOrder.CoPositionOrderRequest.newBuilder()
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

      if (coPositionOrderPOBuilder_ == null) {
        coPositionOrderPO_ = null;
      } else {
        coPositionOrderPO_ = null;
        coPositionOrderPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_CoPositionOrderRequest_descriptor;
    }

    @Override
    public CoPositionOrderRequest getDefaultInstanceForType() {
      return CoPositionOrderRequest.getDefaultInstance();
    }

    @Override
    public CoPositionOrderRequest build() {
      CoPositionOrderRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoPositionOrderRequest buildPartial() {
      CoPositionOrderRequest result = new CoPositionOrderRequest(this);
      result.id_ = id_;
      if (coPositionOrderPOBuilder_ == null) {
        result.coPositionOrderPO_ = coPositionOrderPO_;
      } else {
        result.coPositionOrderPO_ = coPositionOrderPOBuilder_.build();
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
      if (other instanceof CoPositionOrderRequest) {
        return mergeFrom((CoPositionOrderRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoPositionOrderRequest other) {
      if (other == CoPositionOrderRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.hasCoPositionOrderPO()) {
        mergeCoPositionOrderPO(other.getCoPositionOrderPO());
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
      CoPositionOrderRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoPositionOrderRequest) e.getUnfinishedMessage();
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

    private CoPositionOrderPO coPositionOrderPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        CoPositionOrderPO, CoPositionOrderPO.Builder, CoPositionOrderPOOrBuilder> coPositionOrderPOBuilder_;
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public boolean hasCoPositionOrderPO() {
      return coPositionOrderPOBuilder_ != null || coPositionOrderPO_ != null;
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public CoPositionOrderPO getCoPositionOrderPO() {
      if (coPositionOrderPOBuilder_ == null) {
        return coPositionOrderPO_ == null ? CoPositionOrderPO.getDefaultInstance() : coPositionOrderPO_;
      } else {
        return coPositionOrderPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public Builder setCoPositionOrderPO(CoPositionOrderPO value) {
      if (coPositionOrderPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        coPositionOrderPO_ = value;
        onChanged();
      } else {
        coPositionOrderPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public Builder setCoPositionOrderPO(
        CoPositionOrderPO.Builder builderForValue) {
      if (coPositionOrderPOBuilder_ == null) {
        coPositionOrderPO_ = builderForValue.build();
        onChanged();
      } else {
        coPositionOrderPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public Builder mergeCoPositionOrderPO(CoPositionOrderPO value) {
      if (coPositionOrderPOBuilder_ == null) {
        if (coPositionOrderPO_ != null) {
          coPositionOrderPO_ =
            CoPositionOrderPO.newBuilder(coPositionOrderPO_).mergeFrom(value).buildPartial();
        } else {
          coPositionOrderPO_ = value;
        }
        onChanged();
      } else {
        coPositionOrderPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public Builder clearCoPositionOrderPO() {
      if (coPositionOrderPOBuilder_ == null) {
        coPositionOrderPO_ = null;
        onChanged();
      } else {
        coPositionOrderPO_ = null;
        coPositionOrderPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public CoPositionOrderPO.Builder getCoPositionOrderPOBuilder() {
      
      onChanged();
      return getCoPositionOrderPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    public CoPositionOrderPOOrBuilder getCoPositionOrderPOOrBuilder() {
      if (coPositionOrderPOBuilder_ != null) {
        return coPositionOrderPOBuilder_.getMessageOrBuilder();
      } else {
        return coPositionOrderPO_ == null ?
            CoPositionOrderPO.getDefaultInstance() : coPositionOrderPO_;
      }
    }
    /**
     * <code>.coPositionOrder.CoPositionOrderPO coPositionOrderPO = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        CoPositionOrderPO, CoPositionOrderPO.Builder, CoPositionOrderPOOrBuilder>
        getCoPositionOrderPOFieldBuilder() {
      if (coPositionOrderPOBuilder_ == null) {
        coPositionOrderPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            CoPositionOrderPO, CoPositionOrderPO.Builder, CoPositionOrderPOOrBuilder>(
                getCoPositionOrderPO(),
                getParentForChildren(),
                isClean());
        coPositionOrderPO_ = null;
      }
      return coPositionOrderPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:coPositionOrder.CoPositionOrderRequest)
  }

  // @@protoc_insertion_point(class_scope:coPositionOrder.CoPositionOrderRequest)
  private static final CoPositionOrderRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoPositionOrderRequest();
  }

  public static CoPositionOrderRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoPositionOrderRequest>
      PARSER = new com.google.protobuf.AbstractParser<CoPositionOrderRequest>() {
    @Override
    public CoPositionOrderRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoPositionOrderRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoPositionOrderRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoPositionOrderRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CoPositionOrderRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
