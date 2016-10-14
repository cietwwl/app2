// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ReqTruckSkillAction.proto

package com.chuangyou.common.protobuf.pb.truck;

public final class ReqTruckSkillActionProto {
  private ReqTruckSkillActionProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReqTruckSkillActionOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ReqTruckSkillAction)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int64 truckId = 1;</code>
     *
     * <pre>
     *镖车Id
     * </pre>
     */
    boolean hasTruckId();
    /**
     * <code>required int64 truckId = 1;</code>
     *
     * <pre>
     *镖车Id
     * </pre>
     */
    long getTruckId();

    /**
     * <code>required int32 skillId = 2;</code>
     *
     * <pre>
     *对应界面的几个按钮
     * </pre>
     */
    boolean hasSkillId();
    /**
     * <code>required int32 skillId = 2;</code>
     *
     * <pre>
     *对应界面的几个按钮
     * </pre>
     */
    int getSkillId();
  }
  /**
   * Protobuf type {@code ReqTruckSkillAction}
   *
   * <pre>
   *使用技能，主界面点按钮
   * </pre>
   */
  public static final class ReqTruckSkillAction extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:ReqTruckSkillAction)
      ReqTruckSkillActionOrBuilder {
    // Use ReqTruckSkillAction.newBuilder() to construct.
    private ReqTruckSkillAction(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReqTruckSkillAction(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReqTruckSkillAction defaultInstance;
    public static ReqTruckSkillAction getDefaultInstance() {
      return defaultInstance;
    }

    public ReqTruckSkillAction getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReqTruckSkillAction(
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
              truckId_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              skillId_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.internal_static_ReqTruckSkillAction_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.internal_static_ReqTruckSkillAction_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.class, com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.Builder.class);
    }

    public static com.google.protobuf.Parser<ReqTruckSkillAction> PARSER =
        new com.google.protobuf.AbstractParser<ReqTruckSkillAction>() {
      public ReqTruckSkillAction parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReqTruckSkillAction(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReqTruckSkillAction> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TRUCKID_FIELD_NUMBER = 1;
    private long truckId_;
    /**
     * <code>required int64 truckId = 1;</code>
     *
     * <pre>
     *镖车Id
     * </pre>
     */
    public boolean hasTruckId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 truckId = 1;</code>
     *
     * <pre>
     *镖车Id
     * </pre>
     */
    public long getTruckId() {
      return truckId_;
    }

    public static final int SKILLID_FIELD_NUMBER = 2;
    private int skillId_;
    /**
     * <code>required int32 skillId = 2;</code>
     *
     * <pre>
     *对应界面的几个按钮
     * </pre>
     */
    public boolean hasSkillId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 skillId = 2;</code>
     *
     * <pre>
     *对应界面的几个按钮
     * </pre>
     */
    public int getSkillId() {
      return skillId_;
    }

    private void initFields() {
      truckId_ = 0L;
      skillId_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasTruckId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSkillId()) {
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
        output.writeInt64(1, truckId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, skillId_);
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
          .computeInt64Size(1, truckId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, skillId_);
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

    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction prototype) {
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
     * Protobuf type {@code ReqTruckSkillAction}
     *
     * <pre>
     *使用技能，主界面点按钮
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ReqTruckSkillAction)
        com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillActionOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.internal_static_ReqTruckSkillAction_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.internal_static_ReqTruckSkillAction_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.class, com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.newBuilder()
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
        truckId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        skillId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.internal_static_ReqTruckSkillAction_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction build() {
        com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction buildPartial() {
        com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction result = new com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.truckId_ = truckId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.skillId_ = skillId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction other) {
        if (other == com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction.getDefaultInstance()) return this;
        if (other.hasTruckId()) {
          setTruckId(other.getTruckId());
        }
        if (other.hasSkillId()) {
          setSkillId(other.getSkillId());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasTruckId()) {
          
          return false;
        }
        if (!hasSkillId()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.truck.ReqTruckSkillActionProto.ReqTruckSkillAction) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long truckId_ ;
      /**
       * <code>required int64 truckId = 1;</code>
       *
       * <pre>
       *镖车Id
       * </pre>
       */
      public boolean hasTruckId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int64 truckId = 1;</code>
       *
       * <pre>
       *镖车Id
       * </pre>
       */
      public long getTruckId() {
        return truckId_;
      }
      /**
       * <code>required int64 truckId = 1;</code>
       *
       * <pre>
       *镖车Id
       * </pre>
       */
      public Builder setTruckId(long value) {
        bitField0_ |= 0x00000001;
        truckId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 truckId = 1;</code>
       *
       * <pre>
       *镖车Id
       * </pre>
       */
      public Builder clearTruckId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        truckId_ = 0L;
        onChanged();
        return this;
      }

      private int skillId_ ;
      /**
       * <code>required int32 skillId = 2;</code>
       *
       * <pre>
       *对应界面的几个按钮
       * </pre>
       */
      public boolean hasSkillId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 skillId = 2;</code>
       *
       * <pre>
       *对应界面的几个按钮
       * </pre>
       */
      public int getSkillId() {
        return skillId_;
      }
      /**
       * <code>required int32 skillId = 2;</code>
       *
       * <pre>
       *对应界面的几个按钮
       * </pre>
       */
      public Builder setSkillId(int value) {
        bitField0_ |= 0x00000002;
        skillId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 skillId = 2;</code>
       *
       * <pre>
       *对应界面的几个按钮
       * </pre>
       */
      public Builder clearSkillId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        skillId_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:ReqTruckSkillAction)
    }

    static {
      defaultInstance = new ReqTruckSkillAction(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:ReqTruckSkillAction)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ReqTruckSkillAction_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_ReqTruckSkillAction_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031ReqTruckSkillAction.proto\"7\n\023ReqTruckS" +
      "killAction\022\017\n\007truckId\030\001 \002(\003\022\017\n\007skillId\030\002" +
      " \002(\005BB\n&com.chuangyou.common.protobuf.pb" +
      ".truckB\030ReqTruckSkillActionProto"
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
    internal_static_ReqTruckSkillAction_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ReqTruckSkillAction_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_ReqTruckSkillAction_descriptor,
        new java.lang.String[] { "TruckId", "SkillId", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}