// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: space/ActionLogMsg.proto

package com.chuangyou.common.protobuf.pb.space;

public final class ActionLogProto {
  private ActionLogProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ActionLogMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ActionLogMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 sendPlayerId = 1;</code>
     *
     * <pre>
     *发送者ID
     * </pre>
     */
    boolean hasSendPlayerId();
    /**
     * <code>required int64 sendPlayerId = 1;</code>
     *
     * <pre>
     *发送者ID
     * </pre>
     */
    long getSendPlayerId();

    /**
     * <code>required string sendName = 2;</code>
     *
     * <pre>
     *发送者姓名
     * </pre>
     */
    boolean hasSendName();
    /**
     * <code>required string sendName = 2;</code>
     *
     * <pre>
     *发送者姓名
     * </pre>
     */
    java.lang.String getSendName();
    /**
     * <code>required string sendName = 2;</code>
     *
     * <pre>
     *发送者姓名
     * </pre>
     */
    com.google.protobuf.ByteString
        getSendNameBytes();

    /**
     * <code>required string sendFace = 3;</code>
     *
     * <pre>
     *头像
     * </pre>
     */
    boolean hasSendFace();
    /**
     * <code>required string sendFace = 3;</code>
     *
     * <pre>
     *头像
     * </pre>
     */
    java.lang.String getSendFace();
    /**
     * <code>required string sendFace = 3;</code>
     *
     * <pre>
     *头像
     * </pre>
     */
    com.google.protobuf.ByteString
        getSendFaceBytes();

    /**
     * <code>required int32 sendLv = 4;</code>
     *
     * <pre>
     *等级
     * </pre>
     */
    boolean hasSendLv();
    /**
     * <code>required int32 sendLv = 4;</code>
     *
     * <pre>
     *等级
     * </pre>
     */
    int getSendLv();

    /**
     * <code>required int64 createDate = 5;</code>
     *
     * <pre>
     *时间 utc时间
     * </pre>
     */
    boolean hasCreateDate();
    /**
     * <code>required int64 createDate = 5;</code>
     *
     * <pre>
     *时间 utc时间
     * </pre>
     */
    long getCreateDate();

