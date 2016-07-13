// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CampaignInfoMsg.proto

package com.chuangyou.common.protobuf.pb.campaign;

public final class CampaignInfoMsgProto {
  private CampaignInfoMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CampaignInfoMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CampaignInfoMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 id = 1;</code>
     *
     * <pre>
     *副本唯一ID
     * </pre>
     */
    boolean hasId();
    /**
     * <code>optional int32 id = 1;</code>
     *
     * <pre>
     *副本唯一ID
     * </pre>
     */
    int getId();

    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *模板ID
     * </pre>
     */
    boolean hasTempId();
    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *模板ID
     * </pre>
     */
    int getTempId();

    /**
     * <code>optional int64 createTime = 3;</code>
     *
     * <pre>
     *创建时间
     * </pre>
     */
    boolean hasCreateTime();
    /**
     * <code>optional int64 createTime = 3;</code>
     *
     * <pre>
     *创建时间
     * </pre>
     */
    long getCreateTime();

    /**
     * <code>optional int32 state = 4;</code>
     *
     * <pre>
     *当前状态
     * </pre>
     */
    boolean hasState();
    /**
     * <code>optional int32 state = 4;</code>
     *
     * <pre>
     *当前状态
     * </pre>
     */
    int getState();

    /**
     * <code>optional int32 count = 5;</code>
     *
     * <pre>
     *当前人数
     * </pre>
     */
    boolean hasCount();
    /**
     * <code>optional int32 count = 5;</code>
     *
     * <pre>
     *当前人数
     * </pre>
     */
    int getCount();

    /**
     * <code>optional int64 createrId = 6;</code>
     *
     * <pre>
     *创建人ID
     * </pre>
     */
    boolean hasCreaterId();
    /**
     * <code>optional int64 createrId = 6;</code>
     *
     * <pre>
     *创建人ID
     * </pre>
     */
    long getCreaterId();

    /**
     * <code>optional int32 openTime = 7;</code>
     *
     * <pre>
     *开放时间
     * </pre>
     */
    boolean hasOpenTime();
    /**
     * <code>optional int32 openTime = 7;</code>
     *
     * <pre>
     *开放时间
     * </pre>
     */
    int getOpenTime();

