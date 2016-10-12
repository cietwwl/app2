// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: RespTruckAttChg.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class RespTruckAttChgProto {
  private RespTruckAttChgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RespTruckAttChgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:RespTruckAttChg)
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
     * <code>required int32 type = 2;</code>
     *
     * <pre>
     *属性类型
     * </pre>
     */
    boolean hasType();
    /**
     * <code>required int32 type = 2;</code>
     *
     * <pre>
     *属性类型
     * </pre>
     */
    int getType();

    /**
     * <code>required int32 value = 3;</code>
     *
     * <pre>
     *属性值
     * </pre>
     */
    boolean hasValue();
    /**
     * <code>required int32 value = 3;</code>
     *
     * <pre>
     *属性值
     * </pre>
     */
    int getValue();
  }
  /**
   * Protobuf type {@code RespTruckAttChg}
   *
   * <pre>
   *回应
   * </pre>
   */
  public static final class RespTruckAttChg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:RespTruckAttChg)
      RespTruckAttChgOrBuilder {
    // Use RespTruckAttChg.newBuilder() to construct.
    private RespTruckAttChg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RespTruckAttChg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RespTruckAttChg defaultInstance;
    public static RespTruckAttChg getDefaultInstance() {
      return defaultInstance;
    }

    public RespTruckAttChg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RespTruckAttChg(
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
              type_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              value_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.internal_static_RespTruckAttChg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.internal_static_RespTruckAttChg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.class, com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.Builder.class);
    }

    public static com.google.protobuf.Parser<RespTruckAttChg> PARSER =
        new com.google.protobuf.AbstractParser<RespTruckAttChg>() {
      public RespTruckAttChg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RespTruckAttChg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RespTruckAttChg> getParserForType() {
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

    public static final int TYPE_FIELD_NUMBER = 2;
    private int type_;
    /**
     * <code>required int32 type = 2;</code>
     *
     * <pre>
     *属性类型
     * </pre>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 type = 2;</code>
     *
     * <pre>
     *属性类型
     * </pre>
     */
    public int getType() {
      return type_;
    }

    public static final int VALUE_FIELD_NUMBER = 3;
    private int value_;
    /**
     * <code>required int32 value = 3;</code>
     *
     * <pre>
     *属性值
     * </pre>
     */
    public boolean hasValue() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 value = 3;</code>
     *
     * <pre>
     *属性值
     * </pre>
     */
    public int getValue() {
      return value_;
    }

    private void initFields() {
      truckID_ = 0L;
      type_ = 0;
      value_ = 0;
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
      if (!hasType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasValue()) {
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
        output.writeInt32(2, type_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, value_);
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
          .computeInt32Size(2, type_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, value_);
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

    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg prototype) {
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
     * Protobuf type {@code RespTruckAttChg}
     *
     * <pre>
     *回应
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:RespTruckAttChg)
        com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.internal_static_RespTruckAttChg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.internal_static_RespTruckAttChg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.class, com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.newBuilder()
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
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        value_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.internal_static_RespTruckAttChg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg build() {
        com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg result = new com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.truckID_ = truckID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.value_ = value_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg.getDefaultInstance()) return this;
        if (other.hasTruckID()) {
          setTruckID(other.getTruckID());
        }
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTruckID()) {
          
          return false;
        }
        if (!hasType()) {
          
          return false;
        }
        if (!hasValue()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.RespTruckAttChgProto.RespTruckAttChg) e.getUnfinishedMessage();
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

      private int type_ ;
      /**
       * <code>required int32 type = 2;</code>
       *
       * <pre>
       *属性类型
       * </pre>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 type = 2;</code>
       *
       * <pre>
       *属性类型
       * </pre>
       */
      public int getType() {
        return type_;
      }
      /**
       * <code>required int32 type = 2;</code>
       *
       * <pre>
       *属性类型
       * </pre>
       */
      public Builder setType(int value) {
        bitField0_ |= 0x00000002;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 type = 2;</code>
       *
       * <pre>
       *属性类型
       * </pre>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        type_ = 0;
        onChanged();
        return this;
      }

      private int value_ ;
      /**
       * <code>required int32 value = 3;</code>
       *
       * <pre>
       *属性值
       * </pre>
       */
      public boolean hasValue() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 value = 3;</code>
       *
       * <pre>
       *属性值
       * </pre>
       */
      public int getValue() {
        return value_;
      }
      /**
       * <code>required int32 value = 3;</code>
       *
       * <pre>
       *属性值
       * </pre>
       */
      public Builder setValue(int value) {
        bitField0_ |= 0x00000004;
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 value = 3;</code>
       *
       * <pre>
       *属性值
       * </pre>
       */
      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000004);
        value_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:RespTruckAttChg)
    }

    static {
      defaultInstance = new RespTruckAttChg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:RespTruckAttChg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_RespTruckAttChg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_RespTruckAttChg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025RespTruckAttChg.proto\"?\n\017RespTruckAttC" +
      "hg\022\017\n\007truckID\030\001 \002(\003\022\014\n\004type\030\002 \002(\005\022\r\n\005val" +
      "ue\030\003 \002(\005B>\n&com.chuangyou.common.protobu" +
      "f.pb.truckB\024RespTruckAttChgProto"
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
    internal_static_RespTruckAttChg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_RespTruckAttChg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_RespTruckAttChg_descriptor,
        new java.lang.String[] { "TruckID", "Type", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
