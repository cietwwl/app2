// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: InnerReqTruckComplete.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class InnerReqTruckCompleteProto {
  private InnerReqTruckCompleteProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface InnerReqTruckCompleteOrBuilder extends
      // @@protoc_insertion_point(interface_extends:InnerReqTruckComplete)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 trucktype = 1;</code>
     *
     * <pre>
     *镖车类型	
     * </pre>
     */
    boolean hasTrucktype();
    /**
     * <code>required int32 trucktype = 1;</code>
     *
     * <pre>
     *镖车类型	
     * </pre>
     */
    int getTrucktype();

    /**
     * <code>required int32 state = 2;</code>
     *
     * <pre>
     *完成状态 1.正常完成 2.劫镖完成 3.超时完成
     * </pre>
     */
    boolean hasState();
    /**
     * <code>required int32 state = 2;</code>
     *
     * <pre>
     *完成状态 1.正常完成 2.劫镖完成 3.超时完成
     * </pre>
     */
    int getState();

    /**
     * <code>required int32 mat = 3;</code>
     *
     * <pre>
     *物资数量
     * </pre>
     */
    boolean hasMat();
    /**
     * <code>required int32 mat = 3;</code>
     *
     * <pre>
     *物资数量
     * </pre>
     */
    int getMat();

    /**
     * <code>required int32 robbed = 4;</code>
     *
     * <pre>
     *被截过镖
     * </pre>
     */
    boolean hasRobbed();
    /**
     * <code>required int32 robbed = 4;</code>
     *
     * <pre>
     *被截过镖
     * </pre>
     */
    int getRobbed();

    /**
     * <code>required int32 truckertype = 5;</code>
     *
     * <pre>
     *镖师头衔
     * </pre>
     */
    boolean hasTruckertype();
    /**
     * <code>required int32 truckertype = 5;</code>
     *
     * <pre>
     *镖师头衔
     * </pre>
     */
    int getTruckertype();
  }
  /**
   * Protobuf type {@code InnerReqTruckComplete}
   *
   * <pre>
   *运镖完成
   * </pre>
   */
  public static final class InnerReqTruckComplete extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:InnerReqTruckComplete)
      InnerReqTruckCompleteOrBuilder {
    // Use InnerReqTruckComplete.newBuilder() to construct.
    private InnerReqTruckComplete(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private InnerReqTruckComplete(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final InnerReqTruckComplete defaultInstance;
    public static InnerReqTruckComplete getDefaultInstance() {
      return defaultInstance;
    }

    public InnerReqTruckComplete getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private InnerReqTruckComplete(
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
              trucktype_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              state_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              mat_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              robbed_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              truckertype_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.internal_static_InnerReqTruckComplete_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.internal_static_InnerReqTruckComplete_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.class, com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.Builder.class);
    }

    public static com.google.protobuf.Parser<InnerReqTruckComplete> PARSER =
        new com.google.protobuf.AbstractParser<InnerReqTruckComplete>() {
      public InnerReqTruckComplete parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new InnerReqTruckComplete(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<InnerReqTruckComplete> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TRUCKTYPE_FIELD_NUMBER = 1;
    private int trucktype_;
    /**
     * <code>required int32 trucktype = 1;</code>
     *
     * <pre>
     *镖车类型	
     * </pre>
     */
    public boolean hasTrucktype() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 trucktype = 1;</code>
     *
     * <pre>
     *镖车类型	
     * </pre>
     */
    public int getTrucktype() {
      return trucktype_;
    }

    public static final int STATE_FIELD_NUMBER = 2;
    private int state_;
    /**
     * <code>required int32 state = 2;</code>
     *
     * <pre>
     *完成状态 1.正常完成 2.劫镖完成 3.超时完成
     * </pre>
     */
    public boolean hasState() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 state = 2;</code>
     *
     * <pre>
     *完成状态 1.正常完成 2.劫镖完成 3.超时完成
     * </pre>
     */
    public int getState() {
      return state_;
    }

    public static final int MAT_FIELD_NUMBER = 3;
    private int mat_;
    /**
     * <code>required int32 mat = 3;</code>
     *
     * <pre>
     *物资数量
     * </pre>
     */
    public boolean hasMat() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 mat = 3;</code>
     *
     * <pre>
     *物资数量
     * </pre>
     */
    public int getMat() {
      return mat_;
    }

    public static final int ROBBED_FIELD_NUMBER = 4;
    private int robbed_;
    /**
     * <code>required int32 robbed = 4;</code>
     *
     * <pre>
     *被截过镖
     * </pre>
     */
    public boolean hasRobbed() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 robbed = 4;</code>
     *
     * <pre>
     *被截过镖
     * </pre>
     */
    public int getRobbed() {
      return robbed_;
    }

    public static final int TRUCKERTYPE_FIELD_NUMBER = 5;
    private int truckertype_;
    /**
     * <code>required int32 truckertype = 5;</code>
     *
     * <pre>
     *镖师头衔
     * </pre>
     */
    public boolean hasTruckertype() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int32 truckertype = 5;</code>
     *
     * <pre>
     *镖师头衔
     * </pre>
     */
    public int getTruckertype() {
      return truckertype_;
    }

    private void initFields() {
      trucktype_ = 0;
      state_ = 0;
      mat_ = 0;
      robbed_ = 0;
      truckertype_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTrucktype()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasState()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMat()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRobbed()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTruckertype()) {
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
        output.writeInt32(1, trucktype_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, state_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, mat_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, robbed_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, truckertype_);
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
          .computeInt32Size(1, trucktype_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, state_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, mat_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, robbed_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, truckertype_);
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

    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete prototype) {
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
     * Protobuf type {@code InnerReqTruckComplete}
     *
     * <pre>
     *运镖完成
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:InnerReqTruckComplete)
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckCompleteOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.internal_static_InnerReqTruckComplete_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.internal_static_InnerReqTruckComplete_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.class, com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.newBuilder()
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
        trucktype_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        state_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        mat_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        robbed_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        truckertype_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.internal_static_InnerReqTruckComplete_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete build() {
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete result = new com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.trucktype_ = trucktype_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.state_ = state_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.mat_ = mat_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.robbed_ = robbed_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.truckertype_ = truckertype_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete.getDefaultInstance()) return this;
        if (other.hasTrucktype()) {
          setTrucktype(other.getTrucktype());
        }
        if (other.hasState()) {
          setState(other.getState());
        }
        if (other.hasMat()) {
          setMat(other.getMat());
        }
        if (other.hasRobbed()) {
          setRobbed(other.getRobbed());
        }
        if (other.hasTruckertype()) {
          setTruckertype(other.getTruckertype());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTrucktype()) {
          
          return false;
        }
        if (!hasState()) {
          
          return false;
        }
        if (!hasMat()) {
          
          return false;
        }
        if (!hasRobbed()) {
          
          return false;
        }
        if (!hasTruckertype()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int trucktype_ ;
      /**
       * <code>required int32 trucktype = 1;</code>
       *
       * <pre>
       *镖车类型	
       * </pre>
       */
      public boolean hasTrucktype() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 trucktype = 1;</code>
       *
       * <pre>
       *镖车类型	
       * </pre>
       */
      public int getTrucktype() {
        return trucktype_;
      }
      /**
       * <code>required int32 trucktype = 1;</code>
       *
       * <pre>
       *镖车类型	
       * </pre>
       */
      public Builder setTrucktype(int value) {
        bitField0_ |= 0x00000001;
        trucktype_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 trucktype = 1;</code>
       *
       * <pre>
       *镖车类型	
       * </pre>
       */
      public Builder clearTrucktype() {
        bitField0_ = (bitField0_ & ~0x00000001);
        trucktype_ = 0;
        onChanged();
        return this;
      }

      private int state_ ;
      /**
       * <code>required int32 state = 2;</code>
       *
       * <pre>
       *完成状态 1.正常完成 2.劫镖完成 3.超时完成
       * </pre>
       */
      public boolean hasState() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 state = 2;</code>
       *
       * <pre>
       *完成状态 1.正常完成 2.劫镖完成 3.超时完成
       * </pre>
       */
      public int getState() {
        return state_;
      }
      /**
       * <code>required int32 state = 2;</code>
       *
       * <pre>
       *完成状态 1.正常完成 2.劫镖完成 3.超时完成
       * </pre>
       */
      public Builder setState(int value) {
        bitField0_ |= 0x00000002;
        state_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 state = 2;</code>
       *
       * <pre>
       *完成状态 1.正常完成 2.劫镖完成 3.超时完成
       * </pre>
       */
      public Builder clearState() {
        bitField0_ = (bitField0_ & ~0x00000002);
        state_ = 0;
        onChanged();
        return this;
      }

      private int mat_ ;
      /**
       * <code>required int32 mat = 3;</code>
       *
       * <pre>
       *物资数量
       * </pre>
       */
      public boolean hasMat() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 mat = 3;</code>
       *
       * <pre>
       *物资数量
       * </pre>
       */
      public int getMat() {
        return mat_;
      }
      /**
       * <code>required int32 mat = 3;</code>
       *
       * <pre>
       *物资数量
       * </pre>
       */
      public Builder setMat(int value) {
        bitField0_ |= 0x00000004;
        mat_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 mat = 3;</code>
       *
       * <pre>
       *物资数量
       * </pre>
       */
      public Builder clearMat() {
        bitField0_ = (bitField0_ & ~0x00000004);
        mat_ = 0;
        onChanged();
        return this;
      }

      private int robbed_ ;
      /**
       * <code>required int32 robbed = 4;</code>
       *
       * <pre>
       *被截过镖
       * </pre>
       */
      public boolean hasRobbed() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int32 robbed = 4;</code>
       *
       * <pre>
       *被截过镖
       * </pre>
       */
      public int getRobbed() {
        return robbed_;
      }
      /**
       * <code>required int32 robbed = 4;</code>
       *
       * <pre>
       *被截过镖
       * </pre>
       */
      public Builder setRobbed(int value) {
        bitField0_ |= 0x00000008;
        robbed_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 robbed = 4;</code>
       *
       * <pre>
       *被截过镖
       * </pre>
       */
      public Builder clearRobbed() {
        bitField0_ = (bitField0_ & ~0x00000008);
        robbed_ = 0;
        onChanged();
        return this;
      }

      private int truckertype_ ;
      /**
       * <code>required int32 truckertype = 5;</code>
       *
       * <pre>
       *镖师头衔
       * </pre>
       */
      public boolean hasTruckertype() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required int32 truckertype = 5;</code>
       *
       * <pre>
       *镖师头衔
       * </pre>
       */
      public int getTruckertype() {
        return truckertype_;
      }
      /**
       * <code>required int32 truckertype = 5;</code>
       *
       * <pre>
       *镖师头衔
       * </pre>
       */
      public Builder setTruckertype(int value) {
        bitField0_ |= 0x00000010;
        truckertype_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 truckertype = 5;</code>
       *
       * <pre>
       *镖师头衔
       * </pre>
       */
      public Builder clearTruckertype() {
        bitField0_ = (bitField0_ & ~0x00000010);
        truckertype_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:InnerReqTruckComplete)
    }

    static {
      defaultInstance = new InnerReqTruckComplete(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:InnerReqTruckComplete)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_InnerReqTruckComplete_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_InnerReqTruckComplete_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033InnerReqTruckComplete.proto\"k\n\025InnerRe" +
      "qTruckComplete\022\021\n\ttrucktype\030\001 \002(\005\022\r\n\005sta" +
      "te\030\002 \002(\005\022\013\n\003mat\030\003 \002(\005\022\016\n\006robbed\030\004 \002(\005\022\023\n" +
      "\013truckertype\030\005 \002(\005BD\n&com.chuangyou.comm" +
      "on.protobuf.pb.truckB\032InnerReqTruckCompl" +
      "eteProto"
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
    internal_static_InnerReqTruckComplete_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_InnerReqTruckComplete_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_InnerReqTruckComplete_descriptor,
        new java.lang.String[] { "Trucktype", "State", "Mat", "Robbed", "Truckertype", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
