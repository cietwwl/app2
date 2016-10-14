// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AvatarCampaignRewardListMsg .proto

package com.chuangyou.common.protobuf.pb.avatar;

public final class AvatarCampaignRewardListProto {
  private AvatarCampaignRewardListProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface AvatarCampaignRewardListMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:AvatarCampaignRewardListMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    boolean hasCampaignId();
    /**
     * <code>optional int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    int getCampaignId();

    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    java.util.List<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg> 
        getAvatarRewardsList();
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg getAvatarRewards(int index);
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    int getAvatarRewardsCount();
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    java.util.List<? extends com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder> 
        getAvatarRewardsOrBuilderList();
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder getAvatarRewardsOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code AvatarCampaignRewardListMsg}
   */
  public static final class AvatarCampaignRewardListMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:AvatarCampaignRewardListMsg)
      AvatarCampaignRewardListMsgOrBuilder {
    // Use AvatarCampaignRewardListMsg.newBuilder() to construct.
    private AvatarCampaignRewardListMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private AvatarCampaignRewardListMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final AvatarCampaignRewardListMsg defaultInstance;
    public static AvatarCampaignRewardListMsg getDefaultInstance() {
      return defaultInstance;
    }

    public AvatarCampaignRewardListMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private AvatarCampaignRewardListMsg(
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
            case 18: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                avatarRewards_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg>();
                mutable_bitField0_ |= 0x00000002;
              }
              avatarRewards_.add(input.readMessage(com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.PARSER, extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          avatarRewards_ = java.util.Collections.unmodifiableList(avatarRewards_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.internal_static_AvatarCampaignRewardListMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.internal_static_AvatarCampaignRewardListMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.class, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<AvatarCampaignRewardListMsg> PARSER =
        new com.google.protobuf.AbstractParser<AvatarCampaignRewardListMsg>() {
      public AvatarCampaignRewardListMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AvatarCampaignRewardListMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<AvatarCampaignRewardListMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CAMPAIGNID_FIELD_NUMBER = 1;
    private int campaignId_;
    /**
     * <code>optional int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    public boolean hasCampaignId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 campaignId = 1;</code>
     *
     * <pre>
     *副本ID
     * </pre>
     */
    public int getCampaignId() {
      return campaignId_;
    }

    public static final int AVATARREWARDS_FIELD_NUMBER = 2;
    private java.util.List<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg> avatarRewards_;
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    public java.util.List<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg> getAvatarRewardsList() {
      return avatarRewards_;
    }
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    public java.util.List<? extends com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder> 
        getAvatarRewardsOrBuilderList() {
      return avatarRewards_;
    }
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    public int getAvatarRewardsCount() {
      return avatarRewards_.size();
    }
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg getAvatarRewards(int index) {
      return avatarRewards_.get(index);
    }
    /**
     * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
     */
    public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder getAvatarRewardsOrBuilder(
        int index) {
      return avatarRewards_.get(index);
    }

    private void initFields() {
      campaignId_ = 0;
      avatarRewards_ = java.util.Collections.emptyList();
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
        output.writeInt32(1, campaignId_);
      }
      for (int i = 0; i < avatarRewards_.size(); i++) {
        output.writeMessage(2, avatarRewards_.get(i));
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
      for (int i = 0; i < avatarRewards_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(2, avatarRewards_.get(i));
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

    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg prototype) {
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
     * Protobuf type {@code AvatarCampaignRewardListMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:AvatarCampaignRewardListMsg)
        com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.internal_static_AvatarCampaignRewardListMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.internal_static_AvatarCampaignRewardListMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.class, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.newBuilder()
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
          getAvatarRewardsFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        campaignId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        if (avatarRewardsBuilder_ == null) {
          avatarRewards_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          avatarRewardsBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.internal_static_AvatarCampaignRewardListMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg build() {
        com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg result = new com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.campaignId_ = campaignId_;
        if (avatarRewardsBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002)) {
            avatarRewards_ = java.util.Collections.unmodifiableList(avatarRewards_);
            bitField0_ = (bitField0_ & ~0x00000002);
          }
          result.avatarRewards_ = avatarRewards_;
        } else {
          result.avatarRewards_ = avatarRewardsBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg.getDefaultInstance()) return this;
        if (other.hasCampaignId()) {
          setCampaignId(other.getCampaignId());
        }
        if (avatarRewardsBuilder_ == null) {
          if (!other.avatarRewards_.isEmpty()) {
            if (avatarRewards_.isEmpty()) {
              avatarRewards_ = other.avatarRewards_;
              bitField0_ = (bitField0_ & ~0x00000002);
            } else {
              ensureAvatarRewardsIsMutable();
              avatarRewards_.addAll(other.avatarRewards_);
            }
            onChanged();
          }
        } else {
          if (!other.avatarRewards_.isEmpty()) {
            if (avatarRewardsBuilder_.isEmpty()) {
              avatarRewardsBuilder_.dispose();
              avatarRewardsBuilder_ = null;
              avatarRewards_ = other.avatarRewards_;
              bitField0_ = (bitField0_ & ~0x00000002);
              avatarRewardsBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getAvatarRewardsFieldBuilder() : null;
            } else {
              avatarRewardsBuilder_.addAllMessages(other.avatarRewards_);
            }
          }
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
        com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardListProto.AvatarCampaignRewardListMsg) e.getUnfinishedMessage();
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
       * <code>optional int32 campaignId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public boolean hasCampaignId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 campaignId = 1;</code>
       *
       * <pre>
       *副本ID
       * </pre>
       */
      public int getCampaignId() {
        return campaignId_;
      }
      /**
       * <code>optional int32 campaignId = 1;</code>
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
       * <code>optional int32 campaignId = 1;</code>
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

      private java.util.List<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg> avatarRewards_ =
        java.util.Collections.emptyList();
      private void ensureAvatarRewardsIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          avatarRewards_ = new java.util.ArrayList<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg>(avatarRewards_);
          bitField0_ |= 0x00000002;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder> avatarRewardsBuilder_;

      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg> getAvatarRewardsList() {
        if (avatarRewardsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(avatarRewards_);
        } else {
          return avatarRewardsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public int getAvatarRewardsCount() {
        if (avatarRewardsBuilder_ == null) {
          return avatarRewards_.size();
        } else {
          return avatarRewardsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg getAvatarRewards(int index) {
        if (avatarRewardsBuilder_ == null) {
          return avatarRewards_.get(index);
        } else {
          return avatarRewardsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder setAvatarRewards(
          int index, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg value) {
        if (avatarRewardsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAvatarRewardsIsMutable();
          avatarRewards_.set(index, value);
          onChanged();
        } else {
          avatarRewardsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder setAvatarRewards(
          int index, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder builderForValue) {
        if (avatarRewardsBuilder_ == null) {
          ensureAvatarRewardsIsMutable();
          avatarRewards_.set(index, builderForValue.build());
          onChanged();
        } else {
          avatarRewardsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder addAvatarRewards(com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg value) {
        if (avatarRewardsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAvatarRewardsIsMutable();
          avatarRewards_.add(value);
          onChanged();
        } else {
          avatarRewardsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder addAvatarRewards(
          int index, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg value) {
        if (avatarRewardsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAvatarRewardsIsMutable();
          avatarRewards_.add(index, value);
          onChanged();
        } else {
          avatarRewardsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder addAvatarRewards(
          com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder builderForValue) {
        if (avatarRewardsBuilder_ == null) {
          ensureAvatarRewardsIsMutable();
          avatarRewards_.add(builderForValue.build());
          onChanged();
        } else {
          avatarRewardsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder addAvatarRewards(
          int index, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder builderForValue) {
        if (avatarRewardsBuilder_ == null) {
          ensureAvatarRewardsIsMutable();
          avatarRewards_.add(index, builderForValue.build());
          onChanged();
        } else {
          avatarRewardsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder addAllAvatarRewards(
          java.lang.Iterable<? extends com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg> values) {
        if (avatarRewardsBuilder_ == null) {
          ensureAvatarRewardsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, avatarRewards_);
          onChanged();
        } else {
          avatarRewardsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder clearAvatarRewards() {
        if (avatarRewardsBuilder_ == null) {
          avatarRewards_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000002);
          onChanged();
        } else {
          avatarRewardsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public Builder removeAvatarRewards(int index) {
        if (avatarRewardsBuilder_ == null) {
          ensureAvatarRewardsIsMutable();
          avatarRewards_.remove(index);
          onChanged();
        } else {
          avatarRewardsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder getAvatarRewardsBuilder(
          int index) {
        return getAvatarRewardsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder getAvatarRewardsOrBuilder(
          int index) {
        if (avatarRewardsBuilder_ == null) {
          return avatarRewards_.get(index);  } else {
          return avatarRewardsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public java.util.List<? extends com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder> 
           getAvatarRewardsOrBuilderList() {
        if (avatarRewardsBuilder_ != null) {
          return avatarRewardsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(avatarRewards_);
        }
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder addAvatarRewardsBuilder() {
        return getAvatarRewardsFieldBuilder().addBuilder(
            com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder addAvatarRewardsBuilder(
          int index) {
        return getAvatarRewardsFieldBuilder().addBuilder(
            index, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.getDefaultInstance());
      }
      /**
       * <code>repeated .AvatarCampaignRewardMsg avatarRewards = 2;</code>
       */
      public java.util.List<com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder> 
           getAvatarRewardsBuilderList() {
        return getAvatarRewardsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder> 
          getAvatarRewardsFieldBuilder() {
        if (avatarRewardsBuilder_ == null) {
          avatarRewardsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsg.Builder, com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.AvatarCampaignRewardMsgOrBuilder>(
                  avatarRewards_,
                  ((bitField0_ & 0x00000002) == 0x00000002),
                  getParentForChildren(),
                  isClean());
          avatarRewards_ = null;
        }
        return avatarRewardsBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:AvatarCampaignRewardListMsg)
    }

    static {
      defaultInstance = new AvatarCampaignRewardListMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:AvatarCampaignRewardListMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AvatarCampaignRewardListMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_AvatarCampaignRewardListMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\"AvatarCampaignRewardListMsg .proto\032\035Av" +
      "atarCampaignRewardMsg.proto\"b\n\033AvatarCam" +
      "paignRewardListMsg\022\022\n\ncampaignId\030\001 \001(\005\022/" +
      "\n\ravatarRewards\030\002 \003(\0132\030.AvatarCampaignRe" +
      "wardMsgBH\n\'com.chuangyou.common.protobuf" +
      ".pb.avatarB\035AvatarCampaignRewardListProt" +
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
          com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.getDescriptor(),
        }, assigner);
    internal_static_AvatarCampaignRewardListMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AvatarCampaignRewardListMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_AvatarCampaignRewardListMsg_descriptor,
        new java.lang.String[] { "CampaignId", "AvatarRewards", });
    com.chuangyou.common.protobuf.pb.avatar.AvatarCampaignRewardProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
