// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pet/PetPhysiqueUpRespMsg.proto

package com.chuangyou.common.protobuf.pb.pet;

public final class PetPhysiqueUpRespProto {
  private PetPhysiqueUpRespProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PetPhysiqueUpRespMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PetPhysiqueUpRespMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 petId = 1;</code>
     *
     * <pre>
     * 宠物ID
     * </pre>
     */
    boolean hasPetId();
    /**
     * <code>optional int32 petId = 1;</code>
     *
     * <pre>
     * 宠物ID
     * </pre>
     */
    int getPetId();

    /**
     * <code>optional int32 physique = 2;</code>
     *
     * <pre>
     * 宠物炼体等级
     * </pre>
     */
    boolean hasPhysique();
    /**
     * <code>optional int32 physique = 2;</code>
     *
     * <pre>
     * 宠物炼体等级
     * </pre>
     */
    int getPhysique();
  }
  /**
   * Protobuf type {@code PetPhysiqueUpRespMsg}
   */
  public static final class PetPhysiqueUpRespMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:PetPhysiqueUpRespMsg)
      PetPhysiqueUpRespMsgOrBuilder {
    // Use PetPhysiqueUpRespMsg.newBuilder() to construct.
    private PetPhysiqueUpRespMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PetPhysiqueUpRespMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PetPhysiqueUpRespMsg defaultInstance;
    public static PetPhysiqueUpRespMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PetPhysiqueUpRespMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PetPhysiqueUpRespMsg(
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
              petId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              physique_ = input.readInt32();
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
      return com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.internal_static_PetPhysiqueUpRespMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.internal_static_PetPhysiqueUpRespMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.class, com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PetPhysiqueUpRespMsg> PARSER =
        new com.google.protobuf.AbstractParser<PetPhysiqueUpRespMsg>() {
      public PetPhysiqueUpRespMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PetPhysiqueUpRespMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PetPhysiqueUpRespMsg> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int PETID_FIELD_NUMBER = 1;
    private int petId_;
    /**
     * <code>optional int32 petId = 1;</code>
     *
     * <pre>
     * 宠物ID
     * </pre>
     */
    public boolean hasPetId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 petId = 1;</code>
     *
     * <pre>
     * 宠物ID
     * </pre>
     */
    public int getPetId() {
      return petId_;
    }

    public static final int PHYSIQUE_FIELD_NUMBER = 2;
    private int physique_;
    /**
     * <code>optional int32 physique = 2;</code>
     *
     * <pre>
     * 宠物炼体等级
     * </pre>
     */
    public boolean hasPhysique() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 physique = 2;</code>
     *
     * <pre>
     * 宠物炼体等级
     * </pre>
     */
    public int getPhysique() {
      return physique_;
    }

    private void initFields() {
      petId_ = 0;
      physique_ = 0;
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
        output.writeInt32(1, petId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, physique_);
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
          .computeInt32Size(1, petId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, physique_);
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

    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg prototype) {
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
     * Protobuf type {@code PetPhysiqueUpRespMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:PetPhysiqueUpRespMsg)
        com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.internal_static_PetPhysiqueUpRespMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.internal_static_PetPhysiqueUpRespMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.class, com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.newBuilder()
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
        petId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        physique_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.internal_static_PetPhysiqueUpRespMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg build() {
        com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg result = new com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.petId_ = petId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.physique_ = physique_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg.getDefaultInstance()) return this;
        if (other.hasPetId()) {
          setPetId(other.getPetId());
        }
        if (other.hasPhysique()) {
          setPhysique(other.getPhysique());
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
        com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.pet.PetPhysiqueUpRespProto.PetPhysiqueUpRespMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int petId_ ;
      /**
       * <code>optional int32 petId = 1;</code>
       *
       * <pre>
       * 宠物ID
       * </pre>
       */
      public boolean hasPetId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 petId = 1;</code>
       *
       * <pre>
       * 宠物ID
       * </pre>
       */
      public int getPetId() {
        return petId_;
      }
      /**
       * <code>optional int32 petId = 1;</code>
       *
       * <pre>
       * 宠物ID
       * </pre>
       */
      public Builder setPetId(int value) {
        bitField0_ |= 0x00000001;
        petId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 petId = 1;</code>
       *
       * <pre>
       * 宠物ID
       * </pre>
       */
      public Builder clearPetId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        petId_ = 0;
        onChanged();
        return this;
      }

      private int physique_ ;
      /**
       * <code>optional int32 physique = 2;</code>
       *
       * <pre>
       * 宠物炼体等级
       * </pre>
       */
      public boolean hasPhysique() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 physique = 2;</code>
       *
       * <pre>
       * 宠物炼体等级
       * </pre>
       */
      public int getPhysique() {
        return physique_;
      }
      /**
       * <code>optional int32 physique = 2;</code>
       *
       * <pre>
       * 宠物炼体等级
       * </pre>
       */
      public Builder setPhysique(int value) {
        bitField0_ |= 0x00000002;
        physique_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 physique = 2;</code>
       *
       * <pre>
       * 宠物炼体等级
       * </pre>
       */
      public Builder clearPhysique() {
        bitField0_ = (bitField0_ & ~0x00000002);
        physique_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PetPhysiqueUpRespMsg)
    }

    static {
      defaultInstance = new PetPhysiqueUpRespMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PetPhysiqueUpRespMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PetPhysiqueUpRespMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PetPhysiqueUpRespMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036pet/PetPhysiqueUpRespMsg.proto\"7\n\024PetP" +
      "hysiqueUpRespMsg\022\r\n\005petId\030\001 \001(\005\022\020\n\010physi" +
      "que\030\002 \001(\005B>\n$com.chuangyou.common.protob" +
      "uf.pb.petB\026PetPhysiqueUpRespProto"
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
    internal_static_PetPhysiqueUpRespMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PetPhysiqueUpRespMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PetPhysiqueUpRespMsg_descriptor,
        new java.lang.String[] { "PetId", "Physique", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
