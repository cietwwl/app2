// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RespMyTruckStatu.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class RespMyTruckStatuProto {
  private RespMyTruckStatuProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RespMyTruckStatuOrBuilder extends
      // @@protoc_insertion_point(interface_extends:RespMyTruckStatu)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 statu = 1;</code>
     *
     * <pre>
     *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
     * </pre>
     */
    boolean hasStatu();
    /**
     * <code>required int32 statu = 1;</code>
     *
     * <pre>
     *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
     * </pre>
     */
    int getStatu();

    /**
     * <code>optional int64 id = 2;</code>
     *
     * <pre>
     *镖车ID	
     * </pre>
     */
    boolean hasId();
    /**
     * <code>optional int64 id = 2;</code>
     *
     * <pre>
     *镖车ID	
     * </pre>
     */
    long getId();

    /**
     * <code>optional int32 nextCheckPoint = 3;</code>
     *
     * <pre>
     *下一个路点位置
     * </pre>
     */
    boolean hasNextCheckPoint();
    /**
     * <code>optional int32 nextCheckPoint = 3;</code>
     *
     * <pre>
     *下一个路点位置
     * </pre>
     */
    int getNextCheckPoint();

    /**
     * <code>optional int32 truckState = 4;</code>
     *
     * <pre>
     *镖车运镖状态
     * </pre>
     */
    boolean hasTruckState();
    /**
     * <code>optional int32 truckState = 4;</code>
     *
     * <pre>
     *镖车运镖状态
     * </pre>
     */
    int getTruckState();

    /**
     * <code>optional .TruckStatu status = 5;</code>
     *
     * <pre>
     *镖车状态
     * </pre>
     */
    boolean hasStatus();
    /**
     * <code>optional .TruckStatu status = 5;</code>
     *
     * <pre>
     *镖车状态
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu getStatus();
    /**
     * <code>optional .TruckStatu status = 5;</code>
     *
     * <pre>
     *镖车状态
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatuOrBuilder getStatusOrBuilder();
  }
  /**
   * Protobuf type {@code RespMyTruckStatu}
   *
   * <pre>
   *回应和自己相关镖车的状态
   * </pre>
   */
  public static final class RespMyTruckStatu extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:RespMyTruckStatu)
      RespMyTruckStatuOrBuilder {
    // Use RespMyTruckStatu.newBuilder() to construct.
    private RespMyTruckStatu(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RespMyTruckStatu(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RespMyTruckStatu defaultInstance;
    public static RespMyTruckStatu getDefaultInstance() {
      return defaultInstance;
    }

    public RespMyTruckStatu getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RespMyTruckStatu(
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
              id_ = input.readInt64();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              nextCheckPoint_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              truckState_ = input.readInt32();
              break;
            }
            case 42: {
              com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.Builder subBuilder = null;
              if (((bitField0_ & 0x00000010) == 0x00000010)) {
                subBuilder = status_.toBuilder();
              }
              status_ = input.readMessage(com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(status_);
                status_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000010;
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
      return com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.internal_static_RespMyTruckStatu_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.internal_static_RespMyTruckStatu_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.class, com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.Builder.class);
    }

    public static com.google.protobuf.Parser<RespMyTruckStatu> PARSER =
        new com.google.protobuf.AbstractParser<RespMyTruckStatu>() {
      public RespMyTruckStatu parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RespMyTruckStatu(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RespMyTruckStatu> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int STATU_FIELD_NUMBER = 1;
    private int statu_;
    /**
     * <code>required int32 statu = 1;</code>
     *
     * <pre>
     *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
     * </pre>
     */
    public boolean hasStatu() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 statu = 1;</code>
     *
     * <pre>
     *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
     * </pre>
     */
    public int getStatu() {
      return statu_;
    }

    public static final int ID_FIELD_NUMBER = 2;
    private long id_;
    /**
     * <code>optional int64 id = 2;</code>
     *
     * <pre>
     *镖车ID	
     * </pre>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 id = 2;</code>
     *
     * <pre>
     *镖车ID	
     * </pre>
     */
    public long getId() {
      return id_;
    }

    public static final int NEXTCHECKPOINT_FIELD_NUMBER = 3;
    private int nextCheckPoint_;
    /**
     * <code>optional int32 nextCheckPoint = 3;</code>
     *
     * <pre>
     *下一个路点位置
     * </pre>
     */
    public boolean hasNextCheckPoint() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 nextCheckPoint = 3;</code>
     *
     * <pre>
     *下一个路点位置
     * </pre>
     */
    public int getNextCheckPoint() {
      return nextCheckPoint_;
    }

    public static final int TRUCKSTATE_FIELD_NUMBER = 4;
    private int truckState_;
    /**
     * <code>optional int32 truckState = 4;</code>
     *
     * <pre>
     *镖车运镖状态
     * </pre>
     */
    public boolean hasTruckState() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 truckState = 4;</code>
     *
     * <pre>
     *镖车运镖状态
     * </pre>
     */
    public int getTruckState() {
      return truckState_;
    }

    public static final int STATUS_FIELD_NUMBER = 5;
    private com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu status_;
    /**
     * <code>optional .TruckStatu status = 5;</code>
     *
     * <pre>
     *镖车状态
     * </pre>
     */
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional .TruckStatu status = 5;</code>
     *
     * <pre>
     *镖车状态
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu getStatus() {
      return status_;
    }
    /**
     * <code>optional .TruckStatu status = 5;</code>
     *
     * <pre>
     *镖车状态
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatuOrBuilder getStatusOrBuilder() {
      return status_;
    }

    private void initFields() {
      statu_ = 0;
      id_ = 0L;
      nextCheckPoint_ = 0;
      truckState_ = 0;
      status_ = com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasStatu()) {
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
        output.writeInt32(1, statu_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, id_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, nextCheckPoint_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, truckState_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeMessage(5, status_);
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
          .computeInt64Size(2, id_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, nextCheckPoint_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, truckState_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(5, status_);
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

    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu prototype) {
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
     * Protobuf type {@code RespMyTruckStatu}
     *
     * <pre>
     *回应和自己相关镖车的状态
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:RespMyTruckStatu)
        com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatuOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.internal_static_RespMyTruckStatu_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.internal_static_RespMyTruckStatu_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.class, com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.newBuilder()
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
          getStatusFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        statu_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        nextCheckPoint_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        truckState_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        if (statusBuilder_ == null) {
          status_ = com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.getDefaultInstance();
        } else {
          statusBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.internal_static_RespMyTruckStatu_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu build() {
        com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu result = new com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.statu_ = statu_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.nextCheckPoint_ = nextCheckPoint_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.truckState_ = truckState_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        if (statusBuilder_ == null) {
          result.status_ = status_;
        } else {
          result.status_ = statusBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu.getDefaultInstance()) return this;
        if (other.hasStatu()) {
          setStatu(other.getStatu());
        }
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasNextCheckPoint()) {
          setNextCheckPoint(other.getNextCheckPoint());
        }
        if (other.hasTruckState()) {
          setTruckState(other.getTruckState());
        }
        if (other.hasStatus()) {
          mergeStatus(other.getStatus());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasStatu()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.RespMyTruckStatuProto.RespMyTruckStatu) e.getUnfinishedMessage();
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
       * <code>required int32 statu = 1;</code>
       *
       * <pre>
       *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
       * </pre>
       */
      public boolean hasStatu() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 statu = 1;</code>
       *
       * <pre>
       *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
       * </pre>
       */
      public int getStatu() {
        return statu_;
      }
      /**
       * <code>required int32 statu = 1;</code>
       *
       * <pre>
       *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
       * </pre>
       */
      public Builder setStatu(int value) {
        bitField0_ |= 0x00000001;
        statu_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 statu = 1;</code>
       *
       * <pre>
       *运镖状态 0.无状态 1.运镖中 2.护镖中 3.劫镖中
       * </pre>
       */
      public Builder clearStatu() {
        bitField0_ = (bitField0_ & ~0x00000001);
        statu_ = 0;
        onChanged();
        return this;
      }

      private long id_ ;
      /**
       * <code>optional int64 id = 2;</code>
       *
       * <pre>
       *镖车ID	
       * </pre>
       */
      public boolean hasId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int64 id = 2;</code>
       *
       * <pre>
       *镖车ID	
       * </pre>
       */
      public long getId() {
        return id_;
      }
      /**
       * <code>optional int64 id = 2;</code>
       *
       * <pre>
       *镖车ID	
       * </pre>
       */
      public Builder setId(long value) {
        bitField0_ |= 0x00000002;
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 id = 2;</code>
       *
       * <pre>
       *镖车ID	
       * </pre>
       */
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        id_ = 0L;
        onChanged();
        return this;
      }

      private int nextCheckPoint_ ;
      /**
       * <code>optional int32 nextCheckPoint = 3;</code>
       *
       * <pre>
       *下一个路点位置
       * </pre>
       */
      public boolean hasNextCheckPoint() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 nextCheckPoint = 3;</code>
       *
       * <pre>
       *下一个路点位置
       * </pre>
       */
      public int getNextCheckPoint() {
        return nextCheckPoint_;
      }
      /**
       * <code>optional int32 nextCheckPoint = 3;</code>
       *
       * <pre>
       *下一个路点位置
       * </pre>
       */
      public Builder setNextCheckPoint(int value) {
        bitField0_ |= 0x00000004;
        nextCheckPoint_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 nextCheckPoint = 3;</code>
       *
       * <pre>
       *下一个路点位置
       * </pre>
       */
      public Builder clearNextCheckPoint() {
        bitField0_ = (bitField0_ & ~0x00000004);
        nextCheckPoint_ = 0;
        onChanged();
        return this;
      }

      private int truckState_ ;
      /**
       * <code>optional int32 truckState = 4;</code>
       *
       * <pre>
       *镖车运镖状态
       * </pre>
       */
      public boolean hasTruckState() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 truckState = 4;</code>
       *
       * <pre>
       *镖车运镖状态
       * </pre>
       */
      public int getTruckState() {
        return truckState_;
      }
      /**
       * <code>optional int32 truckState = 4;</code>
       *
       * <pre>
       *镖车运镖状态
       * </pre>
       */
      public Builder setTruckState(int value) {
        bitField0_ |= 0x00000008;
        truckState_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 truckState = 4;</code>
       *
       * <pre>
       *镖车运镖状态
       * </pre>
       */
      public Builder clearTruckState() {
        bitField0_ = (bitField0_ & ~0x00000008);
        truckState_ = 0;
        onChanged();
        return this;
      }

      private com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu status_ = com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu, com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.Builder, com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatuOrBuilder> statusBuilder_;
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu getStatus() {
        if (statusBuilder_ == null) {
          return status_;
        } else {
          return statusBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public Builder setStatus(com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu value) {
        if (statusBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          status_ = value;
          onChanged();
        } else {
          statusBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000010;
        return this;
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public Builder setStatus(
          com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.Builder builderForValue) {
        if (statusBuilder_ == null) {
          status_ = builderForValue.build();
          onChanged();
        } else {
          statusBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000010;
        return this;
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public Builder mergeStatus(com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu value) {
        if (statusBuilder_ == null) {
          if (((bitField0_ & 0x00000010) == 0x00000010) &&
              status_ != com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.getDefaultInstance()) {
            status_ =
              com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.newBuilder(status_).mergeFrom(value).buildPartial();
          } else {
            status_ = value;
          }
          onChanged();
        } else {
          statusBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000010;
        return this;
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public Builder clearStatus() {
        if (statusBuilder_ == null) {
          status_ = com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.getDefaultInstance();
          onChanged();
        } else {
          statusBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.Builder getStatusBuilder() {
        bitField0_ |= 0x00000010;
        onChanged();
        return getStatusFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatuOrBuilder getStatusOrBuilder() {
        if (statusBuilder_ != null) {
          return statusBuilder_.getMessageOrBuilder();
        } else {
          return status_;
        }
      }
      /**
       * <code>optional .TruckStatu status = 5;</code>
       *
       * <pre>
       *镖车状态
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu, com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.Builder, com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatuOrBuilder> 
          getStatusFieldBuilder() {
        if (statusBuilder_ == null) {
          statusBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu, com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatu.Builder, com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.TruckStatuOrBuilder>(
                  getStatus(),
                  getParentForChildren(),
                  isClean());
          status_ = null;
        }
        return statusBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:RespMyTruckStatu)
    }

    static {
      defaultInstance = new RespMyTruckStatu(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:RespMyTruckStatu)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RespMyTruckStatu_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_RespMyTruckStatu_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026RespMyTruckStatu.proto\032\020TruckStatu.pro" +
      "to\"v\n\020RespMyTruckStatu\022\r\n\005statu\030\001 \002(\005\022\n\n" +
      "\002id\030\002 \001(\003\022\026\n\016nextCheckPoint\030\003 \001(\005\022\022\n\ntru" +
      "ckState\030\004 \001(\005\022\033\n\006status\030\005 \001(\0132\013.TruckSta" +
      "tuB?\n&com.chuangyou.common.protobuf.pb.t" +
      "ruckB\025RespMyTruckStatuProto"
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
          com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.getDescriptor(),
        }, assigner);
    internal_static_RespMyTruckStatu_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_RespMyTruckStatu_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_RespMyTruckStatu_descriptor,
        new java.lang.String[] { "Statu", "Id", "NextCheckPoint", "TruckState", "Status", });
    com.chuangyou.common.protobuf.pb.truck.TruckStatuProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}