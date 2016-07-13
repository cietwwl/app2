// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: drop/DropItemPickupMsg.proto

package com.chuangyou.common.protobuf.pb.drop;

public final class DropItemPickupProto {
  private DropItemPickupProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DropItemPickupMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:DropItemPickupMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 packageId = 1;</code>
     *
     * <pre>
     * 掉落包ID
     * </pre>
     */
    boolean hasPackageId();
    /**
     * <code>optional int32 packageId = 1;</code>
     *
     * <pre>
     * 掉落包ID
     * </pre>
     */
    int getPackageId();

    /**
     * <code>optional int64 dropItemId = 2;</code>
     *
     * <pre>
     * 掉落物ID
     * </pre>
     */
    boolean hasDropItemId();
    /**
     * <code>optional int64 dropItemId = 2;</code>
     *
     * <pre>
     * 掉落物ID
     * </pre>
     */
    long getDropItemId();
  }
  /**
   * Protobuf type {@code DropItemPickupMsg}
   */
  public static final class DropItemPickupMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:DropItemPickupMsg)
      DropItemPickupMsgOrBuilder {
    // Use DropItemPickupMsg.newBuilder() to construct.
    private DropItemPickupMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private DropItemPickupMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final DropItemPickupMsg defaultInstance;
    public static DropItemPickupMsg getDefaultInstance() {
      return defaultInstance;
    }

    public DropItemPickupMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private DropItemPickupMsg(
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
              packageId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              dropItemId_ = input.readInt64();
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
      return com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.internal_static_DropItemPickupMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.internal_static_DropItemPickupMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.class, com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<DropItemPickupMsg> PARSER =
        new com.google.protobuf.AbstractParser<DropItemPickupMsg>() {
      public DropItemPickupMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DropItemPickupMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<DropItemPickupMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int PACKAGEID_FIELD_NUMBER = 1;
    private int packageId_;
    /**
     * <code>optional int32 packageId = 1;</code>
     *
     * <pre>
     * 掉落包ID
     * </pre>
     */
    public boolean hasPackageId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 packageId = 1;</code>
     *
     * <pre>
     * 掉落包ID
     * </pre>
     */
    public int getPackageId() {
      return packageId_;
    }

    public static final int DROPITEMID_FIELD_NUMBER = 2;
    private long dropItemId_;
    /**
     * <code>optional int64 dropItemId = 2;</code>
     *
     * <pre>
     * 掉落物ID
     * </pre>
     */
    public boolean hasDropItemId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 dropItemId = 2;</code>
     *
     * <pre>
     * 掉落物ID
     * </pre>
     */
    public long getDropItemId() {
      return dropItemId_;
    }

    private void initFields() {
      packageId_ = 0;
      dropItemId_ = 0L;
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
        output.writeInt32(1, packageId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, dropItemId_);
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
          .computeInt32Size(1, packageId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, dropItemId_);
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

    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg prototype) {
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
     * Protobuf type {@code DropItemPickupMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:DropItemPickupMsg)
        com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.internal_static_DropItemPickupMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.internal_static_DropItemPickupMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.class, com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.newBuilder()
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
        packageId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        dropItemId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.internal_static_DropItemPickupMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg build() {
        com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg result = new com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.packageId_ = packageId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.dropItemId_ = dropItemId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg.getDefaultInstance()) return this;
        if (other.hasPackageId()) {
          setPackageId(other.getPackageId());
        }
        if (other.hasDropItemId()) {
          setDropItemId(other.getDropItemId());
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
        com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.drop.DropItemPickupProto.DropItemPickupMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int packageId_ ;
      /**
       * <code>optional int32 packageId = 1;</code>
       *
       * <pre>
       * 掉落包ID
       * </pre>
       */
      public boolean hasPackageId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 packageId = 1;</code>
       *
       * <pre>
       * 掉落包ID
       * </pre>
       */
      public int getPackageId() {
        return packageId_;
      }
      /**
       * <code>optional int32 packageId = 1;</code>
       *
       * <pre>
       * 掉落包ID
       * </pre>
       */
      public Builder setPackageId(int value) {
        bitField0_ |= 0x00000001;
        packageId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 packageId = 1;</code>
       *
       * <pre>
       * 掉落包ID
       * </pre>
       */
      public Builder clearPackageId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        packageId_ = 0;
        onChanged();
        return this;
      }

      private long dropItemId_ ;
      /**
       * <code>optional int64 dropItemId = 2;</code>
       *
       * <pre>
       * 掉落物ID
       * </pre>
       */
      public boolean hasDropItemId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int64 dropItemId = 2;</code>
       *
       * <pre>
       * 掉落物ID
       * </pre>
       */
      public long getDropItemId() {
        return dropItemId_;
      }
      /**
       * <code>optional int64 dropItemId = 2;</code>
       *
       * <pre>
       * 掉落物ID
       * </pre>
       */
      public Builder setDropItemId(long value) {
        bitField0_ |= 0x00000002;
        dropItemId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 dropItemId = 2;</code>
       *
       * <pre>
       * 掉落物ID
       * </pre>
       */
      public Builder clearDropItemId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        dropItemId_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:DropItemPickupMsg)
    }

    static {
      defaultInstance = new DropItemPickupMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:DropItemPickupMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DropItemPickupMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_DropItemPickupMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034drop/DropItemPickupMsg.proto\":\n\021DropIt" +
      "emPickupMsg\022\021\n\tpackageId\030\001 \001(\005\022\022\n\ndropIt" +
      "emId\030\002 \001(\003B<\n%com.chuangyou.common.proto" +
      "buf.pb.dropB\023DropItemPickupProto"
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
    internal_static_DropItemPickupMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_DropItemPickupMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_DropItemPickupMsg_descriptor,
        new java.lang.String[] { "PackageId", "DropItemId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
