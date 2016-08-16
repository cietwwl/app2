// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: vip/ResBuyVipMsg.proto

package com.chuangyou.common.protobuf.pb.vip;

public final class ResBuyVipMsgProto {
  private ResBuyVipMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ResBuyVipMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ResBuyVipMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 vipTimeLimit = 1;</code>
     *
     * <pre>
     *vip 到期时间 （时间戳
     * </pre>
     */
    boolean hasVipTimeLimit();
    /**
     * <code>required int64 vipTimeLimit = 1;</code>
     *
     * <pre>
     *vip 到期时间 （时间戳
     * </pre>
     */
    long getVipTimeLimit();

    /**
     * <code>required int64 vipInterimTimeLimit = 2;</code>
     *
     * <pre>
     *临时vip 到期时间（时间戳
     * </pre>
     */
    boolean hasVipInterimTimeLimit();
    /**
     * <code>required int64 vipInterimTimeLimit = 2;</code>
     *
     * <pre>
     *临时vip 到期时间（时间戳
     * </pre>
     */
    long getVipInterimTimeLimit();
  }
  /**
   * Protobuf type {@code ResBuyVipMsg}
   *
   * <pre>
   *购买或赠送vip成功
   * </pre>
   */
  public static final class ResBuyVipMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ResBuyVipMsg)
      ResBuyVipMsgOrBuilder {
    // Use ResBuyVipMsg.newBuilder() to construct.
    private ResBuyVipMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ResBuyVipMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ResBuyVipMsg defaultInstance;
    public static ResBuyVipMsg getDefaultInstance() {
      return defaultInstance;
    }

    public ResBuyVipMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ResBuyVipMsg(
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
              vipTimeLimit_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              vipInterimTimeLimit_ = input.readInt64();
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
      return com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.internal_static_ResBuyVipMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.internal_static_ResBuyVipMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.class, com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<ResBuyVipMsg> PARSER =
        new com.google.protobuf.AbstractParser<ResBuyVipMsg>() {
      public ResBuyVipMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ResBuyVipMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ResBuyVipMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int VIPTIMELIMIT_FIELD_NUMBER = 1;
    private long vipTimeLimit_;
    /**
     * <code>required int64 vipTimeLimit = 1;</code>
     *
     * <pre>
     *vip 到期时间 （时间戳
     * </pre>
     */
    public boolean hasVipTimeLimit() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 vipTimeLimit = 1;</code>
     *
     * <pre>
     *vip 到期时间 （时间戳
     * </pre>
     */
    public long getVipTimeLimit() {
      return vipTimeLimit_;
    }

    public static final int VIPINTERIMTIMELIMIT_FIELD_NUMBER = 2;
    private long vipInterimTimeLimit_;
    /**
     * <code>required int64 vipInterimTimeLimit = 2;</code>
     *
     * <pre>
     *临时vip 到期时间（时间戳
     * </pre>
     */
    public boolean hasVipInterimTimeLimit() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int64 vipInterimTimeLimit = 2;</code>
     *
     * <pre>
     *临时vip 到期时间（时间戳
     * </pre>
     */
    public long getVipInterimTimeLimit() {
      return vipInterimTimeLimit_;
    }

    private void initFields() {
      vipTimeLimit_ = 0L;
      vipInterimTimeLimit_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasVipTimeLimit()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasVipInterimTimeLimit()) {
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
        output.writeInt64(1, vipTimeLimit_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, vipInterimTimeLimit_);
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
          .computeInt64Size(1, vipTimeLimit_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, vipInterimTimeLimit_);
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

    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg prototype) {
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
     * Protobuf type {@code ResBuyVipMsg}
     *
     * <pre>
     *购买或赠送vip成功
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ResBuyVipMsg)
        com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.internal_static_ResBuyVipMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.internal_static_ResBuyVipMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.class, com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.newBuilder()
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
        vipTimeLimit_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        vipInterimTimeLimit_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.internal_static_ResBuyVipMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg build() {
        com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg result = new com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.vipTimeLimit_ = vipTimeLimit_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.vipInterimTimeLimit_ = vipInterimTimeLimit_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg.getDefaultInstance()) return this;
        if (other.hasVipTimeLimit()) {
          setVipTimeLimit(other.getVipTimeLimit());
        }
        if (other.hasVipInterimTimeLimit()) {
          setVipInterimTimeLimit(other.getVipInterimTimeLimit());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasVipTimeLimit()) {
          
          return false;
        }
        if (!hasVipInterimTimeLimit()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.vip.ResBuyVipMsgProto.ResBuyVipMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long vipTimeLimit_ ;
      /**
       * <code>required int64 vipTimeLimit = 1;</code>
       *
       * <pre>
       *vip 到期时间 （时间戳
       * </pre>
       */
      public boolean hasVipTimeLimit() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 vipTimeLimit = 1;</code>
       *
       * <pre>
       *vip 到期时间 （时间戳
       * </pre>
       */
      public long getVipTimeLimit() {
        return vipTimeLimit_;
      }
      /**
       * <code>required int64 vipTimeLimit = 1;</code>
       *
       * <pre>
       *vip 到期时间 （时间戳
       * </pre>
       */
      public Builder setVipTimeLimit(long value) {
        bitField0_ |= 0x00000001;
        vipTimeLimit_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 vipTimeLimit = 1;</code>
       *
       * <pre>
       *vip 到期时间 （时间戳
       * </pre>
       */
      public Builder clearVipTimeLimit() {
        bitField0_ = (bitField0_ & ~0x00000001);
        vipTimeLimit_ = 0L;
        onChanged();
        return this;
      }

      private long vipInterimTimeLimit_ ;
      /**
       * <code>required int64 vipInterimTimeLimit = 2;</code>
       *
       * <pre>
       *临时vip 到期时间（时间戳
       * </pre>
       */
      public boolean hasVipInterimTimeLimit() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int64 vipInterimTimeLimit = 2;</code>
       *
       * <pre>
       *临时vip 到期时间（时间戳
       * </pre>
       */
      public long getVipInterimTimeLimit() {
        return vipInterimTimeLimit_;
      }
      /**
       * <code>required int64 vipInterimTimeLimit = 2;</code>
       *
       * <pre>
       *临时vip 到期时间（时间戳
       * </pre>
       */
      public Builder setVipInterimTimeLimit(long value) {
        bitField0_ |= 0x00000002;
        vipInterimTimeLimit_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 vipInterimTimeLimit = 2;</code>
       *
       * <pre>
       *临时vip 到期时间（时间戳
       * </pre>
       */
      public Builder clearVipInterimTimeLimit() {
        bitField0_ = (bitField0_ & ~0x00000002);
        vipInterimTimeLimit_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ResBuyVipMsg)
    }

    static {
      defaultInstance = new ResBuyVipMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ResBuyVipMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResBuyVipMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ResBuyVipMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026vip/ResBuyVipMsg.proto\"A\n\014ResBuyVipMsg" +
      "\022\024\n\014vipTimeLimit\030\001 \002(\003\022\033\n\023vipInterimTime" +
      "Limit\030\002 \002(\003B9\n$com.chuangyou.common.prot" +
      "obuf.pb.vipB\021ResBuyVipMsgProto"
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
    internal_static_ResBuyVipMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ResBuyVipMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ResBuyVipMsg_descriptor,
        new java.lang.String[] { "VipTimeLimit", "VipInterimTimeLimit", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}