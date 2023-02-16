// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

/**
 * Protobuf type {@code coOrder.CoOrderPageRequest}
 */
public  final class CoOrderPageRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coOrder.CoOrderPageRequest)
    CoOrderPageRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoOrderPageRequest.newBuilder() to construct.
  private CoOrderPageRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoOrderPageRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoOrderPageRequest(
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

            page_ = input.readInt32();
            break;
          }
          case 16: {

            size_ = input.readInt32();
            break;
          }
          case 26: {
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
    return CoOrderBizServiceProto.internal_static_coOrder_CoOrderPageRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoOrderBizServiceProto.internal_static_coOrder_CoOrderPageRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoOrderPageRequest.class, Builder.class);
  }

  public static final int PAGE_FIELD_NUMBER = 1;
  private int page_;
  /**
   * <code>int32 page = 1;</code>
   */
  public int getPage() {
    return page_;
  }

  public static final int SIZE_FIELD_NUMBER = 2;
  private int size_;
  /**
   * <code>int32 size = 2;</code>
   */
  public int getSize() {
    return size_;
  }

  public static final int COORDERPO_FIELD_NUMBER = 3;
  private CoOrderPO coOrderPO_;
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
   */
  public boolean hasCoOrderPO() {
    return coOrderPO_ != null;
  }
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
   */
  public CoOrderPO getCoOrderPO() {
    return coOrderPO_ == null ? CoOrderPO.getDefaultInstance() : coOrderPO_;
  }
  /**
   * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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
    if (page_ != 0) {
      output.writeInt32(1, page_);
    }
    if (size_ != 0) {
      output.writeInt32(2, size_);
    }
    if (coOrderPO_ != null) {
      output.writeMessage(3, getCoOrderPO());
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (page_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, page_);
    }
    if (size_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, size_);
    }
    if (coOrderPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, getCoOrderPO());
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
    if (!(obj instanceof CoOrderPageRequest)) {
      return super.equals(obj);
    }
    CoOrderPageRequest other = (CoOrderPageRequest) obj;

    if (getPage()
        != other.getPage()) return false;
    if (getSize()
        != other.getSize()) return false;
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
    hash = (37 * hash) + PAGE_FIELD_NUMBER;
    hash = (53 * hash) + getPage();
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getSize();
    if (hasCoOrderPO()) {
      hash = (37 * hash) + COORDERPO_FIELD_NUMBER;
      hash = (53 * hash) + getCoOrderPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoOrderPageRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderPageRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderPageRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderPageRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderPageRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderPageRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderPageRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoOrderPageRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoOrderPageRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoOrderPageRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoOrderPageRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoOrderPageRequest parseFrom(
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
  public static Builder newBuilder(CoOrderPageRequest prototype) {
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
   * Protobuf type {@code coOrder.CoOrderPageRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coOrder.CoOrderPageRequest)
      CoOrderPageRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderPageRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderPageRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoOrderPageRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coOrder.CoOrderPageRequest.newBuilder()
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
      page_ = 0;

      size_ = 0;

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
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderPageRequest_descriptor;
    }

    @Override
    public CoOrderPageRequest getDefaultInstanceForType() {
      return CoOrderPageRequest.getDefaultInstance();
    }

    @Override
    public CoOrderPageRequest build() {
      CoOrderPageRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoOrderPageRequest buildPartial() {
      CoOrderPageRequest result = new CoOrderPageRequest(this);
      result.page_ = page_;
      result.size_ = size_;
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
      if (other instanceof CoOrderPageRequest) {
        return mergeFrom((CoOrderPageRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoOrderPageRequest other) {
      if (other == CoOrderPageRequest.getDefaultInstance()) return this;
      if (other.getPage() != 0) {
        setPage(other.getPage());
      }
      if (other.getSize() != 0) {
        setSize(other.getSize());
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
      CoOrderPageRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoOrderPageRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int page_ ;
    /**
     * <code>int32 page = 1;</code>
     */
    public int getPage() {
      return page_;
    }
    /**
     * <code>int32 page = 1;</code>
     */
    public Builder setPage(int value) {
      
      page_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page = 1;</code>
     */
    public Builder clearPage() {
      
      page_ = 0;
      onChanged();
      return this;
    }

    private int size_ ;
    /**
     * <code>int32 size = 2;</code>
     */
    public int getSize() {
      return size_;
    }
    /**
     * <code>int32 size = 2;</code>
     */
    public Builder setSize(int value) {
      
      size_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 size = 2;</code>
     */
    public Builder clearSize() {
      
      size_ = 0;
      onChanged();
      return this;
    }

    private CoOrderPO coOrderPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        CoOrderPO, CoOrderPO.Builder, CoOrderPOOrBuilder> coOrderPOBuilder_;
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
     */
    public boolean hasCoOrderPO() {
      return coOrderPOBuilder_ != null || coOrderPO_ != null;
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
     */
    public CoOrderPO getCoOrderPO() {
      if (coOrderPOBuilder_ == null) {
        return coOrderPO_ == null ? CoOrderPO.getDefaultInstance() : coOrderPO_;
      } else {
        return coOrderPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
     */
    public CoOrderPO.Builder getCoOrderPOBuilder() {
      
      onChanged();
      return getCoOrderPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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
     * <code>.coOrder.CoOrderPO coOrderPO = 3;</code>
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


    // @@protoc_insertion_point(builder_scope:coOrder.CoOrderPageRequest)
  }

  // @@protoc_insertion_point(class_scope:coOrder.CoOrderPageRequest)
  private static final CoOrderPageRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoOrderPageRequest();
  }

  public static CoOrderPageRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoOrderPageRequest>
      PARSER = new com.google.protobuf.AbstractParser<CoOrderPageRequest>() {
    @Override
    public CoOrderPageRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoOrderPageRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoOrderPageRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoOrderPageRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CoOrderPageRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
