// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: equip/EquipBarInfoRespMsg.proto

package com.chuangyou.common.protobuf.pb.equip;

public final class EquipBarInfoRespProto {
  private EquipBarInfoRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface EquipBarInfoRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:EquipBarInfoRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
     * </pre>
     */
    boolean hasAction();
    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
     * </pre>
     */
    int getAction();

    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    java.util.List<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg> 
        getEquipBarList();
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg getEquipBar(int index);
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    int getEquipBarCount();
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    java.util.List<? extends com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder> 
        getEquipBarOrBuilderList();
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder getEquipBarOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code EquipBarInfoRespMsg}
   *
   * <pre>
   *返回栏位信息
   * </pre>
   */
  public static final class EquipBarInfoRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:EquipBarInfoRespMsg)
      EquipBarInfoRespMsgOrBuilder {
    // Use EquipBarInfoRespMsg.newBuilder() to construct.
    private EquipBarInfoRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private EquipBarInfoRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final EquipBarInfoRespMsg defaultInstance;
    public static EquipBarInfoRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public EquipBarInfoRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private EquipBarInfoRespMsg(
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
              action_ = input.readInt32();
              break;
            }
            case 18: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                equipBar_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg>();
                mutable_bitField0_ |= 0x00000002;
              }
              equipBar_.add(input.readMessage(com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.PARSER, extensionRegistry));
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
          equipBar_ = java.util.Collections.unmodifiableList(equipBar_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.internal_static_EquipBarInfoRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.internal_static_EquipBarInfoRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.class, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<EquipBarInfoRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<EquipBarInfoRespMsg>() {
      public EquipBarInfoRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new EquipBarInfoRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<EquipBarInfoRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ACTION_FIELD_NUMBER = 1;
    private int action_;
    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
     * </pre>
     */
    public boolean hasAction() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
     * </pre>
     */
    public int getAction() {
      return action_;
    }

    public static final int EQUIPBAR_FIELD_NUMBER = 2;
    private java.util.List<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg> equipBar_;
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    public java.util.List<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg> getEquipBarList() {
      return equipBar_;
    }
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    public java.util.List<? extends com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder> 
        getEquipBarOrBuilderList() {
      return equipBar_;
    }
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    public int getEquipBarCount() {
      return equipBar_.size();
    }
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg getEquipBar(int index) {
      return equipBar_.get(index);
    }
    /**
     * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
     *
     * <pre>
     * 栏位信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder getEquipBarOrBuilder(
        int index) {
      return equipBar_.get(index);
    }

    private void initFields() {
      action_ = 0;
      equipBar_ = java.util.Collections.emptyList();
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
        output.writeInt32(1, action_);
      }
      for (int i = 0; i < equipBar_.size(); i++) {
        output.writeMessage(2, equipBar_.get(i));
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
          .computeInt32Size(1, action_);
      }
      for (int i = 0; i < equipBar_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, equipBar_.get(i));
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

    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg prototype) {
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
     * Protobuf type {@code EquipBarInfoRespMsg}
     *
     * <pre>
     *返回栏位信息
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:EquipBarInfoRespMsg)
        com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.internal_static_EquipBarInfoRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.internal_static_EquipBarInfoRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.class, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.newBuilder()
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
          getEquipBarFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        action_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        if (equipBarBuilder_ == null) {
          equipBar_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          equipBarBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.internal_static_EquipBarInfoRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg build() {
        com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg result = new com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.action_ = action_;
        if (equipBarBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002)) {
            equipBar_ = java.util.Collections.unmodifiableList(equipBar_);
            bitField0_ = (bitField0_ & ~0x00000002);
          }
          result.equipBar_ = equipBar_;
        } else {
          result.equipBar_ = equipBarBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg.getDefaultInstance()) return this;
        if (other.hasAction()) {
          setAction(other.getAction());
        }
        if (equipBarBuilder_ == null) {
          if (!other.equipBar_.isEmpty()) {
            if (equipBar_.isEmpty()) {
              equipBar_ = other.equipBar_;
              bitField0_ = (bitField0_ & ~0x00000002);
            } else {
              ensureEquipBarIsMutable();
              equipBar_.addAll(other.equipBar_);
            }
            onChanged();
          }
        } else {
          if (!other.equipBar_.isEmpty()) {
            if (equipBarBuilder_.isEmpty()) {
              equipBarBuilder_.dispose();
              equipBarBuilder_ = null;
              equipBar_ = other.equipBar_;
              bitField0_ = (bitField0_ & ~0x00000002);
              equipBarBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getEquipBarFieldBuilder() : null;
            } else {
              equipBarBuilder_.addAllMessages(other.equipBar_);
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
        com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.equip.EquipBarInfoRespProto.EquipBarInfoRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int action_ ;
      /**
       * <code>optional int32 action = 1;</code>
       *
       * <pre>
       * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
       * </pre>
       */
      public boolean hasAction() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 action = 1;</code>
       *
       * <pre>
       * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
       * </pre>
       */
      public int getAction() {
        return action_;
      }
      /**
       * <code>optional int32 action = 1;</code>
       *
       * <pre>
       * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
       * </pre>
       */
      public Builder setAction(int value) {
        bitField0_ |= 0x00000001;
        action_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 action = 1;</code>
       *
       * <pre>
       * 功能号(1返回所有栏位信息列表 2栏位等级更新 3栏位加持更新)
       * </pre>
       */
      public Builder clearAction() {
        bitField0_ = (bitField0_ & ~0x00000001);
        action_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg> equipBar_ =
        java.util.Collections.emptyList();
      private void ensureEquipBarIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          equipBar_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg>(equipBar_);
          bitField0_ |= 0x00000002;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder> equipBarBuilder_;

      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg> getEquipBarList() {
        if (equipBarBuilder_ == null) {
          return java.util.Collections.unmodifiableList(equipBar_);
        } else {
          return equipBarBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public int getEquipBarCount() {
        if (equipBarBuilder_ == null) {
          return equipBar_.size();
        } else {
          return equipBarBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg getEquipBar(int index) {
        if (equipBarBuilder_ == null) {
          return equipBar_.get(index);
        } else {
          return equipBarBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder setEquipBar(
          int index, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg value) {
        if (equipBarBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureEquipBarIsMutable();
          equipBar_.set(index, value);
          onChanged();
        } else {
          equipBarBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder setEquipBar(
          int index, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder builderForValue) {
        if (equipBarBuilder_ == null) {
          ensureEquipBarIsMutable();
          equipBar_.set(index, builderForValue.build());
          onChanged();
        } else {
          equipBarBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder addEquipBar(com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg value) {
        if (equipBarBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureEquipBarIsMutable();
          equipBar_.add(value);
          onChanged();
        } else {
          equipBarBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder addEquipBar(
          int index, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg value) {
        if (equipBarBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureEquipBarIsMutable();
          equipBar_.add(index, value);
          onChanged();
        } else {
          equipBarBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder addEquipBar(
          com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder builderForValue) {
        if (equipBarBuilder_ == null) {
          ensureEquipBarIsMutable();
          equipBar_.add(builderForValue.build());
          onChanged();
        } else {
          equipBarBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder addEquipBar(
          int index, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder builderForValue) {
        if (equipBarBuilder_ == null) {
          ensureEquipBarIsMutable();
          equipBar_.add(index, builderForValue.build());
          onChanged();
        } else {
          equipBarBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder addAllEquipBar(
          java.lang.Iterable<? extends com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg> values) {
        if (equipBarBuilder_ == null) {
          ensureEquipBarIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, equipBar_);
          onChanged();
        } else {
          equipBarBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder clearEquipBar() {
        if (equipBarBuilder_ == null) {
          equipBar_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
          onChanged();
        } else {
          equipBarBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public Builder removeEquipBar(int index) {
        if (equipBarBuilder_ == null) {
          ensureEquipBarIsMutable();
          equipBar_.remove(index);
          onChanged();
        } else {
          equipBarBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder getEquipBarBuilder(
          int index) {
        return getEquipBarFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder getEquipBarOrBuilder(
          int index) {
        if (equipBarBuilder_ == null) {
          return equipBar_.get(index);  } else {
          return equipBarBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public java.util.List<? extends com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder> 
           getEquipBarOrBuilderList() {
        if (equipBarBuilder_ != null) {
          return equipBarBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(equipBar_);
        }
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder addEquipBarBuilder() {
        return getEquipBarFieldBuilder().addBuilder(
            com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder addEquipBarBuilder(
          int index) {
        return getEquipBarFieldBuilder().addBuilder(
            index, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .EquipBarInfoMsg equipBar = 2;</code>
       *
       * <pre>
       * 栏位信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder> 
           getEquipBarBuilderList() {
        return getEquipBarFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder> 
          getEquipBarFieldBuilder() {
        if (equipBarBuilder_ == null) {
          equipBarBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsg.Builder, com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.EquipBarInfoMsgOrBuilder>(
                  equipBar_,
                  ((bitField0_ & 0x00000002) == 0x00000002),
                  getParentForChildren(),
                  isClean());
          equipBar_ = null;
        }
        return equipBarBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:EquipBarInfoRespMsg)
    }

    static {
      defaultInstance = new EquipBarInfoRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:EquipBarInfoRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EquipBarInfoRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_EquipBarInfoRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037equip/EquipBarInfoRespMsg.proto\032\033equip" +
      "/EquipBarInfoMsg.proto\"I\n\023EquipBarInfoRe" +
      "spMsg\022\016\n\006action\030\001 \001(\005\022\"\n\010equipBar\030\002 \003(\0132" +
      "\020.EquipBarInfoMsgB?\n&com.chuangyou.commo" +
      "n.protobuf.pb.equipB\025EquipBarInfoRespPro" +
      "to"
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
          com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.getDescriptor(),
        }, assigner);
    internal_static_EquipBarInfoRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EquipBarInfoRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_EquipBarInfoRespMsg_descriptor,
        new java.lang.String[] { "Action", "EquipBar", });
    com.chuangyou.common.protobuf.pb.equip.EquipBarInfoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
