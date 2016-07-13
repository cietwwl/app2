// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: friend/AddFriendRespMsg.proto

package com.chuangyou.common.protobuf.pb.friend;

public final class AddFriendRespProto {
  private AddFriendRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface AddFriendRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:AddFriendRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required .FriendInfoMsg friendInfo = 1;</code>
     *
     * <pre>
     *好友信息
     * </pre>
     */
    boolean hasFriendInfo();
    /**
     * <code>required .FriendInfoMsg friendInfo = 1;</code>
     *
     * <pre>
     *好友信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg getFriendInfo();
    /**
     * <code>required .FriendInfoMsg friendInfo = 1;</code>
     *
     * <pre>
     *好友信息
     * </pre>
     */
    com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsgOrBuilder getFriendInfoOrBuilder();
  }
  /**
   * Protobuf type {@code AddFriendRespMsg}
   *
   * <pre>
   *请求添加谁为好友
   *添加成功回复
   * </pre>
   */
  public static final class AddFriendRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:AddFriendRespMsg)
      AddFriendRespMsgOrBuilder {
    // Use AddFriendRespMsg.newBuilder() to construct.
    private AddFriendRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private AddFriendRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final AddFriendRespMsg defaultInstance;
    public static AddFriendRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public AddFriendRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private AddFriendRespMsg(
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
            case 10: {
              com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = friendInfo_.toBuilder();
              }
              friendInfo_ = input.readMessage(com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(friendInfo_);
                friendInfo_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
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
      return com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.internal_static_AddFriendRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.internal_static_AddFriendRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.class, com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<AddFriendRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<AddFriendRespMsg>() {
      public AddFriendRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AddFriendRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<AddFriendRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int FRIENDINFO_FIELD_NUMBER = 1;
    private com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg friendInfo_;
    /**
     * <code>required .FriendInfoMsg friendInfo = 1;</code>
     *
     * <pre>
     *好友信息
     * </pre>
     */
    public boolean hasFriendInfo() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .FriendInfoMsg friendInfo = 1;</code>
     *
     * <pre>
     *好友信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg getFriendInfo() {
      return friendInfo_;
    }
    /**
     * <code>required .FriendInfoMsg friendInfo = 1;</code>
     *
     * <pre>
     *好友信息
     * </pre>
     */
    public com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsgOrBuilder getFriendInfoOrBuilder() {
      return friendInfo_;
    }

    private void initFields() {
      friendInfo_ = com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.getDefaultInstance();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasFriendInfo()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!getFriendInfo().isInitialized()) {
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
        output.writeMessage(1, friendInfo_);
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
          .computeMessageSize(1, friendInfo_);
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

    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg prototype) {
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
     * Protobuf type {@code AddFriendRespMsg}
     *
     * <pre>
     *请求添加谁为好友
     *添加成功回复
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:AddFriendRespMsg)
        com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.internal_static_AddFriendRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.internal_static_AddFriendRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.class, com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.newBuilder()
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
          getFriendInfoFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (friendInfoBuilder_ == null) {
          friendInfo_ = com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.getDefaultInstance();
        } else {
          friendInfoBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.internal_static_AddFriendRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg build() {
        com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg result = new com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (friendInfoBuilder_ == null) {
          result.friendInfo_ = friendInfo_;
        } else {
          result.friendInfo_ = friendInfoBuilder_.build();
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg.getDefaultInstance()) return this;
        if (other.hasFriendInfo()) {
          mergeFriendInfo(other.getFriendInfo());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasFriendInfo()) {
          
          return false;
        }
        if (!getFriendInfo().isInitialized()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg friendInfo_ = com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg, com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.Builder, com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsgOrBuilder> friendInfoBuilder_;
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public boolean hasFriendInfo() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg getFriendInfo() {
        if (friendInfoBuilder_ == null) {
          return friendInfo_;
        } else {
          return friendInfoBuilder_.getMessage();
        }
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public Builder setFriendInfo(com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg value) {
        if (friendInfoBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          friendInfo_ = value;
          onChanged();
        } else {
          friendInfoBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public Builder setFriendInfo(
          com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.Builder builderForValue) {
        if (friendInfoBuilder_ == null) {
          friendInfo_ = builderForValue.build();
          onChanged();
        } else {
          friendInfoBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public Builder mergeFriendInfo(com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg value) {
        if (friendInfoBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              friendInfo_ != com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.getDefaultInstance()) {
            friendInfo_ =
              com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.newBuilder(friendInfo_).mergeFrom(value).buildPartial();
          } else {
            friendInfo_ = value;
          }
          onChanged();
        } else {
          friendInfoBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public Builder clearFriendInfo() {
        if (friendInfoBuilder_ == null) {
          friendInfo_ = com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.getDefaultInstance();
          onChanged();
        } else {
          friendInfoBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.Builder getFriendInfoBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getFriendInfoFieldBuilder().getBuilder();
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      public com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsgOrBuilder getFriendInfoOrBuilder() {
        if (friendInfoBuilder_ != null) {
          return friendInfoBuilder_.getMessageOrBuilder();
        } else {
          return friendInfo_;
        }
      }
      /**
       * <code>required .FriendInfoMsg friendInfo = 1;</code>
       *
       * <pre>
       *好友信息
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg, com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.Builder, com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsgOrBuilder> 
          getFriendInfoFieldBuilder() {
        if (friendInfoBuilder_ == null) {
          friendInfoBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg, com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg.Builder, com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsgOrBuilder>(
                  getFriendInfo(),
                  getParentForChildren(),
                  isClean());
          friendInfo_ = null;
        }
        return friendInfoBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:AddFriendRespMsg)
    }

    static {
      defaultInstance = new AddFriendRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:AddFriendRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_AddFriendRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_AddFriendRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035friend/AddFriendRespMsg.proto\032\032friend/" +
      "FriendInfoMsg.proto\"6\n\020AddFriendRespMsg\022" +
      "\"\n\nfriendInfo\030\001 \002(\0132\016.FriendInfoMsgB=\n\'c" +
      "om.chuangyou.common.protobuf.pb.friendB\022" +
      "AddFriendRespProto"
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
          com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.getDescriptor(),
        }, assigner);
    internal_static_AddFriendRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_AddFriendRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_AddFriendRespMsg_descriptor,
        new java.lang.String[] { "FriendInfo", });
    com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