    /**
     * <code>optional int32 backTime = 8;</code>
     *
     * <pre>
     *玩家回归剩余时间(秒)
     * </pre>
     */
    boolean hasBackTime();
    /**
     * <code>optional int32 backTime = 8;</code>
     *
     * <pre>
     *玩家回归剩余时间(秒)
     * </pre>
     */
    int getBackTime();
  }
  /**
   * Protobuf type {@code CampaignInfoMsg}
   */
  public static final class CampaignInfoMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:CampaignInfoMsg)
      CampaignInfoMsgOrBuilder {
    // Use CampaignInfoMsg.newBuilder() to construct.
    private CampaignInfoMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CampaignInfoMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CampaignInfoMsg defaultInstance;
    public static CampaignInfoMsg getDefaultInstance() {
      return defaultInstance;
    }

    public CampaignInfoMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CampaignInfoMsg(
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
              id_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              tempId_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              createTime_ = input.readInt64();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              state_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              count_ = input.readInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              createrId_ = input.readInt64();
              break;
            }
            case 56: {
              bitField0_ |= 0x00000040;
              openTime_ = input.readInt32();
              break;
            }
            case 64: {
              bitField0_ |= 0x00000080;
              backTime_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.internal_static_CampaignInfoMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.internal_static_CampaignInfoMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.class, com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<CampaignInfoMsg> PARSER =
        new com.google.protobuf.AbstractParser<CampaignInfoMsg>() {
      public CampaignInfoMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CampaignInfoMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CampaignInfoMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    /**
     * <code>optional int32 id = 1;</code>
     *
     * <pre>
     *副本唯一ID
     * </pre>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 id = 1;</code>
     *
     * <pre>
     *副本唯一ID
     * </pre>
     */
    public int getId() {
      return id_;
    }

    public static final int TEMPID_FIELD_NUMBER = 2;
    private int tempId_;
    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *模板ID
     * </pre>
     */
    public boolean hasTempId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *模板ID
     * </pre>
     */
    public int getTempId() {
      return tempId_;
    }

    public static final int CREATETIME_FIELD_NUMBER = 3;
    private long createTime_;
    /**
     * <code>optional int64 createTime = 3;</code>
     *
     * <pre>
     *创建时间
     * </pre>
     */
    public boolean hasCreateTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int64 createTime = 3;</code>
     *
     * <pre>
     *创建时间
     * </pre>
     */
    public long getCreateTime() {
      return createTime_;
    }

    public static final int STATE_FIELD_NUMBER = 4;
    private int state_;
    /**
     * <code>optional int32 state = 4;</code>
     *
     * <pre>
     *当前状态
     * </pre>
     */
    public boolean hasState() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 state = 4;</code>
     *
     * <pre>
     *当前状态
     * </pre>
     */
    public int getState() {
      return state_;
    }

    public static final int COUNT_FIELD_NUMBER = 5;
    private int count_;
    /**
     * <code>optional int32 count = 5;</code>
     *
     * <pre>
     *当前人数
     * </pre>
     */
    public boolean hasCount() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 count = 5;</code>
     *
     * <pre>
     *当前人数
     * </pre>
     */
    public int getCount() {
      return count_;
    }

    public static final int CREATERID_FIELD_NUMBER = 6;
    private long createrId_;
    /**
     * <code>optional int64 createrId = 6;</code>
     *
     * <pre>
     *创建人ID
     * </pre>
     */
    public boolean hasCreaterId() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional int64 createrId = 6;</code>
     *
     * <pre>
     *创建人ID
     * </pre>
     */
    public long getCreaterId() {
      return createrId_;
    }

    public static final int OPENTIME_FIELD_NUMBER = 7;
    private int openTime_;
    /**
     * <code>optional int32 openTime = 7;</code>
     *
     * <pre>
     *开放时间
     * </pre>
     */
    public boolean hasOpenTime() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>optional int32 openTime = 7;</code>
     *
     * <pre>
     *开放时间
     * </pre>
     */
    public int getOpenTime() {
      return openTime_;
    }

    public static final int BACKTIME_FIELD_NUMBER = 8;
    private int backTime_;
    /**
     * <code>optional int32 backTime = 8;</code>
     *
     * <pre>
     *玩家回归剩余时间(秒)
     * </pre>
     */
    public boolean hasBackTime() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <code>optional int32 backTime = 8;</code>
     *
     * <pre>
     *玩家回归剩余时间(秒)
     * </pre>
     */
    public int getBackTime() {
      return backTime_;
    }

    private void initFields() {
      id_ = 0;
      tempId_ = 0;
      createTime_ = 0L;
      state_ = 0;
      count_ = 0;
      createrId_ = 0L;
      openTime_ = 0;
      backTime_ = 0;
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
        output.writeInt32(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, tempId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt64(3, createTime_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, state_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, count_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt64(6, createrId_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeInt32(7, openTime_);
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        output.writeInt32(8, backTime_);
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
          .computeInt32Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, tempId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, createTime_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, state_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, count_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, createrId_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(7, openTime_);
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(8, backTime_);
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

    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg prototype) {
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
     * Protobuf type {@code CampaignInfoMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CampaignInfoMsg)
        com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.internal_static_CampaignInfoMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.internal_static_CampaignInfoMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.class, com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.newBuilder()
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
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        tempId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        createTime_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        state_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        count_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        createrId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000020);
        openTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000040);
        backTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000080);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.internal_static_CampaignInfoMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg build() {
        com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg result = new com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.tempId_ = tempId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.createTime_ = createTime_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.state_ = state_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.count_ = count_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.createrId_ = createrId_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.openTime_ = openTime_;
        if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
          to_bitField0_ |= 0x00000080;
        }
        result.backTime_ = backTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasTempId()) {
          setTempId(other.getTempId());
        }
        if (other.hasCreateTime()) {
          setCreateTime(other.getCreateTime());
        }
        if (other.hasState()) {
          setState(other.getState());
        }
        if (other.hasCount()) {
          setCount(other.getCount());
        }
        if (other.hasCreaterId()) {
          setCreaterId(other.getCreaterId());
        }
        if (other.hasOpenTime()) {
          setOpenTime(other.getOpenTime());
        }
        if (other.hasBackTime()) {
          setBackTime(other.getBackTime());
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
        com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int id_ ;
      /**
       * <code>optional int32 id = 1;</code>
       *
       * <pre>
       *副本唯一ID
       * </pre>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 id = 1;</code>
       *
       * <pre>
       *副本唯一ID
       * </pre>
       */
      public int getId() {
        return id_;
      }
      /**
       * <code>optional int32 id = 1;</code>
       *
       * <pre>
       *副本唯一ID
       * </pre>
       */
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 id = 1;</code>
       *
       * <pre>
       *副本唯一ID
       * </pre>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        onChanged();
        return this;
      }

      private int tempId_ ;
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *模板ID
       * </pre>
       */
      public boolean hasTempId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *模板ID
       * </pre>
       */
      public int getTempId() {
        return tempId_;
      }
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *模板ID
       * </pre>
       */
      public Builder setTempId(int value) {
        bitField0_ |= 0x00000002;
        tempId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *模板ID
       * </pre>
       */
      public Builder clearTempId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        tempId_ = 0;
        onChanged();
        return this;
      }

      private long createTime_ ;
      /**
       * <code>optional int64 createTime = 3;</code>
       *
       * <pre>
       *创建时间
       * </pre>
       */
      public boolean hasCreateTime() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int64 createTime = 3;</code>
       *
       * <pre>
       *创建时间
       * </pre>
       */
      public long getCreateTime() {
        return createTime_;
      }
      /**
       * <code>optional int64 createTime = 3;</code>
       *
       * <pre>
       *创建时间
       * </pre>
       */
      public Builder setCreateTime(long value) {
        bitField0_ |= 0x00000004;
        createTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 createTime = 3;</code>
       *
       * <pre>
       *创建时间
       * </pre>
       */
      public Builder clearCreateTime() {
        bitField0_ = (bitField0_ & ~0x00000004);
        createTime_ = 0L;
        onChanged();
        return this;
      }

      private int state_ ;
      /**
       * <code>optional int32 state = 4;</code>
       *
       * <pre>
       *当前状态
       * </pre>
       */
      public boolean hasState() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 state = 4;</code>
       *
       * <pre>
       *当前状态
       * </pre>
       */
      public int getState() {
        return state_;
      }
      /**
       * <code>optional int32 state = 4;</code>
       *
       * <pre>
       *当前状态
       * </pre>
       */
      public Builder setState(int value) {
        bitField0_ |= 0x00000008;
        state_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 state = 4;</code>
       *
       * <pre>
       *当前状态
       * </pre>
       */
      public Builder clearState() {
        bitField0_ = (bitField0_ & ~0x00000008);
        state_ = 0;
        onChanged();
        return this;
      }

      private int count_ ;
      /**
       * <code>optional int32 count = 5;</code>
       *
       * <pre>
       *当前人数
       * </pre>
       */
      public boolean hasCount() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 count = 5;</code>
       *
       * <pre>
       *当前人数
       * </pre>
       */
      public int getCount() {
        return count_;
      }
      /**
       * <code>optional int32 count = 5;</code>
       *
       * <pre>
       *当前人数
       * </pre>
       */
      public Builder setCount(int value) {
        bitField0_ |= 0x00000010;
        count_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 count = 5;</code>
       *
       * <pre>
       *当前人数
       * </pre>
       */
      public Builder clearCount() {
        bitField0_ = (bitField0_ & ~0x00000010);
        count_ = 0;
        onChanged();
        return this;
      }

      private long createrId_ ;
      /**
       * <code>optional int64 createrId = 6;</code>
       *
       * <pre>
       *创建人ID
       * </pre>
       */
      public boolean hasCreaterId() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>optional int64 createrId = 6;</code>
       *
       * <pre>
       *创建人ID
       * </pre>
       */
      public long getCreaterId() {
        return createrId_;
      }
      /**
       * <code>optional int64 createrId = 6;</code>
       *
       * <pre>
       *创建人ID
       * </pre>
       */
      public Builder setCreaterId(long value) {
        bitField0_ |= 0x00000020;
        createrId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 createrId = 6;</code>
       *
       * <pre>
       *创建人ID
       * </pre>
       */
      public Builder clearCreaterId() {
        bitField0_ = (bitField0_ & ~0x00000020);
        createrId_ = 0L;
        onChanged();
        return this;
      }

      private int openTime_ ;
      /**
       * <code>optional int32 openTime = 7;</code>
       *
       * <pre>
       *开放时间
       * </pre>
       */
      public boolean hasOpenTime() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      /**
       * <code>optional int32 openTime = 7;</code>
       *
       * <pre>
       *开放时间
       * </pre>
       */
      public int getOpenTime() {
        return openTime_;
      }
      /**
       * <code>optional int32 openTime = 7;</code>
       *
       * <pre>
       *开放时间
       * </pre>
       */
      public Builder setOpenTime(int value) {
        bitField0_ |= 0x00000040;
        openTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 openTime = 7;</code>
       *
       * <pre>
       *开放时间
       * </pre>
       */
      public Builder clearOpenTime() {
        bitField0_ = (bitField0_ & ~0x00000040);
        openTime_ = 0;
        onChanged();
        return this;
      }

      private int backTime_ ;
      /**
       * <code>optional int32 backTime = 8;</code>
       *
       * <pre>
       *玩家回归剩余时间(秒)
       * </pre>
       */
      public boolean hasBackTime() {
        return ((bitField0_ & 0x00000080) == 0x00000080);
      }
      /**
       * <code>optional int32 backTime = 8;</code>
       *
       * <pre>
       *玩家回归剩余时间(秒)
       * </pre>
       */
      public int getBackTime() {
        return backTime_;
      }
      /**
       * <code>optional int32 backTime = 8;</code>
       *
       * <pre>
       *玩家回归剩余时间(秒)
       * </pre>
       */
      public Builder setBackTime(int value) {
        bitField0_ |= 0x00000080;
        backTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 backTime = 8;</code>
       *
       * <pre>
       *玩家回归剩余时间(秒)
       * </pre>
       */
      public Builder clearBackTime() {
        bitField0_ = (bitField0_ & ~0x00000080);
        backTime_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:CampaignInfoMsg)
    }

    static {
      defaultInstance = new CampaignInfoMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:CampaignInfoMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CampaignInfoMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CampaignInfoMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025CampaignInfoMsg.proto\"\226\001\n\017CampaignInfo" +
      "Msg\022\n\n\002id\030\001 \001(\005\022\016\n\006tempId\030\002 \001(\005\022\022\n\ncreat" +
      "eTime\030\003 \001(\003\022\r\n\005state\030\004 \001(\005\022\r\n\005count\030\005 \001(" +
      "\005\022\021\n\tcreaterId\030\006 \001(\003\022\020\n\010openTime\030\007 \001(\005\022\020" +
      "\n\010backTime\030\010 \001(\005BA\n)com.chuangyou.common" +
      ".protobuf.pb.campaignB\024CampaignInfoMsgPr" +
      "oto"
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
    internal_static_CampaignInfoMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CampaignInfoMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_CampaignInfoMsg_descriptor,
        new java.lang.String[] { "Id", "TempId", "CreateTime", "State", "Count", "CreaterId", "OpenTime", "BackTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
