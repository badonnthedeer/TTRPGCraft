package com.badonnthedeer.ttrpg_craft.datagen;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.badonnthedeer.ttrpg_craft.TTRPGCraft.LOGGER;

@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        LOGGER.debug("Begin Datagen");

        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        LOGGER.debug("CombinedBuiltInProvider running");
        //Combined BuiltInEntriesProvider. Docs allude to sticking it directly in the gatherData function, but I like the organization Kaupenjoe provides.
        generator.addProvider(event.includeServer(), new CombinedBuiltInProvider(packOutput, lookupProvider, CombinedBuiltInProvider.BUILDER, Set.of(TTRPGCraft.MOD_ID)));

        generator.addProvider(event.includeServer(), new ModDamageTypeTagProvider(packOutput, lookupProvider, TTRPGCraft.MOD_ID, existingFileHelper));

        LOGGER.debug("End datagen");
    }
}
