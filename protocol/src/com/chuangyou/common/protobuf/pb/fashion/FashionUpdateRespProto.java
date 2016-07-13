// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fashion/FashionUpdateRespMsg.proto

package com.chuangyou.common.protobuf.pb.fashion;

public final class FashionUpdateRespProto {
  private FashionUpdateRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface FashionUpdateRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:FashionUpdateRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    java.util.List<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg> 
        getFashionList();
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg getFashion(int index);
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    int getFashionCount();
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    java.util.List<? extends com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder> 
        getFashionOrBuilderList();
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder getFashionOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code FashionUpdateRespMsg}
   */
  public static final class FashionUpdateRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:FashionUpdateRespMsg)
      FashionUpdateRespMsgOrBuilder {
    // Use FashionUpdateRespMsg.newBuilder() to construct.
    private FashionUpdateRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private FashionUpdateRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final FashionUpdateRespMsg defaultInstance;
    public static FashionUpdateRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public FashionUpdateRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private FashionUpdateRespMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                fashion_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg>();
                mutable_bitField0_ |= 0x00000001;
              }
              fashion_.add(input.readMessage(com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.PARSER, extensionRegistry));
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          fashion_ = java.util.Collections.unmodifiableList(fashion_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.internal_static_FashionUpdateRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.internal_static_FashionUpdateRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.class, com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<FashionUpdateRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<FashionUpdateRespMsg>() {
      public FashionUpdateRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new FashionUpdateRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<FashionUpdateRespMsg> getParserForType() {
      return PARSER;
    }

    public static final int FASHION_FIELD_NUMBER = 1;
    private java.util.List<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg> fashion_;
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    public java.util.List<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg> getFashionList() {
      return fashion_;
    }
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    public java.util.List<? extends com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder> 
        getFashionOrBuilderList() {
      return fashion_;
    }
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    public int getFashionCount() {
      return fashion_.size();
    }
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg getFashion(int index) {
      return fashion_.get(index);
    }
    /**
     * <code>repeated .FashionBeanMsg fashion = 1;</code>
     *
     * <pre>
     * 时装信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder getFashionOrBuilder(
        int index) {
      return fashion_.get(index);
    }

    private void initFields() {
      fashion_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < fashion_.size(); i++) {
        output.writeMessage(1, fashion_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < fashion_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, fashion_.get(i));
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code FashionUpdateRespMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:FashionUpdateRespMsg)
        com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.internal_static_FashionUpdateRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.internal_static_FashionUpdateRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.class, com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
          getFashionFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (fashionBuilder_ == null) {
          fashion_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          fashionBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.internal_static_FashionUpdateRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg build() {
        com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg result = new com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg(this);
        int from_bitField0_ = bitField0_;
        if (fashionBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            fashion_ = java.util.Collections.unmodifiableList(fashion_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.fashion_ = fashion_;
        } else {
          result.fashion_ = fashionBuilder_.build();
        }
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg.getDefaultInstance()) return this;
        if (fashionBuilder_ == null) {
          if (!other.fashion_.isEmpty()) {
            if (fashion_.isEmpty()) {
              fashion_ = other.fashion_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureFashionIsMutable();
              fashion_.addAll(other.fashion_);
            }
            onChanged();
          }
        } else {
          if (!other.fashion_.isEmpty()) {
            if (fashionBuilder_.isEmpty()) {
              fashionBuilder_.dispose();
              fashionBuilder_ = null;
              fashion_ = other.fashion_;
              bitField0_ = (bitField0_ & ~0x00000001);
              fashionBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getFashionFieldBuilder() : null;
            } else {
              fashionBuilder_.addAllMessages(other.fashion_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.fashion.FashionUpdateRespProto.FashionUpdateRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg> fashion_ =
        java.util.Collections.emptyList();
      private void ensureFashionIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          fashion_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg>(fashion_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder> fashionBuilder_;

      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg> getFashionList() {
        if (fashionBuilder_ == null) {
          return java.util.Collections.unmodifiableList(fashion_);
        } else {
          return fashionBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public int getFashionCount() {
        if (fashionBuilder_ == null) {
          return fashion_.size();
        } else {
          return fashionBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg getFashion(int index) {
        if (fashionBuilder_ == null) {
          return fashion_.get(index);
        } else {
          return fashionBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder setFashion(
          int index, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg value) {
        if (fashionBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFashionIsMutable();
          fashion_.set(index, value);
          onChanged();
        } else {
          fashionBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder setFashion(
          int index, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder builderForValue) {
        if (fashionBuilder_ == null) {
          ensureFashionIsMutable();
          fashion_.set(index, builderForValue.build());
          onChanged();
        } else {
          fashionBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder addFashion(com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg value) {
        if (fashionBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFashionIsMutable();
          fashion_.add(value);
          onChanged();
        } else {
          fashionBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder addFashion(
          int index, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg value) {
        if (fashionBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFashionIsMutable();
          fashion_.add(index, value);
          onChanged();
        } else {
          fashionBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder addFashion(
          com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder builderForValue) {
        if (fashionBuilder_ == null) {
          ensureFashionIsMutable();
          fashion_.add(builderForValue.build());
          onChanged();
        } else {
          fashionBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder addFashion(
          int index, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder builderForValue) {
        if (fashionBuilder_ == null) {
          ensureFashionIsMutable();
          fashion_.add(index, builderForValue.build());
          onChanged();
        } else {
          fashionBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder addAllFashion(
          java.lang.Iterable<? extends com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg> values) {
        if (fashionBuilder_ == null) {
          ensureFashionIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, fashion_);
          onChanged();
        } else {
          fashionBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder clearFashion() {
        if (fashionBuilder_ == null) {
          fashion_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          fashionBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public Builder removeFashion(int index) {
        if (fashionBuilder_ == null) {
          ensureFashionIsMutable();
          fashion_.remove(index);
          onChanged();
        } else {
          fashionBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder getFashionBuilder(
          int index) {
        return getFashionFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder getFashionOrBuilder(
          int index) {
        if (fashionBuilder_ == null) {
          return fashion_.get(index);  } else {
          return fashionBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public java.util.List<? extends com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder> 
           getFashionOrBuilderList() {
        if (fashionBuilder_ != null) {
          return fashionBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(fashion_);
        }
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder addFashionBuilder() {
        return getFashionFieldBuilder().addBuilder(
            com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder addFashionBuilder(
          int index) {
        return getFashionFieldBuilder().addBuilder(
            index, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .FashionBeanMsg fashion = 1;</code>
       *
       * <pre>
       * 时装信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder> 
           getFashionBuilderList() {
        return getFashionFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder> 
          getFashionFieldBuilder() {
        if (fashionBuilder_ == null) {
          fashionBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsg.Builder, com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.FashionBeanMsgOrBuilder>(
                  fashion_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          fashion_ = null;
        }
        return fashionBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:FashionUpdateRespMsg)
    }

    static {
      defaultInstance = new FashionUpdateRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:FashionUpdateRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_FashionUpdateRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_FashionUpdateRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"fashion/FashionUpdateRespMsg.proto\032\034fa" +
      "shion/FashionBeanMsg.proto\"8\n\024FashionUpd" +
      "ateRespMsg\022 \n\007fashion\030\001 \003(\0132\017.FashionBea" +
      "nMsgBB\n(com.chuangyou.common.protobuf.pb" +
      ".fashionB\026FashionUpdateRespProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.getDescriptor(),
        }, assigner);
    internal_static_FashionUpdateRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_FashionUpdateRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_FashionUpdateRespMsg_descriptor,
        new java.lang.String[] { "Fashion", });
    com.chuangyou.common.protobuf.pb.fashion.FashionBeanProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
