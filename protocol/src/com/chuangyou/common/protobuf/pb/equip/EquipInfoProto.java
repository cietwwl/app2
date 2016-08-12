// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: equip/EquipInfoMsg.proto

package com.chuangyou.common.protobuf.pb.equip;

public final class EquipInfoProto {
  private EquipInfoProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface EquipInfoMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:EquipInfoMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int64 equipId = 1;</code>
     *
     * <pre>
     * 装备道具ID
     * </pre>
     */
    boolean hasEquipId();
    /**
     * <code>optional int64 equipId = 1;</code>
     *
     * <pre>
     * 装备道具ID
     * </pre>
     */
    long getEquipId();

    /**
     * <code>optional int32 awakenLevel = 2;</code>
     *
     * <pre>
     * 装备觉醒等级
     * </pre>
     */
    boolean hasAwakenLevel();
    /**
     * <code>optional int32 awakenLevel = 2;</code>
     *
     * <pre>
     * 装备觉醒等级
     * </pre>
     */
    int getAwakenLevel();

    /**
     * <code>optional int32 awakenPoint = 3;</code>
     *
     * <pre>
     * 觉醒当前级突破点
     * </pre>
     */
    boolean hasAwakenPoint();
    /**
     * <code>optional int32 awakenPoint = 3;</code>
     *
     * <pre>
     * 觉醒当前级突破点
     * </pre>
     */
    int getAwakenPoint();