    /**
     * <code>required int32 op = 6;</code>
     *
     * <pre>
     *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
     * </pre>
     */
    boolean hasOp();
    /**
     * <code>required int32 op = 6;</code>
     *
     * <pre>
     *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
     * </pre>
     */
    int getOp();
  }
  /**
   * Protobuf type {@code ActionLogMsg}
   *
   * <pre>
   *操作日志信息
   * </pre>
   */
  public static final class ActionLogMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ActionLogMsg)
      ActionLogMsgOrBuilder {
    // Use ActionLogMsg.newBuilder() to construct.
    private ActionLogMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ActionLogMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ActionLogMsg defaultInstance;
    public static ActionLogMsg getDefaultInstance() {
      return defaultInstance;
    }

    public ActionLogMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ActionLogMsg(
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
              sendPlayerId_ = input.readInt64();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              sendName_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              sendFace_ = bs;
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              sendLv_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              createDate_ = input.readInt64();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              op_ = input.readInt32();
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.space.ActionLogProto.internal_static_ActionLogMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.space.ActionLogProto.internal_static_ActionLogMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.class, com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<ActionLogMsg> PARSER =
        new com.google.protobuf.AbstractParser<ActionLogMsg>() {
      public ActionLogMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ActionLogMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ActionLogMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int SENDPLAYERID_FIELD_NUMBER = 1;
    private long sendPlayerId_;
    /**
     * <code>required int64 sendPlayerId = 1;</code>
     *
     * <pre>
     *发送者ID
     * </pre>
     */
    public boolean hasSendPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 sendPlayerId = 1;</code>
     *
     * <pre>
     *发送者ID
     * </pre>
     */
    public long getSendPlayerId() {
      return sendPlayerId_;
    }

    public static final int SENDNAME_FIELD_NUMBER = 2;
    private java.lang.Object sendName_;
    /**
     * <code>required string sendName = 2;</code>
     *
     * <pre>
     *发送者姓名
     * </pre>
     */
    public boolean hasSendName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string sendName = 2;</code>
     *
     * <pre>
     *发送者姓名
     * </pre>
     */
    public java.lang.String getSendName() {
      java.lang.Object ref = sendName_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          sendName_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string sendName = 2;</code>
     *
     * <pre>
     *发送者姓名
     * </pre>
     */
    public com.google.protobuf.ByteString
        getSendNameBytes() {
      java.lang.Object ref = sendName_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sendName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SENDFACE_FIELD_NUMBER = 3;
    private java.lang.Object sendFace_;
    /**
     * <code>required string sendFace = 3;</code>
     *
     * <pre>
     *头像
     * </pre>
     */
    public boolean hasSendFace() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string sendFace = 3;</code>
     *
     * <pre>
     *头像
     * </pre>
     */
    public java.lang.String getSendFace() {
      java.lang.Object ref = sendFace_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          sendFace_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string sendFace = 3;</code>
     *
     * <pre>
     *头像
     * </pre>
     */
    public com.google.protobuf.ByteString
        getSendFaceBytes() {
      java.lang.Object ref = sendFace_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        sendFace_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int SENDLV_FIELD_NUMBER = 4;
    private int sendLv_;
    /**
     * <code>required int32 sendLv = 4;</code>
     *
     * <pre>
     *等级
     * </pre>
     */
    public boolean hasSendLv() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 sendLv = 4;</code>
     *
     * <pre>
     *等级
     * </pre>
     */
    public int getSendLv() {
      return sendLv_;
    }

    public static final int CREATEDATE_FIELD_NUMBER = 5;
    private long createDate_;
    /**
     * <code>required int64 createDate = 5;</code>
     *
     * <pre>
     *时间 utc时间
     * </pre>
     */
    public boolean hasCreateDate() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int64 createDate = 5;</code>
     *
     * <pre>
     *时间 utc时间
     * </pre>
     */
    public long getCreateDate() {
      return createDate_;
    }

    public static final int OP_FIELD_NUMBER = 6;
    private int op_;
    /**
     * <code>required int32 op = 6;</code>
     *
     * <pre>
     *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
     * </pre>
     */
    public boolean hasOp() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>required int32 op = 6;</code>
     *
     * <pre>
     *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
     * </pre>
     */
    public int getOp() {
      return op_;
    }

    private void initFields() {
      sendPlayerId_ = 0L;
      sendName_ = "";
      sendFace_ = "";
      sendLv_ = 0;
      createDate_ = 0L;
      op_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasSendPlayerId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSendName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSendFace()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSendLv()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCreateDate()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasOp()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, sendPlayerId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getSendNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getSendFaceBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, sendLv_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt64(5, createDate_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt32(6, op_);
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
          .computeInt64Size(1, sendPlayerId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getSendNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getSendFaceBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, sendLv_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, createDate_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, op_);
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

    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg prototype) {
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
     * Protobuf type {@code ActionLogMsg}
     *
     * <pre>
     *操作日志信息
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ActionLogMsg)
        com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.space.ActionLogProto.internal_static_ActionLogMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.space.ActionLogProto.internal_static_ActionLogMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.class, com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.newBuilder()
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
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        sendPlayerId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        sendName_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        sendFace_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        sendLv_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        createDate_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000010);
        op_ = 0;
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.space.ActionLogProto.internal_static_ActionLogMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg build() {
        com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg result = new com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.sendPlayerId_ = sendPlayerId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.sendName_ = sendName_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.sendFace_ = sendFace_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.sendLv_ = sendLv_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.createDate_ = createDate_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.op_ = op_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg.getDefaultInstance()) return this;
        if (other.hasSendPlayerId()) {
          setSendPlayerId(other.getSendPlayerId());
        }
        if (other.hasSendName()) {
          bitField0_ |= 0x00000002;
          sendName_ = other.sendName_;
          onChanged();
        }
        if (other.hasSendFace()) {
          bitField0_ |= 0x00000004;
          sendFace_ = other.sendFace_;
          onChanged();
        }
        if (other.hasSendLv()) {
          setSendLv(other.getSendLv());
        }
        if (other.hasCreateDate()) {
          setCreateDate(other.getCreateDate());
        }
        if (other.hasOp()) {
          setOp(other.getOp());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasSendPlayerId()) {
          
          return false;
        }
        if (!hasSendName()) {
          
          return false;
        }
        if (!hasSendFace()) {
          
          return false;
        }
        if (!hasSendLv()) {
          
          return false;
        }
        if (!hasCreateDate()) {
          
          return false;
        }
        if (!hasOp()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.space.ActionLogProto.ActionLogMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long sendPlayerId_ ;
      /**
       * <code>required int64 sendPlayerId = 1;</code>
       *
       * <pre>
       *发送者ID
       * </pre>
       */
      public boolean hasSendPlayerId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 sendPlayerId = 1;</code>
       *
       * <pre>
       *发送者ID
       * </pre>
       */
      public long getSendPlayerId() {
        return sendPlayerId_;
      }
      /**
       * <code>required int64 sendPlayerId = 1;</code>
       *
       * <pre>
       *发送者ID
       * </pre>
       */
      public Builder setSendPlayerId(long value) {
        bitField0_ |= 0x00000001;
        sendPlayerId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 sendPlayerId = 1;</code>
       *
       * <pre>
       *发送者ID
       * </pre>
       */
      public Builder clearSendPlayerId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        sendPlayerId_ = 0L;
        onChanged();
        return this;
      }

      private java.lang.Object sendName_ = "";
      /**
       * <code>required string sendName = 2;</code>
       *
       * <pre>
       *发送者姓名
       * </pre>
       */
      public boolean hasSendName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string sendName = 2;</code>
       *
       * <pre>
       *发送者姓名
       * </pre>
       */
      public java.lang.String getSendName() {
        java.lang.Object ref = sendName_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            sendName_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string sendName = 2;</code>
       *
       * <pre>
       *发送者姓名
       * </pre>
       */
      public com.google.protobuf.ByteString
          getSendNameBytes() {
        java.lang.Object ref = sendName_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          sendName_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string sendName = 2;</code>
       *
       * <pre>
       *发送者姓名
       * </pre>
       */
      public Builder setSendName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        sendName_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string sendName = 2;</code>
       *
       * <pre>
       *发送者姓名
       * </pre>
       */
      public Builder clearSendName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        sendName_ = getDefaultInstance().getSendName();
        onChanged();
        return this;
      }
      /**
       * <code>required string sendName = 2;</code>
       *
       * <pre>
       *发送者姓名
       * </pre>
       */
      public Builder setSendNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        sendName_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object sendFace_ = "";
      /**
       * <code>required string sendFace = 3;</code>
       *
       * <pre>
       *头像
       * </pre>
       */
      public boolean hasSendFace() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string sendFace = 3;</code>
       *
       * <pre>
       *头像
       * </pre>
       */
      public java.lang.String getSendFace() {
        java.lang.Object ref = sendFace_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            sendFace_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string sendFace = 3;</code>
       *
       * <pre>
       *头像
       * </pre>
       */
      public com.google.protobuf.ByteString
          getSendFaceBytes() {
        java.lang.Object ref = sendFace_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          sendFace_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string sendFace = 3;</code>
       *
       * <pre>
       *头像
       * </pre>
       */
      public Builder setSendFace(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        sendFace_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string sendFace = 3;</code>
       *
       * <pre>
       *头像
       * </pre>
       */
      public Builder clearSendFace() {
        bitField0_ = (bitField0_ & ~0x00000004);
        sendFace_ = getDefaultInstance().getSendFace();
        onChanged();
        return this;
      }
      /**
       * <code>required string sendFace = 3;</code>
       *
       * <pre>
       *头像
       * </pre>
       */
      public Builder setSendFaceBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        sendFace_ = value;
        onChanged();
        return this;
      }

      private int sendLv_ ;
      /**
       * <code>required int32 sendLv = 4;</code>
       *
       * <pre>
       *等级
       * </pre>
       */
      public boolean hasSendLv() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int32 sendLv = 4;</code>
       *
       * <pre>
       *等级
       * </pre>
       */
      public int getSendLv() {
        return sendLv_;
      }
      /**
       * <code>required int32 sendLv = 4;</code>
       *
       * <pre>
       *等级
       * </pre>
       */
      public Builder setSendLv(int value) {
        bitField0_ |= 0x00000008;
        sendLv_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 sendLv = 4;</code>
       *
       * <pre>
       *等级
       * </pre>
       */
      public Builder clearSendLv() {
        bitField0_ = (bitField0_ & ~0x00000008);
        sendLv_ = 0;
        onChanged();
        return this;
      }

      private long createDate_ ;
      /**
       * <code>required int64 createDate = 5;</code>
       *
       * <pre>
       *时间 utc时间
       * </pre>
       */
      public boolean hasCreateDate() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required int64 createDate = 5;</code>
       *
       * <pre>
       *时间 utc时间
       * </pre>
       */
      public long getCreateDate() {
        return createDate_;
      }
      /**
       * <code>required int64 createDate = 5;</code>
       *
       * <pre>
       *时间 utc时间
       * </pre>
       */
      public Builder setCreateDate(long value) {
        bitField0_ |= 0x00000010;
        createDate_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 createDate = 5;</code>
       *
       * <pre>
       *时间 utc时间
       * </pre>
       */
      public Builder clearCreateDate() {
        bitField0_ = (bitField0_ & ~0x00000010);
        createDate_ = 0L;
        onChanged();
        return this;
      }

      private int op_ ;
      /**
       * <code>required int32 op = 6;</code>
       *
       * <pre>
       *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
       * </pre>
       */
      public boolean hasOp() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>required int32 op = 6;</code>
       *
       * <pre>
       *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
       * </pre>
       */
      public int getOp() {
        return op_;
      }
      /**
       * <code>required int32 op = 6;</code>
       *
       * <pre>
       *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
       * </pre>
       */
      public Builder setOp(int value) {
        bitField0_ |= 0x00000020;
        op_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 op = 6;</code>
       *
       * <pre>
       *操作类型 1：点赞记录  2：送花记录  3：鸡蛋记录
       * </pre>
       */
      public Builder clearOp() {
        bitField0_ = (bitField0_ & ~0x00000020);
        op_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ActionLogMsg)
    }

    static {
      defaultInstance = new ActionLogMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ActionLogMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ActionLogMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ActionLogMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030space/ActionLogMsg.proto\"x\n\014ActionLogM" +
      "sg\022\024\n\014sendPlayerId\030\001 \002(\003\022\020\n\010sendName\030\002 \002" +
      "(\t\022\020\n\010sendFace\030\003 \002(\t\022\016\n\006sendLv\030\004 \002(\005\022\022\n\n" +
      "createDate\030\005 \002(\003\022\n\n\002op\030\006 \002(\005B8\n&com.chua" +
      "ngyou.common.protobuf.pb.spaceB\016ActionLo" +
      "gProto"
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
        }, assigner);
    internal_static_ActionLogMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ActionLogMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ActionLogMsg_descriptor,
        new java.lang.String[] { "SendPlayerId", "SendName", "SendFace", "SendLv", "CreateDate", "Op", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}