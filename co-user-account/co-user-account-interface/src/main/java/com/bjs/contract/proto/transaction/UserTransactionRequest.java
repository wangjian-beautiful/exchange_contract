// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

/**
 * Protobuf type {@code transaction.UserTransactionRequest}
 */
public  final class UserTransactionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:transaction.UserTransactionRequest)
    UserTransactionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserTransactionRequest.newBuilder() to construct.
  private UserTransactionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserTransactionRequest() {
    beginTime_ = "";
    endTime_ = "";
    symbol_ = "";
    type_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserTransactionRequest(
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

            beginTime_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            endTime_ = s;
            break;
          }
          case 24: {

            page_ = input.readInt32();
            break;
          }
          case 32: {

            limit_ = input.readInt32();
            break;
          }
          case 42: {
            String s = input.readStringRequireUtf8();

            symbol_ = s;
            break;
          }
          case 50: {
            String s = input.readStringRequireUtf8();

            type_ = s;
            break;
          }
          case 56: {

            uid_ = input.readInt64();
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
    return TransactionBizServiceProto.internal_static_transaction_UserTransactionRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return TransactionBizServiceProto.internal_static_transaction_UserTransactionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserTransactionRequest.class, Builder.class);
  }

  public static final int BEGINTIME_FIELD_NUMBER = 1;
  private volatile Object beginTime_;
  /**
   * <code>string beginTime = 1;</code>
   */
  public String getBeginTime() {
    Object ref = beginTime_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      beginTime_ = s;
      return s;
    }
  }
  /**
   * <code>string beginTime = 1;</code>
   */
  public com.google.protobuf.ByteString
      getBeginTimeBytes() {
    Object ref = beginTime_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      beginTime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ENDTIME_FIELD_NUMBER = 2;
  private volatile Object endTime_;
  /**
   * <code>string endTime = 2;</code>
   */
  public String getEndTime() {
    Object ref = endTime_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      endTime_ = s;
      return s;
    }
  }
  /**
   * <code>string endTime = 2;</code>
   */
  public com.google.protobuf.ByteString
      getEndTimeBytes() {
    Object ref = endTime_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      endTime_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAGE_FIELD_NUMBER = 3;
  private int page_;
  /**
   * <code>int32 page = 3;</code>
   */
  public int getPage() {
    return page_;
  }

  public static final int LIMIT_FIELD_NUMBER = 4;
  private int limit_;
  /**
   * <code>int32 limit = 4;</code>
   */
  public int getLimit() {
    return limit_;
  }

  public static final int SYMBOL_FIELD_NUMBER = 5;
  private volatile Object symbol_;
  /**
   * <code>string symbol = 5;</code>
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
   * <code>string symbol = 5;</code>
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

  public static final int TYPE_FIELD_NUMBER = 6;
  private volatile Object type_;
  /**
   * <code>string type = 6;</code>
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
   * <code>string type = 6;</code>
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

  public static final int UID_FIELD_NUMBER = 7;
  private long uid_;
  /**
   * <code>int64 uid = 7;</code>
   */
  public long getUid() {
    return uid_;
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
    if (!getBeginTimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, beginTime_);
    }
    if (!getEndTimeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, endTime_);
    }
    if (page_ != 0) {
      output.writeInt32(3, page_);
    }
    if (limit_ != 0) {
      output.writeInt32(4, limit_);
    }
    if (!getSymbolBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, symbol_);
    }
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, type_);
    }
    if (uid_ != 0L) {
      output.writeInt64(7, uid_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getBeginTimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, beginTime_);
    }
    if (!getEndTimeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, endTime_);
    }
    if (page_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, page_);
    }
    if (limit_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, limit_);
    }
    if (!getSymbolBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, symbol_);
    }
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, type_);
    }
    if (uid_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, uid_);
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
    if (!(obj instanceof UserTransactionRequest)) {
      return super.equals(obj);
    }
    UserTransactionRequest other = (UserTransactionRequest) obj;

    if (!getBeginTime()
        .equals(other.getBeginTime())) return false;
    if (!getEndTime()
        .equals(other.getEndTime())) return false;
    if (getPage()
        != other.getPage()) return false;
    if (getLimit()
        != other.getLimit()) return false;
    if (!getSymbol()
        .equals(other.getSymbol())) return false;
    if (!getType()
        .equals(other.getType())) return false;
    if (getUid()
        != other.getUid()) return false;
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
    hash = (37 * hash) + BEGINTIME_FIELD_NUMBER;
    hash = (53 * hash) + getBeginTime().hashCode();
    hash = (37 * hash) + ENDTIME_FIELD_NUMBER;
    hash = (53 * hash) + getEndTime().hashCode();
    hash = (37 * hash) + PAGE_FIELD_NUMBER;
    hash = (53 * hash) + getPage();
    hash = (37 * hash) + LIMIT_FIELD_NUMBER;
    hash = (53 * hash) + getLimit();
    hash = (37 * hash) + SYMBOL_FIELD_NUMBER;
    hash = (53 * hash) + getSymbol().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    hash = (37 * hash) + UID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUid());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserTransactionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserTransactionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserTransactionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserTransactionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserTransactionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserTransactionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserTransactionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserTransactionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserTransactionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserTransactionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserTransactionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserTransactionRequest parseFrom(
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
  public static Builder newBuilder(UserTransactionRequest prototype) {
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
   * Protobuf type {@code transaction.UserTransactionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transaction.UserTransactionRequest)
      UserTransactionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return TransactionBizServiceProto.internal_static_transaction_UserTransactionRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return TransactionBizServiceProto.internal_static_transaction_UserTransactionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserTransactionRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.transaction.UserTransactionRequest.newBuilder()
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
      beginTime_ = "";

      endTime_ = "";

      page_ = 0;

      limit_ = 0;

      symbol_ = "";

      type_ = "";

      uid_ = 0L;

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return TransactionBizServiceProto.internal_static_transaction_UserTransactionRequest_descriptor;
    }

    @Override
    public UserTransactionRequest getDefaultInstanceForType() {
      return UserTransactionRequest.getDefaultInstance();
    }

    @Override
    public UserTransactionRequest build() {
      UserTransactionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UserTransactionRequest buildPartial() {
      UserTransactionRequest result = new UserTransactionRequest(this);
      result.beginTime_ = beginTime_;
      result.endTime_ = endTime_;
      result.page_ = page_;
      result.limit_ = limit_;
      result.symbol_ = symbol_;
      result.type_ = type_;
      result.uid_ = uid_;
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
      if (other instanceof UserTransactionRequest) {
        return mergeFrom((UserTransactionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserTransactionRequest other) {
      if (other == UserTransactionRequest.getDefaultInstance()) return this;
      if (!other.getBeginTime().isEmpty()) {
        beginTime_ = other.beginTime_;
        onChanged();
      }
      if (!other.getEndTime().isEmpty()) {
        endTime_ = other.endTime_;
        onChanged();
      }
      if (other.getPage() != 0) {
        setPage(other.getPage());
      }
      if (other.getLimit() != 0) {
        setLimit(other.getLimit());
      }
      if (!other.getSymbol().isEmpty()) {
        symbol_ = other.symbol_;
        onChanged();
      }
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
      if (other.getUid() != 0L) {
        setUid(other.getUid());
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
      UserTransactionRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserTransactionRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object beginTime_ = "";
    /**
     * <code>string beginTime = 1;</code>
     */
    public String getBeginTime() {
      Object ref = beginTime_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        beginTime_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string beginTime = 1;</code>
     */
    public com.google.protobuf.ByteString
        getBeginTimeBytes() {
      Object ref = beginTime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        beginTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string beginTime = 1;</code>
     */
    public Builder setBeginTime(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      beginTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string beginTime = 1;</code>
     */
    public Builder clearBeginTime() {
      
      beginTime_ = getDefaultInstance().getBeginTime();
      onChanged();
      return this;
    }
    /**
     * <code>string beginTime = 1;</code>
     */
    public Builder setBeginTimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      beginTime_ = value;
      onChanged();
      return this;
    }

    private Object endTime_ = "";
    /**
     * <code>string endTime = 2;</code>
     */
    public String getEndTime() {
      Object ref = endTime_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        endTime_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string endTime = 2;</code>
     */
    public com.google.protobuf.ByteString
        getEndTimeBytes() {
      Object ref = endTime_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        endTime_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string endTime = 2;</code>
     */
    public Builder setEndTime(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      endTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string endTime = 2;</code>
     */
    public Builder clearEndTime() {
      
      endTime_ = getDefaultInstance().getEndTime();
      onChanged();
      return this;
    }
    /**
     * <code>string endTime = 2;</code>
     */
    public Builder setEndTimeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      endTime_ = value;
      onChanged();
      return this;
    }

    private int page_ ;
    /**
     * <code>int32 page = 3;</code>
     */
    public int getPage() {
      return page_;
    }
    /**
     * <code>int32 page = 3;</code>
     */
    public Builder setPage(int value) {
      
      page_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 page = 3;</code>
     */
    public Builder clearPage() {
      
      page_ = 0;
      onChanged();
      return this;
    }

    private int limit_ ;
    /**
     * <code>int32 limit = 4;</code>
     */
    public int getLimit() {
      return limit_;
    }
    /**
     * <code>int32 limit = 4;</code>
     */
    public Builder setLimit(int value) {
      
      limit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 limit = 4;</code>
     */
    public Builder clearLimit() {
      
      limit_ = 0;
      onChanged();
      return this;
    }

    private Object symbol_ = "";
    /**
     * <code>string symbol = 5;</code>
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
     * <code>string symbol = 5;</code>
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
     * <code>string symbol = 5;</code>
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
     * <code>string symbol = 5;</code>
     */
    public Builder clearSymbol() {
      
      symbol_ = getDefaultInstance().getSymbol();
      onChanged();
      return this;
    }
    /**
     * <code>string symbol = 5;</code>
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

    private Object type_ = "";
    /**
     * <code>string type = 6;</code>
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
     * <code>string type = 6;</code>
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
     * <code>string type = 6;</code>
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
     * <code>string type = 6;</code>
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <code>string type = 6;</code>
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

    private long uid_ ;
    /**
     * <code>int64 uid = 7;</code>
     */
    public long getUid() {
      return uid_;
    }
    /**
     * <code>int64 uid = 7;</code>
     */
    public Builder setUid(long value) {
      
      uid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 uid = 7;</code>
     */
    public Builder clearUid() {
      
      uid_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:transaction.UserTransactionRequest)
  }

  // @@protoc_insertion_point(class_scope:transaction.UserTransactionRequest)
  private static final UserTransactionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserTransactionRequest();
  }

  public static UserTransactionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserTransactionRequest>
      PARSER = new com.google.protobuf.AbstractParser<UserTransactionRequest>() {
    @Override
    public UserTransactionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserTransactionRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserTransactionRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UserTransactionRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public UserTransactionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