    /**
     * <code>optional int32 stoneTempId = 4;</code>
     *
     * <pre>
     * 装备注入魂石模板ID
     * </pre>
     */
    boolean hasStoneTempId();
    /**
     * <code>optional int32 stoneTempId = 4;</code>
     *
     * <pre>
     * 装备注入魂石模板ID
     * </pre>
     */
    int getStoneTempId();
  }
  /**
   * Protobuf type {@code EquipInfoMsg}
   *
   * <pre>
   *装备信息
   * </pre>
   */
  public static final class EquipInfoMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:EquipInfoMsg)
      EquipInfoMsgOrBuilder {
    // Use EquipInfoMsg.newBuilder() to construct.
    private EquipInfoMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private EquipInfoMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final EquipInfoMsg defaultInstance;
    public static EquipInfoMsg getDefaultInstance() {
      return defaultInstance;
    }

    public EquipInfoMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private EquipInfoMsg(
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
              equipId_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              awakenLevel_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              awakenPoint_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              stoneTempId_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.internal_static_EquipInfoMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.internal_static_EquipInfoMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.class, com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<EquipInfoMsg> PARSER =
        new com.google.protobuf.AbstractParser<EquipInfoMsg>() {
      public EquipInfoMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new EquipInfoMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<EquipInfoMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int EQUIPID_FIELD_NUMBER = 1;
    private long equipId_;
    /**
     * <code>optional int64 equipId = 1;</code>
     *
     * <pre>
     * 装备道具ID
     * </pre>
     */
    public boolean hasEquipId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 equipId = 1;</code>
     *
     * <pre>
     * 装备道具ID
     * </pre>
     */
    public long getEquipId() {
      return equipId_;
    }

    public static final int AWAKENLEVEL_FIELD_NUMBER = 2;
    private int awakenLevel_;
    /**
     * <code>optional int32 awakenLevel = 2;</code>
     *
     * <pre>
     * 装备觉醒等级
     * </pre>
     */
    public boolean hasAwakenLevel() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 awakenLevel = 2;</code>
     *
     * <pre>
     * 装备觉醒等级
     * </pre>
     */
    public int getAwakenLevel() {
      return awakenLevel_;
    }

    public static final int AWAKENPOINT_FIELD_NUMBER = 3;
    private int awakenPoint_;
    /**
     * <code>optional int32 awakenPoint = 3;</code>
     *
     * <pre>
     * 觉醒当前级突破点
     * </pre>
     */
    public boolean hasAwakenPoint() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 awakenPoint = 3;</code>
     *
     * <pre>
     * 觉醒当前级突破点
     * </pre>
     */
    public int getAwakenPoint() {
      return awakenPoint_;
    }

    public static final int STONETEMPID_FIELD_NUMBER = 4;
    private int stoneTempId_;
    /**
     * <code>optional int32 stoneTempId = 4;</code>
     *
     * <pre>
     * 装备注入魂石模板ID
     * </pre>
     */
    public boolean hasStoneTempId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 stoneTempId = 4;</code>
     *
     * <pre>
     * 装备注入魂石模板ID
     * </pre>
     */
    public int getStoneTempId() {
      return stoneTempId_;
    }

    private void initFields() {
      equipId_ = 0L;
      awakenLevel_ = 0;
      awakenPoint_ = 0;
      stoneTempId_ = 0;
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
        output.writeInt64(1, equipId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, awakenLevel_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, awakenPoint_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, stoneTempId_);
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
          .computeInt64Size(1, equipId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, awakenLevel_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, awakenPoint_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, stoneTempId_);
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

    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg prototype) {
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
     * Protobuf type {@code EquipInfoMsg}
     *
     * <pre>
     *装备信息
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:EquipInfoMsg)
        com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.internal_static_EquipInfoMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.internal_static_EquipInfoMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.class, com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.newBuilder()
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
        equipId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        awakenLevel_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        awakenPoint_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        stoneTempId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.internal_static_EquipInfoMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg build() {
        com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg result = new com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.equipId_ = equipId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.awakenLevel_ = awakenLevel_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.awakenPoint_ = awakenPoint_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.stoneTempId_ = stoneTempId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg.getDefaultInstance()) return this;
        if (other.hasEquipId()) {
          setEquipId(other.getEquipId());
        }
        if (other.hasAwakenLevel()) {
          setAwakenLevel(other.getAwakenLevel());
        }
        if (other.hasAwakenPoint()) {
          setAwakenPoint(other.getAwakenPoint());
        }
        if (other.hasStoneTempId()) {
          setStoneTempId(other.getStoneTempId());
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
        com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.equip.EquipInfoProto.EquipInfoMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long equipId_ ;
      /**
       * <code>optional int64 equipId = 1;</code>
       *
       * <pre>
       * 装备道具ID
       * </pre>
       */
      public boolean hasEquipId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int64 equipId = 1;</code>
       *
       * <pre>
       * 装备道具ID
       * </pre>
       */
      public long getEquipId() {
        return equipId_;
      }
      /**
       * <code>optional int64 equipId = 1;</code>
       *
       * <pre>
       * 装备道具ID
       * </pre>
       */
      public Builder setEquipId(long value) {
        bitField0_ |= 0x00000001;
        equipId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 equipId = 1;</code>
       *
       * <pre>
       * 装备道具ID
       * </pre>
       */
      public Builder clearEquipId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        equipId_ = 0L;
        onChanged();
        return this;
      }

      private int awakenLevel_ ;
      /**
       * <code>optional int32 awakenLevel = 2;</code>
       *
       * <pre>
       * 装备觉醒等级
       * </pre>
       */
      public boolean hasAwakenLevel() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 awakenLevel = 2;</code>
       *
       * <pre>
       * 装备觉醒等级
       * </pre>
       */
      public int getAwakenLevel() {
        return awakenLevel_;
      }
      /**
       * <code>optional int32 awakenLevel = 2;</code>
       *
       * <pre>
       * 装备觉醒等级
       * </pre>
       */
      public Builder setAwakenLevel(int value) {
        bitField0_ |= 0x00000002;
        awakenLevel_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 awakenLevel = 2;</code>
       *
       * <pre>
       * 装备觉醒等级
       * </pre>
       */
      public Builder clearAwakenLevel() {
        bitField0_ = (bitField0_ & ~0x00000002);
        awakenLevel_ = 0;
        onChanged();
        return this;
      }

      private int awakenPoint_ ;
      /**
       * <code>optional int32 awakenPoint = 3;</code>
       *
       * <pre>
       * 觉醒当前级突破点
       * </pre>
       */
      public boolean hasAwakenPoint() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 awakenPoint = 3;</code>
       *
       * <pre>
       * 觉醒当前级突破点
       * </pre>
       */
      public int getAwakenPoint() {
        return awakenPoint_;
      }
      /**
       * <code>optional int32 awakenPoint = 3;</code>
       *
       * <pre>
       * 觉醒当前级突破点
       * </pre>
       */
      public Builder setAwakenPoint(int value) {
        bitField0_ |= 0x00000004;
        awakenPoint_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 awakenPoint = 3;</code>
       *
       * <pre>
       * 觉醒当前级突破点
       * </pre>
       */
      public Builder clearAwakenPoint() {
        bitField0_ = (bitField0_ & ~0x00000004);
        awakenPoint_ = 0;
        onChanged();
        return this;
      }

      private int stoneTempId_ ;
      /**
       * <code>optional int32 stoneTempId = 4;</code>
       *
       * <pre>
       * 装备注入魂石模板ID
       * </pre>
       */
      public boolean hasStoneTempId() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 stoneTempId = 4;</code>
       *
       * <pre>
       * 装备注入魂石模板ID
       * </pre>
       */
      public int getStoneTempId() {
        return stoneTempId_;
      }
      /**
       * <code>optional int32 stoneTempId = 4;</code>
       *
       * <pre>
       * 装备注入魂石模板ID
       * </pre>
       */
      public Builder setStoneTempId(int value) {
        bitField0_ |= 0x00000008;
        stoneTempId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 stoneTempId = 4;</code>
       *
       * <pre>
       * 装备注入魂石模板ID
       * </pre>
       */
      public Builder clearStoneTempId() {
        bitField0_ = (bitField0_ & ~0x00000008);
        stoneTempId_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:EquipInfoMsg)
    }

    static {
      defaultInstance = new EquipInfoMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:EquipInfoMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EquipInfoMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_EquipInfoMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030equip/EquipInfoMsg.proto\"^\n\014EquipInfoM" +
      "sg\022\017\n\007equipId\030\001 \001(\003\022\023\n\013awakenLevel\030\002 \001(\005" +
      "\022\023\n\013awakenPoint\030\003 \001(\005\022\023\n\013stoneTempId\030\004 \001" +
      "(\005B8\n&com.chuangyou.common.protobuf.pb.e" +
      "quipB\016EquipInfoProto"
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
    internal_static_EquipInfoMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EquipInfoMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_EquipInfoMsg_descriptor,
        new java.lang.String[] { "EquipId", "AwakenLevel", "AwakenPoint", "StoneTempId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
