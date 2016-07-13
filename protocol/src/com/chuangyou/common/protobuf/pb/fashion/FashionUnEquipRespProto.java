// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: fashion/FashionUnEquipRespMsg.proto

package com.chuangyou.common.protobuf.pb.fashion;

public final class FashionUnEquipRespProto {
  private FashionUnEquipRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface FashionUnEquipRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:FashionUnEquipRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional bool empty = 1;</code>
     *
     * <pre>
     * 空消息标记
     * </pre>
     */
    boolean hasEmpty();
    /**
     * <code>optional bool empty = 1;</code>
     *
     * <pre>
     * 空消息标记
     * </pre>
     */
    boolean getEmpty();
  }
  /**
   * Protobuf type {@code FashionUnEquipRespMsg}
   */
  public static final class FashionUnEquipRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:FashionUnEquipRespMsg)
      FashionUnEquipRespMsgOrBuilder {
    // Use FashionUnEquipRespMsg.newBuilder() to construct.
    private FashionUnEquipRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private FashionUnEquipRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final FashionUnEquipRespMsg defaultInstance;
    public static FashionUnEquipRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public FashionUnEquipRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private FashionUnEquipRespMsg(
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
              empty_ = input.readBool();
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
      return com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.internal_static_FashionUnEquipRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.internal_static_FashionUnEquipRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.class, com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<FashionUnEquipRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<FashionUnEquipRespMsg>() {
      public FashionUnEquipRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new FashionUnEquipRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<FashionUnEquipRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int EMPTY_FIELD_NUMBER = 1;
    private boolean empty_;
    /**
     * <code>optional bool empty = 1;</code>
     *
     * <pre>
     * 空消息标记
     * </pre>
     */
    public boolean hasEmpty() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional bool empty = 1;</code>
     *
     * <pre>
     * 空消息标记
     * </pre>
     */
    public boolean getEmpty() {
      return empty_;
    }

    private void initFields() {
      empty_ = false;
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
        output.writeBool(1, empty_);
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
          .computeBoolSize(1, empty_);
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

    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg prototype) {
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
     * Protobuf type {@code FashionUnEquipRespMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:FashionUnEquipRespMsg)
        com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.internal_static_FashionUnEquipRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.internal_static_FashionUnEquipRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.class, com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.newBuilder()
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
        empty_ = false;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.internal_static_FashionUnEquipRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg build() {
        com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg result = new com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.empty_ = empty_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg.getDefaultInstance()) return this;
        if (other.hasEmpty()) {
          setEmpty(other.getEmpty());
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
        com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.fashion.FashionUnEquipRespProto.FashionUnEquipRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private boolean empty_ ;
      /**
       * <code>optional bool empty = 1;</code>
       *
       * <pre>
       * 空消息标记
       * </pre>
       */
      public boolean hasEmpty() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional bool empty = 1;</code>
       *
       * <pre>
       * 空消息标记
       * </pre>
       */
      public boolean getEmpty() {
        return empty_;
      }
      /**
       * <code>optional bool empty = 1;</code>
       *
       * <pre>
       * 空消息标记
       * </pre>
       */
      public Builder setEmpty(boolean value) {
        bitField0_ |= 0x00000001;
        empty_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool empty = 1;</code>
       *
       * <pre>
       * 空消息标记
       * </pre>
       */
      public Builder clearEmpty() {
        bitField0_ = (bitField0_ & ~0x00000001);
        empty_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:FashionUnEquipRespMsg)
    }

    static {
      defaultInstance = new FashionUnEquipRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:FashionUnEquipRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_FashionUnEquipRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_FashionUnEquipRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n#fashion/FashionUnEquipRespMsg.proto\"&\n" +
      "\025FashionUnEquipRespMsg\022\r\n\005empty\030\001 \001(\010BC\n" +
      "(com.chuangyou.common.protobuf.pb.fashio" +
      "nB\027FashionUnEquipRespProto"
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
    internal_static_FashionUnEquipRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_FashionUnEquipRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_FashionUnEquipRespMsg_descriptor,
        new java.lang.String[] { "Empty", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
