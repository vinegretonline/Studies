package com.vinegretonline.opalikhovets.network;

import com.vinegretonline.opalikhovets.OpalikhovetsMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** Server -> client: "you have just been photographed", shows the white flash overlay. */
public record PhotoFlashPayload() implements CustomPacketPayload {
    public static final PhotoFlashPayload INSTANCE = new PhotoFlashPayload();
    public static final Type<PhotoFlashPayload> TYPE = new Type<>(OpalikhovetsMod.id("photo_flash"));
    public static final StreamCodec<ByteBuf, PhotoFlashPayload> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
