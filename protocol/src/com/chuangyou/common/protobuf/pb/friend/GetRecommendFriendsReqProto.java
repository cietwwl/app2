// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: friend/GetRecommendFriendsReqMsg.proto

package com.chuangyou.common.protobuf.pb.friend;

public final class GetRecommendFriendsReqProto {
  private GetRecommendFriendsReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GetRecommendFriendsReqMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:GetRecommendFriendsReqMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 isCity = 1;</code>
     *
     * <pre>
     *	是否同城 1：同城 0：其它
     * </pre>
     */
    boolean hasIsCity();
    /**
     * <code>required int32 isCity = 1;</code>
     *
     * <pre>
     *	是否同城 1：同城 0：其它
     * </pre>
     */
    int getIsCity();

    /**
     * <code>required int32 isMan = 2;</code>
     *
     * <pre>
     *	男  1：勾选此选项  0：未勾选
     * </pre>
     */
    boolean hasIsMan();
    /**
     * <code>required int32 isMan = 2;</code>
     *
     * <pre>
     *	男  1：勾选此选项  0：未勾选
     * </pre>
     */
    int getIsMan();

    /**
     * <code>required int32 isWoman = 3;</code>
     *
     * <pre>
     *女  1：勾选此选项  0：未勾选
     * </pre>
     */
    boolean hasIsWoman();
    /**
     * <code>required int32 isWoman = 3;</code>
     *
     * <pre>
     *女  1：勾选此选项  0：未勾选
     * </pre>
     */
    int getIsWoman();
  }
  /**
   * Protobuf type {@code GetRecommendFriendsReqMsg}
   *
   * <pre>
   *获取推荐好友
   * </pre>
   */
  public static final class GetRecommendFriendsReqMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:GetRecommendFriendsReqMsg)
      GetRecommendFriendsReqMsgOrBuilder {
    // Use GetRecommendFriendsReqMsg.newBuilder() to construct.
    private GetRecommendFriendsReqMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private GetRecommendFriendsReqMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final GetRecommendFriendsReqMsg defaultInstance;
    public static GetRecommendFriendsReqMsg getDefaultInstance() {
      return defaultInstance;
    }

    public GetRecommendFriendsReqMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private GetRecommendFriendsReqMsg(
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
              isCity_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              isMan_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              isWoman_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.internal_static_GetRecommendFriendsReqMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.internal_static_GetRecommendFriendsReqMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.class, com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<GetRecommendFriendsReqMsg> PARSER =
        new com.google.protobuf.AbstractParser<GetRecommendFriendsReqMsg>() {
      public GetRecommendFriendsReqMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetRecommendFriendsReqMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<GetRecommendFriendsReqMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int ISCITY_FIELD_NUMBER = 1;
    private int isCity_;
    /**
     * <code>required int32 isCity = 1;</code>
     *
     * <pre>
     *	是否同城 1：同城 0：其它
     * </pre>
     */
    public boolean hasIsCity() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 isCity = 1;</code>
     *
     * <pre>
     *	是否同城 1：同城 0：其它
     * </pre>
     */
    public int getIsCity() {
      return isCity_;
    }

    public static final int ISMAN_FIELD_NUMBER = 2;
    private int isMan_;
    /**
     * <code>required int32 isMan = 2;</code>
     *
     * <pre>
     *	男  1：勾选此选项  0：未勾选
     * </pre>
     */
    public boolean hasIsMan() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 isMan = 2;</code>
     *
     * <pre>
     *	男  1：勾选此选项  0：未勾选
     * </pre>
     */
    public int getIsMan() {
      return isMan_;
    }

    public static final int ISWOMAN_FIELD_NUMBER = 3;
    private int isWoman_;
    /**
     * <code>required int32 isWoman = 3;</code>
     *
     * <pre>
     *女  1：勾选此选项  0：未勾选
     * </pre>
     */
    public boolean hasIsWoman() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 isWoman = 3;</code>
     *
     * <pre>
     *女  1：勾选此选项  0：未勾选
     * </pre>
     */
    public int getIsWoman() {
      return isWoman_;
    }

    private void initFields() {
      isCity_ = 0;
      isMan_ = 0;
      isWoman_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasIsCity()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasIsMan()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasIsWoman()) {
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
        output.writeInt32(1, isCity_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, isMan_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, isWoman_);
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
          .computeInt32Size(1, isCity_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, isMan_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, isWoman_);
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

    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg prototype) {
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
     * Protobuf type {@code GetRecommendFriendsReqMsg}
     *
     * <pre>
     *获取推荐好友
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:GetRecommendFriendsReqMsg)
        com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.internal_static_GetRecommendFriendsReqMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.internal_static_GetRecommendFriendsReqMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.class, com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.newBuilder()
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
        isCity_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        isMan_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        isWoman_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.internal_static_GetRecommendFriendsReqMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg build() {
        com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg result = new com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.isCity_ = isCity_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.isMan_ = isMan_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.isWoman_ = isWoman_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg.getDefaultInstance()) return this;
        if (other.hasIsCity()) {
          setIsCity(other.getIsCity());
        }
        if (other.hasIsMan()) {
          setIsMan(other.getIsMan());
        }
        if (other.hasIsWoman()) {
          setIsWoman(other.getIsWoman());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasIsCity()) {
          
          return false;
        }
        if (!hasIsMan()) {
          
          return false;
        }
        if (!hasIsWoman()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.friend.GetRecommendFriendsReqProto.GetRecommendFriendsReqMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int isCity_ ;
      /**
       * <code>required int32 isCity = 1;</code>
       *
       * <pre>
       *	是否同城 1：同城 0：其它
       * </pre>
       */
      public boolean hasIsCity() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 isCity = 1;</code>
       *
       * <pre>
       *	是否同城 1：同城 0：其它
       * </pre>
       */
      public int getIsCity() {
        return isCity_;
      }
      /**
       * <code>required int32 isCity = 1;</code>
       *
       * <pre>
       *	是否同城 1：同城 0：其它
       * </pre>
       */
      public Builder setIsCity(int value) {
        bitField0_ |= 0x00000001;
        isCity_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 isCity = 1;</code>
       *
       * <pre>
       *	是否同城 1：同城 0：其它
       * </pre>
       */
      public Builder clearIsCity() {
        bitField0_ = (bitField0_ & ~0x00000001);
        isCity_ = 0;
        onChanged();
        return this;
      }

      private int isMan_ ;
      /**
       * <code>required int32 isMan = 2;</code>
       *
       * <pre>
       *	男  1：勾选此选项  0：未勾选
       * </pre>
       */
      public boolean hasIsMan() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 isMan = 2;</code>
       *
       * <pre>
       *	男  1：勾选此选项  0：未勾选
       * </pre>
       */
      public int getIsMan() {
        return isMan_;
      }
      /**
       * <code>required int32 isMan = 2;</code>
       *
       * <pre>
       *	男  1：勾选此选项  0：未勾选
       * </pre>
       */
      public Builder setIsMan(int value) {
        bitField0_ |= 0x00000002;
        isMan_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 isMan = 2;</code>
       *
       * <pre>
       *	男  1：勾选此选项  0：未勾选
       * </pre>
       */
      public Builder clearIsMan() {
        bitField0_ = (bitField0_ & ~0x00000002);
        isMan_ = 0;
        onChanged();
        return this;
      }

      private int isWoman_ ;
      /**
       * <code>required int32 isWoman = 3;</code>
       *
       * <pre>
       *女  1：勾选此选项  0：未勾选
       * </pre>
       */
      public boolean hasIsWoman() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required int32 isWoman = 3;</code>
       *
       * <pre>
       *女  1：勾选此选项  0：未勾选
       * </pre>
       */
      public int getIsWoman() {
        return isWoman_;
      }
      /**
       * <code>required int32 isWoman = 3;</code>
       *
       * <pre>
       *女  1：勾选此选项  0：未勾选
       * </pre>
       */
      public Builder setIsWoman(int value) {
        bitField0_ |= 0x00000004;
        isWoman_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 isWoman = 3;</code>
       *
       * <pre>
       *女  1：勾选此选项  0：未勾选
       * </pre>
       */
      public Builder clearIsWoman() {
        bitField0_ = (bitField0_ & ~0x00000004);
        isWoman_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:GetRecommendFriendsReqMsg)
    }

    static {
      defaultInstance = new GetRecommendFriendsReqMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:GetRecommendFriendsReqMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_GetRecommendFriendsReqMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_GetRecommendFriendsReqMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n&friend/GetRecommendFriendsReqMsg.proto" +
      "\"K\n\031GetRecommendFriendsReqMsg\022\016\n\006isCity\030" +
      "\001 \002(\005\022\r\n\005isMan\030\002 \002(\005\022\017\n\007isWoman\030\003 \002(\005BF\n" +
      "\'com.chuangyou.common.protobuf.pb.friend" +
      "B\033GetRecommendFriendsReqProto"
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
    internal_static_GetRecommendFriendsReqMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_GetRecommendFriendsReqMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_GetRecommendFriendsReqMsg_descriptor,
        new java.lang.String[] { "IsCity", "IsMan", "IsWoman", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
