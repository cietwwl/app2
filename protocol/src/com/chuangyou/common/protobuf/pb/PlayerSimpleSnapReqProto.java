// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: player/PlayerSimpleSnapReqMsg.proto

package com.chuangyou.common.protobuf.pb;

public final class PlayerSimpleSnapReqProto {
  private PlayerSimpleSnapReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PlayerSimpleSnapReqMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PlayerSimpleSnapReqMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 playerId = 1;</code>
     *
     * <pre>
     *要查询玩家的角色ID
     * </pre>
     */
    boolean hasPlayerId();
    /**
     * <code>required int64 playerId = 1;</code>
     *
     * <pre>
     *要查询玩家的角色ID
     * </pre>
     */
    long getPlayerId();
  }
  /**
   * Protobuf type {@code PlayerSimpleSnapReqMsg}
   */
  public static final class PlayerSimpleSnapReqMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:PlayerSimpleSnapReqMsg)
      PlayerSimpleSnapReqMsgOrBuilder {
    // Use PlayerSimpleSnapReqMsg.newBuilder() to construct.
    private PlayerSimpleSnapReqMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerSimpleSnapReqMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerSimpleSnapReqMsg defaultInstance;
    public static PlayerSimpleSnapReqMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerSimpleSnapReqMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerSimpleSnapReqMsg(
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
      return com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.internal_static_PlayerSimpleSnapReqMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.internal_static_PlayerSimpleSnapReqMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.class, com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerSimpleSnapReqMsg> PARSER =
        new com.google.protobuf.AbstractParser<PlayerSimpleSnapReqMsg>() {
      public PlayerSimpleSnapReqMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerSimpleSnapReqMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerSimpleSnapReqMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int PLAYERID_FIELD_NUMBER = 1;
    private long playerId_;
    /**
     * <code>required int64 playerId = 1;</code>
     *
     * <pre>
     *要查询玩家的角色ID
     * </pre>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 playerId = 1;</code>
     *
     * <pre>
     *要查询玩家的角色ID
     * </pre>
     */
    public long getPlayerId() {
      return playerId_;
    }

    private void initFields() {
      playerId_ = 0L;
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
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(1, playerId_);
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

    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg prototype) {
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
     * Protobuf type {@code PlayerSimpleSnapReqMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:PlayerSimpleSnapReqMsg)
        com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.internal_static_PlayerSimpleSnapReqMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.internal_static_PlayerSimpleSnapReqMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.class, com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.newBuilder()
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
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.internal_static_PlayerSimpleSnapReqMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg build() {
        com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg result = new com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.playerId_ = playerId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg.getDefaultInstance()) return this;
        if (other.hasPlayerId()) {
          setPlayerId(other.getPlayerId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasPlayerId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.PlayerSimpleSnapReqProto.PlayerSimpleSnapReqMsg) e.getUnfinishedMessage();
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
       *
       * <pre>
       *要查询玩家的角色ID
       * </pre>
       */
      public boolean hasPlayerId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 playerId = 1;</code>
       *
       * <pre>
       *要查询玩家的角色ID
       * </pre>
       */
      public long getPlayerId() {
        return playerId_;
      }
      /**
       * <code>required int64 playerId = 1;</code>
       *
       * <pre>
       *要查询玩家的角色ID
       * </pre>
       */
      public Builder setPlayerId(long value) {
        bitField0_ |= 0x00000001;
        playerId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 playerId = 1;</code>
       *
       * <pre>
       *要查询玩家的角色ID
       * </pre>
       */
      public Builder clearPlayerId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        playerId_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PlayerSimpleSnapReqMsg)
    }

    static {
      defaultInstance = new PlayerSimpleSnapReqMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PlayerSimpleSnapReqMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PlayerSimpleSnapReqMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PlayerSimpleSnapReqMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#player/PlayerSimpleSnapReqMsg.proto\"*\n" +
      "\026PlayerSimpleSnapReqMsg\022\020\n\010playerId\030\001 \002(" +
      "\003B<\n com.chuangyou.common.protobuf.pbB\030P" +
      "layerSimpleSnapReqProto"
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
    internal_static_PlayerSimpleSnapReqMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PlayerSimpleSnapReqMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PlayerSimpleSnapReqMsg_descriptor,
        new java.lang.String[] { "PlayerId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}