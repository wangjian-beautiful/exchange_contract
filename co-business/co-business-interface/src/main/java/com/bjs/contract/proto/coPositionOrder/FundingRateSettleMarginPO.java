// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoPositionOrderService.proto

package com.bjs.contract.proto.coPositionOrder;

/**
 * Protobuf type {@code coPositionOrder.FundingRateSettleMarginPO}
 */
public  final class FundingRateSettleMarginPO extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coPositionOrder.FundingRateSettleMarginPO)
    FundingRateSettleMarginPOOrBuilder {
private static final long serialVersionUID = 0L;
  // Use FundingRateSettleMarginPO.newBuilder() to construct.
  private FundingRateSettleMarginPO(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FundingRateSettleMarginPO() {
    symbol_ = "";
    side_ = "";
    fundingRateSettleMarginItemPO_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FundingRateSettleMarginPO(
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
          case 10: {
            String s = input.readStringRequireUtf8();

            symbol_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            side_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) != 0)) {
              fundingRateSettleMarginItemPO_ = new java.util.ArrayList<FundingRateSettleMarginItemPO>();
              mutable_bitField0_ |= 0x00000004;
            }
            fundingRateSettleMarginItemPO_.add(
                input.readMessage(FundingRateSettleMarginItemPO.parser(), extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000004) != 0)) {
        fundingRateSettleMarginItemPO_ = java.util.Collections.unmodifiableList(fundingRateSettleMarginItemPO_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_FundingRateSettleMarginPO_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_FundingRateSettleMarginPO_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            FundingRateSettleMarginPO.class, Builder.class);
  }

  private int bitField0_;
  public static final int SYMBOL_FIELD_NUMBER = 1;
  private volatile Object symbol_;
  /**
   * <code>string symbol = 1;</code>
   */
  public String getSymbol() {
    Object ref = symbol_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      symbol_ = s;
      return s;
    }
  }
  /**
   * <code>string symbol = 1;</code>
   */
  public com.google.protobuf.ByteString
      getSymbolBytes() {
    Object ref = symbol_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      symbol_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SIDE_FIELD_NUMBER = 2;
  private volatile Object side_;
  /**
   * <code>string side = 2;</code>
   */
  public String getSide() {
    Object ref = side_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      side_ = s;
      return s;
    }
  }
  /**
   * <code>string side = 2;</code>
   */
  public com.google.protobuf.ByteString
      getSideBytes() {
    Object ref = side_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      side_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FUNDINGRATESETTLEMARGINITEMPO_FIELD_NUMBER = 3;
  private java.util.List<FundingRateSettleMarginItemPO> fundingRateSettleMarginItemPO_;
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  public java.util.List<FundingRateSettleMarginItemPO> getFundingRateSettleMarginItemPOList() {
    return fundingRateSettleMarginItemPO_;
  }
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  public java.util.List<? extends FundingRateSettleMarginItemPOOrBuilder>
      getFundingRateSettleMarginItemPOOrBuilderList() {
    return fundingRateSettleMarginItemPO_;
  }
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  public int getFundingRateSettleMarginItemPOCount() {
    return fundingRateSettleMarginItemPO_.size();
  }
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  public FundingRateSettleMarginItemPO getFundingRateSettleMarginItemPO(int index) {
    return fundingRateSettleMarginItemPO_.get(index);
  }
  /**
   * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
   */
  public FundingRateSettleMarginItemPOOrBuilder getFundingRateSettleMarginItemPOOrBuilder(
      int index) {
    return fundingRateSettleMarginItemPO_.get(index);
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
    if (!getSymbolBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, symbol_);
    }
    if (!getSideBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, side_);
    }
    for (int i = 0; i < fundingRateSettleMarginItemPO_.size(); i++) {
      output.writeMessage(3, fundingRateSettleMarginItemPO_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSymbolBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, symbol_);
    }
    if (!getSideBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, side_);
    }
    for (int i = 0; i < fundingRateSettleMarginItemPO_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, fundingRateSettleMarginItemPO_.get(i));
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
    if (!(obj instanceof FundingRateSettleMarginPO)) {
      return super.equals(obj);
    }
    FundingRateSettleMarginPO other = (FundingRateSettleMarginPO) obj;

    if (!getSymbol()
        .equals(other.getSymbol())) return false;
    if (!getSide()
        .equals(other.getSide())) return false;
    if (!getFundingRateSettleMarginItemPOList()
        .equals(other.getFundingRateSettleMarginItemPOList())) return false;
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
    hash = (37 * hash) + SYMBOL_FIELD_NUMBER;
    hash = (53 * hash) + getSymbol().hashCode();
    hash = (37 * hash) + SIDE_FIELD_NUMBER;
    hash = (53 * hash) + getSide().hashCode();
    if (getFundingRateSettleMarginItemPOCount() > 0) {
      hash = (37 * hash) + FUNDINGRATESETTLEMARGINITEMPO_FIELD_NUMBER;
      hash = (53 * hash) + getFundingRateSettleMarginItemPOList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static FundingRateSettleMarginPO parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static FundingRateSettleMarginPO parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static FundingRateSettleMarginPO parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static FundingRateSettleMarginPO parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static FundingRateSettleMarginPO parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static FundingRateSettleMarginPO parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static FundingRateSettleMarginPO parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static FundingRateSettleMarginPO parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static FundingRateSettleMarginPO parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static FundingRateSettleMarginPO parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static FundingRateSettleMarginPO parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static FundingRateSettleMarginPO parseFrom(
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
  public static Builder newBuilder(FundingRateSettleMarginPO prototype) {
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
   * Protobuf type {@code coPositionOrder.FundingRateSettleMarginPO}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coPositionOrder.FundingRateSettleMarginPO)
      FundingRateSettleMarginPOOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_FundingRateSettleMarginPO_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_FundingRateSettleMarginPO_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              FundingRateSettleMarginPO.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coPositionOrder.FundingRateSettleMarginPO.newBuilder()
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
        getFundingRateSettleMarginItemPOFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      symbol_ = "";

      side_ = "";

      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        fundingRateSettleMarginItemPO_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        fundingRateSettleMarginItemPOBuilder_.clear();
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoPositionOrderBizServiceProto.internal_static_coPositionOrder_FundingRateSettleMarginPO_descriptor;
    }

    @Override
    public FundingRateSettleMarginPO getDefaultInstanceForType() {
      return FundingRateSettleMarginPO.getDefaultInstance();
    }

    @Override
    public FundingRateSettleMarginPO build() {
      FundingRateSettleMarginPO result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public FundingRateSettleMarginPO buildPartial() {
      FundingRateSettleMarginPO result = new FundingRateSettleMarginPO(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.symbol_ = symbol_;
      result.side_ = side_;
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        if (((bitField0_ & 0x00000004) != 0)) {
          fundingRateSettleMarginItemPO_ = java.util.Collections.unmodifiableList(fundingRateSettleMarginItemPO_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.fundingRateSettleMarginItemPO_ = fundingRateSettleMarginItemPO_;
      } else {
        result.fundingRateSettleMarginItemPO_ = fundingRateSettleMarginItemPOBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof FundingRateSettleMarginPO) {
        return mergeFrom((FundingRateSettleMarginPO)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(FundingRateSettleMarginPO other) {
      if (other == FundingRateSettleMarginPO.getDefaultInstance()) return this;
      if (!other.getSymbol().isEmpty()) {
        symbol_ = other.symbol_;
        onChanged();
      }
      if (!other.getSide().isEmpty()) {
        side_ = other.side_;
        onChanged();
      }
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        if (!other.fundingRateSettleMarginItemPO_.isEmpty()) {
          if (fundingRateSettleMarginItemPO_.isEmpty()) {
            fundingRateSettleMarginItemPO_ = other.fundingRateSettleMarginItemPO_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureFundingRateSettleMarginItemPOIsMutable();
            fundingRateSettleMarginItemPO_.addAll(other.fundingRateSettleMarginItemPO_);
          }
          onChanged();
        }
      } else {
        if (!other.fundingRateSettleMarginItemPO_.isEmpty()) {
          if (fundingRateSettleMarginItemPOBuilder_.isEmpty()) {
            fundingRateSettleMarginItemPOBuilder_.dispose();
            fundingRateSettleMarginItemPOBuilder_ = null;
            fundingRateSettleMarginItemPO_ = other.fundingRateSettleMarginItemPO_;
            bitField0_ = (bitField0_ & ~0x00000004);
            fundingRateSettleMarginItemPOBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getFundingRateSettleMarginItemPOFieldBuilder() : null;
          } else {
            fundingRateSettleMarginItemPOBuilder_.addAllMessages(other.fundingRateSettleMarginItemPO_);
          }
        }
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
      FundingRateSettleMarginPO parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (FundingRateSettleMarginPO) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private Object symbol_ = "";
    /**
     * <code>string symbol = 1;</code>
     */
    public String getSymbol() {
      Object ref = symbol_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        symbol_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string symbol = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSymbolBytes() {
      Object ref = symbol_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        symbol_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string symbol = 1;</code>
     */
    public Builder setSymbol(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      symbol_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string symbol = 1;</code>
     */
    public Builder clearSymbol() {
      
      symbol_ = getDefaultInstance().getSymbol();
      onChanged();
      return this;
    }
    /**
     * <code>string symbol = 1;</code>
     */
    public Builder setSymbolBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      symbol_ = value;
      onChanged();
      return this;
    }

    private Object side_ = "";
    /**
     * <code>string side = 2;</code>
     */
    public String getSide() {
      Object ref = side_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        side_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string side = 2;</code>
     */
    public com.google.protobuf.ByteString
        getSideBytes() {
      Object ref = side_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        side_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string side = 2;</code>
     */
    public Builder setSide(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      side_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string side = 2;</code>
     */
    public Builder clearSide() {
      
      side_ = getDefaultInstance().getSide();
      onChanged();
      return this;
    }
    /**
     * <code>string side = 2;</code>
     */
    public Builder setSideBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      side_ = value;
      onChanged();
      return this;
    }

    private java.util.List<FundingRateSettleMarginItemPO> fundingRateSettleMarginItemPO_ =
      java.util.Collections.emptyList();
    private void ensureFundingRateSettleMarginItemPOIsMutable() {
      if (!((bitField0_ & 0x00000004) != 0)) {
        fundingRateSettleMarginItemPO_ = new java.util.ArrayList<FundingRateSettleMarginItemPO>(fundingRateSettleMarginItemPO_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        FundingRateSettleMarginItemPO, FundingRateSettleMarginItemPO.Builder, FundingRateSettleMarginItemPOOrBuilder> fundingRateSettleMarginItemPOBuilder_;

    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public java.util.List<FundingRateSettleMarginItemPO> getFundingRateSettleMarginItemPOList() {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        return java.util.Collections.unmodifiableList(fundingRateSettleMarginItemPO_);
      } else {
        return fundingRateSettleMarginItemPOBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public int getFundingRateSettleMarginItemPOCount() {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        return fundingRateSettleMarginItemPO_.size();
      } else {
        return fundingRateSettleMarginItemPOBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public FundingRateSettleMarginItemPO getFundingRateSettleMarginItemPO(int index) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        return fundingRateSettleMarginItemPO_.get(index);
      } else {
        return fundingRateSettleMarginItemPOBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder setFundingRateSettleMarginItemPO(
        int index, FundingRateSettleMarginItemPO value) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.set(index, value);
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder setFundingRateSettleMarginItemPO(
        int index, FundingRateSettleMarginItemPO.Builder builderForValue) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.set(index, builderForValue.build());
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder addFundingRateSettleMarginItemPO(FundingRateSettleMarginItemPO value) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.add(value);
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder addFundingRateSettleMarginItemPO(
        int index, FundingRateSettleMarginItemPO value) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.add(index, value);
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder addFundingRateSettleMarginItemPO(
        FundingRateSettleMarginItemPO.Builder builderForValue) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.add(builderForValue.build());
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder addFundingRateSettleMarginItemPO(
        int index, FundingRateSettleMarginItemPO.Builder builderForValue) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.add(index, builderForValue.build());
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder addAllFundingRateSettleMarginItemPO(
        Iterable<? extends FundingRateSettleMarginItemPO> values) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        ensureFundingRateSettleMarginItemPOIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, fundingRateSettleMarginItemPO_);
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder clearFundingRateSettleMarginItemPO() {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        fundingRateSettleMarginItemPO_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public Builder removeFundingRateSettleMarginItemPO(int index) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        ensureFundingRateSettleMarginItemPOIsMutable();
        fundingRateSettleMarginItemPO_.remove(index);
        onChanged();
      } else {
        fundingRateSettleMarginItemPOBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public FundingRateSettleMarginItemPO.Builder getFundingRateSettleMarginItemPOBuilder(
        int index) {
      return getFundingRateSettleMarginItemPOFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public FundingRateSettleMarginItemPOOrBuilder getFundingRateSettleMarginItemPOOrBuilder(
        int index) {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        return fundingRateSettleMarginItemPO_.get(index);  } else {
        return fundingRateSettleMarginItemPOBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public java.util.List<? extends FundingRateSettleMarginItemPOOrBuilder>
         getFundingRateSettleMarginItemPOOrBuilderList() {
      if (fundingRateSettleMarginItemPOBuilder_ != null) {
        return fundingRateSettleMarginItemPOBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(fundingRateSettleMarginItemPO_);
      }
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public FundingRateSettleMarginItemPO.Builder addFundingRateSettleMarginItemPOBuilder() {
      return getFundingRateSettleMarginItemPOFieldBuilder().addBuilder(
          FundingRateSettleMarginItemPO.getDefaultInstance());
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public FundingRateSettleMarginItemPO.Builder addFundingRateSettleMarginItemPOBuilder(
        int index) {
      return getFundingRateSettleMarginItemPOFieldBuilder().addBuilder(
          index, FundingRateSettleMarginItemPO.getDefaultInstance());
    }
    /**
     * <code>repeated .coPositionOrder.FundingRateSettleMarginItemPO fundingRateSettleMarginItemPO = 3;</code>
     */
    public java.util.List<FundingRateSettleMarginItemPO.Builder>
         getFundingRateSettleMarginItemPOBuilderList() {
      return getFundingRateSettleMarginItemPOFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        FundingRateSettleMarginItemPO, FundingRateSettleMarginItemPO.Builder, FundingRateSettleMarginItemPOOrBuilder>
        getFundingRateSettleMarginItemPOFieldBuilder() {
      if (fundingRateSettleMarginItemPOBuilder_ == null) {
        fundingRateSettleMarginItemPOBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            FundingRateSettleMarginItemPO, FundingRateSettleMarginItemPO.Builder, FundingRateSettleMarginItemPOOrBuilder>(
                fundingRateSettleMarginItemPO_,
                ((bitField0_ & 0x00000004) != 0),
                getParentForChildren(),
                isClean());
        fundingRateSettleMarginItemPO_ = null;
      }
      return fundingRateSettleMarginItemPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:coPositionOrder.FundingRateSettleMarginPO)
  }

  // @@protoc_insertion_point(class_scope:coPositionOrder.FundingRateSettleMarginPO)
  private static final FundingRateSettleMarginPO DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new FundingRateSettleMarginPO();
  }

  public static FundingRateSettleMarginPO getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FundingRateSettleMarginPO>
      PARSER = new com.google.protobuf.AbstractParser<FundingRateSettleMarginPO>() {
    @Override
    public FundingRateSettleMarginPO parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new FundingRateSettleMarginPO(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FundingRateSettleMarginPO> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<FundingRateSettleMarginPO> getParserForType() {
    return PARSER;
  }

  @Override
  public FundingRateSettleMarginPO getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

