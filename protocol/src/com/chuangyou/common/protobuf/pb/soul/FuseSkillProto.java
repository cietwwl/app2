// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: FuseSkillMsg.proto

package com.chuangyou.common.protobuf.pb.soul;

public final class FuseSkillProto {
  private FuseSkillProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface FuseSkillMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:FuseSkillMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 fuseSkillId = 1;</code>
     *
     * <pre>
     *����ID
     * </pre>
     */
    boolean hasFuseSkillId();
    /**
     * <code>required int32 fuseSkillId = 1;</code>
     *
     * <pre>
     *����ID
     * </pre>
     */
    int getFuseSkillId();

    /**
     * <code>required int32 color = 2;</code>
     *
     * <pre>
     *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
     * </pre>
     */
    boolean hasColor();
    /**
     * <code>required int32 color = 2;</code>
     *
     * <pre>
     *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
     * </pre>
     */
    int getColor();

    /**
     * <code>optional int64 fuseCreateTime = 3;</code>
     *
     * <pre>
     *���ܿ�ʼʱ��
     * </pre>
     */
    boolean hasFuseCreateTime();
    /**
     * <code>optional int64 fuseCreateTime = 3;</code>
     *
     * <pre>
     *���ܿ�ʼʱ��
     * </pre>
     */
    long getFuseCreateTime();

    /**
     * <code>optional int32 proficiency = 4;</code>
     *
     * <pre>
     *������
     * </pre>
     */
    boolean hasProficiency();
    /**
     * <code>optional int32 proficiency = 4;</code>
     *
     * <pre>
     *������
     * </pre>
     */
    int getProficiency();

