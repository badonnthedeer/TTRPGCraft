package com.badonnthedeer.ttrpg_craft.network;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import com.badonnthedeer.ttrpg_craft.client.ClientPayloadHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class NetworkRegistration {

    private NetworkRegistration() {} // optional private constructor to prevent instantiation

    @SubscribeEvent
    public static void registerPayloads(final RegisterPayloadHandlersEvent event) {
        // 1) Get the registrar for your protocol version (in this example, "1")
        final PayloadRegistrar registrar = event.registrar("1");

        // 2) Register your incapacitated payload for both directions if you want:
        registrar.playBidirectional(
                IsImmobileData.TYPE,               // The unique type (IncapacitatedData.TYPE)
                IsImmobileData.STREAM_CODEC,       // The codec to encode/decode
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleIsImmobileOnMain,  // What to do on the client
                        ServerPayloadHandler::handleIsImmobileOnMain   // What to do on the server
                )
        );
        registrar.playBidirectional(
                ClearForcedPoseData.TYPE,
                ClearForcedPoseData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleClearForcedPoseOnMain,
                        ServerPayloadHandler::handleClearForcedPoseOnMain
                )
        );
    }
}