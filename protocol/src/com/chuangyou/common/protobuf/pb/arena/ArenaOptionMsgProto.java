// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ArenaOptionMsg.proto

package com.chuangyou.common.protobuf.pb.arena;

public final class ArenaOptionMsgProto {
  private ArenaOptionMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ArenaOptionMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ArenaOptionMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 optionType = 1;</code>
     *
     * <pre>
     *��������  1 ������ս
     * </pre>
     */
    boolean hasOptionType();
    /**
     * <code>optional int32 optionType = 1;</code>
     *
     * <pre>
     *��������  1 ������ս
     * </pre>
     */
    int getOptionType();

    /**
     * <code>optional int64 param1 = 2;</code>
     *
     * <pre>
     *�������� type=1 ��ʾ����սĿ��
     * </pre>
     */
    boolean hasParam1();
    /**
     * <code>optional int64 param1 = 2;</code>
     *
     * <pre>
     *�������� type=1 ��ʾ����սĿ��
     * </pre>
     */
    long getParam1();
  }
  /**
   * Protobuf type {@code ArenaOptionMsg}
   */
  public static final class ArenaOptionMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ArenaOptionMsg)
      ArenaOptionMsgOrBuilder {
    // Use ArenaOptionMsg.newBuilder() to construct.
    private ArenaOptionMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ArenaOptionMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ArenaOptionMsg defaultInstance;
    public static ArenaOptionMsg getDefaultInstance() {
      return defaultInstance;
    }

    public ArenaOptionMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ArenaOptionMsg(
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
              optionType_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              param1_ = input.readInt64();
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
      return com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.internal_static_ArenaOptionMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.internal_static_ArenaOptionMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.class, com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<ArenaOptionMsg> PARSER =
        new com.google.protobuf.AbstractParser<ArenaOptionMsg>() {
      public ArenaOptionMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ArenaOptionMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ArenaOptionMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int OPTIONTYPE_FIELD_NUMBER = 1;
    private int optionType_;
    /**
     * <code>optional int32 optionType = 1;</code>
     *
     * <pre>
     *��������  1 ������ս
     * </pre>
     */
    public boolean hasOptionType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 optionType = 1;</code>
     *
     * <pre>
     *��������  1 ������ս
     * </pre>
     */
    public int getOptionType() {
      return optionType_;
    }

    public static final int PARAM1_FIELD_NUMBER = 2;
    private long param1_;
    /**
     * <code>optional int64 param1 = 2;</code>
     *
     * <pre>
     *�������� type=1 ��ʾ����սĿ��
     * </pre>
     */
    public boolean hasParam1() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 param1 = 2;</code>
     *
     * <pre>
     *�������� type=1 ��ʾ����սĿ��
     * </pre>
     */
    public long getParam1() {
      return param1_;
    }

    private void initFields() {
      optionType_ = 0;
      param1_ = 0L;
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
        output.writeInt32(1, optionType_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, param1_);
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
          .computeInt32Size(1, optionType_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, param1_);
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

    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg prototype) {
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
     * Protobuf type {@code ArenaOptionMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ArenaOptionMsg)
        com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.internal_static_ArenaOptionMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.internal_static_ArenaOptionMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.class, com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.newBuilder()
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
        optionType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        param1_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.internal_static_ArenaOptionMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg build() {
        com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg result = new com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.optionType_ = optionType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.param1_ = param1_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg.getDefaultInstance()) return this;
        if (other.hasOptionType()) {
          setOptionType(other.getOptionType());
        }
        if (other.hasParam1()) {
          setParam1(other.getParam1());
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
        com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.arena.ArenaOptionMsgProto.ArenaOptionMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int optionType_ ;
      /**
       * <code>optional int32 optionType = 1;</code>
       *
       * <pre>
       *��������  1 ������ս
       * </pre>
       */
      public boolean hasOptionType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 optionType = 1;</code>
       *
       * <pre>
       *��������  1 ������ս
       * </pre>
       */
      public int getOptionType() {
        return optionType_;
      }
      /**
       * <code>optional int32 optionType = 1;</code>
       *
       * <pre>
       *��������  1 ������ս
       * </pre>
       */
      public Builder setOptionType(int value) {
        bitField0_ |= 0x00000001;
        optionType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 optionType = 1;</code>
       *
       * <pre>
       *��������  1 ������ս
       * </pre>
       */
      public Builder clearOptionType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        optionType_ = 0;
        onChanged();
        return this;
      }

      private long param1_ ;
      /**
       * <code>optional int64 param1 = 2;</code>
       *
       * <pre>
       *�������� type=1 ��ʾ����սĿ��
       * </pre>
       */
      public boolean hasParam1() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int64 param1 = 2;</code>
       *
       * <pre>
       *�������� type=1 ��ʾ����սĿ��
       * </pre>
       */
      public long getParam1() {
        return param1_;
      }
      /**
       * <code>optional int64 param1 = 2;</code>
       *
       * <pre>
       *�������� type=1 ��ʾ����սĿ��
       * </pre>
       */
      public Builder setParam1(long value) {
        bitField0_ |= 0x00000002;
        param1_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 param1 = 2;</code>
       *
       * <pre>
       *�������� type=1 ��ʾ����սĿ��
       * </pre>
       */
      public Builder clearParam1() {
        bitField0_ = (bitField0_ & ~0x00000002);
        param1_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ArenaOptionMsg)
    }

    static {
      defaultInstance = new ArenaOptionMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ArenaOptionMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ArenaOptionMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ArenaOptionMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024ArenaOptionMsg.proto\"4\n\016ArenaOptionMsg" +
      "\022\022\n\noptionType\030\001 \001(\005\022\016\n\006param1\030\002 \001(\003B=\n&" +
      "com.chuangyou.common.protobuf.pb.arenaB\023" +
      "ArenaOptionMsgProto"
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
    internal_static_ArenaOptionMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ArenaOptionMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ArenaOptionMsg_descriptor,
        new java.lang.String[] { "OptionType", "Param1", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}