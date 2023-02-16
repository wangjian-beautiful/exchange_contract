// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoTradeService.proto

package com.bjs.contract.proto.coTrade;

/**
 * Protobuf type {@code coTrade.CoTradeRequest}
 */
public  final class CoTradeRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coTrade.CoTradeRequest)
    CoTradeRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoTradeRequest.newBuilder() to construct.
  private CoTradeRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoTradeRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoTradeRequest(
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
            CoTradePO.Builder subBuilder = null;
            if (coTradePO_ != null) {
              subBuilder = coTradePO_.toBuilder();
            }
            coTradePO_ = input.readMessage(CoTradePO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(coTradePO_);
              coTradePO_ = subBuilder.buildPartial();
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
    return CoTradeBizServiceProto.internal_static_coTrade_CoTradeRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoTradeBizServiceProto.internal_static_coTrade_CoTradeRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoTradeRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int COTRADEPO_FIELD_NUMBER = 2;
  private CoTradePO coTradePO_;
  /**
   * <code>.coTrade.CoTradePO coTradePO = 2;</code>
   */
  public boolean hasCoTradePO() {
    return coTradePO_ != null;
  }
  /**
   * <code>.coTrade.CoTradePO coTradePO = 2;</code>
   */
  public CoTradePO getCoTradePO() {
    return coTradePO_ == null ? CoTradePO.getDefaultInstance() : coTradePO_;
  }
  /**
   * <code>.coTrade.CoTradePO coTradePO = 2;</code>
   */
  public CoTradePOOrBuilder getCoTradePOOrBuilder() {
    return getCoTradePO();
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
    if (coTradePO_ != null) {
      output.writeMessage(2, getCoTradePO());
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
    if (coTradePO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getCoTradePO());
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
    if (!(obj instanceof CoTradeRequest)) {
      return super.equals(obj);
    }
    CoTradeRequest other = (CoTradeRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (hasCoTradePO() != other.hasCoTradePO()) return false;
    if (hasCoTradePO()) {
      if (!getCoTradePO()
          .equals(other.getCoTradePO())) return false;
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
    if (hasCoTradePO()) {
      hash = (37 * hash) + COTRADEPO_FIELD_NUMBER;
      hash = (53 * hash) + getCoTradePO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoTradeRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoTradeRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoTradeRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoTradeRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoTradeRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoTradeRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoTradeRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoTradeRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoTradeRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoTradeRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoTradeRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoTradeRequest parseFrom(
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
  public static Builder newBuilder(CoTradeRequest prototype) {
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
   * Protobuf type {@code coTrade.CoTradeRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coTrade.CoTradeRequest)
      CoTradeRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoTradeBizServiceProto.internal_static_coTrade_CoTradeRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoTradeBizServiceProto.internal_static_coTrade_CoTradeRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoTradeRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coTrade.CoTradeRequest.newBuilder()
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

      if (coTradePOBuilder_ == null) {
        coTradePO_ = null;
      } else {
        coTradePO_ = null;
        coTradePOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoTradeBizServiceProto.internal_static_coTrade_CoTradeRequest_descriptor;
    }

    @Override
    public CoTradeRequest getDefaultInstanceForType() {
      return CoTradeRequest.getDefaultInstance();
    }

    @Override
    public CoTradeRequest build() {
      CoTradeRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoTradeRequest buildPartial() {
      CoTradeRequest result = new CoTradeRequest(this);
      result.id_ = id_;
      if (coTradePOBuilder_ == null) {
        result.coTradePO_ = coTradePO_;
      } else {
        result.coTradePO_ = coTradePOBuilder_.build();
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
      if (other instanceof CoTradeRequest) {
        return mergeFrom((CoTradeRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoTradeRequest other) {
      if (other == CoTradeRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.hasCoTradePO()) {
        mergeCoTradePO(other.getCoTradePO());
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
      CoTradeRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoTradeRequest) e.getUnfinishedMessage();
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

    private CoTradePO coTradePO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        CoTradePO, CoTradePO.Builder, CoTradePOOrBuilder> coTradePOBuilder_;
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public boolean hasCoTradePO() {
      return coTradePOBuilder_ != null || coTradePO_ != null;
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public CoTradePO getCoTradePO() {
      if (coTradePOBuilder_ == null) {
        return coTradePO_ == null ? CoTradePO.getDefaultInstance() : coTradePO_;
      } else {
        return coTradePOBuilder_.getMessage();
      }
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public Builder setCoTradePO(CoTradePO value) {
      if (coTradePOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        coTradePO_ = value;
        onChanged();
      } else {
        coTradePOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public Builder setCoTradePO(
        CoTradePO.Builder builderForValue) {
      if (coTradePOBuilder_ == null) {
        coTradePO_ = builderForValue.build();
        onChanged();
      } else {
        coTradePOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public Builder mergeCoTradePO(CoTradePO value) {
      if (coTradePOBuilder_ == null) {
        if (coTradePO_ != null) {
          coTradePO_ =
            CoTradePO.newBuilder(coTradePO_).mergeFrom(value).buildPartial();
        } else {
          coTradePO_ = value;
        }
        onChanged();
      } else {
        coTradePOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public Builder clearCoTradePO() {
      if (coTradePOBuilder_ == null) {
        coTradePO_ = null;
        onChanged();
      } else {
        coTradePO_ = null;
        coTradePOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public CoTradePO.Builder getCoTradePOBuilder() {
      
      onChanged();
      return getCoTradePOFieldBuilder().getBuilder();
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    public CoTradePOOrBuilder getCoTradePOOrBuilder() {
      if (coTradePOBuilder_ != null) {
        return coTradePOBuilder_.getMessageOrBuilder();
      } else {
        return coTradePO_ == null ?
            CoTradePO.getDefaultInstance() : coTradePO_;
      }
    }
    /**
     * <code>.coTrade.CoTradePO coTradePO = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        CoTradePO, CoTradePO.Builder, CoTradePOOrBuilder>
        getCoTradePOFieldBuilder() {
      if (coTradePOBuilder_ == null) {
        coTradePOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            CoTradePO, CoTradePO.Builder, CoTradePOOrBuilder>(
                getCoTradePO(),
                getParentForChildren(),
                isClean());
        coTradePO_ = null;
      }
      return coTradePOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:coTrade.CoTradeRequest)
  }

  // @@protoc_insertion_point(class_scope:coTrade.CoTradeRequest)
  private static final CoTradeRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoTradeRequest();
  }

  public static CoTradeRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoTradeRequest>
      PARSER = new com.google.protobuf.AbstractParser<CoTradeRequest>() {
    @Override
    public CoTradeRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoTradeRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoTradeRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoTradeRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CoTradeRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
