package com.badonnthedeer.ttrpg_craft.common.network.server;

import com.badonnthedeer.ttrpg_craft.common.network.client.CPacketOpenCharSheet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {

        private static final ServerPayloadHandler INSTANCE = new ServerPayloadHandler();

        public static ServerPayloadHandler getInstance() {
            return INSTANCE;
        }

        public void handleOpenCurios(final CPacketOpenCharSheet data, final IPayloadContext ctx) {
            ctx.enqueueWork(() -> {
                Player player = ctx.player();

                if (player instanceof ServerPlayer serverPlayer)
                {
                    player.openMenu(new CuriosContainerProvider());
                }
            });
        }
}
