// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: skill/OneKeyUpSkillOkMsg.proto

package com.chuangyou.common.protobuf.pb.skill;

public final class OneKeyUpSkillOkMsgProto {
  private OneKeyUpSkillOkMsgProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface OneKeyUpSkillOkMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:OneKeyUpSkillOkMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated int32 skillId = 1;</code>
     *
     * <pre>
     *已经学习了的技能ID
     * </pre>
     */
    java.util.List<java.lang.Integer> getSkillIdList();
    /**
     * <code>repeated int32 skillId = 1;</code>
     *
     * <pre>
     *已经学习了的技能ID
     * </pre>
     */
    int getSkillIdCount();
    /**
     * <code>repeated int32 skillId = 1;</code>
     *
     * <pre>
     *已经学习了的技能ID
     * </pre>
     */
    int getSkillId(int index);

    /**
     * <code>optional .UseGoods useGoods = 3;</code>
     *
     * <pre>
     *消耗的物品
     * </pre>
     */
    boolean hasUseGoods();
    /**
     * <code>optional .UseGoods useGoods = 3;</code>
     *
     * <pre>
     *消耗的物品
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods getUseGoods();
    /**
     * <code>optional .UseGoods useGoods = 3;</code>
     *
     * <pre>
     *消耗的物品
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoodsOrBuilder getUseGoodsOrBuilder();

    /**
     * <code>optional int32 needJade = 4;</code>
     *
     * <pre>
     *技能升级需要仙玉
     * </pre>
     */
    boolean hasNeedJade();
    /**
     * <code>optional int32 needJade = 4;</code>
     *
     * <pre>
     *技能升级需要仙玉
     * </pre>
     */
    int getNeedJade();

    /**
     * <code>optional int32 needStone = 5;</code>
     *
     * <pre>
     *技能升级需要灵石
     * </pre>
     */
    boolean hasNeedStone();
    /**
     * <code>optional int32 needStone = 5;</code>
     *
     * <pre>
     *技能升级需要灵石
     * </pre>
     */
    int getNeedStone();

