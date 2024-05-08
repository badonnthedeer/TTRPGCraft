package com.badonnthedeer.ttrpg_craft.common.network.client;

import javax.annotation.Nonnull;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CPacketOpenCharSheet() implements CustomPacketPayload
{

    public static final Type<CPacketOpenCharSheet> TYPE =
            new Type<>(new ResourceLocation(TTRPGCraft.MOD_ID, "open_char_sheet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CPacketOpenCharSheet> STREAM_CODEC =
            StreamCodec.composite(CPacketOpenCharSheet::carried, CPacketOpenCharSheet::new);

    @Nonnull
    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }
}
