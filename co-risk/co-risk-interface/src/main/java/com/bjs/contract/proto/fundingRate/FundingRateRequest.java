// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FundingRateService.proto

package com.bjs.contract.proto.fundingRate;

/**
 * Protobuf type {@code fundingRate.FundingRateRequest}
 */
public  final class FundingRateRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:fundingRate.FundingRateRequest)
    FundingRateRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FundingRateRequest.newBuilder() to construct.
  private FundingRateRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FundingRateRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FundingRateRequest(
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
            FundingRatePO.Builder subBuilder = null;
            if (fundingRatePO_ != null) {
              subBuilder = fundingRatePO_.toBuilder();
            }
            fundingRatePO_ = input.readMessage(FundingRatePO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(fundingRatePO_);
              fundingRatePO_ = subBuilder.buildPartial();
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
    return FundingRateBizServiceProto.internal_static_fundingRate_FundingRateRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return FundingRateBizServiceProto.internal_static_fundingRate_FundingRateRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            FundingRateRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int FUNDINGRATEPO_FIELD_NUMBER = 2;
  private FundingRatePO fundingRatePO_;
  /**
   * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
   */
  public boolean hasFundingRatePO() {
    return fundingRatePO_ != null;
  }
  /**
   * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
   */
  public FundingRatePO getFundingRatePO() {
    return fundingRatePO_ == null ? FundingRatePO.getDefaultInstance() : fundingRatePO_;
  }
  /**
   * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
   */
  public FundingRatePOOrBuilder getFundingRatePOOrBuilder() {
    return getFundingRatePO();
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
    if (fundingRatePO_ != null) {
      output.writeMessage(2, getFundingRatePO());
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
    if (fundingRatePO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getFundingRatePO());
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
    if (!(obj instanceof FundingRateRequest)) {
      return super.equals(obj);
    }
    FundingRateRequest other = (FundingRateRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (hasFundingRatePO() != other.hasFundingRatePO()) return false;
    if (hasFundingRatePO()) {
      if (!getFundingRatePO()
          .equals(other.getFundingRatePO())) return false;
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
    if (hasFundingRatePO()) {
      hash = (37 * hash) + FUNDINGRATEPO_FIELD_NUMBER;
      hash = (53 * hash) + getFundingRatePO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static FundingRateRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static FundingRateRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static FundingRateRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static FundingRateRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static FundingRateRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static FundingRateRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static FundingRateRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static FundingRateRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static FundingRateRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static FundingRateRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static FundingRateRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static FundingRateRequest parseFrom(
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
  public static Builder newBuilder(FundingRateRequest prototype) {
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
   * Protobuf type {@code fundingRate.FundingRateRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:fundingRate.FundingRateRequest)
      FundingRateRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return FundingRateBizServiceProto.internal_static_fundingRate_FundingRateRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return FundingRateBizServiceProto.internal_static_fundingRate_FundingRateRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              FundingRateRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.fundingRate.FundingRateRequest.newBuilder()
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

      if (fundingRatePOBuilder_ == null) {
        fundingRatePO_ = null;
      } else {
        fundingRatePO_ = null;
        fundingRatePOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return FundingRateBizServiceProto.internal_static_fundingRate_FundingRateRequest_descriptor;
    }

    @Override
    public FundingRateRequest getDefaultInstanceForType() {
      return FundingRateRequest.getDefaultInstance();
    }

    @Override
    public FundingRateRequest build() {
      FundingRateRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public FundingRateRequest buildPartial() {
      FundingRateRequest result = new FundingRateRequest(this);
      result.id_ = id_;
      if (fundingRatePOBuilder_ == null) {
        result.fundingRatePO_ = fundingRatePO_;
      } else {
        result.fundingRatePO_ = fundingRatePOBuilder_.build();
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
      if (other instanceof FundingRateRequest) {
        return mergeFrom((FundingRateRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(FundingRateRequest other) {
      if (other == FundingRateRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.hasFundingRatePO()) {
        mergeFundingRatePO(other.getFundingRatePO());
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
      FundingRateRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (FundingRateRequest) e.getUnfinishedMessage();
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

    private FundingRatePO fundingRatePO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        FundingRatePO, FundingRatePO.Builder, FundingRatePOOrBuilder> fundingRatePOBuilder_;
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public boolean hasFundingRatePO() {
      return fundingRatePOBuilder_ != null || fundingRatePO_ != null;
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public FundingRatePO getFundingRatePO() {
      if (fundingRatePOBuilder_ == null) {
        return fundingRatePO_ == null ? FundingRatePO.getDefaultInstance() : fundingRatePO_;
      } else {
        return fundingRatePOBuilder_.getMessage();
      }
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public Builder setFundingRatePO(FundingRatePO value) {
      if (fundingRatePOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        fundingRatePO_ = value;
        onChanged();
      } else {
        fundingRatePOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public Builder setFundingRatePO(
        FundingRatePO.Builder builderForValue) {
      if (fundingRatePOBuilder_ == null) {
        fundingRatePO_ = builderForValue.build();
        onChanged();
      } else {
        fundingRatePOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public Builder mergeFundingRatePO(FundingRatePO value) {
      if (fundingRatePOBuilder_ == null) {
        if (fundingRatePO_ != null) {
          fundingRatePO_ =
            FundingRatePO.newBuilder(fundingRatePO_).mergeFrom(value).buildPartial();
        } else {
          fundingRatePO_ = value;
        }
        onChanged();
      } else {
        fundingRatePOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public Builder clearFundingRatePO() {
      if (fundingRatePOBuilder_ == null) {
        fundingRatePO_ = null;
        onChanged();
      } else {
        fundingRatePO_ = null;
        fundingRatePOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public FundingRatePO.Builder getFundingRatePOBuilder() {
      
      onChanged();
      return getFundingRatePOFieldBuilder().getBuilder();
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    public FundingRatePOOrBuilder getFundingRatePOOrBuilder() {
      if (fundingRatePOBuilder_ != null) {
        return fundingRatePOBuilder_.getMessageOrBuilder();
      } else {
        return fundingRatePO_ == null ?
            FundingRatePO.getDefaultInstance() : fundingRatePO_;
      }
    }
    /**
     * <code>.fundingRate.FundingRatePO fundingRatePO = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        FundingRatePO, FundingRatePO.Builder, FundingRatePOOrBuilder>
        getFundingRatePOFieldBuilder() {
      if (fundingRatePOBuilder_ == null) {
        fundingRatePOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            FundingRatePO, FundingRatePO.Builder, FundingRatePOOrBuilder>(
                getFundingRatePO(),
                getParentForChildren(),
                isClean());
        fundingRatePO_ = null;
      }
      return fundingRatePOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:fundingRate.FundingRateRequest)
  }

  // @@protoc_insertion_point(class_scope:fundingRate.FundingRateRequest)
  private static final FundingRateRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new FundingRateRequest();
  }

  public static FundingRateRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FundingRateRequest>
      PARSER = new com.google.protobuf.AbstractParser<FundingRateRequest>() {
    @Override
    public FundingRateRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FundingRateRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FundingRateRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<FundingRateRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public FundingRateRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
