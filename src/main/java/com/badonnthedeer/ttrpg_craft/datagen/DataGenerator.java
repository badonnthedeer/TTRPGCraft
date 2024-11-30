package com.badonnthedeer.ttrpg_craft.datagen;

import com.badonnthedeer.ttrpg_craft.TTRPGCraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.internal.NeoForgeDamageTypeTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TTRPGCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //Combined BuiltInEntriesProvider. Docs allude to sticking it directly in the gatherData function, but I like the organization Kaupenjoe provides.
        generator.addProvider(event.includeServer(), new CombinedBuiltInProvider(packOutput, lookupProvider, CombinedBuiltInProvider.BUILDER, Set.of(TTRPGCraft.MOD_ID)));

    }
}
