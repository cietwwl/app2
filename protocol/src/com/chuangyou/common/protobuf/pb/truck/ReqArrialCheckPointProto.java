// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReqArrialCheckPoint.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class ReqArrialCheckPointProto {
  private ReqArrialCheckPointProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqArrialCheckPointOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ReqArrialCheckPoint)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 truckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    boolean hasTruckID();
    /**
     * <code>required int64 truckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    long getTruckID();

    /**
     * <code>required int32 curCheckPoint = 2;</code>
     *
     * <pre>
     *当前检查点id
     * </pre>
     */
    boolean hasCurCheckPoint();
    /**
     * <code>required int32 curCheckPoint = 2;</code>
     *
     * <pre>
     *当前检查点id
     * </pre>
     */
    int getCurCheckPoint();
  }
  /**
   * Protobuf type {@code ReqArrialCheckPoint}
   *
   * <pre>
   *请求到底检查点
   * </pre>
   */
  public static final class ReqArrialCheckPoint extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ReqArrialCheckPoint)
      ReqArrialCheckPointOrBuilder {
    // Use ReqArrialCheckPoint.newBuilder() to construct.
    private ReqArrialCheckPoint(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReqArrialCheckPoint(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReqArrialCheckPoint defaultInstance;
    public static ReqArrialCheckPoint getDefaultInstance() {
      return defaultInstance;
    }

    public ReqArrialCheckPoint getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReqArrialCheckPoint(
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
              truckID_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              curCheckPoint_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.internal_static_ReqArrialCheckPoint_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.internal_static_ReqArrialCheckPoint_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.class, com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.Builder.class);
    }

    public static com.google.protobuf.Parser<ReqArrialCheckPoint> PARSER =
        new com.google.protobuf.AbstractParser<ReqArrialCheckPoint>() {
      public ReqArrialCheckPoint parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqArrialCheckPoint(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReqArrialCheckPoint> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TRUCKID_FIELD_NUMBER = 1;
    private long truckID_;
    /**
     * <code>required int64 truckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    public boolean hasTruckID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 truckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    public long getTruckID() {
      return truckID_;
    }

    public static final int CURCHECKPOINT_FIELD_NUMBER = 2;
    private int curCheckPoint_;
    /**
     * <code>required int32 curCheckPoint = 2;</code>
     *
     * <pre>
     *当前检查点id
     * </pre>
     */
    public boolean hasCurCheckPoint() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 curCheckPoint = 2;</code>
     *
     * <pre>
     *当前检查点id
     * </pre>
     */
    public int getCurCheckPoint() {
      return curCheckPoint_;
    }

    private void initFields() {
      truckID_ = 0L;
      curCheckPoint_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTruckID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCurCheckPoint()) {
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
        output.writeInt64(1, truckID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, curCheckPoint_);
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
          .computeInt64Size(1, truckID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, curCheckPoint_);
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

    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint prototype) {
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
     * Protobuf type {@code ReqArrialCheckPoint}
     *
     * <pre>
     *请求到底检查点
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ReqArrialCheckPoint)
        com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPointOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.internal_static_ReqArrialCheckPoint_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.internal_static_ReqArrialCheckPoint_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.class, com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.newBuilder()
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
        truckID_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        curCheckPoint_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.internal_static_ReqArrialCheckPoint_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint build() {
        com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint result = new com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.truckID_ = truckID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.curCheckPoint_ = curCheckPoint_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint.getDefaultInstance()) return this;
        if (other.hasTruckID()) {
          setTruckID(other.getTruckID());
        }
        if (other.hasCurCheckPoint()) {
          setCurCheckPoint(other.getCurCheckPoint());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTruckID()) {
          
          return false;
        }
        if (!hasCurCheckPoint()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.ReqArrialCheckPointProto.ReqArrialCheckPoint) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long truckID_ ;
      /**
       * <code>required int64 truckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public boolean hasTruckID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 truckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public long getTruckID() {
        return truckID_;
      }
      /**
       * <code>required int64 truckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public Builder setTruckID(long value) {
        bitField0_ |= 0x00000001;
        truckID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 truckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public Builder clearTruckID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        truckID_ = 0L;
        onChanged();
        return this;
      }

      private int curCheckPoint_ ;
      /**
       * <code>required int32 curCheckPoint = 2;</code>
       *
       * <pre>
       *当前检查点id
       * </pre>
       */
      public boolean hasCurCheckPoint() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 curCheckPoint = 2;</code>
       *
       * <pre>
       *当前检查点id
       * </pre>
       */
      public int getCurCheckPoint() {
        return curCheckPoint_;
      }
      /**
       * <code>required int32 curCheckPoint = 2;</code>
       *
       * <pre>
       *当前检查点id
       * </pre>
       */
      public Builder setCurCheckPoint(int value) {
        bitField0_ |= 0x00000002;
        curCheckPoint_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 curCheckPoint = 2;</code>
       *
       * <pre>
       *当前检查点id
       * </pre>
       */
      public Builder clearCurCheckPoint() {
        bitField0_ = (bitField0_ & ~0x00000002);
        curCheckPoint_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ReqArrialCheckPoint)
    }

    static {
      defaultInstance = new ReqArrialCheckPoint(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ReqArrialCheckPoint)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReqArrialCheckPoint_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ReqArrialCheckPoint_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031ReqArrialCheckPoint.proto\"=\n\023ReqArrial" +
      "CheckPoint\022\017\n\007truckID\030\001 \002(\003\022\025\n\rcurCheckP" +
      "oint\030\002 \002(\005BB\n&com.chuangyou.common.proto" +
      "buf.pb.truckB\030ReqArrialCheckPointProto"
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
    internal_static_ReqArrialCheckPoint_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ReqArrialCheckPoint_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ReqArrialCheckPoint_descriptor,
        new java.lang.String[] { "TruckID", "CurCheckPoint", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
