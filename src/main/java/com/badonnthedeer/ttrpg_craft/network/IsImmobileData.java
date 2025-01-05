package com.badonnthedeer.ttrpg_craft.network;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;


/**
 * A simple payload carrying:
 *  - entityId: The ID of the entity whose state is changing
 *  - incapacitated: True if incapacitated, false if not
 */
public record IsImmobileData(int entityId, boolean isImmobile) implements CustomPacketPayload {

    // 1) Unique resource location for this payload

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "is_immobile_sync");

    // 2) The "type" ties the ID to this class
    public static final Type<IsImmobileData> TYPE = new CustomPacketPayload.Type<>(ID);

    // 3) Define a StreamCodec that knows how to read/write our record fields
    //    In order: we read/write an int, then a boolean, then call 'new IncapacitatedData(...)'
    public static final StreamCodec<FriendlyByteBuf, IsImmobileData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,     // how to read/write entityId
            IsImmobileData::entityId,
            ByteBufCodecs.BOOL,     // how to read/write incapacitated
            IsImmobileData::isImmobile,
            IsImmobileData::new     // constructor reference (int, boolean)
    );

    // 4) Must override 'type()' to return the TYPE
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
