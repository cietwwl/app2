// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReqTruckAction.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class ReqRobActionProto {
  private ReqRobActionProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqTruckActionOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ReqTruckAction)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 targetTruckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    boolean hasTargetTruckID();
    /**
     * <code>required int64 targetTruckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    long getTargetTruckID();

    /**
     * <code>required int32 action = 2;</code>
     *
     * <pre>
     *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
     * </pre>
     */
    boolean hasAction();
    /**
     * <code>required int32 action = 2;</code>
     *
     * <pre>
     *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
     * </pre>
     */
    int getAction();
  }
  /**
   * Protobuf type {@code ReqTruckAction}
   *
   * <pre>
   *镖车Action
   * </pre>
   */
  public static final class ReqTruckAction extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ReqTruckAction)
      ReqTruckActionOrBuilder {
    // Use ReqTruckAction.newBuilder() to construct.
    private ReqTruckAction(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReqTruckAction(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReqTruckAction defaultInstance;
    public static ReqTruckAction getDefaultInstance() {
      return defaultInstance;
    }

    public ReqTruckAction getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReqTruckAction(
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
              targetTruckID_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              action_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.internal_static_ReqTruckAction_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.internal_static_ReqTruckAction_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.class, com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.Builder.class);
    }

    public static com.google.protobuf.Parser<ReqTruckAction> PARSER =
        new com.google.protobuf.AbstractParser<ReqTruckAction>() {
      public ReqTruckAction parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqTruckAction(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReqTruckAction> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TARGETTRUCKID_FIELD_NUMBER = 1;
    private long targetTruckID_;
    /**
     * <code>required int64 targetTruckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    public boolean hasTargetTruckID() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 targetTruckID = 1;</code>
     *
     * <pre>
     *镖车ID
     * </pre>
     */
    public long getTargetTruckID() {
      return targetTruckID_;
    }

    public static final int ACTION_FIELD_NUMBER = 2;
    private int action_;
    /**
     * <code>required int32 action = 2;</code>
     *
     * <pre>
     *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
     * </pre>
     */
    public boolean hasAction() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 action = 2;</code>
     *
     * <pre>
     *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
     * </pre>
     */
    public int getAction() {
      return action_;
    }

    private void initFields() {
      targetTruckID_ = 0L;
      action_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTargetTruckID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasAction()) {
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
        output.writeInt64(1, targetTruckID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, action_);
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
          .computeInt64Size(1, targetTruckID_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, action_);
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

    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction prototype) {
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
     * Protobuf type {@code ReqTruckAction}
     *
     * <pre>
     *镖车Action
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ReqTruckAction)
        com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckActionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.internal_static_ReqTruckAction_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.internal_static_ReqTruckAction_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.class, com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.newBuilder()
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
        targetTruckID_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        action_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.internal_static_ReqTruckAction_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction build() {
        com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction result = new com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.targetTruckID_ = targetTruckID_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.action_ = action_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction.getDefaultInstance()) return this;
        if (other.hasTargetTruckID()) {
          setTargetTruckID(other.getTargetTruckID());
        }
        if (other.hasAction()) {
          setAction(other.getAction());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTargetTruckID()) {
          
          return false;
        }
        if (!hasAction()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.ReqRobActionProto.ReqTruckAction) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long targetTruckID_ ;
      /**
       * <code>required int64 targetTruckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public boolean hasTargetTruckID() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 targetTruckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public long getTargetTruckID() {
        return targetTruckID_;
      }
      /**
       * <code>required int64 targetTruckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public Builder setTargetTruckID(long value) {
        bitField0_ |= 0x00000001;
        targetTruckID_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 targetTruckID = 1;</code>
       *
       * <pre>
       *镖车ID
       * </pre>
       */
      public Builder clearTargetTruckID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        targetTruckID_ = 0L;
        onChanged();
        return this;
      }

      private int action_ ;
      /**
       * <code>required int32 action = 2;</code>
       *
       * <pre>
       *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
       * </pre>
       */
      public boolean hasAction() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 action = 2;</code>
       *
       * <pre>
       *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
       * </pre>
       */
      public int getAction() {
        return action_;
      }
      /**
       * <code>required int32 action = 2;</code>
       *
       * <pre>
       *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
       * </pre>
       */
      public Builder setAction(int value) {
        bitField0_ |= 0x00000002;
        action_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 action = 2;</code>
       *
       * <pre>
       *1.开始劫镖 2.劫镖成功 3.退出劫镖 4.请求所有镖车的状态 5.查询自己的镖车状态 6.起运
       * </pre>
       */
      public Builder clearAction() {
        bitField0_ = (bitField0_ & ~0x00000002);
        action_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ReqTruckAction)
    }

    static {
      defaultInstance = new ReqTruckAction(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ReqTruckAction)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReqTruckAction_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ReqTruckAction_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024ReqTruckAction.proto\"7\n\016ReqTruckAction" +
      "\022\025\n\rtargetTruckID\030\001 \002(\003\022\016\n\006action\030\002 \002(\005B" +
      ";\n&com.chuangyou.common.protobuf.pb.truc" +
      "kB\021ReqRobActionProto"
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
    internal_static_ReqTruckAction_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ReqTruckAction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ReqTruckAction_descriptor,
        new java.lang.String[] { "TargetTruckID", "Action", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
