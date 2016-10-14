// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Create1V1PvPCampaignMsg.proto

package com.chuangyou.common.protobuf.pb.campaign;

public final class Create1V1PvPCampaignProto {
  private Create1V1PvPCampaignProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface Create1V1PvPCampaignMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Create1V1PvPCampaignMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int64 redId = 1;</code>
     *
     * <pre>
     *�췽ID
     * </pre>
     */
    boolean hasRedId();
    /**
     * <code>optional int64 redId = 1;</code>
     *
     * <pre>
     *�췽ID
     * </pre>
     */
    long getRedId();

    /**
     * <code>optional int64 blueId = 2;</code>
     *
     * <pre>
     *��ɫ��ID
     * </pre>
     */
    boolean hasBlueId();
    /**
     * <code>optional int64 blueId = 2;</code>
     *
     * <pre>
     *��ɫ��ID
     * </pre>
     */
    long getBlueId();
  }
  /**
   * Protobuf type {@code Create1V1PvPCampaignMsg}
   */
  public static final class Create1V1PvPCampaignMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:Create1V1PvPCampaignMsg)
      Create1V1PvPCampaignMsgOrBuilder {
    // Use Create1V1PvPCampaignMsg.newBuilder() to construct.
    private Create1V1PvPCampaignMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Create1V1PvPCampaignMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Create1V1PvPCampaignMsg defaultInstance;
    public static Create1V1PvPCampaignMsg getDefaultInstance() {
      return defaultInstance;
    }

    public Create1V1PvPCampaignMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Create1V1PvPCampaignMsg(
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
              redId_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              blueId_ = input.readInt64();
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
      return com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.internal_static_Create1V1PvPCampaignMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.internal_static_Create1V1PvPCampaignMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.class, com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<Create1V1PvPCampaignMsg> PARSER =
        new com.google.protobuf.AbstractParser<Create1V1PvPCampaignMsg>() {
      public Create1V1PvPCampaignMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Create1V1PvPCampaignMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Create1V1PvPCampaignMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int REDID_FIELD_NUMBER = 1;
    private long redId_;
    /**
     * <code>optional int64 redId = 1;</code>
     *
     * <pre>
     *�췽ID
     * </pre>
     */
    public boolean hasRedId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 redId = 1;</code>
     *
     * <pre>
     *�췽ID
     * </pre>
     */
    public long getRedId() {
      return redId_;
    }

    public static final int BLUEID_FIELD_NUMBER = 2;
    private long blueId_;
    /**
     * <code>optional int64 blueId = 2;</code>
     *
     * <pre>
     *��ɫ��ID
     * </pre>
     */
    public boolean hasBlueId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 blueId = 2;</code>
     *
     * <pre>
     *��ɫ��ID
     * </pre>
     */
    public long getBlueId() {
      return blueId_;
    }

    private void initFields() {
      redId_ = 0L;
      blueId_ = 0L;
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
        output.writeInt64(1, redId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, blueId_);
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
          .computeInt64Size(1, redId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, blueId_);
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

    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg prototype) {
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
     * Protobuf type {@code Create1V1PvPCampaignMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Create1V1PvPCampaignMsg)
        com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.internal_static_Create1V1PvPCampaignMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.internal_static_Create1V1PvPCampaignMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.class, com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.newBuilder()
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
        redId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        blueId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.internal_static_Create1V1PvPCampaignMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg build() {
        com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg result = new com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.redId_ = redId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.blueId_ = blueId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg.getDefaultInstance()) return this;
        if (other.hasRedId()) {
          setRedId(other.getRedId());
        }
        if (other.hasBlueId()) {
          setBlueId(other.getBlueId());
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
        com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.campaign.Create1V1PvPCampaignProto.Create1V1PvPCampaignMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long redId_ ;
      /**
       * <code>optional int64 redId = 1;</code>
       *
       * <pre>
       *�췽ID
       * </pre>
       */
      public boolean hasRedId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int64 redId = 1;</code>
       *
       * <pre>
       *�췽ID
       * </pre>
       */
      public long getRedId() {
        return redId_;
      }
      /**
       * <code>optional int64 redId = 1;</code>
       *
       * <pre>
       *�췽ID
       * </pre>
       */
      public Builder setRedId(long value) {
        bitField0_ |= 0x00000001;
        redId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 redId = 1;</code>
       *
       * <pre>
       *�췽ID
       * </pre>
       */
      public Builder clearRedId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        redId_ = 0L;
        onChanged();
        return this;
      }

      private long blueId_ ;
      /**
       * <code>optional int64 blueId = 2;</code>
       *
       * <pre>
       *��ɫ��ID
       * </pre>
       */
      public boolean hasBlueId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int64 blueId = 2;</code>
       *
       * <pre>
       *��ɫ��ID
       * </pre>
       */
      public long getBlueId() {
        return blueId_;
      }
      /**
       * <code>optional int64 blueId = 2;</code>
       *
       * <pre>
       *��ɫ��ID
       * </pre>
       */
      public Builder setBlueId(long value) {
        bitField0_ |= 0x00000002;
        blueId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 blueId = 2;</code>
       *
       * <pre>
       *��ɫ��ID
       * </pre>
       */
      public Builder clearBlueId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        blueId_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:Create1V1PvPCampaignMsg)
    }

    static {
      defaultInstance = new Create1V1PvPCampaignMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:Create1V1PvPCampaignMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Create1V1PvPCampaignMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_Create1V1PvPCampaignMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035Create1V1PvPCampaignMsg.proto\"8\n\027Creat" +
      "e1V1PvPCampaignMsg\022\r\n\005redId\030\001 \001(\003\022\016\n\006blu" +
      "eId\030\002 \001(\003BF\n)com.chuangyou.common.protob" +
      "uf.pb.campaignB\031Create1V1PvPCampaignProt" +
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
    internal_static_Create1V1PvPCampaignMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Create1V1PvPCampaignMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_Create1V1PvPCampaignMsg_descriptor,
        new java.lang.String[] { "RedId", "BlueId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}