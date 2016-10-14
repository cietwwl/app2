// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: InnerReqTruckFuncConsum.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class InnerReqTruckFuncConsumProto {
  private InnerReqTruckFuncConsumProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface InnerReqTruckFuncConsumOrBuilder extends
      // @@protoc_insertion_point(interface_extends:InnerReqTruckFuncConsum)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    boolean hasTruckid();
    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    long getTruckid();

    /**
     * <code>required int32 skillid = 2;</code>
     *
     * <pre>
     *技能id
     * </pre>
     */
    boolean hasSkillid();
    /**
     * <code>required int32 skillid = 2;</code>
     *
     * <pre>
     *技能id
     * </pre>
     */
    int getSkillid();

    /**
     * <code>required int32 itemtype = 3;</code>
     *
     * <pre>
     *消耗物品id
     * </pre>
     */
    boolean hasItemtype();
    /**
     * <code>required int32 itemtype = 3;</code>
     *
     * <pre>
     *消耗物品id
     * </pre>
     */
    int getItemtype();

    /**
     * <code>required int32 itemcount = 4;</code>
     *
     * <pre>
     *消耗物品数量
     * </pre>
     */
    boolean hasItemcount();
    /**
     * <code>required int32 itemcount = 4;</code>
     *
     * <pre>
     *消耗物品数量
     * </pre>
     */
    int getItemcount();
  }
  /**
   * Protobuf type {@code InnerReqTruckFuncConsum}
   *
   * <pre>
   *镖车数据
   * </pre>
   */
  public static final class InnerReqTruckFuncConsum extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:InnerReqTruckFuncConsum)
      InnerReqTruckFuncConsumOrBuilder {
    // Use InnerReqTruckFuncConsum.newBuilder() to construct.
    private InnerReqTruckFuncConsum(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private InnerReqTruckFuncConsum(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final InnerReqTruckFuncConsum defaultInstance;
    public static InnerReqTruckFuncConsum getDefaultInstance() {
      return defaultInstance;
    }

    public InnerReqTruckFuncConsum getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private InnerReqTruckFuncConsum(
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
              truckid_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              skillid_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              itemtype_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              itemcount_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.internal_static_InnerReqTruckFuncConsum_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.internal_static_InnerReqTruckFuncConsum_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.class, com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.Builder.class);
    }

    public static com.google.protobuf.Parser<InnerReqTruckFuncConsum> PARSER =
        new com.google.protobuf.AbstractParser<InnerReqTruckFuncConsum>() {
      public InnerReqTruckFuncConsum parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new InnerReqTruckFuncConsum(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<InnerReqTruckFuncConsum> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TRUCKID_FIELD_NUMBER = 1;
    private long truckid_;
    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    public boolean hasTruckid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    public long getTruckid() {
      return truckid_;
    }

    public static final int SKILLID_FIELD_NUMBER = 2;
    private int skillid_;
    /**
     * <code>required int32 skillid = 2;</code>
     *
     * <pre>
     *技能id
     * </pre>
     */
    public boolean hasSkillid() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 skillid = 2;</code>
     *
     * <pre>
     *技能id
     * </pre>
     */
    public int getSkillid() {
      return skillid_;
    }

    public static final int ITEMTYPE_FIELD_NUMBER = 3;
    private int itemtype_;
    /**
     * <code>required int32 itemtype = 3;</code>
     *
     * <pre>
     *消耗物品id
     * </pre>
     */
    public boolean hasItemtype() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 itemtype = 3;</code>
     *
     * <pre>
     *消耗物品id
     * </pre>
     */
    public int getItemtype() {
      return itemtype_;
    }

    public static final int ITEMCOUNT_FIELD_NUMBER = 4;
    private int itemcount_;
    /**
     * <code>required int32 itemcount = 4;</code>
     *
     * <pre>
     *消耗物品数量
     * </pre>
     */
    public boolean hasItemcount() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 itemcount = 4;</code>
     *
     * <pre>
     *消耗物品数量
     * </pre>
     */
    public int getItemcount() {
      return itemcount_;
    }

    private void initFields() {
      truckid_ = 0L;
      skillid_ = 0;
      itemtype_ = 0;
      itemcount_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTruckid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSkillid()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasItemtype()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasItemcount()) {
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
        output.writeInt64(1, truckid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, skillid_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, itemtype_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, itemcount_);
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
          .computeInt64Size(1, truckid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, skillid_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, itemtype_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, itemcount_);
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

    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum prototype) {
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
     * Protobuf type {@code InnerReqTruckFuncConsum}
     *
     * <pre>
     *镖车数据
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:InnerReqTruckFuncConsum)
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsumOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.internal_static_InnerReqTruckFuncConsum_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.internal_static_InnerReqTruckFuncConsum_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.class, com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.newBuilder()
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
        truckid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        skillid_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        itemtype_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        itemcount_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.internal_static_InnerReqTruckFuncConsum_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum build() {
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum result = new com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.truckid_ = truckid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.skillid_ = skillid_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.itemtype_ = itemtype_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.itemcount_ = itemcount_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum.getDefaultInstance()) return this;
        if (other.hasTruckid()) {
          setTruckid(other.getTruckid());
        }
        if (other.hasSkillid()) {
          setSkillid(other.getSkillid());
        }
        if (other.hasItemtype()) {
          setItemtype(other.getItemtype());
        }
        if (other.hasItemcount()) {
          setItemcount(other.getItemcount());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTruckid()) {
          
          return false;
        }
        if (!hasSkillid()) {
          
          return false;
        }
        if (!hasItemtype()) {
          
          return false;
        }
        if (!hasItemcount()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long truckid_ ;
      /**
       * <code>required int64 truckid = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public boolean hasTruckid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 truckid = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public long getTruckid() {
        return truckid_;
      }
      /**
       * <code>required int64 truckid = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public Builder setTruckid(long value) {
        bitField0_ |= 0x00000001;
        truckid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 truckid = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public Builder clearTruckid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        truckid_ = 0L;
        onChanged();
        return this;
      }

      private int skillid_ ;
      /**
       * <code>required int32 skillid = 2;</code>
       *
       * <pre>
       *技能id
       * </pre>
       */
      public boolean hasSkillid() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 skillid = 2;</code>
       *
       * <pre>
       *技能id
       * </pre>
       */
      public int getSkillid() {
        return skillid_;
      }
      /**
       * <code>required int32 skillid = 2;</code>
       *
       * <pre>
       *技能id
       * </pre>
       */
      public Builder setSkillid(int value) {
        bitField0_ |= 0x00000002;
        skillid_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 skillid = 2;</code>
       *
       * <pre>
       *技能id
       * </pre>
       */
      public Builder clearSkillid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        skillid_ = 0;
        onChanged();
        return this;
      }

      private int itemtype_ ;
      /**
       * <code>required int32 itemtype = 3;</code>
       *
       * <pre>
       *消耗物品id
       * </pre>
       */
      public boolean hasItemtype() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 itemtype = 3;</code>
       *
       * <pre>
       *消耗物品id
       * </pre>
       */
      public int getItemtype() {
        return itemtype_;
      }
      /**
       * <code>required int32 itemtype = 3;</code>
       *
       * <pre>
       *消耗物品id
       * </pre>
       */
      public Builder setItemtype(int value) {
        bitField0_ |= 0x00000004;
        itemtype_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 itemtype = 3;</code>
       *
       * <pre>
       *消耗物品id
       * </pre>
       */
      public Builder clearItemtype() {
        bitField0_ = (bitField0_ & ~0x00000004);
        itemtype_ = 0;
        onChanged();
        return this;
      }

      private int itemcount_ ;
      /**
       * <code>required int32 itemcount = 4;</code>
       *
       * <pre>
       *消耗物品数量
       * </pre>
       */
      public boolean hasItemcount() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int32 itemcount = 4;</code>
       *
       * <pre>
       *消耗物品数量
       * </pre>
       */
      public int getItemcount() {
        return itemcount_;
      }
      /**
       * <code>required int32 itemcount = 4;</code>
       *
       * <pre>
       *消耗物品数量
       * </pre>
       */
      public Builder setItemcount(int value) {
        bitField0_ |= 0x00000008;
        itemcount_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 itemcount = 4;</code>
       *
       * <pre>
       *消耗物品数量
       * </pre>
       */
      public Builder clearItemcount() {
        bitField0_ = (bitField0_ & ~0x00000008);
        itemcount_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:InnerReqTruckFuncConsum)
    }

    static {
      defaultInstance = new InnerReqTruckFuncConsum(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:InnerReqTruckFuncConsum)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_InnerReqTruckFuncConsum_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_InnerReqTruckFuncConsum_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035InnerReqTruckFuncConsum.proto\"`\n\027Inner" +
      "ReqTruckFuncConsum\022\017\n\007truckid\030\001 \002(\003\022\017\n\007s" +
      "killid\030\002 \002(\005\022\020\n\010itemtype\030\003 \002(\005\022\021\n\titemco" +
      "unt\030\004 \002(\005BF\n&com.chuangyou.common.protob" +
      "uf.pb.truckB\034InnerReqTruckFuncConsumProt" +
      "o"
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
    internal_static_InnerReqTruckFuncConsum_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_InnerReqTruckFuncConsum_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_InnerReqTruckFuncConsum_descriptor,
        new java.lang.String[] { "Truckid", "Skillid", "Itemtype", "Itemcount", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}