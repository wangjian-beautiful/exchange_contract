// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CoOrderService.proto

package com.bjs.contract.proto.coOrder;

/**
 * Protobuf type {@code coOrder.CoOrderStatusReply}
 */
public  final class CoOrderStatusReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:coOrder.CoOrderStatusReply)
    CoOrderStatusReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CoOrderStatusReply.newBuilder() to construct.
  private CoOrderStatusReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CoOrderStatusReply() {
    message_ = "";
    orders_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CoOrderStatusReply(
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

            status_ = input.readBool();
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) != 0)) {
              orders_ = new java.util.ArrayList<CoOrderStatus>();
              mutable_bitField0_ |= 0x00000004;
            }
            orders_.add(
                input.readMessage(CoOrderStatus.parser(), extensionRegistry));
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
        orders_ = java.util.Collections.unmodifiableList(orders_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return CoOrderBizServiceProto.internal_static_coOrder_CoOrderStatusReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return CoOrderBizServiceProto.internal_static_coOrder_CoOrderStatusReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            CoOrderStatusReply.class, Builder.class);
  }

  private int bitField0_;
  public static final int STATUS_FIELD_NUMBER = 1;
  private boolean status_;
  /**
   * <code>bool status = 1;</code>
   */
  public boolean getStatus() {
    return status_;
  }

  public static final int MESSAGE_FIELD_NUMBER = 2;
  private volatile Object message_;
  /**
   * <code>string message = 2;</code>
   */
  public String getMessage() {
    Object ref = message_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>string message = 2;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    Object ref = message_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ORDERS_FIELD_NUMBER = 3;
  private java.util.List<CoOrderStatus> orders_;
  /**
   * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
   */
  public java.util.List<CoOrderStatus> getOrdersList() {
    return orders_;
  }
  /**
   * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
   */
  public java.util.List<? extends CoOrderStatusOrBuilder>
      getOrdersOrBuilderList() {
    return orders_;
  }
  /**
   * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
   */
  public int getOrdersCount() {
    return orders_.size();
  }
  /**
   * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
   */
  public CoOrderStatus getOrders(int index) {
    return orders_.get(index);
  }
  /**
   * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
   */
  public CoOrderStatusOrBuilder getOrdersOrBuilder(
      int index) {
    return orders_.get(index);
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
    if (status_ != false) {
      output.writeBool(1, status_);
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, message_);
    }
    for (int i = 0; i < orders_.size(); i++) {
      output.writeMessage(3, orders_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, status_);
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, message_);
    }
    for (int i = 0; i < orders_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, orders_.get(i));
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
    if (!(obj instanceof CoOrderStatusReply)) {
      return super.equals(obj);
    }
    CoOrderStatusReply other = (CoOrderStatusReply) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getMessage()
        .equals(other.getMessage())) return false;
    if (!getOrdersList()
        .equals(other.getOrdersList())) return false;
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
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getStatus());
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    if (getOrdersCount() > 0) {
      hash = (37 * hash) + ORDERS_FIELD_NUMBER;
      hash = (53 * hash) + getOrdersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static CoOrderStatusReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderStatusReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderStatusReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderStatusReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderStatusReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static CoOrderStatusReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static CoOrderStatusReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoOrderStatusReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoOrderStatusReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static CoOrderStatusReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static CoOrderStatusReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static CoOrderStatusReply parseFrom(
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
  public static Builder newBuilder(CoOrderStatusReply prototype) {
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
   * Protobuf type {@code coOrder.CoOrderStatusReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:coOrder.CoOrderStatusReply)
      CoOrderStatusReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderStatusReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderStatusReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              CoOrderStatusReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.coOrder.CoOrderStatusReply.newBuilder()
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
        getOrdersFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      status_ = false;

      message_ = "";

      if (ordersBuilder_ == null) {
        orders_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        ordersBuilder_.clear();
      }
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return CoOrderBizServiceProto.internal_static_coOrder_CoOrderStatusReply_descriptor;
    }

    @Override
    public CoOrderStatusReply getDefaultInstanceForType() {
      return CoOrderStatusReply.getDefaultInstance();
    }

    @Override
    public CoOrderStatusReply build() {
      CoOrderStatusReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public CoOrderStatusReply buildPartial() {
      CoOrderStatusReply result = new CoOrderStatusReply(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.status_ = status_;
      result.message_ = message_;
      if (ordersBuilder_ == null) {
        if (((bitField0_ & 0x00000004) != 0)) {
          orders_ = java.util.Collections.unmodifiableList(orders_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.orders_ = orders_;
      } else {
        result.orders_ = ordersBuilder_.build();
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
      if (other instanceof CoOrderStatusReply) {
        return mergeFrom((CoOrderStatusReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(CoOrderStatusReply other) {
      if (other == CoOrderStatusReply.getDefaultInstance()) return this;
      if (other.getStatus() != false) {
        setStatus(other.getStatus());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      if (ordersBuilder_ == null) {
        if (!other.orders_.isEmpty()) {
          if (orders_.isEmpty()) {
            orders_ = other.orders_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureOrdersIsMutable();
            orders_.addAll(other.orders_);
          }
          onChanged();
        }
      } else {
        if (!other.orders_.isEmpty()) {
          if (ordersBuilder_.isEmpty()) {
            ordersBuilder_.dispose();
            ordersBuilder_ = null;
            orders_ = other.orders_;
            bitField0_ = (bitField0_ & ~0x00000004);
            ordersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOrdersFieldBuilder() : null;
          } else {
            ordersBuilder_.addAllMessages(other.orders_);
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
      CoOrderStatusReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (CoOrderStatusReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private boolean status_ ;
    /**
     * <code>bool status = 1;</code>
     */
    public boolean getStatus() {
      return status_;
    }
    /**
     * <code>bool status = 1;</code>
     */
    public Builder setStatus(boolean value) {
      
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool status = 1;</code>
     */
    public Builder clearStatus() {
      
      status_ = false;
      onChanged();
      return this;
    }

    private Object message_ = "";
    /**
     * <code>string message = 2;</code>
     */
    public String getMessage() {
      Object ref = message_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessage(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string message = 2;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }

    private java.util.List<CoOrderStatus> orders_ =
      java.util.Collections.emptyList();
    private void ensureOrdersIsMutable() {
      if (!((bitField0_ & 0x00000004) != 0)) {
        orders_ = new java.util.ArrayList<CoOrderStatus>(orders_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        CoOrderStatus, CoOrderStatus.Builder, CoOrderStatusOrBuilder> ordersBuilder_;

    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public java.util.List<CoOrderStatus> getOrdersList() {
      if (ordersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(orders_);
      } else {
        return ordersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public int getOrdersCount() {
      if (ordersBuilder_ == null) {
        return orders_.size();
      } else {
        return ordersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public CoOrderStatus getOrders(int index) {
      if (ordersBuilder_ == null) {
        return orders_.get(index);
      } else {
        return ordersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder setOrders(
        int index, CoOrderStatus value) {
      if (ordersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrdersIsMutable();
        orders_.set(index, value);
        onChanged();
      } else {
        ordersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder setOrders(
        int index, CoOrderStatus.Builder builderForValue) {
      if (ordersBuilder_ == null) {
        ensureOrdersIsMutable();
        orders_.set(index, builderForValue.build());
        onChanged();
      } else {
        ordersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder addOrders(CoOrderStatus value) {
      if (ordersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrdersIsMutable();
        orders_.add(value);
        onChanged();
      } else {
        ordersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder addOrders(
        int index, CoOrderStatus value) {
      if (ordersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOrdersIsMutable();
        orders_.add(index, value);
        onChanged();
      } else {
        ordersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder addOrders(
        CoOrderStatus.Builder builderForValue) {
      if (ordersBuilder_ == null) {
        ensureOrdersIsMutable();
        orders_.add(builderForValue.build());
        onChanged();
      } else {
        ordersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder addOrders(
        int index, CoOrderStatus.Builder builderForValue) {
      if (ordersBuilder_ == null) {
        ensureOrdersIsMutable();
        orders_.add(index, builderForValue.build());
        onChanged();
      } else {
        ordersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder addAllOrders(
        Iterable<? extends CoOrderStatus> values) {
      if (ordersBuilder_ == null) {
        ensureOrdersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, orders_);
        onChanged();
      } else {
        ordersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder clearOrders() {
      if (ordersBuilder_ == null) {
        orders_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        ordersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public Builder removeOrders(int index) {
      if (ordersBuilder_ == null) {
        ensureOrdersIsMutable();
        orders_.remove(index);
        onChanged();
      } else {
        ordersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public CoOrderStatus.Builder getOrdersBuilder(
        int index) {
      return getOrdersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public CoOrderStatusOrBuilder getOrdersOrBuilder(
        int index) {
      if (ordersBuilder_ == null) {
        return orders_.get(index);  } else {
        return ordersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public java.util.List<? extends CoOrderStatusOrBuilder>
         getOrdersOrBuilderList() {
      if (ordersBuilder_ != null) {
        return ordersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(orders_);
      }
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public CoOrderStatus.Builder addOrdersBuilder() {
      return getOrdersFieldBuilder().addBuilder(
          CoOrderStatus.getDefaultInstance());
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public CoOrderStatus.Builder addOrdersBuilder(
        int index) {
      return getOrdersFieldBuilder().addBuilder(
          index, CoOrderStatus.getDefaultInstance());
    }
    /**
     * <code>repeated .coOrder.CoOrderStatus orders = 3;</code>
     */
    public java.util.List<CoOrderStatus.Builder>
         getOrdersBuilderList() {
      return getOrdersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        CoOrderStatus, CoOrderStatus.Builder, CoOrderStatusOrBuilder>
        getOrdersFieldBuilder() {
      if (ordersBuilder_ == null) {
        ordersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            CoOrderStatus, CoOrderStatus.Builder, CoOrderStatusOrBuilder>(
                orders_,
                ((bitField0_ & 0x00000004) != 0),
                getParentForChildren(),
                isClean());
        orders_ = null;
      }
      return ordersBuilder_;
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


    // @@protoc_insertion_point(builder_scope:coOrder.CoOrderStatusReply)
  }

  // @@protoc_insertion_point(class_scope:coOrder.CoOrderStatusReply)
  private static final CoOrderStatusReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new CoOrderStatusReply();
  }

  public static CoOrderStatusReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CoOrderStatusReply>
      PARSER = new com.google.protobuf.AbstractParser<CoOrderStatusReply>() {
    @Override
    public CoOrderStatusReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CoOrderStatusReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CoOrderStatusReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<CoOrderStatusReply> getParserForType() {
    return PARSER;
  }

  @Override
  public CoOrderStatusReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

