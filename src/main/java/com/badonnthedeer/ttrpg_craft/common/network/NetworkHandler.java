package com.badonnthedeer.ttrpg_craft.common.network;


import com.badonnthedeer.ttrpg_craft.common.network.server.ServerPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


public class NetworkHandler
{

    public static void register(final PayloadRegistrar registrar)
    {
        //Client Packets
        //registrar.playToServer(CPacketOpenCharSheet.TYPE, CPacketOpenCharSheet.STREAM_CODEC,
        //        ServerPayloadHandler.getInstance()::handleOpenCharSheet);


        // Server Packets
        //registrar.playToClient(SPacketQuickMove.TYPE, SPacketQuickMove.STREAM_CODEC,
        //        CuriosClientPayloadHandler.getInstance()::handle);
    }
}