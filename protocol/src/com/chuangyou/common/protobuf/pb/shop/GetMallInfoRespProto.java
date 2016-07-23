// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: shop/GetMallInfoRespMsg.proto

package com.chuangyou.common.protobuf.pb.shop;

public final class GetMallInfoRespProto {
  private GetMallInfoRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GetMallInfoRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:GetMallInfoRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 resultType = 1;</code>
     *
     * <pre>
     * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
     * </pre>
     */
    boolean hasResultType();
    /**
     * <code>required int32 resultType = 1;</code>
     *
     * <pre>
     * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
     * </pre>
     */
    int getResultType();

    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    java.util.List<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg> 
        getInfosList();
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg getInfos(int index);
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    int getInfosCount();
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    java.util.List<? extends com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder> 
        getInfosOrBuilderList();
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder getInfosOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code GetMallInfoRespMsg}
   *
   * <pre>
   *请求商城信息返回
   * </pre>
   */
  public static final class GetMallInfoRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:GetMallInfoRespMsg)
      GetMallInfoRespMsgOrBuilder {
    // Use GetMallInfoRespMsg.newBuilder() to construct.
    private GetMallInfoRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private GetMallInfoRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final GetMallInfoRespMsg defaultInstance;
    public static GetMallInfoRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public GetMallInfoRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private GetMallInfoRespMsg(
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
            case 8: {
              bitField0_ |= 0x00000001;
              resultType_ = input.readInt32();
              break;
            }
            case 18: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                infos_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg>();
                mutable_bitField0_ |= 0x00000002;
              }
              infos_.add(input.readMessage(com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.PARSER, extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          infos_ = java.util.Collections.unmodifiableList(infos_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.internal_static_GetMallInfoRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.internal_static_GetMallInfoRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.class, com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<GetMallInfoRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<GetMallInfoRespMsg>() {
      public GetMallInfoRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetMallInfoRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<GetMallInfoRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int RESULTTYPE_FIELD_NUMBER = 1;
    private int resultType_;
    /**
     * <code>required int32 resultType = 1;</code>
     *
     * <pre>
     * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
     * </pre>
     */
    public boolean hasResultType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 resultType = 1;</code>
     *
     * <pre>
     * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
     * </pre>
     */
    public int getResultType() {
      return resultType_;
    }

    public static final int INFOS_FIELD_NUMBER = 2;
    private java.util.List<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg> infos_;
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    public java.util.List<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg> getInfosList() {
      return infos_;
    }
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    public java.util.List<? extends com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder> 
        getInfosOrBuilderList() {
      return infos_;
    }
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    public int getInfosCount() {
      return infos_.size();
    }
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg getInfos(int index) {
      return infos_.get(index);
    }
    /**
     * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
     *
     * <pre>
     *信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder getInfosOrBuilder(
        int index) {
      return infos_.get(index);
    }

    private void initFields() {
      resultType_ = 0;
      infos_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasResultType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      for (int i = 0; i < getInfosCount(); i++) {
        if (!getInfos(i).isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, resultType_);
      }
      for (int i = 0; i < infos_.size(); i++) {
        output.writeMessage(2, infos_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, resultType_);
      }
      for (int i = 0; i < infos_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, infos_.get(i));
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

    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg prototype) {
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
     * Protobuf type {@code GetMallInfoRespMsg}
     *
     * <pre>
     *请求商城信息返回
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:GetMallInfoRespMsg)
        com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.internal_static_GetMallInfoRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.internal_static_GetMallInfoRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.class, com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.newBuilder()
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
          getInfosFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        resultType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        if (infosBuilder_ == null) {
          infos_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          infosBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.internal_static_GetMallInfoRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg build() {
        com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg result = new com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.resultType_ = resultType_;
        if (infosBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002)) {
            infos_ = java.util.Collections.unmodifiableList(infos_);
            bitField0_ = (bitField0_ & ~0x00000002);
          }
          result.infos_ = infos_;
        } else {
          result.infos_ = infosBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg.getDefaultInstance()) return this;
        if (other.hasResultType()) {
          setResultType(other.getResultType());
        }
        if (infosBuilder_ == null) {
          if (!other.infos_.isEmpty()) {
            if (infos_.isEmpty()) {
              infos_ = other.infos_;
              bitField0_ = (bitField0_ & ~0x00000002);
            } else {
              ensureInfosIsMutable();
              infos_.addAll(other.infos_);
            }
            onChanged();
          }
        } else {
          if (!other.infos_.isEmpty()) {
            if (infosBuilder_.isEmpty()) {
              infosBuilder_.dispose();
              infosBuilder_ = null;
              infos_ = other.infos_;
              bitField0_ = (bitField0_ & ~0x00000002);
              infosBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getInfosFieldBuilder() : null;
            } else {
              infosBuilder_.addAllMessages(other.infos_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasResultType()) {
          
          return false;
        }
        for (int i = 0; i < getInfosCount(); i++) {
          if (!getInfos(i).isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.shop.GetMallInfoRespProto.GetMallInfoRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int resultType_ ;
      /**
       * <code>required int32 resultType = 1;</code>
       *
       * <pre>
       * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
       * </pre>
       */
      public boolean hasResultType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 resultType = 1;</code>
       *
       * <pre>
       * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
       * </pre>
       */
      public int getResultType() {
        return resultType_;
      }
      /**
       * <code>required int32 resultType = 1;</code>
       *
       * <pre>
       * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
       * </pre>
       */
      public Builder setResultType(int value) {
        bitField0_ |= 0x00000001;
        resultType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 resultType = 1;</code>
       *
       * <pre>
       * 1:有数据回复   2：无数据回复（ 当用更新去请求的时候。数据没有发生过变化。就会回复2）
       * </pre>
       */
      public Builder clearResultType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        resultType_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg> infos_ =
        java.util.Collections.emptyList();
      private void ensureInfosIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          infos_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg>(infos_);
          bitField0_ |= 0x00000002;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder> infosBuilder_;

      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg> getInfosList() {
        if (infosBuilder_ == null) {
          return java.util.Collections.unmodifiableList(infos_);
        } else {
          return infosBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public int getInfosCount() {
        if (infosBuilder_ == null) {
          return infos_.size();
        } else {
          return infosBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg getInfos(int index) {
        if (infosBuilder_ == null) {
          return infos_.get(index);
        } else {
          return infosBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder setInfos(
          int index, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg value) {
        if (infosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureInfosIsMutable();
          infos_.set(index, value);
          onChanged();
        } else {
          infosBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder setInfos(
          int index, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder builderForValue) {
        if (infosBuilder_ == null) {
          ensureInfosIsMutable();
          infos_.set(index, builderForValue.build());
          onChanged();
        } else {
          infosBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder addInfos(com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg value) {
        if (infosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureInfosIsMutable();
          infos_.add(value);
          onChanged();
        } else {
          infosBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder addInfos(
          int index, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg value) {
        if (infosBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureInfosIsMutable();
          infos_.add(index, value);
          onChanged();
        } else {
          infosBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder addInfos(
          com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder builderForValue) {
        if (infosBuilder_ == null) {
          ensureInfosIsMutable();
          infos_.add(builderForValue.build());
          onChanged();
        } else {
          infosBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder addInfos(
          int index, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder builderForValue) {
        if (infosBuilder_ == null) {
          ensureInfosIsMutable();
          infos_.add(index, builderForValue.build());
          onChanged();
        } else {
          infosBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder addAllInfos(
          java.lang.Iterable<? extends com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg> values) {
        if (infosBuilder_ == null) {
          ensureInfosIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, infos_);
          onChanged();
        } else {
          infosBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder clearInfos() {
        if (infosBuilder_ == null) {
          infos_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
          onChanged();
        } else {
          infosBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public Builder removeInfos(int index) {
        if (infosBuilder_ == null) {
          ensureInfosIsMutable();
          infos_.remove(index);
          onChanged();
        } else {
          infosBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder getInfosBuilder(
          int index) {
        return getInfosFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder getInfosOrBuilder(
          int index) {
        if (infosBuilder_ == null) {
          return infos_.get(index);  } else {
          return infosBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public java.util.List<? extends com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder> 
           getInfosOrBuilderList() {
        if (infosBuilder_ != null) {
          return infosBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(infos_);
        }
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder addInfosBuilder() {
        return getInfosFieldBuilder().addBuilder(
            com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder addInfosBuilder(
          int index) {
        return getInfosFieldBuilder().addBuilder(
            index, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .GetNpcShopInfoRespMsg infos = 2;</code>
       *
       * <pre>
       *信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder> 
           getInfosBuilderList() {
        return getInfosFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder> 
          getInfosFieldBuilder() {
        if (infosBuilder_ == null) {
          infosBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsg.Builder, com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.GetNpcShopInfoRespMsgOrBuilder>(
                  infos_,
                  ((bitField0_ & 0x00000002) == 0x00000002),
                  getParentForChildren(),
                  isClean());
          infos_ = null;
        }
        return infosBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:GetMallInfoRespMsg)
    }

    static {
      defaultInstance = new GetMallInfoRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:GetMallInfoRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetMallInfoRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GetMallInfoRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035shop/GetMallInfoRespMsg.proto\032 shop/Ge" +
      "tNpcShopInfoRespMsg.proto\"O\n\022GetMallInfo" +
      "RespMsg\022\022\n\nresultType\030\001 \002(\005\022%\n\005infos\030\002 \003" +
      "(\0132\026.GetNpcShopInfoRespMsgB=\n%com.chuang" +
      "you.common.protobuf.pb.shopB\024GetMallInfo" +
      "RespProto"
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
          com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.getDescriptor(),
        }, assigner);
    internal_static_GetMallInfoRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GetMallInfoRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_GetMallInfoRespMsg_descriptor,
        new java.lang.String[] { "ResultType", "Infos", });
    com.chuangyou.common.protobuf.pb.shop.GetNpcShopInfoRespProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
