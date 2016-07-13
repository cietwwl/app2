// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: magicwp/MagicwpGetInfoRespMsg.proto

package com.chuangyou.common.protobuf.pb.magicwp;

public final class MagicwpGetInfoRespProto {
  private MagicwpGetInfoRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface MagicwpGetInfoRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:MagicwpGetInfoRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 magicwpId = 1;</code>
     *
     * <pre>
     * 法宝ID
     * </pre>
     */
    boolean hasMagicwpId();
    /**
     * <code>optional int32 magicwpId = 1;</code>
     *
     * <pre>
     * 法宝ID
     * </pre>
     */
    int getMagicwpId();

    /**
     * <code>optional int32 useDanNum = 2;</code>
     *
     * <pre>
     * 使用属性丹数量
     * </pre>
     */
    boolean hasUseDanNum();
    /**
     * <code>optional int32 useDanNum = 2;</code>
     *
     * <pre>
     * 使用属性丹数量
     * </pre>
     */
    int getUseDanNum();

    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    java.util.List<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg> 
        getInfosList();
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg getInfos(int index);
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    int getInfosCount();
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    java.util.List<? extends com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder> 
        getInfosOrBuilderList();
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder getInfosOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code MagicwpGetInfoRespMsg}
   */
  public static final class MagicwpGetInfoRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:MagicwpGetInfoRespMsg)
      MagicwpGetInfoRespMsgOrBuilder {
    // Use MagicwpGetInfoRespMsg.newBuilder() to construct.
    private MagicwpGetInfoRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private MagicwpGetInfoRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final MagicwpGetInfoRespMsg defaultInstance;
    public static MagicwpGetInfoRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public MagicwpGetInfoRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private MagicwpGetInfoRespMsg(
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
              magicwpId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              useDanNum_ = input.readInt32();
              break;
            }
            case 26: {
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                infos_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg>();
                mutable_bitField0_ |= 0x00000004;
              }
              infos_.add(input.readMessage(com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.PARSER, extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          infos_ = java.util.Collections.unmodifiableList(infos_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.internal_static_MagicwpGetInfoRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.internal_static_MagicwpGetInfoRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.class, com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<MagicwpGetInfoRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<MagicwpGetInfoRespMsg>() {
      public MagicwpGetInfoRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MagicwpGetInfoRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<MagicwpGetInfoRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int MAGICWPID_FIELD_NUMBER = 1;
    private int magicwpId_;
    /**
     * <code>optional int32 magicwpId = 1;</code>
     *
     * <pre>
     * 法宝ID
     * </pre>
     */
    public boolean hasMagicwpId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 magicwpId = 1;</code>
     *
     * <pre>
     * 法宝ID
     * </pre>
     */
    public int getMagicwpId() {
      return magicwpId_;
    }

    public static final int USEDANNUM_FIELD_NUMBER = 2;
    private int useDanNum_;
    /**
     * <code>optional int32 useDanNum = 2;</code>
     *
     * <pre>
     * 使用属性丹数量
     * </pre>
     */
    public boolean hasUseDanNum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 useDanNum = 2;</code>
     *
     * <pre>
     * 使用属性丹数量
     * </pre>
     */
    public int getUseDanNum() {
      return useDanNum_;
    }

    public static final int INFOS_FIELD_NUMBER = 3;
    private java.util.List<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg> infos_;
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    public java.util.List<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg> getInfosList() {
      return infos_;
    }
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    public java.util.List<? extends com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder> 
        getInfosOrBuilderList() {
      return infos_;
    }
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    public int getInfosCount() {
      return infos_.size();
    }
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg getInfos(int index) {
      return infos_.get(index);
    }
    /**
     * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
     *
     * <pre>
     * 法宝信息列表
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder getInfosOrBuilder(
        int index) {
      return infos_.get(index);
    }

    private void initFields() {
      magicwpId_ = 0;
      useDanNum_ = 0;
      infos_ = java.util.Collections.emptyList();
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
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, magicwpId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, useDanNum_);
      }
      for (int i = 0; i < infos_.size(); i++) {
        output.writeMessage(3, infos_.get(i));
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
          .computeInt32Size(1, magicwpId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, useDanNum_);
      }
      for (int i = 0; i < infos_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, infos_.get(i));
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

    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg prototype) {
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
     * Protobuf type {@code MagicwpGetInfoRespMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:MagicwpGetInfoRespMsg)
        com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.internal_static_MagicwpGetInfoRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.internal_static_MagicwpGetInfoRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.class, com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.newBuilder()
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
        magicwpId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        useDanNum_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        if (infosBuilder_ == null) {
          infos_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000004);
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
        return com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.internal_static_MagicwpGetInfoRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg build() {
        com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg result = new com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.magicwpId_ = magicwpId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.useDanNum_ = useDanNum_;
        if (infosBuilder_ == null) {
          if (((bitField0_ & 0x00000004) == 0x00000004)) {
            infos_ = java.util.Collections.unmodifiableList(infos_);
            bitField0_ = (bitField0_ & ~0x00000004);
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
        if (other instanceof com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg.getDefaultInstance()) return this;
        if (other.hasMagicwpId()) {
          setMagicwpId(other.getMagicwpId());
        }
        if (other.hasUseDanNum()) {
          setUseDanNum(other.getUseDanNum());
        }
        if (infosBuilder_ == null) {
          if (!other.infos_.isEmpty()) {
            if (infos_.isEmpty()) {
              infos_ = other.infos_;
              bitField0_ = (bitField0_ & ~0x00000004);
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
              bitField0_ = (bitField0_ & ~0x00000004);
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
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int magicwpId_ ;
      /**
       * <code>optional int32 magicwpId = 1;</code>
       *
       * <pre>
       * 法宝ID
       * </pre>
       */
      public boolean hasMagicwpId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 magicwpId = 1;</code>
       *
       * <pre>
       * 法宝ID
       * </pre>
       */
      public int getMagicwpId() {
        return magicwpId_;
      }
      /**
       * <code>optional int32 magicwpId = 1;</code>
       *
       * <pre>
       * 法宝ID
       * </pre>
       */
      public Builder setMagicwpId(int value) {
        bitField0_ |= 0x00000001;
        magicwpId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 magicwpId = 1;</code>
       *
       * <pre>
       * 法宝ID
       * </pre>
       */
      public Builder clearMagicwpId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        magicwpId_ = 0;
        onChanged();
        return this;
      }

      private int useDanNum_ ;
      /**
       * <code>optional int32 useDanNum = 2;</code>
       *
       * <pre>
       * 使用属性丹数量
       * </pre>
       */
      public boolean hasUseDanNum() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 useDanNum = 2;</code>
       *
       * <pre>
       * 使用属性丹数量
       * </pre>
       */
      public int getUseDanNum() {
        return useDanNum_;
      }
      /**
       * <code>optional int32 useDanNum = 2;</code>
       *
       * <pre>
       * 使用属性丹数量
       * </pre>
       */
      public Builder setUseDanNum(int value) {
        bitField0_ |= 0x00000002;
        useDanNum_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 useDanNum = 2;</code>
       *
       * <pre>
       * 使用属性丹数量
       * </pre>
       */
      public Builder clearUseDanNum() {
        bitField0_ = (bitField0_ & ~0x00000002);
        useDanNum_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg> infos_ =
        java.util.Collections.emptyList();
      private void ensureInfosIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          infos_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg>(infos_);
          bitField0_ |= 0x00000004;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder> infosBuilder_;

      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg> getInfosList() {
        if (infosBuilder_ == null) {
          return java.util.Collections.unmodifiableList(infos_);
        } else {
          return infosBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg getInfos(int index) {
        if (infosBuilder_ == null) {
          return infos_.get(index);
        } else {
          return infosBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder setInfos(
          int index, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg value) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder setInfos(
          int index, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder builderForValue) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder addInfos(com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg value) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder addInfos(
          int index, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg value) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder addInfos(
          com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder builderForValue) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder addInfos(
          int index, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder builderForValue) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder addAllInfos(
          java.lang.Iterable<? extends com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg> values) {
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public Builder clearInfos() {
        if (infosBuilder_ == null) {
          infos_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000004);
          onChanged();
        } else {
          infosBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
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
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder getInfosBuilder(
          int index) {
        return getInfosFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder getInfosOrBuilder(
          int index) {
        if (infosBuilder_ == null) {
          return infos_.get(index);  } else {
          return infosBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public java.util.List<? extends com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder> 
           getInfosOrBuilderList() {
        if (infosBuilder_ != null) {
          return infosBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(infos_);
        }
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder addInfosBuilder() {
        return getInfosFieldBuilder().addBuilder(
            com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder addInfosBuilder(
          int index) {
        return getInfosFieldBuilder().addBuilder(
            index, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .MagicwpInfoBeanMsg infos = 3;</code>
       *
       * <pre>
       * 法宝信息列表
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder> 
           getInfosBuilderList() {
        return getInfosFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder> 
          getInfosFieldBuilder() {
        if (infosBuilder_ == null) {
          infosBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg.Builder, com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsgOrBuilder>(
                  infos_,
                  ((bitField0_ & 0x00000004) == 0x00000004),
                  getParentForChildren(),
                  isClean());
          infos_ = null;
        }
        return infosBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:MagicwpGetInfoRespMsg)
    }

    static {
      defaultInstance = new MagicwpGetInfoRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:MagicwpGetInfoRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MagicwpGetInfoRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_MagicwpGetInfoRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#magicwp/MagicwpGetInfoRespMsg.proto\032 m" +
      "agicwp/MagicwpInfoBeanMsg.proto\"a\n\025Magic" +
      "wpGetInfoRespMsg\022\021\n\tmagicwpId\030\001 \001(\005\022\021\n\tu" +
      "seDanNum\030\002 \001(\005\022\"\n\005infos\030\003 \003(\0132\023.MagicwpI" +
      "nfoBeanMsgBC\n(com.chuangyou.common.proto" +
      "buf.pb.magicwpB\027MagicwpGetInfoRespProto"
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
          com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.getDescriptor(),
        }, assigner);
    internal_static_MagicwpGetInfoRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_MagicwpGetInfoRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_MagicwpGetInfoRespMsg_descriptor,
        new java.lang.String[] { "MagicwpId", "UseDanNum", "Infos", });
    com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
