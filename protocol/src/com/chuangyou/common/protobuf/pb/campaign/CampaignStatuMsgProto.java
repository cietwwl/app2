// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CampaignStatuMsg.proto

package com.chuangyou.common.protobuf.pb.campaign;

public final class CampaignStatuMsgProto {
  private CampaignStatuMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CampaignStatuMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CampaignStatuMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 indexId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    boolean hasIndexId();
    /**
     * <code>optional int32 indexId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    int getIndexId();

    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *副本模板
     * </pre>
     */
    boolean hasTempId();
    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *副本模板
     * </pre>
     */
    int getTempId();

    /**
     * <code>optional int32 statu = 3;</code>
     *
     * <pre>
     *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
     * </pre>
     */
    boolean hasStatu();
    /**
     * <code>optional int32 statu = 3;</code>
     *
     * <pre>
     *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
     * </pre>
     */
    int getStatu();

    /**
     * <code>optional int32 teamId = 4;</code>
     *
     * <pre>
     *队伍ID
     * </pre>
     */
    boolean hasTeamId();
    /**
     * <code>optional int32 teamId = 4;</code>
     *
     * <pre>
     *队伍ID
     * </pre>
     */
    int getTeamId();

    /**
     * <code>optional int32 taskId = 5;</code>
     *
     * <pre>
     *副本任务ID 
     * </pre>
     */
    boolean hasTaskId();
    /**
     * <code>optional int32 taskId = 5;</code>
     *
     * <pre>
     *副本任务ID 
     * </pre>
     */
    int getTaskId();

    /**
     * <code>optional int64 playerId = 6;</code>
     *
     * <pre>
     *成员ID
     * </pre>
     */
    boolean hasPlayerId();
    /**
     * <code>optional int64 playerId = 6;</code>
     *
     * <pre>
     *成员ID
     * </pre>
     */
    long getPlayerId();

