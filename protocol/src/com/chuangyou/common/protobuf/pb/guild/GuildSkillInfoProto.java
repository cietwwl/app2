// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: guild/GuildSkillInfoMsg.proto

package com.chuangyou.common.protobuf.pb.guild;

public final class GuildSkillInfoProto {
  private GuildSkillInfoProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GuildSkillInfoMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:GuildSkillInfoMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 guildSkillId = 1;</code>
     */
    boolean hasGuildSkillId();
    /**
     * <code>optional int32 guildSkillId = 1;</code>
     */
    int getGuildSkillId();

    /**
     * <code>optional int32 level = 2;</code>
     */
    boolean hasLevel();
    /**
     * <code>optional int32 level = 2;</code>
     */
    int getLevel();
  }
  /**
   * Protobuf type {@code GuildSkillInfoMsg}
   *
   * <pre>
   *帮派技能信息，这里的技能ID不是人物技能ID，是帮派技能表中的id
   * </pre>
   */
  public static final class GuildSkillInfoMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:GuildSkillInfoMsg)
      GuildSkillInfoMsgOrBuilder {
    // Use GuildSkillInfoMsg.newBuilder() to construct.
    private GuildSkillInfoMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private GuildSkillInfoMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final GuildSkillInfoMsg defaultInstance;
    public static GuildSkillInfoMsg getDefaultInstance() {
      return defaultInstance;
    }

    public GuildSkillInfoMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private GuildSkillInfoMsg(
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
              guildSkillId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              level_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.internal_static_GuildSkillInfoMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.internal_static_GuildSkillInfoMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.class, com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<GuildSkillInfoMsg> PARSER =
        new com.google.protobuf.AbstractParser<GuildSkillInfoMsg>() {
      public GuildSkillInfoMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GuildSkillInfoMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<GuildSkillInfoMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int GUILDSKILLID_FIELD_NUMBER = 1;
    private int guildSkillId_;
    /**
     * <code>optional int32 guildSkillId = 1;</code>
     */
    public boolean hasGuildSkillId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 guildSkillId = 1;</code>
     */
    public int getGuildSkillId() {
      return guildSkillId_;
    }

    public static final int LEVEL_FIELD_NUMBER = 2;
    private int level_;
    /**
     * <code>optional int32 level = 2;</code>
     */
    public boolean hasLevel() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 level = 2;</code>
     */
    public int getLevel() {
      return level_;
    }

    private void initFields() {
      guildSkillId_ = 0;
      level_ = 0;
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
        output.writeInt32(1, guildSkillId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, level_);
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
          .computeInt32Size(1, guildSkillId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, level_);
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

    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg prototype) {
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
     * Protobuf type {@code GuildSkillInfoMsg}
     *
     * <pre>
     *帮派技能信息，这里的技能ID不是人物技能ID，是帮派技能表中的id
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:GuildSkillInfoMsg)
        com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.internal_static_GuildSkillInfoMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.internal_static_GuildSkillInfoMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.class, com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.newBuilder()
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
        guildSkillId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        level_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.internal_static_GuildSkillInfoMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg build() {
        com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg result = new com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.guildSkillId_ = guildSkillId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.level_ = level_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg.getDefaultInstance()) return this;
        if (other.hasGuildSkillId()) {
          setGuildSkillId(other.getGuildSkillId());
        }
        if (other.hasLevel()) {
          setLevel(other.getLevel());
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
        com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.guild.GuildSkillInfoProto.GuildSkillInfoMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int guildSkillId_ ;
      /**
       * <code>optional int32 guildSkillId = 1;</code>
       */
      public boolean hasGuildSkillId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 guildSkillId = 1;</code>
       */
      public int getGuildSkillId() {
        return guildSkillId_;
      }
      /**
       * <code>optional int32 guildSkillId = 1;</code>
       */
      public Builder setGuildSkillId(int value) {
        bitField0_ |= 0x00000001;
        guildSkillId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 guildSkillId = 1;</code>
       */
      public Builder clearGuildSkillId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        guildSkillId_ = 0;
        onChanged();
        return this;
      }

      private int level_ ;
      /**
       * <code>optional int32 level = 2;</code>
       */
      public boolean hasLevel() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 level = 2;</code>
       */
      public int getLevel() {
        return level_;
      }
      /**
       * <code>optional int32 level = 2;</code>
       */
      public Builder setLevel(int value) {
        bitField0_ |= 0x00000002;
        level_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 level = 2;</code>
       */
      public Builder clearLevel() {
        bitField0_ = (bitField0_ & ~0x00000002);
        level_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:GuildSkillInfoMsg)
    }

    static {
      defaultInstance = new GuildSkillInfoMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:GuildSkillInfoMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GuildSkillInfoMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GuildSkillInfoMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035guild/GuildSkillInfoMsg.proto\"8\n\021Guild" +
      "SkillInfoMsg\022\024\n\014guildSkillId\030\001 \001(\005\022\r\n\005le" +
      "vel\030\002 \001(\005B=\n&com.chuangyou.common.protob" +
      "uf.pb.guildB\023GuildSkillInfoProto"
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
    internal_static_GuildSkillInfoMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GuildSkillInfoMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_GuildSkillInfoMsg_descriptor,
        new java.lang.String[] { "GuildSkillId", "Level", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
