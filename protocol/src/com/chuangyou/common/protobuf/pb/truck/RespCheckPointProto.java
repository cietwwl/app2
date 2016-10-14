// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RespCheckPoint.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class RespCheckPointProto {
  private RespCheckPointProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RespCheckPointOrBuilder extends
      // @@protoc_insertion_point(interface_extends:RespCheckPoint)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 statu = 1;</code>
     *
     * <pre>
     *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
     * </pre>
     */
    boolean hasStatu();
    /**
     * <code>optional int32 statu = 1;</code>
     *
     * <pre>
     *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
     * </pre>
     */
    int getStatu();

    /**
     * <code>optional int32 remain = 2;</code>
     *
     * <pre>
     *剩余购买的物资
     * </pre>
     */
    boolean hasRemain();
    /**
     * <code>optional int32 remain = 2;</code>
     *
     * <pre>
     *剩余购买的物资
     * </pre>
     */
    int getRemain();

    /**
     * <code>optional int32 price = 3;</code>
     *
     * <pre>
     *物资的价格
     * </pre>
     */
    boolean hasPrice();
    /**
     * <code>optional int32 price = 3;</code>
     *
     * <pre>
     *物资的价格
     * </pre>
     */
    int getPrice();
  }
  /**
   * Protobuf type {@code RespCheckPoint}
   *
   * <pre>
   *回应所有镖车的状态
   * </pre>
   */
  public static final class RespCheckPoint extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:RespCheckPoint)
      RespCheckPointOrBuilder {
    // Use RespCheckPoint.newBuilder() to construct.
    private RespCheckPoint(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RespCheckPoint(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RespCheckPoint defaultInstance;
    public static RespCheckPoint getDefaultInstance() {
      return defaultInstance;
    }

    public RespCheckPoint getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RespCheckPoint(
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
              statu_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              remain_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              price_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.internal_static_RespCheckPoint_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.internal_static_RespCheckPoint_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.class, com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.Builder.class);
    }

    public static com.google.protobuf.Parser<RespCheckPoint> PARSER =
        new com.google.protobuf.AbstractParser<RespCheckPoint>() {
      public RespCheckPoint parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RespCheckPoint(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RespCheckPoint> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int STATU_FIELD_NUMBER = 1;
    private int statu_;
    /**
     * <code>optional int32 statu = 1;</code>
     *
     * <pre>
     *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
     * </pre>
     */
    public boolean hasStatu() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 statu = 1;</code>
     *
     * <pre>
     *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
     * </pre>
     */
    public int getStatu() {
      return statu_;
    }

    public static final int REMAIN_FIELD_NUMBER = 2;
    private int remain_;
    /**
     * <code>optional int32 remain = 2;</code>
     *
     * <pre>
     *剩余购买的物资
     * </pre>
     */
    public boolean hasRemain() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 remain = 2;</code>
     *
     * <pre>
     *剩余购买的物资
     * </pre>
     */
    public int getRemain() {
      return remain_;
    }

    public static final int PRICE_FIELD_NUMBER = 3;
    private int price_;
    /**
     * <code>optional int32 price = 3;</code>
     *
     * <pre>
     *物资的价格
     * </pre>
     */
    public boolean hasPrice() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 price = 3;</code>
     *
     * <pre>
     *物资的价格
     * </pre>
     */
    public int getPrice() {
      return price_;
    }

    private void initFields() {
      statu_ = 0;
      remain_ = 0;
      price_ = 0;
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
        output.writeInt32(1, statu_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, remain_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, price_);
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
          .computeInt32Size(1, statu_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, remain_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, price_);
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

    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint prototype) {
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
     * Protobuf type {@code RespCheckPoint}
     *
     * <pre>
     *回应所有镖车的状态
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:RespCheckPoint)
        com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPointOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.internal_static_RespCheckPoint_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.internal_static_RespCheckPoint_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.class, com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.newBuilder()
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
        statu_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        remain_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        price_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.internal_static_RespCheckPoint_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint build() {
        com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint result = new com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.statu_ = statu_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.remain_ = remain_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.price_ = price_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint.getDefaultInstance()) return this;
        if (other.hasStatu()) {
          setStatu(other.getStatu());
        }
        if (other.hasRemain()) {
          setRemain(other.getRemain());
        }
        if (other.hasPrice()) {
          setPrice(other.getPrice());
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
        com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.RespCheckPointProto.RespCheckPoint) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int statu_ ;
      /**
       * <code>optional int32 statu = 1;</code>
       *
       * <pre>
       *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
       * </pre>
       */
      public boolean hasStatu() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 statu = 1;</code>
       *
       * <pre>
       *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
       * </pre>
       */
      public int getStatu() {
        return statu_;
      }
      /**
       * <code>optional int32 statu = 1;</code>
       *
       * <pre>
       *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
       * </pre>
       */
      public Builder setStatu(int value) {
        bitField0_ |= 0x00000001;
        statu_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 statu = 1;</code>
       *
       * <pre>
       *1.创建镖车 2.购买物资 3.不能创建镖车 4.不能添加物质 5.未创建镖车
       * </pre>
       */
      public Builder clearStatu() {
        bitField0_ = (bitField0_ & ~0x00000001);
        statu_ = 0;
        onChanged();
        return this;
      }

      private int remain_ ;
      /**
       * <code>optional int32 remain = 2;</code>
       *
       * <pre>
       *剩余购买的物资
       * </pre>
       */
      public boolean hasRemain() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 remain = 2;</code>
       *
       * <pre>
       *剩余购买的物资
       * </pre>
       */
      public int getRemain() {
        return remain_;
      }
      /**
       * <code>optional int32 remain = 2;</code>
       *
       * <pre>
       *剩余购买的物资
       * </pre>
       */
      public Builder setRemain(int value) {
        bitField0_ |= 0x00000002;
        remain_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 remain = 2;</code>
       *
       * <pre>
       *剩余购买的物资
       * </pre>
       */
      public Builder clearRemain() {
        bitField0_ = (bitField0_ & ~0x00000002);
        remain_ = 0;
        onChanged();
        return this;
      }

      private int price_ ;
      /**
       * <code>optional int32 price = 3;</code>
       *
       * <pre>
       *物资的价格
       * </pre>
       */
      public boolean hasPrice() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 price = 3;</code>
       *
       * <pre>
       *物资的价格
       * </pre>
       */
      public int getPrice() {
        return price_;
      }
      /**
       * <code>optional int32 price = 3;</code>
       *
       * <pre>
       *物资的价格
       * </pre>
       */
      public Builder setPrice(int value) {
        bitField0_ |= 0x00000004;
        price_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 price = 3;</code>
       *
       * <pre>
       *物资的价格
       * </pre>
       */
      public Builder clearPrice() {
        bitField0_ = (bitField0_ & ~0x00000004);
        price_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:RespCheckPoint)
    }

    static {
      defaultInstance = new RespCheckPoint(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:RespCheckPoint)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RespCheckPoint_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_RespCheckPoint_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024RespCheckPoint.proto\">\n\016RespCheckPoint" +
      "\022\r\n\005statu\030\001 \001(\005\022\016\n\006remain\030\002 \001(\005\022\r\n\005price" +
      "\030\003 \001(\005B=\n&com.chuangyou.common.protobuf." +
      "pb.truckB\023RespCheckPointProto"
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
    internal_static_RespCheckPoint_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_RespCheckPoint_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_RespCheckPoint_descriptor,
        new java.lang.String[] { "Statu", "Remain", "Price", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}