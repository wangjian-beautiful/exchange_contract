// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

/**
 * Protobuf type {@code transaction.TransactionRequest}
 */
public  final class TransactionRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:transaction.TransactionRequest)
    TransactionRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TransactionRequest.newBuilder() to construct.
  private TransactionRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransactionRequest() {
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TransactionRequest(
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
            TransactionPO.Builder subBuilder = null;
            if (transactionPO_ != null) {
              subBuilder = transactionPO_.toBuilder();
            }
            transactionPO_ = input.readMessage(TransactionPO.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(transactionPO_);
              transactionPO_ = subBuilder.buildPartial();
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
    return TransactionBizServiceProto.internal_static_transaction_TransactionRequest_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return TransactionBizServiceProto.internal_static_transaction_TransactionRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            TransactionRequest.class, Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int TRANSACTIONPO_FIELD_NUMBER = 2;
  private TransactionPO transactionPO_;
  /**
   * <code>.transaction.TransactionPO transactionPO = 2;</code>
   */
  public boolean hasTransactionPO() {
    return transactionPO_ != null;
  }
  /**
   * <code>.transaction.TransactionPO transactionPO = 2;</code>
   */
  public TransactionPO getTransactionPO() {
    return transactionPO_ == null ? TransactionPO.getDefaultInstance() : transactionPO_;
  }
  /**
   * <code>.transaction.TransactionPO transactionPO = 2;</code>
   */
  public TransactionPOOrBuilder getTransactionPOOrBuilder() {
    return getTransactionPO();
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
    if (transactionPO_ != null) {
      output.writeMessage(2, getTransactionPO());
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
    if (transactionPO_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getTransactionPO());
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
    if (!(obj instanceof TransactionRequest)) {
      return super.equals(obj);
    }
    TransactionRequest other = (TransactionRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (hasTransactionPO() != other.hasTransactionPO()) return false;
    if (hasTransactionPO()) {
      if (!getTransactionPO()
          .equals(other.getTransactionPO())) return false;
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
    if (hasTransactionPO()) {
      hash = (37 * hash) + TRANSACTIONPO_FIELD_NUMBER;
      hash = (53 * hash) + getTransactionPO().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static TransactionRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static TransactionRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static TransactionRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static TransactionRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static TransactionRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static TransactionRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static TransactionRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static TransactionRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static TransactionRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static TransactionRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static TransactionRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static TransactionRequest parseFrom(
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
  public static Builder newBuilder(TransactionRequest prototype) {
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
   * Protobuf type {@code transaction.TransactionRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transaction.TransactionRequest)
      TransactionRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return TransactionBizServiceProto.internal_static_transaction_TransactionRequest_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return TransactionBizServiceProto.internal_static_transaction_TransactionRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              TransactionRequest.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.transaction.TransactionRequest.newBuilder()
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

      if (transactionPOBuilder_ == null) {
        transactionPO_ = null;
      } else {
        transactionPO_ = null;
        transactionPOBuilder_ = null;
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return TransactionBizServiceProto.internal_static_transaction_TransactionRequest_descriptor;
    }

    @Override
    public TransactionRequest getDefaultInstanceForType() {
      return TransactionRequest.getDefaultInstance();
    }

    @Override
    public TransactionRequest build() {
      TransactionRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public TransactionRequest buildPartial() {
      TransactionRequest result = new TransactionRequest(this);
      result.id_ = id_;
      if (transactionPOBuilder_ == null) {
        result.transactionPO_ = transactionPO_;
      } else {
        result.transactionPO_ = transactionPOBuilder_.build();
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
      if (other instanceof TransactionRequest) {
        return mergeFrom((TransactionRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(TransactionRequest other) {
      if (other == TransactionRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.hasTransactionPO()) {
        mergeTransactionPO(other.getTransactionPO());
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
      TransactionRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (TransactionRequest) e.getUnfinishedMessage();
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

    private TransactionPO transactionPO_;
    private com.google.protobuf.SingleFieldBuilderV3<
        TransactionPO, TransactionPO.Builder, TransactionPOOrBuilder> transactionPOBuilder_;
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public boolean hasTransactionPO() {
      return transactionPOBuilder_ != null || transactionPO_ != null;
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public TransactionPO getTransactionPO() {
      if (transactionPOBuilder_ == null) {
        return transactionPO_ == null ? TransactionPO.getDefaultInstance() : transactionPO_;
      } else {
        return transactionPOBuilder_.getMessage();
      }
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public Builder setTransactionPO(TransactionPO value) {
      if (transactionPOBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        transactionPO_ = value;
        onChanged();
      } else {
        transactionPOBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public Builder setTransactionPO(
        TransactionPO.Builder builderForValue) {
      if (transactionPOBuilder_ == null) {
        transactionPO_ = builderForValue.build();
        onChanged();
      } else {
        transactionPOBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public Builder mergeTransactionPO(TransactionPO value) {
      if (transactionPOBuilder_ == null) {
        if (transactionPO_ != null) {
          transactionPO_ =
            TransactionPO.newBuilder(transactionPO_).mergeFrom(value).buildPartial();
        } else {
          transactionPO_ = value;
        }
        onChanged();
      } else {
        transactionPOBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public Builder clearTransactionPO() {
      if (transactionPOBuilder_ == null) {
        transactionPO_ = null;
        onChanged();
      } else {
        transactionPO_ = null;
        transactionPOBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public TransactionPO.Builder getTransactionPOBuilder() {
      
      onChanged();
      return getTransactionPOFieldBuilder().getBuilder();
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    public TransactionPOOrBuilder getTransactionPOOrBuilder() {
      if (transactionPOBuilder_ != null) {
        return transactionPOBuilder_.getMessageOrBuilder();
      } else {
        return transactionPO_ == null ?
            TransactionPO.getDefaultInstance() : transactionPO_;
      }
    }
    /**
     * <code>.transaction.TransactionPO transactionPO = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        TransactionPO, TransactionPO.Builder, TransactionPOOrBuilder>
        getTransactionPOFieldBuilder() {
      if (transactionPOBuilder_ == null) {
        transactionPOBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            TransactionPO, TransactionPO.Builder, TransactionPOOrBuilder>(
                getTransactionPO(),
                getParentForChildren(),
                isClean());
        transactionPO_ = null;
      }
      return transactionPOBuilder_;
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


    // @@protoc_insertion_point(builder_scope:transaction.TransactionRequest)
  }

  // @@protoc_insertion_point(class_scope:transaction.TransactionRequest)
  private static final TransactionRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new TransactionRequest();
  }

  public static TransactionRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransactionRequest>
      PARSER = new com.google.protobuf.AbstractParser<TransactionRequest>() {
    @Override
    public TransactionRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TransactionRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TransactionRequest> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<TransactionRequest> getParserForType() {
    return PARSER;
  }

  @Override
  public TransactionRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