    /**
     * <code>optional int32 index = 5;</code>
     *
     * <pre>
     *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
     * </pre>
     */
    boolean hasIndex();
    /**
     * <code>optional int32 index = 5;</code>
     *
     * <pre>
     *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
     * </pre>
     */
    int getIndex();
  }
  /**
   * Protobuf type {@code FuseSkillMsg}
   */
  public static final class FuseSkillMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:FuseSkillMsg)
      FuseSkillMsgOrBuilder {
    // Use FuseSkillMsg.newBuilder() to construct.
    private FuseSkillMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private FuseSkillMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final FuseSkillMsg defaultInstance;
    public static FuseSkillMsg getDefaultInstance() {
      return defaultInstance;
    }

    public FuseSkillMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private FuseSkillMsg(
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
              fuseSkillId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              color_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              fuseCreateTime_ = input.readInt64();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              proficiency_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              index_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.internal_static_FuseSkillMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.internal_static_FuseSkillMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.class, com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<FuseSkillMsg> PARSER =
        new com.google.protobuf.AbstractParser<FuseSkillMsg>() {
      public FuseSkillMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new FuseSkillMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<FuseSkillMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int FUSESKILLID_FIELD_NUMBER = 1;
    private int fuseSkillId_;
    /**
     * <code>required int32 fuseSkillId = 1;</code>
     *
     * <pre>
     *����ID
     * </pre>
     */
    public boolean hasFuseSkillId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 fuseSkillId = 1;</code>
     *
     * <pre>
     *����ID
     * </pre>
     */
    public int getFuseSkillId() {
      return fuseSkillId_;
    }

    public static final int COLOR_FIELD_NUMBER = 2;
    private int color_;
    /**
     * <code>required int32 color = 2;</code>
     *
     * <pre>
     *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
     * </pre>
     */
    public boolean hasColor() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 color = 2;</code>
     *
     * <pre>
     *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
     * </pre>
     */
    public int getColor() {
      return color_;
    }

    public static final int FUSECREATETIME_FIELD_NUMBER = 3;
    private long fuseCreateTime_;
    /**
     * <code>optional int64 fuseCreateTime = 3;</code>
     *
     * <pre>
     *���ܿ�ʼʱ��
     * </pre>
     */
    public boolean hasFuseCreateTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int64 fuseCreateTime = 3;</code>
     *
     * <pre>
     *���ܿ�ʼʱ��
     * </pre>
     */
    public long getFuseCreateTime() {
      return fuseCreateTime_;
    }

    public static final int PROFICIENCY_FIELD_NUMBER = 4;
    private int proficiency_;
    /**
     * <code>optional int32 proficiency = 4;</code>
     *
     * <pre>
     *������
     * </pre>
     */
    public boolean hasProficiency() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 proficiency = 4;</code>
     *
     * <pre>
     *������
     * </pre>
     */
    public int getProficiency() {
      return proficiency_;
    }

    public static final int INDEX_FIELD_NUMBER = 5;
    private int index_;
    /**
     * <code>optional int32 index = 5;</code>
     *
     * <pre>
     *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
     * </pre>
     */
    public boolean hasIndex() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 index = 5;</code>
     *
     * <pre>
     *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
     * </pre>
     */
    public int getIndex() {
      return index_;
    }

    private void initFields() {
      fuseSkillId_ = 0;
      color_ = 0;
      fuseCreateTime_ = 0L;
      proficiency_ = 0;
      index_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasFuseSkillId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasColor()) {
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
        output.writeInt32(1, fuseSkillId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, color_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt64(3, fuseCreateTime_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, proficiency_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, index_);
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
          .computeInt32Size(1, fuseSkillId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, color_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, fuseCreateTime_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, proficiency_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, index_);
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

    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg prototype) {
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
     * Protobuf type {@code FuseSkillMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:FuseSkillMsg)
        com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.internal_static_FuseSkillMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.internal_static_FuseSkillMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.class, com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.newBuilder()
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
        fuseSkillId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        color_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        fuseCreateTime_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000004);
        proficiency_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        index_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.internal_static_FuseSkillMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg build() {
        com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg result = new com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.fuseSkillId_ = fuseSkillId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.color_ = color_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.fuseCreateTime_ = fuseCreateTime_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.proficiency_ = proficiency_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.index_ = index_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg.getDefaultInstance()) return this;
        if (other.hasFuseSkillId()) {
          setFuseSkillId(other.getFuseSkillId());
        }
        if (other.hasColor()) {
          setColor(other.getColor());
        }
        if (other.hasFuseCreateTime()) {
          setFuseCreateTime(other.getFuseCreateTime());
        }
        if (other.hasProficiency()) {
          setProficiency(other.getProficiency());
        }
        if (other.hasIndex()) {
          setIndex(other.getIndex());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasFuseSkillId()) {
          
          return false;
        }
        if (!hasColor()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.soul.FuseSkillProto.FuseSkillMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int fuseSkillId_ ;
      /**
       * <code>required int32 fuseSkillId = 1;</code>
       *
       * <pre>
       *����ID
       * </pre>
       */
      public boolean hasFuseSkillId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 fuseSkillId = 1;</code>
       *
       * <pre>
       *����ID
       * </pre>
       */
      public int getFuseSkillId() {
        return fuseSkillId_;
      }
      /**
       * <code>required int32 fuseSkillId = 1;</code>
       *
       * <pre>
       *����ID
       * </pre>
       */
      public Builder setFuseSkillId(int value) {
        bitField0_ |= 0x00000001;
        fuseSkillId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 fuseSkillId = 1;</code>
       *
       * <pre>
       *����ID
       * </pre>
       */
      public Builder clearFuseSkillId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        fuseSkillId_ = 0;
        onChanged();
        return this;
      }

      private int color_ ;
      /**
       * <code>required int32 color = 2;</code>
       *
       * <pre>
       *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
       * </pre>
       */
      public boolean hasColor() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 color = 2;</code>
       *
       * <pre>
       *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
       * </pre>
       */
      public int getColor() {
        return color_;
      }
      /**
       * <code>required int32 color = 2;</code>
       *
       * <pre>
       *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
       * </pre>
       */
      public Builder setColor(int value) {
        bitField0_ |= 0x00000002;
        color_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 color = 2;</code>
       *
       * <pre>
       *����Ʒ�ʣ�2����  3����  4����  5���ȣ�
       * </pre>
       */
      public Builder clearColor() {
        bitField0_ = (bitField0_ & ~0x00000002);
        color_ = 0;
        onChanged();
        return this;
      }

      private long fuseCreateTime_ ;
      /**
       * <code>optional int64 fuseCreateTime = 3;</code>
       *
       * <pre>
       *���ܿ�ʼʱ��
       * </pre>
       */
      public boolean hasFuseCreateTime() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int64 fuseCreateTime = 3;</code>
       *
       * <pre>
       *���ܿ�ʼʱ��
       * </pre>
       */
      public long getFuseCreateTime() {
        return fuseCreateTime_;
      }
      /**
       * <code>optional int64 fuseCreateTime = 3;</code>
       *
       * <pre>
       *���ܿ�ʼʱ��
       * </pre>
       */
      public Builder setFuseCreateTime(long value) {
        bitField0_ |= 0x00000004;
        fuseCreateTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 fuseCreateTime = 3;</code>
       *
       * <pre>
       *���ܿ�ʼʱ��
       * </pre>
       */
      public Builder clearFuseCreateTime() {
        bitField0_ = (bitField0_ & ~0x00000004);
        fuseCreateTime_ = 0L;
        onChanged();
        return this;
      }

      private int proficiency_ ;
      /**
       * <code>optional int32 proficiency = 4;</code>
       *
       * <pre>
       *������
       * </pre>
       */
      public boolean hasProficiency() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 proficiency = 4;</code>
       *
       * <pre>
       *������
       * </pre>
       */
      public int getProficiency() {
        return proficiency_;
      }
      /**
       * <code>optional int32 proficiency = 4;</code>
       *
       * <pre>
       *������
       * </pre>
       */
      public Builder setProficiency(int value) {
        bitField0_ |= 0x00000008;
        proficiency_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 proficiency = 4;</code>
       *
       * <pre>
       *������
       * </pre>
       */
      public Builder clearProficiency() {
        bitField0_ = (bitField0_ & ~0x00000008);
        proficiency_ = 0;
        onChanged();
        return this;
      }

      private int index_ ;
      /**
       * <code>optional int32 index = 5;</code>
       *
       * <pre>
       *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
       * </pre>
       */
      public boolean hasIndex() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 index = 5;</code>
       *
       * <pre>
       *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
       * </pre>
       */
      public int getIndex() {
        return index_;
      }
      /**
       * <code>optional int32 index = 5;</code>
       *
       * <pre>
       *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
       * </pre>
       */
      public Builder setIndex(int value) {
        bitField0_ |= 0x00000010;
        index_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 index = 5;</code>
       *
       * <pre>
       *�������ڲ�ͨ���õ��ֶΣ��ͻ��˺��ԣ�
       * </pre>
       */
      public Builder clearIndex() {
        bitField0_ = (bitField0_ & ~0x00000010);
        index_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:FuseSkillMsg)
    }

    static {
      defaultInstance = new FuseSkillMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:FuseSkillMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_FuseSkillMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_FuseSkillMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022FuseSkillMsg.proto\"n\n\014FuseSkillMsg\022\023\n\013" +
      "fuseSkillId\030\001 \002(\005\022\r\n\005color\030\002 \002(\005\022\026\n\016fuse" +
      "CreateTime\030\003 \001(\003\022\023\n\013proficiency\030\004 \001(\005\022\r\n" +
      "\005index\030\005 \001(\005B7\n%com.chuangyou.common.pro" +
      "tobuf.pb.soulB\016FuseSkillProto"
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
    internal_static_FuseSkillMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_FuseSkillMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_FuseSkillMsg_descriptor,
        new java.lang.String[] { "FuseSkillId", "Color", "FuseCreateTime", "Proficiency", "Index", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}