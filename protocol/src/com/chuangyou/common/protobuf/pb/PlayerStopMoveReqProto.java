// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scene/PlayerStopMoveReqMsg.proto

package com.chuangyou.common.protobuf.pb;

public final class PlayerStopMoveReqProto {
  private PlayerStopMoveReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PlayerStopMoveReqMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PlayerStopMoveReqMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional .PBVector3 cur = 1;</code>
     *
     * <pre>
     *当前位置
     * </pre>
     */
    boolean hasCur();
    /**
     * <code>optional .PBVector3 cur = 1;</code>
     *
     * <pre>
     *当前位置
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 getCur();
    /**
     * <code>optional .PBVector3 cur = 1;</code>
     *
     * <pre>
     *当前位置
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3OrBuilder getCurOrBuilder();
  }
  /**
   * Protobuf type {@code PlayerStopMoveReqMsg}
   */
  public static final class PlayerStopMoveReqMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:PlayerStopMoveReqMsg)
      PlayerStopMoveReqMsgOrBuilder {
    // Use PlayerStopMoveReqMsg.newBuilder() to construct.
    private PlayerStopMoveReqMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PlayerStopMoveReqMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PlayerStopMoveReqMsg defaultInstance;
    public static PlayerStopMoveReqMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PlayerStopMoveReqMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PlayerStopMoveReqMsg(
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
            case 10: {
              com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = cur_.toBuilder();
              }
              cur_ = input.readMessage(com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(cur_);
                cur_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
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
      return com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.internal_static_PlayerStopMoveReqMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.internal_static_PlayerStopMoveReqMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.class, com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PlayerStopMoveReqMsg> PARSER =
        new com.google.protobuf.AbstractParser<PlayerStopMoveReqMsg>() {
      public PlayerStopMoveReqMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerStopMoveReqMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PlayerStopMoveReqMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CUR_FIELD_NUMBER = 1;
    private com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 cur_;
    /**
     * <code>optional .PBVector3 cur = 1;</code>
     *
     * <pre>
     *当前位置
     * </pre>
     */
    public boolean hasCur() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .PBVector3 cur = 1;</code>
     *
     * <pre>
     *当前位置
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 getCur() {
      return cur_;
    }
    /**
     * <code>optional .PBVector3 cur = 1;</code>
     *
     * <pre>
     *当前位置
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3OrBuilder getCurOrBuilder() {
      return cur_;
    }

    private void initFields() {
      cur_ = com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.getDefaultInstance();
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
        output.writeMessage(1, cur_);
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
          .computeMessageSize(1, cur_);
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

    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg prototype) {
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
     * Protobuf type {@code PlayerStopMoveReqMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:PlayerStopMoveReqMsg)
        com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.internal_static_PlayerStopMoveReqMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.internal_static_PlayerStopMoveReqMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.class, com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.newBuilder()
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
          getCurFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (curBuilder_ == null) {
          cur_ = com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.getDefaultInstance();
        } else {
          curBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.internal_static_PlayerStopMoveReqMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg build() {
        com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg result = new com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (curBuilder_ == null) {
          result.cur_ = cur_;
        } else {
          result.cur_ = curBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg.getDefaultInstance()) return this;
        if (other.hasCur()) {
          mergeCur(other.getCur());
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
        com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.PlayerStopMoveReqProto.PlayerStopMoveReqMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 cur_ = com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3, com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.Builder, com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3OrBuilder> curBuilder_;
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public boolean hasCur() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 getCur() {
        if (curBuilder_ == null) {
          return cur_;
        } else {
          return curBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public Builder setCur(com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 value) {
        if (curBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          cur_ = value;
          onChanged();
        } else {
          curBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public Builder setCur(
          com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.Builder builderForValue) {
        if (curBuilder_ == null) {
          cur_ = builderForValue.build();
          onChanged();
        } else {
          curBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public Builder mergeCur(com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3 value) {
        if (curBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              cur_ != com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.getDefaultInstance()) {
            cur_ =
              com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.newBuilder(cur_).mergeFrom(value).buildPartial();
          } else {
            cur_ = value;
          }
          onChanged();
        } else {
          curBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public Builder clearCur() {
        if (curBuilder_ == null) {
          cur_ = com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.getDefaultInstance();
          onChanged();
        } else {
          curBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.Builder getCurBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getCurFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3OrBuilder getCurOrBuilder() {
        if (curBuilder_ != null) {
          return curBuilder_.getMessageOrBuilder();
        } else {
          return cur_;
        }
      }
      /**
       * <code>optional .PBVector3 cur = 1;</code>
       *
       * <pre>
       *当前位置
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3, com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.Builder, com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3OrBuilder> 
          getCurFieldBuilder() {
        if (curBuilder_ == null) {
          curBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3, com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3.Builder, com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3OrBuilder>(
                  getCur(),
                  getParentForChildren(),
                  isClean());
          cur_ = null;
        }
        return curBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:PlayerStopMoveReqMsg)
    }

    static {
      defaultInstance = new PlayerStopMoveReqMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PlayerStopMoveReqMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PlayerStopMoveReqMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PlayerStopMoveReqMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n scene/PlayerStopMoveReqMsg.proto\032\026comm" +
      "on/PBVector3.proto\"/\n\024PlayerStopMoveReqM" +
      "sg\022\027\n\003cur\030\001 \001(\0132\n.PBVector3B:\n com.chuan" +
      "gyou.common.protobuf.pbB\026PlayerStopMoveR" +
      "eqProto"
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
          com.chuangyou.common.protobuf.pb.Vector3Proto.getDescriptor(),
        }, assigner);
    internal_static_PlayerStopMoveReqMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PlayerStopMoveReqMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PlayerStopMoveReqMsg_descriptor,
        new java.lang.String[] { "Cur", });
    com.chuangyou.common.protobuf.pb.Vector3Proto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
