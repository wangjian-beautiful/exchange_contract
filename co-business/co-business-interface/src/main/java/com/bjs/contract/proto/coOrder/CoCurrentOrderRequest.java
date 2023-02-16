// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

/**
 * Protobuf type {@code coOrder.CoCurrentOrderRequest}
 */
public  final class CoCurrentOrderRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coOrder.CoCurrentOrderRequest)
    CoCurrentOrderRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoCurrentOrderRequest.newBuilder() to construct.
  private CoCurrentOrderRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoCurrentOrderRequest() {
    contractId_ = "";
    type_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoCurrentOrderRequest(
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
            String s = input.readStringRequireUtf8();

            contractId_ = s;
            break;
          }
          case 24: {

            limit_ = input.readInt32();
            break;
          }
          case 32: {

            page_ = input.readInt32();
            break;
          }
          case 42: {
            String s = input.readStringRequireUtf8();

            type_ = s;
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
    return CoOrderBizServiceProto.internal_static_coOrder_CoCurrentOrderRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoOrderBizServiceProto.internal_static_coOrder_CoCurrentOrderRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoCurrentOrderRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int CONTRACTID_FIELD_NUMBER = 2;
  private volatile Object contractId_;
  /**
   * <code>string contractId = 2;</code>
   */
  public String getContractId() {
    Object ref = contractId_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      contractId_ = s;
      return s;
    }
  }
  /**
   * <code>string contractId = 2;</code>
   */
  public com.google.protobuf.ByteString
      getContractIdBytes() {
    Object ref = contractId_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      contractId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int LIMIT_FIELD_NUMBER = 3;
  private int limit_;
  /**
   * <code>int32 limit = 3;</code>
   */
  public int getLimit() {
    return limit_;
  }

  public static final int PAGE_FIELD_NUMBER = 4;
  private int page_;
  /**
   * <code>int32 page = 4;</code>
   */
  public int getPage() {
    return page_;
  }

  public static final int TYPE_FIELD_NUMBER = 5;
  private volatile Object type_;
  /**
   * <code>string type = 5;</code>
   */
  public String getType() {
    Object ref = type_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <code>string type = 5;</code>
   */
  public com.google.protobuf.ByteString
      getTypeBytes() {
    Object ref = type_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getContractIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, contractId_);
    }
    if (limit_ != 0) {
      output.writeInt32(3, limit_);
    }
    if (page_ != 0) {
      output.writeInt32(4, page_);
    }
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, type_);
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
    if (!getContractIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, contractId_);
    }
    if (limit_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, limit_);
    }
    if (page_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, page_);
    }
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, type_);
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
    if (!(obj instanceof CoCurrentOrderRequest)) {
      return super.equals(obj);
    }
    CoCurrentOrderRequest other = (CoCurrentOrderRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (!getContractId()
        .equals(other.getContractId())) return false;
    if (getLimit()
        != other.getLimit()) return false;
    if (getPage()
        != other.getPage()) return false;
    if (!getType()
        .equals(other.getType())) return false;
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
    hash = (37 * hash) + CONTRACTID_FIELD_NUMBER;
    hash = (53 * hash) + getContractId().hashCode();
    hash = (37 * hash) + LIMIT_FIELD_NUMBER;
    hash = (53 * hash) + getLimit();
    hash = (37 * hash) + PAGE_FIELD_NUMBER;
    hash = (53 * hash) + getPage();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoCurrentOrderRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoCurrentOrderRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoCurrentOrderRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoCurrentOrderRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoCurrentOrderRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoCurrentOrderRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoCurrentOrderRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoCurrentOrderRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoCurrentOrderRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoCurrentOrderRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoCurrentOrderRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoCurrentOrderRequest parseFrom(
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
  public static Builder newBuilder(CoCurrentOrderRequest prototype) {
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
   * Protobuf type {@code coOrder.CoCurrentOrderRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coOrder.CoCurrentOrderRequest)
      CoCurrentOrderRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoCurrentOrderRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoCurrentOrderRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoCurrentOrderRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coOrder.CoCurrentOrderRequest.newBuilder()
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

      contractId_ = "";

      limit_ = 0;

      page_ = 0;

      type_ = "";

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoCurrentOrderRequest_descriptor;
    }

    @Override
    public CoCurrentOrderRequest getDefaultInstanceForType() {
      return CoCurrentOrderRequest.getDefaultInstance();
    }

    @Override
    public CoCurrentOrderRequest build() {
      CoCurrentOrderRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoCurrentOrderRequest buildPartial() {
      CoCurrentOrderRequest result = new CoCurrentOrderRequest(this);
      result.id_ = id_;
      result.contractId_ = contractId_;
      result.limit_ = limit_;
      result.page_ = page_;
      result.type_ = type_;
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
      if (other instanceof CoCurrentOrderRequest) {
        return mergeFrom((CoCurrentOrderRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoCurrentOrderRequest other) {
      if (other == CoCurrentOrderRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (!other.getContractId().isEmpty()) {
        contractId_ = other.contractId_;
        onChanged();
      }
      if (other.getLimit() != 0) {
        setLimit(other.getLimit());
      }
      if (other.getPage() != 0) {
        setPage(other.getPage());
      }
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
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
      CoCurrentOrderRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoCurrentOrderRequest) e.getUnfinishedMessage();
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

    private Object contractId_ = "";
    /**
     * <code>string contractId = 2;</code>
     */
    public String getContractId() {
      Object ref = contractId_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        contractId_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string contractId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getContractIdBytes() {
      Object ref = contractId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        contractId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string contractId = 2;</code>
     */
    public Builder setContractId(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      contractId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string contractId = 2;</code>
     */
    public Builder clearContractId() {
      
      contractId_ = getDefaultInstance().getContractId();
      onChanged();
      return this;
    }
    /**
     * <code>string contractId = 2;</code>
     */
    public Builder setContractIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      contractId_ = value;
      onChanged();
      return this;
    }

    private int limit_ ;
    /**
     * <code>int32 limit = 3;</code>
     */
    public int getLimit() {
      return limit_;
    }
    /**
     * <code>int32 limit = 3;</code>
     */
    public Builder setLimit(int value) {
      
      limit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 limit = 3;</code>
     */
    public Builder clearLimit() {
      
      limit_ = 0;
      onChanged();
      return this;
    }

    private int page_ ;
    /**
     * <code>int32 page = 4;</code>
     */
    public int getPage() {
      return page_;
    }
    /**
     * <code>int32 page = 4;</code>
     */
    public Builder setPage(int value) {
      
      page_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page = 4;</code>
     */
    public Builder clearPage() {
      
      page_ = 0;
      onChanged();
      return this;
    }

    private Object type_ = "";
    /**
     * <code>string type = 5;</code>
     */
    public String getType() {
      Object ref = type_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string type = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string type = 5;</code>
     */
    public Builder setType(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string type = 5;</code>
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <code>string type = 5;</code>
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:coOrder.CoCurrentOrderRequest)
  }

  // @@protoc_insertion_point(class_scope:coOrder.CoCurrentOrderRequest)
  private static final CoCurrentOrderRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoCurrentOrderRequest();
  }

  public static CoCurrentOrderRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoCurrentOrderRequest>
      PARSER = new com.google.protobuf.AbstractParser<CoCurrentOrderRequest>() {
    @Override
    public CoCurrentOrderRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoCurrentOrderRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoCurrentOrderRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoCurrentOrderRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public CoCurrentOrderRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

