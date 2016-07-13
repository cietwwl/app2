// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: drop/DropPickupCenterMsg.proto

package com.chuangyou.common.protobuf.pb.drop;

public final class DropPickupCenterProto {
  private DropPickupCenterProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface DropPickupCenterMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:DropPickupCenterMsg)
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

    /**
     * <code>optional int32 itemId = 3;</code>
     */
    boolean hasItemId();
    /**
     * <code>optional int32 itemId = 3;</code>
     */
    int getItemId();

    /**
     * <code>optional int32 count = 4;</code>
     */
    boolean hasCount();
    /**
     * <code>optional int32 count = 4;</code>
     */
    int getCount();
  }
  /**
   * Protobuf type {@code DropPickupCenterMsg}
   */
  public static final class DropPickupCenterMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:DropPickupCenterMsg)
      DropPickupCenterMsgOrBuilder {
    // Use DropPickupCenterMsg.newBuilder() to construct.
    private DropPickupCenterMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private DropPickupCenterMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final DropPickupCenterMsg defaultInstance;
    public static DropPickupCenterMsg getDefaultInstance() {
      return defaultInstance;
    }

    public DropPickupCenterMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private DropPickupCenterMsg(
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
            case 24: {
              bitField0_ |= 0x00000004;
              itemId_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              count_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.internal_static_DropPickupCenterMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.internal_static_DropPickupCenterMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.class, com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<DropPickupCenterMsg> PARSER =
        new com.google.protobuf.AbstractParser<DropPickupCenterMsg>() {
      public DropPickupCenterMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new DropPickupCenterMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<DropPickupCenterMsg> getParserForType() {
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

    public static final int ITEMID_FIELD_NUMBER = 3;
    private int itemId_;
    /**
     * <code>optional int32 itemId = 3;</code>
     */
    public boolean hasItemId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 itemId = 3;</code>
     */
    public int getItemId() {
      return itemId_;
    }

    public static final int COUNT_FIELD_NUMBER = 4;
    private int count_;
    /**
     * <code>optional int32 count = 4;</code>
     */
    public boolean hasCount() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 count = 4;</code>
     */
    public int getCount() {
      return count_;
    }

    private void initFields() {
      packageId_ = 0;
      dropItemId_ = 0L;
      itemId_ = 0;
      count_ = 0;
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
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, itemId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, count_);
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
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, itemId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, count_);
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

    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg prototype) {
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
     * Protobuf type {@code DropPickupCenterMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:DropPickupCenterMsg)
        com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.internal_static_DropPickupCenterMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.internal_static_DropPickupCenterMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.class, com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.newBuilder()
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
        itemId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        count_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.internal_static_DropPickupCenterMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg build() {
        com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg result = new com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg(this);
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
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.itemId_ = itemId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.count_ = count_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg.getDefaultInstance()) return this;
        if (other.hasPackageId()) {
          setPackageId(other.getPackageId());
        }
        if (other.hasDropItemId()) {
          setDropItemId(other.getDropItemId());
        }
        if (other.hasItemId()) {
          setItemId(other.getItemId());
        }
        if (other.hasCount()) {
          setCount(other.getCount());
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
        com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.drop.DropPickupCenterProto.DropPickupCenterMsg) e.getUnfinishedMessage();
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

      private int itemId_ ;
      /**
       * <code>optional int32 itemId = 3;</code>
       */
      public boolean hasItemId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 itemId = 3;</code>
       */
      public int getItemId() {
        return itemId_;
      }
      /**
       * <code>optional int32 itemId = 3;</code>
       */
      public Builder setItemId(int value) {
        bitField0_ |= 0x00000004;
        itemId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 itemId = 3;</code>
       */
      public Builder clearItemId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        itemId_ = 0;
        onChanged();
        return this;
      }

      private int count_ ;
      /**
       * <code>optional int32 count = 4;</code>
       */
      public boolean hasCount() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 count = 4;</code>
       */
      public int getCount() {
        return count_;
      }
      /**
       * <code>optional int32 count = 4;</code>
       */
      public Builder setCount(int value) {
        bitField0_ |= 0x00000008;
        count_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 count = 4;</code>
       */
      public Builder clearCount() {
        bitField0_ = (bitField0_ & ~0x00000008);
        count_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:DropPickupCenterMsg)
    }

    static {
      defaultInstance = new DropPickupCenterMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:DropPickupCenterMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DropPickupCenterMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_DropPickupCenterMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036drop/DropPickupCenterMsg.proto\"[\n\023Drop" +
      "PickupCenterMsg\022\021\n\tpackageId\030\001 \001(\005\022\022\n\ndr" +
      "opItemId\030\002 \001(\003\022\016\n\006itemId\030\003 \001(\005\022\r\n\005count\030" +
      "\004 \001(\005B>\n%com.chuangyou.common.protobuf.p" +
      "b.dropB\025DropPickupCenterProto"
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
    internal_static_DropPickupCenterMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_DropPickupCenterMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_DropPickupCenterMsg_descriptor,
        new java.lang.String[] { "PackageId", "DropItemId", "ItemId", "Count", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
