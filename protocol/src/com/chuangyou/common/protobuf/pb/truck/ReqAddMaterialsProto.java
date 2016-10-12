// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReqAddMaterials.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class ReqAddMaterialsProto {
  private ReqAddMaterialsProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqAddMaterialsOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ReqAddMaterials)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车id
     * </pre>
     */
    boolean hasTruckid();
    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车id
     * </pre>
     */
    long getTruckid();

    /**
     * <code>required int32 addtype = 2;</code>
     *
     * <pre>
     *添加类型 1.灵石添加 2.物品添加	
     * </pre>
     */
    boolean hasAddtype();
    /**
     * <code>required int32 addtype = 2;</code>
     *
     * <pre>
     *添加类型 1.灵石添加 2.物品添加	
     * </pre>
     */
    int getAddtype();

    /**
     * <code>required int32 itemTypeId = 3;</code>
     *
     * <pre>
     *使用物品的ID
     * </pre>
     */
    boolean hasItemTypeId();
    /**
     * <code>required int32 itemTypeId = 3;</code>
     *
     * <pre>
     *使用物品的ID
     * </pre>
     */
    int getItemTypeId();

    /**
     * <code>required int32 count = 4;</code>
     *
     * <pre>
     *添加物质的数量
     * </pre>
     */
    boolean hasCount();
    /**
     * <code>required int32 count = 4;</code>
     *
     * <pre>
     *添加物质的数量
     * </pre>
     */
    int getCount();
  }
  /**
   * Protobuf type {@code ReqAddMaterials}
   *
   * <pre>
   *请求添加物质
   * </pre>
   */
  public static final class ReqAddMaterials extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ReqAddMaterials)
      ReqAddMaterialsOrBuilder {
    // Use ReqAddMaterials.newBuilder() to construct.
    private ReqAddMaterials(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReqAddMaterials(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReqAddMaterials defaultInstance;
    public static ReqAddMaterials getDefaultInstance() {
      return defaultInstance;
    }

    public ReqAddMaterials getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReqAddMaterials(
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
              addtype_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              itemTypeId_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.internal_static_ReqAddMaterials_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.internal_static_ReqAddMaterials_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.class, com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.Builder.class);
    }

    public static com.google.protobuf.Parser<ReqAddMaterials> PARSER =
        new com.google.protobuf.AbstractParser<ReqAddMaterials>() {
      public ReqAddMaterials parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqAddMaterials(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReqAddMaterials> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TRUCKID_FIELD_NUMBER = 1;
    private long truckid_;
    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车id
     * </pre>
     */
    public boolean hasTruckid() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 truckid = 1;</code>
     *
     * <pre>
     *镖车id
     * </pre>
     */
    public long getTruckid() {
      return truckid_;
    }

    public static final int ADDTYPE_FIELD_NUMBER = 2;
    private int addtype_;
    /**
     * <code>required int32 addtype = 2;</code>
     *
     * <pre>
     *添加类型 1.灵石添加 2.物品添加	
     * </pre>
     */
    public boolean hasAddtype() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 addtype = 2;</code>
     *
     * <pre>
     *添加类型 1.灵石添加 2.物品添加	
     * </pre>
     */
    public int getAddtype() {
      return addtype_;
    }

    public static final int ITEMTYPEID_FIELD_NUMBER = 3;
    private int itemTypeId_;
    /**
     * <code>required int32 itemTypeId = 3;</code>
     *
     * <pre>
     *使用物品的ID
     * </pre>
     */
    public boolean hasItemTypeId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 itemTypeId = 3;</code>
     *
     * <pre>
     *使用物品的ID
     * </pre>
     */
    public int getItemTypeId() {
      return itemTypeId_;
    }

    public static final int COUNT_FIELD_NUMBER = 4;
    private int count_;
    /**
     * <code>required int32 count = 4;</code>
     *
     * <pre>
     *添加物质的数量
     * </pre>
     */
    public boolean hasCount() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 count = 4;</code>
     *
     * <pre>
     *添加物质的数量
     * </pre>
     */
    public int getCount() {
      return count_;
    }

    private void initFields() {
      truckid_ = 0L;
      addtype_ = 0;
      itemTypeId_ = 0;
      count_ = 0;
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
      if (!hasAddtype()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasItemTypeId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCount()) {
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
        output.writeInt32(2, addtype_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, itemTypeId_);
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
          .computeInt64Size(1, truckid_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, addtype_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, itemTypeId_);
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

    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials prototype) {
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
     * Protobuf type {@code ReqAddMaterials}
     *
     * <pre>
     *请求添加物质
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ReqAddMaterials)
        com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterialsOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.internal_static_ReqAddMaterials_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.internal_static_ReqAddMaterials_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.class, com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.newBuilder()
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
        addtype_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        itemTypeId_ = 0;
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
        return com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.internal_static_ReqAddMaterials_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials build() {
        com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials result = new com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.truckid_ = truckid_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.addtype_ = addtype_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.itemTypeId_ = itemTypeId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.count_ = count_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials.getDefaultInstance()) return this;
        if (other.hasTruckid()) {
          setTruckid(other.getTruckid());
        }
        if (other.hasAddtype()) {
          setAddtype(other.getAddtype());
        }
        if (other.hasItemTypeId()) {
          setItemTypeId(other.getItemTypeId());
        }
        if (other.hasCount()) {
          setCount(other.getCount());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTruckid()) {
          
          return false;
        }
        if (!hasAddtype()) {
          
          return false;
        }
        if (!hasItemTypeId()) {
          
          return false;
        }
        if (!hasCount()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.ReqAddMaterialsProto.ReqAddMaterials) e.getUnfinishedMessage();
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
       *镖车id
       * </pre>
       */
      public boolean hasTruckid() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 truckid = 1;</code>
       *
       * <pre>
       *镖车id
       * </pre>
       */
      public long getTruckid() {
        return truckid_;
      }
      /**
       * <code>required int64 truckid = 1;</code>
       *
       * <pre>
       *镖车id
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
       *镖车id
       * </pre>
       */
      public Builder clearTruckid() {
        bitField0_ = (bitField0_ & ~0x00000001);
        truckid_ = 0L;
        onChanged();
        return this;
      }

      private int addtype_ ;
      /**
       * <code>required int32 addtype = 2;</code>
       *
       * <pre>
       *添加类型 1.灵石添加 2.物品添加	
       * </pre>
       */
      public boolean hasAddtype() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 addtype = 2;</code>
       *
       * <pre>
       *添加类型 1.灵石添加 2.物品添加	
       * </pre>
       */
      public int getAddtype() {
        return addtype_;
      }
      /**
       * <code>required int32 addtype = 2;</code>
       *
       * <pre>
       *添加类型 1.灵石添加 2.物品添加	
       * </pre>
       */
      public Builder setAddtype(int value) {
        bitField0_ |= 0x00000002;
        addtype_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 addtype = 2;</code>
       *
       * <pre>
       *添加类型 1.灵石添加 2.物品添加	
       * </pre>
       */
      public Builder clearAddtype() {
        bitField0_ = (bitField0_ & ~0x00000002);
        addtype_ = 0;
        onChanged();
        return this;
      }

      private int itemTypeId_ ;
      /**
       * <code>required int32 itemTypeId = 3;</code>
       *
       * <pre>
       *使用物品的ID
       * </pre>
       */
      public boolean hasItemTypeId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 itemTypeId = 3;</code>
       *
       * <pre>
       *使用物品的ID
       * </pre>
       */
      public int getItemTypeId() {
        return itemTypeId_;
      }
      /**
       * <code>required int32 itemTypeId = 3;</code>
       *
       * <pre>
       *使用物品的ID
       * </pre>
       */
      public Builder setItemTypeId(int value) {
        bitField0_ |= 0x00000004;
        itemTypeId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 itemTypeId = 3;</code>
       *
       * <pre>
       *使用物品的ID
       * </pre>
       */
      public Builder clearItemTypeId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        itemTypeId_ = 0;
        onChanged();
        return this;
      }

      private int count_ ;
      /**
       * <code>required int32 count = 4;</code>
       *
       * <pre>
       *添加物质的数量
       * </pre>
       */
      public boolean hasCount() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int32 count = 4;</code>
       *
       * <pre>
       *添加物质的数量
       * </pre>
       */
      public int getCount() {
        return count_;
      }
      /**
       * <code>required int32 count = 4;</code>
       *
       * <pre>
       *添加物质的数量
       * </pre>
       */
      public Builder setCount(int value) {
        bitField0_ |= 0x00000008;
        count_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 count = 4;</code>
       *
       * <pre>
       *添加物质的数量
       * </pre>
       */
      public Builder clearCount() {
        bitField0_ = (bitField0_ & ~0x00000008);
        count_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ReqAddMaterials)
    }

    static {
      defaultInstance = new ReqAddMaterials(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ReqAddMaterials)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReqAddMaterials_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ReqAddMaterials_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025ReqAddMaterials.proto\"V\n\017ReqAddMateria" +
      "ls\022\017\n\007truckid\030\001 \002(\003\022\017\n\007addtype\030\002 \002(\005\022\022\n\n" +
      "itemTypeId\030\003 \002(\005\022\r\n\005count\030\004 \002(\005B>\n&com.c" +
      "huangyou.common.protobuf.pb.truckB\024ReqAd" +
      "dMaterialsProto"
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
    internal_static_ReqAddMaterials_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ReqAddMaterials_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ReqAddMaterials_descriptor,
        new java.lang.String[] { "Truckid", "Addtype", "ItemTypeId", "Count", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
