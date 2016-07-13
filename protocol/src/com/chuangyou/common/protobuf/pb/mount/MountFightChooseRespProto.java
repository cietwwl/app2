// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mount/MountFightChooseRespMsg.proto

package com.chuangyou.common.protobuf.pb.mount;

public final class MountFightChooseRespProto {
  private MountFightChooseRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface MountFightChooseRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:MountFightChooseRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 mountId = 1;</code>
     *
     * <pre>
     * 当前出战的坐骑ID
     * </pre>
     */
    boolean hasMountId();
    /**
     * <code>optional int32 mountId = 1;</code>
     *
     * <pre>
     * 当前出战的坐骑ID
     * </pre>
     */
    int getMountId();
  }
  /**
   * Protobuf type {@code MountFightChooseRespMsg}
   */
  public static final class MountFightChooseRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:MountFightChooseRespMsg)
      MountFightChooseRespMsgOrBuilder {
    // Use MountFightChooseRespMsg.newBuilder() to construct.
    private MountFightChooseRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private MountFightChooseRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final MountFightChooseRespMsg defaultInstance;
    public static MountFightChooseRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public MountFightChooseRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private MountFightChooseRespMsg(
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
              mountId_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.internal_static_MountFightChooseRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.internal_static_MountFightChooseRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.class, com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<MountFightChooseRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<MountFightChooseRespMsg>() {
      public MountFightChooseRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MountFightChooseRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<MountFightChooseRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int MOUNTID_FIELD_NUMBER = 1;
    private int mountId_;
    /**
     * <code>optional int32 mountId = 1;</code>
     *
     * <pre>
     * 当前出战的坐骑ID
     * </pre>
     */
    public boolean hasMountId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 mountId = 1;</code>
     *
     * <pre>
     * 当前出战的坐骑ID
     * </pre>
     */
    public int getMountId() {
      return mountId_;
    }

    private void initFields() {
      mountId_ = 0;
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
        output.writeInt32(1, mountId_);
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
          .computeInt32Size(1, mountId_);
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

    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg prototype) {
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
     * Protobuf type {@code MountFightChooseRespMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:MountFightChooseRespMsg)
        com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.internal_static_MountFightChooseRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.internal_static_MountFightChooseRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.class, com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.newBuilder()
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
        mountId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.internal_static_MountFightChooseRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg build() {
        com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg result = new com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.mountId_ = mountId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg.getDefaultInstance()) return this;
        if (other.hasMountId()) {
          setMountId(other.getMountId());
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
        com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.mount.MountFightChooseRespProto.MountFightChooseRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int mountId_ ;
      /**
       * <code>optional int32 mountId = 1;</code>
       *
       * <pre>
       * 当前出战的坐骑ID
       * </pre>
       */
      public boolean hasMountId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 mountId = 1;</code>
       *
       * <pre>
       * 当前出战的坐骑ID
       * </pre>
       */
      public int getMountId() {
        return mountId_;
      }
      /**
       * <code>optional int32 mountId = 1;</code>
       *
       * <pre>
       * 当前出战的坐骑ID
       * </pre>
       */
      public Builder setMountId(int value) {
        bitField0_ |= 0x00000001;
        mountId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 mountId = 1;</code>
       *
       * <pre>
       * 当前出战的坐骑ID
       * </pre>
       */
      public Builder clearMountId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        mountId_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:MountFightChooseRespMsg)
    }

    static {
      defaultInstance = new MountFightChooseRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:MountFightChooseRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MountFightChooseRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_MountFightChooseRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#mount/MountFightChooseRespMsg.proto\"*\n" +
      "\027MountFightChooseRespMsg\022\017\n\007mountId\030\001 \001(" +
      "\005BC\n&com.chuangyou.common.protobuf.pb.mo" +
      "untB\031MountFightChooseRespProto"
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
    internal_static_MountFightChooseRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_MountFightChooseRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_MountFightChooseRespMsg_descriptor,
        new java.lang.String[] { "MountId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
