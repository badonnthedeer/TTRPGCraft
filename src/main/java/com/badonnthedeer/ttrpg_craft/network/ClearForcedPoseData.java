package com.badonnthedeer.ttrpg_craft.network;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;


public record ClearForcedPoseData(int entityId, boolean removePose) implements CustomPacketPayload {

    // 1) Unique resource location for this payload

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(TTRPGCraft.MOD_ID, "clear_forced_pose_sync");

    // 2) The "type" ties the ID to this class
    public static final Type<ClearForcedPoseData> TYPE = new Type<>(ID);

    // 3) Define a StreamCodec that knows how to read/write our record fields
    //    In order: we read/write an int, then a boolean, then call 'new IncapacitatedData(...)'
    public static final StreamCodec<FriendlyByteBuf, ClearForcedPoseData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,     // how to read/write entityId
            ClearForcedPoseData::entityId,
            ByteBufCodecs.BOOL,     // how to read/write incapacitated
            ClearForcedPoseData::removePose,
            ClearForcedPoseData::new     // constructor reference (int, boolean)
    );

    // 4) Must override 'type()' to return the TYPE
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
