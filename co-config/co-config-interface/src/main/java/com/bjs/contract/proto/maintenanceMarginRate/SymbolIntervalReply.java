// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MaintenanceMarginRateService.proto

package com.bjs.contract.proto.maintenanceMarginRate;

/**
 * Protobuf type {@code maintenanceMarginRate.SymbolIntervalReply}
 */
public  final class SymbolIntervalReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:maintenanceMarginRate.SymbolIntervalReply)
    SymbolIntervalReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SymbolIntervalReply.newBuilder() to construct.
  private SymbolIntervalReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SymbolIntervalReply() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SymbolIntervalReply(
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
            MaintenanceMarginRatePO.Builder subBuilder = null;
            if (min_ != null) {
              subBuilder = min_.toBuilder();
            }
            min_ = input.readMessage(MaintenanceMarginRatePO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(min_);
              min_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            MaintenanceMarginRatePO.Builder subBuilder = null;
            if (max_ != null) {
              subBuilder = max_.toBuilder();
            }
            max_ = input.readMessage(MaintenanceMarginRatePO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(max_);
              max_ = subBuilder.buildPartial();
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
    return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_SymbolIntervalReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_SymbolIntervalReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            SymbolIntervalReply.class, Builder.class);
  }

  public static final int MIN_FIELD_NUMBER = 1;
  private MaintenanceMarginRatePO min_;
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
   */
  public boolean hasMin() {
    return min_ != null;
  }
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
   */
  public MaintenanceMarginRatePO getMin() {
    return min_ == null ? MaintenanceMarginRatePO.getDefaultInstance() : min_;
  }
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
   */
  public MaintenanceMarginRatePOOrBuilder getMinOrBuilder() {
    return getMin();
  }

  public static final int MAX_FIELD_NUMBER = 2;
  private MaintenanceMarginRatePO max_;
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
   */
  public boolean hasMax() {
    return max_ != null;
  }
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
   */
  public MaintenanceMarginRatePO getMax() {
    return max_ == null ? MaintenanceMarginRatePO.getDefaultInstance() : max_;
  }
  /**
   * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
   */
  public MaintenanceMarginRatePOOrBuilder getMaxOrBuilder() {
    return getMax();
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
    if (min_ != null) {
      output.writeMessage(1, getMin());
    }
    if (max_ != null) {
      output.writeMessage(2, getMax());
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (min_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getMin());
    }
    if (max_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getMax());
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
    if (!(obj instanceof SymbolIntervalReply)) {
      return super.equals(obj);
    }
    SymbolIntervalReply other = (SymbolIntervalReply) obj;

    if (hasMin() != other.hasMin()) return false;
    if (hasMin()) {
      if (!getMin()
          .equals(other.getMin())) return false;
    }
    if (hasMax() != other.hasMax()) return false;
    if (hasMax()) {
      if (!getMax()
          .equals(other.getMax())) return false;
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
    if (hasMin()) {
      hash = (37 * hash) + MIN_FIELD_NUMBER;
      hash = (53 * hash) + getMin().hashCode();
    }
    if (hasMax()) {
      hash = (37 * hash) + MAX_FIELD_NUMBER;
      hash = (53 * hash) + getMax().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static SymbolIntervalReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SymbolIntervalReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SymbolIntervalReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SymbolIntervalReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SymbolIntervalReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static SymbolIntervalReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static SymbolIntervalReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SymbolIntervalReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static SymbolIntervalReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static SymbolIntervalReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static SymbolIntervalReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static SymbolIntervalReply parseFrom(
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
  public static Builder newBuilder(SymbolIntervalReply prototype) {
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
   * Protobuf type {@code maintenanceMarginRate.SymbolIntervalReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:maintenanceMarginRate.SymbolIntervalReply)
      SymbolIntervalReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_SymbolIntervalReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_SymbolIntervalReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              SymbolIntervalReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.maintenanceMarginRate.SymbolIntervalReply.newBuilder()
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
      if (minBuilder_ == null) {
        min_ = null;
      } else {
        min_ = null;
        minBuilder_ = null;
      }
      if (maxBuilder_ == null) {
        max_ = null;
      } else {
        max_ = null;
        maxBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_SymbolIntervalReply_descriptor;
    }

    @Override
    public SymbolIntervalReply getDefaultInstanceForType() {
      return SymbolIntervalReply.getDefaultInstance();
    }

    @Override
    public SymbolIntervalReply build() {
      SymbolIntervalReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public SymbolIntervalReply buildPartial() {
      SymbolIntervalReply result = new SymbolIntervalReply(this);
      if (minBuilder_ == null) {
        result.min_ = min_;
      } else {
        result.min_ = minBuilder_.build();
      }
      if (maxBuilder_ == null) {
        result.max_ = max_;
      } else {
        result.max_ = maxBuilder_.build();
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
      if (other instanceof SymbolIntervalReply) {
        return mergeFrom((SymbolIntervalReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(SymbolIntervalReply other) {
      if (other == SymbolIntervalReply.getDefaultInstance()) return this;
      if (other.hasMin()) {
        mergeMin(other.getMin());
      }
      if (other.hasMax()) {
        mergeMax(other.getMax());
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
      SymbolIntervalReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (SymbolIntervalReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private MaintenanceMarginRatePO min_;
    private com.google.protobuf.SingleFieldBuilderV3<
        MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder> minBuilder_;
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public boolean hasMin() {
      return minBuilder_ != null || min_ != null;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public MaintenanceMarginRatePO getMin() {
      if (minBuilder_ == null) {
        return min_ == null ? MaintenanceMarginRatePO.getDefaultInstance() : min_;
      } else {
        return minBuilder_.getMessage();
      }
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public Builder setMin(MaintenanceMarginRatePO value) {
      if (minBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        min_ = value;
        onChanged();
      } else {
        minBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public Builder setMin(
        MaintenanceMarginRatePO.Builder builderForValue) {
      if (minBuilder_ == null) {
        min_ = builderForValue.build();
        onChanged();
      } else {
        minBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public Builder mergeMin(MaintenanceMarginRatePO value) {
      if (minBuilder_ == null) {
        if (min_ != null) {
          min_ =
            MaintenanceMarginRatePO.newBuilder(min_).mergeFrom(value).buildPartial();
        } else {
          min_ = value;
        }
        onChanged();
      } else {
        minBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public Builder clearMin() {
      if (minBuilder_ == null) {
        min_ = null;
        onChanged();
      } else {
        min_ = null;
        minBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public MaintenanceMarginRatePO.Builder getMinBuilder() {
      
      onChanged();
      return getMinFieldBuilder().getBuilder();
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    public MaintenanceMarginRatePOOrBuilder getMinOrBuilder() {
      if (minBuilder_ != null) {
        return minBuilder_.getMessageOrBuilder();
      } else {
        return min_ == null ?
            MaintenanceMarginRatePO.getDefaultInstance() : min_;
      }
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO min = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder>
        getMinFieldBuilder() {
      if (minBuilder_ == null) {
        minBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder>(
                getMin(),
                getParentForChildren(),
                isClean());
        min_ = null;
      }
      return minBuilder_;
    }

    private MaintenanceMarginRatePO max_;
    private com.google.protobuf.SingleFieldBuilderV3<
        MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder> maxBuilder_;
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public boolean hasMax() {
      return maxBuilder_ != null || max_ != null;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public MaintenanceMarginRatePO getMax() {
      if (maxBuilder_ == null) {
        return max_ == null ? MaintenanceMarginRatePO.getDefaultInstance() : max_;
      } else {
        return maxBuilder_.getMessage();
      }
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public Builder setMax(MaintenanceMarginRatePO value) {
      if (maxBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        max_ = value;
        onChanged();
      } else {
        maxBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public Builder setMax(
        MaintenanceMarginRatePO.Builder builderForValue) {
      if (maxBuilder_ == null) {
        max_ = builderForValue.build();
        onChanged();
      } else {
        maxBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public Builder mergeMax(MaintenanceMarginRatePO value) {
      if (maxBuilder_ == null) {
        if (max_ != null) {
          max_ =
            MaintenanceMarginRatePO.newBuilder(max_).mergeFrom(value).buildPartial();
        } else {
          max_ = value;
        }
        onChanged();
      } else {
        maxBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public Builder clearMax() {
      if (maxBuilder_ == null) {
        max_ = null;
        onChanged();
      } else {
        max_ = null;
        maxBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public MaintenanceMarginRatePO.Builder getMaxBuilder() {
      
      onChanged();
      return getMaxFieldBuilder().getBuilder();
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    public MaintenanceMarginRatePOOrBuilder getMaxOrBuilder() {
      if (maxBuilder_ != null) {
        return maxBuilder_.getMessageOrBuilder();
      } else {
        return max_ == null ?
            MaintenanceMarginRatePO.getDefaultInstance() : max_;
      }
    }
    /**
     * <code>.maintenanceMarginRate.MaintenanceMarginRatePO max = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder>
        getMaxFieldBuilder() {
      if (maxBuilder_ == null) {
        maxBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            MaintenanceMarginRatePO, MaintenanceMarginRatePO.Builder, MaintenanceMarginRatePOOrBuilder>(
                getMax(),
                getParentForChildren(),
                isClean());
        max_ = null;
      }
      return maxBuilder_;
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


    // @@protoc_insertion_point(builder_scope:maintenanceMarginRate.SymbolIntervalReply)
  }

  // @@protoc_insertion_point(class_scope:maintenanceMarginRate.SymbolIntervalReply)
  private static final SymbolIntervalReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new SymbolIntervalReply();
  }

  public static SymbolIntervalReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SymbolIntervalReply>
      PARSER = new com.google.protobuf.AbstractParser<SymbolIntervalReply>() {
    @Override
    public SymbolIntervalReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SymbolIntervalReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SymbolIntervalReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<SymbolIntervalReply> getParserForType() {
    return PARSER;
  }

  @Override
  public SymbolIntervalReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

