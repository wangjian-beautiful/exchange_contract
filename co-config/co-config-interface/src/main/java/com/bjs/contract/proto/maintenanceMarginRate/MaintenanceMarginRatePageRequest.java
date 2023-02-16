// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MaintenanceMarginRateService.proto

package com.bjs.contract.proto.maintenanceMarginRate;

/**
 * Protobuf type {@code maintenanceMarginRate.MaintenanceMarginRatePageRequest}
 */
public  final class MaintenanceMarginRatePageRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:maintenanceMarginRate.MaintenanceMarginRatePageRequest)
    MaintenanceMarginRatePageRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MaintenanceMarginRatePageRequest.newBuilder() to construct.
  private MaintenanceMarginRatePageRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MaintenanceMarginRatePageRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MaintenanceMarginRatePageRequest(
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
    return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            MaintenanceMarginRatePageRequest.class, Builder.class);
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
    if (page_ != 0) {
      output.writeInt32(1, page_);
    }
    if (size_ != 0) {
      output.writeInt32(2, size_);
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
    if (page_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, page_);
    }
    if (size_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, size_);
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
    if (!(obj instanceof MaintenanceMarginRatePageRequest)) {
      return super.equals(obj);
    }
    MaintenanceMarginRatePageRequest other = (MaintenanceMarginRatePageRequest) obj;

    if (getPage()
        != other.getPage()) return false;
    if (getSize()
        != other.getSize()) return false;
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
    hash = (37 * hash) + PAGE_FIELD_NUMBER;
    hash = (53 * hash) + getPage();
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getSize();
    if (hasMaintenanceMarginRatePO()) {
      hash = (37 * hash) + MAINTENANCEMARGINRATEPO_FIELD_NUMBER;
      hash = (53 * hash) + getMaintenanceMarginRatePO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static MaintenanceMarginRatePageRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static MaintenanceMarginRatePageRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static MaintenanceMarginRatePageRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static MaintenanceMarginRatePageRequest parseFrom(
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
  public static Builder newBuilder(MaintenanceMarginRatePageRequest prototype) {
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
   * Protobuf type {@code maintenanceMarginRate.MaintenanceMarginRatePageRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:maintenanceMarginRate.MaintenanceMarginRatePageRequest)
      MaintenanceMarginRatePageRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              MaintenanceMarginRatePageRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRatePageRequest.newBuilder()
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
      return MaintenanceMarginRateBizServiceProto.internal_static_maintenanceMarginRate_MaintenanceMarginRatePageRequest_descriptor;
    }

    @Override
    public MaintenanceMarginRatePageRequest getDefaultInstanceForType() {
      return MaintenanceMarginRatePageRequest.getDefaultInstance();
    }

    @Override
    public MaintenanceMarginRatePageRequest build() {
      MaintenanceMarginRatePageRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public MaintenanceMarginRatePageRequest buildPartial() {
      MaintenanceMarginRatePageRequest result = new MaintenanceMarginRatePageRequest(this);
      result.page_ = page_;
      result.size_ = size_;
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
      if (other instanceof MaintenanceMarginRatePageRequest) {
        return mergeFrom((MaintenanceMarginRatePageRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(MaintenanceMarginRatePageRequest other) {
      if (other == MaintenanceMarginRatePageRequest.getDefaultInstance()) return this;
      if (other.getPage() != 0) {
        setPage(other.getPage());
      }
      if (other.getSize() != 0) {
        setSize(other.getSize());
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
      MaintenanceMarginRatePageRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (MaintenanceMarginRatePageRequest) e.getUnfinishedMessage();
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


    // @@protoc_insertion_point(builder_scope:maintenanceMarginRate.MaintenanceMarginRatePageRequest)
  }

  // @@protoc_insertion_point(class_scope:maintenanceMarginRate.MaintenanceMarginRatePageRequest)
  private static final MaintenanceMarginRatePageRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new MaintenanceMarginRatePageRequest();
  }

  public static MaintenanceMarginRatePageRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MaintenanceMarginRatePageRequest>
      PARSER = new com.google.protobuf.AbstractParser<MaintenanceMarginRatePageRequest>() {
    @Override
    public MaintenanceMarginRatePageRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MaintenanceMarginRatePageRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MaintenanceMarginRatePageRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<MaintenanceMarginRatePageRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public MaintenanceMarginRatePageRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