    /**
     * <code>optional int32 needRepair = 6;</code>
     *
     * <pre>
     *技能升级需要修为
     * </pre>
     */
    boolean hasNeedRepair();
    /**
     * <code>optional int32 needRepair = 6;</code>
     *
     * <pre>
     *技能升级需要修为
     * </pre>
     */
    int getNeedRepair();
  }
  /**
   * Protobuf type {@code OneKeyUpSkillOkMsg}
   *
   * <pre>
   *一键升级主动技能成功
   * </pre>
   */
  public static final class OneKeyUpSkillOkMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:OneKeyUpSkillOkMsg)
      OneKeyUpSkillOkMsgOrBuilder {
    // Use OneKeyUpSkillOkMsg.newBuilder() to construct.
    private OneKeyUpSkillOkMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private OneKeyUpSkillOkMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final OneKeyUpSkillOkMsg defaultInstance;
    public static OneKeyUpSkillOkMsg getDefaultInstance() {
      return defaultInstance;
    }

    public OneKeyUpSkillOkMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private OneKeyUpSkillOkMsg(
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
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                skillId_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000001;
              }
              skillId_.add(input.readInt32());
              break;
            }
            case 10: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
                skillId_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000001;
              }
              while (input.getBytesUntilLimit() > 0) {
                skillId_.add(input.readInt32());
              }
              input.popLimit(limit);
              break;
            }
            case 26: {
              com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = useGoods_.toBuilder();
              }
              useGoods_ = input.readMessage(com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(useGoods_);
                useGoods_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 32: {
              bitField0_ |= 0x00000002;
              needJade_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000004;
              needStone_ = input.readInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000008;
              needRepair_ = input.readInt32();
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
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          skillId_ = java.util.Collections.unmodifiableList(skillId_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.internal_static_OneKeyUpSkillOkMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.internal_static_OneKeyUpSkillOkMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.class, com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<OneKeyUpSkillOkMsg> PARSER =
        new com.google.protobuf.AbstractParser<OneKeyUpSkillOkMsg>() {
      public OneKeyUpSkillOkMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new OneKeyUpSkillOkMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<OneKeyUpSkillOkMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int SKILLID_FIELD_NUMBER = 1;
    private java.util.List<java.lang.Integer> skillId_;
    /**
     * <code>repeated int32 skillId = 1;</code>
     *
     * <pre>
     *已经学习了的技能ID
     * </pre>
     */
    public java.util.List<java.lang.Integer>
        getSkillIdList() {
      return skillId_;
    }
    /**
     * <code>repeated int32 skillId = 1;</code>
     *
     * <pre>
     *已经学习了的技能ID
     * </pre>
     */
    public int getSkillIdCount() {
      return skillId_.size();
    }
    /**
     * <code>repeated int32 skillId = 1;</code>
     *
     * <pre>
     *已经学习了的技能ID
     * </pre>
     */
    public int getSkillId(int index) {
      return skillId_.get(index);
    }

    public static final int USEGOODS_FIELD_NUMBER = 3;
    private com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods useGoods_;
    /**
     * <code>optional .UseGoods useGoods = 3;</code>
     *
     * <pre>
     *消耗的物品
     * </pre>
     */
    public boolean hasUseGoods() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .UseGoods useGoods = 3;</code>
     *
     * <pre>
     *消耗的物品
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods getUseGoods() {
      return useGoods_;
    }
    /**
     * <code>optional .UseGoods useGoods = 3;</code>
     *
     * <pre>
     *消耗的物品
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoodsOrBuilder getUseGoodsOrBuilder() {
      return useGoods_;
    }

    public static final int NEEDJADE_FIELD_NUMBER = 4;
    private int needJade_;
    /**
     * <code>optional int32 needJade = 4;</code>
     *
     * <pre>
     *技能升级需要仙玉
     * </pre>
     */
    public boolean hasNeedJade() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 needJade = 4;</code>
     *
     * <pre>
     *技能升级需要仙玉
     * </pre>
     */
    public int getNeedJade() {
      return needJade_;
    }

    public static final int NEEDSTONE_FIELD_NUMBER = 5;
    private int needStone_;
    /**
     * <code>optional int32 needStone = 5;</code>
     *
     * <pre>
     *技能升级需要灵石
     * </pre>
     */
    public boolean hasNeedStone() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 needStone = 5;</code>
     *
     * <pre>
     *技能升级需要灵石
     * </pre>
     */
    public int getNeedStone() {
      return needStone_;
    }

    public static final int NEEDREPAIR_FIELD_NUMBER = 6;
    private int needRepair_;
    /**
     * <code>optional int32 needRepair = 6;</code>
     *
     * <pre>
     *技能升级需要修为
     * </pre>
     */
    public boolean hasNeedRepair() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 needRepair = 6;</code>
     *
     * <pre>
     *技能升级需要修为
     * </pre>
     */
    public int getNeedRepair() {
      return needRepair_;
    }

    private void initFields() {
      skillId_ = java.util.Collections.emptyList();
      useGoods_ = com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.getDefaultInstance();
      needJade_ = 0;
      needStone_ = 0;
      needRepair_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (hasUseGoods()) {
        if (!getUseGoods().isInitialized()) {
          memoizedIsInitialized = 0;
          return false;
        }
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < skillId_.size(); i++) {
        output.writeInt32(1, skillId_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeMessage(3, useGoods_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(4, needJade_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(5, needStone_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(6, needRepair_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < skillId_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(skillId_.get(i));
        }
        size += dataSize;
        size += 1 * getSkillIdList().size();
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, useGoods_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, needJade_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, needStone_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, needRepair_);
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

    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg prototype) {
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
     * Protobuf type {@code OneKeyUpSkillOkMsg}
     *
     * <pre>
     *一键升级主动技能成功
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:OneKeyUpSkillOkMsg)
        com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.internal_static_OneKeyUpSkillOkMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.internal_static_OneKeyUpSkillOkMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.class, com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.newBuilder()
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
          getUseGoodsFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        skillId_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        if (useGoodsBuilder_ == null) {
          useGoods_ = com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.getDefaultInstance();
        } else {
          useGoodsBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        needJade_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        needStone_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        needRepair_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.internal_static_OneKeyUpSkillOkMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg build() {
        com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg result = new com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          skillId_ = java.util.Collections.unmodifiableList(skillId_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.skillId_ = skillId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000001;
        }
        if (useGoodsBuilder_ == null) {
          result.useGoods_ = useGoods_;
        } else {
          result.useGoods_ = useGoodsBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000002;
        }
        result.needJade_ = needJade_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000004;
        }
        result.needStone_ = needStone_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000008;
        }
        result.needRepair_ = needRepair_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg.getDefaultInstance()) return this;
        if (!other.skillId_.isEmpty()) {
          if (skillId_.isEmpty()) {
            skillId_ = other.skillId_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureSkillIdIsMutable();
            skillId_.addAll(other.skillId_);
          }
          onChanged();
        }
        if (other.hasUseGoods()) {
          mergeUseGoods(other.getUseGoods());
        }
        if (other.hasNeedJade()) {
          setNeedJade(other.getNeedJade());
        }
        if (other.hasNeedStone()) {
          setNeedStone(other.getNeedStone());
        }
        if (other.hasNeedRepair()) {
          setNeedRepair(other.getNeedRepair());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (hasUseGoods()) {
          if (!getUseGoods().isInitialized()) {
            
            return false;
          }
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.skill.OneKeyUpSkillOkMsgProto.OneKeyUpSkillOkMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<java.lang.Integer> skillId_ = java.util.Collections.emptyList();
      private void ensureSkillIdIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          skillId_ = new java.util.ArrayList<java.lang.Integer>(skillId_);
          bitField0_ |= 0x00000001;
         }
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public java.util.List<java.lang.Integer>
          getSkillIdList() {
        return java.util.Collections.unmodifiableList(skillId_);
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public int getSkillIdCount() {
        return skillId_.size();
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public int getSkillId(int index) {
        return skillId_.get(index);
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public Builder setSkillId(
          int index, int value) {
        ensureSkillIdIsMutable();
        skillId_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public Builder addSkillId(int value) {
        ensureSkillIdIsMutable();
        skillId_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public Builder addAllSkillId(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureSkillIdIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, skillId_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 skillId = 1;</code>
       *
       * <pre>
       *已经学习了的技能ID
       * </pre>
       */
      public Builder clearSkillId() {
        skillId_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }

      private com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods useGoods_ = com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods, com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.Builder, com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoodsOrBuilder> useGoodsBuilder_;
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public boolean hasUseGoods() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods getUseGoods() {
        if (useGoodsBuilder_ == null) {
          return useGoods_;
        } else {
          return useGoodsBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public Builder setUseGoods(com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods value) {
        if (useGoodsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          useGoods_ = value;
          onChanged();
        } else {
          useGoodsBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public Builder setUseGoods(
          com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.Builder builderForValue) {
        if (useGoodsBuilder_ == null) {
          useGoods_ = builderForValue.build();
          onChanged();
        } else {
          useGoodsBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public Builder mergeUseGoods(com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods value) {
        if (useGoodsBuilder_ == null) {
          if (((bitField0_ & 0x00000002) == 0x00000002) &&
              useGoods_ != com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.getDefaultInstance()) {
            useGoods_ =
              com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.newBuilder(useGoods_).mergeFrom(value).buildPartial();
          } else {
            useGoods_ = value;
          }
          onChanged();
        } else {
          useGoodsBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000002;
        return this;
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public Builder clearUseGoods() {
        if (useGoodsBuilder_ == null) {
          useGoods_ = com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.getDefaultInstance();
          onChanged();
        } else {
          useGoodsBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.Builder getUseGoodsBuilder() {
        bitField0_ |= 0x00000002;
        onChanged();
        return getUseGoodsFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoodsOrBuilder getUseGoodsOrBuilder() {
        if (useGoodsBuilder_ != null) {
          return useGoodsBuilder_.getMessageOrBuilder();
        } else {
          return useGoods_;
        }
      }
      /**
       * <code>optional .UseGoods useGoods = 3;</code>
       *
       * <pre>
       *消耗的物品
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods, com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.Builder, com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoodsOrBuilder> 
          getUseGoodsFieldBuilder() {
        if (useGoodsBuilder_ == null) {
          useGoodsBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods, com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoods.Builder, com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.UseGoodsOrBuilder>(
                  getUseGoods(),
                  getParentForChildren(),
                  isClean());
          useGoods_ = null;
        }
        return useGoodsBuilder_;
      }

      private int needJade_ ;
      /**
       * <code>optional int32 needJade = 4;</code>
       *
       * <pre>
       *技能升级需要仙玉
       * </pre>
       */
      public boolean hasNeedJade() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 needJade = 4;</code>
       *
       * <pre>
       *技能升级需要仙玉
       * </pre>
       */
      public int getNeedJade() {
        return needJade_;
      }
      /**
       * <code>optional int32 needJade = 4;</code>
       *
       * <pre>
       *技能升级需要仙玉
       * </pre>
       */
      public Builder setNeedJade(int value) {
        bitField0_ |= 0x00000004;
        needJade_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 needJade = 4;</code>
       *
       * <pre>
       *技能升级需要仙玉
       * </pre>
       */
      public Builder clearNeedJade() {
        bitField0_ = (bitField0_ & ~0x00000004);
        needJade_ = 0;
        onChanged();
        return this;
      }

      private int needStone_ ;
      /**
       * <code>optional int32 needStone = 5;</code>
       *
       * <pre>
       *技能升级需要灵石
       * </pre>
       */
      public boolean hasNeedStone() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 needStone = 5;</code>
       *
       * <pre>
       *技能升级需要灵石
       * </pre>
       */
      public int getNeedStone() {
        return needStone_;
      }
      /**
       * <code>optional int32 needStone = 5;</code>
       *
       * <pre>
       *技能升级需要灵石
       * </pre>
       */
      public Builder setNeedStone(int value) {
        bitField0_ |= 0x00000008;
        needStone_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 needStone = 5;</code>
       *
       * <pre>
       *技能升级需要灵石
       * </pre>
       */
      public Builder clearNeedStone() {
        bitField0_ = (bitField0_ & ~0x00000008);
        needStone_ = 0;
        onChanged();
        return this;
      }

      private int needRepair_ ;
      /**
       * <code>optional int32 needRepair = 6;</code>
       *
       * <pre>
       *技能升级需要修为
       * </pre>
       */
      public boolean hasNeedRepair() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 needRepair = 6;</code>
       *
       * <pre>
       *技能升级需要修为
       * </pre>
       */
      public int getNeedRepair() {
        return needRepair_;
      }
      /**
       * <code>optional int32 needRepair = 6;</code>
       *
       * <pre>
       *技能升级需要修为
       * </pre>
       */
      public Builder setNeedRepair(int value) {
        bitField0_ |= 0x00000010;
        needRepair_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 needRepair = 6;</code>
       *
       * <pre>
       *技能升级需要修为
       * </pre>
       */
      public Builder clearNeedRepair() {
        bitField0_ = (bitField0_ & ~0x00000010);
        needRepair_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:OneKeyUpSkillOkMsg)
    }

    static {
      defaultInstance = new OneKeyUpSkillOkMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:OneKeyUpSkillOkMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OneKeyUpSkillOkMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_OneKeyUpSkillOkMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036skill/OneKeyUpSkillOkMsg.proto\032\024skill/" +
      "UseGoods.proto\"{\n\022OneKeyUpSkillOkMsg\022\017\n\007" +
      "skillId\030\001 \003(\005\022\033\n\010useGoods\030\003 \001(\0132\t.UseGoo" +
      "ds\022\020\n\010needJade\030\004 \001(\005\022\021\n\tneedStone\030\005 \001(\005\022" +
      "\022\n\nneedRepair\030\006 \001(\005BA\n&com.chuangyou.com" +
      "mon.protobuf.pb.skillB\027OneKeyUpSkillOkMs" +
      "gProto"
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
          com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.getDescriptor(),
        }, assigner);
    internal_static_OneKeyUpSkillOkMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_OneKeyUpSkillOkMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_OneKeyUpSkillOkMsg_descriptor,
        new java.lang.String[] { "SkillId", "UseGoods", "NeedJade", "NeedStone", "NeedRepair", });
    com.chuangyou.common.protobuf.pb.skill.UseGoodsProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
