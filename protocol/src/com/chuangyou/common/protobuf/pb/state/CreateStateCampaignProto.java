// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: state/AddStateBuffMsg.proto

package com.chuangyou.common.protobuf.pb.state;

public final class CreateStateCampaignProto {
  private CreateStateCampaignProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CreateStateCampaignMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CreateStateCampaignMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    boolean hasCampaignId();
    /**
     * <code>required int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    int getCampaignId();
  }
  /**
   * Protobuf type {@code CreateStateCampaignMsg}
   */
  public static final class CreateStateCampaignMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:CreateStateCampaignMsg)
      CreateStateCampaignMsgOrBuilder {
    // Use CreateStateCampaignMsg.newBuilder() to construct.
    private CreateStateCampaignMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private CreateStateCampaignMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final CreateStateCampaignMsg defaultInstance;
    public static CreateStateCampaignMsg getDefaultInstance() {
      return defaultInstance;
    }

    public CreateStateCampaignMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private CreateStateCampaignMsg(
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
              campaignId_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.internal_static_CreateStateCampaignMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.internal_static_CreateStateCampaignMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.class, com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<CreateStateCampaignMsg> PARSER =
        new com.google.protobuf.AbstractParser<CreateStateCampaignMsg>() {
      public CreateStateCampaignMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CreateStateCampaignMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<CreateStateCampaignMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CAMPAIGNID_FIELD_NUMBER = 1;
    private int campaignId_;
    /**
     * <code>required int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    public boolean hasCampaignId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    public int getCampaignId() {
      return campaignId_;
    }

    private void initFields() {
      campaignId_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasCampaignId()) {
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
        output.writeInt32(1, campaignId_);
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
          .computeInt32Size(1, campaignId_);
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

    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg prototype) {
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
     * Protobuf type {@code CreateStateCampaignMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CreateStateCampaignMsg)
        com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.internal_static_CreateStateCampaignMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.internal_static_CreateStateCampaignMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.class, com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.newBuilder()
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
        campaignId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.internal_static_CreateStateCampaignMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg build() {
        com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg result = new com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.campaignId_ = campaignId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg.getDefaultInstance()) return this;
        if (other.hasCampaignId()) {
          setCampaignId(other.getCampaignId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCampaignId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.state.CreateStateCampaignProto.CreateStateCampaignMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int campaignId_ ;
      /**
       * <code>required int32 campaignId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public boolean hasCampaignId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 campaignId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public int getCampaignId() {
        return campaignId_;
      }
      /**
       * <code>required int32 campaignId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public Builder setCampaignId(int value) {
        bitField0_ |= 0x00000001;
        campaignId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 campaignId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public Builder clearCampaignId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        campaignId_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:CreateStateCampaignMsg)
    }

    static {
      defaultInstance = new CreateStateCampaignMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:CreateStateCampaignMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CreateStateCampaignMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_CreateStateCampaignMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033state/AddStateBuffMsg.proto\",\n\026CreateS" +
      "tateCampaignMsg\022\022\n\ncampaignId\030\001 \002(\005BB\n&c" +
      "om.chuangyou.common.protobuf.pb.stateB\030C" +
      "reateStateCampaignProto"
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
    internal_static_CreateStateCampaignMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CreateStateCampaignMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_CreateStateCampaignMsg_descriptor,
        new java.lang.String[] { "CampaignId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