    /**
     * <code>optional int32 isIn = 7;</code>
     *
     * <pre>
     *在副本内还是在副本外
     * </pre>
     */
    boolean hasIsIn();
    /**
     * <code>optional int32 isIn = 7;</code>
     *
     * <pre>
     *在副本内还是在副本外
     * </pre>
     */
    int getIsIn();
  }
  /**
   * Protobuf type {@code CampaignStatuMsg}
   */
  public static final class CampaignStatuMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:CampaignStatuMsg)
      CampaignStatuMsgOrBuilder {
    // Use CampaignStatuMsg.newBuilder() to construct.
    private CampaignStatuMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CampaignStatuMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CampaignStatuMsg defaultInstance;
    public static CampaignStatuMsg getDefaultInstance() {
      return defaultInstance;
    }

    public CampaignStatuMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CampaignStatuMsg(
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
              indexId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              tempId_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              statu_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              teamId_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              taskId_ = input.readInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              playerId_ = input.readInt64();
              break;
            }
            case 56: {
              bitField0_ |= 0x00000040;
              isIn_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.internal_static_CampaignStatuMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.internal_static_CampaignStatuMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.class, com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<CampaignStatuMsg> PARSER =
        new com.google.protobuf.AbstractParser<CampaignStatuMsg>() {
      public CampaignStatuMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CampaignStatuMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CampaignStatuMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int INDEXID_FIELD_NUMBER = 1;
    private int indexId_;
    /**
     * <code>optional int32 indexId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    public boolean hasIndexId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 indexId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    public int getIndexId() {
      return indexId_;
    }

    public static final int TEMPID_FIELD_NUMBER = 2;
    private int tempId_;
    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *副本模板
     * </pre>
     */
    public boolean hasTempId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 tempId = 2;</code>
     *
     * <pre>
     *副本模板
     * </pre>
     */
    public int getTempId() {
      return tempId_;
    }

    public static final int STATU_FIELD_NUMBER = 3;
    private int statu_;
    /**
     * <code>optional int32 statu = 3;</code>
     *
     * <pre>
     *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
     * </pre>
     */
    public boolean hasStatu() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 statu = 3;</code>
     *
     * <pre>
     *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
     * </pre>
     */
    public int getStatu() {
      return statu_;
    }

    public static final int TEAMID_FIELD_NUMBER = 4;
    private int teamId_;
    /**
     * <code>optional int32 teamId = 4;</code>
     *
     * <pre>
     *队伍ID
     * </pre>
     */
    public boolean hasTeamId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 teamId = 4;</code>
     *
     * <pre>
     *队伍ID
     * </pre>
     */
    public int getTeamId() {
      return teamId_;
    }

    public static final int TASKID_FIELD_NUMBER = 5;
    private int taskId_;
    /**
     * <code>optional int32 taskId = 5;</code>
     *
     * <pre>
     *副本任务ID 
     * </pre>
     */
    public boolean hasTaskId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 taskId = 5;</code>
     *
     * <pre>
     *副本任务ID 
     * </pre>
     */
    public int getTaskId() {
      return taskId_;
    }

    public static final int PLAYERID_FIELD_NUMBER = 6;
    private long playerId_;
    /**
     * <code>optional int64 playerId = 6;</code>
     *
     * <pre>
     *成员ID
     * </pre>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>optional int64 playerId = 6;</code>
     *
     * <pre>
     *成员ID
     * </pre>
     */
    public long getPlayerId() {
      return playerId_;
    }

    public static final int ISIN_FIELD_NUMBER = 7;
    private int isIn_;
    /**
     * <code>optional int32 isIn = 7;</code>
     *
     * <pre>
     *在副本内还是在副本外
     * </pre>
     */
    public boolean hasIsIn() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <code>optional int32 isIn = 7;</code>
     *
     * <pre>
     *在副本内还是在副本外
     * </pre>
     */
    public int getIsIn() {
      return isIn_;
    }

    private void initFields() {
      indexId_ = 0;
      tempId_ = 0;
      statu_ = 0;
      teamId_ = 0;
      taskId_ = 0;
      playerId_ = 0L;
      isIn_ = 0;
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
        output.writeInt32(1, indexId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, tempId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, statu_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, teamId_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, taskId_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt64(6, playerId_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeInt32(7, isIn_);
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
          .computeInt32Size(1, indexId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, tempId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, statu_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, teamId_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, taskId_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, playerId_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(7, isIn_);
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

    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg prototype) {
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
     * Protobuf type {@code CampaignStatuMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CampaignStatuMsg)
        com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.internal_static_CampaignStatuMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.internal_static_CampaignStatuMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.class, com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.newBuilder()
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
        indexId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        tempId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        statu_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        teamId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        taskId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        playerId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000020);
        isIn_ = 0;
        bitField0_ = (bitField0_ & ~0x00000040);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.internal_static_CampaignStatuMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg build() {
        com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg result = new com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.indexId_ = indexId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.tempId_ = tempId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.statu_ = statu_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.teamId_ = teamId_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.taskId_ = taskId_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.playerId_ = playerId_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.isIn_ = isIn_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg.getDefaultInstance()) return this;
        if (other.hasIndexId()) {
          setIndexId(other.getIndexId());
        }
        if (other.hasTempId()) {
          setTempId(other.getTempId());
        }
        if (other.hasStatu()) {
          setStatu(other.getStatu());
        }
        if (other.hasTeamId()) {
          setTeamId(other.getTeamId());
        }
        if (other.hasTaskId()) {
          setTaskId(other.getTaskId());
        }
        if (other.hasPlayerId()) {
          setPlayerId(other.getPlayerId());
        }
        if (other.hasIsIn()) {
          setIsIn(other.getIsIn());
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
        com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int indexId_ ;
      /**
       * <code>optional int32 indexId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public boolean hasIndexId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 indexId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public int getIndexId() {
        return indexId_;
      }
      /**
       * <code>optional int32 indexId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public Builder setIndexId(int value) {
        bitField0_ |= 0x00000001;
        indexId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 indexId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public Builder clearIndexId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        indexId_ = 0;
        onChanged();
        return this;
      }

      private int tempId_ ;
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *副本模板
       * </pre>
       */
      public boolean hasTempId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *副本模板
       * </pre>
       */
      public int getTempId() {
        return tempId_;
      }
      /**
       * <code>optional int32 tempId = 2;</code>
       *
       * <pre>
       *副本模板
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
       *副本模板
       * </pre>
       */
      public Builder clearTempId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        tempId_ = 0;
        onChanged();
        return this;
      }

      private int statu_ ;
      /**
       * <code>optional int32 statu = 3;</code>
       *
       * <pre>
       *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
       * </pre>
       */
      public boolean hasStatu() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 statu = 3;</code>
       *
       * <pre>
       *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
       * </pre>
       */
      public int getStatu() {
        return statu_;
      }
      /**
       * <code>optional int32 statu = 3;</code>
       *
       * <pre>
       *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
       * </pre>
       */
      public Builder setStatu(int value) {
        bitField0_ |= 0x00000004;
        statu_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 statu = 3;</code>
       *
       * <pre>
       *状态  0  正常退出 1 进入 2 通过结束退出 3 失败结束退出
       * </pre>
       */
      public Builder clearStatu() {
        bitField0_ = (bitField0_ & ~0x00000004);
        statu_ = 0;
        onChanged();
        return this;
      }

      private int teamId_ ;
      /**
       * <code>optional int32 teamId = 4;</code>
       *
       * <pre>
       *队伍ID
       * </pre>
       */
      public boolean hasTeamId() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 teamId = 4;</code>
       *
       * <pre>
       *队伍ID
       * </pre>
       */
      public int getTeamId() {
        return teamId_;
      }
      /**
       * <code>optional int32 teamId = 4;</code>
       *
       * <pre>
       *队伍ID
       * </pre>
       */
      public Builder setTeamId(int value) {
        bitField0_ |= 0x00000008;
        teamId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 teamId = 4;</code>
       *
       * <pre>
       *队伍ID
       * </pre>
       */
      public Builder clearTeamId() {
        bitField0_ = (bitField0_ & ~0x00000008);
        teamId_ = 0;
        onChanged();
        return this;
      }

      private int taskId_ ;
      /**
       * <code>optional int32 taskId = 5;</code>
       *
       * <pre>
       *副本任务ID 
       * </pre>
       */
      public boolean hasTaskId() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 taskId = 5;</code>
       *
       * <pre>
       *副本任务ID 
       * </pre>
       */
      public int getTaskId() {
        return taskId_;
      }
      /**
       * <code>optional int32 taskId = 5;</code>
       *
       * <pre>
       *副本任务ID 
       * </pre>
       */
      public Builder setTaskId(int value) {
        bitField0_ |= 0x00000010;
        taskId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 taskId = 5;</code>
       *
       * <pre>
       *副本任务ID 
       * </pre>
       */
      public Builder clearTaskId() {
        bitField0_ = (bitField0_ & ~0x00000010);
        taskId_ = 0;
        onChanged();
        return this;
      }

      private long playerId_ ;
      /**
       * <code>optional int64 playerId = 6;</code>
       *
       * <pre>
       *成员ID
       * </pre>
       */
      public boolean hasPlayerId() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>optional int64 playerId = 6;</code>
       *
       * <pre>
       *成员ID
       * </pre>
       */
      public long getPlayerId() {
        return playerId_;
      }
      /**
       * <code>optional int64 playerId = 6;</code>
       *
       * <pre>
       *成员ID
       * </pre>
       */
      public Builder setPlayerId(long value) {
        bitField0_ |= 0x00000020;
        playerId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 playerId = 6;</code>
       *
       * <pre>
       *成员ID
       * </pre>
       */
      public Builder clearPlayerId() {
        bitField0_ = (bitField0_ & ~0x00000020);
        playerId_ = 0L;
        onChanged();
        return this;
      }

      private int isIn_ ;
      /**
       * <code>optional int32 isIn = 7;</code>
       *
       * <pre>
       *在副本内还是在副本外
       * </pre>
       */
      public boolean hasIsIn() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      /**
       * <code>optional int32 isIn = 7;</code>
       *
       * <pre>
       *在副本内还是在副本外
       * </pre>
       */
      public int getIsIn() {
        return isIn_;
      }
      /**
       * <code>optional int32 isIn = 7;</code>
       *
       * <pre>
       *在副本内还是在副本外
       * </pre>
       */
      public Builder setIsIn(int value) {
        bitField0_ |= 0x00000040;
        isIn_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 isIn = 7;</code>
       *
       * <pre>
       *在副本内还是在副本外
       * </pre>
       */
      public Builder clearIsIn() {
        bitField0_ = (bitField0_ & ~0x00000040);
        isIn_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:CampaignStatuMsg)
    }

    static {
      defaultInstance = new CampaignStatuMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:CampaignStatuMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CampaignStatuMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CampaignStatuMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026CampaignStatuMsg.proto\"\202\001\n\020CampaignSta" +
      "tuMsg\022\017\n\007indexId\030\001 \001(\005\022\016\n\006tempId\030\002 \001(\005\022\r" +
      "\n\005statu\030\003 \001(\005\022\016\n\006teamId\030\004 \001(\005\022\016\n\006taskId\030" +
      "\005 \001(\005\022\020\n\010playerId\030\006 \001(\003\022\014\n\004isIn\030\007 \001(\005BB\n" +
      ")com.chuangyou.common.protobuf.pb.campai" +
      "gnB\025CampaignStatuMsgProto"
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
    internal_static_CampaignStatuMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CampaignStatuMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_CampaignStatuMsg_descriptor,
        new java.lang.String[] { "IndexId", "TempId", "Statu", "TeamId", "TaskId", "PlayerId", "IsIn", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
