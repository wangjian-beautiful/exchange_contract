// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransactionService.proto

package com.bjs.contract.proto.transaction;

/**
 * Protobuf type {@code transaction.UserTransactionReply}
 */
public  final class UserTransactionReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:transaction.UserTransactionReply)
    UserTransactionReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserTransactionReply.newBuilder() to construct.
  private UserTransactionReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserTransactionReply() {
    transactionList_ = java.util.Collections.emptyList();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserTransactionReply(
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
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              transactionList_ = new java.util.ArrayList<UserTransactionList>();
              mutable_bitField0_ |= 0x00000001;
            }
            transactionList_.add(
                input.readMessage(UserTransactionList.parser(), extensionRegistry));
            break;
          }
          case 16: {

            count_ = input.readInt32();
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        transactionList_ = java.util.Collections.unmodifiableList(transactionList_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return TransactionBizServiceProto.internal_static_transaction_UserTransactionReply_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return TransactionBizServiceProto.internal_static_transaction_UserTransactionReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            UserTransactionReply.class, Builder.class);
  }

  private int bitField0_;
  public static final int TRANSACTIONLIST_FIELD_NUMBER = 1;
  private java.util.List<UserTransactionList> transactionList_;
  /**
   * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
   */
  public java.util.List<UserTransactionList> getTransactionListList() {
    return transactionList_;
  }
  /**
   * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
   */
  public java.util.List<? extends UserTransactionListOrBuilder>
      getTransactionListOrBuilderList() {
    return transactionList_;
  }
  /**
   * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
   */
  public int getTransactionListCount() {
    return transactionList_.size();
  }
  /**
   * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
   */
  public UserTransactionList getTransactionList(int index) {
    return transactionList_.get(index);
  }
  /**
   * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
   */
  public UserTransactionListOrBuilder getTransactionListOrBuilder(
      int index) {
    return transactionList_.get(index);
  }

  public static final int COUNT_FIELD_NUMBER = 2;
  private int count_;
  /**
   * <code>int32 count = 2;</code>
   */
  public int getCount() {
    return count_;
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
    for (int i = 0; i < transactionList_.size(); i++) {
      output.writeMessage(1, transactionList_.get(i));
    }
    if (count_ != 0) {
      output.writeInt32(2, count_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < transactionList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, transactionList_.get(i));
    }
    if (count_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, count_);
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
    if (!(obj instanceof UserTransactionReply)) {
      return super.equals(obj);
    }
    UserTransactionReply other = (UserTransactionReply) obj;

    if (!getTransactionListList()
        .equals(other.getTransactionListList())) return false;
    if (getCount()
        != other.getCount()) return false;
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
    if (getTransactionListCount() > 0) {
      hash = (37 * hash) + TRANSACTIONLIST_FIELD_NUMBER;
      hash = (53 * hash) + getTransactionListList().hashCode();
    }
    hash = (37 * hash) + COUNT_FIELD_NUMBER;
    hash = (53 * hash) + getCount();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static UserTransactionReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserTransactionReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserTransactionReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserTransactionReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserTransactionReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static UserTransactionReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static UserTransactionReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserTransactionReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserTransactionReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static UserTransactionReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static UserTransactionReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static UserTransactionReply parseFrom(
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
  public static Builder newBuilder(UserTransactionReply prototype) {
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
   * Protobuf type {@code transaction.UserTransactionReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transaction.UserTransactionReply)
      UserTransactionReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return TransactionBizServiceProto.internal_static_transaction_UserTransactionReply_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return TransactionBizServiceProto.internal_static_transaction_UserTransactionReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserTransactionReply.class, Builder.class);
    }

    // Construct using com.bjs.contract.proto.transaction.UserTransactionReply.newBuilder()
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
        getTransactionListFieldBuilder();
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      if (transactionListBuilder_ == null) {
        transactionList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        transactionListBuilder_.clear();
      }
      count_ = 0;

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return TransactionBizServiceProto.internal_static_transaction_UserTransactionReply_descriptor;
    }

    @Override
    public UserTransactionReply getDefaultInstanceForType() {
      return UserTransactionReply.getDefaultInstance();
    }

    @Override
    public UserTransactionReply build() {
      UserTransactionReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public UserTransactionReply buildPartial() {
      UserTransactionReply result = new UserTransactionReply(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (transactionListBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          transactionList_ = java.util.Collections.unmodifiableList(transactionList_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.transactionList_ = transactionList_;
      } else {
        result.transactionList_ = transactionListBuilder_.build();
      }
      result.count_ = count_;
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
      if (other instanceof UserTransactionReply) {
        return mergeFrom((UserTransactionReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(UserTransactionReply other) {
      if (other == UserTransactionReply.getDefaultInstance()) return this;
      if (transactionListBuilder_ == null) {
        if (!other.transactionList_.isEmpty()) {
          if (transactionList_.isEmpty()) {
            transactionList_ = other.transactionList_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureTransactionListIsMutable();
            transactionList_.addAll(other.transactionList_);
          }
          onChanged();
        }
      } else {
        if (!other.transactionList_.isEmpty()) {
          if (transactionListBuilder_.isEmpty()) {
            transactionListBuilder_.dispose();
            transactionListBuilder_ = null;
            transactionList_ = other.transactionList_;
            bitField0_ = (bitField0_ & ~0x00000001);
            transactionListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getTransactionListFieldBuilder() : null;
          } else {
            transactionListBuilder_.addAllMessages(other.transactionList_);
          }
        }
      }
      if (other.getCount() != 0) {
        setCount(other.getCount());
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
      UserTransactionReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (UserTransactionReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<UserTransactionList> transactionList_ =
      java.util.Collections.emptyList();
    private void ensureTransactionListIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        transactionList_ = new java.util.ArrayList<UserTransactionList>(transactionList_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        UserTransactionList, UserTransactionList.Builder, UserTransactionListOrBuilder> transactionListBuilder_;

    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public java.util.List<UserTransactionList> getTransactionListList() {
      if (transactionListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(transactionList_);
      } else {
        return transactionListBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public int getTransactionListCount() {
      if (transactionListBuilder_ == null) {
        return transactionList_.size();
      } else {
        return transactionListBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public UserTransactionList getTransactionList(int index) {
      if (transactionListBuilder_ == null) {
        return transactionList_.get(index);
      } else {
        return transactionListBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder setTransactionList(
        int index, UserTransactionList value) {
      if (transactionListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTransactionListIsMutable();
        transactionList_.set(index, value);
        onChanged();
      } else {
        transactionListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder setTransactionList(
        int index, UserTransactionList.Builder builderForValue) {
      if (transactionListBuilder_ == null) {
        ensureTransactionListIsMutable();
        transactionList_.set(index, builderForValue.build());
        onChanged();
      } else {
        transactionListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder addTransactionList(UserTransactionList value) {
      if (transactionListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTransactionListIsMutable();
        transactionList_.add(value);
        onChanged();
      } else {
        transactionListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder addTransactionList(
        int index, UserTransactionList value) {
      if (transactionListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTransactionListIsMutable();
        transactionList_.add(index, value);
        onChanged();
      } else {
        transactionListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder addTransactionList(
        UserTransactionList.Builder builderForValue) {
      if (transactionListBuilder_ == null) {
        ensureTransactionListIsMutable();
        transactionList_.add(builderForValue.build());
        onChanged();
      } else {
        transactionListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder addTransactionList(
        int index, UserTransactionList.Builder builderForValue) {
      if (transactionListBuilder_ == null) {
        ensureTransactionListIsMutable();
        transactionList_.add(index, builderForValue.build());
        onChanged();
      } else {
        transactionListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder addAllTransactionList(
        Iterable<? extends UserTransactionList> values) {
      if (transactionListBuilder_ == null) {
        ensureTransactionListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, transactionList_);
        onChanged();
      } else {
        transactionListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder clearTransactionList() {
      if (transactionListBuilder_ == null) {
        transactionList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        transactionListBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public Builder removeTransactionList(int index) {
      if (transactionListBuilder_ == null) {
        ensureTransactionListIsMutable();
        transactionList_.remove(index);
        onChanged();
      } else {
        transactionListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public UserTransactionList.Builder getTransactionListBuilder(
        int index) {
      return getTransactionListFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public UserTransactionListOrBuilder getTransactionListOrBuilder(
        int index) {
      if (transactionListBuilder_ == null) {
        return transactionList_.get(index);  } else {
        return transactionListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public java.util.List<? extends UserTransactionListOrBuilder>
         getTransactionListOrBuilderList() {
      if (transactionListBuilder_ != null) {
        return transactionListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(transactionList_);
      }
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public UserTransactionList.Builder addTransactionListBuilder() {
      return getTransactionListFieldBuilder().addBuilder(
          UserTransactionList.getDefaultInstance());
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public UserTransactionList.Builder addTransactionListBuilder(
        int index) {
      return getTransactionListFieldBuilder().addBuilder(
          index, UserTransactionList.getDefaultInstance());
    }
    /**
     * <code>repeated .transaction.UserTransactionList transactionList = 1;</code>
     */
    public java.util.List<UserTransactionList.Builder>
         getTransactionListBuilderList() {
      return getTransactionListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        UserTransactionList, UserTransactionList.Builder, UserTransactionListOrBuilder>
        getTransactionListFieldBuilder() {
      if (transactionListBuilder_ == null) {
        transactionListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            UserTransactionList, UserTransactionList.Builder, UserTransactionListOrBuilder>(
                transactionList_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        transactionList_ = null;
      }
      return transactionListBuilder_;
    }

    private int count_ ;
    /**
     * <code>int32 count = 2;</code>
     */
    public int getCount() {
      return count_;
    }
    /**
     * <code>int32 count = 2;</code>
     */
    public Builder setCount(int value) {
      
      count_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 count = 2;</code>
     */
    public Builder clearCount() {
      
      count_ = 0;
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


    // @@protoc_insertion_point(builder_scope:transaction.UserTransactionReply)
  }

  // @@protoc_insertion_point(class_scope:transaction.UserTransactionReply)
  private static final UserTransactionReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new UserTransactionReply();
  }

  public static UserTransactionReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserTransactionReply>
      PARSER = new com.google.protobuf.AbstractParser<UserTransactionReply>() {
    @Override
    public UserTransactionReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserTransactionReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserTransactionReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<UserTransactionReply> getParserForType() {
    return PARSER;
  }

  @Override
  public UserTransactionReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

