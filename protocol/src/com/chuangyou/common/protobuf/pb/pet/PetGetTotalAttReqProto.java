// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pet/PetGetTotalAttReqMsg.proto

package com.chuangyou.common.protobuf.pb.pet;

public final class PetGetTotalAttReqProto {
  private PetGetTotalAttReqProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PetGetTotalAttReqMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:PetGetTotalAttReqMsg)
      com.google.protobuf.MessageOrBuilder {
  }
  /**
   * Protobuf type {@code PetGetTotalAttReqMsg}
   */
  public static final class PetGetTotalAttReqMsg extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:PetGetTotalAttReqMsg)
      PetGetTotalAttReqMsgOrBuilder {
    // Use PetGetTotalAttReqMsg.newBuilder() to construct.
    private PetGetTotalAttReqMsg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PetGetTotalAttReqMsg(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PetGetTotalAttReqMsg defaultInstance;
    public static PetGetTotalAttReqMsg getDefaultInstance() {
      return defaultInstance;
    }

    public PetGetTotalAttReqMsg getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PetGetTotalAttReqMsg(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
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
      return com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.internal_static_PetGetTotalAttReqMsg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.internal_static_PetGetTotalAttReqMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.class, com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.Builder.class);
    }

    public static com.google.protobuf.Parser<PetGetTotalAttReqMsg> PARSER =
        new com.google.protobuf.AbstractParser<PetGetTotalAttReqMsg>() {
      public PetGetTotalAttReqMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PetGetTotalAttReqMsg(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PetGetTotalAttReqMsg> getParserForType() {
      return PARSER;
    }

    private void initFields() {
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
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
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

    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg prototype) {
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
     * Protobuf type {@code PetGetTotalAttReqMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:PetGetTotalAttReqMsg)
        com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.internal_static_PetGetTotalAttReqMsg_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.internal_static_PetGetTotalAttReqMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.class, com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.Builder.class);
      }

      // Construct using com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.newBuilder()
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
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.internal_static_PetGetTotalAttReqMsg_descriptor;
      }

      public com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg getDefaultInstanceForType() {
        return com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.getDefaultInstance();
      }

      public com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg build() {
        com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg buildPartial() {
        com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg result = new com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg(this);
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg) {
          return mergeFrom((com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg other) {
        if (other == com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg.getDefaultInstance()) return this;
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
        com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.chuangyou.common.protobuf.pb.pet.PetGetTotalAttReqProto.PetGetTotalAttReqMsg) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PetGetTotalAttReqMsg)
    }

    static {
      defaultInstance = new PetGetTotalAttReqMsg(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PetGetTotalAttReqMsg)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_PetGetTotalAttReqMsg_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PetGetTotalAttReqMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036pet/PetGetTotalAttReqMsg.proto\"\026\n\024PetG" +
      "etTotalAttReqMsgB>\n$com.chuangyou.common" +
      ".protobuf.pb.petB\026PetGetTotalAttReqProto"
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
    internal_static_PetGetTotalAttReqMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_PetGetTotalAttReqMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_PetGetTotalAttReqMsg_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
