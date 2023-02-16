// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

/**
 * Protobuf type {@code coOrder.CoOrderRequest}
 */
public  final class CoOrderRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coOrder.CoOrderRequest)
    CoOrderRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoOrderRequest.newBuilder() to construct.
  private CoOrderRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoOrderRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoOrderRequest(
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
            CoOrderPO.Builder subBuilder = null;
            if (coOrderPO_ != null) {
              subBuilder = coOrderPO_.toBuilder();
            }
            coOrderPO_ = input.readMessage(CoOrderPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(coOrderPO_);
              coOrderPO_ = subBuilder.buildPartial();
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
    return CoOrderBizServiceProto.internal_static_coOrder_CoOrderRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoOrderBizServiceProto.internal_static_coOrder_CoOrderRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoOrderRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int COORDERPO_FIELD_NUMBER = 2;
  private CoOrderPO coOrderPO_;
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
   */
  public boolean hasCoOrderPO() {
    return coOrderPO_ != null;
  }
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
   */
  public CoOrderPO getCoOrderPO() {
    return coOrderPO_ == null ? CoOrderPO.getDefaultInstance() : coOrderPO_;
  }
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
   */
  public CoOrderPOOrBuilder getCoOrderPOOrBuilder() {
    return getCoOrderPO();
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
    if (coOrderPO_ != null) {
      output.writeMessage(2, getCoOrderPO());
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
    if (coOrderPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCoOrderPO());
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
    if (!(obj instanceof CoOrderRequest)) {
      return super.equals(obj);
    }
    CoOrderRequest other = (CoOrderRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (hasCoOrderPO() != other.hasCoOrderPO()) return false;
    if (hasCoOrderPO()) {
      if (!getCoOrderPO()
          .equals(other.getCoOrderPO())) return false;
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
    if (hasCoOrderPO()) {
      hash = (37 * hash) + COORDERPO_FIELD_NUMBER;
      hash = (53 * hash) + getCoOrderPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoOrderRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoOrderRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoOrderRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoOrderRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoOrderRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoOrderRequest parseFrom(
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
  public static Builder newBuilder(CoOrderRequest prototype) {
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
   * Protobuf type {@code coOrder.CoOrderRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coOrder.CoOrderRequest)
      CoOrderRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoOrderRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coOrder.CoOrderRequest.newBuilder()
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

      if (coOrderPOBuilder_ == null) {
        coOrderPO_ = null;
      } else {
        coOrderPO_ = null;
        coOrderPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderRequest_descriptor;
    }

    @Override
    public CoOrderRequest getDefaultInstanceForType() {
      return CoOrderRequest.getDefaultInstance();
    }

    @Override
    public CoOrderRequest build() {
      CoOrderRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoOrderRequest buildPartial() {
      CoOrderRequest result = new CoOrderRequest(this);
      result.id_ = id_;
      if (coOrderPOBuilder_ == null) {
        result.coOrderPO_ = coOrderPO_;
      } else {
        result.coOrderPO_ = coOrderPOBuilder_.build();
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
      if (other instanceof CoOrderRequest) {
        return mergeFrom((CoOrderRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoOrderRequest other) {
      if (other == CoOrderRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.hasCoOrderPO()) {
        mergeCoOrderPO(other.getCoOrderPO());
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
      CoOrderRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoOrderRequest) e.getUnfinishedMessage();
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

    private CoOrderPO coOrderPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        CoOrderPO, CoOrderPO.Builder, CoOrderPOOrBuilder> coOrderPOBuilder_;
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public boolean hasCoOrderPO() {
      return coOrderPOBuilder_ != null || coOrderPO_ != null;
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public CoOrderPO getCoOrderPO() {
      if (coOrderPOBuilder_ == null) {
        return coOrderPO_ == null ? CoOrderPO.getDefaultInstance() : coOrderPO_;
      } else {
        return coOrderPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public Builder setCoOrderPO(CoOrderPO value) {
      if (coOrderPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        coOrderPO_ = value;
        onChanged();
      } else {
        coOrderPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public Builder setCoOrderPO(
        CoOrderPO.Builder builderForValue) {
      if (coOrderPOBuilder_ == null) {
        coOrderPO_ = builderForValue.build();
        onChanged();
      } else {
        coOrderPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public Builder mergeCoOrderPO(CoOrderPO value) {
      if (coOrderPOBuilder_ == null) {
        if (coOrderPO_ != null) {
          coOrderPO_ =
            CoOrderPO.newBuilder(coOrderPO_).mergeFrom(value).buildPartial();
        } else {
          coOrderPO_ = value;
        }
        onChanged();
      } else {
        coOrderPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public Builder clearCoOrderPO() {
      if (coOrderPOBuilder_ == null) {
        coOrderPO_ = null;
        onChanged();
      } else {
        coOrderPO_ = null;
        coOrderPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public CoOrderPO.Builder getCoOrderPOBuilder() {
      
      onChanged();
      return getCoOrderPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    public CoOrderPOOrBuilder getCoOrderPOOrBuilder() {
      if (coOrderPOBuilder_ != null) {
        return coOrderPOBuilder_.getMessageOrBuilder();
      } else {
        return coOrderPO_ == null ?
            CoOrderPO.getDefaultInstance() : coOrderPO_;
      }
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        CoOrderPO, CoOrderPO.Builder, CoOrderPOOrBuilder>
        getCoOrderPOFieldBuilder() {
      if (coOrderPOBuilder_ == null) {
        coOrderPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            CoOrderPO, CoOrderPO.Builder, CoOrderPOOrBuilder>(
                getCoOrderPO(),
                getParentForChildren(),
                isClean());
        coOrderPO_ = null;
      }
      return coOrderPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:coOrder.CoOrderRequest)
  }

  // @@protoc_insertion_point(class_scope:coOrder.CoOrderRequest)
  private static final CoOrderRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoOrderRequest();
  }

  public static CoOrderRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoOrderRequest>
      PARSER = new com.google.protobuf.AbstractParser<CoOrderRequest>() {
    @Override
    public CoOrderRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoOrderRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoOrderRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoOrderRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CoOrderRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

