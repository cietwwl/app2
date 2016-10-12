// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: guild/GuildWarehouseRespMsg.proto

package com.chuangyou.common.protobuf.pb.guild;

public final class GuildWarehouseRespProto {
  private GuildWarehouseRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GuildWarehouseRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:GuildWarehouseRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 操作功能号(1获取库存信息 2存入物品 3分配物品)
     * </pre>
     */
    boolean hasAction();
    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 操作功能号(1获取库存信息 2存入物品 3分配物品)
     * </pre>
     */
    int getAction();

    /**
     * <code>optional int32 result = 2;</code>
     *
     * <pre>
     * 操作结果(暂时没用)
     * </pre>
     */
    boolean hasResult();
    /**
     * <code>optional int32 result = 2;</code>
     *
     * <pre>
     * 操作结果(暂时没用)
     * </pre>
     */
    int getResult();

    /**
     * <code>optional int32 flag = 3;</code>
     *
     * <pre>
     * 分包标记 0第一个包 1中间包 2最后一个包
     * </pre>
     */
    boolean hasFlag();
    /**
     * <code>optional int32 flag = 3;</code>
     *
     * <pre>
     * 分包标记 0第一个包 1中间包 2最后一个包
     * </pre>
     */
    int getFlag();

    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    java.util.List<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg> 
        getItemList();
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg getItem(int index);
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    int getItemCount();
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    java.util.List<? extends com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder> 
        getItemOrBuilderList();
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder getItemOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code GuildWarehouseRespMsg}
   *
   * <pre>
   *帮派仓库操作回复
   * </pre>
   */
  public static final class GuildWarehouseRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:GuildWarehouseRespMsg)
      GuildWarehouseRespMsgOrBuilder {
    // Use GuildWarehouseRespMsg.newBuilder() to construct.
    private GuildWarehouseRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private GuildWarehouseRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final GuildWarehouseRespMsg defaultInstance;
    public static GuildWarehouseRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public GuildWarehouseRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private GuildWarehouseRespMsg(
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
            case 16: {
              bitField0_ |= 0x00000002;
              result_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              flag_ = input.readInt32();
              break;
            }
            case 34: {
              if (!((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
                item_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg>();
                mutable_bitField0_ |= 0x00000008;
              }
              item_.add(input.readMessage(com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.PARSER, extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000008) == 0x00000008)) {
          item_ = java.util.Collections.unmodifiableList(item_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.internal_static_GuildWarehouseRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.internal_static_GuildWarehouseRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.class, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<GuildWarehouseRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<GuildWarehouseRespMsg>() {
      public GuildWarehouseRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GuildWarehouseRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<GuildWarehouseRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ACTION_FIELD_NUMBER = 1;
    private int action_;
    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 操作功能号(1获取库存信息 2存入物品 3分配物品)
     * </pre>
     */
    public boolean hasAction() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 action = 1;</code>
     *
     * <pre>
     * 操作功能号(1获取库存信息 2存入物品 3分配物品)
     * </pre>
     */
    public int getAction() {
      return action_;
    }

    public static final int RESULT_FIELD_NUMBER = 2;
    private int result_;
    /**
     * <code>optional int32 result = 2;</code>
     *
     * <pre>
     * 操作结果(暂时没用)
     * </pre>
     */
    public boolean hasResult() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 result = 2;</code>
     *
     * <pre>
     * 操作结果(暂时没用)
     * </pre>
     */
    public int getResult() {
      return result_;
    }

    public static final int FLAG_FIELD_NUMBER = 3;
    private int flag_;
    /**
     * <code>optional int32 flag = 3;</code>
     *
     * <pre>
     * 分包标记 0第一个包 1中间包 2最后一个包
     * </pre>
     */
    public boolean hasFlag() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 flag = 3;</code>
     *
     * <pre>
     * 分包标记 0第一个包 1中间包 2最后一个包
     * </pre>
     */
    public int getFlag() {
      return flag_;
    }

    public static final int ITEM_FIELD_NUMBER = 4;
    private java.util.List<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg> item_;
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    public java.util.List<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg> getItemList() {
      return item_;
    }
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    public java.util.List<? extends com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder> 
        getItemOrBuilderList() {
      return item_;
    }
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    public int getItemCount() {
      return item_.size();
    }
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg getItem(int index) {
      return item_.get(index);
    }
    /**
     * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
     *
     * <pre>
     * 操作影响到的物品信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder getItemOrBuilder(
        int index) {
      return item_.get(index);
    }

    private void initFields() {
      action_ = 0;
      result_ = 0;
      flag_ = 0;
      item_ = java.util.Collections.emptyList();
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
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, result_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, flag_);
      }
      for (int i = 0; i < item_.size(); i++) {
        output.writeMessage(4, item_.get(i));
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
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, result_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, flag_);
      }
      for (int i = 0; i < item_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(4, item_.get(i));
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

    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg prototype) {
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
     * Protobuf type {@code GuildWarehouseRespMsg}
     *
     * <pre>
     *帮派仓库操作回复
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:GuildWarehouseRespMsg)
        com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.internal_static_GuildWarehouseRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.internal_static_GuildWarehouseRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.class, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.newBuilder()
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
          getItemFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        action_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        result_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        flag_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        if (itemBuilder_ == null) {
          item_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000008);
        } else {
          itemBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.internal_static_GuildWarehouseRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg build() {
        com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg result = new com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.action_ = action_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.result_ = result_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.flag_ = flag_;
        if (itemBuilder_ == null) {
          if (((bitField0_ & 0x00000008) == 0x00000008)) {
            item_ = java.util.Collections.unmodifiableList(item_);
            bitField0_ = (bitField0_ & ~0x00000008);
          }
          result.item_ = item_;
        } else {
          result.item_ = itemBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg.getDefaultInstance()) return this;
        if (other.hasAction()) {
          setAction(other.getAction());
        }
        if (other.hasResult()) {
          setResult(other.getResult());
        }
        if (other.hasFlag()) {
          setFlag(other.getFlag());
        }
        if (itemBuilder_ == null) {
          if (!other.item_.isEmpty()) {
            if (item_.isEmpty()) {
              item_ = other.item_;
              bitField0_ = (bitField0_ & ~0x00000008);
            } else {
              ensureItemIsMutable();
              item_.addAll(other.item_);
            }
            onChanged();
          }
        } else {
          if (!other.item_.isEmpty()) {
            if (itemBuilder_.isEmpty()) {
              itemBuilder_.dispose();
              itemBuilder_ = null;
              item_ = other.item_;
              bitField0_ = (bitField0_ & ~0x00000008);
              itemBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getItemFieldBuilder() : null;
            } else {
              itemBuilder_.addAllMessages(other.item_);
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
        com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.guild.GuildWarehouseRespProto.GuildWarehouseRespMsg) e.getUnfinishedMessage();
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
       * 操作功能号(1获取库存信息 2存入物品 3分配物品)
       * </pre>
       */
      public boolean hasAction() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 action = 1;</code>
       *
       * <pre>
       * 操作功能号(1获取库存信息 2存入物品 3分配物品)
       * </pre>
       */
      public int getAction() {
        return action_;
      }
      /**
       * <code>optional int32 action = 1;</code>
       *
       * <pre>
       * 操作功能号(1获取库存信息 2存入物品 3分配物品)
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
       * 操作功能号(1获取库存信息 2存入物品 3分配物品)
       * </pre>
       */
      public Builder clearAction() {
        bitField0_ = (bitField0_ & ~0x00000001);
        action_ = 0;
        onChanged();
        return this;
      }

      private int result_ ;
      /**
       * <code>optional int32 result = 2;</code>
       *
       * <pre>
       * 操作结果(暂时没用)
       * </pre>
       */
      public boolean hasResult() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 result = 2;</code>
       *
       * <pre>
       * 操作结果(暂时没用)
       * </pre>
       */
      public int getResult() {
        return result_;
      }
      /**
       * <code>optional int32 result = 2;</code>
       *
       * <pre>
       * 操作结果(暂时没用)
       * </pre>
       */
      public Builder setResult(int value) {
        bitField0_ |= 0x00000002;
        result_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 result = 2;</code>
       *
       * <pre>
       * 操作结果(暂时没用)
       * </pre>
       */
      public Builder clearResult() {
        bitField0_ = (bitField0_ & ~0x00000002);
        result_ = 0;
        onChanged();
        return this;
      }

      private int flag_ ;
      /**
       * <code>optional int32 flag = 3;</code>
       *
       * <pre>
       * 分包标记 0第一个包 1中间包 2最后一个包
       * </pre>
       */
      public boolean hasFlag() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 flag = 3;</code>
       *
       * <pre>
       * 分包标记 0第一个包 1中间包 2最后一个包
       * </pre>
       */
      public int getFlag() {
        return flag_;
      }
      /**
       * <code>optional int32 flag = 3;</code>
       *
       * <pre>
       * 分包标记 0第一个包 1中间包 2最后一个包
       * </pre>
       */
      public Builder setFlag(int value) {
        bitField0_ |= 0x00000004;
        flag_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 flag = 3;</code>
       *
       * <pre>
       * 分包标记 0第一个包 1中间包 2最后一个包
       * </pre>
       */
      public Builder clearFlag() {
        bitField0_ = (bitField0_ & ~0x00000004);
        flag_ = 0;
        onChanged();
        return this;
      }

      private java.util.List<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg> item_ =
        java.util.Collections.emptyList();
      private void ensureItemIsMutable() {
        if (!((bitField0_ & 0x00000008) == 0x00000008)) {
          item_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg>(item_);
          bitField0_ |= 0x00000008;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder> itemBuilder_;

      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg> getItemList() {
        if (itemBuilder_ == null) {
          return java.util.Collections.unmodifiableList(item_);
        } else {
          return itemBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public int getItemCount() {
        if (itemBuilder_ == null) {
          return item_.size();
        } else {
          return itemBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg getItem(int index) {
        if (itemBuilder_ == null) {
          return item_.get(index);
        } else {
          return itemBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder setItem(
          int index, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg value) {
        if (itemBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureItemIsMutable();
          item_.set(index, value);
          onChanged();
        } else {
          itemBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder setItem(
          int index, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder builderForValue) {
        if (itemBuilder_ == null) {
          ensureItemIsMutable();
          item_.set(index, builderForValue.build());
          onChanged();
        } else {
          itemBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder addItem(com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg value) {
        if (itemBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureItemIsMutable();
          item_.add(value);
          onChanged();
        } else {
          itemBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder addItem(
          int index, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg value) {
        if (itemBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureItemIsMutable();
          item_.add(index, value);
          onChanged();
        } else {
          itemBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder addItem(
          com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder builderForValue) {
        if (itemBuilder_ == null) {
          ensureItemIsMutable();
          item_.add(builderForValue.build());
          onChanged();
        } else {
          itemBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder addItem(
          int index, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder builderForValue) {
        if (itemBuilder_ == null) {
          ensureItemIsMutable();
          item_.add(index, builderForValue.build());
          onChanged();
        } else {
          itemBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder addAllItem(
          java.lang.Iterable<? extends com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg> values) {
        if (itemBuilder_ == null) {
          ensureItemIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, item_);
          onChanged();
        } else {
          itemBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder clearItem() {
        if (itemBuilder_ == null) {
          item_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000008);
          onChanged();
        } else {
          itemBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public Builder removeItem(int index) {
        if (itemBuilder_ == null) {
          ensureItemIsMutable();
          item_.remove(index);
          onChanged();
        } else {
          itemBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder getItemBuilder(
          int index) {
        return getItemFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder getItemOrBuilder(
          int index) {
        if (itemBuilder_ == null) {
          return item_.get(index);  } else {
          return itemBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public java.util.List<? extends com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder> 
           getItemOrBuilderList() {
        if (itemBuilder_ != null) {
          return itemBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(item_);
        }
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder addItemBuilder() {
        return getItemFieldBuilder().addBuilder(
            com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder addItemBuilder(
          int index) {
        return getItemFieldBuilder().addBuilder(
            index, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .GuildWarehouseItemInfoMsg item = 4;</code>
       *
       * <pre>
       * 操作影响到的物品信息
       * </pre>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder> 
           getItemBuilderList() {
        return getItemFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder> 
          getItemFieldBuilder() {
        if (itemBuilder_ == null) {
          itemBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsg.Builder, com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.GuildWarehouseItemInfoMsgOrBuilder>(
                  item_,
                  ((bitField0_ & 0x00000008) == 0x00000008),
                  getParentForChildren(),
                  isClean());
          item_ = null;
        }
        return itemBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:GuildWarehouseRespMsg)
    }

    static {
      defaultInstance = new GuildWarehouseRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:GuildWarehouseRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GuildWarehouseRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GuildWarehouseRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!guild/GuildWarehouseRespMsg.proto\032%gui" +
      "ld/GuildWarehouseItemInfoMsg.proto\"o\n\025Gu" +
      "ildWarehouseRespMsg\022\016\n\006action\030\001 \001(\005\022\016\n\006r" +
      "esult\030\002 \001(\005\022\014\n\004flag\030\003 \001(\005\022(\n\004item\030\004 \003(\0132" +
      "\032.GuildWarehouseItemInfoMsgBA\n&com.chuan" +
      "gyou.common.protobuf.pb.guildB\027GuildWare" +
      "houseRespProto"
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
          com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.getDescriptor(),
        }, assigner);
    internal_static_GuildWarehouseRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GuildWarehouseRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_GuildWarehouseRespMsg_descriptor,
        new java.lang.String[] { "Action", "Result", "Flag", "Item", });
    com.chuangyou.common.protobuf.pb.guild.GuildWarehouseItemInfoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
