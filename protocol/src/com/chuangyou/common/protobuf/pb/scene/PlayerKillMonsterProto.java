// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PlayerKillMonsterMsg.proto

package com.chuangyou.common.protobuf.pb.scene;

public final class PlayerKillMonsterProto {
  private PlayerKillMonsterProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PlayerKillMonsterMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PlayerKillMonsterMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 playerId = 1;</code>
     */
    boolean hasPlayerId();
    /**
     * <code>required int64 playerId = 1;</code>
     */
    long getPlayerId();

    /**
     * <code>required int64 beKillId = 2;</code>
     */
    boolean hasBeKillId();
    /**
     * <code>required int64 beKillId = 2;</code>
     */
    long getBeKillId();

    /**
     * <code>optional int32 type = 3;</code>
     *
     * <pre>
     *1 ���� 2 ����
     * </pre>
     */
    boolean hasType();
    /**
     * <code>optional int32 type = 3;</code>
     *
     * <pre>
     *1 ���� 2 ����
     * </pre>
     */
    int getType();

    /**
     * <code>optional int32 joinType = 4;</code>
     *
     * <pre>
     *1 ��ɱ�� 2 ������
     * </pre>
     */
    boolean hasJoinType();
    /**
     * <code>optional int32 joinType = 4;</code>
     *
     * <pre>
     *1 ��ɱ�� 2 ������
     * </pre>
     */
    int getJoinType();
  }
  /**
   * Protobuf type {@code PlayerKillMonsterMsg}
   *
   * <pre>
   *֪ͨcenter�����ɱ������
   * </pre>
   */
  public static final class PlayerKillMonsterMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:PlayerKillMonsterMsg)
      PlayerKillMonsterMsgOrBuilder {
    // Use PlayerKillMonsterMsg.newBuilder() to construct.
    private PlayerKillMonsterMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerKillMonsterMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerKillMonsterMsg defaultInstance;
    public static PlayerKillMonsterMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerKillMonsterMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerKillMonsterMsg(
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
              playerId_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              beKillId_ = input.readInt64();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              type_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              joinType_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.internal_static_PlayerKillMonsterMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.internal_static_PlayerKillMonsterMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.class, com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerKillMonsterMsg> PARSER =
        new com.google.protobuf.AbstractParser<PlayerKillMonsterMsg>() {
      public PlayerKillMonsterMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerKillMonsterMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerKillMonsterMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int PLAYERID_FIELD_NUMBER = 1;
    private long playerId_;
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }

    public static final int BEKILLID_FIELD_NUMBER = 2;
    private long beKillId_;
    /**
     * <code>required int64 beKillId = 2;</code>
     */
    public boolean hasBeKillId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int64 beKillId = 2;</code>
     */
    public long getBeKillId() {
      return beKillId_;
    }

    public static final int TYPE_FIELD_NUMBER = 3;
    private int type_;
    /**
     * <code>optional int32 type = 3;</code>
     *
     * <pre>
     *1 ���� 2 ����
     * </pre>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 type = 3;</code>
     *
     * <pre>
     *1 ���� 2 ����
     * </pre>
     */
    public int getType() {
      return type_;
    }

    public static final int JOINTYPE_FIELD_NUMBER = 4;
    private int joinType_;
    /**
     * <code>optional int32 joinType = 4;</code>
     *
     * <pre>
     *1 ��ɱ�� 2 ������
     * </pre>
     */
    public boolean hasJoinType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 joinType = 4;</code>
     *
     * <pre>
     *1 ��ɱ�� 2 ������
     * </pre>
     */
    public int getJoinType() {
      return joinType_;
    }

    private void initFields() {
      playerId_ = 0L;
      beKillId_ = 0L;
      type_ = 0;
      joinType_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasPlayerId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasBeKillId()) {
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
        output.writeInt64(1, playerId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, beKillId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, type_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, joinType_);
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
          .computeInt64Size(1, playerId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, beKillId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, type_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, joinType_);
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

    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg prototype) {
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
     * Protobuf type {@code PlayerKillMonsterMsg}
     *
     * <pre>
     *֪ͨcenter�����ɱ������
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:PlayerKillMonsterMsg)
        com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.internal_static_PlayerKillMonsterMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.internal_static_PlayerKillMonsterMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.class, com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.newBuilder()
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
        playerId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        beKillId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        joinType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.internal_static_PlayerKillMonsterMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg build() {
        com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg result = new com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.playerId_ = playerId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.beKillId_ = beKillId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.joinType_ = joinType_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg.getDefaultInstance()) return this;
        if (other.hasPlayerId()) {
          setPlayerId(other.getPlayerId());
        }
        if (other.hasBeKillId()) {
          setBeKillId(other.getBeKillId());
        }
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasJoinType()) {
          setJoinType(other.getJoinType());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasPlayerId()) {
          
          return false;
        }
        if (!hasBeKillId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long playerId_ ;
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public boolean hasPlayerId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public long getPlayerId() {
        return playerId_;
      }
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public Builder setPlayerId(long value) {
        bitField0_ |= 0x00000001;
        playerId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 playerId = 1;</code>
       */
      public Builder clearPlayerId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        playerId_ = 0L;
        onChanged();
        return this;
      }

      private long beKillId_ ;
      /**
       * <code>required int64 beKillId = 2;</code>
       */
      public boolean hasBeKillId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int64 beKillId = 2;</code>
       */
      public long getBeKillId() {
        return beKillId_;
      }
      /**
       * <code>required int64 beKillId = 2;</code>
       */
      public Builder setBeKillId(long value) {
        bitField0_ |= 0x00000002;
        beKillId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 beKillId = 2;</code>
       */
      public Builder clearBeKillId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        beKillId_ = 0L;
        onChanged();
        return this;
      }

      private int type_ ;
      /**
       * <code>optional int32 type = 3;</code>
       *
       * <pre>
       *1 ���� 2 ����
       * </pre>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 type = 3;</code>
       *
       * <pre>
       *1 ���� 2 ����
       * </pre>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>optional int32 type = 3;</code>
       *
       * <pre>
       *1 ���� 2 ����
       * </pre>
       */
      public Builder setType(int value) {
        bitField0_ |= 0x00000004;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 type = 3;</code>
       *
       * <pre>
       *1 ���� 2 ����
       * </pre>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000004);
        type_ = 0;
        onChanged();
        return this;
      }

      private int joinType_ ;
      /**
       * <code>optional int32 joinType = 4;</code>
       *
       * <pre>
       *1 ��ɱ�� 2 ������
       * </pre>
       */
      public boolean hasJoinType() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 joinType = 4;</code>
       *
       * <pre>
       *1 ��ɱ�� 2 ������
       * </pre>
       */
      public int getJoinType() {
        return joinType_;
      }
      /**
       * <code>optional int32 joinType = 4;</code>
       *
       * <pre>
       *1 ��ɱ�� 2 ������
       * </pre>
       */
      public Builder setJoinType(int value) {
        bitField0_ |= 0x00000008;
        joinType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 joinType = 4;</code>
       *
       * <pre>
       *1 ��ɱ�� 2 ������
       * </pre>
       */
      public Builder clearJoinType() {
        bitField0_ = (bitField0_ & ~0x00000008);
        joinType_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PlayerKillMonsterMsg)
    }

    static {
      defaultInstance = new PlayerKillMonsterMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PlayerKillMonsterMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PlayerKillMonsterMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PlayerKillMonsterMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032PlayerKillMonsterMsg.proto\"Z\n\024PlayerKi" +
      "llMonsterMsg\022\020\n\010playerId\030\001 \002(\003\022\020\n\010beKill" +
      "Id\030\002 \002(\003\022\014\n\004type\030\003 \001(\005\022\020\n\010joinType\030\004 \001(\005" +
      "B@\n&com.chuangyou.common.protobuf.pb.sce" +
      "neB\026PlayerKillMonsterProto"
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
    internal_static_PlayerKillMonsterMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PlayerKillMonsterMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PlayerKillMonsterMsg_descriptor,
        new java.lang.String[] { "PlayerId", "BeKillId", "Type", "JoinType", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}